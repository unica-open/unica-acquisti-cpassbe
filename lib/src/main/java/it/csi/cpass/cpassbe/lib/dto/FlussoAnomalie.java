/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class FlussoAnomalie extends BaseDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -72742880724510919L;

	private Integer flussoAnomalieId;

	private String dataElaborazione;

	private UUID impegnoId;

	private BigDecimal importoAttuale;

	private BigDecimal liqAnnoPrecCalcolato;

	private UUID subimpegnoId;

	/**
	 * @return the flussoAnomalieId
	 */
	public Integer getFlussoAnomalieId() {
		return flussoAnomalieId;
	}

	/**
	 * @param flussoAnomalieId the flussoAnomalieId to set
	 */
	public void setFlussoAnomalieId(Integer flussoAnomalieId) {
		this.flussoAnomalieId = flussoAnomalieId;
	}

	/**
	 * @return the dataElaborazione
	 */
	public String getDataElaborazione() {
		return dataElaborazione;
	}

	/**
	 * @param dataElaborazione the dataElaborazione to set
	 */
	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	/**
	 * @return the impegnoId
	 */
	public UUID getImpegnoId() {
		return impegnoId;
	}

	/**
	 * @param impegnoId the impegnoId to set
	 */
	public void setImpegnoId(UUID impegnoId) {
		this.impegnoId = impegnoId;
	}

	/**
	 * @return the importoAttuale
	 */
	public BigDecimal getImportoAttuale() {
		return importoAttuale;
	}

	/**
	 * @param importoAttuale the importoAttuale to set
	 */
	public void setImportoAttuale(BigDecimal importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	/**
	 * @return the liqAnnoPrecCalcolato
	 */
	public BigDecimal getLiqAnnoPrecCalcolato() {
		return liqAnnoPrecCalcolato;
	}

	/**
	 * @param liqAnnoPrecCalcolato the liqAnnoPrecCalcolato to set
	 */
	public void setLiqAnnoPrecCalcolato(BigDecimal liqAnnoPrecCalcolato) {
		this.liqAnnoPrecCalcolato = liqAnnoPrecCalcolato;
	}

	/**
	 * @return the subimpegnoId
	 */
	public UUID getSubimpegnoId() {
		return subimpegnoId;
	}

	/**
	 * @param subimpegnoId the subimpegnoId to set
	 */
	public void setSubimpegnoId(UUID subimpegnoId) {
		this.subimpegnoId = subimpegnoId;
	}



}
