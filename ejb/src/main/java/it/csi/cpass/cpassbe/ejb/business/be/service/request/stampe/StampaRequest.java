/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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
	private String nomeStampa;
	private String formatFile; 
	private List<String> listaParametri;
	
	
	/**
	 * Constructor
	 * @param nomeStampa 
	 * @param formatFile the format file
	 * @param listaParametri 
	 */
	public StampaRequest(String nomeStampa, String formatFile, List<String> listaParametri) {
		this.nomeStampa = nomeStampa;
		this.formatFile = formatFile;
		this.listaParametri = listaParametri;
	}


	/**
	 * @return the nomeStampa
	 */
	public String getNomeStampa() {
		return nomeStampa;
	}

	/**
	 * @return the formatFile
	 */
	public String getFormatFile() {
		return formatFile;
	}

	/**
	 * @return the listaParametri
	 */
	public List<String> getListaParametri() {
		return listaParametri;
	}

}
