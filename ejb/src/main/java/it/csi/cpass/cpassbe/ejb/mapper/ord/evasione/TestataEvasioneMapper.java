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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UfficioMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.StatoNsoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TipoProceduraOrdMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TestataEvasione and CpassTOrdTestataEvasione
 */
@Mapper(uses = { UtenteMapper.class, StatoMapper.class, StringMapper.class, TipoProceduraOrdMapper.class, SettoreCustomMapper.class, ImpegnoMapper.class,
		StatoNsoMapper.class, TipoEvasioneMapper.class, 	DocumentoTrasportoMapper.class, UfficioMapper.class
})
public interface TestataEvasioneMapper extends BaseMapperInterface<TestataEvasione, CpassTOrdTestataEvasione> {

	@Override
	@Mapping(source = "dataAutorizzazione", target = "dataAutorizzazione")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTUfficio", target = "ufficio")
	@Mapping(source = "cpassTFornitore", target = "fornitore")
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassTUtente", target = "utenteCompilatore")
	@Mapping(source = "cpassDOrdTipoEvasione", target = "tipoEvasione")
	@Mapping(source = "documentoConsegna", target = "documentoConsegna")
	@Mapping(source = "documentoDataConsegna", target = "documentoDataConsegna")
	@Mapping(source = "dataConsegna", target = "dataConsegna")
	TestataEvasione toModel(CpassTOrdTestataEvasione entity);

	@Override
	@IterableMapping(elementTargetType = TestataEvasione.class)
	List<TestataEvasione> toModels(Collection<CpassTOrdTestataEvasione> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdTestataEvasione toEntity(TestataEvasione model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdTestataEvasione.class)
	List<CpassTOrdTestataEvasione> toEntities(Collection<TestataEvasione> models);

	CpassTOrdTestataEvasione cloneToEntity(CpassTOrdTestataEvasione entity);

	TestataEvasione cloneToModel(TestataEvasione model);

}
