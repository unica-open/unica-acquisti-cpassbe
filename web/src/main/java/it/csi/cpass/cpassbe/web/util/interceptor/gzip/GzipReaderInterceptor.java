/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.util.interceptor.gzip;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * Interceptor to read GZIP request
 */
@Provider
public class GzipReaderInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		MultivaluedMap<String, String> headers = context.getHeaders();

		List<String> contentEncoding = headers.getOrDefault(HttpHeaders.CONTENT_ENCODING, new ArrayList<>());

		InputStream is = null;
		try {
			for (String s : contentEncoding) {
				if (s.contains("gzip")) {
					is = new GZIPInputStream(context.getInputStream());
					context.setInputStream(is);
					break;
				}
			}
			return context.proceed();
		} finally {
			if(is != null) {
				is.close();
			}
		}
	}
}
