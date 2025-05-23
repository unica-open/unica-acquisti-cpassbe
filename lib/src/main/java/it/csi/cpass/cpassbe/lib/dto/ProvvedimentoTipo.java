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
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.List;


public class ProvvedimentoTipo extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codice;

	private String descrizione;

	private List<Provvedimento> provvedimentos;

	private Ente ente;

	public ProvvedimentoTipo() {
	}



	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}



	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}



	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}



	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}



	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}



	/**
	 * @return the provvedimentos
	 */
	public List<Provvedimento> getProvvedimentos() {
		return provvedimentos;
	}

	/**
	 * @param provvedimentos the provvedimentos to set
	 */
	public void setProvvedimentos(List<Provvedimento> provvedimentos) {
		this.provvedimentos = provvedimentos;
	}


}
