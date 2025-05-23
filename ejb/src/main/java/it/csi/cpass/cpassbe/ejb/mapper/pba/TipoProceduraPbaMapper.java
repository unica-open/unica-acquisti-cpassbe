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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaTipoProcedura;
import it.csi.cpass.cpassbe.lib.dto.pba.TipoProceduraPba;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TipoOrdine and CpassDOrdTipoOrdine
 */
@Mapper
public interface TipoProceduraPbaMapper extends BaseMapperInterface<TipoProceduraPba, CpassDPbaTipoProcedura> {

	@Override
	@Mapping(source = "tipoProceduraCodice", target = "codice")
	@Mapping(source = "tipoProceduraDescrizione", target = "descrizione")
	@Mapping(source = "cpassTEnte", target = "ente")
	TipoProceduraPba toModel(CpassDPbaTipoProcedura entity);

	@Override
	@IterableMapping(elementTargetType = TipoProceduraPba.class)
	List<TipoProceduraPba> toModels(Collection<CpassDPbaTipoProcedura> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDPbaTipoProcedura toEntity(TipoProceduraPba model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaTipoProcedura.class)
	List<CpassDPbaTipoProcedura> toEntities(Collection<TipoProceduraPba> models);

}
