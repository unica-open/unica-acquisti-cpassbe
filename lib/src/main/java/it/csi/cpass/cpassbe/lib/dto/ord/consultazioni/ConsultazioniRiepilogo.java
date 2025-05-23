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

public class ConsultazioniRiepilogo  implements Serializable{

	private static final long serialVersionUID = -5908947947629050252L;

	private Integer annoEsercizio;
	private ConsultazioniRiepilogoFinanziario consultazioniRiepilogoFinanziario;
	private ConsultazioniRiepilogoAcquisti consultazioniRiepilogoAcquisti;
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
	 * @return the consultazioniRiepilogoFinanziario
	 */
	public ConsultazioniRiepilogoFinanziario getConsultazioniRiepilogoFinanziario() {
		return consultazioniRiepilogoFinanziario;
	}
	/**
	 * @param consultazioniRiepilogoFinanziario the consultazioniRiepilogoFinanziario to set
	 */
	public void setConsultazioniRiepilogoFinanziario(ConsultazioniRiepilogoFinanziario consultazioniRiepilogoFinanziario) {
		this.consultazioniRiepilogoFinanziario = consultazioniRiepilogoFinanziario;
	}
	/**
	 * @return the consultazioniRiepilogoAcquisti
	 */
	public ConsultazioniRiepilogoAcquisti getConsultazioniRiepilogoAcquisti() {
		return consultazioniRiepilogoAcquisti;
	}
	/**
	 * @param consultazioniRiepilogoAcquisti the consultazioniRiepilogoAcquisti to set
	 */
	public void setConsultazioniRiepilogoAcquisti(ConsultazioniRiepilogoAcquisti consultazioniRiepilogoAcquisti) {
		this.consultazioniRiepilogoAcquisti = consultazioniRiepilogoAcquisti;
	}

}
