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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.DeleteUtenteByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetCountNotificheNonLetteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetModuloBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetNotificheNonLetteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetPermessiBySettoreAndModuloService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetPermessiBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRicercaUtentiNoPageService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRicercaUtentiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRuoliByEnteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRuoliBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetRupsBySettoreIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriByRupIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriByUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetSettoriRuoliPermessiByUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteByRuoloService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteBySettoreRuoloService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteHrByCfService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteRupDeleganteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtenteSelfService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetUtentiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.PostUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.PutAggiornaNotificheService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.PutSpostaUtentiSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.PutUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.DeleteUtenteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetCountNotificheNonLetteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetModuloBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetNotificheNonLetteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiBySettoreAndModuloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRicercaUtentiNoPageRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRicercaUtentiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRuoliByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRuoliBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRupsBySettoreIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByRupIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriRuoliPermessiByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteByRuoloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteBySettoreRuoloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteHrByCfRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteRupDeleganteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteSelfRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtentiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PostUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutAggiornaNotificheRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutSpostaUtentiSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.DeleteUtenteByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetCountNotificheNonLetteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetModuloBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetNotificheNonLetteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreAndModuloResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRicercaUtentiNoPageResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRicercaUtentiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRuoliByEnteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRuoliBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRupsBySettoreIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByRupIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriRuoliPermessiByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteByRuoloResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteBySettoreRuoloResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteHrByCfResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteRupDeleganteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteSelfResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtentiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PostUtenteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutAggiornaNotificheResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutSpostaUtentiSettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutUtenteResponse;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Fa&ccedil;ade for the /utente path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UtenteFacade extends BaseFacade {
	// Injection point
	@Inject private UtenteDad utenteDad;
	@Inject private SystemDad systemDad;
	@Inject private NotificheDad notificheDad;
	

	/**
	 * Deletes the Utente by its id
	 * @param id the id
	 * @return nothing
	 */
	@Lock(LockType.WRITE)
	public DeleteUtenteByIdResponse deleteUtenteById(UUID id) {
		return executeService(new DeleteUtenteByIdRequest(id), new DeleteUtenteByIdService(configurationHelper, utenteDad));
	}
	/**
	 * Retrieves the utenti
	 * @param page the page
	 * @param limit the limit
	 * @return the utentes
	 */
	public GetUtentiResponse getUtenti(Integer page, Integer limit) {
		return executeService(new GetUtentiRequest(page, limit), new GetUtentiService(configurationHelper, utenteDad));
	}
	/**
	 * Posts an Utente
	 * @param utente
	 * @return the utente
	 */
	@Lock(LockType.WRITE)
	public PostUtenteResponse postUtente(Utente utente) {
		return executeService(new PostUtenteRequest(utente), new PostUtenteService(configurationHelper,externalHelperLookup, utenteDad,systemDad));
	}
	/**
	 * Puts an Utente
	 * @param id the id
	 * @param utente
	 * @return the utente
	 */
	@Lock(LockType.WRITE)
	public PutUtenteResponse putUtenteById(UUID id, Utente utente) {
		return executeService(new PutUtenteRequest(setId(id, utente)), new PutUtenteService(configurationHelper,externalHelperLookup, utenteDad,systemDad));
	}

	/**
	 * Retrieves the Utente by its id
	 * @param id the id
	 * @return the utente
	 */
	public GetUtenteByIdResponse getUtenteById(UUID id) {
		return executeService(new GetUtenteByIdRequest(id), new GetUtenteByIdService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Utente
	 * @return the utente
	 */
	public GetUtenteSelfResponse getUtenteSelf() {
		return executeService(new GetUtenteSelfRequest(), new GetUtenteSelfService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Utente by its id
	 * @return the utente
	 */
	public GetSettoriByUtenteResponse getSettoriByUtente() {
		return executeService(new GetSettoriByUtenteRequest(), new GetSettoriByUtenteService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Modulo by its id settore
	 * @param settoreId the settoreId
	 * @return the modulos
	 */
	public GetModuloBySettoreResponse getModuliBySettore(UUID settoreId) {
		return executeService(new GetModuloBySettoreRequest(settoreId), new GetModuloBySettoreService(configurationHelper, utenteDad, systemDad));
	}

	/**
	 * Retrieves the Permessi by its id settore and Modulo
	 * @param settoreId the id settore
	 * @param idModulo the id modulo
	 * @return the permessos
	 */
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
	 * Gets the utente by ruolo
	 * @param ruoloCodice
	 * @return the utente
	 */
	public GetUtenteByRuoloResponse getUtenteByRuolo(String ruoloCodice) {
		return executeService(new GetUtenteByRuoloRequest(ruoloCodice), new GetUtenteByRuoloService(configurationHelper, utenteDad));
	}

	/**
	 * Gets ruoli by settore
	 * @param settoreId
	 * @return the ruoli
	 */
	public GetRuoliBySettoreResponse getRuoliBySettore(UUID settoreId) {
		return executeService(new GetRuoliBySettoreRequest(settoreId), new GetRuoliBySettoreService(configurationHelper, utenteDad));
	}

	/**
	 * Gets the RUPs by settore
	 * @param id the settore id
	 * @return the RUPs
	 */
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
	public GetSettoriByRupIdResponse getSettoriByRupId(UUID rupId) {
		return executeService(new GetSettoriByRupIdRequest(rupId), new GetSettoriByRupIdService(configurationHelper, utenteDad));
	}
	/**
	 * 
	 * @return
	 */
	public GetSettoriRuoliPermessiByUtenteResponse getSettoriRuoliPermessiByUtente() {
		return executeService(new GetSettoriRuoliPermessiByUtenteRequest(), new GetSettoriRuoliPermessiByUtenteService(configurationHelper, utenteDad));
	}

	/**
	 * Retrieves the Permessi by its id settore
	 * @param settoreId the id settore
	 * @return the permessos
	 */
	public GetPermessiBySettoreResponse getPermessiBySettore(UUID settoreId) {
		return executeService(new GetPermessiBySettoreRequest(settoreId), new GetPermessiBySettoreService(configurationHelper, utenteDad));
	}


	/**
	 * Returns the number of unread notifications
	 * @return
	 */
	public GetCountNotificheNonLetteResponse getCountNotificheNonLette() {
		return executeService(new GetCountNotificheNonLetteRequest(), new GetCountNotificheNonLetteService(configurationHelper,notificheDad, utenteDad, systemDad));
	}

	/**
	 *
	 * @return GetNotificheNonLetteResponse
	 */
	public GetNotificheNonLetteResponse getNotificheNonLette() {
		return executeService(new GetNotificheNonLetteRequest(), new GetNotificheNonLetteService(configurationHelper,notificheDad, utenteDad));
	}

	/**
	 *
	 * @param listaNotifiche
	 * @return
	 */
	public PutAggiornaNotificheResponse putAggiornaNotifiche(List<Notifica> listaNotifiche) {
		return executeService(new PutAggiornaNotificheRequest(listaNotifiche), new PutAggiornaNotificheService(configurationHelper,notificheDad, utenteDad));
	}

	/**
	 *
	 * @return GetRuoliByEnteResponse
	 */
	public GetRuoliByEnteResponse getRuoliByEnte(String selezionabileDaProcedura) {
		return executeService(new GetRuoliByEnteRequest(selezionabileDaProcedura), new GetRuoliByEnteService(configurationHelper, utenteDad));
	}

	/**
	 *
	 * @param page
	 * @param limit
	 * @param sort
	 * @param direction
	 * @param dirigente
	 * @param ruolo
	 * @param settore
	 * @param utente
	 * @return GetRicercaUtentiResponse
	 */
	public GetRicercaUtentiResponse getRicercaUtenti(@Min(0) Integer page, @Min(1) @Max(100) Integer limit, String sort, String direction,Boolean dirigente, Ruolo ruolo, Settore settore, Utente utente, Boolean checkDataValiditaFineRUteSett) {
		return executeService(new GetRicercaUtentiRequest( page,  limit,  sort,  direction, dirigente,  ruolo,  settore,  utente, checkDataValiditaFineRUteSett), new GetRicercaUtentiService(configurationHelper, utenteDad));
	}
	/**
	 *
	 * @param cf
	 * @return GetUtenteHrByCfResponse
	 */
	public GetUtenteHrByCfResponse getUtenteHrByCf(String cf) {
		return executeService(new GetUtenteHrByCfRequest( cf), new GetUtenteHrByCfService(configurationHelper,externalHelperLookup, utenteDad));
	}
	/**
	 *
	 * @param idSettore
	 * @param utenti
	 * @param controllo
	 * @return PutSpostaUtentiSettoreResponse
	 */
	public PutSpostaUtentiSettoreResponse putSpostaUtentiSettore(UUID idSettoreOld,UUID idSettoreNew, List<Utente> utenti, String controllo) {
		return executeService(new PutSpostaUtentiSettoreRequest(idSettoreOld, idSettoreNew, utenti, controllo), new PutSpostaUtentiSettoreService(configurationHelper, utenteDad));
	}
	/**
	 * 
	 * @param dirigente
	 * @param ruolo
	 * @param settore
	 * @param utente
	 * @return
	 */
	public GetRicercaUtentiNoPageResponse getRicercaUtentiNoPage(Boolean dirigente, Ruolo ruolo, Settore settore, Utente utente) {
		return executeService(new GetRicercaUtentiNoPageRequest( dirigente,  ruolo,  settore,  utente), new GetRicercaUtentiNoPageService(configurationHelper, utenteDad));
	}

}
