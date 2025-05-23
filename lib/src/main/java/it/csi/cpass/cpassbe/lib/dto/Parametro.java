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
 * The Class Parametro.
 */
public class Parametro extends BaseAuditedDto<String> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The chiave. */
	private String parametroId;
	/** The chiave. */
	private String chiave;
	/** The valore. */
	private String valore;
	/** The abilitata. */
	private Boolean abilitata;
	/** The riferimento. */
	private String riferimento;
	/** The ambiente. */
	private String ambiente;
	/** The note. */
	private String note;

	private Ente Ente;

	/** Default constructor */
	public Parametro() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public Parametro(String id) {
		super(id);
	}

	/**
	 * @return the parametroId
	 */
	public String getParametroId() {
		return parametroId;
	}

	/**
	 * @param parametroId the parametroId to set
	 */
	public void setParametroId(String parametroId) {
		this.parametroId = parametroId;
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
		this.chiave = chiave;
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
		this.valore = valore;
	}

	/**
	 * @return the abilitata
	 */
	public Boolean getAbilitata() {
		return abilitata;
	}

	/**
	 * @param abilitata the abilitata to set
	 */
	public void setAbilitata(Boolean abilitata) {
		this.abilitata = abilitata;
	}

	/**
	 * @return the riferimento
	 */
	public String getRiferimento() {
		return riferimento;
	}

	/**
	 * @param riferimento the riferimento to set
	 */
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	/**
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}

	/**
	 * @param ambiente the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
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
		return Ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		Ente = ente;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Parametro [chiave=").append(chiave)
				.append(", valore=").append(valore)
				.append(", abilitata=").append(abilitata)
				.append(", riferimento=").append(riferimento)
				.append(", ambiente=").append(ambiente)
				.append(", note=").append(note)
				.append(innerToString()).append("]").toString();
	}

}
