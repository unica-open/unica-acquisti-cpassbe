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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;



public class InterventoCig extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer interventoCigId;

	private String cig;

	private Intervento intervento;

	public InterventoCig() {
	}

	/**
	 * @return the interventoCigId
	 */
	public Integer getInterventoCigId() {
		return interventoCigId;
	}

	/**
	 * @param interventoCigId the interventoCigId to set
	 */
	public void setInterventoCigId(Integer interventoCigId) {
		this.interventoCigId = interventoCigId;
	}

	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}

	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}

	/**
	 * @param intervento the intervento to set
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}



}
