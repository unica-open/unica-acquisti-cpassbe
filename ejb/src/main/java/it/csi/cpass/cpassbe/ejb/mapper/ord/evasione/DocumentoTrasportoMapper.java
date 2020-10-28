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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdTipoEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TipoEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DocumentoTrasporto and CpassTOrdDocumentoTrasporto
 */
@Mapper
public interface DocumentoTrasportoMapper extends BaseMapperInterface<DocumentoTrasporto, CpassTOrdDocumentoTrasporto> {

	@Override
	// @Mapping(source = "dataCancellazione", target = "dataCancellazione")
	DocumentoTrasporto toModel(CpassTOrdDocumentoTrasporto entity);

	@Override
	@IterableMapping(elementTargetType = TipoEvasione.class)
	List<DocumentoTrasporto> toModels(Collection<CpassTOrdDocumentoTrasporto> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTOrdDocumentoTrasporto toEntity(DocumentoTrasporto model);

	@Override
	@IterableMapping(elementTargetType = CpassDOrdTipoEvasione.class)
	List<CpassTOrdDocumentoTrasporto> toEntities(Collection<DocumentoTrasporto> models);

}
