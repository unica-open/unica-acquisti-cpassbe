/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetTokenRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetTokenResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2Helper;

public class GetTokenService extends BaseCommonService<GetTokenRequest, GetTokenResponse> {

	private final SystemDad systemDad;
	/**
	 * 
	 * @param configurationHelper
	 * @param commonDad
	 * @param systemDad
	 */
	public GetTokenService(ConfigurationHelper configurationHelper,CommonDad commonDad,SystemDad systemDad) {
		super(configurationHelper, commonDad);
		this.systemDad = systemDad;
	}

	@Override
	protected void execute() {
		Integer numSec = request.getNumSecondi(); 
		Integer numToken = request.getNumToken();
		log.trace("GetTokenService", "num millisec Sec " + numSec);
		log.trace("GetTokenService", "numToken " + numToken);

		String OAUTH2_URL      = systemDad.getParametro("OAUTH2_URL",null, null).getValore();
		String CONSUMER_KEY    = systemDad.getParametro("CONSUMER_KEY",null, null).getValore();
		String CONSUMER_SECRET = systemDad.getParametro("CONSUMER_SECRET",null, null).getValore();
		log.trace("OAUTH2_URL ", OAUTH2_URL);
		log.trace("CONSUMER_KEY ", CONSUMER_KEY);
		log.trace("CONSUMER_SECRET ", CONSUMER_SECRET);
		for(int i=0;i <request.getNumToken() ;i++) {
			log.trace("GetTokenService", "inizio a staccare i token " + i);
			try {
				Thread.sleep(numSec);
			} catch (InterruptedException e) {
				log.error("Error Thread ", e);
				Thread.currentThread().interrupt();
			}
			initOAuth2Helper(		 OAUTH2_URL, CONSUMER_KEY, CONSUMER_SECRET);
			log.trace("GetTokenService", "token FINE");
		}
	}
	/**
	 * 
	 * @param OAUTH2_URL
	 * @param CONSUMER_KEY
	 * @param CONSUMER_SECRET
	 * @return OAuth2Helper
	 */
	protected OAuth2Helper initOAuth2Helper(		String OAUTH2_URL,String CONSUMER_KEY,String CONSUMER_SECRET) {
		return new OAuth2Helper.Builder()
				.oAuthURL(OAUTH2_URL)
				.consumerKey(CONSUMER_KEY)
				.consumerSecret(CONSUMER_SECRET)
				.type(OAuth2Helper.Type.BODY_GRANT)
				.reuseInstance(true)
				.build();
				
	}

	
}
