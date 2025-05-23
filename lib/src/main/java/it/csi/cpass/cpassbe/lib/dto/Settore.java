/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;

public class Settore extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codice;
	private String descrizione;
	private List<Settore> listSettore = new ArrayList<>(); // aggiungere su yml
	private Settore settorePadre;  // aggiungere su yml
	private Boolean utenteSettoreDefault;
	private Utente rup;
	private Date dataValiditaFine;
	private Date dataValiditaInizio;
	private TipoSettore tipoSettore;
	private Ente ente;
	private List<SettoreIndirizzo> settoreIndirizzos = new ArrayList<>(); // aggiungere su yml
	private List<Ufficio> uffici = new ArrayList<>(); // aggiungere su yml
	private List<Cdc> cdcs = new ArrayList<>(); // aggiungere su yml
	private String firma;
	private List<AooActa> aooActas = new ArrayList<>();
	private Boolean isCdc;
	private String validita;
	private String all;

	private String indirizzoSettorePrincipale;
	private String numCivicoSettorePrincipale;
	private String localitaSettorePrincipale;
	private String provinciaSettorePrincipale;
	private String capSettorePrincipale;
	private String contattoSettorePrincipale;
	private String emailSettorePrincipale;
	private String telefonoSettorePrincipale;
	private String sede;


	/** Default constructor */
	public Settore() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Settore(UUID id) {
		super(id);
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the tipoSettore
	 */
	public TipoSettore getTipoSettore() {
		return tipoSettore;
	}

	/**
	 * @param tipoSettore the tipoSettore to set
	 */
	public void setTipoSettore(TipoSettore tipoSettore) {
		this.tipoSettore = tipoSettore;
	}

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	/**
	 * @return the utenteSettoreDefault
	 */
	public Boolean getUtenteSettoreDefault() {
		return utenteSettoreDefault;
	}

	/**
	 * @param utenteSettoreDefault the utenteSettoreDefault to set
	 */
	public void setUtenteSettoreDefault(Boolean utenteSettoreDefault) {
		this.utenteSettoreDefault = utenteSettoreDefault;
	}

	/**
	 * @return the rup
	 */
	public Utente getRup() {
		return rup;
	}

	/**
	 * @param rup the rup to set
	 */
	/**
	 * @param rup
	 */
	public void setRup(Utente rup) {
		this.rup = rup;
	}

	/**
	 * @return the listSettore
	 */
	public List<Settore> getListSettore() {
		return this.listSettore = listSettore != null ? listSettore : new ArrayList<>();
	}

	/**
	 * @param listCpv the listCpv to set
	 */
	public void setListSettore(List<Settore> listSettore) {
		this.listSettore = listSettore;
	}


	/**
	 * @return the settorePadre
	 */
	public Settore getSettorePadre() {
		return settorePadre;
	}

	/**
	 * @param settorePadre the settorePadre to set
	 */
	public void setSettorePadre(Settore settorePadre) {
		this.settorePadre = settorePadre;
	}


	/**
	 * @return the settoreIndirizzos
	 */
	public List<SettoreIndirizzo> getSettoreIndirizzos() {
		return settoreIndirizzos;
	}

	/**
	 * @param settoreIndirizzos the settoreIndirizzos to set
	 */
	public void setSettoreIndirizzos(List<SettoreIndirizzo> settoreIndirizzos) {
		this.settoreIndirizzos = settoreIndirizzos;
	}


	/**
	 * @return the uffici
	 */
	public List<Ufficio> getUffici() {
		return uffici;
	}

	/**
	 * @param uffici the uffici to set
	 */
	public void setUffici(List<Ufficio> uffici) {
		this.uffici = uffici;
	}

	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	/**
	 * @return the aooActas
	 */
	public List<AooActa> getAooActas() {
		return aooActas;
	}

	/**
	 * @param aooActas the aooActas to set
	 */
	public void setAooActas(List<AooActa> aooActas) {
		this.aooActas = aooActas;
	}


	/**
	 * @return the cdcs
	 */
	public List<Cdc> getCdcs() {
		return cdcs;
	}

	/**
	 * @param cdcs the cdcs to set
	 */
	public void setCdcs(List<Cdc> cdcs) {
		this.cdcs = cdcs;
	}


	/**
	 * @return the isCdc
	 */
	public Boolean getIsCdc() {
		return isCdc;
	}

	/**
	 * @param isCdc the isCdc to set
	 */
	public void setIsCdc(Boolean isCdc) {
		this.isCdc = isCdc;
	}

	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}


	/**
	 * @return the validita
	 */
	public String getValidita() {
		return validita;
	}

	/**
	 * @param validita the validita to set
	 */
	public void setValidita(String validita) {
		this.validita = validita;
	}




	/**
	 * @return the indirizzoSettorePrincipale
	 */
	public String getIndirizzoSettorePrincipale() {
		return indirizzoSettorePrincipale;
	}

	/**
	 * @param indirizzoSettorePrincipale the indirizzoSettorePrincipale to set
	 */
	public void setIndirizzoSettorePrincipale(String indirizzoSettorePrincipale) {
		this.indirizzoSettorePrincipale = indirizzoSettorePrincipale;
	}

	/**
	 * @return the numCivicoSettorePrincipale
	 */
	public String getNumCivicoSettorePrincipale() {
		return numCivicoSettorePrincipale;
	}

	/**
	 * @param numCivicoSettorePrincipale the numCivicoSettorePrincipale to set
	 */
	public void setNumCivicoSettorePrincipale(String numCivicoSettorePrincipale) {
		this.numCivicoSettorePrincipale = numCivicoSettorePrincipale;
	}

	/**
	 * @return the localitaSettorePrincipale
	 */
	public String getLocalitaSettorePrincipale() {
		return localitaSettorePrincipale;
	}

	/**
	 * @param localitaSettorePrincipale the localitaSettorePrincipale to set
	 */
	public void setLocalitaSettorePrincipale(String localitaSettorePrincipale) {
		this.localitaSettorePrincipale = localitaSettorePrincipale;
	}

	/**
	 * @return the provinciaSettorePrincipale
	 */
	public String getProvinciaSettorePrincipale() {
		return provinciaSettorePrincipale;
	}

	/**
	 * @param provinciaSettorePrincipale the provinciaSettorePrincipale to set
	 */
	public void setProvinciaSettorePrincipale(String provinciaSettorePrincipale) {
		this.provinciaSettorePrincipale = provinciaSettorePrincipale;
	}

	/**
	 * @return the capSettorePrincipale
	 */
	public String getCapSettorePrincipale() {
		return capSettorePrincipale;
	}

	/**
	 * @param capSettorePrincipale the capSettorePrincipale to set
	 */
	public void setCapSettorePrincipale(String capSettorePrincipale) {
		this.capSettorePrincipale = capSettorePrincipale;
	}

	/**
	 * @return the contattoSettorePrincipale
	 */
	public String getContattoSettorePrincipale() {
		return contattoSettorePrincipale;
	}

	/**
	 * @param contattoSettorePrincipale the contattoSettorePrincipale to set
	 */
	public void setContattoSettorePrincipale(String contattoSettorePrincipale) {
		this.contattoSettorePrincipale = contattoSettorePrincipale;
	}

	/**
	 * @return the emailSettorePrincipale
	 */
	public String getEmailSettorePrincipale() {
		return emailSettorePrincipale;
	}

	/**
	 * @param emailSettorePrincipale the emailSettorePrincipale to set
	 */
	public void setEmailSettorePrincipale(String emailSettorePrincipale) {
		this.emailSettorePrincipale = emailSettorePrincipale;
	}

	/**
	 * @return the telefonoSettorePrincipale
	 */
	public String getTelefonoSettorePrincipale() {
		return telefonoSettorePrincipale;
	}

	/**
	 * @param telefonoSettorePrincipale the telefonoSettorePrincipale to set
	 */
	public void setTelefonoSettorePrincipale(String telefonoSettorePrincipale) {
		this.telefonoSettorePrincipale = telefonoSettorePrincipale;
	}

	/**
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}

	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(", codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", utenteSettoreDefault=").append(utenteSettoreDefault)
			.append(", rup=").append(rup)
			.append(", tipoSettore=").append(tipoSettore)
			.append(", ente=").append(ente)
			.append(innerToString())
			.append("]")
			.toString();
	}


}
