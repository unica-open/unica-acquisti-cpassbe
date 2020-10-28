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

import it.csi.cpass.cpassbe.lib.dto.ord.Provvedimento;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for Fornitore
 */
public interface ProvvedimentoHelper extends BaseHelper {

	/**
	 * 
	 * @param params
	 * @param provvedimento
	 * @return List<Provvedimento>
	 */
	ExternalServiceResponseWrapper<List<Provvedimento>> getProvvedimenti(Map<String, String> params, Provvedimento provvedimento);
}
