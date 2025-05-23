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
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoResponse;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoVistoService extends BaseInterventoService<PutInterventoStatoVistoRequest, PutInterventoStatoVistoResponse> {


	private final DecodificaDad decodificaDad;
	private List<Intervento> listaIntervento;
	private Stato stato;
	private final SystemDad systemDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoVistoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad, SystemDad systemDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		listaIntervento = request.getInterventi();
		checkBusinessCondition(request.getInterventi()!=null, CoreError.REQUIRED_PARAMETER_OMITTED.getError());
	}

	@Override
	protected void execute() {
		stato = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.VISTO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Parametro isVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, ente.getId());
		int scarto = 0;
		// vanno vistati solo gli interventi con stato BOZZA
		// se presente nella parametri GESTIONE_ACQUISTO_VERS_DEFINITIVA a TRUE vanvistati quelli in bozza con versione definitiva a true
		for(final Intervento interventoId :listaIntervento) {

			checkModel(interventoId, "intervento");
			checkNotNull( interventoId.getOptlock(),"opt lock");
			final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(interventoId.getId()), "intervento");

			//todo utente creazione arriva null dal f.e. capire come valorizzarlo task-298
			final Intervento intervento = CpassMappers.INTERVENTO.cloneToModel(interventoAttuale);

			final Date now = new Date();

			if(interventoAttuale.getStato().getCodice().equalsIgnoreCase(StatoInterventiEnum.BOZZA.getCostante())) {
				if(isVersioneDefinitiva == null || isVersioneDefinitiva.getValore().equalsIgnoreCase("false")) {
					intervento.setDataVisto(now);
					intervento.setUtenteVisto(utenteConnesso);
					intervento.setStato(stato);
					intervento.setStatoXStorico(StatoInterventiEnum.VISTO.getCostante());
					interventoDad.updateStatoIntervento(intervento,utenteConnesso);
				}else {
					if(interventoAttuale.getVersioneDefinitiva()) {
						intervento.setDataVisto(now);
						intervento.setUtenteVisto(utenteConnesso);
						intervento.setStato(stato);
						intervento.setStatoXStorico(StatoInterventiEnum.VISTO.getCostante());
						interventoDad.updateStatoIntervento(intervento,utenteConnesso);
					}else {
						scarto ++;
					}
				}
			}

			if(scarto>0) {
				final ApiError warning = new ApiError();
				warning.setCode(MsgCpassPba.PBAACQA0054.getCode());
				response.addApiWarnings(new ApiError());
			}
		}
	}
}

