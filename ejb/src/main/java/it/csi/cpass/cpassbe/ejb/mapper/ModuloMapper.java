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

import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;
import it.csi.cpass.cpassbe.lib.dto.Modulo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Modulo and CpassTSettore
 */
@Mapper()
public interface ModuloMapper extends BaseMapperInterface<Modulo, CpassDModulo> {

	@Override
	@Mapping(source = "moduloCodice", target = "codice")
	@Mapping(source = "moduloDescrizione", target = "descrizione")
	Modulo toModel(CpassDModulo entity);

	@Override
	@IterableMapping(elementTargetType = Modulo.class)
	List<Modulo> toModels(Collection<CpassDModulo> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassRRuoloModulos", ignore = true)
	CpassDModulo toEntity(Modulo model);

	@Override
	@IterableMapping(elementTargetType = CpassDModulo.class)
	List<CpassDModulo> toEntities(Collection<Modulo> models);

}
