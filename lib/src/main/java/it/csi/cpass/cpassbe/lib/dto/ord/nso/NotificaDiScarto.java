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
import java.util.Map;

/**
 * @author Daniela Tarantino
 *
 */
public class NotificaDiScarto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String xml;
	private String identificativo;
	private String nomeFile;
	private String dataOraRicezione;
    private String messageId;
    private String note;
    private Map<String,String> listaErrori;


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
	 * @return the idenitificativo
	 */
	public String getIdentificativo() {
		return identificativo;
	}
	/**
	 * @param idenitificativo the idenitificativo to set
	 */
	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}
	/**
	 * @return the nomeFile
	 */
	public String getNomeFile() {
		return nomeFile;
	}
	/**
	 * @param nomeFile the nomeFile to set
	 */
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	/**
	 * @return the dataOraRicezione
	 */
	public String getDataOraRicezione() {
		return dataOraRicezione;
	}
	/**
	 * @param dataOraRicezione the dataOraRicezione to set
	 */
	public void setDataOraRicezione(String dataOraRicezione) {
		this.dataOraRicezione = dataOraRicezione;
	}
	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the listaErrori
	 */
	public Map<String, String> getListaErrori() {
		return listaErrori;
	}
	/**
	 * @param listaErrori the listaErrori to set
	 */
	public void setListaErrori(Map<String, String> listaErrori) {
		this.listaErrori = listaErrori;
	}


}
