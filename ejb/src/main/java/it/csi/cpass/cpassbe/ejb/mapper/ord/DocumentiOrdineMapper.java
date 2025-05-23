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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDocumentiOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class })
public interface DocumentiOrdineMapper extends BaseMapperInterface<DocumentiOrdine, CpassTOrdDocumentiOrdine> {

	@Override
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "file", target = "fileDocumentoByte")
	DocumentiOrdine toModel(CpassTOrdDocumentiOrdine entity);

	@Override
	@IterableMapping(elementTargetType = DocumentiOrdine.class)
	List<DocumentiOrdine> toModels(Collection<CpassTOrdDocumentiOrdine> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdDocumentiOrdine toEntity(DocumentiOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDocumentiOrdine.class)
	List<CpassTOrdDocumentiOrdine> toEntities(Collection<DocumentiOrdine> models);

}
