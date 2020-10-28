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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoApprovatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoApprovatoResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Saves an Intervento
 */
public class PutInterventoStatoApprovatoService extends BaseInterventoService<PutInterventoStatoApprovatoRequest, PutInterventoStatoApprovatoResponse> {

	private DecodificaDad decodificaDad;
	private Intervento intervento;
	private Stato stato;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoApprovatoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, DecodificaDad decodificaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkModel(intervento, "intervento");
		checkNotNull( intervento.getOptlock(),"opt look");
	}

	@Override
	protected void execute() {
		//TODO da parlare con Alessandro in merito alla gestione concorrenza
		Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getIntervento(intervento.getId()), "intervento");
		// se in stato annullato non posso approvare l'intervento
		checkBusinessCondition(!interventoAttuale.getStato().getCodice().equals(CpassStatiEnum.INT_CANCELLATO.getCostante()), MsgCpassPba.PBAACQE0021.getError());
		//TODO inserire il controllo sul fatto che l'utente sia abilitato o meno al cambio stato
		checkOptlock(intervento.getOptlock(), interventoAttuale.getOptlock());
		
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		stato = isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.INT_VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		Date now = new Date();
		intervento.setDataValidazione(now);
		intervento.setUtenteValidazione(utenteConnesso);
		intervento.setStato(stato);
		interventoDad.updateStatoIntervento(intervento);

//		interventoDad.updateStatoIntervento(request.getIntervento().getId(),CpassStatiEnum.INT_VALIDATO.getCostante(), "INTERVENTO");
	}
	
}
