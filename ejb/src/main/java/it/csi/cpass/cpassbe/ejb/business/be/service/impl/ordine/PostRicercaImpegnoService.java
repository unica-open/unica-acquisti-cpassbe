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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Retrieves an Impegnos
 */
public class PostRicercaImpegnoService extends BaseService<PostRicercaImpegnoRequest, PostRicercaImpegnoResponse> {

	private final static String CONFIG_PARAM_ASSOC_IMPEGNI_ORD = "ASSOC_IMPEGNI_ORD";
	private final static String CONFIG_PARAM_ASSOC_IMPEGNI_ORD_VALUE_DISP_CRESC = "DISP_CRESC";

	private final ExternalHelperLookup externalHelperLookup;
	private ImpegnoDad impegnoDad;
	private SettoreDad settoreDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaImpegnoService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ImpegnoDad impegnoDad,
			SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.impegnoDad = impegnoDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		request.getFiltroImpegni().setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);

		ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class);
		PagedList<Impegno> pagedListImpegni = invokeExternalService(handler,() -> handler.getInstance().getImpegni(handler.getParams(), request.getFiltroImpegni(), request.getPage(), request.getSize()));

		Impegno impegnoFiltro = request.getFiltroImpegni().getSubimpegno().getImpegno();
		String codiceFornitoreFiltro = impegnoFiltro.getFornitore().getCodice();
		Integer numeroSubimpegnoFiltro = request.getFiltroImpegni().getSubimpegno().getNumero();

		List<Impegno> listImpegnoNew = new ArrayList<Impegno>();

		// 2.8.3 Esposizione degli impegni trovati (scrematura)
		boolean bNoFiltriImpegni = false; // true per test con più dati
		if (pagedListImpegni != null && pagedListImpegni.getList() != null) {
			List<Impegno> listImpegnoSIAC = pagedListImpegni.getList();

			if (bNoFiltriImpegni) {
				listImpegnoNew = listImpegnoSIAC;

			} else {
				for (Impegno impegnoSIAC : listImpegnoSIAC) {

					List<Subimpegno> listSubImpegnoSIAC = impegnoSIAC.getSubimpegni();
					if (listSubImpegnoSIAC != null && listSubImpegnoSIAC.size() > 0) {
						List<Subimpegno> listSubImpegnoNew = new ArrayList<Subimpegno>();

						for (Subimpegno subimpegnoSIAC : listSubImpegnoSIAC) {
							if (checkSubimpegnoByFornitore(codiceFornitoreFiltro, subimpegnoSIAC)
									&& checkSubimpegnoByAnnoNumeroSubimpegnoNumero(impegnoFiltro, numeroSubimpegnoFiltro, impegnoSIAC, subimpegnoSIAC)) {
								listSubImpegnoNew.add(subimpegnoSIAC);
							}
						}
						if (listSubImpegnoNew.size() > 0) {
							listImpegnoNew.add(impegnoSIAC);
							impegnoSIAC.setSubimpegni(listSubImpegnoNew);
						}

					} else {
						if (checkImpegnoFornitore(codiceFornitoreFiltro, impegnoSIAC) && checkImpegnoByCapitoloArticolo(impegnoFiltro, impegnoSIAC)
								&& checkImpegnoByAnnoNumero(impegnoFiltro, impegnoSIAC)) {
							listImpegnoNew.add(impegnoSIAC);
						}
					}
				}

				// carico impegni collegati per controlli successivi (modifica)
				CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = null;
				if (request.getFiltroImpegni().getTestataOrdine().getId() != null) {
					cpassTOrdImpegnoOrdine = UtilityImpegni.verificaPresenzaImpegniCollegatiOrdine(impegnoDad,
							request.getFiltroImpegni().getTestataOrdine().getId());
				}

				// controllo "anno congruente impegni collegati"
				// Se il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e l’ordine ha già degli impegni collegati
				if (cpassTOrdImpegnoOrdine != null) {
					listImpegnoNew = UtilityImpegni.controlloAnnoCongruente(impegnoDad, request.getFiltroImpegni().getTestataOrdine().getId(),
							cpassTOrdImpegnoOrdine, listImpegnoNew);
				}

				// controllo "data_ordini_futuri"
				// Se il caso d’uso chiamante è [6] ORD1 Inserire testata ordine, oppure il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e
				// l’ordine non ha ancora alcun impegno collegato
				boolean bControlloDataOrdiniFuturi = false;
				if (request.getFiltroImpegni().getTestataOrdine().getId() == null) {
					bControlloDataOrdiniFuturi = true;
				} else {
					if (cpassTOrdImpegnoOrdine == null) {
						bControlloDataOrdiniFuturi = true;
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
				Settore settore = settoreDad.findOne(request.getFiltroImpegni().getTestataOrdine().getSettore().getId());
				Ente ente = settore.getEnte();

				for (Impegno impegno : listImpegnoNew) {
					impegno.setEnte(ente);
					impegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, ente.getId(), null)); // request.getFiltroImpegni().getTestataOrdine().getId()));

					if (impegno.getSubimpegni() != null) {
						for (Subimpegno subimpegno : impegno.getSubimpegni()) {
							subimpegno.setEnte(ente);
							subimpegno.setDisponibile(UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, ente.getId()));
						}
					}
				}
			}

			// ordinamento
			String assocImpegniOrdine = handler.getParams().get(CONFIG_PARAM_ASSOC_IMPEGNI_ORD);
			if (assocImpegniOrdine != null && assocImpegniOrdine.equalsIgnoreCase(CONFIG_PARAM_ASSOC_IMPEGNI_ORD_VALUE_DISP_CRESC)) {
				Collections.sort(listImpegnoNew, new Comparator<Impegno>() {
					@Override
					public int compare(Impegno impegno1, Impegno impegno2) {
						if (impegno1.getDisponibile() != null && impegno2.getDisponibile() != null) {
							int compareDisponibile = impegno1.getDisponibile().compareTo(impegno2.getDisponibile());
							if (compareDisponibile != 0) {
								return compareDisponibile;
							}
						}

						int compareAnno = impegno1.getAnno().compareTo(impegno2.getAnno());
						if (compareAnno != 0) {
							return compareAnno;
						} else {
							return impegno1.getNumero().compareTo(impegno2.getNumero());
						}

					}
				});
			} else {
				Collections.sort(listImpegnoNew, new Comparator<Impegno>() {
					@Override
					public int compare(Impegno impegno1, Impegno impegno2) {
						int compareAnno = impegno1.getAnno().compareTo(impegno2.getAnno());
						if (compareAnno != 0) {
							return compareAnno;
						} else {
							return impegno1.getNumero().compareTo(impegno2.getNumero());
						}
					}
				});
			}

			pagedListImpegni = new PagedListImpl<>(listImpegnoNew);
		}

		response.setImpegni(pagedListImpegni);
	}

	private boolean checkSubimpegnoByAnnoNumeroSubimpegnoNumero(Impegno impegnoFiltro, Integer numeroSubimpegnoFiltro, Impegno impegno, Subimpegno subimpegno) {
		// se l’attore ha citato un valore nei campi di mappa 4 Anno impegno, 5 Numero impegno e 6 Numero Subimpegno
		if (impegnoFiltro.getAnno() != null && impegnoFiltro.getNumero() != null && numeroSubimpegnoFiltro != null) {
			if (impegno.getAnno().intValue() == impegnoFiltro.getAnno().intValue() && impegno.getNumero().intValue() == impegnoFiltro.getNumero().intValue()
					&& subimpegno.getNumero().intValue() == numeroSubimpegnoFiltro.intValue()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean checkSubimpegnoByFornitore(String codiceFornitoreFiltro, Subimpegno subimpegno) {
		// Occorre tenere eventuali subimpegni collegati al fornitore dell’ordine (Impegni.Subimpegni.CodiceSoggetto = codice fornitore ordine
		if (subimpegno.getFornitore() != null && subimpegno.getFornitore().getCodice() != null
				&& subimpegno.getFornitore().getCodice().equals(codiceFornitoreFiltro)) {
			return true;
		}
		return false;
	}

	private boolean checkImpegnoByAnnoNumero(Impegno impegnoFiltro, Impegno impegno) {
		// se l’attore ha citato un valore nei campi di mappa 4 Anno impegno e 5 Numero impegno
		if (impegnoFiltro.getAnno() != null && impegnoFiltro.getNumero() != null) {
			if (impegno.getAnno().intValue() == impegnoFiltro.getAnno().intValue() && impegno.getNumero().intValue() == impegnoFiltro.getNumero().intValue()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean checkImpegnoByCapitoloArticolo(Impegno impegnoFiltro, Impegno impegno) {
		// se l’attore ha citato un valore nei campi di mappa 2 Numero capitolo e 3 Numero articolo
		if (impegnoFiltro.getNumeroCapitolo() != null && impegnoFiltro.getNumeroArticolo() != null) {
			if (impegno.getNumeroCapitolo().intValue() == impegnoFiltro.getNumeroCapitolo().intValue()
					&& impegno.getNumeroArticolo().intValue() == impegnoFiltro.getNumeroArticolo().intValue()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean checkImpegnoFornitore(String codiceFornitoreFiltro, Impegno impegno) {
		// Occorre tenere eventuali impegni che non hanno subimpegni e che sono collegati al fornitore dell’ordine (Impegni.CodiceSoggetto = codice
		// fornitore ordine)
		// Occorre tenere tutti impegni che non hanno subimpegni collegati e non citano alcun fornitore nel campo Impegni.CodiceSoggetto
		if (impegno.getFornitore() == null || impegno.getFornitore().getCodice() == null || impegno.getFornitore().getCodice().equals(codiceFornitoreFiltro)) {
			return true;
		}
		return false;
	}

}
