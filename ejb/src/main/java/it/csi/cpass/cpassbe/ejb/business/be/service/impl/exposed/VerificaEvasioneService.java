/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.csi.cpass.cpassbe.ejb.business.be.service.impl.exposed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FruitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.exposed.VerificaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.exposed.VerificaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fruitore;
import it.csi.cpass.cpassbe.lib.dto.Servizio;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;
import it.csi.cpass.cpassbe.lib.exposed.dto.Impegno;



/**
 * Service Verifica Evasione
 * verificare l’esistenza di evasioni autorizzate e non ancora totalmente fatturate ed i relativi impegni con importi.
 */
public class VerificaEvasioneService extends BaseExposedService<VerificaEvasioneRequest, VerificaEvasioneResponse> {

	private final FruitoreDad fruitoreDad;
	private final EnteDad enteDad;
	private final TestataOrdineDad testataOrdineDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	private final DecodificaDad decodificaDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private Evasione evasioni;


	//private static final Pattern REGEX_ORDINI = Pattern.compile("^(\\d{4}\\/\\d{1,10};){1,}$");
	private static final Pattern REGEX_ANNODOCUMENTO = Pattern.compile("^\\d{4}$");
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 */
	// aggiungere qui i dad passati in input
	public VerificaEvasioneService(ConfigurationHelper configurationHelper,FruitoreDad fruitoreDad, EnteDad enteDad, TestataOrdineDad testataOrdineDad, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad, DecodificaDad decodificaDad, DestinatarioEvasioneDad destinatarioEvasioneDad) {
		super(configurationHelper);
		this.fruitoreDad = fruitoreDad;
		this.enteDad = enteDad;
		this.testataOrdineDad = testataOrdineDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
		this.decodificaDad = decodificaDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		evasioni = request.getEvasioni();
		//		checkCondition(StringUtils.isNotEmpty(evasioni.getOrdini()) && REGEX_ORDINI.matcher(evasioni.getOrdini()).matches(), MsgCpassOrd.ORDSRV0002.getError());
		checkCondition(!evasioni.getOrdini().isEmpty(), MsgCpassOrd.ORDSRV0002.getError());
		checkCondition(StringUtils.isNotEmpty(evasioni.getAnnoDocumento())  && REGEX_ANNODOCUMENTO.matcher(evasioni.getAnnoDocumento()).matches(), MsgCpassOrd.ORDSRV0003.getError());
		checkCondition(StringUtils.isNotEmpty(evasioni.getNumeroDocumento()),  MsgCpassOrd.ORDSRV0004.getError());
		checkCondition(StringUtils.isNotEmpty(evasioni.getTipoDocumento()),  MsgCpassOrd.ORDSRV0005.getError());
		checkCondition(evasioni.getCodiceFornitore()!=null,  MsgCpassOrd.ORDSRV0006.getError());
		checkCondition(StringUtils.isNotEmpty(evasioni.getCodiceFruitore()),  MsgCpassOrd.ORDSRV0008.getError());
		checkCondition(StringUtils.isNotEmpty(evasioni.getCodiceFiscaleEnte()),  MsgCpassOrd.ORDSRV0009.getError());
		//		Impegno impegnoMonco = evasioni.getImpegni().stream().filter(x -> x.getAnnoImpegno()==null || x.getNumeroImpegno()==null ||
		//				x.getAnnoSubimpegno()==null || x.getNumeroSubimpegno()==null || x.getImportoQuota()==null).findAny().orElse(null);
		final Impegno impegnoMonco = evasioni.getImpegni().stream().filter(x -> x.getAnnoImpegno()==null || x.getNumeroImpegno()==null || x.getImportoQuota()==null).findAny().orElse(null);
		checkCondition(!evasioni.getImpegni().isEmpty() && impegnoMonco==null,  MsgCpassOrd.ORDSRV0007.getError());

	}
	@Override
	protected void paramExceptionPreReturn() {
		response.setEsito("KO");
	}
	@Override
	protected void runtimeExceptionPreReturn(RuntimeException re) {
		response.setEsito("KO");
	}

	@Override
	protected void execute() {
		log.info("execute()", "check permessi fruitore");
		final Fruitore fruitore = isEntityPresent(() -> fruitoreDad.getFruitore(evasioni.getCodiceFruitore(),evasioni.getCodiceFiscaleEnte()), "fruitore"); // cosa succede se fruitore non trovato? testare
		final List<Servizio> servizios  = fruitoreDad.getServizioByUtenteAndSettoreAndModulo(fruitore.getId());
		final Servizio verifica_evasione = servizios.stream().filter(x -> x.getCodice().equals("VERIFICA_EVASIONE")).findAny().orElse(null);
		checkBusinessCondition(verifica_evasione!=null,  MsgCpassOrd.ORDSRV0010.getError());

		log.info("execute()", "get ente by codiceFiscaleEnte");
		final Ente ente = isEntityPresent(() -> enteDad.getEnteByCodiceFiscale(evasioni.getCodiceFiscaleEnte()), "ente"); // cosa succede se ente non trovato? testare

		log.info("execute()", "get ordini by ordini input");
		final List<UUID> idOrdines = new ArrayList<>();
		//		for (String token : evasioni.getOrdini().split(";")) {
		for (final String token : evasioni.getOrdini()) {
			final String annoOrdine = token.substring(0,token.indexOf("/"));
			final String numeroOrdine = token.substring(token.indexOf("/")+1);
			final TestataOrdine testataOrdine = testataOrdineDad.getByAnnoENumeroEStato(Integer.parseInt(annoOrdine), Integer.parseInt(numeroOrdine),StatoOrdineEnum.AUTORIZZATO.getCostante(), ente.getId());
			// da testare: se in piu casi l'ordine non c'è il codice è aggiunto + volte
			checkBusinessCondition(testataOrdine!=null,  MsgCpassOrd.ORDSRV0011.getError());
			idOrdines.add(testataOrdine.getId());
		}

		log.info("execute()", "get righe evasioni by ordini input");
		// ricerca delle righe evasioni per ordine
		final List<RigaEvasione>  rigaEvasiones = new ArrayList<>();
		for(final UUID idOrdine : idOrdines) {
			final List<RigaEvasione> result = rigaEvasioneDad.getRigheByOrdineEDocumento(idOrdines, idOrdine, Integer.parseInt(evasioni.getAnnoDocumento()),  evasioni.getNumeroDocumento(), evasioni.getTipoDocumento(), evasioni.getCodiceFornitore().toString());
			checkBusinessCondition(!result.isEmpty(),  MsgCpassOrd.ORDSRV0012.getError());
			rigaEvasiones.addAll(result);
		}

		log.info("execute()", "distinct impegni input, sum importoQuota");
		final Map<String, BigDecimal> ordiniMap = new HashMap<>();
		for (final Impegno impegno: evasioni.getImpegni()) {
			if (impegno.getAnnoSubimpegno()==null) {
				impegno.setAnnoSubimpegno(0);
			}
			if (impegno.getNumeroSubimpegno()==null) {
				impegno.setNumeroSubimpegno(0);
			}
			final String key = impegno.getAnnoImpegno()+"-"+impegno.getNumeroImpegno()+"-"+impegno.getAnnoSubimpegno()+"-"+impegno.getNumeroSubimpegno();
			final BigDecimal value = ordiniMap.get(key) == null ? impegno.getImportoQuota() : ordiniMap.get(key).add(impegno.getImportoQuota());
			ordiniMap.put(key, value);
		}
		final List<Impegno> impegnos = new ArrayList<>();
		final List<Impegno> subimpegnos = new ArrayList<>();
		for (String key : ordiniMap.keySet()) {
			final String[] token = key.split("-");
			final Impegno impegno = new Impegno();
			impegno.setAnnoImpegno(Integer.parseInt(token[0]));
			impegno.setNumeroImpegno(Integer.parseInt(token[1]));
			impegno.setAnnoSubimpegno(Integer.parseInt(token[2]));
			impegno.setNumeroSubimpegno(Integer.parseInt(token[3]));
			impegno.setImportoQuota(ordiniMap.get(key));
			if (impegno.getAnnoSubimpegno()==null || impegno.getAnnoSubimpegno()==0) {
				impegnos.add(impegno);
			} else {
				subimpegnos.add(impegno);
			}
		}

		log.info("execute()", "check per ogni impegno del legame con almeno una rigaEvasione con quota disponibile ");
		for(final Impegno impegno : impegnos) {
			final List<ImpegnoEvasione> entities = impegnoEvasioneDad.getByIdsRigaEvasioneEImpegno(rigaEvasiones, impegno.getAnnoImpegno(), impegno.getNumeroImpegno());
			final ImpegnoEvasione entityConSub = entities.stream().filter(x -> !x.getSubimpegnoEvasiones().isEmpty()).findAny().orElse(null);
			checkBusinessCondition(entityConSub == null, MsgCpassOrd.ORDSRV0017.getError("anno", impegno.getAnnoImpegno(), "numero",impegno.getNumeroImpegno()),false);
			checkBusinessCondition(!entities.isEmpty(), MsgCpassOrd.ORDSRV0014.getError("anno", impegno.getAnnoImpegno(), "numero",impegno.getNumeroImpegno(),
					"annoSub", impegno.getAnnoSubimpegno(), "numeroSub", impegno.getNumeroSubimpegno()),false);
			if (entityConSub == null && entities != null) {
				// check ripartizione impegni
				final BigDecimal importoRipartito = entities.stream().map(x -> x.getImportoRipartito()!= null ? x.getImportoRipartito():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				final BigDecimal importoSospeso = entities.stream().map(x -> x.getImportoSospeso()!= null ? x.getImportoSospeso():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				final BigDecimal importoLiquidato = entities.stream().map(x -> x.getImportoLiquidato()!= null ? x.getImportoLiquidato():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				checkBusinessCondition(importoRipartito.add(importoSospeso).subtract(importoLiquidato).compareTo(impegno.getImportoQuota())>0, MsgCpassOrd.ORDSRV0013.getError(),false);
			}
		}

		log.info("execute()", "check per ogni subimpegno del legame con almeno una rigaEvasione con quota disponibile ");
		for(final Impegno impegno : subimpegnos) {
			final List<SubimpegnoEvasione> entities = impegnoEvasioneDad.getByIdsRigaEvasioneESubimpegno(rigaEvasiones, impegno.getAnnoImpegno(), impegno.getNumeroImpegno(),impegno.getAnnoSubimpegno(), impegno.getNumeroSubimpegno());
			checkBusinessCondition(!entities.isEmpty(), MsgCpassOrd.ORDSRV0014.getError("anno", impegno.getAnnoImpegno(), "numero",impegno.getNumeroImpegno(),
					"annoSub", impegno.getAnnoSubimpegno(), "numeroSub", impegno.getNumeroSubimpegno()),false);
			if(!entities.isEmpty()) {
				final BigDecimal importoRipartito = entities.stream().map(x -> x.getImportoRipartito()!= null ? x.getImportoRipartito():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				final BigDecimal importoSospeso = entities.stream().map(x -> x.getImportoSospeso()!= null ? x.getImportoSospeso():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				final BigDecimal importoLiquidato = entities.stream().map(x -> x.getImportoLiquidato()!= null ? x.getImportoLiquidato():BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				checkBusinessCondition(importoRipartito.add(importoSospeso).subtract(importoLiquidato).compareTo(impegno.getImportoQuota())>0, MsgCpassOrd.ORDSRV0013.getError(),false);
			}
		}
		if (!response.getApiErrors().isEmpty()) {
			response.setEsito("KO");
			return;
		}

		log.info("execute()", "Aggiornamento quote per impegni");
		for(final Impegno impegno : impegnos) {
			final List<ImpegnoEvasione> entities = impegnoEvasioneDad.getByIdsRigaEvasioneEImpegno(rigaEvasiones, impegno.getAnnoImpegno(), impegno.getNumeroImpegno());
			BigDecimal importoQuota = impegno.getImportoQuota();
			for(final ImpegnoEvasione impegnoEvasione : entities) {
				final BigDecimal importoRipartito = impegnoEvasione.getImportoRipartito()!= null ? impegnoEvasione.getImportoRipartito() :BigDecimal.ZERO;
				final BigDecimal importoSospeso = impegnoEvasione.getImportoSospeso()!= null ? impegnoEvasione.getImportoSospeso() :BigDecimal.ZERO;
				final BigDecimal importoLiquidato = impegnoEvasione.getImportoLiquidato()!= null ? impegnoEvasione.getImportoLiquidato() :BigDecimal.ZERO;
				final BigDecimal importoResiduo = importoRipartito.add(importoSospeso).subtract(importoLiquidato);
				if (importoResiduo.compareTo(BigDecimal.ZERO) == 0) {
					continue;
				}
				if (importoResiduo.compareTo(importoQuota) < 0) {
					impegnoEvasione.setImportoLiquidato(importoRipartito.add(importoSospeso));
					importoQuota = importoQuota.subtract(importoResiduo);
				} else {
					impegnoEvasione.setImportoLiquidato(importoLiquidato.add(importoQuota));
					importoQuota = BigDecimal.ZERO;
				}
				impegnoEvasioneDad.update(impegnoEvasione);
				if (importoQuota == BigDecimal.ZERO) {
					break;
				}

			}
		}

		log.info("execute()", "Aggiornamento quote per subimpegni");
		for(final Impegno subimpegno : subimpegnos) {
			final List<SubimpegnoEvasione> entities = impegnoEvasioneDad.getByIdsRigaEvasioneESubimpegno(rigaEvasiones, subimpegno.getAnnoImpegno(), subimpegno.getNumeroImpegno(),subimpegno.getAnnoSubimpegno(), subimpegno.getNumeroSubimpegno());
			BigDecimal importoQuota = subimpegno.getImportoQuota();
			for(final SubimpegnoEvasione subimpegnoEvasione : entities) {
				final BigDecimal importoRipartito = subimpegnoEvasione.getImportoRipartito()!= null ? subimpegnoEvasione.getImportoRipartito() :BigDecimal.ZERO;
				final BigDecimal importoSospeso = subimpegnoEvasione.getImportoSospeso()!= null ? subimpegnoEvasione.getImportoSospeso() :BigDecimal.ZERO;
				final BigDecimal importoLiquidato = subimpegnoEvasione.getImportoLiquidato()!= null ? subimpegnoEvasione.getImportoLiquidato() :BigDecimal.ZERO;
				final BigDecimal importoResiduo = importoRipartito.add(importoSospeso).subtract(importoLiquidato);

				if (importoResiduo.compareTo(BigDecimal.ZERO) == 0) {
					continue;
				}

				final ImpegnoEvasione impegnoEvasione = impegnoEvasioneDad.getImpegnoEvasione(subimpegnoEvasione.getImpegnoEvasione().getId())
						.orElseThrow(() -> new NotFoundException("intervento"));
				checkBusinessCondition(impegnoEvasione.getImportoRipartito().add(impegnoEvasione.getImportoSospeso()).subtract(impegnoEvasione.getImportoLiquidato()).compareTo(importoQuota)>=0, MsgCpassOrd.ORDSRV0013.getError());// throw exception;
				impegnoEvasione.setImportoLiquidato(impegnoEvasione.getImportoLiquidato().add(importoQuota));
				impegnoEvasioneDad.update(impegnoEvasione);

				if(importoResiduo.compareTo(importoQuota)<0) {
					subimpegnoEvasione.setImportoLiquidato(importoRipartito.add(importoSospeso));
					importoQuota = importoQuota.subtract(importoResiduo);
				}else {
					subimpegnoEvasione.setImportoLiquidato(importoLiquidato.add(importoQuota));
					importoQuota = BigDecimal.ZERO;
				}
				subimpegnoEvasioneDad.update(subimpegnoEvasione);
				if (importoQuota == BigDecimal.ZERO) {
					break;
				}
			}
		}

		log.info("execute()", "Aggiornamento stato righe evasione");
		final Stato rigaEvasioneTotalementeFatturata = decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_TOTALMENTE_FATTURATA.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante());
		final Stato rigaEvasioneParzialmenteFatturata = decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_PARZIALMENTE_FATTURATA.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante());
		for (final RigaEvasione rigaEvasione: rigaEvasiones) {
			final List<ImpegnoEvasione> entities = impegnoEvasioneDad.getByIdRigaEvasione(rigaEvasione.getId());
			checkBusinessCondition(!entities.isEmpty(), MsgCpassOrd.ORDSRV0015.getError()); // lanciare un altro tipo di errore?
			final BigDecimal importoRipartito = entities.stream().map(x -> x.getImportoRipartito()!= null ? x.getImportoRipartito():BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			final BigDecimal importoSospeso = entities.stream().map(x -> x.getImportoSospeso()!= null ? x.getImportoSospeso():BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			final BigDecimal importoLiquidato = entities.stream().map(x -> x.getImportoLiquidato()!= null ? x.getImportoLiquidato():BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			checkBusinessCondition(importoLiquidato.compareTo(importoRipartito.add(importoSospeso)) <= 0, MsgCpassOrd.ORDSRV0015.getError()); // lanciare un altro tipo di errore?
			if (importoLiquidato.compareTo(importoRipartito.add(importoSospeso))==0) {
				rigaEvasione.setStato(rigaEvasioneTotalementeFatturata);
			} else {
				rigaEvasione.setStato(rigaEvasioneParzialmenteFatturata);
			}
			rigaEvasioneDad.updateRigaEvasione(rigaEvasione);
		}

		final List<UUID> uuidDestinatarioEvasiones = rigaEvasiones.stream().map(x -> x.getDestinatarioEvasione().getId()).distinct().collect(Collectors.toList());
		for (final UUID uuid: uuidDestinatarioEvasiones){
			final List<RigaEvasione> entities = rigaEvasioneDad.getRigheByDestinatarioEvasione(uuid);
			checkBusinessCondition(!entities.isEmpty(), MsgCpassOrd.ORDSRV0015.getError()); // lanciare un altro tipo di errore?
			final RigaEvasione entityNonEvasaTotalmente = entities.stream().filter(x -> !x.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_TOTALMENTE_FATTURATA.getCostante())).findAny().orElse(null);
			if (entityNonEvasaTotalmente == null) {
				destinatarioEvasioneDad.updateStatoDestinatarioEvasione(uuid, ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO.getCostante());
			}else {
				destinatarioEvasioneDad.updateStatoDestinatarioEvasione(uuid, ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_PARZIALMENTE_FATTURATO.getCostante());
			}
		}

		response.setEsito("OK");
	}
}
