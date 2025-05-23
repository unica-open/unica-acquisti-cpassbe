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
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.NavigationConditionInfoType;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PropertyFilterType;
import it.doqui.acta.acaris.common.Query;
import it.doqui.acta.acaris.common.QueryConditionType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.common.QueryableObjectType;
import it.doqui.acta.acaris.objectservice.AcarisException;  
/**
 * The Class AcarisServiceWrapper.
 */
public class CercaStrutturaAggregativaXStrutturaAggregativaActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(CercaStrutturaAggregativaXStrutturaAggregativaActaServiceWrapper.class);

	public ProtocolloOrdine ricerca(String titolario,String voceTitolario, String numeroFascicoloDossier,String aoo) {
		String methodName = "ricerca X Struttura Aggregativa";
		// 1 step
		ProtocolloOrdine protocollo = new ProtocolloOrdine();
		ObjectIdType objIdTitolario = new ObjectIdType();
		try {
			String[] voceTitolariolist = voceTitolario.split("\\.");	
			
			QueryResponse res = getTitolarioPrimaVoce(titolario, voceTitolariolist[0]);
			String dbKeyVocePadre = getPropertyValueByName(res.getObject().getObjects(), "dbKey");
			
			QueryResponse reqFiglio = null;
			for(int i=1;i<voceTitolariolist.length;i++) {
				reqFiglio = getNavigaTitolario(titolario,voceTitolariolist[i],dbKeyVocePadre);
				dbKeyVocePadre = getPropertyValueByName(reqFiglio.getObject().getObjects(), "dbKey");
			}
			objIdTitolario = reqFiglio.getObject().getObjects().get(0).getObjectId();
		}catch(AcarisException ae) {
			log.error(methodName, MsgCpassOrd.ORDORDE0170.getMessage() + ae);
			errorCode = MsgCpassOrd.ORDORDE0170.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0170.getMessage() + " " + ae;
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;			
		}catch(Exception e) {
			log.error(methodName, MsgCpassOrd.ORDORDE0170.getMessage() + " " + e);
			errorCode = MsgCpassOrd.ORDORDE0170.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0170.getMessage();
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;
		}
		
		// 2 step
		String ecmUuidNodo;
		String idTipoAggregazione;
		String targetQuery;
		try{
			Query parQuery = new Query();
			parQuery.setPrincipalId(principalId);
			parQuery.setRepositoryId(repositoryId);
			// setto il target
				QueryableObjectType target = new QueryableObjectType();
				target.setObject("AggregazionePropertiesType");
			parQuery.setTarget(target);
			// setto il filter
	    		PropertyFilterType filter = new PropertyFilterType();
	    		filter.setFilterType(EnumPropertyFilter.ALL);
			parQuery.setFilter(filter);
			// setto i criteri
				QueryConditionType[] criteria = new QueryConditionType[4];
				criteria[0] = new QueryConditionType();
				criteria[0].setPropertyName("codice");
				criteria[0].setOperator(EnumQueryOperator.EQUALS);
				criteria[0].setValue(numeroFascicoloDossier);
				//DA capire l'aoo 	
				criteria[1] = new QueryConditionType();
				criteria[1].setPropertyName("suffissoCodice");
				criteria[1].setOperator(EnumQueryOperator.EQUALS);
				criteria[1].setValue(aoo);
		
				criteria[2] = new QueryConditionType();
				criteria[2].setPropertyName("stato");
				criteria[2].setOperator(EnumQueryOperator.EQUALS);
				criteria[2].setValue("1");
				/*
				criteria[3] = new QueryConditionType();
				criteria[3].setPropertyName("parentNodeId");
				criteria[3].setOperator(EnumQueryOperator.EQUALS);
				criteria[3].setValue(objIdTitolario.getValue());
				*/
			parQuery.getCriteria().add(criteria[0]);
			parQuery.getCriteria().add(criteria[1]);
			parQuery.getCriteria().add(criteria[2]);
			//parQuery.getCriteria().add(criteria[3]);
			// setto il NavigationConditionInfoType
				NavigationConditionInfoType ncit = new NavigationConditionInfoType();
				ncit.setLimitToChildren(false);
				ncit.setParentNodeId(objIdTitolario);
			parQuery.setNavigationLimits(ncit );
			
			QueryResponse response = objectService.getObjectServicePort().query(parQuery);
			//String dbKey              = getPropertyValueByName(response.getObject().getObjects(), "dbKey");
			ecmUuidNodo        = getPropertyValueByName(response.getObject().getObjects(), "ecmUuidNodo");
			idTipoAggregazione = getPropertyValueByName(response.getObject().getObjects(), "idTipoAggregazione");	
			targetQuery        = getTargetQueryByTipoAggregazione(idTipoAggregazione);
		}catch(AcarisException ae) {
			log.error(methodName, MsgCpassOrd.ORDORDE0171.getMessage() + ae);
			errorCode = MsgCpassOrd.ORDORDE0171.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0171.getMessage() + " " + ae;
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;			
		}catch(Exception e) {
			log.error(methodName, MsgCpassOrd.ORDORDE0171.getMessage() + " " + e);
			errorCode = MsgCpassOrd.ORDORDE0171.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0171.getMessage();
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;
		}
		//3 STEP
		try {
			QueryResponse req1 = queryObjectService(targetQuery, ecmUuidNodo , "ecmUuidNodo" ,EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE );
			ObjectIdType strutturaAggregativaObjectId = req1.getObject().getObjects().get(0).getObjectId();
			protocollo.setStrutturaAggregativaObjectId(strutturaAggregativaObjectId.getValue());
		}catch(AcarisException ae) {
			log.error(methodName, MsgCpassOrd.ORDORDE0172.getMessage() + " " + ae);
			errorCode = MsgCpassOrd.ORDORDE0172.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0172.getMessage() + " " + ae;
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;			
		}catch(Exception e) {
			log.error(methodName, MsgCpassOrd.ORDORDE0172.getMessage() + " " + e);
			errorCode = MsgCpassOrd.ORDORDE0172.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0172.getMessage();
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);

		}
		return protocollo;
	}

}
