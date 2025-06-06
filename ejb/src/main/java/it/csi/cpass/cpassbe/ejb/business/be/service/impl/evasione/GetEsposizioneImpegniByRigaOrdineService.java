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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetEsposizioneImpegniByRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetEsposizioneImpegniByRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetEsposizioneImpegniByRigaOrdineService extends BaseService<GetEsposizioneImpegniByRigaOrdineRequest, GetEsposizioneImpegniByRigaOrdineResponse> {

	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoDad impegnoDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param rigaOrdineDad       rigaOrdineDad
	 */
	public GetEsposizioneImpegniByRigaOrdineService(ConfigurationHelper configurationHelper, RigaEvasioneDad rigaEvasioneDad, ImpegnoDad impegnoDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			SubimpegnoEvasioneDad subimpegnoEvasioneDad) {
		super(configurationHelper);
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoDad = impegnoDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdRigaEvasione(), "idRigaEvasione");
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = settoreCorrente.getEnte().getId();

		final RigaEvasione rigaEvasione = rigaEvasioneDad.findOne(request.getIdRigaEvasione());
		BigDecimal totRiga = rigaEvasione.getImportoTotale();
		if (request.isDistribuzioneTotaleRigaSugliImpegni() != null && !request.isDistribuzioneTotaleRigaSugliImpegni().booleanValue()) {
			totRiga = new BigDecimal(0);
		}

		final List<ImpegnoEvasione> listImpegnoEvasione = new ArrayList<>();

		final List<ImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegnoOrdineByRigaNonPresentiEvasione(rigaEvasione.getRigaOrdine().getId(), rigaEvasione.getId());
		if (impegnoOrdines != null && impegnoOrdines.size() > 0) {
			for (final ImpegnoOrdine impegnoOrdine : impegnoOrdines) {

				// 2.9 Verifica impegni/subimpegni ribaltati
				final Impegno impegno = UtilityImpegni.verificaImpegniRibaltati(impegnoDad, enteId, impegnoOrdine);

				final ImpegnoEvasione impegnoEvasione = new ImpegnoEvasione();
				// impegnoEvasione.setRigaEvasione(rigaEvasione);
				impegnoEvasione.setImpegno(impegno);
				impegnoEvasione.setImpegnoOrdine(impegnoOrdine);

				final Calendar calendar = Calendar.getInstance();
				final int yearCurrent = calendar.get(Calendar.YEAR);
				if (impegnoOrdine.getImpegnoAnnoEsercizio() < yearCurrent) {
					impegnoEvasione.setImpegnoAnnoEsercizio(impegnoOrdine.getImpegno().getAnnoEsercizio());
				} else {
					impegnoEvasione.setImpegnoAnnoEsercizio(impegnoOrdine.getImpegnoAnnoEsercizio());
				}
				impegnoEvasione.setImpegnoAnno(impegnoOrdine.getImpegnoAnno());
				impegnoEvasione.setImpegnoNumero(impegnoOrdine.getImpegnoNumero());

				impegnoEvasione.setImportoRipartito(new BigDecimal(0));
				impegnoEvasione.setImportoLiquidato(new BigDecimal(0));
				impegnoEvasione.setImportoSospeso(new BigDecimal(0));

				final List<SubimpegnoOrdine> subimpegnoOrdines = impegnoDad.getSubimpegnoOrdineByImpegnoOrdineId(impegnoOrdine.getId());
				if (subimpegnoOrdines != null && subimpegnoOrdines.size() > 0) {
					for (final SubimpegnoOrdine subimpegnoOrdine : subimpegnoOrdines) {

						// 2.9 Verifica impegni/subimpegni ribaltati
						final Subimpegno subimpegno = UtilityImpegni.verificaSubimpegniRibaltati(impegnoDad, enteId, subimpegnoOrdine);

						final SubimpegnoEvasione subimpegnoEvasione = new SubimpegnoEvasione();
						subimpegnoEvasione.setSubimpegno(subimpegno);
						subimpegnoEvasione.setSubimpegnoOrdine(subimpegnoOrdine);

						// subimpegnoEvasione.setImpegnoEvasione(impegnoEvasione); // attenzione referenza circolare
						subimpegnoEvasione.setImpegnoAnnoEsercizio(impegnoEvasione.getImpegnoAnnoEsercizio());
						subimpegnoEvasione.setImpegnoAnno(impegnoEvasione.getImpegnoAnno());
						subimpegnoEvasione.setImpegnoNumero(impegnoEvasione.getImpegnoNumero());

						subimpegnoEvasione.setSubimpegnoAnno(subimpegnoOrdine.getSubimpegnoAnno());
						subimpegnoEvasione.setSubimpegnoNumero(subimpegnoOrdine.getSubimpegnoNumero());

						subimpegnoEvasione.setImportoRipartito(new BigDecimal(0));
						subimpegnoEvasione.setImportoSospeso(new BigDecimal(0));
						subimpegnoEvasione.setImportoLiquidato(new BigDecimal(0));

						final BigDecimal totaleOrdinato = subimpegnoOrdine.getSubimpegnoImporto();
						final BigDecimal totaleGiaEvaso = subimpegnoEvasioneDad.calcolaTotaleEvaso(subimpegnoOrdine.getId());
						final BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);

						if (totaleRipartibile.compareTo(new BigDecimal(0)) > 0) {
							subimpegnoEvasione.setTotaleRipartibile(totaleRipartibile);

							// 2.8.4	Distribuzione del totale riga sugli impegni
							if (totaleRipartibile.compareTo(totRiga) < 0) {
								subimpegnoEvasione.setImportoRipartito(totaleRipartibile);
								totRiga = totRiga.subtract(totaleRipartibile);
							} else {
								subimpegnoEvasione.setImportoRipartito(totRiga);
								totRiga = new BigDecimal(0);
							}

							impegnoEvasione.getSubimpegnoEvasiones().add(subimpegnoEvasione);
						}
					}

					if (impegnoEvasione.getSubimpegnoEvasiones().size() > 0) {
						listImpegnoEvasione.add(impegnoEvasione);
					}

				} else {
					// 2.8.3 Calcolo del totale ripartibile su un impegno/subimpegno
					// Totale ordinato – totale già evaso
					final BigDecimal totaleOrdinato = impegnoOrdine.getImporto();
					final BigDecimal totaleGiaEvaso = impegnoEvasioneDad.calcolaTotaleEvaso(impegnoOrdine.getId());
					final BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);
					if (totaleRipartibile.compareTo(new BigDecimal(0)) > 0) {
						impegnoEvasione.setTotaleRipartibile(totaleRipartibile);

						// 2.8.4	Distribuzione del totale riga sugli impegni
						if (totaleRipartibile.compareTo(totRiga) < 0) {
							impegnoEvasione.setImportoRipartito(totaleRipartibile);
							totRiga = totRiga.subtract(totaleRipartibile);
						} else {
							impegnoEvasione.setImportoRipartito(totRiga);
							totRiga = new BigDecimal(0);
						}

						listImpegnoEvasione.add(impegnoEvasione);
					}
				}

			}
		}

		// impegni ordinati dal più vecchio al più nuovo
		Collections.sort(listImpegnoEvasione, new Comparator<ImpegnoEvasione>() {
			@Override
			public int compare(ImpegnoEvasione o1, ImpegnoEvasione o2) {
				final int c = o1.getImpegnoAnno().compareTo(o2.getImpegnoAnno());
				if (c != 0) {
					return c;
				}
				return o1.getImpegnoNumero().compareTo(o2.getImpegnoNumero());
			}
		});

		response.setListImpegnoEvasione(listImpegnoEvasione);
	}

}
