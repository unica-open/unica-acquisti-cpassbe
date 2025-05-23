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
package it.csi.cpass.cpassbe.lib.dto.batch;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class VerificaInvioEvasioneInContabilità.
 */
public class VerificaInvioEvasioneInContabilita extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String statoElaborazionEsterno;
	private String esitoEsterno;
	private String codiceErrore;
	private String descrizioneErrore;
	/**
	 * @return the statoElaborazionEsterno
	 */
	public String getStatoElaborazionEsterno() {
		return statoElaborazionEsterno;
	}
	/**
	 * @param statoElaborazionEsterno the statoElaborazionEsterno to set
	 */
	public void setStatoElaborazionEsterno(String statoElaborazionEsterno) {
		this.statoElaborazionEsterno = statoElaborazionEsterno;
	}
	/**
	 * @return the esitoEsterno
	 */
	public String getEsitoEsterno() {
		return esitoEsterno;
	}
	/**
	 * @param esitoEsterno the esitoEsterno to set
	 */
	public void setEsitoEsterno(String esitoEsterno) {
		this.esitoEsterno = esitoEsterno;
	}
	/**
	 * @return the codiceErrore
	 */
	public String getCodiceErrore() {
		return codiceErrore;
	}
	/**
	 * @param codiceErrore the codiceErrore to set
	 */
	public void setCodiceErrore(String codiceErrore) {
		this.codiceErrore = codiceErrore;
	}
	/**
	 * @return the descrizioneErrore
	 */
	public String getDescrizioneErrore() {
		return descrizioneErrore;
	}
	/**
	 * @param descrizioneErrore the descrizioneErrore to set
	 */
	public void setDescrizioneErrore(String descrizioneErrore) {
		this.descrizioneErrore = descrizioneErrore;
	}



}
