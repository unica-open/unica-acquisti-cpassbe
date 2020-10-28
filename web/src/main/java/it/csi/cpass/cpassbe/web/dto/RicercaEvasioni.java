/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class RicercaEvasioni {

	private Integer numeroEvasioneDa;
	private Integer annoEvasioneDa;
	private Integer numeroEvasioneA;
	private Integer annoEvasioneA;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	
	private Integer annoOrdineDa;
	private Integer numeroOrdineDa;
	private Integer annoOrdineA;
	private Integer numeroOrdineA;
	private Date dataEmissioneDa;
	private Date dataEmissioneA;
	private Integer annoProvvedimento;
	private Integer numeroProvvedimento;
	
	private TestataEvasione testataEvasione;
	private DestinatarioEvasione destinatarioEvasione;
	private Impegno impegno;
	private Subimpegno subimpegno;
//	private RigaOrdine rigaOrdine;
	private OggettiSpesa oggettiSpesa;
	/**
	 * @return the numeroEvasioneDa
	 */
	public Integer getNumeroEvasioneDa() {
		return numeroEvasioneDa;
	}
	/**
	 * @param numeroEvasioneDa the numeroEvasioneDa to set
	 */
	public void setNumeroEvasioneDa(Integer numeroEvasioneDa) {
		this.numeroEvasioneDa = numeroEvasioneDa;
	}
	/**
	 * @return the annoEvasioneDa
	 */
	public Integer getAnnoEvasioneDa() {
		return annoEvasioneDa;
	}
	/**
	 * @param annoEvasioneDa the annoEvasioneDa to set
	 */
	public void setAnnoEvasioneDa(Integer annoEvasioneDa) {
		this.annoEvasioneDa = annoEvasioneDa;
	}
	/**
	 * @return the numeroEvasioneA
	 */
	public Integer getNumeroEvasioneA() {
		return numeroEvasioneA;
	}
	/**
	 * @param numeroEvasioneA the numeroEvasioneA to set
	 */
	public void setNumeroEvasioneA(Integer numeroEvasioneA) {
		this.numeroEvasioneA = numeroEvasioneA;
	}
	/**
	 * @return the annoEvasioneA
	 */
	public Integer getAnnoEvasioneA() {
		return annoEvasioneA;
	}
	/**
	 * @param annoEvasioneA the annoEvasioneA to set
	 */
	public void setAnnoEvasioneA(Integer annoEvasioneA) {
		this.annoEvasioneA = annoEvasioneA;
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
	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
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
	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}
	/**
	 * @return the annoOrdineDa
	 */
	public Integer getAnnoOrdineDa() {
		return annoOrdineDa;
	}
	/**
	 * @param annoOrdineDa the annoOrdineDa to set
	 */
	public void setAnnoOrdineDa(Integer annoOrdineDa) {
		this.annoOrdineDa = annoOrdineDa;
	}
	/**
	 * @return the numeroOrdineDa
	 */
	public Integer getNumeroOrdineDa() {
		return numeroOrdineDa;
	}
	/**
	 * @param numeroOrdineDa the numeroOrdineDa to set
	 */
	public void setNumeroOrdineDa(Integer numeroOrdineDa) {
		this.numeroOrdineDa = numeroOrdineDa;
	}
	/**
	 * @return the annoOrdineA
	 */
	public Integer getAnnoOrdineA() {
		return annoOrdineA;
	}
	/**
	 * @param annoOrdineA the annoOrdineA to set
	 */
	public void setAnnoOrdineA(Integer annoOrdineA) {
		this.annoOrdineA = annoOrdineA;
	}
	/**
	 * @return the numeroOrdineA
	 */
	public Integer getNumeroOrdineA() {
		return numeroOrdineA;
	}
	/**
	 * @param numeroOrdineA the numeroOrdineA to set
	 */
	public void setNumeroOrdineA(Integer numeroOrdineA) {
		this.numeroOrdineA = numeroOrdineA;
	}
	/**
	 * @return the dataEmissioneDa
	 */
	public Date getDataEmissioneDa() {
		return dataEmissioneDa;
	}
	/**
	 * @param dataEmissioneDa the dataEmissioneDa to set
	 */
	public void setDataEmissioneDa(Date dataEmissioneDa) {
		this.dataEmissioneDa = dataEmissioneDa;
	}
	/**
	 * @return the dataEmissioneA
	 */
	public Date getDataEmissioneA() {
		return dataEmissioneA;
	}
	/**
	 * @param dataEmissioneA the dataEmissioneA to set
	 */
	public void setDataEmissioneA(Date dataEmissioneA) {
		this.dataEmissioneA = dataEmissioneA;
	}
	
	
	/**
	 * @return the annoProvvedimento
	 */
	public Integer getAnnoProvvedimento() {
		return annoProvvedimento;
	}
	/**
	 * @param annoProvvedimento the annoProvvedimento to set
	 */
	public void setAnnoProvvedimento(Integer annoProvvedimento) {
		this.annoProvvedimento = annoProvvedimento;
	}
	/**
	 * @return the numeroProvvedimento
	 */
	public Integer getNumeroProvvedimento() {
		return numeroProvvedimento;
	}
	/**
	 * @param numeroProvvedimento the numeroProvvedimento to set
	 */
	public void setNumeroProvvedimento(Integer numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}
	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}
	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}
	/**
	 * @return the destinatario
	 */
	public DestinatarioEvasione getDestinatarioEvasione() {
		return destinatarioEvasione;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(DestinatarioEvasione destinatarioEvasione) {
		this.destinatarioEvasione = destinatarioEvasione;
	}
	/**
	 * @return the impegno
	 */
	public Impegno getImpegno() {
		return impegno;
	}
	/**
	 * @param impegno the impegno to set
	 */
	public void setImpegno(Impegno impegno) {
		this.impegno = impegno;
	}
	/**
	 * @return the subimpegno
	 */
	public Subimpegno getSubimpegno() {
		return subimpegno;
	}
	/**
	 * @param subimpegno the subimpegno to set
	 */
	public void setSubimpegno(Subimpegno subimpegno) {
		this.subimpegno = subimpegno;
	}
	/**
	 * @return the oggetiSpesa
	 */
	public OggettiSpesa getOggettiSpesa() {
		return oggettiSpesa;
	}
	/**
	 * @param oggetiSpesa the oggetiSpesa to set
	 */
	public void setOggetiSpesa(OggettiSpesa oggettiSpesa) {
		this.oggettiSpesa = oggettiSpesa;
	}

}
