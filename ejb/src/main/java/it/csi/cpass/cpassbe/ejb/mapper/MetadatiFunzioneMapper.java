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

import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Stato and CpassDPbaNuts
 */
@Mapper
public interface MetadatiFunzioneMapper extends BaseMapperInterface<MetadatiFunzione, CpassTMetadatiFunzione> {

	@Override

	MetadatiFunzione toModel(CpassTMetadatiFunzione entity);

	@Override
	@IterableMapping(elementTargetType = MetadatiFunzione.class)
	List<MetadatiFunzione> toModels(Collection<CpassTMetadatiFunzione> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTMetadatiFunzione toEntity(MetadatiFunzione model);

	@Override
	@IterableMapping(elementTargetType = CpassTMetadatiFunzione.class)
	List<CpassTMetadatiFunzione> toEntities(Collection<MetadatiFunzione> models);

}
