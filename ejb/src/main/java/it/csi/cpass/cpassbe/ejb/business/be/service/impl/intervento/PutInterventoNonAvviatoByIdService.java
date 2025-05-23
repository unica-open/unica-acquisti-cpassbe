/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoNonAvviatoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoNonAvviatoByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.dto.pba.StatiIntervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutInterventoNonAvviatoByIdService extends BaseInterventoService<PutInterventoNonAvviatoByIdRequest, PutInterventoNonAvviatoByIdResponse> {

	UtenteDad utenteDad;

	public PutInterventoNonAvviatoByIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad) {
		super(configurationHelper, interventoDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull( request.getInterventoId(),"id intervento");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		log.info(methodName, "Start");
		final UUID interventoId = request.getInterventoId();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(request.getInterventoId()), "intervento");
		final StatiIntervento statoPrecedente = interventoDad.getStatoPrecedenteInterventoByInterventoId(interventoId);
		final List<InterventoAltriDati> listaAd = interventoAttuale.getListInterventoAltriDati();

		interventoAttuale.setAvviato(Boolean.FALSE);
		interventoAttuale.setUtenteAvviato(null);
		interventoAttuale.setDataAvviato(null);
		if(!listaAd.isEmpty()) {
			for(final InterventoAltriDati ad: listaAd ) {
				ad.setMotiviEsclusioneCig(null);
				interventoDad.saveAltriDati(ad);
			}
		}
		interventoDad.updateInterventoMinimal(interventoAttuale);
		interventoDad.deleteCigByInterventoIdMinimal(interventoId);
		interventoDad.salvaStoricoStatoIntervento(utenteConnesso,interventoAttuale , statoPrecedente.getStato());

		log.info(methodName, "Stop");

	}


}
