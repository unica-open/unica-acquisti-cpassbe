/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.util.collection;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utilities for collections
 */
public final class CollectionUtils {

	/** Private constructor */
	private CollectionUtils() {
		// Prevent instantiation
	}

	/**
	 * Maps a collection to a stream, null-save
	 * @param <T> the collection type
	 * @param collection the collection
	 * @return the stream
	 */
	public static <T> Stream<T> collectionToStream(Collection<T> collection) {
		return Optional.ofNullable(collection)
			.map(Collection::stream)
			.orElseGet(Stream::empty);
	}
}
