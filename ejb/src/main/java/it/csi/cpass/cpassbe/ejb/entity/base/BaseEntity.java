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
package it.csi.cpass.cpassbe.ejb.entity.base;

/**
 * Marker interface for a model
 * @param <K> the key type
 */
public interface BaseEntity<K> {

	/**
	 * @return the id
	 */
	K getId();
	/**
	 * @param id the id to set
	 */
	void setId(K id);
	/**
	 * Initializes the id with the instance fields, if applicable
	 */
	default void initId() {
		// May be overridden
	}
}
