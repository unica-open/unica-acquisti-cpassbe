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
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
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

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione dad
	 */
	protected BaseImpegniEvasioneService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
	}

	public void controlli(SalvaImpegniEvasione salvaImpegniEvasione, List<ApiError> apiErrors, ImpegnoEvasione impegnoEvasione,
			SubimpegnoEvasione subimpegnoEvasione, Subimpegno subimpegnoFiltro) {
		// 2.7.2.1 Esistenza impegno
		FiltroImpegni filtroImpegni = new FiltroImpegni();
		// filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		filtroImpegni.setSubimpegno(subimpegnoFiltro);

		ExternalServiceResolveWrapper<ImpegnoHelper> handlerImpegno = externalHelperLookup.lookup(ImpegnoHelper.class);
		PagedList<Impegno> pagedListImpegni = invokeExternalService(handlerImpegno,
				() -> handlerImpegno.getInstance().getImpegni(handlerImpegno.getParams(), filtroImpegni, 1, 0));

		if (pagedListImpegni == null || pagedListImpegni.getList() == null || pagedListImpegni.getList().size() == 0) {
			apiErrors.add(
					MsgCpassOrd.ORDORDE0086.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero", subimpegnoFiltro.getImpegno().getNumero()));

		} else {
			List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			Impegno impegnoSIAC = listImpegnoSIAC.get(0);
			boolean bIsSubimpegno = subimpegnoFiltro.getAnno() != null && subimpegnoFiltro.getNumero() != null;

			Subimpegno subimpegnoSIAC = null;
			if (bIsSubimpegno) {
				// cerco subimpegno
				for (Subimpegno subimpegnoItem : impegnoSIAC.getSubimpegni()) {
					if (subimpegnoItem.getAnno().equals(subimpegnoFiltro.getAnno()) && subimpegnoItem.getNumero().equals(subimpegnoFiltro.getNumero())) {
						subimpegnoSIAC = subimpegnoItem;
					}
				}
			}

			// Se i due fornitori sono uguali o la fattura non è ancora collegata
			boolean bCheckRiaccertato = false;
			boolean bFornitoriDiversi = false;
			if (salvaImpegniEvasione.getTestataEvasione().getFatturaCodice() == null
					|| salvaImpegniEvasione.getTestataEvasione().getFatturaCodice().trim().equals("")) {
				bCheckRiaccertato = true;
			} else {
				if (salvaImpegniEvasione.getTestataEvasione().getFatturaCodice().equals(salvaImpegniEvasione.getTestataEvasione().getFornitore().getCodice())) {
					bCheckRiaccertato = true;
				} else {
					bFornitoriDiversi = true;
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
						// • Se si tratta di un subimpegno, occorre verificare il soggetto del subimpegno, se non è uguale al soggetto fattura
						if (!subimpegnoSIAC.getFornitore().getCodice().equals(salvaImpegniEvasione.getTestataEvasione().getFatturaCodice())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0096.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
							}
						}
					}

				} else {
					if (impegnoSIAC.getFornitore() != null && impegnoSIAC.getFornitore().getCodice() != null) {
						// • Se si tratta di un impegno con soggetto, se non è uguale al soggetto fattura
						if (!impegnoSIAC.getFornitore().getCodice().equals(salvaImpegniEvasione.getTestataEvasione().getFatturaCodice())) {
							if (!apiErrors.contains(MsgCpassOrd.ORDORDE0096.getError())) {
								apiErrors.add(MsgCpassOrd.ORDORDE0096.getError());
							}
						}
					}
				}

				// • Se si tratta di impegno senza soggetto,
				if (impegnoSIAC.getFornitore() == null || impegnoSIAC.getFornitore().getCodice() == null
						|| impegnoSIAC.getFornitore().getCodice().trim().equals("")) {
					String ctrlClasseSogg = handlerImpegno.getParams().get(ConstantsCPassParametro.ChiaveEnum.CTRL_CLASSE_SOGG.getCostante());
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
				BigDecimal totale = subimpegnoEvasione.getImportoRipartito().add(subimpegnoEvasione.getImportoSospeso());
				if (subimpegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
					apiErrors.add(MsgCpassOrd.ORDORDE0089.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			} else {
				BigDecimal totale = impegnoEvasione.getImportoRipartito().add(impegnoEvasione.getImportoSospeso());
				if (impegnoSIAC.getDisponibilitaLiquidare().compareTo(totale) < 0) {
					apiErrors.add(MsgCpassOrd.ORDORDE0089.getError("anno", subimpegnoFiltro.getImpegno().getAnno(), "numero",
							subimpegnoFiltro.getImpegno().getNumero()));
				}
			}

			// test
			int a = 0;
			if (a != 0) {
				apiErrors.add(MsgCpassOrd.ORDORDA0097.getError());
			}

			if (apiErrors.size() == 0) {
				impegnoEvasione.setImpegno(impegnoSIAC);
				if (bIsSubimpegno) {
					subimpegnoEvasione.setSubimpegno(subimpegnoSIAC);
				}
			}

		}
	}

}
