/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostUploadCsvService;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCsv;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImpCsv;
import it.csi.cpass.cpassbe.lib.dto.pba.ModalitaAffidamento;
import it.csi.cpass.cpassbe.lib.dto.pba.Nuts;
import it.csi.cpass.cpassbe.lib.dto.pba.Priorita;
import it.csi.cpass.cpassbe.lib.dto.pba.RicompresoTipo;
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * Validazioni varie
 */
public class ValidaInterventiCsv {
	
	private  List<InterventoCsv> listaInterventoCsv;
	private  List<InterventoImpCsv> listaInterventoImpCsv;
	/**
	 * 
	 */
	public ValidaInterventiCsv() {
		listaInterventoCsv = new ArrayList<>();
		listaInterventoImpCsv = new ArrayList<>();
	}

	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());

	/**
	 * Validazione interventi
	 * @param bufferedReader
	 * @param cacheAusa
	 * @param cacheCpv
	 * @param cacheAcquistoVariato
	 * @param cacheNuts
	 * @param cachePriorita
	 * @param cacheModalitaAffidamento
	 * @param cacheStato
	 * @param cacheRicompresoTipo
	 * @param cacheUtente
	 * @param cacheSettoreInterventi
	 * @param cfEnte
	 * @param interventoDad
	 * @param cacheInterventi
	 * @return the errors
	 */
	public List<ApiError> validaCsvInterventi(  BufferedReader bufferedReader
												,Elaborazione elaborazione
												,Map<String, Ausa> cacheAusa
												,Map<String, Cpv> cacheCpv
												,Map<String, AcquistoVariato> cacheAcquistoVariato
												,Map<String, Nuts> cacheNuts
												,Map<String, Priorita> cachePriorita
												,Map<String, ModalitaAffidamento> cacheModalitaAffidamento
												,Map<String, Stato> cacheStato		
												,Map<String, RicompresoTipo> cacheRicompresoTipo
												,Map<String, Utente> cacheUtente
												,Map<String, SettoreInterventi> cacheSettoreInterventi
												,String cfEnte
												,InterventoDad interventoDad
												,Map<String, String> cacheInterventi
												,Map<String, Settore> cacheSettore
	) {
		String methodName = "validaCsvInterventi";
		List<ApiError> lista = new ArrayList<>();
		String separatore= ",";
		String line = "";		
		try {
			boolean vuoto = true;
			
			List<ApiError> listaerrcell = new ArrayList<>();
			for (int posizioneRiga = 0; (line = bufferedReader.readLine()) != null; posizioneRiga++) {
				if (line.trim().equals("")) {
					continue;
				}
				
				// prima riga intestazione esco
				if (posizioneRiga > 0) {
					vuoto = false;
				}
				log.debug(methodName, line);		
				//String[] celledellaRiga = line.split(separatore);
				String[] celledellaRiga = mySplit(line, separatore);
				if(celledellaRiga.length !=19) {
					log.error(methodName, "numero celle non conformi num celle passate controllare il separatore che deve essere , " + celledellaRiga.length);
					lista.add(MsgCpassPba.PBAACQE0047.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "nome_file", "file interventi", "riga" , Integer.valueOf(posizioneRiga)));
					vuoto = false;
					break;
				}
				if(!"CUI".equalsIgnoreCase(celledellaRiga[0])) {
					listaerrcell.addAll(controlloCelleRiga(
							                         elaborazione 
													,celledellaRiga
													,posizioneRiga
													,cacheAusa
													,cacheCpv
													,cacheAcquistoVariato
													,cacheNuts
													,cachePriorita
													,cacheModalitaAffidamento
													,cacheStato	
													,cacheRicompresoTipo
													,cacheUtente
													,cacheSettoreInterventi
													,cacheInterventi
													,cfEnte
													,cacheSettore
			    		)
					);
				}
			}
			if(vuoto) {
				lista.add(MsgCpassPba.PBAACQE0041.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId()));
			}
			lista.addAll(listaerrcell);
		} catch (IOException e) {
			log.error(methodName, e);
		}
		return lista;
	}

	private String[] mySplit(String line, String separatore) {
		String[] celledellaRiga = line.split(separatore);
		String[] ris = new String[celledellaRiga.length];
				
		for(int i =0 ; i < celledellaRiga.length;i++) {
			ris[i] = celledellaRiga[i].replace("\"", "");
		}
		return ris;
	}

	private List<ApiError> controlloCelleRiga(  
			                                     Elaborazione elaborazione
												,String[] celledellaRiga
												,int posizioneRiga
												,Map<String, Ausa> cacheAusa
												,Map<String, Cpv> cacheCpv
												,Map<String, AcquistoVariato> cacheAcquistoVariato
												,Map<String, Nuts> cacheNuts
												,Map<String, Priorita> cachePriorita
												,Map<String, ModalitaAffidamento> cacheModalitaAffidamento
												,Map<String, Stato> cacheStato	
												,Map<String, RicompresoTipo> cacheRicompresoTipo	
												,Map<String, Utente> cacheUtente
												,Map<String, SettoreInterventi> cacheSettoreInterventi
												,Map<String, String> cacheInterventi
												,String cfEnte
												,Map<String, Settore> cacheSettore
											) {
		
		/*
		                                             elaborazione 
													,celledellaRiga
													,posizioneRiga
													,cacheAusa
													,cacheCpv
													,cacheAcquistoVariato
													,cacheNuts
													,cachePriorita
													,cacheModalitaAffidamento
													,cacheStato	
													,cacheRicompresoTipo
													,cacheUtente
													,cacheSettoreInterventi
													,cacheInterventi
													,cfEnte
													,cacheSettore
		 */
		
		List<ApiError> listaErroriRiga = new ArrayList<>();
		InterventoCsv intCsv = inizializzaInterventoCsv(celledellaRiga);		
		Validazioni val = new Validazioni();
		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCuiIntervalli(elaborazione, intCsv.getCui(),posizioneRiga, cfEnte,1, cacheInterventi));
		cacheInterventi.put(intCsv.getCui(), intCsv.getCui());
		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloAnnoAvvio(elaborazione, intCsv.getAnnoAvvio(),posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCup(elaborazione, intCsv.getCup(),posizioneRiga));			
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloAcquistoRicompreso(elaborazione, intCsv.getAcquistoRicompreso(),posizioneRiga, cacheRicompresoTipo));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloInterventoRicompresoCui(elaborazione, intCsv.getInterventoRicompresoCui(),posizioneRiga,intCsv.getAcquistoRicompreso(),cfEnte));			
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloLottoFunzionaleSINO(elaborazione, intCsv.getLottoFunzionaleSINO(),posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloDurataMesi(elaborazione, intCsv.getDurataMesi(),posizioneRiga));		
		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloNuovoAffidamentoSiNo(elaborazione, intCsv.getNuovoAffidamentoSiNo(),posizioneRiga));		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloAusaCodice(elaborazione, intCsv.getAusaCodice(),posizioneRiga,cacheAusa,intCsv.getModalitaAffidamentoCodice()));
		
		listaErroriRiga = addErroreToListError(listaErroriRiga, controlloAcquistoVariatoCodice(elaborazione, intCsv.getAcquistoVariatoCodice(), posizioneRiga, cacheAcquistoVariato));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloUtenteRupCf(elaborazione, intCsv.getUtenteRupCf(),posizioneRiga,val, cacheUtente));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloDescrizioneAcquisto(elaborazione, intCsv.getDescrizioneAcquisto(),posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSettoreInterventiFS(elaborazione, intCsv.getSettoreInterventiFS(),posizioneRiga, cacheSettoreInterventi));		

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCpvCodice(elaborazione, intCsv.getCpvCodice(),posizioneRiga,cacheCpv));			
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloNutsCodice(elaborazione, intCsv.getNutsCodice(),posizioneRiga,cacheNuts));		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloPrioritaCodice(elaborazione, intCsv.getPrioritaCodice(),posizioneRiga,cachePriorita));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloModalitaAffidamentoCodice(elaborazione, intCsv.getModalitaAffidamentoCodice(),posizioneRiga,cacheModalitaAffidamento));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloStatoCodice(elaborazione, intCsv.getStatoCodice(),posizioneRiga,cacheStato));
	
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSettoreCodice(elaborazione, intCsv.getSettoreCodice(),posizioneRiga,cacheSettore) );

		return listaErroriRiga;
	}

	private ApiError controlloSettoreCodice(Elaborazione elaborazione, String settoreCodice, int posizioneRiga, Map<String, Settore> cacheSettore) {
		ApiError errore = null;
		
		if(cacheSettore.get(settoreCodice.toUpperCase().trim())==null) {
			log.error("cacheSettore", "settore  codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "19", "descrizione","Modalita Affidamento codice non censito a sistema");
		}
		
		return errore;
	}

	
	
	
	
	private ApiError controlloStatoCodice(Elaborazione elaborazione, String statoCodice, int posizioneRiga, Map<String, Stato> cacheStato) {
		ApiError errore = null;

        Stato stato = cacheStato.get(statoCodice.toUpperCase().trim());
		if(stato==null) {
			log.error("controlloStatoCodice", "stato codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "18", "descrizione","stato codice non censito a sistema");
		}else {
			if(stato.getCodice().contentEquals(CpassStatiEnum.INT_BOZZA.getCostante())) {
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "18", "descrizione","stato BOZZA non Ammessa");				
			}
		}
		return errore;	
	}

	private ApiError controlloModalitaAffidamentoCodice(Elaborazione elaborazione, String modalitaAffidamentoCodice, int posizioneRiga,Map<String, ModalitaAffidamento> cacheModalitaAffidamento) {
		ApiError errore = null;
		if(cacheModalitaAffidamento.get(modalitaAffidamentoCodice.toUpperCase().trim())==null) {
			log.error("controlloModalitaAffidamentoCodice", "Modalita Affidamento codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "17", "descrizione","Modalita Affidamento codice non censito a sistema");
		}
		return errore;	
	}

	private ApiError controlloPrioritaCodice(Elaborazione elaborazione, String prioritaCodice, int posizioneRiga,Map<String, Priorita> cachePriorita) {
		ApiError errore = null;
		if(cachePriorita.get(prioritaCodice.toUpperCase().trim())==null) {
			log.error("controlloPrioritaCodice", "priorita codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "16", "descrizione","nuts codice non censito a sistema");
		}
		return errore;	
	}

	private ApiError controlloNutsCodice(Elaborazione elaborazione, String nutsCodice, int posizioneRiga, Map<String, Nuts> cacheNuts) {
		ApiError errore = null;
		if(cacheNuts.get(nutsCodice.toUpperCase().trim())==null) {
			log.error("controlloNutsCodice", "nuts codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "15", "descrizione","nuts codice non censito a sistema");
		}
		return errore;

	}

	private ApiError controlloCpvCodice(Elaborazione elaborazione, String cpvCodice, int posizioneRiga, Map<String, Cpv> cacheCpv) {
		ApiError errore = null;
		if(cacheCpv.get(cpvCodice.toUpperCase().trim())==null) {
			log.error("controlloCpvCodice", "cpv codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "14", "descrizione","cpv codice non censito a sistema");
		}
		return errore;
	}

	private ApiError controlloSettoreInterventiFS(Elaborazione elaborazione, String settoreInterventiFS, int posizioneRiga, Map<String, SettoreInterventi> cacheSettoreInterventi) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(settoreInterventiFS) && (settoreInterventiFS.contentEquals("S") || settoreInterventiFS.contentEquals("F")))) {
			log.error("controlloSettoreInterventiFS", "settore interventi non coerente (F,S) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "13", "descrizione","settore interventi non coerente (F,S) ");
		}
		return errore;
	}

	private ApiError controlloDescrizioneAcquisto(Elaborazione elaborazione, String descrizioneAcquisto, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(descrizioneAcquisto) && descrizioneAcquisto.length()<=500)){
			log.error("controlloDescrizioneAcquisto", "Descrizione Acquisto non puo'essere maggioe di 500 caratteri ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "12", "descrizione","Descrizione Acquisto non puo'essere maggioe di 500 caratteri ");
		}
		return errore;
	}

	private ApiError controlloUtenteRupCf(Elaborazione elaborazione, String utenteRupCf, int posizioneRiga, Validazioni val, Map<String, Utente> cacheUtente) {
		ApiError errore = null;
		if(!val.isValidCodiceFiscale(utenteRupCf)){
			log.error("controlloUtenteRupCf", "codice fiscale rup non valido ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "11", "descrizione","codice fiscale rup non valido ");
		} else {
			Utente utente = cacheUtente.get(utenteRupCf);
			if (utente == null) {
				log.error("controlloUtenteRupCf", "codice fiscale rup non presente ");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "11", "descrizione","codice fiscale rup non presente ");
			}
		}
		return errore;
	}

	private ApiError controlloAcquistoVariatoCodice(Elaborazione elaborazione, String acquistoVariatoCodice, int posizioneRiga,
			Map<String, AcquistoVariato> cacheAcquistoVariato) {
		ApiError errore = null;
		// bug 170 - Il campo "acquisto variato" non è obbligatorio.
		if (acquistoVariatoCodice != null && !acquistoVariatoCodice.trim().equals("")) {
			if (cacheAcquistoVariato.get(acquistoVariatoCodice.toUpperCase().trim()) == null) {
				log.error("controlloAcquistoVariatoCodice", "acquisto Variato Codice codice non censito a sistema");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga",
						Integer.valueOf(posizioneRiga), "colonna", "10", "descrizione", "acquisto Variato Codice codice non censito a sistema");
			}
		}
		return errore;
	}

	private ApiError controlloAusaCodice(Elaborazione elaborazione, String ausaCodice, int posizioneRiga, Map<String, Ausa> cacheAusa, String modalitaAffidamentoCodice) {
		ApiError errore = null;
		// solo se mod. affidamento (colonna 17) valorizzato con "D" delegato
		if(modalitaAffidamentoCodice!=null && modalitaAffidamentoCodice.toUpperCase().equals("D")) {
			String paddedAusaCodice = StringUtils.leftPad(StringUtils.trimToEmpty(ausaCodice).toUpperCase(), 10, '0');
			if(cacheAusa.get(paddedAusaCodice.toUpperCase().trim())==null) {
				log.error("controlloAusaCodice", "ausa codice non censito a sistema");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "9", "descrizione","ausa codice non censito a sistema");
			}			
		}else {
//			if(StringUtils.isBlank(ausaCodice)) {
//				log.error("controllo file Interventi", "ausa codice valorizzato con mod affidamento non delegato");
//				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "9", "descrizione","ausa codice valorizzato con mod affidamento non delegato");
//			}
		}
		return errore;
	}

	private ApiError controlloNuovoAffidamentoSiNo(Elaborazione elaborazione, String nuovoAffidamentoSiNo, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(nuovoAffidamentoSiNo) && (nuovoAffidamentoSiNo.contentEquals("SI") || nuovoAffidamentoSiNo.contentEquals("NO")))) {
			log.error("controlloNuovoAffidamentoSiNo", "nuovo affidamento non coerente (SI NO) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "8", "descrizione","nuovo affidamento non coerente (SI NO) ");
		}
		return errore;
	}

	private ApiError controlloDurataMesi(Elaborazione elaborazione, String durataMesi, int posizioneRiga) {
		ApiError errore = null;
		int mesi = 0;
		try {
			mesi = Integer.parseInt(durataMesi);
		} catch (Exception e) {
			log.error("controlloDurataMesi", "durata mesi non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7", "descrizione","durata mesi non numerico");
		}
		if (mesi <= 0) {
			log.error("controlloDurataMesi", "durata mesi deve essere maggioe di 0");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7", "descrizione", "durata mesi deve essere maggioe di 0");
		}
		return errore;
	}

	private ApiError controlloLottoFunzionaleSINO(Elaborazione elaborazione, String lottoFunzionaleSINO, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(lottoFunzionaleSINO) && (lottoFunzionaleSINO.contentEquals("SI") || lottoFunzionaleSINO.contentEquals("NO")))) {
			log.error("controlloLottoFunzionaleSINO", "lotto funzionale non coerente (SI NO) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "6", "descrizione","lotto funzionale non coerente (SI NO) ");
		}
		return errore;
	}

	private ApiError controlloInterventoRicompresoCui(Elaborazione elaborazione, String interventoRicompresoCui, int posizioneRiga , String acquistoRicompreso ,String cfEnte) {
		ApiError errore = null;
		/*Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma*/
		//S 80087670016 2019 00009 
		
		if(acquistoRicompreso!= null && acquistoRicompreso.toUpperCase().equals("2")){
			if(!(interventoRicompresoCui.length()==21)) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente" );	
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente");
			} else	if(!(interventoRicompresoCui.substring(0,1).equals("S") || interventoRicompresoCui.substring(0,1).equals("F") || interventoRicompresoCui.substring(0,1).equals("L"))) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente" );	
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente");
			} else if (!interventoRicompresoCui.substring(1,12).equals(cfEnte)){
				log.error("controlloInterventoRicompresoCui", "codice fiscale non coerente con quello dell'ente" );
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","codice fiscale non coerente con quello dell'ente");
			}
		}else {
			if(!StringUtils.isBlank(interventoRicompresoCui)) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente con il campo acquisto ricompreso" );
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente con il campo acquisto ricompreso");
			}
		}
		return errore;
	}

	private ApiError controlloAcquistoRicompreso(Elaborazione elaborazione, String acquistoRicompreso, int posizioneRiga, Map<String, RicompresoTipo> cacheRicompresoTipo) {		
		ApiError errore = null;
		if(cacheRicompresoTipo.get(acquistoRicompreso.toUpperCase().trim()) ==null) {
			log.error("controlloAcquistoRicompreso", "acquisto Ricompreso Codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "4", "descrizione","acquisto Ricompreso Codice non censito a sistema");
			         //MsgCpassPba.PBAACQE0047.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga)));
		}
		return errore;
	}

	private ApiError controlloCup(Elaborazione elaborazione, String cup, int posizioneRiga) {
		ApiError errore = null;
		if(!(StringUtils.isBlank(cup)  || cup.length()==15)) {
			log.error("controlloCup", " cup non coerente" );	
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "3", "descrizione"," cup non coerente");
		}		
		return errore;
	}

	private ApiError controlloCuiIntervalli(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna,Map<String, String> cacheInterventi) {
		ApiError errore = controlloCui(elaborazione, cui, posizioneRiga, cfEnte, colonna, cacheInterventi);
		if (errore == null) {
			// effettuo il controllo solo sul file degli interventi (non per ogni riga di quello degli importi)
//			Optional<Intervento> optional = interventoDad.findInterventoByCUI(cui, null);
//			if (optional != null && !optional.isEmpty()) {
//				log.error("controllo file Interventi", "Record gia' presente - cui: " + cui);
//				errore = MsgCpass.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", colonna);
//			} else 
			if (!cacheInterventi.isEmpty() && cacheInterventi.get(cui) != null) {
				log.error("controlloCuiIntervalli", "Intervento presente piu' volte nel file - cui: " + cui);
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Intervento presente piu' volte nel file - cui: " + cui);
			}
		}
		return errore;
	}
	
	private ApiError controlloCuiImporti(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna, Map<String, String> cacheInterventi) {
		ApiError errore = controlloCui(elaborazione, cui, posizioneRiga, cfEnte, colonna, cacheInterventi);
		if (errore == null) {
			// l'intervento deve essere presente sul file degli interventi
			if (cacheInterventi != null && cacheInterventi.get(cui) == null) {
				log.error("controlloCuiImporti", "Intervento non presente nel file degli interventi - cui: " + cui);
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importo", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Intervento non presente nel file degli interventi - cui: " + cui);
			}
		}
		return errore;
	}
	
	/**
	 * controlli sui CUI comuni ai due file in input
	 * @return whether the CUI is valid
	 */
	private ApiError controlloCui(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna, Map<String, String> cacheInterventi) {
		ApiError errore = null;
		/*Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma*/
		//S 80087670016 2019 00009
		if (cui.length() != 21) {
			log.error("controlloCui", "Cui non coerente");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Cui non coerente");
		} else if (!(cui.substring(0, 1).equals("S") || cui.substring(0, 1).equals("F"))) {
			log.error("controlloCui", " Cui non coerente");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Cui non coerente");
		} else if (!cui.substring(1, 12).equals(cfEnte)) {
			log.error("controlloCui", "codice fiscale non coerente con quello dell'ente");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","codice fiscale non coerente con quello dell'ente");
		}
		return errore;
	}

	private ApiError controlloAnnoAvvio(Elaborazione elaborazione, String annoAvvio, int posizioneRiga) {
		ApiError errore = null;
		
		if (annoAvvio.matches("-?\\d+(\\.\\d+)?")) {
			if(Integer.parseInt(annoAvvio) < 2017) {
				log.error("controlloAnnoAvvio", "anno incoerente < 2017");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "2", "descrizione","anno incoerente < 2017");
			}
		}else {
			log.error("controlloAnnoAvvio", "anno non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "2", "descrizione","anno non numerico");
		}
		return errore;
	}
	
	private InterventoCsv inizializzaInterventoCsv(String[] celledellaRiga) {
		InterventoCsv intCsv = new InterventoCsv();
		intCsv.setCui(celledellaRiga[0]);
		intCsv.setAnnoAvvio(celledellaRiga[1]);
		intCsv.setCup(celledellaRiga[2]);
		intCsv.setAcquistoRicompreso(celledellaRiga[3]);
		intCsv.setInterventoRicompresoCui(celledellaRiga[4]);
		intCsv.setLottoFunzionaleSINO(celledellaRiga[5]);
		intCsv.setDurataMesi(celledellaRiga[6]);
		intCsv.setNuovoAffidamentoSiNo(celledellaRiga[7]);
		intCsv.setAusaCodice(celledellaRiga[8]);
		intCsv.setAcquistoVariatoCodice(celledellaRiga[9]);
		intCsv.setUtenteRupCf(celledellaRiga[10]);
		intCsv.setDescrizioneAcquisto(celledellaRiga[11]);
		intCsv.setSettoreInterventiFS(celledellaRiga[12]);
		intCsv.setCpvCodice(celledellaRiga[13]);
		intCsv.setNutsCodice(celledellaRiga[14]);
		intCsv.setPrioritaCodice(celledellaRiga[15]);
		intCsv.setModalitaAffidamentoCodice(celledellaRiga[16]);
		intCsv.setStatoCodice(celledellaRiga[17]);
		intCsv.setSettoreCodice(celledellaRiga[18]);
		listaInterventoCsv.add(intCsv);
		return intCsv;
	}
	
	private List<ApiError> addErroreToListError(List<ApiError> listaErroriRiga, ApiError controllo) {
		if(controllo!=null) {
			listaErroriRiga.add(controllo);
		}
		return listaErroriRiga;
	}

	/**
	 * @return the listaInterventoCsv
	 */
	public List<InterventoCsv> getListaInterventoCsv() {
		return listaInterventoCsv;
	}

	/**
	 * @param listaInterventoCsv the listaInterventoCsv to set
	 */
	public void setListaInterventoCsv(List<InterventoCsv> listaInterventoCsv) {
		this.listaInterventoCsv = listaInterventoCsv;
	}

	//gestione importi
	/**
	 * Validazione CSV importi
	 * @param brInterventi
	 * @param cacheRisorsa
	 * @param cfEnte
	 * @param cacheInterventi
	 * @return the errors
	 */
	public List<ApiError> validaCsvInterventiImporti(BufferedReader brInterventi, Elaborazione elaborazione, Map<String, Risorsa> cacheRisorsa, String cfEnte, Map<String, String> cacheInterventi) {
		String methodName = "validaCsvInterventiImporti";
		List<ApiError> lista = new ArrayList<>();
		String separatore= ",";
		String line = "";		
		try {
			boolean vuoto = true;
			List<ApiError> listaerrcell = new ArrayList<>();
			for (int posizioneRiga = 0; (line = brInterventi.readLine()) != null; posizioneRiga++) {
				// prima riga intestazione esco
				if(posizioneRiga > 0) {vuoto = false;}
				log.debug(methodName, line);		
				//String[] celledellaRiga = line.split(separatore);
				String[] celledellaRiga = mySplit( line,  separatore) ;
				
				if(celledellaRiga.length !=6) {
					//lista.add(MsgCpass.PBAACQE0044.getError("numriga" , posizioneRiga ,"descrizione", "numero celle non conformi num celle passate " + celledellaRiga.length));
					log.error(methodName, "numero celle non conformi num celle passate " + celledellaRiga.length);
					lista.add(MsgCpassPba.PBAACQE0047.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga)));
					vuoto = false;
					break;
				}
				if(posizioneRiga>0) {
					listaerrcell.addAll(controlloCelleRigaImporti(elaborazione, celledellaRiga,posizioneRiga,cacheRisorsa,cfEnte, cacheInterventi));
				}
			}
			if(vuoto) {
				lista.add(MsgCpassPba.PBAACQE0041.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId()));
			}
			lista.addAll(listaerrcell);
		} catch (IOException e) {
			log.error(methodName, e);
		}
		return lista;
	}

	private List<ApiError> controlloCelleRigaImporti(Elaborazione elaborazione, String[] celledellaRiga, int posizioneRiga,Map<String, Risorsa> cacheRisorsa, String cfEnte, Map<String, String> cacheInterventi) {
		InterventoImpCsv intImpCsv = inizializzaInterventoImpotiCsv(celledellaRiga);	
		List<ApiError> listaErroriRiga = new ArrayList<>();
		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCuiImporti(elaborazione, intImpCsv.getCui(),posizioneRiga, cfEnte,1, cacheInterventi));	
		
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloRisorsa(elaborazione, cacheRisorsa,intImpCsv.getRisorsaCodice(),intImpCsv.getRisorsaTipo(),posizioneRiga,3));	

		if (!isValorizzato(intImpCsv.getImportoAnnoPrimo()) && !isValorizzato(intImpCsv.getImportoAnnoSecondo()) && !isValorizzato(intImpCsv.getImportoAnniSuccessivi())) {
			String msgError = "importi non valorizzati ";
			log.error("controlloCelleRigaImporti", msgError);
			
			ApiError errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga",Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(4), "descrizione", msgError);
			listaErroriRiga = addErroreToListError (listaErroriRiga , errore);

		} else {
			if (isValorizzato(intImpCsv.getImportoAnnoPrimo())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnnoPrimo(), posizioneRiga, 4));
			}
			if (isValorizzato(intImpCsv.getImportoAnnoSecondo())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnnoSecondo(), posizioneRiga, 5));
			}
			if (isValorizzato(intImpCsv.getImportoAnniSuccessivi())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnniSuccessivi(), posizioneRiga, 6));
			}
		}
		return listaErroriRiga;
	}
	
	private boolean isValorizzato(String campo) {
		return campo != null && !campo.trim().equals("");
	}

	private ApiError controlloRisorsa(Elaborazione  elaborazione, Map<String, Risorsa>cacheRisorsa, String risorsaCodice,String risorsaTipo, int posizioneRiga, int colonna) {
		ApiError errore = null;

		// tipo risorsa può valere solo "B" o "C"
		if (risorsaTipo == null || (!risorsaTipo.toUpperCase().trim().equals("B") && !risorsaTipo.toUpperCase().trim().equals("C"))) {
			String msgError = "tipo risorsa può valere solo 'B' o 'C'";
			log.error("controlloCelleRigaImporti", msgError);
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga", Integer.valueOf(posizioneRiga),
					"colonna", Integer.valueOf(colonna), "descrizione", msgError);
			
		} else if (cacheRisorsa.get(risorsaCodice + risorsaTipo.toUpperCase().trim()) == null) {
			log.error("controlloCelleRigaImporti", "risorsa non censita a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", Integer.valueOf(colonna), "descrizione","risorsa non censita a sistema");
		}
		
		return errore;
	}

	private ApiError controlloBigDecimal(Elaborazione elaborazione, String importo, int posizioneRiga, int colonna) {
		ApiError errore = null;
		try {
			BigDecimal bd = new BigDecimal(importo);
			assert bd != null;
		}catch(Exception e) {
			log.error("controlloCelleRigaImporti", "importo non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", Integer.valueOf(colonna), "descrizione","importo non numerico", "valore", importo);
		}
		return errore;
	}

	private InterventoImpCsv inizializzaInterventoImpotiCsv(String[] celledellaRiga) {
		InterventoImpCsv intCsv = new InterventoImpCsv();
		intCsv.setCui(celledellaRiga[0]);
		intCsv.setRisorsaTipo(celledellaRiga[1]);
		intCsv.setRisorsaCodice(celledellaRiga[2]);
		intCsv.setImportoAnnoPrimo(celledellaRiga[3]);
		intCsv.setImportoAnnoSecondo(celledellaRiga[4]);
		intCsv.setImportoAnniSuccessivi(celledellaRiga[5]);		
		listaInterventoImpCsv.add(intCsv);
		return intCsv;
	}

	/**
	 * @return the listaInterventoImpCsv
	 */
	public List<InterventoImpCsv> getListaInterventoImpCsv() {
		return listaInterventoImpCsv;
	}

	/**
	 * @param listaInterventoImpCsv the listaInterventoImpCsv to set
	 */
	public void setListaInterventoImpCsv(List<InterventoImpCsv> listaInterventoImpCsv) {
		this.listaInterventoImpCsv = listaInterventoImpCsv;
	}


	
	
	
}
