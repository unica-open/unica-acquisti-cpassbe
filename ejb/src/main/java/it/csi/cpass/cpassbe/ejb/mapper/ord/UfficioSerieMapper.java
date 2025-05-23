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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRUfficioSerie;
import it.csi.cpass.cpassbe.lib.dto.ord.UfficioSerie;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface UfficioSerieMapper extends BaseMapperInterface<UfficioSerie, CpassRUfficioSerie> {

	@Override
	@Mapping(source = "cpassTUfficio", target = "ufficio")
	UfficioSerie toModel(CpassRUfficioSerie entity);

	@Override
	@IterableMapping(elementTargetType = UfficioSerie.class)
	List<UfficioSerie> toModels(Collection<CpassRUfficioSerie> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassRUfficioSerie toEntity(UfficioSerie model);

	@Override
	@IterableMapping(elementTargetType = CpassRUfficioSerie.class)
	List<CpassRUfficioSerie> toEntities(Collection<UfficioSerie> models);

}
