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

public class ConsultazioniRiepilogoFinanziario  implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1137877871423834407L;

	private BigDecimal  totaleResidui;
	private BigDecimal  totaleCompetenza;
	private BigDecimal  totaleAnnoSuccessivo;
	/**
	 * @return the totaleResidui
	 */
	public BigDecimal getTotaleResidui() {
		return totaleResidui;
	}
	/**
	 * @param totaleResidui the totaleResidui to set
	 */
	public void setTotaleResidui(BigDecimal totaleResidui) {
		this.totaleResidui = totaleResidui;
	}
	/**
	 * @return the totaleCompetenza
	 */
	public BigDecimal getTotaleCompetenza() {
		return totaleCompetenza;
	}
	/**
	 * @param totaleCompetenza the totaleCompetenza to set
	 */
	public void setTotaleCompetenza(BigDecimal totaleCompetenza) {
		this.totaleCompetenza = totaleCompetenza;
	}
	/**
	 * @return the totaleAnnoSuccessivo
	 */
	public BigDecimal getTotaleAnnoSuccessivo() {
		return totaleAnnoSuccessivo;
	}
	/**
	 * @param totaleAnnoSuccessivo the totaleAnnoSuccessivo to set
	 */
	public void setTotaleAnnoSuccessivo(BigDecimal totaleAnnoSuccessivo) {
		this.totaleAnnoSuccessivo = totaleAnnoSuccessivo;
	}

}
