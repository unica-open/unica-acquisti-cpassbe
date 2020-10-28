/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe.StampaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.stampe.StampaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ParametroStampa;

/**
 * Retrieves 
 */
public class StampaService extends BaseStampeService<StampaRequest, StampaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param stampeDad the stampe DAD
	 */
	public StampaService(ConfigurationHelper configurationHelper, StampeDad stampeDad) {
		super(configurationHelper, stampeDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getNomeStampa(), "nome stampa");
		//checkNotNull(request.getFormatFile(), "formato file");
	}

	@Override
	protected void execute() {
		String formatoFile = request.getFormatFile();
		List<ParametroStampa> parametri = stampeDad.getParametriStampeByNomeStampa(request.getNomeStampa());
		
		if(StringUtils.isBlank(formatoFile) || formatoFile.equals("default")) {
			formatoFile = parametri.get(0).getFormatoStampa();
		}
		
    	List<NameValuePair> params = new ArrayList<>();
		for(int i = 0; i < parametri.size(); i++) {
			ParametroStampa ps = parametri.get(i);
			params.add(new BasicNameValuePair(ps.getParametro(), request.getListaParametri().get(i)));
		}
		callBirt(params, parametri.get(0).getFileNameTemplate(), formatoFile);
	}

}








