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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;

public class SubimpegnoEvasione extends BaseAuditedDto<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	private DocumentoTrasporto documentoTrasporto;

	private Integer impegnoAnno;

	private Integer impegnoAnnoEsercizio;

	private Integer impegnoNumero;

	private Integer subimpegnoAnno;

	private BigDecimal importoRipartito;

	private BigDecimal importoSospeso;

	private BigDecimal importoLiquidato;

	private Integer subimpegnoNumero;

	private ImpegnoEvasione impegnoEvasione;

	private SubimpegnoOrdine subimpegnoOrdine;

	private Subimpegno subimpegno;

	private BigDecimal totaleRipartibile;

	private CausaleSospensioneEvasione causaleSospensioneEvasione;
	
	private Date dataSospensione;

	public SubimpegnoEvasione() {
	}

	/**
	 * @return the documentoTrasporto
	 */
	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}

	/**
	 * @param documentoTrasporto the documentoTrasporto to set
	 */
	public void setDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}

	/**
	 * @return the impegnoAnno
	 */
	public Integer getImpegnoAnno() {
		return impegnoAnno;
	}

	/**
	 * @param impegnoAnno the impegnoAnno to set
	 */
	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	/**
	 * @return the impegnoAnnoEsercizio
	 */
	public Integer getImpegnoAnnoEsercizio() {
		return impegnoAnnoEsercizio;
	}

	/**
	 * @param impegnoAnnoEsercizio the impegnoAnnoEsercizio to set
	 */
	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	/**
	 * @return the impegnoNumero
	 */
	public Integer getImpegnoNumero() {
		return impegnoNumero;
	}

	/**
	 * @param impegnoNumero the impegnoNumero to set
	 */
	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	/**
	 * @return the subimpegnoAnno
	 */
	public Integer getSubimpegnoAnno() {
		return subimpegnoAnno;
	}

	/**
	 * @param subimpegnoAnno the subimpegnoAnno to set
	 */
	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
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
	 * @return the subimpegnoNumero
	 */
	public Integer getSubimpegnoNumero() {
		return subimpegnoNumero;
	}

	/**
	 * @param subimpegnoNumero the subimpegnoNumero to set
	 */
	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	/**
	 * @return the impegnoEvasione
	 */
	public ImpegnoEvasione getImpegnoEvasione() {
		return impegnoEvasione;
	}

	/**
	 * @param impegnoEvasione the impegnoEvasione to set
	 */
	public void setImpegnoEvasione(ImpegnoEvasione impegnoEvasione) {
		this.impegnoEvasione = impegnoEvasione;
	}

	/**
	 * @return the subimpegno
	 */
	public Subimpegno getSubimpegno() {
		return subimpegno;
	}

	/**
	 * @param subimpegno the subimpegno to set
	 */
	public void setSubimpegno(Subimpegno subimpegno) {
		this.subimpegno = subimpegno;
	}

	/**
	 * @return the subimpegnoOrdine
	 */
	public SubimpegnoOrdine getSubimpegnoOrdine() {
		return subimpegnoOrdine;
	}

	/**
	 * @param subimpegnoOrdine the subimpegnoOrdine to set
	 */
	public void setSubimpegnoOrdine(SubimpegnoOrdine subimpegnoOrdine) {
		this.subimpegnoOrdine = subimpegnoOrdine;
	}

	/**
	 * @return the totaleRipartibile
	 */
	public BigDecimal getTotaleRipartibile() {
		return totaleRipartibile;
	}

	/**
	 * @param totaleRipartibile the totaleRipartibile to set
	 */
	public void setTotaleRipartibile(BigDecimal totaleRipartibile) {
		this.totaleRipartibile = totaleRipartibile;
	}

	/**
	 * @return the causaleSospensioneEvasione
	 */
	public CausaleSospensioneEvasione getCausaleSospensioneEvasione() {
		return causaleSospensioneEvasione;
	}

	/**
	 * @param causaleSospensioneEvasione the causaleSospensioneEvasione to set
	 */
	public void setCausaleSospensioneEvasione(CausaleSospensioneEvasione causaleSospensioneEvasione) {
		this.causaleSospensioneEvasione = causaleSospensioneEvasione;
	}

	/**
	 * @return the dataSospensione
	 */
	public Date getDataSospensione() {
		return dataSospensione;
	}

	/**
	 * @param dataSospensione the dataSospensione to set
	 */
	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
	}
	
}
