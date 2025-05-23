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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdProtocolloOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class })
public interface ProtocolloOrdineMapper extends BaseMapperInterface<ProtocolloOrdine, CpassTOrdProtocolloOrdine> {

	@Override
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "cpassDAooActa", target = "aooActa")



	ProtocolloOrdine toModel(CpassTOrdProtocolloOrdine entity);

	@Override
	@IterableMapping(elementTargetType = ProtocolloOrdine.class)
	List<ProtocolloOrdine> toModels(Collection<CpassTOrdProtocolloOrdine> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdProtocolloOrdine toEntity(ProtocolloOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdProtocolloOrdine.class)
	List<CpassTOrdProtocolloOrdine> toEntities(Collection<ProtocolloOrdine> models);

}
