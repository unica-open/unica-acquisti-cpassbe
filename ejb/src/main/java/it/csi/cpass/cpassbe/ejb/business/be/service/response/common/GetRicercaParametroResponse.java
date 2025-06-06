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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Parametro;

public class GetRicercaParametroResponse extends BaseGetResponse<Parametro> {

	private Parametro parametro;



	public Parametro getParametro() {
		return parametro;
	}



	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}



	@Override
	protected Parametro getEntity() {
		return parametro;
	}

}
