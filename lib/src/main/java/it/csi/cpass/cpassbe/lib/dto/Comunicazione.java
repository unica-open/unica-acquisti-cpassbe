/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class Comunicazione.
 */
public class Comunicazione extends BaseAuditedDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The comunicazione. */
	private String testo;
	/** The data inizio. */
	private Date dataInizio;
	/** The data fine. */
	private Date dataFine;
	/** The tipo. */
	private String tipo;

	/** Default constructor */
	public Comunicazione() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Comunicazione(Long id) {
		super(id);
	}

	/**
	 * @return the testo
	 */
	public String getTesto() {
		return testo;
	}

	/**
	 * @param testo the testo to set
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/**
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}

	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}

	/**
	 * @param dataFine the dataFine to set
	 */
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Comunicazione [testo=").append(testo)
			.append(", dataInizio=").append(dataInizio)
			.append(", dataFine=").append(dataFine)
			.append(", tipo=").append(tipo)
			.append(innerToString())
			.append("]")
			.toString();
	}

}
