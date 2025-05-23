/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.siac;

import java.net.URL;
import java.util.Calendar;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.batch.VerificaInvioEvasioneInContabilita;
import it.csi.cpass.cpassbe.lib.external.LeggiStatoElaborazioneDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.documenti.svc._1.DocumentiService;
import it.csi.siac.documenti.svc._1.DocumentiService_Service;
import it.csi.siac.documenti.svc._1.LeggiStatoElaborazioneDocumento;
import it.csi.siac.documenti.svc._1.LeggiStatoElaborazioneDocumentoResponse;
import it.csi.siac.integ.data._1.Errore;

/**
 * Example POJO helper impl
 */
public class LeggiStatoElaborazioneDocumentoHelperImpl extends BaseSiacHelperImpl implements LeggiStatoElaborazioneDocumentoHelper {

	@Override
	public ExternalServiceResponseWrapper<VerificaInvioEvasioneInContabilita> leggiStatoElaborazioneDocumento(Map<String, String> params, Integer idVerifica) {
	
		final String methodName = "LeggiStatoElaborazioneDocumentoHelperImpl";

		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		DocumentiService_Service documentiService_Service = wsdlUrl != null ? new DocumentiService_Service(wsdlUrl) : new DocumentiService_Service();
		addHandlerResolver(params, documentiService_Service);
		DocumentiService documentiService = documentiService_Service.getDocumentiServicePort();

		LeggiStatoElaborazioneDocumento leggiStatoElaborazioneDocumento = composeRequest(params, idVerifica);
		LeggiStatoElaborazioneDocumentoResponse res = documentiService.leggiStatoElaborazioneDocumento(leggiStatoElaborazioneDocumento);

		ExternalServiceResponseWrapper<VerificaInvioEvasioneInContabilita> response = initResponse(methodName, res);
		response.setSuccess(true);
		
		VerificaInvioEvasioneInContabilita ris = new VerificaInvioEvasioneInContabilita();
		ris.setEsitoEsterno(res.getEsito().toString());
		ris.setStatoElaborazionEsterno(res.getStatoElaborazione().toString());
		for(Errore error : res.getErrori()) {				
			ris.setCodiceErrore(error.getCodice()); 
			ris.setDescrizioneErrore(error.getDescrizione());
		}
		response.setResponse(ris);

		return response;
	}

	private LeggiStatoElaborazioneDocumento composeRequest(Map<String, String> params, Integer idVerifica) {
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		LeggiStatoElaborazioneDocumento req = new LeggiStatoElaborazioneDocumento();
		req.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));
		req.setAnnoBilancio(annoCorrente);
		req.setIdOperazioneAsincrona(idVerifica);
		return req;
	}


}
