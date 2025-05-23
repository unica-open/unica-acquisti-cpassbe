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
package it.csi.cpass.cpassbe.ejb.util.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicLong;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * {@link Runnable} implementor logging eventually <abbr title="Garbage Collector">GC</abbr>-reclaimed references from the referenceQueue.
 * @param <V> the underlying value type
 */
public class ReclaimedCacheLoggingListener<V> implements Runnable {

	private static final LogUtil LOG = new LogUtil(ReclaimedCacheLoggingListener.class);

	private final AtomicLong garbageCollectorCounter;
	private final ReferenceQueue<V> cacheReferenceQueue;

	/**
	 * Default constructor
	 * @param garbageCollectorCounter the GC counter
	 * @param cacheReferenceQueue the cache referenceQueue
	 */
	public ReclaimedCacheLoggingListener(AtomicLong garbageCollectorCounter, ReferenceQueue<V> cacheReferenceQueue) {
		this.garbageCollectorCounter = garbageCollectorCounter;
		this.cacheReferenceQueue = cacheReferenceQueue;
	}

	@Override
	public void run() {
		final String methodName = "run";
		LOG.info(methodName, "Listening for items removed from the cache");
		try {
			int i=0;
			while(true) {
				i++;
				final Reference<? extends V> removed = cacheReferenceQueue.remove();
				final long count = garbageCollectorCounter.incrementAndGet();
				LOG.debug(methodName, "Removed item " + removed + " from cache (" + count + " references reaped since thread started)");
				if(i>1000) {
					break;
				}
			}
		} catch(final InterruptedException ie) {
			LOG.info(methodName, "Shutting down the thread...");
			Thread.currentThread().interrupt();
		}
		LOG.info(methodName, "Thread shut down");
	}
}
