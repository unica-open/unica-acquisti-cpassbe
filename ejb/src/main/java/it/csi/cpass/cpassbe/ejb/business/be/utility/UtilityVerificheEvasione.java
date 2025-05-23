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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegniNew;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class UtilityVerificheEvasione {
	private static final LogUtil log = new LogUtil(UtilityVerificheEvasione.class);
	
	//	public static BigDecimal getDeltaIvasRiga(RigaEvasione riga) {
	//		BigDecimal importoIva = riga.getImportoIva();
	//		BigDecimal ivaCalc = riga.getAliquoteIva().getPercentuale().multiply(riga.getImportoNetto()).divide(BigDecimal.valueOf(100));
	//
	//		return importoIva.subtract(ivaCalc).abs();
	//	}
	/**
	 * 
	 * @param testataEvasione
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param isPerAutorizzazione
	 * @return
	 */
	public static List<ApiError> checkCompletezza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, Boolean isPerAutorizzazione) {
		final List<ApiError> apiErrors = new ArrayList<>();
		if (!checkInnerCompletezza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad, isPerAutorizzazione)) {
			if(isPerAutorizzazione != null && isPerAutorizzazione.equals(true)) {
				apiErrors.add(MsgCpassOrd.ORDORDE0104.getError());
			} else {
				apiErrors.add(MsgCpassOrd.ORDORDE0121.getError());
			}
		}
		return apiErrors;
	}

	private static boolean checkInnerCompletezza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, Boolean isPerAutorizzazione) {
		//  Levasione deve avere almeno un destinatario collegato
		if (testataEvasione.getDestinatarioEvasiones() == null || testataEvasione.getDestinatarioEvasiones().size() == 0) {
			return false;
		}

		//  Ogni destinatario trovato deve avere almeno una riga
		for (final DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {
			final List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			if (rigaEvasiones == null || rigaEvasiones.size() == 0) {
				return false;
			}

			//  Ogni riga trovata deve avere almeno un impegno collegato
			for (final RigaEvasione rigaEvasione : rigaEvasiones) {
				final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
				if(rigaEvasione.getImportoTotale().compareTo(BigDecimal.ZERO) > 0 &&  (impegnoEvasiones == null || impegnoEvasiones.size() == 0)) {
					return false;
				}
			}

		}

		if(isPerAutorizzazione != null && isPerAutorizzazione.equals(true)) {
			// All evasione deve essere stata collegata una fattura
			if (testataEvasione.getFatturaAnno() == null || testataEvasione.getFatturaNumero() == null || testataEvasione.getFatturaNumero().trim().equals("")) {
				return false;
			}
		}


		return true;
	}
	/**
	 * 
	 * @param testataEvasione
	 * @param externalHelperLookup
	 * @param settoreDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param subimpegnoEvasioneDad
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkValidita(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad,RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad, UUID enteId) {
		final List<ApiError> apiErrors = new ArrayList<>();

		// Per ogni destinatario presente sull’evasione ordine occorre verificare che: il settore_destinatario_id sia valido
		if (testataEvasione.getDestinatarioEvasiones() != null) {
			// ciclo i destinatari
			for (final DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				final Settore settore = settoreDad.findById(destinatarioEvasione.getSettore().getId());
				if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
					if (!apiErrors.contains(MsgCpassOrd.ORDORDE0051.getError())) {
						apiErrors.add(MsgCpassOrd.ORDORDE0051.getError());
					}
				}

				// Per ogni riga presente sull’ordine occorre verificare che:
				final List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (final RigaEvasione rigaEvasione : rigaEvasiones) {
						// Per ogni distinto impegno presente su ogni riga dell’ordine occorre verificare che
						final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null) {
							for (final ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {
								final Impegno impegnoSIAC = getImpegnoSIAC(externalHelperLookup, apiErrors, impegnoEvasione, enteId);
								if (impegnoSIAC != null) {
									if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
										// impegno
										// L'impegno indicato sia definitivo
										if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
											if (!apiErrors.contains(MsgCpassOrd.ORDORDE0105.getError())) {
												apiErrors.add(MsgCpassOrd.ORDORDE0105.getError());
											}
										} else {
											// Il valore di disponibilitaLiquidare restituito dal servizio di ricerca degli impegni
											final BigDecimal totale = impegnoEvasioneDad.calcolaTotale(impegnoEvasione.getImpegnoAnno(),impegnoEvasione.getImpegnoNumero(),testataEvasione.getId() );
											if (impegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
												if (!apiErrors.contains(MsgCpassOrd.ORDORDE0106.getError())) {
													apiErrors.add(MsgCpassOrd.ORDORDE0106.getError());
												}
											}
										}
									} else {
										// subimpegni
										for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {

											for (final Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
												if (subimpegnoSIAC.getAnno().equals(subimpegnoEvasione.getSubimpegnoAnno())
														&& subimpegnoSIAC.getNumero().equals(subimpegnoEvasione.getSubimpegnoNumero())) {
													if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
														if (!apiErrors.contains(MsgCpassOrd.ORDORDE0105.getError())) {
															apiErrors.add(MsgCpassOrd.ORDORDE0105.getError());
														}
													} else {
														//  Il valore di disponibilitaLiquidare restituito dal servizio di ricerca degli impegni
														final BigDecimal totale = subimpegnoEvasioneDad.calcolaTotale(subimpegnoEvasione.getImpegnoAnno(),
																subimpegnoEvasione.getImpegnoNumero(), subimpegnoEvasione.getSubimpegnoAnno(),
																subimpegnoEvasione.getSubimpegnoNumero(), testataEvasione.getId());
														if (subimpegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
															if (!apiErrors.contains(MsgCpassOrd.ORDORDE0106.getError())) {
																apiErrors.add(MsgCpassOrd.ORDORDE0106.getError());
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return apiErrors;
	}

	/**
	 * 
	 * @param externalHelperLookup
	 * @param apiErrors
	 * @param impegnoEvasione
	 * @param enteId
	 * @return
	 */
	private static Impegno getImpegnoSIAC(ExternalHelperLookup externalHelperLookup, List<ApiError> apiErrors, ImpegnoEvasione impegnoEvasione, UUID enteId) {
		// Impegni: chiamata SIAC per verifiche
		Impegno impegnoSIAC = null;
		// Nuova gestione
		final FiltroImpegniNew filtroImpegniNew = new FiltroImpegniNew();
		filtroImpegniNew.setAnnoImpegno(impegnoEvasione.getImpegnoAnno());
		filtroImpegniNew.setNumeroImpegno(impegnoEvasione.getImpegnoNumero());
		final ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class, enteId);
		final PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,() -> handlerImpegno.getInstance().getImpegniEsterni(handlerImpegno.getParams(), filtroImpegniNew, 1, 0,Boolean.FALSE), apiErrors);

		if (pagedListImpegni == null || pagedListImpegni.getList() == null || pagedListImpegni.getList().size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0086.getError("anno", filtroImpegniNew.getAnnoImpegno(), "numero", filtroImpegniNew.getNumeroImpegno()));
			//apiErrors.add(MsgCpassOrd.ORDORDE0086.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero", subimpegnoFiltro.getImpegno().getNumero()));
		} else {
			final List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			impegnoSIAC = listImpegnoSIAC.get(0);
		}
		return impegnoSIAC;
	}
	
	/**
	 * 
	 * @param testataEvasione
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @return
	 */
	public static List<ApiError> checkConsistenza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad) {
		final List<ApiError> apiErrors = new ArrayList<>();
		// Per ogni riga dell’evasione ordine occorre verificare che l’importo coincida con il totale indicato sugli impegni

		if (testataEvasione.getDestinatarioEvasiones() != null) {
			for (final DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				final List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (final RigaEvasione rigaEvasione : rigaEvasiones) {
						BigDecimal totaleImpegni = new BigDecimal(0);

						final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
							for (final ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {
								if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
									totaleImpegni = totaleImpegni.add(impegnoEvasione.getImportoRipartito());
									totaleImpegni = totaleImpegni.add(impegnoEvasione.getImportoSospeso());

								} else {
									for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
										totaleImpegni = totaleImpegni.add(subimpegnoEvasione.getImportoRipartito());
										totaleImpegni = totaleImpegni.add(subimpegnoEvasione.getImportoSospeso());
									}
								}
							}

							//double importoTotaleImpegni = NumberUtility.arrotondaDueDecimali(totaleImpegni.doubleValue());
							//double importoTotaleRiga = NumberUtility.arrotondaDueDecimali(rigaEvasione.getImportoTotale().doubleValue());
							final BigDecimal importoTotaleImpegni = NumberUtility.arrotondaDueDec(totaleImpegni);
							final BigDecimal importoTotaleRiga = NumberUtility.arrotondaDueDec(rigaEvasione.getImportoTotale());

							//if (importoTotaleImpegni != importoTotaleRiga) {
							if (importoTotaleImpegni.compareTo(importoTotaleRiga)!= 0) {
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
	/**
	 * 
	 * @param testataEvasione
	 * @param controllaEvasione
	 * @param systemDad
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkCongruenza(TestataEvasione testataEvasione, ControllaEvasione controllaEvasione, SystemDad systemDad, UUID enteId) {
		return checkCongruenza (testataEvasione.getTotaleConIva(), testataEvasione.getFatturaTotaleLiquidabile(), controllaEvasione, systemDad, enteId );
	}
	
	/**
	 * 
	 * @param totaleEvaso
	 * @param totaleLiquidabile
	 * @param controllaEvasione
	 * @param systemDad
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkCongruenza(BigDecimal totaleEvaso, BigDecimal totaleLiquidabile, ControllaEvasione controllaEvasione, SystemDad systemDad, UUID enteId) {
		final List<ApiError> apiErrors = new ArrayList<>();


		if (totaleEvaso == null) {
			totaleEvaso = new BigDecimal(0);
		}
		if (totaleLiquidabile == null) {
			totaleLiquidabile = new BigDecimal(0);
		}

		final int iComp = totaleEvaso.compareTo(totaleLiquidabile);
		if (iComp == 0) {
			// Se sono uguali, la verifica va a buon fine e termina qui
		} else {
			final Parametro paramTolleranza = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.TOLLERANZA_EVASIONE.getCostante(),
					ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), enteId);
			if (paramTolleranza == null || paramTolleranza.getValore() == null || paramTolleranza.getValore().equals("")) {
				// se il parametro non esiste
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0093.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0093.getError());
				}

			} else {
				final BigDecimal diff = totaleLiquidabile.subtract(totaleEvaso);
				if (diff.compareTo(new BigDecimal(0)) > 0) {
					final double dTolleranzaEvasione = Double.parseDouble(paramTolleranza.getValore());
					if (diff.doubleValue() <= dTolleranzaEvasione) {
						// se TOT_LIQUIDABILE - TOT_EVASO > 0 e TOT_LIQUIDABILE - TOT_EVASO <= TOLLERANZA_EVASIONE
						if (!apiErrors.contains((MsgCpassOrd.ORDORDA0103.getError()))) {
							// se il warning non deve essere ignorato
							if (controllaEvasione.getListIgnoreWarning() != null
									&& !controllaEvasione.getListIgnoreWarning().contains(MsgCpassOrd.ORDORDA0103.getCode())) {
								apiErrors.add(MsgCpassOrd.ORDORDA0103.getError());
							}
						}

					} else {
						// se TOT_LIQUIDABILE - TOT_EVASO > PARAMETRO_TOLLERANZA
						if (!apiErrors.contains((MsgCpassOrd.ORDORDE0102.getError()))) {
							apiErrors.add(MsgCpassOrd.ORDORDE0102.getError());
						}
					}

				} else if (diff.compareTo(new BigDecimal(0)) < 0) {
					// se TOT_LIQUIDABILE - TOT_EVASO < 0
					if (!apiErrors.contains((MsgCpassOrd.ORDORDE0101.getError()))) {
						apiErrors.add(MsgCpassOrd.ORDORDE0101.getError());
					}
				}
			}

		}

		return apiErrors;
	}

	/**
	 * Verifiche sulla fattura collegata
	 * eseguite in conferma e autorizza evasione
	 * @param testataEvasione
	 * @param externalHelperLookup
	 * @param systemDad
	 * @param isPerAutorizzazione
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkFatturaCollegata(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, SystemDad systemDad, Boolean isPerAutorizzazione,UUID enteId ) {
		List<ApiError> apiErrors = new ArrayList<>();
		if (isPerAutorizzazione ||
				(testataEvasione.getFatturaAnno()!=null || testataEvasione.getFatturaNumero()!=null || testataEvasione.getFatturaCodiceFornitore() != null || testataEvasione.getFatturaTipo() != null)) {
			apiErrors = checkFatturaCollegata(testataEvasione, externalHelperLookup, systemDad,enteId);
		}
		return apiErrors;
	}
	/**
	 * 
	 * @param testataEvasione
	 * @param externalHelperLookup
	 * @param systemDad
	 * @param enteId
	 * @return
	 */
	private static List<ApiError> checkFatturaCollegata(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, SystemDad systemDad,UUID enteId) {
		final List<ApiError> apiErrors = new ArrayList<>();

		final List<DocumentoSpesa> lista = getFatturaCollegata(testataEvasione, externalHelperLookup, apiErrors,enteId);

		if (lista == null || lista.size() == 0) {
			// throw new RuntimeException("Errore servizio 'ricercaDocumentoSpesa': nessun documento trovato.");
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "DocumentoSpesa non trovato"));

		} else {
			final DocumentoSpesa documentoSpesa = lista.get(0);

			// verifica STATO_FATTURA_RIPARTIBILE
			final Parametro paramStatoFatturaRipartibile = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.STATO_FATTURA_RIPARTIBILE.getCostante(),	ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(), enteId);
			final String statoFatturaRipartibile = paramStatoFatturaRipartibile.getValore();
			if (!documentoSpesa.getCodiceStato().equalsIgnoreCase(statoFatturaRipartibile)) {
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0108.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0108.getError());
				}
			}

			// confrontare con l’elenco degli ordini collegati all’evasione
			final ArrayList<String> listNumeriOrdineEvasione = new ArrayList<>();
			for (final DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {
				final TestataOrdine testataOrdine = destinatarioEvasione.getDestinatarioOrdine().getTestataOrdine();

				// la verifica va fatta su stringhe del tipo <anno ordine/numero ordine>
				final String numeroOrdineEvasione = testataOrdine.getAnno() + "/" + testataOrdine.getNumero();
				if (!listNumeriOrdineEvasione.contains(numeroOrdineEvasione)) {
					listNumeriOrdineEvasione.add(numeroOrdineEvasione);
				}
			}

			// se l’evasione ha degli ordini non citati nel documento
			final String[] splitNumeriOrdineDs = documentoSpesa.getNumeriOrdine().get(0).split(";"); // supponendo che la lista sia sempre lunga 1
			for (final String numeroOrdineEvasione : listNumeriOrdineEvasione) {
				final String bFound = Arrays.stream(splitNumeriOrdineDs).filter(x -> x.equals(numeroOrdineEvasione)).findAny().orElse(null);
				if (bFound == null) {
					if (!apiErrors.contains((MsgCpassOrd.ORDORDE0092.getError("arg1", splitNumeriOrdineDs, "arg2",numeroOrdineEvasione)))) {
						apiErrors.add(MsgCpassOrd.ORDORDE0092.getError());
					}
				}

			}
		}
		return apiErrors;
	}

	/**
	 * Verifiche sulla fattura collegata
	 * eseguite in invia a contabilità
	 * @param testataEvasiones
	 * @param externalHelperLookup
	 * @param systemDad
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkFatturaCollegata(List<TestataEvasione> testataEvasiones, ExternalHelperLookup externalHelperLookup, SystemDad systemDad,UUID enteId) {

		final List<ApiError> apiErrors = new ArrayList<>();
		final TestataEvasione testataEvasione = testataEvasiones.get(0);

		final List<DocumentoSpesa> lista = getFatturaCollegata(testataEvasione, externalHelperLookup, apiErrors,enteId);

		if (lista == null || lista.size() == 0) {
			// throw new RuntimeException("Errore servizio 'ricercaDocumentoSpesa': nessun documento trovato.");
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "DocumentoSpesa non trovato"));

		} else {
			final DocumentoSpesa documentoSpesa = lista.get(0);

			final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			final Ente ente = settoreCorrente.getEnte();

			// verifica STATO_FATTURA_RIPARTIBILE
			final Parametro paramStatoFatturaRipartibile = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.STATO_FATTURA_RIPARTIBILE.getCostante(),ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(), ente.getId());
			final String statoFatturaRipartibile = paramStatoFatturaRipartibile.getValore();
			if (!documentoSpesa.getCodiceStato().equalsIgnoreCase(statoFatturaRipartibile)) {
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0108.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0108.getError());
				}
			}

			// confrontare con l’elenco degli ordini collegati all’evasione
			final ArrayList<String> listNumeriOrdineEvasione = new ArrayList<>();
			testataEvasiones.stream().flatMap(te -> te.getDestinatarioEvasiones().stream()).collect(Collectors.toList()).forEach(x -> {listNumeriOrdineEvasione.add(x.getDestinatarioOrdine().getTestataOrdine().getAnno() + "/" + x.getDestinatarioOrdine().getTestataOrdine().getNumero());});

			// Se il documento ha degli ordini diversi dalle evasioni (l'evasione ha seciramente ordini citati nella fattura, controllo fatto al momento del conferma e autorizza)
			final String[] splitNumeriOrdineDs = documentoSpesa.getNumeriOrdine().get(0).split(";"); // supponendo che la lista sia sempre lunga 1
			for (final String numeroOrdineDs : splitNumeriOrdineDs) {
				final String bFound = listNumeriOrdineEvasione.stream().filter(x -> x.equals(numeroOrdineDs)).findAny().orElse(null);
				if (bFound == null) {
					if (!apiErrors.contains((MsgCpassOrd.ORDORDE0092.getError("arg1", splitNumeriOrdineDs, "arg2",listNumeriOrdineEvasione)))) {
						apiErrors.add(MsgCpassOrd.ORDORDE0092.getError("arg1", splitNumeriOrdineDs, "arg2",listNumeriOrdineEvasione));
					}
				}
			}
		}
		return apiErrors;
	}
	/**
	 * 
	 * @param testataEvasione
	 * @param externalHelperLookup
	 * @param apiErrors
	 * @param enteId
	 * @return
	 */
	private static List<DocumentoSpesa> getFatturaCollegata(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, List<ApiError> apiErrors,UUID enteId) {

		final DocumentoSpesa filtroDocumentoSpesa = new DocumentoSpesa();
		filtroDocumentoSpesa.setAnnoDocumento(testataEvasione.getFatturaAnno());
		filtroDocumentoSpesa.setNumeroDocumento(testataEvasione.getFatturaNumero());
		filtroDocumentoSpesa.setCodiceFornitore(testataEvasione.getFatturaCodiceFornitore());
		filtroDocumentoSpesa.setTipoDocumento(testataEvasione.getFatturaTipo());

		final ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class,enteId);
		final List<DocumentoSpesa> lista = invokeExternalService(handler, () -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtroDocumentoSpesa),apiErrors);
		return lista;
	}

	/**
	 * Verifiche di congruenza dei fornitori degli impegni con il fornitore della fattura
	 * @param testataEvasione
	 * @param controllaEvasione
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param systemDad
	 * @param isPerAutorizzazione
	 * @param enteId
	 * @return
	 */
	public static List<ApiError> checkCongruenzaFornitoriImpegniFattura(TestataEvasione testataEvasione, ControllaEvasione controllaEvasione,RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SystemDad systemDad, Boolean isPerAutorizzazione, UUID enteId) {
		List<ApiError> apiErrors = new ArrayList<>();
		if (isPerAutorizzazione || (testataEvasione.getFatturaAnno()!=null || testataEvasione.getFatturaNumero()!=null || testataEvasione.getFatturaCodiceFornitore() != null || testataEvasione.getFatturaTipo() != null)) {
			apiErrors = checkCongruenzaFornitoriImpegniFattura(testataEvasione, controllaEvasione,rigaEvasioneDad, impegnoEvasioneDad, systemDad, enteId);
		}
		return apiErrors;
	}
	/**
	 * 
	 * @param testataEvasione
	 * @param controllaEvasione
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param systemDad
	 * @param enteId
	 * @return
	 */
	private static List<ApiError> checkCongruenzaFornitoriImpegniFattura(TestataEvasione testataEvasione, ControllaEvasione controllaEvasione,RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SystemDad systemDad, UUID enteId) {
		final List<ApiError> apiErrors = new ArrayList<>();
		final String codiceSoggettoFattura = testataEvasione.getFatturaCodiceFornitore();
		if (testataEvasione.getDestinatarioEvasiones() != null) {
			for (final DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				final List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (final RigaEvasione rigaEvasione : rigaEvasiones) {

						final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
							for (final ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {

								if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
									// Se si tratta di un impegno con soggetto,
									if (impegnoEvasione.getImpegno().getFornitore() != null && !isEmpty(impegnoEvasione.getImpegno().getFornitore().getCodice())) {

										// se non è uguale al soggetto fattura
										if (!impegnoEvasione.getImpegno().getFornitore().getCodice().equals(codiceSoggettoFattura)) {
											log.info("checkCongruenzaFornitoriImpegniFattura", "1");
											//log.info("checkCongruenzaFornitoriImpegniFattura", "impegnoEvasione.getImpegno().getFornitore().getCodice() "+ impegnoEvasione.getImpegno().getFornitore().getCodice());
											//log.info("checkCongruenzaFornitoriImpegniFattura", "codiceSoggettoFattura " + codiceSoggettoFattura);
											
											if (!apiErrors.contains((MsgCpassOrd.ORDORDE0096.getError()))) {
												apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
											}
										}

									} else {
										final Parametro paramStatoFatturaRipartibile = systemDad.getParametro(
												ConstantsCPassParametro.ChiaveEnum.CTRL_CLASSE_SOGG.getCostante(),
												ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), enteId);
										final String ctrlClasseSogg = paramStatoFatturaRipartibile.getValore();
										if (ctrlClasseSogg != null && ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_ERRORE.getCostante())) {
											log.info("checkCongruenzaFornitoriImpegniFattura", "2");
											//log.info("checkCongruenzaFornitoriImpegniFattura", "ctrlClasseSogg "+ ctrlClasseSogg);
											//log.info("checkCongruenzaFornitoriImpegniFattura", "CTRL_CLASSE_SOGG_ERRORE " + ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_ERRORE.getCostante());											
											apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
										} else if (ctrlClasseSogg != null && ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_AVVISO.getCostante())) {
											// se il warning non deve essere ignorato
											if (controllaEvasione.getListIgnoreWarning() != null && !controllaEvasione.getListIgnoreWarning().contains(MsgCpassOrd.ORDORDA0097.getCode())) {
												apiErrors.add(MsgCpassOrd.ORDORDA0097.getError());
											}
										}
									}

								} else {
									final LogUtil log = new LogUtil(UtilityVerificheEvasione.class);

									for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
										if (    subimpegnoEvasione.getSubimpegno().getFornitore()==null ||
												subimpegnoEvasione.getSubimpegno().getFornitore().getCodice() == null ||	
												!subimpegnoEvasione.getSubimpegno().getFornitore().getCodice().equals(codiceSoggettoFattura)) {
											
											log.info("checkCongruenzaFornitoriImpegniFattura", "3");
											//log.info("checkCongruenzaFornitoriImpegniFattura", "subimpegnoEvasione.getSubimpegno().getFornitore()==null ???    " +  subimpegnoEvasione.getSubimpegno().getFornitore()==null);
											//log.info("checkCongruenzaFornitoriImpegniFattura", "subimpegnoEvasione.getSubimpegno().getFornitore().getCodice() == null  " +  subimpegnoEvasione.getSubimpegno().getFornitore().getCodice() == null);											
											//log.info("checkCongruenzaFornitoriImpegniFattura", "codiceSoggettoFattura " +  codiceSoggettoFattura);											
											if (!apiErrors.contains((MsgCpassOrd.ORDORDE0096.getError()))) {
												apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
											}
										}
									}
								}
							}
						}

					}
				}
			}
		}
		return apiErrors;
	}

	//	private  <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
	//		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
	//		// checkBusinessCondition(externalResponse.isSuccess(), () -> CoreError.GENERIC_ERROR.getError("error",
	//		// externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
	//		return externalResponse.getResponse();
	//	}

	public static <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier,
			List<ApiError> apiErrors) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		if (!externalResponse.isSuccess()) {
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		}
		return externalResponse.getResponse();
	}

	private static boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

}
