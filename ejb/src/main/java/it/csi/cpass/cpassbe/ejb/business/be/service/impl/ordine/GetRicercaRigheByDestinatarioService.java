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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaRigheByDestinatarioRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaRigheByDestinatarioResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class GetRicercaRigheByDestinatarioService extends BaseService<GetRicercaRigheByDestinatarioRequest, GetRicercaRigheByDestinatarioResponse> {
	
	RigaOrdineDad rigaOrdineDad;
	
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetRicercaRigheByDestinatarioService(ConfigurationHelper configurationHelper, RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper);
		this.rigaOrdineDad = rigaOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdDestinatario(), "destinatarioId");
	}

	@Override
	protected void execute() {
		List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(request.getIdDestinatario());
		response.setRigheOrdine(righe);
	}

}
