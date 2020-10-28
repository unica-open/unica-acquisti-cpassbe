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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.mapper.CpassSiacMappers;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.integ.data._1.Soggetto;
import it.csi.siac.ricerche.svc._1.RicercaDettaglioSoggetti;
import it.csi.siac.ricerche.svc._1.RicercaDettaglioSoggettiResponse;
import it.csi.siac.ricerche.svc._1.RicercaService;
import it.csi.siac.ricerche.svc._1.RicercaService_Service;
import it.csi.siac.ricerche.svc._1.RicercaSinteticaSoggetti;
import it.csi.siac.ricerche.svc._1.RicercaSinteticaSoggettiResponse;

/**
 * Example POJO helper impl
 */
public class FornitoreHelperImpl extends BaseSiacHelperImpl implements FornitoreHelper {

	@Override
	public ExternalServiceResponseWrapper<List<Fornitore>> getFornitori(Map<String, String> params, FiltroFornitore filtroFornitore) {
		final String methodName = "getFornitori";

		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);

		RicercaService_Service ricercaServiceService = wsdlUrl != null ? new RicercaService_Service(wsdlUrl) : new RicercaService_Service();
		addHandlerResolver(params, ricercaServiceService);
		RicercaService ricercaService = ricercaServiceService.getRicercaServicePort();

		RicercaSinteticaSoggetti ricercaSinteticaSoggetti = composeRequest(params, filtroFornitore);
		RicercaSinteticaSoggettiResponse ricercaSinteticaSoggettiResponse = ricercaService.ricercaSinteticaSoggetti(ricercaSinteticaSoggetti);

		ExternalServiceResponseWrapper<List<Fornitore>> response = initResponse(methodName, ricercaSinteticaSoggettiResponse);
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
				ricercaDettaglioSoggetto.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
				ricercaDettaglioSoggetto.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));
				ricercaDettaglioSoggetto.setCodice(soggetto.getCodice());

				RicercaDettaglioSoggettiResponse ricercaDettaglioSoggettiResponse = ricercaService.ricercaDettaglioSoggetto(ricercaDettaglioSoggetto);
				response = initResponse(methodName, ricercaDettaglioSoggettiResponse);
				if (response.isSuccess() && ricercaDettaglioSoggettiResponse.getSoggetti() != null) {
					response.setResponse(
							ricercaDettaglioSoggettiResponse.getSoggetti().stream().map(CpassSiacMappers.FORNITORE::toModel).collect(Collectors.toList()));
				}

			} else {
				List<Fornitore> fornitores = soggettosFiltrati.stream().map(CpassSiacMappers.FORNITORE::toModel).collect(Collectors.toList());
				response.setResponse(fornitores);
			}
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
		req.setCodiceEnte(getParameter(params, SiacConfigurationParams.CODICE_ENTE));
		req.setCodiceFruitore(getParameter(params, SiacConfigurationParams.CODICE_APPLICATIVO));

		if (filtroFornitore.getFornitore() != null) {
			String codice = filtroFornitore.getFornitore().getCodice();
			if (codice != null) {
				req.setCodice(codice.trim());
			}

			// req.setDenominazione(StringHelper.mergeAsLike(fornitore.getCognome(), fornitore.getNome()));
			req.setDenominazione(StringHelper.mergeAsLike(filtroFornitore.getFornitore().getRagioneSociale()));

			req.setCodiceFiscale(filtroFornitore.getFornitore().getCodiceFiscale());
			req.setPartitaIva(filtroFornitore.getFornitore().getPartitaIva());
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
