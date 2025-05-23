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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class DeleteRigaEvasioneService extends BaseService<DeleteRigaEvasioneRequest, DeleteRigaEvasioneResponse> {

	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final DecodificaDad decodificaDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteRigaEvasioneService(ConfigurationHelper configurationHelper, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad, DecodificaDad decodificaDad, RigaOrdineDad rigaOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad) {
		super(configurationHelper);
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.testataEvasioneDad = testataEvasioneDad;
		this.decodificaDad = decodificaDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idRigaEvasione = request.getIdRigaEvasione();
		checkNotNull(idRigaEvasione, "idRigaEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		// Non deve essere possibile eliminare tutte le righe associate al destinatario
		final RigaEvasione rigaEvasione = rigaEvasioneDad.findOne(request.getIdRigaEvasione());
		final List<RigaEvasione> rigaEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(rigaEvasione.getDestinatarioEvasione().getId());
		if (rigaEvasiones.size() == 1) {
			response.addApiError(MsgCpassOrd.ORDORDE0115.getError());
			return;
		}

		// elimino tutti gli impegni collegati
		impegnoEvasioneDad.deleteImpegniEvasioneByRiga(request.getIdRigaEvasione());

		// elimino la riga
		rigaEvasioneDad.deleteRigaEvasione(request.getIdRigaEvasione());

		// start cpass-288
		final RigaOrdine rigaOrdine = aggiornaStatoRigaOrdine(rigaEvasione);
		aggiornaStatoDestinatario(rigaOrdine);

		// aggiorno il totale della testata evasione
		aggiornaTotaleTestata(rigaEvasione);
	}

	private RigaOrdine aggiornaStatoRigaOrdine(RigaEvasione rigaEvasione) {
		boolean statoDiversoDaAnnullata = false;
		final RigaOrdine rigaOrdine = rigaEvasione.getRigaOrdine();
		final List<RigaEvasione> listTmp = rigaEvasioneDad.findByIdRigaOrdine(rigaOrdine.getId());
		for(int i = 0; i < listTmp.size() && !statoDiversoDaAnnullata; i++) {
			statoDiversoDaAnnullata = !listTmp.get(i).getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());
		}
		String codiceStatoRigaOrdine = null;
		if(statoDiversoDaAnnullata) {
			codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
		}else {
			codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante();
		}
		final Stato statoRigaoOrdine = decodificaDad.getStato(codiceStatoRigaOrdine,ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
		rigaOrdine.setStato(statoRigaoOrdine);
		return rigaOrdineDad.updateRigaOrdine(rigaOrdine);
	}

	private void aggiornaStatoDestinatario(RigaOrdine rigaOrdine) {
		final Destinatario destinatario = rigaOrdine.getDestinatario();
		final List<RigaOrdine> righeOrdine = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
		final Iterator<RigaOrdine> righeOrdineIterator = righeOrdine.iterator();
		while(righeOrdineIterator.hasNext()) {
			final RigaOrdine ro = righeOrdineIterator.next();
			if(ro.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_ANNULLATA_DA_EVADERE.getCostante())){
				righeOrdineIterator.remove();
			}
		}

		boolean statoEVP = false;
		boolean statoDAE = false;
		boolean statoEVT = false;

		for(int i = 0; i < righeOrdine.size() && !statoEVP && !(statoDAE && statoEVT); i++) {
			if(righeOrdine.get(i).getStato().getCodice().equals(
					ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
				statoEVP = Boolean.TRUE;
			}
			if(righeOrdine.get(i).getStato().getCodice().equals(
					ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())){
				statoDAE = Boolean.TRUE;
			}
			if(righeOrdine.get(i).getStato().getCodice().equals(
					ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_TOTALMENTE.getCostante())){
				statoEVT = Boolean.TRUE;
			}
		}

		String codiceStatoDestinatarioOrdine = null;
		if(statoEVP || (statoDAE && statoEVT)) {
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante();
		}else if(statoDAE){
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante();
		}else if(statoEVT) {
			codiceStatoDestinatarioOrdine =  ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_TOTALMENTE.getCostante();
		}
		if(codiceStatoDestinatarioOrdine != null) {
			final Stato statoDestinatarioOrdine = decodificaDad.getStato(codiceStatoDestinatarioOrdine,ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante());
			destinatario.setStato(statoDestinatarioOrdine);
			destinatarioOrdineDad.updateDestinatario(destinatario);
		}
	}

	private TestataEvasione aggiornaTotaleTestata(RigaEvasione rigaEvasione) {
		final DestinatarioEvasione de = rigaEvasione.getDestinatarioEvasione();
		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneByDestinatario(de.getId());

		final BigDecimal totalePrec = testataEvasione.getTotaleConIva()== null  ? BigDecimal.ZERO :  testataEvasione.getTotaleConIva();
		final BigDecimal totaleRiga = rigaEvasione.getImportoTotale()== null  ? BigDecimal.ZERO :  rigaEvasione.getImportoTotale();
		testataEvasione.setTotaleConIva(totalePrec.subtract(totaleRiga));
		testataEvasioneDad.updateTestataEvasione(testataEvasione);


		return testataEvasione;
	}

}
