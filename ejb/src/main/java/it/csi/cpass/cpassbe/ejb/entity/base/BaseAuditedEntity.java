/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Audited entity base implementation
 * 
 * @param <K> the key type
 */
@MappedSuperclass
public abstract class BaseAuditedEntity<K> implements OptlockEntity, BaseEntity<K> {

	/** The data cancellazione. */
	@Column(name = "data_cancellazione")
	private Date dataCancellazione;

	/** The data creazione. */
	@Column(name = "data_creazione", nullable = false)
	private Date dataCreazione;

	/** The data modifica. */
	@Column(name = "data_modifica", nullable = false)
	private Date dataModifica;

	/** The utente cancellazione. */
	@Column(name = "utente_cancellazione")
	private String utenteCancellazione;

	/** The utente creazione. */
	@Column(name = "utente_creazione", nullable = false)
	private String utenteCreazione;

	/** The utente modifica. */
	@Column(name = "utente_modifica", nullable = false)
	private String utenteModifica;

	/** The optlock. */
	@Column(nullable = false)
	private UUID optlock;

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

	@Override
	public UUID getOptlock() {
		return optlock;
	}

	@Override
	public void setOptlock(UUID optlock) {
		this.optlock = optlock;
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

	@Override
	public void generateNewOptlock() {
		this.optlock = UUID.randomUUID();
	}
}
