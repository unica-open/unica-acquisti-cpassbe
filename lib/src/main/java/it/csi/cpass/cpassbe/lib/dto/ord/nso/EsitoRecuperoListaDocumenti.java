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
package it.csi.cpass.cpassbe.lib.dto.ord.nso;

import java.io.Serializable;
import java.util.List;


public class EsitoRecuperoListaDocumenti implements Serializable {

	private static final long serialVersionUID = 7043471263487087944L;
	private List <DocumentoRichiesto> listaDocumentoRichiesto;

	private String codErrore;
	private String descErrore;
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
	/**
	 * @return the listaDocumentoRicevuto
	 */
	public List<DocumentoRichiesto> getListaDocumentoRichiesto() {
		return listaDocumentoRichiesto;
	}
	/**
	 * @param listaDocumentoRicevuto the listaDocumentoRicevuto to set
	 */
	public void setListaDocumentoRicevuto(List<DocumentoRichiesto> listaDocumentoRichiesto) {
		this.listaDocumentoRichiesto = listaDocumentoRichiesto;
	}



}
