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

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.mapper.CpassSiacMappers;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.ricerche.svc._1.RicercaImpegno;
import it.csi.siac.ricerche.svc._1.RicercaImpegnoResponse;
import it.csi.siac.ricerche.svc._1.RicercaService;
import it.csi.siac.ricerche.svc._1.RicercaService_Service;

/**
 * Example POJO helper impl
 */
public class ImpegnoHelperImpl extends BaseSiacHelperImpl implements ImpegnoHelper {

	private static final int NUM_ELEMENTI_PER_PAGINA = 5;

	@Override
	public ExternalServiceResponseWrapper<PagedList<Impegno>> getImpegni(Map<String, String> params, FiltroImpegni filtroImpegni, int page, int size) {
		final String methodName = "getImpegni";
		
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		log.info(methodName, "wsdlUrl " + wsdlUrl);
		RicercaService_Service ricercaServiceService = wsdlUrl != null ? new RicercaService_Service(wsdlUrl) : new RicercaService_Service();
		addHandlerResolver(params, ricercaServiceService);
		RicercaService ricercaService = ricercaServiceService.getRicercaServicePort();
		
		RicercaImpegno req = composeRequestRicercaImpegno(params, filtroImpegni.getSubimpegno().getImpegno(), page, size);
		RicercaImpegnoResponse res = ricercaService.ricercaImpegno(req);
		log.info(methodName, res.getImpegni().size());
		ExternalServiceResponseWrapper<PagedList<Impegno>> response = initResponse(methodName, res);
		if (response.isSuccess() && res.getImpegni() != null && res.getTotaleRisultati() != null) {
			
			List<it.csi.siac.integ.data._1.Impegno> impegnosSIAC = res.getImpegni();
			if (res.getTotaleRisultati().intValue() > NUM_ELEMENTI_PER_PAGINA) {
				int numPagine = res.getTotaleRisultati().intValue() / NUM_ELEMENTI_PER_PAGINA;
				if (res.getTotaleRisultati().intValue() % NUM_ELEMENTI_PER_PAGINA != 0) {
					numPagine++;
				}

				for (int pagina = 2; pagina <= numPagine; pagina++) {
					req.setNumeroPagina(pagina);
					res = ricercaService.ricercaImpegno(req);
					response = initResponse(methodName, res);
					if (response.isSuccess() && res.getImpegni() != null) {
						impegnosSIAC.addAll(res.getImpegni());
					}
				}
			}
			
			List<Impegno> listImpegnoSIAC = CpassSiacMappers.IMPEGNO.toModels(impegnosSIAC);
			listImpegnoSIAC = applicaFiltro(listImpegnoSIAC, filtroImpegni);
			
			PagedList<Impegno> content = toPagedList(listImpegnoSIAC, listImpegnoSIAC.size(), page, size);
			response.setResponse(content);
		}
		
		// può essere FALLIMENTO anche senza che venga restituito alcun errore nella listaErrori
		if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
			response.getErrors().addAll(response.getMessages());
		}
		
		return response;
	}
	
	private List<Impegno> applicaFiltro(List<Impegno> listImpegnoSIAC, FiltroImpegni filtroImpegni) {
		if (listImpegnoSIAC != null && filtroImpegni.getStatoImpegno() != null && !filtroImpegni.getStatoImpegno().trim().equals("")) {
			List<Impegno> listImpegnoNew = new ArrayList<Impegno>();

			for (Impegno impegnoSIAC : listImpegnoSIAC) {

				List<Subimpegno> listSubImpegnoSIAC = impegnoSIAC.getSubimpegni();
				if (listSubImpegnoSIAC != null && listSubImpegnoSIAC.size() > 0) {
					List<Subimpegno> listSubImpegnoNew = new ArrayList<Subimpegno>();

					for (Subimpegno subimpegnoSIAC : listSubImpegnoSIAC) {
						// scartare tutti i subimpegni non definitivi (subimpegni.stato.codice != ‘D’)
						// NOTA: gli impegni che presentano subimpegni hanno sempre uno stato “N” (Definitivo Non Liquidabile)
						if (!subimpegnoSIAC.getStato().equalsIgnoreCase(filtroImpegni.getStatoImpegno())) {
							continue;
						}
						listSubImpegnoNew.add(subimpegnoSIAC);

					}
					if (listSubImpegnoNew.size() > 0) {
						listImpegnoNew.add(impegnoSIAC);
						impegnoSIAC.setSubimpegni(listSubImpegnoNew);
					}

				} else {
					// scartare tutti gli impegni senza subimpegni non definitivi (Impegni.stato != ‘D’)
					if (!impegnoSIAC.getStato().equalsIgnoreCase(filtroImpegni.getStatoImpegno())) {
						continue;
					}
					listImpegnoNew.add(impegnoSIAC);
				}
			}
			return listImpegnoNew;
		}

		return listImpegnoSIAC;
	}

	/**
	 * Composes the request for the service
	 * @param params the parameters for the environment
	 * @param impegno the service parameter
	 * @param page the page
	 * @param size the size
	 * @return the request
	 */
	private RicercaImpegno composeRequestRicercaImpegno(Map<String, String> params, Impegno impegno, int page, int size) {
		final String methodName = "composeRequestRicercaImpegno";
		RicercaImpegno req = new RicercaImpegno();
		
		req.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));

		// anno bilancio = anno corrente (Obbligatorio)
		Calendar calendar = Calendar.getInstance(); 
		req.setAnnoBilancio(calendar.get(Calendar.YEAR));
		
		req.setAnnoImpegno(impegno.getAnno());
		req.setNumeroImpegno(impegno.getNumero());
		
		req.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		req.setNumeroProvvedimento(impegno.getNumeroProvvedimento());
		
		ricercaTipoProvvedimento(params, impegno, req);
		
		// This service is 1-based
		req.setNumeroPagina(1);
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);
		
		return req;
	}

	private void ricercaTipoProvvedimento(Map<String, String> params, Impegno impegno, RicercaImpegno req) {
		final String methodName = "ricercaTipoProvvedimento";

		// 7.1 (ex 3.11) Ricerca tipo provvedimento
		String strAnnoAvvioStilo = getParameter(params, SiacConfigurationParams.ANNO_AVVIO_STILO);
		int annoAvvioStilo = -1;
		try {
			annoAvvioStilo = Integer.parseInt(strAnnoAvvioStilo);
		} catch (Exception e) {
			log.error(methodName, e.getMessage(), e);
		}

		if (impegno.getAnnoProvvedimento() != null) {
			if (impegno.getAnnoProvvedimento() >= annoAvvioStilo) {
				String codiceTipoProvvStilo = getParameter(params, SiacConfigurationParams.CODICE_TIPO_PROVV_STILO);
				req.setCodiceTipoProvvedimento(codiceTipoProvvStilo);
			} else {
				String codiceTipoProvvStilo = getParameter(params, SiacConfigurationParams.CODICE_TIPO_PROVV_ANTE_STILO);
				req.setCodiceTipoProvvedimento(codiceTipoProvvStilo);
			}
		}
	}
	
}
