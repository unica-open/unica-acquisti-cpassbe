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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostImpegniEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostImpegniEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;

public class PostImpegniEvasioneService extends BaseImpegniEvasioneService<PostImpegniEvasioneRequest, PostImpegniEvasioneResponse> {

	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	private final ImpegnoDad impegnoDad;
	private final FornitoreDad fornitoreDad;

	private SalvaImpegniEvasione salvaImpegniEvasione;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param subimpegnoEvasioneDad
	 * @param destinatarioOrdineDad
	 * @param rigaOrdineDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PostImpegniEvasioneService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoEvasioneDad impegnoEvasioneDad,
			SubimpegnoEvasioneDad subimpegnoEvasioneDad, ImpegnoDad impegnoDad, FornitoreDad fornitoreDad) {
		super(configurationHelper, externalHelperLookup);

		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
		this.impegnoDad = impegnoDad;
		this.fornitoreDad = fornitoreDad;
	}

	@Override
	protected void checkServiceParams() {
		salvaImpegniEvasione = request.getSalvaImpegniEvasione();
		checkNotNull(salvaImpegniEvasione, "salvaImpegniEvasione", true);
	}

	@Override
	protected void execute() {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		for (ImpegnoEvasione impegnoEvasione : salvaImpegniEvasione.getImpegnoEvasiones()) {
			// filtro inserito lato FE
			// if (impegnoEvasione.getImportoRipartito() != null && impegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {

			// Impegni: chiamata SIAC per verifiche
			Impegno impegnoFiltro = new Impegno();
			impegnoFiltro.setAnno(impegnoEvasione.getImpegnoAnno());
			impegnoFiltro.setNumero(impegnoEvasione.getImpegnoNumero());

			Subimpegno subimpegnoFiltro = new Subimpegno();
			subimpegnoFiltro.setImpegno(impegnoFiltro);

			if (impegnoEvasione.getSubimpegnoEvasiones() != null && impegnoEvasione.getSubimpegnoEvasiones().size() > 0) {
				for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
					subimpegnoFiltro.setAnno(subimpegnoEvasione.getSubimpegnoAnno());
					subimpegnoFiltro.setNumero(subimpegnoEvasione.getSubimpegnoNumero());

					controlli(salvaImpegniEvasione, apiErrors, impegnoEvasione, subimpegnoEvasione, subimpegnoFiltro);
				}

			} else {
				controlli(salvaImpegniEvasione, apiErrors, impegnoEvasione, null, subimpegnoFiltro);
			}

			// }
		}

		response.setApiErrors(apiErrors);
		if (apiErrors.size() == 0) {
			Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			Ente ente = settoreCorrente.getEnte();

			Calendar calendar = Calendar.getInstance();
			int annoCorrente = calendar.get(Calendar.YEAR);

			for (ImpegnoEvasione impegnoEvasione : salvaImpegniEvasione.getImpegnoEvasiones()) {
				// filtro inserito lato FE
				// if (impegnoEvasione.getImportoRipartito() != null && impegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {
				Impegno impegno = impegnoDad.getImpegnoByChiaveLogica(annoCorrente, impegnoEvasione.getImpegnoAnno(), impegnoEvasione.getImpegnoNumero(),
						ente.getId());
				if (impegno == null) {
					impegno = impegnoEvasione.getImpegno();
					impegno.setEnte(ente);

					Fornitore fornitore = fornitoreDad.getFornitoreByCodice(impegno.getFornitore().getCodice());
					if (fornitore == null) {
						// inserisco fornitore con solo il codice
						fornitore = fornitoreDad.insert(impegno.getFornitore());
						fornitoreDad.flush();
					}
					impegno.setFornitore(fornitore);

					impegno = impegnoDad.saveImpegno(impegno);
					impegnoDad.flush();
				}
				
				impegnoEvasione.setImpegno(impegno);
				impegnoEvasione.setImpegnoAnnoEsercizio(annoCorrente);
				ImpegnoEvasione impegnoEvasioneSalvato = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
				impegnoEvasioneDad.flush();
				// }

				for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
					if (subimpegnoEvasione.getImportoRipartito() != null && subimpegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {
						Subimpegno subimpegno = impegnoDad.getSubimpegnoByChiaveLogica(annoCorrente, subimpegnoEvasione.getImpegnoAnno(),
								subimpegnoEvasione.getImpegnoNumero(), ente.getId(), subimpegnoEvasione.getSubimpegnoAnno(),
								subimpegnoEvasione.getSubimpegnoNumero());
						if (subimpegno == null) {
							subimpegno = subimpegnoEvasione.getSubimpegno();
							subimpegno.setEnte(ente);
							
							Fornitore fornitore = fornitoreDad.getFornitoreByCodice(subimpegno.getFornitore().getCodice());
							if (fornitore == null) {
								fornitore = fornitoreDad.insert(fornitore);
							}
							subimpegno.setFornitore(fornitore);

							subimpegno = impegnoDad.saveSubimpegno(subimpegno);
							impegnoDad.flush();
						}
						
						subimpegnoEvasione.setSubimpegno(subimpegno);
						subimpegnoEvasione.setImpegnoAnnoEsercizio(annoCorrente);
						subimpegnoEvasione.setImpegnoEvasione(impegnoEvasioneSalvato);
						subimpegnoEvasioneDad.insert(subimpegnoEvasione);
					}

				}

			}

			response.setImpegnoEvasiones(salvaImpegniEvasione.getImpegnoEvasiones());
			
		} else {
			separaMessaggiErrorePerTipo(apiErrors);
		}
	}

}
