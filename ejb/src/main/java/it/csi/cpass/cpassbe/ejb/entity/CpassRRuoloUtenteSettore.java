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
 * The persistent class for the cpass_r_ruolo_utente_settore database table.
 *
 */
@Entity
@Table(name="cpass_r_ruolo_utente_settore")
@NamedQuery(name="CpassRRuoloUtenteSettore.findAll", query="SELECT c FROM CpassRRuoloUtenteSettore c")
public class CpassRRuoloUtenteSettore implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ruolo utente settore id. */
	@Id
	@SequenceGenerator(name="CPASS_R_RUOLO_UTENTE_SETTORE_RUOLOUTENTESETTOREID_GENERATOR", sequenceName="CPASS_R_RUOLO_UTENTE_SETTORE_RUOLO_UTENTE_SETTORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_RUOLO_UTENTE_SETTORE_RUOLOUTENTESETTOREID_GENERATOR")
	@Column(name="ruolo_utente_settore_id", unique=true, nullable=false)
	private Integer ruoloUtenteSettoreId;

	/** The cpass D ruolo. */
	//bi-directional many-to-one association to CpassDRuolo
	@ManyToOne
	@JoinColumn(name="ruolo_id", nullable=false)
	private CpassDRuolo cpassDRuolo;

	/** The cpass R utente settore. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@ManyToOne
	@JoinColumn(name="utente_settore_id", nullable=false)
	private CpassRUtenteSettore cpassRUtenteSettore;

	/** The data validita fine. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;
	
	/** The data validita inizio. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	
	/**
	 * Gets the ruolo utente settore id.
	 *
	 * @return the ruolo utente settore id
	 */
	public Integer getRuoloUtenteSettoreId() {
		return this.ruoloUtenteSettoreId;
	}

	/**
	 * Sets the ruolo utente settore id.
	 *
	 * @param ruoloUtenteSettoreId the new ruolo utente settore id
	 */
	public void setRuoloUtenteSettoreId(Integer ruoloUtenteSettoreId) {
		this.ruoloUtenteSettoreId = ruoloUtenteSettoreId;
	}

	/**
	 * Gets the cpass D ruolo.
	 *
	 * @return the cpass D ruolo
	 */
	public CpassDRuolo getCpassDRuolo() {
		return this.cpassDRuolo;
	}

	/**
	 * Sets the cpass D ruolo.
	 *
	 * @param cpassDRuolo the new cpass D ruolo
	 */
	public void setCpassDRuolo(CpassDRuolo cpassDRuolo) {
		this.cpassDRuolo = cpassDRuolo;
	}

	/**
	 * Gets the cpass R utente settore.
	 *
	 * @return the cpass R utente settore
	 */
	public CpassRUtenteSettore getCpassRUtenteSettore() {
		return this.cpassRUtenteSettore;
	}

	/**
	 * Sets the cpass R utente settore.
	 *
	 * @param cpassRUtenteSettore the new cpass R utente settore
	 */
	public void setCpassRUtenteSettore(CpassRUtenteSettore cpassRUtenteSettore) {
		this.cpassRUtenteSettore = cpassRUtenteSettore;
	}

	
	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	@Override
	public Integer getId() {
		return ruoloUtenteSettoreId;
	}

	@Override
	public void setId(Integer id) {
		ruoloUtenteSettoreId = id;
	}

}
