/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.nso;

import java.io.Serializable;

public class EsitoRecuperoNotificaDiScarto extends EsitoRecuperoDocumento  implements Serializable {

	private static final long serialVersionUID = 7043471263487087944L;
	private NotificaDiScarto notificaDiScarto;


	/**
	 * @return the documento
	 */
	public NotificaDiScarto getNotificaDiScarto() {
		return notificaDiScarto;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setNotificaDiScarto(NotificaDiScarto notificaDiScarto) {
		this.notificaDiScarto = notificaDiScarto;
	}

}
