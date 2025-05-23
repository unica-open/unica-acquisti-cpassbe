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

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.ejb.mapper.FornitoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DocumentoTrasporto and CpassTOrdDocumentoTrasporto
 */
@Mapper(uses = { StatoMapper.class, DocumentoTrasportoRigaMapper.class, FornitoreMapper.class })
public interface DocumentoTrasportoMapper extends BaseMapperInterface<DocumentoTrasporto, CpassTOrdDocumentoTrasporto> {

	@Override
	@Mapping(source = "cpassTFornitore", target = "fornitore")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTOrdTestataEvasione", target = "testataEvasione")
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "cpassTOrdDocumentoTrasportoRigas", target = "documentoTrasportoRigaList")
	@Mapping(source = "cpassTOrdDocumentoTrasportoXmls", target = "documentoTrasportoXMLList")
	DocumentoTrasporto toModel(CpassTOrdDocumentoTrasporto entity);

	@Override
	@IterableMapping(elementTargetType = DocumentoTrasporto.class)
	List<DocumentoTrasporto> toModels(Collection<CpassTOrdDocumentoTrasporto> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDocumentoTrasporto toEntity(DocumentoTrasporto model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDocumentoTrasporto.class)
	List<CpassTOrdDocumentoTrasporto> toEntities(Collection<DocumentoTrasporto> models);

}
