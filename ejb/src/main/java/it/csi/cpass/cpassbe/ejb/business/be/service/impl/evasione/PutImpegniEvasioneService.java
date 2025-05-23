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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutImpegniEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutImpegniEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
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
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutImpegniEvasioneService extends BaseImpegniEvasioneService<PutImpegniEvasioneRequest, PutImpegniEvasioneResponse> {

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
	public PutImpegniEvasioneService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoEvasioneDad impegnoEvasioneDad,
			SubimpegnoEvasioneDad subimpegnoEvasioneDad, ImpegnoDad impegnoDad, FornitoreDad fornitoreDad,SettoreDad settoreDad) {
		super(configurationHelper, externalHelperLookup, settoreDad);

		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
		this.impegnoDad = impegnoDad;
		this.fornitoreDad = fornitoreDad;
	}

	@Override
	protected void checkServiceParams() {
		salvaImpegniEvasione = request.getSalvaImpegniEvasione();
		checkNotNull(salvaImpegniEvasione, "salvaImpegniEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final List<ApiError> apiErrors = new ArrayList<>();
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		for (final ImpegnoEvasione impegnoEvasione : salvaImpegniEvasione.getImpegnoEvasiones()) {
			// filtro inserito lato FE
			// if (impegnoEvasione.getImportoRipartito() != null && impegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {
			// Impegni: chiamata SIAC per verifiche
			final Impegno impegnoFiltro = new Impegno();
			impegnoFiltro.setAnno(impegnoEvasione.getImpegnoAnno());
			impegnoFiltro.setNumero(impegnoEvasione.getImpegnoNumero());

			final Subimpegno subimpegnoFiltro = new Subimpegno();
			subimpegnoFiltro.setImpegno(impegnoFiltro);

			if (impegnoEvasione.getSubimpegnoEvasiones() != null && impegnoEvasione.getSubimpegnoEvasiones().size() > 0) {
				for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
					subimpegnoFiltro.setAnno(subimpegnoEvasione.getSubimpegnoAnno());
					subimpegnoFiltro.setNumero(subimpegnoEvasione.getSubimpegnoNumero());
					controlli(salvaImpegniEvasione, apiErrors, impegnoEvasione, subimpegnoEvasione, subimpegnoFiltro,enteId);
				}

			} else {
				controlli(salvaImpegniEvasione, apiErrors, impegnoEvasione, null, subimpegnoFiltro,enteId);
			}
		}

		separaMessaggiErrorePerTipo(apiErrors);

		if (apiErrors.size() == 0) {
			// cancello vecchie relazioni
			impegnoEvasioneDad.deleteImpegniEvasioneByRiga(salvaImpegniEvasione.getRigaEvasione().getId());

			final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			final Ente ente = settoreCorrente.getEnte();

			final Calendar calendar = Calendar.getInstance();
			final int annoCorrente = calendar.get(Calendar.YEAR);

			for (final ImpegnoEvasione impegnoEvasione : salvaImpegniEvasione.getImpegnoEvasiones()) {

				// filtro inserito lato FE
				// if (impegnoEvasione.getImportoRipartito() != null && impegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {
				final List<Impegno> impegni = impegnoDad.getImpegnoByChiaveLogica(annoCorrente, impegnoEvasione.getImpegnoAnno(), impegnoEvasione.getImpegnoNumero(),ente.getId());

				Impegno impegno = new Impegno();
				if (impegni.isEmpty()) {
					impegno  = impegnoEvasione.getImpegno(); // preso da SIAC
					impegno.setEnte(ente);

					Fornitore fornitore = fornitoreDad.getFornitoreByCodice(impegno.getFornitore().getCodice());
					if (fornitore == null) {
						// inserisco fornitore con solo il codice
						fornitore = fornitoreDad.insert(impegno.getFornitore());
					}
					impegno.setFornitore(fornitore);

					impegno = impegnoDad.saveImpegno(impegno);
				}else {
					impegno = impegni.get(0);
				}

				impegnoEvasione.setImpegno(impegno);
				impegnoEvasione.setImpegnoAnnoEsercizio(annoCorrente);
				final ImpegnoEvasione impegnoEvasioneSalvato = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
				// }

				for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
					if (subimpegnoEvasione.getImportoRipartito() != null && subimpegnoEvasione.getImportoRipartito().compareTo(new BigDecimal(0)) != 0) {
						Subimpegno subimpegno = impegnoDad.getSubimpegnoByChiaveLogica(annoCorrente, subimpegnoEvasione.getImpegnoAnno(),
								subimpegnoEvasione.getImpegnoNumero(), ente.getId(), subimpegnoEvasione.getSubimpegnoAnno(),
								subimpegnoEvasione.getSubimpegnoNumero());
						if (subimpegno == null) {
							subimpegno = subimpegnoEvasione.getSubimpegno(); // preso da SIAC
							subimpegno.setEnte(ente);
							Fornitore fornitore = fornitoreDad.getFornitoreByCodice(subimpegno.getFornitore().getCodice());
							if (fornitore == null) {
								fornitore = fornitoreDad.insert(fornitore);
							}
							subimpegno.setFornitore(fornitore);

							subimpegno = impegnoDad.saveSubimpegno(subimpegno);
						}
						subimpegnoEvasione.setSubimpegno(subimpegno);
						subimpegnoEvasione.setImpegnoAnnoEsercizio(annoCorrente);
						subimpegnoEvasione.setImpegnoEvasione(impegnoEvasioneSalvato);
						subimpegnoEvasioneDad.insert(subimpegnoEvasione);
					}

				}

			}
			response.setImpegnoEvasiones(salvaImpegniEvasione.getImpegnoEvasiones());
		}
	}

}
