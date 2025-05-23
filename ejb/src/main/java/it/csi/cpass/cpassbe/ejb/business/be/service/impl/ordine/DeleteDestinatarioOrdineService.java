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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class DeleteDestinatarioOrdineService extends BaseService<DeleteDestinatarioOrdineRequest, DeleteDestinatarioOrdineResponse> {

	private final DestinatarioOrdineDad destinatarioDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteDestinatarioOrdineService(ConfigurationHelper configurationHelper,DestinatarioOrdineDad destinatarioDad, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.destinatarioDad = destinatarioDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idDestinatario = request.getIdDestinatario();
		checkNotNull(idDestinatario, "idDestinatario", Boolean.TRUE);
	}

	@Override
	protected void execute() {

		final List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(request.getIdDestinatario());

		// elimino tutte le righe collegate
		for(final RigaOrdine riga : righe) {

			//elimino tutti gli impegni e i subimpegni inclusi
			impegnoDad.deleteImpegniByRiga(riga.getId());

			//e la riga
			rigaOrdineDad.deleteRiga(riga.getId());
		}

		// elimino il destinatario
		destinatarioDad.deleteDestinatario(request.getIdDestinatario());
	}
}
