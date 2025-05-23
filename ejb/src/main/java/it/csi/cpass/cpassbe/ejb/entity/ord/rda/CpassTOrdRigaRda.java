/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord.rda;

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

import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsRigaRda;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_ord_testata_rda database table.
 *
 */
@Entity
@Table(name = "cpass_t_ord_riga_rda")
@NamedQuery(name = "CpassTOrdRigaRda.findAll", query = "SELECT c FROM CpassTOrdRigaRda c")
public class CpassTOrdRigaRda extends BaseAuditedEntity<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_riga_rda");

	@Id
	@Column(name = "riga_rda_id", unique = true, nullable = false)
	private UUID rigaRdaId;

	@Column()
	private String note;

	@Column(name = "progressivo_riga")
	private Integer progressivoRiga;

	@Column()
	private BigDecimal quantita;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	// bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name = "stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassDUnitaMisura
	@ManyToOne
	@JoinColumn(name="unita_misura_id")
	private CpassDUnitaMisura cpassDUnitaMisura;

	// bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name = "ente_id")
	private CpassTEnte cpassTEnte;

	// bi-directional many-to-one association to CpassTOrdTestataRda
	@ManyToOne
	@JoinColumn(name = "testata_rda_id")
	private CpassTOrdTestataRda cpassTOrdTestataRda;

	//@OneToMany(mappedBy="cpassTOrdRigaRda")
	//private List<CpassTRmsRigaRms> cpassTRmsRigaRms;

	//bi-directional many-to-one association to CpassRRmsRigaRda
	@OneToMany(mappedBy="cpassTOrdRigaRda")
	private List<CpassRRmsRigaRda> cpassRRmsRigaRdas;

	public CpassTOrdRigaRda() {}

	public UUID getRigaRdaId() {
		return rigaRdaId;
	}

	public void setRigaRdaId(UUID rigaRdaId) {
		this.rigaRdaId = rigaRdaId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getProgressivoRiga() {
		return progressivoRiga;
	}

	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}

	public BigDecimal getQuantita() {
		return quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	public CpassDOggettiSpesa getCpassDOggettiSpesa() {
		return cpassDOggettiSpesa;
	}

	public void setCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		this.cpassDOggettiSpesa = cpassDOggettiSpesa;
	}

	public CpassDStato getCpassDStato() {
		return cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassDUnitaMisura getCpassDUnitaMisura() {
		return cpassDUnitaMisura;
	}

	public void setCpassDUnitaMisura(CpassDUnitaMisura cpassDUnitaMisura) {
		this.cpassDUnitaMisura = cpassDUnitaMisura;
	}

	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTOrdTestataRda getCpassTOrdTestataRda() {
		return cpassTOrdTestataRda;
	}

	public void setCpassTOrdTestataRda(CpassTOrdTestataRda cpassTOrdTestataRda) {
		this.cpassTOrdTestataRda = cpassTOrdTestataRda;
	}

	/*
	public List<CpassTRmsRigaRms> getCpassTRmsRigaRms() {
		return cpassTRmsRigaRms;
	}

	public void setCpassTRmsRigaRms(List<CpassTRmsRigaRms> cpassTRmsRigaRms) {
		this.cpassTRmsRigaRms = cpassTRmsRigaRms;
	}
	 */

	/**
	 * @return the cpassRRmsRigaRdas
	 */
	public List<CpassRRmsRigaRda> getCpassRRmsRigaRdas() {
		return cpassRRmsRigaRdas;
	}

	/**
	 * @param cpassRRmsRigaRdas the cpassRRmsRigaRdas to set
	 */
	public void setCpassRRmsRigaRdas(List<CpassRRmsRigaRda> cpassRRmsRigaRdas) {
		this.cpassRRmsRigaRdas = cpassRRmsRigaRdas;
	}

	/**
	 * @return the id
	 */
	@Override
	public UUID getId() {
		return rigaRdaId;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(UUID id) {
		rigaRdaId = id;
	}

	/**
	 * Initializes the id with the instance fields, if applicable
	 */
	@Override
	public void initId() {
		this.rigaRdaId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdTestataRda.getId()+"|"+ progressivoRiga);
	}
}
