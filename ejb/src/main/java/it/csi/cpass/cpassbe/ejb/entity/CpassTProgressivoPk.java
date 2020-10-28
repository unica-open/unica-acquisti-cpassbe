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
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The primary key class for the pr_t_cella database table.
 *
 */
@Embeddable
public class CpassTProgressivoPk implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 4224279620272204579L;

	@Column(name="progressivo_tipo", unique=true, nullable=false, length=200)
	private String progressivoTipo;

	@Column(name="progressivo_codice", nullable=false, length=200)
	private String progressivoCodice;


	/**
	 * Gets the progressivo tipo.
	 *
	 * @return the progressivo tipo
	 */
	public String getProgressivoTipo() {
		return this.progressivoTipo;
	}

	/**
	 * Sets the progressivo tipo.
	 *
	 * @param progressivoTipo the new progressivo tipo
	 */
	public void setProgressivoTipo(String progressivoTipo) {
		this.progressivoTipo = progressivoTipo;
	}

	/**
	 * Gets the progressivo codice.
	 *
	 * @return the progressivo codice
	 */
	public String getProgressivoCodice() {
		return progressivoCodice;
	}

	/**
	 * Sets the progressivo codice.
	 *
	 * @param progressivoCodice the new progressivo codice
	 */
	public void setProgressivoCodice(String progressivoCodice) {
		this.progressivoCodice = progressivoCodice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(progressivoCodice, progressivoTipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CpassTProgressivoPk)) {
			return false;
		}
		CpassTProgressivoPk other = (CpassTProgressivoPk) obj;
		return Objects.equals(progressivoCodice, other.progressivoCodice)
				&& Objects.equals(progressivoTipo, other.progressivoTipo);
	}

}
