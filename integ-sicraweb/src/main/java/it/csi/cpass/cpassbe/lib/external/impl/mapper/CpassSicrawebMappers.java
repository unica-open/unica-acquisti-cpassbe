/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.mapper;

import org.mapstruct.factory.Mappers;

/**
 * Mappers for Cpass-SIAC integration
 */
public final class CpassSicrawebMappers {
	/** Mapper for Fornitore */
	public static final FornitoreSicrawebMapper FORNITORE = Mappers.getMapper(FornitoreSicrawebMapper.class);
	/** Mapper for Impegno */
	public static final ImpegnoSicrawebMapper IMPEGNO = Mappers.getMapper(ImpegnoSicrawebMapper.class);
//	/** Mapper for Impegno */
	public static final DocumentoSpesaSicrawebMapper DOCUMENTI_SPESA = Mappers.getMapper(DocumentoSpesaSicrawebMapper.class);
	

	/** Private constructor */
	private CpassSicrawebMappers() {
		// Prevent instantiation
	}
}
