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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoCig;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCig;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between InterventoImporti and CpassTPbaIntervento
 */
@Mapper
public interface InterventoCigMapper extends BaseMapperInterface<InterventoCig, CpassTPbaInterventoCig> {

	@Override
	@Mapping(source = "cpassTPbaIntervento", target = "intervento")
	InterventoCig toModel(CpassTPbaInterventoCig entity);

	@Override
	@IterableMapping(elementTargetType = InterventoCig.class)
	List<InterventoCig> toModels(Collection<CpassTPbaInterventoCig> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaInterventoCig.class)
	List<CpassTPbaInterventoCig> toEntities(Collection<InterventoCig> models);

}
