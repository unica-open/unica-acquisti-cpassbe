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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostAnnullaTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostAnnullaTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PostAnnullaTestataOrdineService extends BaseTestataOrdineService<PostAnnullaTestataOrdineRequest, PostAnnullaTestataOrdineResponse> {

	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final DecodificaDad decodificaDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final DestinatarioOrdineDad destinatarioDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostAnnullaTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad,
			RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad, UtenteDad utenteDad, DecodificaDad decodificaDad) {
		super(configurationHelper, testataOrdineDad);
		this.systemDad = systemDad;
		this.utenteDad = utenteDad;
		this.decodificaDad = decodificaDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.destinatarioDad = destinatarioDad;
	}

	@Override
	protected void checkServiceParams() {
		TestataOrdine testataOrdine = request.getTestataOrdine();
		checkNotNull(testataOrdine, "testataOrdine", true);
	}

	@Override
	protected void execute() {
		TestataOrdine testataOrdine = request.getTestataOrdine();
		String codiceStato = testataOrdine.getStato().getCodice();
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());

		boolean valid = false;

		if (!request.isBypassControlli()) {

			// Verifica stato ordine
			if (codiceStato.equals(ConstantsCPassStato.StatoEnum.CONFERMATO.getCostante())) {
				valid = true;

			} else if (codiceStato.equals(ConstantsCPassStato.StatoEnum.AUTORIZZATO.getCostante())) {
				// verifico parametro
				String nomeParametro = ChiaveEnum.SISTEMA_DOCUMENTALE.name();
				Parametro paramSysDocument = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(),
						settoreUtente.get(0).getEnte().getId());
				if (paramSysDocument != null && paramSysDocument.getValore().equals("NO PROTOCOLLO")) {
					valid = true;
				} else {
					// verifiche di un protocollo annullato
					// TODO
				}
			}

			if (valid == true) {
				// controlli andati a buon fine
				apiErrors.add(MsgCpassOrd.ORDORDA0041.getError());

			} else {
				// controlli non andati a buon fine
				String nomeParametro = ChiaveEnum.ANNULLO_ORDINE_PROTOCOLLATO.name();
				Parametro paramOrdProtocollato = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(),
						settoreUtente.get(0).getEnte().getId());

				if (paramOrdProtocollato == null || paramOrdProtocollato.getValore().equals("SI")) {
					apiErrors.add(MsgCpassOrd.ORDORDA0064.getError());
				} else {
					apiErrors.add(MsgCpassOrd.ORDORDE0065.getError());
				}
			}
		}

		if (apiErrors.size() > 0) {
			response.addApiErrors(apiErrors);

		} else {
			// applico l'annullamento

			// imposto gli importi degli impegni a 0
			for (Destinatario destinatario : request.getTestataOrdine().getListDestinatario()) {
				List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());

				for (RigaOrdine riga : righe) {
					impegnoDad.annullaImpegniByRiga(riga.getId());

					// NON IMPOSTARE LA DATA CANCELLAZIONE MA VALORIZZARE LA DATA ANNULLAMENTO DELLA TESTATA QUANDO SARA' AGGIUNTO IL CAMPO SU DB.
//					riga.setUtenteCancellazione(utenteConnesso.getCodiceFiscale());
//					riga.setDataCancellazione(new Date());
					riga.setStatoElOrdine(decodificaDad.getStatoElOrdine(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_ANNULLATA_DA_EVADERE.getCostante(),
							ConstantsCPassStatoElOrdine.TipoEnum.RIGA_ORDINE.getCostante()));
					rigaOrdineDad.updateRigaOrdine(riga);
				}
				// NON IMPOSTARE LA DATA CANCELLAZIONE MA VALORIZZARE LA DATA ANNULLAMENTO DELLA TESTATA QUANDO SARA' AGGIUNTO IL CAMPO SU DB.
//				destinatario.setUtenteCancellazione(utenteConnesso.getCodiceFiscale());
//				destinatario.setDataCancellazione(new Date());
				destinatario
						.setStatoElOrdine(decodificaDad.getStatoElOrdine(ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_ANNULLATO_DA_EVADERE.getCostante(),
								ConstantsCPassStatoElOrdine.TipoEnum.DEST_ORDINE.getCostante()));
				destinatarioDad.updateDestinatario(destinatario);
			}

			// Impostazione nuovo stato ANNULLATO
			testataOrdine.setStato(isEntityPresent(
					() -> decodificaDad.getStato(ConstantsCPassStato.StatoEnum.ANNULLATO.getCostante(), ConstantsCPassStato.TipoEnum.ORDINE.getCostante()),
					"stato"));

			// NON IMPOSTARE LA DATA CANCELLAZIONE MA VALORIZZARE LA DATA ANNULLAMENTO DELLA TESTATA QUANDO SARA' AGGIUNTO IL CAMPO SU DB.
//			testataOrdine.setDataCancellazione(new Date());
//			testataOrdine.setUtenteCancellazione(utenteConnesso.getCodiceFiscale());
			testataOrdine.setDataAnnullamento(new Date());
			testataOrdineDad.updateTestataOrdine(testataOrdine, false);

			testataOrdineDad.deleteAssociati(testataOrdine);
		}

		response.setTestataOrdine(testataOrdine);
	}

}
