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

public class ConsultazioniImpegno   implements Serializable{
	private static final long serialVersionUID = -5334828746038847830L;

	private Integer 	annoEsercizio;
	private Integer 	annoImpegno;
	private Integer 	numeroImpegno;
	private Integer 	annoSubImpegno;
	private Integer 	numeroSubImpegno;
	private String  	statoCode;
	private String  	fornitoreCodice;
	private String  	fornitoreRagioneSociale;
	private BigDecimal  importo;
	private BigDecimal  ordNonLiqAnniPrec;
	private BigDecimal  ordinato ;
	private BigDecimal  disponibileAdOrdinare ;
	private BigDecimal  evaso ;
	private BigDecimal  inContabilita ;
	List<ConsultazioniOrdine> listaConsultazioniOrdine;


	/**
	 * @return the annoEsercizio
	 */
	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}

	/**
	 * @param annoEsercizio the annoEsercizio to set
	 */
	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}

	/**
	 * @return the annoImpegno
	 */
	public Integer getAnnoImpegno() {
		return annoImpegno;
	}

	/**
	 * @param annoImpegno the annoImpegno to set
	 */
	public void setAnnoImpegno(Integer annoImpegno) {
		this.annoImpegno = annoImpegno;
	}

	/**
	 * @return the numeroImpegno
	 */
	public Integer getNumeroImpegno() {
		return numeroImpegno;
	}

	/**
	 * @param numeroImpegno the numeroImpegno to set
	 */
	public void setNumeroImpegno(Integer numeroImpegno) {
		this.numeroImpegno = numeroImpegno;
	}

	/**
	 * @return the annoSubImpegno
	 */
	public Integer getAnnoSubImpegno() {
		return annoSubImpegno;
	}

	/**
	 * @param annoSubImpegno the annoSubImpegno to set
	 */
	public void setAnnoSubImpegno(Integer annoSubImpegno) {
		this.annoSubImpegno = annoSubImpegno;
	}

	/**
	 * @return the numeroSubImpegno
	 */
	public Integer getNumeroSubImpegno() {
		return numeroSubImpegno;
	}

	/**
	 * @param numeroSubImpegno the numeroSubImpegno to set
	 */
	public void setNumeroSubImpegno(Integer numeroSubImpegno) {
		this.numeroSubImpegno = numeroSubImpegno;
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
	 * @return the importo
	 */
	public BigDecimal getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	/**
	 * @return the ordNonLiqAnniPrec
	 */
	public BigDecimal getOrdNonLiqAnniPrec() {
		return ordNonLiqAnniPrec;
	}

	/**
	 * @param ordNonLiqAnniPrec the ordNonLiqAnniPrec to set
	 */
	public void setOrdNonLiqAnniPrec(BigDecimal ordNonLiqAnniPrec) {
		this.ordNonLiqAnniPrec = ordNonLiqAnniPrec;
	}

	/**
	 * @return the ordinato
	 */
	public BigDecimal getOrdinato() {
		return ordinato;
	}

	/**
	 * @param ordinato the ordinato to set
	 */
	public void setOrdinato(BigDecimal ordinato) {
		this.ordinato = ordinato;
	}

	/**
	 * @return the disponibileAdOrdinare
	 */
	public BigDecimal getDisponibileAdOrdinare() {
		return disponibileAdOrdinare;
	}

	/**
	 * @param disponibileAdOrdinare the disponibileAdOrdinare to set
	 */
	public void setDisponibileAdOrdinare(BigDecimal disponibileAdOrdinare) {
		this.disponibileAdOrdinare = disponibileAdOrdinare;
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
	 * @return the listaConsultazioniOrdine
	 */
	public List<ConsultazioniOrdine> getListaConsultazioniOrdine() {
		return listaConsultazioniOrdine;
	}

	/**
	 * @param listaConsultazioniOrdine the listaConsultazioniOrdine to set
	 */
	public void setListaConsultazioniOrdine(List<ConsultazioniOrdine> listaConsultazioniOrdine) {
		this.listaConsultazioniOrdine = listaConsultazioniOrdine;
	}



}
