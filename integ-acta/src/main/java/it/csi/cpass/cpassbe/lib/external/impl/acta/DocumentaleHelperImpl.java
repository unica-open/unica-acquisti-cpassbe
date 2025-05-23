/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.acta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.DocumentaleHelper;
import it.csi.cpass.cpassbe.lib.external.dto.GestoreDocumentoActa;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.ArchiviaOrdineActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.CercaProtocolloActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.CercaStrutturaAggregativaXIndiceClassificazioneActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.CercaStrutturaAggregativaXStrutturaAggregativaActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.ProtocollazioneActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.VerificaArchiviazioneOrdineActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.VerificaDocumentoProtocollatoActaServiceWrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseActaHelperImpl;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.utils.ActaConfigurationParams;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.ObjectResponseType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.objectservice.AcarisException;

/**
 * Example POJO helper impl
 */
public class DocumentaleHelperImpl extends BaseActaHelperImpl implements DocumentaleHelper {
	
	private GestoreDocumentoActa gda = new GestoreDocumentoActa();
			
	/**
	 * @param params
	 * @return
	 */
	private String getCfUtente(Map<String, String> params) {
		String cfUtente;
		cfUtente = params.get(ActaConfigurationParams.CODICE_FISCALE_UTENTE.getParamName());
		if(cfUtente==null || cfUtente.trim().equals("")) {
			cfUtente = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getCodiceFiscale();
		}
		//TODO prova errore cf
		//cfUtente = "BNDNNN74M19L219J";
		return cfUtente;
	}


	/**
	 * @param params
	 * @return
	 */
	private Long getAoo(Map<String, String> params,Long idAOO) {
		String aooforzatura = params.get(ActaConfigurationParams.ID_AOO.getParamName());		
		if(aooforzatura!=null && !aooforzatura.equals("")) {
			idAOO = Long.valueOf(params.get(ActaConfigurationParams.ID_AOO.getParamName()));
		}
		return idAOO;
	}	
	
	@Override
	public ExternalServiceResponseWrapper<ProtocolloOrdine> getProtocolloOrigin(Map<String, String> params,Integer anno, String numero,Long idAOO,String settoreCorrenteCodice) {		
		log.info("DocumentaleHelperImpl", "*************getProtocolloOrdineOrigin ACTA *******************");
		checkBaseParameters(params);
		ProtocolloOrdine protocollo = new ProtocolloOrdine();														
		//Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		ExternalServiceResponseWrapper<ProtocolloOrdine> response = new ExternalServiceResponseWrapper<>();
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);	
		
		if(idAOO==null) {
			//CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato"), true); // corretto ? 	
			protocollo.setCodErrore(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			protocollo.setDescErrore(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			log.error("DocumentaleHelperImpl", "Desc error --> "+CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato"));
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			response.setResponse(protocollo);
			return response;
		} 
		CercaProtocolloActaServiceWrapper cercaProtocolloActaServiceWrapper = new CercaProtocolloActaServiceWrapper();
		
		//scommentare nel caso si voglia utilizzare apimanager
		//addHandlerResolver(params, ????);
		
		try {
			
			cercaProtocolloActaServiceWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
													cfUtente,
													idAOO,
													null,
													null,
													params.get(ActaConfigurationParams.APPKEY.getParamName()),
													params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));	

			protocollo = cercaProtocolloActaServiceWrapper.cercaProtocolloActa(anno, numero);
			protocollo.setAooOrig("" + idAOO);
			protocollo.setAnnoProtocolloOrig(anno);
			protocollo.setNumeroProtocolloOrig(numero);
			response.setSuccess(Boolean.TRUE);
			if(!cercaProtocolloActaServiceWrapper.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(cercaProtocolloActaServiceWrapper.getErrorDesc());
				response.setErrors(errors);				
				response.setSuccess(Boolean.FALSE);
			}
			response.setResponse(protocollo);
			
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "ERRORE INIT ", e);
			response.setSuccess(Boolean.FALSE);
			List<String> errors = new ArrayList<String>();
			//todo da discriminare quando il generico e quando il propagato
			errors.add("Servizio Acta getProtocolloOrigine non disponibile o protocollo non trovato");
			errors.add( e.getMessage() != null ? e.getMessage() : "");
			response.setErrors(errors);
			protocollo.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Acta non disponibile").getFullErrorMessage());
			protocollo.setDescErrore(e.getMessage());
			log.error("DocumentaleHelperImpl", "Desc error --> "+e.getMessage());
			response.setResponse(protocollo);
			return response;
		}
		response.setResponse(protocollo);
		log.info("getProtocolloOrigin", "****** Fine getProtocolloOrdineOrigin ACTA *******************");
		return response;

	}

	@Override
	public ExternalServiceResponseWrapper<String> getSerieTipologica(Map<String, String> params, String ufficio,Long idAOO,String settoreCorrenteCodice) {
		log.info("DocumentaleHelperImpl", "*************getSerieTipologica ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);		
		ArchiviaOrdineActaServiceWrapper archiviaOrdineActaServiceWrapper = new ArchiviaOrdineActaServiceWrapper();
		ExternalServiceResponseWrapper<String> response = new ExternalServiceResponseWrapper<>();
		
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		} 

		try {
			archiviaOrdineActaServiceWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
													cfUtente,
													idAOO,
													null,
													null,
													params.get(ActaConfigurationParams.APPKEY.getParamName()),
													params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));
			String uuidSerieTipologica = archiviaOrdineActaServiceWrapper.getSerieTipologicaUUID(ufficio);
			response.setSuccess(Boolean.TRUE);
			if(!archiviaOrdineActaServiceWrapper.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(archiviaOrdineActaServiceWrapper.getErrorDesc());
				response.setErrors(errors);
				response.setSuccess(Boolean.FALSE);
			}
			response.setResponse(uuidSerieTipologica);

			
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl ", " getSerieTipologica ERRORE INIT ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Acta Serie Tipologica non disponibile");
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", " getSerieTipologica Desc error --> "+e.getMessage());
			return response;
		}
		log.info("getSerieTipologica", "****** Fine getSerieTipologica ACTA *******************");
		return response;
	}
	
	@Override
	public ExternalServiceResponseWrapper<GestoreDocumentoActa> archiviaOrdineNelVolume(Map<String, String> params,
			String uuidSerieTipologica, String ufficio, byte[] fileDoc, TestataOrdine testataOrdine,
			Utente utenteCompilatore, Ente ente, String autoreGiuridico, String destinatarioGiuridico,
			String destinatarioFisico, Long idAOO, String settoreCorrenteCodice) {

		log.info("DocumentaleHelperImpl", "*************archiviaOrdine ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params, idAOO);
		//cfUtente = "CPSTNT80A01L219S"; 
		//Long idAOO = 276L;
		String methodName = "archiviaOrdine";
		ArchiviaOrdineActaServiceWrapper archiviaOrdineActaServiceWrapper = new ArchiviaOrdineActaServiceWrapper();
		ExternalServiceResponseWrapper<GestoreDocumentoActa> response = new ExternalServiceResponseWrapper<>();

		if (idAOO == null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error", "Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add(CoreError.GENERIC_ERROR.getError("error", "Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		}

		try {
			log.info(methodName, "init");
			archiviaOrdineActaServiceWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
					cfUtente, idAOO, null, null, params.get(ActaConfigurationParams.APPKEY.getParamName()),
					params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));

			//N.B. in teoria questa porzione di codice non dovrebbe mai essere interessata perchè mi aspetto che esista il record in locale sulla CPASS_R_UFFICIO_SERIE
			if (uuidSerieTipologica == null) {
				uuidSerieTipologica = archiviaOrdineActaServiceWrapper.getSerieTipologicaUUID(ufficio);
				if (!archiviaOrdineActaServiceWrapper.getErrorDesc().equals("")) {
					log.error(methodName, "serie tipologica non presente ufficio 'ORDINI " + ufficio + "' "+ archiviaOrdineActaServiceWrapper.getErrorDesc());
					List<String> errors = new ArrayList<String>();
					errors.add(archiviaOrdineActaServiceWrapper.getErrorDesc());
					response.setErrors(errors);
					response.setSuccess(Boolean.FALSE);
					return response;
				}
				log.warn(methodName,"La serie tipologica ricercata esiste su acta ma non in locale si dovrebbe procedere con l'inserimento in locale");
			}

			// estraggo l'objectId della serie tipologica
			ObjectIdType objectIdSerieTipologica = archiviaOrdineActaServiceWrapper.getSerieTipologicaObjectId(ufficio);
			log.info(methodName, "objectIdSerieTipologica " + objectIdSerieTipologica.toString());
			
			
			ObjectIdType volumeObjectId = archiviaOrdineActaServiceWrapper.getVolumeUUIDByAnnoCorrente(objectIdSerieTipologica);
			if(volumeObjectId == null) {
				volumeObjectId = archiviaOrdineActaServiceWrapper.getVolumeUUIDByUfficio(ufficio,objectIdSerieTipologica);
				if(volumeObjectId == null) {
					// Creo il Volume
					volumeObjectId= archiviaOrdineActaServiceWrapper.createVolumeFolder(objectIdSerieTipologica, ufficio);					
				}
			}
			
			Long statoEfficacia = Long.valueOf(params.get(ActaConfigurationParams.STATO_EFFICACIA.getParamName()));
			Long fdo = Long.valueOf(params.get(ActaConfigurationParams.FORMA_DOC_ORDINI.getParamName()));
			Long vrc = Long.valueOf(params.get(ActaConfigurationParams.VITAL_RECORD_CODE.getParamName()));
			String uuidDocumentoOrig = null;
			String struttAggregativaObjIdValue = null;
			if (testataOrdine.getProtocolloOrdines() != null && testataOrdine.getProtocolloOrdines().size() > 0) {
				uuidDocumentoOrig = testataOrdine.getProtocolloOrdines().get(0).getUuidDocumentoOrig();
				struttAggregativaObjIdValue = testataOrdine.getProtocolloOrdines().get(0).getStrutturaAggregativaObjectId();
			}

			log.info(methodName, "creaDocumentoElettronicoNonFirmato");
			//Oggetto del documento ricercato e poi eventualmente creato
			String oggettoOrdinePrefix= "ORDINE " + testataOrdine.getAnno() + "/"+testataOrdine.getNumero();
			String oggettoOrdine= oggettoOrdinePrefix + ": "+testataOrdine.getDescrizione();
			
			Boolean objExists = controlloPresenzaDocByOggetto(archiviaOrdineActaServiceWrapper,uuidSerieTipologica,volumeObjectId,oggettoOrdinePrefix);
			if(!objExists) {
				gda = archiviaOrdineActaServiceWrapper.creaDocumentoElettronicoNonFirmato(volumeObjectId, ufficio,
						fileDoc, testataOrdine, utenteCompilatore, ente, statoEfficacia, vrc, fdo, autoreGiuridico,
						destinatarioGiuridico, destinatarioFisico, uuidDocumentoOrig, struttAggregativaObjIdValue,oggettoOrdine);
			}else{
				log.warn(methodName, "IL documento è già stato archiviato sul sistema contabile");
			}


			
			if (!archiviaOrdineActaServiceWrapper.getErrorDesc().equals("")) {
				log.error(methodName, "errore dal salvataggio del documento " + archiviaOrdineActaServiceWrapper.getErrorDesc());
				List<String> errors = new ArrayList<String>();
				errors.add(archiviaOrdineActaServiceWrapper.getErrorDesc());
				response.setErrors(errors);
				response.setSuccess(Boolean.FALSE);
			} else {
				gda.setUuidSerieTipologica(uuidSerieTipologica);
				gda.setObjectIdVolume(volumeObjectId.getValue());
				response.setSuccess(Boolean.TRUE);
				response.setResponse(gda);
			}
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "archiviaOrdine ERRORE INIT ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", "archiviaOrdine Desc error --> " + e.getMessage());
			return response;
		}
		response.setResponse(gda);
		log.info("DocumentaleHelperImpl", "****** Fine archiviaOrdine ACTA *******************");
		return response;
	}
	
	/**
	 * versione antecedente alla gestione con volume
	 * @param params
	 * @param uuidSerieTipologica
	 * @param ufficio
	 * @param fileDoc
	 * @param testataOrdine
	 * @param utenteCompilatore
	 * @param ente
	 * @param autoreGiuridico
	 * @param destinatarioGiuridico
	 * @param destinatarioFisico
	 * @param idAOO
	 * @param settoreCorrenteCodice
	 * @return
	 */
	public ExternalServiceResponseWrapper<GestoreDocumentoActa> archiviaOrdineInSerieTipologica(Map<String, String> params,
																	String uuidSerieTipologica,
																	String ufficio,byte[] fileDoc,
																	TestataOrdine testataOrdine,
																	Utente utenteCompilatore,
																	Ente ente,
																	String autoreGiuridico,
																	String destinatarioGiuridico,
																	String destinatarioFisico,
																	Long idAOO,
																	String settoreCorrenteCodice
			) {
		
		log.info("DocumentaleHelperImpl", "*************archiviaOrdine ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);	
		//cfUtente = "CPSTNT80A01L219S"; 
		//Long idAOO = 276L;
		String methodName="archiviaOrdine";
		ArchiviaOrdineActaServiceWrapper archiviaOrdineActaServiceWrapper = new ArchiviaOrdineActaServiceWrapper();
		ExternalServiceResponseWrapper<GestoreDocumentoActa> response = new ExternalServiceResponseWrapper<>();
		
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add( CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		} 
	
		try {
			log.info(methodName, "init");
			archiviaOrdineActaServiceWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
													cfUtente,
													idAOO,
													null,
													null,
													params.get(ActaConfigurationParams.APPKEY.getParamName()),
													params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));

			//N.B. in teoria questa porzione di codice non dovrebbe mai essere interessata perchè mi aspetto che esista il record in locale sulla CPASS_R_UFFICIO_SERIE
			if(uuidSerieTipologica==null) {
				uuidSerieTipologica = archiviaOrdineActaServiceWrapper.getSerieTipologicaUUID(ufficio);				
				if(!archiviaOrdineActaServiceWrapper.getErrorDesc().equals("")) {
					log.error(methodName, "serie tipologica non presente ufficio 'ORDINI "+ufficio +"' " + archiviaOrdineActaServiceWrapper.getErrorDesc());
					List<String> errors = new ArrayList<String>();
					errors.add(archiviaOrdineActaServiceWrapper.getErrorDesc());
					response.setErrors(errors);
					response.setSuccess(Boolean.FALSE);
					return response;
				}
				log.warn(methodName, "La serie tipologica ricercata esiste su acta ma non in locale si dovrebbe procedere con l'inserimento in locale");
			}
			
			
			
			Long statoEfficacia = Long.valueOf(params.get(ActaConfigurationParams.STATO_EFFICACIA.getParamName()));
			Long fdo = Long.valueOf(params.get(ActaConfigurationParams.FORMA_DOC_ORDINI.getParamName()));
			Long vrc = Long.valueOf(params.get(ActaConfigurationParams.VITAL_RECORD_CODE.getParamName()));
			String uuidDocumentoOrig           = null;
			String struttAggregativaObjIdValue = null;
			if(testataOrdine.getProtocolloOrdines()!= null  && testataOrdine.getProtocolloOrdines().size() >0 ) {				
			//if(testataOrdine.getProtocolloOrdines()!= null  && testataOrdine.getProtocolloOrdines().size() >0 && testataOrdine.getProtocolloOrdines().get(0).getUuidDocumentoOrig() !=null ) {				
				uuidDocumentoOrig = testataOrdine.getProtocolloOrdines().get(0).getUuidDocumentoOrig();
				struttAggregativaObjIdValue = testataOrdine.getProtocolloOrdines().get(0).getStrutturaAggregativaObjectId();
			}

			QueryResponse queryResponse = archiviaOrdineActaServiceWrapper.queryObjectService("SerieTipologicaDocumentiView",uuidSerieTipologica,ConstantsActa.QUERY_UUID_SERIE,EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE);
			//Id Oggetto serieTipologica
			ObjectIdType serieTipologicaObjectId = queryResponse.getObject().getObjects().get(0).getObjectId();	

			log.info(methodName, "creaDocumentoElettronicoNonFirmato");
			
			// controllo se nella serie tipologica il documento èesiste gia'
			//Oggetto del documento ricercato e poi eventualmente creato
			String oggettoOrdinePrefix= "ORDINE " + testataOrdine.getAnno() + "/"+testataOrdine.getNumero();
			String oggettoOrdine= oggettoOrdinePrefix + ": "+testataOrdine.getDescrizione();

			Boolean objExists = controlloPresenzaDocByOggetto(archiviaOrdineActaServiceWrapper,uuidSerieTipologica,serieTipologicaObjectId,oggettoOrdine);		

			if(!objExists) {
				gda = archiviaOrdineActaServiceWrapper.creaDocumentoElettronicoNonFirmato(serieTipologicaObjectId,ufficio,fileDoc,testataOrdine, utenteCompilatore,ente,statoEfficacia, vrc,fdo, autoreGiuridico,destinatarioGiuridico, destinatarioFisico, uuidDocumentoOrig, struttAggregativaObjIdValue,oggettoOrdine);
			}else{
				log.warn(methodName, "IL documento è già stato archiviato sul sistema contabile");
			}
			
			if(!archiviaOrdineActaServiceWrapper.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(archiviaOrdineActaServiceWrapper.getErrorDesc());
				response.setErrors(errors);
				response.setSuccess(Boolean.FALSE);
			}else {
				gda.setUuidSerieTipologica(uuidSerieTipologica);
				response.setSuccess(Boolean.TRUE);
				response.setResponse(gda);
			}
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "archiviaOrdine ERRORE INIT ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", "archiviaOrdine Desc error --> "+e.getMessage());
			return response;
		}
		response.setResponse(gda);
		log.info("DocumentaleHelperImpl", "****** Fine archiviaOrdine ACTA *******************");
		return response;
	}

	/*
	@Override
	public ExternalServiceResponseWrapper<GestoreDocumentoActa> archiviaEProtocollaOrdine(Map<String, String> params,
			String serieTipologica, String ufficio, byte[] fileDoc, TestataOrdine testataOrdine,
			Utente utenteCompilatore, Ente ente, String autoreGiuridico, String destinatarioGiuridico,
			String destinatarioFisico, Long idAOO) {
		ExternalServiceResponseWrapper<ProtocolloOrdine> response = new ExternalServiceResponseWrapper<>();

		if( testataOrdine.getProtocolloOrdines().get(0).getUuidDocumentoOrig().isBlank())	{
			archiviaOrdine( params, serieTipologica,  ufficio,  fileDoc,  testataOrdine,utenteCompilatore,  ente,  autoreGiuridico,  destinatarioGiuridico, destinatarioFisico,  idAOO);
			response.setResponse(gda);
			
			if(gda!= null && gda.getError() != null && !gda.getError().equals("")) {
				log.error("archiviaEProtocollaOrdine", "archiviazione fallita");
				List<String> errors = new ArrayList<String>();
				errors.add(gda.getError());
				response.setSuccess(Boolean.FALSE);
				response.setErrors(errors);
				return response;
			}
			log.info("archiviaEProtocollaOrdine", "chiamo il protocollo");			
		}

		protocollaOrdine(params, idAOO, testataOrdine);
		
		response.setSuccess(Boolean.TRUE);
		response.setResponse(gda);
		if(gda!= null && gda.getError() != null && !gda.getError().equals("")) {
			List<String> errors = new ArrayList<String>();
			errors.add(gda.getError());
			response.setSuccess(Boolean.FALSE);
			response.setErrors(errors);
		}
		return response;
	}
	*/	
	
	@Deprecated
	@Override
	public ExternalServiceResponseWrapper<Boolean> verificaArchiviazioneOrdine(Map<String, String> params,Long idAOO,String settoreCorrenteCodice) {		
		log.info("DocumentaleHelperImpl", "*************verificaArchiviazioneOrdine ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);	
		//cfUtente = "CPSTNT80A01L219S"; 
		//Long idAOO = 276L;
		VerificaArchiviazioneOrdineActaServiceWrapper vaoasw = new VerificaArchiviazioneOrdineActaServiceWrapper();
		ExternalServiceResponseWrapper<Boolean> response = new ExternalServiceResponseWrapper<>();	
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		} 
		try {
			vaoasw.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
									cfUtente,
									idAOO,
									null,
									null,
									params.get(ActaConfigurationParams.APPKEY.getParamName()),
									params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));

			Boolean ris = vaoasw.verificaArchiviazione();
			response.setSuccess(ris);
			response.setResponse(true);			
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "archiviaOrdine ERRORE INIT ", e);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Acta Verifica Archiviazione non disponibile");
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", "archiviaOrdine Desc error --> "+e.getMessage());
			return response;
		}
		response.setResponse(true);
		log.info("DocumentaleHelperImpl", "****** Fine archiviaOrdine ACTA *******************");
		return response;
	}
	
	@Override
	public ExternalServiceResponseWrapper<GestoreDocumentoActa> verificaDocumentoProtocollato(Map<String, String> params,Long idAOO,String uuidDocumentoOrdine,String settoreCorrenteCodice) {	
		log.info("DocumentaleHelperImpl", "*************verificaDocumentoProtocollato ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);
		VerificaDocumentoProtocollatoActaServiceWrapper verificaDocumentoProtocollatoWrapper = new VerificaDocumentoProtocollatoActaServiceWrapper();
		ExternalServiceResponseWrapper<GestoreDocumentoActa> response = new ExternalServiceResponseWrapper<>();
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add( CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		} 
		if (uuidDocumentoOrdine==null) {
			response.setSuccess(Boolean.FALSE);
			List<String> errors = new ArrayList<String>();
			errors.add("UUID documento ordine non presente");
			response.setErrors(errors);
			return response;
		}	
		try {
			verificaDocumentoProtocollatoWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
									cfUtente,
									idAOO,
									null,
									null,
									params.get(ActaConfigurationParams.APPKEY.getParamName()),
									params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));
			//Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();			
			GestoreDocumentoActa gestoreDocumentoActa = verificaDocumentoProtocollatoWrapper.verificaDocumento(uuidDocumentoOrdine);
			response.setSuccess(Boolean.TRUE);
			response.setResponse(gestoreDocumentoActa);
			if(!verificaDocumentoProtocollatoWrapper.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(verificaDocumentoProtocollatoWrapper.getErrorDesc());
				response.setErrors(errors);
				response.setSuccess(Boolean.FALSE);
			}
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "verificaDocumentoProtocollato ERRORE INIT ", e);
			response.setSuccess(Boolean.FALSE);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Acta Verifica Documento Protocollato non disponibile");
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", "verificaDocumentoProtocollato Desc error --> "+e.getMessage());
			return response;
		}
		log.info("DocumentaleHelperImpl", "****** Fine verificaDocumentoProtocollato ACTA *******************");
		return response;
	}
	
	@Override
	public ExternalServiceResponseWrapper<ProtocolloOrdine> protocollaOrdine(Map<String, String> params,Long idAOO,TestataOrdine testataOrdine,ProtocolloOrdine protocollo,String settoreCorrenteCodice) {	
		log.info("DocumentaleHelperImpl", "*************protocollaOrdine ACTA *******************");
		checkBaseParameters(params);
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);		
		//cfUtente = "CPSTNT80A01L219S"; 
		//Long idAOO = 276L;
		ProtocollazioneActaServiceWrapper protocollaWrapper = new ProtocollazioneActaServiceWrapper();
		ExternalServiceResponseWrapper<ProtocolloOrdine> response = new ExternalServiceResponseWrapper<>();		
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add( CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		}
		try {
			protocollaWrapper.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
												cfUtente,
												idAOO,
												null,
												null,
												params.get(ActaConfigurationParams.APPKEY.getParamName()),
												params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));
			Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
			String ricercaSoggettoEstero = params.get(ActaConfigurationParams.RICERCA_SOGGETTO_FONTE_ESTERNA.getParamName());
			
			protocollo = protocollaWrapper.protocolla(ricercaSoggettoEstero,utente,testataOrdine, protocollo);
			response.setResponse(protocollo);
			response.setSuccess(Boolean.TRUE);
			if(!protocollaWrapper.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(protocollaWrapper.getErrorDesc());
				response.setSuccess(Boolean.FALSE);
				response.setErrors(errors);
			}
		} catch (Exception e) {
			log.error("DocumentaleHelperImpl", "protocollaOrdine ERRORE INIT ", e);
			response.setResponse(protocollo);
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Acta Protocolla Ordine non disponibile");
			errors.add( e.getMessage());
			response.setErrors(errors);
			log.error("DocumentaleHelperImpl", "protocollaOrdine Desc error --> "+e.getMessage());
			return response;
		}
		log.info("DocumentaleHelperImpl", "****** Fine protocollaOrdine ACTA *******************");
		return response;
	}


	@Override
	public ExternalServiceResponseWrapper<ProtocolloOrdine> getStrutturaAggregativaXIndiceclassificazioneEstesa(Map<String, String> params,Long idAOO, String indiceClassificazioneEsteso,String settoreCorrenteCodice) {
		log.info("DocumentaleHelperImpl", "*************getIndiceclassificazioneEstesa ACTA *******************");
		checkBaseParameters(params);
		//Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		String cfUtente = getCfUtente(params);
		idAOO = getAoo(params,idAOO);	
		CercaStrutturaAggregativaXIndiceClassificazioneActaServiceWrapper service = new CercaStrutturaAggregativaXIndiceClassificazioneActaServiceWrapper();		
		ExternalServiceResponseWrapper<ProtocolloOrdine> response = new ExternalServiceResponseWrapper<>();
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add( CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		}
		//scommentare nel caso si voglia utilizzare apimanager
		//addHandlerResolver(params, ????);
		
		ProtocolloOrdine protocollo = new ProtocolloOrdine();														
		try {
			
			service.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
													cfUtente,
													idAOO,
													null,
													null,
													params.get(ActaConfigurationParams.APPKEY.getParamName()),
													params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));	

			protocollo = service.ricerca(indiceClassificazioneEsteso);
			//protocollo.setAooOrig("" + idAOO);
			protocollo.setIndiceClassificazioneEsteso(indiceClassificazioneEsteso);			
			response.setSuccess(Boolean.TRUE);
			/*
			if(!service.getErrorDesc().equals("")) {
				List<String> errors = new ArrayList<String>();
				errors.add(service.getErrorDesc());
				response.setErrors(errors);				
				response.setSuccess(Boolean.FALSE);
			}
			*/
			response.setResponse(protocollo);
			
			} catch (Exception e) {
				log.error("DocumentaleHelperImpl", "ERRORE INIT ", e);
				response.setSuccess(Boolean.FALSE);
				List<String> errors = new ArrayList<String>();
				//todo da discriminare quando il generico e quando il propagato
				errors.add("Servizio Acta get Indice classificazione Estesa non disponibile");
				errors.add( e.getMessage());
				response.setErrors(errors);
				protocollo.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Acta non disponibile").getFullErrorMessage());
				protocollo.setDescErrore(e.getMessage());
				log.error("DocumentaleHelperImpl", "Desc error --> "+e.getMessage());
				response.setResponse(protocollo);
				return response;
			}
			response.setResponse(protocollo);
			log.info("getProtocolloOrigin", "****** Fine getIndiceclassificazioneEstesa ACTA *******************");
			return response;	
		}
	
	@Override
	public ExternalServiceResponseWrapper<ProtocolloOrdine> getStrutturaAggregativaXStrutturaAggregativa(Map<String, String> params,Long idAOO, String voceTitolario, String numeroFascicoloDossier,String aooDossier,String settoreCorrenteCodice) {
		log.info("DocumentaleHelperImpl", "*************getStrutturaAggregativaXStrutturaAggregativa ACTA *******************");
		checkBaseParameters(params);
		//Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		String cfUtente = getCfUtente(params);
		// da capire come valorizzare 
		idAOO = getAoo(params,idAOO);		
		CercaStrutturaAggregativaXStrutturaAggregativaActaServiceWrapper service = new CercaStrutturaAggregativaXStrutturaAggregativaActaServiceWrapper();
		ExternalServiceResponseWrapper<ProtocolloOrdine> response = new ExternalServiceResponseWrapper<>();
		ProtocolloOrdine protocollo = new ProtocolloOrdine();	
		if(idAOO==null) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			errors.add( CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			protocollo.setCodErrore(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getCode());
			protocollo.setDescErrore(CoreError.GENERIC_ERROR.getError("error","Il settore " + settoreCorrenteCodice + " non ha un AOO associato").getFullErrorMessage());
			response.setErrors(errors);
			return response;
		}
		//scommentare nel caso si voglia utilizzare apimanager
		//addHandlerResolver(params, ????);		
		try {			
			service.init(params.get(ActaConfigurationParams.REPOSITORY_NAME.getParamName()),
													cfUtente,
													idAOO,
													null,
													null,
													params.get(ActaConfigurationParams.APPKEY.getParamName()),
													params.get(ActaConfigurationParams.NODO_RESPONSABILE.getParamName()));	

			protocollo = service.ricerca(params.get(ActaConfigurationParams.TITOLARIO.getParamName()),voceTitolario,numeroFascicoloDossier,aooDossier);
			protocollo.setAooDossier(aooDossier);
			protocollo.setNumeroFascicoloDossier(numeroFascicoloDossier);
			protocollo.setVoceTitolario(voceTitolario);			
			response.setSuccess(Boolean.TRUE);
			response.setResponse(protocollo);
			
			} catch (Exception e) {
				log.error("DocumentaleHelperImpl", "ERRORE INIT ", e);
				response.setSuccess(Boolean.FALSE);
				List<String> errors = new ArrayList<String>();
				//todo da discriminare quando il generico e quando il propagato
				errors.add("Servizio Acta get Indice classificazione Estesa non disponibile");
				errors.add( e.getMessage());
				response.setErrors(errors);
				protocollo.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Acta non disponibile").getFullErrorMessage());
				protocollo.setDescErrore(e.getMessage());
				log.error("DocumentaleHelperImpl", "Desc error --> "+e.getMessage());
				response.setResponse(protocollo);
				return response;
			}
			response.setResponse(protocollo);
			log.info("getProtocolloOrigin", "****** Fine getIndiceclassificazioneEstesa ACTA *******************");
			return response;	
		}
	/**
	 * 
	 * @param parentNodeId
	 * @param oggetto
	 * @return
	 */
	public Boolean controlloPresenzaDocByOggetto(ArchiviaOrdineActaServiceWrapper archiviaOrdineActaServiceWrapper,String uuidSerieTipologica,ObjectIdType parentNodeId,String oggetto) {
		try {
			QueryResponse queryResponse = archiviaOrdineActaServiceWrapper.queryObjectSmartService("DocumentoSemplicePropertiesType",oggetto,ConstantsActa.QUERY_OGGETTO,EnumPropertyFilter.LIST,EnumQueryOperator.LIKE,parentNodeId,Boolean.FALSE);
			
			if(queryResponse==null || queryResponse.getObject()==null || queryResponse.getObject().getObjects()==null || queryResponse.getObject().getObjects().size()==0) {
				log.info("controlloPresenzaDoc","Il documento NON è presente sul sistema contabile" );
				return Boolean.FALSE;
			}else {
				log.info("controlloPresenzaDoc","Il documento E' presente sul sistema contabile" );
				List<ObjectResponseType> listObjectResponseType = queryResponse.getObject().getObjects();
				gda.setUuidSerieTipologica(uuidSerieTipologica);
				String uuidDocumentoOrdine = archiviaOrdineActaServiceWrapper.getPropertyValueByName(listObjectResponseType,ConstantsActa.QUERY_UUID_DOCUMENTO);
				gda.setUuidDocumentoOrdine(uuidDocumentoOrdine);
				
				String objectId = queryResponse.getObject().getObjects().get(0).getObjectId().getValue();
				String IdClassificazione;
					IdClassificazione = archiviaOrdineActaServiceWrapper.getClassificazioneByObjectId(objectId);
				gda.setIdClassificazioneValue(IdClassificazione);				
				//Se il dato idClassificazioneValue è l'objectId della classificazione collegata al documento trovato, allora è uno dei dati della classificazione a cui fa riferimento il documento.
				//Potete ottenere i dati della classificazione richiamando l'operazione navigationService.getObjectParents e specificando, come parametro di input objectId, il valore della property objectId come restituito dalla ricerca.
				//L'objectId della classificazione sarà restituito come risposta dell'operazione.
				//TODO da verificare se quanto scritto è corretto
				return Boolean.TRUE;
			}
		} catch (AcarisException e) {
			log.error("controlloPresenzaDoc", e);
		}catch (it.doqui.acta.acaris.navigationservice.AcarisException e) {
			log.error("controlloPresenzaDoc", e);
		}
		return Boolean.FALSE;
	}
}