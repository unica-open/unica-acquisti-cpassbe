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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostAnnullaTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostAnnullaTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostAnnullaTestataOrdineService extends BaseOrdineService<PostAnnullaTestataOrdineRequest, PostAnnullaTestataOrdineResponse> {

	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final DecodificaDad decodificaDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final DestinatarioOrdineDad destinatarioDad;
	private final RdaDad rdaDad;
	private final RmsDad rmsDad;
	private final ScaricoMepaTestataDad scaricoMepaTestataDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostAnnullaTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad,
			RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad, UtenteDad utenteDad, DecodificaDad decodificaDad,
			RdaDad rdaDad, RmsDad rmsDad, ScaricoMepaTestataDad scaricoMepaTestataDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.systemDad = systemDad;
		this.utenteDad = utenteDad;
		this.decodificaDad = decodificaDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.destinatarioDad = destinatarioDad;
		this.rdaDad = rdaDad;
		this.rmsDad = rmsDad;
		this.scaricoMepaTestataDad = scaricoMepaTestataDad;
	}

	@Override
	protected void checkServiceParams() {
		final TestataOrdine testataOrdine = request.getTestataOrdine();
		checkNotNull(testataOrdine, "testataOrdine", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final TestataOrdine testataOrdine = request.getTestataOrdine();
		final String codiceStato = testataOrdine.getStato().getCodice();
		final List<ApiError> apiErrors = new ArrayList<>();

		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());

		boolean valid = Boolean.FALSE;
		if (!request.isBypassControlli()) {
			// Verifica stato ordine
			if (codiceStato.equals(StatoOrdineEnum.CONFERMATO.getCostante())) {
				valid = Boolean.TRUE;
			} else if (codiceStato.equals(StatoOrdineEnum.AUTORIZZATO.getCostante())) {
				// verifico parametro
				final Parametro paramSysDocument = systemDad.getParametro(ChiaveEnum.SISTEMA_DOCUMENTALE.name(), ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(),settoreUtente.get(0).getEnte().getId());
				if (paramSysDocument != null && paramSysDocument.getValore().equals("NO PROTOCOLLO")) {
					valid = Boolean.TRUE;
				}
			}

			if (valid) {
				// controlli andati a buon fine
				apiErrors.add(MsgCpassOrd.ORDORDA0041.getError());
			} else {
				// controlli non andati a buon fine
				final String nomeParametro = ChiaveEnum.ANNULLO_ORDINE_PROTOCOLLATO.name();
				final Parametro paramOrdProtocollato = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(),settoreUtente.get(0).getEnte().getId());
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
			for (final Destinatario destinatario : request.getTestataOrdine().getListDestinatario()) {
				final List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				for (final RigaOrdine riga : righe) {
					impegnoDad.annullaImpegniByRiga(riga.getId());
					// NON IMPOSTARE LA DATA CANCELLAZIONE MA VALORIZZARE LA DATA ANNULLAMENTO DELLA TESTATA QUANDO SARA' AGGIUNTO IL CAMPO SU DB.
					//					riga.setUtenteCancellazione(utenteConnesso.getCodiceFiscale());
					//					riga.setDataCancellazione(new Date());
					riga.setStato(decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_ANNULLATA_DA_EVADERE.getCostante(),
							ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante()));
					rigaOrdineDad.updateRigaOrdine(riga);
				}
				// NON IMPOSTARE LA DATA CANCELLAZIONE MA VALORIZZARE LA DATA ANNULLAMENTO DELLA TESTATA QUANDO SARA' AGGIUNTO IL CAMPO SU DB.
				//				destinatario.setUtenteCancellazione(utenteConnesso.getCodiceFiscale());
				//				destinatario.setDataCancellazione(new Date());
				destinatario.setStato(decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_ANNULLATO_DA_EVADERE.getCostante(),ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante()));
				destinatarioDad.updateDestinatario(destinatario);
			}

			// Impostazione nuovo stato ANNULLATO
			testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.ANNULLATO.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()),"stato"));

			testataOrdine.setDataAnnullamento(new Date());

			testataOrdineDad.updateTestataOrdine(testataOrdine, false);

			testataOrdineDad.deleteAssociati(testataOrdine);

			scollegaRdaDaOrdine(testataOrdine, decodificaDad, rdaDad, rmsDad);

			ripristinaScaricoMepaTestata(testataOrdine, scaricoMepaTestataDad, decodificaDad);
		}

		response.setTestataOrdine(testataOrdine);
	}

}
