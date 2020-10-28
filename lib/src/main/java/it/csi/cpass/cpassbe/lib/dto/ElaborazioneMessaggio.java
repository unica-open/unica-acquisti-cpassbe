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

import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;

/**
 * The Class Stato.
 */
public class ElaborazioneMessaggio extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Elaborazione elaborazione;
	private String  tipo;
	private String  code;
	private String  descrizione;

	/** Default empty constructor */
	public ElaborazioneMessaggio() {}

	/** Constructor
	 * @param id the id
	 */
	public ElaborazioneMessaggio(Integer id) {
		super(id);
	}

	
	/**
	 * @return the elaborazione
	 */
	public Elaborazione getElaborazione() {
		return elaborazione;
	}

	/**
	 * @param elaborazione the elaborazione to set
	 */
	public void setElaborazione(Elaborazione elaborazione) {
		this.elaborazione = elaborazione;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = StringHelper.trimToLength(code, 100);
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
		this.descrizione = StringHelper.trimToLength(descrizione, 4000);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("ElaborazioneMessaggio [tipo=").append(tipo)
			.append(", code=").append(code)
			.append(", descrizione=").append(descrizione)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
