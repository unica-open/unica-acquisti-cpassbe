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

import it.csi.cpass.cpassbe.ejb.entity.CpassTProvvedimento;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Provvedimento, CpassTProvvedimento
 */

@Mapper(uses = { ProvvedimentoTipoMapper.class, SettoreCustomMapper.class, EnteMapper.class })
public interface ProvvedimentoMapper extends BaseMapperInterface<Provvedimento, CpassTProvvedimento> {

	@Override
	@Mapping(source = "provvedimentoAnno", target = "anno")
	@Mapping(source = "provvedimentoNote", target = "note")
	@Mapping(source = "provvedimentoNumero", target = "numero")
	@Mapping(source = "provvedimentoOggetto", target = "oggetto")
	@Mapping(source = "provvedimentoDescrizione", target = "descrizione")
	@Mapping(source = "cpassDProvvedimentoTipo", target = "provvedimentoTipo")
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTCdc", target = "cdc")
	Provvedimento toModel(CpassTProvvedimento entity);

	@Override
	@IterableMapping(elementTargetType = Provvedimento.class)
	List<Provvedimento> toModels(Collection<CpassTProvvedimento> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTProvvedimento.class)
	List<CpassTProvvedimento> toEntities(Collection<Provvedimento> models);

}
