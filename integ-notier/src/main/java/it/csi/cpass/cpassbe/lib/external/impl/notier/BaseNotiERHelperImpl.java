/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.util.Map;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;

/**
 * Base helper for NotiER implementations
 */
public abstract class BaseNotiERHelperImpl extends BaseHelperImpl {

	/**
	 * Checks the baseparameters for NotiER integration
	 * 
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		checkParameters(params, NotiERConfigurationParams.HOST_NOTIER, NotiERConfigurationParams.PORTA_NOTIER);
		checkParameters(params, NotiERConfigurationParams.PORTA_NOTIER, NotiERConfigurationParams.PORTA_NOTIER);
		checkParameters(params, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID);
		checkParameters(params, NotiERConfigurationParams.NSO_PROFILE_ID, NotiERConfigurationParams.NSO_PROFILE_ID);
	}

}
