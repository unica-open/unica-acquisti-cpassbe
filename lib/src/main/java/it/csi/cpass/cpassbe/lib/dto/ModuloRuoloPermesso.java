/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

public class ModuloRuoloPermesso extends BaseDto<Integer> implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Modulo modulo;
	private Ruolo ruolo;
	private Permesso permesso;
	private String note;

	/**
	 * Constructor
	 * @param id the id
	 */
	public ModuloRuoloPermesso(Integer id) {
		super(id);
	}

	public ModuloRuoloPermesso() {
	}

	/**
	 * @return the modulo
	 */
	public Modulo getModulo() {
		return modulo;
	}

	/**
	 * @param modulo the modulo to set
	 */
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the permesso
	 */
	public Permesso getPermesso() {
		return permesso;
	}

	/**
	 * @param permesso the permesso to set
	 */
	public void setPermesso(Permesso permesso) {
		this.permesso = permesso;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


}
