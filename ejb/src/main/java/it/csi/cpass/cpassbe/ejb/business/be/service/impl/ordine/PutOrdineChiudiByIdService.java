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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineChiudiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineChiudiByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutOrdineChiudiByIdService extends BaseOrdineService<PutOrdineChiudiByIdRequest, PutOrdineChiudiByIdResponse> {

	private final DecodificaDad decodificaDad;

	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final ImpegnoOrdineDad impegnoOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;

	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutOrdineChiudiByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, RigaOrdineDad rigaOrdineDad,
			ImpegnoOrdineDad impegnoOrdineDad, ImpegnoDad impegnoDad, ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad,
			SystemDad systemDad, SettoreDad settoreDad, DecodificaDad decodificaDad, DestinatarioOrdineDad destinatarioOrdineDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoOrdineDad = impegnoOrdineDad;
		this.impegnoDad = impegnoDad;

		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;

		this.decodificaDad = decodificaDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Calendar calendar = Calendar.getInstance();

		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();

		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());
		for (Destinatario destinatario : testataOrdine.getListDestinatario()) {

			final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
			if (rigaOrdines != null) {
				for (final RigaOrdine rigaOrdine : rigaOrdines) {
					final String statoRigaOrdCode = rigaOrdine.getStato().getCodice();
					if (statoRigaOrdCode.equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())
							|| statoRigaOrdCode.equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {

						// 2.6.1 CPASS_T_ORD_IMPEGNO_ORDINE
						final List<ImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegnoOrdineByRiga(rigaOrdine.getId());
						if (impegnoOrdines != null) {
							for (final ImpegnoOrdine impegnoOrdine : impegnoOrdines) {
								// 2.6.2 CPASS_T_ORD_SUBIMPEGNO_ORDINE
								if("1ad9cdaa-136a-5839-b22b-bcb1255b8cd4".equals(impegnoOrdine.getId().toString())) {
									log.info("", "impegnoOrdine.getId() " + impegnoOrdine.getId());
								}
								final List<SubimpegnoOrdine> subimpegnoOrdines = impegnoDad.getSubimpegnoOrdineByImpegnoOrdineId(impegnoOrdine.getId());
								if (subimpegnoOrdines != null && subimpegnoOrdines.size() > 0) {
									for (final SubimpegnoOrdine subimpegnoOrdine : subimpegnoOrdines) {
										operazioniSuSubimpegno(ente, testataOrdine, rigaOrdine, subimpegnoOrdine);
									}
								} else {
									operazioniSuImpegno(calendar, ente, testataOrdine, rigaOrdine, impegnoOrdine);
								}
							}
						}

						// 2.6.3 CPASS_T_ORD_RIGA_ORDINE
						if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
							final Stato statoRigaOrdineChiusaDaEvadere = decodificaDad.getStato(
									ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_CHIUSA_DA_EVADERE.getCostante(),
									ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
							rigaOrdine.setStato(statoRigaOrdineChiusaDaEvadere);

						} else if (rigaOrdine.getStato().getCodice()
								.equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
							final Stato statoRigaOrdineChiusaEvasaParzialmente = decodificaDad.getStato(
									ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_CHIUSA_EVASA_PARZIALMENTE.getCostante(),
									ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
							rigaOrdine.setStato(statoRigaOrdineChiusaEvasaParzialmente);
						}
						rigaOrdineDad.updateRigaOrdine(rigaOrdine);

					}
				}
			}

			// 2.6.4 CPASS_T_ORD_DESTINATARIO_ORDINE
			if (ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante()
					.equals(destinatario.getStato().getCodice())) {
				destinatario.setStato(decodificaDad.getStato(
						ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_CHIUSO_DA_EVADERE.getCostante(),
						ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante()
						));
			} else if (ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante()
					.equals(destinatario.getStato().getCodice())) {
				destinatario.setStato(decodificaDad.getStato(
						ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_CHIUSO_EVASO_PARZIALMENTE.getCostante(),
						ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante()
						));
			}
			destinatario = destinatarioOrdineDad.updateDestinatario(destinatario);

		}

		// NON aggiornare lo stato ordine a CHIUSO
		response.setTestataOrdine(testataOrdine);
	}

	private void operazioniSuImpegno(Calendar calendar, Ente ente, TestataOrdine testataOrdine, RigaOrdine rigaOrdine, ImpegnoOrdine impegnoOrdine) {
		final BigDecimal totaleGiaEvaso = impegnoEvasioneDad.calcolaTotaleEvaso(impegnoOrdine.getId());
		/*
		if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
			impegnoOrdine.setImporto(new BigDecimal(0));

		} else if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
			// importo con il totale evaso calcolato
			impegnoOrdine.setImporto(totaleGiaEvaso);
		}
		impegnoOrdineDad.update(impegnoOrdine);
		 */
		// Nel caso in cui l’ordine da chiudere non sia dell’anno corrente
		if (testataOrdine.getAnno() < calendar.get(Calendar.YEAR)) {
			// quelli con anno impegno < anno corrente
			final Impegno impegno = impegnoOrdine.getImpegno();
			if (impegno.getAnno() < calendar.get(Calendar.YEAR)) {

				// 1. Trovare l’eventuale record su CPASS_T_IMPEGNO per l’anno esercizio = anno corrente
				final int annoEsercizio = calendar.get(Calendar.YEAR);
				List<Impegno> impegnoAnnoCorrente = impegnoDad.getImpegnoByChiaveLogica(annoEsercizio, impegno.getAnno(), impegno.getNumero(), ente.getId());
				if (impegnoAnnoCorrente.isEmpty()) {
					return;
				}

				BigDecimal lap = impegnoAnnoCorrente.get(0).getLiquidatoAnnoPrecedente();
				// 2. occorre verificare il valore del campo liq_anno_prec - Se esso è > 0
				if (lap.compareTo(new BigDecimal(0)) > 0) {
					// ordinato - evaso
					BigDecimal diffImp = impegnoOrdine.getImporto().subtract(totaleGiaEvaso);
					if (diffImp.compareTo(lap) <= 0) {
						lap = diffImp;
						impegnoAnnoCorrente.get(0).setLiquidatoAnnoPrecedente(lap);
						diffImp = new BigDecimal(0);
					} else {
						impegnoAnnoCorrente.get(0).setLiquidatoAnnoPrecedente(new BigDecimal(0));
						diffImp = diffImp.subtract(lap);
					}
					impegnoDad.update(impegnoAnnoCorrente.get(0));

					if (diffImp.compareTo(new BigDecimal(0)) > 0) {
						boolean bStop = false;

						final ArrayList<UUID> alImpegnoId = new ArrayList<>();
						alImpegnoId.add(impegno.getId());

						final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.findByIdImpegnoOrdine(impegnoOrdine.getId());
						final Iterator<ImpegnoEvasione> iterImpegnoEvasione = impegnoEvasiones.iterator();
						while (iterImpegnoEvasione.hasNext() && !bStop) {
							final ImpegnoEvasione impegnoEvasione = iterImpegnoEvasione.next();

							final Impegno impegnoRiaccertato = impegnoEvasione.getImpegno();
							if (!alImpegnoId.contains(impegnoRiaccertato.getId())) {
								alImpegnoId.add(impegnoRiaccertato.getId());

								// da punto 1)
								impegnoAnnoCorrente = impegnoDad.getImpegnoByChiaveLogica(annoEsercizio, impegnoRiaccertato.getAnno(),impegnoRiaccertato.getNumero(), ente.getId());
								if (impegnoAnnoCorrente == null) {
									continue;
								}
								lap = impegnoAnnoCorrente.get(0).getLiquidatoAnnoPrecedente();

								// 2. occorre verificare il valore del campo liq_anno_prec - Se esso è > 0
								if (lap.compareTo(new BigDecimal(0)) > 0) {
									// ordinato - evaso
									if (diffImp.compareTo(lap) <= 0) {
										lap = diffImp;
										impegnoAnnoCorrente.get(0).setLiquidatoAnnoPrecedente(lap);
										diffImp = new BigDecimal(0);
									} else {
										impegnoAnnoCorrente.get(0).setLiquidatoAnnoPrecedente(new BigDecimal(0));
										diffImp = diffImp.subtract(lap);
									}
									impegnoDad.update(impegnoAnnoCorrente.get(0));
								}
							}

							if (diffImp.compareTo(new BigDecimal(0)) <= 0) {
								bStop = Boolean.TRUE;
							}

						}
					}
				}

			}
		}
		// aggiornamento dell'importo impegno
		if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
			impegnoOrdine.setImporto(new BigDecimal(0));

		} else if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
			// importo con il totale evaso calcolato
			impegnoOrdine.setImporto(totaleGiaEvaso);
		}
		impegnoOrdineDad.update(impegnoOrdine);

	}

	private void operazioniSuSubimpegno(Ente ente, TestataOrdine testataOrdine, RigaOrdine rigaOrdine, SubimpegnoOrdine subimpegnoOrdine) {
		final BigDecimal totaleGiaEvaso = subimpegnoEvasioneDad.calcolaTotaleEvaso(subimpegnoOrdine.getId());

		if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
			subimpegnoOrdine.setSubimpegnoImporto(new BigDecimal(0));

		} else if (rigaOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
			// importo con il totale evaso calcolato
			subimpegnoOrdine.setSubimpegnoImporto(totaleGiaEvaso);

		}
		impegnoOrdineDad.update(subimpegnoOrdine);

		// Nel caso in cui l’ordine da chiudere non sia dell’anno corrente
		final Calendar calendar = Calendar.getInstance();
		if (testataOrdine.getAnno() < calendar.get(Calendar.YEAR)) {
			// quelli con anno impegno < anno corrente
			final Subimpegno subimpegno = subimpegnoOrdine.getSubimpegno();
			if (subimpegno.getAnno() < calendar.get(Calendar.YEAR)) {

				// 1. Trovare l’eventuale record su CPASS_T_IMPEGNO per l’anno esercizio = anno corrente
				final int annoEsercizio = calendar.get(Calendar.YEAR);
				Subimpegno subimpegnoAnnoCorrente = impegnoDad.getSubimpegnoByChiaveLogica(annoEsercizio, subimpegno.getImpegno().getAnno(),
						subimpegno.getImpegno().getNumero(), ente.getId(), subimpegno.getAnno(), subimpegno.getNumero());
				if (subimpegnoAnnoCorrente == null) {
					return;
				}

				BigDecimal lap = subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente();
				// 2. occorre verificare il valore del campo liq_anno_prec - Se esso è > 0
				if (lap.compareTo(new BigDecimal(0)) > 0) {
					// ordinato - evaso
					BigDecimal diffImp = subimpegnoOrdine.getSubimpegnoImporto().subtract(totaleGiaEvaso);
					if (diffImp.compareTo(lap) <= 0) {
						lap = diffImp;
						diffImp = new BigDecimal(0);
						subimpegnoAnnoCorrente.setLiquidatoAnnoPrecedente(diffImp);
					} else {
						subimpegnoAnnoCorrente.setLiquidatoAnnoPrecedente(new BigDecimal(0));
						diffImp = diffImp.subtract(lap);
					}
					impegnoDad.update(subimpegnoAnnoCorrente);

					if (diffImp.compareTo(new BigDecimal(0)) > 0) {
						boolean bStop = false;

						final ArrayList<UUID> alSubimpegnoId = new ArrayList<>();
						alSubimpegnoId.add(subimpegno.getId());

						final List<SubimpegnoEvasione> subimpegnoEvasiones = subimpegnoEvasioneDad.findByIdSubimpegnoOrdine(subimpegnoOrdine.getId());
						final Iterator<SubimpegnoEvasione> iterSubimpegnoEvasione = subimpegnoEvasiones.iterator();
						while (iterSubimpegnoEvasione.hasNext() && !bStop) {
							final SubimpegnoEvasione subimpegnoEvasione = iterSubimpegnoEvasione.next();

							final Subimpegno subimpegnoRiaccertato = subimpegnoEvasione.getSubimpegno();
							if (!alSubimpegnoId.contains(subimpegnoRiaccertato.getId())) {
								alSubimpegnoId.add(subimpegnoRiaccertato.getId());

								// da punto 1)
								subimpegnoAnnoCorrente = impegnoDad.getSubimpegnoByChiaveLogica(annoEsercizio, subimpegnoRiaccertato.getImpegno().getAnno(),
										subimpegnoRiaccertato.getImpegno().getNumero(), ente.getId(), subimpegnoRiaccertato.getAnno(),
										subimpegnoRiaccertato.getNumero());
								if (subimpegnoAnnoCorrente == null) {
									continue;
								}
								lap = subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente();

								// 2. occorre verificare il valore del campo liq_anno_prec - Se esso è > 0
								if (lap.compareTo(new BigDecimal(0)) > 0) {
									// ordinato - evaso
									if (diffImp.compareTo(lap) <= 0) {
										lap = diffImp;
										diffImp = new BigDecimal(0);
										subimpegnoAnnoCorrente.setLiquidatoAnnoPrecedente(diffImp);
									} else {
										subimpegnoAnnoCorrente.setLiquidatoAnnoPrecedente(new BigDecimal(0));
										diffImp = diffImp.subtract(lap);
									}
									impegnoDad.update(subimpegnoAnnoCorrente);
								}
							}

							if (diffImp.compareTo(new BigDecimal(0)) <= 0) {
								bStop = Boolean.TRUE;
							}
						}
					}
				}

			}
		}
	}

}
