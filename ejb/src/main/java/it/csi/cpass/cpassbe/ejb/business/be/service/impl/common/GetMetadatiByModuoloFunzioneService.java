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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMetadatiByModuoloFunzioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetUfficiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMetadatiByModuoloFunzioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetUfficiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;

/**
 * Gets the Ufficios by settore
 */
public class GetMetadatiByModuoloFunzioneService extends BaseCommonService<GetMetadatiByModuoloFunzioneRequest, GetMetadatiByModuoloFunzioneResponse> {
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetMetadatiByModuoloFunzioneService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getFunzione(), "funzione");
		checkNotNull(request.getModulo(), "modulo");
	}
	
	@Override
	protected void execute() {
		List<MetadatiFunzione>lista = commonDad.getMetadatiByModuoloFunzione(request.getModulo(),request.getFunzione());
		response.setMetadatiFunzione(lista);
	}

}
