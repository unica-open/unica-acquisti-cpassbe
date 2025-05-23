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
package it.csi.cpass.cpassbe.ejb.validatori;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBean;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostUploadCsvService;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.custom.AcquistiCsvAdapter;
import it.csi.cpass.cpassbe.lib.dto.custom.AcquistiImportiCsvAdapter;
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
import it.csi.cpass.cpassbe.lib.dto.pba.TipoProceduraPba;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * Validazioni varie
 */
public class ValidaInterventiCsv {

	private  List<InterventoCsv> listaInterventoCsv;
	private  List<InterventoImpCsv> listaInterventoImpCsv;

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
	public List<ApiError> validaCsvInterventi(  CsvToBean<AcquistiCsvAdapter> iterableAcquistiCsvAdapter
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
			,Map<String, String>  cacheInterventiCui
			,Map<String, Settore> cacheSettore
			,Map<String, String>  cacheCodiceInterno
			,Map<String, TipoProceduraPba>  cacheTipoProceduraPba
			) {
		final List<ApiError> lista = new ArrayList<>();
		final List<ApiError> listaerrcell = new ArrayList<>();
		int posizioneRiga = 0;

		for (AcquistiCsvAdapter ia : iterableAcquistiCsvAdapter) {
			posizioneRiga++;
			if(ia.getCui().indexOf("CUI")<0) {
				//if(!"CUI".equalsIgnoreCase(ia.getCui())) {
				listaerrcell.addAll(controlloCelleRiga(
						elaborazione
						,ia
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
						,cacheInterventiCui
						,cfEnte
						,cacheSettore
						,cacheCodiceInterno
						,cacheTipoProceduraPba
						)
						);
			}
		}
		if(posizioneRiga == 0) {
			lista.add(MsgCpassPba.PBAACQE0041.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId()));
		}
		lista.addAll(listaerrcell);
		return lista;
	}

	private List<ApiError> controlloCelleRiga(
			Elaborazione elaborazione
			,AcquistiCsvAdapter ia
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
			,Map<String, String> cacheInterventiCui
			,String cfEnte
			,Map<String, Settore> cacheSettore
			,Map<String, String> cacheCodiceInterno
			,Map<String, TipoProceduraPba> cacheTipoProceduraPba
			) {

		List<ApiError> listaErroriRiga = new ArrayList<>();
		final InterventoCsv intCsv = inizializzaInterventoCsv(ia);
		final Validazioni val = new Validazioni();

		if(StringUtils.isNotBlank(intCsv.getCui()) ) {
			listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCuiIntervalli(elaborazione, intCsv.getCui(),posizioneRiga, cfEnte,1, cacheInterventiCui));
			if(posizioneRiga==1) {
				final String cuiBonificato = StringUtility.bonificaStringaDaCaratteriSpeciali(intCsv.getCui());
				cacheInterventiCui.put(cuiBonificato, cuiBonificato);
				intCsv.setCui(cuiBonificato);
			}else {
				cacheInterventiCui.put(intCsv.getCui().trim(), intCsv.getCui().trim());
			}
		}else {
			if(StringUtils.isNotBlank(intCsv.getCodiceInterno())) {
				cacheCodiceInterno.put(intCsv.getCodiceInterno().trim(), intCsv.getCodiceInterno().trim());
			}
		}

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

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSettoreInterventiFS(elaborazione, intCsv.getSettoreInterventiFS(),posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCpvCodice(elaborazione, intCsv.getCpvCodice(),posizioneRiga,cacheCpv,intCsv.getSettoreInterventiFS(),cacheSettoreInterventi));

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloNutsCodice(elaborazione, intCsv.getNutsCodice(),posizioneRiga,cacheNuts));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloPrioritaCodice(elaborazione, intCsv.getPrioritaCodice(),posizioneRiga,cachePriorita));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloModalitaAffidamentoCodice(elaborazione, intCsv.getModalitaAffidamentoCodice(),posizioneRiga,cacheModalitaAffidamento));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloStatoCodice(elaborazione, intCsv.getStatoCodice(),posizioneRiga,cacheStato));

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSettoreCodice(elaborazione, intCsv.getSettoreCodice(),posizioneRiga,cacheSettore) );

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCodiceInterno(elaborazione, intCsv.getCodiceInterno() ,posizioneRiga,cacheCodiceInterno) );
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloMotivazioneNonRiproposta(elaborazione, intCsv ,posizioneRiga) );
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloNote(elaborazione, intCsv.getNote() ,posizioneRiga) );
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloTipoProceduraCodice(elaborazione, intCsv.getTipoProceduraCodice() ,posizioneRiga, cacheTipoProceduraPba) );

		return listaErroriRiga;
	}

	private ApiError controlloTipoProceduraCodice(Elaborazione elaborazione, String tipoProceduraCodice, int posizioneRiga,Map<String, TipoProceduraPba> cacheTipoProcedura) {
		ApiError errore = null;
		tipoProceduraCodice = tipoProceduraCodice == null ? "" :tipoProceduraCodice.toUpperCase().trim();
		if( StringUtils.isNotEmpty(tipoProceduraCodice) && cacheTipoProcedura.get(tipoProceduraCodice)==null) {
			log.error("controlloTipoProceduraCodice", "Tipo procedura codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "23", "descrizione","Tipo procedura codice non censito a sistema","valore",tipoProceduraCodice);
		}
		return errore;
	}

	private ApiError controlloNote(Elaborazione elaborazione, String note,int posizioneRiga) {
		final ApiError errore = null;
		//log.info(methodName , "riga 21");
		return errore;
	}

	private ApiError controlloMotivazioneNonRiproposta(Elaborazione elaborazione, InterventoCsv intCsv,int posizioneRiga) {
		ApiError errore = null;
		final String motivazioneNonRiproposto = intCsv.getMotivazioneNonRiproposto() == null ? "" :intCsv.getMotivazioneNonRiproposto().trim();
		final String cui = intCsv.getCui() == null ? "" :intCsv.getCui().trim();

		if(StringUtils.isNotEmpty(motivazioneNonRiproposto) && StringUtils.isEmpty(cui)) {
			log.error("controlloMotivazioneNonRiproposta", "CUI non valorizzato su acquisto riproposto");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "21", "descrizione","CUI non valorizzato su acquisto riproposto");
		}

		if(StringUtils.isNotEmpty(motivazioneNonRiproposto) && StringUtils.isNotEmpty(intCsv.getAcquistoVariatoCodice())) {
			log.error("controlloMotivazioneNonRiproposta", "in caso di acquisto non riproposto Non posso avere Acquisto Variato valorizzato");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "21", "descrizione","Non posso avere Acquisto Variato valorizzato");
		}

		return errore;
	}

	private ApiError controlloCodiceInterno(Elaborazione elaborazione, String codiceInterno,int posizioneRiga, Map<String, String> cacheCodiceInterno) {
		final ApiError errore = null;
		//log.info(methodName , "riga 20");
		return errore;
	}

	private ApiError controlloSettoreCodice(Elaborazione elaborazione, String settoreCodice, int posizioneRiga, Map<String, Settore> cacheSettore) {
		ApiError errore = null;
		settoreCodice = settoreCodice ==null ? " " :settoreCodice.toUpperCase().trim();
		if(settoreCodice==null || cacheSettore.get(settoreCodice)==null) {
			log.error("controlloSettoreCodice", "settore  codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "19", "descrizione","Settore codice non censito a sistema","valore",settoreCodice);
		}
		return errore;
	}

	private ApiError controlloStatoCodice(Elaborazione elaborazione, String statoCodice, int posizioneRiga, Map<String, Stato> cacheStato) {
		ApiError errore = null;
		statoCodice = statoCodice == null ? " " :statoCodice.toUpperCase().trim();
		final Stato stato = cacheStato.get(statoCodice);
		if(stato==null) {
			log.error("controlloStatoCodice", "stato codice non censito a sistema " + stato);
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "18", "descrizione","stato codice non censito a sistema","valore","Null");
		}
		/*
		else {
			if(stato.getCodice().contentEquals(CpassStatiEnum.INT_BOZZA.getCostante())) {
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "18", "descrizione","stato BOZZA non Ammessa", "valore",stato);
			}
		}
		 */
		return errore;
	}

	private ApiError controlloModalitaAffidamentoCodice(Elaborazione elaborazione, String modalitaAffidamentoCodice, int posizioneRiga,Map<String, ModalitaAffidamento> cacheModalitaAffidamento) {
		ApiError errore = null;
		modalitaAffidamentoCodice = modalitaAffidamentoCodice == null ? " " :modalitaAffidamentoCodice.toUpperCase().trim();
		if(cacheModalitaAffidamento.get(modalitaAffidamentoCodice)==null) {
			log.error("controlloModalitaAffidamentoCodice", "Modalita Affidamento codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "17", "descrizione","Modalita Affidamento codice non censito a sistema","valore",modalitaAffidamentoCodice);
		}
		return errore;
	}

	private ApiError controlloPrioritaCodice(Elaborazione elaborazione, String prioritaCodice, int posizioneRiga,Map<String, Priorita> cachePriorita) {
		ApiError errore = null;
		if(cachePriorita.get(prioritaCodice.toUpperCase().trim())==null) {
			log.error("controlloPrioritaCodice", "priorita codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "16", "descrizione","nuts codice non censito a sistema","valore",prioritaCodice);
		}
		return errore;
	}

	private ApiError controlloNutsCodice(Elaborazione elaborazione, String nutsCodice, int posizioneRiga, Map<String, Nuts> cacheNuts) {
		ApiError errore = null;
		nutsCodice = nutsCodice == null ? " " :nutsCodice.toUpperCase().trim();
		if(cacheNuts.get(nutsCodice)==null) {
			log.error("controlloNutsCodice", "nuts codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "15", "descrizione","nuts codice non censito a sistema","valore",nutsCodice);
		}
		return errore;

	}

	private ApiError controlloCpvCodice(Elaborazione elaborazione, String cpvCodice, int posizioneRiga, Map<String, Cpv> cacheCpv,String settoreInterventiFS,Map<String, SettoreInterventi> cacheSettoreInterventi) {
		ApiError errore = null;
		cpvCodice = cpvCodice == null ? " " :cpvCodice.toUpperCase().trim();
		if(cacheCpv.get(cpvCodice.toUpperCase().trim())==null) {
			log.error("controlloCpvCodice", "cpv codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "14", "descrizione","cpv codice non censito a sistema","valore",cpvCodice);
		}else {
			final Cpv cpv = cacheCpv.get(cpvCodice.toUpperCase().trim());
			if(cpv.getSettoreInterventi() != null && cpv.getSettoreInterventi().getId()!=null) {
				final Integer settoreInterventoIdDelCpv = cpv.getSettoreInterventi().getId();
				if((StringUtils.isNotBlank(settoreInterventiFS) && (settoreInterventiFS.contentEquals("S") || settoreInterventiFS.contentEquals("F")))) {
					final SettoreInterventi settoreInterventi = cacheSettoreInterventi.get(settoreInterventiFS.toUpperCase().trim());
					//if(settoreInterventi.getId() != settoreInterventoIdDelCpv) {
					if (!settoreInterventi.getId().equals(settoreInterventoIdDelCpv)) {
						log.error("controlloCpvCodice", "CPV non coerente con il tipo settore");
						errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "14", "descrizione","CPV non coerente con il tipo settore ","valore",settoreInterventiFS);
					}
				}
			}else {
				log.error("controlloCpvCodice", "CPV non coerente non presenta il Settore Interventi ");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "14", "descrizione","CPV non coerente non presenta Settore Interventi  ","valore","");
			}


		}
		return errore;
	}

	private ApiError controlloSettoreInterventiFS(Elaborazione elaborazione, String settoreInterventiFS, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(settoreInterventiFS) && (settoreInterventiFS.contentEquals("S") || settoreInterventiFS.contentEquals("F")))) {
			log.error("controlloSettoreInterventiFS", "settore interventi non coerente (F,S) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "13", "descrizione","settore interventi non coerente (F,S) ","valore",settoreInterventiFS);
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
		utenteRupCf = utenteRupCf == null ? " " :utenteRupCf.toUpperCase().trim();
		if(!val.isValidCodiceFiscale(utenteRupCf)){
			log.error("controlloUtenteRupCf", "codice fiscale rup non valido ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "11", "descrizione","codice fiscale rup non valido ","valore",utenteRupCf);
		} else {
			final Utente utente = cacheUtente.get(utenteRupCf);
			if (utente == null) {
				log.error("controlloUtenteRupCf", "codice fiscale rup non presente ");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "11", "descrizione","codice fiscale rup non presente ","valore",utenteRupCf);
			}
		}
		return errore;
	}

	private ApiError controlloAcquistoVariatoCodice(Elaborazione elaborazione, String acquistoVariatoCodice, int posizioneRiga,Map<String, AcquistoVariato> cacheAcquistoVariato) {
		ApiError errore = null;
		acquistoVariatoCodice = acquistoVariatoCodice == null ? "" :acquistoVariatoCodice.toUpperCase().trim();
		// bug 170 - Il campo "acquisto variato" non è obbligatorio.
		if (acquistoVariatoCodice != null && !acquistoVariatoCodice.equals("")) {
			if (cacheAcquistoVariato.get(acquistoVariatoCodice.toUpperCase().trim()) == null) {
				log.error("controlloAcquistoVariatoCodice", "acquisto Variato Codice codice non censito a sistema");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga",
						Integer.valueOf(posizioneRiga), "colonna", "10", "descrizione", "acquisto Variato Codice codice non censito a sistema","valore",acquistoVariatoCodice);
			}
		}
		return errore;
	}

	private ApiError controlloAusaCodice(Elaborazione elaborazione, String ausaCodice, int posizioneRiga, Map<String, Ausa> cacheAusa, String modalitaAffidamentoCodice) {
		ApiError errore = null;
		// solo se mod. affidamento (colonna 17) valorizzato con "D" delegato
		if(modalitaAffidamentoCodice!=null && modalitaAffidamentoCodice.toUpperCase().trim().equals("D")) {
			final String paddedAusaCodice = StringUtils.leftPad(StringUtils.trimToEmpty(ausaCodice).toUpperCase(), 10, '0');
			if(cacheAusa.get(paddedAusaCodice.toUpperCase().trim())==null) {
				log.error("controlloAusaCodice", "ausa codice non censito a sistema");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "9", "descrizione","ausa codice non censito a sistema","valore",paddedAusaCodice);
			}
		}
		return errore;
	}

	private ApiError controlloNuovoAffidamentoSiNo(Elaborazione elaborazione, String nuovoAffidamentoSiNo, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(nuovoAffidamentoSiNo) && (nuovoAffidamentoSiNo.contentEquals("SI") || nuovoAffidamentoSiNo.contentEquals("NO")))) {
			log.error("controlloNuovoAffidamentoSiNo", "nuovo affidamento non coerente (SI NO) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "8", "descrizione","nuovo affidamento non coerente (SI NO) ","valore",nuovoAffidamentoSiNo);
		}
		return errore;
	}

	private ApiError controlloDurataMesi(Elaborazione elaborazione, String durataMesi, int posizioneRiga) {
		ApiError errore = null;
		int mesi = 0;
		try {
			durataMesi = durataMesi == null ? "" :durataMesi.trim();
			mesi = Integer.parseInt(durataMesi);
		} catch (final Exception e) {
			log.error("controlloDurataMesi", "durata mesi non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7", "descrizione","durata mesi non numerico","valore",mesi);
		}
		if (mesi < 0) {
			log.error("controlloDurataMesi", "durata mesi deve essere maggioe o uguale a 0");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7", "descrizione", "durata mesi deve essere maggioe di 0","valore",mesi);
		}
		return errore;
	}

	private ApiError controlloLottoFunzionaleSINO(Elaborazione elaborazione, String lottoFunzionaleSINO, int posizioneRiga) {
		ApiError errore = null;
		if(!(!StringUtils.isBlank(lottoFunzionaleSINO) && (lottoFunzionaleSINO.contentEquals("SI") || lottoFunzionaleSINO.contentEquals("NO")))) {
			log.error("controlloLottoFunzionaleSINO", "lotto funzionale non coerente (SI NO) ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "6", "descrizione","lotto funzionale non coerente (SI NO) ","valore",lottoFunzionaleSINO);
		}
		return errore;
	}

	private ApiError controlloInterventoRicompresoCui(Elaborazione elaborazione, String interventoRicompresoCui, int posizioneRiga , String acquistoRicompreso ,String cfEnte) {
		ApiError errore = null;
		/*Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma*/
		//S 80087670016 2019 00009
		if(acquistoRicompreso!= null && acquistoRicompreso.toUpperCase().equals("2")){
			if(!(interventoRicompresoCui.trim().length()==21)) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente" );
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente","valore",interventoRicompresoCui);
			} else	if(!(interventoRicompresoCui.substring(0,1).equals("S") || interventoRicompresoCui.substring(0,1).equals("F") || interventoRicompresoCui.substring(0,1).equals("L"))) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente" );
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente","valore",interventoRicompresoCui);
			} else if (!interventoRicompresoCui.substring(1,12).equals(cfEnte)){
				log.error("controlloInterventoRicompresoCui", "codice fiscale non coerente con quello dell'ente" );
				//TODO da scommentare
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","codice fiscale non coerente con quello dell'ente","valore",interventoRicompresoCui);
			}
		}else {
			if(!StringUtils.isBlank(interventoRicompresoCui)) {
				log.error("controlloInterventoRicompresoCui", "Intervento Ricompreso Cui non coerente con il campo acquisto ricompreso" );
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione","Intervento Ricompreso Cui non coerente con il campo acquisto ricompreso","valore",interventoRicompresoCui);
			}
		}
		return errore;
	}

	private ApiError controlloAcquistoRicompreso(Elaborazione elaborazione, String acquistoRicompreso, int posizioneRiga, Map<String, RicompresoTipo> cacheRicompresoTipo) {
		ApiError errore = null;
		acquistoRicompreso = acquistoRicompreso == null ? " " :acquistoRicompreso.trim();
		if(cacheRicompresoTipo.get(acquistoRicompreso) ==null) {
			log.error("controlloAcquistoRicompreso", "acquisto Ricompreso Codice non censito a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "4", "descrizione","acquisto Ricompreso Codice non censito a sistema","valore",acquistoRicompreso);
		}
		return errore;
	}

	private ApiError controlloCup(Elaborazione elaborazione, String cup, int posizioneRiga) {
		ApiError errore = null;
		if(!(StringUtils.isBlank(cup)  || cup.trim().length()==15)) {
			log.error("controlloCup", " cup non coerente" );
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "3", "descrizione"," cup non coerente","valore",cup);
		}
		return errore;
	}

	private ApiError controlloCuiIntervalli(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna,Map<String, String> cacheInterventiCui) {
		cui = cui == null ? " " :cui.trim();
		ApiError errore = controlloCui(elaborazione, cui, posizioneRiga, cfEnte, colonna,"file Acquisto");
		if (errore == null) {
			// effettuo il controllo solo sul file degli interventi (non per ogni riga di quello degli importi)
			if (!cacheInterventiCui.isEmpty() && cacheInterventiCui.get(cui) != null) {
				log.error("controlloCuiIntervalli", "Intervento presente piu' volte nel file - cui: " + cui);
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Intervento presente piu' volte nel file - cui: " + cui);
			}
		}
		return errore;
	}

	private ApiError controlloCuiImporti(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna, Map<String, String> cacheInterventi) {
		cui = cui == null ? " " :cui.trim();
		ApiError errore = controlloCui(elaborazione, cui, posizioneRiga, cfEnte, colonna,"file importo");
		if (errore == null) {
			// l'intervento deve essere presente sul file degli interventi
			if (cui != null && cacheInterventi != null && cacheInterventi.get(cui) == null) {
				log.error("controlloCuiImporti", "il cui citato nel file importi non trova corrispondenza nel file interventi - cui: " + cui);
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importo", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","il cui citato nel file importi non trova corrispondenza nel file interventi - cui: ","valore",cui);
			}
		}
		return errore;
	}

	private ApiError controlloCodiceInterno(Elaborazione elaborazione, String codiceInterno, int posizioneRiga,String cfEnte, int colonna, Map<String, String> cacheCodiceInterno) {
		codiceInterno = codiceInterno == null ? " " :codiceInterno.trim();
		ApiError errore = null;
		// l'intervento deve essere presente sul file degli interventi
		if (codiceInterno!= null && cacheCodiceInterno != null && cacheCodiceInterno.get(codiceInterno.trim()) == null) {
			log.error("cacheCodiceInterno", "codice interno non presente nel file degli interventi - codiceInterno: " + codiceInterno);
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importo", "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Intervento non presente nel file degli interventi - codiceInterno: " + codiceInterno,"valore",codiceInterno);
		}
		return errore;
	}

	/**
	 * controlli sui CUI comuni ai due file in input
	 * @return whether the CUI is valid
	 */
	private ApiError controlloCui(Elaborazione elaborazione, String cui, int posizioneRiga, String cfEnte, int colonna,String nomeFile) {
		ApiError errore = null;
		/*Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma*/
		//S 80087670016 2019 00009
		// controllo che non abbia caratteri speciali nel caso li bonifico
		if(posizioneRiga<=1 && StringUtility.hasSpecialChar(cui)) {
			cui = StringUtility.bonificaStringaDaCaratteriSpeciali(cui);
		}
		if (cui.trim().length() != 21) {
			log.error("controlloCui", "Cui non coerente");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", nomeFile, "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Cui non coerente lunghezza prevista 21 "+ cui +" cui len "+ cui.length(),"valore",cui.length());
		} else if (!(cui.substring(0, 1).equals("S") || cui.substring(0, 1).equals("F"))) {
			log.error("controlloCui", " Cui non coerente prima lettera F o S ");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", nomeFile, "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","Cui non coerente prima lettera F o S","valore",cui);
		} else if (!cui.substring(1, 12).equals(cfEnte)) {
			log.error("controlloCui", "codice fiscale non coerente con quello dell'ente");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", nomeFile, "riga", Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(colonna), "descrizione","codice fiscale non coerente con quello dell'ente cf-> " +cui.substring(1, 12));
		}
		return errore;
	}

	private ApiError controlloAnnoAvvio(Elaborazione elaborazione, String annoAvvio, int posizioneRiga) {
		ApiError errore = null;

		if (annoAvvio.matches("-?\\d+(\\.\\d+)?")) {
			if(Integer.parseInt(annoAvvio) < 2017) {
				log.error("controlloAnnoAvvio", "anno incoerente < 2017");
				errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "2", "descrizione","anno incoerente < 2017","valore",annoAvvio);
			}
		}else {
			log.error("controlloAnnoAvvio", "anno non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file Acquisto", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "2", "descrizione","anno non numerico","valore",annoAvvio);
		}
		return errore;
	}

	private InterventoCsv inizializzaInterventoCsv(AcquistiCsvAdapter ia) {
		final InterventoCsv intCsv = new InterventoCsv();
		intCsv.setCui(ia.getCui());
		intCsv.setAnnoAvvio(ia.getAnnoAvvioString());
		intCsv.setCup(ia.getCup());
		intCsv.setAcquistoRicompreso(ia.getAcquistoRicompreso());
		intCsv.setInterventoRicompresoCui(ia.getCuiInterventoRicompreso());
		intCsv.setLottoFunzionaleSINO(ia.getLottoFunzionaleString());
		intCsv.setDurataMesi(ia.getDurataMesiString());
		intCsv.setNuovoAffidamentoSiNo(ia.getNuovoAffidamentoString());
		intCsv.setAusaCodice(ia.getCodiceAusa());
		intCsv.setAcquistoVariatoCodice(ia.getAcquistoVariato());
		intCsv.setUtenteRupCf(ia.getCodiceFiscaleRup());
		intCsv.setDescrizioneAcquisto(ia.getDescrizioneAcquisto());
		intCsv.setSettoreInterventiFS(ia.getSettoreInterventi());
		intCsv.setCpvCodice(ia.getCpv());
		intCsv.setNutsCodice(ia.getNuts());
		intCsv.setPrioritaCodice(ia.getPriorita());
		intCsv.setModalitaAffidamentoCodice(ia.getModalitaAffidamento());
		intCsv.setStatoCodice(ia.getStato());
		intCsv.setSettoreCodice(ia.getSettore());
		intCsv.setCodiceInterno(ia.getCodiceInterno());
		intCsv.setFlagCuiNonGenerato(true);
		if(StringUtils.isEmpty(ia.getCui())) {
			intCsv.setFlagCuiNonGenerato(false);
		}

		intCsv.setNote(ia.getNote()!=null ? ia.getNote().trim() : "");
		intCsv.setMotivazioneNonRiproposto(ia.getMotivazioneNonRiproposto()!=null ? ia.getMotivazioneNonRiproposto().trim() : "");
		intCsv.setTipoProceduraCodice(ia.getTipoProcedura()!=null ? ia.getTipoProcedura().trim() : "");


		listaInterventoCsv.add(intCsv);
		return intCsv;
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
	public List<ApiError> validaCsvInterventiImporti(CsvToBean<AcquistiImportiCsvAdapter> iterableImportiAcquistiCsvAdapter, Elaborazione elaborazione, Map<String, Risorsa> cacheRisorsa, String cfEnte, Map<String, String> cacheInterventiCui, Map<String, String> cacheCodiceInterno) {
		final List<ApiError> lista = new ArrayList<>();
		final List<ApiError> listaerrcell = new ArrayList<>();

		int posizioneRiga = 0;
		for (AcquistiImportiCsvAdapter ia : iterableImportiAcquistiCsvAdapter) {
			//log.debug(methodName, line);
			posizioneRiga++;
			listaerrcell.addAll(controlloCelleRigaImporti(elaborazione, ia, posizioneRiga, cacheRisorsa, cfEnte, cacheInterventiCui, cacheCodiceInterno));
		}

		if(posizioneRiga == 0) {
			lista.add(MsgCpassPba.PBAACQE0041.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId()));
		}
		lista.addAll(listaerrcell);
		return lista;
	}

	private List<ApiError> controlloCelleRigaImporti(Elaborazione elaborazione, AcquistiImportiCsvAdapter ia, int posizioneRiga,Map<String, Risorsa> cacheRisorsa, String cfEnte, Map<String, String> cacheInterventiCui, Map<String, String> cacheCodiceInterno) {
		final InterventoImpCsv intImpCsv = inizializzaInterventoImpotiCsv(ia);
		List<ApiError> listaErroriRiga = new ArrayList<>();
		// lo controllo se valorizzato diversamente controllo il codice-i nterno
		if(StringUtils.isNotEmpty(intImpCsv.getCui())) {
			if(posizioneRiga==1) {
				intImpCsv.setCui(StringUtility.bonificaStringaDaCaratteriSpeciali(intImpCsv.getCui()));
			}
			listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCuiImporti(elaborazione, intImpCsv.getCui(),posizioneRiga, cfEnte,1, cacheInterventiCui));
		}else {
			listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCodiceInterno(elaborazione, intImpCsv.getCodiceInterno(),posizioneRiga, cfEnte,1, cacheCodiceInterno));
		}

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloRisorsa(elaborazione, cacheRisorsa,intImpCsv.getRisorsaCodice(),intImpCsv.getRisorsaTipo(),posizioneRiga));
		if (!isValorizzato(intImpCsv.getImportoAnnoPrimo()) && !isValorizzato(intImpCsv.getImportoAnnoSecondo()) && !isValorizzato(intImpCsv.getImportoAnnoTerzo()) && !isValorizzato(intImpCsv.getImportoAnniSuccessivi())) {
			final String msgError = "importi non valorizzati ";
			log.error("controlloCelleRigaImporti", msgError);
			final ApiError errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga",Integer.valueOf(posizioneRiga), "colonna", Integer.valueOf(3), "descrizione", msgError);
			listaErroriRiga = addErroreToListError (listaErroriRiga , errore);
		} else {
			if (isValorizzato(intImpCsv.getImportoAnnoPrimo())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnnoPrimo(), posizioneRiga, 4));
			}
			if (isValorizzato(intImpCsv.getImportoAnnoSecondo())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnnoSecondo(), posizioneRiga, 5));
			}
			if (isValorizzato(intImpCsv.getImportoAnnoTerzo())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnnoTerzo(), posizioneRiga, 6));
			}
			if (isValorizzato(intImpCsv.getImportoAnniSuccessivi())) {
				listaErroriRiga = addErroreToListError(listaErroriRiga,controlloBigDecimal(elaborazione, intImpCsv.getImportoAnniSuccessivi(), posizioneRiga, 7));
			}
		}
		return listaErroriRiga;
	}


	private boolean isValorizzato(String campo) {
		return campo != null && !campo.trim().equals("");
	}

	private ApiError controlloRisorsa(Elaborazione  elaborazione, Map<String, Risorsa>cacheRisorsa, String risorsaCodice,String risorsaTipo, int posizioneRiga) {
		ApiError errore = null;
		// tipo risorsa può valere solo "B" o "C"
		if (risorsaTipo == null || (!risorsaTipo.toUpperCase().trim().equals("B") && !risorsaTipo.toUpperCase().trim().equals("C"))) {
			final String msgError = "tipo risorsa può valere solo 'B' o 'C'";
			log.error("controlloCelleRigaImporti", msgError);
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga", Integer.valueOf(posizioneRiga),"colonna", "2", "descrizione", msgError,"valore",risorsaTipo);

		} else if (cacheRisorsa.get(risorsaCodice + risorsaTipo.toUpperCase().trim()) == null) {
			log.error("controlloCelleRigaImporti", "risorsa non censita a sistema");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "3", "descrizione","risorsa non censita a sistema","valore",risorsaCodice);
		}
		return errore;
	}

	private ApiError controlloBigDecimal(Elaborazione elaborazione, String importo, int posizioneRiga, int colonna) {
		ApiError errore = null;
		try {
			final BigDecimal bd = new BigDecimal(importo);
			assert bd != null;
		}catch(final Exception e) {
			log.error("controlloCelleRigaImporti", "importo non numerico");
			errore = MsgCpassPba.PBAACQE0045.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", Integer.valueOf(colonna), "descrizione","importo non numerico", "valore", importo);
		}
		return errore;
	}

	private InterventoImpCsv inizializzaInterventoImpotiCsv(AcquistiImportiCsvAdapter ia) {
		final InterventoImpCsv intCsv = new InterventoImpCsv();
		intCsv.setCui(ia.getCui());
		intCsv.setRisorsaTipo(ia.getTipoRisorsa());
		intCsv.setRisorsaCodice(ia.getCodiceRisorsa());
		intCsv.setImportoAnnoPrimo(ia.getImportoAnnoString() !=null ? StringUtility.replace(ia.getImportoAnnoString(),",",".") :"0");
		intCsv.setImportoAnnoSecondo(ia.getImportoAnnoPiu1String() !=null ? StringUtility.replace(ia.getImportoAnnoPiu1String(),",",".") :"0");
		intCsv.setImportoAnnoTerzo(ia.getImportoAnnoPiu2String() !=null ? StringUtility.replace(ia.getImportoAnnoPiu2String(),",",".") :"0");
		intCsv.setImportoAnniSuccessivi(ia.getImportoAnniSuccessiviString() !=null ? StringUtility.replace(ia.getImportoAnniSuccessiviString(),",",".") :"0");
		intCsv.setCodiceInterno(ia.getCodiceInterno());
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

	public static void main(String[] args) {
		//String cui = "F01995120019202000004";
		//System.out.println(hasSpecialChar (cui));
	}



	/*
	private String replace(String importoAnnoString, String oldChar, String newChar) {
		String ris = importoAnnoString.replace(oldChar, newChar);
		return ris;
	}
	 */

}
