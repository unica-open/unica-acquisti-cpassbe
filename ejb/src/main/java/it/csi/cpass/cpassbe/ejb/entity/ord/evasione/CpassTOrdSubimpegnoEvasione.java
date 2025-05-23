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
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_subimpegno_evasione database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_subimpegno_evasione")
@NamedQuery(name="CpassTOrdSubimpegnoEvasione.findAll", query="SELECT c FROM CpassTOrdSubimpegnoEvasione c")
public class CpassTOrdSubimpegnoEvasione extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_subimpegno_evasione");

	@Id
	@Column(name="subimpegno_evasione_id")
	private UUID subimpegnoEvasioneId;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;

	@Column(name="importo_ripartito")
	private BigDecimal importoRipartito;

	@Column(name="importo_sospeso")
	private BigDecimal importoSospeso;

	@Column(name="importo_liquidato")
	private BigDecimal importoLiquidato;

	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;

	@Column(name="data_sospensione")
	private Date dataSospensione;

	// bi-directional many-to-one association to CpassDOrdCausaleSospensioneEvasione
	@ManyToOne
	@JoinColumn(name = "causale_sospensione_id")
	private CpassDOrdCausaleSospensioneEvasione cpassDOrdCausaleSospensioneEvasione;

	//bi-directional many-to-one association to CpassTOrdImpegnoEvasione
	@ManyToOne
	@JoinColumn(name="impegno_evasione_id")
	private CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione;

	//bi-directional many-to-one association to CpassTOrdSubimpegnoOrdine
	@ManyToOne
	@JoinColumn(name="subimpegno_ordine_id")
	private CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine;

	//bi-directional many-to-one association to CpassTSubimpegno
	@ManyToOne
	@JoinColumn(name="subimpegno_id")
	private CpassTSubimpegno cpassTSubimpegno;

	public CpassTOrdSubimpegnoEvasione() {
	}

	public UUID getSubimpegnoEvasioneId() {
		return this.subimpegnoEvasioneId;
	}

	public void setSubimpegnoEvasioneId(UUID subimpegnoEvasioneId) {
		this.subimpegnoEvasioneId = subimpegnoEvasioneId;
	}

	public Integer getImpegnoAnno() {
		return this.impegnoAnno;
	}

	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	public Integer getImpegnoAnnoEsercizio() {
		return this.impegnoAnnoEsercizio;
	}

	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	public Integer getImpegnoNumero() {
		return this.impegnoNumero;
	}

	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	public Integer getSubimpegnoAnno() {
		return this.subimpegnoAnno;
	}

	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
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

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	public CpassTOrdImpegnoEvasione getCpassTOrdImpegnoEvasione() {
		return this.cpassTOrdImpegnoEvasione;
	}

	public void setCpassTOrdImpegnoEvasione(CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione) {
		this.cpassTOrdImpegnoEvasione = cpassTOrdImpegnoEvasione;
	}

	public CpassTOrdSubimpegnoOrdine getCpassTOrdSubimpegnoOrdine() {
		return this.cpassTOrdSubimpegnoOrdine;
	}

	public void setCpassTOrdSubimpegnoOrdine(CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine) {
		this.cpassTOrdSubimpegnoOrdine = cpassTOrdSubimpegnoOrdine;
	}

	public CpassTSubimpegno getCpassTSubimpegno() {
		return this.cpassTSubimpegno;
	}

	public void setCpassTSubimpegno(CpassTSubimpegno cpassTSubimpegno) {
		this.cpassTSubimpegno = cpassTSubimpegno;
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
	 * @return the cpassDOrdCausaleSospensioneEvasione
	 */
	public CpassDOrdCausaleSospensioneEvasione getCpassDOrdCausaleSospensioneEvasione() {
		return cpassDOrdCausaleSospensioneEvasione;
	}
	/**
	 * @param cpassDOrdCausaleSospensioneEvasione the cpassDOrdCausaleSospensioneEvasione to set
	 */
	public void setCpassDOrdCausaleSospensioneEvasione(
			CpassDOrdCausaleSospensioneEvasione cpassDOrdCausaleSospensioneEvasione) {
		this.cpassDOrdCausaleSospensioneEvasione = cpassDOrdCausaleSospensioneEvasione;
	}

	@Override
	public UUID getId() {
		return subimpegnoEvasioneId;
	}

	@Override
	public void setId(UUID id) {
		subimpegnoEvasioneId = id;
	}

	@Override
	public void initId() {
		this.subimpegnoEvasioneId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdImpegnoEvasione.getId() +"|"+ subimpegnoAnno +"|"+ subimpegnoNumero);
	}
}
