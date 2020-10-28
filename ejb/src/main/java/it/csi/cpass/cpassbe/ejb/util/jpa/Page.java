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
package it.csi.cpass.cpassbe.ejb.util.jpa;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Query page
 * @param <T> the retrieved type
 */
public interface Page<T> {

	/**
	 * @return the total elements
	 */
	long getTotalElements();
	/**
	 * @return the content
	 */
	Collection<T> getContent();

	/**
	 * @return the stream
	 */
	Stream<T> stream();
	/**
	 * @return the parallel stream
	 */
	Stream<T> parallelStream();

}
