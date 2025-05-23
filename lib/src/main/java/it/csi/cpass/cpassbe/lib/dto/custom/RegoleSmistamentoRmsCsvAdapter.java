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



public class RegoleSmistamentoRmsCsvAdapter implements CsvAdapter {
	@CsvBindByPosition(position = 0) String odsCodice;
	@CsvBindByPosition(position = 1) String cpvCodice;
	@CsvBindByPosition(position = 2) String settoreRichiedente;
	@CsvBindByPosition(position = 3) String livelloCpv;
	@CsvBindByPosition(position = 4) String regolaCpv;
	@CsvBindByPosition(position = 5) String tuttaLaStruttura;
	@CsvBindByPosition(position = 6) String centroAcquisto;
	@CsvBindByPosition(position = 7) String sezioneAcquisto;
	@CsvBindByPosition(position = 8) String magazzino;


	/**
	 * @return the odsCodice
	 */
	public String getOdsCodice() {
		return odsCodice;
	}


	/**
	 * @param odsCodice the odsCodice to set
	 */
	public void setOdsCodice(String odsCodice) {
		this.odsCodice = odsCodice;
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
	 * @return the settoreRichiedente
	 */
	public String getSettoreRichiedente() {
		return settoreRichiedente;
	}


	/**
	 * @param settoreRichiedente the settoreRichiedente to set
	 */
	public void setSettoreRichiedente(String settoreRichiedente) {
		this.settoreRichiedente = settoreRichiedente;
	}


	/**
	 * @return the livelloCpv
	 */
	public String getLivelloCpv() {
		return livelloCpv;
	}


	/**
	 * @param livelloCpv the livelloCpv to set
	 */
	public void setLivelloCpv(String livelloCpv) {
		this.livelloCpv = livelloCpv;
	}


	/**
	 * @return the regolaCpv
	 */
	public String getRegolaCpv() {
		return regolaCpv;
	}


	/**
	 * @param regolaCpv the regolaCpv to set
	 */
	public void setRegolaCpv(String regolaCpv) {
		this.regolaCpv = regolaCpv;
	}


	/**
	 * @return the tuttaLaStruttura
	 */
	public String getTuttaLaStruttura() {
		return tuttaLaStruttura;
	}


	/**
	 * @param tuttaLaStruttura the tuttaLaStruttura to set
	 */
	public void setTuttaLaStruttura(String tuttaLaStruttura) {
		this.tuttaLaStruttura = tuttaLaStruttura;
	}


	/**
	 * @return the centroAcquisto
	 */
	public String getCentroAcquisto() {
		return centroAcquisto;
	}


	/**
	 * @param centroAcquisto the centroAcquisto to set
	 */
	public void setCentroAcquisto(String centroAcquisto) {
		this.centroAcquisto = centroAcquisto;
	}


	/**
	 * @return the sezioneAcquisto
	 */
	public String getSezioneAcquisto() {
		return sezioneAcquisto;
	}


	/**
	 * @param sezioneAcquisto the sezioneAcquisto to set
	 */
	public void setSezioneAcquisto(String sezioneAcquisto) {
		this.sezioneAcquisto = sezioneAcquisto;
	}


	/**
	 * @return the magazzino
	 */
	public String getMagazzino() {
		return magazzino;
	}


	/**
	 * @param magazzino the magazzino to set
	 */
	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegoleSmistamentoRmsCsvAdapter [cui=").append(odsCodice).append(", odsCodice=").append(odsCodice)
				.append(", cpvCodice=").append(cpvCodice).append(", cpvCodice=")
				;
		return builder.toString();
	}

	@Override
	public boolean validate() {
		return true;
	}



}
