/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;
import java.io.Serializable;
import java.math.BigDecimal;


public class OdsDatiContabili extends BaseAuditedDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal prezzoMassimo;

	private BigDecimal prezzoMinimo;
	private BigDecimal ultimoPrezzo;
	private Ods oggettiSpesa;

	public OdsDatiContabili() {
	}


	public BigDecimal getPrezzoMassimo() {
		return this.prezzoMassimo;
	}

	public void setPrezzoMassimo(BigDecimal prezzoMassimo) {
		this.prezzoMassimo = prezzoMassimo;
	}

	public BigDecimal getPrezzoMinimo() {
		return this.prezzoMinimo;
	}

	public void setPrezzoMinimo(BigDecimal prezzoMinimo) {
		this.prezzoMinimo = prezzoMinimo;
	}

	public BigDecimal getUltimoPrezzo() {
		return this.ultimoPrezzo;
	}

	public void setUltimoPrezzo(BigDecimal ultimoPrezzo) {
		this.ultimoPrezzo = ultimoPrezzo;
	}


	/**
	 * @return the oggettiSpesa
	 */
	public Ods getOggettiSpesa() {
		return oggettiSpesa;
	}


	/**
	 * @param oggettiSpesa the oggettiSpesa to set
	 */
	public void setOggettiSpesa(Ods oggettiSpesa) {
		this.oggettiSpesa = oggettiSpesa;
	}


}
