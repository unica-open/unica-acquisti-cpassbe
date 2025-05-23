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
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves a list of riga ordine
 */
public class GetRigheOrdineDaEvadereByOrdineAnnoNumeroService
extends BaseTestataEvasioneService<GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest, GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse> {

	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final DecodificaDad decodificaDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param rigaOrdineDad
	 */
	public GetRigheOrdineDaEvadereByOrdineAnnoNumeroService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, DecodificaDad decodificaDad,
			RigaEvasioneDad rigaEvasioneDad, UtenteDad utenteDad) {
		super(configurationHelper, null);
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.decodificaDad = decodificaDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Optional<TestataOrdine> testataOrdineOpt = testataOrdineDad.getByAnnoENumeroOpt(request.getAnno(), request.getNumero(), settoreCorrente.getEnte().getId());
		checkBusinessCondition(testataOrdineOpt.isPresent(), MsgCpassOrd.ORDORDE0043.getError());
		//final TestataOrdine testataOrdine = testataOrdineOpt.get();
		final TestataOrdine testataOrdine = isEntityPresent(() -> testataOrdineOpt,"Testata Ordine");
		// fix CPASS-186 - ORD19 Ricercare ordini da evadere: scenario alternativo (unico ordine)
		final boolean bVisibilitàDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad, testataOrdine);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0044.getError());
		}

		// 2.10.4 Criteri di evadibilità degli ordini
		// punto 1 - ordine autorizzato
		if (!testataOrdine.getStato().getCodice().equals(StatoOrdineEnum.AUTORIZZATO.getCostante())) {
			response.addApiError(MsgCpassOrd.ORDORDE0075.getError());
		}

		// punto 2 - destinatari trasmessi NSO
		boolean bCheckDestinatariTrasmessiNSO = false;
		// 2. Deve avere almeno un destinatario con CPASS_T_ORD_DESTINATARIO_ORDINE.stato_nso_id corrispondente allo stato “OK”
		final List<Destinatario> destinatarios = destinatarioOrdineDad.findByOrdine(testataOrdine.getId());
		for (final Destinatario destinatario : destinatarios) {
			if (destinatario.getStatoNso() != null && destinatario.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante())) {
				bCheckDestinatariTrasmessiNSO = Boolean.TRUE;
			}
		}
		// tipo_ordine_id corrisponde ad un tipo in CPASS_D_ORD_TIPO_ORDINE che ha il flag da_trasm_nso = false
		if (!bCheckDestinatariTrasmessiNSO) {
			final List<TipoOrdine> tipoOrdines = decodificaDad.getTipoOrdines();
			for (final TipoOrdine tipoOrdine : tipoOrdines) {
				if (tipoOrdine.getFlagTrasmNso() != null && !tipoOrdine.getFlagTrasmNso()) {
					if (testataOrdine.getTipoOrdine().getId().equals(tipoOrdine.getId())) {
						bCheckDestinatariTrasmessiNSO = Boolean.TRUE;
					}
				}
			}
		}
		if (!bCheckDestinatariTrasmessiNSO) {
			response.addApiError(MsgCpassOrd.ORDORDE0076.getError());
		}

		// punto 3 - controllo totalmente evaso
		boolean bOrdineTotalmenteEvaso = Boolean.TRUE;
		final Stato statoRigaOrdineDaEvadere = decodificaDad.getStato(
				ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
		final Stato statoRigaOrdineEvasaParzialmente = decodificaDad.getStato(
				ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());

		final List<RigaOrdine> rigaOrdines = rigaOrdineDad.findByTestataOrdine(testataOrdine.getId(), null);
		//for (RigaOrdine rigaOrdine : rigaOrdines) {
		for(int i = 0; i < rigaOrdines.size(); i++) {
			final RigaOrdine rigaOrdine = rigaOrdines.get(i);
			rigaOrdine.setTipoAcquisto(testataOrdine.getTipoAcquisto());
			if (rigaOrdine.getStato().getCodice().equals(statoRigaOrdineDaEvadere.getCodice())
					|| rigaOrdine.getStato().getCodice().equals(statoRigaOrdineEvasaParzialmente.getCodice())) {
				bOrdineTotalmenteEvaso = false;
			}
			rigaOrdines.set(i, rigaOrdine);
		}



		if (bOrdineTotalmenteEvaso) {
			response.addApiError(MsgCpassOrd.ORDORDE0077.getError());
		}

		// punto 4 - controllo impegno
		boolean bCheckImpegniAnnoEsercizio = Boolean.TRUE;
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		for (final RigaOrdine rigaOrdine : rigaOrdines) {
			final List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
			for (final Impegno impegno : impegnos) {
				if (impegno.getAnnoEsercizio() > annoCorrente) {
					bCheckImpegniAnnoEsercizio = false;
				}
			}
		}
		if (!bCheckImpegniAnnoEsercizio) {
			response.addApiError(MsgCpassOrd.ORDORDE0078.getError());
		}

		if (response.getApiErrors().size() == 0) {

			for (final RigaOrdine riga : rigaOrdines) {
				if (riga.getStato() != null) {
					if (riga.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
						riga.setImportoDaEvadere(riga.getImportoTotale());
					} else if (riga.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
						// BigDecimal totaleEvaso = cpassTOrdRigaEvasioneDao.calcolaTotale(riga.getId());
						final BigDecimal totaleEvaso = rigaEvasioneDad.calcolaTotale(riga.getId());
						riga.setImportoDaEvadere(riga.getImportoTotale().subtract(totaleEvaso));
					}
				}
				final SettoreInterventi tipoAcquisto = testataOrdine.getTipoAcquisto();
				if(tipoAcquisto != null) {
					if(tipoAcquisto.getCodice().equals(ConstantsDecodifiche.SettoreInterventiEnum.FORNITURE.getCodice())) {
						final BigDecimal totaleQuantitaEvasa = rigaEvasioneDad.calcolaQuantitaEvasa(riga.getId());
						riga.setQuantitaEvadibile(riga.getQuantita().subtract(totaleQuantitaEvasa));
					}
				}
			}

			response.setRigaOrdines(rigaOrdines);
		}
	}

}
