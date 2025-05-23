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

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.ejb.mapper.ord.RigaOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TestataOrdineMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { TestataOrdineMapper.class, RigaOrdineMapper.class, RigaEvasioneMapper.class })
public interface DocumentoTrasportoRigaMapper extends BaseMapperInterface<DocumentoTrasportoRiga, CpassTOrdDocumentoTrasportoRiga> {

	@Override
	@Mapping(source = "cpassTOrdRigaOrdine", target = "rigaOrdine")
	@Mapping(source = "cpassTOrdRigaEvasione", target = "rigaEvasione")
	@Mapping(source = "cpassTOrdDocumentoTrasporto", target = "documentoTrasporto")
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	DocumentoTrasportoRiga toModel(CpassTOrdDocumentoTrasportoRiga entity);

	@Override
	@IterableMapping(elementTargetType = DocumentoTrasportoRiga.class)
	List<DocumentoTrasportoRiga> toModels(Collection<CpassTOrdDocumentoTrasportoRiga> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDocumentoTrasportoRiga toEntity(DocumentoTrasportoRiga model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdDocumentoTrasportoRiga.class)
	List<CpassTOrdDocumentoTrasportoRiga> toEntities(Collection<DocumentoTrasportoRiga> models);
}
