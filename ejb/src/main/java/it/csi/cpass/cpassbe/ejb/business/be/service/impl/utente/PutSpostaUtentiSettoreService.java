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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutSpostaUtentiSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutSpostaUtentiSettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.UtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Utente
 */
public class PutSpostaUtentiSettoreService extends BaseUtenteService<PutSpostaUtentiSettoreRequest, PutSpostaUtentiSettoreResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public PutSpostaUtentiSettoreService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdSettoreOld(), "id settore old");
		checkNotNull(request.getIdSettoreNew(), "id settore new");
	}

	@Override
	protected void execute() {
		CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		/*  2.7 Controlli formali e di validità
		Occorre verificare se uno o più utenti tra quelli selezionati sono dirigenti (presenza di 1 record nella tabella CPASS_R_DIRIGENTE_SETTORE per il vecchio settore con data_validita_fine non valorizzata oppure maggiore della data di sistema.
		Nel caso non ci fosse neanche un utente dirigente, allora la verifica si intende andata a buon fine, altrimenti no.*/
		final List<Utente> listaUtentiCompleti = new ArrayList<>();
		for(final Utente utente : request.getUtenti()) {
			final Utente utenteCompleto = utenteDad.getUtenteCompleto(utente.getId());
			if(request.getControllo().equalsIgnoreCase("SI")) {
				for(final UtenteSettore utenteSettore: utenteCompleto.getUtenteSettores()) {
					//checkBusinessCondition(!(utenteSettore.getSettore().getId().compareTo(request.getIdSettoreOld())==0 && utenteSettore.getDirigente()) , MsgCpassBo.BACUTEA0015.getError());
					checkBusinessCondition  (utenteSettore.getSettore().getId().compareTo(request.getIdSettoreOld())!=0 || !utenteSettore.getDirigente() , MsgCpassBo.BACUTEA0015.getError());
				}
			}
			listaUtentiCompleti.add(utenteCompleto);
		}
		checkBusinessCondition( !(request.getControllo().equalsIgnoreCase("SI")), MsgCpassBo.BACUTEA0014.getError());

		utenteDad.spostaUtentiDalSettore(listaUtentiCompleti,request.getIdSettoreOld(),request.getIdSettoreNew(),utenteConnesso.getCodiceFiscale());
	}
}
