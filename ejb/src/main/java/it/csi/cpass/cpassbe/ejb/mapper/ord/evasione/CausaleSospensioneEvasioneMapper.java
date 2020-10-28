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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdCausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdTipoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.CausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TipoEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between CausaleSospensioneEvasione and CpassDOrdCausaleSospensioneEvasione
 */
@Mapper
public interface CausaleSospensioneEvasioneMapper extends BaseMapperInterface<CausaleSospensioneEvasione, CpassDOrdCausaleSospensioneEvasione> {

	@Override
	@Mapping(source = "cpassTEnte", target = "ente")
	CausaleSospensioneEvasione toModel(CpassDOrdCausaleSospensioneEvasione entity);

	@Override
	@IterableMapping(elementTargetType = TipoEvasione.class)
	List<CausaleSospensioneEvasione> toModels(Collection<CpassDOrdCausaleSospensioneEvasione> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassDOrdCausaleSospensioneEvasione toEntity(CausaleSospensioneEvasione model);

	@Override
	@IterableMapping(elementTargetType = CpassDOrdTipoEvasione.class)
	List<CpassDOrdCausaleSospensioneEvasione> toEntities(Collection<CausaleSospensioneEvasione> models);

}
