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

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetTestataEvasioneByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetTestataEvasioneByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class GetTestataEvasioneByAnnoENumService extends BaseTestataEvasioneService<GetTestataEvasioneByAnnoENumRequest, GetTestataEvasioneByAnnoENumResponse> {

	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetTestataEvasioneByAnnoENumService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad, UtenteDad utenteDad) {
		super(configurationHelper, testataEvasioneDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {
		final TestataEvasione testataEvasione = testataEvasioneDad.getByAnnoENumero(request.getAnno(), request.getNumero(), request.getEnteId());

		final boolean bVisibilitàDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad, testataEvasione);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0082.getError());
		}
		response.setTestataEvasione(testataEvasione);
	}

}
