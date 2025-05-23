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
 * The persistent class for the cpass_r_ruolo_modulo database table.
 *
 */
@Entity
@Table(name="cpass_r_ruolo_modulo")
@NamedQuery(name="CpassRRuoloModulo.findAll", query="SELECT c FROM CpassRRuoloModulo c")
public class CpassRRuoloModulo implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ruolo modulo id. */
	@Id
	@SequenceGenerator(name="CPASS_R_RUOLO_MODULO_RUOLOMODULOID_GENERATOR", sequenceName="CPASS_R_RUOLO_MODULO_RUOLO_MODULO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_RUOLO_MODULO_RUOLOMODULOID_GENERATOR")
	@Column(name="ruolo_modulo_id", unique=true, nullable=false)
	private Integer ruoloModuloId;

	/** The cpass D modulo. */
	//bi-directional many-to-one association to CpassDModulo
	@ManyToOne
	@JoinColumn(name="modulo_id", nullable=false)
	private CpassDModulo cpassDModulo;

	/** The cpass D ruolo. */
	//bi-directional many-to-one association to CpassDRuolo
	@ManyToOne
	@JoinColumn(name="ruolo_id", nullable=false)
	private CpassDRuolo cpassDRuolo;


	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	/**
	 * Gets the ruolo modulo id.
	 *
	 * @return the ruolo modulo id
	 */
	public Integer getRuoloModuloId() {
		return this.ruoloModuloId;
	}

	/**
	 * Sets the ruolo modulo id.
	 *
	 * @param ruoloModuloId the new ruolo modulo id
	 */
	public void setRuoloModuloId(Integer ruoloModuloId) {
		this.ruoloModuloId = ruoloModuloId;
	}

	/**
	 * Gets the cpass D modulo.
	 *
	 * @return the cpass D modulo
	 */
	public CpassDModulo getCpassDModulo() {
		return this.cpassDModulo;
	}

	/**
	 * Sets the cpass D modulo.
	 *
	 * @param cpassDModulo the new cpass D modulo
	 */
	public void setCpassDModulo(CpassDModulo cpassDModulo) {
		this.cpassDModulo = cpassDModulo;
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

	@Override
	public Integer getId() {
		return ruoloModuloId;
	}

	@Override
	public void setId(Integer id) {
		ruoloModuloId = id;
	}

}
