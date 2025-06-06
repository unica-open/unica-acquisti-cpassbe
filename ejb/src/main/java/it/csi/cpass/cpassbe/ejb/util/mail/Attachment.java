/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util.mail;

/**
 * Attachment for email
 */
public class Attachment {

	private final byte[] bytes;
	private final String mediaType;
	private final String fileName;

	/**
	 * Constuctor
	 * 
	 * @param bytes     the bytes of the attachment
	 * @param mediaType the media type for the attachment
	 * @param fileName  the file name
	 */
	public Attachment(byte[] bytes, String mediaType, String fileName) {
		this.bytes = bytes;
		this.mediaType = mediaType;
		this.fileName = fileName;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Attachment [bytes=[...], mediaType=").append(mediaType).append(", fileName=").append(fileName)
				.append("]");
		return builder.toString();
	}

}
