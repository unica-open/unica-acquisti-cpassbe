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
import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Saves an RigaEvasione
 */
public class PutRigaEvasioneService extends BaseService<PutRigaEvasioneRequest, PutRigaEvasioneResponse> {

	private final RigaEvasioneDad rigaEvasioneDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final TestataEvasioneDad testataEvasioneDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param RigaEvasioneDad
	 */
	public PutRigaEvasioneService(ConfigurationHelper configurationHelper, RigaEvasioneDad rigaEvasioneDad, RigaOrdineDad rigaOrdineDad, DecodificaDad decodificaDad, DestinatarioOrdineDad destinatarioOrdineDad, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper);
		this.rigaEvasioneDad 		= rigaEvasioneDad;
		this.rigaOrdineDad   		= rigaOrdineDad;
		this.decodificaDad   		= decodificaDad;
		this.destinatarioOrdineDad 	= destinatarioOrdineDad;
		this.testataEvasioneDad 	= testataEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkModel(request.getRigaEvasione(), "RigaEvasione", true);
	}

	@Override
	protected void execute() {
		RigaEvasione rigaEvasione = request.getRigaEvasione();

		RigaOrdine rordine         = rigaOrdineDad.findOne(rigaEvasione.getRigaOrdine().getId());
		if(rordine.getDestinatario().getTestataOrdine().getTipoAcquisto().getCodice().equals(ConstantsDecodifiche.SettoreInterventiEnum.FORNITURE.getCodice())
				&& request.getQtaDaEvadere().intValue() != -1
				&& request.getTotaliCoerenti().toUpperCase().equals("NULL") 	 ) {

			final BigDecimal totaleEvasione  = rigaEvasione.getImportoTotale();
			final Integer quantitaDaEvadere 	= request.getQtaDaEvadere();

			final BigDecimal netto       	= rigaEvasione.getPrezzoUnitario().multiply(rigaEvasione.getQuantitaEvasa()); // sulla suingola riga
			final BigDecimal divisor100  	= new BigDecimal(100);

			final BigDecimal perc_sconto  = rordine.getPercentualeSconto() == null ? BigDecimal.ZERO :rordine.getPercentualeSconto();
			final BigDecimal perc_sconto2 = rordine.getPercentualeSconto2()== null ? BigDecimal.ZERO :rordine.getPercentualeSconto2();

			final BigDecimal sconto_1 		= (rigaEvasione.getPrezzoUnitario().multiply(rigaEvasione.getQuantitaEvasa())).multiply((perc_sconto).divide(divisor100));
			final BigDecimal sconto_2 		= (rigaEvasione.getPrezzoUnitario().multiply(rigaEvasione.getQuantitaEvasa())).multiply((perc_sconto2).divide(divisor100 ));
			final BigDecimal totale_netto 	= (netto.subtract(sconto_1)).subtract(sconto_2);
			final BigDecimal iva 			= totale_netto.multiply((rordine.getAliquoteIva().getPercentuale()).divide(divisor100));
			final BigDecimal totaleCalcolato = totale_netto.add(iva);

			if(totaleCalcolato.compareTo(totaleEvasione)>0) {
				if(quantitaDaEvadere.equals(0)) {
					checkBusinessCondition(false, MsgCpassOrd.ORDORDA0159.getError());
				}else {
					checkBusinessCondition(false, MsgCpassOrd.ORDORDA0160.getError());
				}
			}

			if(totaleCalcolato.compareTo(totaleEvasione) < 0) {
				checkBusinessCondition(false, MsgCpassOrd.ORDORDE0158.getError());
			}

		}

		rigaEvasione = rigaEvasioneDad.updateRigaEvasione(rigaEvasione);
		final BigDecimal totaleEvaso = rigaEvasioneDad.calcolaTotale(rigaEvasione.getRigaOrdine().getId());
		rigaEvasione.setTotaleEvaso(totaleEvaso);


		//////////////////////////////
		// Determinazione dello stato da assegnare alla riga d’ordine
		final Stato statoRigaOrdine =UtilityEvasione.getStatoRigaOrdine(rordine, rigaEvasioneDad, decodificaDad);
		rordine.setStato(statoRigaOrdine);
		rordine = rigaOrdineDad.updateRigaOrdine(rordine);

		Destinatario destinatarioOrdine = rordine.getDestinatario();
		destinatarioOrdine = destinatarioOrdineDad.getDestinatario(destinatarioOrdine.getId()).orElseThrow(() -> new NotFoundException("destinatario ordine"));
		if(request.getTotaliCoerenti()!=null && request.getTotaliCoerenti().toUpperCase().equals("TRUE")) {
			final Stato stato = decodificaDad.getStato( ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_CON_SCONTO.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
			rordine.setStato(stato);
			rigaOrdineDad.updateRigaOrdine(rordine);
		}
		// determino lo stato del destinatario Ordine
		final List<RigaOrdine> listaordine = new ArrayList<>();
		listaordine.add(rordine);
		final Stato statoDestinatarioOrdine = UtilityEvasione.getStatoDestinatarioOrdine(listaordine, decodificaDad);
		destinatarioOrdine.setStato(statoDestinatarioOrdine);
		destinatarioOrdine = destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
		////////////////////////////////////////////
		rordine.setDestinatario(destinatarioOrdine);
		rigaEvasione.setRigaOrdine(rordine);

		final TestataEvasione testataEvasione = testataEvasioneDad.aggiornaTotaleEvasione(rigaEvasione.getDestinatarioEvasione().getTestataEvasione());
		rigaEvasione.getDestinatarioEvasione().setTestataEvasione(testataEvasione);

		//task-391, controllo che il totale sull'evasione non superi il totale evadibile
		final BigDecimal totEvasoEsclusaEvCorrente = rigaEvasioneDad.calcolaTotaleEsclusaEvasioneCorrente(rigaEvasione.getRigaOrdine().getId(), rigaEvasione.getId());
		final BigDecimal checkTotMaggioreTotDaEvadere = rigaEvasione.getRigaOrdine().getImportoTotale()
                .subtract(rigaEvasione.getImportoTotale()).subtract(totEvasoEsclusaEvCorrente);
		if(checkTotMaggioreTotDaEvadere.compareTo(BigDecimal.ZERO) < 0) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0184.getError());
		}

		response.setRigaEvasione(rigaEvasione);


	}

}
