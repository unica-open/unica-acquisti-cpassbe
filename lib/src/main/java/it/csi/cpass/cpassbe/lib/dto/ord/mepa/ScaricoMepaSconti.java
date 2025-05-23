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

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;

public class ScaricoMepaSconti extends BaseAuditedDto<Integer> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private ScaricoMepaRiga scaricoMepaRiga; //scarico_mepa_riga_id

	private Boolean orderlineLineAllowancechargeIndicator;
	private String orderlineLineAllowancechargeReason;
	private BigDecimal orderlineLineAllowancechargeMultiplierFactorNumeric;
	private BigDecimal orderlineLineAllowancechargeAmount;
	private BigDecimal orderlineLineAllowancechargeBaseAmount;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public ScaricoMepaRiga getScaricoMepaRiga() {
		return scaricoMepaRiga;
	}

	public void setScaricoMepaRiga(ScaricoMepaRiga scaricoMepaRiga) {
		this.scaricoMepaRiga = scaricoMepaRiga;
	}

	public Boolean getOrderlineLineAllowancechargeIndicator() {
		return orderlineLineAllowancechargeIndicator;
	}

	public void setOrderlineLineAllowancechargeIndicator(Boolean orderlineLineAllowancechargeIndicator) {
		this.orderlineLineAllowancechargeIndicator = orderlineLineAllowancechargeIndicator;
	}

	public String getOrderlineLineAllowancechargeReason() {
		return orderlineLineAllowancechargeReason;
	}

	public void setOrderlineLineAllowancechargeReason(String orderlineLineAllowancechargeReason) {
		this.orderlineLineAllowancechargeReason = orderlineLineAllowancechargeReason;
	}

	public BigDecimal getOrderlineLineAllowancechargeMultiplierFactorNumeric() {
		return orderlineLineAllowancechargeMultiplierFactorNumeric;
	}

	public void setOrderlineLineAllowancechargeMultiplierFactorNumeric(BigDecimal orderlineLineAllowancechargeMultiplierFactorNumeric) {
		this.orderlineLineAllowancechargeMultiplierFactorNumeric = orderlineLineAllowancechargeMultiplierFactorNumeric;
	}

	public BigDecimal getOrderlineLineAllowancechargeAmount() {
		return orderlineLineAllowancechargeAmount;
	}

	public void setOrderlineLineAllowancechargeAmount(BigDecimal orderlineLineAllowancechargeAmount) {
		this.orderlineLineAllowancechargeAmount = orderlineLineAllowancechargeAmount;
	}

	public BigDecimal getOrderlineLineAllowancechargeBaseAmount() {
		return orderlineLineAllowancechargeBaseAmount;
	}

	public void setOrderlineLineAllowancechargeBaseAmount(BigDecimal orderlineLineAllowancechargeBaseAmount) {
		this.orderlineLineAllowancechargeBaseAmount = orderlineLineAllowancechargeBaseAmount;
	}
}
