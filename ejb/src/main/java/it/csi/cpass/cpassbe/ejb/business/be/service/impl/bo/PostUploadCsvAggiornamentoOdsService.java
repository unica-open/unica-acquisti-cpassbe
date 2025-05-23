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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PostUploadCsvAggiornamentoOdsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PostUploadCsvAggiornamentoOdsResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.FlussoOdsEsterni;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Save PostUploadCsvAggiornamentoOdsService
 */
public class PostUploadCsvAggiornamentoOdsService extends BaseBoService<PostUploadCsvAggiornamentoOdsRequest, PostUploadCsvAggiornamentoOdsResponse> {

	public static final String GROUP_ELABORAZIONE_ID = "group.elaborazione_id";
	private final DecodificaDad decodificaDad;
	private final CommonDad commonDad;
	private final FornitoreDad fornitoreDad;
	private final ExternalHelperLookup externalHelperLookup;
	private int numeroErrori = 0;
	//FORNITORI
	private final Map<String,Fornitore> fornitoriMap = new HashMap<>();
	//UNITA_MISURA_ID
	private final Map<String,UnitaMisura> unitaMisuraMap = new HashMap<>();
	//CPV_ID
	private final Map<String,Cpv> cpvMap = new HashMap<>();
	//ALIQUOTE_IVA_ID
	private final Map<String,AliquoteIva> aliquoteIvaMap = new HashMap<>();
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad
	 * @param decodificaDad
	 * @param utenteDad
	 * @param interventoImportiDad
	 * @param enteDad
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 */
	public PostUploadCsvAggiornamentoOdsService(
			ConfigurationHelper configurationHelper,
			DecodificaDad decodificaDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			FornitoreDad fornitoreDad,
			ExternalHelperLookup externalHelperLookup,
			CommonDad commonDad
			) {
		super(configurationHelper,elaborazioneDad,elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.decodificaDad 			= decodificaDad;
		this.elaborazioneTipoDad 	= elaborazioneTipoDad;
		this.fornitoreDad 			= fornitoreDad;
		this.externalHelperLookup   = externalHelperLookup;
		this.commonDad 				= commonDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAttachmentCsv(), "file csv aggiorna ods");
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Elaborazione elab         = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.AGGIORNAMENTO_ODS.getStatoDB(),ente.getId().toString(),null);

		numeroErrori = 0;
		final String separatore = request.getSeparatore() !=null ? request.getSeparatore() : ",";
		final Reader readerImp = new InputStreamReader(new ByteArrayInputStream(request.getAttachmentCsv()));

		try (BufferedReader br = new BufferedReader(readerImp)) {
			String line ="";
			final List<FlussoOdsEsterni> listFlussoOdsEsterni = new ArrayList<>();
			Boolean isFileFull = Boolean.FALSE;
			for (int posizioneRiga = 1; (line = br.readLine()) != null; posizioneRiga++) {
				final FlussoOdsEsterni flussoOdsEsterni = new FlussoOdsEsterni();
				line = line.replace('"', ' ');
				line = line.replace(separatore+separatore, separatore+" " +separatore);
				line = line.replace(separatore+separatore, separatore+" " +separatore);

				final String[] celledellaRiga = line.split(separatore);
				//escludo la prima riga di intestazione
				if(posizioneRiga==1) {
					checkBusinessCondition(celledellaRiga!=null && celledellaRiga.length==12, MsgCpassBo.BACODSE0011.getError("num-riga",posizioneRiga));
					continue;
				}
				if(posizioneRiga>1) {
					isFileFull = Boolean.TRUE;
					if(celledellaRiga==null || celledellaRiga.length!=12) {
						//riga da scartare gestire il messaggio
						numeroErrori ++ ;
						flussoOdsEsterni.setErrore("riga "+ posizioneRiga + "  presenta un numero colonne non conformi a quelle attese" );
						flussoOdsEsterni.setEsito("KO");
						inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), flussoOdsEsterni.getErrore());
						continue;
					}

				}
				//controllo numero colonna sulla singolas riga
				flussoOdsEsterni.setElaborazioneId(elab.getId());
				//flussoOdsEsterni.setErrore;
				//flussoOdsEsterni.setEsito;
				flussoOdsEsterni.setIdEnte(ente.getId());
				flussoOdsEsterni.setNumeroRiga(posizioneRiga);
				flussoOdsEsterni.setCodiceOds(StringUtility.trim(celledellaRiga[0]));
				flussoOdsEsterni.setDescrizioneOds(StringUtility.trim(celledellaRiga[1]));
				flussoOdsEsterni.setCpvCodice(StringUtility.trim(celledellaRiga[2]));
				flussoOdsEsterni.setUnitaMisura(StringUtility.trim(celledellaRiga[3]));
				flussoOdsEsterni.setCodiceIva(StringUtility.trim(celledellaRiga[4]));
				flussoOdsEsterni.setInventariabile(StringUtility.trim(celledellaRiga[5]));
				flussoOdsEsterni.setDataInizioValidita(StringUtility.trim(celledellaRiga[6]));
				flussoOdsEsterni.setDataFineValidita(StringUtility.trim(celledellaRiga[7]));

				String prezzoUnitario = StringUtility.trim(celledellaRiga[8]);
				prezzoUnitario = prezzoUnitario.replace(',', '.');
				flussoOdsEsterni.setPrezzoUnitario(prezzoUnitario);

				//gestione fornitore
				flussoOdsEsterni.setCodiceFornitore(StringUtility.trim(celledellaRiga[9]));
				flussoOdsEsterni.setCodiceListinoFornitore(StringUtility.trim(celledellaRiga[10]));
				flussoOdsEsterni.setDescrizioneListinoFornitore(StringUtility.trim(celledellaRiga[11]));
				//mappa dei fornitori
				gestisciFornitore(ente, elab, posizioneRiga, flussoOdsEsterni );
				//gestisci decodifiche
				gestisciDecodifiche(elab,posizioneRiga,  flussoOdsEsterni);
				listFlussoOdsEsterni.add(flussoOdsEsterni);
			}
			checkBusinessCondition(isFileFull, MsgCpassBo.BACODSE0010.getError());

			gestisciOds(elab,listFlussoOdsEsterni, ente, utenteConnesso);

			if(numeroErrori==0) {
				concludiElaborazione(elab, "OK", ConstantsCPassElaborazione.StatoEnum.AGGIORNAMENTO_ODS.getStatoDB() );
			}else {
				log.warn("Carica CSV ODS ", "Numero Errori Complessivi" + numeroErrori);
				concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB() );


				final List<ApiError> apiErrors = new ArrayList<>();
				final ApiError errore = CoreError.GENERIC_ERROR.getError("error","Elaborazione Completata con errori");
				apiErrors.add(errore);
				response.setApiErrors(apiErrors );
			}
		} catch (final IOException e) {
			log.error("execute", "IOException ",e);
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), e.getMessage());
			concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.AGGIORNAMENTO_ODS.getStatoDB() );

		}

	}

	/**
	 * @param ente
	 * @param elab
	 * @param fornitoriMap
	 * @param posizioneRiga
	 * @param flussoOdsEsterni
	 */
	private void gestisciDecodifiche(Elaborazione elab,int posizioneRiga,FlussoOdsEsterni flussoOdsEsterni) {
		//UNITA_MISURA_ID Id relativo al valore indicato nella colonna “UM” del file
		if(!StringUtility.isEmpty(flussoOdsEsterni.getUnitaMisura())){
			if(!unitaMisuraMap.containsKey(flussoOdsEsterni.getUnitaMisura())) {
				//TODO estraggo la UM
				final UnitaMisura um = decodificaDad.getUnitaMisuraByCodice(flussoOdsEsterni.getUnitaMisura());
				if(um==null) {
					numeroErrori ++ ;
					flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " Unità Misura Non Presente " + flussoOdsEsterni.getUnitaMisura());
					flussoOdsEsterni.setEsito("KO");
				}else {
					unitaMisuraMap.put(flussoOdsEsterni.getUnitaMisura(), um);
				}
			}
		}

		//CPV_ID Id relativo al valore indicato nella colonna “CPV_CODICE” del file
		if(!StringUtility.isEmpty(flussoOdsEsterni.getCpvCodice())){
			if(!cpvMap.containsKey(flussoOdsEsterni.getCpvCodice())) {
				//TODO estraggo la CPV
				final Cpv cpv = decodificaDad.getCpvByCodice(flussoOdsEsterni.getCpvCodice());
				if(cpv==null) {
					numeroErrori ++ ;
					flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " Cpv Non Presente " + flussoOdsEsterni.getCpvCodice());
					flussoOdsEsterni.setEsito("KO");
				}else {
					cpvMap.put(flussoOdsEsterni.getCpvCodice(), cpv);
				}
			}
		}

		//ALIQUOTE_IVA_ID Id relativo al valore indicato nella colonna “CODICE_IVA” del file
		if(!StringUtility.isEmpty(flussoOdsEsterni.getCodiceIva())){
			if(!aliquoteIvaMap.containsKey(flussoOdsEsterni.getCodiceIva())) {
				//TODO estraggo la CPV
				final AliquoteIva ai = decodificaDad.getAliquoteIvaByCodice(flussoOdsEsterni.getCodiceIva());
				if(ai==null) {
					numeroErrori ++ ;
					flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " aliquota Iva Non Presente " + flussoOdsEsterni.getCodiceIva());
					flussoOdsEsterni.setEsito("KO");
				}else {
					aliquoteIvaMap.put(flussoOdsEsterni.getCodiceIva(), ai);
				}
			}
		}
	}



	/**
	 * @param ente
	 * @param elab
	 * @param fornitoriMap
	 * @param posizioneRiga
	 * @param flussoOdsEsterni
	 */
	protected void gestisciFornitore(Ente ente, Elaborazione elab, int posizioneRiga, FlussoOdsEsterni flussoOdsEsterni) {
		final boolean  xstCLF = StringUtility.isNotEmpty(flussoOdsEsterni.getCodiceListinoFornitore() ) && !flussoOdsEsterni.getCodiceListinoFornitore().equals("0");
		final boolean  xstDLF = StringUtility.isNotEmpty(flussoOdsEsterni.getDescrizioneListinoFornitore()) && !flussoOdsEsterni.getDescrizioneListinoFornitore().equals("0");
		final boolean  xstFor = StringUtility.isNotEmpty(flussoOdsEsterni.getCodiceFornitore()) && !flussoOdsEsterni.getCodiceFornitore().equals("0");
		flussoOdsEsterni.setEsito("OK");

		if(!((xstCLF && xstDLF && xstFor) || (!xstCLF && !xstDLF && !xstFor))) {
			//riga da scartare gestire il messaggio
			numeroErrori ++ ;
			flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " <DATI LISTINO>: i dati del listino devono essere tutti valorizzati");
			flussoOdsEsterni.setEsito("KO");
		}else {
			//gestione del fornitore
			if(xstFor){
				if(fornitoriMap.containsKey(flussoOdsEsterni.getCodiceFornitore())) {
					if(fornitoriMap.get(flussoOdsEsterni.getCodiceFornitore())==null) {
						//riga da scartare gestire il messaggio
						numeroErrori ++ ;
						flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " Fornitore Non Presente " + flussoOdsEsterni.getCodiceFornitore());
						flussoOdsEsterni.setEsito("KO");
					}
				}else {
					final Fornitore fornitoreEstratto = ricercaFornitore(flussoOdsEsterni.getCodiceFornitore(), ente, elab);
					fornitoriMap.put(flussoOdsEsterni.getCodiceFornitore(), fornitoreEstratto);
					if(fornitoreEstratto == null) {
						//riga da scartare gestire il messaggio
						numeroErrori ++ ;
						flussoOdsEsterni.setErrore("riga "+ posizioneRiga + " Fornitore Non Presente " + flussoOdsEsterni.getCodiceFornitore());
						flussoOdsEsterni.setEsito("KO");
					}
				}
			}

			if((xstCLF && xstDLF && xstFor) && (flussoOdsEsterni.getEsito() ==null || flussoOdsEsterni.getEsito().equals("OK"))) {
				// gestire il listino
				final List<ListinoFornitore> listinoFornitore = new ArrayList<>();
				final ListinoFornitore lf = new ListinoFornitore();
				lf.setFornitore(fornitoriMap.get(flussoOdsEsterni.getCodiceFornitore()));
				lf.setCodiceOds(flussoOdsEsterni.getCodiceListinoFornitore());
				lf.setDescrizione(flussoOdsEsterni.getDescrizioneListinoFornitore());
				listinoFornitore.add(lf);
				flussoOdsEsterni.setListinoFornitore(listinoFornitore);
			}
		}
	}
	/**
	 *
	 * @param fornitoreCode
	 * @param ente
	 * @param elab
	 * @return
	 */
	private Fornitore ricercaFornitore(String fornitoreCode,Ente ente,Elaborazione elab) {
		String error = "";
		Fornitore fornitore = fornitoreDad.getFornitoreByCodice(fornitoreCode, ente.getId());
		if(fornitore == null) {
			//cerco sul sistemaContabile
			fornitore = cercaFornitoreSuSistemaContabile(fornitoreCode, ente.getId());
			if(fornitore == null) {
				error = "Fornitore inesistente " + fornitoreCode;
				log.error("gestisciFornitori" , error);
				//inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_FILE_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), error);
			}else {
				fornitore.setEnte(ente);
				//se non lo ho sul DB e lo trovo sul sistema contabile lo inserisco sul DB
				fornitore = fornitoreDad.insert(fornitore);
			}
		}
		return fornitore;
	}

	private Fornitore cercaFornitoreSuSistemaContabile(String fornitoreCode, UUID enteId) {
		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		final Fornitore forn = new Fornitore();
		forn.setCodice(fornitoreCode);
		filtroFornitore.setFornitore(forn );
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
		final ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
		final List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));
		if(fornitori == null || fornitori.size()==0) {
			return null;
		}else {
			return fornitori.get(0);
		}
	}

	private void gestisciOds(Elaborazione elab, List<FlussoOdsEsterni> listFlussoOdsEsterni, Ente ente, Utente utenteconnesso) {
		for(final FlussoOdsEsterni ods : listFlussoOdsEsterni) {
			if(ods.getEsito()==null || ods.getEsito().equals("OK") ) {
				//controlli sulle singole celle
				if(StringUtility.isEmpty(ods.getCodiceOds())) {
					numeroErrori ++ ;
					ods.setEsito("KO");
					ods.setErrore("riga "+ods.getNumeroRiga() +" codice ODS obbligatorio");
					inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), "riga "+ods.getNumeroRiga() +" codice ODS obbligatorio");
				}else {
					final Ods odsEstratto = decodificaDad.findODSByCodice(ods.getCodiceOds(),ods.getIdEnte());
					if(odsEstratto==null || odsEstratto.getId()==null) {
						log.info("gestisciOds", "codice non trovato inserisco " + ods.getCodiceOds());
						getisciInserimentoNuovoODS(elab, ods, ente, utenteconnesso);
					}else {
						getisciModificaODS(elab, ods, odsEstratto, ente, utenteconnesso);
					}
				}
			}else {
				inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), ods.getErrore());
			}
		}
	}

	private void getisciModificaODS(Elaborazione elab, FlussoOdsEsterni flussoOdsEsterno, Ods odsEstratto, Ente ente,Utente utenteConnesso) {
		final String methodName = "getisciModificaODS";
		log.info(methodName , "START");
		final String errorPrefix = "riga " + flussoOdsEsterno.getNumeroRiga() + " modifica cod Ods "+flussoOdsEsterno.getCodiceOds();

		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDescrizioneOds())) {
			odsEstratto.setDescrizione(flussoOdsEsterno.getDescrizioneOds());
		}
		//UNITA_MISURA_ID
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getUnitaMisura())) {
			odsEstratto.setUnitaMisura(unitaMisuraMap.get(flussoOdsEsterno.getUnitaMisura()));
		}
		//CPV_ID
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getCpvCodice())) {
			odsEstratto.setCpv(cpvMap.get(flussoOdsEsterno.getCpvCodice()));
		}
		//ALIQUOTE_IVA_ID
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getCodiceIva())) {
			odsEstratto.setAliquoteIva(aliquoteIvaMap.get(flussoOdsEsterno.getCodiceIva()));
		}
		//INVENTARIABILE Se la colonna “INVENTARIABILE” del file vale “SI” mettere a TRUE, se vale “NO” mettere a FALSE
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getInventariabile())) {
			odsEstratto.setInventariabile(flussoOdsEsterno.getInventariabile().trim().equalsIgnoreCase("SI") ? Boolean.TRUE : Boolean.FALSE);
		}
		//PREZZO_UNITARIO Valore indicato nella colonna “NUOVO_PREZZO_UNITARIO” del file
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getInventariabile())) {
			odsEstratto.setPrezzoUnitario(new BigDecimal(flussoOdsEsterno.getPrezzoUnitario().trim()));
		}

		//DATA_VALIDITA_INIZIO
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDataInizioValidita())) {
			if(DateUtility.stringToDate(flussoOdsEsterno.getDataInizioValidita(),"dd-MM-yyyy")!=null) {
				odsEstratto.setDataValiditaInizio(DateUtility.stringToDate(flussoOdsEsterno.getDataInizioValidita(),"dd-MM-yyyy"));
			}else {
				numeroErrori ++ ;
				inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data inizio validità non corretta formato desiderato dd-MM-yyyy");
				return;
			}
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data inizio validità non corretta formato desiderato dd-MM-yyyy");
			return;
		}

		//DATA_VALIDITA_FINE
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDataFineValidita()) ) {
			if(DateUtility.stringToDate(flussoOdsEsterno.getDataFineValidita(),"dd-MM-yyyy")!=null) {
				odsEstratto.setDataValiditaFine(DateUtility.stringToDate(flussoOdsEsterno.getDataFineValidita(),"dd-MM-yyyy"));
			}else {
				numeroErrori ++ ;
				inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data fine validità non corretta formato desiderato dd-MM-yyyy");
				return;
			}
		}

		//DATA_MODIFICA
		odsEstratto.setDataModifica(new Date());
		//UTENTE_MODIFICA
		odsEstratto.setUtenteModifica(utenteConnesso.getCodiceFiscale());
		//QUANTITA_MAX_RICHIEDIBILE
		//GENERICO
		odsEstratto.setGenerico(Boolean.FALSE);
		//ENTE_ID
		odsEstratto.setEnte(ente);

		if(flussoOdsEsterno.getListinoFornitore()!=null && flussoOdsEsterno.getListinoFornitore().size()>0) {
			odsEstratto.setListinoFornitores(flussoOdsEsterno.getListinoFornitore());
		}
		log.info(methodName , "prima di update ods");
		commonDad.putOggettoSpesaMassiva(odsEstratto, utenteConnesso.getCodiceFiscale(), ente);
		log.info(methodName , "STOP");

	}

	private void getisciInserimentoNuovoODS(Elaborazione elab, FlussoOdsEsterni flussoOdsEsterno, Ente ente, Utente utenteConnesso) {
		final String methodName = "getisciInserimentoNuovoODS";
		log.info(methodName , "START");
		final Ods odsNuovo = new Ods();
		final String errorPrefix = "riga " + flussoOdsEsterno.getNumeroRiga() + " inserimento cod Ods "+flussoOdsEsterno.getCodiceOds();

		if(StringUtility.isNotEmpty(flussoOdsEsterno.getCodiceOds())) {
			odsNuovo.setCodice(flussoOdsEsterno.getCodiceOds());
		}else{
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), errorPrefix +"  CODICE ODS non valorizzato");
			return;
		}

		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDescrizioneOds())) {
			odsNuovo.setDescrizione(flussoOdsEsterno.getDescrizioneOds());
		}else{
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), errorPrefix +"  DESCRIZIONE ODS non valorizzata");
			return;
		}
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getUnitaMisura())) {
			odsNuovo.setUnitaMisura(unitaMisuraMap.get(flussoOdsEsterno.getUnitaMisura()));
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),  errorPrefix + "  Unita di misura non valorizzata");
			return;
		}
		//CPV_ID
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getCpvCodice())) {
			odsNuovo.setCpv(cpvMap.get(flussoOdsEsterno.getCpvCodice()));
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), errorPrefix +"  Unita di misura non valorizzata");
			return;
		}

		//ALIQUOTE_IVA_ID
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getCodiceIva())) {
			odsNuovo.setAliquoteIva(aliquoteIvaMap.get(flussoOdsEsterno.getCodiceIva()));
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), errorPrefix + "Codice Iva non valorizzata");
			return;
		}

		//INVENTARIABILE Se la colonna “INVENTARIABILE” del file vale “SI” mettere a TRUE, se vale “NO” mettere a FALSE
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getInventariabile())) {
			odsNuovo.setInventariabile(flussoOdsEsterno.getInventariabile().trim().equalsIgnoreCase("SI") ? Boolean.TRUE : Boolean.FALSE);
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), errorPrefix + "  campo Inventariabile non valorizzato");
			return;
		}

		//PREZZO_UNITARIO Valore indicato nella colonna “NUOVO_PREZZO_UNITARIO” del file
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getInventariabile())) {
			odsNuovo.setPrezzoUnitario(new BigDecimal(flussoOdsEsterno.getPrezzoUnitario().trim()));
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  prezzo unitario non valorizzato");
			return;
		}

		if(flussoOdsEsterno.getListinoFornitore()!=null && flussoOdsEsterno.getListinoFornitore().size()>0) {
			odsNuovo.setListinoFornitores(flussoOdsEsterno.getListinoFornitore());
		}

		//DATA_VALIDITA_INIZIO
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDataInizioValidita())) {
			if(DateUtility.stringToDate(flussoOdsEsterno.getDataInizioValidita(),"dd-MM-yyyy")!=null) {
				odsNuovo.setDataValiditaInizio(DateUtility.stringToDate(flussoOdsEsterno.getDataInizioValidita(),"dd-MM-yyyy"));
			}else {
				numeroErrori ++ ;
				inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data inizio validità non corretta formato desiderato dd-MM-yyyy");
				return;
			}
		}else {
			numeroErrori ++ ;
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data inizio validità non corretta formato desiderato dd-MM-yyyy");
			return;
		}

		//DATA_VALIDITA_FINE
		if(StringUtility.isNotEmpty(flussoOdsEsterno.getDataFineValidita()) ) {
			if(DateUtility.stringToDate(flussoOdsEsterno.getDataFineValidita(),"dd-MM-yyyy")!=null) {
				odsNuovo.setDataValiditaFine(DateUtility.stringToDate(flussoOdsEsterno.getDataFineValidita(),"dd-MM-yyyy"));
			}else {
				numeroErrori ++ ;
				inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(),errorPrefix  + "  data fine validità non corretta formato desiderato dd-MM-yyyy");
				return;
			}
		}

		//DATA_CREAZIONE
		odsNuovo.setDataCreazione(new Date());
		//UTENTE_CREAZIONE
		odsNuovo.setUtenteCreazione(utenteConnesso.getCodiceFiscale());
		//DATA_MODIFICA
		odsNuovo.setDataModifica(new Date());
		//UTENTE_MODIFICA
		odsNuovo.setUtenteModifica(utenteConnesso.getCodiceFiscale());
		//QUANTITA_MAX_RICHIEDIBILE
		//GENERICO
		odsNuovo.setGenerico(Boolean.FALSE);
		//ENTE_ID
		odsNuovo.setEnte(ente);
		log.info(methodName , "inserimento ODS");
		commonDad.postOggettoSpesa(odsNuovo,ente);
		log.info(methodName , "STOP");
	}
}
