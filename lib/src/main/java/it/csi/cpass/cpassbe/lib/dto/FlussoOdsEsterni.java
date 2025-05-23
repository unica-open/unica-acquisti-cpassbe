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
import java.util.List;
import java.util.UUID;




public class FlussoOdsEsterni extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer elaborazioneId;
	private String errore;
	private String esito;
	private UUID idEnte;
	private String codiceOds;
	private String descrizioneOds;
	private String cpvCodice;
	private String unitaMisura;
	private String codiceIva;
	private String inventariabile;
	private String dataInizioValidita;
	private String dataFineValidita;
	private String prezzoUnitario;
	private String codiceListinoFornitore;
	private String descrizioneListinoFornitore;
	private String codiceFornitore;
	private Integer numeroRiga;
	private List<ListinoFornitore> listinoFornitore;

	public FlussoOdsEsterni() {
	}


	/**
	 * @return the elaborazioneId
	 */
	public Integer getElaborazioneId() {
		return elaborazioneId;
	}


	/**
	 * @param elaborazioneId the elaborazioneId to set
	 */
	public void setElaborazioneId(Integer elaborazioneId) {
		this.elaborazioneId = elaborazioneId;
	}


	/**
	 * @return the errore
	 */
	public String getErrore() {
		return errore;
	}


	/**
	 * @param errore the errore to set
	 */
	public void setErrore(String errore) {
		this.errore = errore;
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
	 * @return the idEnte
	 */
	public UUID getIdEnte() {
		return idEnte;
	}


	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
	}


	/**
	 * @return the codiceOds
	 */
	public String getCodiceOds() {
		return codiceOds;
	}


	/**
	 * @param codiceOds the codiceOds to set
	 */
	public void setCodiceOds(String codiceOds) {
		this.codiceOds = codiceOds;
	}


	/**
	 * @return the descrizioneOds
	 */
	public String getDescrizioneOds() {
		return descrizioneOds;
	}


	/**
	 * @param descrizioneOds the descrizioneOds to set
	 */
	public void setDescrizioneOds(String descrizioneOds) {
		this.descrizioneOds = descrizioneOds;
	}


	/**
	 * @return the cpvCodice
	 */
	public String getCpvCodice() {
		return cpvCodice;
	}


	/**
	 * @param cpvCodice the cpvCodice to set
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}


	/**
	 * @return the unitaMisura
	 */
	public String getUnitaMisura() {
		return unitaMisura;
	}


	/**
	 * @param unitaMisura the unitaMisura to set
	 */
	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}


	/**
	 * @return the codiceIva
	 */
	public String getCodiceIva() {
		return codiceIva;
	}


	/**
	 * @param codiceIva the codiceIva to set
	 */
	public void setCodiceIva(String codiceIva) {
		this.codiceIva = codiceIva;
	}


	/**
	 * @return the inventariabile
	 */
	public String getInventariabile() {
		return inventariabile;
	}


	/**
	 * @param inventariabile the inventariabile to set
	 */
	public void setInventariabile(String inventariabile) {
		this.inventariabile = inventariabile;
	}


	/**
	 * @return the dataInizioValidita
	 */
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}


	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}


	/**
	 * @return the dataFineValidita
	 */
	public String getDataFineValidita() {
		return dataFineValidita;
	}


	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}


	/**
	 * @return the prezzoUnitario
	 */
	public String getPrezzoUnitario() {
		return prezzoUnitario;
	}


	/**
	 * @param prezzoUnitario the prezzoUnitario to set
	 */
	public void setPrezzoUnitario(String prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}


	/**
	 * @return the codiceListinoFornitore
	 */
	public String getCodiceListinoFornitore() {
		return codiceListinoFornitore;
	}


	/**
	 * @param codiceListinoFornitore the codiceListinoFornitore to set
	 */
	public void setCodiceListinoFornitore(String codiceListinoFornitore) {
		this.codiceListinoFornitore = codiceListinoFornitore;
	}


	/**
	 * @return the descrizioneListinoFornitore
	 */
	public String getDescrizioneListinoFornitore() {
		return descrizioneListinoFornitore;
	}


	/**
	 * @param descrizioneListinoFornitore the descrizioneListinoFornitore to set
	 */
	public void setDescrizioneListinoFornitore(String descrizioneListinoFornitore) {
		this.descrizioneListinoFornitore = descrizioneListinoFornitore;
	}


	/**
	 * @return the codiceFornitore
	 */
	public String getCodiceFornitore() {
		return codiceFornitore;
	}


	/**
	 * @param codiceFornitore the codiceFornitore to set
	 */
	public void setCodiceFornitore(String codiceFornitore) {
		this.codiceFornitore = codiceFornitore;
	}


	/**
	 * @return the numeroRiga
	 */
	public Integer getNumeroRiga() {
		return numeroRiga;
	}


	/**
	 * @param numeroRiga the numeroRiga to set
	 */
	public void setNumeroRiga(Integer numeroRiga) {
		this.numeroRiga = numeroRiga;
	}


	/**
	 * @return the listinoFornitore
	 */
	public List<ListinoFornitore> getListinoFornitore() {
		return listinoFornitore;
	}


	/**
	 * @param listinoFornitore the listinoFornitore to set
	 */
	public void setListinoFornitore(List<ListinoFornitore> listinoFornitore) {
		this.listinoFornitore = listinoFornitore;
	}







}
