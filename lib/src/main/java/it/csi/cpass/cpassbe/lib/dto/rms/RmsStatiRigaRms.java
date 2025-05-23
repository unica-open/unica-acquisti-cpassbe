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
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

public class RmsStatiRigaRms extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stato;

	private String motivazione;

	private RigaRms rigaRms;

	private Date dataModifica;

	private String utenteModifica;

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the motivazione
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione the motivazione to set
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

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



}
