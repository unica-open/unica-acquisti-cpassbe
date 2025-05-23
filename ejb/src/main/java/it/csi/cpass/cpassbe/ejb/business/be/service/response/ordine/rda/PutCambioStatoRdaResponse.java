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
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutCambioStatoRdaResponse extends BasePutResponse{

	private TestataRda testataRda;


	public TestataRda getRigaRda() {
		return testataRda;
	}

	public void setTestataRda(TestataRda testataRda) {
		this.testataRda = testataRda;
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(testataRda);
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.OK)
				.entity(serialized)
				.location(URI.create("rda/" + testataRda.getId()))
				.build();
	}

}
