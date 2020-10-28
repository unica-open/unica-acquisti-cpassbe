/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDStatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatoElOrdine and CpassDStatoElOrdine
 */
@Mapper
public interface StatoElOrdineMapper extends BaseMapperInterface<StatoElOrdine, CpassDStatoElOrdine> {

	@Override
	@Mapping(source = "statoCodice", target = "codice")
	@Mapping(source = "statoDescrizione", target = "descrizione")
	@Mapping(source = "statoTipo", target = "tipo")
	StatoElOrdine toModel(CpassDStatoElOrdine entity);

	@Override
	@IterableMapping(elementTargetType = StatoElOrdine.class)
	List<StatoElOrdine> toModels(Collection<CpassDStatoElOrdine> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDStatoElOrdine toEntity(StatoElOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassDStatoElOrdine.class)
	List<CpassDStatoElOrdine> toEntities(Collection<StatoElOrdine> models);

}
