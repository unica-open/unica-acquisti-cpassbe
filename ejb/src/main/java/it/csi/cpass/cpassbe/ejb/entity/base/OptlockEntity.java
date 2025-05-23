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

import java.util.UUID;

/**
 * Marker interface for an entity with Optlock
 */
public interface OptlockEntity {

	/**
	 * @return the id
	 */
	UUID getOptlock();
	/**
	 * @param id the id to set
	 */
	void setOptlock(UUID id);
	/**
	 * Generates a new Optlock
	 */
	void generateNewOptlock();
}
