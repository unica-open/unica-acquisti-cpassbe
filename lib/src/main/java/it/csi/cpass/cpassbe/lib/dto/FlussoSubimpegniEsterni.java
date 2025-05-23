/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;




public class FlussoSubimpegniEsterni extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String elaborazioneId;

	private String annoAttoAmministrativo;

	private String annoImpegno;

	private String annoriaccertato;

	private String bilAnno;

	private String cfEsteroSoggetto;

	private String cfSoggetto;

	private String codCdcAttoAmministrativo;

	private String codCdrAttoAmministrativo;

	private String codClasseSoggetto;

	private String codSoggetto;

	private String codStatoSubimpegno;

	private String codSubimpegno;

	private String codTipoAttoAmministrativo;

	private String codTipoImpegno;

	private String dataElaborazione;

	private String descCdcAttoAmministrativo;

	private String descCdrAttoAmministrativo;

	private String descClasseSoggetto;

	private String descSoggetto;

	private String descSubimpegno;

	private String descTipoImpegno;

	private String enteCodice;

	private String errore;

	private String esito;

	private UUID idEnte;

	private String importoAttuale;

	private String importoIniziale;

	private String importoLiquidato;

	private String importoUtilizzabile;

	private String numAttoAmministrativo;

	private String numImpegno;

	private String numriaccertato;

	private String oggettoAttoAmministrativo;

	private String pIvaSoggetto;

	private Date dataCaricamento;

	private Integer numElaborazioneDiGiornata;


	public FlussoSubimpegniEsterni() {
	}

	public String getAnnoAttoAmministrativo() {
		return this.annoAttoAmministrativo;
	}

	public void setAnnoAttoAmministrativo(String annoAttoAmministrativo) {
		this.annoAttoAmministrativo = annoAttoAmministrativo;
	}

	public String getAnnoImpegno() {
		return this.annoImpegno;
	}

	public void setAnnoImpegno(String annoImpegno) {
		this.annoImpegno = annoImpegno;
	}

	public String getAnnoriaccertato() {
		return this.annoriaccertato;
	}

	public void setAnnoriaccertato(String annoriaccertato) {
		this.annoriaccertato = annoriaccertato;
	}

	public String getBilAnno() {
		return this.bilAnno;
	}

	public void setBilAnno(String bilAnno) {
		this.bilAnno = bilAnno;
	}

	public String getCfEsteroSoggetto() {
		return this.cfEsteroSoggetto;
	}

	public void setCfEsteroSoggetto(String cfEsteroSoggetto) {
		this.cfEsteroSoggetto = cfEsteroSoggetto;
	}

	public String getCfSoggetto() {
		return this.cfSoggetto;
	}

	public void setCfSoggetto(String cfSoggetto) {
		this.cfSoggetto = cfSoggetto;
	}

	public String getCodCdcAttoAmministrativo() {
		return this.codCdcAttoAmministrativo;
	}

	public void setCodCdcAttoAmministrativo(String codCdcAttoAmministrativo) {
		this.codCdcAttoAmministrativo = codCdcAttoAmministrativo;
	}

	public String getCodCdrAttoAmministrativo() {
		return this.codCdrAttoAmministrativo;
	}

	public void setCodCdrAttoAmministrativo(String codCdrAttoAmministrativo) {
		this.codCdrAttoAmministrativo = codCdrAttoAmministrativo;
	}

	public String getCodClasseSoggetto() {
		return this.codClasseSoggetto;
	}

	public void setCodClasseSoggetto(String codClasseSoggetto) {
		this.codClasseSoggetto = codClasseSoggetto;
	}

	public String getCodSoggetto() {
		return this.codSoggetto;
	}

	public void setCodSoggetto(String codSoggetto) {
		this.codSoggetto = codSoggetto;
	}

	public String getCodStatoSubimpegno() {
		return this.codStatoSubimpegno;
	}

	public void setCodStatoSubimpegno(String codStatoSubimpegno) {
		this.codStatoSubimpegno = codStatoSubimpegno;
	}

	public String getCodSubimpegno() {
		return this.codSubimpegno;
	}

	public void setCodSubimpegno(String codSubimpegno) {
		this.codSubimpegno = codSubimpegno;
	}

	public String getCodTipoAttoAmministrativo() {
		return this.codTipoAttoAmministrativo;
	}

	public void setCodTipoAttoAmministrativo(String codTipoAttoAmministrativo) {
		this.codTipoAttoAmministrativo = codTipoAttoAmministrativo;
	}

	public String getCodTipoImpegno() {
		return this.codTipoImpegno;
	}

	public void setCodTipoImpegno(String codTipoImpegno) {
		this.codTipoImpegno = codTipoImpegno;
	}

	public String getDataElaborazione() {
		return this.dataElaborazione;
	}

	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public String getDescCdcAttoAmministrativo() {
		return this.descCdcAttoAmministrativo;
	}

	public void setDescCdcAttoAmministrativo(String descCdcAttoAmministrativo) {
		this.descCdcAttoAmministrativo = descCdcAttoAmministrativo;
	}

	public String getDescCdrAttoAmministrativo() {
		return this.descCdrAttoAmministrativo;
	}

	public void setDescCdrAttoAmministrativo(String descCdrAttoAmministrativo) {
		this.descCdrAttoAmministrativo = descCdrAttoAmministrativo;
	}

	public String getDescClasseSoggetto() {
		return this.descClasseSoggetto;
	}

	public void setDescClasseSoggetto(String descClasseSoggetto) {
		this.descClasseSoggetto = descClasseSoggetto;
	}

	public String getDescSoggetto() {
		return this.descSoggetto;
	}

	public void setDescSoggetto(String descSoggetto) {
		this.descSoggetto = descSoggetto;
	}

	public String getDescSubimpegno() {
		return this.descSubimpegno;
	}

	public void setDescSubimpegno(String descSubimpegno) {
		this.descSubimpegno = descSubimpegno;
	}

	public String getDescTipoImpegno() {
		return this.descTipoImpegno;
	}

	public void setDescTipoImpegno(String descTipoImpegno) {
		this.descTipoImpegno = descTipoImpegno;
	}

	public String getEnteCodice() {
		return this.enteCodice;
	}

	public void setEnteCodice(String enteCodice) {
		this.enteCodice = enteCodice;
	}

	public String getErrore() {
		return this.errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

	public String getEsito() {
		return this.esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public UUID getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
	}

	public String getImportoAttuale() {
		return this.importoAttuale;
	}

	public void setImportoAttuale(String importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	public String getImportoIniziale() {
		return this.importoIniziale;
	}

	public void setImportoIniziale(String importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	public String getImportoLiquidato() {
		return this.importoLiquidato;
	}

	public void setImportoLiquidato(String importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	public String getImportoUtilizzabile() {
		return this.importoUtilizzabile;
	}

	public void setImportoUtilizzabile(String importoUtilizzabile) {
		this.importoUtilizzabile = importoUtilizzabile;
	}

	public String getNumAttoAmministrativo() {
		return this.numAttoAmministrativo;
	}

	public void setNumAttoAmministrativo(String numAttoAmministrativo) {
		this.numAttoAmministrativo = numAttoAmministrativo;
	}

	public String getNumImpegno() {
		return this.numImpegno;
	}

	public void setNumImpegno(String numImpegno) {
		this.numImpegno = numImpegno;
	}

	public String getNumriaccertato() {
		return this.numriaccertato;
	}

	public void setNumriaccertato(String numriaccertato) {
		this.numriaccertato = numriaccertato;
	}

	public String getOggettoAttoAmministrativo() {
		return this.oggettoAttoAmministrativo;
	}

	public void setOggettoAttoAmministrativo(String oggettoAttoAmministrativo) {
		this.oggettoAttoAmministrativo = oggettoAttoAmministrativo;
	}

	public String getPIvaSoggetto() {
		return this.pIvaSoggetto;
	}

	public void setPIvaSoggetto(String pIvaSoggetto) {
		this.pIvaSoggetto = pIvaSoggetto;
	}



	public String getElaborazioneId() {
		return elaborazioneId;
	}

	public void setElaborazioneId(String elaborazioneId) {
		this.elaborazioneId = elaborazioneId;
	}


	/**
	 * @return the dataCaricamento
	 */
	public Date getDataCaricamento() {
		return dataCaricamento;
	}

	/**
	 * @param dataCaricamento the dataCaricamento to set
	 */
	public void setDataCaricamento(Date dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	/**
	 * @return the pIvaSoggetto
	 */
	public String getpIvaSoggetto() {
		return pIvaSoggetto;
	}

	/**
	 * @param pIvaSoggetto the pIvaSoggetto to set
	 */
	public void setpIvaSoggetto(String pIvaSoggetto) {
		this.pIvaSoggetto = pIvaSoggetto;
	}

	/**
	 * @return the numElaborazioneDiGiornata
	 */
	public Integer getNumElaborazioneDiGiornata() {
		return numElaborazioneDiGiornata;
	}

	/**
	 * @param numElaborazioneDiGiornata the numElaborazioneDiGiornata to set
	 */
	public void setNumElaborazioneDiGiornata(Integer numElaborazioneDiGiornata) {
		this.numElaborazioneDiGiornata = numElaborazioneDiGiornata;
	}

}
