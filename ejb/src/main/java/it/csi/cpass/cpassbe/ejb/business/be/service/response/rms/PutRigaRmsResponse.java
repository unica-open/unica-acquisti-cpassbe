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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutRigaRmsResponse extends BasePutResponse{

	private RigaRms rigaRms;


	public RigaRms getRigaRms() {
		return rigaRms;
	}




	public void setRigaRms(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
	}




	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(rigaRms);
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.OK)
				.entity(serialized)
				.location(URI.create("riga-rms/" + rigaRms.getId()))
				.build();
	}

}
