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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
//import com.sun.jersey.core.header.FormDataContentDisposition;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.DeleteInterventoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetCpvsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoByCuiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetInterventoImportiByInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetRicercaInterventiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetRicercaInterventiXCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetStoricoRupsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.GetUltimoStatoInfoByInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostArchivioRupsByInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostCpvsByInterventoIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostInterventiCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostUploadCsvService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoAnnullatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoApprovatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoBozzaDaRifiutoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoVistoEValidatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PutInterventoStatoVistoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.DeleteInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoByCuiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoImportiByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiXCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetStoricoRupsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetUltimoStatoInfoByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostArchivioRupsByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventiCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostUploadCsvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoApprovatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoBozzaDaRifiutoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoEValidatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoVistoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.DeleteInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoByCuiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoImportiByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiXCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetStoricoRupsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetUltimoStatoInfoByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostArchivioRupsByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventiCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostUploadCsvResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoApprovatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoBozzaDaRifiutoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoEValidatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoVistoResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.FileHolder;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Fa&ccedil;ade for the /intervento path
 */
@Stateless
@Lock(LockType.READ)
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
	/**
	 * Deletes the Intervento by its id
	 * @param id the id
	 * @return nothing
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteInterventoByIdResponse deleteInterventoById(UUID id) {
		return executeService(new DeleteInterventoByIdRequest(id), new DeleteInterventoByIdService(configurationHelper, interventoDad));
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaInterventiResponse getRicercaInterventi(Integer page, Integer limit, String sort, String direction, Intervento intervento, UUID settoreId) {
		return executeService(new GetRicercaInterventiRequest(page, limit, sort, direction, intervento, settoreId), new GetRicercaInterventiService(configurationHelper, interventoDad, commonDad));
	}
	/**
	 * Retrieves the Intervento by its id
	 * @param id the id
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetInterventoByIdResponse getInterventoById(UUID id) {
		return executeService(new GetInterventoByIdRequest(id), new GetInterventoByIdService(configurationHelper, interventoDad));
	}
	/**
	 * Gets the InterventoImporti by the Intervento id
	 * @param id the id of the Intervento
	 * @param page the page
	 * @param limit the limit
	 * @return the interventoImportis
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetInterventoImportiByInterventoResponse getInterventoImportiByIntervento(UUID id, Integer page, Integer limit) {
		return executeService(new GetInterventoImportiByInterventoRequest(id, page, limit), new GetInterventoImportiByInterventoService(configurationHelper, interventoImportiDad));
	}
	/**
	 * Posts an Intervento
	 * @param intervento
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostInterventoResponse postIntervento(Intervento intervento) {
		return executeService(new PostInterventoRequest(intervento), new PostInterventoService(configurationHelper, interventoDad, decodificaDad,programmaDad));
	}
	
	/**
	 * Posts an copia Intervento
	 * @param intervento
	 * @param interventoCopiaTipo 
	 * @param interventoImportoCopiaTipo 
	 * @param idProgramma 
	 * @return the intervento
	 */
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	//@Lock(LockType.WRITE)
	//public PostInterventoCopiaResponse postInterventoCopia(Intervento intervento,String interventoCopiaTipo,String interventoImportoCopiaTipo,UUID idProgramma) {
	//	return executeService(new PostInterventoCopiaRequest(intervento, interventoCopiaTipo, interventoImportoCopiaTipo, idProgramma), new PostInterventiCopiaService(configurationHelper, interventoDad, programmaDad));
	//}

	/**
	 * Posts copia interventi
	 * @param interventi
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param idProgramma
	 * @return the interventi
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostInterventiCopiaResponse postInterventiCopia(List<Intervento> interventi, String interventoCopiaTipo,String interventoImportoCopiaTipo,UUID idProgramma) {
		return executeService(new PostInterventiCopiaRequest(interventi, interventoCopiaTipo, interventoImportoCopiaTipo, idProgramma), new PostInterventiCopiaService(configurationHelper, interventoDad, programmaDad));

	}
	
	/**
	 * Puts an Intervento
	 * @param id the id
	 * @param intervento
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetInterventoByCuiResponse getInterventoByCui(String cui, UUID idProgramma, UUID settoreId) {
		return executeService(new GetInterventoByCuiRequest(cui, idProgramma, settoreId), new GetInterventoByCuiService(configurationHelper, interventoDad));
	}
	
	/**
	 * Puts an stato annullato Intervento
	 * @param id the id
	 * @param intervento
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoApprovatoResponse putInterventoStatoApprovatoById(UUID id, Intervento intervento) {
		return executeService(new PutInterventoStatoApprovatoRequest(setId(id,intervento)), new PutInterventoStatoApprovatoService(configurationHelper, interventoDad, decodificaDad));
	}
	

	/**
	 * Puts an stato approvato Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoApprovatoResponse putInterventiStatoApprovato(List<Intervento> interventi) {
		for(Intervento intervento : interventi) {
			self.putInterventoStatoApprovatoById(intervento.getId(), intervento) ;
		}
		return new PutInterventoStatoApprovatoResponse();
	}
	
	/**
	 * Puts an stato annullato Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoAnnullatoResponse putInterventiStatoAnnullato(UUID settoreId, List<Intervento> interventi) {
		for(Intervento intervento : interventi) {
			self.putInterventoStatoAnnullatoById(intervento.getId(),settoreId, intervento) ;
		}		
		return new PutInterventoStatoAnnullatoResponse();
	}

	/**
	 * Posts the upload of a CSV
	 * @param fileHolder
	 * @return the response
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostUploadCsvResponse postUploadCsv(FileHolder fileHolder ) {
		return executeService(new PostUploadCsvRequest(fileHolder), new PostUploadCsvService(configurationHelper, interventoDad, decodificaDad, programmaDad, utenteDad, interventoImportiDad, enteDad, elaborazioneDad, elaborazioneTipoDad, elaborazioneMessaggioDad, settoreDad));
	}
	
	/**
	 * Retrieves the Interventi
	 * @param page the page
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param programmaIdOld 
	 * @param programmaIdNew 
	 * @return the interventos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaInterventiXCopiaResponse getRicercaInterventiXCopia(Integer page, Integer limit, String sort, String direction,UUID programmaIdOld, UUID programmaIdNew) {
		return executeService(new GetRicercaInterventiXCopiaRequest(page, limit, sort, direction, programmaIdOld,programmaIdNew), new GetRicercaInterventiXCopiaService(configurationHelper, interventoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetCpvsByInterventoIdResponse getCpvsByInterventoId(UUID id) {
		return executeService(new GetCpvsByInterventoIdRequest(id), new GetCpvsByInterventoIdService(configurationHelper, interventoDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostCpvsByInterventoIdResponse postCpvsByInterventoId(UUID idIntervento, List<Cpv> cpvs) {
		return executeService(new PostCpvsByInterventoIdRequest(idIntervento,cpvs), new PostCpvsByInterventoIdService(configurationHelper, interventoDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetStoricoRupsByInterventoIdResponse getStoricoRupssByInterventoId(UUID id) {
		return executeService(new GetStoricoRupsByInterventoIdRequest(id), new GetStoricoRupsByInterventoIdService(configurationHelper, interventoDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostArchivioRupsByInterventoResponse putInterventoVoltura(UUID idRup, List<Intervento> listaInterventiId) {
		return executeService(new PostArchivioRupsByInterventoRequest(idRup, listaInterventiId), new PostArchivioRupsByInterventoService(configurationHelper, interventoDad, utenteDad));
	}
	
	/**
	 * Puts an stato visto Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoVistoResponse putInterventiStatoVisto(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoVistoRequest(interventi), new PutInterventoStatoVistoService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}
	/**
	 * Puts an stato bozza Interventi
	 * @param interventi the interventi
	 * @return the intervento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoBozzaDaRifiutoResponse putInterventiStatoBozzaDaRifiuto(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoBozzaDaRifiutoRequest(interventi), new PutInterventoStatoBozzaDaRifiutoService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutInterventoStatoVistoEValidatoResponse putInterventiStatoVistoEValidato(List<Intervento> interventi) {
		return executeService(new PutInterventoStatoVistoEValidatoRequest(interventi), new PutInterventoStatoVistoEValidatoService(configurationHelper, interventoDad, utenteDad, decodificaDad));
	}
	
	
	public GetUltimoStatoInfoByInterventoResponse getUltimoStatoInfoByIntervento(UUID idIntervento) {
		return executeService(new GetUltimoStatoInfoByInterventoRequest(idIntervento), new GetUltimoStatoInfoByInterventoService(configurationHelper, interventoDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostArchivioRupsByInterventoResponse putInterventoPrendiInCarico(List<Intervento> listaInterventiId) {
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		// se fosse necessario implementare logiche differenti dalla voltura creare un servizio dedicato alla presa in carico
		return executeService(new PostArchivioRupsByInterventoRequest(utenteConnesso.getId(), listaInterventiId), new PostArchivioRupsByInterventoService(configurationHelper, interventoDad, utenteDad));
	}

}
