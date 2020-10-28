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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaModAffidamento;
import it.csi.cpass.cpassbe.lib.dto.pba.ModalitaAffidamento;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ModalitaAffidamento and CpassDPbaModAffidamento
 */
@Mapper
public interface ModalitaAffidamentoMapper extends BaseMapperInterface<ModalitaAffidamento, CpassDPbaModAffidamento> {

	@Override
	@Mapping(source = "modAffidamentoCodice", target = "codice")
	@Mapping(source = "modAffidamentoDescrizione", target = "descrizione")
	ModalitaAffidamento toModel(CpassDPbaModAffidamento entity);

	@Override
	@IterableMapping(elementTargetType = ModalitaAffidamento.class)
	List<ModalitaAffidamento> toModels(Collection<CpassDPbaModAffidamento> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaModAffidamento toEntity(ModalitaAffidamento model);
	@Override
	@IterableMapping(elementTargetType = CpassDPbaModAffidamento.class)
	List<CpassDPbaModAffidamento> toEntities(Collection<ModalitaAffidamento> models);
}
