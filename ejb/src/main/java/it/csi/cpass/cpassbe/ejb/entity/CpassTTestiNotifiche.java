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
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_testi_notifiche database table.
 *
 */
@Entity
@Table(name="cpass_t_testi_notifiche")
@NamedQuery(name="CpassTTestiNotifiche.findAll", query="SELECT c FROM CpassTTestiNotifiche c")
public class CpassTTestiNotifiche implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	public CpassTTestiNotifiche() {
	}

	@Id
	@Column(name="testo_id")
	private Integer testoId;

	@Column(name="en_testo")
	private String enTesto;

	@Column(name="it_testo")
	private String itTesto;

	@Column(name="codice")
	private String codice;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getTestoId() {
		return testoId;
	}

	public void setTestoId(Integer testoId) {
		this.testoId = testoId;
	}

	public String getEnTesto() {
		return enTesto;
	}

	public void setEnTesto(String enTesto) {
		this.enTesto = enTesto;
	}

	public String getItTesto() {
		return itTesto;
	}

	public void setItTesto(String itTesto) {
		this.itTesto = itTesto;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Override
	public Integer getId() {
		return testoId;
	}

	@Override
	public void setId(Integer id) {
		testoId = id;
	}
}
