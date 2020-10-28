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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityAnnullamentoEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class DeleteEvasioneService extends BaseService<DeleteEvasioneRequest, DeleteEvasioneResponse> {

	private final DecodificaDad decodificaDad;

	private final TestataEvasioneDad testataEvasioneDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;

	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 */
	public DeleteEvasioneService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad, TestataEvasioneDad testataEvasioneDad,
			DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper);
		this.decodificaDad = decodificaDad;

		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;

		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		UUID idTestataEvasione = request.getIdTestataEvasione();
		checkNotNull(idTestataEvasione, "idTestataEvasione", true);
	}

	@Override
	protected void execute() {
		// elimino tutti i destinatari collegati
		List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getIdTestataEvasione());
		for (DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

			// elimino tutte le righe collegate
			List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			for (RigaEvasione rigaEvasione : righeEvasiones) {

				// elimino tutti gli impegni e i subimpegni inclusi
				impegnoEvasioneDad.deleteImpegniEvasioneByRiga(rigaEvasione.getId());

				// e la riga
				rigaEvasioneDad.deleteRigaEvasione(rigaEvasione.getId());

				// aggiornamento stato riga ordine
				RigaOrdine rigaOrdine = rigaEvasione.getRigaOrdine();
				UtilityAnnullamentoEvasione.aggiornamentoStatoRigaOrdine(decodificaDad, rigaEvasioneDad, rigaOrdineDad, rigaOrdine);
			}

			// elimino il destinatario
			destinatarioEvasioneDad.deleteDestinatarioEvasione(destinatarioEvasione.getId());

			// aggiornamento stato destinatario ordine
			// fix- ricarico ogni volta il "DestinatarioOrdine" per evasioni in cui è presente più volte un riferimento a "DestinatarioOrdine" (errore optlock)
			UUID idDestinatarioOrdine = destinatarioEvasione.getDestinatarioOrdine().getId();
			Destinatario destinatarioOrdine = destinatarioOrdineDad.getDestinatario(idDestinatarioOrdine).get();
			UtilityAnnullamentoEvasione.aggiornamentoStatoDestinatarioOrdine(decodificaDad, destinatarioEvasioneDad, destinatarioOrdineDad, destinatarioOrdine);
		}

		testataEvasioneDad.deleteTestataEvasione(request.getIdTestataEvasione());
	}

}
