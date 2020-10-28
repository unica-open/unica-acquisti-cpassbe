/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVerificheEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneVerifichePreliminariAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneVerifichePreliminariAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
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

public class PutEvasioneVerifichePreliminariAnnullaByIdService
		extends BaseTestataEvasioneService<PutEvasioneVerifichePreliminariAnnullaByIdRequest, PutEvasioneVerifichePreliminariAnnullaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	private final DecodificaDad decodificaDad;
	private final SystemDad systemDad;

	private final TestataEvasioneDad testataEvasioneDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutEvasioneVerifichePreliminariAnnullaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			DecodificaDad decodificaDad, SystemDad systemDad, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;

		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;

		this.testataEvasioneDad = testataEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());

		String codiceStatoTestataEvasione = ConstantsCPassStato.StatoEnum.INVIATA.getCostante();
		Stato statoEvasioneInviata = decodificaDad.getStato(codiceStatoTestataEvasione, ConstantsCPassStato.TipoEnum.EVASIONE.getCostante()).get();

		if (!testataEvasione.getStato().getCodice().equals(statoEvasioneInviata.getCodice())) {
			// se l’evasione non è in stato INVIATA, la verifica va a buon fine e termina qui.

		} else {
			// richiamare il servizio [7] Ricerca Documento Spesa
			DocumentoSpesa filtroDocumentoSpesa = new DocumentoSpesa();
			filtroDocumentoSpesa.setAnnoDocumento(testataEvasione.getFatturaAnno());
			filtroDocumentoSpesa.setNumeroDocumento(testataEvasione.getFatturaNumero());
			filtroDocumentoSpesa.setCodiceFornitore(testataEvasione.getFatturaCodice());
			filtroDocumentoSpesa.setTipoDocumento(testataEvasione.getFatturaTipo());

			ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class);
			List<DocumentoSpesa> documentoSpesas = UtilityVerificheEvasione.invokeExternalService(handler,
					() -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtroDocumentoSpesa), apiErrors);
			if (documentoSpesas == null || documentoSpesas.size() == 0) {
				// throw new RuntimeException("Errore servizio 'ricercaDocumentoSpesa': nessun documento trovato.");
				apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "DocumentoSpesa non trovato"));

			} else {
				ArrayList<DocumentoSpesa> listDocumentoSpesa = new ArrayList<DocumentoSpesa>();
				for (DocumentoSpesa documentoSpesa : documentoSpesas) {
					if (!documentoSpesa.getCodiceStato().equalsIgnoreCase(DocumentoSpesaHelper.STATO_FATTURA_ANNULLATO)) {
						listDocumentoSpesa.add(documentoSpesa);
					}
				}

				if (listDocumentoSpesa.size() == 0) {
					apiErrors.add(MsgCpassOrd.ORDORDA0112.getError());

				} else {
					DocumentoSpesa documentoSpesa = listDocumentoSpesa.get(0);

					Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
					Ente ente = settoreCorrente.getEnte();

					// verifica STATO_FATTURA_RIPARTIBILE
					Parametro paramStatoFatturaRipartibile = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.STATO_FATTURA_RIPARTIBILE.getCostante(),
							ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(), ente.getId());
					String statoFatturaRipartibile = paramStatoFatturaRipartibile.getValore();
					if (documentoSpesa.getCodiceStato().equalsIgnoreCase(statoFatturaRipartibile)) {
						apiErrors.add(MsgCpassOrd.ORDORDA0113.getError());
					} else {
						apiErrors.add(MsgCpassOrd.ORDORDE0114.getError());
					}
				}
			}
		}

		separaMessaggiErrorePerTipo(apiErrors);
		response.setTestataEvasione(testataEvasione);
	}

}
