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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_r_notifica_utente database table.
 *
 */
@Entity
@Table(name="cpass_r_notifica_utente")
@NamedQuery(name="CpassRNotificaUtente.findAll", query="SELECT c FROM CpassRNotificaUtente c")
public class CpassRNotificaUtente implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="notifica_utente_id")
	@SequenceGenerator(name="CPASS_R_NOTIFICA_UTENTE_NOTIFICA_UTENTE_ID_GENERATOR", sequenceName="CPASS_R_NOTIFICA_UTENTE_NOTIFICA_UTENTE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_NOTIFICA_UTENTE_NOTIFICA_UTENTE_ID_GENERATOR")
	private Integer notificaUtenteId;

	@Column(name="flg_letto")
	private Boolean flgLetto;

	//bi-directional many-to-one association to CpassTNotifica
	@ManyToOne
	@JoinColumn(name="notifica_id")
	private CpassTNotifica cpassTNotifica;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id")
	private CpassTUtente cpassTUtente;

	public CpassRNotificaUtente() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getNotificaUtenteId() {
		return notificaUtenteId;
	}

	public void setNotificaUtenteId(Integer notificaUtenteId) {
		this.notificaUtenteId = notificaUtenteId;
	}

	public Boolean getFlgLetto() {
		return flgLetto;
	}

	public void setFlgLetto(Boolean flgLetto) {
		this.flgLetto = flgLetto;
	}

	public CpassTNotifica getCpassTNotifica() {
		return cpassTNotifica;
	}

	public void setCpassTNotifica(CpassTNotifica cpassTNotifica) {
		this.cpassTNotifica = cpassTNotifica;
	}

	public CpassTUtente getCpassTUtente() {
		return cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	@Override
	public Integer getId() {
		return this.notificaUtenteId;
	}

	@Override
	public void setId(Integer id) {
		this.notificaUtenteId = id;
	}
}
