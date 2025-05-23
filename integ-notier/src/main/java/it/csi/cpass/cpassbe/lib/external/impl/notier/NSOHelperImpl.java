/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoXML;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.Documento;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoInvioDocumento;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoDocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaDiScarto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaMdn;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoRicevutaDiConsegna;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.NotificaDiScarto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.NotificaMdn;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.RicevutaDiConsegna;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.TipoOrdineNSO;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.NSOListener;
import it.csi.cpass.cpassbe.lib.external.dto.Errore;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;
import it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1.ErroreType;
import it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1.ListaErroriType;
import it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1.NotificaScartoType;
import it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1.RiceventeType;
import it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1.RicevutaConsegnaType;
import it.rer.intercenter.notier.services._1_0.esito.response.EsitoRichiesta;
import it.rer.intercenter.notier.services._1_0.notificamdn.DataOraTransitoDocumentoType;
import it.rer.intercenter.notier.services._1_0.notificamdn.EsitoTrasmissioneType;
import it.rer.intercenter.notier.services._1_0.notificamdn.IdentificativoDocumentoType;
import it.rer.intercenter.notier.services._1_0.notificamdn.IdentificativoTrasmissioneType;
import it.rer.intercenter.notier.services._1_0.notificamdn.NotificaMDNType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AddressType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContactType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DespatchLineType;
import oasis.names.specification.ubl.schema.xsd.despatchadvice_2.DespatchAdviceType;

/**
 * Example POJO helper impl
 */
public class NSOHelperImpl extends BaseNotiERHelperImpl implements NSOHelper {

	@Override
	public ExternalServiceResponseWrapper<EsitoInvioDocumento> invioDocumentoNSO(
			TipoOrdineNSO tipoOrdineNSO, Map<String, String> params, TestataOrdine testataOrdine,Destinatario destinatarioOrdine, RigheOrdineConTotali righeOrdineConTotali,SettoreIndirizzo indirizzoPrincipaleSettoreEmittente, NSOListener nsoListener) {
		
		final String methodName = "invioDocumentoNSO";

		ExternalServiceResponseWrapper<EsitoInvioDocumento> response = new ExternalServiceResponseWrapper<>();
		EsitoInvioDocumento esitoInvioDocumento = new EsitoInvioDocumento();
		response.setResponse(esitoInvioDocumento);

		try {
			checkBaseParameters(params);
	
			int maxProgressivo = nsoListener.getMaxProgressivoInvio(destinatarioOrdine!= null ? destinatarioOrdine.getId() : null, testataOrdine.getId());

			String xmlMetadati = PeppolMetadati.getXmlMetadati(testataOrdine, destinatarioOrdine,maxProgressivo, params);
			log.info(methodName, "xmlMetadati: " + xmlMetadati);

			Documento documento = PeppolDocumento.getXmlDocumento(tipoOrdineNSO, testataOrdine, destinatarioOrdine, righeOrdineConTotali,maxProgressivo, params, indirizzoPrincipaleSettoreEmittente);
			String xmlDocumento = documento.getXml();
			log.info(methodName, "xmlDocumento: " + xmlDocumento);
			documento.setMetadati(xmlMetadati);
			esitoInvioDocumento.setDocumento(documento);

			CloseableHttpClient httpclient = initHttpClient(params);
			
			try {
				protocolNoTIER = getParameter(params, NotiERConfigurationParams.PROTOCOL_NOTIER);
				hostNoTIER = getParameter(params, NotiERConfigurationParams.HOST_NOTIER);
				portaNoTIER = getParameter(params, NotiERConfigurationParams.PORTA_NOTIER);
				String url = protocolNoTIER + "://" + hostNoTIER + ":" + portaNoTIER + "/notier/rest/v1.0/documenti/invio";
				log.info(methodName, "url: " + url);

				HttpPost httpPost = new HttpPost(url);
				httpPost.addHeader("Content-Type", "multipart/form-data");

				byte[] byteXmlMetadati = xmlMetadati.getBytes(Charsets.UTF_8);
				byte[] byteXmlDocumento = xmlDocumento.getBytes(Charsets.UTF_8);

				HttpEntity httpEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
						.addPart("metadati", new PeppolContentBody(byteXmlMetadati, "metadati.xml"))
						.addPart("documento", new PeppolContentBody(byteXmlDocumento, "documento.xml")).build();

				httpPost.setEntity(httpEntity);
				
				// fix Content-Type con boundary - multipart/form-data; boundary=--------------------------062059736886472513323683
				// altrimenti otteniamo pagina di errore
				httpPost.setHeader(httpEntity.getContentType());

				log.info(methodName, "-------------getRequestLine-----------------");
				log.info(methodName, " executing request " + httpPost.getRequestLine());
				log.info(methodName, "-------------fine getRequestLine-----------------");
				
				CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
				try {
					log.info(methodName, "----------------------------------------");
					log.info(methodName, "statusLine: " + httpResponse.getStatusLine());
					HttpEntity resEntity = httpResponse.getEntity();
					if (resEntity != null) {
						log.info(methodName, "Response content length: " + resEntity.getContentLength());
					}
					
					String xmlResponse = EntityUtils.toString(resEntity, Charsets.UTF_8);
					log.info(methodName, "xmlResponse: " + xmlResponse);
					EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);

					// DA XSD L'esito è sempre presente, il codice è sempre presente e puo avere valori OK, KO, WARN, il codice Errore è valorizzato se esito KO, la descrizione errore se esito KO o WARN
					if (esitoRichiesta != null && esitoRichiesta.getEsito() != null && esitoRichiesta.getEsito().getCodiceErrore()!= null )
					{
						List<Errore> errors = new ArrayList<Errore>();
						Errore errore  = new Errore ();
						errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
						errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
						errors.add(errore);
						response.setErrori(errors);
						response.setSuccess(true);
						
						esitoInvioDocumento.setCodErrore(esitoRichiesta.getEsito().getCodiceErrore().toString());
						esitoInvioDocumento.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());
						
					}else {
						String urnDocumento = esitoRichiesta.getDocumento().getUrn();
						esitoInvioDocumento.setUrnDocumento(urnDocumento);
						esitoInvioDocumento.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());
						response.setSuccess(true);
					}

				} finally {
					httpResponse.close();
				}
			} finally {
				httpclient.close();
			}

		} catch (Exception e) {
			log.error(methodName, e.getMessage(), e);

			response.setSuccess(false);
			response.addError(e.getMessage());
		}

		return response;
	}

	@Override
	public ExternalServiceResponseWrapper<EsitoRecuperoNotificaMdn> recuperoNotificaMdn(Map<String, String> params, String urn)   {
		final String methodName = "recuperoDocumentoNotificaMdn";
		ExternalServiceResponseWrapper<EsitoRecuperoNotificaMdn> response = new ExternalServiceResponseWrapper<>();
		EsitoRecuperoNotificaMdn esitoRecuperoDocumento = new EsitoRecuperoNotificaMdn();
		response.setResponse(esitoRecuperoDocumento);
		try {
			String xmlResponse = recuperoDocumentoXml(params, urn);
			log.info(methodName, "xmlResponse: " + xmlResponse);
			
			NotificaMDNType notificaMdnType = JAXBUtility.unmarshall(xmlResponse, NotificaMDNType.class);		
			if(notificaMdnType.getEsito()!=null) {
				
				NotificaMdn documentoTrasmesso = popolaNotificaMdn(notificaMdnType, xmlResponse);
				esitoRecuperoDocumento.setNotificaMdn(documentoTrasmesso);

				EsitoTrasmissioneType esitoTrasmissione = notificaMdnType.getEsito().getEsitoTrasmissione();
				IdentificativoTrasmissioneType identificativoTrasmissione = notificaMdnType.getEsito().getEsitoMDN();
				esitoRecuperoDocumento.setIdentificativoTrasmissione(identificativoTrasmissione.getTransmissionID());
				esitoRecuperoDocumento.setMessaggioTrasmissione(identificativoTrasmissione.getMessageID());
				esitoRecuperoDocumento.setCodEsito(esitoTrasmissione.getCodiceEsito().toString());
				esitoRecuperoDocumento.setDescErrore(esitoTrasmissione.getDescrizioneErrore());				
			}else {
				EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);	
				List<Errore> errors = new ArrayList<Errore>();
				Errore errore  = new Errore ();
				errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
				errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
				errors.add(errore);
				response.setErrori(errors);
				esitoRecuperoDocumento.setCodEsito(esitoRichiesta.getEsito().getCodiceEsito().toString()); // ATTENZIONE! qui mi aspetto un KO 
				esitoRecuperoDocumento.setCodErrore(esitoRichiesta.getEsito().getCodiceErrore().toString());
				esitoRecuperoDocumento.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());				
			}
			response.setSuccess(true);
			
			return response;
		}catch (Exception e) {
			response.setSuccess(false);
			log.error(methodName, e);
		}
		return response;
	}
	private NotificaMdn popolaNotificaMdn(NotificaMDNType notificaMdnType, String xmlResponse) {
		
		NotificaMdn documentoTrasmesso = new NotificaMdn();
		
		documentoTrasmesso.setXml(xmlResponse);
		
		IdentificativoDocumentoType documentoType = notificaMdnType.getEsito().getDocumento();
		documentoTrasmesso.setUrn(documentoType.getUrn());
		documentoTrasmesso.setNumeroDocumento(documentoType.getNumeroDocumento());
		documentoTrasmesso.setRecipientID(documentoType.getRecipientID());
		documentoTrasmesso.setDocumentID(documentoType.getDocumentID());
		
		DataOraTransitoDocumentoType riferimentoTemporale = documentoType.getRiferimentoTemporale(); 
		documentoTrasmesso.setDataInvio(riferimentoTemporale.getDataInvioSuPeppol());
		documentoTrasmesso.setOrarioInvio(riferimentoTemporale.getOrarioInvioSuPeppol());

		return documentoTrasmesso;
	}
	private RicevutaDiConsegna popolaRicevutaDiConsegna(RicevutaConsegnaType ricevutaConsegnaType, String xmlResponse) {
		
		RicevutaDiConsegna documentoTrasmesso = new RicevutaDiConsegna();
		
		documentoTrasmesso.setXml(xmlResponse);
		documentoTrasmesso.setIdentificativo(ricevutaConsegnaType.getIdentificativo());
		documentoTrasmesso.setNomeFile(ricevutaConsegnaType.getNomeFile());
		XMLGregorianCalendar gcr = ricevutaConsegnaType.getDataOraRicezione();
		documentoTrasmesso.setDataOraRicezione(gcr.getYear()+"-"+gcr.getMonth()+"-"+ gcr.getDay());
		XMLGregorianCalendar gcc = ricevutaConsegnaType.getDataOraConsegna();
		documentoTrasmesso.setDataOraConsegna(gcc.getYear()+"-"+gcc.getMonth()+"-"+ gcc.getDay());
		
		RiceventeType ricevente =  ricevutaConsegnaType.getRicevente();
		documentoTrasmesso.setCodiceRicevente(ricevente.getCodice());
		documentoTrasmesso.setDescrizioneRicevente(ricevente.getDescrizione());

		documentoTrasmesso.setMessageId(ricevutaConsegnaType.getMessageId());
		documentoTrasmesso.setNote(ricevutaConsegnaType.getNote());
	
		return documentoTrasmesso;
	}
	
	private NotificaDiScarto popolaNotificaDiScarto(NotificaScartoType notificaScartoType, String xmlResponse) {
		
		NotificaDiScarto documentoTrasmesso = new NotificaDiScarto();
		
		documentoTrasmesso.setXml(xmlResponse);
		documentoTrasmesso.setIdentificativo(notificaScartoType.getIdentificativo());
		documentoTrasmesso.setNomeFile(notificaScartoType.getNomeFile());
		XMLGregorianCalendar gcr = notificaScartoType.getDataOraRicezione();
		documentoTrasmesso.setDataOraRicezione(gcr.getYear()+"-"+gcr.getMonth()+"-"+ gcr.getDay());
		
		documentoTrasmesso.setMessageId(notificaScartoType.getMessageId());
		documentoTrasmesso.setNote(notificaScartoType.getNote());
		
		ListaErroriType listaErroriType = notificaScartoType.getListaErrori();
		
		// considerato il codice identificativo e non replicabile nella lista
		Map<String, String> listaErrori = listaErroriType.getErrore().stream()
			      .collect(Collectors.toMap(ErroreType::getCodice, ErroreType::getDescrizione));		
		documentoTrasmesso.setListaErrori(listaErrori);
		
		return documentoTrasmesso;
	}
	
	@Override
	public ExternalServiceResponseWrapper<EsitoRecuperoRicevutaDiConsegna> recuperoRicevutaDiConsegna(Map<String, String> params, String urn)   {
		final String methodName = "recuperoDocumentoRicevutaDiConsegna";
		
		ExternalServiceResponseWrapper<EsitoRecuperoRicevutaDiConsegna> response = new ExternalServiceResponseWrapper<>();
		EsitoRecuperoRicevutaDiConsegna esitoRecuperoDocumento = new EsitoRecuperoRicevutaDiConsegna();
		response.setResponse(esitoRecuperoDocumento);

		try {
			String xmlResponse = recuperoDocumentoXml(params, urn);
			log.debug(methodName, "xmlResponse: " + xmlResponse);
			
			RicevutaConsegnaType ricevutaConsegnaType = JAXBUtility.unmarshall(xmlResponse, RicevutaConsegnaType.class);		

			if(!StringUtils.isBlank(ricevutaConsegnaType.getIdentificativo())) {
				RicevutaDiConsegna documentoTrasmesso = popolaRicevutaDiConsegna(ricevutaConsegnaType, xmlResponse);
				esitoRecuperoDocumento.setRicevutaDiConsegna(documentoTrasmesso);
				esitoRecuperoDocumento.setIdentificativoTrasmissione(ricevutaConsegnaType.getIdentificativo());
				esitoRecuperoDocumento.setMessaggioTrasmissione(ricevutaConsegnaType.getMessageId());
				esitoRecuperoDocumento.setCodEsito("OK");
			}else {
				EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);	
				List<Errore> errors = new ArrayList<Errore>();
				Errore errore  = new Errore ();
				errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
				errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
				errors.add(errore);
				response.setErrori(errors);
				esitoRecuperoDocumento.setCodEsito(esitoRichiesta.getEsito().getCodiceEsito().toString()); // ATTENZIONE! qui mi aspetto un KO 
				esitoRecuperoDocumento.setCodErrore(esitoRichiesta.getEsito().getCodiceErrore().toString());
				esitoRecuperoDocumento.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());				
			}
			response.setSuccess(true);
			
			return response;
		}catch (Exception e) {
			response.setSuccess(false);
			log.error(methodName, e);
		}
		return response;
	}

	@Override
	public ExternalServiceResponseWrapper<EsitoRecuperoNotificaDiScarto> recuperoNotificaDiScarto(Map<String, String> params, String urn)   {
		final String methodName = "recuperoNotificaDiScarto";
		
		ExternalServiceResponseWrapper<EsitoRecuperoNotificaDiScarto> response = new ExternalServiceResponseWrapper<>();
		EsitoRecuperoNotificaDiScarto esitoRecuperoDocumento = new EsitoRecuperoNotificaDiScarto();
		response.setResponse(esitoRecuperoDocumento);
		
		try {
			String xmlResponse = recuperoDocumentoXml(params, urn);
			log.debug(methodName, "xmlResponse: " + xmlResponse);
			
			NotificaScartoType notificaScartoType = JAXBUtility.unmarshall(xmlResponse, NotificaScartoType.class);		
			
			if(!StringUtils.isBlank(notificaScartoType.getIdentificativo())) {
				NotificaDiScarto documentoTrasmesso = popolaNotificaDiScarto(notificaScartoType, xmlResponse);
				esitoRecuperoDocumento.setNotificaDiScarto(documentoTrasmesso);
				esitoRecuperoDocumento.setIdentificativoTrasmissione(notificaScartoType.getIdentificativo());
				esitoRecuperoDocumento.setMessaggioTrasmissione(notificaScartoType.getMessageId());
				esitoRecuperoDocumento.setCodEsito("OK");
			}else {
				EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);	
				List<Errore> errors = new ArrayList<Errore>();
				Errore errore  = new Errore ();
				errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
				errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
				errors.add(errore);
				response.setErrori(errors);
				esitoRecuperoDocumento.setCodEsito(esitoRichiesta.getEsito().getCodiceEsito().toString()); // ATTENZIONE! qui mi aspetto un KO 
				esitoRecuperoDocumento.setCodErrore(esitoRichiesta.getEsito().getCodiceErrore().toString());
				esitoRecuperoDocumento.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());				
			}
			response.setSuccess(true);
			
			return response;
		}catch (Exception e) {
			response.setSuccess(false);
			log.error(methodName, e);
		}
		return response;
	}

	@Override
	public ExternalServiceResponseWrapper<EsitoRecuperoDocumentoTrasporto> recuperoDDT(Map<String, String> params, String urn, String idNotier)   {
		final String methodName = "recuperoDDT";
		ExternalServiceResponseWrapper<EsitoRecuperoDocumentoTrasporto> response = new ExternalServiceResponseWrapper<>();

		try {
			String xmlDDTResponse = recuperoDocumentoXml(params, urn);
			log.info(methodName, "xmlDDTResponse: " + xmlDDTResponse);				
			
			EsitoRecuperoDocumentoTrasporto erdt = new EsitoRecuperoDocumentoTrasporto();			
			DespatchAdviceType ddt = JAXBUtility.unmarshall(xmlDDTResponse, DespatchAdviceType.class);		
			if(!StringUtils.isEmpty(ddt.getID().toString())) {
				DocumentoTrasporto documentoTrasporto = popolaDDT (idNotier, ddt, xmlDDTResponse); 			
				erdt.setDocumentoTrasporto(documentoTrasporto);
			}else {
				EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlDDTResponse, EsitoRichiesta.class);	
				List<Errore> errors = new ArrayList<Errore>();
				Errore errore  = new Errore ();
				errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
				errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
				errors.add(errore);
				response.setErrori(errors);
				erdt.setCodErrore(esitoRichiesta.getEsito().getCodiceErrore().toString());
				erdt.setDescErrore(esitoRichiesta.getEsito().getDescrizioneErrore());				
			}
			response.setSuccess(true);
			response.setResponse(erdt);
			
			return response;
		}catch (Exception e) {
			response.setSuccess(false);
			log.error(methodName, e);
		}
		return response;
	}
	
	/**
	 * 
	 * @param idNotier
	 * @param ddt
	 * @param xmlDDTResponse
	 * @return DocumentoTrasporto
	 */
	private DocumentoTrasporto popolaDDT(String idNotier, DespatchAdviceType ddt, String xmlDDTResponse) {		
		String methodName="popolaDDT";
		log.info(methodName, "Inizio");
		DocumentoTrasporto ris = new DocumentoTrasporto();
		String despatchAdviceId = replaceNull(ddt.getID());	
		ris.setUrnDocumento(ddt.getProfileID().getValue());  	
		ris.setDespatchAdviceId(despatchAdviceId);
		ris.setIdNotier(idNotier);
		
		String dataConsegna ="";
		if(ddt.getIssueDate() !=null) {
			XMLGregorianCalendar gc = ddt.getIssueDate().getValue();
			dataConsegna = gc.getYear()+"-"+gc.getMonth()+"-"+ gc.getDay();
			ris.setDataConsegna(dataConsegna);
		}
		
		//da gestire piu' avanti a fronte di validazione
		Stato stato = new Stato();
		ris.setStato(stato);
		
		ris.setNote(ddt.getNote().toString());
		
		if(ddt.getOrderReference()!= null && ddt.getOrderReference().size()>0) {
			ris.setOrdineUnicoId(replaceNull(ddt.getOrderReference().get(0).getID()));			
			if(ddt.getOrderReference().get(0).getIssueDate() != null) {
				XMLGregorianCalendar ordData = ddt.getOrderReference().get(0).getIssueDate().getValue();
				ris.setOrdineUnicoData(ordData.getYear()+"-"+ordData.getMonth()+"-"+ ordData.getDay()); 
			}
			ris.setOrdineUnicoTipo(replaceNull(ddt.getOrderReference().get(0).getOrderTypeCode())); 
		}

		ris.setEndpointId(replaceNull(ddt.getDeliveryCustomerParty().getParty().getEndpointID()));
		
		if(ddt.getDeliveryCustomerParty().getParty().getPostalAddress()!=null) {
			AddressType pa = ddt.getDeliveryCustomerParty().getParty().getPostalAddress();
			ris.setIndirizzoDestinatario(replaceNull(pa.getStreetName()));
			ris.setLocalitaDestinatario(replaceNull(pa.getCityName()));
			ris.setCapDestinatario(replaceNull(pa.getPostalZone()));
			ris.setProvinciaDestinatario(replaceNull(pa.getCountrySubentity()));
		}		
		
		ContactType dc = ddt.getDeliveryCustomerParty().getDeliveryContact();
		
		ris.setContattoDestinatario(replaceNull(dc.getName()));
		ris.setTelefonoDestinatario(replaceNull((dc.getTelephone())));
		
		if(ddt.getBuyerCustomerParty().getParty().getPartyName().size()>0) {
			ris.setSettoreEmittenteOrdine(replaceNull(ddt.getBuyerCustomerParty().getParty().getPartyName().get(0).getName()));
		}
		
		if(ddt.getSellerSupplierParty().getParty().getPartyIdentification().size()>0) {
			ris.setPartitaIvaFornitore(replaceNull(ddt.getSellerSupplierParty().getParty().getPartyIdentification().get(0).getID()));
			String pif = ris.getPartitaIvaFornitore();
			pif = pif.substring(pif.lastIndexOf(":")+1,pif.length()); // gestisce il caso in cui il ddt fornisca il cf nel formato IT:CF:02483810000
			ris.setPartitaIvaFornitore(pif);
		}
		
		if(ddt.getSellerSupplierParty().getParty().getPartyName().size()>0) {
			ris.setRagioneSocialeFornitore(replaceNull(ddt.getSellerSupplierParty().getParty().getPartyName().get(0).getName()));
		}
		
		//ricercarlo a partire dal valore contenuto su ris.getPartitaIvaFornitore()
		Fornitore fornitore = new Fornitore();
		ris.setFornitore(fornitore);
				
		//Documento Trasporto riga
		log.info(methodName, "popolamento ddt riga");		
		DocumentoTrasportoRiga documentoTrasportoRiga = new DocumentoTrasportoRiga();
		List<DespatchLineType> listaDl = ddt.getDespatchLine();
		List<DocumentoTrasportoRiga> documentoTrasportoRigaList = new ArrayList<DocumentoTrasportoRiga>();
		for(DespatchLineType dl : listaDl){
			// da valorizzare con il dt appena inserito 
			documentoTrasportoRiga.setDocumentoTrasporto(null);
			documentoTrasportoRiga.setProgressivoRigaId(replaceNull(dl.getID()));
			if(dl.getDeliveredQuantity() !=null && dl.getDeliveredQuantity().getUnitCode() !=null) {
				documentoTrasportoRiga.setUnitaMisura(dl.getDeliveredQuantity().getUnitCode().toString());
			}
			documentoTrasportoRiga.setQtaEvasa(replaceNull(dl.getDeliveredQuantity()));
			documentoTrasportoRiga.setQtaInevasa(replaceNull(dl.getOutstandingQuantity()));
			documentoTrasportoRiga.setMotivoQtaInevasa(dl.getOutstandingReason().toString());
			if(dl.getOrderLineReference().size()>0) {
				documentoTrasportoRiga.setProgressivoRigaOrdineEvasa(replaceNull(dl.getOrderLineReference().get(0).getLineID()));
				documentoTrasportoRiga.setOrdineNsoId(replaceNull(dl.getOrderLineReference().get(0).getOrderReference().getID()));				
				if(dl.getOrderLineReference().get(0).getOrderReference() != null && dl.getOrderLineReference().get(0).getOrderReference().getIssueDate() != null) {
					XMLGregorianCalendar dataOrdine = dl.getOrderLineReference().get(0).getOrderReference().getIssueDate().getValue();
					documentoTrasportoRiga.setOrdineData(dataOrdine.getYear()+"-"+dataOrdine.getMonth()+"-"+ dataOrdine.getDay());				
				}

				documentoTrasportoRiga.setOrdineTipo(replaceNull(dl.getOrderLineReference().get(0).getOrderReference().getOrderTypeCode()));		
			}			
			//TODO da valorizzare in fase di inserimento
			RigaOrdine rigaOrdine = new RigaOrdine();
			documentoTrasportoRiga.setRigaOrdine(rigaOrdine);			
			// ORDINE_ID	???? da capire con Fabio	Fk Verso la nostra tabella	
			documentoTrasportoRiga.setCodiceListinoFornitore(replaceNull(dl.getItem().getSellersItemIdentification().getID()));  // TODO da verificare con anna Salvare il valore del tag <DespatchAdvice>/< cac:DespatchLine>/<OrderLineReference>/< cac:OrderLineReference>/<cac:Item>/<cac:SellersItemIdentification>/<cbc:ID>
			documentoTrasportoRiga.setNoteFornitore(dl.getItem().getAdditionalInformation().toString()); // TODO <DespatchAdvice>/< cac:DespatchLine>/<OrderLineReference>/< cac:OrderLineReference>/<cac:Item/cbc: AdditionalInformation>			
			documentoTrasportoRigaList.add(documentoTrasportoRiga);
			//Documento Trasporto riga
			log.info(methodName, "fine popolamento ddt riga");
		}
		ris.setDocumentoTrasportoRigaList(documentoTrasportoRigaList);
					
		//Documento Trasporto xml
		log.info(methodName, "popolamento ddt xml");
		DocumentoTrasportoXML documentoTrasportoXml = new DocumentoTrasportoXML();

		documentoTrasportoXml.setDespatchAdviceId(despatchAdviceId);
		documentoTrasportoXml.setDataConsegna(dataConsegna);
		documentoTrasportoXml.setNote(ddt.getNote().toString());
		documentoTrasportoXml.setFileXml(xmlDDTResponse);
		documentoTrasportoXml.setTipodoc("DOCUMENTO_DI_TRASPORTO");				
		List<DocumentoTrasportoXML> documentoTrasportoXMLList = new ArrayList<DocumentoTrasportoXML>();
		documentoTrasportoXMLList.add(documentoTrasportoXml);		
		ris.setDocumentoTrasportoXMLList(documentoTrasportoXMLList);
		//Documento Trasporto xml
		log.info(methodName, "fine");		
		return ris;
	}
	
	/*
	@Override
	public ExternalServiceResponseWrapper<EsitoRecuperoDocumentoTrasporto> recuperoDDT_TEST(Map<String, String> params,String urn,String idNotier)   {
		final String methodName = "recuperoDDT_TEST";
		    ExternalServiceResponseWrapper<EsitoRecuperoDocumentoTrasporto> response = new ExternalServiceResponseWrapper<>();
			String xmlDDTResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DespatchAdvice xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:DespatchAdvice-2\" xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\">\n" + 
					"	<cbc:CustomizationID>urn:fdc:peppol.eu:poacc:trns:despatch_advice:3:extended:urn:www.agid.gov.it:trns:ddt:3.1</cbc:CustomizationID>\n" + 
					"	<cbc:ProfileID>urn:fdc:peppol.eu:poacc:bis:despatch_advice:3</cbc:ProfileID>\n" + 
					"	<cbc:ID>301220_csi_2</cbc:ID>\n" + 
					"	<cbc:IssueDate>2020-12-30</cbc:IssueDate>\n" + 
					"	<cbc:Note>BOLLA</cbc:Note>\n" + 
					"	<cac:OrderReference>\n" + 
					"		<cbc:ID>2020_85_1_1</cbc:ID>\n" + 
					"	</cac:OrderReference>\n" + 
					"	<cac:DespatchSupplierParty>\n" + 
					"		<cac:Party>\n" + 
					"			<cbc:EndpointID schemeID=\"0201\">TESTAP</cbc:EndpointID>\n" + 
					"			<cac:PostalAddress>\n" + 
					"				<cbc:StreetName>Via Lucio Dalla 1</cbc:StreetName>\n" + 
					"				<cbc:CityName>Bologna</cbc:CityName>\n" + 
					"				<cbc:PostalZone>40121</cbc:PostalZone>\n" + 
					"				<cbc:CountrySubentity>Bologna</cbc:CountrySubentity>\n" + 
					"				<cac:Country>\n" + 
					"					<cbc:IdentificationCode>IT</cbc:IdentificationCode>\n" + 
					"				</cac:Country>\n" + 
					"			</cac:PostalAddress>\n" + 
					"			<cac:PartyLegalEntity>\n" + 
					"				<cbc:RegistrationName>STM Group</cbc:RegistrationName>\n" + 
					"			</cac:PartyLegalEntity>\n" + 
					"			<cac:Contact>\n" + 
					"				<cbc:Name>Deposito Logistica Integratar</cbc:Name>\n" + 
					"				<cbc:Telephone>029506981</cbc:Telephone>\n" + 
					"			</cac:Contact>\n" + 
					"		</cac:Party>\n" + 
					"	</cac:DespatchSupplierParty>\n" + 
					"	<cac:DeliveryCustomerParty>\n" + 
					"		<cac:Party>\n" + 
					"			<cbc:EndpointID schemeID=\"0201\">TD2TB3</cbc:EndpointID>\n" + 
					"			<cac:PostalAddress>\n" + 
					"				<cbc:StreetName>Via Viotti 8</cbc:StreetName>\n" + 
					"				<cbc:AdditionalStreetName>Destinatario</cbc:AdditionalStreetName>\n" + 
					"				<cbc:CityName>TORINO</cbc:CityName>\n" + 
					"				<cbc:PostalZone>10121</cbc:PostalZone>\n" + 
					"				<cbc:CountrySubentity>RA</cbc:CountrySubentity>\n" + 
					"				<cac:Country>\n" + 
					"					<cbc:IdentificationCode>IT</cbc:IdentificationCode>\n" + 
					"				</cac:Country>\n" + 
					"			</cac:PostalAddress>\n" + 
					"			<cac:PartyLegalEntity>\n" + 
					"				<cbc:RegistrationName>Azienda USL</cbc:RegistrationName>\n" + 
					"			</cac:PartyLegalEntity>\n" + 
					"		</cac:Party>\n" + 
					"		<cac:DeliveryContact>\n" + 
					"			<cbc:Name>Magazzino Unico</cbc:Name>\n" + 
					"			<cbc:Telephone>0547300000</cbc:Telephone>\n" + 
					"		</cac:DeliveryContact>\n" + 
					"	</cac:DeliveryCustomerParty>\n" + 
					"	<cac:BuyerCustomerParty>\n" + 
					"		<cac:Party>\n" + 
					"			<cac:PartyIdentification>\n" + 
					"				<cbc:ID>IT:CF:02400000000</cbc:ID>\n" + 
					"			</cac:PartyIdentification>\n" + 
					"			<cac:PartyName>\n" + 
					"				<cbc:Name>Azienda</cbc:Name>\n" + 
					"			</cac:PartyName>\n" + 
					"			<cac:PostalAddress>\n" + 
					"				<cbc:StreetName>Via Napoli 1</cbc:StreetName>\n" + 
					"				<cbc:CityName>Milano</cbc:CityName>\n" + 
					"				<cbc:PostalZone>20100</cbc:PostalZone>\n" + 
					"				<cbc:CountrySubentity>MI</cbc:CountrySubentity>\n" + 
					"				<cac:Country>\n" + 
					"					<cbc:IdentificationCode>IT</cbc:IdentificationCode>\n" + 
					"				</cac:Country>\n" + 
					"			</cac:PostalAddress>\n" + 
					"		</cac:Party>\n" + 
					"	</cac:BuyerCustomerParty>\n" + 
					"	<cac:SellerSupplierParty>\n" + 
					"		<cac:Party>\n" + 
					"			<cac:PartyIdentification>\n" + 
					"				<cbc:ID>IT:VAT:03270040961</cbc:ID>\n" + 
					"			</cac:PartyIdentification>\n" + 
					"			<cac:PartyName>\n" + 
					"				<cbc:Name>FARMAC S.p.A</cbc:Name>\n" + 
					"			</cac:PartyName>\n" + 
					"			<cac:PostalAddress>\n" + 
					"				<cbc:StreetName>Via Spezia 1</cbc:StreetName>\n" + 
					"				<cbc:CityName>Enna</cbc:CityName>\n" + 
					"				<cbc:PostalZone>20100</cbc:PostalZone>\n" + 
					"				<cbc:CountrySubentity>EN</cbc:CountrySubentity>\n" + 
					"				<cac:Country>\n" + 
					"					<cbc:IdentificationCode>IT</cbc:IdentificationCode>\n" + 
					"				</cac:Country>\n" + 
					"			</cac:PostalAddress>\n" + 
					"		</cac:Party>\n" + 
					"	</cac:SellerSupplierParty>\n" + 
					"	<cac:OriginatorCustomerParty>\n" + 
					"		<cac:Party>\n" + 
					"			<cac:PartyIdentification>\n" + 
					"				<cbc:ID>IT:CF:02483810000</cbc:ID>\n" + 
					"			</cac:PartyIdentification>\n" + 
					"			<cac:PartyName>\n" + 
					"				<cbc:Name>Magazzino Unico</cbc:Name>\n" + 
					"			</cac:PartyName>\n" + 
					"		</cac:Party>\n" + 
					"	</cac:OriginatorCustomerParty>\n" + 
					"	<cac:Shipment>\n" + 
					"		<cbc:ID>STM 0001946113</cbc:ID>\n" + 
					"		<cbc:Information>Vendita</cbc:Information>\n" + 
					"		<cbc:GrossWeightMeasure unitCode=\"KGM\">165.440</cbc:GrossWeightMeasure>\n" + 
					"		<cbc:TotalTransportHandlingUnitQuantity>22</cbc:TotalTransportHandlingUnitQuantity>\n" + 
					"		<cac:Consignment>\n" + 
					"			<cbc:ID>STM 0001946113</cbc:ID>\n" + 
					"			<cbc:Information>Porto Franco</cbc:Information>\n" + 
					"			<cac:CarrierParty>\n" + 
					"				<cac:PartyIdentification>\n" + 
					"					<cbc:ID>IT:VAT:IT00204260285</cbc:ID>\n" + 
					"				</cac:PartyIdentification>\n" + 
					"				<cac:PartyName>\n" + 
					"					<cbc:Name>FARM SRL</cbc:Name>\n" + 
					"				</cac:PartyName>\n" + 
					"				<cac:PostalAddress>\n" + 
					"					<cbc:StreetName>Via Europa 100</cbc:StreetName>\n" + 
					"					<cbc:AdditionalStreetName>Palazzo A</cbc:AdditionalStreetName>\n" + 
					"					<cbc:CityName>Bologna</cbc:CityName>\n" + 
					"					<cbc:PostalZone>40121</cbc:PostalZone>\n" + 
					"					<cbc:CountrySubentity>Bologna</cbc:CountrySubentity>\n" + 
					"					<cac:Country>\n" + 
					"						<cbc:IdentificationCode>IT</cbc:IdentificationCode>\n" + 
					"					</cac:Country>\n" + 
					"				</cac:PostalAddress>\n" + 
					"			</cac:CarrierParty>\n" + 
					"		</cac:Consignment>\n" + 
					"	</cac:Shipment>\n" + 
					"	<cac:DespatchLine>\n" + 
					"		<cbc:ID>1</cbc:ID>\n" + 
					"		<cbc:DeliveredQuantity unitCode=\"NAR\">960</cbc:DeliveredQuantity>\n" + 
					"		<cac:OrderLineReference>\n" + 
					"			<cbc:LineID>1</cbc:LineID>\n" + 
					"			<cac:OrderReference>\n" + 
					"				<cbc:ID>2020_85_1_1</cbc:ID>\n" + 
					"			</cac:OrderReference>\n" + 
					"		</cac:OrderLineReference>\n" + 
					"		<cac:DocumentReference>\n" + 
					"			<cbc:ID>5641857FAA</cbc:ID>\n" + 
					"			<cbc:DocumentType>CIG</cbc:DocumentType>\n" + 
					"		</cac:DocumentReference>\n" + 
					"		<cac:Item>\n" + 
					"			<cbc:Name>CONNETTIVINA CREMA PLUS 25G</cbc:Name>\n" + 
					"			<cac:SellersItemIdentification>\n" + 
					"				<cbc:ID>101500H</cbc:ID>\n" + 
					"			</cac:SellersItemIdentification>\n" + 
					"			<cac:StandardItemIdentification>\n" + 
					"				<cbc:ID schemeID=\"0160\">028440030</cbc:ID>\n" + 
					"			</cac:StandardItemIdentification>\n" + 
					"			<cac:ItemInstance>\n" + 
					"				<cac:LotIdentification>\n" + 
					"					<cbc:LotNumberID>126200</cbc:LotNumberID>\n" + 
					"					<cbc:ExpiryDate>2017-06-30</cbc:ExpiryDate>\n" + 
					"				</cac:LotIdentification>\n" + 
					"			</cac:ItemInstance>\n" + 
					"		</cac:Item>\n" + 
					"	</cac:DespatchLine>\n" + 
					"</DespatchAdvice>\n" + 
					"";
			log.info(methodName, "xmlResponse: " + xmlDDTResponse);
						
			DespatchAdviceType ddt = JAXBUtility.unmarshall(xmlDDTResponse, DespatchAdviceType.class);
			DocumentoTrasporto documentoTrasporto = popolaDDT (idNotier, ddt, xmlDDTResponse); 		
			EsitoRecuperoDocumentoTrasporto erdt = new EsitoRecuperoDocumentoTrasporto();
			erdt.setDocumentoTrasporto(documentoTrasporto);
			response.setSuccess(true);
			response.setResponse(erdt);
			return response;
	}
	*/
	/*		
	private String replaceNull(String value) {
		return value == null ? "" :  value;
	}
	private String replaceNull(Object ogj) {
		Class<? extends Object> pippo = ogj.getClass();		
		Method ris = pippo.getDeclaredMethod("getValue");
		String value = (String)ris.invoke(ogj);	
		return value == null ? "" :  value;
	}
*/	

}
