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

public class DocumentoRichiesto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String xml;
	private String urn;
	private String urnCollegato;

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}
	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}
	/**
	 * @return the urn
	 */
	public String getUrn() {
		return urn;
	}
	/**
	 * @param urn the urn to set
	 */
	public void setUrn(String urn) {
		this.urn = urn;
	}
	/**
	 * @return the urnCollegato
	 */
	public String getUrnCollegato() {
		return urnCollegato;
	}
	/**
	 * @param urnCollegato the urnCollegato to set
	 */
	public void setUrnCollegato(String urnCollegato) {
		this.urnCollegato = urnCollegato;
	}



}
