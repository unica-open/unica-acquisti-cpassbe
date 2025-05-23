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
package it.csi.cpass.cpassbe.lib.dto.custom;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public interface CsvAdapter {
	boolean validate();
	default Integer parseInteger(String value) {
		return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
	}
	default boolean parseBoolean(String value) {
		return "SI".equalsIgnoreCase(value);
	}
	default BigDecimal parseBigDecimal(String value) {
		return StringUtils.isEmpty(value) ? null : new BigDecimal(value.replaceAll(",", "."));
	}
}

