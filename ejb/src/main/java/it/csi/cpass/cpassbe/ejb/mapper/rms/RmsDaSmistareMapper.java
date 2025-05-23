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
package it.csi.cpass.cpassbe.ejb.mapper.rms;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRmsDaSmistare;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsDaSmistare;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface RmsDaSmistareMapper extends BaseMapperInterface<RmsDaSmistare, CpassVRmsDaSmistare> {
	@Override
	RmsDaSmistare toModel(CpassVRmsDaSmistare entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassVRmsDaSmistare toEntity(RmsDaSmistare model);

	@Override
	@IterableMapping(elementTargetType = RmsDaSmistare.class)
	List<RmsDaSmistare> toModels(Collection<CpassVRmsDaSmistare> entities);


	@Override
	@IterableMapping(elementTargetType = CpassVRmsDaSmistare.class)
	List<CpassVRmsDaSmistare> toEntities(Collection<RmsDaSmistare> models);
}
