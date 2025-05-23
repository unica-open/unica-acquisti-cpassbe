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

import it.csi.cpass.cpassbe.ejb.entity.CpassROdsDatiContabili;
import it.csi.cpass.cpassbe.lib.dto.OdsDatiContabili;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface OdsDatiContabiliMapper extends BaseMapperInterface<OdsDatiContabili, CpassROdsDatiContabili> {

	@Override
	OdsDatiContabili toModel(CpassROdsDatiContabili entity);

	@Override
	@IterableMapping(elementTargetType = OdsDatiContabili.class)
	List<OdsDatiContabili> toModels(Collection<CpassROdsDatiContabili> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassROdsDatiContabili toEntity(OdsDatiContabili model);

	@Override
	@IterableMapping(elementTargetType = CpassROdsDatiContabili.class)
	List<CpassROdsDatiContabili> toEntities(Collection<OdsDatiContabili> models);

}
