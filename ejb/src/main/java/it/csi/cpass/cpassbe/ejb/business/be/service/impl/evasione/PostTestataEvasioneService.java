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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostTestataEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostTestataEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Saves an TestataEvasione
 */
public class PostTestataEvasioneService extends BaseService<PostTestataEvasioneRequest, PostTestataEvasioneResponse> {

	private final TestataEvasioneDad testataEvasioneDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;

	private final DecodificaDad decodificaDad;
	private final SettoreDad settoreDad;
	private SalvaEvasione salvaEvasione;


	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 * @param subimpegnoEvasioneDad
	 * @param destinatarioOrdineDad
	 * @param rigaOrdineDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PostTestataEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad,
			DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			SubimpegnoEvasioneDad subimpegnoEvasioneDad, DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad,
			DecodificaDad decodificaDad, SettoreDad settoreDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;

		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;

		this.impegnoDad = impegnoDad;

		this.decodificaDad = decodificaDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		salvaEvasione = request.getSalvaEvasione();
		checkNotNull(salvaEvasione, "salvaEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {

		TestataEvasione testataEvasione = salvaEvasione.getTestataEvasione();
		// testata
		testataEvasione.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoEvasioneEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante()), "stato"));
		testataEvasione.setTipoEvasione(decodificaDad.getTipoEvasioneByCodice(ConstantsDecodifiche.TipoEvasioneEnum.MANUALE.getCodice()));

		BigDecimal totaleConIva = new BigDecimal(0);
		for (final RigaEvasione rigaEvasione : salvaEvasione.getListEvasione()) {
			if (rigaEvasione.getImportoTotale().doubleValue() > 0) {
				totaleConIva = totaleConIva.add(rigaEvasione.getImportoTotale());
			}
		}

		testataEvasione.setTotaleConIva(totaleConIva);
		testataEvasione = testataEvasioneDad.saveTestataEvasione(testataEvasione);
		final Hashtable<UUID, Destinatario> hstDestinatarioOrdine = new Hashtable<>();
		hstDestinatarioOrdine.clear();

		// righe evasione
		final Hashtable<UUID, DestinatarioEvasione> hstDestinatarioEvasione = new Hashtable<>();
		hstDestinatarioEvasione.clear();

		UUID idRigaOrdine = null;
		for (RigaEvasione rigaEvasione : salvaEvasione.getListEvasione()) {
			if ((rigaEvasione.getQuantitaEvasa() == null && rigaEvasione.getImportoTotale().doubleValue() > 0 ) || (rigaEvasione.getQuantitaEvasa() != null && rigaEvasione.getQuantitaEvasa().doubleValue() > 0 )) {

				// riga ordine
				idRigaOrdine = rigaEvasione.getRigaOrdine().getId();
				RigaOrdine rigaOrdine = rigaOrdineDad.findOne(idRigaOrdine);

				// destinatario
				DestinatarioEvasione destinatarioEvasione = hstDestinatarioEvasione.get(rigaOrdine.getDestinatario().getId());
				if (destinatarioEvasione == null) {
					destinatarioEvasione = UtilityEvasione.creaDestinatarioEvasione(testataEvasione, rigaOrdine, decodificaDad);

					destinatarioEvasione = destinatarioEvasioneDad.saveDestinatarioEvasione(destinatarioEvasione);
					hstDestinatarioEvasione.put(rigaOrdine.getDestinatario().getId(), destinatarioEvasione);
				}

				// riga evasione
				// rigaEvasione.setImportoTotale(rigaEvasione.getImportoTotale()); arriva da FE
				rigaEvasione.setPrezzoUnitario(rigaOrdine.getPrezzoUnitario());
				rigaEvasione.setDestinatarioEvasione(destinatarioEvasione);
				rigaEvasione.setAliquoteIva(rigaOrdine.getAliquoteIva());
				rigaEvasione.setOggettiSpesa(rigaOrdine.getOds());
				rigaEvasione.setStato(decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_DA_FATTURARE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante()));
				rigaEvasione.setListinoFornitore(rigaOrdine.getListinoFornitore());
				rigaEvasione = rigaEvasioneDad.saveRigaEvasione(rigaEvasione);

				// impegni
				final Settore settore = settoreDad.findById(testataEvasione.getSettore().getId());
				final UUID enteId = settore.getEnte().getId();

				// List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
				final List<ImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegnoOrdineByRiga(rigaOrdine.getId());
				if (impegnoOrdines != null && impegnoOrdines.size() == 1) {
					final ImpegnoOrdine impegnoOrdine = impegnoOrdines.get(0);

					// 2.9 Verifica impegni/subimpegni ribaltati
					final Impegno impegno = UtilityImpegni.verificaImpegniRibaltati(impegnoDad, enteId, impegnoOrdine);
					if (impegno != null) {

						ImpegnoEvasione impegnoEvasione = UtilityEvasione.creaImpegnoEvasione(rigaEvasione, impegno, impegnoOrdine);

						final List<SubimpegnoOrdine> subimpegnoOrdines = impegnoDad.getSubimpegnoOrdineByImpegnoOrdineId(impegnoOrdine.getId());
						if (subimpegnoOrdines == null) {
							impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
						} else if (subimpegnoOrdines.size() == 1) {
							final SubimpegnoOrdine subimpegnoOrdine = subimpegnoOrdines.get(0);

							// 2.9 Verifica impegni/subimpegni ribaltati
							final Subimpegno subimpegno = UtilityImpegni.verificaSubimpegniRibaltati(impegnoDad, enteId, subimpegnoOrdine);
							if (subimpegno != null) {
								impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);

								SubimpegnoEvasione subimpegnoEvasione = UtilityEvasione.creaSubimpegnoEvasione(impegnoEvasione, subimpegnoOrdine, subimpegno, rigaEvasione);
								subimpegnoEvasione = subimpegnoEvasioneDad.insert(subimpegnoEvasione);
							}
						}
					}
				}

				// Determinazione dello stato da assegnare alla riga d’ordine
				final Stato statoRigaOrdine =UtilityEvasione.getStatoRigaOrdine(rigaOrdine, rigaEvasioneDad, decodificaDad);
				rigaOrdine.setStato(statoRigaOrdine);
				rigaOrdine = rigaOrdineDad.updateRigaOrdine(rigaOrdine);

				final Destinatario destinatarioOrdine = rigaOrdine.getDestinatario();
				hstDestinatarioOrdine.put(destinatarioOrdine.getId(), destinatarioOrdine);
			}
		}

		// Determinazione dello stato da assegnare al destinatario d’ordine
		final Enumeration<UUID> enumDestinatarioOrdine = hstDestinatarioOrdine.keys();
		while (enumDestinatarioOrdine.hasMoreElements()) {
			final UUID idDestinatario = enumDestinatarioOrdine.nextElement();
			Destinatario destinatarioOrdine = hstDestinatarioOrdine.get(idDestinatario);
			final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(idDestinatario);
			final Stato statoDestinatarioOrdine = UtilityEvasione.getStatoDestinatarioOrdine(rigaOrdines, decodificaDad);
			destinatarioOrdine.setStato(statoDestinatarioOrdine);
			destinatarioOrdine = destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
		}

		// 	CPASS-584
		//
		if(idRigaOrdine !=null) {
			//UUID testataOrdineId = rigaOrdineDad.findOne(idRigaOrdine).getDestinatario().getTestataOrdine().getId();
			//TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(testataOrdineId).get();
			final Ufficio ufficio = rigaOrdineDad.findOne(idRigaOrdine).getDestinatario().getTestataOrdine().getUfficio();
			testataEvasione.setUfficio(ufficio);
			testataEvasione = testataEvasioneDad.updateTestataEvasione(testataEvasione);
		}
		response.setTestataEvasione(testataEvasione);
	}

}
