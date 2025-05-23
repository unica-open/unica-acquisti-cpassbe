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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityAnnullamentoEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class DeleteDestinatarioEvasioneService extends BaseService<DeleteDestinatarioEvasioneRequest, DeleteDestinatarioEvasioneResponse> {

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final DecodificaDad decodificaDad;
	private final RigaOrdineDad rigaOrdineDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param destinatarioEvasioneDad
	 * @param rigaEvasioneDad
	 * @param impegnoEvasioneDad
	 */
	public DeleteDestinatarioEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad,
			RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad, DestinatarioOrdineDad destinatarioOrdineDad, DecodificaDad decodificaDad ,RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.decodificaDad = decodificaDad;
		this.rigaOrdineDad = rigaOrdineDad;

	}

	@Override
	protected void checkServiceParams() {
		final UUID idDestinatarioEvasione = request.getIdDestinatarioEvasione();
		checkNotNull(idDestinatarioEvasione, "idDestinatarioEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		// Non deve essere possibile eliminare tutti i destinatari associati all’evasione
		final DestinatarioEvasione destinatarioEvasione = destinatarioEvasioneDad.findOne(request.getIdDestinatarioEvasione());
		final UUID idTestata = destinatarioEvasione.getTestataEvasione().getId();
		final List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(idTestata);

		checkBusinessCondition(destinatarioEvasiones.size() > 1, MsgCpassOrd.ORDORDE0116.getError());

		final List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(request.getIdDestinatarioEvasione());

		BigDecimal totaleConIvaDaSottrarreInTestata = BigDecimal.ZERO;
		// elimino tutte le righe collegate
		for (final RigaEvasione rigaEvasione : righeEvasiones) {

			// elimino tutti gli impegni e i subimpegni inclusi
			impegnoEvasioneDad.deleteImpegniEvasioneByRiga(rigaEvasione.getId());

			// e la riga
			rigaEvasioneDad.deleteRigaEvasione(rigaEvasione.getId());
			totaleConIvaDaSottrarreInTestata = totaleConIvaDaSottrarreInTestata.add(rigaEvasione.getImportoTotale());
		}


		// elimino il destinatario
		destinatarioEvasioneDad.deleteDestinatarioEvasione(request.getIdDestinatarioEvasione());

		//aggiornamento totali su Testata evasione Jira-260
		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(idTestata).orElseThrow(() -> new NotFoundException("testata evasione"));
		final BigDecimal totaleConIvaNew = testataEvasione.getTotaleConIva().subtract(totaleConIvaDaSottrarreInTestata);
		testataEvasione.setTotaleConIva(totaleConIvaNew);
		testataEvasioneDad.updateTestataEvasione(testataEvasione);

		final UUID idDestinatarioOrdine = destinatarioEvasione.getDestinatarioOrdine().getId();
		final Destinatario destinatarioOrdine = destinatarioOrdineDad.getDestinatario(idDestinatarioOrdine).orElseThrow(() -> new NotFoundException("destinatario ordine"));
		UtilityAnnullamentoEvasione.aggiornamentoStatoDestinatarioOrdine(decodificaDad, destinatarioEvasioneDad, destinatarioOrdineDad, destinatarioOrdine);


		String codiceStatoRigaOrdine = null;
		for(final RigaEvasione r : righeEvasiones) {
			boolean statoDiversoDaAnnullata = false;
			final RigaOrdine ro = r.getRigaOrdine();
			final List<RigaEvasione> listTmp = rigaEvasioneDad.findByIdRigaOrdine(ro.getId());
			for(int j = 0; j < listTmp.size() && !statoDiversoDaAnnullata; j++) {
				statoDiversoDaAnnullata = !listTmp.get(j).getStato().getDescrizione().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());
			}
			if(statoDiversoDaAnnullata) {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
			}else {
				codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante();
			}
			final Stato statoRigaoOrdine = decodificaDad.getStato(codiceStatoRigaOrdine,ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
			ro.setStato(statoRigaoOrdine);
			rigaOrdineDad.updateRigaOrdine(ro);
		}


	}
}

