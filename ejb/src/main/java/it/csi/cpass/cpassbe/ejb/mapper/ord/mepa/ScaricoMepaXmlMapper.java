/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.mapper.ord.mepa;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaXml;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaXml;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper()
public interface ScaricoMepaXmlMapper extends BaseMapperInterface<ScaricoMepaXml, CpassTScaricoMepaXml>{

	@Override
	@Mapping(source = "cpassTScaricoMepaTestata", target = "scaricoMepaTestata")
	@Mapping(source = "dataSpostamento", target = "dataSpostamento")
	@Mapping(source = "fileXml", target = "fileXml")
	@Mapping(source = "pathFile", target = "pathFile")
	ScaricoMepaXml toModel(CpassTScaricoMepaXml entity);


	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTScaricoMepaXml toEntity(ScaricoMepaXml model);

}
