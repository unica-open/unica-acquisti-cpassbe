/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.mepa;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;

public class ScaricoMepaXml extends BaseAuditedDto<Integer> implements Serializable{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private ScaricoMepaTestata scaricoMepaTestata; //scarico_mepa_testata_id
	private String fileXml;
	private String pathFile;
	private Date dataSpostamento;
	public ScaricoMepaTestata getScaricoMepaTestata() {
		return scaricoMepaTestata;
	}
	public void setScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		this.scaricoMepaTestata = scaricoMepaTestata;
	}
	public String getFileXml() {
		return fileXml;
	}
	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

	public String getPathFile() {
		return pathFile;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	public Date getDataSpostamento() {
		return dataSpostamento;
	}
	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

}
