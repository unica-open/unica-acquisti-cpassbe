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
package it.csi.cpass.cpassbe.ejb.business.be.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.UtilityArrotondamento;
import it.csi.cpass.cpassbe.ejb.util.UtilityParametri;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class UtilityVerificheOrdine {

	public static BigDecimal getDeltaIvasRiga(RigaOrdine riga) {
		BigDecimal importoIva = riga.getImportoIva();
		BigDecimal ivaCalc = riga.getAliquoteIva().getPercentuale().multiply(riga.getImportoNetto()).divide(BigDecimal.valueOf(100));

		return importoIva.subtract(ivaCalc).abs();
	}

	public static List<ApiError> checkCompletezza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		if (!checkInnerCompletezza(testataOrdine, rigaOrdineDad, impegnoDad)) {
			apiErrors.add(MsgCpassOrd.ORDORDE0047.getError());
		}
		return apiErrors;
	}

	private static boolean checkInnerCompletezza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		// • L’ordine deve avere almeno un destinatario collegato
		if (testataOrdine.getListDestinatario() == null || testataOrdine.getListDestinatario().size() == 0) {
			return false;
		}

		// • Ogni destinatario trovato deve avere almeno una riga
		for (Destinatario destinatario : testataOrdine.getListDestinatario()) {
			List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
			if (rigaOrdines == null || rigaOrdines.size() == 0) {
				return false;
			}

			// • Ogni riga trovata deve avere almeno un impegno collegato
			for (RigaOrdine rigaOrdine : rigaOrdines) {
				List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
				if (impegnos == null || impegnos.size() == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public static List<ApiError> checkValidita(TestataOrdine testataOrdine, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad,
			RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		Ente ente = testataOrdine.getSettore().getEnte();

		if (ente == null) {
			Settore settore = settoreDad.findOne(testataOrdine.getSettore().getId());
			ente = settore.getEnte();
		}

		// Fornitore corrisponda ad un fornitore valido
		FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setFornitore(testataOrdine.getFornitore());
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		ExternalServiceResolveWrapper<FornitoreHelper> handlerFornitore = externalHelperLookup.lookup(FornitoreHelper.class);
		List<Fornitore> fornitori = invokeExternalService(handlerFornitore,
				() -> handlerFornitore.getInstance().getFornitori(handlerFornitore.getParams(), filtroFornitore));
		if (fornitori == null || fornitori.size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0048.getError());
		}

		// • il provvedimento esista TODO
		if (false) {
			apiErrors.add(MsgCpassOrd.ORDORDE0049.getError());
		}

		// • il settore_emittente sia valido
		Settore settore = settoreDad.findById(testataOrdine.getSettore().getId());
		if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
			apiErrors.add(MsgCpassOrd.ORDORDE0050.getError());
		}

		// Per ogni destinatario presente sull’ordine occorre verificare che:
		// • il settore_destinatario_id sia valido
		if (testataOrdine.getListDestinatario() != null) {
			List<Impegno> impegnosErrore = new ArrayList<Impegno>();
			List<Subimpegno> subimpegnosErrore = new ArrayList<Subimpegno>();

			// Impegni: chiamata SIAC per verifiche
			Impegno impegnoFiltro = new Impegno();
			impegnoFiltro.setAnnoProvvedimento(testataOrdine.getProvvedimento().getAnno());
			impegnoFiltro.setNumeroProvvedimento(testataOrdine.getProvvedimento().getNumero());

			Subimpegno subimpegnoFiltro = new Subimpegno();
			subimpegnoFiltro.setImpegno(impegnoFiltro);

			FiltroImpegni filtroImpegni = new FiltroImpegni();
			filtroImpegni.setSubimpegno(subimpegnoFiltro);
			// filtroImpegni.setStatoImpegno(SiacConstants.IMPEGNO_STATO_DEFINITIVO);

			ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class);
			PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,
					() -> handlerImpegno.getInstance().getImpegni(handlerImpegno.getParams(), filtroImpegni, 1, 0));

			List<Impegno> listImpegnoSIAC = new ArrayList<Impegno>(); // lista vuota per far funzionare il controllo su "impegno found"
			if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
				listImpegnoSIAC = pagedListImpegni.getList();
			}

			// ciclo i destinatari
			for (Destinatario destinatario : testataOrdine.getListDestinatario()) {
				settore = settoreDad.findById(destinatario.getSettore().getId());
				if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
					if (!apiErrors.contains(MsgCpassOrd.ORDORDE0051.getError())) {
						apiErrors.add(MsgCpassOrd.ORDORDE0051.getError());
					}
				}

				// l'id dell'indirizzo non
				// • l’indirizzo_id sia valido
				// (verificare che la data_cancellazione su CPASS_T_SETTORE_INDIRIZZO non sia valorizzata o al più sia maggiore della data odierna)
				// apiErrors.add(MsgCpassOrd.ORDORDE0052.getError());

				// Per ogni riga presente sull’ordine occorre verificare che:
				List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {

					for (RigaOrdine rigaOrdine : rigaOrdines) {
						// • L’oggetto di spesa utilizzato sia valido (CPASS_D_OGGETTO_SPESA.data_cancellazione non valorizzata)
						if (rigaOrdine.getOds().getDataCancellazione() != null) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0053.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0053.getError());
							}
						}

						// • L’aliquota iva sia valida (CPASS_D_ALIQUOTE_IVA.data_validita_inizio <= data corrente <= CPASS_D_ALIQUOTE_IVA.data_validita_inizio)
						if (rigaOrdine.getAliquoteIva().getDataValiditaInizio() != null
								&& rigaOrdine.getAliquoteIva().getDataValiditaInizio().after(new Date())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0054.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0054.getError());
							}
						}
						if (rigaOrdine.getAliquoteIva().getDataValiditaFine() != null && rigaOrdine.getAliquoteIva().getDataValiditaFine().before(new Date())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0054.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0054.getError());
							}
						}

						// • L’unità di misura sia valida (CPASS_D_UNITA_MISURA.data_cancellazione non valorizzata)
						if (rigaOrdine.getUnitaMisura().getDataValiditaInizio() != null
								&& rigaOrdine.getUnitaMisura().getDataValiditaInizio().after(new Date())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0055.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0055.getError());
							}
						}
						if (rigaOrdine.getUnitaMisura().getDataValiditaFine() != null && rigaOrdine.getUnitaMisura().getDataValiditaFine().before(new Date())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0055.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0055.getError());
							}
						}

						// Per ogni distinto impegno presente su ogni riga dell’ordine occorre verificare che
						List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
						if (impegnos != null) {
							for (Impegno impegno : impegnos) {

								boolean bImpegnoFoundSIAC = false;
								for (Impegno impegnoSIAC : listImpegnoSIAC) {

									if (impegno.getAnnoEsercizio().equals(impegnoSIAC.getAnnoEsercizio()) && impegno.getAnno().equals(impegnoSIAC.getAnno())
											&& impegno.getNumero().equals(impegnoSIAC.getNumero())) {
										bImpegnoFoundSIAC = true;

										// • Il disponibile ad ordinare non sia negativo
										if (impegno.getSubimpegni() == null || impegno.getSubimpegni().size() == 0) {

											// L’impegno deve essere definitivo - scartare tutti gli impegni senza subimpegni non definitivi (Impegni.stato !=
											// ‘D’)
											if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
												if (!apiErrors.contains(MsgCpassOrd.ORDORDE0021.getError())) {
													apiErrors.add(MsgCpassOrd.ORDORDE0021.getError());
												}
											} else {
												BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC, ente.getId(),	null); // testataOrdine.getId());
												if (disponibile.floatValue() < 0) {
													if (!impegnosErrore.contains(impegno)) {
														impegnosErrore.add(impegno);
													}
												}
											}

										} else {
											for (Subimpegno subimpegno : impegno.getSubimpegni()) {
												boolean bSubImpegnoFoundSIAC = false;

												for (Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
													if (subimpegno.getNumero().equals(subimpegnoSIAC.getNumero())) {
														bSubImpegnoFoundSIAC = true;

														// L’impegno deve essere definitivo - scartare tutti i subimpegni non definitivi
														// (subimpegni.stato.codice != ‘D’)
														if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
															if (!apiErrors.contains(MsgCpassOrd.ORDORDE0021.getError())) {
																apiErrors.add(MsgCpassOrd.ORDORDE0021.getError());
															}
														} else {
															BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC,
																	subimpegnoSIAC, ente.getId());
															if (disponibile.floatValue() < 0) {
																if (!subimpegnosErrore.contains(subimpegno)) {
																	subimpegnosErrore.add(subimpegno);
																}
															}
														}
													}
												}

												// subimpegno non trovato (stato cambiato?)
												if (!bSubImpegnoFoundSIAC) {
													if (!subimpegnosErrore.contains(subimpegno)) {
														subimpegnosErrore.add(subimpegno);
													}
												}
											}

										}
									}
								}

								// impegno non trovato (stato cambiato?)
								if (!bImpegnoFoundSIAC) {
									if (!impegnosErrore.contains(impegno)) {
										impegnosErrore.add(impegno);
									}
								}

								// • Se l’impegno è un impegno futuro
								if (impegno.getAnno() > testataOrdine.getAnno()) {
									// occorre verificare che siano state indicate le date di consegna in testata
									if (testataOrdine.getConsegnaDataDa() == null || testataOrdine.getConsegnaDataA() == null) {
										if (!apiErrors.contains(MsgCpassOrd.ORDORDE0057.getError())) {
											apiErrors.add(MsgCpassOrd.ORDORDE0057.getError());
										}
									}
								}
							}
						}
					}
				}
			}

			String strImpegniErrore = "";
			for (Impegno impegno : impegnosErrore) {
				strImpegniErrore += "(impegno " + impegno.getAnno() + " - " + impegno.getNumero() + ") ";
			}
			for (Subimpegno subimpegno : subimpegnosErrore) {
				strImpegniErrore += "(subimpegno " + subimpegno.getImpegno().getAnno() + " - " + subimpegno.getImpegno().getNumero() + "-"
						+ subimpegno.getNumero() + ") ";
			}
			if (strImpegniErrore != null && strImpegniErrore.length() > 1) {
				apiErrors.add(MsgCpassOrd.ORDORDE0056.getError("impegni", strImpegniErrore));
			}
		}

		return apiErrors;
	}

	public static List<ApiError> checkCongruenza(TestataOrdine testataOrdine) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		// • Verificare che sia stato indicato o l’indirizzo unico di consegna in testata
		if (isEmpty(testataOrdine.getConsegnaIndirizzo()) || isEmpty(testataOrdine.getConsegnaCap()) || isEmpty(testataOrdine.getConsegnaLocalita())) {
			// o in alternativa che siano stati indicati gli indirizzi su tutti i destinatari
			for (Destinatario destinatario : testataOrdine.getListDestinatario()) {
				if (isEmpty(destinatario.getIndirizzo()) || isEmpty(destinatario.getNumCivico()) || isEmpty(destinatario.getLocalita())
						|| isEmpty(destinatario.getProvincia()) || isEmpty(destinatario.getCap())) {
					if (!apiErrors.contains((MsgCpassOrd.ORDORDE0058.getError()))) {
						apiErrors.add(MsgCpassOrd.ORDORDE0058.getError());
					}
				}
			}
		}

		return apiErrors;
	}

	public static List<ApiError> checkConsistenza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		if (testataOrdine.getListDestinatario() != null) {
			for (Destinatario destinatario : testataOrdine.getListDestinatario()) {

				List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (RigaOrdine rigaOrdine : rigaOrdines) {
						BigDecimal totaleImpegni = new BigDecimal(0);

						List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
						if (impegnos != null && impegnos.size() > 0) {
							for (Impegno impegno : impegnos) {
								if (impegno.getSubimpegni() == null || impegno.getSubimpegni().size() == 0) {
									totaleImpegni = totaleImpegni.add(impegno.getImporto());
								} else {

									for (Subimpegno subimpegno : impegno.getSubimpegni()) {
										totaleImpegni = totaleImpegni.add(subimpegno.getImporto());
									}
								}
							}

							double importoTotaleImpegni = UtilityArrotondamento.arrotondaDueDecimali(totaleImpegni.doubleValue());
							double importoTotaleRiga = UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoTotale().doubleValue());
							if (importoTotaleImpegni != importoTotaleRiga) {
								if (!apiErrors.contains((MsgCpassOrd.ORDORDE0059.getError()))) {
									apiErrors.add(MsgCpassOrd.ORDORDE0059.getError());
								}
							}
						}
					}
				}
			}
		}

		return apiErrors;
	}

	public static List<ApiError> checkTotaliRiga(TestataOrdine testataOrdine, SystemDad systemDad, RigaOrdineDad rigaOrdineDad, UUID enteId) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		Map<String, String> params = UtilityParametri.readParams(systemDad, ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(), enteId);
		String strOrdTolleranzaIva = UtilityParametri.getParameter(params, ConstantsCPassParametro.ChiaveEnum.ORD_TOLLERANZA_IVA.getCostante());
		if (strOrdTolleranzaIva != null && !strOrdTolleranzaIva.trim().equals("")) {
			double ordTolleranzaIva = Double.parseDouble(strOrdTolleranzaIva);

			// Dati gli importi NETTO e IVA e TOTALE e data ALIQUOTA IVA, occorre verificare che:
			// (NETTO * ALIQUOTA IVA/100) = IVA
			if (testataOrdine.getListDestinatario() != null) {
				for (Destinatario destinatario : testataOrdine.getListDestinatario()) {

					List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
					if (rigaOrdines != null) {
						for (RigaOrdine rigaOrdine : rigaOrdines) {

							double netto = UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoNetto().doubleValue());
							double perc = rigaOrdine.getAliquoteIva().getPercentuale().doubleValue();
							double importoIvaCalcolato = netto * perc / 100;
							double importoIva = UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoIva().doubleValue());
							double diff = importoIva - importoIvaCalcolato;
							if (Math.abs(diff) > ordTolleranzaIva) {
								if (!apiErrors.contains((MsgCpassOrd.ORDORDE0060.getError()))) {
									apiErrors.add(MsgCpassOrd.ORDORDE0060.getError());
								}
							} else if (Math.abs(diff) > 0) {
								if (!apiErrors.contains((MsgCpassOrd.ORDORDI0061.getError()))) {
									apiErrors.add(MsgCpassOrd.ORDORDI0061.getError());
								}
							}
						}
					}
				}
			}
		}

		return apiErrors;
	}

	public static boolean rigaPerCopiaIsValid(RigaOrdine rigaOrdine, DecodificaDad decodificaDad) {
		Date now = new Date();

		OggettiSpesa ods = decodificaDad.getOdsById(rigaOrdine.getOds().getId());
		if (ods.getDataCancellazione() != null && ods.getDataCancellazione().before(now)) {
			return false;
		}

		UnitaMisura unMi = decodificaDad.getUnitaMisuraById(rigaOrdine.getUnitaMisura().getId());
		if (unMi.getDataValiditaFine() != null && unMi.getDataValiditaFine().before(now)) {
			return false;
		}

		AliquoteIva alIva = decodificaDad.getAliquoteIvaById(rigaOrdine.getAliquoteIva().getId());
		if (alIva.getDataValiditaFine() != null && alIva.getDataValiditaFine().before(now)) {
			return false;
		}

		return true;
	}

	private static <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		// checkBusinessCondition(externalResponse.isSuccess(), () -> CoreError.GENERIC_ERROR.getError("error",
		// externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		return externalResponse.getResponse();
	}

	private static boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

	private static boolean isNotEmpty(String s) {
		return s != null && !s.trim().equals("");
	}

}
