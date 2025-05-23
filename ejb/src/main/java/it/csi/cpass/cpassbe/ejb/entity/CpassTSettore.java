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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRSettoreAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_settore database table.
 *
 */
@Entity
@Table(name="cpass_t_settore")
@NamedQuery(name="CpassTSettore.findAll", query="SELECT c FROM CpassTSettore c")
public class CpassTSettore extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_settore");

	/** The settore id. */
	@Id
	@Column(name="settore_id", unique=true, nullable=false)
	private UUID settoreId;

	/** The settore codice. */
	@Column(name="settore_codice", nullable=false, length=50)
	private String settoreCodice;

	/** The settore descrizione. */
	@Column(name="settore_descrizione", nullable=false, length=500)
	private String settoreDescrizione;

	/** The data modifica. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	/** The data modifica. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	/** The cpass R utente settores. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRUtenteSettore> cpassRUtenteSettores;

	/** The cpass R utente settores. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassTSettoreIndirizzo> cpassTSettoreIndirizzos;

	/** The cpass D tipo settore. */
	//bi-directional many-to-one association to CpassDTipoSettore
	@ManyToOne
	@JoinColumn(name="tipo_settore_id", nullable=false)
	private CpassDTipoSettore cpassDTipoSettore;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_padre_id")
	private CpassTSettore cpassTSettorePadre;

	//bi-directional many-to-one association to CpassTSettore
	@OneToMany(mappedBy="cpassTSettorePadre")
	private List<CpassTSettore> cpassTSettores;

	//bi-directional many-to-one association to CpassRUfficioSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRUfficioSettore> cpassRUfficioSettores;

	//bi-directional many-to-one association to CpassRUfficioSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRSettoreCdc> cpassRSettoreCdcs;

	//bi-directional many-to-one association to CpassRUtenteRupSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRUtenteRupSettore> cpassRUtenteRupSettores;

	//bi-directional many-to-one association to CpassRSettoreAooActa
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRSettoreAooActa> cpassRSettoreAooActas;

	/** The firma. */
	@Column(name="firma", nullable=true, length=50)
	private String firma;

	/** The cpass R dirigente settores. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRDirigenteSettore> cpassRDirigenteSettore;

	/**
	 * Gets the settore id.
	 *
	 * @return the settore id
	 */
	public UUID getSettoreId() {
		return this.settoreId;
	}

	/**
	 * Sets the settore id.
	 *
	 * @param settoreId the new settore id
	 */
	public void setSettoreId(UUID settoreId) {
		this.settoreId = settoreId;
	}

	/**
	 * Gets the settore codice.
	 *
	 * @return the settore codice
	 */
	public String getSettoreCodice() {
		return this.settoreCodice;
	}

	/**
	 * Sets the settore codice.
	 *
	 * @param settoreCodice the new settore codice
	 */
	public void setSettoreCodice(String settoreCodice) {
		this.settoreCodice = settoreCodice;
	}

	/**
	 * Gets the settore descrizione.
	 *
	 * @return the settore descrizione
	 */
	public String getSettoreDescrizione() {
		return this.settoreDescrizione;
	}

	/**
	 * Sets the settore descrizione.
	 *
	 * @param settoreDescrizione the new settore descrizione
	 */
	public void setSettoreDescrizione(String settoreDescrizione) {
		this.settoreDescrizione = settoreDescrizione;
	}

	/**
	 * Gets the cpass R utente settores.
	 *
	 * @return the cpass R utente settores
	 */
	public List<CpassRUtenteSettore> getCpassRUtenteSettores() {
		return this.cpassRUtenteSettores;
	}

	/**
	 * Sets the cpass R utente settores.
	 *
	 * @param cpassRUtenteSettores the new cpass R utente settores
	 */
	public void setCpassRUtenteSettores(List<CpassRUtenteSettore> cpassRUtenteSettores) {
		this.cpassRUtenteSettores = cpassRUtenteSettores;
	}

	/**
	 * Adds the cpass R utente settore.
	 *
	 * @param cpassRUtenteSettore the cpass R utente settore
	 * @return the cpass R utente settore
	 */
	public CpassRUtenteSettore addCpassRUtenteSettore(CpassRUtenteSettore cpassRUtenteSettore) {
		getCpassRUtenteSettores().add(cpassRUtenteSettore);
		cpassRUtenteSettore.setCpassTSettore(this);

		return cpassRUtenteSettore;
	}

	/**
	 * Removes the cpass R utente settore.
	 *
	 * @param cpassRUtenteSettore the cpass R utente settore
	 * @return the cpass R utente settore
	 */
	public CpassRUtenteSettore removeCpassRUtenteSettore(CpassRUtenteSettore cpassRUtenteSettore) {
		getCpassRUtenteSettores().remove(cpassRUtenteSettore);
		cpassRUtenteSettore.setCpassTSettore(null);

		return cpassRUtenteSettore;
	}

	/**
	 * Gets the cpass D tipo settore.
	 *
	 * @return the cpass D tipo settore
	 */
	public CpassDTipoSettore getCpassDTipoSettore() {
		return this.cpassDTipoSettore;
	}

	/**
	 * Sets the cpass D tipo settore.
	 *
	 * @param cpassDTipoSettore the new cpass D tipo settore
	 */
	public void setCpassDTipoSettore(CpassDTipoSettore cpassDTipoSettore) {
		this.cpassDTipoSettore = cpassDTipoSettore;
	}

	/**
	 * Gets the cpass T ente.
	 *
	 * @return the cpass T ente
	 */
	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	/**
	 * Sets the cpass T ente.
	 *
	 * @param cpassTEnte the new cpass T ente
	 */
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTSettore getCpassTSettorePadre() {
		return this.cpassTSettorePadre;
	}

	public void setCpassTSettorePadre(CpassTSettore cpassTSettorePadre) {
		this.cpassTSettorePadre = cpassTSettorePadre;
	}

	public List<CpassTSettore> getCpassTSettores() {
		return this.cpassTSettores;
	}

	public void setCpassTSettores(List<CpassTSettore> cpassTSettores) {
		this.cpassTSettores = cpassTSettores;
	}

	public CpassTSettore addCpassTSettorePadre(CpassTSettore cpassTSettore) {
		getCpassTSettores().add(cpassTSettore);
		cpassTSettore.setCpassTSettorePadre(this);

		return cpassTSettore;
	}

	public CpassTSettore removeCpassTSettorePadre(CpassTSettore cpassTSettore) {
		getCpassTSettores().remove(cpassTSettore);
		cpassTSettore.setCpassTSettorePadre(null);

		return cpassTSettore;
	}

	public List<CpassRUfficioSettore> getCpassRUfficioSettores() {
		return this.cpassRUfficioSettores;
	}

	public void setCpassRUfficioSettores(List<CpassRUfficioSettore> cpassRUfficioSettores) {
		this.cpassRUfficioSettores = cpassRUfficioSettores;
	}

	public CpassRUfficioSettore addCpassRUfficioSettore(CpassRUfficioSettore cpassRUfficioSettore) {
		getCpassRUfficioSettores().add(cpassRUfficioSettore);
		cpassRUfficioSettore.setCpassTSettore(this);

		return cpassRUfficioSettore;
	}

	public CpassRUfficioSettore removeCpassRUfficioSettore(CpassRUfficioSettore cpassRUfficioSettore) {
		getCpassRUfficioSettores().remove(cpassRUfficioSettore);
		cpassRUfficioSettore.setCpassTSettore(null);

		return cpassRUfficioSettore;
	}

	public List<CpassRUtenteRupSettore> getCpassRUtenteRupSettores() {
		return this.cpassRUtenteRupSettores;
	}

	public void setCpassRUtenteRupSettores(List<CpassRUtenteRupSettore> cpassRUtenteRupSettores) {
		this.cpassRUtenteRupSettores = cpassRUtenteRupSettores;
	}


	/**
	 * @return the cpassTSettoreIndirizzos
	 */
	public List<CpassTSettoreIndirizzo> getCpassTSettoreIndirizzos() {
		return cpassTSettoreIndirizzos;
	}

	/**
	 * @param cpassTSettoreIndirizzos the cpassTSettoreIndirizzos to set
	 */
	public void setCpassTSettoreIndirizzos(List<CpassTSettoreIndirizzo> cpassTSettoreIndirizzos) {
		this.cpassTSettoreIndirizzos = cpassTSettoreIndirizzos;
	}



	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}



	/**
	 * @return the cpassRSettoreAooActas
	 */
	public List<CpassRSettoreAooActa> getCpassRSettoreAooActas() {
		return cpassRSettoreAooActas;
	}

	/**
	 * @param cpassRSettoreAooActas the cpassRSettoreAooActas to set
	 */
	public void setCpassRSettoreAooActas(List<CpassRSettoreAooActa> cpassRSettoreAooActas) {
		this.cpassRSettoreAooActas = cpassRSettoreAooActas;
	}

	public CpassRUtenteRupSettore addCpassRUtenteRupSettore(CpassRUtenteRupSettore cpassRUtenteRupSettore) {
		getCpassRUtenteRupSettores().add(cpassRUtenteRupSettore);
		cpassRUtenteRupSettore.setCpassTSettore(this);

		return cpassRUtenteRupSettore;
	}


	public CpassRUtenteRupSettore removeCpassRUtenteRupSettore(CpassRUtenteRupSettore cpassRUtenteRupSettore) {
		getCpassRUtenteRupSettores().remove(cpassRUtenteRupSettore);
		cpassRUtenteRupSettore.setCpassTSettore(null);

		return cpassRUtenteRupSettore;
	}


	/**
	 * @return the cpassRDirigenteSettore
	 */
	public List<CpassRDirigenteSettore> getCpassRDirigenteSettore() {
		return cpassRDirigenteSettore;
	}

	/**
	 * @param cpassRDirigenteSettore the cpassRDirigenteSettore to set
	 */
	public void setCpassRDirigenteSettore(List<CpassRDirigenteSettore> cpassRDirigenteSettore) {
		this.cpassRDirigenteSettore = cpassRDirigenteSettore;
	}


	/**
	 * @return the cpassRSettoreCdcs
	 */
	public List<CpassRSettoreCdc> getCpassRSettoreCdcs() {
		return cpassRSettoreCdcs;
	}

	/**
	 * @param cpassRSettoreCdcs the cpassRSettoreCdcs to set
	 */
	public void setCpassRSettoreCdcs(List<CpassRSettoreCdc> cpassRSettoreCdcs) {
		this.cpassRSettoreCdcs = cpassRSettoreCdcs;
	}

	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	@Override
	public UUID getId() {
		return settoreId;
	}

	@Override
	public void setId(UUID id) {
		settoreId = id;
	}

	@Override
	public void initId() {
		this.settoreId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, settoreCodice +"|"+super.getDataCreazione());
	}
}
