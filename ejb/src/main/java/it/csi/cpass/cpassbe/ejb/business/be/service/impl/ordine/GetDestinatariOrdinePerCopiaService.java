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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDestinatariOrdinePerCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDestinatariOrdinePerCopiaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class GetDestinatariOrdinePerCopiaService extends BaseService<GetDestinatariOrdinePerCopiaRequest, GetDestinatariOrdinePerCopiaResponse> {
	
	DestinatarioOrdineDad destinatarioDad;
	
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetDestinatariOrdinePerCopiaService(ConfigurationHelper configurationHelper, DestinatarioOrdineDad destinatarioDad) {
		super(configurationHelper);
		this.destinatarioDad = destinatarioDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdTestataOrdine(), "tetataOrdineId");
	}

	@Override
	protected void execute() {
		List<Destinatario> destinatari = destinatarioDad.getDestinatariPerCopia(request.getIdTestataOrdine());
		response.setDestinatari(destinatari);
	}
}
