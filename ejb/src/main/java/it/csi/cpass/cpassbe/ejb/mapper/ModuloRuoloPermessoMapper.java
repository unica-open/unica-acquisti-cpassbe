/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.entity.CpassRModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = {ModuloMapper.class,RuoloMapper.class,PermessoMapper.class})
public interface ModuloRuoloPermessoMapper extends BaseMapperInterface<ModuloRuoloPermesso, CpassRModuloRuoloPermesso> {
	@Override
	@Mapping(source = "cpassDModulo", target = "modulo")
	@Mapping(source = "cpassDRuolo", target = "ruolo")
	@Mapping(source = "cpassDPermesso", target = "permesso")
	@Mapping(source = "note", target = "note")

	ModuloRuoloPermesso toModel(CpassRModuloRuoloPermesso entity);

	@Override
	@IterableMapping(elementTargetType = ModuloRuoloPermesso.class)
	List<ModuloRuoloPermesso> toModels(Collection<CpassRModuloRuoloPermesso> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassRModuloRuoloPermesso toEntity(ModuloRuoloPermesso model);

	@Override
	@IterableMapping(elementTargetType = CpassRModuloRuoloPermesso.class)
	List<CpassRModuloRuoloPermesso> toEntities(Collection<ModuloRuoloPermesso> models);

}
