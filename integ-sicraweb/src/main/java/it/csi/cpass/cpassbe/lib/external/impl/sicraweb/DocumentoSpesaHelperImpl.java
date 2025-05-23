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

import fdewsappjricercagateway.FdeWSAppjRicercaGateway;
import fdewsappjricercagateway.FdeWSAppjRicercaGatewayService;
import fdewsappjricercagateway.RicercaDocumentoSpesa;
import fdewsappjricercagateway.RicercaDocumentoSpesaResponse;
import fdewsappjricercagateway.SagaException;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.impl.mapper.CpassSicrawebMappers;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.utils.SicrawebConfigurationParams;


/**
 * Example POJO helper impl
 */
public class DocumentoSpesaHelperImpl extends BaseSicrawebRicercaHelperImpl implements DocumentoSpesaHelper {
	
	private static final int NUM_ELEMENTI_PER_PAGINA = 5;
	
	@Override
	public ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesa(Map<String, String> params,DocumentoSpesa filtroDocumentoSpesa) {
		final String methodName = "getDocumentoSpesa";
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();	
		addHandlerResolver(params, ricercaServiceService);
		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();
		RicercaDocumentoSpesa req = composeRequest(params, filtroDocumentoSpesa,1);
		RicercaDocumentoSpesaResponse ricercaDocumentoSpesaResponse;
		ExternalServiceResponseWrapper<List<DocumentoSpesa>> response = new  ExternalServiceResponseWrapper<List<DocumentoSpesa>>();
		try {
			ricercaDocumentoSpesaResponse = ricercaService.ricercaDocumentoSpesa(req);
			response = initResponse(methodName, ricercaDocumentoSpesaResponse);
			
			if (response.isSuccess() && ricercaDocumentoSpesaResponse.getDocumentiSpesa() != null && ricercaDocumentoSpesaResponse.getTotaleRisultati() != null) {
				List<fdewsappjricercagateway.DocumentoSpesa> documentiSpesaSIAC = ricercaDocumentoSpesaResponse.getDocumentiSpesa(); 
				List<DocumentoSpesa> listDCSIAC = CpassSicrawebMappers.DOCUMENTI_SPESA.toModels(documentiSpesaSIAC);
				response.setResponse(listDCSIAC);
			}
			
			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}	
		} catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("FornitoreHelperImpl", "getFornitori ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("FornitoreHelperImpl", "getFornitori Desc error --> "+e.getMessage());
			return response;
		}
		return  response;
	}

	@Override
	public ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesaRipartibile(Map<String, String> params,DocumentoSpesa filtroDocumentoSpesa) {
		final String methodName = "getDocumentoSpesa";
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);		
		// Api manager 
		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();	
		addHandlerResolver(params, ricercaServiceService);	
		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();
		ExternalServiceResponseWrapper<List<DocumentoSpesa>> response = new  ExternalServiceResponseWrapper<List<DocumentoSpesa>>();
		RicercaDocumentoSpesa req = composeRequest(params, filtroDocumentoSpesa,1);
		RicercaDocumentoSpesaResponse ricercaDocumentoSpesaResponse;
		try {
			ricercaDocumentoSpesaResponse = ricercaService.ricercaDocumentoSpesa(req);	
			response = initResponse(methodName, ricercaDocumentoSpesaResponse);	
			String statoFatturaRipartibile = params.get(SicrawebConfigurationParams.STATO_FATTURA_RIPARTIBILE.getParamName());			
			if (response.isSuccess() && ricercaDocumentoSpesaResponse.getDocumentiSpesa() != null && ricercaDocumentoSpesaResponse.getTotaleRisultati() != null) {
				List<fdewsappjricercagateway.DocumentoSpesa> documentiSpesaSIAC = ricercaDocumentoSpesaResponse.getDocumentiSpesa(); 				
				List<fdewsappjricercagateway.DocumentoSpesa> documentiSpesaRipartibiliSIAC = new ArrayList<fdewsappjricercagateway.DocumentoSpesa>();	
				for(fdewsappjricercagateway.DocumentoSpesa dc : documentiSpesaSIAC) {
					if (dc.getStato().getCodice().equalsIgnoreCase(statoFatturaRipartibile)) {
						documentiSpesaRipartibiliSIAC.add(dc);
					}
				}			
				List<DocumentoSpesa> listDCSIAC = CpassSicrawebMappers.DOCUMENTI_SPESA.toModels(documentiSpesaRipartibiliSIAC);
				response.setResponse(listDCSIAC);
			}
			
			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}	
		}catch (SagaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("FornitoreHelperImpl", "getFornitori ", e);
				response.setSuccess(false);
				List<String> errors = new ArrayList<String>();
				errors.add( e.getMessage());
				response.setErrors(errors);
				log.error("FornitoreHelperImpl", "getFornitori Desc error --> "+e.getMessage());
				return response;
			}
		return  response;
	}
	
	private RicercaDocumentoSpesa composeRequest(Map<String, String> params, DocumentoSpesa filtroDocumentoSpesa,int paginaCorrente) {
		RicercaDocumentoSpesa req = new RicercaDocumentoSpesa();
		//rds.setAnnoBilancio(filtroDocumentoSpesa.getAnnoDocumento());
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);	
		req.setAnnoBilancio(annoCorrente);
		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));
		req.setAnnoRepertorio(filtroDocumentoSpesa.getAnnoProtocollo());
		req.setNumeroRepertorio(filtroDocumentoSpesa.getNumeroProtocollo());	
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);
		req.setNumeroPagina(paginaCorrente);	
		req.setAnnoDocumento(filtroDocumentoSpesa.getAnnoDocumento());
		req.setNumeroDocumento(filtroDocumentoSpesa.getNumeroDocumento());	
		req.setCodiceSoggetto(filtroDocumentoSpesa.getCodiceFornitore());
		req.setTipoDocumento(filtroDocumentoSpesa.getTipoDocumento());	
		req.setRegistroRepertorio(filtroDocumentoSpesa.getRegistroRepertorio());
		return req;
	}
}
