/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class PostCopiaRigheRequest implements BaseRequest {

	private final UUID idFrom;
	private final UUID idTo;

	/**
	 * Constructor
	 * 
	 * @param idFrom the id of Destinatario from who copy the RigaOrdine
	 * @param idTo the id of Destinatario to whom copy the RigaOrdine
	 */
	public PostCopiaRigheRequest(UUID idFrom, UUID idTo) {
		this.idFrom = idFrom;
		this.idTo = idTo;
	}

	public UUID getIdFrom() {
		return idFrom;
	}

	public UUID getIdTo() {
		return idTo;
	}
	
}
