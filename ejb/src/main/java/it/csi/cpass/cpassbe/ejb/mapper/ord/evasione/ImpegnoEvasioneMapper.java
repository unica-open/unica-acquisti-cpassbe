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

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.CausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, ImpegnoMapper.class, RigaEvasioneMapper.class, CausaleSospensioneEvasione.class })
public interface ImpegnoEvasioneMapper extends BaseMapperInterface<ImpegnoEvasione, CpassTOrdImpegnoEvasione> {

	@Override
	@Mapping(source = "cpassTImpegno", target = "impegno")
	@Mapping(source = "cpassTOrdImpegnoOrdine", target = "impegnoOrdine")
	@Mapping(source = "cpassTOrdRigaEvasione", target = "rigaEvasione")
	@Mapping(source = "cpassDOrdCausaleSospensioneEvasione", target = "causaleSospensioneEvasione")
	ImpegnoEvasione toModel(CpassTOrdImpegnoEvasione entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdImpegnoEvasione toEntity(ImpegnoEvasione model);

	CpassTOrdImpegnoEvasione cloneToEntity(CpassTOrdImpegnoEvasione entity);

	ImpegnoEvasione cloneToModel(ImpegnoEvasione model);

}
