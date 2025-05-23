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
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventiStatoVistoRagioneriaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventiStatoVistoRagioneriaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventiStatoVistoRagioneriaService extends BaseInterventoService<PutInterventiStatoVistoRagioneriaRequest, PutInterventiStatoVistoRagioneriaResponse> {


	private List<Intervento> listaIntervento;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventiStatoVistoRagioneriaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		listaIntervento = request.getInterventi();
		checkBusinessCondition(request.getInterventi()!=null, CoreError.REQUIRED_PARAMETER_OMITTED.getError());
	}

	@Override
	protected void execute() {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		for(final Intervento intervento :listaIntervento) {
			checkModel(intervento, "intervento");
			final Date now = new Date();
			final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(intervento.getId()), "intervento");
			interventoAttuale.setDataVistoRagioneria(now);
			interventoAttuale.setUtenteVistoRagioneria(utenteConnesso);
			//interventoAttuale.setStato(stato);
			interventoAttuale.setVistoRagioneria(Boolean.TRUE);
			interventoDad.updateInterventoEasy(interventoAttuale);
			interventoDad.salvaStoricoStatoIntervento( utenteConnesso, interventoAttuale,StatoInterventiEnum.VISTO_DA_RAGIONERIA.getCostante());
		}
	}

}
