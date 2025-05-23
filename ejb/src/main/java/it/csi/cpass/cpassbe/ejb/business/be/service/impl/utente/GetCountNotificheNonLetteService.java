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


import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetCountNotificheNonLetteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetCountNotificheNonLetteResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.NotificaInfo;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetCountNotificheNonLetteService extends
BaseNotificheService<GetCountNotificheNonLetteRequest, GetCountNotificheNonLetteResponse> {

	SystemDad systemDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetCountNotificheNonLetteService(ConfigurationHelper configurationHelper,NotificheDad notificheDad, UtenteDad utenteDad, SystemDad systemDad) {
		super(configurationHelper,notificheDad, utenteDad);
		this.systemDad = systemDad;
	}

	@Override
	protected void execute() {
		final UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		final Integer count = notificheDad.getCountNotificheByUtente(utenteId, Boolean.FALSE);
		final Parametro pollingTimeParam = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.POLL_TIME_NOTIFICATIONS.getCostante(),ConstantsCPassParametro.RiferimentoEnum.NOTIFICA.getCostante(), null);

		BigDecimal pollingTime = BigDecimal.valueOf(2.0);

		if(pollingTimeParam != null) {
			pollingTime = BigDecimal.valueOf(Double.parseDouble(pollingTimeParam.getValore()));
		}

		final NotificaInfo info = new NotificaInfo();

		info.setUnreadCount(count);
		info.setPollingTime(pollingTime);

		response.setNotificaInfo(info);
	}
}
