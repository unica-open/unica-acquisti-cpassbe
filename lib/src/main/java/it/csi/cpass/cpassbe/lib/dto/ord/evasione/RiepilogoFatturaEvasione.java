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

import javax.persistence.Column;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;


public class RiepilogoFatturaEvasione extends BaseDto<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long riepilogoFatturaEvasioneId;
		
	private UUID testataEvasioneId;
	
	private Integer impegnoAnno;

	private Integer impegnoAnnoEsercizio;

	private Integer impegnoNumero;

	private BigDecimal ripartito;

	private BigDecimal sospeso;

	private Integer subimpegnoAnno;

	private Integer subimpegnoNumero;

	private String causaleSospensioneCodice;

	private String causaleSospensioneDescrizione;

	private Date dataSospensione;
	
	public RiepilogoFatturaEvasione() {
	}

	public Integer getImpegnoAnno() {
		return this.impegnoAnno;
	}

	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	public Integer getImpegnoAnnoEsercizio() {
		return this.impegnoAnnoEsercizio;
	}

	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	public Integer getImpegnoNumero() {
		return this.impegnoNumero;
	}

	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	public BigDecimal getRipartito() {
		return this.ripartito;
	}

	public void setRipartito(BigDecimal ripartito) {
		this.ripartito = ripartito;
	}

	public BigDecimal getSospeso() {
		return this.sospeso;
	}

	public void setSospeso(BigDecimal sospeso) {
		this.sospeso = sospeso;
	}

	public Integer getSubimpegnoAnno() {
		return this.subimpegnoAnno;
	}

	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	
	/**
	 * @return the riepilogoFatturaEvasioneId
	 */
	public Long getRiepilogoFatturaEvasioneId() {
		return riepilogoFatturaEvasioneId;
	}

	/**
	 * @param riepilogoFatturaEvasioneId the riepilogoFatturaEvasioneId to set
	 */
	public void setRiepilogoFatturaEvasioneId(Long riepilogoFatturaEvasioneId) {
		this.riepilogoFatturaEvasioneId = riepilogoFatturaEvasioneId;
	}

	/**
	 * @return the testataEvasioneId
	 */
	public UUID getTestataEvasioneId() {
		return testataEvasioneId;
	}

	/**
	 * @param testataEvasioneId the testataEvasioneId to set
	 */
	public void setTestataEvasioneId(UUID testataEvasioneId) {
		this.testataEvasioneId = testataEvasioneId;
	}

	/**
	 * @return the causaleSospensioneCodice
	 */
	public String getCausaleSospensioneCodice() {
		return causaleSospensioneCodice;
	}

	/**
	 * @param causaleSospensioneCodice the causaleSospensioneCodice to set
	 */
	public void setCausaleSospensioneCodice(String causaleSospensioneCodice) {
		this.causaleSospensioneCodice = causaleSospensioneCodice;
	}

	/**
	 * @return the causaleSospensioneDescrizione
	 */
	public String getCausaleSospensioneDescrizione() {
		return causaleSospensioneDescrizione;
	}

	/**
	 * @param causaleSospensioneDescrizione the causaleSospensioneDescrizione to set
	 */
	public void setCausaleSospensioneDescrizione(String causaleSospensioneDescrizione) {
		this.causaleSospensioneDescrizione = causaleSospensioneDescrizione;
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
