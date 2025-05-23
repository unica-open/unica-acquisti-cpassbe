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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.facade.MITManager;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutTrasmettiProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutTrasmettiProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.jms.BeanQueueRegistrator;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Programma
 */
public class PutTrasmettiProgrammaService extends BaseProgrammaService<PutTrasmettiProgrammaRequest, PutTrasmettiProgrammaResponse> {

	private final ElaborazioneDad elaborazioneDad;
	private final ElaborazioneParametroDad elaborazioneParametroDad;
	private final ElaborazioneTipoDad elaborazioneTipoDad;
	private final BeanQueueRegistrator beanQueueRegistrator;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param elaborazioneDad
	 * @param beanQueueRegistrator
	 */
	public PutTrasmettiProgrammaService(
			ConfigurationHelper configurationHelper,
			ProgrammaDad programmaDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneParametroDad elaborazioneParametroDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			BeanQueueRegistrator beanQueueRegistrator) {
		super(configurationHelper, programmaDad);
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneParametroDad = elaborazioneParametroDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
		this.beanQueueRegistrator = beanQueueRegistrator;
	}

	@Override
	protected void checkServiceParams() {
		//		programma = request.getProgramma();
		//		checkModel(programma, "programma");
		//		checkNotNull(programma.getOptlock(), "opt look");
	}

	@Override
	protected void execute() {
		Programma programma = request.getProgramma();

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
			checkCondition(false, MsgCpassPba.PBAPRGE0035.getError("errori", errori));
		}

		separaMessaggiErrorePerTipo(response.getApiErrors());

		if(response.getApiErrors().isEmpty()) {
			// aggiorno il programma nel caso vi siano modifiche sui campi di provvedimento
			programma = programmaDad.updateProgramma(programma);
			final UUID entitaId = programma.getId();
			final UUID utente = request.getIdUtente(); // Id_utente dell'utente che ha richiesto l’elaborazione
			// inserisce record elaborazione
			final Elaborazione elaborazione = new Elaborazione();
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazione.setEsito(null);
			elaborazione.setEntitaId(entitaId.toString());
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
			elaborazione.setUtente(utente.toString());

			final Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.TRASMISSIONE_PROGRAMMA_MIT.getCostante());
			elaborazione.setElaborazioneTipo(elaborazioneTipo.orElseThrow(() -> new NotFoundException("elaborazione tipo")));
			final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
			elaborazione.setEnte(ente);
			elaborazioneDad.saveElaborazione(elaborazione);
			// elaborazione parametri
			final ElaborazioneParametro elaborazioneParametro = new ElaborazioneParametro();
			elaborazioneParametro.setElaborazione(elaborazione);
			elaborazioneParametro.setChiave(MITManager.MODALITA_INVIO);
			elaborazioneParametro.setValore(request.getModalitaInvio());
			elaborazioneParametroDad.saveElaborazioneParametro(elaborazioneParametro);
			// send MQ message
			beanQueueRegistrator.register(elaborazione.getId());
		}

	}

}
