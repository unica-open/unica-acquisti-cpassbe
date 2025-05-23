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
 * The Class Stato.
 */
public class Permesso extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	/** The permesso tipo. */
	private String tipo;
	/** The titolo box. */
	private String titoloBox;
	/** The voce menu. */
	private Boolean voceMenu;

	private String disattivabile;

	private Boolean attivo;

	private Modulo modulo;


	/** The voce menu. */
	//private Boolean trasversale;

	/** Empty constructor */
	public Permesso() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Permesso(Long id) {
		super(id);
	}

	/**
	 * Constructor
	 * @param id the id
	 * @param codice the codice
	 * @param descrizione the descrizione
	 * @param tipo the tipo
	 * @param titoloBox the titoloBox
	 * @param voceMenu the voceMenu
	 */
	public Permesso(Long id, String codice, String descrizione, String tipo, String titoloBox, Boolean voceMenu,Boolean attivo, String disattivabile) {
		super(id);
		this.codice = codice;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.titoloBox = titoloBox;
		this.voceMenu = voceMenu;
		this.attivo = attivo;
		this.disattivabile = disattivabile;

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
	 * @return the titoloBox
	 */
	public String getTitoloBox() {
		return titoloBox;
	}

	/**
	 * @param titoloBox the titoloBox to set
	 */
	public void setTitoloBox(String titoloBox) {
		this.titoloBox = titoloBox;
	}

	/**
	 * @return the voceMenu
	 */
	public Boolean getVoceMenu() {
		return voceMenu;
	}

	/**
	 * @param voceMenu the voceMenu to set
	 */
	public void setVoceMenu(Boolean voceMenu) {
		this.voceMenu = voceMenu;
	}


	/**
	 * @return the disattivabile
	 */
	public String getDisattivabile() {
		return disattivabile;
	}

	/**
	 * @param disattivabile the disattivabile to set
	 */
	public void setDisattivabile(String disattivabile) {
		this.disattivabile = disattivabile;
	}

	/**
	 * @return the attivo
	 */
	public Boolean getAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}



	/**
	 * @return the moduloId
	 */
	public Modulo getModulo() {
		return modulo;
	}

	/**
	 * @param moduloId the moduloId to set
	 */
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Permesso [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", tipo=").append(tipo)
			.append(", titoloBox=").append(titoloBox)
			.append(", voceMenu=").append(voceMenu)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
