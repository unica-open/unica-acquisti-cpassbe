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

import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetTestataRmsByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetTestataRmsByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetTestataRmsByAnnoENumService extends BaseRmsService<GetTestataRmsByAnnoENumRequest, GetTestataRmsByAnnoENumResponse> {

	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param RmsDad    the RmsDad DAD
	 */
	public GetTestataRmsByAnnoENumService(ConfigurationHelper configurationHelper, RmsDad rmsDad, UtenteDad utenteDad) {
		super(configurationHelper, rmsDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {

		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		final TestataRms testataRms= rmsDad.getByAnnoENumero(request.getAnno(), request.getNumero(), settoreCorrente.getEnte().getId()).orElseThrow(() -> new NotFoundException("testataRms"));

		final boolean bVisibilitaDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad,testataRms);

		checkBusinessCondition(bVisibilitaDocumentale, MsgCpassRms.RMSRMSE0020.getError());

		response.setTestataRms(testataRms);
	}

}
