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
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Settore;


public class SettoreAooActa  extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date dataFineValidita;

	private AooActa aooActa;

	private Settore settore;

	public SettoreAooActa() {
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @return the aooActa
	 */
	public AooActa getAooActa() {
		return aooActa;
	}

	/**
	 * @param aooActa the aooActa to set
	 */
	public void setAooActa(AooActa aooActa) {
		this.aooActa = aooActa;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}



}
