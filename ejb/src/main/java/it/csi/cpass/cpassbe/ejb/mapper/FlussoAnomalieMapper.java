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

import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoAnomalie;
import it.csi.cpass.cpassbe.lib.dto.FlussoAnomalie;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface FlussoAnomalieMapper extends BaseMapperInterface<FlussoAnomalie, CpassTFlussoAnomalie> {

	@Override
	FlussoAnomalie toModel(CpassTFlussoAnomalie entity);

	@Override
	@IterableMapping(elementTargetType = FlussoAnomalie.class)
	List<FlussoAnomalie> toModels(Collection<CpassTFlussoAnomalie> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTFlussoAnomalie toEntity(FlussoAnomalie model);

	@Override
	@IterableMapping(elementTargetType = CpassTFlussoAnomalie.class)
	List<CpassTFlussoAnomalie> toEntities(Collection<FlussoAnomalie> models);

}
