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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityAnnullamentoEvasione;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutEvasioneAnnullaByIdService extends BaseTestataEvasioneService<PutEvasioneAnnullaByIdRequest, PutEvasioneAnnullaByIdResponse> {

	private final DecodificaDad decodificaDad;
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
	 * @param externalHelperLookup
	 * @param decodificaDad
	 * @param testataEvasioneDad
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param destinatarioOrdineDad
	 * @param rigaOrdineDad
	 */
	public PutEvasioneAnnullaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, DecodificaDad decodificaDad,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad,
			ImpegnoEvasioneDad impegnoEvasioneDad, DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad,
			DocumentoTrasportoDad documentoTrasportoDad, DocumentoTrasportoRigaDad documentoTrasportoRigaDad) {

		super(configurationHelper, testataEvasioneDad);
		this.decodificaDad = decodificaDad;
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
	}

	@Override
	protected void execute() {
		// per tutti i destinatari collegati
		final List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getId());
		for (final DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

			// per tutte le righe collegate
			final List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			for (final RigaEvasione rigaEvasione : righeEvasiones) {

				// cancellazione "logica" di tutti gli impegni e i subimpegni inclusi
				impegnoEvasioneDad.annullamentoLogicoImpegniEvasioneByRiga(rigaEvasione.getId());

				// aggiornare stato riga evasione
				final String codiceStatoRigaEvasione = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante();
				final Stato statoRigaEvasione = decodificaDad.getStato(codiceStatoRigaEvasione,ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante());
				rigaEvasione.setStato(statoRigaEvasione);
				rigaEvasioneDad.updateRigaEvasione(rigaEvasione);

				// aggiornamento stato riga ordine
				final RigaOrdine rigaOrdine = rigaEvasione.getRigaOrdine();
				UtilityAnnullamentoEvasione.cambioStatoRigaOrdineDaCancellazioneEvasione(decodificaDad, rigaEvasioneDad, rigaOrdineDad, rigaOrdine);

				// aggiorno il documento di trasporto
				final DocumentoTrasportoRiga documentoTrasportoRiga = documentoTrasportoRigaDad.findByRigaEvasioneId(rigaEvasione.getId());
				UtilityAnnullamentoEvasione.aggiornamentoDocumentoDiTrasporto(documentoTrasportoRiga,
						documentoTrasportoDad, documentoTrasportoRigaDad, decodificaDad);
			}

			// aggiornare stato destinatario evasione
			final String codiceStatoRigaEvasione = ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_ANNULLATO.getCostante();
			final Stato statoRigaEvasione = decodificaDad.getStato(codiceStatoRigaEvasione,ConstantsCPassStato.TipoStatoEnum.DESTINATARIO_EVASIONE.getCostante());
			destinatarioEvasione.setStato(statoRigaEvasione);
			destinatarioEvasioneDad.updateDestinatarioEvasione(destinatarioEvasione);

			// aggiornamento stato destinatario ordine
			final Destinatario destinatarioOrdine = destinatarioEvasione.getDestinatarioOrdine();
			UtilityAnnullamentoEvasione.aggiornamentoStatoDestinatarioOrdine(decodificaDad, destinatarioEvasioneDad, destinatarioOrdineDad, destinatarioOrdine);
		}

		// aggiornare stato testata evasione
		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		final String codiceStatoTestataEvasione = StatoEvasioneEnum.ANNULLATA.getCostante();
		final Stato statoEvasione = decodificaDad.getStato(codiceStatoTestataEvasione, ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante());
		testataEvasione.setStato(statoEvasione);
		testataEvasioneDad.updateTestataEvasione(testataEvasione);

		response.setTestataEvasione(testataEvasione);
	}

}
