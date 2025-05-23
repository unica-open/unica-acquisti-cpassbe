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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutCambioStatoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutCambioStatoRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityCommon;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an TestataRms
 */
public class PutCambioStatoRmsService extends BaseRmsService<PutCambioStatoRmsRequest, PutCambioStatoRmsResponse> {

	private TestataRms testataRms;
	private final DecodificaDad decodificaDad;
	private final SettoreDad settoreDad;
	private String statoCode;
	private final NotificheDad notificheDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rms DAD
	 */
	public PutCambioStatoRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad, DecodificaDad decodificaDad, SettoreDad settoreDad, UtenteDad utenteDad, NotificheDad notificheDad) {
		super(configurationHelper, rmsDad);
		this.decodificaDad = decodificaDad;
		this.settoreDad = settoreDad;
		this.notificheDad = notificheDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id testata rms");
		checkNotNull(request.getStatoCode(), "codice stato rms");
	}

	@Override
	protected void execute() {
		testataRms = rmsDad.findOneTestata(request.getId());
		checkModel(testataRms.getSettoreDestinatario(), "stato destinatario non presente");
		statoCode = request.getStatoCode();
		boolean inserisciNotificaUtente = false;
		if(statoCode.equals(ConstantsCPassStato.StatoRmsEnum.RIFIUTATA.getCostante())) {
			statoCode = ConstantsCPassStato.StatoRmsEnum.BOZZA.getCostante();
			testataRms.setMotivoRifiuto(request.getTestataRms().getMotivoRifiuto());
			inserisciNotificaUtente = true;
		}
		final Stato stato = isEntityPresent(() -> decodificaDad.getStatoOpt(statoCode, ConstantsCPassStato.TipoStatoEnum.RMS.getCostante()),"stao non censito");

		controlli(testataRms);
		testataRms.setStato(stato);
		testataRms = rmsDad.updateTestataRms(testataRms);
		if (inserisciNotificaUtente) {
			inserisciNotificaUtente(testataRms, ConstantsDecodifiche.NotificaEnum.N0003.getCodice());
		}
	}

	private void controlli(TestataRms testataRms2) {
		// TODO Auto-generated method stub
		final String statoCode = request.getStatoCode();
		final UUID utenteConnessoId   = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		if(statoCode.equals(ConstantsCPassStato.StatoRmsEnum.CONFERMATA.getCostante())) {
			final List<RigaRms> righe = testataRms2.getRigaRms();
			checkBusinessCondition(righe!=null && righe.size()>0, MsgCpassRms.RMSRMSE0017.getError());
			checkBusinessCondition(UtilityCommon.isSettoreValid(settoreDad,testataRms2.getSettoreDestinatario().getId()), MsgCpassRms.RMSRMSE0016.getError());
			testataRms2.setDataConferma(new Date());
		} else if (statoCode.equals(ConstantsCPassStato.StatoRmsEnum.AUTORIZZATA.getCostante())) {
			checkBusinessCondition(UtilityCommon.isSettoreValid(settoreDad,testataRms2.getSettoreDestinatario().getId()), MsgCpassRms.RMSRMSE0016.getError());
			testataRms2.setDataAutorizzazione(new Date());
			testataRms2.setUtenteAutorizzatoreId(utenteConnessoId);
		}else if(statoCode.equals(ConstantsCPassStato.StatoRmsEnum.RIFIUTATA.getCostante())) {
			checkNotNull(request.getTestataRms(), "Testata RMS obbligatoria");
			checkNotNull(request.getTestataRms().getMotivoRifiuto(), "Motivo rifiuto obbligatorio");

		}

	}

	private void inserisciNotificaUtente(TestataRms testataRms, String codiceNotifica) {
		log.info("inserisciNotificaPerUtente","NOTIFICA  -> "+codiceNotifica+" per Rms -> "+testataRms.getRmsAnno()+"/"+testataRms.getRmsNumero());

		final TestoNotifica testoNotifica = isEntityPresent(() -> notificheDad.getTestoNotifica(codiceNotifica),"testoNotifica");

		final Notifica notifica = new Notifica();
		notifica.setEntita(testataRms.getId().toString());
		notifica.setFonte(ConstantsDecodifiche.NotificaFonteEnum.RMS.getCodice());
		notifica.setEntitaTipo(ConstantsDecodifiche.NotificaTipoEntitaEnum.RMS.getCodice());
		notifica.setDataInizio(new Date());
		notifica.setFlgGenerico(false);
		final Map<String,Object> parametri = new HashMap<>();
		parametri.put("anno",testataRms.getRmsAnno());
		parametri.put("numero",testataRms.getRmsNumero());
		notifica.setParametri(JsonUtility.serialize(parametri));
		notifica.setTestoNotifica(testoNotifica);
		notificheDad.saveNotificaUtente(notifica, testataRms.getUtente());
	}
}

