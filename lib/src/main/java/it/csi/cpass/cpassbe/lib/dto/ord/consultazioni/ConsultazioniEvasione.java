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
import java.util.UUID;

public class ConsultazioniEvasione   implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4965769895703507094L;

	private UUID 		id;
	private Integer 	annoEvasione;
	private Integer 	numeroEvasione;
	private String  	statoCode;
	private String  	statoDesc;
	private BigDecimal  evaso;
	private BigDecimal  ripartitoSuImpegni;
	private BigDecimal  inContabilita;
	private String  	fornitoreDesc;
	private String  	documentoFatturaTipo;
	private String  	documentoFatturaAnno;
	private String  	documentoFatturaNumero;
	private String  	fornitoreRagioneSociale;
	private String  	fornitoreCodice;

/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

/**
	 * @return the annoEvasione
	 */
	public Integer getAnnoEvasione() {
		return annoEvasione;
	}

	/**
	 * @param annoEvasione the annoEvasione to set
	 */
	public void setAnnoEvasione(Integer annoEvasione) {
		this.annoEvasione = annoEvasione;
	}

	/**
	 * @return the numeroEvasione
	 */
	public Integer getNumeroEvasione() {
		return numeroEvasione;
	}

	/**
	 * @param numeroEvasione the numeroEvasione to set
	 */
	public void setNumeroEvasione(Integer numeroEvasione) {
		this.numeroEvasione = numeroEvasione;
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
	 * @return the ripartitoSuImpegni
	 */
	public BigDecimal getRipartitoSuImpegni() {
		return ripartitoSuImpegni;
	}

	/**
	 * @param ripartitoSuImpegni the ripartitoSuImpegni to set
	 */
	public void setRipartitoSuImpegni(BigDecimal ripartitoSuImpegni) {
		this.ripartitoSuImpegni = ripartitoSuImpegni;
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
	 * @return the fornitoreDesc
	 */
	public String getFornitoreDesc() {
		return fornitoreDesc;
	}

	/**
	 * @param fornitoreDesc the fornitoreDesc to set
	 */
	public void setFornitoreDesc(String fornitoreDesc) {
		this.fornitoreDesc = fornitoreDesc;
	}

	/**
	 * @return the documentoFatturaTipo
	 */
	public String getDocumentoFatturaTipo() {
		return documentoFatturaTipo;
	}

	/**
	 * @param documentoFatturaTipo the documentoFatturaTipo to set
	 */
	public void setDocumentoFatturaTipo(String documentoFatturaTipo) {
		this.documentoFatturaTipo = documentoFatturaTipo;
	}

	/**
	 * @return the documentoFatturaAnno
	 */
	public String getDocumentoFatturaAnno() {
		return documentoFatturaAnno;
	}

	/**
	 * @param documentoFatturaAnno the documentoFatturaAnno to set
	 */
	public void setDocumentoFatturaAnno(String documentoFatturaAnno) {
		this.documentoFatturaAnno = documentoFatturaAnno;
	}

	/**
	 * @return the documentoFatturaNumero
	 */
	public String getDocumentoFatturaNumero() {
		return documentoFatturaNumero;
	}

	/**
	 * @param documentoFatturaNumero the documentoFatturaNumero to set
	 */
	public void setDocumentoFatturaNumero(String documentoFatturaNumero) {
		this.documentoFatturaNumero = documentoFatturaNumero;
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
	 * @return the statoCode
	 */
	public String getStatoCode() {
		return statoCode;
	}

	/**
	 * @param statoCode the statoCode to set
	 */
	public void setStatoCode(String statoCode) {
		this.statoCode = statoCode;
	}

	List<ConsultazioniRipartizioneEvasione> listaConsultazioniRipartizioneEvasione;

	/**
	 * @return the listaConsultazioniRipartizioneEvasione
	 */
	public List<ConsultazioniRipartizioneEvasione> getListaConsultazioniRipartizioneEvasione() {
		return listaConsultazioniRipartizioneEvasione;
	}

	/**
	 * @param listaConsultazioniRipartizioneEvasione the listaConsultazioniRipartizioneEvasione to set
	 */
	public void setListaConsultazioniRipartizioneEvasione(
			List<ConsultazioniRipartizioneEvasione> listaConsultazioniRipartizioneEvasione) {
		this.listaConsultazioniRipartizioneEvasione = listaConsultazioniRipartizioneEvasione;
	}

}
