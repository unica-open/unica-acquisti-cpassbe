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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetModuloBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetPermessiBySettoreAndModuloService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetPermessiBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRuoliBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRupsBySettoreIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriByRupIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriByUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriRuoliPermessiByUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteBySettoreRuoloService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteRupDeleganteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteSelfService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtentiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetModuloBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiBySettoreAndModuloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRuoliBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRupsBySettoreIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByRupIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriRuoliPermessiByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteBySettoreRuoloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteRupDeleganteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteSelfRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtentiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.DeleteUtenteByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetModuloBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreAndModuloResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRuoliBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRupsBySettoreIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByRupIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriRuoliPermessiByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteBySettoreRuoloResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteRupDeleganteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteSelfResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtentiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PostUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutUtenteResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Fa&ccedil;ade for the /utente path
 */
@Stateless
@Lock(LockType.READ)
public class UtenteFacade extends BaseFacade {
	// Injection point
	@Inject private UtenteDad utenteDad;

	/**
	 * Deletes the Utente by its id
	 * @param id the id
	 * @return nothing
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteUtenteByIdResponse deleteUtenteById(UUID id) {
		// TODO
		//return executeService(new DeleteUtenteByIdRequest(id), new DeleteUtenteByIdService(configurationHelper, utenteDad));
		return new DeleteUtenteByIdResponse();
	}
	/**
	 * Retrieves the utenti
	 * @param page the page
	 * @param limit the limit
	 * @return the utentes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUtentiResponse getUtenti(Integer page, Integer limit) {
		return executeService(new GetUtentiRequest(page, limit), new GetUtentiService(configurationHelper, utenteDad));
	}
	/**
	 * Posts an Utente
	 * @param utente
	 * @return the utente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostUtenteResponse postUtente(Utente utente) {
		// TODO
		//return executeService(new PostUtenteRequest(utente), new PostUtenteService(configurationHelper, utenteDad, decodificaDad,programmaDad));
		return new PostUtenteResponse();
	}
	/**
	 * Puts an Utente
	 * @param id the id
	 * @param utente
	 * @return the utente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutUtenteResponse putUtenteById(UUID id, Utente utente) {
		// TODO
		//return executeService(new PutUtenteRequest(setId(id, utente)), new PutUtenteService(configurationHelper, utenteDad, decodificaDad,programmaDad));
		return new PutUtenteResponse();
	}

	/**
	 * Retrieves the Utente by its id
	 * @param id the id
	 * @return the utente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUtenteByIdResponse getUtenteById(UUID id) {
		return executeService(new GetUtenteByIdRequest(id), new GetUtenteByIdService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Utente
	 * @return the utente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUtenteSelfResponse getUtenteSelf() {
		return executeService(new GetUtenteSelfRequest(), new GetUtenteSelfService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Utente by its id
	 * @return the utente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSettoriByUtenteResponse getSettoriByUtente() {
		return executeService(new GetSettoriByUtenteRequest(), new GetSettoriByUtenteService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Modulo by its id settore
	 * @param settoreId the settoreId
	 * @return the modulos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetModuloBySettoreResponse getModuloBySettore(UUID settoreId) {
		return executeService(new GetModuloBySettoreRequest(settoreId), new GetModuloBySettoreService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Permessi by its id settore and Modulo
	 * @param settoreId the id settore
	 * @param idModulo the id modulo
	 * @return the permessos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetPermessiBySettoreAndModuloResponse getPermessiBySettoreAndModulo(UUID settoreId, Integer idModulo) {
		return executeService(new GetPermessiBySettoreAndModuloRequest(settoreId, idModulo), new GetPermessiBySettoreAndModuloService(configurationHelper, utenteDad));
	}


	/**
	 * Gets the utente by settore and ruolo
	 * @param settoreId
	 * @param ruoloCodice
	 * @return the utente
	 */
	public GetUtenteBySettoreRuoloResponse getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice) {
		return executeService(new GetUtenteBySettoreRuoloRequest(settoreId, ruoloCodice), new GetUtenteBySettoreRuoloService(configurationHelper, utenteDad));
	}

	/**
	 * Gets ruoli by settore
	 * @param settoreId
	 * @return the ruoli
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRuoliBySettoreResponse getRuoliBySettore(UUID settoreId) {
		return executeService(new GetRuoliBySettoreRequest(settoreId), new GetRuoliBySettoreService(configurationHelper, utenteDad));
	}

	/**
	 * Gets the RUPs by settore
	 * @param id the settore id
	 * @return the RUPs
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRupsBySettoreIdResponse getRupsBySettoreId(UUID id) {
		return executeService(new GetRupsBySettoreIdRequest(id), new GetRupsBySettoreIdService(configurationHelper, utenteDad));
	}

	/**
	 * Gets the RUP delegante
	 * @param idUtenteDelegante the id utente delegante
	 * @return the utente RUP
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUtenteRupDeleganteResponse getUtenteRupDelegante(UUID idUtenteDelegante) {
		return executeService(new GetUtenteRupDeleganteRequest(idUtenteDelegante), new GetUtenteRupDeleganteService(configurationHelper, utenteDad));
	}

	/**
	 * Gets the settori by RUP id
	 * @param rupId the RUP id
	 * @return the settori
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSettoriByRupIdResponse getSettoriByRupId(UUID rupId) {
		return executeService(new GetSettoriByRupIdRequest(rupId), new GetSettoriByRupIdService(configurationHelper, utenteDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSettoriRuoliPermessiByUtenteResponse getSettoriRuoliPermessiByUtente() {
		return executeService(new GetSettoriRuoliPermessiByUtenteRequest(), new GetSettoriRuoliPermessiByUtenteService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Permessi by its id settore
	 * @param settoreId the id settore
	 * @return the permessos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetPermessiBySettoreResponse getPermessiBySettore(UUID settoreId) {
		return executeService(new GetPermessiBySettoreRequest(settoreId), new GetPermessiBySettoreService(configurationHelper, utenteDad));
	}
}
