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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;


/**
 * Mapper between Elaborazione and CpassTElaborazione
 */
@Mapper(uses = { StringMapper.class, ElaborazioneMessaggioLimitedMapper.class, ElaborazioneTipoMapper.class, ElaborazioneParametroMapper.class })
public interface ElaborazioneMapper extends BaseMapperInterface<Elaborazione, CpassTElaborazione> {

	@Override
	@Mapping(source = "entitaId", target = "entitaId")
	//@Mapping(source = "elaborazioneTipo", target = "tipo")
	@Mapping(source = "elaborazioneUtente", target = "utente")
	@Mapping(source = "elaborazioneStato", target = "stato")
	@Mapping(source = "elaborazioneData", target = "data")
	@Mapping(source = "elaborazioneEsito", target = "esito")
	@Mapping(source = "cpassTElaborazioneMessaggios", target = "listaMessaggi")
	@Mapping(source = "cpassTElaborazioneParametros", target = "listaParametri")
	@Mapping(source = "cpassDElaborazioneTipo", target = "elaborazioneTipo")
	@Mapping(source = "elaborazione_id_esterno", target = "idEsterno")
	
	Elaborazione toModel(CpassTElaborazione entity);

	@Override
	@IterableMapping(elementTargetType = Elaborazione.class)
	List<Elaborazione> toModels(Collection<CpassTElaborazione> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	//@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassTElaborazione toEntity(Elaborazione model);

	@Override
	@IterableMapping(elementTargetType = CpassTElaborazione.class)
	List<CpassTElaborazione> toEntities(Collection<Elaborazione> models);
}
