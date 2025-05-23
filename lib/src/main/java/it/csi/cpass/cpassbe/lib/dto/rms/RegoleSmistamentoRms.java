/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.rms;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;

public class RegoleSmistamentoRms extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String centroAcquistoCodice;

	private String cpvCodice;

	private Integer livelloCpv;

	private String magazzinoCodice;

	private String oggettiSpesaCodice;

	private String regolaCpv;

	private String settoreCodice;

	private String sezioneAcquistoCodice;

	private Boolean tuttaLaStruttura;

	private Ente ente;

	private Magazzino magazzino;

	private Sezione sezione;

	private Settore settoreAcquisto;


	public RegoleSmistamentoRms() {
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
	 * @return the livelloCpv
	 */
	public Integer getLivelloCpv() {
		return livelloCpv;
	}

	/**
	 * @param livelloCpv the livelloCpv to set
	 */
	public void setLivelloCpv(Integer livelloCpv) {
		this.livelloCpv = livelloCpv;
	}

	/**
	 * @return the oggettiSpesaCodice
	 */
	public String getOggettiSpesaCodice() {
		return oggettiSpesaCodice;
	}

	/**
	 * @param oggettiSpesaCodice the oggettiSpesaCodice to set
	 */
	public void setOggettiSpesaCodice(String oggettiSpesaCodice) {
		this.oggettiSpesaCodice = oggettiSpesaCodice;
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

	/**
	 * @return the tuttaLaStruttura
	 */
	public Boolean getTuttaLaStruttura() {
		return tuttaLaStruttura;
	}

	/**
	 * @param tuttaLaStruttura the tuttaLaStruttura to set
	 */
	public void setTuttaLaStruttura(Boolean tuttaLaStruttura) {
		this.tuttaLaStruttura = tuttaLaStruttura;
	}

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	/**
	 * @return the magazzino
	 */
	public Magazzino getMagazzino() {
		return magazzino;
	}

	/**
	 * @param magazzino the magazzino to set
	 */
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}

	/**
	 * @return the sezione
	 */
	public Sezione getSezione() {
		return sezione;
	}

	/**
	 * @param sezione the sezione to set
	 */
	public void setSezione(Sezione sezione) {
		this.sezione = sezione;
	}

	/**
	 * @return the centroAcquistoCodice
	 */
	public String getCentroAcquistoCodice() {
		return centroAcquistoCodice;
	}

	/**
	 * @param centroAcquistoCodice the centroAcquistoCodice to set
	 */
	public void setCentroAcquistoCodice(String centroAcquistoCodice) {
		this.centroAcquistoCodice = centroAcquistoCodice;
	}

	/**
	 * @return the magazzinoCodice
	 */
	public String getMagazzinoCodice() {
		return magazzinoCodice;
	}

	/**
	 * @param magazzinoCodice the magazzinoCodice to set
	 */
	public void setMagazzinoCodice(String magazzinoCodice) {
		this.magazzinoCodice = magazzinoCodice;
	}

	/**
	 * @return the sezioneAcquistoCodice
	 */
	public String getSezioneAcquistoCodice() {
		return sezioneAcquistoCodice;
	}

	/**
	 * @param sezioneAcquistoCodice the sezioneAcquistoCodice to set
	 */
	public void setSezioneAcquistoCodice(String sezioneAcquistoCodice) {
		this.sezioneAcquistoCodice = sezioneAcquistoCodice;
	}

	/**
	 * @return the settoreAcquisto
	 */
	public Settore getSettoreAcquisto() {
		return settoreAcquisto;
	}

	/**
	 * @param settoreAcquisto the settoreAcquisto to set
	 */
	public void setSettoreAcquisto(Settore settoreAcquisto) {
		this.settoreAcquisto = settoreAcquisto;
	}

}
