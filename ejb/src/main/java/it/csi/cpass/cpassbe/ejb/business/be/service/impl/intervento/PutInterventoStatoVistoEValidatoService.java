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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoEValidatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoEValidatoResponse;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoVistoEValidatoService extends BaseInterventoService<PutInterventoStatoVistoEValidatoRequest, PutInterventoStatoVistoEValidatoResponse> {


	private final DecodificaDad decodificaDad;
	private List<Intervento> listaIntervento;
	private Stato stato;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoVistoEValidatoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		listaIntervento = request.getInterventi();
		checkBusinessCondition(request.getInterventi()!=null, CoreError.REQUIRED_PARAMETER_OMITTED.getError());
	}

	@Override
	protected void execute() {
		stato           = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		new ArrayList<ApiError>();
		for(final Intervento interventoId :listaIntervento) {
			checkModel(interventoId, "intervento");
			checkNotNull( interventoId.getOptlock(),"opt lock");
			final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(interventoId.getId()), "intervento");

			//todo utente creazione arriva null dal f.e. capire come valorizzarlo task-298
			final Intervento intervento = CpassMappers.INTERVENTO.cloneToModel(interventoAttuale);
			//TODO da parlare con Alessandro in merito alla gestione concorrenza
			final Date now = new Date();
			if (intervento.getStato().getCodice().equals(StatoInterventiEnum.BOZZA.getCostante())) {
				intervento.setDataVisto(now);
				intervento.setUtenteVisto(utenteConnesso);
			}
			intervento.setDataValidazione(now);
			intervento.setUtenteValidazione(utenteConnesso);
			intervento.setStato(stato);
			intervento.setStatoXStorico(StatoInterventiEnum.VISTATO_VALIDATO.getCostante());
			interventoDad.updateStatoIntervento(intervento,utenteConnesso);
		}
	}
}
