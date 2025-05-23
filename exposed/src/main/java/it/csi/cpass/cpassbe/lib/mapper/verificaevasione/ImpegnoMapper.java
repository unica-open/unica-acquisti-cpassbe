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
package it.csi.cpass.cpassbe.lib.mapper.verificaevasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between data.verificaevasione.Evasioni and lib.exposed.dto.Evasione
 */
@Mapper
public interface ImpegnoMapper extends BaseMapperInterface<it.csi.cpass.cpassbe.lib.exposed.dto.Impegno, it.csi.cpass.cpassbe.data.verificaevasione.Impegno> {

	@Override
	@Mapping(source = "numeroPrenotazione", target = "numeroSubimpegno")
	it.csi.cpass.cpassbe.lib.exposed.dto.Impegno toModel(it.csi.cpass.cpassbe.data.verificaevasione.Impegno entity);

	@Override
	@IterableMapping(elementTargetType = it.csi.cpass.cpassbe.lib.exposed.dto.Impegno.class)
	List<it.csi.cpass.cpassbe.lib.exposed.dto.Impegno> toModels(Collection<it.csi.cpass.cpassbe.data.verificaevasione.Impegno> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	it.csi.cpass.cpassbe.data.verificaevasione.Impegno toEntity(it.csi.cpass.cpassbe.lib.exposed.dto.Impegno model);

	@Override
	@IterableMapping(elementTargetType = it.csi.cpass.cpassbe.data.verificaevasione.Impegno.class)
	List<it.csi.cpass.cpassbe.data.verificaevasione.Impegno> toEntities(Collection<it.csi.cpass.cpassbe.lib.exposed.dto.Impegno> models);
}
