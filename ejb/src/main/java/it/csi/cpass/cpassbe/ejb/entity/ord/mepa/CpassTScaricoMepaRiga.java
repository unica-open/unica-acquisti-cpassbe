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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_scarico_mepa_riga database table.
 *
 */
@Entity
@Table(name="cpass_t_scarico_mepa_riga")
@NamedQuery(name="CpassTScaricoMepaRiga.findAll", query="SELECT c FROM CpassTScaricoMepaRiga c")
public class CpassTScaricoMepaRiga implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SCARICO_MEPA_RIGA_SCARICOMEPARIGAID_GENERATOR", sequenceName="cpass_t_scarico_mepa_riga_scarico_mepa_riga_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SCARICO_MEPA_RIGA_SCARICOMEPARIGAID_GENERATOR")
	@Column(name="scarico_mepa_riga_id")
	private Integer scaricoMepaRigaId;

	private BigDecimal basequantity;

	@Column(name="classifiedtaxcategory_id")
	private String classifiedtaxcategoryId;

	@Column(name="classifiedtaxcategory_percent")
	private BigDecimal classifiedtaxcategoryPercent;

	@Column(name="classifiedtaxcategory_taxscheme_id")
	private String classifiedtaxcategoryTaxschemeId;

	@Column(name="item_buyers_id")
	private String itemBuyersId;

	@Column(name="item_classification_code")
	private String itemClassificationCode;

	@Column(name="item_name")
	private String itemName;

	@Column(name="item_sellers_id")
	private String itemSellersId;

	@Column(name="orderline_id")
	private String orderlineId;

	@Column(name="orderline_line_delivery_id")
	private String orderlineLineDeliveryId;

	@Column(name="orderline_line_extension_amount")
	private BigDecimal orderlineLineExtensionAmount;

	@Column(name="orderline_line_partial_delivery_indicator")
	private Boolean orderlineLinePartialDeliveryIndicator;

	@Column(name="orderline_note")
	private String orderlineNote;

	@Column(name="orderline_quantity")
	private BigDecimal orderlineQuantity;

	@Column(name="orderline_quantity_unit_code")
	private String orderlineQuantityUnitCode;

	private BigDecimal priceamount;

	//bi-directional many-to-one association to CpassTScaricoMepaTestata
	@ManyToOne
	@JoinColumn(name="scarico_mepa_testata_id")
	private CpassTScaricoMepaTestata cpassTScaricoMepaTestata;

	//bi-directional many-to-one association to CpassTScaricoMepaSconti
	@OneToMany(mappedBy="cpassTScaricoMepaRiga")
	private List<CpassTScaricoMepaSconti> cpassTScaricoMepaScontis;

	public CpassTScaricoMepaRiga() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getScaricoMepaRigaId() {
		return scaricoMepaRigaId;
	}

	public void setScaricoMepaRigaId(Integer scaricoMepaRigaId) {
		this.scaricoMepaRigaId = scaricoMepaRigaId;
	}

	public BigDecimal getBasequantity() {
		return basequantity;
	}

	public void setBasequantity(BigDecimal basequantity) {
		this.basequantity = basequantity;
	}

	public String getClassifiedtaxcategoryId() {
		return classifiedtaxcategoryId;
	}

	public void setClassifiedtaxcategoryId(String classifiedtaxcategoryId) {
		this.classifiedtaxcategoryId = classifiedtaxcategoryId;
	}

	public BigDecimal getClassifiedtaxcategoryPercent() {
		return classifiedtaxcategoryPercent;
	}

	public void setClassifiedtaxcategoryPercent(BigDecimal classifiedtaxcategoryPercent) {
		this.classifiedtaxcategoryPercent = classifiedtaxcategoryPercent;
	}

	public String getClassifiedtaxcategoryTaxschemeId() {
		return classifiedtaxcategoryTaxschemeId;
	}

	public void setClassifiedtaxcategoryTaxschemeId(String classifiedtaxcategoryTaxschemeId) {
		this.classifiedtaxcategoryTaxschemeId = classifiedtaxcategoryTaxschemeId;
	}

	public String getItemBuyersId() {
		return itemBuyersId;
	}

	public void setItemBuyersId(String itemBuyersId) {
		this.itemBuyersId = itemBuyersId;
	}

	public String getItemClassificationCode() {
		return itemClassificationCode;
	}

	public void setItemClassificationCode(String itemClassificationCode) {
		this.itemClassificationCode = itemClassificationCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSellersId() {
		return itemSellersId;
	}

	public void setItemSellersId(String itemSellersId) {
		this.itemSellersId = itemSellersId;
	}

	public String getOrderlineId() {
		return orderlineId;
	}

	public void setOrderlineId(String orderlineId) {
		this.orderlineId = orderlineId;
	}

	public String getOrderlineLineDeliveryId() {
		return orderlineLineDeliveryId;
	}

	public void setOrderlineLineDeliveryId(String orderlineLineDeliveryId) {
		this.orderlineLineDeliveryId = orderlineLineDeliveryId;
	}

	public BigDecimal getOrderlineLineExtensionAmount() {
		return orderlineLineExtensionAmount;
	}

	public void setOrderlineLineExtensionAmount(BigDecimal orderlineLineExtensionAmount) {
		this.orderlineLineExtensionAmount = orderlineLineExtensionAmount;
	}

	public Boolean getOrderlineLinePartialDeliveryIndicator() {
		return orderlineLinePartialDeliveryIndicator;
	}

	public void setOrderlineLinePartialDeliveryIndicator(Boolean orderlineLinePartialDeliveryIndicator) {
		this.orderlineLinePartialDeliveryIndicator = orderlineLinePartialDeliveryIndicator;
	}

	public String getOrderlineNote() {
		return orderlineNote;
	}

	public void setOrderlineNote(String orderlineNote) {
		this.orderlineNote = orderlineNote;
	}

	public BigDecimal getOrderlineQuantity() {
		return orderlineQuantity;
	}

	public void setOrderlineQuantity(BigDecimal orderlineQuantity) {
		this.orderlineQuantity = orderlineQuantity;
	}

	public String getOrderlineQuantityUnitCode() {
		return orderlineQuantityUnitCode;
	}

	public void setOrderlineQuantityUnitCode(String orderlineQuantityUnitCode) {
		this.orderlineQuantityUnitCode = orderlineQuantityUnitCode;
	}

	public BigDecimal getPriceamount() {
		return priceamount;
	}

	public void setPriceamount(BigDecimal priceamount) {
		this.priceamount = priceamount;
	}

	public CpassTScaricoMepaTestata getCpassTScaricoMepaTestata() {
		return cpassTScaricoMepaTestata;
	}

	public void setCpassTScaricoMepaTestata(CpassTScaricoMepaTestata cpassTScaricoMepaTestata) {
		this.cpassTScaricoMepaTestata = cpassTScaricoMepaTestata;
	}

	public List<CpassTScaricoMepaSconti> getCpassTScaricoMepaScontis() {
		return cpassTScaricoMepaScontis;
	}

	public void setCpassTScaricoMepaScontis(List<CpassTScaricoMepaSconti> cpassTScaricoMepaScontis) {
		this.cpassTScaricoMepaScontis = cpassTScaricoMepaScontis;
	}

	public CpassTScaricoMepaSconti addCpassTScaricoMepaSconti(CpassTScaricoMepaSconti cpassTScaricoMepaSconti) {
		getCpassTScaricoMepaScontis().add(cpassTScaricoMepaSconti);
		cpassTScaricoMepaSconti.setCpassTScaricoMepaRiga(this);

		return cpassTScaricoMepaSconti;
	}

	public CpassTScaricoMepaSconti removeCpassTScaricoMepaSconti(CpassTScaricoMepaSconti cpassTScaricoMepaSconti) {
		getCpassTScaricoMepaScontis().remove(cpassTScaricoMepaSconti);
		cpassTScaricoMepaSconti.setCpassTScaricoMepaRiga(null);

		return cpassTScaricoMepaSconti;
	}

	@Override
	public Integer getId() {
		return scaricoMepaRigaId;
	}

	@Override
	public void setId(Integer id) {
		scaricoMepaRigaId = id;
	}
}
