/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * <p>Example injection on a managed bean field:</p>
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
	/** Producer for the entity manager */
	@Produces
	@PersistenceContext
	private EntityManager em;

	/**
	 * Producer for the logger
	 * @param injectionPoint the injection point
	 * @return the logger
	 */
	@Produces
	public LogUtil produceLogUtil(InjectionPoint injectionPoint) {
		return new LogUtil(injectionPoint.getMember().getDeclaringClass());
	}
}
