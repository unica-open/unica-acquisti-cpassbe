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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.Validazioni;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.RuoloUtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.UtenteRupDeleghe;
import it.csi.cpass.cpassbe.lib.dto.UtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;

/**
 * Base class for services for /utente path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseNotificheService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for utente */
	protected final NotificheDad notificheDad;
	protected final UtenteDad utenteDad;


	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente dad
	 */
	protected BaseNotificheService(ConfigurationHelper configurationHelper, NotificheDad notificheDad,UtenteDad utenteDad) {
		super(configurationHelper);
		this.notificheDad = notificheDad;
		this.utenteDad    = utenteDad;
	}

	protected Optional<Utente> validaUtente(ExternalHelperLookup externalHelperLookup,SystemDad systemDad,Utente utenteNew,Ente ente,String method) {
		Boolean valido = Boolean.FALSE;
		try {
			valido = new Validazioni().isValidCodiceFiscale(utenteNew.getCodiceFiscale());
			checkBusinessCondition(valido,MsgCpassBo.BACUTEE0018.getError());
		}catch(final Exception e){
			checkBusinessCondition(valido,MsgCpassBo.BACUTEE0018.getError());
		}

		checkBusinessCondition(utenteNew.getUtenteSettores()!= null, MsgCpassBo.BACUTEE0009.getError());
		//verifico che l'utente abbia almeno 1 settore e che lo stesso abbia almeno un ruolo valido
		final List<UtenteSettore> listUtenteSettoreValido = utenteNew.getUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
		Boolean controllo = Boolean.FALSE;
		for(final UtenteSettore us : listUtenteSettoreValido) {
			for(final RuoloUtenteSettore rus:us.getRuoloUtenteSettores()) {
				if(rus.getValido()) {
					controllo = Boolean.TRUE;
				}
			}
		}
		checkBusinessCondition(controllo, MsgCpassBo.BACUTEE0009.getError());

		final Optional<Utente> utente = utenteDad.getUtenteByCf(utenteNew.getCodiceFiscale(),Boolean.FALSE);
		if(method.equalsIgnoreCase("post")) {
			//verifico che l'utente sia effettivamente un utente nuovo
			//checkBusinessCondition(!(utenteDad.getUtenteByCf(utenteNew.getCodiceFiscale(),Boolean.TRUE).isPresent()), MsgCpassBo.BACUTEE0010.getError("cf",utenteNew.getCodiceFiscale()));
			checkBusinessCondition(!(utenteDad.getUtenteByCf(utenteNew.getCodiceFiscale(),Boolean.FALSE).isPresent()), MsgCpassBo.BACUTEE0010.getError("cf",utenteNew.getCodiceFiscale()));
		}

		/*
		List<Parametro> parametri = systemDad.getParametriList("INTEGRAZIONE_HR", "UTENTE-HR","HR", ente.getId());
		//verifico da servizio esterno hr la presenza del cf
		if(parametri != null && parametri.size() > 0 && parametri.get(0).getValore().equalsIgnoreCase("true")) {
			Boolean verificaHr = verificaSuHR(externalHelperLookup,utenteNew.getCodiceFiscale(),ente);
			checkBusinessCondition(verificaHr, MsgCpassBo.BACUTEE0012.getError());
		}
		 */

		//verifico che in presenza di delegati siano tutti censiti sul sistema
		if(utenteNew.getUtenteRupDeleghes()!=null) {
			for(final UtenteRupDeleghe delegato: utenteNew.getUtenteRupDeleghes()) {
				checkBusinessCondition((utenteDad.getUtenteByCf(delegato.getUtenteRupDelegato().getCodiceFiscale(),Boolean.TRUE).isPresent()), MsgCpassBo.BACUTEE0016.getError());
			}
		}

		return utente;
	}

}
