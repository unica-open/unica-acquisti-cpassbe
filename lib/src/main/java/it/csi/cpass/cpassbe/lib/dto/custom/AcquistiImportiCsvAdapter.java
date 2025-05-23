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



public class AcquistiImportiCsvAdapter implements CsvAdapter {
	@CsvBindByPosition(position = 0) String cui;
	@CsvBindByPosition(position = 1) String tipoRisorsa;
	@CsvBindByPosition(position = 2) String codiceRisorsa;
	@CsvBindByPosition(position = 3) String importoAnnoString;
	@CsvBindByPosition(position = 4) String importoAnnoPiu1String;
	@CsvBindByPosition(position = 5) String importoAnnoPiu2String;
	@CsvBindByPosition(position = 6) String importoAnniSuccessiviString;
	@CsvBindByPosition(position = 7) String codiceInterno;



	@Override
	public boolean validate() {
		// valido a posteriori
		return true;
	}


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
	 * @return the tipoRisorsa
	 */
	public String getTipoRisorsa() {
		return tipoRisorsa;
	}


	/**
	 * @param tipoRisorsa the tipoRisorsa to set
	 */
	public void setTipoRisorsa(String tipoRisorsa) {
		this.tipoRisorsa = tipoRisorsa;
	}


	/**
	 * @return the codiceRisorsa
	 */
	public String getCodiceRisorsa() {
		return codiceRisorsa;
	}


	/**
	 * @param codiceRisorsa the codiceRisorsa to set
	 */
	public void setCodiceRisorsa(String codiceRisorsa) {
		this.codiceRisorsa = codiceRisorsa;
	}


	/**
	 * @return the importoAnnoString
	 */
	public String getImportoAnnoString() {
		return importoAnnoString;
	}


	/**
	 * @param importoAnnoString the importoAnnoString to set
	 */
	public void setImportoAnnoString(String importoAnnoString) {
		this.importoAnnoString = importoAnnoString;
	}


	/**
	 * @return the importoAnnoPiu1String
	 */
	public String getImportoAnnoPiu1String() {
		return importoAnnoPiu1String;
	}


	/**
	 * @param importoAnnoPiu1String the importoAnnoPiu1String to set
	 */
	public void setImportoAnnoPiu1String(String importoAnnoPiu1String) {
		this.importoAnnoPiu1String = importoAnnoPiu1String;
	}


	/**
	 * @return the importoAnniSuccessiviString
	 */
	public String getImportoAnniSuccessiviString() {
		return importoAnniSuccessiviString;
	}


	/**
	 * @param importoAnniSuccessiviString the importoAnniSuccessiviString to set
	 */
	public void setImportoAnniSuccessiviString(String importoAnniSuccessiviString) {
		this.importoAnniSuccessiviString = importoAnniSuccessiviString;
	}


	/**
	 * @return the codiceInterno
	 */
	public String getCodiceInterno() {
		return codiceInterno;
	}


	/**
	 * @param codiceInterno the codiceInterno to set
	 */
	public void setCodiceInterno(String codiceInterno) {
		this.codiceInterno = codiceInterno;
	}



	/**
	 * @return the importoAnnoPiu2String
	 */
	public String getImportoAnnoPiu2String() {
		return importoAnnoPiu2String;
	}


	/**
	 * @param importoAnnoPiu2String the importoAnnoPiu2String to set
	 */
	public void setImportoAnnoPiu2String(String importoAnnoPiu2String) {
		this.importoAnnoPiu2String = importoAnnoPiu2String;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImportiAdapter [cui=").append(cui).append(", tipoRisorsa=").append(tipoRisorsa)
				.append(", codiceRisorsa=").append(codiceRisorsa).append(", importoAnno=")
				.append(", importoAnnoPiu1=").append("]");
		return builder.toString();
	}

}