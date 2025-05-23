/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;

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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_r_settore_cdc database table.
 *
 */
@Entity
@Table(name="cpass_r_settore_cdc")
@NamedQuery(name="CpassRSettoreCdc.findAll", query="SELECT c FROM CpassRSettoreCdc c")
public class CpassRSettoreCdc extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;



	@Id
	@SequenceGenerator(name="CPASS_R_SETTORE_CDC_ID_GENERATOR", sequenceName="cpass_r_settore_cdc_settore_cdc_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_SETTORE_CDC_ID_GENERATOR")
	@Column(name="settore_cdc_id")
	private Integer settoreCdcId;

	//bi-directional many-to-one association to CpassTCdc
	@ManyToOne
	@JoinColumn(name="cdc_id")
	private CpassTCdc cpassTCdc;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	public CpassRSettoreCdc() {
	}

	public Integer getSettoreCdcId() {
		return this.settoreCdcId;
	}

	public void setSettoreCdcId(Integer settoreCdcId) {
		this.settoreCdcId = settoreCdcId;
	}

	public CpassTCdc getCpassTCdc() {
		return this.cpassTCdc;
	}

	public void setCpassTCdc(CpassTCdc cpassTCdc) {
		this.cpassTCdc = cpassTCdc;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return settoreCdcId;
	}

	@Override
	public void setId(Integer id) {
		settoreCdcId = id;
	}

}
