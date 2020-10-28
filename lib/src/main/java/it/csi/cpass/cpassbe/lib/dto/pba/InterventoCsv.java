/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class Intervento.
 */
public class InterventoCsv extends BaseDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cui. */
	private String cui;
	/** The anno avvio. */
	private String annoAvvio;
	/** The cup. */
	private String cup;
	/** The ricompreso. */
	//private RicompresoTipo ricompresoTipo;  ()  
	private String acquistoRicompreso;//Codice  

	/** The intervento ricompreso. */
	//private Intervento interventoRicompreso;
	private String interventoRicompresoCui;
	
	/** The lotto funzionale. */
	//private Boolean lottoFunzionale;
	private String lottoFunzionaleSINO;

	/** The durata mesi. */
	private String durataMesi;	
	
	/** The nuovo affidamento. */
	//private Boolean nuovoAffidamento;
	private String nuovoAffidamentoSiNo;
	
	/** The ausa. */
	//private Ausa ausa;
	private String ausaCodice;
	
	/** The acquistoVariato. */
	//private AcquistoVariato acquistoVariato;
	private String acquistoVariatoCodice;//tab b2
	
	/** The utente rup. */
	//private Utente utenteRup;
	private String utenteRupCf;
	
	/** The descrizione acquisto. */
	private String descrizioneAcquisto;	

	/** The settore interventi. */
	//private SettoreInterventi settoreInterventi;
	private String  settoreInterventiFS;
	
	/** The cpv. */
	//private Cpv cpv;
	private String cpvCodice;
		
	/** The nuts. */
	//private Nuts nuts;
	private String nutsCodice;
	
	/** The tipo priorita. */
	//private Priorita priorita;
	private String prioritaCodice;

	/** The modalita affidamento. */
	//private ModalitaAffidamento modalitaAffidamento;
	private String modalitaAffidamentoCodice;

	/** The stato. */
	//private Stato stato;
	private String statoCodice;

	/** The stato. */
	//private Stato stato;
	private String settoreCodice;

	/** The programma. */
	//private Programma programma;


	/** Default constructor */
	public InterventoCsv() {}

	/**
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * @param cui the cui to set
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * @return the annoAvvio
	 */
	public String getAnnoAvvio() {
		return annoAvvio;
	}

	/**
	 * @param annoAvvio the annoAvvio to set
	 */
	public void setAnnoAvvio(String annoAvvio) {
		this.annoAvvio = annoAvvio;
	}

	/**
	 * @return the cup
	 */
	public String getCup() {
		return cup;
	}

	/**
	 * @param cup the cup to set
	 */
	public void setCup(String cup) {
		this.cup = cup;
	}

	/**
	 * @return the acquistoRicompreso
	 */
	public String getAcquistoRicompreso() {
		return acquistoRicompreso;
	}

	/**
	 * @param acquistoRicompreso the acquistoRicompreso to set
	 */
	public void setAcquistoRicompreso(String acquistoRicompreso) {
		this.acquistoRicompreso = acquistoRicompreso;
	}

	/**
	 * @return the interventoRicompresoCui
	 */
	public String getInterventoRicompresoCui() {
		return interventoRicompresoCui;
	}

	/**
	 * @param interventoRicompresoCui the interventoRicompresoCui to set
	 */
	public void setInterventoRicompresoCui(String interventoRicompresoCui) {
		this.interventoRicompresoCui = interventoRicompresoCui;
	}

	/**
	 * @return the lottoFunzionaleSINO
	 */
	public String getLottoFunzionaleSINO() {
		return lottoFunzionaleSINO;
	}

	/**
	 * @param lottoFunzionaleSINO the lottoFunzionaleSINO to set
	 */
	public void setLottoFunzionaleSINO(String lottoFunzionaleSINO) {
		this.lottoFunzionaleSINO = lottoFunzionaleSINO;
	}

	/**
	 * @return the durataMesi
	 */
	public String getDurataMesi() {
		return durataMesi;
	}

	/**
	 * @param durataMesi the durataMesi to set
	 */
	public void setDurataMesi(String durataMesi) {
		this.durataMesi = durataMesi;
	}

	/**
	 * @return the nuovoAffidamentoSiNo
	 */
	public String getNuovoAffidamentoSiNo() {
		return nuovoAffidamentoSiNo;
	}

	/**
	 * @param nuovoAffidamentoSiNo the nuovoAffidamentoSiNo to set
	 */
	public void setNuovoAffidamentoSiNo(String nuovoAffidamentoSiNo) {
		this.nuovoAffidamentoSiNo = nuovoAffidamentoSiNo;
	}

	/**
	 * @return the ausaCodice
	 */
	public String getAusaCodice() {
		return ausaCodice;
	}

	/**
	 * @param ausaCodice the ausaCodice to set
	 */
	public void setAusaCodice(String ausaCodice) {
		this.ausaCodice = ausaCodice;
	}

	/**
	 * @return the acquistoVariatoCodice
	 */
	public String getAcquistoVariatoCodice() {
		return acquistoVariatoCodice;
	}

	/**
	 * @param acquistoVariatoCodice the acquistoVariatoCodice to set
	 */
	public void setAcquistoVariatoCodice(String acquistoVariatoCodice) {
		this.acquistoVariatoCodice = acquistoVariatoCodice;
	}

	/**
	 * @return the utenteRupCf
	 */
	public String getUtenteRupCf() {
		return utenteRupCf;
	}

	/**
	 * @param utenteRupCf the utenteRupCf to set
	 */
	public void setUtenteRupCf(String utenteRupCf) {
		this.utenteRupCf = utenteRupCf;
	}

	/**
	 * @return the descrizioneAcquisto
	 */
	public String getDescrizioneAcquisto() {
		return descrizioneAcquisto;
	}

	/**
	 * @param descrizioneAcquisto the descrizioneAcquisto to set
	 */
	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}

	/**
	 * @return the settoreInterventiFS
	 */
	public String getSettoreInterventiFS() {
		return settoreInterventiFS;
	}

	/**
	 * @param settoreInterventiFS the settoreInterventiFS to set
	 */
	public void setSettoreInterventiFS(String settoreInterventiFS) {
		this.settoreInterventiFS = settoreInterventiFS;
	}

	/**
	 * @return the cpvCodice
	 */
	public String getCpvCodice() {
		return cpvCodice;
	}

	/**
	 * @param cpvCodice the cpvCodice to set
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}

	/**
	 * @return the nutsCodice
	 */
	public String getNutsCodice() {
		return nutsCodice;
	}

	/**
	 * @param nutsCodice the nutsCodice to set
	 */
	public void setNutsCodice(String nutsCodice) {
		this.nutsCodice = nutsCodice;
	}

	/**
	 * @return the prioritaCodice
	 */
	public String getPrioritaCodice() {
		return prioritaCodice;
	}

	/**
	 * @param prioritaCodice the prioritaCodice to set
	 */
	public void setPrioritaCodice(String prioritaCodice) {
		this.prioritaCodice = prioritaCodice;
	}

	/**
	 * @return the modalitaAffidamentoCodice
	 */
	public String getModalitaAffidamentoCodice() {
		return modalitaAffidamentoCodice;
	}

	/**
	 * @param modalitaAffidamentoCodice the modalitaAffidamentoCodice to set
	 */
	public void setModalitaAffidamentoCodice(String modalitaAffidamentoCodice) {
		this.modalitaAffidamentoCodice = modalitaAffidamentoCodice;
	}

	/**
	 * @return the statoCodice
	 */
	public String getStatoCodice() {
		return statoCodice;
	}

	/**
	 * @param statoCodice the statoCodice to set
	 */
	public void setStatoCodice(String statoCodice) {
		this.statoCodice = statoCodice;
	}

	/**
	 * @return the settoreCodice
	 */
	public String getSettoreCodice() {
		return settoreCodice;
	}

	/**
	 * @param settoreCodice the settoreCodice to set
	 */
	public void setSettoreCodice(String settoreCodice) {
		this.settoreCodice = settoreCodice;
	}
	
}
