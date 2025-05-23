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
 * The persistent class for the cpass_r_utente_settore database table.
 *
 */
@Entity
@Table(name="cpass_r_utente_settore")
@NamedQuery(name="CpassRUtenteSettore.findAll", query="SELECT c FROM CpassRUtenteSettore c")
public class CpassRUtenteSettore implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The utente settore id. */
	@Id
	@SequenceGenerator(name="CPASS_R_UTENTE_SETTORE_UTENTESETTOREID_GENERATOR", sequenceName="CPASS_R_UTENTE_SETTORE_UTENTE_SETTORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_UTENTE_SETTORE_UTENTESETTOREID_GENERATOR")
	@Column(name="utente_settore_id", unique=true, nullable=false)
	private Integer utenteSettoreId;

	/** The utente settore default. */
	@Column(name="utente_settore_default")
	private Boolean utenteSettoreDefault;

	/** The cpass R ruolo utente settores. */
	//bi-directional many-to-one association to CpassRRuoloUtenteSettore
	@OneToMany(mappedBy="cpassRUtenteSettore")
	private List<CpassRRuoloUtenteSettore> cpassRRuoloUtenteSettores;

	/** The cpass T settore. */
	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id", nullable=false)
	private CpassTSettore cpassTSettore;

	/** The cpass T utente. */
	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id", nullable=false)
	private CpassTUtente cpassTUtente;

	/** The data validita fine. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	/** The data validita inizio. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;


	/** The cpass T utente. */
	//bi-directional many-to-one association to CpassTUtente
	//@ManyToOne
	//@JoinColumn(name="utente_rup_id", nullable=false)
	//private CpassTUtente cpassTRupUtente;

	/**
	 * Gets the utente settore id.
	 *
	 * @return the utente settore id
	 */
	public Integer getUtenteSettoreId() {
		return this.utenteSettoreId;
	}

	/**
	 * Sets the utente settore id.
	 *
	 * @param utenteSettoreId the new utente settore id
	 */
	public void setUtenteSettoreId(Integer utenteSettoreId) {
		this.utenteSettoreId = utenteSettoreId;
	}

	/**
	 * Gets the cpass R ruolo utente settores.
	 *
	 * @return the cpass R ruolo utente settores
	 */
	public List<CpassRRuoloUtenteSettore> getCpassRRuoloUtenteSettores() {
		return this.cpassRRuoloUtenteSettores;
	}

	/**
	 * Sets the cpass R ruolo utente settores.
	 *
	 * @param cpassRRuoloUtenteSettores the new cpass R ruolo utente settores
	 */
	public void setCpassRRuoloUtenteSettores(List<CpassRRuoloUtenteSettore> cpassRRuoloUtenteSettores) {
		this.cpassRRuoloUtenteSettores = cpassRRuoloUtenteSettores;
	}

	/**
	 * Adds the cpass R ruolo utente settore.
	 *
	 * @param cpassRRuoloUtenteSettore the cpass R ruolo utente settore
	 * @return the cpass R ruolo utente settore
	 */
	public CpassRRuoloUtenteSettore addCpassRRuoloUtenteSettore(CpassRRuoloUtenteSettore cpassRRuoloUtenteSettore) {
		getCpassRRuoloUtenteSettores().add(cpassRRuoloUtenteSettore);
		cpassRRuoloUtenteSettore.setCpassRUtenteSettore(this);

		return cpassRRuoloUtenteSettore;
	}

	/**
	 * Removes the cpass R ruolo utente settore.
	 *
	 * @param cpassRRuoloUtenteSettore the cpass R ruolo utente settore
	 * @return the cpass R ruolo utente settore
	 */
	public CpassRRuoloUtenteSettore removeCpassRRuoloUtenteSettore(CpassRRuoloUtenteSettore cpassRRuoloUtenteSettore) {
		getCpassRRuoloUtenteSettores().remove(cpassRRuoloUtenteSettore);
		cpassRRuoloUtenteSettore.setCpassRUtenteSettore(null);

		return cpassRRuoloUtenteSettore;
	}

	/**
	 * Gets the cpass T settore.
	 *
	 * @return the cpass T settore
	 */
	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	/**
	 * Sets the cpass T settore.
	 *
	 * @param cpassTSettore the new cpass T settore
	 */
	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	/**
	 * Gets the cpass T utente.
	 *
	 * @return the cpass T utente
	 */
	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	/**
	 * Sets the cpass T utente.
	 *
	 * @param cpassTUtente the new cpass T utente
	 */
	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	/**
	 * Gets the utente settore default.
	 *
	 * @return the utente settore default
	 */
	public Boolean getUtenteSettoreDefault() {
		return this.utenteSettoreDefault;
	}

	/**
	 * Sets the utente settore default.
	 *
	 * @param utenteSettoreDefault the new utente settore default
	 */
	public void setUtenteSettoreDefault(Boolean utenteSettoreDefault) {
		this.utenteSettoreDefault = utenteSettoreDefault;
	}


	/**
	public CpassTUtente getCpassTRupUtente() {
		return cpassTRupUtente;
	}

	public void setCpassTRupUtente(CpassTUtente cpassTRupUtente) {
		this.cpassTRupUtente = cpassTRupUtente;
	}
	 */

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
		return utenteSettoreId;
	}

	@Override
	public void setId(Integer id) {
		utenteSettoreId = id;
	}

}
