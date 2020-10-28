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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaOrdiniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaOrdiniResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Ordines
 */
public class GetRicercaOrdiniService extends BaseService<GetRicercaOrdiniRequest, GetRicercaOrdiniResponse> {

	TestataOrdineDad testataOrdineDad;
	UtenteDad utenteDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaOrdiniService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
	}

	@Override
	protected void execute() {
		
		long count = testataOrdineDad.countRicercaOrdini(
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				request.getDataEmissioneDa(),
				request.getDataEmissioneA(),
				request.getTestataOrdine(),
				request.getDestinatario(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getRigaOrdine());
		
		checkBusinessCondition(count>0, MsgCpassOrd.ORDORDE0043.getError());
		
		PagedList<TestataOrdine> testataOrdines = testataOrdineDad.getRicercaOrdini(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				request.getDataEmissioneDa(),
				request.getDataEmissioneA(),
				request.getTestataOrdine(),
				request.getDestinatario(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getRigaOrdine()
				);
		
		checkBusinessCondition(!testataOrdines.isEmpty(), MsgCpassOrd.ORDORDE0044.getError());
		response.setTestataOrdines(testataOrdines);
	}

}
