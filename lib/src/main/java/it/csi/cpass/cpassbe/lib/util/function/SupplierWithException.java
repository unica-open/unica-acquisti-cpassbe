/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.util.function;

/**
 * Supplier which may throw an Exception
 *
 * @param <T> the resulting type
 * @param <E> the exception type
 */
@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> {
	/**
	 * Get the value
	 * @return the value
	 * @throws E the exception
	 */
	public T get() throws E;
}
