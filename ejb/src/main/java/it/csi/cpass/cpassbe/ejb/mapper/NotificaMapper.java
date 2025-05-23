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

import it.csi.cpass.cpassbe.ejb.entity.CpassTNotifica;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper( uses = { TestoNotificaMapper.class } )
public interface NotificaMapper extends BaseMapperInterface<Notifica, CpassTNotifica> {

	@Override
	@Mapping(source = "notificaId", target = "id")
	@Mapping(source = "entitaId", target = "entita")
	@Mapping(source = "cpassTTestiNotifiche", target = "testoNotifica")
	Notifica toModel(CpassTNotifica entity);

	@Override
	@IterableMapping(elementTargetType = Notifica.class)
	List<Notifica> toModels(Collection<CpassTNotifica> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTNotifica toEntity(Notifica model);

	@Override
	@IterableMapping(elementTargetType = CpassTNotifica.class)
	List<CpassTNotifica> toEntities(Collection<Notifica> models);

}
