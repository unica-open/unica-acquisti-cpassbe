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
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
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
public class PostRicercaImpegnoBypassService extends BaseService<PostRicercaImpegnoRequest, PostRicercaImpegnoResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final ImpegnoDad impegnoDad;
	private final SettoreDad settoreDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaImpegnoBypassService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoDad impegnoDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.impegnoDad = impegnoDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void execute() {
		request.getFiltroImpegni().setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class,enteId);
		arricchisciProvvedimentocolSettore( enteId);
		PagedList<Impegno> pagedListImpegni         = invokeExternalService(handler,() -> handler.getInstance().getImpegniEsterni(handler.getParams(), request.getFiltroImpegni(), request.getPage(), request.getSize(),Boolean.TRUE,Boolean.TRUE));

		//PagedList<Impegno> pagedListImpegniAnnoSucc = invokeExternalService(handler,() -> handler.getInstance().getImpegniEsterniAnnoSuccessivo(handler.getParams(), request.getFiltroImpegni(), request.getPage(), request.getSize(),Boolean.TRUE));

		final Impegno impegnoFiltro = request.getFiltroImpegni().getSubimpegno().getImpegno();
		final String codiceFornitoreFiltro = impegnoFiltro.getFornitore().getCodice();
		final Integer numeroSubimpegnoFiltro = request.getFiltroImpegni().getSubimpegno().getNumero();

		List<Impegno> listImpegnoNew = new ArrayList<>();
		//List<Impegno> listImpegnoAnnoSuccNew = new ArrayList<Impegno>();

		// 2.8.3 Esposizione degli impegni trovati (scrematura)
		final boolean bNoFiltriImpegni = false; // true per test con più dati
		if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
			final List<Impegno> listImpegnoEsterno = pagedListImpegni.getList();
			if (bNoFiltriImpegni) {
				listImpegnoNew = listImpegnoEsterno;
			} else {
				for (final Impegno impegnoEsterno : listImpegnoEsterno) {
					final List<Subimpegno> listSubImpegnoEsterno = impegnoEsterno.getSubimpegni();
					if (listSubImpegnoEsterno != null && listSubImpegnoEsterno.size() > 0) {
						final List<Subimpegno> listSubImpegnoNew = new ArrayList<>();

						for (final Subimpegno subimpegnoEsterno : listSubImpegnoEsterno) {
							if (checkSubimpegnoByFornitore(codiceFornitoreFiltro, subimpegnoEsterno) && checkSubImpegnoByCapitoloArticolo(impegnoFiltro, subimpegnoEsterno) && checkSubimpegnoByAnnoNumeroSubimpegnoNumero(impegnoFiltro, numeroSubimpegnoFiltro, impegnoEsterno, subimpegnoEsterno)) {
								listSubImpegnoNew.add(subimpegnoEsterno);
							}
						}
						if (listSubImpegnoNew.size() > 0) {
							listImpegnoNew.add(impegnoEsterno);
							impegnoEsterno.setSubimpegni(listSubImpegnoNew);
						}

					} else {

						if (checkImpegnoFornitore(codiceFornitoreFiltro, impegnoEsterno) && checkImpegnoByCapitoloArticolo(impegnoFiltro, impegnoEsterno)&& checkImpegnoByAnnoNumero(impegnoFiltro, impegnoEsterno)) {
							listImpegnoNew.add(impegnoEsterno);
						}

					}
				}

				// carico impegni collegati per controlli successivi (modifica)
				CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = null;
				if (request.getFiltroImpegni().getTestataOrdine().getId() != null) {
					cpassTOrdImpegnoOrdine = UtilityImpegni.verificaPresenzaImpegniCollegatiOrdine(impegnoDad,request.getFiltroImpegni().getTestataOrdine().getId());
				}

				// controllo "anno congruente impegni collegati"
				// Se il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e l’ordine ha già degli impegni collegati
				if (cpassTOrdImpegnoOrdine != null) {
					listImpegnoNew = UtilityImpegni.controlloAnnoCongruente(impegnoDad, request.getFiltroImpegni().getTestataOrdine().getId(),cpassTOrdImpegnoOrdine, listImpegnoNew);
				}
				// controllo "data_ordini_futuri"
				// Se il caso d’uso chiamante è [6] ORD1 Inserire testata ordine, oppure il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e
				// l’ordine non ha ancora alcun impegno collegato
				boolean bControlloDataOrdiniFuturi = false;
				if (request.getFiltroImpegni().getTestataOrdine().getId() == null) {
					bControlloDataOrdiniFuturi = Boolean.TRUE;
				} else {
					if (cpassTOrdImpegnoOrdine == null) {
						bControlloDataOrdiniFuturi = Boolean.TRUE;
					}
				}
				if (bControlloDataOrdiniFuturi) {
					listImpegnoNew = UtilityImpegni.controlloDataAnniFuturi(handler.getParams(), listImpegnoNew,
							request.getFiltroImpegni().getTestataOrdine().getDataEmissione());
				}
			}

			// Disponibile Calcolato con l’algoritmo [4] Calcolo del disponibile ad ordinare
			if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
				// ente necessario per ricerca
				final Settore settore = settoreDad.findOne(request.getFiltroImpegni().getTestataOrdine().getSettore().getId());
				final Ente ente = settore.getEnte();

				for (final Impegno impegno : listImpegnoNew) {
					impegno.setEnte(ente);
					impegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, ente.getId(), null)); // request.getFiltroImpegni().getTestataOrdine().getId()));

					if (impegno.getSubimpegni() != null) {
						for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
							subimpegno.setEnte(ente);
							subimpegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, ente.getId()));
						}
					}
				}
			}
			pagedListImpegni = new PagedListImpl<>(listImpegnoNew);
			response.setImpegni(pagedListImpegni);
		}
	}

	private Boolean checkSubimpegnoByAnnoNumeroSubimpegnoNumero(Impegno impegnoFiltro, Integer numeroSubimpegnoFiltro, Impegno impegno, Subimpegno subimpegno) {
		// se l’attore ha citato un valore nei campi di mappa 4 Anno impegno, 5 Numero impegno e 6 Numero Subimpegno
		if (impegnoFiltro.getAnno() != null && impegnoFiltro.getNumero() != null && numeroSubimpegnoFiltro != null) {
			if (impegno.getAnno().intValue() == impegnoFiltro.getAnno().intValue() && impegno.getNumero().intValue() == impegnoFiltro.getNumero().intValue()
					&& subimpegno.getNumero().intValue() == numeroSubimpegnoFiltro.intValue()) {
				log.debug("checkSubimpegnoByAnnoNumeroSubimpegnoNumero ","ret true");
			} else {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	private boolean checkSubimpegnoByFornitore(String codiceFornitoreFiltro, Subimpegno subimpegno) {
		// Occorre tenere eventuali subimpegni collegati al fornitore dell’ordine (Impegni.Subimpegni.CodiceSoggetto = codice fornitore ordine
		if (subimpegno.getFornitore() != null && subimpegno.getFornitore().getCodice() != null
				&& subimpegno.getFornitore().getCodice().equals(codiceFornitoreFiltro)) {
			return Boolean.TRUE;
		}
		return false;
	}

	private boolean checkImpegnoByAnnoNumero(Impegno impegnoFiltro, Impegno impegno) {
		// se l’attore ha citato un valore nei campi di mappa 4 Anno impegno e 5 Numero impegno
		if (impegnoFiltro.getAnno() != null && impegnoFiltro.getNumero() != null) {
			if (impegno.getAnno().intValue() == impegnoFiltro.getAnno().intValue() && impegno.getNumero().intValue() == impegnoFiltro.getNumero().intValue()) {
			} else {
				return false;
			}
		}
		return Boolean.TRUE;
	}

	private boolean checkImpegnoByCapitoloArticolo(Impegno impegnoFiltro, Impegno impegno) {
		// se l’attore ha citato un valore nei campi di mappa 2 Numero capitolo e 3 Numero articolo
		if (impegnoFiltro.getNumeroCapitolo() != null && impegnoFiltro.getNumeroArticolo() != null) {
			if (impegno.getNumeroCapitolo().intValue() == impegnoFiltro.getNumeroCapitolo().intValue() && impegno.getNumeroArticolo().intValue() == impegnoFiltro.getNumeroArticolo().intValue()) {
			} else {
				return false;
			}
		}
		return Boolean.TRUE;
	}

	private Boolean checkSubImpegnoByCapitoloArticolo(Impegno impegnoFiltro, Subimpegno sub) {
		// se l’attore ha citato un valore nei campi di mappa 2 Numero capitolo e 3 Numero articolo
		Boolean ris = Boolean.TRUE;
		if (impegnoFiltro.getNumeroCapitolo() != null && impegnoFiltro.getNumeroArticolo() != null) {
			if (!(sub.getNumeroCapitolo().intValue() == impegnoFiltro.getNumeroCapitolo().intValue() && sub.getNumeroArticolo().intValue() == impegnoFiltro.getNumeroArticolo().intValue())) {
				ris = Boolean.FALSE;
			}
		}
		return ris;
	}


	private boolean checkImpegnoFornitore(String codiceFornitoreFiltro, Impegno impegno) {
		// Occorre tenere eventuali impegni che non hanno subimpegni e che sono collegati al fornitore dell’ordine (Impegni.CodiceSoggetto = codice
		// fornitore ordine)
		// Occorre tenere tutti impegni che non hanno subimpegni collegati e non citano alcun fornitore nel campo Impegni.CodiceSoggetto
		if (impegno.getFornitore() == null || impegno.getFornitore().getCodice() == null || impegno.getFornitore().getCodice().equals(codiceFornitoreFiltro)) {
			return Boolean.TRUE;
		}
		return false;
	}

	// nella tabella provvedimento ho il settore id che deve essere valido e dell'ente giusto
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
