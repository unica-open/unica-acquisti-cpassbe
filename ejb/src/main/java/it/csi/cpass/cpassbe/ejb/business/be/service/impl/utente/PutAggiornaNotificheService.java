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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutAggiornaNotificheRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutAggiornaNotificheResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutAggiornaNotificheService extends BaseNotificheService<PutAggiornaNotificheRequest, PutAggiornaNotificheResponse> {

	public PutAggiornaNotificheService(ConfigurationHelper configurationHelper,NotificheDad notificheDad, UtenteDad utenteDad) {
		super(configurationHelper,notificheDad, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getListaNotifiche(), "listaNotifiche");
	}

	@Override
	protected void execute() {

		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<Notifica> listaNotifiche = request.getListaNotifiche();

		notificheDad.putAggiornaRNotifiche(utente, listaNotifiche);

	}

}
