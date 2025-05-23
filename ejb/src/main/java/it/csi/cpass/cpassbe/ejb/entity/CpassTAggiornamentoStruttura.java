/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_aggiornamento_struttura database table.
 *
 */
@Entity
@Table(name="cpass_t_aggiornamento_struttura")
@NamedQuery(name="CpassTAggiornamentoStruttura.findAll", query="SELECT c FROM CpassTAggiornamentoStruttura c")
public class CpassTAggiornamentoStruttura implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_AGGIORNAMENTO_STRUTTURA_AGGIORNAMENTOSTRUTTURAID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_AGGIORNAMENTO_STRUTTURA_AGGIORNAMENTOSTRUTTURAID_GENERATOR")
	@Column(name="aggiornamento_struttura_id")
	private Integer aggiornamentoStrutturaId;

	private String azione;

	@Column(name="descrizione_settore")
	private String descrizioneSettore;

	@Column(name="ente_code")
	private String enteCode;

	@Column(name="settore_nuovo")
	private String settoreNuovo;

	@Column(name="settore_padre")
	private String settorePadre;

	@Column(name="settore_vecchio")
	private String settoreVecchio;

	@Column(name="tipo_settore")
	private String tipoSettore;

	@Column(name="esito")
	private String esito;

	@Column(name="DATA_VALIDITA_INIZIO")// TIMESTAMP;
	private Date DataValiditaInizio;

	@Column(name="descrizione_indirizzo")// VARCHAR(500);
	private String descrizioneIndirizzo;

	@Column(name="indirizzo") //VARCHAR(200);
	private String indirizzo;

	@Column(name="num_civico") //VARCHAR(20);
	private String numCivico;

	@Column(name="localita")// VARCHAR(200);
	private String localita;

	@Column(name="provincia")// VARCHAR(200);
	private String provincia;

	@Column(name="cap") //VARCHAR(5);
	private String cap;

	@Column(name="contatto") //VARCHAR(200);
	private String contatto;

	@Column(name="email") //VARCHAR(50);
	private String email;

	@Column(name="telefono") //VARCHAR(50);
	private String telefono;

	//@Column(name="settore_indirizzo_codice")// VARCHAR(50);
	//private String settoreIndirizzoCodice;

	//@Column(name="principale") //BOOLEAN DEFAULT true;
	//private Boolean principale;

	//@Column(name="esterno_ente") //BOOLEAN DEFAULT false;
	//private Boolean esternoEnte;


	public CpassTAggiornamentoStruttura() {
	}

	public Integer getAggiornamentoStrutturaId() {
		return this.aggiornamentoStrutturaId;
	}

	public void setAggiornamentoStrutturaId(Integer aggiornamentoStrutturaId) {
		this.aggiornamentoStrutturaId = aggiornamentoStrutturaId;
	}

	public String getAzione() {
		return this.azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public String getDescrizioneSettore() {
		return this.descrizioneSettore;
	}

	public void setDescrizioneSettore(String descrizioneSettore) {
		this.descrizioneSettore = descrizioneSettore;
	}

	public String getEnteCode() {
		return this.enteCode;
	}

	public void setEnteCode(String enteCode) {
		this.enteCode = enteCode;
	}

	public String getSettoreNuovo() {
		return this.settoreNuovo;
	}

	public void setSettoreNuovo(String settoreNuovo) {
		this.settoreNuovo = settoreNuovo;
	}

	public String getSettorePadre() {
		return this.settorePadre;
	}

	public void setSettorePadre(String settorePadre) {
		this.settorePadre = settorePadre;
	}

	public String getSettoreVecchio() {
		return this.settoreVecchio;
	}

	public void setSettoreVecchio(String settoreVecchio) {
		this.settoreVecchio = settoreVecchio;
	}

	public String getTipoSettore() {
		return this.tipoSettore;
	}

	public void setTipoSettore(String tipoSettore) {
		this.tipoSettore = tipoSettore;
	}

	@Override
	public Integer getId() {
		return aggiornamentoStrutturaId;
	}

	@Override
	public void setId(Integer id) {
		aggiornamentoStrutturaId = id;
	}

	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}

	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return DataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		DataValiditaInizio = dataValiditaInizio;
	}

	/**
	 * @return the descrizioneIndirizzo
	 */
	public String getDescrizioneIndirizzo() {
		return descrizioneIndirizzo;
	}

	/**
	 * @param descrizioneIndirizzo the descrizioneIndirizzo to set
	 */
	public void setDescrizioneIndirizzo(String descrizioneIndirizzo) {
		this.descrizioneIndirizzo = descrizioneIndirizzo;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the numCivico
	 */
	public String getNumCivico() {
		return numCivico;
	}

	/**
	 * @param numCivico the numCivico to set
	 */
	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}

	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the contatto
	 */
	public String getContatto() {
		return contatto;
	}

	/**
	 * @param contatto the contatto to set
	 */
	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	public String getSettoreIndirizzoCodice() {
		return settoreIndirizzoCodice;
	}

	public void setSettoreIndirizzoCodice(String settoreIndirizzoCodice) {
		this.settoreIndirizzoCodice = settoreIndirizzoCodice;
	}
	 */


}
