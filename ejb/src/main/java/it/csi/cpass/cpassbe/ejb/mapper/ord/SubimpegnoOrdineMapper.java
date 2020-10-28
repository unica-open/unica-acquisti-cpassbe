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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SubImpegnoMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, SubImpegnoMapper.class, TestataOrdineMapper.class })
public interface SubimpegnoOrdineMapper extends BaseMapperInterface<SubimpegnoOrdine, CpassTOrdSubimpegnoOrdine> {

	@Override
	@Mapping(source = "subimpegnoOrdineId", target = "id")
	@Mapping(source = "cpassTSubimpegno", target = "subimpegno")
	SubimpegnoOrdine toModel(CpassTOrdSubimpegnoOrdine entity);

	@Override
	@IterableMapping(elementTargetType = SubimpegnoOrdine.class)
	List<SubimpegnoOrdine> toModels(Collection<CpassTOrdSubimpegnoOrdine> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdSubimpegnoOrdine toEntity(SubimpegnoOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdSubimpegnoOrdine.class)
	List<CpassTOrdSubimpegnoOrdine> toEntities(Collection<SubimpegnoOrdine> models);

}
