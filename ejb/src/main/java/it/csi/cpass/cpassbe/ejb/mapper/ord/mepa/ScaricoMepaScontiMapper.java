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

import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaSconti;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaSconti;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper()
public interface ScaricoMepaScontiMapper extends BaseMapperInterface<ScaricoMepaSconti,CpassTScaricoMepaSconti>{

	@Override
	@Mapping(source = "orderlineLineAllowancechargeAmount", target = "orderlineLineAllowancechargeAmount")
	@Mapping(source = "orderlineLineAllowancechargeBaseAmount", target = "orderlineLineAllowancechargeBaseAmount")
	@Mapping(source = "orderlineLineAllowancechargeIndicator", target = "orderlineLineAllowancechargeIndicator")
	@Mapping(source = "orderlineLineAllowancechargeMultiplierFactorNumeric", target = "orderlineLineAllowancechargeMultiplierFactorNumeric")
	@Mapping(source = "orderlineLineAllowancechargeReason", target = "orderlineLineAllowancechargeReason")
	@Mapping(source = "cpassTScaricoMepaRiga", target = "scaricoMepaRiga")
	ScaricoMepaSconti toModel(CpassTScaricoMepaSconti entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTScaricoMepaSconti toEntity(ScaricoMepaSconti model);



}
