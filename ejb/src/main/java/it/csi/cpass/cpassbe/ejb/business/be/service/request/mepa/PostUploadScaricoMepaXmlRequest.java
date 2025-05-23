/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.FileHolder;

public class PostUploadScaricoMepaXmlRequest implements BaseRequest{
	private UUID idEnte;
	private byte[] attachmentScaricoMepa;
	private String fileXml;

	public PostUploadScaricoMepaXmlRequest(FileHolder file) {
		this.idEnte = file.getIdEnte();
		this.attachmentScaricoMepa = file.getAttachment();
		this.fileXml = file.getFileXml();
	}


	public UUID getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
	}

	public byte[] getAttachmentScaricoMepa() {
		return attachmentScaricoMepa;
	}

	public void setAttachmentScaricoMepa(byte[] attachmentScaricoMepa) {
		this.attachmentScaricoMepa = attachmentScaricoMepa;
	}


	public String getFileXml() {
		return fileXml;
	}


	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}
}
