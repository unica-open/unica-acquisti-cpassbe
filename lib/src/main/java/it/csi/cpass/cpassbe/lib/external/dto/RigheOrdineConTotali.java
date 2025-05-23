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
package it.csi.cpass.cpassbe.lib.external.dto;

import java.math.BigDecimal;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class RigheOrdineConTotali {

	private List<RigaOrdine> rigaOrdines = null;
	private Impegno impegno = null; // Ok sino a che l'impegno considerato è il primo definito per la prima riga dell'elenco ( recupero CIG ), quindi comune a tutte le righe destinatario da inviare

	public BigDecimal taxAmount = new BigDecimal(0);
	public BigDecimal lineExtensionAmount = new BigDecimal(0);
	public BigDecimal allowanceTotalAmount = new BigDecimal(0);
	public BigDecimal taxExclusiveAmount = new BigDecimal(0);
	public BigDecimal taxInclusiveAmount = new BigDecimal(0);
	public BigDecimal payableAmount = new BigDecimal(0);

	/**
	 * @return the rigaOrdines
	 */
	public List<RigaOrdine> getRigaOrdines() {
		return rigaOrdines;
	}

	/**
	 * @param rigaOrdines the rigaOrdines to set
	 */
	public void setRigaOrdines(List<RigaOrdine> rigaOrdines) {
		this.rigaOrdines = rigaOrdines;
	}

	/**
	 * @return the impegno
	 */
	public Impegno getImpegno() {
		return impegno;
	}

	/**
	 * @param impegno the impegno to set
	 */
	public void setImpegno(Impegno impegno) {
		this.impegno = impegno;
	}

}
