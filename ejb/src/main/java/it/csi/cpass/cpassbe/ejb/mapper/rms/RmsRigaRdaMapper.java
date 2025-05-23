/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.mapper.rms;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsRigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsRigaRda;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface RmsRigaRdaMapper extends BaseMapperInterface<RmsRigaRda, CpassRRmsRigaRda> {
	@Override
	@Mapping(source = "cpassTRmsRigaRms", target = "rigaRms")
	RmsRigaRda toModel(CpassRRmsRigaRda entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassRRmsRigaRda toEntity(RmsRigaRda model);

	@Override
	@IterableMapping(elementTargetType = RmsRigaRda.class)
	List<RmsRigaRda> toModels(Collection<CpassRRmsRigaRda> entities);

	@Override
	@IterableMapping(elementTargetType = CpassRRmsRigaRda.class)
	List<CpassRRmsRigaRda> toEntities(Collection<RmsRigaRda> models);

}
