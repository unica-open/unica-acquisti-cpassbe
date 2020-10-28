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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;

/**
 * The persistent class for the cpass_t_comunicazione database table.
 *
 */
@Entity
@Table(name="cpass_t_comunicazione")
@NamedQuery(name="CpassTComunicazione.findAll", query="SELECT c FROM CpassTComunicazione c")
public class CpassTComunicazione extends BaseAuditedEntity<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The comunicazione id. */
	@Id
	@SequenceGenerator(name="CPASS_T_COMUNICAZIONE_COMUNICAZIONEID_GENERATOR", sequenceName="CPASS_T_COMUNICAZIONE_COMUNICAZIONE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_COMUNICAZIONE_COMUNICAZIONEID_GENERATOR")
	@Column(name="comunicazione_id")
	private Integer comunicazioneId;

	/** The comunicazione testo. */
	@Column(name="comunicazione_testo")
	private String comunicazioneTesto;

	/** The comunicazione data fine. */
	@Column(name="comunicazione_data_fine")
	private Date comunicazioneDataFine;

	/** The comunicazione data inizio. */
	@Column(name="comunicazione_data_inizio")
	private Date comunicazioneDataInizio;

	/** The comunicazione tipo. */
	@Column(name="comunicazione_tipo")
	private String comunicazioneTipo;

	/**
	 * Gets the comunicazione id.
	 *
	 * @return the comunicazione id
	 */
	public Integer getComunicazioneId() {
		return this.comunicazioneId;
	}

	/**
	 * Sets the comunicazione id.
	 *
	 * @param comunicazioneId the new comunicazione id
	 */
	public void setComunicazioneId(Integer comunicazioneId) {
		this.comunicazioneId = comunicazioneId;
	}

	/**
	 * Gets the comunicazioneTesto.
	 *
	 * @return the comunicazione testo
	 */
	public String getComunicazioneTesto() {
		return this.comunicazioneTesto;
	}

	/**
	 * Sets the comunicazione testo.
	 *
	 * @param comunicazioneTesto the new comunicazione testo
	 */
	public void setComunicazioneTesto(String comunicazioneTesto) {
		this.comunicazioneTesto = comunicazioneTesto;
	}


	/**
	 * Gets the comunicazione data fine.
	 *
	 * @return the comunicazione data fine
	 */
	public Date getComunicazioneDataFine() {
		return this.comunicazioneDataFine;
	}

	/**
	 * Sets the comunicazione data fine.
	 *
	 * @param comunicazioneDataFine the new comunicazione data fine
	 */
	public void setComunicazioneDataFine(Date comunicazioneDataFine) {
		this.comunicazioneDataFine = comunicazioneDataFine;
	}

	/**
	 * Gets the comunicazione data inizio.
	 *
	 * @return the comunicazione data inizio
	 */
	public Date getComunicazioneDataInizio() {
		return this.comunicazioneDataInizio;
	}

	/**
	 * Sets the comunicazione data inizio.
	 *
	 * @param comunicazioneDataInizio the new comunicazione data inizio
	 */
	public void setComunicazioneDataInizio(Date comunicazioneDataInizio) {
		this.comunicazioneDataInizio = comunicazioneDataInizio;
	}

	/**
	 * Gets the comunicazione tipo.
	 *
	 * @return the comunicazione tipo
	 */
	public String getComunicazioneTipo() {
		return this.comunicazioneTipo;
	}

	/**
	 * Sets the comunicazione tipo.
	 *
	 * @param comunicazioneTipo the new comunicazione tipo
	 */
	public void setComunicazioneTipo(String comunicazioneTipo) {
		this.comunicazioneTipo = comunicazioneTipo;
	}

	@Override
	public Integer getId() {
		return comunicazioneId;
	}

	@Override
	public void setId(Integer id) {
		comunicazioneId = id;
	}

}
