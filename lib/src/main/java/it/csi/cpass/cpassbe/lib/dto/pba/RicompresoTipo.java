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
 * The Class RicomprensoTipo.
 */
public class RicompresoTipo extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	/** The tipo. */
	private Boolean cuiObbligatorio;
	/** The conteggioImporti. */
	private Boolean conteggioImporti;

	/** Default constructor */
	public RicompresoTipo() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public RicompresoTipo(Integer id) {
		super(id);
	}

	/**
	 * Gets the descrizione.
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	 * @return the cuiObbligatorio
	 */
	public Boolean getCuiObbligatorio() {
		return cuiObbligatorio;
	}

	/**
	 * @param cuiObbligatorio the cuiObbligatorio to set
	 */
	public void setCuiObbligatorio(Boolean cuiObbligatorio) {
		this.cuiObbligatorio = cuiObbligatorio;
	}


	/**
	 * @return the conteggioImporti
	 */
	public Boolean getConteggioImporti() {
		return conteggioImporti;
	}

	/**
	 * @param conteggioImporti the conteggioImporti to set
	 */
	public void setConteggioImporti(Boolean conteggioImporti) {
		this.conteggioImporti = conteggioImporti;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Stato [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", cuiObbligatorio=").append(cuiObbligatorio)
			.append(", conteggioImporti=").append(conteggioImporti)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
