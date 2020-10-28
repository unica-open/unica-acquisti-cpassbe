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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoProcedura;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProcedura;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TipoOrdine and CpassDOrdTipoOrdine
 */
@Mapper
public interface TipoProceduraMapper extends BaseMapperInterface<TipoProcedura, CpassDOrdTipoProcedura> {

	@Override
	@Mapping(source = "tipoProceduraCodice", target = "codice")
	@Mapping(source = "tipoProceduraDescrizione", target = "descrizione")
	TipoProcedura toModel(CpassDOrdTipoProcedura entity);

	@Override
	@IterableMapping(elementTargetType = TipoProcedura.class)
	List<TipoProcedura> toModels(Collection<CpassDOrdTipoProcedura> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDOrdTipoProcedura toEntity(TipoProcedura model);

	@Override
	@IterableMapping(elementTargetType = CpassDOrdTipoProcedura.class)
	List<CpassDOrdTipoProcedura> toEntities(Collection<TipoProcedura> models);

}
