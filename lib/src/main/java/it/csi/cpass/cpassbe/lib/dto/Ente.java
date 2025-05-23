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
import java.util.UUID;

/**
 * The Class Ente.
 */
public class Ente extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The denominazione. */
	private String denominazione;
	private String codice;
	/** The codice fiscale. */
	private String codiceFiscale;
	private String codiceIpaAmministrazione;
	private String dipartimento;
	private String ufficio;
	private String regione;
	private String provincia;
	private String indirizzo;
	private String telefono;
	private String email;
	private String emailPEC;
	private String pathLogo;
	private String link;

	/** Default constructor */
	public Ente() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Ente(UUID id) {
		super(id);
	}

	/**
	 * Gets the denominazione.
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * Sets the denominazione.
	 * @param denominazione the new denominazione
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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
	 * @return the codiceIpaAmministrazione
	 */
	public String getCodiceIpaAmministrazione() {
		return codiceIpaAmministrazione;
	}

	/**
	 * @param codiceIpaAmministrazione the codiceIpaAmministrazione to set
	 */
	public void setCodiceIpaAmministrazione(String codiceIpaAmministrazione) {
		this.codiceIpaAmministrazione = codiceIpaAmministrazione;
	}

	/**
	 * @return the dipartimento
	 */
	public String getDipartimento() {
		return dipartimento;
	}

	/**
	 * @param dipartimento the dipartimento to set
	 */
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}

	/**
	 * @return the ufficio
	 */
	public String getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	/**
	 * @return the regione
	 */
	public String getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(String regione) {
		this.regione = regione;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
	 * @return the emailPEC
	 */
	public String getEmailPEC() {
		return emailPEC;
	}

	/**
	 * @param emailPEC the emailPEC to set
	 */
	public void setEmailPEC(String emailPEC) {
		this.emailPEC = emailPEC;
	}

	public String getPathLogo() {
		return pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Ente [denominazione=").append(denominazione)
			.append(", codice=").append(codice)
			.append(", codiceFiscale=").append(codiceFiscale)
			.append(", codiceIpaAmministrazione=").append(codiceIpaAmministrazione)
			.append(", dipartimento=").append(dipartimento)
			.append(", ufficio=").append(ufficio)
			.append(", regione=").append(regione)
			.append(", provincia=").append(provincia)
			.append(", indirizzo=").append(indirizzo)
			.append(", telefono=").append(telefono)
			.append(", email=").append(email)
			.append(", emailPEC=").append(emailPEC)
			.append(innerToString())
			.append("]")
			.toString();
	}

}
