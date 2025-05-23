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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdRdaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.OrdRdaOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between OrdRdaOrdine and CpassROrdRdaOrdine
 */
@Mapper
public interface RdaOrdineMapper extends BaseMapperInterface<OrdRdaOrdine, CpassROrdRdaOrdine> {

	@Override
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "cpassTOrdTestataRda", target = "testatarda")
	OrdRdaOrdine toModel(CpassROrdRdaOrdine entity);

	@Override
	@IterableMapping(elementTargetType = OrdRdaOrdine.class)
	List<OrdRdaOrdine> toModels(Collection<CpassROrdRdaOrdine> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassROrdRdaOrdine toEntity(OrdRdaOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassROrdRdaOrdine.class)
	List<CpassROrdRdaOrdine> toEntities(Collection<OrdRdaOrdine> models);

}
