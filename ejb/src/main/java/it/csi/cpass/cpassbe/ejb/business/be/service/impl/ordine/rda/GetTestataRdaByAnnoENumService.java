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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetTestataRdaByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetTestataRdaByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

public class GetTestataRdaByAnnoENumService extends BaseRdaService<GetTestataRdaByAnnoENumRequest, GetTestataRdaByAnnoENumResponse> {

	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetTestataRdaByAnnoENumService(ConfigurationHelper configurationHelper, RdaDad rdaDad, UtenteDad utenteDad) {
		super(configurationHelper, rdaDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {
		final TestataRda testataRda = rdaDad.getByAnnoENumero(request.getAnno(), request.getNumero(), request.getEnteId()).orElseThrow(() -> new NotFoundException("testataRda"));

		final boolean bVisibilitàDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad, testataRda);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0156.getError());
		}

		response.setTestataRda(testataRda);
	}

}
