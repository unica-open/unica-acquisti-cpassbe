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
package it.csi.cpass.cpassbe.ejb.mapper.rms;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface RegoleSmistamentoRmsMapper extends BaseMapperInterface<RegoleSmistamentoRms, CpassTRegoleSmistamentoRms> {
	@Override
	@Mapping(source = "cpassTMagMagazzino", target = "magazzino")
	@Mapping(source = "cpassTOrdSezione", target = "sezione")
	@Mapping(source = "cpassTSettoreAcquisto", target = "settoreAcquisto")
	@Mapping(source = "cpassTEnte", target = "ente")
	RegoleSmistamentoRms toModel(CpassTRegoleSmistamentoRms entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTRegoleSmistamentoRms toEntity(RegoleSmistamentoRms model);

	@Override
	@IterableMapping(elementTargetType = RegoleSmistamentoRms.class)
	List<RegoleSmistamentoRms> toModels(Collection<CpassTRegoleSmistamentoRms> entities);


	@Override
	@IterableMapping(elementTargetType = CpassTRegoleSmistamentoRms.class)
	List<CpassTRegoleSmistamentoRms> toEntities(Collection<RegoleSmistamentoRms> models);
}
