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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


public class AnteprimaOrdineSuEvasione implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private UUID id;
	private Integer anno; // ordine_anno
	private Integer numero; // ordine_numero
	private Date dataAutorizzazione; // data_autorizzazione
	private BigDecimal totaleConIva;
	private BigDecimal totaleSuEvasione;
	private String descrizione;
	private UUID idEvasione;


	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}
	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}
	public BigDecimal getTotaleConIva() {
		return totaleConIva;
	}
	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}
	public BigDecimal getTotaleSuEvasione() {
		return totaleSuEvasione;
	}
	public void setTotaleSuEvasione(BigDecimal totaleSuEvasione) {
		this.totaleSuEvasione = totaleSuEvasione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public UUID getIdEvasione() {
		return idEvasione;
	}
	public void setIdEvasione(UUID idEvasione) {
		this.idEvasione = idEvasione;
	}



}
