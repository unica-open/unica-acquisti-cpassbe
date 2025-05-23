/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external;

import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.EsitoProvvedimento;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
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
	@Deprecated
	ExternalServiceResponseWrapper<EsitoProvvedimento> getProvvedimenti(Map<String, String> params, Provvedimento provvedimento);

	/**
	 *
	 * @param params
	 * @param provvedimento
	 * @return List<Provvedimento>
	 */
	@Deprecated
	ExternalServiceResponseWrapper<EsitoProvvedimento> getDocumentiProvvedimentiAll(Map<String, String> params, Provvedimento provvedimento,String enteCode);

	/**
	 *
	 * @param params
	 * @param provvedimento
	 * @return List<Provvedimento>
	 */
	@Deprecated
	ExternalServiceResponseWrapper<EsitoProvvedimento> getDocumentiProvvedimenti(Map<String, String> params, Provvedimento provvedimento,String enteCode);

	/**
	 *
	 * @param params
	 * @param provvedimento
	 * @param enteCode
	 * @return
	 */
	ExternalServiceResponseWrapper<EsitoProvvedimento> getAttiPerAcquisti(Map<String, String> params, Provvedimento provvedimento,String enteCode);

}