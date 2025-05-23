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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DestinatarioInvioNsoXml and CpassTOrdDestinatarioInvioNsoXml
 */
@Mapper(uses = {
		DestinatarioInvioNsoMapper.class
})
public interface DestinatarioInvioNsoXmlMapper extends BaseMapperInterface<DestinatarioInvioNsoXml, CpassTOrdDestinatarioInvioNsoXml> {

	@Override
	@Mapping(source = "cpassTOrdDestinatarioInvioNso", target = "destinatarioInvioNso")
	DestinatarioInvioNsoXml toModel(CpassTOrdDestinatarioInvioNsoXml entity);

	@Override
	@IterableMapping(elementTargetType = DestinatarioInvioNsoXml.class)
	List<DestinatarioInvioNsoXml> toModels(Collection<CpassTOrdDestinatarioInvioNsoXml> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDestinatarioInvioNsoXml toEntity(DestinatarioInvioNsoXml model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDestinatarioInvioNsoXml.class)
	List<CpassTOrdDestinatarioInvioNsoXml> toEntities(Collection<DestinatarioInvioNsoXml> models);

}
