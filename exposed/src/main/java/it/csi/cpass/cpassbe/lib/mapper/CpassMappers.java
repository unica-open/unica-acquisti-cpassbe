/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EXPOSED submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.mapper;

import org.mapstruct.factory.Mappers;

import it.csi.cpass.cpassbe.lib.mapper.verificaevasione.EvasioneMapper;


/**
 * Mappers for Cpass-exposed
 */
public final class CpassMappers {
	/** Mapper for Evasioni */
	public static final EvasioneMapper EVASIONI = Mappers.getMapper(EvasioneMapper.class);
	/** Private constructor */
	private CpassMappers() {
		// Prevent instantiation
	}
}
