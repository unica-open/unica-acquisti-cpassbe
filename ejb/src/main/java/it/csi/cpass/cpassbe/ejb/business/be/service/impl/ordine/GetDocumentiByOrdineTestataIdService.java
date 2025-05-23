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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDocumentiByOrdineTestataIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDocumentiByOrdineTestataIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;

public class GetDocumentiByOrdineTestataIdService extends BaseService<GetDocumentiByOrdineTestataIdRequest, GetDocumentiByOrdineTestataIdResponse> {

	private final TestataOrdineDad testataOrdineDad;

	public GetDocumentiByOrdineTestataIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final List<DocumentiOrdine> documenti = testataOrdineDad.getDocumentiByOrdineTestataId(request.getId(),true);
		response.setLista(documenti);

	}

}
