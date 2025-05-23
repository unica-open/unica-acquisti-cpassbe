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
package it.csi.cpass.cpassbe.ejb.csv;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvHandlingTest {

	public static void main(String[] args) throws Exception {
		new CsvHandlingTest().execute();
	}

	public void execute() throws Exception {
		//System.out.println("IMPORTI SEMICOLON\n");
		parseImporti("", ';');

		//System.out.println("\n\nIMPORTI COMMA\n");
		parseImporti("_comma", ',');

		//System.out.println("\n\nACQUISTI SEMICOLON\n");
		parseAcquisti("", ';');

		//System.out.println("\n\nACQUISTI COMMA\n");
		parseAcquisti("_comma", ',');
	}

	private void parseImporti(String suffix, char separator) throws Exception {
		URI uri = Thread.currentThread().getContextClassLoader().getResource("csv/CSI-Esigenze-Elaborate-Acquisti_importi" + suffix + ".csv").toURI();
		Path path = Paths.get(uri);
		byte[] bytesBytes = Files.readAllBytes(path);
		CsvToBean<ImportiAdapter> iterable = new CsvToBeanBuilder<ImportiAdapter>(new InputStreamReader(new ByteArrayInputStream(bytesBytes)))
			.withType(ImportiAdapter.class)
			.withSeparator(separator)
			.build();
		//int rowNum = 0;
		for (ImportiAdapter ia : iterable) {
			if("CUI".equalsIgnoreCase(ia.cui)) {
				// Intestazione, ignoro
				continue;
			}
			ia.validate();
			//System.out.println(rowNum + " ==> " + ia);
			//rowNum++;
		}
	}

	private void parseAcquisti(String suffix, char separator) throws Exception {
		URI uri = Thread.currentThread().getContextClassLoader().getResource("csv/CSI-Esigenze-Elaborate-Acquisti" + suffix + ".csv").toURI();
		Path path = Paths.get(uri);
		byte[] bytesBytes = Files.readAllBytes(path);
		CsvToBean<AcquistiAdapter> iterable = new CsvToBeanBuilder<AcquistiAdapter>(new InputStreamReader(new ByteArrayInputStream(bytesBytes)))
			.withType(AcquistiAdapter.class)
			.withSeparator(separator)
			.build();
		//int rowNum = 0;
		for (AcquistiAdapter ia : iterable) {
			if("CUI".equalsIgnoreCase(ia.cui)) {
				// Intestazione, ignoro
				continue;
			}
			ia.validate();
			//System.out.println(rowNum + " ==> " + ia);
			//rowNum++;
		}
	}


	public static class ImportiAdapter implements CsvAdapter {
		@CsvBindByPosition(position = 0) String cui;
		@CsvBindByPosition(position = 1) String tipoRisorsa;
		@CsvBindByPosition(position = 2) String codiceRisorsa;
		@CsvBindByPosition(position = 3) String importoAnnoString;
		@CsvBindByPosition(position = 4) String importoAnnoPiu1String;
		@CsvBindByPosition(position = 5) String importoAnniSuccessiviString;

		BigDecimal importoAnno;
		BigDecimal importoAnnoPiu1;
		BigDecimal importoAnniSuccessivi;

		@Override
		public boolean validate() {
			importoAnno = parseBigDecimal(importoAnnoString);
			importoAnnoPiu1 = parseBigDecimal(importoAnnoPiu1String);
			importoAnniSuccessivi = parseBigDecimal(importoAnniSuccessiviString);
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ImportiAdapter [cui=").append(cui).append(", tipoRisorsa=").append(tipoRisorsa)
					.append(", codiceRisorsa=").append(codiceRisorsa).append(", importoAnno=").append(importoAnno)
					.append(", importoAnnoPiu1=").append(importoAnnoPiu1).append(", importoAnniSuccessivi=")
					.append(importoAnniSuccessivi).append("]");
			return builder.toString();
		}

	}

	public static class AcquistiAdapter implements CsvAdapter {
		@CsvBindByPosition(position = 0) String cui;
		@CsvBindByPosition(position = 1) String annoAvvioString;
		@CsvBindByPosition(position = 2) String cup;
		@CsvBindByPosition(position = 3) String acquistoRicompreso;
		@CsvBindByPosition(position = 4) String cuiInterventoRicompreso;
		@CsvBindByPosition(position = 5) String lottoFunzionaleString;
		@CsvBindByPosition(position = 6) String durataMesiString;
		@CsvBindByPosition(position = 7) String nuovoAffidamentoString;
		@CsvBindByPosition(position = 8) String codiceAusa;
		@CsvBindByPosition(position = 9) String acquistoVariato;
		@CsvBindByPosition(position = 10) String codiceFiscaleRup;
		@CsvBindByPosition(position = 11) String descrizioneAcquisto;
		@CsvBindByPosition(position = 12) String settoreInterventi;
		@CsvBindByPosition(position = 13) String cpv;
		@CsvBindByPosition(position = 14) String nuts;
		@CsvBindByPosition(position = 15) String priorita;
		@CsvBindByPosition(position = 16) String modalitaAffidamento;
		@CsvBindByPosition(position = 17) String stato;
		@CsvBindByPosition(position = 18) String settore;
		@CsvBindByPosition(position = 19) String tipoRisorsa;
		@CsvBindByPosition(position = 20) String codiceRisorsa;
		@CsvBindByPosition(position = 21) String annoString;
		@CsvBindByPosition(position = 22) String importiString;

		Integer annoAvvio;
		boolean lottoFunzionale;
		Integer durataMesi;
		boolean nuovoAffidamento;
		Integer anno;
		BigDecimal importi;

		@Override
		public boolean validate() {
			annoAvvio = parseInteger(annoAvvioString);
			durataMesi = parseInteger(durataMesiString);
			anno = parseInteger(annoString);
			lottoFunzionale = parseBoolean(lottoFunzionaleString);
			nuovoAffidamento = parseBoolean(nuovoAffidamentoString);
			importi = parseBigDecimal(importiString);
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AcquistiAdapter [cui=").append(cui).append(", cup=").append(cup)
					.append(", acquistoRicompreso=").append(acquistoRicompreso).append(", cuiInterventoRicompreso=")
					.append(cuiInterventoRicompreso).append(", codiceAusa=").append(codiceAusa)
					.append(", acquistoVariato=").append(acquistoVariato).append(", codiceFiscaleRup=")
					.append(codiceFiscaleRup).append(", descrizioneAcquisto=").append(descrizioneAcquisto)
					.append(", settoreInterventi=").append(settoreInterventi).append(", cpv=").append(cpv)
					.append(", nuts=").append(nuts).append(", priorita=").append(priorita)
					.append(", modalitaAffidamento=").append(modalitaAffidamento).append(", stato=").append(stato)
					.append(", settore=").append(settore).append(", tipoRisorsa=").append(tipoRisorsa)
					.append(", codiceRisorsa=").append(codiceRisorsa).append(", annoAvvio=").append(annoAvvio)
					.append(", lottoFunzionale=").append(lottoFunzionale).append(", durataMesi=").append(durataMesi)
					.append(", nuovoAffidamento=").append(nuovoAffidamento).append(", anno=").append(anno)
					.append(", importi=").append(importi).append("]");
			return builder.toString();
		}



	}

	private interface CsvAdapter {
		boolean validate();
		default Integer parseInteger(String value) {
			return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
		}
		default boolean parseBoolean(String value) {
			return "SI".equalsIgnoreCase(value);
		}
		default BigDecimal parseBigDecimal(String value) {
			return StringUtils.isEmpty(value) ? null : new BigDecimal(value.replaceAll(",", "."));
		}
	}
}
