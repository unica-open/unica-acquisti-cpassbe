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

import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.FlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface FlussoImpegniEsterniMapper extends BaseMapperInterface<FlussoImpegniEsterni, CpassTFlussoImpegniEsterni> {

	@Override
	FlussoImpegniEsterni toModel(CpassTFlussoImpegniEsterni entity);

	@Override
	@IterableMapping(elementTargetType = FlussoImpegniEsterni.class)
	List<FlussoImpegniEsterni> toModels(Collection<CpassTFlussoImpegniEsterni> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTFlussoImpegniEsterni toEntity(FlussoImpegniEsterni model);

	@Override
	@IterableMapping(elementTargetType = CpassTFlussoImpegniEsterni.class)
	List<CpassTFlussoImpegniEsterni> toEntities(Collection<FlussoImpegniEsterni> models);

}
