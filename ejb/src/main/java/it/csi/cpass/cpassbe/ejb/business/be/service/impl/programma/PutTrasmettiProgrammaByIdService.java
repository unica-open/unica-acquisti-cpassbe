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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.facade.MITManager;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutTrasmettiProgrammaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutTrasmettiProgrammaByIdResponse;
import it.csi.cpass.cpassbe.ejb.jms.BeanQueueRegistrator;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PutTrasmettiProgrammaByIdService
		extends BaseProgrammaService<PutTrasmettiProgrammaByIdRequest, PutTrasmettiProgrammaByIdResponse> {

	private ElaborazioneDad elaborazioneDad;
	private ElaborazioneParametroDad elaborazioneParametroDad;
	private ElaborazioneTipoDad elaborazioneTipoDad;
	private BeanQueueRegistrator beanQueueRegistrator;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param elaborazioneDad 
	 * @param beanQueueRegistrator 
	 */
	public PutTrasmettiProgrammaByIdService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad,
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
		Optional<Programma> optional = programmaDad.getProgramma(request.getIdProgramma());
		Programma programma = optional.get();

		UUID entitaId = programma.getId();
		UUID utente = request.getIdUtente(); // Id_utente dell'utente che ha richiesto l’elaborazione

		// inserisce record elaborazione
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setData(new Date(System.currentTimeMillis()));
		elaborazione.setEsito(null);
		elaborazione.setEntitaId(entitaId.toString());
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
		elaborazione.setUtente(utente.toString());

		Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad
				.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.TRASMISSIONE_PROGRAMMA_MIT.getCostante());
		elaborazione.setElaborazioneTipo(elaborazioneTipo.get());
				
		elaborazioneDad.saveElaborazione(elaborazione);
		elaborazioneDad.flush();

		// elaborazione parametri
		ElaborazioneParametro elaborazioneParametro = new ElaborazioneParametro();
		elaborazioneParametro.setElaborazione(elaborazione);
		elaborazioneParametro.setChiave(MITManager.MODALITA_INVIO);
		elaborazioneParametro.setValore(request.getModalitaInvio());
		elaborazioneParametroDad.saveElaborazioneParametro(elaborazioneParametro);
		elaborazioneParametroDad.flush();
		
		// send MQ message
		beanQueueRegistrator.register(elaborazione.getId());
	}

}
