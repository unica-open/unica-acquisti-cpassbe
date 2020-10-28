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

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVisibilitàDocumentale;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestataOrdineByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestataOrdineByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class GetTestataOrdineByAnnoENumService extends BaseTestataOrdineService<GetTestataOrdineByAnnoENumRequest, GetTestataOrdineByAnnoENumResponse> {

	private UtenteDad utenteDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetTestataOrdineByAnnoENumService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, UtenteDad utenteDad) {
		super(configurationHelper, testataOrdineDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {
		// TestataOrdine testataOrdine = testataOrdineDad.getByAnnoENumero(request.getAnno(), request.getNumero(), request.getIdSettore());
		TestataOrdine testataOrdine = testataOrdineDad.getByAnnoENumero(request.getAnno(), request.getNumero(), request.getEnteId());

		boolean bVisibilitàDocumentale = UtilityVisibilitàDocumentale.isVisibile(utenteDad, testataOrdine);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0044.getError());
		}

		response.setTestataOrdine(testataOrdine);
	}

}
