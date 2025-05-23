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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetRecuperoDdtRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetRecuperoDdtResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoDocumentoTrasportoEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoXML;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.DocumentoRichiesto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoDocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoListaDocumenti;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * GetRecuperoDdtService
 */
public class GetRecuperoDdtService extends BaseBatchService<GetRecuperoDdtRequest, GetRecuperoDdtResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	private final DocumentoTrasportoDad documentoTrasportoDad;
	private final DocumentoTrasportoRigaDad documentoTrasportoRigaDad;
	private Fornitore fornitore;
	private TestataOrdine testataOrdine;
	private Destinatario destinatario;

	/**
	 *
	 * @param configurationHelper the helper for the configuration
	 * @param externalHelperLookup
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 * @param testataOrdineDad
	 * @param destinatarioOrdineDad
	 * @param rigaOrdineDad
	 * @param decodificaDad
	 * @param documentoTrasportoDad
	 * @param documentoTrasportoRigaDad
	 * @param enteDad
	 * @param commonDad the DAD for the common
	 * @param elaborazioneTipoDad
	 */
	public GetRecuperoDdtService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ExternalHelperLookup externalHelperLookup,
			TestataOrdineDad testataOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			RigaOrdineDad rigaOrdineDad,
			DecodificaDad decodificaDad,
			DocumentoTrasportoDad documentoTrasportoDad,
			DocumentoTrasportoRigaDad documentoTrasportoRigaDad
			) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
		this.documentoTrasportoDad = documentoTrasportoDad;
		this.documentoTrasportoRigaDad = documentoTrasportoRigaDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		final Ente ente = enteOpt.orElseThrow(() -> new NotFoundException("ente"));
		final UUID enteId = ente.getId();

		final List<Ufficio> listaUffici = commonDad.getUfficiByEnte(enteId);
		/*ciclo Per ogni distinto idNotier, il sistema:*/
		for(final Ufficio uff : listaUffici) {
			if (StringUtils.isNotEmpty(uff.getIdNotier())) {
				// Inserisce un record per l’elaborazione
				final Elaborazione elaborazione = inizializzaElaborazione(uff, ente);

				// richiama il servizio [7] Recupero Lista Documenti
				final EsitoRecuperoListaDocumenti eri =recuperoListaDocumenti(uff.getIdNotier(), enteId, "&tipodoc=documento_di_trasporto&ordinamento=desc&stato_giacenza=da_recapitare");

				if (StringUtils.isNotEmpty(eri.getDescErrore())){
					// caso in cui il recupero lista documento dia errore
					final Elaborazione elab = concludiElaborazione(elaborazione,"KO", uff.getIdNotier());
					inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "RECUPERO_DOCUMENTI", "Id Notier -->  "+uff.getIdNotier() , "Errore in recupero documenti cod : "+ eri.getCodErrore()+" - desc --> " + eri.getDescErrore() );
				}else {
					final List<DocumentoRichiesto> listaDocumentoRichiesto = eri.getListaDocumentoRichiesto();
					if(listaDocumentoRichiesto.size()==0) {
						// caso in cui la urn non recupera nessun DDT (caso che non dovrebbe mai verificarsi)
						concludiElaborazione(elaborazione, "OK_NO_ELAB", null);
					}
					//ciclo sui documenti estratti dal primo servizio
					for(final DocumentoRichiesto dr :listaDocumentoRichiesto) {
						final String urn = dr.getUrn();
						final EsitoRecuperoDocumentoTrasporto esito = recuperoDDT(urn, enteId, uff.getIdNotier());
						if(StringUtils.isEmpty(esito.getCodErrore())) {
							final DocumentoTrasporto ddt = esito.getDocumentoTrasporto();
							final ApiError errore = validaDDT(ddt,enteId);
							Integer xmlId =null;
							//gestione delle validazioni formali dei documenti tornati
							if(StringUtils.isEmpty(errore.getCode())) {
								xmlId = inserisciDDT(ddt,"OK");
								final Elaborazione elab = concludiElaborazione(elaborazione, "OK", urn);
								inserisciMessaggioElaborazione (errore, elab, ddt, xmlId, false );
							}else {
								xmlId = inserisciDDT(ddt,"KO");
								final Elaborazione elab = concludiElaborazione(elaborazione, "KO", urn);
								inserisciMessaggioElaborazione (errore, elab, ddt, xmlId, Boolean.TRUE );
							}
						}else {
							// caso in cui la urn passata al servizio di recupero torna un errore
							final Elaborazione elab = concludiElaborazione(elaborazione, "KO", urn);
							inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "RECUPERO_DDT", "Id Notier -->  "+uff.getIdNotier() , "Errore in recupero DDT  : "+ esito.getCodErrore() +" - desc --> " + esito.getDescErrore());
						}
					}
				}
			}
		}

	}

	/**
	 *
	 * @param idNotier
	 * @param enteId
	 * @param parametri
	 * @return EsitoRichiestaInterno
	 */
	private EsitoRecuperoListaDocumenti recuperoListaDocumenti(String idNotier, UUID enteId, String parametri ) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final EsitoRecuperoListaDocumenti esitoRichiesta = invokeExternalService(handler,() -> handler.getInstance().recuperoListaDocumenti(handler.getParams(), idNotier, parametri));
		return esitoRichiesta;
	}
	/**
	 *
	 * @param urn
	 * @param enteId
	 * @param idNotier
	 * @return
	 */
	private EsitoRecuperoDocumentoTrasporto recuperoDDT(String urn, UUID enteId, String idNotier) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		//EsitoRecuperoDocumentoTrasporto esitoRecuperoDDT = invokeExternalService(handler,() -> handler.getInstance().recuperoDDT_TEST(handler.getParams(), urn, idNotier));
		final EsitoRecuperoDocumentoTrasporto esitoRecuperoDDT = invokeExternalService(handler,() -> handler.getInstance().recuperoDDT(handler.getParams(), urn, idNotier));
		return esitoRecuperoDDT;
	}

	/**
	 *
	 * @param uff
	 * @return Elaborazione
	 */
	private Elaborazione inizializzaElaborazione(Ufficio uff,Ente ente) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setEntitaId(uff.getIdNotier());
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		elaborazione.setNumElaborazioneDiGiornata(0);
		elaborazione.setDataElaborazioneDiGiornata("");
		final Optional<ElaborazioneTipo> elaborazioneTipoOpt = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.RICEZIONE_DDT.getCodice());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoOpt.orElseThrow(() -> new NotFoundException("elaborazione tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione.setEnte(ente);
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param elab
	 * @param statoElab
	 * @param urn
	 * @return Elaborazione
	 */
	@Override
	protected Elaborazione concludiElaborazione(Elaborazione elab, String statoElab,String urn) {
		log.info("concludiElaborazione", "statoElab " + statoElab);
		Elaborazione elaborazione = elaborazioneDad.loadElaborazione(elab.getId());
		elaborazione.setStato(statoElab);

		String esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB();
		if(statoElab.equals("KO")) {
			esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB();
		}
		if(statoElab.equals("OK_NO_ELAB")) {
			esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB() + " - nessun DDT recuperato";
		}
		elaborazione.setEsito(esito);
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		if(!StringUtils.isEmpty(urn)) {
			elaborazione.setIdEsterno(urn);
		}
		final Optional<ElaborazioneTipo> elaborazioneTipoOpt = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.RICEZIONE_DDT.getCodice());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoOpt.orElseThrow(() -> new NotFoundException("elaborazione tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param ddt
	 * @param enteId
	 * @return ApiError
	 */
	private ApiError validaDDT(DocumentoTrasporto ddt,UUID enteId) {
		log.info("validaDDT", "Start");
		String annoOrdine 				= "";
		String numeroOrdine 			= "";
		String progressivoDestinatario 	= "";
		Integer annoOrdineInt;
		Integer numeroOrdineInt;
		Integer progressivoDestinatarioInt;

		ApiError ae = new ApiError();
		final String ordineUnicoId = ddt.getOrdineUnicoId();
		//int numeroseparatori = StringUtils.countMatches(ordineUnicoId, '_');
		final int numeroseparatori = ordineUnicoId.split("_").length;
		if (numeroseparatori!=4 ) {
			ae = CoreError.GENERIC_ERROR.getError("error","identificativo ordine errato o non correttamente formattato --> " + ordineUnicoId);
			return ae;
		}else {
			final String[] token = ordineUnicoId.split("_");
			annoOrdine = token[0];// La stringa prima del primo “_” coincide con anno ordine
			numeroOrdine =token[1];//La stringa tra il primo ed il secondo “_” coincide con numero ordine
			progressivoDestinatario =token[2];//La stringa tra il secondo ed il terzo “_” coincide con progressivo destinatario ordine
			if (NumberUtility.isNumber(progressivoDestinatario) && NumberUtility.isNumber(annoOrdine) && NumberUtility.isNumber(numeroOrdine)) {
				annoOrdineInt = Integer.parseInt(annoOrdine);
				numeroOrdineInt = Integer.parseInt(numeroOrdine);
				progressivoDestinatarioInt = Integer.parseInt(progressivoDestinatario);
			}else {
				ae = CoreError.GENERIC_ERROR.getError("error","identificativo ordine errato valori non numerici --> " + ordineUnicoId);
				return ae;
			}
		}

		final Optional<TestataOrdine> too = testataOrdineDad.getByAnnoENumeroOpt(annoOrdineInt, numeroOrdineInt, enteId);
		//il record sia presente, altrimenti il messaggio di riferimento  ORD-ORD-E-0043
		if(!too.isPresent()) {
			ae = MsgCpassOrd.ORDORDE0043.getError();
			return ae;
		}else {
			testataOrdine = too.get();
			final List<Destinatario> destinatari = destinatarioOrdineDad.getDestinatarioByTestataAndProgressivo(testataOrdine.getId(),progressivoDestinatarioInt);
			if(destinatari.size()!=1) {
				ae = CoreError.GENERIC_ERROR.getError("error","destinatario non presente in archivio controllare il progressivo destinatario  " + progressivoDestinatarioInt +" con idTestata " + testataOrdine.getId());
				return ae;
			}
			destinatario = destinatari.get(0);
		}

		// abbia stato_id corrispondente con AUTORIZZATO, altrimenti il messaggio di riferimento  ORD-ORD-E-0075
		if(!testataOrdine.getStato().getCodice().equals(StatoOrdineEnum.AUTORIZZATO.getCostante())) {
			ae = MsgCpassOrd.ORDORDE0075.getError();
			return ae;
		}

		// abbia stato_invio_NSO = 'OK', altrimenti il messaggio di riferimento  ORD-ORD-E-0076
		if(testataOrdine.getStatoNso() == null || (!testataOrdine.getStatoNso().getCodice().equalsIgnoreCase(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante()))) {
			ae = MsgCpassOrd.ORDORDE0076.getError();
			return ae;
		}

		//sia di competenza dell utente (vedi regole [5]2.5 Visibilit documentale), altrimenti il messaggio di riferimento  ORD-ORD-E-0044

		//sia ordine di “FORNITURE” (CPASS_T_ORD_TESTATA_ORDINE.tipo_acquisto_id corrispondente al tipo FORNITURE), altrimenti il messaggio di riferimento è ORD-ORD-E-0126
		if(testataOrdine.getTipoAcquisto() == null || (!testataOrdine.getTipoAcquisto().getCodice().equalsIgnoreCase(ConstantsDecodifiche.SettoreInterventiEnum.FORNITURE.getCodice()))) {
			ae = MsgCpassOrd.ORDORDE0126.getError();
			return ae;
		}

		//il fornitore dell’ordine coincida con quello da cui si è ricevuto il DDT, cioè occorre cercare in CPASS_T_FORNITORE con la condizione CPASS_T_FORNITORE.partita_iva= valore del tag <DespatchAdvice>/<Cac: SellerSupplierParty>/<cac:Party>/<cac:PartyIdentification>< cbcID schemeID=”IT:VAT”> e quindi confrontare CPASS_T_FORNITORE.fornitore_id con CPASS_T_ORD_TESTATA_ORDINE.fornitore_id; se non coincidono
		fornitore = null;
		final String pif = ddt.getPartitaIvaFornitore();
		//pif = pif.substring(pif.lastIndexOf(":")+1,pif.length()); // gestisce il caso in cui il ddt fornisca il cf nel formato IT:CF:02483810000
		if(!testataOrdine.getFornitore().getPartitaIva().equalsIgnoreCase(pif)){
			ae = MsgCpassOrd.ORDORDE0133.getError();
			return ae;
		}
		ddt.setPartitaIvaFornitore(pif);
		fornitore = testataOrdine.getFornitore();
		//Fine controlli testata

		//Controlli destinatario Unico

		// CPASS_T_ORD_DESTINATARIO_ORDINE.indirizzo opportunamente concatenato con CPASS_T_ORD_DESTINATARIO_ORDINE.num_civico deve matchare con <DespatchAdvice>/ <Cac:DeliveryCustomerParty>/ <cacParty>/< cac:PostalAddress>/<cbc:StreetName>  (NOTA: fare attenzione alle virgole e agli spazi che potrebbero essere presenti, occorre trattare opportunamente la stringa per poterla agevolmente confrontare con i due dati presenti sul db). Se i due dati non matchano, il messaggio da dare è ORD-ORD-E0128
		final String indirizzoDDT = ddt.getIndirizzoDestinatario();
		final String indirizzoDDTCompattato = getIndirizzoPerComparazioneSimilare(indirizzoDDT);
		final String indirizzoDestinatario = destinatario.getIndirizzo()+destinatario.getNumCivico();
		final String indirizzoDestinatarioCompattato = getIndirizzoPerComparazioneSimilare(indirizzoDestinatario);

		if(!indirizzoDDTCompattato.equalsIgnoreCase(indirizzoDestinatarioCompattato)){
			ae = MsgCpassOrd.ORDORDE0128.getError();
			return ae;
		}

		if(!ddt.getLocalitaDestinatario().equalsIgnoreCase(destinatario.getLocalita())){
			ae = MsgCpassOrd.ORDORDE0129.getError();
			return ae;
		}

		if(!ddt.getCapDestinatario().equalsIgnoreCase(destinatario.getCap())){
			ae = MsgCpassOrd.ORDORDE0130.getError();
			return ae;
		}

		log.info("validaDDT", "Stop");
		return ae;

	}

	/**
	 * @param destinatario
	 * @return
	 */
	private String getIndirizzoPerComparazioneSimilare(String indirizzo) {
		String indirizzoCompatto = indirizzo.trim().toUpperCase();
		indirizzoCompatto = indirizzoCompatto.replace(" ","");
		indirizzoCompatto = indirizzoCompatto.replace(",","");
		indirizzoCompatto = indirizzoCompatto.replace("/","");
		indirizzoCompatto = indirizzoCompatto.replace("°","");
		indirizzoCompatto = indirizzoCompatto.replace("INT","");
		indirizzoCompatto = indirizzoCompatto.replace("NUMERO","");
		indirizzoCompatto = indirizzoCompatto.replace("NUM","");
		indirizzoCompatto = indirizzoCompatto.replace(".","");
		indirizzoCompatto = indirizzoCompatto.replace("(","");
		indirizzoCompatto = indirizzoCompatto.replace(")","");
		return indirizzoCompatto;
	}
	/**
	 *
	 * @param elaborazione
	 * @param tipo
	 * @param codice
	 * @param descrizione
	 */
	/*
	private void inserisciMessaggioErroreInElaborazione(Elaborazione elaborazione, String tipo, String codice, String descrizione) {
		ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setTipo(tipo);
		elaborazioneMessaggio.setDescrizione(descrizione);
		elaborazioneMessaggio.setCode(codice);
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}
	 */
	/**
	 *
	 * @param ae
	 * @param elaborazione
	 * @param ddt
	 * @param xmlId
	 * @param xstErrore
	 */
	private void inserisciMessaggioElaborazione(ApiError ae,Elaborazione elaborazione, DocumentoTrasporto ddt, Integer xmlId, Boolean xstErrore) {
		final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		if(xstErrore) {
			elaborazioneMessaggio.setTipo("ERRORE_DOCUMENTO_DDT");
			elaborazioneMessaggio.setDescrizione(ae.getErrorMessage());
			elaborazioneMessaggio.setCode("URN --> " +ddt.getUrnDocumento() + " Ordine Unico Id --> " + ddt.getOrdineUnicoId()+" - "+"xmlId--> "+xmlId + " - ErrorCode --> "+ae.getCode());
		}else {
			elaborazioneMessaggio.setTipo("DOCUMENTO_DDT");
			elaborazioneMessaggio.setDescrizione("Documento elaborato correttamente");
			elaborazioneMessaggio.setCode("URN --> " +ddt.getUrnDocumento() +" Ordine Unico Id --> " + ddt.getOrdineUnicoId()+" - "+"xmlId--> "+xmlId );
		}
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}
	/**
	 *
	 * @param ddt
	 * @param stato
	 * @return Integer
	 */
	private Integer inserisciDDT(DocumentoTrasporto ddt, String stato) {
		//  Stato DA ABBINARE se non sono stati trovati errori, altrimenti stato  SCARTATO
		Stato st = decodificaDad.getStatoOpt(StatoDocumentoTrasportoEnum.DA_ABBINARE.getCostante(), ConstantsCPassStato.TipoStatoEnum.DOCUMENTO_DI_TRASPORTO.getCostante()).orElseThrow(() -> new NotFoundException("stato DDT")) ;
		ddt.setStato(st);
		if(!stato.equals("OK")){
			st = decodificaDad.getStatoOpt(StatoDocumentoTrasportoEnum.SCARTATO.getCostante(), ConstantsCPassStato.TipoStatoEnum.DOCUMENTO_DI_TRASPORTO.getCostante()).orElseThrow(() -> new NotFoundException("stato DDT")) ;
			ddt.setStato(st);
		}
		// Setto il fornitore
		ddt.setFornitore(fornitore);
		// salvo il DDT
		ddt = documentoTrasportoDad.saveDocumentoTrasporto(ddt);
		// salvo il DDT XML
		final DocumentoTrasportoXML xml = documentoTrasportoDad.saveDocumentoTrasportoXMl(ddt.getDocumentoTrasportoXMLList().get(0),ddt);
		//Salvataggio righe DDT
		for(final DocumentoTrasportoRiga rigaDDT : ddt.getDocumentoTrasportoRigaList()) {
			rigaDDT.setDocumentoTrasporto(ddt);
			List<RigaOrdine> riga = new ArrayList<>();

			if(destinatario !=null && destinatario.getId() != null ) {
				if(StringUtils.isNumeric(rigaDDT.getProgressivoRigaId())) {
					riga = rigaOrdineDad.getRigheByDestinatarioAndprogressivo(destinatario.getId(),Integer.parseInt(rigaDDT.getProgressivoRigaId()));
					// in teoria e si spera in pratica questa condizione e' superflua la query deve restituire 1 solo record
					if(riga.size()>0) {
						rigaDDT.setRigaOrdine(riga.get(0));
					}
				}
				rigaDDT.setTestataOrdine(testataOrdine);
			}
			documentoTrasportoRigaDad.saveDocumentoTrasportoRiga(rigaDDT);
		}
		return xml.getId();
	}

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
	/*
	public static void main(String[] args) {
	  String cf = "IT:CF:02483810000";
	  System.out.println(cf.lastIndexOf(":"));
	  System.out.println(cf.substring(cf.lastIndexOf("-")+1,cf.length()));
	}
	 */
}