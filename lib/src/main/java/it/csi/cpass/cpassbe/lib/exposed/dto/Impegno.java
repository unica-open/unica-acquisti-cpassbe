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
package it.csi.cpass.cpassbe.lib.exposed.dto;

import java.math.BigDecimal;
// classe da generare ma diversa da quella del fruitore
public class Impegno {

	private Integer annoImpegno; // classe fruitore passa stringa
	private Integer numeroImpegno;
	private Integer annoSubimpegno;
	private Integer numeroSubimpegno; // numeroPrenotazione
	private BigDecimal importoQuota;


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
	 * @return the annoSubimpegno
	 */
	public Integer getAnnoSubimpegno() {
		return annoSubimpegno;
	}
	/**
	 * @param annoSubimpegno the annoSubimpegno to set
	 */
	public void setAnnoSubimpegno(Integer annoSubimpegno) {
		this.annoSubimpegno = annoSubimpegno;
	}
	/**
	 * @return the numeroSubimpegno
	 */
	public Integer getNumeroSubimpegno() {
		return numeroSubimpegno;
	}
	/**
	 * @param numeroSubimpegno the numeroSubimpegno to set
	 */
	public void setNumeroSubimpegno(Integer numeroSubimpegno) {
		this.numeroSubimpegno = numeroSubimpegno;
	}
	/**
	 * @return the importoQuota
	 */
	public BigDecimal getImportoQuota() {
		return importoQuota;
	}
	/**
	 * @param importoQuota the importoQuota to set
	 */
	public void setImportoQuota(BigDecimal importoQuota) {
		this.importoQuota = importoQuota;
	}

}
