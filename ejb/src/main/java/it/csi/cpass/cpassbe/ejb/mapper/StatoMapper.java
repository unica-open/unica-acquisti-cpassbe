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

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Stato and CpassDPbaNuts
 */
@Mapper
public interface StatoMapper extends BaseMapperInterface<Stato, CpassDStato> {

	@Override
	@Mapping(source = "statoCodice", target = "codice")
	@Mapping(source = "statoDescrizione", target = "descrizione")
	@Mapping(source = "statoTipo", target = "tipo")
	Stato toModel(CpassDStato entity);

	@Override
	@IterableMapping(elementTargetType = Stato.class)
	List<Stato> toModels(Collection<CpassDStato> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDStato toEntity(Stato model);

	@Override
	@IterableMapping(elementTargetType = CpassDStato.class)
	List<CpassDStato> toEntities(Collection<Stato> models);

}
