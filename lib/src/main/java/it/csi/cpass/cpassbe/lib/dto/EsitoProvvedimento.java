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


public class EsitoProvvedimento implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private List <Provvedimento> lista;

	private String codErrore;
	private String descErrore;
	/**
	 * @return the lista
	 */
	public List<Provvedimento> getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<Provvedimento> lista) {
		this.lista = lista;
	}
	/**
	 * @return the codErrore
	 */
	public String getCodErrore() {
		return codErrore;
	}
	/**
	 * @param codErrore the codErrore to set
	 */
	public void setCodErrore(String codErrore) {
		this.codErrore = codErrore;
	}
	/**
	 * @return the descErrore
	 */
	public String getDescErrore() {
		return descErrore;
	}
	/**
	 * @param descErrore the descErrore to set
	 */
	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
	}




}
