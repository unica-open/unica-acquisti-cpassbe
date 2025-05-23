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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetPrioritaRequest.
 */
public class GetPrioritaRequest implements BaseRequest {

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetPrioritaRequest []");
		return builder.toString();
	}
}
