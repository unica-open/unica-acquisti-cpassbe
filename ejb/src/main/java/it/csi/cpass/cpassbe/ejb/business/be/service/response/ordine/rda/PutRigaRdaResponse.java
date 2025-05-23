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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutRigaRdaResponse extends BasePutResponse{

	private RigaRda rigaRda;


	public RigaRda getRigaRda() {
		return rigaRda;
	}




	public void setRigaRda(RigaRda rigaRda) {
		this.rigaRda = rigaRda;
	}




	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(rigaRda);
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.OK)
				.entity(serialized)
				.location(URI.create("riga-rda/" + rigaRda.getId()))
				.build();
	}

}
