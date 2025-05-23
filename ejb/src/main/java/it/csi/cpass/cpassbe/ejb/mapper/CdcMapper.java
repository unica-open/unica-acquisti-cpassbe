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

import it.csi.cpass.cpassbe.ejb.entity.CpassTCdc;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ufficio and CpassTUfficio
 */
@Mapper
public interface CdcMapper extends BaseMapperInterface<Cdc, CpassTCdc> {

	@Override
	@Mapping(source = "cdcCodice", target = "codice")
	@Mapping(source = "cdcDescrizione", target = "descrizione")
	@Mapping(source = "cpassTEnte", target = "ente")
	Cdc toModel(CpassTCdc entity);

	@Override
	@IterableMapping(elementTargetType = Cdc.class)
	List<Cdc> toModels(Collection<CpassTCdc> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTCdc toEntity(Cdc model);

	@Override
	@IterableMapping(elementTargetType = CpassTCdc.class)
	List<CpassTCdc> toEntities(Collection<Cdc> models);

}
