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
package it.csi.cpass.cpassbe.ejb.mapper.mag;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TestataOrdine and TestataRmMapper
 */
@Mapper
public interface MagazzinoMapper extends BaseMapperInterface<Magazzino, CpassTMagMagazzino> {

	@Override
	@Mapping(source = "magazzinoCodice", target = "codice")
	@Mapping(source = "magazzinoDescrizione", target = "descrizione")
	@Mapping(source = "cpassTEnte", target = "ente")

	Magazzino toModel(CpassTMagMagazzino entity);

	@Override
	@IterableMapping(elementTargetType = Magazzino.class)
	List<Magazzino> toModels(Collection<CpassTMagMagazzino> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTMagMagazzino.class)
	List<CpassTMagMagazzino> toEntities(Collection<Magazzino> models);

}
