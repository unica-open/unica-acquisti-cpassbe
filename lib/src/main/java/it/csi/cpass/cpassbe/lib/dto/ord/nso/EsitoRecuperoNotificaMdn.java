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

public class EsitoRecuperoNotificaMdn extends EsitoRecuperoDocumento  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private  NotificaMdn notificaMdn;


	/**
	 * @return the documento
	 */
	public NotificaMdn getNotificaMdn() {
		return notificaMdn;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setNotificaMdn(NotificaMdn documentoTrasmesso) {
		this.notificaMdn = documentoTrasmesso;
	}

}
