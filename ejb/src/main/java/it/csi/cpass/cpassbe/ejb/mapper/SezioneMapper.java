/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface SezioneMapper extends BaseMapperInterface<Sezione, CpassTOrdSezione> {

	@Override
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTSettore", target = "settore")
	Sezione toModel(CpassTOrdSezione entity);

	@Override
	@IterableMapping(elementTargetType = Sezione.class)
	List<Sezione> toModels(Collection<CpassTOrdSezione> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdSezione toEntity(Sezione model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdSezione.class)
	List<CpassTOrdSezione> toEntities(Collection<Sezione> models);

}
