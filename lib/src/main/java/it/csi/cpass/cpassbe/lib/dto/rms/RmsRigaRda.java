/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.rms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;

public class RmsRigaRda extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private RigaRms rigaRms;

	private RigaRda rigaRda;

	private Date dataModifica;

	private BigDecimal quantita;

	private String utenteModifica;


	/**
	 * @return the dataModifica
	 */
	public Date getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	/**
	 * @return the utenteModifica
	 */
	public String getUtenteModifica() {
		return utenteModifica;
	}

	/**
	 * @param utenteModifica the utenteModifica to set
	 */
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	/**
	 * @return the rigaRms
	 */
	public RigaRms getRigaRms() {
		return rigaRms;
	}

	/**
	 * @param rigaRms the rigaRms to set
	 */
	public void setRigaRms(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
	}

	/**
	 * @return the rigaRda
	 */
	public RigaRda getRigaRda() {
		return rigaRda;
	}

	/**
	 * @param rigaRda the rigaRda to set
	 */
	public void setRigaRda(RigaRda rigaRda) {
		this.rigaRda = rigaRda;
	}

	/**
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return quantita;
	}

	/**
	 * @param quantita the quantita to set
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}



}
