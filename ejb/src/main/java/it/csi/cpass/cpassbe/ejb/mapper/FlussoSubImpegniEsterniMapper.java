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

import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface FlussoSubImpegniEsterniMapper extends BaseMapperInterface<FlussoSubimpegniEsterni, CpassTFlussoSubimpegniEsterni> {

	@Override
	FlussoSubimpegniEsterni toModel(CpassTFlussoSubimpegniEsterni entity);

	@Override
	@IterableMapping(elementTargetType = FlussoSubimpegniEsterni.class)
	List<FlussoSubimpegniEsterni> toModels(Collection<CpassTFlussoSubimpegniEsterni> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTFlussoSubimpegniEsterni toEntity(FlussoSubimpegniEsterni model);

	@Override
	@IterableMapping(elementTargetType = CpassTFlussoSubimpegniEsterni.class)
	List<CpassTFlussoSubimpegniEsterni> toEntities(Collection<FlussoSubimpegniEsterni> models);

}
