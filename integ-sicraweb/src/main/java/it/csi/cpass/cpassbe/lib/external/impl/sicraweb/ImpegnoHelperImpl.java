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
import java.util.stream.Collectors;

import fdewsappjricercagateway.FdeWSAppjRicercaGateway;
import fdewsappjricercagateway.FdeWSAppjRicercaGatewayService;
import fdewsappjricercagateway.RicercaImpegno;
import fdewsappjricercagateway.RicercaImpegnoResponse;
import fdewsappjricercagateway.SagaException;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegniNew;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.dto.Errore;
import it.csi.cpass.cpassbe.lib.external.impl.mapper.CpassSicrawebMappers;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.utils.SicrawebConfigurationParams;

/**
 * Example POJO helper impl
 */
public class ImpegnoHelperImpl extends BaseSicrawebRicercaHelperImpl implements ImpegnoHelper {

	private static final int NUM_ELEMENTI_PER_PAGINA = 20;
	
	@Override
	public ExternalServiceResponseWrapper<PagedList<Impegno>> getImpegniEsterni(Map<String, String> params, FiltroImpegni filtroImpegni, int page, int size,Boolean filtri,Boolean includiAnnoSuccessivo) {
		final String methodName = "getImpegniEsterni";		
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		log.info(methodName, "wsdlUrl " + wsdlUrl);
		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();
		addHandlerResolver(params, ricercaServiceService);
		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();		
		ExternalServiceResponseWrapper<PagedList<Impegno>> responseAll = new ExternalServiceResponseWrapper<PagedList<Impegno>>();
		ExternalServiceResponseWrapper<PagedList<Impegno>> response = new ExternalServiceResponseWrapper<PagedList<Impegno>>();
		RicercaImpegno req = composeRequestRicercaImpegno(params, filtroImpegni, page, size);
		try {
			response = estraiImpegniDaSistemaContabile(filtroImpegni, page, size, filtri, ricercaService, req);
			List<Impegno> listaImpegno = response.getResponse().getList();
			
			// può essere FALLIMENTO anche senza che venga restituito alcun errore nella listaErrori
			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}
			
			
			// caso in cui devo aggiungere i risultati dell'anno successivo
			if(includiAnnoSuccessivo) {
				ExternalServiceResponseWrapper<PagedList<Impegno>> responseAnnosucc = new ExternalServiceResponseWrapper<PagedList<Impegno>>();	
				req.setAnnoBilancio(req.getAnnoBilancio()+1);
				responseAnnosucc = estraiImpegniDaSistemaContabile(filtroImpegni, page, size, filtri, ricercaService, req);
				// può essere FALLIMENTO anche senza che venga restituito alcun errore nella listaErrori
				if (!responseAnnosucc.isSuccess() && responseAnnosucc.getMessages() != null && responseAnnosucc.getMessages().size() > 0) {
					responseAnnosucc.getErrors().addAll(responseAnnosucc.getMessages());
				}
				
				List<Impegno> listaImpegnoAnniSucc = responseAnnosucc.getResponse().getList();	
				
				// Filtriamo la lista degli impegni successivi im modo da utilizzare 
				// solo ed esclusoivamente impegni con ANNO pari al successivo (impegni futuri)
				// NON posso usare residui nell'esercizio successivo controllare l'anno impegno  
				List<Impegno> listImpegniSuccFiltrata = listaImpegnoAnniSucc.stream()
				        .filter(impegnoOrdine -> impegnoOrdine.getAnno() > req.getAnnoBilancio()-1)
				        .collect(Collectors.toList());
				
				
				responseAll.setSuccess(response.isSuccess() && responseAnnosucc.isSuccess());

				List<Impegno> combinedList = new ArrayList<>();
				combinedList.addAll(listImpegniSuccFiltrata);
				combinedList.addAll(listaImpegno);
				PagedList<Impegno> content = toPagedList(combinedList, combinedList.size(), page, size);
				responseAll.setResponse(content);
				
				List<String> combinedListErrors = new ArrayList<>();
				combinedListErrors.addAll(response.getErrors());
				combinedListErrors.addAll(responseAnnosucc.getErrors());				
				responseAll.setErrors(combinedListErrors);				
								
				List<String> combinedListMessages = new ArrayList<>();
				combinedListMessages.addAll(response.getMessages());
				combinedListMessages.addAll(responseAnnosucc.getMessages());				
				responseAll.setMessages(combinedListMessages);
				
				List<Errore> combinedListErrori = new ArrayList<>();
				combinedListErrori.addAll(response.getErrori());
				combinedListErrori.addAll(responseAnnosucc.getErrori());				
				responseAll.setErrori(combinedListErrori);				
			}else {
				responseAll = response;
			}

			
			
		}catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("ImpegnoHelperImpl", "getImpegniEsterni ", e);
			responseAll.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			responseAll.setErrors(errors);
			log.error("ImpegnoHelperImpl", "getImpegniEsterni Desc error --> "+e.getMessage());
			return responseAll;
		}
		return responseAll;
	}



	/**
	 * @param filtroImpegni
	 * @param page
	 * @param size
	 * @param filtri
	 * @param methodName
	 * @param ricercaService
	 * @param req
	 * @return
	 * @throws SagaException
	 */
	protected ExternalServiceResponseWrapper<PagedList<Impegno>> estraiImpegniDaSistemaContabile(
			FiltroImpegni filtroImpegni, int page, int size, Boolean filtri, 
			FdeWSAppjRicercaGateway ricercaService, RicercaImpegno req) throws SagaException {
		ExternalServiceResponseWrapper<PagedList<Impegno>> response;
		RicercaImpegnoResponse ricercaImpegnoResponse = ricercaService.ricercaImpegno(req);
		final String methodName = "estraiImpegniDaSistemaContabile";
		log.info(methodName, ricercaImpegnoResponse.getImpegni().size());
		response = initResponse(methodName, ricercaImpegnoResponse);
		if (response.isSuccess() && ricercaImpegnoResponse.getImpegni() != null && ricercaImpegnoResponse.getTotaleRisultati() != null) {			
			List<fdewsappjricercagateway.Impegno> impegnosSIAC = ricercaImpegnoResponse.getImpegni();
			if (ricercaImpegnoResponse.getTotaleRisultati().intValue() > NUM_ELEMENTI_PER_PAGINA) {
				int numPagine = ricercaImpegnoResponse.getTotaleRisultati().intValue() / NUM_ELEMENTI_PER_PAGINA;
				if (ricercaImpegnoResponse.getTotaleRisultati().intValue() % NUM_ELEMENTI_PER_PAGINA != 0) {
					numPagine++;
				}
				for (int pagina = 2; pagina <= numPagine; pagina++) {
					req.setNumeroPagina(pagina);
					ricercaImpegnoResponse = ricercaService.ricercaImpegno(req);
					response = initResponse(methodName, ricercaImpegnoResponse);
					if (response.isSuccess() && ricercaImpegnoResponse.getImpegni() != null) {
						impegnosSIAC.addAll(ricercaImpegnoResponse.getImpegni());
					}
				}
			}				
			List<Impegno> listImpegnoSIAC = CpassSicrawebMappers.IMPEGNO.toModels(impegnosSIAC);
			// da settare l'anno bilancio da filtro
			if(filtri) {
				listImpegnoSIAC = applicaFiltro(listImpegnoSIAC, filtroImpegni);
			}
			PagedList<Impegno> content = toPagedList(listImpegnoSIAC, listImpegnoSIAC.size(), page, size);
			response.setResponse(content);
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
	private RicercaImpegno composeRequestRicercaImpegno(Map<String, String> params, FiltroImpegni filtroImpegni , int page, int size) {
		final String methodName = "composeRequestRicercaImpegno";
		Impegno impegno = filtroImpegni.getSubimpegno().getImpegno();
		Provvedimento provvedimento = new Provvedimento();
		if(filtroImpegni.getTestataOrdine()!=null && filtroImpegni.getTestataOrdine().getProvvedimento()!= null) {
			provvedimento = filtroImpegni.getTestataOrdine().getProvvedimento();
		}
			
		RicercaImpegno req = new RicercaImpegno();
		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));

		if(filtroImpegni.getAnnoEsercizio()==null) {
			// anno bilancio = anno corrente (Obbligatorio)
			Calendar calendar = Calendar.getInstance(); 
			req.setAnnoBilancio(calendar.get(Calendar.YEAR));
		}else {
			req.setAnnoBilancio(filtroImpegni.getAnnoEsercizio());
		}	
		req.setAnnoBilancio(req.getAnnoBilancio());
		//TODO da commentare serve per fare le prove in test dato che il bilancio nuovo è chiuso attualmente 
		//req.setAnnoBilancio(2020);
		req.setAnnoImpegno(impegno.getAnno());
		req.setNumeroImpegno(impegno.getNumero());
		
		req.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		req.setNumeroProvvedimento(impegno.getNumeroProvvedimento()!= null ? Integer.parseInt(impegno.getNumeroProvvedimento()) : null);
		//req.setSettoreProvvedimento(impegno.getSettoreProvvedimento());
		if(provvedimento!=null && provvedimento.getProvvedimentoTipo()!= null && provvedimento.getProvvedimentoTipo().getCodice()!= null) {
			req.setCodiceTipoProvvedimento(provvedimento.getProvvedimentoTipo().getCodice());
		}//else {ricercaTipoProvvedimento(params, impegno, req);}
		
		Boolean isAttiUnivoci = false;
		try {
			isAttiUnivoci = getParameter(params, SicrawebConfigurationParams.NUMERAZIONE_ATTI_UNIVOCA).equalsIgnoreCase("true");
		}catch(Exception e) {
			log.error(methodName, "***************************************************************************************");
			log.error(methodName, "NUMERAZIONE_ATTI_UNIVOCA non presente in tabella parametri valori possibili true/ false");
			log.error(methodName, "***************************************************************************************");
		}
		
		if(!isAttiUnivoci && provvedimento!=null && provvedimento.getSettore() != null && provvedimento.getSettore().getCodice()!= null) {
			//se il flg_direzione cpass_d_tipo settore da settore del provvedimento true CDR else cdc
			req.setCodiceStruttura(provvedimento.getSettore().getCodice());
			req.setCodiceTipoStruttura("CDR");
			TipoSettore ts = provvedimento.getSettore().getTipoSettore();
			if(ts == null) {
				log.warn(methodName, "ATTENZIONE TIPO SETTORE NON VALORIZZATO");
			}else if(ts.getFlagDirezione().equals(Boolean.FALSE)) {
				req.setCodiceTipoStruttura("CDC");
			}
		}
		
		// This service is 1-based
		req.setNumeroPagina(1);
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);
		
		return req;
	}


	///////////////////////NUOVA GESTIONE /////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ExternalServiceResponseWrapper<PagedList<Impegno>> getImpegniEsterni(Map<String, String> params, FiltroImpegniNew filtroImpegniNew, int page, int size,Boolean filtri) {
		final String methodName = "getImpegniEsterni";
		
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		log.info(methodName, "wsdlUrl " + wsdlUrl);
		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();
		addHandlerResolver(params, ricercaServiceService);
		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();
		
		RicercaImpegno req = composeRequestRicercaImpegno(params, filtroImpegniNew, page, size);
		
		ExternalServiceResponseWrapper<PagedList<Impegno>> response = new ExternalServiceResponseWrapper<PagedList<Impegno>>();
		try {
			RicercaImpegnoResponse res = ricercaService.ricercaImpegno(req);
			log.info(methodName, res.getImpegni().size());
			response = initResponse(methodName, res);
			if (response.isSuccess() && res.getImpegni() != null && res.getTotaleRisultati() != null) {
				
				List<fdewsappjricercagateway.Impegno> impegnosSIAC = res.getImpegni();
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
				
				List<Impegno> listImpegnoSIAC = CpassSicrawebMappers.IMPEGNO.toModels(impegnosSIAC);
				// da settare l'anno bilancio da filtro
				if(filtri) {
					listImpegnoSIAC = applicaFiltro(listImpegnoSIAC, filtroImpegniNew);
				}
				PagedList<Impegno> content = toPagedList(listImpegnoSIAC, listImpegnoSIAC.size(), page, size);
				response.setResponse(content);
			}
			
			// può essere FALLIMENTO anche senza che venga restituito alcun errore nella listaErrori
			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}
		} catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("ImpegnoHelperImpl", "getImpegniEsterni ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("ImpegnoHelperImpl", "getImpegniEsterni Desc error --> "+e.getMessage());
			return response;
		}
		return response;
	}
	
	private List<Impegno> applicaFiltro(List<Impegno> listImpegnoSIAC, FiltroImpegniNew filtroImpegniNew) {
		String statoImpegno = filtroImpegniNew.getStatoImpegno() == null ? "" : filtroImpegniNew.getStatoImpegno().trim();
		if (listImpegnoSIAC != null  && !statoImpegno.equals("")) {
			List<Impegno> listImpegnoNew = new ArrayList<Impegno>();
			for (Impegno impegnoSIAC : listImpegnoSIAC) {
				List<Subimpegno> listSubImpegnoSIAC = impegnoSIAC.getSubimpegni();
				if (listSubImpegnoSIAC != null && listSubImpegnoSIAC.size() > 0) {
					List<Subimpegno> listSubImpegnoNew = new ArrayList<Subimpegno>();

					for (Subimpegno subimpegnoSIAC : listSubImpegnoSIAC) {
						// scartare tutti i subimpegni non definitivi (subimpegni.stato.codice != ‘D’)
						// NOTA: gli impegni che presentano subimpegni hanno sempre uno stato “N” (Definitivo Non Liquidabile)
						if (!subimpegnoSIAC.getStato().equalsIgnoreCase(filtroImpegniNew.getStatoImpegno())) {
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
					if (!impegnoSIAC.getStato().equalsIgnoreCase(filtroImpegniNew.getStatoImpegno())) {
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
	private RicercaImpegno composeRequestRicercaImpegno(Map<String, String> params, FiltroImpegniNew filtroImpegniNew , int page, int size) {
		final String methodName = "composeRequestRicercaImpegno";
		Provvedimento provvedimento = filtroImpegniNew.getProvvedimento();
		RicercaImpegno req = new RicercaImpegno();
		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));

		int annoBil = filtroImpegniNew.getAnnoEsercizio()==null ? Calendar.getInstance().get(Calendar.YEAR) : filtroImpegniNew.getAnnoEsercizio();
		req.setAnnoBilancio(annoBil);
		//TODO da commentare serve per fare le prove in test dato che il bilancio nuovo è chiuso attualmente 
		//req.setAnnoBilancio(2020);
		req.setAnnoImpegno(filtroImpegniNew.getAnnoImpegno());
		req.setNumeroImpegno(filtroImpegniNew.getNumeroImpegno());
		
		if(provvedimento!=null) {
			req.setAnnoProvvedimento(provvedimento.getAnno()!= null ? provvedimento.getAnno() :null);
			req.setNumeroProvvedimento(provvedimento.getNumero()!= null ? Integer.parseInt(provvedimento.getNumero()) : null);
			if(provvedimento.getProvvedimentoTipo()!= null && provvedimento.getProvvedimentoTipo().getCodice()!= null) {
				req.setCodiceTipoProvvedimento(provvedimento.getProvvedimentoTipo().getCodice());
			}	
			
			Boolean isAttiUnivoci = false;
			try {
				isAttiUnivoci = getParameter(params, SicrawebConfigurationParams.NUMERAZIONE_ATTI_UNIVOCA).equalsIgnoreCase("true");
			}catch(Exception e) {
				log.error(methodName, "***************************************************************************************");
				log.error(methodName, "NUMERAZIONE_ATTI_UNIVOCA non presente in tabella parametri valori possibili true/ false");
				log.error(methodName, "***************************************************************************************");
			}
			
			if(!isAttiUnivoci && provvedimento.getSettore() != null && provvedimento.getSettore().getCodice()!= null) {
				//se il flg_direzione cpass_d_tipo settore da settore del provvedimento true CDR else cdc
				req.setCodiceStruttura(provvedimento.getSettore().getCodice());
				req.setCodiceTipoStruttura("CDR");
				TipoSettore ts = provvedimento.getSettore().getTipoSettore();
				if(ts == null) {
					log.warn(methodName, "ATTENZIONE TIPO SETTORE NON VALORIZZATO");
				}else if(ts.getFlagDirezione().equals(Boolean.FALSE)) {
					req.setCodiceTipoStruttura("CDC");
				}
			}
			
		}
		// This service is 1-based
		req.setNumeroPagina(1);
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);		
		return req;
	}
	
	////////////// DA BUTTARE /////////////////////////////////////////////////
/*
	@Override
	public ExternalServiceResponseWrapper<PagedList<Impegno>> getImpegniEsterniAnnoSuccessivo(Map<String, String> params, FiltroImpegni filtroImpegni, int page, int size,Boolean filtri) {
		final String methodName = "getImpegniEsterni";
		
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		log.info(methodName, "wsdlUrl " + wsdlUrl);
		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();
		addHandlerResolver(params, ricercaServiceService);
		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();		
		ExternalServiceResponseWrapper<PagedList<Impegno>> response = new ExternalServiceResponseWrapper<PagedList<Impegno>>();	
		RicercaImpegno req = composeRequestRicercaImpegnoAnnoSuccessivo(params, filtroImpegni, page, size);	
		try {
			response = estraiImpegniDaSistemaContabile(filtroImpegni, page, size, filtri, methodName, ricercaService,req);			
			// può essere FALLIMENTO anche senza che venga restituito alcun errore nella listaErrori
			if (!response.isSuccess() && response.getMessages() != null && response.getMessages().size() > 0) {
				response.getErrors().addAll(response.getMessages());
			}		
		}catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("ImpegnoHelperImpl", "getImpegniEsterni ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("ImpegnoHelperImpl", "getImpegniEsterni Desc error --> "+e.getMessage());
			return response;
		}
		return response;
	}

	private RicercaImpegno composeRequestRicercaImpegnoAnnoSuccessivo(Map<String, String> params, FiltroImpegni filtroImpegni , int page, int size) {
		final String methodName = "composeRequestRicercaImpegno";
		Impegno impegno = filtroImpegni.getSubimpegno().getImpegno();
		Provvedimento provvedimento = new Provvedimento();
		if(filtroImpegni.getTestataOrdine()!=null && filtroImpegni.getTestataOrdine().getProvvedimento()!= null) {
			provvedimento = filtroImpegni.getTestataOrdine().getProvvedimento();
		}
			
		RicercaImpegno req = new RicercaImpegno();
		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));

		if(filtroImpegni.getAnnoEsercizio()==null) {
			// anno bilancio = anno corrente (Obbligatorio)
			Calendar calendar = Calendar.getInstance(); 
			req.setAnnoBilancio(calendar.get(Calendar.YEAR));
		}else {
			req.setAnnoBilancio(filtroImpegni.getAnnoEsercizio());
		}	
		req.setAnnoBilancio(req.getAnnoBilancio() + 1);
		//TODO da commentare serve per fare le prove in test dato che il bilancio nuovo è chiuso attualmente 
		//req.setAnnoBilancio(2020);
		req.setAnnoImpegno(impegno.getAnno());
		req.setNumeroImpegno(impegno.getNumero());
		
		req.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		req.setNumeroProvvedimento(impegno.getNumeroProvvedimento()!= null ? Integer.parseInt(impegno.getNumeroProvvedimento()) : null);
		//req.setSettoreProvvedimento(impegno.getSettoreProvvedimento());
		if(provvedimento!=null && provvedimento.getProvvedimentoTipo()!= null && provvedimento.getProvvedimentoTipo().getCodice()!= null) {
			req.setCodiceTipoProvvedimento(provvedimento.getProvvedimentoTipo().getCodice());
		}//else {ricercaTipoProvvedimento(params, impegno, req);}
		
		Boolean isAttiUnivoci = false;
		try {
			isAttiUnivoci = getParameter(params, SicrawebConfigurationParams.NUMERAZIONE_ATTI_UNIVOCA).equalsIgnoreCase("true");
		}catch(Exception e) {
			log.error(methodName, "***************************************************************************************");
			log.error(methodName, "NUMERAZIONE_ATTI_UNIVOCA non presente in tabella parametri valori possibili true/ false");
			log.error(methodName, "***************************************************************************************");
		}
		
		if(!isAttiUnivoci && provvedimento!=null && provvedimento.getSettore() != null && provvedimento.getSettore().getCodice()!= null) {
			//se il flg_direzione cpass_d_tipo settore da settore del provvedimento true CDR else cdc
			req.setCodiceStruttura(provvedimento.getSettore().getCodice());
			req.setCodiceTipoStruttura("CDR");
			TipoSettore ts = provvedimento.getSettore().getTipoSettore();
			if(ts == null) {
				log.warn(methodName, "ATTENZIONE TIPO SETTORE NON VALORIZZATO");
			}else if(ts.getFlagDirezione().equals(Boolean.FALSE)) {
				req.setCodiceTipoStruttura("CDC");
			}
		}
		
		// This service is 1-based
		req.setNumeroPagina(1);
		req.setNumeroElementiPerPagina(NUM_ELEMENTI_PER_PAGINA);
		
		return req;
	}
	*/
}
