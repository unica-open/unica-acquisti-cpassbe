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
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;

/**
 * The Class SettoreRuoliPermessi.
 */
public class UtenteSettore extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Boolean utenteSettoreDefault;

	private List<RuoloUtenteSettore> ruoloUtenteSettores;

	private Settore settore;

	private Utente utente;

	private Date dataValiditaFine;

	private Date dataValiditaInizio;

	// da valorizzare esternamente al mapper
	private List<Sezione> seziones;

	private Boolean dirigente;

	private Boolean rup;

	/** Default constructor */
	public UtenteSettore() {}
	/**
	 * Constructor
	 * @param id the id
	 */
	public UtenteSettore(Integer id) {
		super(id);
	}

	/**
	 * @return the utenteSettoreDefault
	 */
	public Boolean getUtenteSettoreDefault() {
		return utenteSettoreDefault;
	}

	/**
	 * @param utenteSettoreDefault the utenteSettoreDefault to set
	 */
	public void setUtenteSettoreDefault(Boolean utenteSettoreDefault) {
		this.utenteSettoreDefault = utenteSettoreDefault;
	}




	/**
	 * @return the ruoloUtenteSettores
	 */
	public List<RuoloUtenteSettore> getRuoloUtenteSettores() {
		return ruoloUtenteSettores;
	}




	/**
	 * @param ruoloUtenteSettores the ruoloUtenteSettores to set
	 */
	public void setRuoloUtenteSettores(List<RuoloUtenteSettore> ruoloUtenteSettores) {
		this.ruoloUtenteSettores = ruoloUtenteSettores;
	}




	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}




	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}




	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}




	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}




	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}




	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}




	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}


	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}



	/**
	 * @return the seziones
	 */
	public List<Sezione> getSeziones() {
		return seziones;
	}

	/**
	 * @param seziones the seziones to set
	 */
	public void setSeziones(List<Sezione> seziones) {
		this.seziones = seziones;
	}


	/**
	 * @return the dirigente
	 */
	public Boolean getDirigente() {
		return dirigente;
	}


	/**
	 * @param dirigente the dirigente to set
	 */
	public void setDirigente(Boolean dirigente) {
		this.dirigente = dirigente;
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
	 * @return the valido
	 */
	public Boolean getValido() {
		if(dataValiditaFine != null && new Date().compareTo(dataValiditaFine) >= 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("UtenteSettore [settore=").append(settore)
			.append(", utente=").append(utente)
			.append("]")
			.toString();
	}


}
