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
package it.csi.cpass.cpassbe.web.util.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Interceptor that logs an invocation
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

	/** For serialization */
	private static final long serialVersionUID = -3961060405619482358L;
	private static final AtomicLong COUNTER = new AtomicLong();

	/**
	 * Checks the permissions
	 * @param invocationContext the invocation context
	 * @return the invocation result
	 * @throws Exception in case of any exception
	 */
	@AroundInvoke
	public Object checkPermissions(InvocationContext invocationContext) throws Exception {
		// Create a logger with the correct name
		long requestId = COUNTER.incrementAndGet();
		Class<?> targetClass = InterceptorUtils.deProxy(invocationContext);
		String methodName = invocationContext.getMethod().getName();
		LogUtil log = new LogUtil(targetClass);
		long initTime = System.currentTimeMillis();
		long endTime = initTime;
		
		try {
			log.trace(methodName, () -> "[REQ " + requestId + "] Start");
			Object response = invocationContext.proceed();
			// Before the log instruction so as not to steal time
			endTime = System.currentTimeMillis();
			log.trace(methodName, () -> "[REQ " + requestId + "] End. InputParameters: " + getInputParameters(invocationContext) + ". Response: " + JsonUtility.serialize(response));
			return response;
		} catch(Exception e) {
			// Before the log instruction so as not to steal time
			endTime = System.currentTimeMillis();
			log.error(methodName, () -> "[REQ " + requestId + "] End. InputParameters: " + getInputParameters(invocationContext) + ". Error: " + e.getMessage(), e);
			throw e;
		} finally {
			// Workaround for lambda problems with non-effectively final data
			long finalEndTime = endTime;
			log.trace(methodName, () -> "[REQ " + requestId + "] Elapsed time: " + (finalEndTime - initTime) + " ms");
		}
		
	}
	/**
	 * Retrieves and formats the input parameters
	 * @param invocationContext the invocation context
	 * @return the parameters
	 */
	private String getInputParameters(InvocationContext invocationContext) {
		return Arrays.stream(invocationContext.getParameters())
				.filter(p -> !(p instanceof SecurityContext || p instanceof HttpHeaders || p instanceof HttpServletRequest))
				.map(JsonUtility::serialize)
				.collect(Collectors.joining(";"));
	}

}
