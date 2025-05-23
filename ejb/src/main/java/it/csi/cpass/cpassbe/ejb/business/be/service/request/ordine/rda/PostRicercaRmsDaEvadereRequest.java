/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class PostRicercaRmsDaEvadereRequest extends BasePagedRequest {

	private final Integer annoRmsDa;
	private final Integer numeroRmsDa;
	private final Integer annoRmsA;
	private final Integer numeroRmsA;
	private final Date dataInserimentoDa;
	private final Date dataInserimentoA;
	private final Date dataAutorizzazioneDa;
	private final Date dataAutorizzazioneoA;
	private final TestataRms testataRms;
	private final RigaRms rigaRms;
	private final Sezione sezione;
	private final String statoRigaRms;

	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */
	public PostRicercaRmsDaEvadereRequest(Integer page, Integer size, String sort, String direction, Integer annoRmsDa, Integer numeroRmsDa, Integer annoRmsA, Integer numeroRmsA, Date dataInserimentoDa, Date dataInserimentoA, Date dataAutorizzazioneDa, Date dataAutorizzazioneA, TestataRms testataRms, RigaRms rigaRms, Sezione sezione, String statoRigaRms) {
		super(size, page, sort, direction);
		this.annoRmsDa = annoRmsDa;
		this.numeroRmsDa = numeroRmsDa;
		this.annoRmsA = annoRmsA;
		this.numeroRmsA = numeroRmsA;
		this.dataInserimentoDa = dataInserimentoDa;
		this.dataInserimentoA = dataInserimentoA;
		this.dataAutorizzazioneDa = dataAutorizzazioneDa;
		this.dataAutorizzazioneoA = dataAutorizzazioneA;
		this.testataRms = testataRms;
		this.rigaRms = rigaRms;
		this.sezione = sezione;
		this.statoRigaRms = statoRigaRms;
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
	 * @return the dataAutorizzazioneDa
	 */
	public Date getDataAutorizzazioneDa() {
		return dataAutorizzazioneDa;
	}

	/**
	 * @return the dataAutorizzazioneoA
	 */
	public Date getDataAutorizzazioneoA() {
		return dataAutorizzazioneoA;
	}

	/**
	 * @return the testataRms
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}

	/**
	 * @return the statoRigaRms
	 */
	public String getStatoRigaRms() {
		return statoRigaRms;
	}

	/**
	 * @return the rigaRms
	 */
	public RigaRms getRigaRms() {
		return rigaRms;
	}

	/**
	 * @return the sezione
	 */
	public Sezione getSezione() {
		return sezione;
	}




}