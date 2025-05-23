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

import com.opencsv.bean.CsvToBean;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento.PostUploadCsvService;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.custom.RegoleSmistamentoRmsCsvAdapter;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * ValidaSmistamentoRmsCsv varie
 */
public class ValidaSmistamentoRmsCsv {

	private  List<RegoleSmistamentoRms> listaRegoleSmistamentoRms;

	public ValidaSmistamentoRmsCsv() {
		listaRegoleSmistamentoRms = new ArrayList<>();
	}

	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());

	/**
	 *
	 * @param iterableCsvAdapter
	 * @param elaborazione
	 * @return
	 */
	public List<ApiError> validaRegoleSmistamentoRmsCsv(  CsvToBean<RegoleSmistamentoRmsCsvAdapter> iterableCsvAdapter
			,Elaborazione elaborazione
			,Ente ente
			,Map<String, Ods> cacheOds
			,Map<String, Cpv> cacheCpv
			,Map<String, Settore> cacheSettore
			,Map<String, Sezione> cacheSezione
			,Map<String, Magazzino> cacheMagazzino
			) {
		final List<ApiError> lista = new ArrayList<>();
		final List<ApiError> listaerrcell = new ArrayList<>();
		int posizioneRiga = 0;

		for (RegoleSmistamentoRmsCsvAdapter rsrc : iterableCsvAdapter) {
			posizioneRiga++;
			listaerrcell.addAll(controlloCelleRiga( elaborazione,rsrc,posizioneRiga,ente,cacheOds,cacheCpv,cacheSettore,cacheSezione,cacheMagazzino));
		}
		if(posizioneRiga == 0) {
			lista.add(MsgCpassPba.PBAACQE0041.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId()));
		}
		lista.addAll(listaerrcell);
		return lista;
	}

	private List<ApiError> controlloCelleRiga(Elaborazione elaborazione
			,RegoleSmistamentoRmsCsvAdapter regola
			,int posizioneRiga
			,Ente ente
			,Map<String, Ods> cacheOds
			,Map<String, Cpv> cacheCpv
			,Map<String, Settore> cacheSettore
			,Map<String, Sezione> cacheSezione
			,Map<String, Magazzino> cacheMagazzino
			) {
		List<ApiError> listaErroriRiga = new ArrayList<>();
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloValorizzazioniDellePrimeColonne(elaborazione, regola, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloValorizzazioniDelleUltimeColonne(elaborazione, regola, posizioneRiga));

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloOdsCodice(elaborazione, regola,cacheOds, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCpvCodice(elaborazione, regola,cacheCpv, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSettoreRichiedente(elaborazione, regola,cacheSettore, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloLivelloCpv(elaborazione, regola, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloRegolaCpv(elaborazione, regola, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloTuttaLaStruttura(elaborazione, regola, posizioneRiga));

		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloCentroAcquisto(elaborazione, regola,cacheSettore, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloSezioneAcquisto(elaborazione, regola,cacheSezione, posizioneRiga));
		listaErroriRiga = addErroreToListError (listaErroriRiga , controlloMagazzino(elaborazione, regola,cacheMagazzino, posizioneRiga));

		if(listaErroriRiga.size()==0) {
			inizializzaRegoleSmistamentoRmsCsv(regola, ente, cacheOds, cacheCpv, cacheSettore, cacheSezione, cacheMagazzino);
		}
		return listaErroriRiga;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloValorizzazioniDellePrimeColonne(Elaborazione elaborazione,RegoleSmistamentoRmsCsvAdapter regola, int posizioneRiga) {
		ApiError errore 	 = null;
		final String  odsCode 	 = regola.getOdsCodice()!=null ?  regola.getOdsCodice().toString().trim()  : "";
		final String  cpvCode 	 = regola.getCpvCodice()!=null ?  regola.getCpvCodice().toString().trim()  : "";
		final String  cpvLevel 	 = regola.getLivelloCpv()!=null ? regola.getLivelloCpv().toString().trim() : "";
		final String  cpvRegola 	 = regola.getRegolaCpv();

		//aggiunto odsCode != null per segnalazione sonarqube
		final boolean possibilita1 = isValorizzato(odsCode) && !odsCode.equals("ALL") &&  isNonValorizzato(cpvCode) && isNonValorizzato(cpvLevel) && isNonValorizzato(cpvRegola);
		final boolean possibilita2 = isValorizzato(odsCode) && odsCode.equals("ALL")  &&  isValorizzato(cpvCode)    && isNonValorizzato(cpvLevel) && isNonValorizzato(cpvRegola);
		final boolean possibilita3 = isValorizzato(odsCode) && odsCode.equals("ALL")  &&  isNonValorizzato(cpvCode) && isValorizzato(cpvLevel)    && isValorizzato(cpvRegola);

		if(!(possibilita1 || possibilita2 || possibilita3)) {
			log.error("controlloMotivazioneNonRiproposta", "la combinazione dei primi 4 campi non è ammessa ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "Csv Regole Smistamento", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "1-2-3-4", "descrizione", "la combinazione dei primi 4 campi non e' ammessa","valore","");
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloValorizzazioniDelleUltimeColonne(Elaborazione elaborazione,RegoleSmistamentoRmsCsvAdapter regola, int posizioneRiga) {
		ApiError errore = null;
		final String centroAcquisto = regola.getCentroAcquisto();
		final String sezione = regola.getSezioneAcquisto();
		final String magazzino = regola.getMagazzino();

		final boolean possibilita1 = isValorizzato(centroAcquisto)     &&  isNonValorizzato(sezione) && isNonValorizzato(magazzino);
		final boolean possibilita2 = isValorizzato(centroAcquisto)     &&  isValorizzato(sezione)    && isNonValorizzato(magazzino);
		final boolean possibilita3 = isNonValorizzato(centroAcquisto)  &&  isNonValorizzato(sezione) && isValorizzato(magazzino);

		if(!(possibilita1 || possibilita2 || possibilita3)) {
			log.error("controlloMotivazioneNonRiproposta", "la combinazione dei 3 campi di output non è ammessa ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "Csv Regole Smistamento", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7-8-9", "descrizione", "la combinazione dei 3 campi di output non è ammessa");
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cacheOds
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloOdsCodice(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Ods> cacheOds, int posizioneRiga) {
		ApiError errore = null;
		final String odsCodice = regola.getOdsCodice() == null ? "" :regola.getOdsCodice().toUpperCase().trim();

		if(!(isValorizzato(odsCodice) && (cacheOds.get(odsCodice.toUpperCase().trim())!=null || odsCodice.equals("ALL")))) {
			log.error("controlloOdsCodice", "codice ods non censito");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "1", "descrizione", "oggetto di spesa inesistente", "valore", odsCodice);
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cachecpv
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloCpvCodice(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Cpv> cachecpv, int posizioneRiga) {
		ApiError errore = null;
		final String cpvCodice = regola.getCpvCodice() == null ? "" :regola.getCpvCodice().toUpperCase().trim();
		if(isValorizzato(cpvCodice) ) {
			if(!(cpvCodice.equals("ALL") || cachecpv.get(cpvCodice.toUpperCase().trim())!=null)) {
				log.error("controlloCpvCodice", "codice cpv non censito");
				errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "2", "descrizione", "codice cpv inesistente", "valore", cpvCodice);
			}
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cacheSettore
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloSettoreRichiedente(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Settore> cacheSettore, int posizioneRiga) {
		ApiError errore = null;
		final String settoreRicCodice = regola.getSettoreRichiedente() == null ? "" :regola.getSettoreRichiedente().toUpperCase().trim();
		if(!(isValorizzato(settoreRicCodice) && (settoreRicCodice.equals("ALL") || cacheSettore.get(settoreRicCodice)!=null))) {
			log.error("controlloSettoreRichiedente", "settore richiedente inesistente");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "3", "descrizione", "settore richiedente inesistente", "valore", settoreRicCodice);
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloLivelloCpv(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,int posizioneRiga) {
		ApiError errore = null;
		final String livCpv = regola.getLivelloCpv();
		if(!(isNonValorizzato(livCpv) || (isNumber(livCpv) && (Integer.parseInt(livCpv) > 0) && (Integer.parseInt(livCpv)<5)))) {
			//if(!(isNonValorizzato(livCpv) && (livCpv > 0) && (livCpv<5))) {
			log.error("controlloSettoreRichiedente", "livello cpv se presente e compreso fra 1 e 4 ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "4", "descrizione", "livello_cpv non valido", "valore", livCpv);
		}
		return errore;
	}

	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloRegolaCpv(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,int posizioneRiga) {
		ApiError errore = null;
		Integer livCpv = null;
		final String livCpvStr = regola.getLivelloCpv();
		if(!(isNonValorizzato(livCpvStr) || (isNumber(livCpvStr) && (Integer.parseInt(livCpvStr) > 0) && (Integer.parseInt(livCpvStr)<5)))) {
			// baypasso il controllo dato che il livello è già stato classificato come errato
			return errore;
		}else {
			livCpv = isValorizzato(livCpvStr)? Integer.parseInt(livCpvStr): null;
		}

		final String  regCpv = regola.getRegolaCpv() == null ? "" : regola.getRegolaCpv().trim();
		final int     lenRegCpv = regCpv.length();


		if(livCpv!=null && isNonValorizzato(regCpv)) {
			log.error("controlloSettoreRichiedente", "Regola_cpv non valorizzata, in presenza di un livello_cpv ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione", "Regola_cpv non valorizzata, in presenza di un livello_cpv", "valore", regCpv);
		}
		if(livCpv==null && isValorizzato(regCpv)) {
			log.error("controlloSettoreRichiedente", "Regola_cpv non valorizzata, in presenza di un livello_cpv ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione", "livello cpv non valorizzato, in presenza di un regola cpv", "valore", livCpv);
		}
		if(livCpv!=null && isValorizzato(regCpv)) {
			if(!((livCpv== 1 && lenRegCpv==2)|| (livCpv== 2 && lenRegCpv==3)||(livCpv== 3 && lenRegCpv==4)||(livCpv== 4 && lenRegCpv==5))) {
				log.error("controlloSettoreRichiedente", "Lunghezza regola_cpv non coerente con livello_cpv");
				errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "5", "descrizione", "Lunghezza regola_cpv non coerente con livello_cpv", "valore", livCpv);
			}
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloTuttaLaStruttura(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,int posizioneRiga) {
		ApiError errore = null;
		final String tls = regola.getTuttaLaStruttura()!=null ? regola.getTuttaLaStruttura().trim() : "";
		if(!(tls.toUpperCase().equals("SI") || tls.toUpperCase().equals("NO"))) {
			log.error("controlloSettoreRichiedente", "valore di tutta_la_struttura non ammesso ");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "6", "descrizione", "valore di tutta_la_struttura non ammesso usare SI/NO", "valore", tls);
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cacheSettore
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloCentroAcquisto(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Settore> cacheSettore, int posizioneRiga) {
		ApiError errore = null;
		final String centroAcquisto = regola.getCentroAcquisto() == null ? "" :regola.getCentroAcquisto().toUpperCase().trim();
		if(isValorizzato(centroAcquisto) && cacheSettore.get(centroAcquisto)==null) {
			log.error("controlloCentroAcquisto", "Centro Acquisto inesistente");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "7", "descrizione", "Centro Acquisto inesistente", "valore", centroAcquisto);
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cacheSezione
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloSezioneAcquisto(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Sezione> cacheSezione, int posizioneRiga) {
		ApiError errore = null;
		final String sezione = regola.getSezioneAcquisto() == null ? "" :regola.getSezioneAcquisto().toUpperCase().trim();
		if(isValorizzato(sezione) && cacheSezione.get(sezione)==null) {
			log.error("controlloSezioneAcquisto", "SEZIONE ACQUISTO inesistente");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "8", "descrizione", "SEZIONE ACQUISTO inesistente", "valore", sezione);
		}
		return errore;
	}
	/**
	 *
	 * @param elaborazione
	 * @param regola
	 * @param cacheMagazzino
	 * @param posizioneRiga
	 * @return
	 */
	private ApiError controlloMagazzino(Elaborazione elaborazione, RegoleSmistamentoRmsCsvAdapter regola,Map<String, Magazzino> cacheMagazzino, int posizioneRiga) {
		ApiError errore = null;
		final String magazzino = regola.getMagazzino() == null ? "" :regola.getMagazzino().toUpperCase().trim();
		if(isValorizzato(magazzino) && cacheMagazzino.get(magazzino)==null) {
			log.error("controlloMagazzino", "magazzino inesistente");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(), "riga" , Integer.valueOf(posizioneRiga) ,"colonna", "9", "descrizione", "magazzino inesistente", "valore", magazzino);
		}
		return errore;
	}

	/**
	 *
	 * @param regola
	 * @param ente
	 * @param cacheOds
	 * @param cacheCpv
	 * @param cacheSettore
	 * @param cacheSezione
	 * @param cacheMagazzino
	 * @return RegoleSmistamentoRms
	 */
	private RegoleSmistamentoRms inizializzaRegoleSmistamentoRmsCsv(RegoleSmistamentoRmsCsvAdapter regola
			,Ente ente
			,Map<String, Ods> cacheOds
			,Map<String, Cpv> cacheCpv
			,Map<String, Settore> cacheSettore
			,Map<String, Sezione> cacheSezione
			,Map<String, Magazzino> cacheMagazzino) {
		final RegoleSmistamentoRms regoleSmistamentoRms = new RegoleSmistamentoRms();
		regoleSmistamentoRms.setOggettiSpesaCodice(regola.getOdsCodice().trim());
		regoleSmistamentoRms.setCpvCodice(regola.getCpvCodice().trim());

		final Integer livello = isValorizzato(regola.getLivelloCpv()) ? Integer.parseInt(regola.getLivelloCpv()) :null;
		regoleSmistamentoRms.setLivelloCpv(livello);

		regoleSmistamentoRms.setRegolaCpv(regola.getRegolaCpv()!=null ? regola.getRegolaCpv().trim() : "");
		regoleSmistamentoRms.setSettoreCodice(regola.getSettoreRichiedente()!=null ? regola.getSettoreRichiedente().trim() : "");
		regoleSmistamentoRms.setEnte(ente);

		regoleSmistamentoRms.setTuttaLaStruttura(false);
		if(regola.getTuttaLaStruttura()!=null && regola.getTuttaLaStruttura().trim().toUpperCase().equals("TRUE")){
			regoleSmistamentoRms.setTuttaLaStruttura(true);
		}


		if(isValorizzato(regola.getMagazzino())){
			final Magazzino mag = cacheMagazzino.get(regola.getMagazzino().trim());
			regoleSmistamentoRms.setMagazzino(mag);
			regoleSmistamentoRms.setMagazzinoCodice(mag.getCodice());
		}

		if(isValorizzato(regola.getSezioneAcquisto())){
			final Sezione sez = cacheSezione.get(regola.getSezioneAcquisto().trim());
			regoleSmistamentoRms.setSezione(sez);
			regoleSmistamentoRms.setSezioneAcquistoCodice(sez.getSezioneCodice());
		}

		if(isValorizzato(regola.getCentroAcquisto())){
			final Settore setAcquisto = cacheSettore.get(regola.getCentroAcquisto().trim());
			regoleSmistamentoRms.setSettoreAcquisto(setAcquisto);
			regoleSmistamentoRms.setCentroAcquistoCodice(setAcquisto.getCodice());
		}

		listaRegoleSmistamentoRms.add(regoleSmistamentoRms);
		return regoleSmistamentoRms;
	}

	private boolean isValorizzato(String campo) {
		return campo != null && !campo.trim().equals("");
	}

	private boolean isNonValorizzato(String campo) {
		return !isValorizzato(campo);
	}

	/**
	 *
	 * @param elaborazione
	 * @param importo
	 * @param posizioneRiga
	 * @param colonna
	 * @return
	 */
	public ApiError controlloBigDecimal(Elaborazione elaborazione, String importo, int posizioneRiga, int colonna) {
		ApiError errore = null;
		try {
			final BigDecimal bd = new BigDecimal(importo);
			assert bd != null;
		}catch(final Exception e) {
			log.error("controlloCelleRigaImporti", "importo non numerico");
			errore = MsgCpassRms.RMSRMSE0030.getError(PostUploadCsvService.GROUP_ELABORAZIONE_ID, elaborazione.getId(),"nome_file", "file importi", "riga" , Integer.valueOf(posizioneRiga) ,"colonna", Integer.valueOf(colonna), "descrizione","importo non numerico", "valore", importo);
		}
		return errore;
	}

	private List<ApiError> addErroreToListError(List<ApiError> listaErroriRiga, ApiError controllo) {
		if(controllo!=null) {
			listaErroriRiga.add(controllo);
		}
		return listaErroriRiga;
	}

	/**
	 * @return the listaRegoleSmistamentoRms
	 */
	public List<RegoleSmistamentoRms> getListaRegoleSmistamentoRms() {
		return listaRegoleSmistamentoRms;
	}

	/**
	 * @param listaRegoleSmistamentoRms the listaRegoleSmistamentoRms to set
	 */
	public void setListaRegoleSmistamentoRms(List<RegoleSmistamentoRms> listaRegoleSmistamentoRms) {
		this.listaRegoleSmistamentoRms = listaRegoleSmistamentoRms;
	}

	private boolean isNumber(String livCpv) {
		try {
			Integer.parseInt(livCpv);
		}catch(final Exception e){
			return false;
		}
		return true;
	}

}
