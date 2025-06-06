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

import java.util.Calendar;
import java.util.Date;

/**
 * A CacheRollingPolicy which checks staleness via a Calendar.
 */
public abstract class CalendarCacheRollingPolicy implements CacheRollingPolicy {

	/**
	 * Checks whether the date makes the cached data stale.
	 *
	 * @param data           the cached date
	 * @param calendarFields the calendar fields to check
	 *
	 * @return true, if the date is expired
	 */
	protected boolean isExpiredDate(Date data, int... calendarFields) {
		final Calendar cacheDate = Calendar.getInstance();
		cacheDate.setTime(data);
		final Calendar now = Calendar.getInstance();

		for(final int calendarField : calendarFields){
			final int cf = now.get(calendarField);
			final int cfCache = cacheDate.get(calendarField);

			if (cfCache < cf) {
				return true;
			}
		}

		return false;
	}
}
