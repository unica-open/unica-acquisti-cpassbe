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
 * The persistent class for the cpass_t_parametro database table.
 *
 */
@Entity
@Table(name = "cpass_t_parametro")
@NamedQuery(name = "CpassTParametro.findAll", query = "SELECT c FROM CpassTParametro c")
public class CpassTParametro implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_PARAMETRO_PARAMETROID_GENERATOR", sequenceName="CPASS_T_PARAMETRO_PARAMETRO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_PARAMETRO_PARAMETROID_GENERATOR")
	@Column(name = "parametro_id")
	private Integer parametroId;

	@Column(name = "chiave")
	private String chiave;

	@Column(name = "valore")
	private String valore;

	@Column(name = "abilitata")
	private Boolean abilitata;

	@Column(name = "riferimento")
	private String riferimento;

	@Column(name = "ambiente")
	private String ambiente;

	@Column(name = "note")
	private String note;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	@Override
	public Integer getId() {
		return parametroId;
	}

	@Override
	public void setId(Integer id) {
		this.parametroId = id;
	}

	/**
	 * @return the parametroId
	 */
	public Integer getParametroId() {
		return parametroId;
	}

	/**
	 * @param parametroId the parametroId to set
	 */
	public void setParametroId(Integer parametroId) {
		this.parametroId = parametroId;
	}

	/**
	 * @return the chiave
	 */
	public String getChiave() {
		return chiave;
	}

	/**
	 * @param chiave the chiave to set
	 */
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}

	/**
	 * @return the abilitata
	 */
	public Boolean getAbilitata() {
		return abilitata;
	}

	/**
	 * @param abilitata the abilitata to set
	 */
	public void setAbilitata(Boolean abilitata) {
		this.abilitata = abilitata;
	}

	/**
	 * @return the riferimento
	 */
	public String getRiferimento() {
		return riferimento;
	}

	/**
	 * @param riferimento the riferimento to set
	 */
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	/**
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}

	/**
	 * @param ambiente the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the cpassTEnte
	 */
	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}

	/**
	 * @param cpassTEnte the cpassTEnte to set
	 */
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}


}
