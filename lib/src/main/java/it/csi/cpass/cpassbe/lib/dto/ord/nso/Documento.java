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

public class Documento implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String xml;
	private String metadati;
	private String orderId;
	private String orderDocumentReferenceId;
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
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @return the metadati
	 */
	public String getMetadati() {
		return metadati;
	}
	/**
	 * @param metadati the metadati to set
	 */
	public void setMetadati(String metadati) {
		this.metadati = metadati;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDocumenteReferenceId
	 */
	public String getOrderDocumentReferenceId() {
		return orderDocumentReferenceId;
	}
	/**
	 * @param orderDocumenteReferenceId the orderDocumenteReferenceId to set
	 */
	public void setOrderDocumentReferenceId(String orderDocumentReferenceId) {
		this.orderDocumentReferenceId = orderDocumentReferenceId;
	}



}
