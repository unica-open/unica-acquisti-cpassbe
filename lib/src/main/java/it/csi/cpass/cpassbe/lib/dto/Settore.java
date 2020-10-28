/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Class Settore.
 */
public class Settore extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String cap;
	private String codice;
	private String descrizione;
	private String indirizzo;
	private String localita;
	private String provincia;
	private String telefono;
	private String numCivico;
	private String contatto;
	private String email;
	private Integer livello;  // aggiungere su yml
	private List<Settore> listSettore = new ArrayList<>(); // aggiungere su yml
	private UUID idPadre;  // aggiungere su yml	
	private Boolean utenteSettoreDefault;
	private Utente rup;
	private TipoSettore tipoSettore;
	private Ente ente;
	

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
	 * @return the livello
	 */
	public Integer getLivello() {
		return livello;
	}

	/**
	 * @param livello the livello to set
	 */
	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	/**
	 * @return the idPadre
	 */
	public UUID getIdPadre() {
		return idPadre;
	}

	/**
	 * @param livello the idPadre to set
	 */
	public void setIdPadre(UUID idPadre) {
		this.idPadre = idPadre;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Settore [cap=").append(cap)
			.append(", codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", indirizzo=").append(indirizzo)
			.append(", localita=").append(localita)
			.append(", provincia=").append(provincia)
			.append(", telefono=").append(telefono)
			.append(", utenteSettoreDefault=").append(utenteSettoreDefault)
			.append(", rup=").append(rup)
			.append(", tipoSettore=").append(tipoSettore)
			.append(", ente=").append(ente)
			.append(innerToString())
			.append("]")
			.toString();
	}


}
