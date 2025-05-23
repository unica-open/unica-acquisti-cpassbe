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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_d_ord_causale_sospensione_evasione database table.
 *
 */
@Entity
@Table(name="cpass_d_ord_causale_sospensione_evasione")
@NamedQuery(name="CpassDOrdCausaleSospensioneEvasione.findAll", query="SELECT c FROM CpassDOrdCausaleSospensioneEvasione c")
public class CpassDOrdCausaleSospensioneEvasione implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="causale_sospensione_id")
	private Integer causaleSospensioneId;

	@Column(name="causale_sospensione_codice")
	private String causaleSospensioneCodice;

	@Column(name="causale_sospensione_descrizione")
	private String causaleSospensioneDescrizione;

	@Column(name="data_cancellazione")
	private Date dataCancellazione;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="utente_cancellazione")
	private String utenteCancellazione;

	@Column(name="utente_creazione")
	private String utenteCreazione;

	@Column(name="utente_modifica")
	private String utenteModifica;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	public CpassDOrdCausaleSospensioneEvasione() {
	}


	public Integer getCausaleSospensioneId() {
		return this.causaleSospensioneId;
	}

	public void setCausaleSospensioneId(Integer causaleSospensioneId) {
		this.causaleSospensioneId = causaleSospensioneId;
	}

	public String getCausaleSospensioneCodice() {
		return this.causaleSospensioneCodice;
	}

	public void setCausaleSospensioneCodice(String causaleSospensioneCodice) {
		this.causaleSospensioneCodice = causaleSospensioneCodice;
	}

	public String getCausaleSospensioneDescrizione() {
		return this.causaleSospensioneDescrizione;
	}

	public void setCausaleSospensioneDescrizione(String causaleSospensioneDescrizione) {
		this.causaleSospensioneDescrizione = causaleSospensioneDescrizione;
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

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return causaleSospensioneId;
	}

	@Override
	public void setId(Integer id) {
		causaleSospensioneId = id;
	}

}
