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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoAnnullatoService extends BaseInterventoService<PutInterventoStatoAnnullatoRequest, PutInterventoStatoAnnullatoResponse> {

	private Intervento intervento;
	private UtenteDad utenteDad;
	
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoAnnullatoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad) {
		super(configurationHelper, interventoDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkModel(intervento, "intervento");
		checkNotNull( intervento.getOptlock(),"opt look");
	}

	@Override
	protected void execute() {
		Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getIntervento(intervento.getId()), "intervento");
		//TODO inserire il controllo sul fatto che l'utente sia abilitato o meno al cambio stato a seconda dello stato dell'acquisto
//		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
       
        // l'intervento deve essere in stato != CANCELLATO
        checkBusinessCondition(!interventoAttuale.getStato().getCodice().equals(CpassStatiEnum.INT_CANCELLATO.getCostante()), MsgCpassPba.PBAACQE0013.getError());
        
		checkOptlock(intervento.getOptlock(), interventoAttuale.getOptlock());
		interventoDad.updateStatoIntervento(request.getIntervento().getId(),CpassStatiEnum.INT_CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante());
	}

	private boolean containsRuolo(List<Ruolo> ruoli,String ruoloCode) {
		boolean ris = false;
		for(Ruolo ruolo : ruoli) {
			if (ruolo.getCodice().equals(ruoloCode)){
				ris = true;
			}
		}
		return ris;
	}
}
