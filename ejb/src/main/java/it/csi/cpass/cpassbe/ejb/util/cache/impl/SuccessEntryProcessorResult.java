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
package it.csi.cpass.cpassbe.ejb.util.cache.impl;

import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

/**
 * EntryProcessorResult for success values
 * @param <T> the result type
 */
public class SuccessEntryProcessorResult<T> implements EntryProcessorResult<T> {

	/** The result */
	private final T result;

	/**
	 * the constructor
	 * @param result the result
	 */
	public SuccessEntryProcessorResult(T result) {
		this.result = result;
	}
	@Override
	public T get() throws EntryProcessorException {
		return result;
	}

}
