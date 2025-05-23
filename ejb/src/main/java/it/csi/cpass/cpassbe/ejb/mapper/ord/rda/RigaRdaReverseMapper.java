/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.mapper.ord.rda;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.mapper.EnteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UnitaMisuraMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StatoMapper.class, OggettiSpesaMapper.class, UnitaMisuraMapper.class, EnteMapper.class})
public interface RigaRdaReverseMapper extends BaseMapperInterface <RigaRda, CpassTOrdRigaRda>{

	@Override
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassDOggettiSpesa", target = "oggettiSpesa")
	@Mapping(source = "cpassDUnitaMisura", target = "unitaMisura")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTOrdTestataRda", target = "testataRda")

	//@Mapping(source = "cpassTRmsRigaRms", target = "rigaRms")

	RigaRda toModel(CpassTOrdRigaRda entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdRigaRda toEntity(RigaRda model);

	@Override
	@IterableMapping(elementTargetType = RigaRda.class)
	List<RigaRda> toModels(Collection<CpassTOrdRigaRda> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTRmsRigaRms.class)
	List<CpassTOrdRigaRda> toEntities(Collection<RigaRda> models);
	//	CpassTOrdRigaRda cloneToEntity(CpassTOrdRigaRda entity);

	//	RigaRda cloneToModel(RigaRda model);

}
