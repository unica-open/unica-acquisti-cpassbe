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
import java.util.Date;


public class Provvedimento extends BaseDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer anno;

	private String note;

	private String numero;

	private String oggetto;

	private ProvvedimentoTipo provvedimentoTipo;

	private Ente ente;

	private Settore settore;

	private Cdc cdc;

	private String descrizione;

	private boolean selected;

	private Date dataValiditaInizio;

	private Date dataValiditaFine;

	private Date dataCreazione;

	private Date dataModifica;

	private String cdrCodice;

	private String cdcCodice;

	public Provvedimento() {
	}


	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}


	/**
	 * @param anno the anno to set
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}


	/**
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}


	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}


	/**
	 * @return the provvedimentoTipo
	 */
	public ProvvedimentoTipo getProvvedimentoTipo() {
		return provvedimentoTipo;
	}


	/**
	 * @param provvedimentoTipo the provvedimentoTipo to set
	 */
	public void setProvvedimentoTipo(ProvvedimentoTipo provvedimentoTipo) {
		this.provvedimentoTipo = provvedimentoTipo;
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
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}


	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
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
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}


	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}


	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}


	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}


	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}


	public Date getDataCreazione() {
		return dataCreazione;
	}


	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}


	public Date getDataModifica() {
		return dataModifica;
	}


	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}


	/**
	 * @return the cdrCodice
	 */
	public String getCdrCodice() {
		return cdrCodice;
	}


	/**
	 * @param cdrCodice the cdrCodice to set
	 */
	public void setCdrCodice(String cdrCodice) {
		this.cdrCodice = cdrCodice;
	}


	/**
	 * @return the cdcCodice
	 */
	public String getCdcCodice() {
		return cdcCodice;
	}


	/**
	 * @param cdcCodice the cdcCodice to set
	 */
	public void setCdcCodice(String cdcCodice) {
		this.cdcCodice = cdcCodice;
	}


	/**
	 * @return the cdc
	 */
	public Cdc getCdc() {
		return cdc;
	}


	/**
	 * @param cdc the cdc to set
	 */
	public void setCdc(Cdc cdc) {
		this.cdc = cdc;
	}




}
