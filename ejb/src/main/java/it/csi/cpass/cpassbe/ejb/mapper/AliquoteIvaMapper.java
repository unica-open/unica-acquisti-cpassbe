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

import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AliquoteIva and CpassDAliquoteIva
 */
@Mapper
public interface AliquoteIvaMapper extends BaseMapperInterface<AliquoteIva, CpassDAliquoteIva> {

	@Override
	@Mapping(source = "aliquoteIvaCodice", target = "codice")
	@Mapping(source = "aliquoteIvaDescrizione", target = "descrizione")
	@Mapping(source = "codificaPeppol", target = "codificaPeppol")
	@Mapping(source = "percentuale", target = "percentuale")
	AliquoteIva toModel(CpassDAliquoteIva entity);

	@Override
	@IterableMapping(elementTargetType = AliquoteIva.class)
	List<AliquoteIva> toModels(Collection<CpassDAliquoteIva> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassDAliquoteIva toEntity(AliquoteIva model);

	@Override
	@IterableMapping(elementTargetType = CpassDAliquoteIva.class)
	List<CpassDAliquoteIva> toEntities(Collection<AliquoteIva> models);

}
