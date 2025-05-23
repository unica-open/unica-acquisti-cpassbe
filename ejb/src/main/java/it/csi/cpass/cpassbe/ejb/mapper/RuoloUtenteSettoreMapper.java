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

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloUtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.RuoloUtenteSettore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between RuoloUtenteSettore, CpassRRuoloUtenteSettore
 */
@Mapper(uses = {RuoloMapper.class})
public interface RuoloUtenteSettoreMapper extends BaseMapperInterface<RuoloUtenteSettore, CpassRRuoloUtenteSettore> {

	@Override
	@Mapping(source = "cpassDRuolo", target = "ruolo")
	@Mapping(source = "cpassRUtenteSettore", target = "utenteSettore")
	RuoloUtenteSettore toModel(CpassRRuoloUtenteSettore entity);


	@Override
	@IterableMapping(elementTargetType = RuoloUtenteSettore.class)
	List<RuoloUtenteSettore> toModels(Collection<CpassRRuoloUtenteSettore> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassRRuoloUtenteSettore toEntity(RuoloUtenteSettore model);

	@Override
	@IterableMapping(elementTargetType = CpassRRuoloUtenteSettore.class)
	List<CpassRRuoloUtenteSettore> toEntities(Collection<RuoloUtenteSettore> models);

}
