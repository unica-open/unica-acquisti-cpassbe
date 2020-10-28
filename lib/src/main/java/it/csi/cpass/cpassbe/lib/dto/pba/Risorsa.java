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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class Risorsa.
 */
public class Risorsa extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descrizione. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	/** The risorsaTipo. */
	private String tipo;
	/** risorsa_tag_trasmissione*/
	private String tagTrasmissione;
	
	/** ordinamento */
	private Integer ordinamento;
	
	/** Default constructor */
	public Risorsa() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Risorsa(Integer id) {
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

	
	/**
	 * @return the tagTrasmissione
	 */
	public String getTagTrasmissione() {
		return tagTrasmissione;
	}

	/**
	 * @param tagTrasmissione the tagTrasmissione to set
	 */
	public void setTagTrasmissione(String tagTrasmissione) {
		this.tagTrasmissione = tagTrasmissione;
	}
	
	
	/**
	 * @return the ordinamento
	 */
	public Integer getOrdinamento() {
		return ordinamento;
	}

	/**
	 * @param ordinamento the ordinamento to set
	 */
	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}
	

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Risorsa [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", risorsaTipo=").append(tipo)
			.append(", tagTrasmissione=").append(tagTrasmissione)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
