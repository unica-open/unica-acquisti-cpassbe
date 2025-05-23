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

import it.csi.cpass.cpassbe.ejb.entity.CpassTTestiNotifiche;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface TestoNotificaMapper extends BaseMapperInterface<TestoNotifica, CpassTTestiNotifiche> {

	@Override
	@Mapping(source = "itTesto", target = "it")
	@Mapping(source = "enTesto", target = "en")
	TestoNotifica toModel(CpassTTestiNotifiche entity);

	@Override
	@IterableMapping(elementTargetType = TestoNotifica.class)
	List<TestoNotifica> toModels(Collection<CpassTTestiNotifiche> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTTestiNotifiche toEntity(TestoNotifica model);

	@Override
	@IterableMapping(elementTargetType = CpassTTestiNotifiche.class)
	List<CpassTTestiNotifiche> toEntities(Collection<TestoNotifica> models);

}
