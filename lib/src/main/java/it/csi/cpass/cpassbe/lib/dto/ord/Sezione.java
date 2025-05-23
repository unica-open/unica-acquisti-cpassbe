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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;


public class Sezione extends BaseAuditedDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sezioneCodice;
	private String sezioneDescrizione;
	private Ente ente;
	private Settore settore;

	private List<Utente> utenteSeziones;
	private List<RegoleSmistamentoRms> regoleSmistamentoRms;
	private List<RigaRms> rigaRms;

	/** Default constructor */
	public Sezione() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Sezione(Integer id) {
		super(id);
	}

	/**
	 * @return the sezioneCodice
	 */
	public String getSezioneCodice() {
		return sezioneCodice;
	}
	/**
	 * @param sezioneCodice the sezioneCodice to set
	 */
	public void setSezioneCodice(String sezioneCodice) {
		this.sezioneCodice = sezioneCodice;
	}
	/**
	 * @return the sezioneDescrizione
	 */
	public String getSezioneDescrizione() {
		return sezioneDescrizione;
	}
	/**
	 * @param sezioneDescrizione the sezioneDescrizione to set
	 */
	public void setSezioneDescrizione(String sezioneDescrizione) {
		this.sezioneDescrizione = sezioneDescrizione;
	}
	/**
	 * @return the utenteSeziones
	 */
	public List<Utente> getUtenteSeziones() {
		return utenteSeziones;
	}
	/**
	 * @param utenteSeziones the utenteSeziones to set
	 */
	public void setUtenteSeziones(List<Utente> utenteSeziones) {
		this.utenteSeziones = utenteSeziones;
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
	 * @return the regoleSmistamentoRms
	 */
	public List<RegoleSmistamentoRms> getRegoleSmistamentoRms() {
		return regoleSmistamentoRms;
	}
	/**
	 * @param regoleSmistamentoRms the regoleSmistamentoRms to set
	 */
	public void setRegoleSmistamentoRms(List<RegoleSmistamentoRms> regoleSmistamentoRms) {
		this.regoleSmistamentoRms = regoleSmistamentoRms;
	}
	/**
	 * @return the rigaRms
	 */
	public List<RigaRms> getRigaRms() {
		return rigaRms;
	}
	/**
	 * @param rigaRms the rigaRms to set
	 */
	public void setRigaRms(List<RigaRms> rigaRms) {
		this.rigaRms = rigaRms;
	}
	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}
	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}



}
