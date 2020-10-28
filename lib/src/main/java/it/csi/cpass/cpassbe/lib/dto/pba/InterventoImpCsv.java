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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The intervento Impo Csv
 */
public class InterventoImpCsv extends BaseDto<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cui;
	private String risorsaTipo;
	private String risorsaCodice;
	/** The importo anno primo. */
	private String importoAnnoPrimo;
	/** The importo anno secondo. */
	private String importoAnnoSecondo;
	/** The importo anni successivi. */
	private String importoAnniSuccessivi;
	/**
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}
	/**
	 * @param cui the cui to set
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}
	/**
	 * @return the risorsaTipo
	 */
	public String getRisorsaTipo() {
		return risorsaTipo;
	}
	/**
	 * @param risorsaTipo the risorsaTipo to set
	 */
	public void setRisorsaTipo(String risorsaTipo) {
		this.risorsaTipo = risorsaTipo;
	}
	/**
	 * @return the risorsaCodice
	 */
	public String getRisorsaCodice() {
		return risorsaCodice;
	}
	/**
	 * @param risorsaCodice the risorsaCodice to set
	 */
	public void setRisorsaCodice(String risorsaCodice) {
		this.risorsaCodice = risorsaCodice;
	}
	/**
	 * @return the importoAnnoPrimo
	 */
	public String getImportoAnnoPrimo() {
		return importoAnnoPrimo;
	}
	/**
	 * @param importoAnnoPrimo the importoAnnoPrimo to set
	 */
	public void setImportoAnnoPrimo(String importoAnnoPrimo) {
		this.importoAnnoPrimo = importoAnnoPrimo;
	}
	/**
	 * @return the importoAnnoSecondo
	 */
	public String getImportoAnnoSecondo() {
		return importoAnnoSecondo;
	}
	/**
	 * @param importoAnnoSecondo the importoAnnoSecondo to set
	 */
	public void setImportoAnnoSecondo(String importoAnnoSecondo) {
		this.importoAnnoSecondo = importoAnnoSecondo;
	}
	/**
	 * @return the importoAnniSuccessivi
	 */
	public String getImportoAnniSuccessivi() {
		return importoAnniSuccessivi;
	}
	/**
	 * @param importoAnniSuccessivi the importoAnniSuccessivi to set
	 */
	public void setImportoAnniSuccessivi(String importoAnniSuccessivi) {
		this.importoAnniSuccessivi = importoAnniSuccessivi;
	}
	
	
	

}
