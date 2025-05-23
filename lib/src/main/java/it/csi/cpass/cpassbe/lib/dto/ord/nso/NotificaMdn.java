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

public class NotificaMdn implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String xml;
	private String urn;
	private String numeroDocumento;
	private String recipientID;
	private String documentID;
    private String dataInvio;
    private String orarioInvio;


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
	 * @return the recipientID
	 */
	public String getRecipientID() {
		return recipientID;
	}
	/**
	 * @param recipientID the recipientID to set
	 */
	public void setRecipientID(String recipientID) {
		this.recipientID = recipientID;
	}
	/**
	 * @return the documentID
	 */
	public String getDocumentID() {
		return documentID;
	}
	/**
	 * @param documentID the documentID to set
	 */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	/**
	 * @return the dataInvio
	 */
	public String getDataInvio() {
		return dataInvio;
	}
	/**
	 * @param dataInvio the dataInvio to set
	 */
	public void setDataInvio(String dataInvio) {
		this.dataInvio = dataInvio;
	}
	/**
	 * @return the orarioInvio
	 */
	public String getOrarioInvio() {
		return orarioInvio;
	}
	/**
	 * @param orarioInvio the orarioInvio to set
	 */
	public void setOrarioInvio(String orarioInvio) {
		this.orarioInvio = orarioInvio;
	}



}
