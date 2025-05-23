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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNso;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNso;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DestinatarioInvioNso and CpassTOrdDestinatarioInvioNso
 */
@Mapper(uses = {
		DestinatarioOrdineMapper.class, TestataOrdineMapper.class, UtenteMapper.class
})
public interface DestinatarioInvioNsoMapper extends BaseMapperInterface<DestinatarioInvioNso, CpassTOrdDestinatarioInvioNso> {

	@Override
	@Mapping(source = "cpassTOrdDestinatarioOrdine", target = "destinatario")
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "cpassTUtente", target = "utenteInvio")
	DestinatarioInvioNso toModel(CpassTOrdDestinatarioInvioNso entity);

	@Override
	@IterableMapping(elementTargetType = DestinatarioInvioNso.class)
	List<DestinatarioInvioNso> toModels(Collection<CpassTOrdDestinatarioInvioNso> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDestinatarioInvioNso toEntity(DestinatarioInvioNso model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDestinatarioInvioNso.class)
	List<CpassTOrdDestinatarioInvioNso> toEntities(Collection<DestinatarioInvioNso> models);

}
