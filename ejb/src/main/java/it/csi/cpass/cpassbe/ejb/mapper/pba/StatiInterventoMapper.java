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

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStatiIntervento;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.pba.StatiIntervento;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between InterventoStoricoRup, CpassRPbaInterventoRup
 */
@Mapper(uses = {UtenteMapper.class})
public interface StatiInterventoMapper extends BaseMapperInterface<StatiIntervento, CpassRPbaStatiIntervento> {


	@Override
	@Mapping(source = "cpassTPbaIntervento", target = "intervento")
	@Mapping(source = "cpassTUtente", target = "utente")
	StatiIntervento toModel(CpassRPbaStatiIntervento entity);

	@Override
	@IterableMapping(elementTargetType = StatiIntervento.class)
	List<StatiIntervento> toModels(Collection<CpassRPbaStatiIntervento> entities);


	@Override
	@IterableMapping(elementTargetType = CpassRPbaStatiIntervento.class)
	List<CpassRPbaStatiIntervento> toEntities(Collection<StatiIntervento> models);

}
