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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioOrdineMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, TestataEvasioneMapper.class, SettoreCustomMapper.class, DestinatarioOrdineMapper.class, StatoMapper.class })
public interface DestinatarioEvasioneMapper extends BaseMapperInterface<DestinatarioEvasione, CpassTOrdDestinatarioEvasione> {

	@Override
	@Mapping(source = "progressivo", target = "progressivo")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTOrdDestinatarioOrdine", target = "destinatarioOrdine")
	@Mapping(source = "cpassTOrdTestataEvasione", target = "testataEvasione")
	@Mapping(source = "cpassTSettore", target = "settore")
	DestinatarioEvasione toModel(CpassTOrdDestinatarioEvasione entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdDestinatarioEvasione toEntity(DestinatarioEvasione model);

	CpassTOrdDestinatarioEvasione cloneToEntity(CpassTOrdDestinatarioEvasione entity);

	DestinatarioEvasione cloneToModel(DestinatarioEvasione model);

}
