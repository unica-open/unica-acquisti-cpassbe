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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.base;

import javax.ws.rs.core.Response;

/**
 * Base response for PUT methods
 */
public abstract class BasePutResponse extends BaseResponse {

	@Override
	protected Response composeOwnResponse() {
		return Response.noContent().build();
	}
}
