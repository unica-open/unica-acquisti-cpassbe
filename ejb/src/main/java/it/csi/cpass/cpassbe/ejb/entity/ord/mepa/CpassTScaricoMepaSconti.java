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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_scarico_mepa_sconti database table.
 *
 */
@Entity
@Table(name="cpass_t_scarico_mepa_sconti")
@NamedQuery(name="CpassTScaricoMepaSconti.findAll", query="SELECT c FROM CpassTScaricoMepaSconti c")
public class CpassTScaricoMepaSconti implements Serializable, BaseEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SCARICO_MEPA_SCONTI_SCARICOMEPASCONTIID_GENERATOR",sequenceName="cpass_t_scarico_mepa_sconti_scarico_mepa_sconti_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SCARICO_MEPA_SCONTI_SCARICOMEPASCONTIID_GENERATOR")
	@Column(name="scarico_mepa_sconti_id")
	private Integer scaricoMepaScontiId;

	@Column(name="orderline_line_allowancecharge_amount")
	private BigDecimal orderlineLineAllowancechargeAmount;

	@Column(name="orderline_line_allowancecharge_base_amount")
	private BigDecimal orderlineLineAllowancechargeBaseAmount;

	@Column(name="orderline_line_allowancecharge_indicator")
	private Boolean orderlineLineAllowancechargeIndicator;

	@Column(name="orderline_line_allowancecharge_multiplier_factor_numeric")
	private BigDecimal orderlineLineAllowancechargeMultiplierFactorNumeric;

	@Column(name="orderline_line_allowancecharge_reason")
	private String orderlineLineAllowancechargeReason;

	//bi-directional many-to-one association to CpassTScaricoMepaRiga
	@ManyToOne
	@JoinColumn(name="scarico_mepa_riga_id")
	private CpassTScaricoMepaRiga cpassTScaricoMepaRiga;

	public CpassTScaricoMepaSconti() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getScaricoMepaScontiId() {
		return scaricoMepaScontiId;
	}

	public void setScaricoMepaScontiId(Integer scaricoMepaScontiId) {
		this.scaricoMepaScontiId = scaricoMepaScontiId;
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

	public Boolean getOrderlineLineAllowancechargeIndicator() {
		return orderlineLineAllowancechargeIndicator;
	}

	public void setOrderlineLineAllowancechargeIndicator(Boolean orderlineLineAllowancechargeIndicator) {
		this.orderlineLineAllowancechargeIndicator = orderlineLineAllowancechargeIndicator;
	}

	public BigDecimal getOrderlineLineAllowancechargeMultiplierFactorNumeric() {
		return orderlineLineAllowancechargeMultiplierFactorNumeric;
	}

	public void setOrderlineLineAllowancechargeMultiplierFactorNumeric(BigDecimal orderlineLineAllowancechargeMultiplierFactorNumeric) {
		this.orderlineLineAllowancechargeMultiplierFactorNumeric = orderlineLineAllowancechargeMultiplierFactorNumeric;
	}

	public String getOrderlineLineAllowancechargeReason() {
		return orderlineLineAllowancechargeReason;
	}

	public void setOrderlineLineAllowancechargeReason(String orderlineLineAllowancechargeReason) {
		this.orderlineLineAllowancechargeReason = orderlineLineAllowancechargeReason;
	}

	public CpassTScaricoMepaRiga getCpassTScaricoMepaRiga() {
		return cpassTScaricoMepaRiga;
	}

	public void setCpassTScaricoMepaRiga(CpassTScaricoMepaRiga cpassTScaricoMepaRiga) {
		this.cpassTScaricoMepaRiga = cpassTScaricoMepaRiga;
	}

	@Override
	public Integer getId() {
		return scaricoMepaScontiId;
	}

	@Override
	public void setId(Integer id) {
		scaricoMepaScontiId = id;
	}

}
