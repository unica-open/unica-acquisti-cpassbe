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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaEvasioniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaEvasioniResponse;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Ordines
 */
public class GetRicercaEvasioniService extends BaseService<GetRicercaEvasioniRequest, GetRicercaEvasioniResponse> {

	TestataEvasioneDad testataEvasioneDad;
	UtenteDad utenteDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaEvasioniService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
	}

	@Override
	protected void execute() {

		final Date dataInserimentoDawithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoDa());
		final Date dataInserimentoAwithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoA());
		final Date dataEmissioneDawithoutTime = DateUtility.getDateWithoutTime(request.getDataEmissioneDa());
		final Date dataEmissioneAwithoutTime = DateUtility.getDateWithoutTime(request.getDataEmissioneA());

		final long count = testataEvasioneDad.countRicercaEvasioni(
				request.getAnnoEvasioneDa(),
				request.getNumeroEvasioneDa(),
				request.getAnnoEvasioneA(),
				request.getNumeroEvasioneA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				dataEmissioneDawithoutTime,
				dataEmissioneAwithoutTime,
				request.getAnnoProvvedimento(),
				request.getNumeroProvvedimento(),
				request.getProvvedimentoTipo(),
				request.getTestataEvasione(),
				request.getDestinatarioEvasione(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getOggettiSpesa());

		checkBusinessCondition(count>0, MsgCpassOrd.ORDORDE0081.getError());

		final PagedList<TestataEvasione> testataEvasiones = testataEvasioneDad.getRicercaEvasioni(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getAnnoEvasioneDa(),
				request.getNumeroEvasioneDa(),
				request.getAnnoEvasioneA(),
				request.getNumeroEvasioneA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				dataEmissioneDawithoutTime,
				dataEmissioneAwithoutTime,
				request.getAnnoProvvedimento(),
				request.getNumeroProvvedimento(),
				request.getProvvedimentoTipo(),
				request.getTestataEvasione(),
				request.getDestinatarioEvasione(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getOggettiSpesa()
				);

		checkBusinessCondition(!testataEvasiones.isEmpty(), MsgCpassOrd.ORDORDE0082.getError());

		response.setTestataEvasiones(testataEvasiones);
	}

}
