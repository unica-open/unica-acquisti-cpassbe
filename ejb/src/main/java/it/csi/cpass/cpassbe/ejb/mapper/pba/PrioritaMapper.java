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

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaPriorita;
import it.csi.cpass.cpassbe.lib.dto.pba.Priorita;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Priorita and CpassDPbaPriorita
 */
@Mapper
public interface PrioritaMapper extends BaseMapperInterface<Priorita, CpassDPbaPriorita> {

	@Override
	@Mapping(source = "prioritaCodice", target = "codice")
	@Mapping(source = "prioritaDescrizione", target = "descrizione")
	Priorita toModel(CpassDPbaPriorita entity);

	@Override
	@IterableMapping(elementTargetType = Priorita.class)
	List<Priorita> toModels(Collection<CpassDPbaPriorita> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaPriorita toEntity(Priorita model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaPriorita.class)
	List<CpassDPbaPriorita> toEntities(Collection<Priorita> models);
}
