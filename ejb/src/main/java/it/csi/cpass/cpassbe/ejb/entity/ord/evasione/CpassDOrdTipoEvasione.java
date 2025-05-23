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
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_d_ord_tipo_evasione database table.
 *
 */
@Entity
@Table(name="cpass_d_ord_tipo_evasione")
@NamedQuery(name="CpassDOrdTipoEvasione.findAll", query="SELECT c FROM CpassDOrdTipoEvasione c")
public class CpassDOrdTipoEvasione implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tipo_evasione_id")
	private Integer tipoEvasioneId;

	@Column(name="data_cancellazione")
	private Date dataCancellazione;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="tipo_evasione_codice")
	private String tipoEvasioneCodice;

	@Column(name="tipo_evasione_descrizione")
	private String tipoEvasioneDescrizione;

	@Column(name="utente_cancellazione")
	private String utenteCancellazione;

	@Column(name="utente_creazione")
	private String utenteCreazione;

	@Column(name="utente_modifica")
	private String utenteModifica;

	public CpassDOrdTipoEvasione() {
	}

	public Integer getTipoEvasioneId() {
		return this.tipoEvasioneId;
	}

	public void setTipoEvasioneId(Integer tipoEvasioneId) {
		this.tipoEvasioneId = tipoEvasioneId;
	}

	public Date getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getTipoEvasioneCodice() {
		return this.tipoEvasioneCodice;
	}

	public void setTipoEvasioneCodice(String tipoEvasioneCodice) {
		this.tipoEvasioneCodice = tipoEvasioneCodice;
	}

	public String getTipoEvasioneDescrizione() {
		return this.tipoEvasioneDescrizione;
	}

	public void setTipoEvasioneDescrizione(String tipoEvasioneDescrizione) {
		this.tipoEvasioneDescrizione = tipoEvasioneDescrizione;
	}

	public String getUtenteCancellazione() {
		return this.utenteCancellazione;
	}

	public void setUtenteCancellazione(String utenteCancellazione) {
		this.utenteCancellazione = utenteCancellazione;
	}

	public String getUtenteCreazione() {
		return this.utenteCreazione;
	}

	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public String getUtenteModifica() {
		return this.utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	@Override
	public Integer getId() {
		return tipoEvasioneId;
	}

	@Override
	public void setId(Integer id) {
		tipoEvasioneId = id;
	}
}
