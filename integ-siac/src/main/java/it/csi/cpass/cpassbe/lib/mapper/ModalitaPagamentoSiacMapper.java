/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.lib.dto.ModalitaPagamento;
import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between ModalitaPagamento and "ModalitaPagamento SIAC"
 */
@Mapper(uses = {
	StringMapper.class
})
public interface ModalitaPagamentoSiacMapper extends BaseMapperInterface<ModalitaPagamento, it.csi.siac.integ.data._1.ModalitaPagamento> {

	@Override
	@Mapping(source = "codice", target = "codice", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.codice", target = "stato", qualifiedBy = TrimmedString.class)
	ModalitaPagamento toModel(it.csi.siac.integ.data._1.ModalitaPagamento entity);

	@Override
	@IterableMapping(elementTargetType = ModalitaPagamento.class)
	List<ModalitaPagamento> toModels(Collection<it.csi.siac.integ.data._1.ModalitaPagamento> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	it.csi.siac.integ.data._1.ModalitaPagamento toEntity(ModalitaPagamento model);

	@Override
	@IterableMapping(elementTargetType = it.csi.siac.integ.data._1.ModalitaPagamento.class)
	List<it.csi.siac.integ.data._1.ModalitaPagamento> toEntities(Collection<ModalitaPagamento> models);
}
