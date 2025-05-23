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

import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaTestata;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;


@Mapper(uses = {StatoMapper.class, ScaricoMepaXmlMapper.class, ScaricoMepaRigaMapper.class})
public interface ScaricoMepaTestataMapper extends BaseMapperInterface<ScaricoMepaTestata, CpassTScaricoMepaTestata>{

	@Override
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "additionalDocumentReferenceDocumentType", target = "additionalDocumentReferenceDocumentType")
	@Mapping(source = "additionalDocumentReferenceId", target = "additionalDocumentReferenceId")
	@Mapping(source = "anticipatedmonetarytotalAllowanceTotalAmount", target = "anticipatedmonetarytotalAllowanceTotalAmount")
	@Mapping(source = "anticipatedmonetarytotalLineExtensionAmount", target = "anticipatedmonetarytotalLineExtensionAmount")
	@Mapping(source = "anticipatedmonetarytotalPayableAmount", target = "anticipatedmonetarytotalPayableAmount")
	@Mapping(source = "anticipatedmonetarytotalTaxExclusiveAmount", target = "anticipatedmonetarytotalTaxExclusiveAmount")
	@Mapping(source = "anticipatedmonetarytotalTaxInclusiveAmount", target = "anticipatedmonetarytotalTaxInclusiveAmount")
	@Mapping(source = "buyerCustomerPartyCityName", target = "buyerCustomerPartyCityName")
	@Mapping(source = "buyerCustomerPartyCompanyId", target = "buyerCustomerPartyCompanyId")
	@Mapping(source = "buyerCustomerPartyId", target = "buyerCustomerPartyId")
	@Mapping(source = "buyerCustomerPartyName", target = "buyerCustomerPartyName")
	@Mapping(source = "buyerCustomerPartyPostalZone", target = "buyerCustomerPartyPostalZone")
	@Mapping(source = "buyerCustomerPartyStreetName", target = "buyerCustomerPartyStreetName")
	@Mapping(source = "customerReference", target = "customerReference")
	@Mapping(source = "deliverpartyContactEmail", target = "deliverpartyContactEmail")
	@Mapping(source = "deliverpartyContactName", target = "deliverpartyContactName")
	@Mapping(source = "deliverpartyContactTelephone", target = "deliverpartyContactTelephone")
	@Mapping(source = "deliverpartyId", target = "deliverpartyId")
	@Mapping(source = "deliverpartyName", target = "deliverpartyName")
	@Mapping(source = "deliveryLocationCityName", target = "deliveryLocationCityName")
	@Mapping(source = "deliveryLocationId", target = "deliveryLocationId")
	@Mapping(source = "deliveryLocationPostalZone", target = "deliveryLocationPostalZone")
	@Mapping(source = "deliveryLocationStreetName", target = "deliveryLocationStreetName")
	@Mapping(source = "endDate", target = "endDate")
	@Mapping(source = "issueDate", target = "issueDate")
	@Mapping(source = "note", target = "note")
	@Mapping(source = "orderTypeCode", target = "orderTypeCode")
	@Mapping(source = "originatorDocumentReferenceId", target = "originatorDocumentReferenceId")
	@Mapping(source = "requesteddeliveryperiodEndDate", target = "requesteddeliveryperiodEndDate")
	@Mapping(source = "requesteddeliveryperiodStartDate", target = "requesteddeliveryperiodStartDate")
	@Mapping(source = "sellersupplierpartyCityName", target = "sellersupplierpartyCityName")
	@Mapping(source = "sellersupplierpartyEndpointId", target = "sellersupplierpartyEndpointId")
	@Mapping(source = "sellersupplierpartyName", target = "sellersupplierpartyName")
	@Mapping(source = "sellersupplierpartyPostalZone", target = "sellersupplierpartyPostalZone")
	@Mapping(source = "sellersupplierpartyRegistrationName", target = "sellersupplierpartyRegistrationName")
	@Mapping(source = "sellersupplierpartyStreetName", target = "sellersupplierpartyStreetName")
	@Mapping(source = "taxTotal", target = "taxTotal")
	@Mapping(source = "cpassTScaricoMepaRigas", target = "scaricoMepaRigas")
	@Mapping(source = "cpassTScaricoMepaXmls", target = "scaricoMepaXmls")
	ScaricoMepaTestata toModel(CpassTScaricoMepaTestata entity);


	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTScaricoMepaTestata toEntity(ScaricoMepaTestata model);






}
