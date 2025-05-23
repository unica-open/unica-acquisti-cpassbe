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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Impegno
 */
public class Impegno extends BaseAuditedDto<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer annoEsercizio;
	private Integer anno;
	private Integer numero;
	private String descrizione;
	private String stato;

	private BigDecimal importoAttuale;
	private BigDecimal importoIniziale;
	private BigDecimal liquidatoAnnoPrecedente;

	private Integer numeroCapitolo;
	private Integer numeroArticolo;
	private String descrizioneCapitolo;

	private Integer annoProvvedimento;
	private String numeroProvvedimento;
	//private String codiceTipoProvvedimento;
	private String settoreProvvedimento;

	private Ente ente;
	private Fornitore fornitore;
	private List<Subimpegno> subimpegni = new ArrayList<>();

	// il calcolo del disponibile deve essere sempre calcolato in tempo reale
	// ed in consultazione non è necessario andare a fare chiamate ai servizi, che rallentano
	private BigDecimal disponibile;

	// piano dei conti
	private String pdcCodice;
	private String pdcDescrizione;

	private BigDecimal importo;
	private BigDecimal importoRipartito;
	private BigDecimal importoSospeso;
	private BigDecimal importoLiquidato;

	private Integer annoImpegnoRiaccertato;
	private Integer numImpegnoRiaccertato;
	private BigDecimal disponibilitaLiquidare;

	private String cig;
//	private String motivazioneAssenzaCig; // quando il servizio siac lo restituirà
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
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
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
	 * @return the importoAttuale
	 */
	public BigDecimal getImportoAttuale() {
		return importoAttuale;
	}

	/**
	 * @param importoAttuale the importoAttuale to set
	 */
	public void setImportoAttuale(BigDecimal importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	/**
	 * @return the importoIniziale
	 */
	public BigDecimal getImportoIniziale() {
		return importoIniziale;
	}

	/**
	 * @param importoIniziale the importoIniziale to set
	 */
	public void setImportoIniziale(BigDecimal importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	/**
	 * @return the liquidatoAnnoPrecedente
	 */
	public BigDecimal getLiquidatoAnnoPrecedente() {
		return liquidatoAnnoPrecedente;
	}

	/**
	 * @param liquidatoAnnoPrecedente the liquidatoAnnoPrecedente to set
	 */
	public void setLiquidatoAnnoPrecedente(BigDecimal liquidatoAnnoPrecedente) {
		this.liquidatoAnnoPrecedente = liquidatoAnnoPrecedente;
	}

	/**
	 * @return the numeroCapitolo
	 */
	public Integer getNumeroCapitolo() {
		return numeroCapitolo;
	}

	/**
	 * @param numeroCapitolo the numeroCapitolo to set
	 */
	public void setNumeroCapitolo(Integer numeroCapitolo) {
		this.numeroCapitolo = numeroCapitolo;
	}

	/**
	 * @return the numeroArticolo
	 */
	public Integer getNumeroArticolo() {
		return numeroArticolo;
	}

	/**
	 * @param numeroArticolo the numeroArticolo to set
	 */
	public void setNumeroArticolo(Integer numeroArticolo) {
		this.numeroArticolo = numeroArticolo;
	}

	/**
	 * @return the descrizioneCapitolo
	 */
	public String getDescrizioneCapitolo() {
		return descrizioneCapitolo;
	}

	/**
	 * @param descrizioneCapitolo the descrizioneCapitolo to set
	 */
	public void setDescrizioneCapitolo(String descrizioneCapitolo) {
		this.descrizioneCapitolo = descrizioneCapitolo;
	}

	/**
	 * @return the annoProvvedimento
	 */
	public Integer getAnnoProvvedimento() {
		return annoProvvedimento;
	}

	/**
	 * @param annoProvvedimento the annoProvvedimento to set
	 */
	public void setAnnoProvvedimento(Integer annoProvvedimento) {
		this.annoProvvedimento = annoProvvedimento;
	}

	/**
	 * @return the numeroProvvedimento
	 */
	public String getNumeroProvvedimento() {
		return numeroProvvedimento;
	}

	/**
	 * @param numeroProvvedimento the numeroProvvedimento to set
	 */
	public void setNumeroProvvedimento(String numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}

	public String getSettoreProvvedimento() {
		return settoreProvvedimento;
	}

	public void setSettoreProvvedimento(String settoreProvvedimento) {
		this.settoreProvvedimento = settoreProvvedimento;
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
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	/**
	 * @return the subimpegni
	 */
	public List<Subimpegno> getSubimpegni() {
		return subimpegni;
	}

	/**
	 * @param subimpegni the subimpegni to set
	 */
	public void setSubimpegni(List<Subimpegno> subimpegni) {
		this.subimpegni = subimpegni != null ? subimpegni : new ArrayList<>();
	}

	/**
	 * @return the pdcCodice
	 */
	public String getPdcCodice() {
		return pdcCodice;
	}

	/**
	 * @param pdcCodice the pdcCodice to set
	 */
	public void setPdcCodice(String pdcCodice) {
		this.pdcCodice = pdcCodice;
	}

	/**
	 * @return the pdcDescrizione
	 */
	public String getPdcDescrizione() {
		return pdcDescrizione;
	}

	/**
	 * @param pdcDescrizione the pdcDescrizione to set
	 */
	public void setPdcDescrizione(String pdcDescrizione) {
		this.pdcDescrizione = pdcDescrizione;
	}

	/**
	 * @return the disponibile
	 */
	public BigDecimal getDisponibile() {
		return disponibile;
	}

	/**
	 * @param disponibile the disponibile to set
	 */
	public void setDisponibile(BigDecimal disponibile) {
		this.disponibile = disponibile;
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
	 * @return the importoRipartito
	 */
	public BigDecimal getImportoRipartito() {
		return importoRipartito;
	}

	/**
	 * @param importoRipartito the importoRipartito to set
	 */
	public void setImportoRipartito(BigDecimal importoRipartito) {
		this.importoRipartito = importoRipartito;
	}

	/**
	 * @return the importoSospeso
	 */
	public BigDecimal getImportoSospeso() {
		return importoSospeso;
	}

	/**
	 * @param importoSospeso the importoSospeso to set
	 */
	public void setImportoSospeso(BigDecimal importoSospeso) {
		this.importoSospeso = importoSospeso;
	}

	/**
	 * @return the importoLiquidato
	 */
	public BigDecimal getImportoLiquidato() {
		return importoLiquidato;
	}

	/**
	 * @param importoLiquidato the importoLiquidato to set
	 */
	public void setImportoLiquidato(BigDecimal importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	/**
	 * @return the annoImpegnoRiaccertato
	 */
	public Integer getAnnoImpegnoRiaccertato() {
		return annoImpegnoRiaccertato;
	}

	/**
	 * @param annoImpegnoRiaccertato the annoImpegnoRiaccertato to set
	 */
	public void setAnnoImpegnoRiaccertato(Integer annoImpegnoRiaccertato) {
		this.annoImpegnoRiaccertato = annoImpegnoRiaccertato;
	}

	/**
	 * @return the numImpegnoRiaccertato
	 */
	public Integer getNumImpegnoRiaccertato() {
		return numImpegnoRiaccertato;
	}

	/**
	 * @param numImpegnoRiaccertato the numImpegnoRiaccertato to set
	 */
	public void setNumImpegnoRiaccertato(Integer numImpegnoRiaccertato) {
		this.numImpegnoRiaccertato = numImpegnoRiaccertato;
	}

	/**
	 * @return the disponibilitaLiquidare
	 */
	public BigDecimal getDisponibilitaLiquidare() {
		return disponibilitaLiquidare;
	}

	/**
	 * @param disponibilitaLiquidare the disponibilitaLiquidare to set
	 */
	public void setDisponibilitaLiquidare(BigDecimal disponibilitaLiquidare) {
		this.disponibilitaLiquidare = disponibilitaLiquidare;
	}

	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}


/*
	public String getCodiceTipoProvvedimento() {
		return codiceTipoProvvedimento;
	}

	public void setCodiceTipoProvvedimento(String codiceTipoProvvedimento) {
		this.codiceTipoProvvedimento = codiceTipoProvvedimento;
	}
*/
	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Impegno impegno = (Impegno) o;
		return Objects.equals(anno, impegno.anno) && Objects.equals(numero, impegno.numero);
	}

}
