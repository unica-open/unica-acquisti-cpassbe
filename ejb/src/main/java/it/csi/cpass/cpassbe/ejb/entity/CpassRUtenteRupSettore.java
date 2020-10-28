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
import java.util.Date;

import javax.persistence.*;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_r_utente_rup_settore database table.
 * 
 */
@Entity
@Table(name="cpass_r_utente_rup_settore")
@NamedQuery(name="CpassRUtenteRupSettore.findAll", query="SELECT c FROM CpassRUtenteRupSettore c")
public class CpassRUtenteRupSettore implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	/** The utente settore id. */
	@Id
	@SequenceGenerator(name="CPASS_R_UTENTE_RUP_SETTORE_UTENTERUPSETTOREID_GENERATOR", sequenceName="CPASS_R_UTENTE_RUP_SETTORE_UTENTE_RUP_SETTORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_UTENTE_RUP_SETTORE_UTENTERUPSETTOREID_GENERATOR")
	@Column(name="utente_rup_settore_id", unique=true, nullable=false)
	private Integer utenteRupSettoreId;

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

	public CpassRUtenteRupSettore() {
	}

	public Integer getUtenteRupSettoreId() {
		return this.utenteRupSettoreId;
	}

	public void setUtenteRupSettoreId(Integer utenteRupSettoreId) {
		this.utenteRupSettoreId = utenteRupSettoreId;
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
		return utenteRupSettoreId;
	}

	@Override
	public void setId(Integer id) {
		utenteRupSettoreId = id;
	}
}
