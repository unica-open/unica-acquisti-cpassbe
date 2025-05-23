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

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class SubimpegnoOrdine extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer impegnoAnno;
	private Integer impegnoAnnoEsercizio;
	private Integer impegnoNumero;
	private Integer subimpegnoAnno;
	private Subimpegno subimpegno;
	private BigDecimal subimpegnoImporto;
	private Integer subimpegnoNumero;
	private ImpegnoOrdine impegnoOrdine;

	public SubimpegnoOrdine() {
	}

	public SubimpegnoOrdine(UUID id) {
		super(id);
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
	 * @return the impegnoOrdine
	 */
	public ImpegnoOrdine getImpegnoOrdine() {
		return impegnoOrdine;
	}

	/**
	 * @param impegnoOrdine the impegnoOrdine to set
	 */
	public void setImpegnoOrdine(ImpegnoOrdine impegnoOrdine) {
		this.impegnoOrdine = impegnoOrdine;
	}

}
