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
package it.csi.cpass.cpassbe.ejb.entity.ord.mepa;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_scarico_mepa_testata database table.
 *
 */
@Entity
@Table(name="cpass_t_scarico_mepa_testata")
@NamedQuery(name="CpassTScaricoMepaTestata.findAll", query="SELECT c FROM CpassTScaricoMepaTestata c")
public class CpassTScaricoMepaTestata implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SCARICO_MEPA_TESTATA_SCARICOMEPATESTATAID_GENERATOR", sequenceName="cpass_t_scarico_mepa_testata_scarico_mepa_testata_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SCARICO_MEPA_TESTATA_SCARICOMEPATESTATAID_GENERATOR")
	@Column(name="scarico_mepa_testata_id")
	private Integer scaricoMepaTestataId;

	@Column(name="additional_document_reference_document_type")
	private String additionalDocumentReferenceDocumentType;

	@Column(name="additional_document_reference_id")
	private String additionalDocumentReferenceId;

	@Column(name="anticipatedmonetarytotal_allowance_total_amount")
	private BigDecimal anticipatedmonetarytotalAllowanceTotalAmount;

	@Column(name="anticipatedmonetarytotal_line_extension_amount")
	private BigDecimal anticipatedmonetarytotalLineExtensionAmount;

	@Column(name="anticipatedmonetarytotal_payable_amount")
	private BigDecimal anticipatedmonetarytotalPayableAmount;

	@Column(name="anticipatedmonetarytotal_tax_exclusive_amount")
	private BigDecimal anticipatedmonetarytotalTaxExclusiveAmount;

	@Column(name="anticipatedmonetarytotal_tax_inclusive_amount")
	private BigDecimal anticipatedmonetarytotalTaxInclusiveAmount;

	@Column(name="buyer_customer_party_city_name")
	private String buyerCustomerPartyCityName;

	@Column(name="buyer_customer_party_company_id")
	private String buyerCustomerPartyCompanyId;

	@Column(name="buyer_customer_party_id")
	private String buyerCustomerPartyId;

	@Column(name="buyer_customer_party_name")
	private String buyerCustomerPartyName;

	@Column(name="buyer_customer_party_postal_zone")
	private String buyerCustomerPartyPostalZone;

	@Column(name="buyer_customer_party_street_name")
	private String buyerCustomerPartyStreetName;

	@Column(name="customer_reference")
	private String customerReference;

	@Column(name="deliverparty_contact_email")
	private String deliverpartyContactEmail;

	@Column(name="deliverparty_contact_name")
	private String deliverpartyContactName;

	@Column(name="deliverparty_contact_telephone")
	private String deliverpartyContactTelephone;

	@Column(name="deliverparty_id")
	private String deliverpartyId;

	@Column(name="deliverparty_name")
	private String deliverpartyName;

	@Column(name="delivery_location_city_name")
	private String deliveryLocationCityName;

	@Column(name="delivery_location_id")
	private String deliveryLocationId;

	@Column(name="delivery_location_postal_zone")
	private String deliveryLocationPostalZone;

	@Column(name="delivery_location_street_name")
	private String deliveryLocationStreetName;

	@Column(name="end_date")
	private String endDate;

	@Column(name="issue_date")
	private String issueDate;

	private String note;

	@Column(name="order_id")
	private String orderId;

	@Column(name="order_type_code")
	private String orderTypeCode;

	@Column(name="originator_document_reference_id")
	private String originatorDocumentReferenceId;

	@Column(name="requesteddeliveryperiod_end_date")
	private String requesteddeliveryperiodEndDate;

	@Column(name="requesteddeliveryperiod_start_date")
	private String requesteddeliveryperiodStartDate;

	@Column(name="sellersupplierparty_city_name")
	private String sellersupplierpartyCityName;

	@Column(name="sellersupplierparty_endpoint_id")
	private String sellersupplierpartyEndpointId;

	@Column(name="sellersupplierparty_name")
	private String sellersupplierpartyName;

	@Column(name="sellersupplierparty_postal_zone")
	private String sellersupplierpartyPostalZone;

	@Column(name="sellersupplierparty_registration_name")
	private String sellersupplierpartyRegistrationName;

	@Column(name="sellersupplierparty_street_name")
	private String sellersupplierpartyStreetName;

	@Column(name="tax_total")
	private BigDecimal taxTotal;

	//bi-directional many-to-one association to CpassTScaricoMepaRiga
	@OneToMany(mappedBy="cpassTScaricoMepaTestata")
	private List<CpassTScaricoMepaRiga> cpassTScaricoMepaRigas;

	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTScaricoMepaXml
	@OneToMany(mappedBy="cpassTScaricoMepaTestata")
	private List<CpassTScaricoMepaXml> cpassTScaricoMepaXmls;

	public CpassTScaricoMepaTestata() {
	}

	public Integer getScaricoMepaTestataId() {
		return this.scaricoMepaTestataId;
	}

	public void setScaricoMepaTestataId(Integer scaricoMepaTestataId) {
		this.scaricoMepaTestataId = scaricoMepaTestataId;
	}

	public String getAdditionalDocumentReferenceDocumentType() {
		return this.additionalDocumentReferenceDocumentType;
	}

	public void setAdditionalDocumentReferenceDocumentType(String additionalDocumentReferenceDocumentType) {
		this.additionalDocumentReferenceDocumentType = additionalDocumentReferenceDocumentType;
	}

	public String getAdditionalDocumentReferenceId() {
		return this.additionalDocumentReferenceId;
	}

	public void setAdditionalDocumentReferenceId(String additionalDocumentReferenceId) {
		this.additionalDocumentReferenceId = additionalDocumentReferenceId;
	}

	public BigDecimal getAnticipatedmonetarytotalAllowanceTotalAmount() {
		return this.anticipatedmonetarytotalAllowanceTotalAmount;
	}

	public void setAnticipatedmonetarytotalAllowanceTotalAmount(BigDecimal anticipatedmonetarytotalAllowanceTotalAmount) {
		this.anticipatedmonetarytotalAllowanceTotalAmount = anticipatedmonetarytotalAllowanceTotalAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalLineExtensionAmount() {
		return this.anticipatedmonetarytotalLineExtensionAmount;
	}

	public void setAnticipatedmonetarytotalLineExtensionAmount(BigDecimal anticipatedmonetarytotalLineExtensionAmount) {
		this.anticipatedmonetarytotalLineExtensionAmount = anticipatedmonetarytotalLineExtensionAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalPayableAmount() {
		return this.anticipatedmonetarytotalPayableAmount;
	}

	public void setAnticipatedmonetarytotalPayableAmount(BigDecimal anticipatedmonetarytotalPayableAmount) {
		this.anticipatedmonetarytotalPayableAmount = anticipatedmonetarytotalPayableAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalTaxExclusiveAmount() {
		return this.anticipatedmonetarytotalTaxExclusiveAmount;
	}

	public void setAnticipatedmonetarytotalTaxExclusiveAmount(BigDecimal anticipatedmonetarytotalTaxExclusiveAmount) {
		this.anticipatedmonetarytotalTaxExclusiveAmount = anticipatedmonetarytotalTaxExclusiveAmount;
	}

	public BigDecimal getAnticipatedmonetarytotalTaxInclusiveAmount() {
		return this.anticipatedmonetarytotalTaxInclusiveAmount;
	}

	public void setAnticipatedmonetarytotalTaxInclusiveAmount(BigDecimal anticipatedmonetarytotalTaxInclusiveAmount) {
		this.anticipatedmonetarytotalTaxInclusiveAmount = anticipatedmonetarytotalTaxInclusiveAmount;
	}

	public String getBuyerCustomerPartyCityName() {
		return this.buyerCustomerPartyCityName;
	}

	public void setBuyerCustomerPartyCityName(String buyerCustomerPartyCityName) {
		this.buyerCustomerPartyCityName = buyerCustomerPartyCityName;
	}

	public String getBuyerCustomerPartyCompanyId() {
		return this.buyerCustomerPartyCompanyId;
	}

	public void setBuyerCustomerPartyCompanyId(String buyerCustomerPartyCompanyId) {
		this.buyerCustomerPartyCompanyId = buyerCustomerPartyCompanyId;
	}

	public String getBuyerCustomerPartyId() {
		return this.buyerCustomerPartyId;
	}

	public void setBuyerCustomerPartyId(String buyerCustomerPartyId) {
		this.buyerCustomerPartyId = buyerCustomerPartyId;
	}

	public String getBuyerCustomerPartyName() {
		return this.buyerCustomerPartyName;
	}

	public void setBuyerCustomerPartyName(String buyerCustomerPartyName) {
		this.buyerCustomerPartyName = buyerCustomerPartyName;
	}

	public String getBuyerCustomerPartyPostalZone() {
		return this.buyerCustomerPartyPostalZone;
	}

	public void setBuyerCustomerPartyPostalZone(String buyerCustomerPartyPostalZone) {
		this.buyerCustomerPartyPostalZone = buyerCustomerPartyPostalZone;
	}

	public String getBuyerCustomerPartyStreetName() {
		return this.buyerCustomerPartyStreetName;
	}

	public void setBuyerCustomerPartyStreetName(String buyerCustomerPartyStreetName) {
		this.buyerCustomerPartyStreetName = buyerCustomerPartyStreetName;
	}

	public String getCustomerReference() {
		return this.customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getDeliverpartyContactEmail() {
		return this.deliverpartyContactEmail;
	}

	public void setDeliverpartyContactEmail(String deliverpartyContactEmail) {
		this.deliverpartyContactEmail = deliverpartyContactEmail;
	}

	public String getDeliverpartyContactName() {
		return this.deliverpartyContactName;
	}

	public void setDeliverpartyContactName(String deliverpartyContactName) {
		this.deliverpartyContactName = deliverpartyContactName;
	}

	public String getDeliverpartyContactTelephone() {
		return this.deliverpartyContactTelephone;
	}

	public void setDeliverpartyContactTelephone(String deliverpartyContactTelephone) {
		this.deliverpartyContactTelephone = deliverpartyContactTelephone;
	}

	public String getDeliverpartyId() {
		return this.deliverpartyId;
	}

	public void setDeliverpartyId(String deliverpartyId) {
		this.deliverpartyId = deliverpartyId;
	}

	public String getDeliverpartyName() {
		return this.deliverpartyName;
	}

	public void setDeliverpartyName(String deliverpartyName) {
		this.deliverpartyName = deliverpartyName;
	}

	public String getDeliveryLocationCityName() {
		return this.deliveryLocationCityName;
	}

	public void setDeliveryLocationCityName(String deliveryLocationCityName) {
		this.deliveryLocationCityName = deliveryLocationCityName;
	}

	public String getDeliveryLocationId() {
		return this.deliveryLocationId;
	}

	public void setDeliveryLocationId(String deliveryLocationId) {
		this.deliveryLocationId = deliveryLocationId;
	}

	public String getDeliveryLocationPostalZone() {
		return this.deliveryLocationPostalZone;
	}

	public void setDeliveryLocationPostalZone(String deliveryLocationPostalZone) {
		this.deliveryLocationPostalZone = deliveryLocationPostalZone;
	}

	public String getDeliveryLocationStreetName() {
		return this.deliveryLocationStreetName;
	}

	public void setDeliveryLocationStreetName(String deliveryLocationStreetName) {
		this.deliveryLocationStreetName = deliveryLocationStreetName;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public String getOriginatorDocumentReferenceId() {
		return this.originatorDocumentReferenceId;
	}

	public void setOriginatorDocumentReferenceId(String originatorDocumentReferenceId) {
		this.originatorDocumentReferenceId = originatorDocumentReferenceId;
	}

	public String getRequesteddeliveryperiodEndDate() {
		return this.requesteddeliveryperiodEndDate;
	}

	public void setRequesteddeliveryperiodEndDate(String requesteddeliveryperiodEndDate) {
		this.requesteddeliveryperiodEndDate = requesteddeliveryperiodEndDate;
	}

	public String getRequesteddeliveryperiodStartDate() {
		return this.requesteddeliveryperiodStartDate;
	}

	public void setRequesteddeliveryperiodStartDate(String requesteddeliveryperiodStartDate) {
		this.requesteddeliveryperiodStartDate = requesteddeliveryperiodStartDate;
	}

	public String getSellersupplierpartyCityName() {
		return this.sellersupplierpartyCityName;
	}

	public void setSellersupplierpartyCityName(String sellersupplierpartyCityName) {
		this.sellersupplierpartyCityName = sellersupplierpartyCityName;
	}

	public String getSellersupplierpartyEndpointId() {
		return this.sellersupplierpartyEndpointId;
	}

	public void setSellersupplierpartyEndpointId(String sellersupplierpartyEndpointId) {
		this.sellersupplierpartyEndpointId = sellersupplierpartyEndpointId;
	}

	public String getSellersupplierpartyName() {
		return this.sellersupplierpartyName;
	}

	public void setSellersupplierpartyName(String sellersupplierpartyName) {
		this.sellersupplierpartyName = sellersupplierpartyName;
	}

	public String getSellersupplierpartyPostalZone() {
		return this.sellersupplierpartyPostalZone;
	}

	public void setSellersupplierpartyPostalZone(String sellersupplierpartyPostalZone) {
		this.sellersupplierpartyPostalZone = sellersupplierpartyPostalZone;
	}

	public String getSellersupplierpartyRegistrationName() {
		return this.sellersupplierpartyRegistrationName;
	}

	public void setSellersupplierpartyRegistrationName(String sellersupplierpartyRegistrationName) {
		this.sellersupplierpartyRegistrationName = sellersupplierpartyRegistrationName;
	}

	public String getSellersupplierpartyStreetName() {
		return this.sellersupplierpartyStreetName;
	}

	public void setSellersupplierpartyStreetName(String sellersupplierpartyStreetName) {
		this.sellersupplierpartyStreetName = sellersupplierpartyStreetName;
	}

	public BigDecimal getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	public List<CpassTScaricoMepaRiga> getCpassTScaricoMepaRigas() {
		return this.cpassTScaricoMepaRigas;
	}

	public void setCpassTScaricoMepaRigas(List<CpassTScaricoMepaRiga> cpassTScaricoMepaRigas) {
		this.cpassTScaricoMepaRigas = cpassTScaricoMepaRigas;
	}

	public CpassTScaricoMepaRiga addCpassTScaricoMepaRiga(CpassTScaricoMepaRiga cpassTScaricoMepaRiga) {
		getCpassTScaricoMepaRigas().add(cpassTScaricoMepaRiga);
		cpassTScaricoMepaRiga.setCpassTScaricoMepaTestata(this);

		return cpassTScaricoMepaRiga;
	}

	public CpassTScaricoMepaRiga removeCpassTScaricoMepaRiga(CpassTScaricoMepaRiga cpassTScaricoMepaRiga) {
		getCpassTScaricoMepaRigas().remove(cpassTScaricoMepaRiga);
		cpassTScaricoMepaRiga.setCpassTScaricoMepaTestata(null);

		return cpassTScaricoMepaRiga;
	}

	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public List<CpassTScaricoMepaXml> getCpassTScaricoMepaXmls() {
		return this.cpassTScaricoMepaXmls;
	}

	public void setCpassTScaricoMepaXmls(List<CpassTScaricoMepaXml> cpassTScaricoMepaXmls) {
		this.cpassTScaricoMepaXmls = cpassTScaricoMepaXmls;
	}

	public CpassTScaricoMepaXml addCpassTScaricoMepaXml(CpassTScaricoMepaXml cpassTScaricoMepaXml) {
		getCpassTScaricoMepaXmls().add(cpassTScaricoMepaXml);
		cpassTScaricoMepaXml.setCpassTScaricoMepaTestata(this);

		return cpassTScaricoMepaXml;
	}

	public CpassTScaricoMepaXml removeCpassTScaricoMepaXml(CpassTScaricoMepaXml cpassTScaricoMepaXml) {
		getCpassTScaricoMepaXmls().remove(cpassTScaricoMepaXml);
		cpassTScaricoMepaXml.setCpassTScaricoMepaTestata(null);

		return cpassTScaricoMepaXml;
	}

	@Override
	public Integer getId() {
		return scaricoMepaTestataId;
	}

	@Override
	public void setId(Integer id) {
		scaricoMepaTestataId = id;
	}
}
