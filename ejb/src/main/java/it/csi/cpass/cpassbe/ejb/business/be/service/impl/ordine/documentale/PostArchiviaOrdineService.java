/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.BaseDocumentaleService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.PostArchiviaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.PostArchiviaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.UfficioSerie;
import it.csi.cpass.cpassbe.lib.external.DocumentaleHelper;
import it.csi.cpass.cpassbe.lib.external.dto.GestoreDocumentoActa;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostArchiviaOrdineService extends BaseDocumentaleService<PostArchiviaOrdineRequest, PostArchiviaOrdineResponse> {

	private final UtenteDad utenteDad;
	private final DecodificaDad decodificaDad;
	private final FornitoreDad fornitoreDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final ExternalHelperLookup externalHelperLookup;
	//private final SystemDad systemDad;



	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostArchiviaOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,TestataOrdineDad testataOrdineDad, DocumentaleDad documentaleDad,CommonDad commonDad,StampeDad stampeDad,UtenteDad utenteDad,SettoreDad settoreDad,FornitoreDad fornitoreDad,DecodificaDad decodificaDad, RigaOrdineDad rigaOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad,SystemDad systemDad) {
		super(configurationHelper,testataOrdineDad,documentaleDad,settoreDad,commonDad,stampeDad,systemDad);
		this.externalHelperLookup 	= externalHelperLookup;
		this.utenteDad 				= utenteDad;
		this.fornitoreDad			= fornitoreDad;
		this.decodificaDad			= decodificaDad;
		this.rigaOrdineDad			= rigaOrdineDad;
		this.destinatarioOrdineDad	= destinatarioOrdineDad;
		//this.systemDad              = systemDad;
	}


	@Override
	protected void checkServiceParams() {
		final UUID testataOrdineId = request.getTestataOrdineId();
		checkNotNull(testataOrdineId, "testataOrdineId");
	}

	@Override
	protected void execute() {

		final UUID uuid = request.getTestataOrdineId();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(uuid);
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final List<ProtocolloOrdine> prot = testataOrdine.getProtocolloOrdines();
		
		//final boolean documentoGiaArchiviatoMaNonCompletoSuCpass = prot!= null && prot.size()>0 ;
		
		final boolean documentoGiaArchiviatoAncheSuCpass = prot!= null && prot.size()>0 && prot.get(0).getUuidDocumentoOrdine()!=null;
		
		
		final String uuidSerieTipologicaLocale = getSerieTipologicaDaLocale(testataOrdine.getUfficio().getId());
		final Parametro destinazioneArchiviazioneDoc = systemDad.getParametro("DEST_ARCHIVIA_DOC", "DOCUMENTALE",ente.getId());
		final ExternalServiceResolveWrapper<DocumentaleHelper> handler = externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());
		//String oggetto = "";
		//invokeExternalService(handler, () -> handler.getInstance().controlloPresenzaDocByOggetto( uuidSerieTipologicaLocale,null, oggetto));

		if (! documentoGiaArchiviatoAncheSuCpass) {
			//chiamo acta
			final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			final Ufficio ufficio = getUfficioById(testataOrdine.getUfficio().getId());
			//creazione del file da salvare
			final byte[] fileDoc = getFileDocByTestataOrdineId(uuid);
			log.info("PostArchiviaOrdineService", "dopo aver estratto il filedoc " + fileDoc );
			final Utente utenteCompilatore = utenteDad.getUtente(testataOrdine.getUtenteCompilatore().getId()).orElseThrow(() -> new NotFoundException("utente compilatore"));
			final String autoreGiuridico = getAutoreGiuridico(ente,testataOrdine.getSettore().getId(),testataOrdine.getSettore().getSettorePadre());
			final Fornitore fornitore = fornitoreDad.getRicercaFornitoreInterno(testataOrdine.getFornitore().getId());
			final String destinatarioGiuridico = getDestinatarioGiuridico(fornitore);
			final String destinatarioFisico	 = getDestinatarioFisico(fornitore);
			final Long idAOO = getAooBySettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());
			GestoreDocumentoActa gda = null;
			final List<ApiError> apiErrors = new ArrayList<>();

			try {

				if(destinazioneArchiviazioneDoc==null || !destinazioneArchiviazioneDoc.getValore().equalsIgnoreCase("VOLUME")) {
					gda = invokeExternalService(handler, () -> handler.getInstance().archiviaOrdineInSerieTipologica(handler.getParams()
							,uuidSerieTipologicaLocale
							,ufficio.getCodice()
							,fileDoc
							,testataOrdine
							,utenteCompilatore
							,ente
							,autoreGiuridico
							,destinatarioGiuridico
							,destinatarioFisico
							,idAOO
							,settoreCorrente.getCodice()
							)
							);
				}else {
					gda = invokeExternalService(handler, () -> handler.getInstance().archiviaOrdineNelVolume(handler.getParams()
							,uuidSerieTipologicaLocale
							,ufficio.getCodice()
							,fileDoc
							,testataOrdine
							,utenteCompilatore
							,ente
							,autoreGiuridico
							,destinatarioGiuridico
							,destinatarioFisico
							,idAOO
							,settoreCorrente.getCodice()
							)
							);
				}
				log.info("PostArchiviaOrdineService", "error" + gda.getError());

			}catch(final Exception e){
				apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", "Problemi nell'invocazione del servizio CONTABILE verificare che sia attivo "));
				response.addApiErrors(apiErrors);					
				log.error("getProvvedimenti", "Problemi nell'invocazione del servizio esposto da ACTA verificare che il servizio esterno sia attivo ",e );
			}

			if(gda==null) {
				log.error("PostArchiviaOrdineService", " errore nell'oggetto gda che risulta null");
				apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", "Gda NULL controllare archiviaOrdineNelVolume chiamata sistema contabile"));
				response.addApiErrors(apiErrors);			
			}
			
			if(gda!=null) {			
				if (StringUtility.isNotEmpty(gda.getError())) {
					log.error("PostArchiviaOrdineService", " errore nell'oggetto gda non riesco a settare l'oggetto protocollo");
					apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", gda.getError()));
					response.addApiErrors(apiErrors);
				}else {
					if(StringUtility.isEmpty(gda.getUuidDocumentoOrdine()) 
							|| StringUtility.isEmpty(gda.getIdClassificazioneValue())  
							|| StringUtility.isEmpty(gda.getUuidSerieTipologica())
							) {
						log.error(" PostArchiviaOrdineService", " errore nell'oggetto gda non riesco a settare l'oggetto protocollo");
						log.error(" UuidDocumentoOrdine  is null ? " , StringUtility.isEmpty(gda.getUuidDocumentoOrdine()));
						log.error(" ClassificazioneValue is null ? " , StringUtility.isEmpty(gda.getIdClassificazioneValue()));
						log.error(" UuidSerieTipologica  is null ? " , StringUtility.isEmpty(gda.getUuidSerieTipologica()));					
						apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", "uno dei 3 attributi obbligatori e' null"));
						response.addApiErrors(apiErrors);
					}
					ProtocolloOrdine protocolloOrdine = new ProtocolloOrdine();
					if(testataOrdine.getProtocolloOrdines() != null && testataOrdine.getProtocolloOrdines().size()>0) {
						protocolloOrdine = testataOrdine.getProtocolloOrdines().get(0);
					}
					protocolloOrdine.setUuidDocumentoOrdine(gda.getUuidDocumentoOrdine());
					protocolloOrdine.setIdClassificazioneValue(gda.getIdClassificazioneValue());
	
					final TestataOrdine to = new TestataOrdine();
					to.setId(testataOrdine.getId());
					protocolloOrdine.setTestataOrdine(to);
	
					documentaleDad.saveProtocollo(protocolloOrdine);
					//setto eventualmente la CPASS_R_UFFICIO_SERIE con la serie tipologica
					if(uuidSerieTipologicaLocale == null || uuidSerieTipologicaLocale.equals("")) {
						final UfficioSerie ufficioSerie  = new UfficioSerie();
						ufficioSerie.setUfficio(ufficio);
						ufficioSerie.setUuidSerieActa(gda.getUuidSerieTipologica());
						documentaleDad.saveUfficioSerie(ufficioSerie);
					}
	
					log.info("PostArchiviaOrdineService", "ordine da aggiornare? " + request.isAggiornaOrdine());					
					if (request.isAggiornaOrdine()) {
						aggiornaOrdine(testataOrdine);
					}
					response.setProtocolloOrdine(protocolloOrdine);
					log.info("","FINE");
				}
			}
		}else {
			log.info("PostArchiviaOrdineService ", " documentoGiaArchiviatoAncheSuCpass in questo caso setto solo lo stato ammesso che non sia già in firma "+ testataOrdine.getStato().getCodice());
			if (request.isAggiornaOrdine() && !testataOrdine.getStato().getCodice().equals(StatoOrdineEnum.IN_FIRMA.getCostante())) {
				aggiornaOrdine(testataOrdine);
			}
			response.setProtocolloOrdine(prot.get(0));
		}
		
	}

	/**
	 * @param testataOrdine
	 */
	private void aggiornaOrdine(TestataOrdine testataOrdine) {
		// TODO l'arrodontamento dei decimali si fa al conferma, invia in firma e autorizza. sarebbe molto bello centrallizzare, occhio agli altri valori settati nelle singole operazioni!
		testataOrdine.setTotaleConIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleConIva()));
		testataOrdine.setTotaleNoIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleNoIva()));
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.IN_FIRMA.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()),"stato"));
		testataOrdine.setDataInvioFirma(new Date());
		testataOrdineDad.updateTestataOrdineLigth(testataOrdine);
		final List<Destinatario> destinatarios = destinatarioOrdineDad.findByOrdine(testataOrdine.getId());
		if (destinatarios != null) {
			for (final Destinatario destinatario : destinatarios) {

				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (final RigaOrdine rigaOrdine : rigaOrdines) {
						rigaOrdine.setImportoIva(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoIva()));
						rigaOrdine.setImportoNetto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoNetto()));
						rigaOrdine.setImportoTotale(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale()));
						rigaOrdine.setImportoSconto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto()));
						rigaOrdine.setImportoSconto2(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto2()));
						rigaOrdineDad.updateRigaOrdine(rigaOrdine);
					}
				}
			}
		}
	}
}
