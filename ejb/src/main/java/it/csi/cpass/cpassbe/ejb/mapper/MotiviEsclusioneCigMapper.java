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

import it.csi.cpass.cpassbe.ejb.entity.CpassDMotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class})
public interface MotiviEsclusioneCigMapper extends BaseMapperInterface<MotiviEsclusioneCig, CpassDMotiviEsclusioneCig> {

	@Override
	@Mapping(source = "cpassTOrdTestataOrdines", target = "testataOrdines")
	MotiviEsclusioneCig toModel(CpassDMotiviEsclusioneCig entity);

	@Override
	@IterableMapping(elementTargetType = MotiviEsclusioneCig.class)
	List<MotiviEsclusioneCig> toModels(Collection<CpassDMotiviEsclusioneCig> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassDMotiviEsclusioneCig toEntity(MotiviEsclusioneCig model);

	@Override
	@IterableMapping(elementTargetType = CpassDMotiviEsclusioneCig.class)
	List<CpassDMotiviEsclusioneCig> toEntities(Collection<MotiviEsclusioneCig> models);

}
