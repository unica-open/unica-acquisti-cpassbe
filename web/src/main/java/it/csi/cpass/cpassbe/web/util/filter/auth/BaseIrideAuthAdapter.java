/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.util.filter.auth;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.web.util.filter.auth.provider.iride.Identita;
import it.csi.cpass.cpassbe.web.util.filter.auth.provider.iride.MalformedIdTokenException;

/**
 * Auth adapter for IRIDE
 */
public abstract class BaseIrideAuthAdapter implements AuthAdapter {

	private static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";
	private static final LogUtil LOG = new LogUtil(BaseIrideAuthAdapter.class);
	@Inject private UtenteDad utenteDad;

	@Override
	public Utente processAuth(boolean devMode, UriInfo uriInfo, ContainerRequestContext containerRequest) {
		final String methodName = "processAuth";
		String marker = getToken(containerRequest);
		if (marker != null) {
			return initMarkerIride(normalizeToken(marker));
		} else if (devMode) {
			return initMarkerIride(null);
		} else if (mustCheckPage(uriInfo.getRequestUri())) {
			// Il marcatore deve sempre essere presente altrimenti e' una condizione di errore
			LOG.error(methodName, "Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
			return null;
		}
		return null;
	}

	/**
	 * Ottiene il cookie di autenticazione
	 * @param containerRequest la richiesta HTTP
	 * @return il marker
	 */
	public String getToken(ContainerRequestContext containerRequest) {
		String marker = containerRequest.getHeaderString(AUTH_ID_MARKER);
		if(marker == null) {
			// Null-safety
			return null;
		}
		// Gestione dell'encoding
		return new String(marker.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	}
	
	private Utente initMarkerIride(String token) {
		final String methodName = "initMarkerIride";
		Identita identita;
		try {
			if(token != null) {
				identita = new Identita(token);
			} else {
				// Dev mode
				identita = initializeDevModeIdentita();
			}
		} catch (MalformedIdTokenException e) {
			LOG.error(methodName, "Token non correttamente formattato. " + e.toString(), e);
			// Skip?
			return null;
		}
		LOG.trace(methodName, () -> "Caricato marcatore IRIDE: " + identita);
		Utente utente = utenteDad.getUtenteByCf(identita.getCodFiscale()).orElse(null);
		if(utente == null) {
			LOG.error(methodName, "Utente non censito");
			return null;
		}

		return utente;
	}

	/**
	 * Initialization of the Identita instance for dev mode
	 * @return the instance
	 */
	protected abstract Identita initializeDevModeIdentita();

	/**
	 * Normalizzazione del token
	 * @param token il token
	 * @return il token normalizzato
	 */
	private String normalizeToken(String token) {
		return token;
	}

	/**
	 * Controlla se l'URI debba essere controllato
	 * @param requestURI l'URI da controllare
	 * @return se l'URI debba essere controllato
	 */
	private boolean mustCheckPage(URI requestURI) {
		return requestURI != null;
	}
}
