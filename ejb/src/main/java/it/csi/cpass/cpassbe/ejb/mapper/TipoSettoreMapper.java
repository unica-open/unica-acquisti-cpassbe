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

import it.csi.cpass.cpassbe.ejb.entity.CpassDTipoSettore;
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between tipoSettore and CpassDTipoSettore
 */
@Mapper
public interface TipoSettoreMapper extends BaseMapperInterface<TipoSettore, CpassDTipoSettore> {

	@Override
	@Mapping(source = "tipoSettoreCodice", target = "codice")
	@Mapping(source = "tipoSettoreDescrizione", target = "descrizione")
	@Mapping(source = "cpassTEnte", target = "ente")
	TipoSettore toModel(CpassDTipoSettore entity);


	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDTipoSettore toEntity(TipoSettore model);


	@Override
	@IterableMapping(elementTargetType = TipoSettore.class)
	public abstract List<TipoSettore> toModels(Collection<CpassDTipoSettore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassDTipoSettore.class)
	public abstract List<CpassDTipoSettore> toEntities(Collection<TipoSettore> models);


}
