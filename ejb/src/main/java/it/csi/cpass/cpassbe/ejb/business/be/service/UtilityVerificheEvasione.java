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
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.UtilityArrotondamento;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
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
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class UtilityVerificheEvasione {

//	public static BigDecimal getDeltaIvasRiga(RigaEvasione riga) {
//		BigDecimal importoIva = riga.getImportoIva();
//		BigDecimal ivaCalc = riga.getAliquoteIva().getPercentuale().multiply(riga.getImportoNetto()).divide(BigDecimal.valueOf(100));
//
//		return importoIva.subtract(ivaCalc).abs();
//	}

	public static List<ApiError> checkCompletezza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		if (!checkInnerCompletezza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad)) {
			apiErrors.add(MsgCpassOrd.ORDORDE0104.getError());
		}
		return apiErrors;
	}

	private static boolean checkInnerCompletezza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad) {
		// • L’evasione deve avere almeno un destinatario collegato
		if (testataEvasione.getDestinatarioEvasiones() == null || testataEvasione.getDestinatarioEvasiones().size() == 0) {
			return false;
		}

		// • Ogni destinatario trovato deve avere almeno una riga
		for (DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {
			List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			if (rigaEvasiones == null || rigaEvasiones.size() == 0) {
				return false;
			}

			// • Ogni riga trovata deve avere almeno un impegno collegato
			for (RigaEvasione rigaEvasione : rigaEvasiones) {
				List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
				if (impegnoEvasiones == null || impegnoEvasiones.size() == 0) {
					return false;
				}
			}
		}

		// • All’evasione deve essere stata collegata una fattura
		if (testataEvasione.getFatturaAnno() == null || testataEvasione.getFatturaNumero() == null || testataEvasione.getFatturaNumero().trim().equals("")) {
			return false;
		}

		return true;
	}

	public static List<ApiError> checkValidita(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad,
			RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		// Per ogni destinatario presente sull’evasione ordine occorre verificare che:
		// • il settore_destinatario_id sia valido
		if (testataEvasione.getDestinatarioEvasiones() != null) {
			// ciclo i destinatari
			for (DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				Settore settore = settoreDad.findById(destinatarioEvasione.getSettore().getId());
				if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(new Date())) {
					if (!apiErrors.contains(MsgCpassOrd.ORDORDE0051.getError())) {
						apiErrors.add(MsgCpassOrd.ORDORDE0051.getError());
					}
				}

				// Per ogni riga presente sull’ordine occorre verificare che:
				List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (RigaEvasione rigaEvasione : rigaEvasiones) {

						// Per ogni distinto impegno presente su ogni riga dell’ordine occorre verificare che
						List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null) {
							for (ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {

								Impegno impegnoSIAC = getImpegnoSIAC(externalHelperLookup, apiErrors, impegnoEvasione);

								if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
									// impegno
									// • L’impegno indicato sia definitivo
									if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
										if (!apiErrors.contains(MsgCpassOrd.ORDORDE0105.getError())) {
											apiErrors.add(MsgCpassOrd.ORDORDE0105.getError());
										}
									} else {
										// • Il valore di disponibilitaLiquidare restituito dal servizio di ricerca degli impegni
										BigDecimal totale = impegnoEvasioneDad.calcolaTotale(impegnoEvasione.getImpegnoAnno(),
												impegnoEvasione.getImpegnoNumero());
										if (impegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
											if (!apiErrors.contains(MsgCpassOrd.ORDORDE0106.getError())) {
												apiErrors.add(MsgCpassOrd.ORDORDE0106.getError());
											}
										}
									}

								} else {
									// subimpegni
									for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {

										for (Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
											if (subimpegnoSIAC.getAnno().equals(subimpegnoEvasione.getSubimpegnoAnno())
													&& subimpegnoSIAC.getNumero().equals(subimpegnoEvasione.getSubimpegnoNumero())) {
												if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
													if (!apiErrors.contains(MsgCpassOrd.ORDORDE0105.getError())) {
														apiErrors.add(MsgCpassOrd.ORDORDE0105.getError());
													}
												} else {
													// • Il valore di disponibilitaLiquidare restituito dal servizio di ricerca degli impegni
													BigDecimal totale = subimpegnoEvasioneDad.calcolaTotale(subimpegnoEvasione.getImpegnoAnno(),
															subimpegnoEvasione.getImpegnoNumero(), subimpegnoEvasione.getSubimpegnoAnno(),
															subimpegnoEvasione.getSubimpegnoNumero());
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

		return apiErrors;
	}

	private static Impegno getImpegnoSIAC(ExternalHelperLookup externalHelperLookup, List<ApiError> apiErrors, ImpegnoEvasione impegnoEvasione) {
		// Impegni: chiamata SIAC per verifiche
		Impegno impegnoSIAC = null;

		Impegno impegnoFiltro = new Impegno();
		impegnoFiltro.setAnno(impegnoEvasione.getImpegnoAnno());
		impegnoFiltro.setNumero(impegnoEvasione.getImpegnoNumero());

		Subimpegno subimpegnoFiltro = new Subimpegno();
		subimpegnoFiltro.setImpegno(impegnoFiltro);

		FiltroImpegni filtroImpegni = new FiltroImpegni();
		// filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		filtroImpegni.setSubimpegno(subimpegnoFiltro);

		ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class);
		PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,
				() -> handlerImpegno.getInstance().getImpegni(handlerImpegno.getParams(), filtroImpegni, 1, 0), apiErrors);
		if (pagedListImpegni == null || pagedListImpegni.getList() == null || pagedListImpegni.getList().size() == 0) {
			apiErrors.add(
					MsgCpassOrd.ORDORDE0086.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero", subimpegnoFiltro.getImpegno().getNumero()));
		} else {
			List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			impegnoSIAC = listImpegnoSIAC.get(0);
		}
		return impegnoSIAC;
	}

	public static List<ApiError> checkConsistenza(TestataEvasione testataEvasione, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		// Per ogni riga dell’evasione ordine occorre verificare che l’importo coincida con il totale indicato sugli impegni

		if (testataEvasione.getDestinatarioEvasiones() != null) {
			for (DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (RigaEvasione rigaEvasione : rigaEvasiones) {
						BigDecimal totaleImpegni = new BigDecimal(0);

						List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
							for (ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {
								if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
									totaleImpegni = totaleImpegni.add(impegnoEvasione.getImportoRipartito());
									totaleImpegni = totaleImpegni.add(impegnoEvasione.getImportoSospeso());

								} else {
									for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
										totaleImpegni = totaleImpegni.add(subimpegnoEvasione.getImportoRipartito());
										totaleImpegni = totaleImpegni.add(subimpegnoEvasione.getImportoSospeso());
									}
								}
							}

							double importoTotaleImpegni = UtilityArrotondamento.arrotondaDueDecimali(totaleImpegni.doubleValue());
							double importoTotaleRiga = UtilityArrotondamento.arrotondaDueDecimali(rigaEvasione.getImportoTotale().doubleValue());
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

	public static List<ApiError> checkCongruenza(TestataEvasione testataEvasione, ControllaEvasione controllaEvasione, SystemDad systemDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		// Occorre confrontare CPASS_T_ORD_TESTATA_EVASIONE.totale_con_iva (di seguito TOT_EVASO) con
		// CPASS_T_ORD_TESTATA_EVASIONE.fattura_totale_liquidabile (di seguito TOT_LIQUIDABILE)
		BigDecimal totaleEvaso = testataEvasione.getTotaleConIva();
		BigDecimal totaleLiquidabile = testataEvasione.getFatturaTotaleLiquidabile();
		
		if (totaleEvaso == null) {
			totaleEvaso = new BigDecimal(0);
		}
		if (totaleLiquidabile == null) {
			totaleLiquidabile = new BigDecimal(0);
		}

		int iComp = totaleEvaso.compareTo(totaleLiquidabile);
		if (iComp == 0) {
			// • Se sono uguali, la verifica va a buon fine e termina qui

		} else {
			Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			Ente ente = settoreCorrente.getEnte();

			Parametro paramTolleranza = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.TOLLERANZA_EVASIONE.getCostante(),
					ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), ente.getId());
			if (paramTolleranza == null || paramTolleranza.getValore() == null || paramTolleranza.getValore().equals("")) {
				// se il parametro non esiste
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0093.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0093.getError());
				}

			} else {
				BigDecimal diff = totaleLiquidabile.subtract(totaleEvaso);
				if (diff.compareTo(new BigDecimal(0)) > 0) {
					double dTolleranzaEvasione = Double.parseDouble(paramTolleranza.getValore());
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
	 */
	public static List<ApiError> checkFatturaCollegata(TestataEvasione testataEvasione, ExternalHelperLookup externalHelperLookup, SystemDad systemDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		DocumentoSpesa filtroDocumentoSpesa = new DocumentoSpesa();
		filtroDocumentoSpesa.setAnnoDocumento(testataEvasione.getFatturaAnno());
		filtroDocumentoSpesa.setNumeroDocumento(testataEvasione.getFatturaNumero());
		filtroDocumentoSpesa.setCodiceFornitore(testataEvasione.getFatturaCodice());
		filtroDocumentoSpesa.setTipoDocumento(testataEvasione.getFatturaTipo());

		ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class);
		List<DocumentoSpesa> lista = invokeExternalService(handler, () -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtroDocumentoSpesa),
				apiErrors);
		if (lista == null || lista.size() == 0) {
			// throw new RuntimeException("Errore servizio 'ricercaDocumentoSpesa': nessun documento trovato.");
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "DocumentoSpesa non trovato"));
			
		} else {
			DocumentoSpesa documentoSpesa = lista.get(0);

			Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			Ente ente = settoreCorrente.getEnte();

			// verifica STATO_FATTURA_RIPARTIBILE
			Parametro paramStatoFatturaRipartibile = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.STATO_FATTURA_RIPARTIBILE.getCostante(),
					ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(), ente.getId());
			String statoFatturaRipartibile = paramStatoFatturaRipartibile.getValore();
			if (!documentoSpesa.getCodiceStato().equalsIgnoreCase(statoFatturaRipartibile)) {
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0108.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0108.getError());
				}
			}

			// confrontare con l’elenco degli ordini collegati all’evasione
			ArrayList<String> listNumeriOrdineEvasione = new ArrayList<String>();
			for (DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {
				TestataOrdine testataOrdine = destinatarioEvasione.getDestinatarioOrdine().getTestataOrdine();

				// la verifica va fatta su stringhe del tipo <anno ordine/numero ordine>
				String numeroOrdineEvasione = testataOrdine.getAnno() + "/" + testataOrdine.getNumero();
				if (!listNumeriOrdineEvasione.contains(numeroOrdineEvasione)) {
					listNumeriOrdineEvasione.add(numeroOrdineEvasione);
				}
			}

			// tali due elenchi devono essere identici
			if (listNumeriOrdineEvasione.size() != documentoSpesa.getNumeriOrdine().size()) {
				if (!apiErrors.contains((MsgCpassOrd.ORDORDE0092.getError()))) {
					apiErrors.add(MsgCpassOrd.ORDORDE0092.getError());
				}
			}

			// se l’evasione ha degli ordini diversi rispetto al documento
			for (String numeroOrdineEvasione : listNumeriOrdineEvasione) {
				boolean bFound = false;
				for (String numeroOrdineDocumento : documentoSpesa.getNumeriOrdine()) {
					if (numeroOrdineEvasione.equals(numeroOrdineDocumento)) {
						bFound = true;
					}
				}
				if (!bFound) {
					if (!apiErrors.contains((MsgCpassOrd.ORDORDE0092.getError()))) {
						apiErrors.add(MsgCpassOrd.ORDORDE0092.getError());
					}
				}
			}
		}

		return apiErrors;
	}

	/**
	 * Verifiche di congruenza dei fornitori degli impegni con il fornitore della fattura
	 */
	public static List<ApiError> checkCongruenzaFornitoriImpegniFattura(TestataEvasione testataEvasione, ControllaEvasione controllaEvasione,
			RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, SystemDad systemDad) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		String codiceSoggettoFattura = testataEvasione.getFatturaCodice();
		if (testataEvasione.getDestinatarioEvasiones() != null) {
			for (DestinatarioEvasione destinatarioEvasione : testataEvasione.getDestinatarioEvasiones()) {

				List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				if (rigaEvasiones != null) {
					for (RigaEvasione rigaEvasione : rigaEvasiones) {

						List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
							for (ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {

								if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
									// • Se si tratta di un impegno con soggetto,
									if (impegnoEvasione.getImpegno().getFornitore() != null
											&& !isEmpty(impegnoEvasione.getImpegno().getFornitore().getCodice())) {

										// se non è uguale al soggetto fattura
										if (!impegnoEvasione.getImpegno().getFornitore().getCodice().equals(codiceSoggettoFattura)) {
											if (!apiErrors.contains((MsgCpassOrd.ORDORDE0096.getError()))) {
												apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
											}
										}

									} else {
										Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
										Ente ente = settoreCorrente.getEnte();
										Parametro paramStatoFatturaRipartibile = systemDad.getParametro(
												ConstantsCPassParametro.ChiaveEnum.CTRL_CLASSE_SOGG.getCostante(),
												ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), ente.getId());
										String ctrlClasseSogg = paramStatoFatturaRipartibile.getValore();
										if (ctrlClasseSogg != null
												&& ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_ERRORE.getCostante())) {
											apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
										} else if (ctrlClasseSogg != null
												&& ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_AVVISO.getCostante())) {
											// se il warning non deve essere ignorato
											if (controllaEvasione.getListIgnoreWarning() != null
													&& !controllaEvasione.getListIgnoreWarning().contains(MsgCpassOrd.ORDORDA0097.getCode())) {
												apiErrors.add(MsgCpassOrd.ORDORDA0097.getError());
											}
										}
									}

								} else {
									for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
										if (!subimpegnoEvasione.getSubimpegno().getFornitore().getCodice().equals(codiceSoggettoFattura)) {
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
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		if (!externalResponse.isSuccess()) {
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		}
		return externalResponse.getResponse();
	}

	private static boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

}
