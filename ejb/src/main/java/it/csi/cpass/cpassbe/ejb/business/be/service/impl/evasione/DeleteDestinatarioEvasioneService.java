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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class DeleteDestinatarioEvasioneService extends BaseService<DeleteDestinatarioEvasioneRequest, DeleteDestinatarioEvasioneResponse> {

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 */
	public DeleteDestinatarioEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad,
			RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		UUID idDestinatarioEvasione = request.getIdDestinatarioEvasione();
		checkNotNull(idDestinatarioEvasione, "idDestinatarioEvasione", true);
	}

	@Override
	protected void execute() {
		// Non deve essere possibile eliminare tutti i destinatari associati all’evasione
		DestinatarioEvasione destinatarioEvasione = destinatarioEvasioneDad.findOne(request.getIdDestinatarioEvasione());
		UUID idTestata = destinatarioEvasione.getTestataEvasione().getId();
		List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(idTestata);
		
		checkBusinessCondition(destinatarioEvasiones.size() > 1, MsgCpassOrd.ORDORDE0116.getError());
		
		List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(request.getIdDestinatarioEvasione());

		BigDecimal totaleConIvaDaSottrarreInTestata = BigDecimal.ZERO;
		// elimino tutte le righe collegate
		for (RigaEvasione rigaEvasione : righeEvasiones) {

			// elimino tutti gli impegni e i subimpegni inclusi
			impegnoEvasioneDad.deleteImpegniEvasioneByRiga(rigaEvasione.getId());

			// e la riga
			rigaEvasioneDad.deleteRigaEvasione(rigaEvasione.getId());
			totaleConIvaDaSottrarreInTestata = totaleConIvaDaSottrarreInTestata.add(rigaEvasione.getImportoTotale());
		}

		// elimino il destinatario
		destinatarioEvasioneDad.deleteDestinatarioEvasione(request.getIdDestinatarioEvasione());
		
		//aggiornamento totali su Testata evasione Jira-260
		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(idTestata).get();
		BigDecimal totaleConIvaNew = testataEvasione.getTotaleConIva().subtract(totaleConIvaDaSottrarreInTestata);
		testataEvasione.setTotaleConIva(totaleConIvaNew);
		testataEvasioneDad.updateTestataEvasione(testataEvasione);
	}
}

