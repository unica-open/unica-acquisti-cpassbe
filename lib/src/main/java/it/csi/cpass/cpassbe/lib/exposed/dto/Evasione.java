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
package it.csi.cpass.cpassbe.lib.exposed.dto;

import java.util.List;

public class Evasione {

	private Integer codiceFornitore;
	private String codiceFruitore;
	private String codiceFiscaleEnte;
	private List<String> ordini;
	private List<Impegno> impegni;
	private String annoDocumento;
	private String numeroDocumento;
	private String tipoDocumento;

	/**
	 * @return the codiceFornitore
	 */
	public Integer getCodiceFornitore() {
		return codiceFornitore;
	}
	/**
	 * @param codiceFornitore the codiceFornitore to set
	 */
	public void setCodiceFornitore(Integer codiceFornitore) {
		this.codiceFornitore = codiceFornitore;
	}
	/**
	 * @return the codiceFruitore
	 */
	public String getCodiceFruitore() {
		return codiceFruitore;
	}
	/**
	 * @param codiceFruitore the codiceFruitore to set
	 */
	public void setCodiceFruitore(String codiceFruitore) {
		this.codiceFruitore = codiceFruitore;
	}

	/**
	 * @return the codiceFiscaleEnte
	 */
	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}
	/**
	 * @param codiceFiscaleEnte the codiceFiscaleEnte to set
	 */
	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}
	/**
	 * @return the ordini
	 */
	public List<String> getOrdini() {
		return ordini;
	}
	/**
	 * @param ordini the ordini to set
	 */
	public void setOrdini(List<String> ordini) {
		this.ordini = ordini;
	}
	/**
	 * @return the impegni
	 */
	public List<Impegno> getImpegni() {
		return impegni;
	}
	/**
	 * @param impegni the impegni to set
	 */
	public void setImpegni(List<Impegno> impegni) {
		this.impegni = impegni;
	}
	/**
	 * @return the annoDocumento
	 */
	public String getAnnoDocumento() {
		return annoDocumento;
	}
	/**
	 * @param annoDocumento the annoDocumento to set
	 */
	public void setAnnoDocumento(String annoDocumento) {
		this.annoDocumento = annoDocumento;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


}
