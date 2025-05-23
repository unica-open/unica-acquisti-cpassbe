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

import it.csi.cpass.cpassbe.ejb.entity.CpassTComunicazione;
import it.csi.cpass.cpassbe.lib.dto.Comunicazione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Comunicazione and CpassTComunicazione
 */
@Mapper
public interface ComunicazioneMapper extends BaseMapperInterface<Comunicazione, CpassTComunicazione> {

	@Override
	@Mapping(source = "comunicazioneTesto", target = "testo")
	@Mapping(source = "comunicazioneDataInizio", target = "dataInizio")
	@Mapping(source = "comunicazioneDataFine", target = "dataFine")
	@Mapping(source = "comunicazioneTipo", target = "tipo")
	Comunicazione toModel(CpassTComunicazione entity);

	@Override
	@IterableMapping(elementTargetType = Comunicazione.class)
	List<Comunicazione> toModels(Collection<CpassTComunicazione> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTComunicazione.class)
	List<CpassTComunicazione> toEntities(Collection<Comunicazione> models);

}
