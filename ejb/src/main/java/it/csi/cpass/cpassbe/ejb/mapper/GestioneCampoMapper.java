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

import it.csi.cpass.cpassbe.ejb.entity.CpassTGestioneCampo;
import it.csi.cpass.cpassbe.lib.dto.GestioneCampo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Modulo and CpassTSettore
 */
@Mapper()
public interface GestioneCampoMapper extends BaseMapperInterface<GestioneCampo, CpassTGestioneCampo> {

	@Override
	@Mapping(source = "cpassTEnte", target = "ente")
	GestioneCampo toModel(CpassTGestioneCampo entity);

	@Override
	@IterableMapping(elementTargetType = GestioneCampo.class)
	List<GestioneCampo> toModels(Collection<CpassTGestioneCampo> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTGestioneCampo toEntity(GestioneCampo model);

	@Override
	@IterableMapping(elementTargetType = CpassTGestioneCampo.class)
	List<CpassTGestioneCampo> toEntities(Collection<GestioneCampo> models);

}
