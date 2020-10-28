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

import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Permesso and CpassDPermesso
 */
@Mapper()
public interface PermessoMapper extends BaseMapperInterface<Permesso, CpassDPermesso> {

	@Override
	@Mapping(source = "permessoCodice", target = "codice")
	@Mapping(source = "permessoDescrizione", target = "descrizione")
	@Mapping(source = "permessoTipo", target = "tipo")
	@Mapping(source = "permessoTitoloBox", target = "titoloBox")
	@Mapping(source = "permessoVoceMenu", target = "voceMenu")
	//@Mapping(source = "permessoTrasversale", target = "trasversale")
	
	Permesso toModel(CpassDPermesso entity);
	
	@Override
	@IterableMapping(elementTargetType = Permesso.class)
	List<Permesso> toModels(Collection<CpassDPermesso> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassRRuoloPermessos", ignore = true)
	CpassDPermesso toEntity(Permesso model);

	@Override
	@IterableMapping(elementTargetType = CpassDPermesso.class)
	List<CpassDPermesso> toEntities(Collection<Permesso> models);

}
