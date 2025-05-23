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

/**
 * The Class Utente.
 */
public class GestioneCampo extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String nomeCampo;

	private Boolean visibile;

	private Boolean obbligatorioIns;

	private Boolean obbligatorioUpd;

	private Boolean editabile;

	private String note;

	private Ente ente;

	/**
	 * @return the nomeCampo
	 */
	public String getNomeCampo() {
		return nomeCampo;
	}

	/**
	 * @param nomeCampo the nomeCampo to set
	 */
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	/**
	 * @return the visibile
	 */
	public Boolean getVisibile() {
		return visibile;
	}

	/**
	 * @param visibile the visibile to set
	 */
	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	/**
	 * @return the obbligatorioIns
	 */
	public Boolean getObbligatorioIns() {
		return obbligatorioIns;
	}

	/**
	 * @param obbligatorioIns the obbligatorioIns to set
	 */
	public void setObbligatorioIns(Boolean obbligatorioIns) {
		this.obbligatorioIns = obbligatorioIns;
	}

	/**
	 * @return the obbligatorioUpd
	 */
	public Boolean getObbligatorioUpd() {
		return obbligatorioUpd;
	}

	/**
	 * @param obbligatorioUpd the obbligatorioUpd to set
	 */
	public void setObbligatorioUpd(Boolean obbligatorioUpd) {
		this.obbligatorioUpd = obbligatorioUpd;
	}

	/**
	 * @return the editabile
	 */
	public Boolean getEditabile() {
		return editabile;
	}

	/**
	 * @param editabile the editabile to set
	 */
	public void setEditabile(Boolean editabile) {
		this.editabile = editabile;
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

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}


}
