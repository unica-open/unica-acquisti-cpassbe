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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class DeleteRigaEvasioneService extends BaseService<DeleteRigaEvasioneRequest, DeleteRigaEvasioneResponse> {

	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteRigaEvasioneService(ConfigurationHelper configurationHelper, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad) {
		super(configurationHelper);
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		UUID idRigaEvasione = request.getIdRigaEvasione();
		checkNotNull(idRigaEvasione, "idRigaEvasione", true);
	}

	@Override
	protected void execute() {
		// Non deve essere possibile eliminare tutte le righe associate al destinatario
		RigaEvasione rigaEvasione = rigaEvasioneDad.findOne(request.getIdRigaEvasione());
		List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(rigaEvasione.getDestinatarioEvasione().getId());
		if (rigaEvasiones.size() == 1) {
			response.addApiError(MsgCpassOrd.ORDORDE0115.getError());
			return;
		}
		
		// elimino tutti gli impegni collegati
		impegnoEvasioneDad.deleteImpegniEvasioneByRiga(request.getIdRigaEvasione());
		
		// elimino la riga
		rigaEvasioneDad.deleteRigaEvasione(request.getIdRigaEvasione());
	}

}
