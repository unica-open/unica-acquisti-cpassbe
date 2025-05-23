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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostRigaOrdineService extends BaseOrdineService<PostRigaOrdineRequest, PostRigaOrdineResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final DestinatarioOrdineDad destinatarioDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	private final ImpegnoDad impegnoDad;
	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final CommonDad commonDad;
	private final FornitoreDad fornitoreDad;
	private RigaOrdine rigaOrdine;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostRigaOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, RigaOrdineDad rigaOrdineDad, DestinatarioOrdineDad destinatarioDad, DecodificaDad decodificaDad, ImpegnoDad impegnoDad,
			SystemDad systemDad, UtenteDad utenteDad, CommonDad commonDad, TestataOrdineDad testataOrdineDad, FornitoreDad fornitoreDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.externalHelperLookup = externalHelperLookup;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioDad = destinatarioDad;
		this.decodificaDad = decodificaDad;
		this.impegnoDad = impegnoDad;
		this.utenteDad = utenteDad;
		this.systemDad = systemDad;
		this.commonDad = commonDad;
		this.fornitoreDad = fornitoreDad;
	}

	@Override
	protected void checkServiceParams() {
		rigaOrdine = request.getRigaOrdine();
		checkNotNull(rigaOrdine, "rigaOrdine", Boolean.TRUE);
		checkNotNull(rigaOrdine.getOds(), "ods", Boolean.TRUE);
		checkNotNull(rigaOrdine.getOds().getCodice(), "codiceOds", Boolean.TRUE);
		checkNotNull(rigaOrdine.getOds().getCpv(), "cpv", Boolean.TRUE);
		checkNotNull(rigaOrdine.getOds().getCpv().getCodice(), "codiceCpv", Boolean.TRUE);
		checkNotNull(rigaOrdine.getUnitaMisura(), "unitaMisura", Boolean.TRUE);
		checkNotNull(rigaOrdine.getAliquoteIva(), "aliquoteIva", Boolean.TRUE);
		checkNotNull(rigaOrdine.getPrezzoUnitario(), "prezzoUnitario", Boolean.TRUE);
		checkNotNull(rigaOrdine.getQuantita(), "quantita", Boolean.TRUE);
		checkNotNull(rigaOrdine.getImportoNetto(), "importoNetto", Boolean.TRUE);
		checkNotNull(rigaOrdine.getImportoIva(), "iva", Boolean.TRUE);
		checkNotNull(rigaOrdine.getImportoTotale(), "importoTotale", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		boolean hasErrors = false;
		final Date now = new Date();
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreUtente.getEnte();

		//controllo che esista il cpv e non sia scaduto
		final Cpv cpv = decodificaDad.getCpvByCodice(rigaOrdine.getOds().getCpv().getCodice());
		if(cpv == null) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0011.getError());
			return;
		}

		//controllo che esista l'ods e non sia scaduto
		final Ods ods = decodificaDad.getOdsByCodice(rigaOrdine.getOds().getCodice());
		if(ods == null || (ods.getDataValiditaFine() != null && ods.getDataValiditaFine().before(now))) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0012.getError());
			return;
		}

		// controllo congruenza tra i due
		if(!ods.getCpv().getCodice().equals(cpv.getCodice())) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0013.getError());
		}

		// controllo validita unita di misura
		final UnitaMisura unitaMisura = decodificaDad.getUnitaMisuraByCodice(rigaOrdine.getUnitaMisura().getCodice());
		if (unitaMisura == null || (unitaMisura.getDataValiditaFine() != null && unitaMisura.getDataValiditaFine().before(now))) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0014.getError());
		}

		// controllo validita aliquotaIva
		final AliquoteIva aliquoteIva = decodificaDad.getAliquoteIvaByCodice(rigaOrdine.getAliquoteIva().getCodice());
		if (aliquoteIva == null || (aliquoteIva.getDataValiditaFine() != null && aliquoteIva.getDataValiditaFine().before(now))) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0015.getError());
		}

		//controllo quantita
		if(rigaOrdine.getQuantita().compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0018.getError());
		}

		//controllo prezzoUnitario
		final BigDecimal pU = rigaOrdine.getPrezzoUnitario();
		if(pU.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0019.getError());
		}

		//controllo importoNetto
		final BigDecimal netto = rigaOrdine.getImportoNetto();
		if(netto.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0067.getError());
		}

		//controllo importoTotale
		final BigDecimal totale = rigaOrdine.getImportoTotale();
		if(totale.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0068.getError());
		}

		//controllo che l'ods della riga ordine non sia già assegnato ad un altra riga ordine dello stesso ordine
		final List<RigaOrdine> righeDestinatario = rigaOrdineDad.getRigheByDestinatario(rigaOrdine.getDestinatario().getId());
		boolean isFirstOdsOfOrdine = true;
		for(final RigaOrdine riga : righeDestinatario) {
			if(riga.getOds().getCodice().equalsIgnoreCase(rigaOrdine.getOds().getCodice())) {
				isFirstOdsOfOrdine = false;
				break;
			}
		}
		if(!isFirstOdsOfOrdine) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0031.getError());
		}

		if(!hasErrors) {
			//controllo diff iva
			final BigDecimal diff = UtilityVerificheOrdine.getDeltaIvasRiga(request.getRigaOrdine());
			//Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
			//List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());
			final String nomeParametro = ChiaveEnum.ORD_TOLLERANZA_IVA.name();
			final Parametro paramTolleranza = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(),ente.getId());

			//final Double tolleranza = Double.parseDouble(paramTolleranza.getValore());
			//final Double twoDecimalDiff = NumberUtility.arrotondaDueDecimali(diff.doubleValue());
			final BigDecimal tolleranza     = new BigDecimal(paramTolleranza.getValore());
			final BigDecimal twoDecimalDiff = NumberUtility.arrotondaDueDec(diff);
			
			final int confronto = twoDecimalDiff.compareTo(tolleranza);
			if(request.getFlagBypassControlloIva() == null || !request.getFlagBypassControlloIva().equals(Boolean.TRUE)) {
				if(!twoDecimalDiff.equals(BigDecimal.ZERO)) {
				//if(!twoDecimalDiff.equals(0.0)) {
					if(confronto > 0) {
						response.addApiError(MsgCpassOrd.ORDORDE0016.getError());
					} else {
						response.addApiError(MsgCpassOrd.ORDORDA0017.getError());
					}
				} else {
					final ListinoFornitore listino = inserisciListino(ods);
					rigaOrdine.setListinoFornitore(listino);
					rigaOrdine = rigaOrdineDad.saveRigaOrdine(rigaOrdine);
					response.setRigaOrdine(rigaOrdine);
					UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
					inserimentoAutomaticoFinanziari(rigaOrdine, destinatarioDad, rigaOrdineDad, utenteDad, systemDad, impegnoDad, externalHelperLookup);

				}
			} else {
				//controllo comunque che vi siano l'iva sia nella tolleranza prima di salvare
				checkBusinessCondition(confronto <= 0, MsgCpassOrd.ORDORDE0016.getError());
				final ListinoFornitore listino = inserisciListino(ods);
				rigaOrdine.setListinoFornitore(listino);
				rigaOrdine = rigaOrdineDad.saveRigaOrdine(rigaOrdine);
				response.setRigaOrdine(rigaOrdine);
				UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
				inserimentoAutomaticoFinanziari(rigaOrdine, destinatarioDad, rigaOrdineDad, utenteDad, systemDad, impegnoDad, externalHelperLookup);

			}
		}
	}

	/**
	 *
	 * @param ods
	 * @return ListinoFornitore
	 */
	private ListinoFornitore inserisciListino(Ods ods) {
		//Inseriso se serve il listinoFornitore
		ListinoFornitore listino        = rigaOrdine.getListinoFornitore();
		if(listino!=null && listino.getId()!= null && listino.getId()==-1) {
			//TODO inserire listino
			final Fornitore fornitore = fornitoreDad.getRicercaFornitoreInterno( listino.getFornitore().getId());
			listino.setId(null);
			listino.setFornitore(fornitore);
			listino.setOggettiSpesa(ods);
			listino = commonDad.saveListinoFornitore(listino);
		}else if(listino!=null && listino.getId()== null) {
			return null;
		}
		return listino;
	}
}
