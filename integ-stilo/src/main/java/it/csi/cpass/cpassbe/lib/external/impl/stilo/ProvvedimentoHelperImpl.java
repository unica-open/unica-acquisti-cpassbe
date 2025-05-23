/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - STILO
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.stilo;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;

import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.EsitoProvvedimento;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.external.ProvvedimentoHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.utils.StringUtility;
import it.eng.auriga.repository2.model.baseoutput.BaseOutputWS;
import it.eng.auriga.repository2.model.identificazioneud.EstremiRegNumType;
import it.eng.auriga.repository2.model.identificazioneud.EstremiXIdentificazioneUD;
import it.eng.auriga.repository2.model.outputdatiud.DatiUD;
import it.eng.auriga.repository2.model.outputdatiud.DatiUD.OggettoUD;
import it.eng.auriga.repository2.model.outputdatiud.LivelloGerarchiaType;
import it.eng.auriga.repository2.model.outputdatiud.RegistrazioneNumerazioneType;
import it.eng.auriga.repository2.model.searchoutput.Lista;
import it.eng.auriga.repository2.model.searchoutput.Lista.Riga;
import it.eng.auriga.repository2.model.searchoutput.Lista.Riga.Colonna;
import it.eng.auriga.repository2.model.searchoutput.OggDiTabDiSistemaType;
import it.eng.auriga.repository2.model.searchoutput.RequestGetAttoPerAcquisti;
import it.eng.auriga.repository2.model.searchoutput.ResponseGetAttoPerAcquisti;
import it.eng.auriga.repository2.model.searchoutput.ResponseGetAttoPerAcquisti.DatiAtto;
import it.eng.auriga.repository2.model.searchoutput.TrovaDocFolder;
import it.eng.auriga.repository2.model.searchoutput.TrovaDocFolder.FiltriAvanzati;
import it.eng.auriga.repository2.model.searchoutput.TrovaDocFolder.FiltriAvanzati.RegistrazioneDoc;
import it.eng.auriga.repository2.model.searchoutput.TrovaDocFolder.FiltriPrincipali;
import it.eng.auriga.repository2.webservices.getattoperacquisti.WSGetAttoPerAcquistiService;
import it.eng.auriga.repository2.webservices.getattoperacquisti.WSIGetAttoPerAcquisti;
import it.eng.auriga.repository2.webservices.getmetadataud.Service;
import it.eng.auriga.repository2.webservices.getmetadataud.ServiceResponse;
import it.eng.auriga.repository2.webservices.getmetadataud.WSGetMetadataUdService;
import it.eng.auriga.repository2.webservices.getmetadataud.WSIGetMetadataUd;
import it.eng.auriga.repository2.webservices.trovadocfolder.WSITrovaDocFolder;
import it.eng.auriga.repository2.webservices.trovadocfolder.WSTrovaDocFolderService;

/**
 * Example POJO helper impl
 */
public class ProvvedimentoHelperImpl extends BaseStiloHelperImpl implements ProvvedimentoHelper {
	
	@Deprecated
	private TrovaDocFolder composeTrovaDocFolder(Map<String, String> params, Provvedimento provvedimento,String enteCode) {
		log.info("composeTrovaDocFolder", "ente "+ enteCode +" START" );
		TrovaDocFolder estremi = new TrovaDocFolder();
		FiltriPrincipali fp =new FiltriPrincipali();
		fp.setTipoOggettiDaCercare("D");
		estremi.setFiltriPrincipali(fp);	
		FiltriAvanzati fa =new FiltriAvanzati();
		RegistrazioneDoc rd = new RegistrazioneDoc();

		if(enteCode.equalsIgnoreCase("REGP")) {	
			String decodificaNome = "Determina dirigenziale";
			if(provvedimento.getProvvedimentoTipo().getCodice().equals("AD")){
				decodificaNome = "Atto dirigenziale";
			}else if (provvedimento.getProvvedimentoTipo().getCodice().equals("DG")) {
				decodificaNome = "Delibera di giunta";
			}
			/*
			String decodificaNome = descDD;
			if(provvedimento.getProvvedimentoTipo().getCodice().equals("AD")){
				decodificaNome = descAD;
			}else if (provvedimento.getProvvedimentoTipo().getCodice().equals("DG")) {
				decodificaNome = descDG;
			}
			*/			
			OggDiTabDiSistemaType oggDiTabDiSistemaType = new OggDiTabDiSistemaType();
			oggDiTabDiSistemaType.setDecodificaNome(decodificaNome);
			fa.setTipoDocumento(oggDiTabDiSistemaType );	
			
		}else {
			rd.setSiglaReg(provvedimento.getProvvedimentoTipo().getCodice());
		}
		
		rd.setCategoriaReg("R");
		rd.setAnnoReg(provvedimento.getAnno());
		rd.setNumRegDa(Integer.parseInt(provvedimento.getNumero().trim()));
		rd.setNumRegA(Integer.parseInt(provvedimento.getNumero().trim()));		
		fa.setRegistrazioneDoc(rd);
		estremi.setFiltriAvanzati(fa);
		
		List<BigInteger> a = estremi.getLimitaEstrazioneAlCampo();
		a.add(new BigInteger("2"));
		a.add(new BigInteger("3"));
		a.add(new BigInteger("4"));
		a.add(new BigInteger("10"));
		a.add(new BigInteger("11"));
		a.add(new BigInteger("18"));   // descrizione provvedimento
		a.add(new BigInteger("226"));  // estrapolazione della direzione
		log.info("composeTrovaDocFolder", "ente "+ enteCode +" STOP" );
		return estremi;
	}

	@Override
	@Deprecated
	public ExternalServiceResponseWrapper<EsitoProvvedimento> getDocumentiProvvedimenti(Map<String, String> params, Provvedimento provvedimento,String enteCode) {
		log.info("getDocumentiProvvedimenti", "*************getProvvedimenti STILO*******************");
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<EsitoProvvedimento> response = new ExternalServiceResponseWrapper<>();
		EsitoProvvedimento ep = new EsitoProvvedimento();
		readAttachment= true;

		URL wsdlUrl = getWSDLUrlRicercaDoc(params);	
		WSTrovaDocFolderService wSTrovaDocFolderService =null;
		try {
			wSTrovaDocFolderService = wsdlUrl != null ? new WSTrovaDocFolderService(wsdlUrl) : new WSTrovaDocFolderService();		
			addHandlerResolver(params, wSTrovaDocFolderService);
			WSITrovaDocFolder wSITrovaDocFolder = wSTrovaDocFolderService.getWSTrovaDocFolderPort();
			TrovaDocFolder trovaDocFolder = composeTrovaDocFolder(params, provvedimento,enteCode);	
			it.eng.auriga.repository2.webservices.trovadocfolder.Service req = composeTrovaDocFolderRequest(params, trovaDocFolder);					
			log.info("getDocumentiProvvedimenti", "***************************************************************");
			log.info("getDocumentiProvvedimenti", "*************Chiamata servizio STILO***************************");
			log.info("getDocumentiProvvedimenti", "***************************************************************");			
			it.eng.auriga.repository2.webservices.trovadocfolder.ServiceResponse res = wSITrovaDocFolder.serviceOperation(req);
			log.info("getDocumentiProvvedimenti", "***************************************************************");		
			byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
			String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);
			log.debug("getDocumentiProvvedimenti response", "xml " + xml);
			BaseOutputWS bows = JAXBUtility.unmarshall(xml, BaseOutputWS.class);
			List<Provvedimento> listProvv= new ArrayList<Provvedimento>();			
			if(bows.getWSResult()==null || bows.getWSResult().equals("0")) {
				ep.setLista(listProvv);
				ep.setCodErrore("" + bows.getWSError().getErrorNumber());
				ep.setDescErrore(bows.getWSError().getErrorMessage());				
			}else {
				response.setSuccess(true);
				InputStream isAllegatoResult = CpassThreadLocalContainer.ALLEGATI.get().get(0);
				String xmlAllegatoResult = getXmlResultFromAttachment(isAllegatoResult);
				Lista datiUD = JAXBUtility.unmarshall(xmlAllegatoResult, Lista.class);
				if(datiUD.getRiga()!=null) {
					log.debug("getDocumentiProvvedimenti", "numero id trovati --> " + datiUD.getRiga().size());
					for( Riga riga : datiUD.getRiga()) {
						//Colonna colId   = riga.getColonna().get(1);
						String colDesc = riga.getColonna().get(6).getContent();
						String coll226 = riga.getColonna().get(7).getContent();						
						//////////////////////////////////////////////////////////////////////////////////////////
						// TODO TACCONE IN ATTESA DEL CAMBIO SERVIZIO STILO CON COLONNA DEDICATA ALLA DIREZIONE						
						String settore  = coll226;
						if(enteCode.equalsIgnoreCase("REGP")) {
							settore  = StringUtility.estraiStringa(coll226, '.', '.');							
						}else {
							settore  = StringUtility.estraiStringa(coll226, '.', ' ');
							if(settore==null || settore.trim().equals("")) {
								settore  = coll226.substring(0, coll226.indexOf(" "));
							}
						}
						///////////////////////////////////////////////////////////////////////////////////////////
						String coll3    = riga.getColonna().get(2).getContent();
						String numero   = provvedimento.getNumero();
						Integer anno     = provvedimento.getAnno();						
						log.debug("getDocumentiProvvedimenti coll226 ", coll226);		
						log.debug("getDocumentiProvvedimenti coll3   ", coll3);								
						if(settore!=null && !settore.trim().equals("")) {
							Provvedimento prov =initProvvedimento( colDesc, settore,  anno, numero, provvedimento);
							listProvv.add(prov ); 
						}else {
							log.error("getDocumentiProvvedimenti","getDocumentiProvvedimenti settore non trovato dal parser su descrizione  " + coll226 );	
						}
						
					}
				}
				ep.setLista(listProvv);
			}			
			response.setSuccess(true);
			response.setResponse(ep);
			log.info("getProvvedimenti", "****** Fine getProvvedimenti STILO *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Stilo non disponibile");
			response.setErrors(errors);
			ep.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Stilo non disponibile").getFullErrorMessage());
			ep.setDescErrore(e.getMessage());
			log.error("getProvvedimenti", "Desc error --> "+e.getMessage());
			response.setResponse(ep);
			return response;
		}
	}
	
	@Deprecated
	private Provvedimento initProvvedimento(String descrizione,String codSettore, Integer annoReg,String numReg,Provvedimento provvedimento) {
		Provvedimento provv = new Provvedimento();
		Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		provv.setAnno(annoReg);
		provv.setNumero(numReg!= null ? numReg.trim():"0");
		provv.setDescrizione(descrizione);
		Settore settore = new Settore();
		settore.setCodice(codSettore);
		provv.setSettore(settore);
		provv.setDataCreazione(new Date());
		provv.setDataModifica(new Date());
		provv.setDataValiditaInizio(new Date());
		/*
		provv.setDataCreazione(toDate(data));
		provv.setDataModifica(toDate(data));
		provv.setDataValiditaInizio(toDate(data));
		*/
		provv.setProvvedimentoTipo(provvedimento.getProvvedimentoTipo());
		provv.setEnte(ente);
		provv.setDataValiditaFine(null);
		return provv;
	}
	
	
	public  String getXmlResultFromAttachment(InputStream inputStream) throws IOException{
        String contentStringFile = IOUtils.toString(inputStream, StandardCharsets.ISO_8859_1);	
        log.debug("getXmlResultFromAttachment ", "file giunto " + contentStringFile);
        String contentXmlFile = unescapeHtml(contentStringFile);
        contentXmlFile = contentXmlFile.replaceAll("<WarningMessage>.*</WarningMessage>", "");
        log.debug("getXmlResultFromAttachment ", "xml " + contentXmlFile);
        return contentXmlFile;
    }

	/**
	 * @param contentStringFile
	 * @return
	 */
	protected String unescapeHtml(String contentStringFile) {
		contentStringFile = contentStringFile.replaceAll("&#160;", " ");
        String contentXmlFile = StringEscapeUtils.unescapeHtml4(contentStringFile);
		return contentXmlFile;
	}
		
	@Override
	@Deprecated
	public ExternalServiceResponseWrapper<EsitoProvvedimento> getProvvedimenti(Map<String, String> params, Provvedimento provvedimento) {
		log.info("getProvvedimenti", "*************getProvvedimenti STILO*******************");
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<EsitoProvvedimento> response = new ExternalServiceResponseWrapper<>();
		EsitoProvvedimento ep = new EsitoProvvedimento();
		readAttachment= true;

		URL wsdlUrl = getWSDLUrl(params);	
		WSGetMetadataUdService wSGetMetadataUdService =null;
		try {
			wSGetMetadataUdService = wsdlUrl != null ? new WSGetMetadataUdService(wsdlUrl) : new WSGetMetadataUdService();		
			addHandlerResolver(params, wSGetMetadataUdService);
			WSIGetMetadataUd wSIGetMetadataUd = wSGetMetadataUdService.getWSGetMetadataUdPort();
			EstremiXIdentificazioneUD estremiXIdentificazioneUD = composeEstremiXIdentificazioneUD(params, provvedimento);
			Service req = composeRequestMetadataUd(params, estremiXIdentificazioneUD);			
			log.info("getProvvedimenti", "***************************************************************");
			log.info("getProvvedimenti", "*************Chiamata servizio STILO***************************");
			log.info("getProvvedimenti", "***************************************************************");
			ServiceResponse res = wSIGetMetadataUd.serviceOperation(req);
			log.info("getProvvedimenti", "***************************************************************");
			
			byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
			String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);
			log.debug("response", "xml " + xml);
			BaseOutputWS bows = JAXBUtility.unmarshall(xml, BaseOutputWS.class);
			List<Provvedimento> listProvv= new ArrayList<Provvedimento>();
			
			if(bows.getWSResult()==null || bows.getWSResult().equals("0")) {
				ep.setLista(listProvv);
				ep.setCodErrore("" + bows.getWSError().getErrorNumber());
				ep.setDescErrore(bows.getWSError().getErrorMessage());				
			}else {
				response.setSuccess(true);
				InputStream isAllegatoResult = CpassThreadLocalContainer.ALLEGATI.get().get(0);
				String xmlAllegatoResult = getXmlResultFromAttachment(isAllegatoResult);
				DatiUD datiUD = JAXBUtility.unmarshall(xmlAllegatoResult, DatiUD.class);
				//estraggo i dati del provvedimento
				List<RegistrazioneNumerazioneType> lista = datiUD.getRegistrazioneData();
				OggettoUD descrizione = datiUD.getOggettoUD(); 
				for(RegistrazioneNumerazioneType rnt : lista) {
					if(rnt.getCategoriaReg().equalsIgnoreCase("R")) {
						Provvedimento prov = initProvvedimento(rnt, descrizione.getValue(),provvedimento);
						listProvv.add(prov);
					}
				}
				ep.setLista(listProvv);
			}			
			response.setSuccess(true);
			response.setResponse(ep);
			log.info("getProvvedimenti", "****** Fine getProvvedimenti STILO *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			ep.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Stilo non disponibile").getFullErrorMessage());
			ep.setDescErrore(e.getMessage());
			log.error("getProvvedimenti", "Desc error --> "+e.getMessage());
			response.setResponse(ep);
			return response;
		}
	}
	
	@Override
	@Deprecated
	public ExternalServiceResponseWrapper<EsitoProvvedimento> getDocumentiProvvedimentiAll(Map<String, String> params, Provvedimento provvedimento,String enteCode) {
		log.info("getDocumentiProvvedimentiAll", "*************getProvvedimenti STILO*******************");
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<EsitoProvvedimento> response = new ExternalServiceResponseWrapper<>();
		EsitoProvvedimento ep = new EsitoProvvedimento();
		readAttachment= true;
		URL wsdlUrl = getWSDLUrlRicercaDoc(params);	
		WSTrovaDocFolderService wSTrovaDocFolderService =null;
		try {
			wSTrovaDocFolderService = wsdlUrl != null ? new WSTrovaDocFolderService(wsdlUrl) : new WSTrovaDocFolderService();		
			addHandlerResolver(params, wSTrovaDocFolderService);
			WSITrovaDocFolder wSITrovaDocFolder = wSTrovaDocFolderService.getWSTrovaDocFolderPort();
			TrovaDocFolder trovaDocFolder = composeTrovaDocFolder(params, provvedimento,enteCode);	
			it.eng.auriga.repository2.webservices.trovadocfolder.Service req = composeTrovaDocFolderRequest(params, trovaDocFolder);					
			log.info("getDocumentiProvvedimentiAll", "***************************************************************");
			log.info("getDocumentiProvvedimentiAll", "*************Chiamata servizio STILO***************************");
			log.info("getDocumentiProvvedimentiAll", "***************************************************************");			
			it.eng.auriga.repository2.webservices.trovadocfolder.ServiceResponse res = wSITrovaDocFolder.serviceOperation(req);
			log.info("getDocumentiProvvedimenti", "***************************************************************");			
			byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
			String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);
			log.info("getDocumentiProvvedimentiAll response", "xml " + xml);
			BaseOutputWS bows = JAXBUtility.unmarshall(xml, BaseOutputWS.class);
			List<Provvedimento> listProvv= new ArrayList<Provvedimento>();			
			if(bows.getWSResult()==null || bows.getWSResult().equals("0")) {
				ep.setLista(listProvv);
				ep.setCodErrore("" + bows.getWSError().getErrorNumber());
				ep.setDescErrore(bows.getWSError().getErrorMessage());				
			}else {
				response.setSuccess(true);
				InputStream isAllegatoResult = CpassThreadLocalContainer.ALLEGATI.get().get(0);
				String xmlAllegatoResult = getXmlResultFromAttachment(isAllegatoResult);
				Lista datiUD = JAXBUtility.unmarshall(xmlAllegatoResult, Lista.class);
				if(datiUD.getRiga()!=null) {
					log.info("getDocumentiProvvedimenti", "numero id trovati --> " + datiUD.getRiga().size());
					for( Riga riga : datiUD.getRiga()) {
						Colonna id = riga.getColonna().get(1);
						log.info("getDocumentiProvvedimenti", id.getContent());		
						List<Provvedimento> list = getProvvedimentiById(params,provvedimento,new BigInteger (id.getContent()));
						listProvv.addAll(list);
					}
				}
				ep.setLista(listProvv);
			}		
			response.setSuccess(true);
			response.setResponse(ep);
			log.info("getDocumentiProvvedimentiAll", "****** Fine getProvvedimenti STILO *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			ep.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Stilo non disponibile").getFullErrorMessage());
			ep.setDescErrore(e.getMessage());
			log.error("getDocumentiProvvedimentiAll", "Desc error --> "+e.getMessage());
			response.setResponse(ep);
			return response;
		}
	}
	
	private Provvedimento initProvvedimento(RegistrazioneNumerazioneType rnt,String descrizione,Provvedimento provvedimento) {
		Provvedimento provv = new Provvedimento();
		Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		XMLGregorianCalendar data = rnt.getDataOraReg();
		String codSettore = "";
		try {
			List<LivelloGerarchiaType> liv = rnt.getUOReg().getLivelloUO();					
			for(LivelloGerarchiaType livello : liv) {
				if(livello.getNro()==2) {
					codSettore = livello.getCodice();				
				}
			}
		}catch(Exception e) {
			log.error("initProvvedimento ", " Settore non presente nel XML errore " + e.getMessage());
		}		
		provv.setAnno(rnt.getAnnoReg());
		provv.setNumero(String.valueOf(rnt.getNumReg()));
		provv.setDescrizione(descrizione);
		Settore settore = new Settore();
		settore.setCodice(codSettore);
		provv.setSettore(settore);
		provv.setDataCreazione(toDate(data));
		provv.setDataModifica(toDate(data));
		provv.setDataValiditaInizio(toDate(data));
		provv.setProvvedimentoTipo(provvedimento.getProvvedimentoTipo());
		provv.setEnte(ente);
		provv.setDataValiditaFine(null);
		return provv;
	}

	public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

	private EstremiXIdentificazioneUD composeEstremiXIdentificazioneUD(Map<String, String> params, Provvedimento provvedimento) {
		EstremiXIdentificazioneUD estremi = new EstremiXIdentificazioneUD();
		estremi.setEstremiRegNum(new EstremiRegNumType());
		estremi.getEstremiRegNum().setCategoriaReg("R");		
		estremi.getEstremiRegNum().setSiglaReg(provvedimento.getProvvedimentoTipo().getCodice()); // DD
		estremi.getEstremiRegNum().setAnnoReg(provvedimento.getAnno());
		estremi.getEstremiRegNum().setNumReg(Integer.parseInt(provvedimento.getNumero()));
		log.debug("composeEstremiXIdentificazioneUD","estremi chiamata categoria R " +"sigla Reg: "+provvedimento.getProvvedimentoTipo().getCodice()+" anno:"+provvedimento.getAnno() + " numero:"+ provvedimento.getNumero()); 
		return estremi;
	}

	private EstremiXIdentificazioneUD composeEstremiById(Map<String, String> params, Provvedimento provvedimento, BigInteger idProvv) {
		EstremiXIdentificazioneUD estremi = new EstremiXIdentificazioneUD();
		estremi.setIdUD(idProvv);
		return estremi;
	}

	private List<Provvedimento> getProvvedimentiById(Map<String, String> params, Provvedimento provvedimento,BigInteger idProvv) {
		log.info("getProvvedimentiById", "*************getProvvedimentiById STILO*******************");
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<EsitoProvvedimento> response = new ExternalServiceResponseWrapper<>();
		readAttachment= true;

		URL wsdlUrl = getWSDLUrl(params);	
		WSGetMetadataUdService wSGetMetadataUdService =null;
		List<Provvedimento> listProvv= new ArrayList<Provvedimento>();
		try {
			wSGetMetadataUdService = wsdlUrl != null ? new WSGetMetadataUdService(wsdlUrl) : new WSGetMetadataUdService();		
			addHandlerResolver(params, wSGetMetadataUdService);
			WSIGetMetadataUd wSIGetMetadataUd = wSGetMetadataUdService.getWSGetMetadataUdPort();

			EstremiXIdentificazioneUD estremiXIdentificazioneUD = composeEstremiById(params, provvedimento, idProvv);
			Service req = composeRequestMetadataUd(params, estremiXIdentificazioneUD);			
			log.info("getProvvedimentiById", "***************************************************************");
			log.info("getProvvedimentiById", "*************Chiamata servizio STILO BY ID" +idProvv);
			log.info("getProvvedimentiById", "***************************************************************");
			ServiceResponse res = wSIGetMetadataUd.serviceOperation(req);
			log.info("getProvvedimentiById", "***************************************************************");			
			byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
			String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);
			log.debug("response", "xml " + xml);
			BaseOutputWS bows = JAXBUtility.unmarshall(xml, BaseOutputWS.class);
			
			response.setSuccess(true);
			InputStream isAllegatoResult = CpassThreadLocalContainer.ALLEGATI.get().get(0);
			String xmlAllegatoResult = getXmlResultFromAttachment(isAllegatoResult);
			DatiUD datiUD = JAXBUtility.unmarshall(xmlAllegatoResult, DatiUD.class);
			//estraggo i dati del provvedimento
			List<RegistrazioneNumerazioneType> lista = datiUD.getRegistrazioneData();
			OggettoUD descrizione = datiUD.getOggettoUD(); 
			for(RegistrazioneNumerazioneType rnt : lista) {
				if(rnt.getCategoriaReg().equalsIgnoreCase("R")) {
					Provvedimento prov = initProvvedimento(rnt, descrizione.getValue(),provvedimento);
					listProvv.add(prov);
				}
			}
		}catch(Exception e) {
			response.setSuccess(false);
			log.error("getProvvedimentiById", "Desc error --> "+e.getMessage());
		}
		return listProvv;
	}
	
	@Override
	public ExternalServiceResponseWrapper<EsitoProvvedimento> getAttiPerAcquisti(Map<String, String> params,Provvedimento provvedimento, String enteCode) {
		log.info("getDocumentiProvvedimenti", "*************getProvvedimenti STILO*******************");
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<EsitoProvvedimento> response = new ExternalServiceResponseWrapper<>();
		EsitoProvvedimento ep = new EsitoProvvedimento();
		readAttachment= true;
		URL wsdlUrl = getWSDLUrl(params);	
		
		WSGetAttoPerAcquistiService wSGetAttoPerAcquistiService =null;
		try {
			//wSGetAttoPerAcquistiService = wsdlUrl != null ? new WSGetAttoPerAcquistiService(wsdlUrl) : new WSGetAttoPerAcquistiService();		
			wSGetAttoPerAcquistiService = new WSGetAttoPerAcquistiService(wsdlUrl);
			
			addHandlerResolver(params, wSGetAttoPerAcquistiService);
			WSIGetAttoPerAcquisti wSIGetAttoPerAcquisti = wSGetAttoPerAcquistiService.getWSGetAttoPerAcquistiPort();			
			RequestGetAttoPerAcquisti RequestGetAttoPerAcquisti = composeNumerazioneType(params, provvedimento,enteCode);				
			it.eng.auriga.repository2.webservices.getattoperacquisti.Service req = composeAttoPerAcquistiRequest(params, RequestGetAttoPerAcquisti);	
			
			log.info("getDocumentiProvvedimenti", "***************************************************************");
			log.info("getDocumentiProvvedimenti", "*************Chiamata servizio STILO***************************");
			log.info("getDocumentiProvvedimenti", "***************************************************************");			
			it.eng.auriga.repository2.webservices.getattoperacquisti.ServiceResponse res = wSIGetAttoPerAcquisti.serviceOperation(req);
			log.info("getDocumentiProvvedimenti", "***************************************************************");		
			byte[] xmlBytes = Base64.getMimeDecoder().decode(res.getServiceReturn());
			String xml = new String(xmlBytes, StandardCharsets.ISO_8859_1);
			log.info("getDocumentiProvvedimenti response", "xml " + xml);
			
			
			BaseOutputWS bows = null;
			ResponseGetAttoPerAcquisti responseGetAttoPerAcquisti= new ResponseGetAttoPerAcquisti();
			try {
				bows = JAXBUtility.unmarshall(xml, BaseOutputWS.class);
			}catch(Exception e) {
				xml = unescapeHtml(xml);
				/*
				<?xml version="1.0" encoding="UTF-8"?>
				<ResponseGetAttoPerAcquisti>
				<Esito>atto_non_trovato</Esito>
				</ResponseGetAttoPerAcquisti>
				*/
				log.info("wa", "xml \n" + xml);
				responseGetAttoPerAcquisti = JAXBUtility.unmarshall(xml, ResponseGetAttoPerAcquisti.class);
			}

			List<Provvedimento> listProvv= new ArrayList<Provvedimento>();			
			
			if(bows!= null && bows.getWSResult()!=null ) {
				ep.setLista(listProvv);
				ep.setCodErrore("" + bows.getWSError().getErrorNumber());
				ep.setDescErrore(bows.getWSError().getErrorMessage());				
			}else {				
				DatiAtto resProv = responseGetAttoPerAcquisti.getDatiAtto();
				//Implementare la gestione lista 
				//for(lista) {
					if (resProv == null) {
						ep.setLista(listProvv);
					}else {
						Provvedimento provv = new Provvedimento();
						Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
						provv.setAnno(provvedimento.getAnno());
						provv.setNumero(provvedimento.getNumero() != null ? provvedimento.getNumero() :"0");
						//provv.setDescrizione(resProv.getOggetto());
						provv.setDescrizione(resProv.getOggetto());
						
						provv.setCdcCodice(resProv.getCdC() != null ? resProv.getCdC() :"");
						provv.setCdrCodice(resProv.getCdR() != null ? resProv.getCdR() :"");
						
						Settore settore = new Settore();
						
						//settore.setCodice(resProv.getStrutturaProponente().getCodice());
						settore.setCodice(resProv.getCdC() != null ? resProv.getCdC() :"");
						
						provv.setSettore(settore);
						provv.setDataCreazione(new Date());
						provv.setDataModifica(new Date());
						provv.setDataValiditaInizio(new Date());
						provv.setProvvedimentoTipo(provvedimento.getProvvedimentoTipo());
						provv.setEnte(ente);
						provv.setDataValiditaFine(null);
						listProvv.add(provv);
						
					}
				//}
				ep.setLista(listProvv);
			}			
			response.setSuccess(Boolean.TRUE);
			response.setResponse(ep);
			log.info("getProvvedimenti", "****** Fine getProvvedimenti STILO *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio Stilo non disponibile");
			response.setErrors(errors);
			ep.setCodErrore(CoreError.SYSTEM_ERROR.getError("error", "servizio Stilo non disponibile").getFullErrorMessage());
			ep.setDescErrore(e.getMessage());
			log.error("getProvvedimenti", "Desc error --> "+e.getMessage());
			response.setResponse(ep);
			return response;
		}
	}	
	
	private RequestGetAttoPerAcquisti composeNumerazioneType(Map<String, String> params, Provvedimento provvedimento,String enteCode) {
		RequestGetAttoPerAcquisti nt = new RequestGetAttoPerAcquisti();
		nt.setAnno(provvedimento.getAnno());
		nt.setNro(new BigInteger(provvedimento.getNumero().trim()));
		nt.setSiglaRegistro(provvedimento.getProvvedimentoTipo().getCodice());	
		return nt;
	}

	public static void main(String[] args){
		//String pippo = "Repertorio DD-A18 0000013 / 2021: Concessione demaniale TO.LT.5711 ed autorizzazione idraulica n. 54";
		//StringUtility.estraiStringa(pippo,' ',2,' ',1);
		//StringUtility.estraiStringa(pippo,' ',4,':',1);
		//StringUtility.estraiStringa(pippo,'-',':');		
		//String coll226="U.U3.dfgdgfdgdf";
		//String settore  = coll226.substring(0, coll226.indexOf(" "));
		//System.out.println("end  " + coll226.indexOf("."));
		//System.out.println("settore "+settore);
	}
}

