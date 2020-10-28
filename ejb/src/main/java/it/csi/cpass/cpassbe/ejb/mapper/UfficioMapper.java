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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ufficio and CpassTUfficio
 */
@Mapper
public interface UfficioMapper extends BaseMapperInterface<Ufficio, CpassTUfficio> {

	@Override
	@Mapping(source = "ufficioCodice", target = "codice")
	@Mapping(source = "ufficioDescrizione", target = "descrizione")
	Ufficio toModel(CpassTUfficio entity);

	@Override
	@IterableMapping(elementTargetType = Ufficio.class)
	List<Ufficio> toModels(Collection<CpassTUfficio> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTUfficio toEntity(Ufficio model);

	@Override
	@IterableMapping(elementTargetType = CpassTUfficio.class)
	List<CpassTUfficio> toEntities(Collection<Ufficio> models);

}
