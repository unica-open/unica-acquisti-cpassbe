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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseInviaOrdineNSOResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoInviaOrdineNSOService;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class InviaOrdineNSOResponse extends BaseInviaOrdineNSOResponse {

	private EsitoInviaOrdineNSOService esitoInviaOrdineNSOService;




	/**
	 * @return the esitoInviaOrdineNSOService
	 */
	public EsitoInviaOrdineNSOService getEsitoInviaOrdineNSOService() {
		return esitoInviaOrdineNSOService;
	}

	/**
	 * @param esitoInviaOrdineNSOService the esitoInviaOrdineNSOService to set
	 */
	public void setEsitoInviaOrdineNSOService(EsitoInviaOrdineNSOService esitoInviaOrdineNSOService) {
		this.esitoInviaOrdineNSOService = esitoInviaOrdineNSOService;
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(esitoInviaOrdineNSOService);
		log.debug(methodName, "JSON response: " + serialized);
		return Response.status(Status.OK)
				.entity(serialized)
				.header("Content-Length", Integer.valueOf(StringHelper.getStringLengthUTF8(serialized)))
				.location(URI.create("testata-ordine/" + esitoInviaOrdineNSOService.getTestataOrdine().getId()))
				.build();
	}

	@Override
	protected Object getEntity() {
		// TODO Auto-generated method stub
		return esitoInviaOrdineNSOService;
	}

}
