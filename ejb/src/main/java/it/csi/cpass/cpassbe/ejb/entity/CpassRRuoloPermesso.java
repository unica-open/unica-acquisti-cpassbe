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
 * The persistent class for the cpass_r_ruolo_permesso database table.
 *
 */
@Entity
@Table(name="cpass_r_ruolo_permesso")
@NamedQuery(name="CpassRRuoloPermesso.findAll", query="SELECT c FROM CpassRRuoloPermesso c")
public class CpassRRuoloPermesso implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ruolo permesso id. */
	@Id
	@SequenceGenerator(name="CPASS_R_RUOLO_PERMESSO_RUOLOPERMESSOID_GENERATOR", sequenceName="CPASS_R_RUOLO_PERMESSO_RUOLO_PERMESSO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_RUOLO_PERMESSO_RUOLOPERMESSOID_GENERATOR")
	@Column(name="ruolo_permesso_id", unique=true, nullable=false)
	private Integer ruoloPermessoId;

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
	 * Gets the ruolo permesso id.
	 *
	 * @return the ruolo permesso id
	 */
	public Integer getRuoloPermessoId() {
		return this.ruoloPermessoId;
	}

	/**
	 * Sets the ruolo permesso id.
	 *
	 * @param ruoloPermessoId the new ruolo permesso id
	 */
	public void setRuoloPermessoId(Integer ruoloPermessoId) {
		this.ruoloPermessoId = ruoloPermessoId;
	}

	/**
	 * Gets the cpass D permesso.
	 *
	 * @return the cpass D permesso
	 */
	public CpassDPermesso getCpassDPermesso() {
		return this.cpassDPermesso;
	}

	/**
	 * Sets the cpass D permesso.
	 *
	 * @param cpassDPermesso the new cpass D permesso
	 */
	public void setCpassDPermesso(CpassDPermesso cpassDPermesso) {
		this.cpassDPermesso = cpassDPermesso;
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

	@Override
	public Integer getId() {
		return ruoloPermessoId;
	}

	@Override
	public void setId(Integer id) {
		ruoloPermessoId = id;
	}

}
