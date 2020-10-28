/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between Strings
 */
@Mapper
public interface StringMapper {

	/**
	 * Maps a string
	 * @param input the input string
	 * @return the mapped value
	 */
	@TrimmedString
	default String mapString(String input) {
		return StringUtils.trimToNull(input);
	}

}
