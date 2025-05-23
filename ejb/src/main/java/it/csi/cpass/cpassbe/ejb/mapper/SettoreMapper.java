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

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between settore and CpassTSettore
 */
@Mapper(uses = {TipoSettoreMapper.class})
public interface SettoreMapper extends BaseMapperInterface<Settore, CpassTSettore> {


	@Override
	@Mapping(source = "settoreCodice", target = "codice")
	@Mapping(source = "settoreDescrizione", target = "descrizione")
	@Mapping(source = "cpassTSettores", target = "listSettore")
	@Mapping(source = "cpassDTipoSettore", target = "tipoSettore")
	@Mapping(source = "cpassTSettorePadre", target = "settorePadre")
	@Mapping(source = "cpassRSettoreAooActas", target = "aooActas")
	@Mapping(source = "cpassTSettoreIndirizzos", target = "settoreIndirizzos")
	@Mapping(source = "cpassTEnte", target = "ente")
	Settore toModel(CpassTSettore entity);

	@Override
	@IterableMapping(elementTargetType = Settore.class)
	List<Settore> toModels(Collection<CpassTSettore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTSettore.class)
	List<CpassTSettore> toEntities(Collection<Settore> models);
}
