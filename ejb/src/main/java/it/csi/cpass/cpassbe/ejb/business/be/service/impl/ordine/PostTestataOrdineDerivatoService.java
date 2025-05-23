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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostTestataOrdineDerivatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.TipoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.OrdRdaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an TestataOrdine
 */
public class PostTestataOrdineDerivatoService extends BaseOrdineService<PostTestataOrdineRequest, PostTestataOrdineDerivatoResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	private final DecodificaDad decodificaDad;
	private final RmsDad rmsDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final RdaDad rdaDad;
	private final UtenteDad utenteDad;
	private final SystemDad systemDad;
	private final ImpegnoDad impegnoDad;
	private final CommonDad commonDad;

	private TestataOrdine testataOrdine;

	/**
	 * Constructor
	 *
	 * @param configurationHelper 	the configuration helper
	 * @param testataOrdineDad    	the testataOrdine DAD
	 * @param decodificaDad       	the decodifica DAD
	 * @param rmsDad              	the rms DAD
	 * @param DestinatarioOrdineDad the Destinatario Dad
	 * @param RigaOrdineDad 		the rigaOrdine Dad
	 * @param RdaDad rdaDad  		the rda Dad
	 * @param UtenteDad utenteDad 	the utente Dad
	 * @param SystemDad systemDad 	the system Dad
	 * @param ImpegnoDad impegnoDad the impegno Dad

	 */
	public PostTestataOrdineDerivatoService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataOrdineDad testataOrdineDad, DecodificaDad decodificaDad, RmsDad rmsDad, DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad, RdaDad rdaDad
			, UtenteDad utenteDad, SystemDad systemDad, ImpegnoDad impegnoDad, CommonDad commonDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.externalHelperLookup = externalHelperLookup;
		this.decodificaDad = decodificaDad;
		this.rmsDad = rmsDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.rdaDad = rdaDad;
		this.utenteDad = utenteDad;
		this.systemDad = systemDad;
		this.impegnoDad = impegnoDad;
		this.commonDad = commonDad;
	}

	@Override
	protected void checkServiceParams() {
		testataOrdine = request.getTestataOrdine();
		checkNotNull(testataOrdine, "testataOrdine", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final Utente utenteConnesso =CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		// Impostazione dello stato a bozza
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()), "stato"));
		final List<ApiError> apiErrors = checkFornitoreValido(externalHelperLookup,testataOrdine,enteId);
		response.addApiErrors(apiErrors);
		if (apiErrors == null || apiErrors.size() == 0) {
			final UUID rdaId = testataOrdine.getRdas().get(0).getId();
			testataOrdine.setTotaleConIva(BigDecimal.ZERO);
			testataOrdine.setTotaleNoIva(BigDecimal.ZERO);
			testataOrdine = testataOrdineDad.insertTestataOrdine(testataOrdine);

			final List<RigaRms> listaRigaRmsByTestataRda = rmsDad.getRigheRmsByRdaIdReverse(rdaId);
			for(final RigaRms rigaRms : listaRigaRmsByTestataRda) {
				final List<RigaRda> rigaRda = rdaDad.getRigaRdaByRmsIdAndTestataRda(rigaRms.getId(), rdaId);
				rigaRms.setRigaRda(rigaRda);
			}




			checkBusinessCondition(!listaRigaRmsByTestataRda.isEmpty(), CoreError.GENERIC_ERROR.getError(), false); // corretto ?
			final Stato statoDestinatarioOrdine = decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante(),ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante());
			final Stato statoRigaOrdine = decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
			final Stato evtRigaRda = decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRdaEnum.EVT.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante()).orElseThrow(() -> new NotFoundException("stato riga rda")) ;
			final Stato evtRigaRms = decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRmsEnum.EVT.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()).orElseThrow(() -> new NotFoundException("stato riga rms")) ;
			final Stato evpRigaRms = decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRmsEnum.EVP.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()).orElseThrow(() -> new NotFoundException("stato riga rms")) ;

			// raggruppa le rms per settore destinatario
			final Map<UUID, List<RigaRms>> mapDestinatario = listaRigaRmsByTestataRda.stream().collect(Collectors.groupingBy(a-> a.getTestataRms().getSettoreDestinatario().getId()));
			for(final List<RigaRms> valueMapDestinatario : mapDestinatario.values()) {



				// righe delle rms con settore indirizzo non valorizzato -> generano un destinatario ordine
				final List<RigaRms> rmsSenzaIndirizzoSecondario = valueMapDestinatario.stream().filter((x) -> x.getTestataRms().getSettoreIndirizzo()==null || x.getTestataRms().getSettoreIndirizzo().getId()==null).collect(Collectors.toList());




				// righe delle rms con settore indirizzo valorizzato -> sono da raggruppare per indirizzo, ogni indirizzo genera un destinatario ordine
				final List<RigaRms> rmsConIndirizzoSecondario = valueMapDestinatario.stream().filter((x) -> x.getTestataRms().getSettoreIndirizzo()!=null && x.getTestataRms().getSettoreIndirizzo().getId()!=null).collect(Collectors.toList());







				if (!rmsSenzaIndirizzoSecondario.isEmpty()) {
					salvaDestinatarioConRigheOrdine(statoDestinatarioOrdine, rmsSenzaIndirizzoSecondario.get(0).getTestataRms(), rmsSenzaIndirizzoSecondario, statoRigaOrdine);
				}
				if (!rmsConIndirizzoSecondario.isEmpty()) {

					// raggruppa per indirizzo settore le rms con indirizzo settore valorizzato
					final Map<Integer, List<RigaRms>> mapIndirizzo =  rmsConIndirizzoSecondario.stream().collect(Collectors.groupingBy(a-> a.getTestataRms().getSettoreIndirizzo().getId()));

					for(final List<RigaRms> valueMapIndirizzo : mapIndirizzo.values()) {

						salvaDestinatarioConRigheOrdine(statoDestinatarioOrdine, valueMapIndirizzo.get(0).getTestataRms(), valueMapIndirizzo, statoRigaOrdine);

					}
				}
			}

			testataOrdineDad.updateTestataOrdineLigth(testataOrdine);
			//aggiornamento totali
			final TestataRda testataRda = rdaDad.getTestataRdaById(rdaId).orElseThrow(() -> new NotFoundException("testata rda"));
			//Occorre inserire un record in CPASS_R_ORD_RDA_ORDINE
			final OrdRdaOrdine ordRdaOrdine = new OrdRdaOrdine();
			ordRdaOrdine.setTestataOrdine(testataOrdine);
			ordRdaOrdine.setTestatarda(testataRda);
			testataOrdineDad.saveOrdRdaOrdine(ordRdaOrdine);

			//Occorre aggiornare tutte le righe della RDA
			for (final RigaRda rigaRda : testataRda.getRigaRda()) {
				rigaRda.setStato(evtRigaRda);
				rigaRda.setUtenteModifica( utenteConnesso.getCodiceFiscale());
				rdaDad.updateRigaRda(rigaRda);
			}

			//Occorre aggiornare le righe RMS confluite nell’ordine
			for (final RigaRms rigaRms : listaRigaRmsByTestataRda) {
				rigaRms.setUtenteModifica( utenteConnesso.getCodiceFiscale());


				if(rigaRms.getQuantita().equals(rigaRms.getQuantitaSuRda())) {
					final BigDecimal qtaSuOrd = rmsDad.getQuantitaNonEvasaSuOrdine(rigaRms.getId());
					if(qtaSuOrd.equals(BigDecimal.ZERO)) {
						rigaRms.setStato(evtRigaRms);
					}else {
						rigaRms.setStato(evpRigaRms);
					}
				}else {
					rigaRms.setStato(evpRigaRms);
				}


				rmsDad.updateRigaRms(rigaRms);

				final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
				statiRigaRms.setRigaRms(rigaRms);
				statiRigaRms.setStato(rigaRms.getStato().getDescrizione());
				statiRigaRms.setDataModifica(new Date());
				statiRigaRms.setUtenteModifica(utenteConnesso.getCodiceFiscale());
				rmsDad.insertStatiRigaRms(statiRigaRms);


			}
		}

		response.setTestataOrdine(testataOrdineDad.getTestataOrdine(testataOrdine.getId()));
	}

	private String concatenaContatto (List<RigaRms> righeRms) {
		final String joined = righeRms.stream()
				.map(a-> a.getTestataRms().getDestinatarioContatto())
				.distinct()
				.collect(Collectors.joining(","));
		return joined;
	}

	private Destinatario salvaDestinatarioConRigheOrdine(Stato statoDestinatarioOrdine, TestataRms testataRms, List<RigaRms> righeRms, Stato statoRigaOrdine) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final TipoOrdine tipoOrdine = testataOrdine.getTipoOrdine();
		final Fornitore fornitore = testataOrdine.getFornitore();

		Destinatario destinatario = new Destinatario();
		destinatario.setSettore(testataRms.getSettoreDestinatario());
		destinatario.setStato(statoDestinatarioOrdine);
		destinatario.setTestataOrdine(testataOrdine);
		destinatario.setIndirizzo(testataRms.getDestinatarioIndirizzo());
		destinatario.setNumCivico(testataRms.getDestinatarioNumCivico());
		destinatario.setLocalita(testataRms.getDestinatarioLocalita());
		destinatario.setProvincia(testataRms.getDestinatarioProvincia());
		destinatario.setCap(testataRms.getDestinatarioCap());
		destinatario.setContatto(concatenaContatto(righeRms));
		destinatario.setEmail(testataRms.getDestinatarioEmail());
		destinatario.setTelefono(testataRms.getDestinatarioTelefono());
		destinatario.setUtenteCreazione(utenteConnesso.getCodiceFiscale());
		destinatario.setUtenteModifica(utenteConnesso.getCodiceFiscale());

		destinatario.setSettoreIndirizzo(testataRms.getSettoreIndirizzo());


		destinatario = destinatarioOrdineDad.saveDestinatario(destinatario);

		int progressivoRiga = 1;


		final Map<Integer, List<RigaRms>> mapOds = righeRms.stream().collect(Collectors.groupingBy(a-> a.getRigaRda().get(0).getOggettiSpesa().getId()));

		for(final List<RigaRms> value : mapOds.values()) {

			final Ods oggettiSpesa = value.get(0).getRigaRda().get(0).getOggettiSpesa();
			final Optional<ListinoFornitore> optionalListinoFornitore = commonDad.getListinoFornitore(fornitore.getId(), oggettiSpesa.getId());
			checkBusinessCondition(
					!tipoOrdine.getTipologiaDocumentoCodice().equals(TipoOrdineEnum.SEMPLICE.getCodice()) ||
					(tipoOrdine.getTipologiaDocumentoCodice().equals(TipoOrdineEnum.SEMPLICE.getCodice()) && optionalListinoFornitore.isPresent())
					, MsgCpassOrd.ORDORDE0165.getError());

			final ListinoFornitore listinoFornitore = optionalListinoFornitore.orElse(null);

			final boolean consegnaParziale = (value.stream().filter(x -> !x.getConsegnaParziale()).findAny().orElse(null)) == null ? Boolean.TRUE : Boolean.FALSE;
			final BigDecimal sumQta = value.stream().map(x-> x.getQuantitaSuRda()).reduce(BigDecimal.ZERO, BigDecimal::add);

			RigaOrdine rigaOrdine = new RigaOrdine();

			rigaOrdine.setProgressivo(progressivoRiga);
			progressivoRiga++;
			rigaOrdine.setConsegnaParziale(consegnaParziale);
			rigaOrdine.setPrezzoUnitario(oggettiSpesa.getPrezzoUnitario());
			rigaOrdine.setQuantita(sumQta);
			rigaOrdine.setImportoSconto(BigDecimal.ZERO);
			rigaOrdine.setImportoSconto2(BigDecimal.ZERO);

			rigaOrdine.setImportoNetto(UtilityRigaOrdine.calcolaImportoNetto(rigaOrdine.getPrezzoUnitario(),rigaOrdine.getQuantita()));
			rigaOrdine.setImportoIva(UtilityRigaOrdine.calcolaImportoIva(rigaOrdine.getImportoNetto(), oggettiSpesa.getAliquoteIva().getPercentuale()));
			rigaOrdine.setImportoTotale(rigaOrdine.getImportoNetto().add(rigaOrdine.getImportoIva()));
			rigaOrdine.setOds(oggettiSpesa);
			rigaOrdine.setUnitaMisura(oggettiSpesa.getUnitaMisura());
			rigaOrdine.setAliquoteIva(oggettiSpesa.getAliquoteIva());
			rigaOrdine.setStato(statoRigaOrdine);
			rigaOrdine.setListinoFornitore(listinoFornitore);
			rigaOrdine.setDestinatario(destinatario);
			rigaOrdine.setUtenteCreazione(utenteConnesso.getCodiceFiscale());//// vedere se utente viene impostato nel dad
			rigaOrdine.setUtenteModifica(utenteConnesso.getCodiceFiscale());// vedere se utente viene impostato nel dad

			rigaOrdine = rigaOrdineDad.saveRigaOrdine(rigaOrdine);

			inserimentoAutomaticoFinanziari(rigaOrdine,destinatarioOrdineDad, rigaOrdineDad, utenteDad, systemDad, impegnoDad, externalHelperLookup);

			// incremento totali testata
			testataOrdine.setTotaleConIva(testataOrdine.getTotaleConIva().add(rigaOrdine.getImportoTotale()));
			testataOrdine.setTotaleNoIva(testataOrdine.getTotaleNoIva().add(rigaOrdine.getImportoNetto()));
		}
		if (!response.getApiWarnings().isEmpty()) {

		}
		return destinatario;
	}
}
