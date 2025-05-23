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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class DeleteRigaOrdineService extends BaseService<DeleteRigaOrdineRequest, DeleteRigaOrdineResponse> {

	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteRigaOrdineService(ConfigurationHelper configurationHelper, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad) {
		super(configurationHelper);
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioDad = destinatarioDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idRiga = request.getIdRiga();
		checkNotNull(idRiga, "idRiga", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final RigaOrdine rigaOrdine = rigaOrdineDad.findOne(request.getIdRiga());

		// elimino tutti gli impegni collegati
		impegnoDad.deleteImpegniByRiga(request.getIdRiga());

		// elimino la riga
		rigaOrdineDad.deleteRiga(request.getIdRiga());

		UtilityRigaOrdine.aggiornamentoTotali(null, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
	}

}
