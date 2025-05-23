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
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;

public class PostListinoFornitoreService extends BaseCommonService<PostListinoFornitoreRequest, PostListinoFornitoreResponse> {

	private ListinoFornitore listinoFornitore;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param decodificaDad the deodifica DAD
	 * @param programmaDad the programma DAD
	 */
	public PostListinoFornitoreService(ConfigurationHelper configurationHelper, CommonDad commonDad, FornitoreDad fornitoredad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		listinoFornitore = request.getListinoFornitore();
		checkModel(listinoFornitore.getOggettiSpesa(), "Oggetti Spesa");
		checkModel(listinoFornitore.getFornitore(), "Fornitore");
	}

	@Override
	protected void execute() {
		//intervento.setCpv(isEntityPresent(() -> decodificaDad.getCpv(intervento.getCpv().getId()), "cpv"));
		listinoFornitore = commonDad.saveListinoFornitore(listinoFornitore);
		response.setListinoFornitore(listinoFornitore);
	}
}
