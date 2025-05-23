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

import it.csi.cpass.cpassbe.lib.dto.InvioQuoteDocumento;
import it.csi.cpass.cpassbe.lib.external.InvioQuoteDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.documenti.svc._1.DocumentiService;
import it.csi.siac.documenti.svc._1.DocumentiService_Service;
import it.csi.siac.documenti.svc._1.ElaboraDocumento;
import it.csi.siac.documenti.svc._1.ElaboraDocumentoAsyncResponse;

/**
 * Example POJO helper impl
 */
public class InvioQuoteDocumentoHelperImpl extends BaseSiacHelperImpl implements InvioQuoteDocumentoHelper {

	@Override
	public ExternalServiceResponseWrapper<String> postInvioQuoteDocumento(Map<String, String> params, InvioQuoteDocumento invioQuoteDocumento) {
		final String methodName = "postInvioQuoteDocumento";

		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		DocumentiService_Service documentiService_Service = wsdlUrl != null ? new DocumentiService_Service(wsdlUrl) : new DocumentiService_Service();
		addHandlerResolver(params, documentiService_Service);

		DocumentiService documentiService = documentiService_Service.getDocumentiServicePort();
		ElaboraDocumento reqElaboraDocumentoAsync = composeRequest(params, invioQuoteDocumento);
		ElaboraDocumentoAsyncResponse elaboraDocumentoAsyncResponse = documentiService.elaboraDocumentoAsync(reqElaboraDocumentoAsync);

		ExternalServiceResponseWrapper<String> response = initResponse(methodName, elaboraDocumentoAsyncResponse);

		if (response.isSuccess() && elaboraDocumentoAsyncResponse.getIdOperazioneAsincrona() != null) {
			response.setResponse(String.valueOf(elaboraDocumentoAsyncResponse.getIdOperazioneAsincrona()));
		}

		if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
			response.getErrors().addAll(response.getMessages());
		}
		return response;
	}

	private ElaboraDocumento composeRequest(Map<String, String> params, InvioQuoteDocumento invioQuoteDocumento) {
		ElaboraDocumento req = new ElaboraDocumento();

		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		
		req.setAnnoBilancio(annoCorrente);

		req.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));

		req.setCodiceTipoDocumento(invioQuoteDocumento.getCodiceTipoDocumento());
		req.setContenutoDocumento(invioQuoteDocumento.getContenutoDocumento());

		return req;
	}

}
