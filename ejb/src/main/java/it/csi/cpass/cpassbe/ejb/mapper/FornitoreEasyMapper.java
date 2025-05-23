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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between FornitoreEasy and CpassTFornitore
 */
@Mapper
public interface FornitoreEasyMapper extends BaseMapperInterface<Fornitore, CpassTFornitore> {

	@Override
	@Mapping(source = "cap", target = "cap")
	@Mapping(source = "codice", target = "codice")
	@Mapping(source = "codiceFiscale", target = "codiceFiscale")
	@Mapping(source = "codiceFiscaleEstero", target = "codiceFiscaleEstero")
	@Mapping(source = "cognome", target = "cognome")
	@Mapping(source = "comune", target = "comune")
	@Mapping(source = "indirizzo", target = "indirizzo")
	@Mapping(source = "naturaGiuridica", target = "naturaGiuridica")
	@Mapping(source = "nome", target = "nome")
	@Mapping(source = "numeroCivico", target = "numeroCivico")
	@Mapping(source = "partitaIva", target = "partitaIva")
	@Mapping(source = "provincia", target = "provincia")
	@Mapping(source = "ragioneSociale", target = "ragioneSociale")
	@Mapping(source = "stato", target = "stato")
	@Mapping(source = "cpassTEnte", target = "ente")

	Fornitore toModel(CpassTFornitore entity);

	@Override
	@IterableMapping(elementTargetType = Fornitore.class)
	List<Fornitore> toModels(Collection<CpassTFornitore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTFornitore.class)
	List<CpassTFornitore> toEntities(Collection<Fornitore> models);

}
