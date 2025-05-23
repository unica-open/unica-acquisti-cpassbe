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
package it.csi.cpass.cpassbe.web.dto;

import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class RicercaRmsDaEvadere {

	private Integer annoRmsDa;
	private Integer numeroRmsDa;
	private Integer annoRmsA;
	private Integer numeroRmsA;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	private Date dataAutorizzazioneDa;
	private Date dataAutorizzazioneA;
	private TestataRms testataRms;
	private RigaRms rigaRms;
	private Sezione sezione;
	private String statoRigaRms;

	/**
	 * @return the annoRmsDa
	 */
	public Integer getAnnoRmsDa() {
		return annoRmsDa;
	}
	/**
	 * @param annoRmsDa the annoRmsDa to set
	 */
	public void setAnnoRmsDa(Integer annoOrdineDa) {
		this.annoRmsDa = annoOrdineDa;
	}
	/**
	 * @return the numeroRmsDa
	 */
	public Integer getNumeroRmsDa() {
		return numeroRmsDa;
	}
	/**
	 * @param numeroRmsDa the numeroRmsDa to set
	 */
	public void setNumeroRmsDa(Integer numeroOrdineDa) {
		this.numeroRmsDa = numeroOrdineDa;
	}
	/**
	 * @return the annoRmsA
	 */
	public Integer getAnnoRmsA() {
		return annoRmsA;
	}
	/**
	 * @param annoRmsA the annoRmsA to set
	 */
	public void setAnnoRmsA(Integer annoOrdineA) {
		this.annoRmsA = annoOrdineA;
	}
	/**
	 * @return the numeroRmsA
	 */
	public Integer getNumeroRmsA() {
		return numeroRmsA;
	}
	/**
	 * @param numeroRmsA the numeroRmsA to set
	 */
	public void setNumeroRmsA(Integer numeroOrdineA) {
		this.numeroRmsA = numeroOrdineA;
	}
	/**
	 * @return the dataInserimentoDa
	 */
	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}
	/**
	 * @param dataInserimentoDa the dataInserimentoDa to set
	 */
	public void setDataInserimentoDa(Date dataEmissioneDa) {
		this.dataInserimentoDa = dataEmissioneDa;
	}
	/**
	 * @return the dataInserimentoA
	 */
	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}
	/**
	 * @param dataInserimentoA the dataInserimentoA to set
	 */
	public void setDataInserimentoA(Date dataEmissioneA) {
		this.dataInserimentoA = dataEmissioneA;
	}
	/**
	 * @return the dataAutorizzazioneDa
	 */
	public Date getDataAutorizzazioneDa() {
		return dataAutorizzazioneDa;
	}
	/**
	 * @param dataAutorizzazioneDa the dataAutorizzazioneDa to set
	 */
	public void setDataAutorizzazioneDa(Date dataAutorizzazioneDa) {
		this.dataAutorizzazioneDa = dataAutorizzazioneDa;
	}
	/**
	 * @return the dataAutorizzazioneA
	 */
	public Date getDataAutorizzazioneA() {
		return dataAutorizzazioneA;
	}
	/**
	 * @param dataAutorizzazioneA the dataAutorizzazioneA to set
	 */
	public void setDataAutorizzazioneA(Date dataAutorizzazioneA) {
		this.dataAutorizzazioneA = dataAutorizzazioneA;
	}
	/**
	 * @return the testataRms
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}
	/**
	 * @param testataRms the testataRms to set
	 */
	public void setTestataRms(TestataRms testataRms) {
		this.testataRms = testataRms;
	}
	/**
	 * @return the rigaRms
	 */
	public RigaRms getRigaRms() {
		return rigaRms;
	}
	/**
	 * @param rigaRms the rigaRms to set
	 */
	public void setRigaRms(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
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
	 * @return the statoRigaRms
	 */
	public String getStatoRigaRms() {
		return statoRigaRms;
	}
	/**
	 * @param statoRigaRms the statoRigaRms to set
	 */
	public void setStatoRigaRms(String statoRigaRms) {
		this.statoRigaRms = statoRigaRms;
	}



}
