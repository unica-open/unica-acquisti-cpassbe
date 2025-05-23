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
 * The persistent class for the cpass_r_utente_rup_settore database table.
 *
 */
@Entity
@Table(name="cpass_t_utente_rup_deleghe ")
@NamedQuery(name="CpassTUtenteRupDeleghe.findAll", query="SELECT c FROM CpassTUtenteRupDeleghe c")
public class CpassTUtenteRupDeleghe implements Serializable, BaseEntity<Integer> {


	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="CPASS_T_UTENTE_RUP_DELEGHE_UTENTERUPDELEGHEID_GENERATOR", sequenceName="CPASS_T_UTENTE_RUP_DELEGHE_UTENTE_RUP_DELEGHE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_UTENTE_RUP_DELEGHE_UTENTERUPDELEGHEID_GENERATOR")

	@Column(name="utente_rup_deleghe_id", unique=true, nullable=false)
	private Integer utenteRupDelegheId;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;


	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_rup_id")
	private CpassTUtente cpassTUtenteRup;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_rup_delegato_id ")
	private CpassTUtente cpassTUtenteRupDelegato;

	/**
	 * @return the utenteRupDelegheId
	 */
	public Integer getUtenteRupDelegheId() {
		return utenteRupDelegheId;
	}


	/**
	 * @param utenteRupDelegheId the utenteRupDelegheId to set
	 */
	public void setUtenteRupDelegheId(Integer utenteRupDelegheId) {
		this.utenteRupDelegheId = utenteRupDelegheId;
	}


	/**
	 * @return the cpassTUtenteRup
	 */
	public CpassTUtente getCpassTUtenteRup() {
		return cpassTUtenteRup;
	}


	/**
	 * @param cpassTUtenteRup the cpassTUtenteRup to set
	 */
	public void setCpassTUtenteRup(CpassTUtente cpassTUtenteRup) {
		this.cpassTUtenteRup = cpassTUtenteRup;
	}


	/**
	 * @return the cpassTUtenteRupDelegato
	 */
	public CpassTUtente getCpassTUtenteRupDelegato() {
		return cpassTUtenteRupDelegato;
	}


	/**
	 * @param cpassTUtenteRupDelegato the cpassTUtenteRupDelegato to set
	 */
	public void setCpassTUtenteRupDelegato(CpassTUtente cpassTUtenteRupDelegato) {
		this.cpassTUtenteRupDelegato = cpassTUtenteRupDelegato;
	}

	public CpassTUtenteRupDeleghe() {
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




	@Override
	public Integer getId() {
		return utenteRupDelegheId;
	}

	@Override
	public void setId(Integer id) {
		utenteRupDelegheId = id;
	}



}
