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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetTestataEvasioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetTestataEvasioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Retrieves an testataEvasione by its id
 */
public class GetTestataEvasioneByIdService extends BaseTestataEvasioneService<GetTestataEvasioneByIdRequest, GetTestataEvasioneByIdResponse> {

	private final UtenteDad utenteDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public GetTestataEvasioneByIdService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad, UtenteDad utenteDad, DestinatarioEvasioneDad destinatarioEvasioneDad) {
		super(configurationHelper, testataEvasioneDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataEvasione"));

		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		setSede(testataEvasione);
		final boolean bVisibilitàDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad, testataEvasione);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0082.getError());
		}
		response.setTestataEvasione(testataEvasione);
	}

	private void setSede(TestataEvasione testataEvasione) {
		for(final DestinatarioEvasione destinatarioEvasione: testataEvasione.getDestinatarioEvasiones()) {
			if(destinatarioEvasione.getDestinatarioOrdine().getSettoreIndirizzo() != null) {
				destinatarioEvasione.setSede(destinatarioEvasione.getDestinatarioOrdine().getSettoreIndirizzo().getDescrizione());
				if(destinatarioEvasione.getDestinatarioOrdine().getSettoreIndirizzo().getPrincipale() != null && destinatarioEvasione.getDestinatarioOrdine().getSettoreIndirizzo().getPrincipale() ) {
					destinatarioEvasione.setSede(CpassEnum.PRINCIPALE.getCostante());
				}

			}
		}

	}

}
