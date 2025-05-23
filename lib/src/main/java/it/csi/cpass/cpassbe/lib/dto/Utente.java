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
import java.util.List;
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
	/** The nome. */
	private String msgData;
	/** Rup */
	//private Boolean rup;
	private Boolean presenteSuHr;

	private String identitaDigitale;

	private List<UtenteSettore> utenteSettores;

	private List<UtenteRupDeleghe> utenteRupDeleghes;

	/** The nome. */
	private ApiError error;

	//private List<UtenteSezione> utenteSeziones;

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
	/*
	public Boolean getRup() {
		return rup;
	}

	public void setRup(Boolean rup) {
		this.rup = rup;
	}
*/

	/**
	 * @return the identitaDigitale
	 */
	public String getIdentitaDigitale() {
		return identitaDigitale;
	}

	/**
	 * @param identitaDigitale the identitaDigitale to set
	 */
	public void setIdentitaDigitale(String identitaDigitale) {
		this.identitaDigitale = identitaDigitale;
	}



	/**
	 * @return the utenteSettores
	 */
	public List<UtenteSettore> getUtenteSettores() {
		return utenteSettores;
	}

	/**
	 * @param utenteSettores the utenteSettores to set
	 */
	public void setUtenteSettores(List<UtenteSettore> utenteSettores) {
		this.utenteSettores = utenteSettores;
	}

	/**
	 * @return the utenteRupDeleghes
	 */
	public List<UtenteRupDeleghe> getUtenteRupDeleghes() {
		return utenteRupDeleghes;
	}

	/**
	 * @param utenteRupDeleghes the utenteRupDeleghes to set
	 */
	public void setUtenteRupDeleghes(List<UtenteRupDeleghe> utenteRupDeleghes) {
		this.utenteRupDeleghes = utenteRupDeleghes;
	}


	/**
	 * @return the msgData
	 */
	public String getMsgData() {
		return msgData;
	}

	/**
	 * @param msgData the msgData to set
	 */
	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}


	/**
	 * @return the presenteSuHr
	 */
	public Boolean getPresenteSuHr() {
		return presenteSuHr;
	}

	/**
	 * @param presenteSuHr the presenteSuHr to set
	 */
	public void setPresenteSuHr(Boolean presenteSuHr) {
		this.presenteSuHr = presenteSuHr;
	}



	/**
	 * @return the error
	 */
	public ApiError getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ApiError error) {
		this.error = error;
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
