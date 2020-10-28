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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.sql.Timestamp;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;

public class CausaleSospensioneEvasione extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer causaleSospensioneId;
	private String causaleSospensioneCodice;
	private String causaleSospensioneDescrizione;
	private Timestamp dataCancellazione;
	private Timestamp dataCreazione;
	private Timestamp dataModifica;
	private String utenteCancellazione;
	private String utenteCreazione;
	private String utenteModifica;
	private Ente ente;
	/**
	 * @return the causaleSospensioneId
	 */
	public Integer getCausaleSospensioneId() {
		return causaleSospensioneId;
	}

	/**
	 * @param causaleSospensioneId the causaleSospensioneId to set
	 */
	public void setCausaleSospensioneId(Integer causaleSospensioneId) {
		this.causaleSospensioneId = causaleSospensioneId;
	}

	/**
	 * @return the causaleSospensioneCodice
	 */
	public String getCausaleSospensioneCodice() {
		return causaleSospensioneCodice;
	}

	/**
	 * @param causaleSospensioneCodice the causaleSospensioneCodice to set
	 */
	public void setCausaleSospensioneCodice(String causaleSospensioneCodice) {
		this.causaleSospensioneCodice = causaleSospensioneCodice;
	}

	/**
	 * @return the causaleSospensioneDescrizione
	 */
	public String getCausaleSospensioneDescrizione() {
		return causaleSospensioneDescrizione;
	}

	/**
	 * @param causaleSospensioneDescrizione the causaleSospensioneDescrizione to set
	 */
	public void setCausaleSospensioneDescrizione(String causaleSospensioneDescrizione) {
		this.causaleSospensioneDescrizione = causaleSospensioneDescrizione;
	}

	/**
	 * @return the dataCancellazione
	 */
	public Timestamp getDataCancellazione() {
		return dataCancellazione;
	}

	/**
	 * @param dataCancellazione the dataCancellazione to set
	 */
	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	/**
	 * @return the dataCreazione
	 */
	public Timestamp getDataCreazione() {
		return dataCreazione;
	}

	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	/**
	 * @return the dataModifica
	 */
	public Timestamp getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	/**
	 * @return the utenteCancellazione
	 */
	public String getUtenteCancellazione() {
		return utenteCancellazione;
	}

	/**
	 * @param utenteCancellazione the utenteCancellazione to set
	 */
	public void setUtenteCancellazione(String utenteCancellazione) {
		this.utenteCancellazione = utenteCancellazione;
	}

	/**
	 * @return the utenteCreazione
	 */
	public String getUtenteCreazione() {
		return utenteCreazione;
	}

	/**
	 * @param utenteCreazione the utenteCreazione to set
	 */
	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	/**
	 * @return the utenteModifica
	 */
	public String getUtenteModifica() {
		return utenteModifica;
	}

	/**
	 * @param utenteModifica the utenteModifica to set
	 */
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
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
