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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityAnnullamentoEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutEvasioneAnnullaByIdService extends BaseTestataEvasioneService<PutEvasioneAnnullaByIdRequest, PutEvasioneAnnullaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;

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
	 * @param testataEvasioneDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutEvasioneAnnullaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, DecodificaDad decodificaDad,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad,
			ImpegnoEvasioneDad impegnoEvasioneDad, DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;

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
	}

	@Override
	protected void execute() {
		// per tutti i destinatari collegati
		List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getId());
		for (DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

			// per tutte le righe collegate
			List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			for (RigaEvasione rigaEvasione : righeEvasiones) {

				// cancellazione "logica" di tutti gli impegni e i subimpegni inclusi
				impegnoEvasioneDad.annullamentoLogicoImpegniEvasioneByRiga(rigaEvasione.getId());

				// aggiornare stato riga evasione
				String codiceStatoRigaEvasione = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_EVASIONE_ANNULLATA.getCostante();
				StatoElOrdine statoElOrdineRigaEvasione = decodificaDad.getStatoElOrdine(codiceStatoRigaEvasione,
						ConstantsCPassStatoElOrdine.TipoEnum.RIGA_EVASIONE.getCostante());
				rigaEvasione.setStatoElOrdine(statoElOrdineRigaEvasione);
				rigaEvasioneDad.updateRigaEvasione(rigaEvasione);

				// aggiornamento stato riga ordine
				RigaOrdine rigaOrdine = rigaEvasione.getRigaOrdine();
				UtilityAnnullamentoEvasione.aggiornamentoStatoRigaOrdine(decodificaDad, rigaEvasioneDad, rigaOrdineDad, rigaOrdine);
			}

			// aggiornare stato destinatario evasione
			String codiceStatoRigaEvasione = ConstantsCPassStatoElOrdine.StatoEnum.DESTINATARIO_EVASIONE_ANNULLATO.getCostante();
			StatoElOrdine statoElOrdineRigaEvasione = decodificaDad.getStatoElOrdine(codiceStatoRigaEvasione,
					ConstantsCPassStatoElOrdine.TipoEnum.DESTINATARIO_EVASIONE.getCostante());
			destinatarioEvasione.setStatoElOrdine(statoElOrdineRigaEvasione);
			destinatarioEvasioneDad.updateDestinatarioEvasione(destinatarioEvasione);

			// aggiornamento stato destinatario ordine
			Destinatario destinatarioOrdine = destinatarioEvasione.getDestinatarioOrdine();
			UtilityAnnullamentoEvasione.aggiornamentoStatoDestinatarioOrdine(decodificaDad, destinatarioEvasioneDad, destinatarioOrdineDad, destinatarioOrdine);
		}

		// aggiornare stato testata evasione
		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		String codiceStatoTestataEvasione = ConstantsCPassStato.StatoEnum.ANNULLATA.getCostante();
		Stato statoEvasione = decodificaDad.getStato(codiceStatoTestataEvasione, ConstantsCPassStato.TipoEnum.EVASIONE.getCostante()).get();
		testataEvasione.setStato(statoEvasione);
		testataEvasioneDad.updateTestataEvasione(testataEvasione);
		
		response.setTestataEvasione(testataEvasione);
	}

}
