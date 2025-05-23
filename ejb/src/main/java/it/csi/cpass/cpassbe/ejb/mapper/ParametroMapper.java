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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Parametro and CpassTParametro
 */
@Mapper
public interface ParametroMapper extends BaseMapperInterface<Parametro, CpassTParametro> {

	@Override
	@Mapping(source = "parametroId", target = "parametroId")
	@Mapping(source = "chiave", target = "chiave")
	@Mapping(source = "valore", target = "valore")
	@Mapping(source = "abilitata", target = "abilitata")
	@Mapping(source = "riferimento", target = "riferimento")
	@Mapping(source = "ambiente", target = "ambiente")
	@Mapping(source = "note", target = "note")
	@Mapping(source = "cpassTEnte", target = "ente")

	Parametro toModel(CpassTParametro entity);

	@Override
	@IterableMapping(elementTargetType = Parametro.class)
	List<Parametro> toModels(Collection<CpassTParametro> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTParametro.class)
	List<CpassTParametro> toEntities(Collection<Parametro> models);

}
