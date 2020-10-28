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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaNuts;
import it.csi.cpass.cpassbe.lib.dto.pba.Nuts;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Nuts and CpassDPbaNuts
 */
@Mapper
public interface NutsMapper extends BaseMapperInterface<Nuts, CpassDPbaNuts> {

	@Override
	@Mapping(source = "nutsCodice", target = "codice")
	@Mapping(source = "nutsDescrizione", target = "descrizione")
	Nuts toModel(CpassDPbaNuts entity);

	@Override
	@IterableMapping(elementTargetType = Nuts.class)
	List<Nuts> toModels(Collection<CpassDPbaNuts> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaNuts toEntity(Nuts model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaNuts.class)
	List<CpassDPbaNuts> toEntities(Collection<Nuts> models);
}
