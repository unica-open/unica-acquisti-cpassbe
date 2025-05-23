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
package it.csi.cpass.cpassbe.lib.util.soap;

import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.logging.log4j.Level;


/**
 * JAX-WS HandlerResolver for injecting the Logger
 */
public class AllegatoHandlerResolver extends DefaultHandlerResolver {

	/**
	 * Injection constructor
	 * @param oAuth2Helper the helper
	 * @param logLevel the log level
	 */
	public AllegatoHandlerResolver(Level logLevel) {
		super(logLevel);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addOtherHandlers(List<Handler> chain) {
		chain.add(new AllegatoSOAPHandler());
	}

}
