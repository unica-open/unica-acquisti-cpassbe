/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external;

import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for Fornitore
 */
public interface FornitoreHelper extends BaseHelper {

	/**
	 * Retrieves a list of Fornitore via the given filter
	 * @param params the params
	 * @param fornitore the filter to apply
	 * @return the element corresponding to the filter
	 */
	ExternalServiceResponseWrapper<List<Fornitore>> getFornitori(Map<String, String> params, FiltroFornitore filtroFornitore);
}
