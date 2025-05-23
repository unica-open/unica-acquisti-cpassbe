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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

/**
 * Request for putting the TestataRms
 */
public class PutSmistamentoManualeRmsRequest implements BaseRequest {

	private final Settore settore;
	private final Sezione sezione;
	private final Magazzino magazzino;
	private final List<RigaRms> listaRigheRms;

	/**
	 * Constructor
	 * @param testataRms the testataRms
	 */
	public PutSmistamentoManualeRmsRequest(Settore settore,Sezione sezione,Magazzino magazzino,List<RigaRms> listaRigheRms) {
		this.settore = settore;
		this.sezione = sezione;
		this.magazzino = magazzino;
		this.listaRigheRms = listaRigheRms;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the sezione
	 */
	public Sezione getSezione() {
		return sezione;
	}


	/**
	 * @return the magazzino
	 */
	public Magazzino getMagazzino() {
		return magazzino;
	}


	/**
	 * @return the listaRigheRms
	 */
	public List<RigaRms> getListaRigheRms() {
		return listaRigheRms;
	}


}
