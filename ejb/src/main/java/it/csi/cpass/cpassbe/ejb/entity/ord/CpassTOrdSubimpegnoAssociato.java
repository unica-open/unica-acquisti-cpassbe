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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.math.BigDecimal;
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
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_subimpegno_ordine database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_subimpegno_associato")
@NamedQuery(name="CpassTOrdSubimpegnoAssociato.findAll", query="SELECT c FROM CpassTOrdSubimpegnoAssociato c")
public class CpassTOrdSubimpegnoAssociato extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_subimpegno_associato");

	@Id
	@Column(name="subimpegno_associato_id")
	private UUID subimpegnoAssociatoId;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;

	@Column(name="subimpegno_importo")
	private BigDecimal subimpegnoImporto;

	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;

	//bi-directional many-to-one association to CpassTOrdImpegnoAssociato
	@ManyToOne
	@JoinColumn(name="impegno_associato_id")
	private CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato;

	//bi-directional many-to-one association to CpassTSubimpegno
	@ManyToOne
	@JoinColumn(name="subimpegno_id")
	private CpassTSubimpegno cpassTSubimpegno;

	public CpassTOrdSubimpegnoAssociato() {
	}

	public UUID getSubimpegnoAssociatoId() {
		return this.subimpegnoAssociatoId;
	}

	public void setSubimpegnoAssociatoId(UUID subimpegnoAssociatoId) {
		this.subimpegnoAssociatoId = subimpegnoAssociatoId;
	}

	public Integer getImpegnoAnno() {
		return this.impegnoAnno;
	}

	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
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

	public BigDecimal getSubimpegnoImporto() {
		return this.subimpegnoImporto;
	}

	public void setSubimpegnoImporto(BigDecimal subimpegnoImporto) {
		this.subimpegnoImporto = subimpegnoImporto;
	}

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}
	public CpassTOrdImpegnoAssociato getCpassTOrdImpegnoAssociato() {
		return this.cpassTOrdImpegnoAssociato;
	}

	public void setCpassTOrdImpegnoAssociato(CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato) {
		this.cpassTOrdImpegnoAssociato = cpassTOrdImpegnoAssociato;
	}

	public CpassTSubimpegno getCpassTSubimpegno() {
		return this.cpassTSubimpegno;
	}

	public void setCpassTSubimpegno(CpassTSubimpegno cpassTSubimpegno) {
		this.cpassTSubimpegno = cpassTSubimpegno;
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

	@Override
	public UUID getId() {
		return subimpegnoAssociatoId;
	}

	@Override
	public void setId(UUID id) {
		subimpegnoAssociatoId = id;
	}

	@Override
	public void initId() {
		this.subimpegnoAssociatoId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdImpegnoAssociato.getId() +"|"+ subimpegnoAnno +"|"+ subimpegnoNumero);
	}

}
