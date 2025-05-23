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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetRicercaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetRicercaRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Rmss
 */
public class GetRicercaRmsService extends BaseRmsService<GetRicercaRmsRequest, GetRicercaRmsResponse> {

	UtenteDad utenteDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad, UtenteDad utenteDad) {
		super(configurationHelper, rmsDad);
		this.utenteDad =utenteDad;
	}

	@Override
	protected void execute() {

		final Date dataInserimentoDawithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoDa());
		final Date dataInserimentoAwithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoA());
		final Utente utenteConnesso   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		//List<Ruolo> listaRuoli = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());
		//UtilityVisibilitaDocumentale.isUtentePrivilegiato(utenteDad, utenteConnesso, settoreCorrente);

		/*
		long count = rmsDad.countRicercaRms(
											request.getNumeroRmsDa(),
											request.getNumeroRmsA(),
											request.getAnnoRmsDa(),
											request.getAnnoRmsA(),
											dataInserimentoDawithoutTime,
											dataInserimentoAwithoutTime,
											request.getTestataRms(),
											request.getRigaRms(),
											settoreCorrente.getEnte()
											);
		 */
		final PagedList<TestataRms> testataRmss = rmsDad.getRicercaRms(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getNumeroRmsDa(),
				request.getNumeroRmsA(),
				request.getAnnoRmsDa(),
				request.getAnnoRmsA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getTestataRms(),
				request.getRigaRms(),
				request.getSettoreEmittente(),
				request.getSettore(),
				request.getSettoreIndirizzo(),
				utenteConnesso,
				settoreCorrente
				);

		checkBusinessCondition(!testataRmss.isEmpty(), MsgCpassRms.RMSRMSE0020.getError());
		response.setTestataRmss(testataRmss);
	}
}
