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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_progressivo database table.
 *
 */
@Entity
@Table(name="cpass_t_progressivo")
@NamedQuery(name="CpassTProgressivo.findAll", query="SELECT c FROM CpassTProgressivo c")
public class CpassTProgressivo implements Serializable, BaseEntity<CpassTProgressivoPk>  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CpassTProgressivoPk id;

	/** The progressivo numero. */
	@Version
	@Column(name="progressivo_numero", nullable=false)
	private Integer progressivoNumero;

	/**
	 * Gets the progressivo numero.
	 *
	 * @return the progressivo numero
	 */
	public Integer getProgressivoNumero() {
		return this.progressivoNumero;
	}

	/**
	 * Sets the progressivo numero.
	 *
	 * @param progressivoNumero the new progressivo numero
	 */
	public void setProgressivoNumero(Integer progressivoNumero) {
		this.progressivoNumero = progressivoNumero;
	}

	@Override
	public CpassTProgressivoPk getId() {
		return this.id;
	}

	@Override
	public void setId(CpassTProgressivoPk id) {
		this.id = id;
	}

	@Override
	public void initId() {
		// Do nothing
	}

}
