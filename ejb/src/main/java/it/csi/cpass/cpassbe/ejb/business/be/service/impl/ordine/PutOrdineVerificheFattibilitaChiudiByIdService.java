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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineVerificheFattibilitaChiudiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineVerificheFattibilitaChiudiByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutOrdineVerificheFattibilitaChiudiByIdService
extends BaseOrdineService<PutOrdineVerificheFattibilitaChiudiByIdRequest, PutOrdineVerificheFattibilitaChiudiByIdResponse> {

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutOrdineVerificheFattibilitaChiudiByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad, DestinatarioEvasioneDad destinatarioEvasioneDad, SystemDad systemDad, SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);

		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());
		// 2.7.1.1 Verifica stati ordine
		// Occorre verificare che, per l’ordine in questione, i destinatari NON siano:
		// O tutti in stato Da evadere (stato_el_ordine_id corrispondente al valore presente in CPASS_D_STATO_EL_ORDINE con codice DAE e
		// stato_tipo = DEST_ORDINE
		// O tutti in stato Evaso totalmente (stato_el_ordine_id corrispondente al valore presente in CPASS_D_STATO_EL_ORDINE con codice EVT e
		// stato_tipo = DEST_ORDINE
		boolean bDaEvadere = false;
		boolean bEvasoTotalmente = false;
		boolean bAltroStato = false;
		for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
			if (destinatario.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante())) {
				bDaEvadere = Boolean.TRUE;
			} else if (destinatario.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_TOTALMENTE.getCostante())) {
				bEvasoTotalmente = Boolean.TRUE;
			} else {
				bAltroStato = Boolean.TRUE;
			}
		}

		boolean bVerificaStatiOrdine = Boolean.TRUE;
		if (bDaEvadere && !bEvasoTotalmente && !bAltroStato) {
			bVerificaStatiOrdine = false;
		} else if (!bDaEvadere && bEvasoTotalmente && !bAltroStato) {
			bVerificaStatiOrdine = false;
		}
		if (!bVerificaStatiOrdine) {
			response.addApiError(MsgCpassOrd.ORDORDE0117.getError());
		}

		// 2.7.1.2 Verifiche stati evasione
		// Per ogni destinatario dell’ordine con stato EVP, occorre ricercare i destinatari evasione non ANNULLATI ad esso collegate
		// i record trovati, devono avere tutti lo stato ETF, altrimenti la verifica non va a buon fine
		for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
			if (destinatario.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante())) {
				final List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByIdDestinatarioOrdine(destinatario.getId());
				for (final DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {
					if (!destinatarioEvasione.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_ANNULLATO.getCostante())) {
						if (!destinatarioEvasione.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO.getCostante())) {
							if (!response.getApiErrors().contains((CoreError.GENERIC_ERROR.getError()))) {
								if (!response.getApiErrors().contains((MsgCpassOrd.ORDORDE0118.getError()))) {
									response.addApiError(MsgCpassOrd.ORDORDE0118.getError());
								}
							}
						}
					}
				}

			}
		}
		response.setTestataOrdine(testataOrdine);
	}
}
