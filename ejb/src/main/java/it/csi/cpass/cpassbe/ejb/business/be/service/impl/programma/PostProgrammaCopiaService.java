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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaCopiaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort.Direction;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Copy a Programma
 */
public class PostProgrammaCopiaService extends BaseProgrammaService<PostProgrammaCopiaRequest, PostProgrammaCopiaResponse> {

	private final InterventoDad interventoDad;
	private final DecodificaDad decodificaDad;
	private final SystemDad systemDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param decodificaDad
	 */
	public PostProgrammaCopiaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad,DecodificaDad decodificaDad,SystemDad systemDad) {
		super(configurationHelper, programmaDad);
		this.interventoDad = interventoDad;
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		checkModel(request.getProgramma(), "programma", true);
		checkNotNull(request.getStatoCopia(), "parametro per lo stato copia");
	}

	@Override
	protected void execute() {
		final Programma programma = request.getProgramma();
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		final List<Programma> listaProgramma = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(),programma.getAnnoFineProgramma(), null, programma.getEnte().getId(),StatoProgrammaEnum.BOZZA.getCostante(), false, new Sort("versione", Direction.ASC));
		checkBusinessCondition(listaProgramma.size() == 0, MsgCpassPba.PBAPRGE0070.getError());
		response.setProgramma(programma);

		final Parametro isVistoRagioneria = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),null, ente.getId());
		final Parametro isVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, ente.getId());

		if (request.getSoloControlli() != null && !request.getSoloControlli().booleanValue()) {
			elaboraCopia(programma, utente, ente, isVistoRagioneria, isVersioneDefinitiva);
		}
	}

	/**
	 * @param programma
	 * @param utente
	 * @param ente
	 * @param isVistoRagioneria
	 * @param isVersioneDefinitiva
	 */
	private void elaboraCopia(final Programma programma, Utente utente, Ente ente, Parametro isVistoRagioneria,Parametro isVersioneDefinitiva) {
		Programma programmaCopia = new Programma();
		programmaCopia.setAnno(programma.getAnno());
		programmaCopia.setAnnoFineProgramma(programma.getAnnoFineProgramma());
		programmaCopia.setEnte(programma.getEnte());
		programmaCopia.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoProgrammaEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.PROGRAMMA.getCostante()),"stato"));
		programmaCopia.setUtenteReferente(programma.getUtenteReferente());
		programmaCopia.setDescrizione(programma.getDescrizione());
		final Integer maxVer = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(programma.getAnno(), programma.getEnte().getId(), "");
		programmaCopia.setVersione(maxVer + 1);
		programmaCopia = programmaDad.saveProgramma(programmaCopia);
		// carico tutti gli interventi del programma
		final List<UUID> interventosId = interventoDad.getInterventoIdByProgrammaStato(programma.getId(), null);
		final List<Intervento> listaNuoviInterventi = new ArrayList<>();
		int i = 0;
		for (final UUID interventoId : interventosId) {
			i++;
			final Intervento intervento = isEntityPresent(() -> interventoDad.getInterventoOpt(interventoId), "intervento");
			/*
			  Occorre leggere i record presenti nel programma consultato, ESCLUDENDO gli acquisti che abbiano entrambe queste condizioni:
			  Siano in stato “CANCELLATO”
			  E Non abbiano alcun valore sul campo acquisto_aggiunto_variato_id oppure abbiano un valore che ha CPASS_D_PBA_ACQUISTO_VARIATO.acquisti_non_riproposti a FALSE
			 */
			if(!(intervento.getStato().getCodice().equals(StatoInterventiEnum.CANCELLATO.getCostante()) && (intervento.getAcquistoVariato() == null || !intervento.getAcquistoVariato().getAcquistiNonRiproposti()))) {
				final Intervento interventoOld = new Intervento();
				interventoOld.setId(intervento.getId());
				intervento.setId(null);
				intervento.setProgramma(programmaCopia);
				intervento.setInterventoCopia(interventoOld);
				//Se nel programma precedente CPASS_T_PBA_INTERVENTO.avviato = TRUE
				//Oppure se CPASS_T_PBA_INTERVENTO.motivazione_non_riproposta non è nulla,
				//allora si riporta lo stesso stato di quello di origine,
				//altrimenti si riporta BOZZA lo stato selezionato dall’attore nella modale
				if((intervento.getAvviato()!= null && intervento.getAvviato().equals(Boolean.TRUE))|| StringUtility.isNotEmpty( intervento.getMotivazioneNonRiproposto())) {
					intervento.setStatoXStorico(intervento.getStato().getCodice());
					// stato invariato
				} else {

					if("VALIDATI".equalsIgnoreCase(request.getStatoCopia())) {
						final Stato statoInterventi = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
						if(!intervento.getStato().getCodice().equalsIgnoreCase(StatoInterventiEnum.CANCELLATO.getCostante())) {
							intervento.setStatoXStorico(statoInterventi.getCodice());
							intervento.setStato(statoInterventi);
						}
					}

					if("BOZZA".equalsIgnoreCase(request.getStatoCopia())) {
						final Stato statoInterventi = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
						if(!intervento.getStato().getCodice().equalsIgnoreCase(StatoInterventiEnum.CANCELLATO.getCostante())) {
							intervento.setStatoXStorico(statoInterventi.getCodice());
							intervento.setStato(statoInterventi);
						}
					}
				}
				if(intervento.getMotivazioneNonRiproposto()==null) {
					intervento.setInterventoCopiaTipo(CpassEnum.ACQ_DA_COPIA_PROGRAMMA.getCostante());
				}else {
					intervento.setInterventoCopiaTipo(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante());
				}
				intervento.setDataVisto(null);
				intervento.setDataValidazione(null);
				intervento.setDataRifiuto(null);
				intervento.setUtenteVisto(null);
				intervento.setUtenteValidazione(null);
				intervento.setUtenteRifiuto(null);

				if(isVistoRagioneria != null && isVistoRagioneria.getValore().equalsIgnoreCase("true")) {
					if(intervento.getAvviato() != null && intervento.getAvviato().equals(Boolean.TRUE)) {
						// il visto ragioneria rimane invariato
					}else {
						intervento.setVistoRagioneria(Boolean.FALSE);
						intervento.setUtenteVistoRagioneria(null);
						intervento.setDataVistoRagioneria(null);
					}
				}else {
					intervento.setVistoRagioneria(Boolean.TRUE);
					intervento.setUtenteVistoRagioneria(null);
					intervento.setDataVistoRagioneria(null);
				}

				if(isVersioneDefinitiva == null || isVersioneDefinitiva.getValore().equalsIgnoreCase("false")) {
					intervento.setVersioneDefinitiva(Boolean.TRUE);
				}else {
					if(intervento.getAvviato() != null && intervento.getAvviato().equals(Boolean.TRUE)) {
						// versione definitiva come l'originale
					}else {
						intervento.setVersioneDefinitiva(Boolean.FALSE);
					}
				}
				//intervento.setStatoXStorico(statoBozza.getCodice());
				log.info(" ", "num intervento " + i + " cui " + intervento.getCui());
				final Intervento interventoNew = interventoDad.saveIntervento(intervento,utente);
				listaNuoviInterventi.add(interventoNew);
			}
		}
		//
		for (final Intervento interventoNew : listaNuoviInterventi) {
			interventoDad.gestisciCapofila(interventoNew,programmaCopia,ente.getId());
		}
		response.setProgramma(programmaCopia);
	}

}
