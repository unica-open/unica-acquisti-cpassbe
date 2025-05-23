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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the cpass_d_ausa database table.
 *
 */
@Entity
@Table(name="cpass_t_elaborazione_messaggio")
@NamedQuery(name="CpassTElaborazioneMessaggio.findAll", query="SELECT c FROM CpassTElaborazioneMessaggio c")
public class CpassTElaborazioneMessaggio implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ausa id. */
	@Id
	@SequenceGenerator(name="CPASS_T_ELABORAZIONE_ELABORAZIONEMESSAGGIOID_GENERATOR", sequenceName="CPASS_T_ELABORAZIONE_MESSAGGIO_ELABORAZIONE_MESSAGGIO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ELABORAZIONE_ELABORAZIONEMESSAGGIOID_GENERATOR")
	@Column(name="elaborazione_messaggio_id")
	private Integer elaborazioneMessaggioId;

	@Column(name="elaborazione_messaggio_tipo")
	private String elaborazioneMessaggioTipo;

	@Column(name="elaborazione_messaggio_code")
	private String elaborazioneMessaggioCode;

	@Column(name="elaborazione_messaggio_descrizione")
	private String elaborazioneMessaggioDescrizione;

	//bi-directional many-to-one association to CpassTElaborazione
	@ManyToOne
	@JoinColumn(name="elaborazione_id", nullable=false)
	private CpassTElaborazione cpassTElaborazione;

	/**
	 * @return the cpassTElaborazione
	 */
	public CpassTElaborazione getCpassTElaborazione() {
		return cpassTElaborazione;
	}

	/**
	 * @param cpassTElaborazione the cpassTElaborazione to set
	 */
	public void setCpassTElaborazione(CpassTElaborazione cpassTElaborazione) {
		this.cpassTElaborazione = cpassTElaborazione;
	}

	/**
	 * @return the elaborazioneMessaggioId
	 */
	public Integer getElaborazioneMessaggioId() {
		return elaborazioneMessaggioId;
	}

	/**
	 * @param elaborazioneMessaggioId the elaborazioneMessaggioId to set
	 */
	public void setElaborazioneMessaggioId(Integer elaborazioneMessaggioId) {
		this.elaborazioneMessaggioId = elaborazioneMessaggioId;
	}

	/**
	 * @return the elaborazioneMessaggioTipo
	 */
	public String getElaborazioneMessaggioTipo() {
		return elaborazioneMessaggioTipo;
	}

	/**
	 * @param elaborazioneMessaggioTipo the elaborazioneTipo to set
	 */
	public void setElaborazioneMessaggioTipo(String elaborazioneMessaggioTipo) {
		this.elaborazioneMessaggioTipo = elaborazioneMessaggioTipo;
	}

	/**
	 * @return the elaborazioneMessaggioCode
	 */
	public String getElaborazioneMessaggioCode() {
		return elaborazioneMessaggioCode;
	}

	/**
	 * @param elaborazioneMessaggioCode the elaborazioneMessaggioCode to set
	 */
	public void setElaborazioneMessaggioCode(String elaborazioneMessaggioCode) {
		this.elaborazioneMessaggioCode = elaborazioneMessaggioCode;
	}

	/**
	 * @return the elaborazioneMessaggioDescrizione
	 */
	public String getElaborazioneMessaggioDescrizione() {
		return elaborazioneMessaggioDescrizione;
	}

	/**
	 * @param elaborazioneMessaggioDescrizione the elaborazioneMessaggioDescrizione to set
	 */
	public void setElaborazioneMessaggioDescrizione(String elaborazioneMessaggioDescrizione) {
		this.elaborazioneMessaggioDescrizione = elaborazioneMessaggioDescrizione;
	}

	@Override
	public Integer getId() {
		return elaborazioneMessaggioId;
	}

	@Override
	public void setId(Integer id) {
		elaborazioneMessaggioId = id;
	}

}
