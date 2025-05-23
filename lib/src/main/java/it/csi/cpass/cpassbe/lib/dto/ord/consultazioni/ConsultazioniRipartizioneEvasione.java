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
import java.util.Date;

public class ConsultazioniRipartizioneEvasione  implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -7690473893444689507L;

	private Integer annoEsercizio;
	private Integer 	annoImpegno;
	private Integer 	numeroImpegno;
	private Integer 	annoSubImpegno;
	private Integer 	numeroSubImpegno;
	private BigDecimal  ripartito;
	private BigDecimal  sospeso;
	private String  	causaleSospensione;
	private Date     	dataSospensione;
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
	 * @return the ripartito
	 */
	public BigDecimal getRipartito() {
		return ripartito;
	}
	/**
	 * @param ripartito the ripartito to set
	 */
	public void setRipartito(BigDecimal ripartito) {
		this.ripartito = ripartito;
	}
	/**
	 * @return the sospeso
	 */
	public BigDecimal getSospeso() {
		return sospeso;
	}
	/**
	 * @param sospeso the sospeso to set
	 */
	public void setSospeso(BigDecimal sospeso) {
		this.sospeso = sospeso;
	}
	/**
	 * @return the causaleSospensione
	 */
	public String getCausaleSospensione() {
		return causaleSospensione;
	}
	/**
	 * @param causaleSospensione the causaleSospensione to set
	 */
	public void setCausaleSospensione(String causaleSospensione) {
		this.causaleSospensione = causaleSospensione;
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
