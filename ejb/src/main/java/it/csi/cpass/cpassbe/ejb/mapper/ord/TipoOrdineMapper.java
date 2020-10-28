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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TipoOrdine and CpassDOrdTipoOrdine
 */
@Mapper
public interface TipoOrdineMapper extends BaseMapperInterface<TipoOrdine, CpassDOrdTipoOrdine> {

	@Override
	@Mapping(source = "tipologiaDocumentoCodice", target = "tipologiaDocumentoCodice")
	@Mapping(source = "tipologiaDocumentoDescrizione", target = "tipologiaDocumentoDescrizione")
	TipoOrdine toModel(CpassDOrdTipoOrdine entity);

	@Override
	@IterableMapping(elementTargetType = TipoOrdine.class)
	List<TipoOrdine> toModels(Collection<CpassDOrdTipoOrdine> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDOrdTipoOrdine toEntity(TipoOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassDOrdTipoOrdine.class)
	List<CpassDOrdTipoOrdine> toEntities(Collection<TipoOrdine> models);

}
