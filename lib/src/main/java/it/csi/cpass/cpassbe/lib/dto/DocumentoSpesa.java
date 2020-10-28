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
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * The Class DocumentoSpesa.
 */
public class DocumentoSpesa extends BaseDto<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;
    protected Integer annoProtocollo;
    protected String numeroProtocollo;
    protected String registroProtocollo;
	protected Integer annoDocumento;
    protected String numeroDocumento;
    protected String tipoDocumento;
    protected String codiceFornitore;
    protected String codiceStato;
    protected String descrizioneStato;
    protected BigDecimal totaleDocumento;
    protected BigDecimal totaleLiquidabileDocumento;
    protected List<String> numeriOrdine;
    /**
	 * @return the annoProtocollo
	 */
	public Integer getAnnoProtocollo() {
		return annoProtocollo;
	}
	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(Integer annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}
	/**
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	/**
	 * @return the registroProtocollo
	 */
	public String getRegistroProtocollo() {
		return registroProtocollo;
	}
	/**
	 * @param registroProtocollo the registroProtocollo to set
	 */
	public void setRegistroProtocollo(String registroProtocollo) {
		this.registroProtocollo = registroProtocollo;
	}
	/**
	 * @return the annoDocumento
	 */
	public Integer getAnnoDocumento() {
		return annoDocumento;
	}
	/**
	 * @param annoDocumento the annoDocumento to set
	 */
	public void setAnnoDocumento(Integer annoDocumento) {
		this.annoDocumento = annoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * @return the codiceStato
	 */
	public String getCodiceStato() {
		return codiceStato;
	}
	/**
	 * @param codiceStato the codiceStato to set
	 */
	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}
	/**
	 * @return the descrizioneStato
	 */
	public String getDescrizioneStato() {
		return descrizioneStato;
	}
	/**
	 * @param descrizioneStato the descrizioneStato to set
	 */
	public void setDescrizioneStato(String descrizioneStato) {
		this.descrizioneStato = descrizioneStato;
	}
	/**
	 * @return the totaleDocumento
	 */
	public BigDecimal getTotaleDocumento() {
		return totaleDocumento;
	}
	/**
	 * @param totaleDocumento the totaleDocumento to set
	 */
	public void setTotaleDocumento(BigDecimal totaleDocumento) {
		this.totaleDocumento = totaleDocumento;
	}
	/**
	 * @return the totaleLiquidabileDocumento
	 */
	public BigDecimal getTotaleLiquidabileDocumento() {
		return totaleLiquidabileDocumento;
	}
	/**
	 * @param totaleLiquidabileDocumento the totaleLiquidabileDocumento to set
	 */
	public void setTotaleLiquidabileDocumento(BigDecimal totaleLiquidabileDocumento) {
		this.totaleLiquidabileDocumento = totaleLiquidabileDocumento;
	}
	/**
	 * @return the numeriOrdine
	 */
	public List<String> getNumeriOrdine() {
		return numeriOrdine;
	}
	/**
	 * @param numeriOrdine the numeriOrdine to set
	 */
	public void setNumeriOrdine(List<String> numeriOrdine) {
		this.numeriOrdine = numeriOrdine;
	}
}
