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
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutSmistamentoManualeRmsResponse extends BasePutResponse{

	private List<RigaRms> rigaRmss;

	/**
	 * @return the rigaRmss
	 */
	public List<RigaRms> getRigaRmss() {
		return rigaRmss;
	}
	/**
	 * @param rigaRmss the rigaRmss to set
	 */
	public void setRigaRmss(List<RigaRms> rigaRmss) {
		this.rigaRmss = rigaRmss;
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(rigaRmss);
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.OK)
				.entity(serialized)
				.location(URI.create("riga-rms/"))
				.build();
	}

}
