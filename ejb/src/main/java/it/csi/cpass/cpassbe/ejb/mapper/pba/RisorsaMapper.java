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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRisorsa;
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Risorsa and CpassDRisorse
 */
@Mapper
public interface RisorsaMapper extends BaseMapperInterface<Risorsa, CpassDPbaRisorsa> {

	@Override
	@Mapping(source = "risorsaCodice", target = "codice")
	@Mapping(source = "risorsaDescrizione", target = "descrizione")
	@Mapping(source = "risorsaTipo", target = "tipo")
	@Mapping(source = "risorsaTagTrasmissione", target = "tagTrasmissione")
	@Mapping(source = "risorsaOrdinamento", target = "ordinamento")
	Risorsa toModel(CpassDPbaRisorsa entity);

	@Override
	@IterableMapping(elementTargetType = Risorsa.class)
	List<Risorsa> toModels(Collection<CpassDPbaRisorsa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventoImportis", ignore = true)
	CpassDPbaRisorsa toEntity(Risorsa model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaRisorsa.class)
	List<CpassDPbaRisorsa> toEntities(Collection<Risorsa> models);
}
