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
package it.csi.cpass.cpassbe.ejb.entity;

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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
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

	/** The settore cap. */
	@Column(name="settore_cap", nullable=false, length=5)
	private String settoreCap;

	/** The settore codice. */
	@Column(name="settore_codice", nullable=false, length=50)
	private String settoreCodice;

	/** The settore descrizione. */
	@Column(name="settore_descrizione", nullable=false, length=500)
	private String settoreDescrizione;

	/** The settore indirizzo. */
	@Column(name="settore_indirizzo", nullable=false, length=500)
	private String settoreIndirizzo;

	/** The settore localita. */
	@Column(name="settore_localita", nullable=false, length=500)
	private String settoreLocalita;

	/** The settore provincia. */
	@Column(name="settore_provincia", nullable=false, length=2)
	private String settoreProvincia;

	/** The settore telefono. */
	@Column(name="settore_telefono", nullable=false, length=50)
	private String settoreTelefono;

	/** The cpass R utente settores. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRUtenteSettore> cpassRUtenteSettores;

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

	@Column(name="settore_num_civico")
	private String settoreNumCivico;

	@Column(name="settore_contatto")
	private String settoreContatto;
	
	@Column(name="settore_email")
	private String settoreEmail;

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

	//bi-directional many-to-one association to CpassRUtenteRupSettore
	@OneToMany(mappedBy="cpassTSettore")
	private List<CpassRUtenteRupSettore> cpassRUtenteRupSettores;
	
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
	 * Gets the settore cap.
	 *
	 * @return the settore cap
	 */
	public String getSettoreCap() {
		return this.settoreCap;
	}

	/**
	 * Sets the settore cap.
	 *
	 * @param settoreCap the new settore cap
	 */
	public void setSettoreCap(String settoreCap) {
		this.settoreCap = settoreCap;
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
	 * Gets the settore indirizzo.
	 *
	 * @return the settore indirizzo
	 */
	public String getSettoreIndirizzo() {
		return this.settoreIndirizzo;
	}

	/**
	 * Sets the settore indirizzo.
	 *
	 * @param settoreIndirizzo the new settore indirizzo
	 */
	public void setSettoreIndirizzo(String settoreIndirizzo) {
		this.settoreIndirizzo = settoreIndirizzo;
	}

	/**
	 * Gets the settore localita.
	 *
	 * @return the settore localita
	 */
	public String getSettoreLocalita() {
		return this.settoreLocalita;
	}

	/**
	 * Sets the settore localita.
	 *
	 * @param settoreLocalita the new settore localita
	 */
	public void setSettoreLocalita(String settoreLocalita) {
		this.settoreLocalita = settoreLocalita;
	}

	/**
	 * Gets the settore provincia.
	 *
	 * @return the settore provincia
	 */
	public String getSettoreProvincia() {
		return this.settoreProvincia;
	}

	/**
	 * Sets the settore provincia.
	 *
	 * @param settoreProvincia the new settore provincia
	 */
	public void setSettoreProvincia(String settoreProvincia) {
		this.settoreProvincia = settoreProvincia;
	}

	/**
	 * Gets the settore telefono.
	 *
	 * @return the settore telefono
	 */
	public String getSettoreTelefono() {
		return this.settoreTelefono;
	}

	/**
	 * Sets the settore telefono.
	 *
	 * @param settoreTelefono the new settore telefono
	 */
	public void setSettoreTelefono(String settoreTelefono) {
		this.settoreTelefono = settoreTelefono;
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

	/**
	 * @return the settoreNumCivico
	 */
	public String getSettoreNumCivico() {
		return settoreNumCivico;
	}

	/**
	 * @param settoreNumCivico the settoreNumCivico to set
	 */
	public void setSettoreNumCivico(String settoreNumCivico) {
		this.settoreNumCivico = settoreNumCivico;
	}

	/**
	 * @return the settoreContatto
	 */
	public String getSettoreContatto() {
		return settoreContatto;
	}

	/**
	 * @param settoreContatto the settoreContatto to set
	 */
	public void setSettoreContatto(String settoreContatto) {
		this.settoreContatto = settoreContatto;
	}

	/**
	 * @return the settoreEmail
	 */
	public String getSettoreEmail() {
		return settoreEmail;
	}

	/**
	 * @param settoreEmail the settoreEmail to set
	 */
	public void setSettoreEmail(String settoreEmail) {
		this.settoreEmail = settoreEmail;
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
