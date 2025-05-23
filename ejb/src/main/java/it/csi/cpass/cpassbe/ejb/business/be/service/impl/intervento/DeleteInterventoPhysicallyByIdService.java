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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;


import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.DeleteInterventoPhysicallyByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.DeleteInterventoPhysicallyByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Deletes an intervento by its id
 */
public class DeleteInterventoPhysicallyByIdService extends BaseInterventoService<DeleteInterventoPhysicallyByIdRequest, DeleteInterventoPhysicallyByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public DeleteInterventoPhysicallyByIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final String methodName = "DeleteInterventoPhysicallyByIdService";
		log.info(methodName , "START");
		final List<Intervento> lista = interventoDad.getInterventiByCapofilaId(request.getId(),null);
		log.info(methodName , "lista " + lista.size());
		checkBusinessCondition(lista.isEmpty(), MsgCpassPba.PBAACQE0095.getError());
		log.info(methodName , "deleteInterventoPhysically");
		interventoDad.deleteInterventoPhysically(request.getId());
	}

}
