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
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
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
		LOG.debug(methodName, "START");
		//System.out.println("START");
		String marker = getToken(containerRequest);
		if (marker != null) {
			//System.out.println("dopo la normalizzazione "+ normalizeToken(marker));
			//LOG.info(methodName, "dopo la normalizzazione "+ normalizeToken(marker));
			return initMarkerIride(normalizeToken(marker));
		} else if (devMode) {
			//System.out.println("DEV MODE");
			LOG.info(methodName, "DEV MODE");
			return initMarkerIride(null);
		} else if (mustCheckPage(uriInfo.getRequestUri())) {
			//System.out.println("Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza " + uriInfo.getRequestUri().toString());
			// Il marcatore deve sempre essere presente altrimenti e' una condizione di errore
			LOG.error(methodName, "Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
			LOG.error(methodName, "Hai per caso compilato con un properties diverso da local?");
			return null;
		}
		//System.out.println("STOP nessuno dei precedenti");
		LOG.error(methodName, "STOP nessuno dei precedenti");
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
			//System.out.println("getToken marker == null non ho nell'heder della chiamata il token " + AUTH_ID_MARKER);
			return null;
		}
		// Gestione dell'encoding
		return new String(marker.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	}
	/**
	 * 
	 * @param token
	 * @return uTENTE
	 */
	private Utente initMarkerIride(String token) {
		final String methodName = "initMarkerIride";
		//System.out.println(methodName+"START");
		Identita identita;
		try {
			if(token != null) {
				//System.out.println(methodName+" NO LOCAL MODE");
				identita = new Identita(token);
			} else {
				//System.out.println(methodName+" LOCAL MODE");
				identita = initializeDevModeIdentita();
			}
		} catch (MalformedIdTokenException e) {
			//System.out.println(methodName+"Token non correttamente formattato. " + e.toString());
			LOG.error(methodName, "Token non correttamente formattato. " + e.toString(), e);
			return null;
		}
		LOG.trace(methodName, () -> "Caricato marcatore IRIDE: " + identita);

		
		
		String cf = StringUtility.trim(identita.getCodFiscale());
		//System.out.println(methodName+" Utente C.F " + cf);
		LOG.trace(methodName, "Utente C.F " + cf);
		
		Optional<Utente> utenteOpt = utenteDad.getUtenteByCf(cf,Boolean.FALSE);
		Utente utente = null;
		if(utenteOpt.isPresent()) {
			utente = utenteOpt.get();
			//se sono in dev inizializzo con token di fittizio
			String identitaDigitale = "";
			if(token != null) {
				identitaDigitale=identita.toString();
				//System.out.println(methodName+" identitaDigitale "+identitaDigitale);
			}else {
				System.out.println(methodName+" sono inn local ");
				LOG.warn(methodName, "*****************************************************************");
				LOG.warn(methodName, "** ambiente LOCALE HAI CAMBIATO l'IDENTITA' DIGITALE???????????");
				LOG.warn(methodName, "** http://tst-www.sistemapiemonte.it/routingconf-cons/identita.do**");
				LOG.warn(methodName, "** cambiare identitaDigitalesu  BaseIrideAuthAdapter**");
				LOG.warn(methodName, "*****************************************************************");
				//identitaDigitale = cf +"/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20230227105522/16/qpW6R9GZnMZWp5aEPI2ovg==";
				identitaDigitale = "AAAAAA00A11B000J/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20240410083631/16/ZycSEWEWJGKAxmRAPSH1TA==";
			}
			//System.out.println(methodName+" identitaDigitale "+identitaDigitale);
			LOG.trace(methodName, identitaDigitale);
			utente .setIdentitaDigitale(identitaDigitale);

			if (utente == null || utente.getIdentitaDigitale() == null) {
				//System.out.println(methodName + " Utente non censito " + cf);
				LOG.error(methodName, "Utente non censito " + cf);
				return null;
			}
		}else {
			//System.out.println(methodName + "Utente non censito correttamente controllare la tabella cpass_t_utente con cf --> " + cf);
			LOG.error(methodName, "Utente non censito correttamente controllare la tabella cpass_t_utente con cf --> " + cf);
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
