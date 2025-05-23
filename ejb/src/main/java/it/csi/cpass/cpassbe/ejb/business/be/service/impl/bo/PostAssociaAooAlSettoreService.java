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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PostAssociaAooAlSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PostAssociaAooAlSettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;

/**
 * Save PostAssociaAooAlSettoreService
 */
public class PostAssociaAooAlSettoreService extends BaseBoService<PostAssociaAooAlSettoreRequest, PostAssociaAooAlSettoreResponse> {

	private final SettoreDad settoreDad;

	public PostAssociaAooAlSettoreService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			SettoreDad settoreDad
			) {
		super(configurationHelper,elaborazioneDad,elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.settoreDad 				= settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		//checkNotNull(request.getAttachmentCsv(), "file csv aggiorna ods");
		checkNotNull(request.getAooActaId(), "id acta");
		checkNotNull(request.getSettoreId(), "id settore");
	}

	@Override
	protected void execute() {
		Settore settore = new Settore();
		settore.setId(request.getSettoreId());
		AooActa aooActa = new AooActa();
		aooActa.setId(request.getAooActaId());
		settoreDad.insertSettoreAooActa(aooActa, settore);
	}


}
