/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.content.ContentBody;

public class PeppolContentBody implements ContentBody {

	private byte[] byteXml;
	private String filename;

	public PeppolContentBody(byte[] byteXml, String filename) {
		this.byteXml = byteXml;
		this.filename = filename;
	}

	@Override
	public String getTransferEncoding() {
		return MIME.ENC_8BIT;
	}

	@Override
	public String getSubType() {
		return null;
	}

	@Override
	public String getMimeType() {
		//return "application/xml; charset=UTF-8";
		 return "application/xml";
		// return "text/xml";
	}

	@Override
	public String getMediaType() {
		return null;
	}

	@Override
	public long getContentLength() {
		return byteXml.length;
	}

	@Override
	public String getCharset() {
		return "UTF-8";
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		final InputStream in = new ByteArrayInputStream(byteXml);
		final byte[] tmp = new byte[4096];
		int l;
		while ((l = in.read(tmp)) != -1) {
			out.write(tmp, 0, l);
		}
		out.flush();
	}

	@Override
	public String getFilename() {
		return filename;
	}
}
