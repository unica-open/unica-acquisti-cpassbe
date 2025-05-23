/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.mepa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Stato;

public class ScaricoMepaTestata extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String orderId;
	private Stato stato; //stato_id
	private String issueDate;
	private String orderTypeCode;
	private String note;
	private String customerReference;
	private String endDate;
	private String originatorDocumentReferenceId;
	private String additionalDocumentReferenceId;
	private String additionalDocumentReferenceDocumentType;

	private String buyerCustomerPartyId;
	private String buyerCustomerPartyName;
	private String buyerCustomerPartyStreetName;
	private String buyerCustomerPartyCityName;
	private String buyerCustomerPartyPostalZone;
	private String buyerCustomerPartyCompanyId;

	private String sellersupplierpartyEndpointId;
	private String sellersupplierpartyName;
	private String sellersupplierpartyStreetName;
	private String sellersupplierpartyCityName;
	private String sellersupplierpartyPostalZone;
	private String sellersupplierpartyRegistrationName;

	private String deliveryLocationId;
	private String deliveryLocationStreetName;
	private String deliveryLocationCityName;
	private String deliveryLocationPostalZone;

	private String requesteddeliveryperiodStartDate;
	private String requesteddeliveryperiodEndDate;

	private String deliverpartyId;
	private String deliverpartyName;
	private String deliverpartyContactName;
	private String deliverpartyContactTelephone;
	private String deliverpartyContactEmail;

	private BigDecimal taxTotal;

	private BigDecimal anticipatedmonetarytotalLineExtensionAmount;
	private BigDecimal anticipatedmonetarytotalAllowanceTotalAmount;
	private BigDecimal anticipatedmonetarytotalTaxExclusiveAmount;
	private BigDecimal anticipatedmonetarytotalTaxInclusiveAmount;
	private BigDecimal anticipatedmonetarytotalPayableAmount;

	private List<ScaricoMepaRiga> scaricoMepaRigas;
	private List<ScaricoMepaXml> scaricoMepaXmls;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOriginatorDocumentReferenceId() {
		return originatorDocumentReferenceId;
	}

	public void setOriginatorDocumentReferenceId(String originatorDocumentReferenceId) {
		this.originatorDocumentReferenceId = originatorDocumentReferenceId;
	}

	public String getAdditionalDocumentReferenceId() {
		return additionalDocumentReferenceId;
	}

	public void setAdditionalDocumentReferenceId(String additionalDocumentReferenceId) {
		this.additionalDocumentReferenceId = additionalDocumentReferenceId;
	}

	public String getAdditionalDocumentReferenceDocumentType() {
		return additionalDocumentReferenceDocumentType;
	}

	public void setAdditionalDocumentReferenceDocumentType(String additionalDocumentReferenceDocumentType) {
		this.additionalDocumentReferenceDocumentType = additionalDocumentReferenceDocumentType;
	}

	public String getBuyerCustomerPartyId() {
		return buyerCustomerPartyId;
	}

	public void setBuyerCustomerPartyId(String buyerCustomerPartyId) {
		this.buyerCustomerPartyId = buyerCustomerPartyId;
	}

	public String getBuyerCustomerPartyName() {
		return buyerCustomerPartyName;
	}

	public void setBuyerCustomerPartyName(String buyerCustomerPartyName) {
		this.buyerCustomerPartyName = buyerCustomerPartyName;
	}

	public String getBuyerCustomerPartyStreetName() {
		return buyerCustomerPartyStreetName;
	}

	public void setBuyerCustomerPartyStreetName(String buyerCustomerPartyStreetName) {
		this.buyerCustomerPartyStreetName = buyerCustomerPartyStreetName;
	}

	public String getBuyerCustomerPartyCityName() {
		return buyerCustomerPartyCityName;
	}

	public void setBuyerCustomerPartyCityName(String buyerCustomerPartyCityName) {
		this.buyerCustomerPartyCityName = buyerCustomerPartyCityName;
	}

	public String getBuyerCustomerPartyPostalZone() {
		return buyerCustomerPartyPostalZone;
	}

	public void setBuyerCustomerPartyPostalZone(String buyerCustomerPartyPostalZone) {
		this.buyerCustomerPartyPostalZone = buyerCustomerPartyPostalZone;
	}

	public String getBuyerCustomerPartyCompanyId() {
		return buyerCustomerPartyCompanyId;
	}

	public void setBuyerCustomerPartyCompanyId(String buyerCustomerPartyCompanyId) {
		this.buyerCustomerPartyCompanyId = buyerCustomerPartyCompanyId;
	}

	public String getSellersupplierpartyEndpointId() {
		return sellersupplierpartyEndpointId;
	}

	public void setSellersupplierpartyEndpointId(String sellersupplierpartyEndpointId) {
		this.sellersupplierpartyEndpointId = sellersupplierpartyEndpointId;
	}

	public String getSellersupplierpartyName() {
		return sellersupplierpartyName;
	}

	public void setSellersupplierpartyName(String sellersupplierpartyName) {
		this.sellersupplierpartyName = sellersupplierpartyName;
	}

	public String getSellersupplierpartyStreetName() {
		return sellersupplierpartyStreetName;
	}

	public void setSellersupplierpartyStreetName(String sellersupplierpartyStreetName) {
		this.sellersupplierpartyStreetName = sellersupplierpartyStreetName;
	}

	public String getSellersupplierpartyCityName() {
		return sellersupplierpartyCityName;
	}

	public void setSellersupplierpartyCityName(String sellersupplierpartyCityName) {
		this.sellersupplierpartyCityName = sellersupplierpartyCityName;
	}

	public String getSellersupplierpartyPostalZone() {
		return sellersupplierpartyPostalZone;
	}

	public void setSellersupplierpartyPostalZone(String sellersupplierpartyPostalZone) {
		this.sellersupplierpartyPostalZone = sellersupplierpartyPostalZone;
	}

	public String getSellersupplierpartyRegistrationName() {
		return sellersupplierpartyRegistrationName;
	}

	public void setSellersupplierpartyRegistrationName(String sellersupplierpartyRegistrationName) {
		this.sellersupplierpartyRegistrationName = sellersupplierpartyRegistrationName;
	}

	public String getDeliveryLocationId() {
		return deliveryLocationId;
	}

	public void setDeliveryLocationId(String deliveryLocationId) {
		this.deliveryLocationId = deliveryLocationId;
	}

	public String getDeliveryLocationStreetName() {
		return deliveryLocationStreetName;
	}

	public void setDeliveryLocationStreetName(String deliveryLocationStreetName) {
		this.deliveryLocationStreetName = deliveryLocationStreetName;
	}

	public String getDeliveryLocationCityName() {
		return deliveryLocationCityName;
	}

	public void setDeliveryLocationCityName(String deliveryLocationCityName) {
		this.deliveryLocationCityName = deliveryLocationCityName;
	}

	public String getDeliveryLocationPostalZone() {
		return deliveryLocationPostalZone;
	}

	public void setDeliveryLocationPostalZone(String deliveryLocationPostalZone) {
		this.deliveryLocationPostalZone = deliveryLocationPostalZone;
	}

	public String getRequesteddeliveryperiodStartDate() {
		return requesteddeliveryperiodStartDate;
	}

	public void setRequesteddeliveryperiodStartDate(String requesteddeliveryperiodStartDate) {
		this.requesteddeliveryperiodStartDate = requesteddeliveryperiodStartDate;
	}

	public String getRequesteddeliveryperiodEndDate() {
		return requesteddeliveryperiodEndDate;
	}

	public void setRequesteddeliveryperiodEndDate(String requesteddeliveryperiodEndDate) {
		this.requesteddeliveryperiodEndDate = requesteddeliveryperiodEndDate;
	}

	public String getDeliverpartyId() {
		return deliverpartyId;
	}

	public void setDeliverpartyId(String deliverpartyId) {
		this.deliverpartyId = deliverpartyId;
	}

	public String getDeliverpartyName() {
		return deliverpartyName;
	}

	public void setDeliverpartyName(String deliverpartyName) {
		this.deliverpartyName = deliverpartyName;
	}

	public String getDeliverpartyContactName() {
		return deliverpartyContactName;
	}

	public void setDeliverpartyContactName(String deliverpartyContactName) {
		this.deliverpartyContactName = deliverpartyContactName;
	}

	public String getDeliverpartyContactTelephone() {
		return deliverpartyContactTelephone;
	}

	public void setDeliverpartyContactTelephone(String deliverpartyContactTelephone) {
		this.deliverpartyContactTelephone = deliverpartyContactTelephone;
	}

	public String getDeliverpartyContactEmail() {
		return deliverpartyContactEmail;
	}

	public void setDeliverpartyContactEmail(String deliverpartyContactEmail) {
		this.deliverpartyContactEmail = deliverpartyContactEmail;
	}

	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	public BigDecimal getAnticipatedmonetarytotalLineExtensionAmount() {
		return anticipatedmonetarytotalLineExtensionAmount;
	}

	public void setAnticipatedmonetarytotalLineExtensionAmount(BigDecimal anticipatedmonetarytotalLineExtensionAmount) {
		this.anticipatedmonetarytotalLineExtensionAmount = anticipatedmonetarytotalLineExtensionAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalAllowanceTotalAmount() {
		return anticipatedmonetarytotalAllowanceTotalAmount;
	}

	public void setAnticipatedmonetarytotalAllowanceTotalAmount(BigDecimal anticipatedmonetarytotalAllowanceTotalAmount) {
		this.anticipatedmonetarytotalAllowanceTotalAmount = anticipatedmonetarytotalAllowanceTotalAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalTaxExclusiveAmount() {
		return anticipatedmonetarytotalTaxExclusiveAmount;
	}

	public void setAnticipatedmonetarytotalTaxExclusiveAmount(BigDecimal anticipatedmonetarytotalTaxExclusiveAmount) {
		this.anticipatedmonetarytotalTaxExclusiveAmount = anticipatedmonetarytotalTaxExclusiveAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalTaxInclusiveAmount() {
		return anticipatedmonetarytotalTaxInclusiveAmount;
	}

	public void setAnticipatedmonetarytotalTaxInclusiveAmount(BigDecimal anticipatedmonetarytotalTaxInclusiveAmount) {
		this.anticipatedmonetarytotalTaxInclusiveAmount = anticipatedmonetarytotalTaxInclusiveAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalPayableAmount() {
		return anticipatedmonetarytotalPayableAmount;
	}

	public void setAnticipatedmonetarytotalPayableAmount(BigDecimal anticipatedmonetarytotalPayableAmount) {
		this.anticipatedmonetarytotalPayableAmount = anticipatedmonetarytotalPayableAmount;
	}

	public List<ScaricoMepaRiga> getScaricoMepaRigas() {
		return scaricoMepaRigas;
	}

	public void setScaricoMepaRigas(List<ScaricoMepaRiga> scaricoMepaRigas) {
		this.scaricoMepaRigas = scaricoMepaRigas;
	}

	public List<ScaricoMepaXml> getScaricoMepaXmls() {
		return scaricoMepaXmls;
	}

	public void setScaricoMepaXmls(List<ScaricoMepaXml> scaricoMepaXmls) {
		this.scaricoMepaXmls = scaricoMepaXmls;
	}
}
