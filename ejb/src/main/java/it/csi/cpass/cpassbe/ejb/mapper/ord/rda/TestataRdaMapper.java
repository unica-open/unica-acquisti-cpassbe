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

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.mapper.EnteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UfficioMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { UtenteMapper.class, StatoMapper.class, StringMapper.class, EnteMapper.class, UfficioMapper.class,SettoreCustomMapper.class, RigaRdaMapper.class })
public interface TestataRdaMapper extends BaseMapperInterface <TestataRda, CpassTOrdTestataRda>{

	@Override
	@Mapping(source = "anno", target = "anno")
	@Mapping(source = "dataAutorizzazione", target = "dataAutorizzazione")
	@Mapping(source = "descrizione", target = "descrizione")
	@Mapping(source = "note", target = "note")
	@Mapping(source = "numero", target = "numero" )
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTSettore", target = "settoreEmittente")
	@Mapping(source = "cpassTUfficio", target = "ufficio")
	@Mapping(source = "cpassTUtente", target = "utenteCompilatore")
	@Mapping(source = "cpassTOrdRigaRda", target = "rigaRda")


	TestataRda toModel(CpassTOrdTestataRda entity);

	@Override
	@IterableMapping(elementTargetType = TestataRda.class)
	List<TestataRda> toModels(Collection<CpassTOrdTestataRda> entities);

	CpassTOrdTestataRda cloneToEntity(CpassTOrdTestataRda entity);

	TestataRda cloneToModel(TestataRda model);

}
