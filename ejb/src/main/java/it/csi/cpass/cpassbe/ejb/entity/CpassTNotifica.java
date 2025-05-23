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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_notifica database table.
 *
 */
@Entity
@Table(name="cpass_t_notifica")
@NamedQuery(name="CpassTNotifica.findAll", query="SELECT c FROM CpassTNotifica c")
public class CpassTNotifica implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	public CpassTNotifica() {
	}

	@Id
	@SequenceGenerator(name="CPASS_T_NOTIFICAID_GENERATOR", sequenceName="cpass_t_notifica_notifica_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_NOTIFICAID_GENERATOR")
	@Column(name="notifica_id", unique=true, nullable=false)
	private Integer notificaId;

	@OneToOne
	@JoinColumn(name="testo_id")
	private CpassTTestiNotifiche cpassTTestiNotifiche;

	@Column(name="entita_id")
	private String entitaId;

	@Column(name="entita_tipo")
	private String entitaTipo;

	@Column(name="fonte")
	private String fonte;

	@Column(name="data_fine")
	private Date dataFine;

	@Column(name="data_inizio")
	private Date dataInizio;

	@Column(name="flg_generico")
	private Boolean flgGenerico;

	@Column(name="parametri")
	private String parametri;

	public Integer getNotificaId() {
		return notificaId;
	}

	public void setNotificaId(Integer notificaId) {
		this.notificaId = notificaId;
	}

	public CpassTTestiNotifiche getCpassTTestiNotifiche() {
		return cpassTTestiNotifiche;
	}

	public void setCpassTTestiNotifiche(CpassTTestiNotifiche cpassTTestiNotifiche) {
		this.cpassTTestiNotifiche = cpassTTestiNotifiche;
	}

	public String getEntitaId() {
		return entitaId;
	}

	public void setEntitaId(String entitaId) {
		this.entitaId = entitaId;
	}

	public String getEntitaTipo() {
		return entitaTipo;
	}

	public void setEntitaTipo(String entitaTipo) {
		this.entitaTipo = entitaTipo;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Boolean getFlgGenerico() {
		return flgGenerico;
	}

	public void setFlgGenerico(Boolean flgGenerico) {
		this.flgGenerico = flgGenerico;
	}

	public String getParametri() {
		return parametri;
	}

	public void setParametri(String parametri) {
		this.parametri = parametri;
	}

	@Override
	public Integer getId() {
		return notificaId;
	}

	@Override
	public void setId(Integer id) {
		notificaId = id;
	}


}
