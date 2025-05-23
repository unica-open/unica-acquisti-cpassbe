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

import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;

/**
 * The Class Stato.
 */
public class ElaborazioneParametro extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Elaborazione elaborazione;
	private String chiave;
	private String valore;

	/** Default empty constructor */
	public ElaborazioneParametro() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public ElaborazioneParametro(Integer id) {
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
	 * @return the chiave
	 */
	public String getChiave() {
		return chiave;
	}

	/**
	 * @param chiave the chiave to set
	 */
	public void setChiave(String chiave) {
		this.chiave = StringHelper.trimToLength(chiave, 50);
	}

	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = StringHelper.trimToLength(valore, 4000);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("ElaborazioneParametro [tipo=").append(", chiave=").append(chiave).append(", valore=")
				.append(valore).append(", id=").append(id).append("]").toString();
	}

}
