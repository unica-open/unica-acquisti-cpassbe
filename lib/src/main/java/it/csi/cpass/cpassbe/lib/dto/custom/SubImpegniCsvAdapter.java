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
package it.csi.cpass.cpassbe.lib.dto.custom;

import com.opencsv.bean.CsvBindByPosition;



public class SubImpegniCsvAdapter implements CsvAdapter {

	@CsvBindByPosition(position = 2)  String bilAnno;
	@CsvBindByPosition(position = 5)  String annoImpegno;
	@CsvBindByPosition(position = 6)  String numImpegno;
	@CsvBindByPosition(position = 8)  String codSubimpegno;
	@CsvBindByPosition(position = 9)  String descSubimpegno;
	@CsvBindByPosition(position = 10)  String codStatoSubimpegno;
	@CsvBindByPosition(position = 20)  String codSoggetto;
	@CsvBindByPosition(position = 21)  String descSoggetto;
	@CsvBindByPosition(position = 22)  String cfSoggetto;
	@CsvBindByPosition(position = 23)  String cfEsteroSoggetto;
	@CsvBindByPosition(position = 24)  String pIvaSoggetto;
	@CsvBindByPosition(position = 25)  String codClasseSoggetto;
	@CsvBindByPosition(position = 26)  String descClasseSoggetto;
	@CsvBindByPosition(position = 27)  String codTipoImpegno;
	@CsvBindByPosition(position = 28)  String descTipoImpegno;
	@CsvBindByPosition(position = 80)  String annoriaccertato;
	@CsvBindByPosition(position = 81)  String numriaccertato;
	@CsvBindByPosition(position = 84)  String annoAttoAmministrativo;
	@CsvBindByPosition(position = 85)  String numAttoAmministrativo;
	@CsvBindByPosition(position = 86)  String oggettoAttoAmministrativo;
	@CsvBindByPosition(position = 88)  String codTipoAttoAmministrativo;
	@CsvBindByPosition(position = 91)  String codCdrAttoAmministrativo;
	@CsvBindByPosition(position = 92)  String descCdrAttoAmministrativo;
	@CsvBindByPosition(position = 93)  String codCdcAttoAmministrativo;
	@CsvBindByPosition(position = 94)  String descCdcAttoAmministrativo;
	@CsvBindByPosition(position = 95)  String importoIniziale;
	@CsvBindByPosition(position = 96)  String importoAttuale;
	@CsvBindByPosition(position = 97)  String importoUtilizzabile;
	@CsvBindByPosition(position = 105)  String importoLiquidato;
	@CsvBindByPosition(position = 108)  String dataElaborazione;

	/**
	 * @return the bilAnno
	 */
	public String getBilAnno() {
		return bilAnno;
	}

	/**
	 * @param bilAnno the bilAnno to set
	 */
	public void setBilAnno(String bilAnno) {
		this.bilAnno = bilAnno;
	}

	/**
	 * @return the annoImpegno
	 */
	public String getAnnoImpegno() {
		return annoImpegno;
	}

	/**
	 * @param annoImpegno the annoImpegno to set
	 */
	public void setAnnoImpegno(String annoImpegno) {
		this.annoImpegno = annoImpegno;
	}

	/**
	 * @return the numImpegno
	 */
	public String getNumImpegno() {
		return numImpegno;
	}

	/**
	 * @param numImpegno the numImpegno to set
	 */
	public void setNumImpegno(String numImpegno) {
		this.numImpegno = numImpegno;
	}

	/**
	 * @return the codSubimpegno
	 */
	public String getCodSubimpegno() {
		return codSubimpegno;
	}

	/**
	 * @param codSubimpegno the codSubimpegno to set
	 */
	public void setCodSubimpegno(String codSubimpegno) {
		this.codSubimpegno = codSubimpegno;
	}

	/**
	 * @return the descSubimpegno
	 */
	public String getDescSubimpegno() {
		return descSubimpegno;
	}

	/**
	 * @param descSubimpegno the descSubimpegno to set
	 */
	public void setDescSubimpegno(String descSubimpegno) {
		this.descSubimpegno = descSubimpegno;
	}

	/**
	 * @return the codStatoSubimpegno
	 */
	public String getCodStatoSubimpegno() {
		return codStatoSubimpegno;
	}

	/**
	 * @param codStatoSubimpegno the codStatoSubimpegno to set
	 */
	public void setCodStatoSubimpegno(String codStatoSubimpegno) {
		this.codStatoSubimpegno = codStatoSubimpegno;
	}

	/**
	 * @return the codSoggetto
	 */
	public String getCodSoggetto() {
		return codSoggetto;
	}

	/**
	 * @param codSoggetto the codSoggetto to set
	 */
	public void setCodSoggetto(String codSoggetto) {
		this.codSoggetto = codSoggetto;
	}

	/**
	 * @return the descSoggetto
	 */
	public String getDescSoggetto() {
		return descSoggetto;
	}

	/**
	 * @param descSoggetto the descSoggetto to set
	 */
	public void setDescSoggetto(String descSoggetto) {
		this.descSoggetto = descSoggetto;
	}

	/**
	 * @return the cfSoggetto
	 */
	public String getCfSoggetto() {
		return cfSoggetto;
	}

	/**
	 * @param cfSoggetto the cfSoggetto to set
	 */
	public void setCfSoggetto(String cfSoggetto) {
		this.cfSoggetto = cfSoggetto;
	}

	/**
	 * @return the cfEsteroSoggetto
	 */
	public String getCfEsteroSoggetto() {
		return cfEsteroSoggetto;
	}

	/**
	 * @param cfEsteroSoggetto the cfEsteroSoggetto to set
	 */
	public void setCfEsteroSoggetto(String cfEsteroSoggetto) {
		this.cfEsteroSoggetto = cfEsteroSoggetto;
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
	 * @return the codClasseSoggetto
	 */
	public String getCodClasseSoggetto() {
		return codClasseSoggetto;
	}

	/**
	 * @param codClasseSoggetto the codClasseSoggetto to set
	 */
	public void setCodClasseSoggetto(String codClasseSoggetto) {
		this.codClasseSoggetto = codClasseSoggetto;
	}

	/**
	 * @return the descClasseSoggetto
	 */
	public String getDescClasseSoggetto() {
		return descClasseSoggetto;
	}

	/**
	 * @param descClasseSoggetto the descClasseSoggetto to set
	 */
	public void setDescClasseSoggetto(String descClasseSoggetto) {
		this.descClasseSoggetto = descClasseSoggetto;
	}

	/**
	 * @return the codTipoImpegno
	 */
	public String getCodTipoImpegno() {
		return codTipoImpegno;
	}

	/**
	 * @param codTipoImpegno the codTipoImpegno to set
	 */
	public void setCodTipoImpegno(String codTipoImpegno) {
		this.codTipoImpegno = codTipoImpegno;
	}

	/**
	 * @return the descTipoImpegno
	 */
	public String getDescTipoImpegno() {
		return descTipoImpegno;
	}

	/**
	 * @param descTipoImpegno the descTipoImpegno to set
	 */
	public void setDescTipoImpegno(String descTipoImpegno) {
		this.descTipoImpegno = descTipoImpegno;
	}

	/**
	 * @return the annoriaccertato
	 */
	public String getAnnoriaccertato() {
		return annoriaccertato;
	}

	/**
	 * @param annoriaccertato the annoriaccertato to set
	 */
	public void setAnnoriaccertato(String annoriaccertato) {
		this.annoriaccertato = annoriaccertato;
	}

	/**
	 * @return the numriaccertato
	 */
	public String getNumriaccertato() {
		return numriaccertato;
	}

	/**
	 * @param numriaccertato the numriaccertato to set
	 */
	public void setNumriaccertato(String numriaccertato) {
		this.numriaccertato = numriaccertato;
	}

	/**
	 * @return the annoAttoAmministrativo
	 */
	public String getAnnoAttoAmministrativo() {
		return annoAttoAmministrativo;
	}

	/**
	 * @param annoAttoAmministrativo the annoAttoAmministrativo to set
	 */
	public void setAnnoAttoAmministrativo(String annoAttoAmministrativo) {
		this.annoAttoAmministrativo = annoAttoAmministrativo;
	}

	/**
	 * @return the numAttoAmministrativo
	 */
	public String getNumAttoAmministrativo() {
		return numAttoAmministrativo;
	}

	/**
	 * @param numAttoAmministrativo the numAttoAmministrativo to set
	 */
	public void setNumAttoAmministrativo(String numAttoAmministrativo) {
		this.numAttoAmministrativo = numAttoAmministrativo;
	}

	/**
	 * @return the oggettoAttoAmministrativo
	 */
	public String getOggettoAttoAmministrativo() {
		return oggettoAttoAmministrativo;
	}

	/**
	 * @param oggettoAttoAmministrativo the oggettoAttoAmministrativo to set
	 */
	public void setOggettoAttoAmministrativo(String oggettoAttoAmministrativo) {
		this.oggettoAttoAmministrativo = oggettoAttoAmministrativo;
	}

	/**
	 * @return the codTipoAttoAmministrativo
	 */
	public String getCodTipoAttoAmministrativo() {
		return codTipoAttoAmministrativo;
	}

	/**
	 * @param codTipoAttoAmministrativo the codTipoAttoAmministrativo to set
	 */
	public void setCodTipoAttoAmministrativo(String codTipoAttoAmministrativo) {
		this.codTipoAttoAmministrativo = codTipoAttoAmministrativo;
	}

	/**
	 * @return the codCdrAttoAmministrativo
	 */
	public String getCodCdrAttoAmministrativo() {
		return codCdrAttoAmministrativo;
	}

	/**
	 * @param codCdrAttoAmministrativo the codCdrAttoAmministrativo to set
	 */
	public void setCodCdrAttoAmministrativo(String codCdrAttoAmministrativo) {
		this.codCdrAttoAmministrativo = codCdrAttoAmministrativo;
	}

	/**
	 * @return the descCdrAttoAmministrativo
	 */
	public String getDescCdrAttoAmministrativo() {
		return descCdrAttoAmministrativo;
	}

	/**
	 * @param descCdrAttoAmministrativo the descCdrAttoAmministrativo to set
	 */
	public void setDescCdrAttoAmministrativo(String descCdrAttoAmministrativo) {
		this.descCdrAttoAmministrativo = descCdrAttoAmministrativo;
	}

	/**
	 * @return the codCdcAttoAmministrativo
	 */
	public String getCodCdcAttoAmministrativo() {
		return codCdcAttoAmministrativo;
	}

	/**
	 * @param codCdcAttoAmministrativo the codCdcAttoAmministrativo to set
	 */
	public void setCodCdcAttoAmministrativo(String codCdcAttoAmministrativo) {
		this.codCdcAttoAmministrativo = codCdcAttoAmministrativo;
	}

	/**
	 * @return the descCdcAttoAmministrativo
	 */
	public String getDescCdcAttoAmministrativo() {
		return descCdcAttoAmministrativo;
	}

	/**
	 * @param descCdcAttoAmministrativo the descCdcAttoAmministrativo to set
	 */
	public void setDescCdcAttoAmministrativo(String descCdcAttoAmministrativo) {
		this.descCdcAttoAmministrativo = descCdcAttoAmministrativo;
	}

	/**
	 * @return the importoIniziale
	 */
	public String getImportoIniziale() {
		return importoIniziale;
	}

	/**
	 * @param importoIniziale the importoIniziale to set
	 */
	public void setImportoIniziale(String importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	/**
	 * @return the importoAttuale
	 */
	public String getImportoAttuale() {
		return importoAttuale;
	}

	/**
	 * @param importoAttuale the importoAttuale to set
	 */
	public void setImportoAttuale(String importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	/**
	 * @return the importoUtilizzabile
	 */
	public String getImportoUtilizzabile() {
		return importoUtilizzabile;
	}

	/**
	 * @param importoUtilizzabile the importoUtilizzabile to set
	 */
	public void setImportoUtilizzabile(String importoUtilizzabile) {
		this.importoUtilizzabile = importoUtilizzabile;
	}

	/**
	 * @return the importoLiquidato
	 */
	public String getImportoLiquidato() {
		return importoLiquidato;
	}

	/**
	 * @param importoLiquidato the importoLiquidato to set
	 */
	public void setImportoLiquidato(String importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	/**
	 * @return the dataElaborazione
	 */
	public String getDataElaborazione() {
		return dataElaborazione;
	}

	/**
	 * @param dataElaborazione the dataElaborazione to set
	 */
	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}



}


