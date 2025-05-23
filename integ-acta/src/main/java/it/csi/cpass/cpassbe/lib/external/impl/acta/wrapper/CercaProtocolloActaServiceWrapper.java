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
package it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.doqui.acta.acaris.archive.GetFolderParent;
import it.doqui.acta.acaris.archive.GetFolderParentResponse;
import it.doqui.acta.acaris.archive.GetObjectParents;
import it.doqui.acta.acaris.archive.GetObjectParentsResponse;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.ObjectResponseType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.objectservice.AcarisException;  
/**
 * The Class AcarisServiceWrapper.
 */
public class CercaProtocolloActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(CercaProtocolloActaServiceWrapper.class);

	//public void cercaProtocolloActa(String idProtProcActa,Integer annoProtProc, Integer numProtProc, String repositoryId,String principalId, long idAoo){
	public ProtocolloOrdine cercaProtocolloActa(Integer annoProtProc, String numProtProc) throws AcarisException, ParseException{
		String methodName = "cercaProtocolloActa";
		ProtocolloOrdine protocollo = new ProtocolloOrdine();

		//PropertyFilterType filter;
		//inputGetRegistries.setFilter(filter);		
		//GetRegistriesResponse registro;
		try {
			//STEP 2 CERCA PROTOCOLLO
			String idRegistro = estraiRegistro(annoProtProc);
			if(idRegistro.equals("")) {
				log.error(methodName, "Registro non trovato");
				errorDesc = "Registro non trovato";
				protocollo.setDescErrore(errorDesc);
				return protocollo;
			}
			//STEP 3 CERCA PROTOCOLLO
	    	QueryResponse registrazioneProtocollo = getRegistrazioneView(numProtProc, idRegistro);
			if (registrazioneProtocollo == null || registrazioneProtocollo.getObject() == null || registrazioneProtocollo.getObject().getObjects().size() == 0) {
				log.error(methodName, "STEP 3 Registrazione inesistente o senza properties la registrazione di protocollo non ha come nodo responsabile il nodo " + nodoResponsabile);
				errorDesc = "la registrazione di protocollo non ha come nodo responsabile il nodo " + nodoResponsabile;
				protocollo.setDescErrore(errorDesc);
				return protocollo;
			}
			List<ObjectResponseType> ortRp = registrazioneProtocollo.getObject().getObjects();
			for (int i = 0; i < ortRp.size(); i++) {
				//idRegistrazioneCodificata = registrazioneProtocollo.getObjects()[i].getObjectId();
				for (int j = 0; j < ortRp.get(i).getProperties().size(); j++) {

					String propertyName = ortRp.get(i).getProperties().get(j).getQueryName().getPropertyName();
					String propertyValue = "";
					log.info(methodName, "propertyName con valore null " + propertyName);
					try {
						propertyValue = ortRp.get(i).getProperties().get(j).getValue().getContent().get(0);
						log.info(methodName, "propertyName --> " + propertyValue);
					}catch(Exception e) {
						log.info(methodName, "propertyName con valore null " + propertyName);
						continue;
					}
					if (ConstantsActa.QUERY_DB_KEY_TEMP.equalsIgnoreCase(propertyName)|| ConstantsActa.QUERY_DB_KEY.equalsIgnoreCase(propertyName)) {
						String idRegistrazioneNonCodificata = propertyValue;
						protocollo.setUuidRegProtocolloOrig(idRegistrazioneNonCodificata);
					}

					if (ConstantsActa.QUERY_DATA_PROTOCOLLO.equalsIgnoreCase(propertyName)|| ConstantsActa.QUERY_DATA_PROTOCOLLO.equalsIgnoreCase(propertyName)) {
						String dataProtocolloOrig = propertyValue;
						protocollo.setDataProtocolloOrig(new SimpleDateFormat("dd/MM/yyyy").parse(dataProtocolloOrig));
					}


					if (ConstantsActa.QUERY_DESCRIZIONE_STATO_REGISTRAZIONE.equalsIgnoreCase(propertyName)) { 
						protocollo.setAnnullato(propertyValue.equalsIgnoreCase("Annullata")|| propertyValue.equalsIgnoreCase("Annullato"));
					}
					
					if (ConstantsActa.QUERY_DATA_PROTOCOLLO.equalsIgnoreCase(propertyName) ){
						//protocollo.setDataOra(new SimpleDateFormat("dd/MM/yyyy").parse(ortRp.get(i).getProperties().get(j).getValue().getContent().get(0)));
					}
					//da capire se è la descrizione
					if (ConstantsActa.QUERY_OGGETTO.equalsIgnoreCase(propertyName)) {
						protocollo.setDescrizioneProtocolloOrig(propertyValue);
					}
					if (ConstantsActa.QUERY_DESCRIZIONE_TIPO_REGISTRAZIONE.equalsIgnoreCase(propertyName)) {
						protocollo.setDescrizioneTipoRegistrazione(propertyValue);
					}
					String descNodoResp = "";
					if (ConstantsActa.QUERY_DESC_NODO_RESPONSABILE.equalsIgnoreCase(propertyName)) {
						descNodoResp = propertyValue;
						if(!nodoResponsabile.equals(descNodoResp)){
							log.error(methodName, "STEP 3 la registrazione di protocollo non ha come nodo responsabile il nodo " + nodoResponsabile);
							errorDesc = "la registrazione di protocollo non ha come nodo responsabile il nodo " + nodoResponsabile;
							protocollo.setDescErrore(errorDesc);
							return protocollo;
						}
					}
				}								
			}
			// step 4
			QueryResponse documDaRegresp = getDocumentoDaRegistrazioneView( numProtProc,  idRegistro);
			//PagingResponseType resultDocument = officialBookImpl.query(idRepositoryType, idPrincipalType, target, null, criteria, null, null, null);
			if (documDaRegresp == null || documDaRegresp.getObject().getObjects() == null || documDaRegresp.getObject().getObjects().size() == 0) {
				log.error(methodName, "STEP 4 documento da registrazione non presente");
				errorDesc = "documento da registrazione non presente";
				protocollo.setDescErrore(errorDesc);
				return protocollo;

			}
			List<ObjectResponseType> objRespType = documDaRegresp.getObject().getObjects();
			
			if (objRespType == null  || objRespType.get(0).getProperties().size() == 0) {
				log.error(methodName, "STEP 4 proprieta documento non trovate");
				errorDesc = "proprieta documento non trovate";
				protocollo.setDescErrore(errorDesc);
				return protocollo;
			}	
			String uuidDocumento ="";
			for (int j = 0; j < objRespType.get(0).getProperties().size(); j++) {
				if (ConstantsActa.QUERY_UUID.equalsIgnoreCase(objRespType.get(0).getProperties().get(j).getQueryName().getPropertyName())) {
					uuidDocumento = objRespType.get(0).getProperties().get(j).getValue().getContent().get(0);
					protocollo.setUuidDocumentoOrig(uuidDocumento);
				}
			}
			if(uuidDocumento.equals("")) {
				log.error(methodName, "STEP 4 uuidDocumento non trovato");
				errorDesc = "uuidDocumento non trovato";
				protocollo.setDescErrore(errorDesc);
				return protocollo;
			}
			
			//step 5
			QueryResponse documViewresp =getDocumentoView( uuidDocumento);
		    //PagingResponseType resultDocumentId = objectImpl.query(idRepositoryType, idPrincipalType, target, null, criteriaDoc, null, null, null);			
		    List<ObjectResponseType> ortDoc = documDaRegresp.getObject().getObjects();

			if (documViewresp == null || ortDoc == null || ortDoc.size() == 0) {
				log.error(methodName, "STEP 5 documento non presente");
				errorDesc = "documento non presente";
				protocollo.setDescErrore(errorDesc);
				return protocollo;
		    }
			ObjectIdType idDocumento = new ObjectIdType();
			for (int i = 0; i < ortDoc.size(); i++) {
				if (ortDoc.get(i).getProperties() != null && ortDoc.get(i).getProperties().size() > 0) {
					for (int j = 0; j < ortDoc.get(i).getProperties().size(); j++) {
						log.info(methodName, "prop "+ ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName());
						//if (ConstantsActa.QUERY_OBJECT_ID_DOC_SEMPLICE.equalsIgnoreCase(ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName())) {							
						if (ConstantsActa.QUERY_OBJECT_ID.equalsIgnoreCase(ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName())) {							
							idDocumento.setValue(ortDoc.get(i).getProperties().get(j).getValue().getContent().get(0));
						}
					}
				}
			}
			// STEP 6	e  7		
			GetObjectParents op = new GetObjectParents();
			op.setPrincipalId(principalId);
			op.setRepositoryId(repositoryId);
			op.setObjectId(idDocumento);
			GetObjectParentsResponse objParentresp = navigationService.getNavigationServicePort().getObjectParents(op);
			//ObjectResponseType[] resultListIdClass = navigationImpl.getObjectParents(idRepositoryType, idPrincipalType, idDocumento, null);
			if (objParentresp == null || objParentresp.getObjects()==null || objParentresp.getObjects().size() ==0) {
				log.error(methodName, "STEP 6 classificazione Inesistente");
				errorDesc = "classificazione Inesistente";
				protocollo.setDescErrore(errorDesc);
				return protocollo;

			}

			boolean accessibile = false;
			//ObjectResponseType idStruttura = null;
			
			for (int i = 0; i < objParentresp.getObjects().size() && !accessibile; i++) {			
				GetFolderParent fp = new GetFolderParent();
				fp.setPrincipalId(principalId);
				fp.setRepositoryId(repositoryId);
				fp.setFolderId(objParentresp.getObjects().get(i).getObjectId());
				GetFolderParentResponse idStruttura = navigationService.getNavigationServicePort().getFolderParent(fp );
				if (idStruttura != null && idStruttura.getObject().getObjectId() != null) {
					//idClassType = resultListIdClass[i].getObjectId();
					accessibile = true;
				} 
			}
			if (!accessibile) {
				protocollo.setStrutturaAccessibile(Boolean.FALSE);
				
			}else {
				protocollo.setStrutturaAccessibile(Boolean.TRUE);
			}
		} catch (it.doqui.acta.acaris.officialbookservice.AcarisException e) {			
			log.error(methodName, "officialbookservice.AcarisException " ,e);
		} catch (it.doqui.acta.acaris.navigationservice.AcarisException e) {
			log.error(methodName, "navigationservice.AcarisException " ,e);
		}
		return protocollo;
	}
	
}
