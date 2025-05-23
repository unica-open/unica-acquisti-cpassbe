/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the stampa
 */
public class StampaRequest implements BaseRequest {
	private final String nomeStampaLogico;
	private String fileName;
	private final String formatFile;
	private final List<String> listaParametri;


	/**
	 * Constructor
	 * @param nomeStampa
	 * @param formatFile the format file
	 * @param listaParametri
	 */
	public StampaRequest(String nomeStampaLogico,String fileName, String formatFile, List<String> listaParametri) {
		this.nomeStampaLogico = nomeStampaLogico;
		this.fileName = fileName;
		this.formatFile = formatFile;
		this.listaParametri = listaParametri;
	}


	/**
	 * @return the nomeStampa
	 */
	public String getNomeStampaLogico() {
		return nomeStampaLogico;
	}

	/**
	 * @return the formatFile
	 */
	public String getFormatFile() {
		return formatFile;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * @return the listaParametri
	 */
	public List<String> getListaParametri() {
		return listaParametri;
	}

}
