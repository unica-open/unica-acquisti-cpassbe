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

import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaRiga;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = {ScaricoMepaScontiMapper.class})
public interface ScaricoMepaRigaMapper extends BaseMapperInterface<ScaricoMepaRiga,CpassTScaricoMepaRiga>{

	@Override
	@Mapping(source = "basequantity", target = "basequantity")
	@Mapping(source = "classifiedtaxcategoryId", target = "classifiedtaxcategoryId")
	@Mapping(source = "classifiedtaxcategoryPercent", target = "classifiedtaxcategoryPercent")
	@Mapping(source = "classifiedtaxcategoryTaxschemeId", target = "classifiedtaxcategoryTaxschemeId")
	@Mapping(source = "itemBuyersId", target = "itemBuyersId")
	@Mapping(source = "itemClassificationCode", target = "itemClassificationCode")
	@Mapping(source = "itemName", target = "itemName")
	@Mapping(source = "itemSellersId", target = "itemSellersId")
	@Mapping(source = "orderlineId", target = "orderlineId")
	@Mapping(source = "orderlineLineDeliveryId", target = "orderlineLineDeliveryId")
	@Mapping(source = "orderlineLineExtensionAmount", target = "orderlineLineExtensionAmount")
	@Mapping(source = "orderlineLinePartialDeliveryIndicator", target = "orderlineLinePartialDeliveryIndicator")
	@Mapping(source = "orderlineNote", target = "orderlineNote")
	@Mapping(source = "orderlineQuantity", target = "orderlineQuantity")
	@Mapping(source = "priceamount", target = "priceamount")
	@Mapping(source = "cpassTScaricoMepaTestata", target = "scaricoMepaTestata")
	@Mapping(source = "cpassTScaricoMepaScontis", target = "scaricoMepaScontis")
	ScaricoMepaRiga toModel(CpassTScaricoMepaRiga entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTScaricoMepaRiga toEntity(ScaricoMepaRiga model);



}
