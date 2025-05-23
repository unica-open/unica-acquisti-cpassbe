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

import it.csi.cpass.cpassbe.lib.dto.Provvedimento;

public class FiltroImpegniNew implements Serializable {
	private static final long serialVersionUID = 6917368461093667542L;
	//private Impegno impegno;
	private Integer annoImpegno;
	private Integer numeroImpegno;
	private Provvedimento provvedimento;
	private String statoImpegno;
	private Integer annoEsercizio;
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
	 * @return the provvedimento
	 */
	public Provvedimento getProvvedimento() {
		return provvedimento;
	}
	/**
	 * @param provvedimento the provvedimento to set
	 */
	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}
	/**
	 * @return the statoImpegno
	 */
	public String getStatoImpegno() {
		return statoImpegno;
	}
	/**
	 * @param statoImpegno the statoImpegno to set
	 */
	public void setStatoImpegno(String statoImpegno) {
		this.statoImpegno = statoImpegno;
	}
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




}
