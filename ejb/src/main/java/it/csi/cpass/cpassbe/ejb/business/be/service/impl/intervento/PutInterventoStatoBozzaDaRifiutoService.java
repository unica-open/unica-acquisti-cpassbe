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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoBozzaDaRifiutoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoBozzaDaRifiutoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoBozzaDaRifiutoService extends BaseInterventoService<PutInterventoStatoBozzaDaRifiutoRequest, PutInterventoStatoBozzaDaRifiutoResponse> {


	private final DecodificaDad decodificaDad;
	private List<Intervento> listaIntervento;
	private Stato stato;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoBozzaDaRifiutoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {

		listaIntervento = request.getInterventi();
		stato           = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		for(final Intervento intervento :listaIntervento) {
			final Date now = new Date();
			intervento.setVersioneDefinitiva(Boolean.FALSE);
			intervento.setDataRifiuto(now);
			intervento.setUtenteRifiuto(utenteConnesso);
			intervento.setStato(stato);
			intervento.setStatoXStorico(StatoInterventiEnum.BOZZA_DA_RIFIUTO.getCostante());
			interventoDad.updateStatoIntervento(intervento,utenteConnesso);
		}

	}
}
