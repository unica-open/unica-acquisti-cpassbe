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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.NotificaInfo;

/**
 * Response for getting count of user's unread notifications
 */
public class GetCountNotificheNonLetteResponse extends BaseGetResponse<NotificaInfo> {

	private NotificaInfo notificaInfo;


	public NotificaInfo getNotificaInfo() {
		return notificaInfo;
	}

	public void setNotificaInfo(NotificaInfo notificaInfo) {
		this.notificaInfo = notificaInfo;
	}

	@Override
	protected NotificaInfo getEntity() {
		return notificaInfo;
	}


}
