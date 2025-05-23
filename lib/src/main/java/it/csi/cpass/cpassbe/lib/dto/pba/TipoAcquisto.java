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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class ModalitaAffidamento.
 */
public class TipoAcquisto extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String  codice;
	private String  descrizione;
	private Boolean flgdefault;



	/** Default constructor */
	public TipoAcquisto() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public TipoAcquisto(Integer id) {
		super(id);
	}

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
	 * @return the flgdefault
	 */
	public Boolean getFlgdefault() {
		return flgdefault;
	}

	/**
	 * @param flgdefault the flgdefault to set
	 */
	public void setFlgdefault(Boolean flgdefault) {
		this.flgdefault = flgdefault;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("AcquistoVariato [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", flgdefault=").append(flgdefault)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
