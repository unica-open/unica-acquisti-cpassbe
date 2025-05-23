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
import java.util.List;


/**
 * The Class RiepilogoImpegni.
 */
public class RiepilogoImpegni implements Serializable {//extends BaseDto<Long> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal totaleOrdineNoIVA;
	private BigDecimal totaleOrdineConIVA;
	private BigDecimal totaleImpegnato;
	private Boolean totaliCoerenti;
	private List<VOrdine> impegniOrdine;


	/**
	 * @return the totaleOrdineNoIVA
	 */
	public BigDecimal getTotaleOrdineNoIVA() {
		return totaleOrdineNoIVA;
	}



	/**
	 * @param totaleOrdineNoIVA the totaleOrdineNoIVA to set
	 */
	public void setTotaleOrdineNoIVA(BigDecimal totaleOrdineNoIVA) {
		this.totaleOrdineNoIVA = totaleOrdineNoIVA;
	}



	/**
	 * @return the totaleOrdineConIVA
	 */
	public BigDecimal getTotaleOrdineConIVA() {
		return totaleOrdineConIVA;
	}



	/**
	 * @param totaleOrdineConIVA the totaleOrdineConIVA to set
	 */
	public void setTotaleOrdineConIVA(BigDecimal totaleOrdineConIVA) {
		this.totaleOrdineConIVA = totaleOrdineConIVA;
	}



	/**
	 * @return the totaleImpegnato
	 */
	public BigDecimal getTotaleImpegnato() {
		return totaleImpegnato;
	}



	/**
	 * @param totaleImpegnato the totaleImpegnato to set
	 */
	public void setTotaleImpegnato(BigDecimal totaleImpegnato) {
		this.totaleImpegnato = totaleImpegnato;
	}


	/**
	 * @return the totaliCoerenti
	 */
	public Boolean getTotaliCoerenti() {
		return totaliCoerenti;
	}



	/**
	 * @param totaliCoerenti the totaliCoerenti to set
	 */
	public void setTotaliCoerenti(Boolean totaliCoerenti) {
		this.totaliCoerenti = totaliCoerenti;
	}



	/**
	 * @return the riepilogoImpegniOrdine
	 */
	public List<VOrdine> getImpegniOrdine() {
		return impegniOrdine;
	}



	/**
	 * @param riepilogoImpegniOrdine the riepilogoImpegniOrdine to set
	 */
	public void setImpegniOrdine(List<VOrdine> impegniOrdine) {
		this.impegniOrdine = impegniOrdine;
	}



}
