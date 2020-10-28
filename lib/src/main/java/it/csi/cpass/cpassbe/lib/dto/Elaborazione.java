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
import java.util.Date;
import java.util.List;

/**
 * The Class Stato.
 */
public class Elaborazione extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String entitaId;
	private String utente;
	private String stato;
	private Date data;
	private String esito;
	private ElaborazioneTipo elaborazioneTipo;
	private List<ElaborazioneMessaggio> listaMessaggi = new ArrayList<>();
	private List<ElaborazioneParametro> listaParametri = new ArrayList<>();
	private String idEsterno;
	
	/** Default empty constructor */
	public Elaborazione() {}

	/** Constructor
	 * @param id the id
	 */
	public Elaborazione(Integer id) {
		super(id);
	}


	/**
	 * @return the entitaId
	 */
	public String getEntitaId() {
		return entitaId;
	}

	/**
	 * @param entitaId the entitaId to set
	 */
	public void setEntitaId(String entitaId) {
		this.entitaId = entitaId;
	}

	/**
	 * @return the utente
	 */
	public String getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(String utente) {
		this.utente = utente;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
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
	 * @return the listaMessaggi
	 */
	public List<ElaborazioneMessaggio> getListaMessaggi() {
		return listaMessaggi;
	}
	
	/**
	 * @return the listaParametri
	 */
	public List<ElaborazioneParametro> getListaParametri() {
		return listaParametri;
	}

	/**
	 * @param listaParametri the listaParametri to set
	 */
	public void setListaParametri(List<ElaborazioneParametro> listaParametri) {
		this.listaParametri = listaParametri;
	}

	/**
	 * @return the elaborazioneTipo
	 */
	public ElaborazioneTipo getElaborazioneTipo() {
		return elaborazioneTipo;
	}

	/**
	 * @param elaborazioneTipo the elaborazioneTipo to set
	 */
	public void setElaborazioneTipo(ElaborazioneTipo elaborazioneTipo) {
		this.elaborazioneTipo = elaborazioneTipo;
	}

	/**
	 * @param listaMessaggi the listaMessaggi to set
	 */
	public void setListaMessaggi(List<ElaborazioneMessaggio> listaMessaggi) {
		this.listaMessaggi = listaMessaggi;
	}

	/**
	 * @return the IdEsterno
	 */
	public String getIdEsterno() {
		return idEsterno;
	}

	/**
	 * @param IdEsterno the IdEsterno to set
	 */
	public void setIdEsterno(String idEsterno) {
		this.idEsterno = idEsterno;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Elaborazione [entita_id=").append(entitaId)			
			//.append(", tipo=").append(tipo)
			.append(", utente=").append(utente)
			.append(", stato=").append(stato)
			.append(", data=").append(data)
			.append(", esito=").append(esito)
			.append(", id=").append(id)
			.append(", idEsterno=").append(idEsterno)
			.append("]")
			.toString();
	}

}
