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

import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.UtenteSettore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between UtenteSettore, CpassRUtenteSettore
 */
@Mapper(uses = {RuoloUtenteSettoreMapper.class, SettoreCustomMapper.class})
public interface UtenteSettoreMapper extends BaseMapperInterface<UtenteSettore, CpassRUtenteSettore> {

	@Override
	@Mapping(source = "cpassRRuoloUtenteSettores", target = "ruoloUtenteSettores")
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassTUtente", target = "utente")
	UtenteSettore toModel(CpassRUtenteSettore entity);


	@Override
	@IterableMapping(elementTargetType = UtenteSettore.class)
	List<UtenteSettore> toModels(Collection<CpassRUtenteSettore> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassRUtenteSettore toEntity(UtenteSettore model);

	@Override
	@IterableMapping(elementTargetType = CpassRUtenteSettore.class)
	List<CpassRUtenteSettore> toEntities(Collection<UtenteSettore> models);

}
