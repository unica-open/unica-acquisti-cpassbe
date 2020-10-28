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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
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
@Table(name="cpass_t_ord_impegno_associato")
@NamedQuery(name="CpassTOrdImpegnoAssociato.findAll", query="SELECT c FROM CpassTOrdImpegnoAssociato c")
public class CpassTOrdImpegnoAssociato extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_impegno_associato");
	
	@Id
	@Column(name="impegno_associato_id")
	private UUID impegnoAssociatoId;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;


	//@Column(name="impegno_progressivo")
	//private Integer impegnoProgressivo;

	//bi-directional many-to-one association to CpassTImpegno
	@ManyToOne
	@JoinColumn(name="impegno_id")
	private CpassTImpegno cpassTImpegno;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	//bi-directional many-to-one association to CpassTOrdSubimpegnoAssociato
	@OneToMany(mappedBy="cpassTOrdImpegnoAssociato")
	private List<CpassTOrdSubimpegnoAssociato> cpassTOrdSubimpegnoAssociatos;

	public CpassTOrdImpegnoAssociato() {
	}

	/**
	 * @return the impegnoAssociatoId
	 */
	public UUID getImpegnoAssociatoId() {
		return impegnoAssociatoId;
	}

	/**
	 * @param impegnoAssociatoId the impegnoAssociatoId to set
	 */
	public void setImpegnoAssociatoId(UUID impegnoAssociatoId) {
		this.impegnoAssociatoId = impegnoAssociatoId;
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
	 * @return the cpassTImpegno
	 */
	public CpassTImpegno getCpassTImpegno() {
		return cpassTImpegno;
	}

	/**
	 * @param cpassTImpegno the cpassTImpegno to set
	 */
	public void setCpassTImpegno(CpassTImpegno cpassTImpegno) {
		this.cpassTImpegno = cpassTImpegno;
	}

	/**
	 * @return the cpassTOrdTestataOrdine
	 */
	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return cpassTOrdTestataOrdine;
	}

	/**
	 * @param cpassTOrdTestataOrdine the cpassTOrdTestataOrdine to set
	 */
	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}

	/**
	 * @return the cpassTOrdSubimpegnoAssociatos
	 */
	public List<CpassTOrdSubimpegnoAssociato> getCpassTOrdSubimpegnoAssociatos() {
		return cpassTOrdSubimpegnoAssociatos;
	}

	public void setCpassTOrdSubimpegnoAssociatos(List<CpassTOrdSubimpegnoAssociato> cpassTOrdSubimpegnoAssociatos) {
		this.cpassTOrdSubimpegnoAssociatos = cpassTOrdSubimpegnoAssociatos;
	}

	public CpassTOrdSubimpegnoAssociato addCpassTOrdSubimpegnoAssociato(CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato) {
		getCpassTOrdSubimpegnoAssociatos().add(cpassTOrdSubimpegnoAssociato);
		cpassTOrdSubimpegnoAssociato.setCpassTOrdImpegnoAssociato(this);

		return cpassTOrdSubimpegnoAssociato;
	}

	public CpassTOrdSubimpegnoAssociato removeCpassTOrdSubimpegnoAssociato(CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato) {
		getCpassTOrdSubimpegnoAssociatos().remove(cpassTOrdSubimpegnoAssociato);
		cpassTOrdSubimpegnoAssociato.setCpassTOrdImpegnoAssociato(null);

		return cpassTOrdSubimpegnoAssociato;
	}
	

	@Override
	public UUID getId() {
		return impegnoAssociatoId;
	}

	@Override
	public void setId(UUID id) {
		impegnoAssociatoId = id;
	}

	@Override
	public void initId() {
		this.impegnoAssociatoId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdTestataOrdine.getId()+"|"+ impegnoAnno+"|"+ impegnoAnnoEsercizio+"|"+ impegnoNumero);
	}
	

}
