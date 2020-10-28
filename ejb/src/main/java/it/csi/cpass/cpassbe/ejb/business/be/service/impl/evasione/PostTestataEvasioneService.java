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
import java.util.Calendar;
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
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostTestataEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostTestataEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
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
		checkNotNull(salvaEvasione, "salvaEvasione", true);
	}

	@Override
	protected void execute() {
		TestataEvasione testataEvasione = salvaEvasione.getTestataEvasione();

		// testata
		testataEvasione.setStato(isEntityPresent(
				() -> decodificaDad.getStato(ConstantsCPassStato.StatoEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoEnum.EVASIONE.getCostante()), "stato"));
		testataEvasione.setTipoEvasione(decodificaDad.getTipoEvasioneByCodice(ConstantsDecodifiche.TipoEvasioneEnum.MANUALE.getCodice()));

		BigDecimal totaleConIva = new BigDecimal(0);
		for (RigaEvasione rigaEvasione : salvaEvasione.getListEvasione()) {
			if (rigaEvasione.getImportoTotale().doubleValue() > 0) {
				totaleConIva = totaleConIva.add(rigaEvasione.getImportoTotale());
			}
		}
		testataEvasione.setTotaleConIva(totaleConIva);

		testataEvasione = testataEvasioneDad.saveTestataEvasione(testataEvasione);
		testataEvasioneDad.flush();

		Hashtable<UUID, Destinatario> hstDestinatarioOrdine = new Hashtable<UUID, Destinatario>();
		hstDestinatarioOrdine.clear();

		// righe evasione
		Hashtable<UUID, DestinatarioEvasione> hstDestinatarioEvasione = new Hashtable<>();
		hstDestinatarioEvasione.clear();

		for (RigaEvasione rigaEvasione : salvaEvasione.getListEvasione()) {
			if (rigaEvasione.getImportoTotale().doubleValue() > 0) {

				// riga ordine
				UUID idRigaOrdine = rigaEvasione.getRigaOrdine().getId();
				RigaOrdine rigaOrdine = rigaOrdineDad.findOne(idRigaOrdine);

				// destinatario
				DestinatarioEvasione destinatarioEvasione = hstDestinatarioEvasione.get(rigaOrdine.getDestinatario().getId());
				if (destinatarioEvasione == null) {
					destinatarioEvasione = new DestinatarioEvasione();
					destinatarioEvasione.setTestataEvasione(testataEvasione);
					destinatarioEvasione.setDestinatarioOrdine(rigaOrdine.getDestinatario());
					destinatarioEvasione.setStatoElOrdine(
							decodificaDad.getStatoElOrdine(ConstantsCPassStatoElOrdine.StatoEnum.DESTINATARIO_EVASIONE_DA_FATTURARE.getCostante(),
									ConstantsCPassStatoElOrdine.TipoEnum.DESTINATARIO_EVASIONE.getCostante()));

					// settore del destinatario
					Settore settoreDestinatario = rigaOrdine.getDestinatario().getSettore();
					destinatarioEvasione.setSettore(settoreDestinatario);
					destinatarioEvasione.setIndirizzo(settoreDestinatario.getIndirizzo());
					destinatarioEvasione.setNumCivico(settoreDestinatario.getNumCivico());
					destinatarioEvasione.setLocalita(settoreDestinatario.getLocalita());
					destinatarioEvasione.setCap(settoreDestinatario.getCap());
					destinatarioEvasione.setProvincia(settoreDestinatario.getProvincia());
					destinatarioEvasione.setContatto("");
					destinatarioEvasione.setEmail("");
					destinatarioEvasione.setTelefono(settoreDestinatario.getTelefono());

					destinatarioEvasione = destinatarioEvasioneDad.saveDestinatarioEvasione(destinatarioEvasione);
					destinatarioEvasioneDad.flush();
					hstDestinatarioEvasione.put(rigaOrdine.getDestinatario().getId(), destinatarioEvasione);
				}

				// riga evasione
				// rigaEvasione.setImportoTotale(rigaEvasione.getImportoTotale()); arriva da FE
				rigaEvasione.setPrezzoUnitario(rigaOrdine.getPrezzoUnitario());
				rigaEvasione.setDestinatarioEvasione(destinatarioEvasione);
				rigaEvasione.setAliquoteIva(rigaOrdine.getAliquoteIva());
				rigaEvasione.setOggettiSpesa(rigaOrdine.getOds());
				rigaEvasione.setStatoElOrdine(decodificaDad.getStatoElOrdine(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_EVASIONE_DA_FATTURARE.getCostante(),ConstantsCPassStatoElOrdine.TipoEnum.RIGA_EVASIONE.getCostante()));
				rigaEvasione.setListinoFornitore(rigaOrdine.getListinoFornitore());

				rigaEvasione = rigaEvasioneDad.saveRigaEvasione(rigaEvasione);
				rigaEvasioneDad.flush();

				// impegni
				Settore settore = settoreDad.findById(testataEvasione.getSettore().getId());
				UUID enteId = settore.getEnte().getId();

				// List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
				List<ImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegnoOrdineByRiga(rigaOrdine.getId());
				if (impegnoOrdines != null && impegnoOrdines.size() == 1) {
					ImpegnoOrdine impegnoOrdine = impegnoOrdines.get(0);

					// 2.9 Verifica impegni/subimpegni ribaltati
					Impegno impegno = UtilityImpegni.verificaImpegniRibaltati(impegnoDad, enteId, impegnoOrdine);
					if (impegno != null) {

						ImpegnoEvasione impegnoEvasione = new ImpegnoEvasione();
						impegnoEvasione.setRigaEvasione(rigaEvasione);
						impegnoEvasione.setImpegno(impegno);
						impegnoEvasione.setImpegnoOrdine(impegnoOrdine);

						Calendar calendar = Calendar.getInstance();
						int yearCurrent = calendar.get(Calendar.YEAR);
						if (impegnoOrdine.getImpegnoAnnoEsercizio() < yearCurrent) {
							impegnoEvasione.setImpegnoAnnoEsercizio(impegnoOrdine.getImpegno().getAnnoEsercizio());
						} else {
							impegnoEvasione.setImpegnoAnnoEsercizio(impegnoOrdine.getImpegnoAnnoEsercizio());
						}
						impegnoEvasione.setImpegnoAnno(impegnoOrdine.getImpegnoAnno());
						impegnoEvasione.setImpegnoNumero(impegnoOrdine.getImpegnoNumero());

						impegnoEvasione.setImportoRipartito(rigaEvasione.getImportoTotale());
						impegnoEvasione.setImportoLiquidato(new BigDecimal(0));
						impegnoEvasione.setImportoSospeso(new BigDecimal(0));

						List<SubimpegnoOrdine> subimpegnoOrdines = impegnoDad.getSubimpegnoOrdineByImpegnoOrdineId(impegnoOrdine.getId());
						if (subimpegnoOrdines == null) {
							impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
							impegnoEvasioneDad.flush();

						} else if (subimpegnoOrdines.size() == 1) {
							SubimpegnoOrdine subimpegnoOrdine = subimpegnoOrdines.get(0);

							// 2.9 Verifica impegni/subimpegni ribaltati
							Subimpegno subimpegno = UtilityImpegni.verificaSubimpegniRibaltati(impegnoDad, enteId, subimpegnoOrdine);
							if (subimpegno != null) {
								impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
								impegnoEvasioneDad.flush();

								SubimpegnoEvasione subimpegnoEvasione = new SubimpegnoEvasione();
								subimpegnoEvasione.setImpegnoEvasione(impegnoEvasione);
								subimpegnoEvasione.setImpegnoAnnoEsercizio(impegnoEvasione.getImpegnoAnnoEsercizio());
								subimpegnoEvasione.setImpegnoAnno(impegnoEvasione.getImpegnoAnno());
								subimpegnoEvasione.setImpegnoNumero(impegnoEvasione.getImpegnoNumero());

								subimpegnoEvasione.setSubimpegnoAnno(subimpegnoOrdine.getSubimpegnoAnno());
								subimpegnoEvasione.setSubimpegnoNumero(subimpegnoOrdine.getSubimpegnoNumero());

								subimpegnoEvasione.setImportoRipartito(rigaEvasione.getImportoTotale());
								subimpegnoEvasione.setImportoSospeso(new BigDecimal(0));
								subimpegnoEvasione.setImportoLiquidato(new BigDecimal(0));
								subimpegnoEvasione.setSubimpegno(subimpegno);

								subimpegnoEvasione.setSubimpegnoOrdine(subimpegnoOrdine);

								subimpegnoEvasione = subimpegnoEvasioneDad.insert(subimpegnoEvasione);
								subimpegnoEvasioneDad.flush();
							}
						}
					}
				}

				// Determinazione dello stato da assegnare alla riga d’ordine
				String codiceStatoRigaOrdine = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
				if (calcoloTotaleEvadibileRigaOrdine(rigaOrdine).compareTo(new BigDecimal(0)) == 0) {
					codiceStatoRigaOrdine = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante();
				}

				StatoElOrdine statoElOrdineRigaOrdine = decodificaDad.getStatoElOrdine(codiceStatoRigaOrdine,ConstantsCPassStatoElOrdine.TipoEnum.RIGA_ORDINE.getCostante());
				rigaOrdine.setStatoElOrdine(statoElOrdineRigaOrdine);
				rigaOrdine = rigaOrdineDad.updateRigaOrdine(rigaOrdine);

				Destinatario destinatarioOrdine = rigaOrdine.getDestinatario();
				hstDestinatarioOrdine.put(destinatarioOrdine.getId(), destinatarioOrdine);
			}
		}

		// Determinazione dello stato da assegnare al destinatario d’ordine
		Enumeration<UUID> enumDestinatarioOrdine = hstDestinatarioOrdine.keys();
		while (enumDestinatarioOrdine.hasMoreElements()) {
			UUID idDestinatario = enumDestinatarioOrdine.nextElement();
			Destinatario destinatarioOrdine = hstDestinatarioOrdine.get(idDestinatario);

			boolean bEvasoTotalmente = true;
			List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(idDestinatario);
			for (RigaOrdine rigaOrdine : rigaOrdines) {
				if (rigaOrdine.getStatoElOrdine() == null || !rigaOrdine.getStatoElOrdine().getCodice()
						.equals(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante())) {
					bEvasoTotalmente = false;
				}
			}

			String codiceStatoDestinatarioOrdine = ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante();
			if (bEvasoTotalmente) {
				codiceStatoDestinatarioOrdine = ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_EVASO_TOTALMENTE.getCostante();
			}

			StatoElOrdine statoElOrdineDestinatarioOrdine = decodificaDad.getStatoElOrdine(codiceStatoDestinatarioOrdine,
					ConstantsCPassStatoElOrdine.TipoEnum.DEST_ORDINE.getCostante());
			destinatarioOrdine.setStatoElOrdine(statoElOrdineDestinatarioOrdine);
			destinatarioOrdine = destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
		}

		response.setTestataEvasione(testataEvasione);
	}

	private BigDecimal calcoloTotaleEvadibileRigaOrdine(RigaOrdine rigaOrdine) {
//		TOTALE EVADIBILE = CPASS_T_ORD_RIGA_ORDINE.importo_totale – 
//				∑(CPASS_T_ORD_EVASIONE_RIGA.importo_totale per:
//				•	CPASS_T_ORD_RIGA_ EVASIONE.riga_ordine_id = CPASS_T_ORD_RIGA_ORDINE.riga_ordine_id
//				•	CPASS_T_ORD_RIGA_ EVASIONE.stato_id <> CPASS_D_STATO_EL_ORDINE.id corrispondente a ‘DISABBINATA’ 

		BigDecimal importoTotaleRigheEvasione = rigaEvasioneDad.calcolaTotale(rigaOrdine.getId());
		return rigaOrdine.getImportoTotale().subtract(importoTotaleRigheEvasione);
	}

}
