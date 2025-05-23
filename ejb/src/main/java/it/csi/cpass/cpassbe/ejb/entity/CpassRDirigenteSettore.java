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
 * The persistent class for the cpass_r_dirigente_settore database table.
 *
 */
@Entity
@Table(name="cpass_r_dirigente_settore")
@NamedQuery(name="CpassRDirigenteSettore.findAll", query="SELECT c FROM CpassRDirigenteSettore c")
public class CpassRDirigenteSettore implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	/** The utente settore id. */
	@Id
	@SequenceGenerator(name="CPASS_R_DIRIGENTE_SETTORE_DIRIGENTESETTOREID_GENERATOR", sequenceName="CPASS_R_DIRIGENTE_SETTORE_DIRIGENTE_SETTORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_DIRIGENTE_SETTORE_DIRIGENTESETTOREID_GENERATOR")
	@Column(name="dirigente_settore_id", unique=true, nullable=false)
	private Integer dirigenteSettoreId;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id")
	private CpassTUtente cpassTUtente;

	public CpassRDirigenteSettore() {
	}

	/**
	 * @return the dirigenteSettoreId
	 */
	public Integer getDirigenteSettoreId() {
		return dirigenteSettoreId;
	}

	/**
	 * @param dirigenteSettoreId the dirigenteSettoreId to set
	 */
	public void setDirigenteSettoreId(Integer dirigenteSettoreId) {
		this.dirigenteSettoreId = dirigenteSettoreId;
	}

	public Date getDataValiditaFine() {
		return this.dataValiditaFine;
	}

	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	public Date getDataValiditaInizio() {
		return this.dataValiditaInizio;
	}

	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}



	@Override
	public Integer getId() {
		return dirigenteSettoreId;
	}

	@Override
	public void setId(Integer id) {
		dirigenteSettoreId = id;
	}
}
