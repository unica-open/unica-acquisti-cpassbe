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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
//import org.jboss.ejb3;

import org.jboss.ejb3.annotation.TransactionTimeout;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
//import com.sun.jersey.core.header.FormDataContentDisposition;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.DeleteInterventoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.DeleteInterventoPhysicallyByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetCigByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetControlloCancellazioneInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetCpvsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventiByCapofilaIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventiByCuiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventiCapofilaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoByCuiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoImportiByInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoInProgrammaFuturoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetRicercaInterventiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetRicercaInterventiXCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetStatiInterventoByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetStoricoRupsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetUltimoStatoInfoByInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostControlloCapofilaXCopiaInterventiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostCpvsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostInterventiCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostInterventiCopiaV2Service;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostUploadCsvService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutAvviaInterventoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventiRiportaInBozzaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventiStatoRifiutoVistoRagioneriaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventiStatoVistoRagioneriaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoNonAvviatoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoPrendiInCaricoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoAnnullatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoApprovatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoBozzaDaRifiutoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoVistoEValidatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoVistoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoVolturaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.DeleteInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.DeleteInterventoPhysicallyByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetCigByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetControlloCancellazioneInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiByCapofilaIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiByCuiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiCapofilaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoByCuiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoImportiByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoInProgrammaFuturoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiXCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetStatiInterventoByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetStoricoRupsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetUltimoStatoInfoByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostControlloCapofilaXCopiaInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventiCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventiCopiaV2Request;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostUploadCsvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutAvviaInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventiRiportaInBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventiStatoRifiutoVistoRagioneriaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventiStatoVistoRagioneriaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoNonAvviatoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoPrendiInCaricoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoApprovatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoBozzaDaRifiutoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoEValidatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoVolturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.DeleteInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.DeleteInterventoPhysicallyByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetCigByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetControlloCancellazioneInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiByCapofilaIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiByCuiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiCapofilaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoByCuiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoImportiByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoInProgrammaFuturoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiXCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetStatiInterventoByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetStoricoRupsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetUltimoStatoInfoByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostControlloCapofilaXCopiaInterventiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventiCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventiCopiaV2Response;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostUploadCsvResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutAvviaInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventiRiportaInBozzaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventiStatoRifiutoVistoRagioneriaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventiStatoVistoRagioneriaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoNonAvviatoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoPrendiInCaricoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoApprovatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoBozzaDaRifiutoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoEValidatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoVolturaResponse;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.FileHolder;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.CopiaInterventoWrapper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Fa&ccedil;ade for the /intervento path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class InterventoFacade extends BaseFacade {
	@EJB private InterventoFacade self;
	// Injection point
	@Inject private InterventoDad 				interventoDad;
	@Inject private DecodificaDad 				decodificaDad;
	@Inject private ProgrammaDad  				programmaDad;
	@Inject private UtenteDad     				utenteDad;
	@Inject private EnteDad       				enteDad;
	@Inject private InterventoImportiDad 		interventoImportiDad;
	@Inject private ElaborazioneDad 			elaborazioneDad;
	@Inject private ElaborazioneTipoDad 		elaborazioneTipoDad;
	@Inject private ElaborazioneMessaggioDad 	elaborazioneMessaggioDad;
	@Inject private CommonDad       			commonDad;
	@Inject private SettoreDad       			settoreDad;
	@Inject private SystemDad       			systemDad;
	@Inject private NotificheDad       			notificheDad;
	
	
	/**
	 * Deletes the Intervento by its id
	 * @param id the id
	 * @return nothing
	 */
	@Lock(LockType.WRITE)
	public DeleteInterventoByIdResponse deleteInterventoById(UUID id) {
		return executeService(new DeleteInterventoByIdRequest(id), new DeleteInterventoByIdService(configurationHelper, interventoDad));
	}

	public DeleteInterventoPhysicallyByIdResponse deleteInterventoPhysicallyById(UUID id) {
		return executeService(new DeleteInterventoPhysicallyByIdRequest(id), new DeleteInterventoPhysicallyByIdService(configurationHelper, interventoDad));
	}

	/**
	 * Retrieves the Interventi
	 * @param page the page
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 * @return the interventos
	 */
	public GetRicercaInterventiResponse getRicercaInterventi(Integer page, Integer limit, String sort, String direction, Intervento intervento) {
		return executeService(new GetRicercaInterventiRequest(page, limit, sort, direction, intervento), new GetRicercaInterventiService(configurationHelper, interventoDad, commonDad));
	}

	/**
	 * Retrieves the Intervento by its id
	 * @param id the id
	 * @return the intervento
	 */
	public GetInterventoByIdResponse getInterventoById(UUID idIntervento) {
		return executeService(new GetInterventoByIdRequest(idIntervento), new GetInterventoByIdService(configurationHelper, interventoDad));
	}

	public GetInterventoInProgrammaFuturoByIdResponse getInterventoInProgrammaFuturoById(UUID idIntervento) {
		return executeService(new GetInterventoInProgrammaFuturoByIdRequest(idIntervento), new GetInterventoInProgrammaFuturoByIdService(configurationHelper, interventoDad));
	}
	/**
	 * Gets the InterventoImporti by the Intervento id
	 * @param id the id of the Intervento
	 * @param page the page
	 * @param limit the limit
	 * @return the interventoImportis
	 */
	public GetInterventoImportiByInterventoResponse getInterventoImportiByIntervento(UUID id, Integer page, Integer limit) {
		return executeService(new GetInterventoImportiByInterventoRequest(id, page, limit), new GetInterventoImportiByInterventoService(configurationHelper, interventoImportiDad));
	}

	/**
	 * Posts an Intervento
	 * @param intervento
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PostInterventoResponse postIntervento(Intervento intervento) {
		return executeService(new PostInterventoRequest(intervento), new PostInterventoService(configurationHelper, interventoDad, decodificaDad,programmaDad,systemDad));
	}

	/**
	 * Posts copia interventi
	 * @param interventi
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param idProgramma
	 * @return the interventi
	 */
	@Lock(LockType.WRITE)
	public PostInterventiCopiaResponse postInterventiCopia(List<Intervento> interventi, String interventoCopiaTipo,String interventoImportoCopiaTipo,UUID idProgramma) {
		return executeService(new PostInterventiCopiaRequest(interventi, interventoCopiaTipo, interventoImportoCopiaTipo, idProgramma,null), new PostInterventiCopiaService(configurationHelper, interventoDad, programmaDad, decodificaDad, systemDad));
	}
	/**
	 * Posts copia interventi
	 * @param interventi
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param idProgramma
	 * @return the interventi
	 */
	@Lock(LockType.WRITE)
	public PostInterventiCopiaV2Response postInterventiCopiaV2(List<CopiaInterventoWrapper> listCopiaInterventoWrapper, UUID idProgramma, String stato) {
		return executeService(new PostInterventiCopiaV2Request(listCopiaInterventoWrapper, idProgramma, stato), new PostInterventiCopiaV2Service(configurationHelper, interventoDad, programmaDad, decodificaDad, systemDad));
	}

	/**
	 * Puts an Intervento
	 * @param id the id
	 * @param intervento
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PutInterventoResponse putInterventoById(UUID id, Intervento intervento) {
		return executeService(new PutInterventoRequest(setId(id, intervento)), new PutInterventoService(configurationHelper, interventoDad, interventoImportiDad, decodificaDad,programmaDad));
	}

	/**
	 * Retrieves the Intervento by its id
	 * @param cui the cui
	 * @param idProgramma the id programma
	 * @return the intervento
	 */
	public GetInterventoByCuiResponse getInterventoByCui(String cui, UUID idProgramma) {
		return executeService(new GetInterventoByCuiRequest(cui, idProgramma), new GetInterventoByCuiService(configurationHelper, interventoDad));
	}

	/**
	 * Retrieves the Intervento by its id
	 * @param cui the cui
	 * @param idProgramma the id programma
	 * @return the intervento
	 */
	public GetInterventiByCuiResponse getInterventiByCui(String cui) {
		return executeService(new GetInterventiByCuiRequest(cui), new GetInterventiByCuiService(configurationHelper, interventoDad));
	}

	/**
	 * Puts an stato annullato Intervento
	 * @param id the id
	 * @param intervento
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PutInterventoStatoAnnullatoResponse putInterventoStatoAnnullatoById(UUID id,UUID settoreId, Intervento intervento) {
		return executeService(new PutInterventoStatoAnnullatoRequest(setId(id,intervento),settoreId), new PutInterventoStatoAnnullatoService(configurationHelper, interventoDad, utenteDad));
	}


	/**
	 * Puts an stato approvato Intervento
	 * @param id the id
	 * @param intervento
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PutInterventoStatoApprovatoResponse putInterventoStatoApprovatoById(UUID id, Intervento intervento) {
		return executeService(new PutInterventoStatoApprovatoRequest(setId(id,intervento)), new PutInterventoStatoApprovatoService(configurationHelper, interventoDad, decodificaDad));
	}

	/**
	 * Puts an stato approvato Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PutInterventoStatoApprovatoResponse putInterventiStatoApprovato(List<Intervento> interventi) {
		for(final Intervento intervento : interventi) {
			self.putInterventoStatoApprovatoById(intervento.getId(), intervento) ;
		}
		return new PutInterventoStatoApprovatoResponse();
	}

	/**
	 * Puts an stato annullato Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@Lock(LockType.WRITE)
	public PutInterventoStatoAnnullatoResponse putInterventiStatoAnnullato(UUID settoreId, List<Intervento> interventi) {
		for(final Intervento intervento : interventi) {
			self.putInterventoStatoAnnullatoById(intervento.getId(),settoreId, intervento) ;
		}
		return new PutInterventoStatoAnnullatoResponse();
	}

	/**
	 * Posts the upload of a CSV
	 * @param fileHolder
	 * @return the response
	 */
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 10)
	@Lock(LockType.WRITE)
	public PostUploadCsvResponse postUploadCsv(FileHolder fileHolder ) {
		return executeService(new PostUploadCsvRequest(fileHolder), new PostUploadCsvService(configurationHelper, interventoDad, decodificaDad, programmaDad, utenteDad, interventoImportiDad, enteDad, elaborazioneDad, elaborazioneTipoDad, elaborazioneMessaggioDad, settoreDad, commonDad, systemDad));
	}
	/**
	 *
	 * @param page
	 * @param limit
	 * @param sort
	 * @param direction
	 * @param programmaIdOld
	 * @param programmaIdNew
	 * @return
	 */
	public GetRicercaInterventiXCopiaResponse getRicercaInterventiXCopia(Integer page, Integer limit, String sort, String direction,UUID programmaIdOld, UUID programmaIdNew, UUID utenteRupId) {
		return executeService(new GetRicercaInterventiXCopiaRequest(page, limit, sort, direction, programmaIdOld,programmaIdNew, utenteRupId), new GetRicercaInterventiXCopiaService(configurationHelper, interventoDad));
	}

	public GetCpvsByInterventoIdResponse getCpvsByInterventoId(UUID id) {
		return executeService(new GetCpvsByInterventoIdRequest(id), new GetCpvsByInterventoIdService(configurationHelper, interventoDad));
	}

	@Lock(LockType.WRITE)
	public PostCpvsByInterventoIdResponse postCpvsByInterventoId(UUID idIntervento, List<Cpv> cpvs) {
		return executeService(new PostCpvsByInterventoIdRequest(idIntervento,cpvs), new PostCpvsByInterventoIdService(configurationHelper, interventoDad));
	}

	public GetStoricoRupsByInterventoIdResponse getStoricoRupssByInterventoId(UUID id) {
		return executeService(new GetStoricoRupsByInterventoIdRequest(id), new GetStoricoRupsByInterventoIdService(configurationHelper, interventoDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventoVolturaResponse putInterventoVoltura(UUID idSettore, UUID idRup, List<Intervento> listaInterventiId) {
		return executeService(new PutInterventoVolturaRequest(idSettore, idRup, listaInterventiId), new PutInterventoVolturaService(configurationHelper, interventoDad, utenteDad, settoreDad,notificheDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventoStatoVistoResponse putInterventiStatoVisto(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoVistoRequest(interventi), new PutInterventoStatoVistoService(configurationHelper, interventoDad, utenteDad, decodificaDad, systemDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventoStatoBozzaDaRifiutoResponse putInterventiStatoBozzaDaRifiuto(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoBozzaDaRifiutoRequest(interventi), new PutInterventoStatoBozzaDaRifiutoService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventoStatoVistoEValidatoResponse putInterventiStatoVistoEValidato(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoVistoEValidatoRequest(interventi), new PutInterventoStatoVistoEValidatoService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}

	public GetUltimoStatoInfoByInterventoResponse getUltimoStatoInfoByIntervento(UUID idIntervento) {
		return executeService(new GetUltimoStatoInfoByInterventoRequest(idIntervento), new GetUltimoStatoInfoByInterventoService(configurationHelper, interventoDad));
	}

	public GetStatiInterventoByInterventoIdResponse getStatiInterventoByInterventoId(UUID idIntervento) {
		return executeService(new GetStatiInterventoByInterventoIdRequest(idIntervento), new GetStatiInterventoByInterventoIdService(configurationHelper, interventoDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventoPrendiInCaricoResponse putInterventoPrendiInCarico(List<Intervento> listaInterventiId) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		return executeService(new PutInterventoPrendiInCaricoRequest(utenteConnesso.getId(), listaInterventiId), new PutInterventoPrendiInCaricoService(configurationHelper, interventoDad, utenteDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventiStatoVistoRagioneriaResponse putInterventiStatoVistoRagioneria(List<Intervento> interventi) {
		return executeService(new PutInterventiStatoVistoRagioneriaRequest(interventi), new PutInterventiStatoVistoRagioneriaService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventiStatoRifiutoVistoRagioneriaResponse putInterventiStatoRifiutoVistoRagioneria(List<Intervento> interventi) {
		return executeService(new PutInterventiStatoRifiutoVistoRagioneriaRequest(interventi), new PutInterventiStatoRifiutoVistoRagioneriaService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}

	public GetInterventiCapofilaResponse getInterventiCapofila(UUID programmaId, Intervento intervento) {
		return executeService(new GetInterventiCapofilaRequest(programmaId , intervento), new GetInterventiCapofilaService(configurationHelper, interventoDad));
	}

	public GetInterventiByCapofilaIdResponse getInterventibyCapofilaId(UUID capofilaId) {
		return executeService(new GetInterventiByCapofilaIdRequest(capofilaId), new GetInterventiByCapofilaIdService(configurationHelper, interventoDad));
	}

	public PostControlloCapofilaXCopiaInterventiResponse postControlloCapofilaXCopiaInterventi(List<Intervento> interventi) {
		return executeService(new PostControlloCapofilaXCopiaInterventiRequest(interventi), new PostControlloCapofilaXCopiaInterventiService(configurationHelper, interventoDad));
	}

	public GetControlloCancellazioneInterventoResponse postControlloCancellazioneIntervento(List<Intervento> interventi) {
		return executeService(new GetControlloCancellazioneInterventoRequest(interventi), new GetControlloCancellazioneInterventoService(configurationHelper, interventoDad));
	}

	public GetCigByInterventoIdResponse getCigByInterventoId(UUID idIntervento) {
		return executeService(new GetCigByInterventoIdRequest(idIntervento), new GetCigByInterventoIdService(configurationHelper, interventoDad));
	}

	@Lock(LockType.WRITE)
	public PutInterventiRiportaInBozzaResponse putInterventiRiportaInBozza(List<Intervento> interventi) {
		return executeService(new PutInterventiRiportaInBozzaRequest(interventi), new PutInterventiRiportaInBozzaService(configurationHelper, interventoDad, utenteDad, decodificaDad,systemDad,notificheDad));
	}
	public PutAvviaInterventoByIdResponse putAvviaInterventoById(UUID idIntervento,Integer motivoEsclusioneCigId, List<String> cigs) {
		return executeService(new PutAvviaInterventoByIdRequest(idIntervento,motivoEsclusioneCigId, cigs), new PutAvviaInterventoByIdService(configurationHelper, interventoDad,utenteDad));
	}

	public PutInterventoNonAvviatoByIdResponse putInterventoNonAvviatoById(UUID idIntervento) {
		return executeService(new PutInterventoNonAvviatoByIdRequest(idIntervento), new PutInterventoNonAvviatoByIdService(configurationHelper, interventoDad,utenteDad));
	}



}
