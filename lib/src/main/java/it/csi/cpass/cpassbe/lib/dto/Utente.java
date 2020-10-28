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
import java.util.UUID;

/**
 * The Class Utente.
 */
public class Utente extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nome. */
	private String nome;
	/** The cognome. */
	private String cognome;
	/** The codice fiscale. */
	private String codiceFiscale;
	/** telefono */
	private String telefono;
	/** Email */	
	private String email;
	/** Rup */	
	private Boolean rup;
	
	/** Default constructor */
	public Utente() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Utente(UUID id) {
		super(id);
	}

	/**
	 * Gets the nome.
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the cognome.
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Sets the cognome.
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Gets the codice fiscale.
	 * @return the codice fiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * Sets the codice fiscale.
	 * @param codiceFiscale the new codice fiscale
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * @return the rup
	 */
	public Boolean getRup() {
		return rup;
	}

	/**
	 * @param rup the rup to set
	 */
	public void setRup(Boolean rup) {
		this.rup = rup;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Utente [nome=").append(nome)
			.append(", cognome=").append(cognome)
			.append(", codiceFiscale=").append(codiceFiscale)
			.append(", email=").append(email)
			.append(", telefono=").append(telefono)
			.append(innerToString())
			.append("]")
			.toString();
	}

}
