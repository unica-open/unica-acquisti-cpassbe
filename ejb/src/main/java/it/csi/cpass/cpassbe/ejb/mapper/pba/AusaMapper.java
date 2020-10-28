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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaAusa;
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ausa and CpassDPbaAusa
 */
@Mapper
public interface AusaMapper extends BaseMapperInterface<Ausa, CpassDPbaAusa> {

	@Override
	@Mapping(source = "ausaCodice", target = "codice")
	@Mapping(source = "ausaDescrizione", target = "descrizione")
	@Mapping(source = "ausaCodiceFiscale", target = "codiceFiscale")
	Ausa toModel(CpassDPbaAusa entity);

	@Override
	@IterableMapping(elementTargetType = Ausa.class)
	List<Ausa> toModels(Collection<CpassDPbaAusa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaAusa toEntity(Ausa model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaAusa.class)
	List<CpassDPbaAusa> toEntities(Collection<Ausa> models);

}
