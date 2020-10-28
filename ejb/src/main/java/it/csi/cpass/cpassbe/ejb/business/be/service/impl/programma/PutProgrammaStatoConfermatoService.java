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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoConfermatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoConfermatoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PutProgrammaStatoConfermatoService extends BaseProgrammaService<PutProgrammaStatoConfermatoRequest, PutProgrammaStatoConfermatoResponse> {

	private Programma programma;
	private InterventoDad interventoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param interventoDad
	 */
	public PutProgrammaStatoConfermatoService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad) {
		super(configurationHelper, programmaDad);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkModel(programma, "programma");
		// checkNotEmpty(programma.getUrl(), "url");
		checkNotNull(programma.getOptlock(), "opt look");
	}

	@Override
	protected void execute() {
		Programma programmaAttuale = programmaDad.getProgramma(programma.getId()).orElse(null);

		// Deve esistere almeno un intervento "approvato" per il programma
		// List<Intervento> listIntApprovati = interventoDad.getInterventoByProgrammaStato(programma.getId(),
		// CpassStatiEnum.APPROVATO.getCostante(), true);

		// Deve controllare che ci sia almeno un acquisto associato al programma
		Intervento intervento = new Intervento();
		intervento.setProgramma(programma);
		List<Intervento> listInt = interventoDad.getRicercaInterventi(0, 0, null, intervento, null,null).getList();
		checkCondition(listInt.size() > 0, MsgCpassPba.PBAPRGE0033.getError());

		// Non devono esistere interventi in "bozza" per il programma
		List<Intervento> listIntBozza = interventoDad.getInterventoByProgrammaStato(programma.getId(), CpassStatiEnum.PRO_BOZZA.getCostante(), true);
		if (request.getIgnoreWarnings() == null || !request.getIgnoreWarnings().booleanValue()) {
			checkCondition(listIntBozza.size() == 0, MsgCpassPba.PBAPRGA0002.getError());
		}

		String errori = "";
		if (StringUtils.isEmpty(programma.getUrl())) {
			errori += ", Url";
		}
		if (StringUtils.isEmpty(programma.getDescrizioneProvvedimento())) {
			errori += ", Descrizione provvedimento";
		}
		if (programma.getDataProvvedimento() == null) {
			errori += ", Data provvedimento";
		}
		if (programma.getNumeroProvvedimento() == null) {
			errori += ", Numero provvedimento";
		}
		if (programma.getDataPubblicazione() == null) {
			errori += ", Data pubblicazione";
		}
		if (errori.length() > 0) {
			errori = errori.substring(2);
			checkCondition(false, MsgCpassPba.PBAPRGE0023.getError("errori", errori));
		}

		separaMessaggiErrorePerTipo(response.getApiErrors());

		if (response.getApiErrors().isEmpty()) {
			// TODO inserire il controllo sul fatto che l'utente sia abilitato o meno al cambio stato
			checkOptlock(programma.getOptlock(), programmaAttuale.getOptlock());
			programmaDad.updateStatoProgramma(request.getProgramma().getId(), ConstantsCPassStato.StatoEnum.CONFERMATO.getCostante(),
					CpassEnum.PROGRAMMA.getCostante());

			for (Intervento interventoItem : listIntBozza) {
				interventoDad.updateStatoIntervento(interventoItem.getId(), CpassStatiEnum.INT_CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante());
			}
		}
	}

}
