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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityAnnullamentoEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class DeleteEvasioneService extends BaseService<DeleteEvasioneRequest, DeleteEvasioneResponse> {

	private final DecodificaDad decodificaDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final DocumentoTrasportoDad documentoTrasportoDad;
	private final DocumentoTrasportoRigaDad documentoTrasportoRigaDad;

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
			DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad, DocumentoTrasportoDad documentoTrasportoDad,
			DocumentoTrasportoRigaDad documentoTrasportoRigaDad) {

		super(configurationHelper);
		this.decodificaDad = decodificaDad;
		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.documentoTrasportoDad = documentoTrasportoDad;
		this.documentoTrasportoRigaDad = documentoTrasportoRigaDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idTestataEvasione = request.getIdTestataEvasione();
		checkNotNull(idTestataEvasione, "idTestataEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		// elimino tutti i destinatari collegati
		final List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getIdTestataEvasione());
		for (final DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

			// elimino tutte le righe collegate
			final List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			for (final RigaEvasione rigaEvasione : righeEvasiones) {

				// elimino tutti gli impegni e i subimpegni inclusi
				impegnoEvasioneDad.deleteImpegniEvasioneByRiga(rigaEvasione.getId());

				// aggiornamento stato riga ordine
				final RigaOrdine rigaOrdine = rigaEvasione.getRigaOrdine();
				UtilityAnnullamentoEvasione.cambioStatoRigaOrdineDaCancellazioneEvasione(decodificaDad, rigaEvasioneDad, rigaOrdineDad, rigaOrdine);

				// aggiorno il documento di trasporto
				final DocumentoTrasportoRiga documentoTrasportoRiga = documentoTrasportoRigaDad.findByRigaEvasioneId(rigaEvasione.getId());
				UtilityAnnullamentoEvasione.aggiornamentoDocumentoDiTrasporto(documentoTrasportoRiga,
						documentoTrasportoDad, documentoTrasportoRigaDad, decodificaDad);

				// elimino la riga
				rigaEvasioneDad.deleteRigaEvasione(rigaEvasione.getId());
			}

			// elimino il destinatario
			destinatarioEvasioneDad.deleteDestinatarioEvasione(destinatarioEvasione.getId());

			// aggiornamento stato destinatario ordine
			// fix- ricarico ogni volta il "DestinatarioOrdine" per evasioni in cui è presente più volte un riferimento a "DestinatarioOrdine" (errore optlock)
			final UUID idDestinatarioOrdine = destinatarioEvasione.getDestinatarioOrdine().getId();
			final Destinatario destinatarioOrdine = destinatarioOrdineDad.getDestinatario(idDestinatarioOrdine).orElseThrow(() -> new NotFoundException("destinatario ordine"));
			UtilityAnnullamentoEvasione.aggiornamentoStatoDestinatarioOrdine(decodificaDad, destinatarioEvasioneDad, destinatarioOrdineDad, destinatarioOrdine);
		}

		testataEvasioneDad.deleteTestataEvasione(request.getIdTestataEvasione());
	}

}
