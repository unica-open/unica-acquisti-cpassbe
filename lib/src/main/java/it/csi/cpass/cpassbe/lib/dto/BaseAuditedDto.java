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

import java.util.Date;
import java.util.UUID;

import javax.json.bind.annotation.JsonbTransient;

/**
 * Base model class
 * @param <K> the key type
 */
public abstract class BaseAuditedDto<K> extends BaseDto<K> {

	/** The data cancellazione. */
	protected Date dataCancellazione;
	/** The data creazione. */
	protected Date dataCreazione;
	/** The data modifica. */
	protected Date dataModifica;
	/** The utente cancellazione. */
	protected String utenteCancellazione;
	/** The utente creazione. */
	protected String utenteCreazione;
	/** The utente modifica. */
	protected String utenteModifica;
	/** The optlock. */
	protected UUID optlock;

	/** Base JavaBean contructor */
	protected BaseAuditedDto() {
		super(null);
	}

	/**
	 * Constructor
	 * @param id the id
	 */
	protected BaseAuditedDto(K id) {
		super(id);
	}

	/**
	 * @return the dataCancellazione
	 */
	public Date getDataCancellazione() {
		return dataCancellazione;
	}

	/**
	 * @param dataCancellazione the dataCancellazione to set
	 */
	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	/**
	 * @return the dataCreazione
	 */
	public Date getDataCreazione() {
		return dataCreazione;
	}

	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	/**
	 * @return the dataModifica
	 */
	public Date getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	/**
	 * @return the utenteCancellazione
	 */
	@JsonbTransient
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
	//@JsonbTransient
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
	@JsonbTransient
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
	 * @return the optlock
	 */
	public UUID getOptlock() {
		return optlock;
	}

	/**
	 * @param optlock the optlock to set
	 */
	public void setOptlock(UUID optlock) {
		this.optlock = optlock;
	}

	/**
	 * Internal toString data
	 * @return the toString for the inner data
	 */
	protected String innerToString() {
		return new StringBuilder()
			.append(", dataCancellazione=").append(dataCancellazione)
			.append(", dataCreazione=").append(dataCreazione)
			.append(", dataModifica=").append(dataModifica)
			.append(", utenteCancellazione=").append(utenteCancellazione)
			.append(", utenteCreazione=").append(utenteCreazione)
			.append(", utenteModifica=").append(utenteModifica)
			.append(", optlock=").append(optlock)
			.append(", id=").append(id)
			.toString();
	}
}
