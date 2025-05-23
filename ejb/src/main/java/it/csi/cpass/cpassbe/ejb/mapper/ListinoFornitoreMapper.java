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

import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AliquoteIva and CpassDAliquoteIva
 */
@Mapper
public interface ListinoFornitoreMapper extends BaseMapperInterface<ListinoFornitore, CpassTListinoFornitore> {

	@Override
	@Mapping(source = "listinoFornitoreCodiceOds", target = "codiceOds")
	@Mapping(source = "listinoFornitoreDescrizione", target = "descrizione")
	@Mapping(source = "cpassDOggettiSpesa", target = "oggettiSpesa")
	@Mapping(source = "cpassTFornitore", target = "fornitore")
	ListinoFornitore toModel(CpassTListinoFornitore entity);

	@Override
	@IterableMapping(elementTargetType = ListinoFornitore.class)
	List<ListinoFornitore> toModels(Collection<CpassTListinoFornitore> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTListinoFornitore toEntity(ListinoFornitore model);

	@Override
	@IterableMapping(elementTargetType = CpassTListinoFornitore.class)
	List<CpassTListinoFornitore> toEntities(Collection<ListinoFornitore> models);

}
