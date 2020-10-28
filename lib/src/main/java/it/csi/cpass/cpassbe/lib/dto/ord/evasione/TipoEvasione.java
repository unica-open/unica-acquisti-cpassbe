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

public class TipoEvasione extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer tipoEvasioneId;
	private Timestamp dataCancellazione;
	private Timestamp dataCreazione;
	private Timestamp dataModifica;
	private String tipoEvasioneCodice;
	private String tipoEvasioneDescrizione;
	private String utenteCancellazione;
	private String utenteCreazione;
	private String utenteModifica;

	/**
	 * @return the tipoEvasioneId
	 */
	public Integer getTipoEvasioneId() {
		return tipoEvasioneId;
	}

	/**
	 * @param tipoEvasioneId the tipoEvasioneId to set
	 */
	public void setTipoEvasioneId(Integer tipoEvasioneId) {
		this.tipoEvasioneId = tipoEvasioneId;
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
	 * @return the tipoEvasioneCodice
	 */
	public String getTipoEvasioneCodice() {
		return tipoEvasioneCodice;
	}

	/**
	 * @param tipoEvasioneCodice the tipoEvasioneCodice to set
	 */
	public void setTipoEvasioneCodice(String tipoEvasioneCodice) {
		this.tipoEvasioneCodice = tipoEvasioneCodice;
	}

	/**
	 * @return the tipoEvasioneDescrizione
	 */
	public String getTipoEvasioneDescrizione() {
		return tipoEvasioneDescrizione;
	}

	/**
	 * @param tipoEvasioneDescrizione the tipoEvasioneDescrizione to set
	 */
	public void setTipoEvasioneDescrizione(String tipoEvasioneDescrizione) {
		this.tipoEvasioneDescrizione = tipoEvasioneDescrizione;
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

}
