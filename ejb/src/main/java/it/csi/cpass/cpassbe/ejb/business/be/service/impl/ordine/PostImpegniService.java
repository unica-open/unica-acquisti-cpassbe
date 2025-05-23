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
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Saves an TestataOrdine
 */
public class PostImpegniService extends BaseService<PostImpegniRequest, PostImpegniResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final ImpegnoDad impegnoDad;
	private final SettoreDad settoreDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param impegnoDad
	 */
	public PostImpegniService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoDad impegnoDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.impegnoDad = impegnoDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		// checkNotNull(testataOrdine, "testataOrdine", true);
	}

	@Override
	protected void execute() {
		final Fornitore fornitoreOrdine = request.getSalvaImpegni().getTestataOrdine().getFornitore();
		final CpassTFornitore cpassTFornitoreOrdine = CpassMappers.FORNITORE.toEntity(fornitoreOrdine);

		// ente necessario per ricerca
		final Settore settore = settoreDad.findOne(request.getSalvaImpegni().getTestataOrdine().getSettore().getId());
		final Ente ente = settore.getEnte();
		final CpassTEnte cpassTEnte = CpassMappers.ENTE.toEntity(ente);

		// carico "vecchi" impegni (per controllo disponibile in modifica)
		final List<Impegno> impegnosOld = impegnoDad.getImpegniByRiga(request.getSalvaImpegni().getRigaOrdine().getId());

		// chiamata SIAC per verifiche
		final Impegno impegnoFiltro = new Impegno();
		impegnoFiltro.setAnnoProvvedimento(request.getSalvaImpegni().getTestataOrdine().getProvvedimento().getAnno());
		impegnoFiltro.setNumeroProvvedimento(request.getSalvaImpegni().getTestataOrdine().getProvvedimento().getNumero());

		final Subimpegno subimpegnoFiltro = new Subimpegno();
		subimpegnoFiltro.setImpegno(impegnoFiltro);

		final FiltroImpegni filtroImpegni = new FiltroImpegni();
		filtroImpegni.setSubimpegno(subimpegnoFiltro);
		filtroImpegni.setTestataOrdine(request.getSalvaImpegni().getTestataOrdine());
		arricchisciProvvedimentocolSettore( filtroImpegni, cpassTEnte.getEnteId());
		final ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class,ente.getId());

		final PagedList<Impegno> pagedListImpegni = invokeExternalService(handler, () -> handler.getInstance().getImpegniEsterni(handler.getParams(), filtroImpegni, 1, 0,Boolean.TRUE,Boolean.FALSE));

		if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
			final List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			for (final Impegno impegnoSIAC : listImpegnoSIAC) {

				for (final Impegno impegno : request.getSalvaImpegni().getListImpegno()) {
					if (impegno == null) {
						continue;
					}
					if (impegno.getAnnoEsercizio() != null && impegno.getAnnoEsercizio().equals(impegnoSIAC.getAnnoEsercizio())
							&& impegno.getAnno() != null && impegno.getAnno().equals(impegnoSIAC.getAnno())
							&& impegno.getNumero() != null && impegno.getNumero().equals(impegnoSIAC.getNumero())) {

						// Disponibile Calcolato con l’algoritmo [4] Calcolo del disponibile ad ordinare
						impegno.setEnte(ente);
						impegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC, ente.getId(), null)); // request.getSalvaImpegni().getTestataOrdine().getId()));

						if (impegno.getSubimpegni() != null && impegno.getSubimpegni().size() > 0) {
							for (final Subimpegno subimpegno : impegno.getSubimpegni()) {

								for (final Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
									if (subimpegno.getNumero().equals(subimpegnoSIAC.getNumero())) {

										// L’impegno deve essere definitivo - scartare tutti i subimpegni non definitivi (subimpegni.stato.codice != ‘D’)
										if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
											checkCondition(false, MsgCpassOrd.ORDORDE0021.getError());
										}

										subimpegno.setEnte(ente);
										subimpegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, ente.getId()));

										BigDecimal importoOld = new BigDecimal(0);
										for (final Impegno impegnoOld : impegnosOld) {
											if (impegno.getAnnoEsercizio() != null && impegno.getAnnoEsercizio().equals(impegnoOld.getAnnoEsercizio())
													&& impegno.getAnno() != null && impegno.getAnno().equals(impegnoOld.getAnno())
													&& impegno.getNumero() != null && impegno.getNumero().equals(impegnoOld.getNumero())) {

												for (final Subimpegno subimpegnoOld : impegnoOld.getSubimpegni()) {
													if (subimpegno.getNumero() != null && subimpegno.getNumero().equals(subimpegnoOld.getNumero())) {
														importoOld = subimpegnoOld.getImporto();
														if (importoOld == null) { // caso impegno creato da inserimento automatico senza importo
															importoOld = new BigDecimal(0);
														}
													}
												}
											}
										}

										// Il disponibile ad ordinare deve essere maggiore o al più uguale
										final BigDecimal disponibile = subimpegno.getDisponibile().add(importoOld);
										if (disponibile.compareTo(subimpegno.getImporto()) < 0) {
											checkCondition(false, MsgCpassOrd.ORDORDE0026.getError());
										}
									}
								}

							}

						} else {
							// se il FE non ha filtrato correttamente
							if (impegno.getImporto().compareTo(new BigDecimal(0)) <= 0) {
								continue;
							}

							// L’impegno deve essere definitivo - scartare tutti gli impegni senza subimpegni non definitivi (Impegni.stato != ‘D’)
							if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
								checkCondition(false, MsgCpassOrd.ORDORDE0021.getError());
							}

							BigDecimal importoOld = new BigDecimal(0);
							for (final Impegno impegnoOld : impegnosOld) {
								if (impegno.getAnnoEsercizio() != null && impegno.getAnnoEsercizio().equals(impegnoOld.getAnnoEsercizio())
										&& impegno.getAnno() != null && impegno.getAnno().equals(impegnoOld.getAnno())
										&& impegno.getNumero() != null && impegno.getNumero().equals(impegnoOld.getNumero())) {
									importoOld = impegnoOld.getImporto();
								}
							}

							// Il disponibile ad ordinare deve essere maggiore o al più uguale
							final BigDecimal disponibile = impegno.getDisponibile().add(importoOld);
							if (disponibile.compareTo(impegno.getImporto()) < 0) {
								checkCondition(false, MsgCpassOrd.ORDORDE0026.getError());
							}
						}

						// 2.7.3 Controllo sulla classe di soggetti
						// impegni senza subimpegno
						if (impegnoSIAC.getSubimpegni() == null || impegnoSIAC.getSubimpegni().size() == 0) {
							if (impegnoSIAC.getFornitore().getCodice() == null || impegnoSIAC.getFornitore().getCodice().trim().equals("")) {

								final String ctrlClasseSogg = handler.getParams().get(ConstantsCPassParametro.ChiaveEnum.CTRL_CLASSE_SOGG.getCostante());
								if (ctrlClasseSogg != null
										&& ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_ERRORE.getCostante())) {
									checkCondition(false, MsgCpassOrd.ORDORDE0029.getError());
								} else if (ctrlClasseSogg != null
										&& ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_AVVISO.getCostante())) {
									if (request.getSalvaImpegni().getIgnoreWarnings() == null
											|| !request.getSalvaImpegni().getIgnoreWarnings().booleanValue()) {
										checkCondition(false, MsgCpassOrd.ORDORDA0028.getError());
									}
								}
							}
						}

					}
				}

			}
		}

		// impegni di competenza/residui
		boolean bImpegniCompetenzaResidui = false;
		// impegni futuri
		boolean bImpegniFuturi = false;

		final int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
		for (final Impegno impegno : request.getSalvaImpegni().getListImpegno()) {
			if (impegno == null) {
				continue;
			}
			if (impegno.getAnno().intValue() <= annoCorrente) {
				bImpegniCompetenzaResidui = true;
			} else {
				bImpegniFuturi = Boolean.TRUE;
			}
		}
		if (bImpegniCompetenzaResidui && bImpegniFuturi) {
			checkCondition(false, MsgCpassOrd.ORDORDE0027.getError());
		}

		if (response.getApiErrors() == null || response.getApiErrors().size() == 0) {
			impegnoDad.insertImpegni(request.getSalvaImpegni().getRigaOrdine(), request.getSalvaImpegni().getListImpegno(), cpassTFornitoreOrdine, cpassTEnte);
		}

		separaMessaggiErrorePerTipo(response.getApiErrors());

		response.setRigaOrdine(request.getSalvaImpegni().getRigaOrdine());
	}


	private void arricchisciProvvedimentocolSettore(FiltroImpegni filtroImpegni , UUID enteId) {
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
	}

}
