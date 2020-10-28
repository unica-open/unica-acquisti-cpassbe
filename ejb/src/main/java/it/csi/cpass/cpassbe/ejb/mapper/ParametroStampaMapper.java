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

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametroStampa;
import it.csi.cpass.cpassbe.lib.dto.ParametroStampa;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ParametroStampa and CpassTParametroStampas
 */
@Mapper
public interface ParametroStampaMapper extends BaseMapperInterface<ParametroStampa, CpassTParametroStampa> {

	@Override	
	@Mapping(source = "modulo", target = "modulo")
	@Mapping(source = "nomeStampa", target = "nomeStampa")
	@Mapping(source = "fileNameTemplate", target = "fileNameTemplate")
	@Mapping(source = "parametro", target = "parametro")
	@Mapping(source = "parametroTipo", target = "parametroTipo")
	@Mapping(source = "ordinamento", target = "ordinamento")
	@Mapping(source = "note", target = "note")
	@Mapping(source = "procedureUtilizzate", target = "procedureUtilizzate")
	@Mapping(source = "formatoStampa", target = "formatoStampa")
	
	ParametroStampa toModel(CpassTParametroStampa entity);

	@Override
	@IterableMapping(elementTargetType = Stato.class)
	List<ParametroStampa> toModels(Collection<CpassTParametroStampa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTParametroStampa toEntity(ParametroStampa model);

	@Override
	@IterableMapping(elementTargetType = CpassDStato.class)
	List<CpassTParametroStampa> toEntities(Collection<ParametroStampa> models);

}
