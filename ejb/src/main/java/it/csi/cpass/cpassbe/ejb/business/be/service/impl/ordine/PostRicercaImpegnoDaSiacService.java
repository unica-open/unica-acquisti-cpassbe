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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Impegnos
 */
public class PostRicercaImpegnoDaSiacService extends BaseService<PostRicercaImpegnoRequest, PostRicercaImpegnoResponse> {


	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaImpegnoDaSiacService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoDad impegnoDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void execute() {
		//request.getFiltroImpegni().setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		arricchisciProvvedimentocolSettore(enteId);
		final ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class,enteId);

		PagedList<Impegno> pagedListImpegni = invokeExternalService(handler,() -> handler.getInstance().getImpegniEsterni(handler.getParams(), request.getFiltroImpegni(), request.getPage(), request.getSize(),Boolean.FALSE,Boolean.FALSE));
		final List<Impegno> listImpegnoNew = new ArrayList<>();
		if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
			final List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();
			for (final Impegno impegnoSIAC : listImpegnoSIAC) {
				final List<Subimpegno> listSubImpegnoSIAC = impegnoSIAC.getSubimpegni();
				if (listSubImpegnoSIAC != null && listSubImpegnoSIAC.size() > 0) {
					final List<Subimpegno> listSubImpegnoNew = new ArrayList<>();
					for (final Subimpegno subimpegnoSIAC : listSubImpegnoSIAC) {
						listSubImpegnoNew.add(subimpegnoSIAC);
					}
					if (listSubImpegnoNew.size() > 0) {
						listImpegnoNew.add(impegnoSIAC);
						impegnoSIAC.setSubimpegni(listSubImpegnoNew);
					}
				} else {
					listImpegnoNew.add(impegnoSIAC);
				}
			}
			// Disponibile Calcolato con l’algoritmo [4] Calcolo del disponibile ad ordinare
			if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
				// ente necessario per ricerca
				final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
				final Ente ente = settoreCorrente.getEnte();
				for (final Impegno impegno : listImpegnoNew) {
					impegno.setEnte(ente);
					impegno.setAnnoEsercizio(request.getFiltroImpegni().getAnnoEsercizio());
					if (impegno.getSubimpegni() != null) {
						for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
							subimpegno.setEnte(ente);
							//TODO da capire se serve
							//subimpegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, ente.getId()));
						}
					}
				}
			}
			pagedListImpegni = new PagedListImpl<>(listImpegnoNew);
		}

		response.setImpegni(pagedListImpegni);
	}

	private void arricchisciProvvedimentocolSettore( UUID enteId) {
		final FiltroImpegni filtroImpegni =request.getFiltroImpegni();
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
			request.getFiltroImpegni().getTestataOrdine().setProvvedimento(provvedimento);
		}
	}








}
