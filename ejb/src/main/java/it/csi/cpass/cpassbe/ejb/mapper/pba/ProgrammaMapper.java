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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.mapper.EnteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Programma and CpassTPbaProgramma
 */
@Mapper(uses = {EnteMapper.class, StatoMapper.class, UtenteMapper.class, StringMapper.class})
public interface ProgrammaMapper extends BaseMapperInterface<Programma, CpassTPbaProgramma> {

	@Override
	@Mapping(source = "programmaAnno", target = "anno")
	@Mapping(source = "programmaVersione", target = "versione")
	@Mapping(source = "programmaDescrizione", target = "descrizione")
	@Mapping(source = "numeroProvvedimento", target = "numeroProvvedimento")
	@Mapping(source = "descrizioneProvvedimento", target = "descrizioneProvvedimento")
	@Mapping(source = "dataProvvedimento", target = "dataProvvedimento")
	@Mapping(source = "dataPubblicazione", target = "dataPubblicazione")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "programmaCodiceMit", target = "codiceMit")
	@Mapping(source = "idRicevutoMit", target = "idRicevutoMit")
	@Mapping(source = "dataApprovazione", target = "dataApprovazione")
	@Mapping(source = "dataTrasmissioneMit", target = "dataTrasmissioneMit")
	@Mapping(source = "annoFineProgramma", target = "annoFineProgramma")

	@Mapping(source = "cpassTUtenteReferente", target = "utenteReferente")
	Programma toModel(CpassTPbaProgramma entity);

	@Override
	@IterableMapping(elementTargetType = Programma.class)
	List<Programma> toModels(Collection<CpassTPbaProgramma> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassTPbaProgramma toEntity(Programma model);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaProgramma.class)
	List<CpassTPbaProgramma> toEntities(Collection<Programma> models);
}
