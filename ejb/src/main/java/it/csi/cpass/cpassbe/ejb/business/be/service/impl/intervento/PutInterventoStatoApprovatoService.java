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

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoApprovatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoApprovatoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Intervento
 */
public class PutInterventoStatoApprovatoService extends BaseInterventoService<PutInterventoStatoApprovatoRequest, PutInterventoStatoApprovatoResponse> {

	private final DecodificaDad decodificaDad;
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
		checkNotNull( intervento.getOptlock(),"opt lock");
	}

	@Override
	protected void execute() {
		//TODO da parlare con Alessandro in merito alla gestione concorrenza
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(intervento.getId()), "intervento");
		// se in stato annullato non posso approvare l'intervento
		checkBusinessCondition(!interventoAttuale.getStato().getCodice().equals(StatoInterventiEnum.CANCELLATO.getCostante()), MsgCpassPba.PBAACQE0021.getError());
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		stato = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		final Date now = new Date();
		intervento.setDataValidazione(now);
		intervento.setUtenteValidazione(utenteConnesso);
		intervento.setStato(stato);
		intervento.setStatoXStorico(StatoInterventiEnum.VALIDATO.getCostante());
		interventoDad.updateStatoIntervento(intervento,utenteConnesso);
	}

}
