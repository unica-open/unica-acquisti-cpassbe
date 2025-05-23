/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AliquoteIva  extends BaseAuditedDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Default constructor */
	public AliquoteIva() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public AliquoteIva(Integer id) {
		super(id);
	}

	private String codice;

	private String descrizione;

	private String codificaPeppol;

	private BigDecimal percentuale;

	private Date dataValiditaFine;

	private Date dataValiditaInizio;

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the codificaPeppol
	 */
	public String getCodificaPeppol() {
		return codificaPeppol;
	}

	/**
	 * @param codificaPeppol the codificaPeppol to set
	 */
	public void setCodificaPeppol(String codificaPeppol) {
		this.codificaPeppol = codificaPeppol;
	}

	/**
	 * @return the percentuale
	 */
	public BigDecimal getPercentuale() {
		return percentuale;
	}

	/**
	 * @param percentuale the percentuale to set
	 */
	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}


	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("AliquotaIva [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", codificaPeppol=").append(codificaPeppol)
			.append(", percentuale=").append(percentuale)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}
}
