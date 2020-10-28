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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class PostRicercaListinoFornitoreService extends BaseCommonService<PostRicercaListinoFornitoreRequest, PostRicercaListinoFornitoreResponse> {

	private ListinoFornitore listinoFornitore;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaListinoFornitoreService(ConfigurationHelper configurationHelper,  CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		listinoFornitore = request.getListinoFornitore();
	}

	@Override
	protected void execute() {
		PagedList<ListinoFornitore> listinoFornitore = commonDad.getRicercaListinoFornitore(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getListinoFornitore()				
		);
		response.setListinoFornitore(listinoFornitore);
	}

	
}
