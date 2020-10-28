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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SubImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.StatoElOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SubimpegnoOrdineMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, ImpegnoEvasioneMapper.class, SubimpegnoOrdineMapper.class, SubImpegnoMapper.class })
public interface SubimpegnoEvasioneMapper extends BaseMapperInterface<SubimpegnoEvasione, CpassTOrdSubimpegnoEvasione> {

	@Override
	@Mapping(source = "cpassTOrdImpegnoEvasione", target = "impegnoEvasione")
	@Mapping(source = "cpassTOrdSubimpegnoOrdine", target = "subimpegnoOrdine")
	@Mapping(source = "cpassTSubimpegno", target = "subimpegno")
	@Mapping(source = "cpassDOrdCausaleSospensioneEvasione", target = "causaleSospensioneEvasione")
	
	
	SubimpegnoEvasione toModel(CpassTOrdSubimpegnoEvasione entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdSubimpegnoEvasione toEntity(SubimpegnoEvasione model);

	CpassTOrdSubimpegnoEvasione cloneToEntity(CpassTOrdSubimpegnoEvasione entity);

	SubimpegnoEvasione cloneToModel(SubimpegnoEvasione model);

}
