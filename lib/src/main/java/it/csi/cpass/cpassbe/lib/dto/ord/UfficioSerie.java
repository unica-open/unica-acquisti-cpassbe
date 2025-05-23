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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;

public class UfficioSerie extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ufficioSerieId;

	private String uuidSerieActa;

	private Ufficio ufficio;

	/**
	 * @return the ufficioSerieId
	 */
	public Integer getUfficioSerieId() {
		return ufficioSerieId;
	}

	/**
	 * @param ufficioSerieId the ufficioSerieId to set
	 */
	public void setUfficioSerieId(Integer ufficioSerieId) {
		this.ufficioSerieId = ufficioSerieId;
	}

	/**
	 * @return the uuidSerieActa
	 */
	public String getUuidSerieActa() {
		return uuidSerieActa;
	}

	/**
	 * @param uuidSerieActa the uuidSerieActa to set
	 */
	public void setUuidSerieActa(String uuidSerieActa) {
		this.uuidSerieActa = uuidSerieActa;
	}

	/**
	 * @return the ufficio
	 */
	public Ufficio getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
	}


}
