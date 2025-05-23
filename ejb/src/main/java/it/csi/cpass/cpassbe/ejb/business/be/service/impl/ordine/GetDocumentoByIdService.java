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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDocumentoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDocumentoByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;

public class GetDocumentoByIdService extends BaseService<GetDocumentoByIdRequest, GetDocumentoByIdResponse> {

	private final TestataOrdineDad testataOrdineDad;

	public GetDocumentoByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final DocumentiOrdine documento = testataOrdineDad.getDocumentoById(request.getId());
		response.setDocumento(documento);

	}

}
