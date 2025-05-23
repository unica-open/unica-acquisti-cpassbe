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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

public class DocumentoTrasportoXML extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataConsegna;
	private Date   dataSpostamento;
	private String despatchAdviceId;
	private String fileXml;
	private String note;
	private String pathFile;
	private String tipodoc;
	private DocumentoTrasporto documentoTrasporto;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Date getDataSpostamento() {
		return dataSpostamento;
	}

	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public String getDespatchAdviceId() {
		return despatchAdviceId;
	}

	public void setDespatchAdviceId(String despatchAdviceId) {
		this.despatchAdviceId = despatchAdviceId;
	}

	public String getFileXml() {
		return fileXml;
	}

	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}

	public void setDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}
}
