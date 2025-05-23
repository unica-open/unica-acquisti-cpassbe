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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Base class for services for /testataEvasione path
 *
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseImpegniEvasioneService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	protected final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione dad
	 */
	protected BaseImpegniEvasioneService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.settoreDad = settoreDad;
	}

	public void controlli(SalvaImpegniEvasione salvaImpegniEvasione
			, List<ApiError> apiErrors
			, ImpegnoEvasione impegnoEvasione
			,SubimpegnoEvasione subimpegnoEvasione
			, Subimpegno subimpegnoFiltro
			, UUID enteId
			) {
		// 2.7.2.1 Esistenza impegno
		final FiltroImpegni filtroImpegni = new FiltroImpegni();
		// filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		filtroImpegni.setSubimpegno(subimpegnoFiltro);

		final ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class,enteId);
		arricchisciProvvedimentocolSettore( filtroImpegni, enteId);

		final PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,() -> handlerImpegno.getInstance().getImpegniEsterni(handlerImpegno.getParams(), filtroImpegni, 1, 0,Boolean.TRUE,Boolean.FALSE));

		if (pagedListImpegni == null || pagedListImpegni.getList() == null || pagedListImpegni.getList().size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0086.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero", subimpegnoFiltro.getImpegno().getNumero()));
		} else {
			final List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			final Impegno impegnoSIAC = listImpegnoSIAC.get(0);
			final boolean bIsSubimpegno = subimpegnoFiltro.getAnno() != null && subimpegnoFiltro.getNumero() != null;

			Subimpegno subimpegnoSIAC = null;
			if (bIsSubimpegno) {
				// cerco subimpegno
				for (final Subimpegno subimpegnoItem : impegnoSIAC.getSubimpegni()) {
					if (subimpegnoItem.getAnno().equals(subimpegnoFiltro.getAnno()) && subimpegnoItem.getNumero().equals(subimpegnoFiltro.getNumero())) {
						subimpegnoSIAC = subimpegnoItem;
					}
				}
			}

			// Se i due fornitori sono uguali o la fattura non è ancora collegata
			boolean bCheckRiaccertato = false;
			boolean bFornitoriDiversi = false;
			if (salvaImpegniEvasione.getTestataEvasione().getFatturaCodiceFornitore() == null
					|| salvaImpegniEvasione.getTestataEvasione().getFatturaCodiceFornitore().trim().equals("")) {
				bCheckRiaccertato = Boolean.TRUE;
			} else {
				if (salvaImpegniEvasione.getTestataEvasione().getFatturaCodiceFornitore().equals(salvaImpegniEvasione.getTestataEvasione().getFornitore().getCodice())) {
					bCheckRiaccertato = Boolean.TRUE;
				} else {
					bFornitoriDiversi = Boolean.TRUE;
				}
			}

			if (bCheckRiaccertato) {
				if (impegnoEvasione.getImpegnoOrdine().getImpegnoAnno() < impegnoSIAC.getAnno()) {
					if (impegnoEvasione.getImpegnoOrdine().getImpegnoAnno().equals(impegnoSIAC.getAnnoImpegnoRiaccertato())
							&& impegnoEvasione.getImpegnoOrdine().getImpegnoNumero().equals(impegnoSIAC.getNumImpegnoRiaccertato())) {
						// ok
					} else {
						apiErrors.add(MsgCpassOrd.ORDORDE0087.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
								subimpegnoFiltro.getImpegno().getNumero(), "anno-impegno-ordine", impegnoEvasione.getImpegnoOrdine().getImpegnoAnno(),
								"numero-impegno-ordine", impegnoEvasione.getImpegnoOrdine().getImpegnoNumero()));
					}

				}
			}

			// Se i due fornitori non sono uguali
			if (bFornitoriDiversi) {
				if (bIsSubimpegno) {
					if (subimpegnoSIAC.getFornitore() != null && subimpegnoSIAC.getFornitore().getCodice() != null) {
						//  Se si tratta di un subimpegno, occorre verificare il soggetto del subimpegno, se non è uguale al soggetto fattura
						if (
								subimpegnoSIAC.getFornitore()==null ||
								subimpegnoSIAC.getFornitore().getCodice() !=null ||	
								!subimpegnoSIAC.getFornitore().getCodice().equals(salvaImpegniEvasione.getTestataEvasione().getFatturaCodiceFornitore())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0096.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
							}
						}
					}

				} else {
					if (impegnoSIAC.getFornitore() != null && impegnoSIAC.getFornitore().getCodice() != null) {
						//  Se si tratta di un impegno con soggetto, se non è uguale al soggetto fattura
						if (!impegnoSIAC.getFornitore().getCodice().equals(salvaImpegniEvasione.getTestataEvasione().getFatturaCodiceFornitore())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0096.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
							}
						}
					}
				}

				//  Se si tratta di impegno senza soggetto,
				if (impegnoSIAC.getFornitore() == null || impegnoSIAC.getFornitore().getCodice() == null
						|| impegnoSIAC.getFornitore().getCodice().trim().equals("")) {
					final String ctrlClasseSogg = handlerImpegno.getParams().get(ConstantsCPassParametro.ChiaveEnum.CTRL_CLASSE_SOGG.getCostante());
					if (ctrlClasseSogg != null && ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_ERRORE.getCostante())) {
						if (!apiErrors.contains(MsgCpassOrd.ORDORDE0096.getError())) {
							apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
						}
					} else if (ctrlClasseSogg != null
							&& ctrlClasseSogg.equalsIgnoreCase(ConstantsCPassParametro.ValoreEnum.CTRL_CLASSE_SOGG_AVVISO.getCostante())) {
						if (salvaImpegniEvasione.getIgnoreWarnings() == null || !salvaImpegniEvasione.getIgnoreWarnings().booleanValue()) {
							apiErrors.add(MsgCpassOrd.ORDORDA0097.getError());
						}
					}
				}
			}

			// 2.7.2.3 Stato
			if (bIsSubimpegno) {
				if (!subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
					apiErrors.add(MsgCpassOrd.ORDORDE0088.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			} else {
				if (!impegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO)) {
					apiErrors.add(MsgCpassOrd.ORDORDE0088.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			}

			// 2.7.2.4 Liquidabile
			if (bIsSubimpegno) {
				final BigDecimal totale = subimpegnoEvasione.getImportoRipartito().add(subimpegnoEvasione.getImportoSospeso());
				if (subimpegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
					apiErrors.add(MsgCpassOrd.ORDORDE0089.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			} else {
				final BigDecimal totale = impegnoEvasione.getImportoRipartito().add(impegnoEvasione.getImportoSospeso());
				if (impegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
					apiErrors.add(MsgCpassOrd.ORDORDE0089.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			}

			// test
			/*
			final int a = 0;
			if (a != 0) {
				apiErrors.add(MsgCpassOrd.ORDORDA0097.getError());
			}
			 */
			if (apiErrors.size() == 0) {
				impegnoEvasione.setImpegno(impegnoSIAC);
				if (bIsSubimpegno) {
					subimpegnoEvasione.setSubimpegno(subimpegnoSIAC);
				}
			}

		}
	}

	/**
	 * @param enteId
	 */
	protected void arricchisciProvvedimentocolSettore(FiltroImpegni filtroImpegni,UUID enteId) {
		if(filtroImpegni.getTestataOrdine()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento().getSettore() !=null) {
			final Provvedimento provvedimento= filtroImpegni.getTestataOrdine().getProvvedimento();
			final String codiceSettore = provvedimento.getSettore().getCodice();
			final Optional<Settore> settore = settoreDad.findByCodice (codiceSettore,enteId,false,"like");
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
