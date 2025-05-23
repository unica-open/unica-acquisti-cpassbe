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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneVerifichePreliminariAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneVerifichePreliminariAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheEvasione;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutEvasioneVerifichePreliminariAnnullaByIdService
extends BaseTestataEvasioneService<PutEvasioneVerifichePreliminariAnnullaByIdRequest, PutEvasioneVerifichePreliminariAnnullaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	private final DecodificaDad decodificaDad;
	private final SystemDad systemDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param externalHelperLookup
	 * @param decodificaDad
	 * @param systemDad
	 * @param testataEvasioneDad
	 */
	public PutEvasioneVerifichePreliminariAnnullaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			DecodificaDad decodificaDad, SystemDad systemDad, TestataEvasioneDad testataEvasioneDad) {

		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final List<ApiError> apiErrors = new ArrayList<>();
		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final Stato statoEvasioneInviata = decodificaDad.getStato(StatoEvasioneEnum.INVIATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante());
		final Stato statoEvasioneInContabilita = decodificaDad.getStato(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante());
		final Stato testataEvasioneStato = testataEvasione.getStato();

		if (testataEvasioneStato.getCodice().equals(statoEvasioneInviata.getCodice()) ||
				testataEvasioneStato.getCodice().equals(statoEvasioneInContabilita.getCodice())) {

			// richiamare il servizio [7] Ricerca Documento Spesa
			final DocumentoSpesa filtroDocumentoSpesa = new DocumentoSpesa();
			filtroDocumentoSpesa.setAnnoDocumento(testataEvasione.getFatturaAnno());
			filtroDocumentoSpesa.setNumeroDocumento(testataEvasione.getFatturaNumero());
			filtroDocumentoSpesa.setCodiceFornitore(testataEvasione.getFatturaCodiceFornitore());
			filtroDocumentoSpesa.setTipoDocumento(testataEvasione.getFatturaTipo());

			final ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class,enteId);
			final List<DocumentoSpesa> documentoSpesas = UtilityVerificheEvasione.invokeExternalService(handler,
					() -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtroDocumentoSpesa), apiErrors);
			if (documentoSpesas == null || documentoSpesas.size() == 0) {
				// throw new RuntimeException("Errore servizio 'ricercaDocumentoSpesa': nessun documento trovato.");
				apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "DocumentoSpesa non trovato"));

			} else {
				final ArrayList<DocumentoSpesa> listDocumentoSpesa = new ArrayList<>();
				for (final DocumentoSpesa documentoSpesa : documentoSpesas) {
					if (!documentoSpesa.getCodiceStato().equalsIgnoreCase(DocumentoSpesaHelper.STATO_FATTURA_ANNULLATO)) {
						listDocumentoSpesa.add(documentoSpesa);
					}
				}

				if (listDocumentoSpesa.size() == 0) {
					apiErrors.add(MsgCpassOrd.ORDORDA0112.getError());

				} else {
					final DocumentoSpesa documentoSpesa = listDocumentoSpesa.get(0);

					final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
					final Ente ente = settoreCorrente.getEnte();

					// verifica STATO_FATTURA_RIPARTIBILE
					final Parametro paramStatoFatturaRipartibile = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.STATO_FATTURA_RIPARTIBILE.getCostante(),
							ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(), ente.getId());
					final String statoFatturaRipartibile = paramStatoFatturaRipartibile.getValore();
					if (documentoSpesa.getCodiceStato().equalsIgnoreCase(statoFatturaRipartibile)) {
						apiErrors.add(MsgCpassOrd.ORDORDA0113.getError());
					} else {
						apiErrors.add(MsgCpassOrd.ORDORDE0114.getError());
					}
				}
			}
		} else {
			// se l’evasione non è in stato INVIATA, la verifica va a buon fine e termina qui.
		}

		separaMessaggiErrorePerTipo(apiErrors);
		response.setTestataEvasione(testataEvasione);
	}

}
