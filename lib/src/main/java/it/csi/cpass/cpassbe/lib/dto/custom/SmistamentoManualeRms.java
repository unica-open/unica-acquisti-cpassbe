/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.custom;

import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class SmistamentoManualeRms {

	private Settore settore;
	private Sezione sezione;
	private Magazzino magazzino;
	private List<RigaRms> listaRigheRms;
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
	 * @return the listaRigheRms
	 */
	public List<RigaRms> getListaRigheRms() {
		return listaRigheRms;
	}
	/**
	 * @param listaRigheRms the listaRigheRms to set
	 */
	public void setListaRigheRms(List<RigaRms> listaRigheRms) {
		this.listaRigheRms = listaRigheRms;
	}



}
