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
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;


/**
 * The persistent class for the cpass_t_settore_indirizzo database table.
 *
 */
@Entity
@Table(name="cpass_t_settore_indirizzo")
@NamedQuery(name="CpassTSettoreIndirizzo.findAll", query="SELECT c FROM CpassTSettoreIndirizzo c")
public class CpassTSettoreIndirizzo extends BaseAuditedEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SETTORE_INDIRIZZO_SETTOREINDIRIZZOID_GENERATOR", sequenceName="CPASS_T_SETTORE_INDIRIZZO_SETTORE_INDIRIZZO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SETTORE_INDIRIZZO_SETTOREINDIRIZZOID_GENERATOR")
	@Column(name="settore_indirizzo_id")
	private Integer settoreIndirizzoId;

	private String cap;

	private String contatto;

	private String descrizione;

	private String email;

	private String indirizzo;

	private String localita;

	@Column(name="num_civico")
	private String numCivico;

	private String provincia;

	private String telefono;
	@ManyToOne
	@JoinColumn(name="magazzino_id")
	private CpassTMagMagazzino cpassTMagMagazzino;

	@Column(name="esterno_ente")
	private Boolean esternoEnte;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	@Column(name="settore_indirizzo_codice")
	private String settoreIndirizzoCodice;

	private Boolean principale;

	public Integer getSettoreIndirizzoId() {
		return this.settoreIndirizzoId;
	}

	public void setSettoreIndirizzoId(Integer settoreIndirizzoId) {
		this.settoreIndirizzoId = settoreIndirizzoId;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getContatto() {
		return this.contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNumCivico() {
		return this.numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}


	/**
	 * @return the settoreIndirizzoCodice
	 */
	public String getSettoreIndirizzoCodice() {
		return settoreIndirizzoCodice;
	}

	/**
	 * @param settoreIndirizzoCodice the settoreIndirizzoCodice to set
	 */
	public void setSettoreIndirizzoCodice(String settoreIndirizzoCodice) {
		this.settoreIndirizzoCodice = settoreIndirizzoCodice;
	}


	/**
	 * @return the principale
	 */
	public Boolean getPrincipale() {
		return principale;
	}

	/**
	 * @param principale the principale to set
	 */
	public void setPrincipale(Boolean principale) {
		this.principale = principale;
	}

	@Override
	public Integer getId() {
		return settoreIndirizzoId;
	}

	@Override
	public void setId(Integer id) {
		settoreIndirizzoId =id;
	}

	/**
	 * @return the cpassTMagMagazzino
	 */
	public CpassTMagMagazzino getCpassTMagMagazzino() {
		return cpassTMagMagazzino;
	}

	/**
	 * @param cpassTMagMagazzino the cpassTMagMagazzino to set
	 */
	public void setCpassTMagMagazzino(CpassTMagMagazzino cpassTMagMagazzino) {
		this.cpassTMagMagazzino = cpassTMagMagazzino;
	}

	/**
	 * @return the esternoEnte
	 */
	public Boolean getEsternoEnte() {
		return esternoEnte;
	}

	/**
	 * @param esternoEnte the esternoEnte to set
	 */
	public void setEsternoEnte(Boolean esternoEnte) {
		this.esternoEnte = esternoEnte;
	}

}
