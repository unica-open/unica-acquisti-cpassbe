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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRSettoreAooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.SettoreAooActa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface SettoreAooActaMapper extends BaseMapperInterface<SettoreAooActa, CpassRSettoreAooActa> {

	@Override
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassDAooActa", target = "aooActa")
	SettoreAooActa toModel(CpassRSettoreAooActa entity);

	@Override
	@IterableMapping(elementTargetType = SettoreAooActa.class)
	List<SettoreAooActa> toModels(Collection<CpassRSettoreAooActa> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassRSettoreAooActa toEntity(SettoreAooActa model);

	@Override
	@IterableMapping(elementTargetType = CpassRSettoreAooActa.class)
	List<CpassRSettoreAooActa> toEntities(Collection<SettoreAooActa> models);

}
