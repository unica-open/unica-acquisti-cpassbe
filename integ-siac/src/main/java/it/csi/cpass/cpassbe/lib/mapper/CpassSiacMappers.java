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

import org.mapstruct.factory.Mappers;

/**
 * Mappers for Cpass-SIAC integration
 */
public final class CpassSiacMappers {
	/** Mapper for Fornitore */
	public static final FornitoreSiacMapper FORNITORE = Mappers.getMapper(FornitoreSiacMapper.class);
	/** Mapper for Impegno */
	public static final ImpegnoSiacMapper IMPEGNO = Mappers.getMapper(ImpegnoSiacMapper.class);
	/** Mapper for Impegno */
	public static final DocumentoSpesaSiacMapper DOCUMENTI_SPESA = Mappers.getMapper(DocumentoSpesaSiacMapper.class);
	

	/** Private constructor */
	private CpassSiacMappers() {
		// Prevent instantiation
	}
}
