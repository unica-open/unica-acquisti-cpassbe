/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
 * The persistent class for the cpass_r_ufficio_settore database table.
 * 
 */
@Entity
@Table(name="cpass_r_ufficio_settore")
@NamedQuery(name="CpassRUfficioSettore.findAll", query="SELECT c FROM CpassRUfficioSettore c")
public class CpassRUfficioSettore implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_UFFICIO_SETTORE_UFFICIOSETTOREID_GENERATOR", sequenceName="cpass_r_ufficio_settore_ufficio_settore_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_UFFICIO_SETTORE_UFFICIOSETTOREID_GENERATOR")
	@Column(name="ufficio_settore_id")
	private Integer ufficioSettoreId;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTUfficio
	@ManyToOne
	@JoinColumn(name="ufficio_id")
	private CpassTUfficio cpassTUfficio;

	public CpassRUfficioSettore() {
	}

	public Integer getUfficioSettoreId() {
		return this.ufficioSettoreId;
	}

	public void setUfficioSettoreId(Integer ufficioSettoreId) {
		this.ufficioSettoreId = ufficioSettoreId;
	}

	public Date getDataValiditaFine() {
		return this.dataValiditaFine;
	}

	public void setDataValiditaFine(Timestamp dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	public Date getDataValiditaInizio() {
		return this.dataValiditaInizio;
	}

	public void setDataValiditaInizio(Timestamp dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public CpassTUfficio getCpassTUfficio() {
		return this.cpassTUfficio;
	}

	public void setCpassTUfficio(CpassTUfficio cpassTUfficio) {
		this.cpassTUfficio = cpassTUfficio;
	}

	@Override
	public Integer getId() {
		return this.ufficioSettoreId;
	}

	@Override
	public void setId(Integer id) {
		this.ufficioSettoreId = id;
	}

}
