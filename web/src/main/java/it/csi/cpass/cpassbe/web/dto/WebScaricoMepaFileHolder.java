/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import java.util.UUID;

import javax.ws.rs.FormParam;

import it.csi.cpass.cpassbe.lib.dto.FileHolder;

public class WebScaricoMepaFileHolder extends WebFileHolder{

	@FormParam("fileXml")
	private String fileXml;

	public String getFileXml() {
		return fileXml;
	}

	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

	@Override
	public FileHolder toFileHolder() {
		FileHolder fh = new FileHolder();
		fh.setAttachment(super.getAttachment());
		fh.setFileXml(fileXml);
		fh.setIdEnte(UUID.fromString(super.getIdEnte()));
		return fh;
	}
}
