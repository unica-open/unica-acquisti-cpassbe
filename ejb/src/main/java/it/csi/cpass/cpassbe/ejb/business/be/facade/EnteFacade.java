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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ente.GetEnteByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ente.GetEnteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ente.GetEnteByIdResponse;

/**
 * Fa&ccedil;ade for the /ente path
 */
@Stateless
@Lock(LockType.READ)
public class EnteFacade extends BaseFacade {
	// Injection point
	@Inject private EnteDad enteDad;



	/**
	 * Retrieves the Ente by its id
	 * @param id the id
	 * @return the ente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetEnteByIdResponse getEnteById(UUID id) {
		return executeService(new GetEnteByIdRequest(id), new GetEnteByIdService(configurationHelper, enteDad));
	}
	

}
