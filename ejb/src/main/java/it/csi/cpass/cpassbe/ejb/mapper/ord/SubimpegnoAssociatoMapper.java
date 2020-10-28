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

import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SubImpegnoMapper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.SubImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, ImpegnoMapper.class, TestataOrdineMapper.class, SubImpegnoMapper.class })
public interface SubimpegnoAssociatoMapper extends BaseMapperInterface<SubImpegnoAssociato, CpassTOrdSubimpegnoAssociato> {
	
	@Override
	@Mapping(source = "subimpegnoAssociatoId", target = "id")
	@Mapping(source = "impegnoAnno", target = "anno")
	@Mapping(source = "impegnoAnnoEsercizio", target = "annoEsercizio")
	@Mapping(source = "impegnoNumero", target = "numero")
	@Mapping(source = "subimpegnoAnno", target = "annoSubImpegno")
	@Mapping(source = "subimpegnoImporto", target = "importoSubImpegno")
	@Mapping(source = "subimpegnoNumero", target = "numeroSubImpegno")
	@Mapping(source = "cpassTOrdImpegnoAssociato", target = "impegnoAssociato")
	@Mapping(source = "cpassTSubimpegno", target = "subImpegno")
	SubImpegnoAssociato toModel(CpassTOrdSubimpegnoAssociato entity);

	@Override
	@IterableMapping(elementTargetType = SubImpegnoAssociato.class)
	List<SubImpegnoAssociato> toModels(Collection<CpassTOrdSubimpegnoAssociato> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdSubimpegnoAssociato toEntity(SubImpegnoAssociato model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdSubimpegnoAssociato.class)
	List<CpassTOrdSubimpegnoAssociato> toEntities(Collection<SubImpegnoAssociato> models);

}
