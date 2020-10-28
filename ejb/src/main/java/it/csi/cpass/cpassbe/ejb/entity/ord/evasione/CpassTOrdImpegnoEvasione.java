/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_impegno_evasione database table.
 * 
 */
@Entity
@Table(name="cpass_t_ord_impegno_evasione")
@NamedQuery(name="CpassTOrdImpegnoEvasione.findAll", query="SELECT c FROM CpassTOrdImpegnoEvasione c")
public class CpassTOrdImpegnoEvasione  extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_impegno_evasione");

	@Id
	@Column(name="impegno_evasione_id")
	private UUID impegnoEvasioneId;

	@Column(name="data_sospensione")
	private Date dataSospensione;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="impegno_progressivo")
	private Integer impegnoProgressivo;

	@Column(name="importo_ripartito")
	private BigDecimal importoRipartito;


	@Column(name="importo_liquidato")
	private BigDecimal importoLiquidato;

	@Column(name="importo_sospeso")
	private BigDecimal importoSospeso;

	// bi-directional many-to-one association to CpassDOrdCausaleSospensioneEvasione
	@ManyToOne
	@JoinColumn(name = "causale_sospensione_id")
	private CpassDOrdCausaleSospensioneEvasione cpassDOrdCausaleSospensioneEvasione;

	//bi-directional many-to-one association to CpassTImpegno
	@ManyToOne
	@JoinColumn(name="impegno_id")
	private CpassTImpegno cpassTImpegno;

	//bi-directional many-to-one association to CpassTOrdImpegnoOrdine
	@ManyToOne
	@JoinColumn(name="impegno_ordine_id")
	private CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine;

	//bi-directional many-to-one association to CpassTOrdRigaEvasione
	@ManyToOne
	@JoinColumn(name="riga_evasione_id")
	private CpassTOrdRigaEvasione cpassTOrdRigaEvasione;

	//bi-directional many-to-one association to CpassTOrdSubimpegnoEvasione
	@OneToMany(mappedBy="cpassTOrdImpegnoEvasione")
	private List<CpassTOrdSubimpegnoEvasione> cpassTOrdSubimpegnoEvasiones;

	public CpassTOrdImpegnoEvasione() {
	}

	public UUID getImpegnoEvasioneId() {
		return this.impegnoEvasioneId;
	}

	public void setImpegnoEvasioneId(UUID impegnoEvasioneId) {
		this.impegnoEvasioneId = impegnoEvasioneId;
	}

	public Date getDataSospensione() {
		return this.dataSospensione;
	}

	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
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

	public Integer getImpegnoProgressivo() {
		return this.impegnoProgressivo;
	}

	public void setImpegnoProgressivo(Integer impegnoProgressivo) {
		this.impegnoProgressivo = impegnoProgressivo;
	}

	public BigDecimal getImportoRipartito() {
		return importoRipartito;
	}

	public void setImportoRipartito(BigDecimal importoRipartito) {
		this.importoRipartito = importoRipartito;
	}

	public BigDecimal getImportoLiquidato() {
		return this.importoLiquidato;
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

	public void setImportoLiquidato(BigDecimal importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	public CpassDOrdCausaleSospensioneEvasione getCpassDOrdCausaleSospensioneEvasione() {
		return cpassDOrdCausaleSospensioneEvasione;
	}

	public void setCpassDOrdCausaleSospensioneEvasione(CpassDOrdCausaleSospensioneEvasione cpassDOrdCausaleSospensioneEvasione) {
		this.cpassDOrdCausaleSospensioneEvasione = cpassDOrdCausaleSospensioneEvasione;
	}

	public CpassTImpegno getCpassTImpegno() {
		return this.cpassTImpegno;
	}

	public void setCpassTImpegno(CpassTImpegno cpassTImpegno) {
		this.cpassTImpegno = cpassTImpegno;
	}

	public CpassTOrdImpegnoOrdine getCpassTOrdImpegnoOrdine() {
		return this.cpassTOrdImpegnoOrdine;
	}

	public void setCpassTOrdImpegnoOrdine(CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine) {
		this.cpassTOrdImpegnoOrdine = cpassTOrdImpegnoOrdine;
	}

	public CpassTOrdRigaEvasione getCpassTOrdRigaEvasione() {
		return this.cpassTOrdRigaEvasione;
	}

	public void setCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
		this.cpassTOrdRigaEvasione = cpassTOrdRigaEvasione;
	}

	public List<CpassTOrdSubimpegnoEvasione> getCpassTOrdSubimpegnoEvasiones() {
		return this.cpassTOrdSubimpegnoEvasiones;
	}

	public void setCpassTOrdSubimpegnoEvasiones(List<CpassTOrdSubimpegnoEvasione> cpassTOrdSubimpegnoEvasiones) {
		this.cpassTOrdSubimpegnoEvasiones = cpassTOrdSubimpegnoEvasiones;
	}

	public CpassTOrdSubimpegnoEvasione addCpassTOrdSubimpegnoEvasione(CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione) {
		getCpassTOrdSubimpegnoEvasiones().add(cpassTOrdSubimpegnoEvasione);
		cpassTOrdSubimpegnoEvasione.setCpassTOrdImpegnoEvasione(this);

		return cpassTOrdSubimpegnoEvasione;
	}

	public CpassTOrdSubimpegnoEvasione removeCpassTOrdSubimpegnoEvasione(CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione) {
		getCpassTOrdSubimpegnoEvasiones().remove(cpassTOrdSubimpegnoEvasione);
		cpassTOrdSubimpegnoEvasione.setCpassTOrdImpegnoEvasione(null);

		return cpassTOrdSubimpegnoEvasione;
	}


	@Override
	public UUID getId() {
		return impegnoEvasioneId;
	}

	@Override
	public void setId(UUID id) {
		impegnoEvasioneId = id;
	}

	@Override
	public void initId() {
		this.impegnoEvasioneId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdRigaEvasione.getId()+"|"+ impegnoProgressivo);
	}
	
}
