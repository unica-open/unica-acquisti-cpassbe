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


import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Request for reading the Rms paginated
 */
public class GetRicercaRmsRequest extends BasePagedRequest {

	private final Integer annoRmsDa;
	private final Integer numeroRmsDa;
	private final Integer annoRmsA;
	private final Integer numeroRmsA;
	private final Date dataInserimentoDa;
	private final Date dataInserimentoA;
	private final TestataRms testataRms;
	private final RigaRms rigaRms;
	private final Settore settoreEmittente;
	private final Settore settore;
	private final SettoreIndirizzo settoreIndirizzo;


	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */
	public GetRicercaRmsRequest(Integer page, Integer size, String sort, String direction, Integer annoRmsDa, Integer numeroRmsDa, Integer annoRmsA, Integer numeroRmsA, Date dataInserimentoDa, Date dataInserimentoA, TestataRms testataRms, RigaRms rigaRms, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo) {
		super(size, page, sort, direction);
		this.annoRmsDa = annoRmsDa;
		this.numeroRmsDa = numeroRmsDa;
		this.annoRmsA = annoRmsA;
		this.numeroRmsA = numeroRmsA;
		this.dataInserimentoDa = dataInserimentoDa;
		this.dataInserimentoA = dataInserimentoA;
		this.testataRms = testataRms;
		this.rigaRms = rigaRms;
		this.settoreEmittente = settoreEmittente;
		this.settore = settore;
		this.settoreIndirizzo = settoreIndirizzo;
	}

	/**
	 * @return the annoRmsDa
	 */
	public Integer getAnnoRmsDa() {
		return annoRmsDa;
	}

	/**
	 * @return the numeroRmsDa
	 */
	public Integer getNumeroRmsDa() {
		return numeroRmsDa;
	}

	/**
	 * @return the annoRmsA
	 */
	public Integer getAnnoRmsA() {
		return annoRmsA;
	}

	/**
	 * @return the numeroRmsA
	 */
	public Integer getNumeroRmsA() {
		return numeroRmsA;
	}

	/**
	 * @return the dataInserimentoDa
	 */
	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	/**
	 * @return the dataInserimentoA
	 */
	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	/**
	 * @return the testataRms
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}

	/**
	 * @return the rigaRms
	 */
	public RigaRms getRigaRms() {
		return rigaRms;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the settoreIndirizzo
	 */
	public SettoreIndirizzo getSettoreIndirizzo() {
		return settoreIndirizzo;
	}

	/**
	 * @return the settoreEmittente
	 */
	public Settore getSettoreEmittente() {
		return settoreEmittente;
	}






}
