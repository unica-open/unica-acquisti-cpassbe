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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdUtenteSezione;
import it.csi.cpass.cpassbe.lib.dto.ord.OrdUtenteSezione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between OrdUtenteSezione, CpassROrdUtenteSezione
 */
@Mapper
public interface UtenteSezioneMapper extends BaseMapperInterface<OrdUtenteSezione, CpassROrdUtenteSezione> {

	@Override
	@Mapping(source = "cpassTOrdSezione", target = "sezione")
	@Mapping(source = "cpassTUtente", target = "utente")
	@Mapping(source = "cpassTSettore", target = "settore")
	OrdUtenteSezione toModel(CpassROrdUtenteSezione entity);


	@Override
	@IterableMapping(elementTargetType = OrdUtenteSezione.class)
	List<OrdUtenteSezione> toModels(Collection<CpassROrdUtenteSezione> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassROrdUtenteSezione toEntity(OrdUtenteSezione model);

	@Override
	@IterableMapping(elementTargetType = CpassROrdUtenteSezione.class)
	List<CpassROrdUtenteSezione> toEntities(Collection<OrdUtenteSezione> models);

}
