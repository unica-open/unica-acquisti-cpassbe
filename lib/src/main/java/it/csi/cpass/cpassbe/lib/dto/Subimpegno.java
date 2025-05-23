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
import java.util.Objects;
import java.util.UUID;

/**
 * Impegno
 */
public class Subimpegno extends BaseAuditedDto<UUID> implements Serializable {

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
	private String settoreProvvedimento;

	private Ente ente;
	private Fornitore fornitore;
	private Impegno impegno;

	// il calcolo del disponibile deve essere sempre calcolato in tempo reale
	// ed in consultazione non è necessario andare a fare chiamate ai servizi, che rallentano
	private BigDecimal disponibile;

	private BigDecimal importo;
	private BigDecimal importoEvaso;
	private BigDecimal importoLiquidato;
	private BigDecimal disponibilitaLiquidare;

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

	/**
	 * @return the settoreProvvedimento
	 */
	public String getSettoreProvvedimento() {
		return settoreProvvedimento;
	}

	/**
	 * @param settoreProvvedimento the settoreProvvedimento to set
	 */
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
	 * @return the impegno
	 */
	public Impegno getImpegno() {
		return impegno;
	}

	/**
	 * @param impegno the impegno to set
	 */
	public void setImpegno(Impegno impegno) {
		this.impegno = impegno;
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
	 * @return the importoEvaso
	 */
	public BigDecimal getImportoEvaso() {
		return importoEvaso;
	}

	/**
	 * @param importoEvaso the importoEvaso to set
	 */
	public void setImportoEvaso(BigDecimal importoEvaso) {
		this.importoEvaso = importoEvaso;
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
		Subimpegno subimpegno = (Subimpegno) o;
		return Objects.equals(impegno.getAnno(), subimpegno.impegno.getAnno()) && Objects.equals(impegno.getNumero(), subimpegno.impegno.getNumero())
				&& Objects.equals(numero, subimpegno.numero);
	}

}
