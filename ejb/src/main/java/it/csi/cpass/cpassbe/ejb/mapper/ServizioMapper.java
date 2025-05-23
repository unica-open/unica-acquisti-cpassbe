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

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTServizio;
import it.csi.cpass.cpassbe.lib.dto.Servizio;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Servizio and CpassTServizio
 */
@Mapper
public interface ServizioMapper extends BaseMapperInterface<Servizio, CpassTServizio> {

	@Override
	@Mapping(source = "servizioCodice", target = "codice")
	@Mapping(source = "servizioDescrizione", target = "descrizione")
	Servizio toModel(CpassTServizio entity);

	@Override
	@IterableMapping(elementTargetType = Servizio.class)
	List<Servizio> toModels(Collection<CpassTServizio> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTServizio.class)
	List<CpassTServizio> toEntities(Collection<Servizio> models);

}
