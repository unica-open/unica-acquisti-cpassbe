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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Stato;

public class RicercaDdt implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7323488505304478597L;
	private Integer ordineNumero;
	private Integer ordineAnno;
	private Stato statoDdt;
	private Date dataConsegna;
	private Fornitore fornitore;
	private UUID idSettore;

	public Integer getOrdineNumero() {
		return ordineNumero;
	}

	public void setOrdineNumero(Integer ordineNumero) {
		this.ordineNumero = ordineNumero;
	}

	public Integer getOrdineAnno() {
		return ordineAnno;
	}

	public void setOrdineAnno(Integer ordineAnno) {
		this.ordineAnno = ordineAnno;
	}

	public Stato getStatoDdt() {
		return statoDdt;
	}

	public void setStatoDdt(Stato statoDdt) {
		this.statoDdt = statoDdt;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public UUID getIdSettore() {
		return idSettore;
	}

	public void setIdSettore(UUID idSettore) {
		this.idSettore = idSettore;
	}
}
