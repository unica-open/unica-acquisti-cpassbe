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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;
import it.csi.cpass.cpassbe.ejb.entity.CpassDRuolo;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ruolo and CpassDRuolo
 */
@Mapper
public interface RuoloMapper extends BaseMapperInterface<Ruolo, CpassDRuolo> {

	@Override
	@Mapping(source = "ruoloCodice", target = "codice")
	@Mapping(source = "ruoloDescrizione", target = "descrizione")
	Ruolo toModel(CpassDRuolo entity);

	@Override
	@IterableMapping(elementTargetType = Ruolo.class)
	List<Ruolo> toModels(Collection<CpassDRuolo> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDRuolo toEntity(Ruolo model);

	@Override
	@IterableMapping(elementTargetType = CpassDModulo.class)
	List<CpassDRuolo> toEntities(Collection<Ruolo> models);
}
