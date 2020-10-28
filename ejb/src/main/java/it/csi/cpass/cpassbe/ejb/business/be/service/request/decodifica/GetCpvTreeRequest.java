/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetCpvRequest.
 */
public class GetCpvTreeRequest implements BaseRequest {

	public Boolean associatiOds;
	
	/**
	 * Constructor
	 * @param ods cpv associate a ods
	 */
	public GetCpvTreeRequest(Boolean associatiOds) {
		this.associatiOds = associatiOds;
	}
	
	/**
	 * @return the ods
	 */
	public boolean getAssociatiOds() {
		return associatiOds;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetCpvTreeRequest []");
		return builder.toString();
	}
}