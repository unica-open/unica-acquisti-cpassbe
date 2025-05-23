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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.lib.external.dto.GestoreDocumentoActa;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.doqui.acta.acaris.archive.GetProperties;
import it.doqui.acta.acaris.archive.GetPropertiesResponse;
import it.doqui.acta.acaris.common.EnumObjectType;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.ObjectResponseType;
import it.doqui.acta.acaris.common.PropertyFilterType;
import it.doqui.acta.acaris.common.PropertyType;
import it.doqui.acta.acaris.common.Query;
import it.doqui.acta.acaris.common.QueryConditionType;
import it.doqui.acta.acaris.common.QueryNameType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.common.QueryableObjectType;
import it.doqui.acta.acaris.objectservice.AcarisException;
import it.doqui.acta.acaris.officialbookservice.GetRegistries;
import it.doqui.acta.acaris.officialbookservice.GetRegistriesResponse;
/**
 * The Class AcarisServiceWrapper.
 */
public class VerificaDocumentoProtocollatoActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(VerificaDocumentoProtocollatoActaServiceWrapper.class);

	public GestoreDocumentoActa verificaDocumento(String uuidDocumentoOrdine) throws AcarisException, it.doqui.acta.acaris.officialbookservice.AcarisException{
		String methodName = "verificaDocumento";
		log.info(methodName, "START "  );
		log.info(methodName, "uuidDocumentoOrdine " +uuidDocumentoOrdine );

		QueryResponse documViewresp =getDocumentoView(uuidDocumentoOrdine);
		if (documViewresp == null || documViewresp.getObject() == null || documViewresp.getObject().getObjects()==null || documViewresp.getObject().getObjects().size() == 0) {
			log.error(methodName, "documento non presente");
			errorDesc = "documento non presente";
			return null;
		}
		List<ObjectResponseType> ortDoc = documViewresp.getObject().getObjects();		
		ObjectIdType idDocumento = new ObjectIdType();
		idDocumento.setValue(getPropertyValueByName(ortDoc, ConstantsActa.QUERY_OBJECT_ID_DOC_SEMPLICE));
		
		GetProperties getPropertiesInput = new GetProperties();
		getPropertiesInput.setRepositoryId(repositoryId);
		getPropertiesInput.setPrincipalId(principalId);
		getPropertiesInput.setObjectId(idDocumento);
		PropertyFilterType propertyFilterType = new PropertyFilterType();
		propertyFilterType.setFilterType(EnumPropertyFilter.LIST);
		QueryNameType datoDaRestituireProt = new QueryNameType();
		datoDaRestituireProt.setClassName(EnumObjectType.DOCUMENTO_SEMPLICE_PROPERTIES_TYPE.value());
		datoDaRestituireProt.setPropertyName(ConstantsActa.QUERY_ID_PROTOCOLLO_LIST);
		QueryNameType datoDaRestituireAnn = new QueryNameType();
		datoDaRestituireAnn.setClassName(EnumObjectType.DOCUMENTO_SEMPLICE_PROPERTIES_TYPE.value());
		datoDaRestituireAnn.setPropertyName(ConstantsActa.QUERY_ID_ANNOTAZIONI_LIST);
		propertyFilterType.getPropertyList().add(datoDaRestituireProt);
		propertyFilterType.getPropertyList().add(datoDaRestituireAnn);
		
		GetPropertiesResponse getPropertiesResponse = objectService.getObjectServicePort().getProperties(getPropertiesInput);
		
		if (getPropertiesResponse == null || getPropertiesResponse.getObject() == null || getPropertiesResponse.getObject().getProperties()==null || getPropertiesResponse.getObject().getProperties().size()==0) {
			log.error(methodName, "l'ordine non è ancora protocollato");
			errorDesc = "l'ordine non è ancora protocollato";
			return null;
		}
		
		List<String> idProtocolloList = new ArrayList<String>();
		for (PropertyType propertyType : getPropertiesResponse.getObject().getProperties()) {
			log.info(methodName, "prop "+ propertyType.getQueryName().getPropertyName());
			if (ConstantsActa.QUERY_ID_PROTOCOLLO_LIST.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {						
				if (propertyType.getValue() != null && !propertyType.getValue().getContent().isEmpty()) {
					idProtocolloList.addAll(propertyType.getValue().getContent());
				}
			}
		}
		
		if (idProtocolloList.isEmpty()) {
			log.error(methodName, "l'ordine non è ancora protocollato");
			errorDesc = "l'ordine non è ancora protocollato";
			return null;
		}
		
		String idRegistrazione = null;
		String stato = null;
		String uuidRegProtocolloOrdine = null;
		String descrizioneProtocollo = null;
		String dataProtocollo = null;
		for(String idProtocollo : idProtocolloList) {
			QueryResponse queryResponse = queryObjectService("ProtocolloPropertiesType",idProtocollo,"objectId",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE);
			if (queryResponse == null || queryResponse.getObject() == null || queryResponse.getObject().getObjects()==null || queryResponse.getObject().getObjects().size() == 0) {
				log.error(methodName, "l'ordine non è ancora protocollato, idProtocollo "+ idProtocollo);
				errorDesc = "l'ordine non è ancora protocollato";
				continue;
			}
			if (queryResponse.getObject().getObjects().size()>1) {
				log.error(methodName, "query.size > 1, idProtocollo "+ idProtocollo);
				errorDesc = "l'ordine non è ancora protocollato";
				continue;
			}
			ObjectResponseType ort = queryResponse.getObject().getObjects().get(0);
			
			for(PropertyType propertyType : ort.getProperties()) {
				if (ConstantsActa.QUERY_ID_REGISTRAZIONE.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {
					idRegistrazione = propertyType.getValue().getContent().get(0);
				}
				if (ConstantsActa.QUERY_OGGETTO.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {
					descrizioneProtocollo = propertyType.getValue().getContent().get(0);
				}
				if (ConstantsActa.QUERY_DATA_PROTOCOLLO.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {
					dataProtocollo = propertyType.getValue().getContent().get(0);
				}
			}
			
			String idRegistroNonCodificato = null;
			String annoReg = "0";
			String numeroReg = null;
			if (idRegistrazione != null) {
				annoReg = idRegistrazione.substring(idRegistrazione.indexOf("/") + 1);
			    numeroReg = idRegistrazione.substring(0, idRegistrazione.indexOf("/"));
			}
			ObjectIdType idAooType = getIdTypeByDbKey("AOOPropertiesType", idAOO, ConstantsActa.MODULO_BKO);
			
			GetRegistries getRegistriesInput = new GetRegistries();
			getRegistriesInput.setRepositoryId(repositoryId);
			getRegistriesInput.setPrincipalId(principalId);
			getRegistriesInput.setAooId(idAooType);
			getRegistriesInput.setAnno(Integer.parseInt(annoReg));
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.ALL);
			getRegistriesInput.setFilter(filter);
			
			GetRegistriesResponse getRegistriesResponse = officialBookService.getOfficialBookServicePort().getRegistries(getRegistriesInput);
			if (getRegistriesResponse != null && getRegistriesResponse.getObject() != null && getRegistriesResponse.getObject().getObjects().size()> 0) {
				idRegistroNonCodificato = getPropertyValueByName(getRegistriesResponse.getObject().getObjects(), ConstantsActa.QUERY_DB_KEY);
			}
			
			QueryableObjectType target = new QueryableObjectType();
			target.setObject("RegistrazioneView");    	
			filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.ALL);
			QueryConditionType criterioObjectIdRegistro = buildQueryCondition(EnumQueryOperator.EQUALS, idRegistroNonCodificato, "idRegistro");
			QueryConditionType criterioNumeroProt = buildQueryCondition(EnumQueryOperator.EQUALS, numeroReg, "codice");
			Query parQuery = new Query();
			parQuery.setFilter(filter);
			parQuery.setPrincipalId(principalId);
			parQuery.setRepositoryId(repositoryId);
			parQuery.setTarget(target);
			parQuery.getCriteria().add(criterioObjectIdRegistro);
			parQuery.getCriteria().add(criterioNumeroProt);
			
			QueryResponse registrazione = officialBookService.getOfficialBookServicePort().query(parQuery);
			if (registrazione != null && registrazione.getObject() != null && registrazione.getObject().getObjects()!=null && registrazione.getObject().getObjects().size()==1) {
				ObjectResponseType objRegistrazione = registrazione.getObject().getObjects().get(0);
				objRegistrazione.getObjectId().getValue();// 
				for(PropertyType propertyType : objRegistrazione.getProperties()) {
					if (ConstantsActa.QUERY_DESCRIZIONE_STATO_REGISTRAZIONE.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {
						stato = propertyType.getValue().getContent().get(0);
					}
					
					if (ConstantsActa.QUERY_UUID.equalsIgnoreCase(propertyType.getQueryName().getPropertyName())) {
						uuidRegProtocolloOrdine = propertyType.getValue().getContent().get(0);//da restituire perchè deve essere salvato su db nostro setUuidRegProtOrdine
					}
				}
			}
			if (stato != null && !"Annullata".equalsIgnoreCase(stato) && !"Annullato".equalsIgnoreCase(stato)) {
				GestoreDocumentoActa gestoreDocumentoActa = new GestoreDocumentoActa();
				gestoreDocumentoActa.setUuidRegProtocolloOrdine(uuidRegProtocolloOrdine);
				gestoreDocumentoActa.setAnnoProtocollo(annoReg);
				gestoreDocumentoActa.setNumeroProtocollo(numeroReg);
				gestoreDocumentoActa.setDescrizioneProtocollo(descrizioneProtocollo);
				gestoreDocumentoActa.setDataProtocollo(dataProtocollo);
				errorDesc = "";
				return gestoreDocumentoActa;
			}else {
				errorDesc = "l'ordine è stato protocollato ma il protocollo è stato annullato";
			}
		}
		
		log.info(methodName, "END "  );

		return StringUtils.isBlank(errorDesc) ? new GestoreDocumentoActa()  : null;
	}
	private QueryConditionType buildQueryCondition(EnumQueryOperator op, String value, String propertyName){
		QueryConditionType criterioNumeroProt = new QueryConditionType();
		criterioNumeroProt.setOperator(op);
		criterioNumeroProt.setValue(value);
		criterioNumeroProt.setPropertyName(propertyName);
		return criterioNumeroProt;
	}
}
