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

public class ScaricoMepaRiga extends BaseAuditedDto<Integer> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private ScaricoMepaTestata scaricoMepaTestata; //scarico_mepa_testata_id

	private String orderlineNote;
	private String orderlineId;
	private BigDecimal orderlineQuantity;
	private String orderlineQuantityUnitCode;
	private BigDecimal orderlineLineExtensionAmount;
	private Boolean orderlineLinePartialDeliveryIndicator;
	private String orderlineLineDeliveryId;

	private BigDecimal priceamount;
	private BigDecimal basequantity;

	private String itemName;
	private String itemBuyersId;
	private String itemSellersId;

	private String itemClassificationCode;

	private String classifiedtaxcategoryId;
	private BigDecimal classifiedtaxcategoryPercent;
	private String classifiedtaxcategoryTaxschemeId;

	private List<ScaricoMepaSconti> scaricoMepaScontis;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public ScaricoMepaTestata getScaricoMepaTestata() {
		return scaricoMepaTestata;
	}

	public void setScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		this.scaricoMepaTestata = scaricoMepaTestata;
	}

	public String getOrderlineNote() {
		return orderlineNote;
	}

	public void setOrderlineNote(String orderlineNote) {
		this.orderlineNote = orderlineNote;
	}

	public String getOrderlineId() {
		return orderlineId;
	}

	public void setOrderlineId(String orderlineId) {
		this.orderlineId = orderlineId;
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

	public String getOrderlineLineDeliveryId() {
		return orderlineLineDeliveryId;
	}

	public void setOrderlineLineDeliveryId(String orderlineLineDeliveryId) {
		this.orderlineLineDeliveryId = orderlineLineDeliveryId;
	}

	public BigDecimal getPriceamount() {
		return priceamount;
	}

	public void setPriceamount(BigDecimal priceamount) {
		this.priceamount = priceamount;
	}

	public BigDecimal getBasequantity() {
		return basequantity;
	}

	public void setBasequantity(BigDecimal basequantity) {
		this.basequantity = basequantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemBuyersId() {
		return itemBuyersId;
	}

	public void setItemBuyersId(String itemBuyersId) {
		this.itemBuyersId = itemBuyersId;
	}

	public String getItemSellersId() {
		return itemSellersId;
	}

	public void setItemSellersId(String itemSellersId) {
		this.itemSellersId = itemSellersId;
	}

	public String getItemClassificationCode() {
		return itemClassificationCode;
	}

	public void setItemClassificationCode(String itemClassificationCode) {
		this.itemClassificationCode = itemClassificationCode;
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

	public List<ScaricoMepaSconti> getScaricoMepaScontis() {
		return scaricoMepaScontis;
	}

	public void setScaricoMepaScontis(List<ScaricoMepaSconti> scaricoMepaScontis) {
		this.scaricoMepaScontis = scaricoMepaScontis;
	}
}
