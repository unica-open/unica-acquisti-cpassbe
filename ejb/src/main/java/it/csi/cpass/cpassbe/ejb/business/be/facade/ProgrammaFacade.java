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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.DeleteProgrammaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetProgrammiByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetProgrammiBySettoreAndStatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetProgrammiBySettoreAnnoVersioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetProgrammiBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetProgrammiTrasmissioneMITService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetRicercaProgrammiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.GetUltimiProgrammiBySettoreAndStatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PostProgrammaCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PostProgrammaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PutProgrammaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PutProgrammaStatoAnnullatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PutProgrammaStatoConfermatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PutProgrammaStatoRiportaInBozzaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma.PutTrasmettiProgrammaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.DeleteProgrammaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiBySettoreAndStatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiBySettoreAnnoVersioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiTrasmissioneMITRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetRicercaProgrammiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoConfermatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoRiportaInBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutTrasmettiProgrammaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.DeleteProgrammaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiBySettoreAndStatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiBySettoreAnnoVersioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiTrasmissioneMITResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetRicercaProgrammiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoConfermatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoRiportaInBozzaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutTrasmettiProgrammaByIdResponse;
import it.csi.cpass.cpassbe.ejb.jms.BeanQueueRegistrator;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Fa&ccedil;ade for the /programma path
 */
@Stateless
@Lock(LockType.READ)
public class ProgrammaFacade extends BaseFacade {
	@EJB private ProgrammaFacade self;
	@EJB private BeanQueueRegistrator beanQueueRegistrator;
	
	// Injection point
	@Inject private ProgrammaDad programmaDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private InterventoDad interventoDad;
	@Inject private ElaborazioneDad elaborazioneDad;
	@Inject private ElaborazioneParametroDad elaborazioneParametroDad;
	@Inject private ElaborazioneTipoDad elaborazioneTipoDad;
	@Inject private UtenteDad utenteDad;
	

	/**
	 * Deletes the Programma by its id
	 * @param id the id
	 * @return nothing
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteProgrammaByIdResponse deleteProgrammaById(UUID id) {
		return executeService(new DeleteProgrammaByIdRequest(id), new DeleteProgrammaByIdService(configurationHelper, programmaDad));
	}
	
	/**
	 * Retrieves the Programmi
	 * @param page the page
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param programma the programma
	 * @return the programmas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaProgrammiResponse getRicercaProgrammi(Integer page, Integer limit, String sort, String direction, Programma programma) {
		return executeService(new GetRicercaProgrammiRequest(page, limit, sort, direction, programma), new GetRicercaProgrammiService(configurationHelper, programmaDad));
	}
	
	/**
	 * Retrieves the Programma by its id
	 * @param id the id
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammaByIdResponse getProgrammaById(UUID id) {
		return executeService(new GetProgrammaByIdRequest(id), new GetProgrammiByIdService(configurationHelper, programmaDad));
	}
	
	/**
	 * Posts an Programma
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostProgrammaResponse postProgramma(Programma programma) {
		return executeService(new PostProgrammaRequest(programma), new PostProgrammaService(configurationHelper, programmaDad, interventoDad, decodificaDad));
	}

	/**
	 * Copy an Programma
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostProgrammaCopiaResponse postProgrammaCopia(Programma programma, Boolean soloControlli) {
		return executeService(new PostProgrammaCopiaRequest(programma, soloControlli),
				new PostProgrammaCopiaService(configurationHelper, programmaDad, interventoDad, decodificaDad));
	}
	
	/**
	 * Puts an Programma
	 * @param id the id
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaResponse putProgrammaById(UUID id, Programma programma) {
		return executeService(new PutProgrammaRequest(setId(id, programma)), new PutProgrammaService(configurationHelper, programmaDad));
	}
	
	/**
	 * Puts an stato annullato Programma
	 * @param id the id
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaStatoAnnullatoResponse putProgrammaStatoAnnullatoById(UUID id, Programma programma) {
		return executeService(new PutProgrammaStatoAnnullatoRequest(setId(id,programma)), new PutProgrammaStatoAnnullatoService(configurationHelper, programmaDad, interventoDad));
	}

	/**
	 * Puts an stato approvato Programma
	 * @param id the id
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaStatoConfermatoResponse putProgrammaStatoConfermatoById(UUID id, Programma programma, Boolean ignoreWarning) {
		return executeService(new PutProgrammaStatoConfermatoRequest(setId(id, programma), ignoreWarning),
				new PutProgrammaStatoConfermatoService(configurationHelper, programmaDad, interventoDad));
	}

	/**
	 * Puts an stato bozza Programma
	 * @param id the id
	 * @param programma
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaStatoRiportaInBozzaResponse putProgrammaStatoRiportaInBozzaById(UUID id, Programma programma) {
		return executeService(new PutProgrammaStatoRiportaInBozzaRequest(setId(id, programma)),
				new PutProgrammaStatoRiportaInBozzaService(configurationHelper, programmaDad));
	}
	

	/**
	 * Puts an stato approvato Programmi
	 * @param programmi the programmi
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaStatoConfermatoResponse putProgrammiStatoApprovato(List<Programma> programmi) {
		for(Programma programma : programmi) {
			self.putProgrammaStatoConfermatoById(programma.getId(), programma, true) ;
		}
		return new PutProgrammaStatoConfermatoResponse();
	}
	
	/**
	 * Puts an stato annullato Programmi
	 * @param programmi the programmi
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutProgrammaStatoAnnullatoResponse putProgrammiStatoAnnullato(List<Programma> programmi) {
		for(Programma programma : programmi) {
			self.putProgrammaStatoConfermatoById(programma.getId(), programma, true) ;
		}		
		return new PutProgrammaStatoAnnullatoResponse();
	}
	
	/**
	 * Retrieves the Programma by its id settore
	 * @param settoreId the settoreId
	 * @param soloValidi solo validi
	 * @return the programmas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammiBySettoreResponse getProgrammiBySettore(UUID settoreId, Boolean soloValidi) {
		return executeService(new GetProgrammiBySettoreRequest(settoreId, soloValidi), new GetProgrammiBySettoreService(configurationHelper, programmaDad));
	}

	/**
	 * Retrieves the Programma by its settoreid and anno
	 * @param settoreId the settoreid
	 * @param anno the anno
	 * @param versione the versione
	 * @return the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammiBySettoreAnnoVersioneResponse getProgrammiBySettoreAnnoVersione(UUID settoreId, Integer anno, Integer versione) {
		return executeService(new GetProgrammiBySettoreAnnoVersioneRequest(settoreId,anno,versione), new GetProgrammiBySettoreAnnoVersioneService(configurationHelper, programmaDad));
	}
	
	
	/**
	 * Retrieves the Programma by its id settore
	 * @param settoreId the settoreId
	 * @param statoCode the stato
	 * @return the programmas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammiBySettoreAndStatoResponse getProgrammiBySettoreAndStato(UUID settoreId, String statoCode) {
		return executeService(new GetProgrammiBySettoreAndStatoRequest(settoreId, statoCode), new GetProgrammiBySettoreAndStatoService(configurationHelper, programmaDad));
	}
	
	/**
	 * Retrieves the Programma by its id settore
	 * @param settoreId the settoreId
	 * @param statoCode the stato
	 * @return the programmas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammiBySettoreAndStatoResponse getUltimiProgrammiBySettoreAndStato(UUID settoreId, String statoCode) {
		return executeService(new GetProgrammiBySettoreAndStatoRequest(settoreId, statoCode), new GetUltimiProgrammiBySettoreAndStatoService(configurationHelper, programmaDad));
	}
	/**
	 * Retrieves the Programma da trasmettere al ministero
	 * @return the programmi MIT
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetProgrammiTrasmissioneMITResponse getProgrammiTrasmissioneMIT() {
		return executeService(new GetProgrammiTrasmissioneMITRequest(), new GetProgrammiTrasmissioneMITService(configurationHelper, programmaDad));
	}
	
	/**
	 * Updates the trasmissione programma by id
	 * @param idProgramma the id programma
	 * @param idUtente tehe id utente
	 * @return the response
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutTrasmettiProgrammaByIdResponse putTrasmettiProgrammaById(UUID idProgramma, UUID idUtente, String modalitaInvio) {
		return executeService(new PutTrasmettiProgrammaByIdRequest(idProgramma, idUtente, modalitaInvio),
				new PutTrasmettiProgrammaByIdService(configurationHelper, programmaDad, elaborazioneDad, elaborazioneParametroDad, elaborazioneTipoDad, beanQueueRegistrator));
	}
	
}
