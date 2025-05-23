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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaRigheDaEvadereRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaRigheDaEvadereResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

/**
 * Retrieves an Ordines
 */
public class GetRicercaRigheDaEvadereService extends BaseService<GetRicercaRigheDaEvadereRequest, GetRicercaRigheDaEvadereResponse> {

	RigaOrdineDad rigaOrdineDad;
	UtenteDad utenteDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaRigheDaEvadereService(ConfigurationHelper configurationHelper, RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper);
		this.rigaOrdineDad = rigaOrdineDad;
	}

	@Override
	protected void execute() {

		final long count = rigaOrdineDad.countRigheDaEvadere(
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				request.getDataEmissioneDa(),
				request.getDataEmissioneA(),
				request.getTestataOrdine(),
				request.getDestinatario(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getRigaOrdine(),
				request.getOdsList());

		checkBusinessCondition(count>0, MsgCpassOrd.ORDORDE0043.getError()); // Nessun ordine presente

		final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheDaEvadere(
				request.getAnnoOrdineDa(),
				request.getNumeroOrdineDa(),
				request.getAnnoOrdineA(),
				request.getNumeroOrdineA(),
				request.getDataEmissioneDa(),
				request.getDataEmissioneA(),
				request.getTestataOrdine(),
				request.getDestinatario(),
				request.getImpegno(),
				request.getSubimpegno(),
				request.getRigaOrdine(),
				request.getOdsList()
				);

		checkBusinessCondition(rigaOrdines.size()>0, MsgCpassOrd.ORDORDE0044.getError());// Ordini non di competenza

		response.setRigheOrdine(rigaOrdines);
	}

}
