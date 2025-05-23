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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteTestataRmsRequest implements BaseRequest {

	private final UUID idTestataRms;

	/**
	 * Constructor
	 *
	 * @param idTestataRms the id of the TestataRms to delete
	 */
	public DeleteTestataRmsRequest(UUID idTestataRms) {
		this.idTestataRms = idTestataRms;
	}

	/**
	 * @return the idTestataRms
	 */
	public UUID getIdTestataRms() {
		return idTestataRms;
	}



}
