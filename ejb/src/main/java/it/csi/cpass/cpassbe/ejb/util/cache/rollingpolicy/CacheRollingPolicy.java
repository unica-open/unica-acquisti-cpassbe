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
package it.csi.cpass.cpassbe.ejb.util.cache.rollingpolicy;

import java.util.Date;

/**
 * Rolling policy for cached data.
 */
public interface CacheRollingPolicy {

	/**
	 * Checks whether the cached item is stale.
	 *
	 * @param cacheDate the date in which the item was cached
	 * @param hitCount  the number of times the cached item was used
	 *
	 * @return true if the policy considers the cached value as stale
	 */
	boolean isExpired(Date cacheDate, int hitCount);

}
