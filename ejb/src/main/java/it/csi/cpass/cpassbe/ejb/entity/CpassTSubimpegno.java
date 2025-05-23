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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_subimpegno database table.
 *
 */
@Entity
@Table(name="cpass_t_subimpegno")
@NamedQuery(name="CpassTSubimpegno.findAll", query="SELECT c FROM CpassTSubimpegno c")
public class CpassTSubimpegno extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_subimpegno");

	@Id
	@Column(name="subimpegno_id")
	private UUID subimpegnoId;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="importo_attuale")
	private BigDecimal importoAttuale;

	@Column(name="importo_iniziale")
	private BigDecimal importoIniziale;

	@Column(name="liq_anno_prec")
	private BigDecimal liqAnnoPrec;

	@Column(name="provvedimento_anno")
	private Integer provvedimentoAnno;

	@Column(name="provvedimento_numero")
	private String provvedimentoNumero;

	@Column(name="provvedimento_settore")
	private String provvedimentoSettore;

	private String stato;

	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;

	@Column(name="subimpegno_anno_esercizio")
	private Integer subimpegnoAnnoEsercizio;

	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;

	//bi-directional many-to-one association to CpassTOrdSubimpegnoOrdine
	@OneToMany(mappedBy="cpassTSubimpegno")
	private List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name="fornitore_id")
	private CpassTFornitore cpassTFornitore;

	//bi-directional many-to-one association to CpassTImpegno
	@ManyToOne
	@JoinColumn(name="impegno_id")
	private CpassTImpegno cpassTImpegno;

	public CpassTSubimpegno() {
	}

	public UUID getSubimpegnoId() {
		return this.subimpegnoId;
	}

	public void setSubimpegnoId(UUID subimpegnoId) {
		this.subimpegnoId = subimpegnoId;
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

	public BigDecimal getImportoAttuale() {
		return this.importoAttuale;
	}

	public void setImportoAttuale(BigDecimal importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	public BigDecimal getImportoIniziale() {
		return this.importoIniziale;
	}

	public void setImportoIniziale(BigDecimal importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	public BigDecimal getLiqAnnoPrec() {
		return this.liqAnnoPrec;
	}

	public void setLiqAnnoPrec(BigDecimal liqAnnoPrec) {
		this.liqAnnoPrec = liqAnnoPrec;
	}

	public Integer getProvvedimentoAnno() {
		return this.provvedimentoAnno;
	}

	public void setProvvedimentoAnno(Integer provvedimentoAnno) {
		this.provvedimentoAnno = provvedimentoAnno;
	}

	public String getProvvedimentoNumero() {
		return this.provvedimentoNumero;
	}

	public void setProvvedimentoNumero(String provvedimentoNumero) {
		this.provvedimentoNumero = provvedimentoNumero;
	}

	public String getProvvedimentoSettore() {
		return this.provvedimentoSettore;
	}

	public void setProvvedimentoSettore(String provvedimentoSettore) {
		this.provvedimentoSettore = provvedimentoSettore;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Integer getSubimpegnoAnno() {
		return this.subimpegnoAnno;
	}

	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}

	public Integer getSubimpegnoAnnoEsercizio() {
		return this.subimpegnoAnnoEsercizio;
	}

	public void setSubimpegnoAnnoEsercizio(Integer subimpegnoAnnoEsercizio) {
		this.subimpegnoAnnoEsercizio = subimpegnoAnnoEsercizio;
	}

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	public List<CpassTOrdSubimpegnoOrdine> getCpassTOrdSubimpegnoOrdines() {
		return this.cpassTOrdSubimpegnoOrdines;
	}

	public void setCpassTOrdSubimpegnoOrdines(List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines) {
		this.cpassTOrdSubimpegnoOrdines = cpassTOrdSubimpegnoOrdines;
	}

	public CpassTOrdSubimpegnoOrdine addCpassTOrdSubimpegnoOrdine(CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine) {
		getCpassTOrdSubimpegnoOrdines().add(cpassTOrdSubimpegnoOrdine);
		cpassTOrdSubimpegnoOrdine.setCpassTSubimpegno(this);

		return cpassTOrdSubimpegnoOrdine;
	}

	public CpassTOrdSubimpegnoOrdine removeCpassTOrdSubimpegnoOrdine(CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine) {
		getCpassTOrdSubimpegnoOrdines().remove(cpassTOrdSubimpegnoOrdine);
		cpassTOrdSubimpegnoOrdine.setCpassTSubimpegno(null);

		return cpassTOrdSubimpegnoOrdine;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTFornitore getCpassTFornitore() {
		return this.cpassTFornitore;
	}

	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	public CpassTImpegno getCpassTImpegno() {
		return this.cpassTImpegno;
	}

	public void setCpassTImpegno(CpassTImpegno cpassTImpegno) {
		this.cpassTImpegno = cpassTImpegno;
	}

	@Override
	public UUID getId() {
		return subimpegnoId;
	}

	@Override
	public void setId(UUID id) {
		subimpegnoId=id;
	}

	@Override
	public void initId() {
		this.subimpegnoId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, subimpegnoAnno+"|"+ subimpegnoNumero+"|"+subimpegnoAnnoEsercizio+"|"+ cpassTImpegno.getId());
	}

}
