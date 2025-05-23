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
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_impegno_ordine database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_impegno_ordine")
@NamedQuery(name="CpassTOrdImpegnoOrdine.findAll", query="SELECT c FROM CpassTOrdImpegnoOrdine c")
public class CpassTOrdImpegnoOrdine extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_impegno_ordine");

	@Id
	@Column(name="impegno_ordine_id")
	private UUID impegnoOrdineId;

	//bi-directional many-to-one association to CpassTImpegno
	@ManyToOne
	@JoinColumn(name="impegno_id")
	private CpassTImpegno cpassTImpegno;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="impegno_progressivo")
	private Integer impegnoProgressivo;

	private BigDecimal importo;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@ManyToOne
	@JoinColumn(name="riga_ordine_id")
	private CpassTOrdRigaOrdine cpassTOrdRigaOrdine;

	//bi-directional many-to-one association to CpassTOrdSubimpegnoOrdine
	@OneToMany(mappedBy="cpassTOrdImpegnoOrdine")
	private List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines;

	public CpassTOrdImpegnoOrdine() {
	}

	public UUID getImpegnoOrdineId() {
		return this.impegnoOrdineId;
	}

	public void setImpegnoOrdineId(UUID impegnoOrdineId) {
		this.impegnoOrdineId = impegnoOrdineId;
	}

	public CpassTImpegno getCpassTImpegno() {
		return this.cpassTImpegno;
	}

	public void setCpassTImpegno(CpassTImpegno cpassTImpegno) {
		this.cpassTImpegno = cpassTImpegno;
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

	public Integer getImpegnoProgressivo() {
		return this.impegnoProgressivo;
	}

	public void setImpegnoProgressivo(Integer impegnoProgressivo) {
		this.impegnoProgressivo = impegnoProgressivo;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public CpassTOrdRigaOrdine getCpassTOrdRigaOrdine() {
		return this.cpassTOrdRigaOrdine;
	}

	public void setCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		this.cpassTOrdRigaOrdine = cpassTOrdRigaOrdine;
	}

	public List<CpassTOrdSubimpegnoOrdine> getCpassTOrdSubimpegnoOrdines() {
		return this.cpassTOrdSubimpegnoOrdines;
	}

	public void setCpassTOrdSubimpegnoOrdines(List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubimpegnoOrdines) {
		this.cpassTOrdSubimpegnoOrdines = cpassTOrdSubimpegnoOrdines;
	}

	public CpassTOrdSubimpegnoOrdine addCpassTOrdSubimpegnoOrdine(CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine) {
		getCpassTOrdSubimpegnoOrdines().add(cpassTOrdSubimpegnoOrdine);
		cpassTOrdSubimpegnoOrdine.setCpassTOrdImpegnoOrdine(this);
		return cpassTOrdSubimpegnoOrdine;
	}

	public CpassTOrdSubimpegnoOrdine removeCpassTOrdSubimpegnoOrdine(CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine) {
		getCpassTOrdSubimpegnoOrdines().remove(cpassTOrdSubimpegnoOrdine);
		cpassTOrdSubimpegnoOrdine.setCpassTOrdImpegnoOrdine(null);
		return cpassTOrdSubimpegnoOrdine;
	}


	@Override
	public UUID getId() {
		return impegnoOrdineId;
	}

	@Override
	public void setId(UUID id) {
		impegnoOrdineId = id;
	}

	@Override
	public void initId() {
		this.impegnoOrdineId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdRigaOrdine.getId()+"|"+ impegnoProgressivo);
	}


}
