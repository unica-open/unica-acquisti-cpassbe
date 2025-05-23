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
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.mapper.EnteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.mag.MagazzinoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.rda.RigaRdaReverseMapper;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = {  OggettiSpesaMapper.class,SettoreCustomMapper.class,EnteMapper.class,StatoMapper.class,MagazzinoMapper.class, TestataRmsReverseMapper.class, RigaRdaReverseMapper.class})
public interface RigaRmsReverseMapper extends BaseMapperInterface<RigaRms, CpassTRmsRigaRms> {

	@Override
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTRmsTestataRms", target = "testataRms")
	@Mapping(source = "cpassDOggettiSpesa", target = "oggettiSpesa")
	@Mapping(source = "cpassTSettoreAcquisto", target = "settoreAcquisto")
	@Mapping(source = "cpassTMagMagazzino", target = "magazzino")
	@Mapping(source = "cpassTOrdSezione", target = "sezione")
	//@Mapping(source = "cpassTOrdRigaRda", target = "rigaRda")
	RigaRms toModel(CpassTRmsRigaRms entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTRmsRigaRms toEntity(RigaRms model);

	@Override
	@IterableMapping(elementTargetType = RigaRms.class)
	List<RigaRms> toModels(Collection<CpassTRmsRigaRms> entities);


	@Override
	@IterableMapping(elementTargetType = CpassTRmsRigaRms.class)
	List<CpassTRmsRigaRms> toEntities(Collection<RigaRms> models);

	/*
	CpassTRmsRigaRm cloneToEntity(CpassTRmsRigaRm entity);
	RigaRm cloneToModel(RigaRm model);
	 */

}
