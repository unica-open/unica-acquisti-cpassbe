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

import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.custom.SubImpegniCsvAdapter;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * Validazioni varie
 */
public class ValidaSubImpegniEsterniCsv {

	public ValidaSubImpegniEsterniCsv() {
	}

	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());


	public FlussoSubimpegniEsterni validaCreaRigaSubImpegno(int numriga,String[] riga) {
		String errore = "";
		final Validazioni val = new Validazioni();



		final FlussoSubimpegniEsterni ris = new FlussoSubimpegniEsterni();
		ris.setBilAnno(troncaStringa(numriga,riga[2],50));
		errore = errore + val.isAnno("bilAnno", riga[2].trim());
		errore = errore + val.isNumerico("bilAnno", riga[2].trim());

		ris.setAnnoImpegno(troncaStringa(numriga,riga[5],50));
		errore = errore + val.isAnno("AnnoImpegno", riga[5].trim());
		errore = errore + val.isNumerico("AnnoImpegno", riga[5].trim());

		ris.setNumImpegno(troncaStringa(numriga,riga[6],50));
		errore = errore + val.isValorizzato("NumImpegno", riga[6].trim());
		errore = errore + val.isNumerico("NumImpegno", riga[6].trim());

		ris.setCodSubimpegno(troncaStringa(numriga,riga[8],50));
		errore = errore + val.isValorizzato("getCodSubimpegno", riga[8].trim());
		errore = errore + val.isNumerico("getCodSubimpegno", riga[8].trim());

		ris.setDescSubimpegno(troncaStringa(numriga,riga[9],500));

		ris.setCodStatoSubimpegno(troncaStringa(numriga,riga[10],500));
		errore = errore + val.isValorizzato("getCodStatoSubimpegno", riga[10].trim());


		ris.setCodSoggetto(troncaStringa(numriga,riga[20],50));
		errore = errore + val.isNumericoOVuoto("getCodSoggetto", riga[20].trim());


		ris.setDescSoggetto(troncaStringa(numriga,riga[21],50));
		ris.setCfSoggetto(troncaStringa(numriga,riga[22],50));
		ris.setCfEsteroSoggetto(troncaStringa(numriga,riga[23],50));
		ris.setpIvaSoggetto(troncaStringa(numriga,riga[24],50));

		ris.setCodClasseSoggetto(troncaStringa(numriga,riga[25],50));
		ris.setDescClasseSoggetto(troncaStringa(numriga,riga[26],50));
		ris.setCodTipoImpegno(troncaStringa(numriga,riga[27],50));
		ris.setDescTipoImpegno(troncaStringa(numriga,riga[28],50));


		ris.setAnnoriaccertato(troncaStringa(numriga,riga[80],50));
		errore = errore + val.isAnnoOVuoto("Annoriaccertato", riga[80].trim());

		ris.setNumriaccertato(troncaStringa(numriga,riga[81],50));
		errore = errore + val.isNumericoOVuoto("getNumriaccertato", riga[81].trim());


		ris.setAnnoAttoAmministrativo(troncaStringa(numriga,riga[84],50));
		errore = errore + val.isValorizzato("AnnoAttoAmministrativo", riga[84].trim());
		errore = errore + val.isAnno("AnnoAttoAmministrativo", riga[84].trim());


		ris.setNumAttoAmministrativo(troncaStringa(numriga,riga[85],50));
		errore = errore + val.isValorizzato("NumAttoAmministrativo", riga[85].trim());
		errore = errore + val.isNumerico("NumAttoAmministrativo", riga[85].trim());


		ris.setOggettoAttoAmministrativo(troncaStringa(numriga,riga[86],50));

		ris.setCodTipoAttoAmministrativo(troncaStringa(numriga,riga[88],50));

		ris.setCodCdrAttoAmministrativo(troncaStringa(numriga,riga[91],50));
		ris.setDescCdrAttoAmministrativo(troncaStringa(numriga,riga[92],50));
		ris.setCodCdcAttoAmministrativo(troncaStringa(numriga,riga[93],50));
		ris.setDescCdcAttoAmministrativo(troncaStringa(numriga,riga[94],50));

		ris.setImportoIniziale(troncaStringa(numriga,riga[95],50));
		errore = errore + val.isValorizzato("getImportoIniziale", riga[95].trim());
		errore = errore + val.isBigDecimal("getImportoIniziale", riga[95].trim());


		ris.setImportoAttuale(troncaStringa(numriga,riga[96],50));
		errore = errore + val.isValorizzato("getImportoAttuale", riga[96].trim());
		errore = errore + val.isBigDecimal("getImportoAttuale", riga[96].trim());

		ris.setImportoUtilizzabile(troncaStringa(numriga,riga[97],50));
		//errore = errore + val.isValorizzato("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile());
		errore = errore + val.isBigDecimalONull("getImportoUtilizzabile", riga[97].trim());

		ris.setImportoLiquidato(troncaStringa(numriga,riga[105],50));
		//errore = errore + val.isValorizzato("getImportoLiquidato", csvadapter.getImportoLiquidato());
		errore = errore + val.isBigDecimalONull("getImportoLiquidato", riga[98].trim());

		ris.setDataElaborazione(troncaStringa(numriga,riga[108],50));
		// TODO Auto-generated method stub

		ris.setErrore(errore);
		if(errore ==null || errore.equals("")) {
			ris.setEsito("OK");
		}else {
			ris.setEsito("KO");
			ris.setErrore("Num riga: "+numriga + " - "+errore);
		}

		return ris;
	}

	public FlussoSubimpegniEsterni validaCreaRigaSubImpegno_old(int riga,SubImpegniCsvAdapter csvadapter) {
		String errore = "";
		final Validazioni val = new Validazioni();



		final FlussoSubimpegniEsterni ris = new FlussoSubimpegniEsterni();
		ris.setBilAnno(csvadapter.getBilAnno().trim());
		errore = errore + val.isAnno("bilAnno", csvadapter.getBilAnno().trim());
		errore = errore + val.isNumerico("bilAnno", csvadapter.getBilAnno().trim());

		ris.setAnnoImpegno(csvadapter.getAnnoImpegno().trim());
		errore = errore + val.isAnno("AnnoImpegno", csvadapter.getAnnoImpegno().trim());
		errore = errore + val.isNumerico("AnnoImpegno", csvadapter.getAnnoImpegno().trim());

		ris.setNumImpegno(csvadapter.getNumImpegno().trim());
		errore = errore + val.isValorizzato("NumImpegno", csvadapter.getNumImpegno().trim());
		errore = errore + val.isNumerico("NumImpegno", csvadapter.getNumImpegno().trim());

		ris.setCodSubimpegno(csvadapter.getCodSubimpegno().trim());
		errore = errore + val.isValorizzato("getCodSubimpegno", csvadapter.getCodSubimpegno().trim());
		errore = errore + val.isNumerico("getCodSubimpegno", csvadapter.getCodSubimpegno().trim());

		ris.setDescSubimpegno(csvadapter.getDescSubimpegno().trim());

		ris.setCodStatoSubimpegno(csvadapter.getCodStatoSubimpegno().trim());
		errore = errore + val.isValorizzato("getCodStatoSubimpegno", csvadapter.getCodStatoSubimpegno().trim());


		ris.setCodSoggetto(csvadapter.getCodSoggetto().trim());
		errore = errore + val.isNumericoOVuoto("getCodSoggetto", csvadapter.getCodSoggetto().trim());


		ris.setDescSoggetto(csvadapter.getDescSoggetto().trim());
		ris.setCfSoggetto(csvadapter.getCfSoggetto().trim());
		ris.setCfEsteroSoggetto(csvadapter.getCfEsteroSoggetto().trim());
		ris.setpIvaSoggetto(csvadapter.getpIvaSoggetto().trim());

		ris.setCodClasseSoggetto(csvadapter.getCodClasseSoggetto().trim());
		ris.setDescClasseSoggetto(csvadapter.getDescClasseSoggetto().trim());
		ris.setCodTipoImpegno(csvadapter.getCodTipoImpegno().trim());
		ris.setDescTipoImpegno(csvadapter.getDescTipoImpegno().trim());


		ris.setAnnoriaccertato(csvadapter.getAnnoriaccertato().trim());
		errore = errore + val.isAnnoOVuoto("Annoriaccertato", csvadapter.getAnnoriaccertato().trim());

		ris.setNumriaccertato(csvadapter.getNumriaccertato().trim());
		errore = errore + val.isNumericoOVuoto("getNumriaccertato", csvadapter.getNumriaccertato().trim());


		ris.setAnnoAttoAmministrativo(csvadapter.getAnnoAttoAmministrativo().trim());
		errore = errore + val.isValorizzato("AnnoAttoAmministrativo", csvadapter.getAnnoAttoAmministrativo().trim());
		errore = errore + val.isAnno("AnnoAttoAmministrativo", csvadapter.getAnnoAttoAmministrativo().trim());


		ris.setNumAttoAmministrativo(csvadapter.getNumAttoAmministrativo().trim());
		errore = errore + val.isValorizzato("NumAttoAmministrativo", csvadapter.getNumAttoAmministrativo().trim());
		errore = errore + val.isNumerico("NumAttoAmministrativo", csvadapter.getNumAttoAmministrativo().trim());


		ris.setOggettoAttoAmministrativo(csvadapter.getOggettoAttoAmministrativo().trim());

		ris.setCodTipoAttoAmministrativo(csvadapter.getCodTipoAttoAmministrativo().trim());

		ris.setCodCdrAttoAmministrativo(csvadapter.getCodCdrAttoAmministrativo().trim());
		ris.setDescCdrAttoAmministrativo(csvadapter.getDescCdrAttoAmministrativo().trim());
		ris.setCodCdcAttoAmministrativo(csvadapter.getCodCdcAttoAmministrativo().trim());
		ris.setDescCdcAttoAmministrativo(csvadapter.getDescCdcAttoAmministrativo().trim());

		ris.setImportoIniziale(csvadapter.getImportoIniziale().trim());
		errore = errore + val.isValorizzato("getImportoIniziale", csvadapter.getImportoIniziale().trim());
		errore = errore + val.isBigDecimal("getImportoIniziale", csvadapter.getImportoIniziale().trim());


		ris.setImportoAttuale(csvadapter.getImportoAttuale().trim());
		errore = errore + val.isValorizzato("getImportoAttuale", csvadapter.getImportoAttuale().trim());
		errore = errore + val.isBigDecimal("getImportoAttuale", csvadapter.getImportoAttuale().trim());

		ris.setImportoUtilizzabile(csvadapter.getImportoUtilizzabile().trim());
		//errore = errore + val.isValorizzato("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile());
		errore = errore + val.isBigDecimalONull("getImportoUtilizzabile", csvadapter.getImportoUtilizzabile().trim());

		ris.setImportoLiquidato(csvadapter.getImportoLiquidato().trim());
		//errore = errore + val.isValorizzato("getImportoLiquidato", csvadapter.getImportoLiquidato());
		errore = errore + val.isBigDecimalONull("getImportoLiquidato", csvadapter.getImportoLiquidato().trim());

		ris.setDataElaborazione(csvadapter.getDataElaborazione().trim());
		// TODO Auto-generated method stub

		ris.setErrore(errore);
		if(errore.equals("")) {
			ris.setEsito("OK");
		}else {
			ris.setEsito("KO");
			ris.setErrore(errore);
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


}
