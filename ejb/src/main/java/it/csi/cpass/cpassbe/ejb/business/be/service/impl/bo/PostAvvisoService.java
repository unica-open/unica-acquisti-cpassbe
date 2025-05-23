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
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PostAvvisoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PostAvvisoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Notifica;

/**
 * Saves an Notifica
 */
public class PostAvvisoService extends BaseBoService<PostAvvisoRequest, PostAvvisoResponse> {

	private Notifica notifica;
	//private final UtenteDad utenteDad;
	private final NotificheDad notificheDad;

	public PostAvvisoService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad, ElaborazioneMessaggioDad elaborazioneMessaggioDad, ElaborazioneTipoDad elaborazioneTipoDad
			//,UtenteDad utenteDad
			,NotificheDad notificheDad) {
		super(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad);
		//this.utenteDad = utenteDad;
		this.notificheDad = notificheDad;
		

	}

	@Override
	protected void checkServiceParams() {
		notifica = request.getNotifica();
		checkNotNull(notifica, "notifica", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		notifica = request.getNotifica();
		notifica = notificheDad.saveAvviso(request.getNotifica());
		response.setNotifica(notifica);

	}
}
