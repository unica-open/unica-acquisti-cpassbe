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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe.StampaAllegatoInterventoByProgrammaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe.StampaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe.StampaAllegatoInterventoByProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe.StampaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.stampe.StampaAllegatoInterventoByProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.stampe.StampaResponse;

/**
 * Fa&ccedil;ade for the /intervento path
 */
@Stateless
@Lock(LockType.READ)
public class StampeFacade extends BaseFacade {

	@Inject private StampeDad stampeDad;
	/**
	 * Prints the AllegatoSchedaB by programma
	 * @param id the id
	 * @param formatFile the format file
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public StampaAllegatoInterventoByProgrammaResponse stampaAllegatoIntervento(UUID id, String formatFile) {
		return executeService(new StampaAllegatoInterventoByProgrammaRequest(id, formatFile), new StampaAllegatoInterventoByProgrammaService(configurationHelper, stampeDad));
	}

	/**
	 * Stampa
	 * @param nomeStampa the nome stampa
	 * @param formatFile the format
	 * @param listaParametri the parametri
	 * @return the stampa
	 */
	public StampaResponse stampa(String nomeStampa, String formatFile, List<String> listaParametri) {
		return executeService(new StampaRequest( nomeStampa,  formatFile, listaParametri), new StampaService(configurationHelper, stampeDad));
	}
	
}
