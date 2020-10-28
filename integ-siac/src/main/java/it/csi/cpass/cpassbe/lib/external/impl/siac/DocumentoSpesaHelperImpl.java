/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.siac;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.mapper.CpassSiacMappers;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.documenti.svc._1.RicercaDocumentoSpesaResponse;
import it.csi.siac.ricerche.svc._1.RicercaDocumentoSpesa;
import it.csi.siac.ricerche.svc._1.RicercaService;
import it.csi.siac.ricerche.svc._1.RicercaService_Service;


/**
 * Example POJO helper impl
 */
public class DocumentoSpesaHelperImpl extends BaseSiacHelperImpl implements DocumentoSpesaHelper {

	private static final int NUM_ELEMENTI_PER_PAGINA = 5;
	
	@Override
	public ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesa(Map<String, String> params,DocumentoSpesa filtroDocumentoSpesa) {
		final String methodName = "getDocumentoSpesa";
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		RicercaService_Service ricercaServiceService = wsdlUrl != null ? new RicercaService_Service(wsdlUrl) : new RicercaService_Service();
		addHandlerResolver(params, ricercaServiceService);
		RicercaService ricercaService = ricercaServiceService.getRicercaServicePort();
		RicercaDocumentoSpesa req = composeRequest(params, filtroDocumentoSpesa,1);
		RicercaDocumentoSpesaResponse ricercaDocumentoSpesaResponse = ricercaService.ricercaDocumentoSpesa(req);
		ExternalServiceResponseWrapper<List<DocumentoSpesa>> response = initResponse(methodName, ricercaDocumentoSpesaResponse);
		//ExternalServiceResponseWrapper<PagedList<Impegno>>   response = initResponse(methodName, res);
		
		if (response.isSuccess() && ricercaDocumentoSpesaResponse.getDocumentiSpesa() != null && ricercaDocumentoSpesaResponse.getTotaleRisultati() != null) {
			List<it.csi.siac.ricerche.data._1.DocumentoSpesa> documentiSpesaSIAC = ricercaDocumentoSpesaResponse.getDocumentiSpesa(); 
			List<DocumentoSpesa> listDCSIAC = CpassSiacMappers.DOCUMENTI_SPESA.toModels(documentiSpesaSIAC);
			//PagedList<DocumentoSpesa> content = toPagedList(listDCSIAC, listDCSIAC.size(), 1, NUM_ELEMENTI_PER_PAGINA);
			response.setResponse(listDCSIAC);
		}
		
		if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
			response.getErrors().addAll(response.getMessages());
		}		 
		return  response;
	}

	@Override
	public ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesaRipartibile(Map<String, String> params,DocumentoSpesa filtroDocumentoSpesa) {
		final String methodName = "getDocumentoSpesa";
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		RicercaService_Service ricercaServiceService = wsdlUrl != null ? new RicercaService_Service(wsdlUrl) : new RicercaService_Service();
		addHandlerResolver(params, ricercaServiceService);
		RicercaService ricercaService = ricercaServiceService.getRicercaServicePort();
		RicercaDocumentoSpesa req = composeRequest(params, filtroDocumentoSpesa,1);
		RicercaDocumentoSpesaResponse ricercaDocumentoSpesaResponse = ricercaService.ricercaDocumentoSpesa(req);
		ExternalServiceResponseWrapper<List<DocumentoSpesa>> response = initResponse(methodName, ricercaDocumentoSpesaResponse);
		//ExternalServiceResponseWrapper<PagedList<Impegno>>   response = initResponse(methodName, res);
		
		String statoFatturaRipartibile = params.get(SiacConfigurationParams.STATO_FATTURA_RIPARTIBILE.getParamName());
		
		if (response.isSuccess() && ricercaDocumentoSpesaResponse.getDocumentiSpesa() != null && ricercaDocumentoSpesaResponse.getTotaleRisultati() != null) {
			List<it.csi.siac.ricerche.data._1.DocumentoSpesa> documentiSpesaSIAC = ricercaDocumentoSpesaResponse.getDocumentiSpesa(); 
			
			List<it.csi.siac.ricerche.data._1.DocumentoSpesa> documentiSpesaRipartibiliSIAC = new ArrayList<it.csi.siac.ricerche.data._1.DocumentoSpesa>();

			for(it.csi.siac.ricerche.data._1.DocumentoSpesa dc : documentiSpesaSIAC) {
				if (dc.getStato().getCodice().equalsIgnoreCase(statoFatturaRipartibile)) {
					documentiSpesaRipartibiliSIAC.add(dc);
				}
			}
			
			List<DocumentoSpesa> listDCSIAC = CpassSiacMappers.DOCUMENTI_SPESA.toModels(documentiSpesaRipartibiliSIAC);
			//PagedList<DocumentoSpesa> content = toPagedList(listDCSIAC, listDCSIAC.size(), 1, NUM_ELEMENTI_PER_PAGINA);
			response.setResponse(listDCSIAC);
		}
		
		if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
			response.getErrors().addAll(response.getMessages());
		}		 
		return  response;
	}
	
	private RicercaDocumentoSpesa composeRequest(Map<String, String> params, DocumentoSpesa filtroDocumentoSpesa,int paginaCorrente) {
		RicercaDocumentoSpesa req = new RicercaDocumentoSpesa();
		//rds.setAnnoBilancio(filtroDocumentoSpesa.getAnnoDocumento());
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		
		req.setAnnoBilancio(annoCorrente);
		req.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));

		req.setAnnoRepertorio(filtroDocumentoSpesa.getAnnoProtocollo());
		req.setNumeroRepertorio(filtroDocumentoSpesa.getNumeroProtocollo());	
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);
		req.setNumeroPagina(paginaCorrente);
		
		req.setAnnoDocumento(filtroDocumentoSpesa.getAnnoDocumento());
		req.setNumeroDocumento(filtroDocumentoSpesa.getNumeroDocumento());
		
		req.setCodiceSoggetto(filtroDocumentoSpesa.getCodiceFornitore());
		req.setTipoDocumento(filtroDocumentoSpesa.getTipoDocumento());
		
		return req;
	}

}
