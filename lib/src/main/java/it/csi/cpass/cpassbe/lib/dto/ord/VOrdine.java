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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

public class VOrdine extends BaseDto<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Long ordineId;

private UUID testataOrdineId;

//private UUID destinatarioId;

//private UUID rigaOrdineId;

//private UUID impegnoOrdineId;

private UUID impegnoId;

private UUID subimpegnoOrdineId;

private Integer numeroCapitolo;

private Integer numeroArticolo;

private Integer impegnoAnnoEsercizio;

private Integer impegnoAnno;

private Integer impegnoNumero;

private Integer subimpegnoAnno;

private Integer subimpegnoNumero;

private BigDecimal importoImpegno;

private BigDecimal subimpegnoImporto;



/**
 * @return the ordineId
 */
public Long getOrdineId() {
	return ordineId;
}

/**
 * @param ordineId the ordineId to set
 */
public void setOrdineId(Long ordineId) {
	this.ordineId = ordineId;
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
 * @return the importoImpegno
 */
public BigDecimal getImportoImpegno() {
	return importoImpegno;
}

/**
 * @param importoImpegno the importoImpegno to set
 */
public void setImportoImpegno(BigDecimal importoImpegno) {
	this.importoImpegno = importoImpegno;
}

/**
 * @return the subimpegnoImporto
 */
public BigDecimal getSubimpegnoImporto() {
	return subimpegnoImporto;
}

/**
 * @param subimpegnoImporto the subimpegnoImporto to set
 */
public void setSubimpegnoImporto(BigDecimal subimpegnoImporto) {
	this.subimpegnoImporto = subimpegnoImporto;
}

/**
 * @return the testataOrdineId
 */
public UUID getTestataOrdineId() {
	return testataOrdineId;
}

/**
 * @param testataOrdineId the testataOrdineId to set
 */
public void setTestataOrdineId(UUID testataOrdineId) {
	this.testataOrdineId = testataOrdineId;
}


/**
 * @return the subimpegnoOrdineId
 */
public UUID getSubimpegnoOrdineId() {
	return subimpegnoOrdineId;
}

/**
 * @param subimpegnoOrdineId the subimpegnoOrdineId to set
 */
public void setSubimpegnoOrdineId(UUID subimpegnoOrdineId) {
	this.subimpegnoOrdineId = subimpegnoOrdineId;
}

/**
 * @return the impegnoId
 */
public UUID getImpegnoId() {
	return impegnoId;
}

/**
 * @param impegnoId the impegnoId to set
 */
public void setImpegnoId(UUID impegnoId) {
	this.impegnoId = impegnoId;
}




}

