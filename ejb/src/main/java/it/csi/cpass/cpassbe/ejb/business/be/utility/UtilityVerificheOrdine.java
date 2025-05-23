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
package it.csi.cpass.cpassbe.ejb.business.be.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.UtilityParametri;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class UtilityVerificheOrdine {
	protected final static LogUtil log = new LogUtil(UtilityVerificheOrdine.class);

	public static BigDecimal getDeltaIvasRiga(RigaOrdine riga) {
		final BigDecimal importoIva = riga.getImportoIva();
		final BigDecimal ivaCalc = riga.getAliquoteIva().getPercentuale().multiply(riga.getImportoNetto()).divide(BigDecimal.valueOf(100));
		return importoIva.subtract(ivaCalc).abs();
	}

	public static List<ApiError> checkCompletezza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad,SettoreDad settoreDad) {
		final List<ApiError> apiErrors = new ArrayList<>();
		if (!checkInnerCompletezza(testataOrdine, rigaOrdineDad, impegnoDad)) {
			apiErrors.add(MsgCpassOrd.ORDORDE0047.getError());
		}
		return apiErrors;
	}

	private static boolean checkInnerCompletezza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		//  L'ordine deve avere almeno un destinatario collegato
		if (testataOrdine.getListDestinatario() == null || testataOrdine.getListDestinatario().size() == 0) {
			return false;
		}
		// Ogni destinatario trovato deve avere almeno una riga
		for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
			final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
			if (rigaOrdines == null || rigaOrdines.size() == 0) {
				return false;
			}
			// Ogni riga trovata deve avere almeno un impegno collegato
			for (final RigaOrdine rigaOrdine : rigaOrdines) {
				final List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
				if (impegnos == null || impegnos.size() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<ApiError> checkValidita(TestataOrdine testataOrdine, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad,RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad,UUID enteId) {
		List<ApiError> apiErrors = new ArrayList<>();
		Ente ente = testataOrdine.getSettore().getEnte();
		if (ente == null) {
			final Settore settore = settoreDad.findOne(testataOrdine.getSettore().getId());
			ente = settore.getEnte();
		}
		// Fornitore corrisponda ad un fornitore valido
		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setFornitore(testataOrdine.getFornitore());
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
		final ExternalServiceResolveWrapper<FornitoreHelper> handlerFornitore = externalHelperLookup.lookup(FornitoreHelper.class,enteId);

		final List<Fornitore> fornitori = invokeExternalService(handlerFornitore,() -> handlerFornitore.getInstance().getFornitori(handlerFornitore.getParams(), filtroFornitore));

		if (fornitori == null || fornitori.size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0048.getError());
		}
		// il settore_emittente sia valido
		Settore settore = settoreDad.findById(testataOrdine.getSettore().getId());
		if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
			apiErrors.add(MsgCpassOrd.ORDORDE0050.getError());
		}
		// Per ogni destinatario presente sull’ordine occorre verificare che:
		// il settore_destinatario_id sia valido
		if (testataOrdine.getListDestinatario() != null) {
			final List<Impegno> impegnosErrore = new ArrayList<>();
			final List<Subimpegno> subimpegnosErrore = new ArrayList<>();

			// Impegni: chiamata SIAC per verifiche
			final Impegno impegnoFiltro = new Impegno();
			impegnoFiltro.setAnnoProvvedimento(testataOrdine.getProvvedimento().getAnno());
			impegnoFiltro.setNumeroProvvedimento(testataOrdine.getProvvedimento().getNumero());
			final Subimpegno subimpegnoFiltro = new Subimpegno();
			subimpegnoFiltro.setImpegno(impegnoFiltro);
			final FiltroImpegni filtroImpegni = new FiltroImpegni();
			filtroImpegni.setSubimpegno(subimpegnoFiltro);
			filtroImpegni.setTestataOrdine(testataOrdine);
			final FiltroImpegni fi = arricchisciProvvedimentocolSettore( filtroImpegni ,  enteId,  settoreDad);
			final ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class,enteId);

			@SuppressWarnings("deprecation")
			final PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,() -> handlerImpegno.getInstance().getImpegniEsterni(handlerImpegno.getParams(), fi, 1, 0,Boolean.TRUE,Boolean.TRUE));
			//List<Impegno> listImpegni = pagedListImpegni.stream().collect(Collectors.toList());
			//PagedList<Impegno> pagedListImpegniSucc = invokeExternalService(handlerImpegno,() -> handlerImpegno.getInstance().getImpegniEsterniAnnoSuccessivo(handlerImpegno.getParams(), fi, 1, 0, Boolean.TRUE));

			/*
			Calendar calendar = Calendar.getInstance();
			List<Impegno> pagedListImpegniSuccFiltrata = pagedListImpegniSucc.stream()
			        .filter(impegno -> impegno.getAnno() > calendar.get(Calendar.YEAR))
			        .collect(Collectors.toList());
			//////////////////////////////////////////////////////////////////

			List<Impegno> combinedList = new ArrayList<>();
			combinedList.addAll(listImpegni);
			combinedList.addAll(pagedListImpegniSuccFiltrata);

			pagedListImpegni = new PagedListImpl<>(combinedList );
			 */

			//pagedListImpegniAnnoSucc = new PagedListImpl<>(listaAll );
			List<Impegno> listImpegnoSIAC = new ArrayList<>(); // lista vuota per far funzionare il controllo su "impegno found"
			if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
				listImpegnoSIAC = pagedListImpegni.getList();
			}

			// ciclo i destinatari
			for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
				settore = settoreDad.findById(destinatario.getSettore().getId());
				if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
					apiErrors= addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0051.getError());
				}
				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (final RigaOrdine rigaOrdine : rigaOrdines) {
						// L'oggetto di spesa utilizzato sia valido (CPASS_D_OGGETTO_SPESA.data_cancellazione non valorizzata)
						if (rigaOrdine.getOds().getDataCancellazione() != null) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0053.getError());
						}
						// L'aliquota iva sia valida (CPASS_D_ALIQUOTE_IVA.data_validita_inizio <= data corrente <= CPASS_D_ALIQUOTE_IVA.data_validita_inizio)
						if (rigaOrdine.getAliquoteIva().getDataValiditaInizio() != null&& rigaOrdine.getAliquoteIva().getDataValiditaInizio().after(new Date())) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0054.getError());
						}
						if (rigaOrdine.getAliquoteIva().getDataValiditaFine() != null && rigaOrdine.getAliquoteIva().getDataValiditaFine().before(new Date())) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0054.getError());
						}
						// L'unita' di misura sia valida (CPASS_D_UNITA_MISURA.data_cancellazione non valorizzata)
						if (rigaOrdine.getUnitaMisura().getDataValiditaInizio() != null && rigaOrdine.getUnitaMisura().getDataValiditaInizio().after(new Date())) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0055.getError());
						}
						if (rigaOrdine.getUnitaMisura().getDataValiditaFine() != null && rigaOrdine.getUnitaMisura().getDataValiditaFine().before(new Date())) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0055.getError());
						}

						// Per ogni distinto impegno presente su ogni riga dell’ordine occorre verificare che
						final List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
						//if (impegnos != null) {
						for (final Impegno impegno : impegnos) {
							boolean bImpegnoFoundSIAC = false;
							for (final Impegno impegnoSIAC : listImpegnoSIAC) {
								if (impegno.getAnnoEsercizio().equals(impegnoSIAC.getAnnoEsercizio()) && impegno.getAnno().equals(impegnoSIAC.getAnno())&& impegno.getNumero().equals(impegnoSIAC.getNumero())) {
									bImpegnoFoundSIAC = true;
									// Il disponibile ad ordinare non sia negativo
									if (impegno.getSubimpegni() == null || impegno.getSubimpegni().size() == 0) {
										// L'impegno deve essere definitivo - scartare tutti gli impegni senza subimpegni non definitivi (Impegni.stato !='D')
										if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
											apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0021.getError());
										} else {
											final BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC, ente.getId(),	null); // testataOrdine.getId());
											if (disponibile.floatValue() < 0) {
												if (!impegnosErrore.contains(impegno)) {
													impegnosErrore.add(impegno);
												}
											}
											/*
											 * Il valore di disponibilitaLiquidare restituito dal servizio di ricerca degli impegni sia maggiore
											 * o al più uguale all’importo indicato sull’impegno/subimpegno nell’ordine,
											 * altrimenti occorre restituire al caso d’uso chiamante il messaggio ORD-ORD-E-0106
											 */
											//anno impegno > anno corrente

											if ((getAnnoCorrente().compareTo(impegno.getAnno())>=0) && (impegnoSIAC.getDisponibilitaLiquidare().compareTo(impegno.getImporto()) < 0)) {
												log.info("", "impegnoSIAC.getDisponibilitaLiquidare() "+ impegnoSIAC.getDisponibilitaLiquidare());
												log.info("", "impegno.getImporto() "+ impegno.getImporto());
												apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0106.getError());
											}
										}

									} else {
										for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
											boolean bSubImpegnoFoundSIAC = false;

											for (final Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
												if (subimpegno.getNumero().equals(subimpegnoSIAC.getNumero())) {
													bSubImpegnoFoundSIAC = true;
													// L'impegno deve essere definitivo - scartare tutti i subimpegni non definitivi
													// (subimpegni.stato.codice != ‘D’)
													if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
														apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0021.getError());
													} else {
														final BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC,subimpegnoSIAC, ente.getId());
														if (disponibile.floatValue() < 0) {
															if (!subimpegnosErrore.contains(subimpegno)) {
																subimpegnosErrore.add(subimpegno);
															}
														}
														if ((getAnnoCorrente().compareTo(subimpegno.getAnno())>=0) && (subimpegnoSIAC.getDisponibilitaLiquidare().compareTo(subimpegno.getImporto())) < 0) {
															log.info("", "subimpegnoSIAC.getDisponibilitaLiquidare() "+ subimpegnoSIAC.getDisponibilitaLiquidare());
															log.info("", "subimpegno.getImporto() "+ subimpegno.getImporto());
															apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0106.getError());
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

							// Se l'impegno e' un impegno futuro
							if (impegno.getAnno() > testataOrdine.getAnno()) {
								// occorre verificare che siano state indicate le date di consegna in testata
								if (testataOrdine.getConsegnaDataDa() == null || testataOrdine.getConsegnaDataA() == null) {
									apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0057.getError());
								}
							}
						}
					}
				}
				//}
			}

			String strImpegniErrore = "";
			for (final Impegno impegno : impegnosErrore) {
				strImpegniErrore += "(impegno " + impegno.getAnno() + " - " + impegno.getNumero() + ") ";
			}
			for (final Subimpegno subimpegno : subimpegnosErrore) {
				strImpegniErrore += "(subimpegno " + subimpegno.getImpegno().getAnno() + " - " + subimpegno.getImpegno().getNumero() + "-" + subimpegno.getNumero() + ") ";
			}
			if (strImpegniErrore != null && strImpegniErrore.length() > 1) {
				apiErrors.add(MsgCpassOrd.ORDORDE0056.getError("impegni", strImpegniErrore));
			}
		}

		return apiErrors;
	}

	/**
	 * @param apiErrors
	 */
	protected static List<ApiError>  addErrorIfNotPresent(List<ApiError> apiErrors,ApiError error) {
		if (!apiErrors.contains(error)) {
			apiErrors.add(error);
		}
		return apiErrors;
	}




	public static List<ApiError> checkCongruenza(TestataOrdine testataOrdine) {
		List<ApiError> apiErrors = new ArrayList<>();

		// Verificare che sia stato indicato o l’indirizzo unico di consegna in testata
		if (StringUtility.isEmpty(testataOrdine.getConsegnaIndirizzo()) || StringUtility.isEmpty(testataOrdine.getConsegnaCap()) || StringUtility.isEmpty(testataOrdine.getConsegnaLocalita())) {
			// o in alternativa che siano stati indicati gli indirizzi su tutti i destinatari
			for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
				if (StringUtility.isEmpty(destinatario.getIndirizzo()) || StringUtility.isEmpty(destinatario.getNumCivico()) || StringUtility.isEmpty(destinatario.getLocalita()) || StringUtility.isEmpty(destinatario.getProvincia()) || StringUtility.isEmpty(destinatario.getCap())) {
					apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0058.getError());
				}
			}
		}
		return apiErrors;
	}

	public static List<ApiError> checkConsistenza(TestataOrdine testataOrdine, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		List<ApiError> apiErrors = new ArrayList<>();

		// la presenza del codice listino va verificata nel caso flagTrasmNso = true
		final boolean verificaListino = Boolean.TRUE.equals(testataOrdine.getTipoOrdine().getFlagTrasmNso());
		boolean listinoOk = true;

		if (testataOrdine.getListDestinatario() != null) {
			for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {

				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (final RigaOrdine rigaOrdine : rigaOrdines) {

						// verifica importi
						if (rigaOrdine.getImportoTotale().compareTo(BigDecimal.ZERO) == 0) {
							apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0146.getError());
						} else {
							BigDecimal totaleImpegni = new BigDecimal(0);

							final List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
							if (impegnos != null && impegnos.size() > 0) {
								for (final Impegno impegno : impegnos) {
									if (impegno.getSubimpegni() == null || impegno.getSubimpegni().size() == 0) {
										totaleImpegni = totaleImpegni.add(impegno.getImporto());
									} else {
										for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
											totaleImpegni = totaleImpegni.add(subimpegno.getImporto());
										}
									}
								}
								/*
								final double importoTotaleImpegni = NumberUtility.arrotondaDueDecimali(totaleImpegni.doubleValue());
								final double importoTotaleRiga = NumberUtility.arrotondaDueDecimali(rigaOrdine.getImportoTotale().doubleValue());
								if (importoTotaleImpegni != importoTotaleRiga) {
									apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0059.getError());
								}
								*/
								final BigDecimal importoTotaleImpegni = NumberUtility.arrotondaDueDec(totaleImpegni);
								final BigDecimal importoTotaleRiga = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale());
								//if (importoTotaleImpegni != importoTotaleRiga) {
								if (!importoTotaleImpegni.equals(importoTotaleRiga)) {
									apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0059.getError());
								}

							}
						}

						// verifica listino
						if (verificaListino && (rigaOrdine.getListinoFornitore() == null || rigaOrdine.getListinoFornitore().getId() == null)) {
							listinoOk = false;
						}

					}
				}
			}
		}

		if (verificaListino && !listinoOk) {
			apiErrors.add(MsgCpassOrd.ORDORDE0147.getError());
		}

		return apiErrors;
	}

	public static List<ApiError> checkTotaliRiga(TestataOrdine testataOrdine, SystemDad systemDad, RigaOrdineDad rigaOrdineDad, UUID enteId) {
		List<ApiError> apiErrors = new ArrayList<>();

		final Map<String, String> params = UtilityParametri.readParams(systemDad, ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(), enteId);
		final String strOrdTolleranzaIva = UtilityParametri.getParameter(params, ConstantsCPassParametro.ChiaveEnum.ORD_TOLLERANZA_IVA.getCostante());
		if (strOrdTolleranzaIva != null && !strOrdTolleranzaIva.trim().equals("")) {
			final double ordTolleranzaIva = Double.parseDouble(strOrdTolleranzaIva);

			// Dati gli importi NETTO e IVA e TOTALE e data ALIQUOTA IVA, occorre verificare che:
			// (NETTO * ALIQUOTA IVA/100) = IVA
			if (testataOrdine.getListDestinatario() != null) {
				for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {

					final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
					if (rigaOrdines != null) {
						for (final RigaOrdine rigaOrdine : rigaOrdines) {
							/*
							final double netto = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoNetto()).doubleValue();
							final double perc = rigaOrdine.getAliquoteIva().getPercentuale().doubleValue();
							final double importoIvaCalcolato = NumberUtility.arrotondaDueDecimali(netto * perc / 100);
							double importoIva = NumberUtility.arrotondaDueDecimali(rigaOrdine.getImportoIva().doubleValue());
							final double diff = importoIva - importoIvaCalcolato;
														
							if (Math.abs( NumberUtility.arrotondaDueDecimali(diff)) > ordTolleranzaIva) {
								apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0060.getError());
							} else if (Math.abs(diff) > 0) {
								apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDI0061.getError());
							}
							*/
							
							final BigDecimal netto = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoNetto());
							final BigDecimal perc = rigaOrdine.getAliquoteIva().getPercentuale();
							final BigDecimal importoIvaCalcolato = NumberUtility.arrotondaDueDec(netto.multiply(perc).divide(new BigDecimal(100)));
							BigDecimal importoIva = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoIva());
							final BigDecimal diff = importoIva.subtract(importoIvaCalcolato);
														
							if (Math.abs( NumberUtility.arrotondaDueDec(diff).doubleValue()) > ordTolleranzaIva) {
								apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDE0060.getError());
							} else if (Math.abs(diff.doubleValue()) > 0) {
								apiErrors = addErrorIfNotPresent(apiErrors,MsgCpassOrd.ORDORDI0061.getError());
							}
						}
					}
				}
			}
		}
		return apiErrors;
	}

	public static boolean rigaPerCopiaIsValid(RigaOrdine rigaOrdine, DecodificaDad decodificaDad) {
		final Date now = new Date();

		final Ods ods = decodificaDad.getOdsById(rigaOrdine.getOds().getId());
		if (ods.getDataCancellazione() != null && ods.getDataCancellazione().before(now)) {
			return false;
		}

		final UnitaMisura unMi = decodificaDad.getUnitaMisuraById(rigaOrdine.getUnitaMisura().getId());
		if (unMi.getDataValiditaFine() != null && unMi.getDataValiditaFine().before(now)) {
			return false;
		}

		final AliquoteIva alIva = decodificaDad.getAliquoteIvaById(rigaOrdine.getAliquoteIva().getId());
		if (alIva.getDataValiditaFine() != null && alIva.getDataValiditaFine().before(now)) {
			return false;
		}

		return true;
	}

	private static <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		return externalResponse.getResponse();
	}



	public static Integer getAnnoCorrente() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	private static FiltroImpegni arricchisciProvvedimentocolSettore(FiltroImpegni filtroImpegni , UUID enteId, SettoreDad settoreDad) {
		if(    filtroImpegni.getTestataOrdine()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento().getSettore()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento().getSettore().getCodice()!=null
				) {
			final Provvedimento provvedimento = filtroImpegni.getTestataOrdine().getProvvedimento();
			final String codiceSettore = provvedimento.getSettore().getCodice();
			final Optional<Settore> settore = settoreDad.findByCodice (codiceSettore,enteId,false);
			if(settore.isPresent()) {
				provvedimento.setSettore(settore.get());
			}else {
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "SETTORE NON TROVATO - NON VALIDO - NON ASSOCIASTO ALL'ENTE CORRETTO " +codiceSettore +" ENTE_ID -->"+enteId);
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
			}





			filtroImpegni.getTestataOrdine().setProvvedimento(provvedimento);
		}
		return filtroImpegni;
	}

}
