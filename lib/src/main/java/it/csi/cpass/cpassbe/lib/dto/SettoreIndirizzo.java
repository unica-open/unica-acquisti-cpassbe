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

import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;


public class SettoreIndirizzo extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String descrizione;
	private String indirizzo;
	private String numCivico;
	private String localita;
	private String provincia;
	private String cap;
	private String contatto;
	private String email;
	private String telefono;
	private Settore settore;
	private String indirizzoCodice;
	private Boolean principale;
	private Boolean esternoEnte;
	private Magazzino magazzino;

	/** Default constructor */
	public SettoreIndirizzo() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public SettoreIndirizzo(Integer id) {
		super(id);
	}


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumCivico() {
		return numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getContatto() {
		return contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	/**
	 * @return the indirizzoCodice
	 */
	public String getIndirizzoCodice() {
		return indirizzoCodice;
	}

	/**
	 * @param codice the indirizzoCodice to set
	 */
	public void setIndirizzoCodice(String indirizzoCodice) {
		this.indirizzoCodice = indirizzoCodice;
	}

	/**
	 * @return the principale
	 */
	public Boolean getPrincipale() {
		return principale;
	}

	/**
	 * @param principale the principale to set
	 */
	public void setPrincipale(Boolean principale) {
		this.principale = principale;
	}

	/**
	 * @return the esternoEnte
	 */
	public Boolean getEsternoEnte() {
		return esternoEnte;
	}

	/**
	 * @param esternoEnte the esternoEnte to set
	 */
	public void setEsternoEnte(Boolean esternoEnte) {
		this.esternoEnte = esternoEnte;
	}

	/**
	 * @return the magazzino
	 */
	public Magazzino getMagazzino() {
		return magazzino;
	}

	/**
	 * @param magazzino the magazzino to set
	 */
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}

}
