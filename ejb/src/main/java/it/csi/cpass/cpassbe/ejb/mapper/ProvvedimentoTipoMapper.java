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

import it.csi.cpass.cpassbe.ejb.entity.CpassDProvvedimentoTipo;
import it.csi.cpass.cpassbe.lib.dto.ProvvedimentoTipo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Parametro and CpassTParametro
 */
@Mapper
public interface ProvvedimentoTipoMapper extends BaseMapperInterface<ProvvedimentoTipo, CpassDProvvedimentoTipo> {

	@Override
	@Mapping(source = "provvedimentoTipoCodice", target = "codice")
	@Mapping(source = "provvedimentoTipoDescrizione", target = "descrizione")
	@Mapping(source = "cpassTEnte", target = "ente")
	ProvvedimentoTipo toModel(CpassDProvvedimentoTipo entity);

	@Override
	@IterableMapping(elementTargetType = ProvvedimentoTipo.class)
	List<ProvvedimentoTipo> toModels(Collection<CpassDProvvedimentoTipo> entities);

	@Override
	@IterableMapping(elementTargetType = CpassDProvvedimentoTipo.class)
	List<CpassDProvvedimentoTipo> toEntities(Collection<ProvvedimentoTipo> models);

}
