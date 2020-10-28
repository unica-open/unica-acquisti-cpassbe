/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, ImpegnoMapper.class, TestataOrdineMapper.class })
public interface ImpegnoOrdineMapper extends BaseMapperInterface<ImpegnoOrdine, CpassTOrdImpegnoOrdine> {

	@Override
	@Mapping(source = "impegnoOrdineId", target = "id")
	@Mapping(source = "cpassTImpegno", target = "impegno")
	@Mapping(source = "cpassTOrdRigaOrdine", target = "rigaOrdine")
	ImpegnoOrdine toModel(CpassTOrdImpegnoOrdine entity);

	@Override
	@IterableMapping(elementTargetType = ImpegnoOrdine.class)
	List<ImpegnoOrdine> toModels(Collection<CpassTOrdImpegnoOrdine> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdImpegnoOrdine toEntity(ImpegnoOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdImpegnoOrdine.class)
	List<CpassTOrdImpegnoOrdine> toEntities(Collection<ImpegnoOrdine> models);

}
