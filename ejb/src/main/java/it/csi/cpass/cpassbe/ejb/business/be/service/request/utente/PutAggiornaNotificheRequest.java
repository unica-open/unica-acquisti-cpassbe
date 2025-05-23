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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Notifica;

public class PutAggiornaNotificheRequest implements BaseRequest {

	List<Notifica> listaNotifiche;

	public PutAggiornaNotificheRequest(List<Notifica> listaNotifiche) {
		this.listaNotifiche = listaNotifiche;
	}

	public List<Notifica> getListaNotifiche() {
		return listaNotifiche;
	}

}
