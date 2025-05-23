/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
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

@Entity
@Table(name="cpass_r_modulo_ruolo_permesso")
@NamedQuery(name="CpassRModuloRuoloPermesso.findAll", query="SELECT c FROM CpassRModuloRuoloPermesso c")
public class CpassRModuloRuoloPermesso implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ruolo permesso id. */
	@Id
	@SequenceGenerator(name="CPASS_R_MODULO_RUOLO_PERMESSO_MODULORUOLOPERMESSOID_GENERATOR", sequenceName="CPASS_R_MODULO_RUOLO_PERMESSO_MODULO_RUOLO_PERMESSO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_MODULO_RUOLO_PERMESSO_MODULORUOLOPERMESSOID_GENERATOR")
	@Column(name="modulo_ruolo_permesso_id", unique=true, nullable=false)
	private Integer moduloruoloPermessoId;

	private String note;
	/** The cpass D permesso. */
	//bi-directional many-to-one association to CpassDPermesso
	@ManyToOne
	@JoinColumn(name="modulo_id", nullable=false)
	private CpassDModulo cpassDModulo;

	/** The cpass D permesso. */
	//bi-directional many-to-one association to CpassDPermesso
	@ManyToOne
	@JoinColumn(name="permesso_id", nullable=false)
	private CpassDPermesso cpassDPermesso;

	/** The cpass D ruolo. */
	//bi-directional many-to-one association to CpassDRuolo
	@ManyToOne
	@JoinColumn(name="ruolo_id", nullable=false)
	private CpassDRuolo cpassDRuolo;

	/**
	 * @return the moduloruoloPermessoId
	 */
	public Integer getModuloruoloPermessoId() {
		return moduloruoloPermessoId;
	}

	/**
	 * @param moduloruoloPermessoId the moduloruoloPermessoId to set
	 */
	public void setModuloruoloPermessoId(Integer moduloruoloPermessoId) {
		this.moduloruoloPermessoId = moduloruoloPermessoId;
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
	 * @return the cpassDModulo
	 */
	public CpassDModulo getCpassDModulo() {
		return cpassDModulo;
	}

	/**
	 * @param cpassDModulo the cpassDModulo to set
	 */
	public void setCpassDModulo(CpassDModulo cpassDModulo) {
		this.cpassDModulo = cpassDModulo;
	}

	/**
	 * @return the cpassDPermesso
	 */
	public CpassDPermesso getCpassDPermesso() {
		return cpassDPermesso;
	}

	/**
	 * @param cpassDPermesso the cpassDPermesso to set
	 */
	public void setCpassDPermesso(CpassDPermesso cpassDPermesso) {
		this.cpassDPermesso = cpassDPermesso;
	}

	/**
	 * @return the cpassDRuolo
	 */
	public CpassDRuolo getCpassDRuolo() {
		return cpassDRuolo;
	}

	/**
	 * @param cpassDRuolo the cpassDRuolo to set
	 */
	public void setCpassDRuolo(CpassDRuolo cpassDRuolo) {
		this.cpassDRuolo = cpassDRuolo;
	}

	@Override
	public Integer getId() {
		return moduloruoloPermessoId;
	}

	@Override
	public void setId(Integer id) {
		moduloruoloPermessoId = id;
	}



}
