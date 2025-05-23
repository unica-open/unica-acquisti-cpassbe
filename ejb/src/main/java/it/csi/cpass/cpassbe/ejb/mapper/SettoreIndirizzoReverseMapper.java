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

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { SettoreCustomMapper.class })
//public abstract class SettoreIndirizzoMapper implements BaseMapperInterface<SettoreIndirizzo, CpassTSettoreIndirizzo> {
public interface SettoreIndirizzoReverseMapper extends BaseMapperInterface<SettoreIndirizzo, CpassTSettoreIndirizzo> {
	@Override
	@Mapping(source = "settoreIndirizzoCodice", target = "indirizzoCodice")
	@Mapping(source = "cpassTSettore", target = "settore")
	SettoreIndirizzo toModel(CpassTSettoreIndirizzo entity);

	@Override
	@IterableMapping(elementTargetType = SettoreIndirizzo.class)
	public abstract List<SettoreIndirizzo> toModels(Collection<CpassTSettoreIndirizzo> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTSettoreIndirizzo.class)
	public abstract List<CpassTSettoreIndirizzo> toEntities(Collection<SettoreIndirizzo> models);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTSettoreIndirizzo toEntity(SettoreIndirizzo model);


}
