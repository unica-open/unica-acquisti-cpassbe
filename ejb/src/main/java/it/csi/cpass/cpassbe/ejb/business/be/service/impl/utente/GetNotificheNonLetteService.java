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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;


import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetNotificheNonLetteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetNotificheNonLetteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetNotificheNonLetteService extends BaseNotificheService<GetNotificheNonLetteRequest, GetNotificheNonLetteResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetNotificheNonLetteService(ConfigurationHelper configurationHelper, NotificheDad notificheDad ,UtenteDad utenteDad) {
		super(configurationHelper, notificheDad,utenteDad);
	}

	@Override
	protected void execute() {
		final UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		final List<Notifica> notifiche = notificheDad.getNotificheAllByUtente(utenteId, false);
		notifiche.sort(Comparator.comparing(Notifica::getDataInizio));
		response.setNotifiche(notifiche);
	}
}
