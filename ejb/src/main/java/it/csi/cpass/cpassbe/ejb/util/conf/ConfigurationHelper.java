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
package it.csi.cpass.cpassbe.ejb.util.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;

import javax.ejb.Singleton;
import javax.servlet.ServletContext;

/**
 * Helper for the configuration handling
 */
@Singleton
public class ConfigurationHelper {

	/** The underlying properties */
	private final Properties properties = new Properties();
	private volatile boolean initialized = false;

	/**
	 * Initialization of the properties
	 * @param servletContext the servlet context
	 */
	public void initializeProperties(ServletContext servletContext) {
		if(initialized) {
			return;
		}
		initialized = true;
		Optional.ofNullable(servletContext.getResourcePaths("/WEB-INF/classes/configuration"))
		.orElseGet(HashSet<String>::new)
		.stream()
		.forEach(file -> loadPropertyFile(servletContext, file));
	}

	/**
	 * Property file loading
	 * @param servletContext the servlet context
	 * @param fileName the file name
	 */
	private void loadPropertyFile(ServletContext servletContext, String fileName) {
		try(InputStream applicationPropertiesStream = servletContext.getResourceAsStream(fileName)) {
			properties.load(applicationPropertiesStream);
		} catch (final IOException e) {
			throw new IllegalStateException("Properties file \"" + fileName + "\" cannot be loaded");
		} catch (final NullPointerException npe) {
			// Ignore
		}
	}

	/**
	 * Retrieves a property
	 * @param value the property name
	 * @return the value
	 */
	public String getProperty(ConfigurationValue value) {
		return properties.getProperty(value.getPropertyName());
	}
}
