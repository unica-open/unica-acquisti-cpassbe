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

import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Permesso and CpassDPermesso
 */
@Mapper(uses = {RuoloMapper.class,PermessoMapper.class})
public interface RuoloPermessoMapper extends BaseMapperInterface<RuoloPermesso, CpassRRuoloPermesso> {

	@Override
	@Mapping(source = "cpassDRuolo", target = "ruolo")
	@Mapping(source = "cpassDPermesso", target = "permesso")

	RuoloPermesso toModel(CpassRRuoloPermesso entity);

	@Override
	@IterableMapping(elementTargetType = RuoloPermesso.class)
	List<RuoloPermesso> toModels(Collection<CpassRRuoloPermesso> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassRRuoloPermesso toEntity(RuoloPermesso model);

	@Override
	@IterableMapping(elementTargetType = CpassDPermesso.class)
	List<CpassRRuoloPermesso> toEntities(Collection<RuoloPermesso> models);

}
