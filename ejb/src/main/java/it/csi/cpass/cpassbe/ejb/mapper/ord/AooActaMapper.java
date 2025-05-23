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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface AooActaMapper extends BaseMapperInterface<AooActa, CpassDAooActa> {

	@Override
	AooActa toModel(CpassDAooActa entity);

	@Override
	@IterableMapping(elementTargetType = AooActa.class)
	List<AooActa> toModels(Collection<CpassDAooActa> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassDAooActa toEntity(AooActa model);

	@Override
	@IterableMapping(elementTargetType = CpassDAooActa.class)
	List<CpassDAooActa> toEntities(Collection<AooActa> models);

}
