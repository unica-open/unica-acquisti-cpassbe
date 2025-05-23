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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Ods;

public class PostOggettiSpesaRequest  implements BaseRequest {

	private final Ods ods;

	public PostOggettiSpesaRequest(Ods ods) {
		this.ods = ods;
	}

	/**
	 * @return the ods
	 */
	public Ods getOds() {
		return ods;
	}
}
