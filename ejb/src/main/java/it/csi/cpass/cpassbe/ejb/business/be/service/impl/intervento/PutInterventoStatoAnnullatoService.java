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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoAnnullatoService extends BaseInterventoService<PutInterventoStatoAnnullatoRequest, PutInterventoStatoAnnullatoResponse> {

	private Intervento intervento;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoAnnullatoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkModel(intervento, "intervento");
		checkNotNull( intervento.getOptlock(),"opt lock");
	}

	@Override
	protected void execute() {
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(intervento.getId()), "intervento");
		// l'intervento deve essere in stato != CANCELLATO
		final Utente utente= CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		checkBusinessCondition(!interventoAttuale.getStato().getCodice().equals(StatoInterventiEnum.CANCELLATO.getCostante()), MsgCpassPba.PBAACQE0013.getError());
		intervento.setStatoXStorico(StatoInterventiEnum.CANCELLATO.getCostante());
		interventoDad.updateStatoIntervento(intervento,StatoInterventiEnum.CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante(),utente);
	}

}
