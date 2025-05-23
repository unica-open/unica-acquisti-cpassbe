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

import it.csi.cpass.cpassbe.ejb.entity.CpassTFruitore;
import it.csi.cpass.cpassbe.lib.dto.Fruitore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Fruitore and CpassTFruitore
 */
@Mapper
public interface FruitoreMapper extends BaseMapperInterface<Fruitore, CpassTFruitore> {

	@Override
	@Mapping(source = "fruitoreCodice", target = "codice")
	@Mapping(source = "fruitoreDescrizione", target = "descrizione")
	@Mapping(source = "fruitoreEnteCodiceFiscale", target = "enteCodiceFiscale")
	Fruitore toModel(CpassTFruitore entity);

	@Override
	@IterableMapping(elementTargetType = Fruitore.class)
	List<Fruitore> toModels(Collection<CpassTFruitore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTFruitore.class)
	List<CpassTFruitore> toEntities(Collection<Fruitore> models);

}
