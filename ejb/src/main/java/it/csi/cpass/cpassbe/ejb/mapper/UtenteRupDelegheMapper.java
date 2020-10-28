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

import it.csi.cpass.cpassbe.ejb.entity.CpassTUtenteRupDeleghe;
import it.csi.cpass.cpassbe.lib.dto.UtenteRupDeleghe;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between InterventoStoricoRup, CpassRPbaInterventoRup
 */
@Mapper(uses = {UtenteMapper.class})
public interface UtenteRupDelegheMapper extends BaseMapperInterface<UtenteRupDeleghe, CpassTUtenteRupDeleghe> {

	@Override
	@Mapping(source = "cpassTUtenteRup", target = "utenteRup")
	@Mapping(source = "cpassTUtenteRupDelegato", target = "utenteRupDelegato")
	UtenteRupDeleghe toModel(CpassTUtenteRupDeleghe entity);
	
	
	@Override
	@IterableMapping(elementTargetType = UtenteRupDeleghe.class)
	List<UtenteRupDeleghe> toModels(Collection<CpassTUtenteRupDeleghe> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTUtenteRupDeleghe toEntity(UtenteRupDeleghe model);

	@Override
	@IterableMapping(elementTargetType = CpassTUtenteRupDeleghe.class)
	List<CpassTUtenteRupDeleghe> toEntities(Collection<UtenteRupDeleghe> models);

}
