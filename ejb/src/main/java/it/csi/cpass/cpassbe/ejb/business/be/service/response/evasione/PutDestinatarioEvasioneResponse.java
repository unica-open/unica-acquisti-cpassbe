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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutDestinatarioEvasioneResponse extends BasePutResponse {

	private DestinatarioEvasione destinatarioEvasione;

	/**
	 * @return the DestinatarioEvasione
	 */
	public DestinatarioEvasione getDestinatarioEvasione() {
		return destinatarioEvasione;
	}

	/**
	 * @param DestinatarioEvasione the DestinatarioEvasione to set
	 */
	public void setDestinatario(DestinatarioEvasione destinatarioEvasione) {
		this.destinatarioEvasione = destinatarioEvasione;
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(destinatarioEvasione);
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.OK)
				.entity(serialized)
				.location(URI.create("destinatario/" + destinatarioEvasione.getId()))
				.build();
	}


}
