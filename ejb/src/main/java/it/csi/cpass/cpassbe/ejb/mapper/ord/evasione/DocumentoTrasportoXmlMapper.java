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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoXml;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoXML;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DocumentoTrasporto and CpassTOrdDocumentoTrasporto
 */
@Mapper(uses = { StatoMapper.class })
public interface DocumentoTrasportoXmlMapper extends BaseMapperInterface<DocumentoTrasportoXML, CpassTOrdDocumentoTrasportoXml> {

	@Override
	@Mapping(source = "cpassTOrdDocumentoTrasporto", target = "documentoTrasporto")
	DocumentoTrasportoXML toModel(CpassTOrdDocumentoTrasportoXml entity);

	@Override
	@IterableMapping(elementTargetType = DocumentoTrasportoXML.class)
	List<DocumentoTrasportoXML> toModels(Collection<CpassTOrdDocumentoTrasportoXml> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDocumentoTrasportoXml toEntity(DocumentoTrasportoXML model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDocumentoTrasportoXml.class)
	List<CpassTOrdDocumentoTrasportoXml> toEntities(Collection<DocumentoTrasportoXML> models);

}
