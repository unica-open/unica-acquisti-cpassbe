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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PutEvasioneInviaContabilitaByIdResponse extends BasePutResponse {

	private TestataEvasione testataEvasione;

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(testataEvasione);
		log.debug(methodName, "JSON response: " + serialized);
		return Response.status(Status.OK)
				.entity(serialized)
				.location(URI.create("evasione/" + testataEvasione.getId()))
				.build();
	}

}
