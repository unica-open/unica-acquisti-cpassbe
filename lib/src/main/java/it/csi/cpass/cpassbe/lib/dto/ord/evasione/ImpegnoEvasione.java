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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;

public class ImpegnoEvasione extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dataSospensione;

	private Integer impegnoAnno;
	private Integer impegnoAnnoEsercizio;
	private Integer impegnoNumero;
	private Integer impegnoProgressivo;

	private BigDecimal importoRipartito;
	private BigDecimal importoSospeso;
	private BigDecimal importoLiquidato;

	private Impegno impegno;
	private ImpegnoOrdine impegnoOrdine;

	private RigaEvasione rigaEvasione;

	private List<SubimpegnoEvasione> subimpegnoEvasiones = new ArrayList<>();

	private CausaleSospensioneEvasione causaleSospensioneEvasione;

	private BigDecimal totaleRipartibile;

	public ImpegnoEvasione() {
	}

	/**
	 * @return the dataSospensione
	 */
	public Date getDataSospensione() {
		return dataSospensione;
	}

	/**
	 * @param dataSospensione the dataSospensione to set
	 */
	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	/**
	 * @return the impegnoAnno
	 */
	public Integer getImpegnoAnno() {
		return impegnoAnno;
	}

	/**
	 * @param impegnoAnno the impegnoAnno to set
	 */
	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	/**
	 * @return the impegnoAnnoEsercizio
	 */
	public Integer getImpegnoAnnoEsercizio() {
		return impegnoAnnoEsercizio;
	}

	/**
	 * @param impegnoAnnoEsercizio the impegnoAnnoEsercizio to set
	 */
	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	/**
	 * @return the impegnoNumero
	 */
	public Integer getImpegnoNumero() {
		return impegnoNumero;
	}

	/**
	 * @param impegnoNumero the impegnoNumero to set
	 */
	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	/**
	 * @return the impegnoProgressivo
	 */
	public Integer getImpegnoProgressivo() {
		return impegnoProgressivo;
	}

	/**
	 * @param impegnoProgressivo the impegnoProgressivo to set
	 */
	public void setImpegnoProgressivo(Integer impegnoProgressivo) {
		this.impegnoProgressivo = impegnoProgressivo;
	}

	/**
	 * @return the importoRipartito
	 */
	public BigDecimal getImportoRipartito() {
		return importoRipartito;
	}

	/**
	 * @param importoRipartito the importoRipartito to set
	 */
	public void setImportoRipartito(BigDecimal importoRipartito) {
		this.importoRipartito = importoRipartito;
	}

	/**
	 * @return the importoSospeso
	 */
	public BigDecimal getImportoSospeso() {
		return importoSospeso;
	}

	/**
	 * @param importoSospeso the importoSospeso to set
	 */
	public void setImportoSospeso(BigDecimal importoSospeso) {
		this.importoSospeso = importoSospeso;
	}

	/**
	 * @return the importoLiquidato
	 */
	public BigDecimal getImportoLiquidato() {
		return importoLiquidato;
	}

	/**
	 * @param importoLiquidato the importoLiquidato to set
	 */
	public void setImportoLiquidato(BigDecimal importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
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
	 * @return the rigaEvasione
	 */
	public RigaEvasione getRigaEvasione() {
		return rigaEvasione;
	}

	/**
	 * @param rigaEvasione the rigaEvasione to set
	 */
	public void setRigaEvasione(RigaEvasione rigaEvasione) {
		this.rigaEvasione = rigaEvasione;
	}

	/**
	 * @return the subimpegnoEvasiones
	 */
	public List<SubimpegnoEvasione> getSubimpegnoEvasiones() {
		return subimpegnoEvasiones;
	}

	/**
	 * @param subimpegnoEvasiones the subimpegnoEvasiones to set
	 */
	public void setSubimpegnoEvasiones(List<SubimpegnoEvasione> subimpegnoEvasiones) {
		this.subimpegnoEvasiones = subimpegnoEvasiones;
	}

	/**
	 * @return the impegnoOrdine
	 */
	public ImpegnoOrdine getImpegnoOrdine() {
		return impegnoOrdine;
	}

	/**
	 * @param impegnoOrdine the impegnoOrdine to set
	 */
	public void setImpegnoOrdine(ImpegnoOrdine impegnoOrdine) {
		this.impegnoOrdine = impegnoOrdine;
	}

	/**
	 * @return the causaleSospensioneEvasione
	 */
	public CausaleSospensioneEvasione getCausaleSospensioneEvasione() {
		return causaleSospensioneEvasione;
	}

	/**
	 * @param causaleSospensioneEvasione the causaleSospensioneEvasione to set
	 */
	public void setCausaleSospensioneEvasione(CausaleSospensioneEvasione causaleSospensioneEvasione) {
		this.causaleSospensioneEvasione = causaleSospensioneEvasione;
	}

	/**
	 * @return the totaleRipartibile
	 */
	public BigDecimal getTotaleRipartibile() {
		return totaleRipartibile;
	}

	/**
	 * @param totaleRipartibile the totaleRipartibile to set
	 */
	public void setTotaleRipartibile(BigDecimal totaleRipartibile) {
		this.totaleRipartibile = totaleRipartibile;
	}

}
