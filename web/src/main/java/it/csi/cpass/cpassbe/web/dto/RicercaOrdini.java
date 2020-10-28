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
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Intervento da copia
 */
public class RicercaOrdini {

	private Integer annoOrdineDa;
	private Integer numeroOrdineDa;
	private Integer annoOrdineA;
	private Integer numeroOrdineA;
	private Date dataEmissioneDa;
	private Date dataEmissioneA;
	private TestataOrdine testataOrdine;
	private Destinatario destinatario;
	private Impegno impegno;
	private Subimpegno subimpegno;
	private RigaOrdine rigaOrdine;

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
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}
	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}
	/**
	 * @return the destinatario
	 */
	public Destinatario getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
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
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}
	/**
	 * @param rigaOrdine the rigaOrdine to set
	 */
	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}
}
