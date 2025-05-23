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
package it.csi.cpass.cpassbe.ejb.business.be.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdTipoEvasione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;

public class UtilityEvasione {

	//private static final LogUtil log = new LogUtil(UtilityEvasione.class);

	public static TestataEvasione creaTestataEvasioneDaDocumentoTrasporto(
			DecodificaDad decodificaDad,
			DocumentoTrasporto documentoTrasporto,
			Settore settore,
			Utente utenteConnesso) {

		final TestataEvasione testataEvasione = new TestataEvasione();

		final Fornitore fornitore = documentoTrasporto.getFornitore();
		final String descrizione = "EVASIONE DDT N." + documentoTrasporto.getDespatchAdviceId();

		final Date dataConsegna = documentoTrasporto.getDataConsegna()!=null ? DateUtility.stringToDate(documentoTrasporto.getDataConsegna()) : new Date();

		testataEvasione.setFornitore(fornitore);
		testataEvasione.setSettore(settore);

		final Optional<Stato> statoOpt = decodificaDad.getStatoOpt(StatoEvasioneEnum.BOZZA.getCostante(),ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante());
		if(statoOpt.isPresent()) {
			testataEvasione.setStato(statoOpt.get());
		}
		/*
		testataEvasione.setStato(isStatoPresent(
			StatoEvasioneEnum.BOZZA.getCostante(),
			ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante(),
			decodificaDad)
		);
		 */
		testataEvasione.setTipoEvasione(
				decodificaDad.getTipoEvasioneByCodice(
						ConstantsCPassOrdTipoEvasione.TipoEvasione.RICEVUTA_NSO.getCostante()));

		// manca il totale, da calcolarsi come sommatoria degli importi delle singole righe

		testataEvasione.setUtenteCompilatore(utenteConnesso);
		testataEvasione.setDescrizione(descrizione);
		testataEvasione.setDataConsegna(dataConsegna);
		testataEvasione.setDocumentoTrasportoId(documentoTrasporto.getId());
		testataEvasione.setNote(documentoTrasporto.getNote());

		return testataEvasione;
	}



	public static DestinatarioEvasione creaDestinatarioEvasione(TestataEvasione testataEvasione, RigaOrdine rigaOrdine, DecodificaDad decodificaDad) {
		final DestinatarioEvasione destinatarioEvasione = new DestinatarioEvasione();
		destinatarioEvasione.setTestataEvasione(testataEvasione);
		destinatarioEvasione.setDestinatarioOrdine(rigaOrdine.getDestinatario());
		destinatarioEvasione.setStato(
				decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_DA_FATTURARE.getCostante(),
						ConstantsCPassStato.TipoStatoEnum.DESTINATARIO_EVASIONE.getCostante()));

		// settore del destinatario
		final Settore settoreDestinatario = rigaOrdine.getDestinatario().getSettore();
		destinatarioEvasione.setSettore(settoreDestinatario);
		destinatarioEvasione.setIndirizzo(rigaOrdine.getDestinatario().getIndirizzo());
		destinatarioEvasione.setNumCivico(rigaOrdine.getDestinatario().getNumCivico());
		destinatarioEvasione.setLocalita(rigaOrdine.getDestinatario().getLocalita());
		destinatarioEvasione.setCap(rigaOrdine.getDestinatario().getCap());
		destinatarioEvasione.setProvincia(rigaOrdine.getDestinatario().getProvincia());
		destinatarioEvasione.setContatto(rigaOrdine.getDestinatario().getContatto());
		destinatarioEvasione.setEmail(rigaOrdine.getDestinatario().getEmail());
		destinatarioEvasione.setTelefono(rigaOrdine.getDestinatario().getTelefono());

		return destinatarioEvasione;
	}

	public static RigaEvasione creaRigaEvasioneDaDocumentoTrasporto(DocumentoTrasportoRiga documentoTrasportoRiga,
			RigaOrdine rigaOrdine,
			DestinatarioEvasione destinatarioEvasione,
			DecodificaDad decodificaDad) {
		final RigaEvasione rigaEvasione = new RigaEvasione();

		// calcolo importo
		BigDecimal importo = BigDecimal.ZERO;
		if (documentoTrasportoRiga.getNoteFornitore() != null &&
				documentoTrasportoRiga.getNoteFornitore().toLowerCase().indexOf("omaggio") < 0) {
			// CPASS-382
			// importo = rigaOrdine.getPrezzoUnitario().multiply(documentoTrasportoRiga.getQtaEvasa());
			if (rigaOrdine.getImportoTotale() != null && rigaOrdine.getQuantita() != null) {
				final BigDecimal prezzoUnitario = rigaOrdine.getImportoTotale().divide(rigaOrdine.getQuantita());
				// uso il setScale sul multiply e non sul divide per fare la stessa cosa che fa il FE
				importo = prezzoUnitario.multiply(documentoTrasportoRiga.getQtaEvasa()).setScale(2, RoundingMode.HALF_UP);
			}
		}

		rigaEvasione.setQuantitaEvasa(documentoTrasportoRiga.getQtaEvasa());
		rigaEvasione.setImportoTotale(importo);
		rigaEvasione.setPrezzoUnitario(rigaOrdine.getPrezzoUnitario());
		rigaEvasione.setDestinatarioEvasione(destinatarioEvasione);
		rigaEvasione.setRigaOrdine(rigaOrdine);
		rigaEvasione.setAliquoteIva(rigaOrdine.getAliquoteIva());
		rigaEvasione.setOggettiSpesa(rigaOrdine.getOds());
		rigaEvasione.setListinoFornitore(rigaOrdine.getListinoFornitore());
		rigaEvasione.setStato(decodificaDad.getStato(
				ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_DA_FATTURARE.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante())
				);
		rigaEvasione.setNote(documentoTrasportoRiga.getNoteFornitore());

		return rigaEvasione;
	}

	public static ImpegnoEvasione creaImpegnoEvasione(RigaEvasione rigaEvasione, Impegno impegno, ImpegnoOrdine impegnoOrdine) {
		final ImpegnoEvasione impegnoEvasione = new ImpegnoEvasione();

		impegnoEvasione.setRigaEvasione(rigaEvasione);
		impegnoEvasione.setImpegno(impegno);
		impegnoEvasione.setImpegnoOrdine(impegnoOrdine);

		final Calendar calendar = Calendar.getInstance();
		final int yearCurrent = calendar.get(Calendar.YEAR);
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

		return impegnoEvasione;
	}

	public static SubimpegnoEvasione creaSubimpegnoEvasione(ImpegnoEvasione impegnoEvasione,
			SubimpegnoOrdine subimpegnoOrdine,
			Subimpegno subimpegno,
			RigaEvasione rigaEvasione) {

		final SubimpegnoEvasione subimpegnoEvasione = new SubimpegnoEvasione();

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

		return subimpegnoEvasione;
	}

	public static BigDecimal calcoloTotaleEvadibileRigaOrdine(RigaOrdine rigaOrdine, RigaEvasioneDad rigaEvasioneDad) {
		//		TOTALE EVADIBILE = CPASS_T_ORD_RIGA_ORDINE.importo_totale –
		//				∑(CPASS_T_ORD_EVASIONE_RIGA.importo_totale per:
		//					CPASS_T_ORD_RIGA_ EVASIONE.riga_ordine_id = CPASS_T_ORD_RIGA_ORDINE.riga_ordine_id
		//					CPASS_T_ORD_RIGA_ EVASIONE.stato_id <> CPASS_D_STATO.id corrispondente a DISABBINATA

		final BigDecimal importoTotaleRigheEvasione = rigaEvasioneDad.calcolaTotale(rigaOrdine.getId());
		return rigaOrdine.getImportoTotale().subtract(importoTotaleRigheEvasione);
	}

	/**
	 * usato solo nel caso di FORNITURE, non di servizi
	 * @param rigaOrdine
	 * @return
	 */
	public static BigDecimal calcoloQuantitaEvadibileRigaOrdine(RigaOrdine rigaOrdine, RigaEvasioneDad rigaEvasioneDad) {
		final TestataOrdine testataOrdine = rigaOrdine.getDestinatario().getTestataOrdine();

		if (testataOrdine.getTipoAcquisto().getCodice().equals("S")) {
			return null;
		}

		final BigDecimal quantitaOrdinata = rigaOrdine.getQuantita();
		final BigDecimal quantitaEvasa = rigaEvasioneDad.calcolaQuantitaEvasa(rigaOrdine.getId());

		return quantitaOrdinata.subtract(quantitaEvasa);
	}

	public static Stato getStatoDestinatarioOrdine(List<RigaOrdine> rigaOrdineList, DecodificaDad decodificaDad) {
		boolean bEvasoTotalmente = true;
		boolean bEvasoParzialmenteConSconto = false;
		for (final RigaOrdine rigaOrdine : rigaOrdineList) {
			if (rigaOrdine.getStato() == null || !rigaOrdine.getStato().getCodice()
					.equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante())) {
				bEvasoTotalmente = false;
			}
			if(rigaOrdine.getStato() == null || rigaOrdine.getStato().getCodice()
					.equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE_CON_SCONTO.getCostante())) {
				bEvasoParzialmenteConSconto = true;
			}
		}
		String codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante();
		if (bEvasoTotalmente) {
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_TOTALMENTE.getCostante();
		}else if (bEvasoParzialmenteConSconto) {
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE_CON_SCONTO.getCostante();
		}
		final Stato statoDestinatarioOrdine = decodificaDad.getStato(codiceStatoDestinatarioOrdine,ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante());
		return statoDestinatarioOrdine;
	}

	public static Stato getStatoRigaOrdine(RigaOrdine rigaOrdine, RigaEvasioneDad rigaEvasioneDad, DecodificaDad decodificaDad) {
		Stato stato;
		final SettoreInterventi tipoAcquisto = rigaOrdine.getDestinatario().getTestataOrdine().getTipoAcquisto();
		String codiceStatoRigaOrdine = null;

		//3.19 Calcolo totale evadibile su una riga d'ordine
		final BigDecimal totaleEvadibile = UtilityEvasione.calcoloTotaleEvadibileRigaOrdine(rigaOrdine, rigaEvasioneDad);

		if (tipoAcquisto.getCodice().equals(ConstantsDecodifiche.SettoreInterventiEnum.SERVIZI.getCodice())) {
			// caso servizi
			codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
			if (totaleEvadibile.compareTo(new BigDecimal(0)) == 0) {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante();
			}
		} else if (tipoAcquisto.getCodice().equals(ConstantsDecodifiche.SettoreInterventiEnum.FORNITURE.getCodice())) {
			// caso forniture
			//3.25 Calcolo quantita evadibile
			final BigDecimal quantitaEvadibile = UtilityEvasione.calcoloQuantitaEvadibileRigaOrdine(rigaOrdine, rigaEvasioneDad);

			if(quantitaEvadibile != null && totaleEvadibile.compareTo(new BigDecimal(0)) == 0 && quantitaEvadibile.compareTo(new BigDecimal(0)) == 0) {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante();

			}else if(quantitaEvadibile != null && totaleEvadibile.compareTo(new BigDecimal(0)) > 0 && quantitaEvadibile.compareTo(new BigDecimal(0)) == 0) {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE_CON_SCONTO.getCostante();
			}
			else if(quantitaEvadibile != null && totaleEvadibile.compareTo(new BigDecimal(0)) > 0 && quantitaEvadibile.compareTo(new BigDecimal(0)) > 0) {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
			}
		}

		stato = decodificaDad.getStato(codiceStatoRigaOrdine, ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());

		return stato;
	}
}
