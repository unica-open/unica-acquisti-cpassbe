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

import it.csi.cpass.cpassbe.lib.dto.FlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.custom.ImpegniCsvAdapter;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * Validazioni varie
 */
public class ValidaImpegniEsterniCsv {

	public ValidaImpegniEsterniCsv() {
	}

	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());


	public FlussoImpegniEsterni validaCreaRigaImpegno(int numriga,String[] riga) {
		String errore = "";
		final Validazioni val = new Validazioni();

		final FlussoImpegniEsterni ris = new FlussoImpegniEsterni();
		ris.setBilAnno(troncaStringa(numriga,riga[2],50));
		errore = errore + val.isAnno("bilAnno", riga[2].trim());
		errore = errore + val.isValorizzato("bilAnno", riga[2].trim());

		ris.setAnnoImpegno(troncaStringa(numriga,riga[5],50));
		errore = errore +val.isAnno("AnnoImpegno", riga[5].trim());
		errore = errore + val.isValorizzato("AnnoImpegno", riga[5].trim());

		ris.setNumImpegno(troncaStringa(numriga,riga[6],50));
		errore = errore + val.isValorizzato("NumImpegno", riga[6].trim());
		errore = errore + val.isNumerico("NumImpegno", riga[6].trim());

		ris.setDescImpegno(troncaStringa(numriga,riga[7],500));
		ris.setCodImpegno(troncaStringa(numriga,riga[8],50));

		ris.setCodStatoImpegno(troncaStringa(numriga,riga[9],50));
		errore = errore + val.isValorizzato("CodStatoImpegno", riga[9].trim());

		ris.setDescStatoImpegno(troncaStringa(numriga,riga[10],500));

		ris.setDataScadenza(troncaStringa(numriga,riga[11],50));

		ris.setParereFinanziario(troncaStringa(numriga,riga[12],500));

		ris.setCodCapitolo(troncaStringa(numriga,riga[13],50));
		errore = errore + val.isValorizzato("CodCapitolo", riga[13].trim());
		errore = errore + val.isNumerico("CodCapitolo", riga[13].trim());

		ris.setCodArticolo(troncaStringa(numriga,riga[14],50));
		errore = errore + val.isValorizzato("CodArticolo", riga[14].trim());
		errore = errore + val.isNumerico("CodArticolo", riga[14].trim());

		ris.setCodUeb(troncaStringa(numriga,riga[15],50));

		ris.setDescCapitolo(troncaStringa(numriga,riga[16],4000));
		errore = errore + val.isValorizzato("DescCapitolo", riga[16].trim());

		ris.setDescArticolo(troncaStringa(numriga,riga[17],500));
		//errore = errore + isValorizzato("DescArticolo", csvadapter.getDescArticolo());

		ris.setCodSoggetto(troncaStringa(numriga,riga[19],50));
		errore = errore + val.isNumericoOVuoto("getCodSoggetto", riga[19].trim());

		//TRATTAMENTO dATI
		ris.setDescSoggetto(troncaStringa(numriga,riga[20],500));
		ris.setCfSoggetto(troncaStringa(numriga,riga[21],50));
		ris.setCfEsteroSoggetto(troncaStringa(numriga,riga[22],50));
		ris.setpIvaSoggetto(troncaStringa(numriga,riga[23],50));

		ris.setCodClasseSoggetto(troncaStringa(numriga,riga[24],500));
		ris.setDescClasseSoggetto(troncaStringa(numriga,riga[25],500));
		ris.setCodTipoImpegno(troncaStringa(numriga,riga[26],50));
		ris.setDescTipoImpegno(troncaStringa(numriga,riga[27],500));

		ris.setAnnoriaccertato(troncaStringa(numriga,riga[79],500));
		errore = errore + val.isAnnoOVuoto("Annoriaccertato", riga[79].trim());

		ris.setNumriaccertato(troncaStringa(numriga,riga[80],50));
		errore = errore + val.isNumericoOVuoto("getNumriaccertato", riga[80].trim());


		ris.setAnnoAttoAmministrativo(troncaStringa(numriga,riga[83],500));
		errore = errore + val.isValorizzato("AnnoAttoAmministrativo", riga[83].trim());
		errore = errore + val.isAnno("AnnoAttoAmministrativo", riga[83].trim());


		ris.setNumAttoAmministrativo(troncaStringa(numriga,riga[84],50));
		errore = errore + val.isValorizzato("NumAttoAmministrativo", riga[84].trim());
		errore = errore + val.isNumerico("NumAttoAmministrativo", riga[84].trim());


		ris.setOggettoAttoAmministrativo(troncaStringa(numriga,riga[85],500));

		ris.setCodTipoAttoAmministrativo(troncaStringa(numriga,riga[87],50));

		ris.setCodCdrAttoAmministrativo(troncaStringa(numriga,riga[90],50));
		ris.setDescCdrAttoAmministrativo(troncaStringa(numriga,riga[91],500));

		ris.setCodCdcAttoAmministrativo(troncaStringa(numriga,riga[92],50));
		ris.setDescCdcAttoAmministrativo(troncaStringa(numriga,riga[93],500));

		ris.setImportoIniziale(troncaStringa(numriga,riga[94],50));
		errore = errore + val.isValorizzato("getImportoIniziale", riga[94].trim());
		errore = errore + val.isBigDecimal("getImportoIniziale", riga[94].trim());


		ris.setImportoAttuale(troncaStringa(numriga,riga[95],50));
		errore = errore + val.isValorizzato("getImportoAttuale", riga[95].trim());
		errore = errore + val.isBigDecimal("getImportoAttuale", riga[95].trim());

		ris.setImportoUtilizzabile(troncaStringa(numriga,riga[96],50));
		//errore = errore + isValorizzato("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile());
		errore = errore + val.isBigDecimalONull("getImportoUtilizzabile", riga[96].trim());

		ris.setCig( troncaStringa(numriga,riga[99],50));
		ris.setCup( troncaStringa(numriga,riga[100],50));
		ris.setImportoLiquidato(troncaStringa(numriga,riga[104],50));
		//errore = errore + isValorizzato("getImportoLiquidato", csvadapter.getImportoLiquidato());
		errore = errore + val.isBigDecimalONull("getImportoLiquidato", riga[104].trim());
		ris.setDataElaborazione(riga[107]);
		ris.setErrore(errore);
		if(errore.equals("")) {
			ris.setEsito("OK");
		}else {
			ris.setEsito("KO");
			ris.setErrore("Num riga: "+numriga + " - "+errore);
		}
		return ris;
	}


	public FlussoImpegniEsterni validaCreaRigaImpegno_old(int riga,ImpegniCsvAdapter csvadapter) {
		String errore = "";
		final Validazioni val = new Validazioni();

		final FlussoImpegniEsterni ris = new FlussoImpegniEsterni();
		ris.setBilAnno(troncaStringa(csvadapter,csvadapter.getBilAnno(),500));
		errore = errore + val.isAnno("bilAnno", csvadapter.getBilAnno().trim());
		errore = errore + val.isValorizzato("bilAnno", csvadapter.getBilAnno().trim());

		ris.setAnnoImpegno(troncaStringa(csvadapter,csvadapter.getAnnoImpegno(),500));
		errore = errore +val.isAnno("AnnoImpegno", csvadapter.getAnnoImpegno().trim());
		errore = errore + val.isValorizzato("AnnoImpegno", csvadapter.getAnnoImpegno().trim());

		ris.setNumImpegno(troncaStringa(csvadapter,csvadapter.getNumImpegno(),500));
		errore = errore + val.isValorizzato("NumImpegno", csvadapter.getNumImpegno().trim());
		errore = errore + val.isNumerico("NumImpegno", csvadapter.getNumImpegno().trim());

		ris.setDescImpegno(troncaStringa(csvadapter,csvadapter.getDescImpegno(),500));
		ris.setCodImpegno(troncaStringa(csvadapter,csvadapter.getCodImpegno(),500));

		ris.setCodStatoImpegno(troncaStringa(csvadapter,csvadapter.getCodStatoImpegno(),500));
		errore = errore + val.isValorizzato("CodStatoImpegno", csvadapter.getCodStatoImpegno().trim());

		ris.setDescStatoImpegno(troncaStringa(csvadapter,csvadapter.getDescStatoImpegno(),500));

		ris.setDataScadenza(troncaStringa(csvadapter,csvadapter.getDataScadenza(),500));

		ris.setParereFinanziario(troncaStringa(csvadapter,csvadapter.getParereFinanziario(),500));

		ris.setCodCapitolo(troncaStringa(csvadapter,csvadapter.getCodCapitolo(),500));
		errore = errore + val.isValorizzato("CodCapitolo", csvadapter.getCodCapitolo().trim());
		errore = errore + val.isNumerico("CodCapitolo", csvadapter.getCodCapitolo().trim());

		ris.setCodArticolo(troncaStringa(csvadapter,csvadapter.getCodArticolo(),500));
		errore = errore + val.isValorizzato("CodArticolo", csvadapter.getCodArticolo().trim());
		errore = errore + val.isNumerico("CodArticolo", csvadapter.getCodArticolo().trim());

		ris.setCodUeb(troncaStringa(csvadapter,csvadapter.getCodUeb(),500));

		ris.setDescCapitolo(troncaStringa(csvadapter,csvadapter.getDescCapitolo(),500));
		errore = errore + val.isValorizzato("DescCapitolo", csvadapter.getDescCapitolo().trim());

		ris.setDescArticolo(troncaStringa(csvadapter,csvadapter.getDescArticolo(),500));
		//errore = errore + isValorizzato("DescArticolo", csvadapter.getDescArticolo());

		ris.setCodSoggetto(troncaStringa(csvadapter,csvadapter.getCodSoggetto(),500));
		errore = errore + val.isNumericoOVuoto("getCodSoggetto", csvadapter.getCodSoggetto().trim());

		//TRATTAMENTO dATI
		ris.setDescSoggetto(troncaStringa(csvadapter,csvadapter.getDescSoggetto(),500));
		ris.setCfSoggetto(troncaStringa(csvadapter,csvadapter.getCfSoggetto(),500));
		ris.setCfEsteroSoggetto(troncaStringa(csvadapter,csvadapter.getCfEsteroSoggetto(),500));
		ris.setpIvaSoggetto(troncaStringa(csvadapter,csvadapter.getpIvaSoggetto(),500));

		ris.setCodClasseSoggetto(troncaStringa(csvadapter,csvadapter.getCodClasseSoggetto(),500));
		ris.setDescClasseSoggetto(troncaStringa(csvadapter,csvadapter.getDescClasseSoggetto(),500));
		ris.setCodTipoImpegno(troncaStringa(csvadapter,csvadapter.getCodTipoImpegno(),500));
		ris.setDescTipoImpegno(troncaStringa(csvadapter,csvadapter.getDescTipoImpegno(),500));

		ris.setAnnoriaccertato(troncaStringa(csvadapter,csvadapter.getAnnoriaccertato(),500));
		errore = errore + val.isAnnoOVuoto("Annoriaccertato", csvadapter.getAnnoriaccertato().trim());

		ris.setNumriaccertato(troncaStringa(csvadapter,csvadapter.getNumriaccertato(),500));
		errore = errore + val.isNumericoOVuoto("getNumriaccertato", csvadapter.getNumriaccertato().trim());


		ris.setAnnoAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getAnnoAttoAmministrativo(),500));
		errore = errore + val.isValorizzato("AnnoAttoAmministrativo", csvadapter.getAnnoAttoAmministrativo().trim());
		errore = errore + val.isAnno("AnnoAttoAmministrativo", csvadapter.getAnnoAttoAmministrativo().trim());


		ris.setNumAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getNumAttoAmministrativo(),500));
		errore = errore + val.isValorizzato("NumAttoAmministrativo", csvadapter.getNumAttoAmministrativo().trim());
		errore = errore + val.isNumerico("NumAttoAmministrativo", csvadapter.getNumAttoAmministrativo().trim());


		ris.setOggettoAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getOggettoAttoAmministrativo(),500));

		ris.setCodTipoAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getCodTipoAttoAmministrativo(),500));

		ris.setCodCdrAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getCodCdrAttoAmministrativo(),500));
		ris.setDescCdrAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getDescCdrAttoAmministrativo(),500));
		ris.setCodCdcAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getCodCdcAttoAmministrativo(),500));
		ris.setDescCdcAttoAmministrativo(troncaStringa(csvadapter,csvadapter.getDescCdcAttoAmministrativo(),500));

		ris.setImportoIniziale(troncaStringa(csvadapter,csvadapter.getImportoIniziale(),500));
		errore = errore + val.isValorizzato("getImportoIniziale", csvadapter.getImportoIniziale().trim());
		errore = errore + val.isBigDecimal("getImportoIniziale", csvadapter.getImportoIniziale().trim());


		ris.setImportoAttuale(troncaStringa(csvadapter,csvadapter.getImportoAttuale(),500));
		errore = errore + val.isValorizzato("getImportoAttuale", csvadapter.getImportoAttuale().trim());
		errore = errore + val.isBigDecimal("getImportoAttuale", csvadapter.getImportoAttuale().trim());

		ris.setImportoUtilizzabile(troncaStringa(csvadapter,csvadapter.getImportoUtilizzabile(),500));
		//errore = errore + isValorizzato("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile());
		errore = errore + val.isBigDecimalONull("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile().trim());

		ris.setCig( troncaStringa(csvadapter,csvadapter.getCig(),500));
		ris.setCup( troncaStringa(csvadapter,csvadapter.getCup(),500));
		ris.setImportoLiquidato(troncaStringa(csvadapter,csvadapter.getImportoLiquidato(),500));
		//errore = errore + isValorizzato("getImportoLiquidato", csvadapter.getImportoLiquidato());
		errore = errore + val.isBigDecimalONull("getImportoLiquidato", csvadapter.getImportoLiquidato().trim());
		ris.setDataElaborazione(csvadapter.getDataElaborazione());
		ris.setErrore(errore);
		if(errore.equals("")) {
			ris.setEsito("OK");
		}else {
			ris.setEsito("KO");
		}

		return ris;
	}

	private String troncaStringa(int numRiga,String value,int lunghezza) {
		String ris = "";
		if(value!=null) {
			value = value.trim();//.replace('"', ' ').replace("'", " ");
			if(value.length()>lunghezza) {
				log.warn("", "riga numero "+ numRiga + " len: " + value.length() + " valore "+ value.trim()   );
				ris = value.substring(0, lunghezza-1);
			}
			ris = value;
		}
		return ris;
	}


	private String troncaStringa(ImpegniCsvAdapter csvadapter,String value,int lunghezza) {
		String ris = "";
		if(value!=null) {
			value = value.trim();//.replace('"', ' ').replace("'", " ");
			if(value.length()>lunghezza) {
				log.warn("", "riga su file non corretta anno imp " + csvadapter.getAnnoImpegno() + " numero imp "+ csvadapter.getNumImpegno() + " len: " + value.length() + " valore "+ value.trim()   );
				ris = value.substring(0, lunghezza-1);
			}
		}
		return ris;
	}

}
