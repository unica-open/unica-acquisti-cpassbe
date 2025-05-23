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
package it.csi.cpass.cpassbe.ejb.entity.ord.rda;

import java.io.Serializable;
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

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdRdaOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_ord_testata_rda database table.
 *
 */
@Entity
@Table(name = "cpass_t_ord_testata_rda")
@NamedQuery(name = "CpassTOrdTestataRda.findAll", query = "SELECT c FROM CpassTOrdTestataRda c")
public class CpassTOrdTestataRda extends BaseAuditedEntity<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_testata_rda");

	@Id
	@Column(name = "testata_rda_id", unique = true, nullable = false)
	private UUID testataRdaId;

	@Column(name = "rda_anno")
	private Integer anno;

	@Column(name = "data_autorizzazione")
	private Date dataAutorizzazione;

	@Column(name = "rda_descrizione")
	private String descrizione;

	@Column()
	private String note;

	@Column(name = "rda_numero")
	private Integer numero;

	// bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name = "stato_id")
	private CpassDStato cpassDStato;

	// bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name = "ente_id")
	private CpassTEnte cpassTEnte;

	// bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name = "settore_emittente_id")
	private CpassTSettore cpassTSettore;

	// bi-directional many-to-one association to CpassTUfficio
	@ManyToOne
	@JoinColumn(name = "ufficio_id")
	private CpassTUfficio cpassTUfficio;

	// bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name = "utente_compilatore_id")
	private CpassTUtente cpassTUtente;

	//bi-directional many-to-one association to CpassROrdRdaOrdine
	@OneToMany(mappedBy="cpassTOrdTestataRda")
	private List<CpassROrdRdaOrdine> cpassROrdRdaOrdines;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@OneToMany(mappedBy="cpassTOrdTestataRda")
	private List<CpassTOrdRigaRda> cpassTOrdRigaRda;

	public CpassTOrdTestataRda() {
	}

	public UUID getTestataRdaId() {
		return testataRdaId;
	}

	public void setTestataRdaId(UUID testataRdaId) {
		this.testataRdaId = testataRdaId;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public CpassDStato getCpassDStato() {
		return cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTSettore getCpassTSettore() {
		return cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public CpassTUfficio getCpassTUfficio() {
		return cpassTUfficio;
	}

	public void setCpassTUfficio(CpassTUfficio cpassTUfficio) {
		this.cpassTUfficio = cpassTUfficio;
	}

	public CpassTUtente getCpassTUtente() {
		return cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}


	/**
	 * @return the id
	 */
	@Override
	public UUID getId() {
		return testataRdaId;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(UUID id) {
		testataRdaId = id;
	}

	@Override
	public void initId() {
		this.testataRdaId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, anno + "|" + numero + "|" + cpassTSettore.getId());
	}

	public List<CpassROrdRdaOrdine> getCpassROrdRdaOrdines() {
		return cpassROrdRdaOrdines;
	}

	public void setCpassROrdRdaOrdines(List<CpassROrdRdaOrdine> cpassROrdRdaOrdines) {
		this.cpassROrdRdaOrdines = cpassROrdRdaOrdines;
	}


	/**
	 * @return the cpassTOrdRigaRda
	 */
	public List<CpassTOrdRigaRda> getCpassTOrdRigaRda() {
		return cpassTOrdRigaRda;
	}

	/**
	 * @param cpassTOrdRigaRda the cpassTOrdRigaRda to set
	 */
	public void setCpassTOrdRigaRda(List<CpassTOrdRigaRda> cpassTOrdRigaRda) {
		this.cpassTOrdRigaRda = cpassTOrdRigaRda;
	}

	public CpassROrdRdaOrdine addCpassROrdRdaOrdine(CpassROrdRdaOrdine cpassROrdRdaOrdine) {
		getCpassROrdRdaOrdines().add(cpassROrdRdaOrdine);
		cpassROrdRdaOrdine.setCpassTOrdTestataRda(this);

		return cpassROrdRdaOrdine;
	}

	public CpassROrdRdaOrdine removeCpassROrdRdaOrdine(CpassROrdRdaOrdine cpassROrdRdaOrdine) {
		getCpassROrdRdaOrdines().remove(cpassROrdRdaOrdine);
		cpassROrdRdaOrdine.setCpassTOrdTestataRda(null);

		return cpassROrdRdaOrdine;
	}

}
