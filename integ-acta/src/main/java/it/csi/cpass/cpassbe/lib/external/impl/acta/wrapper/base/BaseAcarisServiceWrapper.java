/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.utils.ActaConfigurationParams;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.csi.cpass.cpassbe.lib.utils.KeyValue;
import it.doqui.acta.acaris.archive.AcarisRepositoryEntryType;
import it.doqui.acta.acaris.archive.AcarisRepositoryInfoType;
import it.doqui.acta.acaris.archive.CreateFolder;
import it.doqui.acta.acaris.archive.CreateFolderResponse;
import it.doqui.acta.acaris.archive.EnumFolderObjectType;
import it.doqui.acta.acaris.archive.GetObjectParents;
import it.doqui.acta.acaris.archive.GetObjectParentsResponse;
import it.doqui.acta.acaris.archive.GetRepositories;
import it.doqui.acta.acaris.archive.GetRepositoriesResponse;
import it.doqui.acta.acaris.archive.GetRepositoryInfo;
import it.doqui.acta.acaris.archive.GetRepositoryInfoResponse;
import it.doqui.acta.acaris.archive.VolumeSerieTipologicaDocumentiPropertiesType;
import it.doqui.acta.acaris.backoffice.ClientApplicationInfo;
import it.doqui.acta.acaris.backoffice.DettaglioStrutturaType;
import it.doqui.acta.acaris.backoffice.GetDettaglioStruttura;
import it.doqui.acta.acaris.backoffice.GetDettaglioStrutturaResponse;
import it.doqui.acta.acaris.backoffice.GetPrincipal;
import it.doqui.acta.acaris.backoffice.GetPrincipalExt;
import it.doqui.acta.acaris.backoffice.GetPrincipalExtResponse;
import it.doqui.acta.acaris.backoffice.GetPrincipalResponse;
import it.doqui.acta.acaris.backoffice.PrincipalExtResponseType;
import it.doqui.acta.acaris.backoffice.PrincipalResponseType;
import it.doqui.acta.acaris.backofficeservice.BackOfficeService;
import it.doqui.acta.acaris.common.AcarisFaultType;
import it.doqui.acta.acaris.common.CodiceFiscaleType;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.IdAOOType;
import it.doqui.acta.acaris.common.IdNodoType;
import it.doqui.acta.acaris.common.IdStrutturaType;
import it.doqui.acta.acaris.common.ItemType;
import it.doqui.acta.acaris.common.NavigationConditionInfoType;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.ObjectResponseType;
import it.doqui.acta.acaris.common.PagingResponseType;
import it.doqui.acta.acaris.common.PrincipalIdType;
import it.doqui.acta.acaris.common.PropertyFilterType;
import it.doqui.acta.acaris.common.Query;
import it.doqui.acta.acaris.common.QueryConditionType;
import it.doqui.acta.acaris.common.QueryNameType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.common.QueryableObjectType;
import it.doqui.acta.acaris.common.VarargsType;
import it.doqui.acta.acaris.documentservice.DocumentService;
import it.doqui.acta.acaris.managementservice.ManagementService;
import it.doqui.acta.acaris.multifilingservice.MultifilingService;
import it.doqui.acta.acaris.navigationservice.NavigationService;
import it.doqui.acta.acaris.objectservice.ObjectService;
import it.doqui.acta.acaris.officialbookservice.GetRegistries;
import it.doqui.acta.acaris.officialbookservice.GetRegistriesResponse;
import it.doqui.acta.acaris.officialbookservice.OfficialBookService;
import it.doqui.acta.acaris.repositoryservice.AcarisException;
import it.doqui.acta.acaris.repositoryservice.RepositoryService;
import it.doqui.acta.acaris.subjectregistryservice.SubjectRegistryService;


/**
 * The Class AcarisServiceWrapper.
 */
public class BaseAcarisServiceWrapper {
	
	protected final static LogUtil log = new LogUtil(BaseAcarisServiceWrapper.class);
	
	protected RepositoryService repositoryService 			= null;
	protected NavigationService navigationService 			= null;
	protected BackOfficeService backofficeService 			= null;
	protected OfficialBookService officialBookService 		= null;
	protected ObjectService objectService 					= null;
	protected DocumentService documentService 				= null;
	protected MultifilingService multifilingService 		= null;
	protected ManagementService managementService 			= null;
	protected SubjectRegistryService subjectRegistryService = null;
	protected AcarisRepositoryInfoType repositoryInfo 		= null;
	protected ObjectIdType repositoryId 					= null;
	protected String repositoryName 						= null;
	protected String codiceFiscaleUtente 					= null;
	protected PrincipalIdType principalId 					= null;	
	protected Long idAOO 									= null;
	protected Long idStrutturaLogged						= null;
	protected Long idNodoLogged								= null;
	protected String appKey									= null;
	protected String nodoResponsabile						= null;
	protected String errorDesc								= "";
	protected String errorCode								= "";
	
	/**
	 * Inizializza l'oggetto con il nome del repository specificato (purch univoco) ed 
	 * i dati del Principal passati come parametri.
	 *
	 * @param repositoryName the repository name
	 * @param codiceFiscaleUtente the codice fiscale utente
	 * @param idAOO the id aoo
	 * @param idStruttura the id struttura
	 * @param idNodo the id nodo
	 * @param appKey the app key
	 * @param numInfoAppClient the num info app client
	 * @param infoAppClient the info app client
	 * @throws Exception the exception
	 */
	public BaseAcarisServiceWrapper() {//,List<KeyValue> infoAppClient) 
		
	}
	
	public void init(String repositoryName,
						String codiceFiscaleUtente,
						Long idAOO,
						Long idStruttura,
						Long idNodo,
						String appKey,
						String nodoResponsabile) throws Exception {		
		/*		 
		utente CF = CPSTNT80A01L219S, collocato in
		idAOO = 276 (A11000 - Risorse Finanziarie e patrimonio)
		idStruttura = 1043 (A11000 - RISORSE FINANZIARIE E PATRIMONIO)
		idNodo = 1123 (A11000-O1 - SEGRETERIA)
		repositoryName = RP201209 Regione Piemonte - Agg. 09/2012
		fruitore configurato con codice CPASS
		appKey = -29/-128/-61/124/23/12/121/115/84/-38/-8/-80/-64/-64/-113/-28
		serie tipologica creata
		codice = CPASS/AX8DPY
		descrizione = CPASS - ORDINI AX8DPY
		paroleChiave = ORDINI AX8DPY
		*/		
		String methodName = "Init";
		log.info(methodName , "Start");	
		this.repositoryName = repositoryName;
		this.codiceFiscaleUtente = codiceFiscaleUtente;
		this.idAOO = idAOO;
		this.appKey = appKey;
		this.nodoResponsabile = nodoResponsabile;
		initializeServices();
		repositoryId = getRepositoryIdByName(repositoryName);		
		initRepositoryInfo();
		principalId = getPrincipalExt(repositoryId,this.codiceFiscaleUtente,this.idAOO,idStruttura,idNodo,appKey,0,null).getPrincipalId();
	}
	
	/**
	 * Initialize services.
	 *
	 * @throws Exception the exception
	 */
	protected void initializeServices() throws Exception {
		final String methodName = "initializeServices";		
		repositoryService = new RepositoryService();
		log.debug(methodName,"RepositoryService creato");
		backofficeService = new BackOfficeService();
		log.debug(methodName,"backofficeService creato");
		objectService = new ObjectService();		
		log.debug(methodName,"objectService creato");
		officialBookService = new OfficialBookService();
		log.debug(methodName,"officialBookService creato");
		navigationService = new NavigationService();
		log.debug(methodName,"navigationService creato");
		documentService = new DocumentService();
		log.debug(methodName,"documentService creato");
		documentService = new DocumentService();
		log.debug(methodName,"documentService creato");
		multifilingService = new MultifilingService();
		log.debug(methodName,"multifilingService creato");
		subjectRegistryService = new SubjectRegistryService();
		log.debug(methodName,"subjectRegistry creato");
	}
	/**
	 * Gets the principal ext.
	 *
	 * @param codiceFiscaleUtente the codice fiscale utente
	 * @param idAOO the id aoo
	 * @param idStruttura the id struttura
	 * @param idNodo the id nodo
	 * @param appKey the app key
	 * @param numInfoAppClient the num info app client
	 * @param infoAppClient the info app client
	 * @return the principal ext
	 * @throws it.doqui.acta.acaris.backofficeservice.AcarisException 
	 * @throws Exception 
	 */
	public PrincipalExtResponseType getPrincipalExt(String codiceFiscaleUtente, Long idAOO, Long idStruttura, Long idNodo,String appKey,Integer numInfoAppClient,List<KeyValue> infoAppClient) throws Exception {
		return getPrincipalExt(repositoryId,codiceFiscaleUtente,idAOO,idStruttura,idNodo,appKey, numInfoAppClient,infoAppClient);
	}
	
	/**
	 * 
	 * @param repositoryId
	 * @param codiceFiscaleUtente
	 * @param idAOO
	 * @param idStruttura
	 * @param idNodo
	 * @param appKey
	 * @param numInfoAppClient
	 * @param infoAppClient
	 * @return
	 * @throws it.doqui.acta.acaris.backofficeservice.AcarisException 
	 * @throws Exception 
	 */
	public PrincipalExtResponseType getPrincipalExt(
			ObjectIdType repositoryId,
			String codiceFiscaleUtente,
			Long idAOO,
			
			Long idStruttura,
			Long idNodo,

			String appKey,			
			Integer numInfoAppClient,
			List<KeyValue> infoAppClient
			)throws Exception {
		final String methodName = "getPrincipalExt";
		log.info(methodName, "Start");
		CodiceFiscaleType codFiscaleT = new CodiceFiscaleType();
		codFiscaleT.setValue(codiceFiscaleUtente);
		log.info(methodName, "PARAMETRI GET PRINCIPAL ");		
		log.info(methodName, "repositoryId " +repositoryId.getValue());
		log.info(methodName, "codFiscaleT " +codFiscaleT.getValue());  
		IdAOOType idAOOT = null;
		if(idAOO!=null){
			idAOOT =new IdAOOType();
			idAOOT.setValue(idAOO);
			log.info(methodName, "idAOOT " +idAOOT.getValue());
		}

		IdStrutturaType idStrutturaT = null;
		if(idStruttura!=null){
			idStrutturaT = new IdStrutturaType();
			idStrutturaT.setValue(idStruttura);
			log.info(methodName, "idStrutturaT " +idStrutturaT.getValue());
		}

		IdNodoType idNodoT = null;
		if(idNodo!=null){
			idNodoT = new IdNodoType();
			idNodoT.setValue(idNodo);
			log.info(methodName, "idNodoT " +idNodoT.getValue());
		}
			
		ClientApplicationInfo clientApplicationInfoT = null;
		if(appKey!=null){		
			clientApplicationInfoT = new ClientApplicationInfo();
			clientApplicationInfoT.setAppKey(appKey);
		}
		log.info(methodName, "FINE PARAMETRI PRINCIPAL ");		
		PrincipalExtResponseType arrPrincipal = null;
		try {					
			GetPrincipalExt getPrincipalExt = new GetPrincipalExt();
			getPrincipalExt.setRepositoryId(repositoryId);
			getPrincipalExt.setClientApplicationInfo(clientApplicationInfoT);
			CodiceFiscaleType cf = new CodiceFiscaleType();
			cf.setValue(codiceFiscaleUtente);
			getPrincipalExt.setIdUtente(cf);
			getPrincipalExt.setIdAOO(idAOOT);
			getPrincipalExt.setIdNodo(idNodoT);
			getPrincipalExt.setIdStruttura(idStrutturaT);						
			GetPrincipalExtResponse getPrincipalExtResponse = backofficeService.getBackOfficeServicePort().getPrincipalExt(getPrincipalExt);			                       				
			
			//arrPrincipal = getPrincipalExtResponse.getPrincipal().get(0);
			for(int i =0; i<getPrincipalExtResponse.getPrincipal().size(); i++) {
				PrincipalExtResponseType pert = getPrincipalExtResponse.getPrincipal().get(i);
				if(pert.getUtente().getNodo().getDescrizione().equals(this.nodoResponsabile)){
					arrPrincipal = getPrincipalExtResponse.getPrincipal().get(i);
				}
			}
			if(arrPrincipal==null) {
				String err = "Principal non trovato cf "+codiceFiscaleUtente +" repository " + repositoryId +" idAOO "+idAOO +" Nodo "+ActaConfigurationParams.NODO_RESPONSABILE.getParamName();
				log.error(methodName, err ); 
				throw new Exception(err);
			}

			idStrutturaLogged = arrPrincipal.getUtente().getStruttura().getIdentificatore();
			idNodoLogged      = arrPrincipal.getUtente().getNodo().getIdentificatore();
			//principalId = getPrincipalExtResponse.getPrincipal().get(0).getPrincipalId();
			//arrPrincipal = backofficeService.getBackOfficeServicePort().getPrincipalExt(repositoryId,  codFiscaleT,  idAOOT,  idStrutturaT,  idNodoT,clientApplicationInfoT);			                       				
			//log.info(methodName,"principalExt found: "+Utility.fieldsToString(arrPrincipal));
		} catch (it.doqui.acta.acaris.backofficeservice.AcarisException acEx) {
			printAcarisException(acEx);
			throw  acEx;
		} 
		log.info(methodName, "Stop");
		return arrPrincipal;		
	}
	

	public  VarargsType getInfo(Integer numInfoAppClient,List<KeyValue> infoAppClient){
		VarargsType info = new VarargsType();
		for(int s=0;s<infoAppClient.size();s++){
			ItemType propA = new ItemType();
			propA.setKey(infoAppClient.get(s).getKey()); 
			propA.setValue(infoAppClient.get(s).getValue()); 
			info.getItems().set(s, propA);
		}
		return info;
	}
	
	protected void printAcarisException(it.doqui.acta.acaris.backofficeservice.AcarisException acEx) {
		final String methodName = "printAcarisException";
		if (acEx!=null && acEx.getMessage() != null && acEx.getFaultInfo() != null) {		
			AcarisFaultType fi = acEx.getFaultInfo();			
			log.error(methodName,"AcarisException.getFaultInfo().getErrorCode(): " + fi.getErrorCode());
			log.error(methodName,"AcarisException.getFaultInfo().getPropertyName(): " + fi.getPropertyName());
			log.error(methodName,"AcarisException.getFaultInfo().getObjectId(): " + fi.getObjectId());
			log.error(methodName,"AcarisException.getFaultInfo().getExceptionType(): " + fi.getExceptionType());
			log.error(methodName,"AcarisException.getFaultInfo().getClassName(): " + fi.getClassName());
			log.error(methodName,"AcarisException.getFaultInfo().getTechnicalInfo(): "+fi.getTechnicalInfo());			
		} 		
		log.error(methodName,"AcarisException.getMessage(): " + acEx!=null?acEx.getMessage():"null",acEx);
		log.error(methodName,"AcarisException.getCause(): " + acEx!=null?acEx.getCause():"null",acEx!=null?acEx.getCause():null);
	}
	/*
	public QueryResponse queryObjectService(String objectType, String value,String propertyName,EnumPropertyFilter enumPropertyFilter,EnumQueryOperator enumQueryOperator) throws it.doqui.acta.acaris.objectservice.AcarisException{			
		return queryObjectService( objectType,  value, propertyName, enumPropertyFilter, enumQueryOperator,null ) ;
	}
	*/
	/**
	 * 
	 * @param objectType
	 * @param value
	 * @param propertyName
	 * @param enumPropertyFilter
	 * @param enumQueryOperator
	 * @param parentNodeId
	 * @return QueryResponse
	 * @throws it.doqui.acta.acaris.objectservice.AcarisException
	 */
	public QueryResponse queryObjectSmartService(String objectType, String value,String propertyName,EnumPropertyFilter enumPropertyFilter,EnumQueryOperator enumQueryOperator,ObjectIdType parentNodeId,Boolean limitToChildren ) throws it.doqui.acta.acaris.objectservice.AcarisException{
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(objectType);    	
		PropertyFilterType filter = new PropertyFilterType();
		
		filter.setFilterType(enumPropertyFilter);		
		QueryNameType qnt1 = new QueryNameType();
		qnt1.setPropertyName("uuidDocumento");
		qnt1.setClassName("DocumentoSemplicePropertiesType");
		filter.getPropertyList().add(qnt1);
		
		
		QueryConditionType[] criteria = new QueryConditionType[1];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(value);
		criteria[0].setPropertyName(propertyName);
		criteria[0].setOperator(enumQueryOperator);
		
		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		if(parentNodeId!=null) {
			NavigationConditionInfoType navigationConditionInfo = new NavigationConditionInfoType();
			if(limitToChildren==null) {
				navigationConditionInfo.setLimitToChildren(Boolean.TRUE);
			}else {
				navigationConditionInfo.setLimitToChildren(limitToChildren);
			}
			navigationConditionInfo.setParentNodeId(parentNodeId);		
			parQuery.setNavigationLimits(navigationConditionInfo);
		}
		
		QueryResponse response = objectService.getObjectServicePort().query(parQuery);		
		return response;
	}

	
	
	public String getClassificazioneByObjectId(String objectIdValue) throws it.doqui.acta.acaris.navigationservice.AcarisException{
		String methodName = "getClassificazioneByObjectId";
		GetObjectParents op = new GetObjectParents();
		op.setPrincipalId(principalId);
		op.setRepositoryId(repositoryId);
		ObjectIdType objectId = new ObjectIdType();
		objectId.setValue(objectIdValue);
		op.setObjectId(objectId );
		GetObjectParentsResponse objParentresp = navigationService.getNavigationServicePort().getObjectParents(op);
		//ObjectResponseType[] resultListIdClass = navigationImpl.getObjectParents(idRepositoryType, idPrincipalType, idDocumento, null);
		if (objParentresp == null || objParentresp.getObjects()==null || objParentresp.getObjects().size() ==0) {			
			log.error(methodName, "classificazione Inesistente");
			errorDesc = "classificazione Inesistente";
			return "";
		}
		log.info(methodName, objParentresp.getObjects().get(0).getObjectId().getValue());
		return objParentresp.getObjects().get(0).getObjectId().getValue();
	}
	
	
	
	public QueryResponse queryObjectService(String objectType, String value,String propertyName,EnumPropertyFilter enumPropertyFilter,EnumQueryOperator enumQueryOperator,ObjectIdType parentNodeId,Boolean limitToChildren ) throws it.doqui.acta.acaris.objectservice.AcarisException{
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(objectType);    	
		PropertyFilterType filter = new PropertyFilterType();
		
		filter.setFilterType(enumPropertyFilter);		
		QueryNameType qnt1 = new QueryNameType();
		qnt1.setPropertyName("uuidDocumento");
		qnt1.setClassName("DocumentoSemplicePropertiesType");
		filter.getPropertyList().add(qnt1);
		
		QueryNameType qnt2 = new QueryNameType();
		qnt2.setPropertyName("objectId");
		qnt2.setClassName("DocumentoSemplicePropertiesType");
		filter.getPropertyList().add(qnt2);
		
		QueryConditionType[] criteria = new QueryConditionType[1];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(value);
		criteria[0].setPropertyName(propertyName);
		criteria[0].setOperator(enumQueryOperator);
		
		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		if(parentNodeId!=null) {
			NavigationConditionInfoType navigationConditionInfo = new NavigationConditionInfoType();
			if(limitToChildren==null) {
				navigationConditionInfo.setLimitToChildren(Boolean.TRUE);
			}else {
				navigationConditionInfo.setLimitToChildren(limitToChildren);
			}
			navigationConditionInfo.setParentNodeId(parentNodeId);		
			parQuery.setNavigationLimits(navigationConditionInfo);
		}
		
		QueryResponse response = objectService.getObjectServicePort().query(parQuery);		
		return response;
	}
	
	protected QueryResponse queryOfficialBookService(String objectType, String value,String propertyName,EnumPropertyFilter enumPropertyFilter,EnumQueryOperator enumQueryOperator ) throws it.doqui.acta.acaris.officialbookservice.AcarisException {
		QueryableObjectType target = new QueryableObjectType();
		target.setObject(objectType);    	
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(enumPropertyFilter);		
		QueryConditionType[] criteria = new QueryConditionType[1];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(value);
		criteria[0].setPropertyName(propertyName);
		criteria[0].setOperator(enumQueryOperator);
		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		QueryResponse response = officialBookService.getOfficialBookServicePort().query(parQuery);
		return response;
	}
	
	public String getSerieTipologicaUUID(String ufficio) throws AcarisException, it.doqui.acta.acaris.objectservice.AcarisException {
		PagingResponseType prt = queryObjectService("SerieTipologicaDocumentiPropertiesType","ORDINI "+ufficio,"paroleChiave",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE).getObject();
		if (prt == null || prt.getObjects()==null || prt.getObjects().isEmpty()) {
			log.error("getSerieTipologica", "nessuna serie tipologica trovata ufficio " + ufficio);
			errorDesc = "nessuna serie tipologica trovata ufficio " + ufficio;
			return null;
		}
		return getPropertyValueByName(prt.getObjects(), ConstantsActa.QUERY_UUID);
	}
	
	public ObjectIdType getSerieTipologicaObjectId(String ufficio) throws AcarisException, it.doqui.acta.acaris.objectservice.AcarisException {
		PagingResponseType prt = queryObjectService("SerieTipologicaDocumentiPropertiesType","ORDINI "+ufficio,"paroleChiave",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE).getObject();
		if (prt == null || prt.getObjects()==null || prt.getObjects().isEmpty()) {
			log.error("getSerieTipologica", "nessuna serie tipologica trovata ufficio " + ufficio);
			//errorDesc = "nessuna serie tipologica trovata ufficio " + ufficio;
			return null;
		}
		return prt.getObjects().get(0).getObjectId();
	}
	
	public ObjectIdType getVolumeUUIDByAnnoCorrente(ObjectIdType parentFolder) throws AcarisException, it.doqui.acta.acaris.objectservice.AcarisException {
		Calendar calendar = Calendar.getInstance();
		Integer annoCorrente = calendar.get(Calendar.YEAR);
		PagingResponseType prt = queryObjectService("VolumeSerieTipologicaDocumentiPropertiesType", String.valueOf(annoCorrente)     ,"descrizione",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS, parentFolder,Boolean.TRUE).getObject();
		if (prt == null || prt.getObjects()==null || prt.getObjects().isEmpty()) {
			log.error("getVolumeUUIDByAnnoCorrente", "nessun volume trovato");
			//errorDesc = "nessun volume trovato";
			return null;
		}
		return prt.getObjects().get(0).getObjectId();
		//return getPropertyValueByName(prt.getObjects(), ConstantsActa.QUERY_UUID);
		//return getPropertyValueByName(prt.getObjects(), ConstantsActa.QUERY_OBJECT_ID);
	}
	
	public ObjectIdType getVolumeUUIDByUfficio(String ufficio,ObjectIdType parentFolder) throws AcarisException, it.doqui.acta.acaris.objectservice.AcarisException {

		Calendar calendar = Calendar.getInstance();
		Integer annoCorrente = calendar.get(Calendar.YEAR);
		PagingResponseType prt = queryObjectService("VolumeSerieTipologicaDocumentiPropertiesType", ufficio+"_"+String.valueOf(annoCorrente)     ,"paroleChiave",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS, parentFolder,Boolean.TRUE).getObject();
		if (prt == null || prt.getObjects()==null || prt.getObjects().isEmpty()) {
			log.error("getVolumeUUIDByUfficio", "nessun volume trovato");
			//errorDesc = "nessun volume trovato";
			return null;
		}
		return prt.getObjects().get(0).getObjectId();
		//return getPropertyValueByName(prt.getObjects(), ConstantsActa.QUERY_UUID);
		//return getPropertyValueByName(prt.getObjects(), ConstantsActa.QUERY_OBJECT_ID);
	}
	
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}

	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @param methodName
	 * @param listObjects
	 * @param propertyName
	 * @return
	 */
	public String getPropertyValueByName(List<ObjectResponseType> listObjects, String propertyName) {
		String methodName = "getPropertyValueByName";
		String propertyValueEstratto = null;
		for (int i = 0; i < listObjects.size(); i++) {
			if (listObjects.get(i).getProperties() != null && listObjects.get(i).getProperties().size() > 0) {
				for (int j = 0; j < listObjects.get(i).getProperties().size(); j++) {
					String propertyNameEstratto  = listObjects.get(i).getProperties().get(j).getQueryName().getPropertyName();
					
					//log.info(methodName, "propertyNamedi cui voglio memorizzare il valore " + propertyNameEstratto)
					//log.info(methodName, "propertyNameEstratto dalla lista " + propertyNameEstratto)
					
					if (propertyName.equalsIgnoreCase(listObjects.get(i).getProperties().get(j).getQueryName().getPropertyName())) {							
						propertyValueEstratto = listObjects.get(i).getProperties().get(j).getValue().getContent().get(0);
						log.info(methodName, "prop value *********************");
						log.info(methodName, "prop Name  cercato "+ propertyNameEstratto);
						log.info(methodName, "prop value cercato "+ propertyValueEstratto);
						log.info(methodName, "prop value *********************");
					}else {
						try {
							log.info(methodName, "prop Name  NON cercato "+ propertyNameEstratto);
							//log.info(methodName, "prop value NON cercato "+ propertyValueEstratto);
						}catch(Exception e) {
							log.error(methodName, "prop value NON cercato NULL");
						}
					}
					
				}
			}
		}
		return propertyValueEstratto;
	}
	
	/**
	 * se dispongo dell'id sul DB posso ottenere con questo metodo l'ObjectIdType cifrato
	 * tramite l'invocazione del servizio query
	 * @param propertyType
	 * @param identificativo
	 * @param idRepositoryType
	 * @param idPrincipalType
	 * @return
	 */
	protected ObjectIdType getIdTypeByDbKey(String propertyType, long idSulDB, String modulo){
		String methodName = "getIdTypeByDbKey";
		log.info(methodName, "BEGIN");
		ObjectIdType idType = new ObjectIdType();
		QueryableObjectType target = null;
		PropertyFilterType filter = null;
		QueryConditionType criteria = null;
		PagingResponseType responseQuery = null;
		target = new QueryableObjectType();
    	target.setObject(propertyType);
    	filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.LIST);
		QueryNameType datoDaRestituire = new QueryNameType();
		datoDaRestituire.setClassName(propertyType);
		datoDaRestituire.setPropertyName("objectId");
		filter.getPropertyList().add(datoDaRestituire);
		criteria = new QueryConditionType();
		criteria = new QueryConditionType();
		criteria.setValue(Long.toString(idSulDB));
		criteria.setPropertyName("dbKey");
		criteria.setOperator(EnumQueryOperator.EQUALS);
		
		if(ConstantsActa.MODULO_BKO.equals(modulo)){		
			Query parQuery = new Query();
			parQuery.setFilter(filter);
			parQuery.setPrincipalId(principalId);
			parQuery.setRepositoryId(repositoryId);
			parQuery.setTarget(target);
			parQuery.getCriteria().add(criteria);
			try {
				responseQuery = backofficeService.getBackOfficeServicePort().query(parQuery).getObject();
			} catch (it.doqui.acta.acaris.backofficeservice.AcarisException e) {				
				log.error(methodName, e.getMessage());
			}
		} 
		
		if (responseQuery != null && responseQuery.getObjects() != null && responseQuery.getObjects().size() > 0) {
			if (responseQuery.getObjects().get(0).getProperties() != null && responseQuery.getObjects().get(0).getProperties().size() > 0) {
				idType.setValue(responseQuery.getObjects().get(0).getObjectId().getValue());
			}
		}
		log.info(methodName, "idType.Value " + idType.getValue());
		log.info(methodName, "END");
		return idType;
	}
	
	//	
	///**
	// * Inits the repository info.
	// *
	// * @throws AcarisException the acaris exception
	// */
	protected void initRepositoryInfo() throws AcarisException {		
		initRepositoryInfo(this.repositoryId);
	}
	
	/**
	 * Inits the repository info.
	 *
	 * @param repositoryId the repository id
	 * @throws AcarisException the acaris exception
	 */
	protected void initRepositoryInfo(ObjectIdType repositoryId) throws AcarisException {
		//final String methodName = "initRepositoryInfo";
		GetRepositoryInfo iri = new GetRepositoryInfo();
		iri.setRepositoryId(repositoryId);
		GetRepositoryInfoResponse ri = repositoryService.getRepositoryServicePort().getRepositoryInfo(iri);
		this.repositoryInfo = ri.getRepositoryInfo();		
	}
	
	
	//
	/**
	 * Gets the repository id by name.
	 *
	 * @param name the name
	 * @return the repository id by name
	 * @throws AcarisException the acaris exception
	 */
	protected ObjectIdType getRepositoryIdByName(String name) {
		final String methodName = "getRepositoryIdByName";
			GetRepositories input = new GetRepositories();
			try {
				GetRepositoriesResponse response = repositoryService.getRepositoryServicePort().getRepositories(input );
				for (int i = 0; i < response.getRepository().size(); i++) {
					AcarisRepositoryEntryType rep =response.getRepository().get(i);				
					log.debug(methodName,"id: "+rep.getRepositoryId().getValue() +" name:"+ rep.getRepositoryName() +" uri:"+rep.getRepositoryURI());				
					if(rep.getRepositoryName().indexOf(name) != -1){
						log.debug(methodName, "returning repository name: "+rep.getRepositoryName());
						return rep.getRepositoryId();
					}				
				}	
			} catch (AcarisException e) {
				log.error(methodName, "AcarisException  ", e);
			}			
		return null;
	}



	/**
	 * @param inputGetRegistries
	 * @return
	 * @throws AcarisException
	 */
	protected String estraiRegistro(Integer annoProtProc)throws it.doqui.acta.acaris.officialbookservice.AcarisException {		
		GetRegistries inputGetRegistries = new GetRegistries();
		inputGetRegistries.setPrincipalId(principalId);
		inputGetRegistries.setRepositoryId(repositoryId);		
		inputGetRegistries.setAnno(annoProtProc);			
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		inputGetRegistries.setFilter(filter);
		GetRegistriesResponse registro = officialBookService.getOfficialBookServicePort().getRegistries(inputGetRegistries );
		String idRegistro = getPropertyValueByName(registro.getObject().getObjects(), ConstantsActa.QUERY_DB_KEY);
		return idRegistro;
	}
	/**
	 * @param numProtProc
	 * @param idRegistro
	 * @param target
	 * @return
	 * @throws AcarisException
	 */
	protected QueryResponse getRegistrazioneView(String numProtProc, String idRegistro)throws it.doqui.acta.acaris.officialbookservice.AcarisException {		
		QueryableObjectType target = new QueryableObjectType();
    	target.setObject("RegistrazioneView");
    	
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType[] criteria = new QueryConditionType[2];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(StringUtils.leftPad(numProtProc, 8,'0'));
		criteria[0].setPropertyName("codice");
		criteria[0].setOperator(EnumQueryOperator.EQUALS);
		criteria[1] = new QueryConditionType();
		criteria[1].setValue(idRegistro);
		criteria[1].setPropertyName("idRegistro");
		criteria[1].setOperator(EnumQueryOperator.EQUALS);

		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		parQuery.getCriteria().add(criteria[1]);
		QueryResponse registrazioneProtocollo = officialBookService.getOfficialBookServicePort().query(parQuery);
		return registrazioneProtocollo;
	}

	/**
	 * @param numProtProc
	 * @param idRegistro
	 * @param target
	 * @return
	 * @throws AcarisException
	 */
	protected QueryResponse getDocumentoDaRegistrazioneView(String numProtProc, String idRegistro)throws it.doqui.acta.acaris.officialbookservice.AcarisException {		
		QueryableObjectType target = new QueryableObjectType();
    	target.setObject("DocumentoDaRegistrazioneView");    	
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType[] criteria = new QueryConditionType[2];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(StringUtils.leftPad(String.valueOf(numProtProc), 8,'0'));
		criteria[0].setPropertyName("codice");
		criteria[0].setOperator(EnumQueryOperator.EQUALS);
		criteria[1] = new QueryConditionType();
		criteria[1].setValue(idRegistro);
		criteria[1].setPropertyName("idRegistro");
		criteria[1].setOperator(EnumQueryOperator.EQUALS);

		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		parQuery.getCriteria().add(criteria[1]);
		QueryResponse docDaRegResp = officialBookService.getOfficialBookServicePort().query(parQuery);
				
		return docDaRegResp;
	}
	
	protected QueryResponse getDocumentoView(String uuidDocumento) throws it.doqui.acta.acaris.objectservice.AcarisException{	
		return queryObjectService("DocumentoView",uuidDocumento,"uuidDocumento",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE);	
	}
	
	
	
	public static void main(String[] args) throws Exception {
		//AcarisServiceWrapper acarisServiceWrapper = new AcarisServiceWrapper("RP201209 Regione Piemonte - Agg. 09/2012", "", null, null, null,null);
		//acarisServiceWrapper.init();
	}

	@Deprecated
	protected PrincipalResponseType getPrincipal() {
		String methodName = "getPrincipal";
		GetPrincipal input = new GetPrincipal();
		input.setRepositoryId(repositoryId);
		CodiceFiscaleType cf = new CodiceFiscaleType();
		cf.setValue(codiceFiscaleUtente);
		input.setIdUtente(cf);
		GetPrincipalResponse getPrincipalResponse;
		PrincipalResponseType ris = null;
		//List<PrincipalResponseType> listaPRY = null;
		
		try {
			getPrincipalResponse = backofficeService.getBackOfficeServicePort().getPrincipal(input);
			List<PrincipalResponseType> listaPrincipal = getPrincipalResponse.getPrincipal();
			if (listaPrincipal != null && listaPrincipal.size() > 0) {
				
				// prima raffinazione elimino quelli che non hanno AOO coerenti
				for (int i = 0; i < listaPrincipal.size(); i++) {
					IdAOOType aooId = listaPrincipal.get(i).getIdAOO();
					if(aooId.getValue() != idAOO) {
						listaPrincipal.remove(i);
					}
				}
				if(listaPrincipal == null || listaPrincipal.size()==0) {
					log.error(methodName, "Non esistono AOO per l'ente di lavoro");
				}
				
				//seconda raffinazione leggo la struttura 
				List<DettaglioStrutturaType> listaStruttura = new ArrayList<DettaglioStrutturaType>();
				for (int a = 0; a < listaPrincipal.size(); a++) {
					GetDettaglioStruttura gds = new GetDettaglioStruttura();
					gds.setRepositoryId(repositoryId);
					gds.setIdStruttura(listaPrincipal.get(a).getIdStruttura());
					GetDettaglioStrutturaResponse getDettaglioStrutturaResponse = backofficeService.getBackOfficeServicePort().getDettaglioStruttura(gds);
					DettaglioStrutturaType struttura = getDettaglioStrutturaResponse.getDettaglioStruttura();
					if(struttura != null) {			
						QueryableObjectType target = new QueryableObjectType();
				    	target.setObject("NodoPropertiesType");
				    	
				    	PropertyFilterType filter = new PropertyFilterType();
						filter.setFilterType(EnumPropertyFilter.ALL);
						
						QueryConditionType[] criteria = new QueryConditionType[1];
						criteria[0] = new QueryConditionType();						
						criteria[0].setValue(String.valueOf(listaPrincipal.get(a).getIdNodo().getValue()));
						criteria[0].setPropertyName("dbKey");
						criteria[0].setOperator(EnumQueryOperator.EQUALS);
						
						//Object responseQuery = backofficeService.query( listaPrincipal.get(a).getIdPrincipal(), target, filter, criteria,null,null,null);
						Query parQuery = new Query();
						parQuery.setFilter(filter);
						parQuery.setPrincipalId(listaPrincipal.get(a).getIdPrincipal());
						parQuery.setRepositoryId(repositoryId);
						parQuery.setTarget(target);
						parQuery.getCriteria().add(criteria[0]);
						
						QueryResponse responseQuery = backofficeService.getBackOfficeServicePort().query(parQuery);
						
						
						
						
						if (responseQuery != null && responseQuery.getObject() != null && responseQuery.getObject().getObjects()!= null && responseQuery.getObject().getObjects().size() > 0) {							
							for (int j = 0; j < responseQuery.getObject().getObjects().size(); j++) {
								ObjectResponseType elemento = responseQuery.getObject().getObjects().get(j);
								
								if (elemento.getProperties() != null && elemento.getProperties().size() > 0) {
									boolean nodoCoincidente = false;
									for (int k = 0; k < elemento.getProperties().size(); k++) {
										try {
											log.info(methodName, "nodo esaminato "+elemento.getProperties().get(k).getQueryName().getPropertyName()+" value " +elemento.getProperties().get(k).getValue().getContent().get(0));
											if (elemento.getProperties().get(k).getValue().getContent() != null && nodoResponsabile.equals(elemento.getProperties().get(k).getValue().getContent().get(0).toString())) {
												log.info(methodName, "nodo esaminato valore "+elemento.getProperties().get(k).getQueryName().getPropertyName()+" value " +elemento.getProperties().get(k).getValue().getContent().get(0));
												//descNodo = elemento.getProperties().get(k).getValue();
												nodoCoincidente = true;
												principalId = listaPrincipal.get(a).getIdPrincipal();
												return listaPrincipal.get(a);
											}
										}catch(Exception e) {}
									}
									if(!nodoCoincidente) {
										log.error(methodName, "Il nodo non coincide con quello atteso "+nodoResponsabile);
									}
								}
							}
						}else {
							log.error(methodName, "Struttura senza Nodi");
						}
						//NODO_RESPONSABILE
						//A11000-O1 - SEGRETERIA					
						//QueryResponse queryResponse = objectService.getObjectServicePort().query(parQuery);
						//listaStruttura.add(struttura);
					}else {
						listaStruttura.remove(a);
					}
				}
				if(listaStruttura == null ||listaStruttura.size()==0) {
					log.error(methodName, "Non esistono strutture valide");
				}

			}else {
				log.error(methodName, "Principal ACTA non trovato");
			}
		} catch (it.doqui.acta.acaris.backofficeservice.AcarisException e) {
			log.error(methodName, "backofficeservice AcarisException  ", e);
		}
		return ris;
	}
	
	public ObjectIdType createVolumeFolder(ObjectIdType parentObjetId,String ufficio) throws it.doqui.acta.acaris.objectservice.AcarisException {
		Calendar calendar = Calendar.getInstance();
		Integer annoCorrente = calendar.get(Calendar.YEAR);
		log.info("createVolumeFolder", "principalId --> "+principalId.getValue());
		log.info("createVolumeFolder", "repositoryId --> "+repositoryId.getValue());
		log.info("createVolumeFolder", "parentObjetId --> "+parentObjetId.getValue());
		log.info("createVolumeFolder", "TypeId --> "+EnumFolderObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE);
		log.info("createVolumeFolder", "paroleChiave --> "+ufficio+"_"+annoCorrente.toString());
		log.info("createVolumeFolder", "codice  --> "+annoCorrente);
		log.info("createVolumeFolder", "descrizione  --> "+annoCorrente);
		CreateFolder folder = new CreateFolder();
		folder.setPrincipalId(principalId);
		folder.setRepositoryId(repositoryId);
		folder.setFolderId(parentObjetId);
		folder.setTypeId(EnumFolderObjectType.VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE );
		VolumeSerieTipologicaDocumentiPropertiesType prop = new VolumeSerieTipologicaDocumentiPropertiesType();
		prop.setParoleChiave(ufficio+"_"+annoCorrente.toString());
		prop.setDescrizione(annoCorrente.toString());
		prop.setCodice(annoCorrente.toString());
		folder.setProperties(prop);
		CreateFolderResponse ris = objectService.getObjectServicePort().createFolder(folder );
		
		ObjectIdType objId = ris.getObjectId();		
		return objId;
	}
	
	protected QueryResponse getTitolarioPrimaVoce(String titolario, String primavoce) throws it.doqui.acta.acaris.objectservice.AcarisException {		
		QueryableObjectType target = new QueryableObjectType();
    	target.setObject("VocePropertiesType");
    	
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType[] criteria = new QueryConditionType[3];
		criteria[0] = new QueryConditionType();
		criteria[0].setPropertyName("codice");
		criteria[0].setOperator(EnumQueryOperator.EQUALS);
		criteria[0].setValue(primavoce);
		
		criteria[1] = new QueryConditionType();
		criteria[1].setPropertyName("dbKeyTitolario");
		criteria[1].setOperator(EnumQueryOperator.EQUALS);
		criteria[1].setValue(titolario);
		
		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		parQuery.getCriteria().add(criteria[1]);
		QueryResponse response = objectService.getObjectServicePort().query(parQuery);
		return response;
	}
	
	protected QueryResponse getNavigaTitolario(String titolario, String voce,String dbKeyVocePadre) throws it.doqui.acta.acaris.objectservice.AcarisException {		
		QueryableObjectType target = new QueryableObjectType();
    	target.setObject("VocePropertiesType");
    	
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		
		QueryConditionType[] criteria = new QueryConditionType[3];
		criteria[0] = new QueryConditionType();
		criteria[0].setPropertyName("codice");
		criteria[0].setOperator(EnumQueryOperator.EQUALS);
		criteria[0].setValue(voce);
		
		criteria[1] = new QueryConditionType();
		criteria[1].setPropertyName("dbKeyTitolario");
		criteria[1].setOperator(EnumQueryOperator.EQUALS);
		criteria[1].setValue(titolario);
		
		criteria[2] = new QueryConditionType();
		criteria[2].setValue(dbKeyVocePadre);
		criteria[2].setPropertyName("dbKeyPadre");
		criteria[2].setOperator(EnumQueryOperator.EQUALS);

		Query parQuery = new Query();
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		parQuery.getCriteria().add(criteria[1]);
		parQuery.getCriteria().add(criteria[2]);
		QueryResponse response = objectService.getObjectServicePort().query(parQuery);
		return response;
	}
	
	protected String getTargetQueryByTipoAggregazione(String idTipoAggregazione) {
		switch(idTipoAggregazione) {
		case "1":
			return "SerieTipologicaDocumentiPropertiesType";
		case "2":
			return "SerieFascicoliPropertiesType";
		case "3":
			return "SerieDossierPropertiesType";
		case "4":
			return "SottofascicoloPropertiesType";
		case "5":
			return "FascicoloRealeAnnualePropertiesType";
		case "6":
			return "FascicoloRealeContinuoPropertiesType";
		case "7":
			return "FascicoloRealeLiberoPropertiesType";
		case "8":
			return "FascicoloRealeEreditatoPropertiesType";
		case "9":
			return "FascicoloRealeLegislaturaPropertiesType";
		case "10":
			return "DossierPropertiesType";
		case "11":
			return "VolumeSottofascicoliPropertiesType";
		case "12":
			return "VolumeFascicoliPropertiesType";
		case "13":
			return "VolumeSerieFascicoliPropertiesType";
		case "14":
			return "VolumeSerieTipologicaDocumentiPropertiesType";
		default:
			return "";
		}
	}
}
