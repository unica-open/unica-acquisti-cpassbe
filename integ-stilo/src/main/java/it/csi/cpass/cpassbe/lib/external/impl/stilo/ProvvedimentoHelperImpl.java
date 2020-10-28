/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - STILO
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.stilo;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import it.csi.cpass.cpassbe.lib.dto.ord.Provvedimento;
import it.csi.cpass.cpassbe.lib.external.ProvvedimentoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.eng.auriga.repository2.model.identificazioneud.EstremiRegNumType;
import it.eng.auriga.repository2.model.identificazioneud.EstremiXIdentificazioneUD;
import it.eng.auriga.repository2.webservices.getmetadataud.Service;
import it.eng.auriga.repository2.webservices.getmetadataud.ServiceResponse;
import it.eng.auriga.repository2.webservices.getmetadataud.WSGetMetadataUdService;
import it.eng.auriga.repository2.webservices.getmetadataud.WSIGetMetadataUd;
/**
 * Example POJO helper impl
 */
public class ProvvedimentoHelperImpl extends BaseStiloHelperImpl implements ProvvedimentoHelper {

	@Override
	public ExternalServiceResponseWrapper<List<Provvedimento>> getProvvedimenti(Map<String, String> params, Provvedimento provvedimento) {
		checkBaseParameters(params);
		URL wsdlUrl = getWSDLUrl(params);
		
		WSGetMetadataUdService wSGetMetadataUdService = wsdlUrl != null ? new WSGetMetadataUdService(wsdlUrl) : new WSGetMetadataUdService();
		addHandlerResolver(params, wSGetMetadataUdService);
		WSIGetMetadataUd wSIGetMetadataUd = wSGetMetadataUdService.getWSGetMetadataUdPort();
		
		EstremiXIdentificazioneUD estremi = composeEstremi(params, provvedimento);
		Service req = composeRequest(params, estremi);
		ServiceResponse res = wSIGetMetadataUd.serviceOperation(req);
		// TODO: elaborate response
		/*
		ExternalServiceResponseWrapper<List<Fornitore>> response = initResponse(methodName, res);
		if(response.isSuccess() && res.getSoggetti() != null) {
			response.setResponse(
				res.getSoggetti()
					.stream()
					.map(CpassSiacMappers.FORNITORE::toModel)
					.collect(Collectors.toList()));
		}
		*/
		byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
		//Poi, l'XML che arriva è in ISO-8859-1 (ma io non so...), quindi per ottenerne una stringa
		String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);

		log.info("response", "xml " + xml);
		
		
		
		
		//Object xml = JAXBUtility.unmarshall(res, BaseOutputWS.class);
		ExternalServiceResponseWrapper<List<Provvedimento>> response = new ExternalServiceResponseWrapper<>();
		return response;

	}

	private EstremiXIdentificazioneUD composeEstremi(Map<String, String> params, Provvedimento provvedimento) {
		EstremiXIdentificazioneUD estremi = new EstremiXIdentificazioneUD();
		// Composizione dati
		estremi.setEstremiRegNum(new EstremiRegNumType());
		// FROM PARAMS!!
		estremi.getEstremiRegNum().setCategoriaReg("R");
		estremi.getEstremiRegNum().setSiglaReg("DD");
		estremi.getEstremiRegNum().setAnnoReg(provvedimento.getAnno());
		estremi.getEstremiRegNum().setNumReg(provvedimento.getNumero());
		
		return estremi;
	}

}






























