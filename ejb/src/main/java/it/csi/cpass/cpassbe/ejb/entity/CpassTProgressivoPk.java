/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the cpass_t_progressivo database table.
 *
 */
@Embeddable
public class CpassTProgressivoPk implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="progressivo_tipo")
	private String progressivoTipo;

	@Column(name="progressivo_codice")
	private String progressivoCodice;

	//bi-directional many-to-one association to CpassTEnte
	/*
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;
	 */
	@Column(name="ente_id" , insertable=true, updatable=true)
	private UUID enteId;

	public CpassTProgressivoPk() {
	}
	public String getProgressivoTipo() {
		return this.progressivoTipo;
	}
	public void setProgressivoTipo(String progressivoTipo) {
		this.progressivoTipo = progressivoTipo;
	}
	public String getProgressivoCodice() {
		return this.progressivoCodice;
	}
	public void setProgressivoCodice(String progressivoCodice) {
		this.progressivoCodice = progressivoCodice;
	}

	public UUID getEnteId() {
		return this.enteId;
	}
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CpassTProgressivoPk)) {
			return false;
		}
		final CpassTProgressivoPk castOther = (CpassTProgressivoPk)other;
		return
				this.progressivoTipo.equals(castOther.progressivoTipo)
				&& this.progressivoCodice.equals(castOther.progressivoCodice)
				&& this.enteId.equals(castOther.enteId);
		//&& this.getCpassTEnte().getId().equals(castOther.getCpassTEnte().getEnteId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.progressivoTipo.hashCode();
		hash = hash * prime + this.progressivoCodice.hashCode();
		hash = hash * prime + this.enteId.hashCode();
		//hash = hash * prime + this.getCpassTEnte().getId().hashCode();
		return hash;
	}

	/*
	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}
	 */

}
