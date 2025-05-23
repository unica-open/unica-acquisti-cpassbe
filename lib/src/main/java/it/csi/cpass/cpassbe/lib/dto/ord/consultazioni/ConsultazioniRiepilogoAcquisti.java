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

public class ConsultazioniRiepilogoAcquisti  implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 44218827610311579L;

	private BigDecimal  ordinatoComplessivo;
	private BigDecimal  ordinatoComplessivoSuImpegni;
	private BigDecimal  ordinatoComplessivoDisponibile;
	private BigDecimal  ordinatoComplessivoSuImpegniResidui;
	private BigDecimal  ordinatoComplessivoSuImpegniCompetenza;
	private BigDecimal  ordinatoComplessivoSuImpegniAnnoSuccessivo;
	private BigDecimal  ordinatoComplessivoDisponibileResidui;
	private BigDecimal  ordinatoComplessivoDisponibileCompetenza;
	private BigDecimal  ordinatoComplessivoDisponibileAnnoSuccessivo;
	private BigDecimal  ordinatoComplessivoAnniPrecedenti;
	private BigDecimal  ordinatoComplessivoAnniPrecedentiSuImpegni;
	private BigDecimal  ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq;
	private BigDecimal  ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio;
	/**
	 * @return the ordinatoComplessivo
	 */
	public BigDecimal getOrdinatoComplessivo() {
		return ordinatoComplessivo;
	}
	/**
	 * @param ordinatoComplessivo the ordinatoComplessivo to set
	 */
	public void setOrdinatoComplessivo(BigDecimal ordinatoComplessivo) {
		this.ordinatoComplessivo = ordinatoComplessivo;
	}
	/**
	 * @return the ordinatoComplessivoSuImpegni
	 */
	public BigDecimal getOrdinatoComplessivoSuImpegni() {
		return ordinatoComplessivoSuImpegni;
	}
	/**
	 * @param ordinatoComplessivoSuImpegni the ordinatoComplessivoSuImpegni to set
	 */
	public void setOrdinatoComplessivoSuImpegni(BigDecimal ordinatoComplessivoSuImpegni) {
		this.ordinatoComplessivoSuImpegni = ordinatoComplessivoSuImpegni;
	}
	/**
	 * @return the ordinatoComplessivoDisponibile
	 */
	public BigDecimal getOrdinatoComplessivoDisponibile() {
		return ordinatoComplessivoDisponibile;
	}
	/**
	 * @param ordinatoComplessivoDisponibile the ordinatoComplessivoDisponibile to set
	 */
	public void setOrdinatoComplessivoDisponibile(BigDecimal ordinatoComplessivoDisponibile) {
		this.ordinatoComplessivoDisponibile = ordinatoComplessivoDisponibile;
	}
	/**
	 * @return the ordinatoComplessivoSuImpegniResidui
	 */
	public BigDecimal getOrdinatoComplessivoSuImpegniResidui() {
		return ordinatoComplessivoSuImpegniResidui;
	}
	/**
	 * @param ordinatoComplessivoSuImpegniResidui the ordinatoComplessivoSuImpegniResidui to set
	 */
	public void setOrdinatoComplessivoSuImpegniResidui(BigDecimal ordinatoComplessivoSuImpegniResidui) {
		this.ordinatoComplessivoSuImpegniResidui = ordinatoComplessivoSuImpegniResidui;
	}
	/**
	 * @return the ordinatoComplessivoSuImpegniCompetenza
	 */
	public BigDecimal getOrdinatoComplessivoSuImpegniCompetenza() {
		return ordinatoComplessivoSuImpegniCompetenza;
	}
	/**
	 * @param ordinatoComplessivoSuImpegniCompetenza the ordinatoComplessivoSuImpegniCompetenza to set
	 */
	public void setOrdinatoComplessivoSuImpegniCompetenza(BigDecimal ordinatoComplessivoSuImpegniCompetenza) {
		this.ordinatoComplessivoSuImpegniCompetenza = ordinatoComplessivoSuImpegniCompetenza;
	}
	/**
	 * @return the ordinatoComplessivoSuImpegniAnnoSuccessivo
	 */
	public BigDecimal getOrdinatoComplessivoSuImpegniAnnoSuccessivo() {
		return ordinatoComplessivoSuImpegniAnnoSuccessivo;
	}
	/**
	 * @param ordinatoComplessivoSuImpegniAnnoSuccessivo the ordinatoComplessivoSuImpegniAnnoSuccessivo to set
	 */
	public void setOrdinatoComplessivoSuImpegniAnnoSuccessivo(BigDecimal ordinatoComplessivoSuImpegniAnnoSuccessivo) {
		this.ordinatoComplessivoSuImpegniAnnoSuccessivo = ordinatoComplessivoSuImpegniAnnoSuccessivo;
	}
	/**
	 * @return the ordinatoComplessivoDisponibileResidui
	 */
	public BigDecimal getOrdinatoComplessivoDisponibileResidui() {
		return ordinatoComplessivoDisponibileResidui;
	}
	/**
	 * @param ordinatoComplessivoDisponibileResidui the ordinatoComplessivoDisponibileResidui to set
	 */
	public void setOrdinatoComplessivoDisponibileResidui(BigDecimal ordinatoComplessivoDisponibileResidui) {
		this.ordinatoComplessivoDisponibileResidui = ordinatoComplessivoDisponibileResidui;
	}
	/**
	 * @return the ordinatoComplessivoDisponibileCompetenza
	 */
	public BigDecimal getOrdinatoComplessivoDisponibileCompetenza() {
		return ordinatoComplessivoDisponibileCompetenza;
	}
	/**
	 * @param ordinatoComplessivoDisponibileCompetenza the ordinatoComplessivoDisponibileCompetenza to set
	 */
	public void setOrdinatoComplessivoDisponibileCompetenza(BigDecimal ordinatoComplessivoDisponibileCompetenza) {
		this.ordinatoComplessivoDisponibileCompetenza = ordinatoComplessivoDisponibileCompetenza;
	}
	/**
	 * @return the ordinatoComplessivoDisponibileAnnoSuccessivo
	 */
	public BigDecimal getOrdinatoComplessivoDisponibileAnnoSuccessivo() {
		return ordinatoComplessivoDisponibileAnnoSuccessivo;
	}
	/**
	 * @param ordinatoComplessivoDisponibileAnnoSuccessivo the ordinatoComplessivoDisponibileAnnoSuccessivo to set
	 */
	public void setOrdinatoComplessivoDisponibileAnnoSuccessivo(BigDecimal ordinatoComplessivoDisponibileAnnoSuccessivo) {
		this.ordinatoComplessivoDisponibileAnnoSuccessivo = ordinatoComplessivoDisponibileAnnoSuccessivo;
	}
	/**
	 * @return the ordinatoComplessivoAnniPrecedenti
	 */
	public BigDecimal getOrdinatoComplessivoAnniPrecedenti() {
		return ordinatoComplessivoAnniPrecedenti;
	}
	/**
	 * @param ordinatoComplessivoAnniPrecedenti the ordinatoComplessivoAnniPrecedenti to set
	 */
	public void setOrdinatoComplessivoAnniPrecedenti(BigDecimal ordinatoComplessivoAnniPrecedenti) {
		this.ordinatoComplessivoAnniPrecedenti = ordinatoComplessivoAnniPrecedenti;
	}
	/**
	 * @return the ordinatoComplessivoAnniPrecedentiSuImpegni
	 */
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegni() {
		return ordinatoComplessivoAnniPrecedentiSuImpegni;
	}
	/**
	 * @param ordinatoComplessivoAnniPrecedentiSuImpegni the ordinatoComplessivoAnniPrecedentiSuImpegni to set
	 */
	public void setOrdinatoComplessivoAnniPrecedentiSuImpegni(BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegni) {
		this.ordinatoComplessivoAnniPrecedentiSuImpegni = ordinatoComplessivoAnniPrecedentiSuImpegni;
	}
	/**
	 * @return the ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq
	 */
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq() {
		return ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq;
	}
	/**
	 * @param ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq the ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq to set
	 */
	public void setOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq(
			BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq) {
		this.ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq = ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq;
	}
	/**
	 * @return the ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio
	 */
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio() {
		return ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio;
	}
	/**
	 * @param ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio the ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio to set
	 */
	public void setOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio(
			BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio) {
		this.ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio = ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio;
	}



}
