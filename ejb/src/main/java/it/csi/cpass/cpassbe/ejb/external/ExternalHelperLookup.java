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
package it.csi.cpass.cpassbe.ejb.external;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceImpl;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * Lookup class for external service helpers
 */
@ApplicationScoped
public class ExternalHelperLookup {

	@Inject private SystemDad systemDad;
	private static final LogUtil LOG = new LogUtil(ExternalHelperLookup.class);

	/**
	 * Retrieves the helper for a given class, for the default configuration
	 * @param <H> the helper class
	 * @param clazz the class instance to use
	 * @return the implementor
	 */
	public <H extends BaseHelper> ExternalServiceResolveWrapper<H> lookup(Class<H> clazz) {
		final String configurationReference = extractedConfigurationReference(clazz);
		return lookup(clazz, configurationReference,null);
	}

	/**
	 * Retrieves the helper for a given class, for a given configuration
	 * @param <H> the helper class
	 * @param clazz the class instance to use
	 * @param configurationReference the reference to the parameters
	 * @return the implementor
	 */
	public <H extends BaseHelper> ExternalServiceResolveWrapper<H> lookup(Class<H> clazz, String configurationReference) {
		return lookup( clazz, configurationReference, null);
	}

	/**
	 *
	 * @param <H>
	 * @param clazz
	 * @param enteId
	 * @return
	 */
	public <H extends BaseHelper> ExternalServiceResolveWrapper<H> lookup(Class<H> clazz,  UUID enteId) {
		final String methodName = "lookup";
		final String configurationReference = extractedConfigurationReference(clazz);
		LOG.info(methodName, "Lookup of class " + clazz.getName() + " via configuration " + configurationReference);
		return lookup( clazz, configurationReference, enteId);
	}

	/**
	 * @param <H>
	 * @param clazz
	 * @return
	 */
	protected <H extends BaseHelper> String extractedConfigurationReference(Class<H> clazz) {
		if(clazz == null) {
			throw new IllegalArgumentException("Helper class must be set");
		}
		final String tmp = clazz.getSimpleName();
		final int helperIndex = tmp.indexOf("Helper");
		if(helperIndex < 0) {
			throw new IllegalArgumentException("Helper class must have a name of the form <Service>Helper");
		}
		return StringHelper.toKebabCase(tmp.substring(0, helperIndex)).toUpperCase();
	}

	/**
	 * Retrieves the helper for a given class, for a given configuration
	 * @param <H> the helper class
	 * @param clazz the class instance to use
	 * @param configurationReference the reference to the parameters
	 * @return the implementor
	 */
	public <H extends BaseHelper> ExternalServiceResolveWrapper<H> lookup(Class<H> clazz, String configurationReference, UUID enteId) {
		final String methodName = "lookup";
		LOG.info(methodName, "Lookup of class " + clazz.getName() + " via configuration " + configurationReference+ " enteId " + enteId);
		final List<Parametro> parameters = systemDad.getParametriList(null, configurationReference, null, enteId);
		final Parametro implementor = parameters.stream()
				.filter(BaseExternalConfigurationParams.IMPLEMENTOR::isParametroEqual)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No implementor of " + clazz.getName() + " for reference " + configurationReference));

		LOG.trace(methodName, () -> "Implementor found to be equal to " + implementor.getValore());
		// Extract parameters by ambiente
		final Map<String, String> params = extractParameters(parameters, implementor , enteId);

		final H instance = instantiate(clazz, configurationReference, params);
		return new ExternalServiceResolveWrapper<>(instance, params);
	}

	/**
	 * Extraction of the parameters.
	 * <p>The proposed order is as follows:
	 * <ul>
	 *   <li>parameters by configuration</li>
	 *   <li>parameters by ambiente (as defined by the implementor)</li>
	 *   <li>parameters by configuration+ambiente</li>
	 * </ul>
	 * Each configuration may override the previous one
	 * @param parameters the parameters by configuration
	 * @param implementor the implementor
	 * @return the parameters for the invocation
	 */
	private Map<String, String> extractParameters(List<Parametro> parameters, Parametro implementor , UUID enteId) {
		final String methodName = "extractParameters";



		final String ambiente = implementor.getValore().toUpperCase();
		// Read transversal data
		final Map<String, String> params = new HashMap<>();
		// Parameter order: parameters by configuration -> parameters by ambiente -> parameters by configuration+ambiente
		//TODO gestire il filtro successivo per ente_id
		final List<Parametro> parametersByAmbiente = systemDad.getParametriList(null, "EXT", ambiente , enteId);


		parameters.stream()
		.filter(p -> p.getAmbiente() == null || p.getAmbiente().isEmpty())
		.forEach(p -> params.put(p.getChiave(), p.getValore()));
		parametersByAmbiente.stream()
		.forEach(p -> params.put(p.getChiave(), p.getValore()));
		parameters.stream()
		.filter(p -> p.getAmbiente() != null && ambiente.equals(p.getAmbiente()))
		.forEach(p -> params.put(p.getChiave(), p.getValore()));

		LOG.trace(methodName, () -> "Parameters found: " + params);
		return params;
	}

	/**
	 * Instantiation of the helper
	 * @param <H> the helper type
	 * @param clazz the interface to be implemented
	 * @param configurationReference the reference to the configuration
	 * @param params the parameters
	 * @return the implementor instance
	 */

	private <H extends BaseHelper> H instantiate(Class<H> clazz, String configurationReference, Map<String, String> params) {
		if(params.containsKey(BaseExternalConfigurationParams.IMPLEMENTOR_EJB_NAME.getParamName())) {
			return lookupByEJB(clazz, configurationReference, params.get(BaseExternalConfigurationParams.IMPLEMENTOR_EJB_NAME.getParamName()));
		}
		if(params.containsKey(BaseExternalConfigurationParams.IMPLEMENTOR_CDI_NAME.getParamName())) {
			return lookupByCDI(clazz, configurationReference, params.get(BaseExternalConfigurationParams.IMPLEMENTOR_CDI_NAME.getParamName()));
		}
		if(params.containsKey(BaseExternalConfigurationParams.IMPLEMENTOR_POJO_NAME.getParamName())) {
			final String className = params.get(BaseExternalConfigurationParams.IMPLEMENTOR_POJO_NAME.getParamName());
			return lookupByPOJO(clazz, configurationReference, className);
		}
		// Fallback: construct base implementor class name
		final String className = composeImplementorClassName(clazz, params.get(BaseExternalConfigurationParams.IMPLEMENTOR.getParamName()));
		return lookupByPOJO(clazz, configurationReference, className);
	}

	/**
	 * Lookup of the EJB via its JNDI name
	 * @param <H> the helper type
	 * @param clazz the interface to be implemented
	 * @param configurationReference the reference to the configuration
	 * @param ejbName the EJB name to lookup
	 * @return the implementor instance
	 */
	private <H extends BaseHelper> H lookupByEJB(Class<H> clazz, String configurationReference, String ejbName) {
		final String methodName = "lookupByEJB";
		try {
			return InitialContext.doLookup(ejbName);
		} catch (final NamingException e) {
			LOG.error(methodName, "NamingException in lookup. Parameters: class = " + clazz + " - configurationReference = " + configurationReference);
			throw new IllegalArgumentException("Cannot lookup EJB implementor " + clazz.getCanonicalName() + " for reference " + configurationReference
					+ ". Searched EJB name is " + ejbName, e);
		}
	}

	/**
	 * Lookup of the CDI via its name
	 * @param <H> the helper type
	 * @param clazz the interface to be implemented
	 * @param configurationReference the reference to the configuration
	 * @param cdiName the CDI name to lookup
	 * @return the implementor instance
	 */
	private <H extends BaseHelper> H lookupByCDI(Class<H> clazz, String configurationReference, String cdiName) {
		final Instance<H> instance = CDI.current().select(clazz, new ExternalServiceImpl.Literal(cdiName));

		if(instance.isUnsatisfied()) {
			throw new IllegalArgumentException("Cannot find CDI implementor of " + clazz.getCanonicalName() + " for reference " + configurationReference
					+ ". Searched CDI name is " + cdiName);
		}
		if(instance.isAmbiguous()) {
			throw new IllegalArgumentException("Ambiguous CDI implementor of " + clazz.getCanonicalName() + " for reference " + configurationReference
					+ ". Searched CDI name is " + cdiName);
		}
		return instance.get();
	}

	@SuppressWarnings("unchecked")
	private <H extends BaseHelper> H lookupByPOJO(Class<H> clazz, String configurationReference, String className) {
		final String methodName = "lookupByPOJO";
		LOG.debug(methodName, () -> "Implementor class name is " + className);
		try {
			final Class<? extends H> implClass = (Class<? extends H>) Class.forName(className);
			final Constructor<? extends H> constructor = implClass.getConstructor();
			return constructor.newInstance();
		} catch (final ClassNotFoundException e) {
			LOG.error(methodName, "ClassNotFoundException in lookup. Parameters: class = " + clazz + " - configurationReference = " + configurationReference);
			throw new IllegalArgumentException("Cannot find implementor class of " + clazz.getCanonicalName() + " for reference " + configurationReference
					+ ". Searched class is " + className, e);
		} catch (final NoSuchMethodException e) {
			LOG.error(methodName, "NoSuchMethodException in lookup. Parameters: class = " + clazz + " - configurationReference = " + configurationReference);
			throw new IllegalArgumentException("Cannot find empty constructor for class " + className, e);
		} catch (final SecurityException e) {
			LOG.error(methodName, "SecurityException in lookup. Parameters: class = " + clazz + " - configurationReference = " + configurationReference);
			throw new IllegalArgumentException("Security exception in initializing class " + className, e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOG.error(methodName, e.getClass().getSimpleName() + " in lookup. Parameters: class = " + clazz + " - configurationReference = " + configurationReference);
			throw new IllegalArgumentException("Instantiation exception in initializing class " + className, e);
		}
	}

	/**
	 * Composes the implementor class name
	 * @param <H> the helper type
	 * @param clazz the interface to be implemented
	 * @param implementor the implementor value
	 * @return the class name for the implementor
	 */
	private <H extends BaseHelper> String composeImplementorClassName(Class<H> clazz, String implementor) {
		return clazz.getPackageName() + ".impl." + implementor.toLowerCase() + "." + clazz.getSimpleName() + "Impl";
	}

}
