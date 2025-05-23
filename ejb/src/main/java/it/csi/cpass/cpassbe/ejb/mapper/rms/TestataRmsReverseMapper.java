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

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.ejb.mapper.EnteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TestataOrdine and TestataRmMapper
 */
@Mapper(uses = {  StatoMapper.class, EnteMapper.class, SettoreCustomMapper.class, UtenteMapper.class})
public interface TestataRmsReverseMapper extends BaseMapperInterface<TestataRms, CpassTRmsTestataRms> {

	@Override
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTRmsRigaRms", target = "rigaRms")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTSettoreDestinatario", target = "settoreDestinatario")
	@Mapping(source = "cpassTSettoreEmittente", target = "settoreEmittente")
	@Mapping(source = "cpassTUtente", target = "utente")
	@Mapping(source = "cpassTSettoreIndirizzo", target = "settoreIndirizzo")
	TestataRms toModel(CpassTRmsTestataRms entity);

	@Override
	@IterableMapping(elementTargetType = TestataRms.class)
	List<TestataRms> toModels(Collection<CpassTRmsTestataRms> entities);


	@Override
	@IterableMapping(elementTargetType = CpassTRmsTestataRms.class)
	List<CpassTRmsTestataRms> toEntities(Collection<TestataRms> models);

	/*
	CpassTRmsTestataRm cloneToEntity(CpassTRmsTestataRm entity);
	TestataRm cloneToModel(TestataRm model);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTOrdDestinatarios", ignore = true ,qualifiedBy = Minimal.class )
	CpassTRmsTestataRm toEntity(TestataRm model);
	 */

}
