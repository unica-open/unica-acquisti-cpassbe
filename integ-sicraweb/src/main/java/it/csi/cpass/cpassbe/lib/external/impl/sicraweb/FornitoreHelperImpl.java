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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fdewsappjricercagateway.FdeWSAppjRicercaGateway;
import fdewsappjricercagateway.FdeWSAppjRicercaGatewayService;
import fdewsappjricercagateway.RicercaDettaglioSoggetti;
import fdewsappjricercagateway.RicercaDettaglioSoggettiResponse;
import fdewsappjricercagateway.RicercaSinteticaSoggetti;
import fdewsappjricercagateway.RicercaSinteticaSoggettiResponse;
import fdewsappjricercagateway.SagaException;
import fdewsappjricercagateway.Soggetto;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.impl.mapper.CpassSicrawebMappers;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.utils.SicrawebConfigurationParams;

/**
 * Example POJO helper impl
 */
public class FornitoreHelperImpl extends BaseSicrawebRicercaHelperImpl implements FornitoreHelper {

	@Override
	public ExternalServiceResponseWrapper<List<Fornitore>> getFornitori(Map<String, String> params, FiltroFornitore filtroFornitore) {
		
		final String methodName = "getFornitori";

		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);

		FdeWSAppjRicercaGatewayService ricercaServiceService = wsdlUrl != null ? new FdeWSAppjRicercaGatewayService(wsdlUrl) : new FdeWSAppjRicercaGatewayService();
		addHandlerResolver(params, ricercaServiceService);

		FdeWSAppjRicercaGateway ricercaService = ricercaServiceService.getFdeWSAppjRicercaGateway();
		
		ExternalServiceResponseWrapper<List<Fornitore>> response = new  ExternalServiceResponseWrapper<List<Fornitore>>();
		
		RicercaSinteticaSoggetti ricercaSinteticaSoggetti = composeRequest(params, filtroFornitore);
		

		try {
			RicercaSinteticaSoggettiResponse  ricercaSinteticaSoggettiResponse = ricercaService.ricercaSinteticaSoggetti(ricercaSinteticaSoggetti);
	
			response = initResponse(methodName, ricercaSinteticaSoggettiResponse);
			
			if (response.isSuccess() && ricercaSinteticaSoggettiResponse.getSoggetti() != null) {
	
				List<Soggetto> soggettosFiltrati = new ArrayList<Soggetto>();
				List<Soggetto> soggettos = ricercaSinteticaSoggettiResponse.getSoggetti();
	
				for (Soggetto soggetto : soggettos) {
					// fix il servizio non filtra correttamente per stato
					if (filtroFornitore.getStatoFornitore() == null) {
						soggettosFiltrati.add(soggetto);
					} else if (soggetto.getStato().getCodice().equals(filtroFornitore.getStatoFornitore())) {
						soggettosFiltrati.add(soggetto);
					}
				}
	
				if (soggettosFiltrati.size() == 1) {
					Soggetto soggetto = soggettosFiltrati.get(0);
	
					// fix CPASS-2 ORD-INSERIMENTO TESTATA ORDINE: mancano i dati dell'indirizzo del soggetto
					RicercaDettaglioSoggetti ricercaDettaglioSoggetto = new RicercaDettaglioSoggetti();
					ricercaDettaglioSoggetto.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
					ricercaDettaglioSoggetto.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));
					ricercaDettaglioSoggetto.setCodice(soggetto.getCodice());
	
					RicercaDettaglioSoggettiResponse ricercaDettaglioSoggettiResponse = ricercaService.ricercaDettaglioSoggetto(ricercaDettaglioSoggetto);
					response = initResponse(methodName, ricercaDettaglioSoggettiResponse);
					if (response.isSuccess() && ricercaDettaglioSoggettiResponse.getSoggetti() != null) {
						response.setResponse(ricercaDettaglioSoggettiResponse.getSoggetti().stream().map(CpassSicrawebMappers.FORNITORE::toModel).collect(Collectors.toList()));
					}
	
				} else {
					List<Fornitore> fornitores = soggettosFiltrati.stream().map(CpassSicrawebMappers.FORNITORE::toModel).collect(Collectors.toList());
					response.setResponse(fornitores);
				}
			}
		} catch (SagaException e) {
			// TODO Auto-generated catch block
			log.error("FornitoreHelperImpl", "getFornitori ", e);
			response.setSuccess(Boolean.FALSE);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("FornitoreHelperImpl", "getFornitori Desc error --> "+e.getMessage());
			return response;
		}

		return response;
	}

	/**
	 * Composes the request for the service
	 * 
	 * @param params    the parameters for the environment
	 * @param fornitore the service parameter
	 * @return the request
	 */
	private RicercaSinteticaSoggetti composeRequest(Map<String, String> params, FiltroFornitore filtroFornitore) {
		RicercaSinteticaSoggetti req = new RicercaSinteticaSoggetti();
		req.setCodiceEnte(getParameter(params, SicrawebConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SicrawebConfigurationParams.CODICE_APPLICATIVO));

		if (filtroFornitore.getFornitore() != null) {
			String codice = filtroFornitore.getFornitore().getCodice();
			if (codice != null) {
				req.setCodice(codice.trim());
			}

			req.setDenominazione(StringHelper.mergeAsLike(filtroFornitore.getFornitore().getRagioneSociale()));

			req.setCodiceFiscale(StringHelper.ifNotBlank(filtroFornitore.getFornitore().getCodiceFiscale()));
			req.setPartitaIva(StringHelper.ifNotBlank(filtroFornitore.getFornitore().getPartitaIva()));
		}

		// FIXME
		// se filtro per codice fiscale e stato -> "Errore di sistema: For input string: "it.csi.siac.siacintegser.model.base.Stato@729fccf9"
//		if (filtroFornitore.getStatoFornitore() != null) {
//			Stato stato = new Stato();
//			stato.setCodice(filtroFornitore.getStatoFornitore());
//			req.setStato(stato);
//		}

		return req;
	}

}
