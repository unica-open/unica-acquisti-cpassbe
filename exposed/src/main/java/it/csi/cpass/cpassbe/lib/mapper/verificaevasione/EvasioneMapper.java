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

import it.csi.cpass.cpassbe.data.verificaevasione.Evasioni;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between data.verificaevasione.Evasioni and lib.exposed.dto.Evasione
 */
@Mapper(uses = { ImpegnoMapper.class })
public interface EvasioneMapper extends BaseMapperInterface<Evasione, Evasioni> {

	@Override
	Evasione toModel( it.csi.cpass.cpassbe.data.verificaevasione.Evasioni entity);

	@Override
	@IterableMapping(elementTargetType = Evasione.class)
	List<Evasione> toModels(Collection<it.csi.cpass.cpassbe.data.verificaevasione.Evasioni> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	it.csi.cpass.cpassbe.data.verificaevasione.Evasioni toEntity(Evasione model);

	@Override
	@IterableMapping(elementTargetType = Evasioni.class)
	List<Evasioni> toEntities(Collection<Evasione> models);
}
