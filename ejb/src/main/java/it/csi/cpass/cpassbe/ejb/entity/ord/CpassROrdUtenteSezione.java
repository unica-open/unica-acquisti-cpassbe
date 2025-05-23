/* ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord;

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

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_r_ord_utente_sezione database table.
 *
 */
@Entity
@Table(name="cpass_r_ord_utente_sezione")
@NamedQuery(name="CpassROrdUtenteSezione.findAll", query="SELECT c FROM CpassROrdUtenteSezione c")
public class CpassROrdUtenteSezione extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_ORD_UTENTE_SEZIONE_UTENTESEZIONEID_GENERATOR", sequenceName="cpass_r_ord_utente_sezione_utente_sezione_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_ORD_UTENTE_SEZIONE_UTENTESEZIONEID_GENERATOR")
	@Column(name="utente_sezione_id")
	private Integer utenteSezioneId;

	//bi-directional many-to-one association to CpassTOrdSezione
	@ManyToOne
	@JoinColumn(name="sezione_id")
	private CpassTOrdSezione cpassTOrdSezione;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id")
	private CpassTUtente cpassTUtente;

	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	public CpassROrdUtenteSezione() {
	}

	public Integer getUtenteSezioneId() {
		return this.utenteSezioneId;
	}

	public void setUtenteSezioneId(Integer utenteSezioneId) {
		this.utenteSezioneId = utenteSezioneId;
	}

	public CpassTOrdSezione getCpassTOrdSezione() {
		return this.cpassTOrdSezione;
	}

	public void setCpassTOrdSezione(CpassTOrdSezione cpassTOrdSezione) {
		this.cpassTOrdSezione = cpassTOrdSezione;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return utenteSezioneId;
	}

	@Override
	public void setId(Integer id) {
		utenteSezioneId = id;
	}

	public CpassTSettore getCpassTSettore() {
		return cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

}