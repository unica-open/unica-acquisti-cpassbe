/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetDocumentoTrasportoByEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetDocumentoTrasportoByEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

public class GetDocumentoTrasportoByEvasioneService extends BaseService<GetDocumentoTrasportoByEvasioneRequest,GetDocumentoTrasportoByEvasioneResponse>{

	DocumentoTrasportoDad documentoTrasportoDad;

	public GetDocumentoTrasportoByEvasioneService(ConfigurationHelper configurationHelper, DocumentoTrasportoDad documentoTrasportoDad) {
		super(configurationHelper);
		this.documentoTrasportoDad = documentoTrasportoDad;
	}




	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdEvasione(),"idEvasione");
	}




	@Override
	protected void execute() {
		final UUID idEvasione = request.getIdEvasione();
		final DocumentoTrasporto documentoTrasporto = documentoTrasportoDad.findDocumentoTrasportoByIdEvasione(idEvasione);

		response.setDocumentoTrasporto(documentoTrasporto);

	}


}
