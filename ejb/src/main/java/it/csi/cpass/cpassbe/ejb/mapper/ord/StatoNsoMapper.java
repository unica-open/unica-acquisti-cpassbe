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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdStatoNso;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoNso;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatoNso and CpassDOrdStatoNso
 */
@Mapper
public interface StatoNsoMapper extends BaseMapperInterface<StatoNso, CpassDOrdStatoNso> {

	@Override
	@Mapping(source = "statoNsoCodice", target = "codice")
	@Mapping(source = "statoNsoDescrizione", target = "descrizione")
	@Mapping(source = "statoNsoTipo", target = "tipo")
	StatoNso toModel(CpassDOrdStatoNso entity);

	@Override
	@IterableMapping(elementTargetType = StatoNso.class)
	List<StatoNso> toModels(Collection<CpassDOrdStatoNso> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	//	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDOrdStatoNso toEntity(StatoNso model);

	@Override
	@IterableMapping(elementTargetType = CpassDOrdStatoNso.class)
	List<CpassDOrdStatoNso> toEntities(Collection<StatoNso> models);

}
