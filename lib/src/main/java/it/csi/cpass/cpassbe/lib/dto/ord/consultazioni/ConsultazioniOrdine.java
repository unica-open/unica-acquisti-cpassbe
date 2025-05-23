/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.consultazioni;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ConsultazioniOrdine  implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8509401615466761772L;

	private String 		id;
	private Integer 	annoOrdine;
	private Integer 	numeroOrdine;
	private String  	statoDesc;
	private String  	fornitoreCodice;
	private String  	fornitoreRagioneSociale;
	private BigDecimal  netto;
	private BigDecimal  lordo;
	private BigDecimal  impegnato;
	private BigDecimal  evaso;
	private BigDecimal  inContabilita;
	private String  	descrizioneAcquisto;
	List<ConsultazioniEvasione> listaConsultazioniEvasione;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the annoOrdine
	 */
	public Integer getAnnoOrdine() {
		return annoOrdine;
	}

	/**
	 * @param annoOrdine the annoOrdine to set
	 */
	public void setAnnoOrdine(Integer annoOrdine) {
		this.annoOrdine = annoOrdine;
	}

	/**
	 * @return the numeroOrdine
	 */
	public Integer getNumeroOrdine() {
		return numeroOrdine;
	}

	/**
	 * @param numeroOrdine the numeroOrdine to set
	 */
	public void setNumeroOrdine(Integer numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	/**
	 * @return the statoDesc
	 */
	public String getStatoDesc() {
		return statoDesc;
	}

	/**
	 * @param statoDesc the statoDesc to set
	 */
	public void setStatoDesc(String statoDesc) {
		this.statoDesc = statoDesc;
	}


	/**
	 * @return the netto
	 */
	public BigDecimal getNetto() {
		return netto;
	}

	/**
	 * @param netto the netto to set
	 */
	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}

	/**
	 * @return the lordo
	 */
	public BigDecimal getLordo() {
		return lordo;
	}

	/**
	 * @param lordo the lordo to set
	 */
	public void setLordo(BigDecimal lordo) {
		this.lordo = lordo;
	}

	/**
	 * @return the impegnato
	 */
	public BigDecimal getImpegnato() {
		return impegnato;
	}

	/**
	 * @param impegnato the impegnato to set
	 */
	public void setImpegnato(BigDecimal impegnato) {
		this.impegnato = impegnato;
	}

	/**
	 * @return the evaso
	 */
	public BigDecimal getEvaso() {
		return evaso;
	}

	/**
	 * @param evaso the evaso to set
	 */
	public void setEvaso(BigDecimal evaso) {
		this.evaso = evaso;
	}

	/**
	 * @return the inContabilita
	 */
	public BigDecimal getInContabilita() {
		return inContabilita;
	}

	/**
	 * @param inContabilita the inContabilita to set
	 */
	public void setInContabilita(BigDecimal inContabilita) {
		this.inContabilita = inContabilita;
	}


	/**
	 * @return the fornitoreCodice
	 */
	public String getFornitoreCodice() {
		return fornitoreCodice;
	}

	/**
	 * @param fornitoreCodice the fornitoreCodice to set
	 */
	public void setFornitoreCodice(String fornitoreCodice) {
		this.fornitoreCodice = fornitoreCodice;
	}

	/**
	 * @return the fornitoreRagioneSociale
	 */
	public String getFornitoreRagioneSociale() {
		return fornitoreRagioneSociale;
	}

	/**
	 * @param fornitoreRagioneSociale the fornitoreRagioneSociale to set
	 */
	public void setFornitoreRagioneSociale(String fornitoreRagioneSociale) {
		this.fornitoreRagioneSociale = fornitoreRagioneSociale;
	}

	/**
	 * @return the descrizioneAcquisto
	 */
	public String getDescrizioneAcquisto() {
		return descrizioneAcquisto;
	}

	/**
	 * @param descrizioneAcquisto the descrizioneAcquisto to set
	 */
	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}

	/**
	 * @return the listaConsultazioniEvasione
	 */
	public List<ConsultazioniEvasione> getListaConsultazioniEvasione() {
		return listaConsultazioniEvasione;
	}

	/**
	 * @param listaConsultazioniEvasione the listaConsultazioniEvasione to set
	 */
	public void setListaConsultazioniEvasione(List<ConsultazioniEvasione> listaConsultazioniEvasione) {
		this.listaConsultazioniEvasione = listaConsultazioniEvasione;
	}



}
