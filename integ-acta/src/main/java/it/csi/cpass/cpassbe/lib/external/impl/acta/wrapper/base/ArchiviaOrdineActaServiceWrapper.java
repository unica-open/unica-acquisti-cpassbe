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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.utils.ConstantsActa;
import it.doqui.acta.acaris.archive.AggiungiClassificazione;
import it.doqui.acta.acaris.archive.ClassificazionePropertiesType;
import it.doqui.acta.acaris.archive.ContenutoFisicoPropertiesType;
import it.doqui.acta.acaris.archive.DocumentoFisicoPropertiesType;
import it.doqui.acta.acaris.archive.DocumentoSemplicePropertiesType;
import it.doqui.acta.acaris.archive.EnumDocPrimarioType;
import it.doqui.acta.acaris.archive.EnumTipoDocumentoType;
import it.doqui.acta.acaris.archive.GetObjectParents;
import it.doqui.acta.acaris.archive.GetObjectParentsResponse;
import it.doqui.acta.acaris.archive.IdFormaDocumentariaType;
import it.doqui.acta.acaris.archive.IdStatoDiEfficaciaType;
import it.doqui.acta.acaris.common.AcarisContentStreamType;
import it.doqui.acta.acaris.common.EnumMimeTypeType;
import it.doqui.acta.acaris.common.EnumStreamId;
import it.doqui.acta.acaris.common.IdVitalRecordCodeType;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.ObjectResponseType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.document.CreaDocumento;
import it.doqui.acta.acaris.documentservice.ContenutoFisicoIRC;
import it.doqui.acta.acaris.documentservice.DocumentoArchivisticoIRC;
import it.doqui.acta.acaris.documentservice.DocumentoFisicoIRC;
import it.doqui.acta.acaris.documentservice.EnumStepErrorAction;
import it.doqui.acta.acaris.documentservice.EnumTipoDocumentoArchivistico;
import it.doqui.acta.acaris.documentservice.EnumTipoOperazione;
import it.doqui.acta.acaris.documentservice.IdentificatoreDocumento;
import it.doqui.acta.acaris.documentservice.StepErrorAction;
import it.doqui.acta.acaris.objectservice.AcarisException;
/**
 * The Class AcarisServiceWrapper.
 */
public class ArchiviaOrdineActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(ArchiviaOrdineActaServiceWrapper.class);

	


	private DocumentoFisicoPropertiesType getDocumentoFisico(){
		DocumentoFisicoPropertiesType dfis = new DocumentoFisicoPropertiesType();
		dfis.setDescrizione("");
		return dfis;
	}

	public IdentificatoreDocumento creaDocumentoElettronicoNonFirmato(  String serieTipologica,
																		String ufficio,
																		byte[] contentStream,
																		TestataOrdine testataOrdine,
																		Utente utenteCompilatore,
																		Ente ente,
																		Long statoEfficacia,
																		Long vrc,
																		Long fdo,
																		String autoreGiuridico,
																		String destinatarioGiuridico,
																		String destinatarioFisico,
																		String uuidDocumento
																		) {
		String methodName = "creaDocumentoElettronicoNonFirmato";
        IdentificatoreDocumento documentId = null;
        DocumentoArchivisticoIRC datiCreazione = new DocumentoArchivisticoIRC();
        try {
            DocumentoSemplicePropertiesType properties = new DocumentoSemplicePropertiesType();
            //SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            properties.setRegistrato(true);
            properties.setDefinitivo(true);
            properties.setModificabile(false);

            properties.setOrigineInterna(true);
            properties.setAnalogico(false);
            properties.setDaConservare(false);
            properties.setProntoPerConservazione(false);
            // properties.setDaConservareDopoIl(formatDate.parse("06/10/2010"));
            // properties.setDaConservarePrimaDel(formatDate.parse("06/10/2010"));
            properties.setDaConservarePrimaDel(null);
            properties.setDatiPersonali(false);
            properties.setDatiRiservati(false);
            properties.setDatiSensibili(false);
            properties.setParoleChiave("ORDINE");

            properties.getAutoreGiuridico().add(autoreGiuridico);
            
            properties.getDestinatarioGiuridico().add(destinatarioGiuridico);
            properties.getDestinatarioFisico().add(destinatarioFisico);
            
            properties.setOggetto("ORDINE " + testataOrdine.getAnno() + "/"+testataOrdine.getNumero() + ": "+testataOrdine.getDescrizione());

            properties.getAutoreFisico().add("");
            properties.getScrittore().add("");
            properties.getOriginatore().add(utenteCompilatore.getCognome() + " " +utenteCompilatore.getNome()+" ("+utenteCompilatore.getCodiceFiscale()+")");
            // properties.setOggetto(null);
            properties.setDataDocTopica(ente.getProvincia());
        	GregorianCalendar gcal = new GregorianCalendar();
        	XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            properties.setDataDocCronica(xgcal);
        	properties.setNumRepertorio(testataOrdine.getAnno() + "/" + testataOrdine.getNumero());

            properties.setDocConAllegati(false);
            properties.setDocAutenticato(false);
            properties.setDocAutenticatoCopiaAutentica(false);
            properties.setDocAutenticatoFirmaAutenticata(false);
            properties.setContentStreamFilename("StampaOrdine" + testataOrdine.getAnno() + "-" + testataOrdine.getNumero() + ".pdf");

            // valore indicato in sede di analisi archivistica
            // recuperare tramite servizio query, entita "StatoDiEfficaciaDecodifica"
            // IdStatoDiEfficaciaType idStatoE = new IdStatoDiEfficaciaType();
            // idStatoE.setValue(2);

            IdStatoDiEfficaciaType idStatoE = new IdStatoDiEfficaciaType();
            idStatoE.setValue(statoEfficacia);
            properties.setIdStatoDiEfficacia(idStatoE);

            // valore indicato in sede di analisi archivistica
            // recuperare tramite servizio query, entita "FormaDocumentariaDecodifica"
			// String descrizioneFormaDocumentaria = "";
			// IdFormaDocumentariaType idFormaDoc = queryForFormaDocumentaria(descrizioneFormaDocumentaria, CODICE_ENTE);
			// if (idFormaDoc == null) {
			// 	System.err.println("non trovata una forma documentaria con descrizione " + descrizioneFormaDocumentaria);
			//  return null;
			// }
			// properties.setIdFormaDocumentaria(idFormaDoc);
            // valore indicato in sede di analisi archivistica
            // recuperare tramite servizio managementService.getVitalRecordCode
            
            IdFormaDocumentariaType idFormaDoc = new IdFormaDocumentariaType();
            idFormaDoc.setValue(fdo);
			properties.setIdFormaDocumentaria(idFormaDoc);
            
			/*
            //String vrcDescrizione = "alto";
            GetVitalRecordCode gvsc = new GetVitalRecordCode() ;
            gvsc.setRepositoryId(repositoryId);
			List<VitalRecordCodeType> vrcArray = managementService.getManagementServicePort().getVitalRecordCode(gvsc).getVitalRecordCode();
            for (VitalRecordCodeType vrc : vrcArray) {
                if (vrcDescrizione.equalsIgnoreCase(vrc.getDescrizione())) {
                    System.out.println("Trovato vital record code " + vrcDescrizione + " con id " + vrc.getIdVitalRecordCode().getValue());
                    properties.setIdVitalRecordCode(vrc.getIdVitalRecordCode());
                    break;
                }
            }
			*/
            IdVitalRecordCodeType vitalRecordCode = new IdVitalRecordCodeType();
            vitalRecordCode.setValue(vrc);
            properties.setIdVitalRecordCode(vitalRecordCode );
            // properties.setTipoDocFisico(EnumTipoDocumentoType.FIRMATO);
            properties.setTipoDocFisico(EnumTipoDocumentoType.SEMPLICE);
            // properties.setCodBarre("codiceBARRE");            
            properties.setComposizione(EnumDocPrimarioType.DOCUMENTO_SINGOLO);
            properties.setMultiplo(false);

            ClassificazionePropertiesType associativeObjectProperties = new ClassificazionePropertiesType();
            // valore indicato in sede di analisi archivistica
            associativeObjectProperties.setCopiaCartacea(false);

            properties.setRappresentazioneDigitale(true); // per doc elettronico
            associativeObjectProperties.setCollocazioneCartacea("locazione polverosa");
            // valore indicato in sede di analisi archivistica
            associativeObjectProperties.setCartaceo(false);
            
            // creazione contenuto fisico e relativo stream dati
            ContenutoFisicoIRC[] contenuti = new ContenutoFisicoIRC[1];
            contenuti[0] = new ContenutoFisicoIRC();
            //AcarisContentStreamType contentStream = creaContentStream(FILE_FOLDER_PATH, "prova.pdf", "pdf", EnumMimeTypeType.APPLICATION_PDF);
            
           /* 
            AcarisContentStreamType cs = new AcarisContentStreamType();
            cs.setMimeType(EnumMimeTypeType.APPLICATION_PDF);
            cs.setFilename("StampaOrdine" + testataOrdine.getAnno() + "-" + testataOrdine.getNumero() + ".pdf");
            cs.setStream(contentStream);
           */
            AcarisContentStreamType cs = creaContentStream(contentStream, "StampaOrdine" + testataOrdine.getAnno() + "-" + testataOrdine.getNumero()+".pdf", "pdf", EnumMimeTypeType.APPLICATION_PDF);
            
            contenuti[0].setStream(cs);
            contenuti[0].setTipo(EnumStreamId.PRIMARY);
            //contenuti[0].setTipo(EnumStreamId.SIGNATURE);
            contenuti[0].getAzioniVerificaFirma().addAll(getDatiTestAzioniVerificaFirma());

            ContenutoFisicoPropertiesType contenutoFisicoPropertiesType = new ContenutoFisicoPropertiesType();
            // definire a true per sbustare il contenuto fisico
            contenutoFisicoPropertiesType.setSbustamento(false);
            contenuti[0].setPropertiesContenutoFisico(contenutoFisicoPropertiesType);

            // creazione documento fisico
            DocumentoFisicoIRC[] documenti = new DocumentoFisicoIRC[1];
            documenti[0] = new DocumentoFisicoIRC();
            DocumentoFisicoPropertiesType documentoFisicoProperty = new DocumentoFisicoPropertiesType();
            documentoFisicoProperty.setDescrizione("documento fisico");
            documentoFisicoProperty.setDataMemorizzazione(xgcal);
            documenti[0].setPropertiesDocumentoFisico(documentoFisicoProperty);
            documenti[0].getContenutiFisici().add(contenuti[0]);
            documenti[0].getAzioniVerificaFirma().addAll(getDatiTestAzioniVerificaFirma());

            ObjectIdType parentFolder =  new ObjectIdType();
            parentFolder.setValue(serieTipologica);
            
			datiCreazione.setParentFolderId(parentFolder);
            datiCreazione.setTipoDocumento(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE);
            datiCreazione.setPropertiesDocumento(properties);
            datiCreazione.setPropertiesClassificazione(associativeObjectProperties);
            datiCreazione.getDocumentiFisici().add(documenti[0]);

            EnumTipoOperazione tipoOperazione = EnumTipoOperazione.ELETTRONICO;

            CreaDocumento cd = new CreaDocumento();
        	cd.setPrincipalId(principalId);
        	cd.setRepositoryId(repositoryId);
        	cd.setTipoOperazione(tipoOperazione);
        	cd.setDatiCreazione(datiCreazione);
            //documentId = documentService.getDocumentServicePort().creaDocumento(repositoryId, principalId, tipoOperazione, datiCreazione);
            documentId = documentService.getDocumentServicePort().creaDocumento(cd).getInfo();
            
            //Nel caso in cui il documento debba essere classificato anche nel fascicolo del documento di origine (cioè l’utente, sull’ordine, ha indicato anche un protocollo di origine)
            if(uuidDocumento!=null) {
            	aggiungiClassificazione(documentId,uuidDocumento); 			
            }
            
        } catch (it.doqui.acta.acaris.documentservice.AcarisException acEx) {
        	log.error(methodName,"acEx.getMessage(): " + acEx.getMessage());
        	log.error(methodName,"acEx.getFaultInfo().getErrorCode(): " + acEx.getFaultInfo().getErrorCode());
        	log.error(methodName,"acEx.getFaultInfo().getPropertyName(): " + acEx.getFaultInfo().getPropertyName());
        	log.error(methodName,"acEx.getFaultInfo().getObjectId(): " + acEx.getFaultInfo().getObjectId());
        	log.error(methodName,"acEx.getFaultInfo().getExceptionType(): " + acEx.getFaultInfo().getExceptionType());
        	log.error(methodName,"acEx.getFaultInfo().getClassName(): " + acEx.getFaultInfo().getClassName());
        } catch (Exception ex) {
        	//System.err.println("errore nella classificazione del documento");
            ex.printStackTrace();
        }

        return documentId;
    }

	
	/**
	 * @param uuidDocumento
	 * @param methodName
	 * @throws AcarisException
	 * @throws AcarisException
	 * @throws AcarisException
	 */
	protected void aggiungiClassificazione(IdentificatoreDocumento documentCreatoId,String uuidDocumentoProtocollo) throws AcarisException, it.doqui.acta.acaris.navigationservice.AcarisException,it.doqui.acta.acaris.multifilingservice.AcarisException {
		String methodName = "aggiungiClassificazione";
		log.info(methodName, "BEGIN "  );
		
		QueryResponse documViewresp =getDocumentoView(uuidDocumentoProtocollo);
		List<ObjectResponseType> ortDoc = documViewresp.getObject().getObjects();

		if (documViewresp == null || ortDoc == null || ortDoc.size() == 0) {
			log.error(methodName, "documento non presente");
			errorDesc = "documento non presente";
		}
		
		ObjectIdType idDocumento = new ObjectIdType();
		for (int i = 0; i < ortDoc.size(); i++) {
			if (ortDoc.get(i).getProperties() != null && ortDoc.get(i).getProperties().size() > 0) {
				for (int j = 0; j < ortDoc.get(i).getProperties().size(); j++) {
					log.info(methodName, "prop "+ ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName());
					if (ConstantsActa.QUERY_OBJECT_ID_DOC_SEMPLICE.equalsIgnoreCase(ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName())) {							
					//if (ConstantsActa.QUERY_OBJECT_ID.equalsIgnoreCase(ortDoc.get(i).getProperties().get(j).getQueryName().getPropertyName())) {							
						idDocumento.setValue(ortDoc.get(i).getProperties().get(j).getValue().getContent().get(0));
					}
				}
			}
		}
		log.info(methodName, "getObjectParents "  );
		GetObjectParents op = new GetObjectParents();
		op.setPrincipalId(principalId);
		op.setRepositoryId(repositoryId);
		op.setObjectId(idDocumento);
		op.setFilter(null);
		//ObjectResponseType[] resultListIdClass = navigationService.getNavigationServicePort().getObjectParents(idRepository, idPrincipal, objectId, null);
		GetObjectParentsResponse resultOP = navigationService.getNavigationServicePort().getObjectParents(op);
		ObjectResponseType objParent = new ObjectResponseType();
		if(resultOP.getObjects() != null) {
			objParent = resultOP.getObjects().get(0);
		}
		
		log.info(methodName, "getMultifilingServicePort "  );
		AggiungiClassificazione aci = new AggiungiClassificazione();
		aci.setPrincipalId(principalId);
		aci.setRepositoryId(repositoryId);    			
		//prova 1
		//aci.setFolderId(idDocumento);
		//aci.setClassificazioneDiPartenzaId(objParent.getObjectId());
		//prova 2
		//aci.setFolderId(objParent.getObjectId());
		//aci.setClassificazioneDiPartenzaId(idDocumento);
		//prova 3
		aci.setFolderId(objParent.getObjectId());
		aci.setClassificazioneDiPartenzaId(documentCreatoId.getObjectIdClassificazione());

		
		aci.setParams(null);
		//multiFillingImpl.aggiungiClassificazione(idRepository, resultListIdClass[0].getObjectId(), idPrincipal, objectId, null);
		multifilingService.getMultifilingServicePort().aggiungiClassificazione(aci );
		log.info(methodName, "END "  );

	}


	
    private List<StepErrorAction> getDatiTestAzioniVerificaFirma() {
        List<StepErrorAction> azioniVerificaFirma = new ArrayList<StepErrorAction>();
        for (int i = 0; i < 7; i++) {
        	StepErrorAction sa= new StepErrorAction();
            sa.setAction(EnumStepErrorAction.INSERT);
            sa.setStep(i + 1);
            azioniVerificaFirma.add(sa);
        }
        return azioniVerificaFirma;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////
	public String archiviaOrdineActa(String serieTipologica, String ufficio, byte[] contentStream, TestataOrdine testataOrdine) throws AcarisException, it.doqui.acta.acaris.repositoryservice.AcarisException, it.doqui.acta.acaris.documentservice.AcarisException, DatatypeConfigurationException{
		String methodName = "archiviaOrdineActa";
		ObjectIdType idSerie = new ObjectIdType();
		/*
		if(serieTipologica == null) {
			PagingResponseType prt = getSerieTipologica(ufficio);
			
			if (prt != null && prt.getObjects().size()> 0) {
				for (int i = 0; i < prt.getObjects().size(); i++) {
					for (int j = 0; j < prt.getObjects().get(i).getProperties().size(); j++) {
						if ("objectId".equalsIgnoreCase(prt.getObjects().get(i).getProperties().get(j).getQueryName().getPropertyName())) {
							idSerie.setValue(prt.getObjects().get(i).getProperties().get(j).getValue().getContent().get(0));
						}
					}
				}
			}
		}else {
		*/
			idSerie.setValue(serieTipologica);
		//}
		if(idSerie.getValue() == null) {
			log.error(methodName, "nessuna serie tipologica trovata uff-->"+ufficio );
			errorDesc = "nessuna serie tipologica trovata uff-->"+ufficio;
		}
		//STEP 2 Una volta trovato l’UUID della serie, occorre archiviare il documento nella serie tipologica
		// 
	    // DocumentoArchivisticoIRC.class,
	    // --DocumentoFisicoIRC.class,
	    // ----ContenutoFisicoIRC.class
		log.info(methodName, "serieTipologica "+serieTipologica);
		CreaDocumento cd = new CreaDocumento();
    	cd.setPrincipalId(principalId);
    	cd.setRepositoryId(repositoryId);
    	cd.setTipoOperazione(EnumTipoOperazione.ELETTRONICO);
    	
		DocumentoArchivisticoIRC documentoArchivisticoIRC = new DocumentoArchivisticoIRC();
		documentoArchivisticoIRC.setParentFolderId(idSerie);
		documentoArchivisticoIRC.setTipoDocumento(EnumTipoDocumentoArchivistico.DOCUMENTO_SEMPLICE);
		
		/*
		DocumentoSemplicePropertiesType properties = getDocumentoSemplice();		
    	properties.setComposizione(EnumDocPrimarioType.DOCUMENTO_SINGOLO);
    	ClassificazionePropertiesType classificazione = getClassificazione();
    	documentoArchivisticoIRC.setPropertiesDocumento(properties);
    	documentoArchivisticoIRC.setPropertiesClassificazione(classificazione);
		*/
		
    	DocumentoFisicoIRC documentoFisicoIRC = new DocumentoFisicoIRC();
    	DocumentoFisicoPropertiesType documentoFisicoProperty = getDocumentoFisico();
    	GregorianCalendar gcal = new GregorianCalendar();
    	XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		documentoFisicoProperty.setDataMemorizzazione(xgcal);
		documentoFisicoIRC.setPropertiesDocumentoFisico(documentoFisicoProperty);
    	
    	ContenutoFisicoIRC contenutoFisicoIRC = new ContenutoFisicoIRC();
    	AcarisContentStreamType st = new AcarisContentStreamType();
		st.setStream(contentStream);		
		st.setFilename("StampaOrdine" + testataOrdine.getAnno() + "-" + testataOrdine.getNumero() + ".pdf"); // obbligatorio
		contenutoFisicoIRC.setStream(st);	
		//aggiungo il contenuto fisico al documento fisico 
		documentoFisicoIRC.getContenutiFisici().add(contenutoFisicoIRC);
		//aggiungo il documento fisico al documento archivistico
		documentoArchivisticoIRC.getDocumentiFisici().add(documentoFisicoIRC);		
		//aggiungo il documento archivistico al crea documento
		cd.setDatiCreazione(documentoArchivisticoIRC);
		
		log.info(methodName, "invoco il crea documento ");
		documentService.getDocumentServicePort().creaDocumento(cd);
		//CreaDocumentoResponse cdr = documentService.getDocumentServicePort().creaDocumento(cd);
		return errorDesc;
	}

	//private AcarisContentStreamType creaContentStream(String filePath, final String fileName, final String estensioneFile, EnumMimeTypeType mimeType)
	private AcarisContentStreamType creaContentStream(byte[] stream, final String fileName, final String estensioneFile, EnumMimeTypeType mimeType)
	        throws IOException {

	        AcarisContentStreamType contentStream = new AcarisContentStreamType();
	        contentStream.setFilename(fileName);
	        contentStream.setMimeType(mimeType);


	        //if (getServiziAcaris().isMtomEnabled()) {
	            final InputStream iS = new ByteArrayInputStream(stream);
	            final OutputStream oS = new ByteArrayOutputStream(stream.length);

	            javax.activation.DataSource a = new javax.activation.DataSource() {

	                public OutputStream getOutputStream() throws IOException {
	                    return oS;
	                }

	                public String getName() {
	                    return fileName;
	                }

	                public InputStream getInputStream() throws IOException {
	                    return iS;
	                }

	                public String getContentType() {
	                    return estensioneFile;
	                }
	            };
	            // valorizzare StreamMTOM se servizio invocato via WS SOAP
	            contentStream.setStreamMTOM(new DataHandler(a));
	        return contentStream;
	    }	
}
	



