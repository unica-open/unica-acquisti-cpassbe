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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Notifica;

/**
 * Response for getting user's unread notifications
 */
public class GetNotificheNonLetteResponse extends BaseGetResponse<List<Notifica>> {

	private List<Notifica> notifiche;

	/**
	 * @return list of unread notifications
	 */
	public List<Notifica> getNotifiche() {
		return notifiche;
	}

	/**
	 * @param notifiche
	 */
	public void setNotifiche(List<Notifica> notifiche) {
		this.notifiche = notifiche;
	}

	@Override
	protected List<Notifica> getEntity() {
		return notifiche;
	}
}
