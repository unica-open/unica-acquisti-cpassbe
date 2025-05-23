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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The persistent class for the cpass_t_pba_acquisti_cap_privati_da_trasmettere database table.
 *
 */
public class AcquistiCapPrivatiDaTrasmettere extends BaseDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer acquistiCapPrivatiDaTrasmettereId;

	private String codiceRisorsa;

	private String descrizioneRisorsa;

	private UUID interventoId;

	private UUID programmaId;

	private BigDecimal totCapitalePrivato;

	public Integer getAcquistiCapPrivatiDaTrasmettereId() {
		return this.acquistiCapPrivatiDaTrasmettereId;
	}

	public void setAcquistiCapPrivatiDaTrasmettereId(Integer acquistiCapPrivatiDaTrasmettereId) {
		this.acquistiCapPrivatiDaTrasmettereId = acquistiCapPrivatiDaTrasmettereId;
	}

	public String getCodiceRisorsa() {
		return this.codiceRisorsa;
	}

	public void setCodiceRisorsa(String codiceRisorsa) {
		this.codiceRisorsa = codiceRisorsa;
	}

	public String getDescrizioneRisorsa() {
		return this.descrizioneRisorsa;
	}

	public void setDescrizioneRisorsa(String descrizioneRisorsa) {
		this.descrizioneRisorsa = descrizioneRisorsa;
	}

	public UUID getInterventoId() {
		return this.interventoId;
	}

	public void setInterventoId(UUID interventoId) {
		this.interventoId = interventoId;
	}

	public BigDecimal getTotCapitalePrivato() {
		return this.totCapitalePrivato;
	}

	public void setTotCapitalePrivato(BigDecimal totCapitalePrivato) {
		this.totCapitalePrivato = totCapitalePrivato;
	}

	/**
	 * @return the programmaId
	 */
	public UUID getProgrammaId() {
		return programmaId;
	}

	/**
	 * @param programmaId the programmaId to set
	 */
	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}

}
