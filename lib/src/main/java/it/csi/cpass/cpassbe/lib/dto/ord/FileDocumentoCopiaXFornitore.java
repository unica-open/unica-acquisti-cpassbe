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

public class FileDocumentoCopiaXFornitore implements Serializable {

	private static final long serialVersionUID = 1L;
	private byte[] bytes;
	private String fileNameTemplate;
	//private MimeTypeContainer mimeTypeContainer;
	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the fileNameTemplate
	 */
	public String getFileNameTemplate() {
		return fileNameTemplate;
	}

	/**
	 * @param fileNameTemplate the fileNameTemplate to set
	 */
	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}


/*
	public MimeTypeContainer getMimeTypeContainer() {
		return mimeTypeContainer;
	}

	public void setMimeTypeContainer(MimeTypeContainer mimeTypeContainer) {
		this.mimeTypeContainer = mimeTypeContainer;
	}
*/

}

