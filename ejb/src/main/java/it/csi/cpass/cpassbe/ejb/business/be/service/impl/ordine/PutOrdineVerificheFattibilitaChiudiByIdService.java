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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineVerificheFattibilitaChiudiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineVerificheFattibilitaChiudiByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;

public class PutOrdineVerificheFattibilitaChiudiByIdService
		extends BaseTestataOrdineService<PutOrdineVerificheFattibilitaChiudiByIdRequest, PutOrdineVerificheFattibilitaChiudiByIdResponse> {

	private final SettoreDad settoreDad;
	private final SystemDad systemDad;

	private final DestinatarioOrdineDad destinatarioOrdineDad;
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
		super(configurationHelper, testataOrdineDad);

		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;

		this.systemDad = systemDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId());

//		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
//		Ente ente = settoreCorrente.getEnte();

		// 2.7.1.1 Verifica stati ordine
		// Occorre verificare che, per l’ordine in questione, i destinatari NON siano:
		// • O tutti in stato “Da evadere” (stato_el_ordine_id corrispondente al valore presente in CPASS_D_STATO_EL_ORDINE con codice “DAE” e
		// stato_tipo = ‘DEST_ORDINE’
		// • O tutti in stato “Evaso totalmente” (stato_el_ordine_id corrispondente al valore presente in CPASS_D_STATO_EL_ORDINE con codice “EVT” e
		// stato_tipo = ‘DEST_ORDINE’
		boolean bDaEvadere = false;
		boolean bEvasoTotalmente = false;
		boolean bAltroStato = false;
		for (Destinatario destinatario : testataOrdine.getListDestinatario()) {
			if (destinatario.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_DA_EVADERE.getCostante())) {
				bDaEvadere = true;
			} else if (destinatario.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_EVASO_TOTALMENTE.getCostante())) {
				bEvasoTotalmente = true;
			} else {
				bAltroStato = true;
			}
		}

		boolean bVerificaStatiOrdine = true;
		if (bDaEvadere && !bEvasoTotalmente && !bAltroStato) {
			bVerificaStatiOrdine = false;
		} else if (!bDaEvadere && bEvasoTotalmente && !bAltroStato) {
			bVerificaStatiOrdine = false;
		}
		if (!bVerificaStatiOrdine) {
			response.addApiError(MsgCpassOrd.ORDORDE0117.getError());
			// response.addApiError(CoreError.GENERIC_ERROR.getError("error", "Errore nella 'Verifica stati ordine'"));
		}

		// 2.7.1.2 Verifiche stati evasione
		// Per ogni destinatario dell’ordine con stato EVP, occorre ricercare i destinatari evasione non ANNULLATI ad esso collegate
		// i record trovati, devono avere tutti lo stato ETF, altrimenti la verifica non va a buon fine
		for (Destinatario destinatario : testataOrdine.getListDestinatario()) {
			if (destinatario.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante())) {

				List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByIdDestinatarioOrdine(destinatario.getId());
				for (DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {
					if (!destinatarioEvasione.getStatoElOrdine().getCodice()
							.equals(ConstantsCPassStatoElOrdine.StatoEnum.DESTINATARIO_EVASIONE_ANNULLATO.getCostante())) {

						if (!destinatarioEvasione.getStatoElOrdine().getCodice()
								.equals(ConstantsCPassStatoElOrdine.StatoEnum.DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO.getCostante())) {
							if (!response.getApiErrors().contains((CoreError.GENERIC_ERROR.getError()))) {
								response.addApiError(MsgCpassOrd.ORDORDE0118.getError());
								// response.addApiError(CoreError.GENERIC_ERROR.getError("error", "Errore nelle 'Verifiche stati evasione'"));
							}
						}
					}
				}

			}
		}

		response.setTestataOrdine(testataOrdine);
	}

}
