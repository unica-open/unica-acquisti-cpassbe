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
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVisibilitàDocumentale;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;

/**
 * Retrieves a list of riga ordine
 */
public class GetRigheOrdineDaEvadereByOrdineAnnoNumeroService
		extends BaseTestataEvasioneService<GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest, GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse> {

	private TestataOrdineDad testataOrdineDad;
	private DestinatarioOrdineDad destinatarioOrdineDad;
	private RigaOrdineDad rigaOrdineDad;
	private ImpegnoDad impegnoDad;
	private DecodificaDad decodificaDad;
	private RigaEvasioneDad rigaEvasioneDad;
	private UtenteDad utenteDad;

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
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		TestataOrdine testataOrdine = testataOrdineDad.getByAnnoENumero(request.getAnno(), request.getNumero(), settoreCorrente.getEnte().getId());

		// fix CPASS-186 - ORD19 Ricercare ordini da evadere: scenario alternativo (unico ordine)
		boolean bVisibilitàDocumentale = UtilityVisibilitàDocumentale.isVisibile(utenteDad, testataOrdine);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0044.getError());
		}
		
		// 2.10.4 Criteri di evadibilità degli ordini
		// punto 1 - ordine autorizzato
		if (!testataOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoEnum.AUTORIZZATO.getCostante())) {
			response.addApiError(MsgCpassOrd.ORDORDE0075.getError());
		}

		// punto 2 - destinatari trasmessi NSO
		boolean bCheckDestinatariTrasmessiNSO = false;
		// 2. Deve avere almeno un destinatario con CPASS_T_ORD_DESTINATARIO_ORDINE.stato_nso_id corrispondente allo stato “OK”
		List<Destinatario> destinatarios = destinatarioOrdineDad.findByOrdine(testataOrdine.getId());
		for (Destinatario destinatario : destinatarios) {
			if (destinatario.getStatoNso() != null && destinatario.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante())) {
				bCheckDestinatariTrasmessiNSO = true;
			}
		}
		// tipo_ordine_id corrisponde ad un tipo in CPASS_D_ORD_TIPO_ORDINE che ha il flag da_trasm_nso = false
		if (!bCheckDestinatariTrasmessiNSO) {
			List<TipoOrdine> tipoOrdines = decodificaDad.getTipoOrdines();
			for (TipoOrdine tipoOrdine : tipoOrdines) {
				if (tipoOrdine.getFlagTrasmNso() != null && !tipoOrdine.getFlagTrasmNso()) {
					if (testataOrdine.getTipoOrdine().getId().equals(tipoOrdine.getId())) {
						bCheckDestinatariTrasmessiNSO = true;
					}
				}
			}
		}
		if (!bCheckDestinatariTrasmessiNSO) {
			response.addApiError(MsgCpassOrd.ORDORDE0076.getError());
		}

		// punto 3 - controllo totalmente evaso
		boolean bOrdineTotalmenteEvaso = true;
		final StatoElOrdine statoElOrdineRigaOrdineDaEvadere = decodificaDad.getStatoElOrdine(
				ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_DA_EVADERE.getCostante(), ConstantsCPassStatoElOrdine.TipoEnum.RIGA_ORDINE.getCostante());
		final StatoElOrdine statoElOrdineRigaOrdineEvasaParzialmente = decodificaDad.getStatoElOrdine(
				ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante(),
				ConstantsCPassStatoElOrdine.TipoEnum.RIGA_ORDINE.getCostante());

		List<RigaOrdine> rigaOrdines = rigaOrdineDad.findByTestataOrdine(testataOrdine.getId(), null);
		for (RigaOrdine rigaOrdine : rigaOrdines) {
			if (rigaOrdine.getStatoElOrdine().getCodice().equals(statoElOrdineRigaOrdineDaEvadere.getCodice())
					|| rigaOrdine.getStatoElOrdine().getCodice().equals(statoElOrdineRigaOrdineEvasaParzialmente.getCodice())) {
				bOrdineTotalmenteEvaso = false;
			}
		}
		if (bOrdineTotalmenteEvaso) {
			response.addApiError(MsgCpassOrd.ORDORDE0077.getError());
		}

		// punto 4 - controllo impegno
		boolean bCheckImpegniAnnoEsercizio = true;
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		for (RigaOrdine rigaOrdine : rigaOrdines) {
			List<Impegno> impegnos = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
			for (Impegno impegno : impegnos) {
				if (impegno.getAnnoEsercizio() > annoCorrente) {
					bCheckImpegniAnnoEsercizio = false;
				}
			}
		}
		if (!bCheckImpegniAnnoEsercizio) {
			response.addApiError(MsgCpassOrd.ORDORDE0078.getError());
		}

		if (response.getApiErrors().size() == 0) {

			for (RigaOrdine riga : rigaOrdines) {
				if (riga.getStatoElOrdine() != null) {
					if (riga.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_DA_EVADERE.getCostante()))
						riga.setImportoDaEvadere(riga.getImportoTotale());
					else if (riga.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
						// BigDecimal totaleEvaso = cpassTOrdRigaEvasioneDao.calcolaTotale(riga.getId());
						BigDecimal totaleEvaso = rigaEvasioneDad.calcolaTotale(riga.getId());
						riga.setImportoDaEvadere(riga.getImportoTotale().subtract(totaleEvaso));
					}
				}
			}

			response.setRigaOrdines(rigaOrdines);
		}
	}

}
