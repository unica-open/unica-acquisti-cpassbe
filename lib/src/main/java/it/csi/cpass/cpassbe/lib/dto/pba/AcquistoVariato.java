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
 * The Class ModalitaAffidamento.
 */
public class AcquistoVariato extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String codice;
	private String descrizione;
	private String descrizioneEstesa;

	/** Default constructor */
	public AcquistoVariato() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public AcquistoVariato(Integer id) {
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
	 * @return the descrizioneEstesa
	 */
	public String getDescrizioneEstesa() {
		return descrizioneEstesa;
	}

	/**
	 * @param descrizioneEstesa the descrizioneEstesa to set
	 */
	public void setDescrizioneEstesa(String descrizioneEstesa) {
		this.descrizioneEstesa = descrizioneEstesa;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("AcquistoVariato [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", descrizioneEstesa=").append(descrizioneEstesa)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
