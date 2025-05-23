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
package it.csi.cpass.cpassbe.lib.external.impl.sicraweb;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import fdewsappjdocumentigateway.ElaboraDocumento;
import fdewsappjdocumentigateway.ElaboraDocumentoResponse;
import fdewsappjdocumentigateway.FdeWSAppjDocumentiGateway;
import fdewsappjdocumentigateway.FdeWSAppjDocumentiGatewayService;
import fdewsappjdocumentigateway.SagaException;
import it.csi.cpass.cpassbe.lib.dto.InvioQuoteDocumento;
import it.csi.cpass.cpassbe.lib.external.InvioQuoteDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.utils.SicrawebConfigurationParams;

/**
 * Example POJO helper impl
 */
public class InvioQuoteDocumentoHelperImpl extends BaseSicrawebDocumentiHelperImpl implements InvioQuoteDocumentoHelper {

	@Override
	public ExternalServiceResponseWrapper<String> postInvioQuoteDocumento(Map<String, String> params, InvioQuoteDocumento invioQuoteDocumento) {
		final String methodName = "postInvioQuoteDocumento";

		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		FdeWSAppjDocumentiGatewayService documentiService_Service = wsdlUrl != null ? new FdeWSAppjDocumentiGatewayService(wsdlUrl) : new FdeWSAppjDocumentiGatewayService();
		addHandlerResolver(params, documentiService_Service);

		FdeWSAppjDocumentiGateway documentiService = documentiService_Service.getFdeWSAppjDocumentiGateway();
		
		ElaboraDocumento reqElaboraDocumentoAsync = composeRequest(params, invioQuoteDocumento);
		ExternalServiceResponseWrapper<String> response = new  ExternalServiceResponseWrapper<String>();
		
		try {
			ElaboraDocumentoResponse elaboraDocumentoAsyncResponse = documentiService.elaboraDocumento(reqElaboraDocumentoAsync);

			response = initResponse( elaboraDocumentoAsyncResponse);

			if (response.isSuccess() && elaboraDocumentoAsyncResponse.getResponseElaborazione() != null) {
				response.setResponse(elaboraDocumentoAsyncResponse.getResponseElaborazione());
			}

			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}
		} catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(methodName, e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error( methodName , "Desc error --> "+e.getMessage());
			return response;
		}
		return response;
	}

	private ElaboraDocumento composeRequest(Map<String, String> params, InvioQuoteDocumento invioQuoteDocumento) {
		ElaboraDocumento req = new ElaboraDocumento();

		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		
		req.setAnnoBilancio(annoCorrente);

		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));
		req.setCodiceTipoDocumento(getParameter(params, SicrawebConfigurationParams.CODICE_TIPO_DOCUMENTO));

		req.setContenutoDocumento(invioQuoteDocumento.getContenutoDocumento());

		return req;
	}

}
