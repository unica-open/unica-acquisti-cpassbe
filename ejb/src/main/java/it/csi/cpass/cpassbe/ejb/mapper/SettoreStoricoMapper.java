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

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettoreStorico;
import it.csi.cpass.cpassbe.lib.dto.SettoreStorico;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Parametro and CpassTParametro
 */
@Mapper
public interface SettoreStoricoMapper extends BaseMapperInterface<SettoreStorico, CpassTSettoreStorico> {

	@Override
	@Mapping(source = "note", target = "note")
	@Mapping(source = "settoreCodiceAttuale", target = "settoreCodiceAttuale")
	@Mapping(source = "settoreCodiceStorico", target = "settoreCodiceStorico")
	@Mapping(source = "settoreAttuale", target = "settoreAttuale")
	@Mapping(source = "settoreStorico", target = "settoreStorico")
	@Mapping(source = "cpassTEnte", target = "ente")
	SettoreStorico toModel(CpassTSettoreStorico entity);

	@Override
	@IterableMapping(elementTargetType = SettoreStorico.class)
	List<SettoreStorico> toModels(Collection<CpassTSettoreStorico> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTSettoreStorico.class)
	List<CpassTSettoreStorico> toEntities(Collection<SettoreStorico> models);

}
