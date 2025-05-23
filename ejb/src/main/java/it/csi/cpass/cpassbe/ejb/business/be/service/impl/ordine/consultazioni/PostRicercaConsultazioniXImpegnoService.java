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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.consultazioni;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ConsultazioniDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniImpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
public class PostRicercaConsultazioniXImpegnoService extends BaseService<PostRicercaConsultazioniXImpegnoRequest, PostRicercaConsultazioniXImpegnoResponse> {

	ConsultazioniDad consultazioniDad;
	FornitoreDad     fornitoreDad;
	RicercaXConsultazioni filtro;
	List<Impegno>listaImpesercizio;
	List<Impegno>listaImpesercizioProssimo;
	ExternalHelperLookup externalHelperLookup;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param consultazioniDad    the testataOrdine DAD
	 */

	public PostRicercaConsultazioniXImpegnoService(ConfigurationHelper configurationHelper,ExternalHelperLookup externalHelperLookup,ConsultazioniDad consultazioniDad,FornitoreDad     fornitoreDad) {
		super(configurationHelper);
		this.consultazioniDad = consultazioniDad;
		this.fornitoreDad = fornitoreDad;
		this.externalHelperLookup =externalHelperLookup;
	}

	@Override
	protected void checkServiceParams() {

	}

	@Override
	protected void execute() {
		filtro                      = request.getRicercaXConsultazioni();
		listaImpesercizio 			= request.getListImpegnoEsercizio();
		listaImpesercizioProssimo 	= request.getListImpegnoEsercizioProssimo();

		List<Impegno> listaImpegniAll = new ArrayList<>();
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = settoreCorrente.getEnte().getId();

		for(final Impegno imp:listaImpesercizio) {
			if(filtro.getAnnoEsercizio() > annoCorrente) {
				if(filtro.getAnnoEsercizio() <= imp.getAnno()) {
					listaImpegniAll.add(imp);
				}
			}else {
				listaImpegniAll.add(imp);
			}
		}
		for(final Impegno imp2:listaImpesercizioProssimo) {
			if(filtro.getAnnoEsercizio()+1 <= imp2.getAnno()) {
				listaImpegniAll.add(imp2);
			}
		}
		listaImpegniAll = riordino (listaImpegniAll);
		List<ConsultazioniImpegno> listConsultazioniImpegno = consultazioniDad.valorizzaConsultazioniImpegno(listaImpegniAll,enteId,filtro.getAnnoEsercizio());
		//int pagina = request.getPage();
		//int lunghezza = request.getSize();
		//listConsultazioniImpegno = UtilityCommon.estraiLaPaginaScelta(listConsultazioniImpegno, pagina, lunghezza);
		listConsultazioniImpegno = aggiungiIlFornitore(listConsultazioniImpegno);
		//int totalPages = UtilityCommon.getTotalPage(listConsultazioniImpegno.size(), request.getSize());
		//PagedListImpl<ConsultazioniImpegno> pagedListImpegni = new PagedListImpl<>(listConsultazioniImpegnoPaginata ,request.getPage(),totalPages,listConsultazioniImpegno.size());
		//response.setConsultazioniImpegno(pagedListImpegni);
		response.setListaconsultazioniImpegno(listConsultazioniImpegno);
	}
	// per ottimizzare partire da qui
	private List<ConsultazioniImpegno> aggiungiIlFornitore(List<ConsultazioniImpegno> listConsultazioniImpegno) {
		final List<ConsultazioniImpegno> risultato = new ArrayList<>();
		//arricchiscoil risultato con la ragione sociale del fornitore
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		for(final ConsultazioniImpegno ci:listConsultazioniImpegno) {
			List<Fornitore> listaForn = new ArrayList<>();

			if(ci.getFornitoreCodice()!=null && !ci.getFornitoreCodice().trim().equalsIgnoreCase("")) {
				final Fornitore fornitore = new Fornitore();
				fornitore.setCodice(ci.getFornitoreCodice());
				listaForn = fornitoreDad.postRicercaFornitoreMinimalInterno(fornitore , ente);


				if(listaForn!= null && listaForn.size()>0) {
					ci.setFornitoreRagioneSociale(listaForn.get(0).getRagioneSociale());
				}else {
					final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
					final FiltroFornitore filtroFornitore = new FiltroFornitore();
					final Fornitore forn = new Fornitore();

					forn.setCodice(ci.getFornitoreCodice());
					filtroFornitore.setFornitore(forn );
					filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
					final ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
					final List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));
					if(fornitori!= null && fornitori.size()>0) {
						ci.setFornitoreRagioneSociale(fornitori.get(0).getRagioneSociale());
					}

				}
			}

			risultato.add(ci);
		}
		return risultato;
	}

	private List<Impegno> riordino(List<Impegno> listaImpegniAll) {
		// TODO Auto-generated method stub
		return listaImpegniAll;
	}

}
