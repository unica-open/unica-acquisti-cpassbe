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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class DestinatarioInvioNso
 */
public class DestinatarioInvioNsoXml extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String dataConsegna;
	private String dataRicezione;
	private Date dataSpostamento;
	private String fileXml;
	private String metadatiXml;
	private String identificativoTrasmissione;
	private String note;
	private String pathFile;
	private String tipodoc;
	private DestinatarioInvioNso destinatarioInvioNso;

	public DestinatarioInvioNsoXml() {}


	/**
	 * @return the dataConsegna
	 */
	public String getDataConsegna() {
		return dataConsegna;
	}


	/**
	 * @param dataConsegna the dataConsegna to set
	 */
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}


	/**
	 * @return the dataRicezione
	 */
	public String getDataRicezione() {
		return dataRicezione;
	}


	/**
	 * @param dataRicezione the dataRicezione to set
	 */
	public void setDataRicezione(String dataRicezione) {
		this.dataRicezione = dataRicezione;
	}


	/**
	 * @return the dataSpostamento
	 */
	public Date getDataSpostamento() {
		return dataSpostamento;
	}


	/**
	 * @param dataSpostamento the dataSpostamento to set
	 */
	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}


	/**
	 * @return the fileXml
	 */
	public String getFileXml() {
		return fileXml;
	}


	/**
	 * @param fileXml the fileXml to set
	 */
	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}


	/**
	 * @return the metadatiXml
	 */
	public String getMetadatiXml() {
		return metadatiXml;
	}


	/**
	 * @param metadatiXml the metadatiXml to set
	 */
	public void setMetadatiXml(String metadatiXml) {
		this.metadatiXml = metadatiXml;
	}


	/**
	 * @return the identificativoTrasmissione
	 */
	public String getIdentificativoTrasmissione() {
		return identificativoTrasmissione;
	}


	/**
	 * @param identificativoTrasmissione the identificativoTrasmissione to set
	 */
	public void setIdentificativoTrasmissione(String identificativoTrasmissione) {
		this.identificativoTrasmissione = identificativoTrasmissione;
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
	 * @return the pathFile
	 */
	public String getPathFile() {
		return pathFile;
	}


	/**
	 * @param pathFile the pathFile to set
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}


	/**
	 * @return the tipodoc
	 */
	public String getTipodoc() {
		return tipodoc;
	}


	/**
	 * @param tipodoc the tipodoc to set
	 */
	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}


	/**
	 * @return the destinatarioInvioNso
	 */
	public DestinatarioInvioNso getDestinatarioInvioNso() {
		return destinatarioInvioNso;
	}


	/**
	 * @param destinatarioInvioNso the destinatarioInvioNso to set
	 */
	public void setDestinatarioInvioNso(DestinatarioInvioNso destinatarioInvioNso) {
		this.destinatarioInvioNso = destinatarioInvioNso;
	}


	@Override
	public String toString() {
		return new StringBuilder()
				.append("DestinatarioInvioNsoXml [")
				.append(", id=").append(id)
				.append("]").toString();
	}

}
