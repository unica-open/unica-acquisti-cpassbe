/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.custom;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;


/**
 * The Class Cpv.
 */
public class StatoInterventoInfo extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String stato;
	
	private Date dataInserimento;
		
	private String utenteCognome;
		
	private String utenteNome;

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
	 * @return the dataInserimento
	 */
	public Date getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	/**
	 * @return the utenteCognome
	 */
	public String getUtenteCognome() {
		return utenteCognome;
	}

	/**
	 * @param utenteCognome the utenteCognome to set
	 */
	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = utenteCognome;
	}

	/**
	 * @return the utenteNome
	 */
	public String getUtenteNome() {
		return utenteNome;
	}

	/**
	 * @param utenteNome the utenteNome to set
	 */
	public void setUtenteNome(String utenteNome) {
		this.utenteNome = utenteNome;
	}



}
