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
 * The persistent class for the cpass_t_elaborazione_parametro database table.
 *
 */
@Entity
@Table(name="cpass_t_elaborazione_parametro")
@NamedQuery(name="CpassTElaborazioneParametro.findAll", query="SELECT c FROM CpassTElaborazioneParametro c")
public class CpassTElaborazioneParametro implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="CPASS_T_ELABORAZIONE_ELABORAZIONEPARAMETROID_GENERATOR", sequenceName="CPASS_T_ELABORAZIONE_PARAMETRO_ELABORAZIONE_PARAMETRO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ELABORAZIONE_ELABORAZIONEPARAMETROID_GENERATOR")
	@Column(name="elaborazione_parametro_id")
	private Integer elaborazioneParametroId;
	
	@Column(name="elaborazione_parametro_chiave")
	private String elaborazioneParametroChiave;
	
	@Column(name="elaborazione_parametro_valore") 
	private String elaborazioneParametroValore;

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
	 * @return the elaborazioneParametroId
	 */
	public Integer getElaborazioneParametroId() {
		return elaborazioneParametroId;
	}

	/**
	 * @param elaborazioneParametroId the elaborazioneParametroId to set
	 */
	public void setElaborazioneParametroId(Integer elaborazioneParametroId) {
		this.elaborazioneParametroId = elaborazioneParametroId;
	}

	/**
	 * @return the elaborazioneParametroChiave
	 */
	public String getElaborazioneParametroChiave() {
		return elaborazioneParametroChiave;
	}

	/**
	 * @param elaborazioneParametroChiave the elaborazioneParametroChiave to set
	 */
	public void setElaborazioneParametroChiave(String elaborazioneParametroChiave) {
		this.elaborazioneParametroChiave = elaborazioneParametroChiave;
	}

	/**
	 * @return the elaborazioneParametroValore
	 */
	public String getElaborazioneParametroValore() {
		return elaborazioneParametroValore;
	}

	/**
	 * @param elaborazioneParametroValore the elaborazioneParametroValore to set
	 */
	public void setElaborazioneParametroValore(String elaborazioneParametroValore) {
		this.elaborazioneParametroValore = elaborazioneParametroValore;
	}

	@Override
	public Integer getId() {
		return elaborazioneParametroId;
	}

	@Override
	public void setId(Integer id) {
		elaborazioneParametroId = id;
	}

}
