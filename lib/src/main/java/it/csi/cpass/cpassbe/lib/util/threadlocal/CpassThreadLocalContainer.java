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
package it.csi.cpass.cpassbe.lib.util.threadlocal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;


/**
 * Thead local container
 */
public class CpassThreadLocalContainer {
	/** Contains the connected user */
	public static final ThreadLocal<Utente> UTENTE_CONNESSO = new ThreadLocal<>();
	/** Contains the settore utente */
	public static final ThreadLocal<Settore> SETTORE_UTENTE = new ThreadLocal<>();
	/** Contains the settore utente */
	public static final ThreadLocal<List<InputStream>> ALLEGATI = new ThreadLocal<>() {
		protected java.util.List<InputStream> initialValue() {
			return new ArrayList<>();
		};
	};

	/** Private constructor */
	private CpassThreadLocalContainer() {
		// Prevent instantiation
	}


	/**
	 * Cleanup of the thread locals
	 */
	public static void cleanup() {
		ALLEGATI.remove();
		UTENTE_CONNESSO.remove();
		SETTORE_UTENTE.remove();
	}


}
