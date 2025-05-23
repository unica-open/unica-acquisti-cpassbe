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



public class ImpegniCsvAdapter implements CsvAdapter {

	@CsvBindByPosition(position = 2)  String bilAnno;
	@CsvBindByPosition(position = 5)  String annoImpegno;
	@CsvBindByPosition(position = 6)  String numImpegno;
	@CsvBindByPosition(position = 7)  String descImpegno;
	@CsvBindByPosition(position = 8)  String codImpegno;
	@CsvBindByPosition(position = 9)  String codStatoImpegno;
	@CsvBindByPosition(position = 10)  String descStatoImpegno;
	@CsvBindByPosition(position = 11)  String dataScadenza;
	@CsvBindByPosition(position = 12)  String parereFinanziario;
	@CsvBindByPosition(position = 13)  String codCapitolo;
	@CsvBindByPosition(position = 14)  String codArticolo;
	@CsvBindByPosition(position = 15)  String codUeb;
	@CsvBindByPosition(position = 16)  String descCapitolo;
	@CsvBindByPosition(position = 17)  String descArticolo;
	@CsvBindByPosition(position = 19)  String codSoggetto;
	@CsvBindByPosition(position = 20)  String descSoggetto;
	@CsvBindByPosition(position = 21)  String cfSoggetto;
	@CsvBindByPosition(position = 22)  String cfEsteroSoggetto;
	@CsvBindByPosition(position = 23)  String pIvaSoggetto;
	@CsvBindByPosition(position = 24)  String codClasseSoggetto;
	@CsvBindByPosition(position = 25)  String descClasseSoggetto;
	@CsvBindByPosition(position = 26)  String codTipoImpegno;
	@CsvBindByPosition(position = 27)  String descTipoImpegno;
	@CsvBindByPosition(position = 79)  String annoriaccertato;
	@CsvBindByPosition(position = 80)  String numriaccertato;
	@CsvBindByPosition(position = 83)  String annoAttoAmministrativo;
	@CsvBindByPosition(position = 84)  String numAttoAmministrativo;
	@CsvBindByPosition(position = 85)  String oggettoAttoAmministrativo;
	@CsvBindByPosition(position = 87)  String codTipoAttoAmministrativo;
	@CsvBindByPosition(position = 90)  String codCdrAttoAmministrativo;
	@CsvBindByPosition(position = 91)  String descCdrAttoAmministrativo;
	@CsvBindByPosition(position = 92)  String codCdcAttoAmministrativo;
	@CsvBindByPosition(position = 93)  String descCdcAttoAmministrativo;
	@CsvBindByPosition(position = 94)  String importoIniziale;
	@CsvBindByPosition(position = 95)  String importoAttuale;
	@CsvBindByPosition(position = 96)  String importoUtilizzabile;
	@CsvBindByPosition(position = 99)  String cig;
	@CsvBindByPosition(position = 100)  String cup;
	@CsvBindByPosition(position = 104)  String importoLiquidato;
	@CsvBindByPosition(position = 107)  String dataElaborazione;

	/*
	public FlussoImpegnoSiac parseEntry(String... data) {

			//almeno 96 devono essere le colonne file csv impegni


			//annoEsercizio
			if (data[2] == null || data[2].trim().equals("") || !GenericUtil.isNumber(data[2])) {
				logger.debug("SCARTO RECORD: con annoEsercizio = "+data[2]);
				logger.debug("Motivo: annoEsercizio null o vuoto o non solo cifre");
				return null;
			}
			String annoEsercizio = data[2];

			//annoImp
			if (data[5] == null || data[5].trim().equals("") || !GenericUtil.isNumber(data[5])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]);
				logger.debug("Motivo: annoImpegno null o vuoto o non solo cifre");
				return null;
			}
			String annoImp = data[5];

			//numImp
			if (data[6] == null || data[6].trim().equals("") || !GenericUtil.isNumber(data[6])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: numImpegno null o vuoto o non solo cifre");
				return null;
			}
			Integer numImp = new Integer(data[6].trim());

			//descri impegno
			String descri = data[7];

			//staoper
			if (data[9] == null || data[9].trim().equals("")) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: staoper null o vuoto");
				return null;
			}
			String staoper = data[9];

			//nroCapitolo
			if (data[13] == null || data[13].trim().equals("") || !GenericUtil.isNumber(data[13])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: nroCapitolo null o vuoto o non solo cifre");
				return null;
			}
			Integer nroCapitolo = new Integer(data[13].trim());

			//nroArticolo
			if (data[14] == null || data[14].trim().equals("") || !GenericUtil.isNumber(data[14])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: nroArticolo null o vuoto o non solo cifre");
				return null;
			}
			Integer nroArticolo = new Integer(data[14].trim());

			//descriCapitolo    MODIFICA 20/01/2017 indice cambiato da 17 a 16
			String descriCapitolo = data[16];

			//codben
			Integer codben = data[19] != null && !data[19].trim().equals("") && GenericUtil.isNumber(data[19]) ?
					new Integer(data[19].trim()) : null;

			//tipoImpegno      MODIFICA 20/01/2017 indice cambiato da 25 a 26
			String tipoImpegno = data[26];

			//annoImpOrig      MODIFICA 20/01/2017 indice cambiato da 78 a 79
//			if (data[78] == null && !GenericUtil.isNumber(data[78])) {
//				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
//				logger.debug("Motivo: annoImpOrig non solo cifre");
//				return null;
//			}
			String annoImpOrig = data[79];
			if (annoImpOrig != null) {
				if (!annoImpOrig.trim().equals("")) {
					if (!GenericUtil.isNumber(annoImpOrig)) {
						logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
						logger.debug("Motivo: annoImpOrig non solo cifre");
						return null;
					}
				} else
					annoImpOrig = null;
			}


			//numImpOrig      MODIFICA 20/01/2017 indice cambiato da 79 a 80
//			if (data[79] == null && data[79].trim().equals("") && !GenericUtil.isNumber(data[79])) {
//				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
//				logger.debug("Motivo: numImpOrig non solo cifre");
//				return null;
//			}
			Integer numImpOrig = data[80] != null && !data[80].trim().equals("") && GenericUtil.isNumber(data[80]) ?
					new Integer(data[80].trim()) : null;

			//annoProv       MODIFICA 20/01/2017 indice cambiato da 82 a 83
			if (data[83] == null || data[83].trim().equals("") || !GenericUtil.isNumber(data[83])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: annoProv null o vuoto o non solo cifre");
				return null;
			}
			String annoProv = data[83];

			//nProv         MODIFICA 20/01/2017 indice cambiato da 83 a 84
			if (data[84] == null || data[84].trim().equals("") || !GenericUtil.isNumber(data[84])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: nProv null o vuoto o non solo cifre");
				return null;
			}
			String nProv = data[84];

			//impoIni       MODIFICA 20/01/2017 indice cambiato da 89 a 94
			if (data[94] == null || data[94].trim().equals("") || !GenericUtil.isDouble(data[94])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: impoIni null o vuoto o non solo cifre");
				return null;
			}
			BigDecimal impoini = new BigDecimal(Double.parseDouble(data[94].trim()));

			//impoatt      MODIFICA 20/01/2017 indice cambiato da 90 a 95
			if (data[95] == null || data[95].trim().equals("") || !GenericUtil.isDouble(data[95])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: impoatt null o vuoto o non solo cifre");
				return null;
			}
			BigDecimal impoatt = new BigDecimal(Double.parseDouble(data[95].trim()));

			//importo liquidato
			if (data[104] == null || data[104].trim().equals("") || !GenericUtil.isDouble(data[104])) {
				logger.debug("SCARTO RECORD: con annoImpegno = "+data[5]+" e numImpegno = "+data[6]);
				logger.debug("Motivo: importo liquidato null o vuoto o non solo cifre");
				return null;
			}
			BigDecimal impLiquidato = new BigDecimal(Double.parseDouble(data[104].trim()));

			//liqAnnoPrec impostato a 0
			BigDecimal liqAnnoPrec = new BigDecimal(0);

			return new FlussoImpegnoSiac(annoEsercizio, annoImp, numImp, descri,  annoImpOrig, numImpOrig,
					nroArticolo, nroCapitolo, codben, staoper, tipoImpegno,	impoini, impoatt, descriCapitolo,
					annoProv, null, 1, nProv, null, null, null,null, liqAnnoPrec, impLiquidato);
		}
		*/

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
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}




	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}




	/**
	 * @return the codArticolo
	 */
	public String getCodArticolo() {
		return codArticolo;
	}




	/**
	 * @param codArticolo the codArticolo to set
	 */
	public void setCodArticolo(String codArticolo) {
		this.codArticolo = codArticolo;
	}




	/**
	 * @return the codCapitolo
	 */
	public String getCodCapitolo() {
		return codCapitolo;
	}




	/**
	 * @param codCapitolo the codCapitolo to set
	 */
	public void setCodCapitolo(String codCapitolo) {
		this.codCapitolo = codCapitolo;
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
	 * @return the codImpegno
	 */
	public String getCodImpegno() {
		return codImpegno;
	}




	/**
	 * @param codImpegno the codImpegno to set
	 */
	public void setCodImpegno(String codImpegno) {
		this.codImpegno = codImpegno;
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
	 * @return the codStatoImpegno
	 */
	public String getCodStatoImpegno() {
		return codStatoImpegno;
	}




	/**
	 * @param codStatoImpegno the codStatoImpegno to set
	 */
	public void setCodStatoImpegno(String codStatoImpegno) {
		this.codStatoImpegno = codStatoImpegno;
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
	 * @return the codUeb
	 */
	public String getCodUeb() {
		return codUeb;
	}




	/**
	 * @param codUeb the codUeb to set
	 */
	public void setCodUeb(String codUeb) {
		this.codUeb = codUeb;
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




	/**
	 * @return the dataScadenza
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}




	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}




	/**
	 * @return the descArticolo
	 */
	public String getDescArticolo() {
		return descArticolo;
	}




	/**
	 * @param descArticolo the descArticolo to set
	 */
	public void setDescArticolo(String descArticolo) {
		this.descArticolo = descArticolo;
	}




	/**
	 * @return the descCapitolo
	 */
	public String getDescCapitolo() {
		return descCapitolo;
	}




	/**
	 * @param descCapitolo the descCapitolo to set
	 */
	public void setDescCapitolo(String descCapitolo) {
		this.descCapitolo = descCapitolo;
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
	 * @return the descImpegno
	 */
	public String getDescImpegno() {
		return descImpegno;
	}




	/**
	 * @param descImpegno the descImpegno to set
	 */
	public void setDescImpegno(String descImpegno) {
		this.descImpegno = descImpegno;
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
	 * @return the descStatoImpegno
	 */
	public String getDescStatoImpegno() {
		return descStatoImpegno;
	}




	/**
	 * @param descStatoImpegno the descStatoImpegno to set
	 */
	public void setDescStatoImpegno(String descStatoImpegno) {
		this.descStatoImpegno = descStatoImpegno;
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
	 * @return the parereFinanziario
	 */
	public String getParereFinanziario() {
		return parereFinanziario;
	}




	/**
	 * @param parereFinanziario the parereFinanziario to set
	 */
	public void setParereFinanziario(String parereFinanziario) {
		this.parereFinanziario = parereFinanziario;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public boolean validate() {
		return true;
	}



}

