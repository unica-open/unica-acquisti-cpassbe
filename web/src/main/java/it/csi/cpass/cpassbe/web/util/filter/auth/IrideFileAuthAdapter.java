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
package it.csi.cpass.cpassbe.web.util.filter.auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationValue;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.web.util.filter.auth.provider.iride.Identita;

/**
 * Auth adapter for IRIDE.
 * <br/>
 * This is a development adapter to be used with a file (specified as a configuration parameter)
 */
@ApplicationScoped
public class IrideFileAuthAdapter extends BaseIrideAuthAdapter {

	private static final LogUtil LOG = new LogUtil(IrideFileAuthAdapter.class);
	@Inject private ConfigurationHelper configurationHelper;

	@Override
	protected Identita initializeDevModeIdentita() {
		final String methodName = "initializeDevModeIdentita";
		String fileLocation = configurationHelper.getProperty(ConfigurationValue.AUTHFILTER_IRIDE_FILENAME);
		Properties properties = new Properties();
		try(FileInputStream fis = new FileInputStream(fileLocation)) {
			properties.load(fis);
		} catch (IOException e) {
			LOG.warn(methodName, "IOException in file read: " + e.getMessage());
		}
		String cf = properties.getProperty("user.cf", "AAAAAA00A11B000J");
		Identita identita = new Identita();
		identita.setCodFiscale(cf);
		return identita;
	}
}
