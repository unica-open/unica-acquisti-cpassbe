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
	//@Inject private ConfigurationHelper configurationHelper;

	@Override
	protected Identita initializeDevModeIdentita() {
		final String methodName = "initializeDevModeIdentita";
		//String fileLocation = configurationHelper.getProperty(ConfigurationValue.AUTHFILTER_IRIDE_FILENAME);
		Properties properties = new Properties();
		try(FileInputStream fis = new FileInputStream("C:\\\\myworkspace\\\\CPASS\\\\cpassbe\\\\web\\\\src\\\\main\\\\resources\\\\configuration\\\\localLoginHandler.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			LOG.warn(methodName, "IOException in file read: " + e.getMessage());
		}
		String cf ="";
		//demo ANNA
		//cf = properties.getProperty("user.cf","TLLNRT69E56D122C");
		//demo 21
		cf = properties.getProperty("user.cf", "AAAAAA00A11B000J");
		//SABATINO AURORA
		//cf = properties.getProperty("user.cf", "SBTRRA03M42L219R");


		//demo Bertolotti
		//cf = properties.getProperty("user.cf", "BRTPFR80C14L013A");
		//demo 25
		//cf = properties.getProperty("user.cf", "AAAAAA00A11F000N");
		//demo 26
		//cf = properties.getProperty("user.cf", "AAAAAA00A11G000O");
		//demo 27
		//cf = properties.getProperty("user.cf", "AAAAAA00A11H000P");
		//demo 28
		//cf = properties.getProperty("user.cf", "AAAAAA00A11I000Q");
		//demo 20
		//cf = properties.getProperty("user.cf", "AAAAAA00B77B000F");
		//cf = properties.getProperty("user.cf", "AAAAAA00B77B000F");
		//demo 29
		//cf = properties.getProperty("user.cf", "AAAAAA00A11J000R");
		//BARBERO
		//cf = properties.getProperty("user.cf","BRBFRZ64A01L219J");
		//demo 40
		//cf = properties.getProperty("user.cf", "AAAAAA00A11P000X");
		//demo 34
		//cf = properties.getProperty("user.cf", "AAAAAA00A11O000W");
		//demo 22
//		cf = properties.getProperty("user.cf", "AAAAAA00A11C000K");
		//demo 22
		//cf = properties.getProperty("user.cf", "AAAAAA00A11D000L");
		// ragio
//		cf = properties.getProperty("user.cf", "AAAAAA00A11L000T");
		//demo 35
		//cf = properties.getProperty("user.cf", "AAAAAA00A11R000Z");
		//demo 26
//		cf = properties.getProperty("user.cf", "AAAAAA00A11G000O");
		//demo 31
//		cf = properties.getProperty("user.cf", "AAAAAA00A11L000T");

		Identita identita = new Identita();
		identita.setCodFiscale(cf);
		return identita;
	}
}
