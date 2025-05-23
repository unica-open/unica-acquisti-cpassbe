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
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PagingResponseType;
import it.doqui.acta.acaris.common.PropertyFilterType;
import it.doqui.acta.acaris.common.Query;
import it.doqui.acta.acaris.common.QueryConditionType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.common.QueryableObjectType;
import it.doqui.acta.acaris.common.prt.EnumPFPGUL;
import it.doqui.acta.acaris.officialbookservice.CreaRegistrazione;
import it.doqui.acta.acaris.officialbookservice.DestinatarioEsterno;
import it.doqui.acta.acaris.officialbookservice.EnumTipoAPI;
import it.doqui.acta.acaris.officialbookservice.EnumTipoRegistrazioneDaCreare;
import it.doqui.acta.acaris.officialbookservice.EnumTipologiaSoggettoAssociato;
import it.doqui.acta.acaris.officialbookservice.IdentificazioneProtocollante;
import it.doqui.acta.acaris.officialbookservice.IdentificazioneRegistrazione;
import it.doqui.acta.acaris.officialbookservice.InfoCreazioneCorrispondente;
import it.doqui.acta.acaris.officialbookservice.InfoCreazioneRegistrazione;
import it.doqui.acta.acaris.officialbookservice.MittenteInterno;
import it.doqui.acta.acaris.officialbookservice.ProtocollazioneDocumentoEsistente;
import it.doqui.acta.acaris.officialbookservice.RegistrazionePartenza;
import it.doqui.acta.acaris.officialbookservice.RegistrazioneRequest;
import it.doqui.acta.acaris.officialbookservice.RiferimentoSoggettoEsistente;
import it.doqui.acta.acaris.officialbookservice.SoggettoEsterno;
import it.doqui.acta.acaris.subjectregistryservice.AnagraficaGenerica;
import it.doqui.acta.acaris.subjectregistryservice.EnumRegistryObjectType;
import it.doqui.acta.acaris.subjectregistryservice.RicercaSoggetto;
import it.doqui.acta.acaris.subjectregistryservice.RicercaSoggettoResponse;
/**
 * The Class AcarisServiceWrapper.
 */
public class ProtocollazioneActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(ProtocollazioneActaServiceWrapper.class);
	private ObjectIdType soggettoIdType = null;
	private boolean soggettoEsterno = false;
	//private String codiceFonteSoggettoEsterno = null;

	public ProtocolloOrdine protocolla(String ricercaSoggettoEstero, Utente utente,TestataOrdine testataOrdine,ProtocolloOrdine protocolloRegistrato) throws ParseException, DatatypeConfigurationException, it.doqui.acta.acaris.officialbookservice.AcarisException {
		String methodName = "protocolla";
		log.info(methodName, "START "  );
		//boolean isSoggettoEsterno = false;
		AnagraficaGenerica anagrafica = null;
		/*
		if(ricercaSoggettoEstero!=null && ricercaSoggettoEstero.equalsIgnoreCase("true")) {
			anagrafica = ricercaSoggetto( ricercaSoggettoEstero,  utente,testataOrdine,"GMS");
			if (anagrafica.getChiaveEsterna()!=null && !anagrafica.getChiaveEsterna().trim().equals("")) {
				isSoggettoEsterno = true;
			}			
		}else {
			anagrafica = ricercaSoggetto( ricercaSoggettoEstero,  utente,testataOrdine,"");
		}
		*/
		anagrafica = ricercaSoggetto( ricercaSoggettoEstero,  utente,testataOrdine,"GMS");
		if(!errorDesc.isEmpty()) {
			return protocolloRegistrato;	
		}
		//TODO registrazione
		String soggettoId ="";
		if(anagrafica.getChiaveEsterna()!=null && !"".equalsIgnoreCase((anagrafica.getChiaveEsterna()))) {
			soggettoId = anagrafica.getChiaveEsterna();
		}else {
			 soggettoId = anagrafica.getObjectId().getValue();
		}
		
		protocolloRegistrato = creaRegistrazione( ricercaSoggettoEstero,  utente,  testataOrdine, anagrafica,protocolloRegistrato, soggettoId);		
		log.info(methodName, "END "  );
		return protocolloRegistrato;
	}
	
	private AnagraficaGenerica ricercaSoggetto(String ricercaSoggettoEstero, Utente utente,TestataOrdine testataOrdine,String isGMS) {
		String methodName = "ricercaSoggetto";
		log.info(methodName," BEGIN.");
		AnagraficaGenerica anagraficaSoggetto = null;
		//AcarisOfficialBookImpl officialBookImpl = new AcarisOfficialBookImpl();
		//AcarisSubjectImpl subjectImpl = new AcarisSubjectImpl();
		//PropertyFilterType filterAll = new PropertyFilterType();
		//filterAll.setFilterType(EnumPropertyFilterOperation.ALL);
		String codiceFiscale = testataOrdine.getFornitore().getCodiceFiscale();
		String partitaIva = testataOrdine.getFornitore().getPartitaIva();
		boolean flagPersonaGiuridica = false;
		//boolean usaCodFisc = false;
		//boolean usaPartiva = false;
		//boolean isEsternoGms = false;
		try {			
			RicercaSoggetto ricSocReq = new RicercaSoggetto();
			ricSocReq.setPrincipalId(principalId);
			ricSocReq.setRepositoryId(repositoryId);
			ricSocReq.setFlagPersonaGiuridica(flagPersonaGiuridica);
			ricSocReq.setIdentitaDigitale(utente.getIdentitaDigitale());
			
			if(!isGMS.trim().equalsIgnoreCase("")) {
				ricSocReq.setFonteEsterna(isGMS);
				//isEsternoGms = true;
			}
			
			if(partitaIva!=null && !partitaIva.trim().equalsIgnoreCase("")) {
				ricSocReq.setPartitaIva(partitaIva);
				//usaCodFisc = false;
				//usaPartiva = true;
				flagPersonaGiuridica = true;
			}else {
				if(!codiceFiscale.trim().equalsIgnoreCase("")) {					
					if (controlloCodiceFiscaleNumerico(codiceFiscale)) {
						ricSocReq.setPartitaIva(codiceFiscale); 
						//usaPartiva = true;
						//usaCodFisc = false;
						flagPersonaGiuridica = true;
					}
					else {
						ricSocReq.setCodiceFiscale(codiceFiscale);
						//usaPartiva = false;
						//usaCodFisc = true;
						flagPersonaGiuridica = false;
					}			
				}
			}			
			
			ricSocReq.setFlagPersonaGiuridica(flagPersonaGiuridica);
			RicercaSoggettoResponse rSresponse = subjectRegistryService.getSubjectRegistryServicePort().ricercaSoggetto(ricSocReq);
			anagraficaSoggetto = rSresponse.getObject();

			if (anagraficaSoggetto != null) {
				if (anagraficaSoggetto.getChiaveEsterna() != null && !"".equalsIgnoreCase(anagraficaSoggetto.getChiaveEsterna())) {
					log.debug(methodName, "RicercaSoggetto - SoggettoEsterno GMS");
					soggettoIdType = new ObjectIdType();
					soggettoIdType.setValue(anagraficaSoggetto.getChiaveEsterna());
					soggettoEsterno = true;
					//codiceFonteSoggettoEsterno = anagraficaSoggetto.getCodiceFonte();
				} else if (anagraficaSoggetto.getObjectId() != null) {
					QueryableObjectType targetSogg = new QueryableObjectType();
					targetSogg.setObject(EnumRegistryObjectType.SOGGETTO_PROPERTIES_TYPE.value());
					
					PropertyFilterType filter = new PropertyFilterType();
					filter.setFilterType(EnumPropertyFilter.ALL);
					
					QueryConditionType criteriaSogg = new QueryConditionType();
					criteriaSogg.setValue(anagraficaSoggetto.getObjectId().getValue());
					criteriaSogg.setPropertyName(ConstantsActa.QUERY_OBJECT_ID);
					criteriaSogg.setOperator(EnumQueryOperator.EQUALS);

					Query input = new Query();
					input.setPrincipalId(principalId);
					input.setRepositoryId(repositoryId);
					input.setTarget(targetSogg);
					input.setFilter(filter);
					input.getCriteria().add(criteriaSogg);
					
					QueryResponse qr = subjectRegistryService.getSubjectRegistryServicePort().query(input);
					PagingResponseType soggResp = qr.getObject();
					
					
					if(soggResp!=null && soggResp.getObjects()!=null && soggResp.getObjects().size() >0){
						soggettoIdType = soggResp.getObjects().get(0).getObjectId();
						if(soggResp.getObjects().get(0).getProperties() != null && soggResp.getObjects().get(0).getProperties().size() > 0) {
							String stato = null;
							for(int j=0;j<soggResp.getObjects().get(0).getProperties().size();j++){
								if (ConstantsActa.QUERY_DESC_STATO_SOGGETTO.equalsIgnoreCase(soggResp.getObjects().get(0).getProperties().get(j).getQueryName().getPropertyName())) {
									stato = soggResp.getObjects().get(0).getProperties().get(j).getValue().getContent().get(0);
								}
							}
							if (!stato.equals("Definitivo")) {
								errorDesc = "PR-E-0002,  stato non definitivo";
								log.error(methodName, errorDesc);
							} 
						}
					}
				} else if (anagraficaSoggetto.getChiaveEsterna() == null || "".equalsIgnoreCase(anagraficaSoggetto.getChiaveEsterna())){
					errorDesc = "PR-E-0002, la ricerca del soggetto nel sistema documentale non ha dato risultati";
					log.error(methodName, errorDesc);
				}
				
				
			} else {
				errorDesc = "PR-E-0002, la ricerca del soggetto nel sistema documentale non ha dato risultati";
				log.error(methodName, errorDesc);
			}			
		} 
		//catch (AcarisException e) {
		//log.error("","[AcarisSubjectImpl::ricercaSoggetto] StackTrace:" + e.getMessage() );
		//errorDesc = e.getMessage();
		//} 
		catch (Exception e) {
			log.error("[OrdiniManagerBean::ricercaSoggettoPreInvioOrdine] ", e);
			String stringaErrore = ConstantsActa.ERRORE_SOGGETTI_MULTIPLI;
			if (e.getMessage().indexOf(stringaErrore) != -1) {
				String errorePartitaIva = e.getMessage().substring(e.getMessage().indexOf(ConstantsActa.ERRORE_PARTITA_IVA_MOLTEPLICE));
				errorDesc = ConstantsActa.ERRORE_SOGGETTI_MULTIPLI + " " + errorePartitaIva;
				log.error(methodName, errorDesc);
			}
			stringaErrore = ConstantsActa.ERRORE_SOGGETTO_INESISTENTE;
			if (e.getMessage().indexOf(stringaErrore) != -1) {
				errorDesc = ConstantsActa.ERRORE_SOGGETTO_INESISTENTE;
				log.error(methodName, errorDesc);
			}
			if (e.getMessage().indexOf(ConstantsActa.ERRORE_STATO_SOGGETTO_NON_DEFINITIVO) != -1) {
				errorDesc = ConstantsActa.ERRORE_STATO_SOGGETTO_NON_DEFINITIVO;
				log.error(methodName, errorDesc);
			}
			if("".equals(errorDesc)) {
				log.error("","[AcarisSubjectImpl::ricercaSoggetto] StackTrace:" + e.getMessage() );
				errorDesc = "il soggetto non e' presente in ACTA oppure non e' definitivo oppure il codice fiscale o la partita IVA sono presenti più volte. Verificare i dati in ACTA prima di procedere con la protocollazione dell'ordine";
			}
		}				
		log.info(methodName,  "END");
		return anagraficaSoggetto;
	}
	
	private ProtocolloOrdine creaRegistrazione(   String ricercaSoggettoEstero
															, Utente utente
															, TestataOrdine testata
															, AnagraficaGenerica anagrafica
															, ProtocolloOrdine protocollo
															, String soggettoId
															) throws ParseException, DatatypeConfigurationException, it.doqui.acta.acaris.officialbookservice.AcarisException {
		String methodName = "creaRegistrazione";
		//GregorianCalendar gcal = new GregorianCalendar();
		//XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		IdentificazioneRegistrazione idReg = new IdentificazioneRegistrazione();
		CreaRegistrazione cr = new CreaRegistrazione();
		cr.setRepositoryId(repositoryId);
		cr.setPrincipalId(principalId);
		cr.setTipologiaCreazione(EnumTipoRegistrazioneDaCreare.PROTOCOLLAZIONE_DOCUMENTO_ESISTENTE);
				
																												// parametro nodo responsabile sulla parametri		                                  // dall'oggetto restituito da archiviazione
		RegistrazioneRequest infoRichiestaCreazione = popolaRegistrazioneOrdine(idStrutturaLogged
																				, idNodoLogged
																				, soggettoIdType
																				, testata
																				, "SEGRETERIA"
																				, soggettoEsterno
																				, protocollo.getIdClassificazioneValue());
		//RegistrazioneRequest infoRichiestaCreazione = popolaRegistrazioneOrdine(idStrutturaLogged, idNodoLogged, soggettoIdType, testata, testata.getInfoProtocollo().getDescNodo(), soggettoEsterno, gestoreArchiviazioneDocumento.getIdClassificazione());
		/* vecchia gestione 
		List listaRegistrazione = AcarisMappingUtils.propertyToPropertyType(infoRichiestaCreazione, true);
		PropertyType[] listaProperty1 = null;
		if (listaRegistrazione != null && listaRegistrazione.size() > 0) {
			listaProperty1 = new PropertyType[listaRegistrazione.size()];
			for (int i = 0; i < listaProperty1.length; i++) {
				listaProperty1[i] = (PropertyType) listaRegistrazione.get(i);
			}
		}
		 */	
		cr.setInfoRichiestaCreazione(infoRichiestaCreazione);
		try {
			idReg = officialBookService.getOfficialBookServicePort().creaRegistrazione(cr).getIdentificazioneCreazione();
		} catch (it.doqui.acta.acaris.officialbookservice.AcarisException e) {
			errorDesc = "errore in crazione registrazione " + e.getMessage();
			log.error(methodName, e.getMessage());
			return protocollo;
		}
		
		//servizio 5 OB: getProperties
		if (idReg != null) {
			QueryableObjectType target = new QueryableObjectType();
			target.setObject("RegistrazioneView");
			PropertyFilterType filter = new PropertyFilterType();
			filter.setFilterType(EnumPropertyFilter.ALL);
			
			QueryConditionType criteria = new QueryConditionType();
			criteria.setValue(idReg.getRegistrazioneId().getValue());
			criteria.setPropertyName("objectId");
			criteria.setOperator(EnumQueryOperator.EQUALS);
			
			Query regReq = new Query();
			regReq.setPrincipalId(principalId);
			regReq.setRepositoryId(repositoryId);
			regReq.setTarget(target);
			regReq.setFilter(filter);
			regReq.getCriteria().add(criteria);
			
			PagingResponseType respRegistraz = officialBookService.getOfficialBookServicePort().query(regReq).getObject();
			
			String uuidRegProtocolloOrdine = getPropertyValueByName(respRegistraz.getObjects(), ConstantsActa.QUERY_UUID);
			String oggetto = getPropertyValueByName(respRegistraz.getObjects(), ConstantsActa.QUERY_OGGETTO);
			String numeroReg = getPropertyValueByName(respRegistraz.getObjects(), ConstantsActa.QUERY_CODICE);
			String annoReg = getPropertyValueByName(respRegistraz.getObjects(), ConstantsActa.QUERY_DATA_PROTOCOLLO);
			annoReg = annoReg.substring(annoReg.length()-4);
			String aoo = getPropertyValueByName(respRegistraz.getObjects(), ConstantsActa.QUERY_AOO_PROTOCOLLANTE); // da verificare

			protocollo.setUuidRegProtocolloOrdine(uuidRegProtocolloOrdine);			
			protocollo.setAnnoProtocollo(Integer.parseInt(annoReg));
			protocollo.setNumeroProtocollo(numeroReg);
			protocollo.setDescrizioneProtocollo(oggetto);
			protocollo.setDataProtocollo(new Date());
			protocollo.setAoo(aoo);
		}			
		return protocollo;
	}

	private boolean controlloCodiceFiscaleNumerico(String codiceFiscale) {
		String carattereSingolo = null;
		boolean codFiscNumerico = true;
		for (int i = 0; i < codiceFiscale.length() && codFiscNumerico; i++) {
			carattereSingolo = codiceFiscale.substring(i, i+1);
			if (carattereSingolo.compareTo("0") < 0 || carattereSingolo.compareTo("9") > 0) {
				codFiscNumerico = false;
			}
		}
		return codFiscNumerico;
	}
	
	private ProtocollazioneDocumentoEsistente popolaRegistrazioneOrdine( long idStrutturaLogged, long idNodoLogged, ObjectIdType soggettoIdType, TestataOrdine testata, String descNodo, boolean soggettoEsterno, String idClassValue) throws ParseException, DatatypeConfigurationException, it.doqui.acta.acaris.officialbookservice.AcarisException {
		String methodName = "popolaRegistrazioneOrdine";
		log.info(methodName, "BEGIN");
		GregorianCalendar gcal = new GregorianCalendar();
    	XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		ProtocollazioneDocumentoEsistente reg = new ProtocollazioneDocumentoEsistente();
		ObjectIdType idAooType = new ObjectIdType();
		idAooType = getIdTypeByDbKey("AOOPropertiesType", idAOO, ConstantsActa.MODULO_BKO);
		
		ObjectIdType idStrutturaLoggedType = new ObjectIdType();
		idStrutturaLoggedType = getIdTypeByDbKey("StrutturaPropertiesType", idStrutturaLogged, ConstantsActa.MODULO_BKO);
		
		ObjectIdType idNodoLoggedType = new ObjectIdType();
		idNodoLoggedType = getIdTypeByDbKey("NodoPropertiesType", idNodoLogged, ConstantsActa.MODULO_BKO);
		
		reg.setAooProtocollanteId(idAooType);
		ObjectIdType idClass = new ObjectIdType();
		idClass.setValue(idClassValue);
		reg.setClassificazioneId(idClass);
		
		RegistrazionePartenza regPartenza = new RegistrazionePartenza();		
		regPartenza.setTipoRegistrazione(EnumTipoAPI.PARTENZA);
		
		InfoCreazioneRegistrazione info = new InfoCreazioneRegistrazione();
		info.setForzareSeRegistrazioneSimile(true);
		info.setOggetto(testata.getDescrizione());
		info.setDataDocumento(xgcal);
		regPartenza.setInfoCreazione(info);
		DestinatarioEsterno destEst0 = new DestinatarioEsterno();
		InfoCreazioneCorrispondente icc = new InfoCreazioneCorrispondente();
		if (soggettoEsterno) {
			SoggettoEsterno datiSoggettoEsterno = new SoggettoEsterno();
			datiSoggettoEsterno.setChiaveEsterna(this.soggettoIdType.getValue());
			if ("PF".equalsIgnoreCase(testata.getFornitore().getNaturaGiuridica())) {
				datiSoggettoEsterno.setIdPFPGUL(EnumPFPGUL.PF);
			} else {
				datiSoggettoEsterno.setIdPFPGUL(EnumPFPGUL.PG);
			}
			datiSoggettoEsterno.setCodiceTipoSoggetto(datiSoggettoEsterno.getIdPFPGUL().toString());
			datiSoggettoEsterno.setCodiceFonte(ConstantsActa.GMS);			
			icc.setInfoSoggettoAssociato(datiSoggettoEsterno);
			reg.setSenzaCreazioneSoggettiEsterni(Boolean.TRUE);
		} else {
			RiferimentoSoggettoEsistente rsa = new RiferimentoSoggettoEsistente();
			rsa.setTipologia(EnumTipologiaSoggettoAssociato.SOGGETTO_ACTA);
			
			if ("PF".equalsIgnoreCase(testata.getFornitore().getNaturaGiuridica())) {				
				rsa.setIdPFPGUL(EnumPFPGUL.PF);
			} else {
				rsa.setIdPFPGUL(EnumPFPGUL.PG);
			}
			
			rsa.setSoggettoId(this.soggettoIdType);
			icc.setInfoSoggettoAssociato(rsa);	
			reg.setSenzaCreazioneSoggettiEsterni(false);
		}
		icc.setDenominazione(testata.getFornitore().getRagioneSociale());
		destEst0.setCorrispondente(icc);		
		/*
		QueryConditionType[] criteria = new QueryConditionType[1];
		criteria[0] = new QueryConditionType();
		criteria[0].setValue(idAOO.toString());
		criteria[0].setPropertyName("idAoo");
		criteria[0].setOperator(EnumQueryOperator.EQUALS);
		QueryableObjectType target = new QueryableObjectType();
		target.setObject("RuoloCorrispondenteDefaultView");
		Query parQuery = new Query();
		PropertyFilterType filter = new PropertyFilterType();
		filter.setFilterType(EnumPropertyFilter.ALL);
		parQuery.setFilter(filter);
		parQuery.setPrincipalId(principalId);
		parQuery.setRepositoryId(repositoryId);
		parQuery.setTarget(target);
		parQuery.getCriteria().add(criteria[0]);
		PagingResponseType ruoloCorrispondente = officialBookService.getOfficialBookServicePort().query(parQuery).getObject();
		*/
		PagingResponseType ruoloCorrispondente = queryOfficialBookService("RuoloCorrispondenteDefaultView", idAOO.toString(),"idAoo",EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS).getObject();
		
		
		if (ruoloCorrispondente != null && ruoloCorrispondente.getObjects() != null && ruoloCorrispondente.getObjects().size() > 0) {
			for (int i = 0; i < ruoloCorrispondente.getObjects().size(); i++) {
				if (ruoloCorrispondente.getObjects().get(i).getProperties() != null && ruoloCorrispondente.getObjects().get(i).getProperties().size() > 0) {
					for (int j = 0; j < ruoloCorrispondente.getObjects().get(i).getProperties().size(); j++) {
						if (ConstantsActa.QUERY_DB_KEY.equalsIgnoreCase(ruoloCorrispondente.getObjects().get(i).getProperties().get(j).getQueryName().getPropertyName())) {
							//destEst0.setIdRuoloCorrispondente(new Long(ruoloCorrispondente.getObjects().get(i).getProperties().get(j).getValue().getContent().get(0)).longValue());
							destEst0.setIdRuoloCorrispondente(Long.parseLong(ruoloCorrispondente.getObjects().get(i).getProperties().get(j).getValue().getContent().get(0)));
							//Long.parseLong("0", 10)  
						}
					}
				}
			}
		} else {
			log.error("","[HeavyManagerBean::popolaRegistrazione] NON HO TROVATO IL RUOLO CORRISPONDENTE!! CI SARA' UN ERRORE SULLA CREA REGISTRAZIONE!!");
		}
		info.getDestinatarioEsterno().add(destEst0);
		
		MittenteInterno mittInt0 = new MittenteInterno();
		icc = new InfoCreazioneCorrispondente();
		RiferimentoSoggettoEsistente rsaDest = new RiferimentoSoggettoEsistente();
		rsaDest.setTipologia(EnumTipologiaSoggettoAssociato.NODO);
		rsaDest.setIdPFPGUL(EnumPFPGUL.PG);
		rsaDest.setSoggettoId(idNodoLoggedType);
		icc.setDenominazione(descNodo);
		icc.setInfoSoggettoAssociato(rsaDest);		
		mittInt0.setCorrispondente(icc);
		info.getMittenteInterno().add(mittInt0);
		
		IdentificazioneProtocollante prot = new IdentificazioneProtocollante();
		prot.setStrutturaId(idStrutturaLoggedType);
		prot.setNodoId(idNodoLoggedType);		
		info.setProtocollante(prot);		
		regPartenza.setInfoCreazione(info);
		reg.setRegistrazioneAPI(regPartenza);
		
		//dati del protocollo					
		/*
		protocolloOrdine.setAnnoProtocollo(Integer.parseInt(gda.getAnnoProtocollo()));
		protocolloOrdine.setNumeroProtocollo(gda.getNumeroProtocollo());
		protocolloOrdine.setAoo(String.valueOf(idAOO));
		protocolloOrdine.setDescrizioneProtocollo(gda.getDescrizioneProtocollo());
		protocolloOrdine.setDataProtocollo(DateUtility.stringToDate(gda.getDataProtocollo()));
		*/
		
		return reg;
	}
	

	
}
