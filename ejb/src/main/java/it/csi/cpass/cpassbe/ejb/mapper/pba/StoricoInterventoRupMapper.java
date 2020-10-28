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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.pba.StoricoInterventoRup;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.mapper.annotation.Minimal;

/**
 * Mapper between InterventoStoricoRup, CpassRPbaInterventoRup
 */
@Mapper(uses = {UtenteMapper.class})
public interface StoricoInterventoRupMapper extends BaseMapperInterface<StoricoInterventoRup, CpassRPbaStoricoInterventoRup> {

	@Override
	@Mapping(source = "cpassTPbaIntervento", target = "intervento")
	@Mapping(source = "cpassTUtenteRup", target = "utenteRup")
	@Mapping(source = "cpassTUtente", target = "utente")
	@Mapping(source = "dataStoricizzazione", target = "dataStoricizzazione")
	StoricoInterventoRup toModel(CpassRPbaStoricoInterventoRup entity);

	/**
	 * Minimal mapping
	 * <br/>
	 * TODO: minimalize
	 * @param entity the entity to map
	 * @return the mapped object
	 */
	@Minimal
	@Mapping(source = "cpassTPbaIntervento", target = "intervento")
	@Mapping(source = "cpassTUtenteRup", target = "utenteRup")
	@Mapping(source = "cpassTUtente", target = "utente")
	@Mapping(source = "dataStoricizzazione", target = "dataStoricizzazione")
	StoricoInterventoRup toModelMinimal(CpassRPbaStoricoInterventoRup entity);
	
	@Override
	@IterableMapping(elementTargetType = StoricoInterventoRup.class)
	List<StoricoInterventoRup> toModels(Collection<CpassRPbaStoricoInterventoRup> entities);

	/**
	 * Converts to a list of models with the minimal mapping
	 * @param entities the entities to convert
	 * @return the converted models
	 */
	@Minimal
	@IterableMapping(elementTargetType = StoricoInterventoRup.class, qualifiedBy = Minimal.class)
	List<StoricoInterventoRup> toModelsMinimal(Collection<CpassRPbaStoricoInterventoRup> entities);

	@Override
	@IterableMapping(elementTargetType = CpassRPbaStoricoInterventoRup.class)
	List<CpassRPbaStoricoInterventoRup> toEntities(Collection<StoricoInterventoRup> models);

}
