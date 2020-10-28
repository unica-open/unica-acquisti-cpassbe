/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import javax.persistence.*;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

import java.sql.Timestamp;


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
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

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

	public Timestamp getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Timestamp getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
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
