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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, ImpegnoMapper.class, TestataOrdineMapper.class })
public interface ImpegnoAssociatoMapper extends BaseMapperInterface<ImpegnoAssociato, CpassTOrdImpegnoAssociato> {

	@Override
	@Mapping(source = "impegnoAssociatoId", target = "id")
	@Mapping(source = "impegnoAnno", target = "anno")
	@Mapping(source = "impegnoAnnoEsercizio", target = "annoEsercizio")
	@Mapping(source = "impegnoNumero", target = "numero")
	@Mapping(source = "cpassTImpegno", target = "impegno")
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	ImpegnoAssociato toModel(CpassTOrdImpegnoAssociato entity);

	@Override
	@IterableMapping(elementTargetType = ImpegnoAssociato.class)
	List<ImpegnoAssociato> toModels(Collection<CpassTOrdImpegnoAssociato> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdImpegnoAssociato toEntity(ImpegnoAssociato model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdImpegnoAssociato.class)
	List<CpassTOrdImpegnoAssociato> toEntities(Collection<ImpegnoAssociato> models);

}
