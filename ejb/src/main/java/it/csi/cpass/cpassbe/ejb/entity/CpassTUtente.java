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
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdUtenteSezione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNso;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStatiIntervento;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;



/**
 * The persistent class for the cpass_t_utente database table.
 *
 */
@Entity
@Table(name="cpass_t_utente")
@NamedQuery(name="CpassTUtente.findAll", query="SELECT c FROM CpassTUtente c")
public class CpassTUtente extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_utente");

	/** The utente id. */
	@Id
	@Column(name="utente_id", unique=true, nullable=false)
	private UUID utenteId;

	/** The utente codice fiscale. */
	@Column(name="utente_codice_fiscale", nullable=false, length=16)
	private String utenteCodiceFiscale;

	/** The utente cognome. */
	@Column(name="utente_cognome", nullable=false, length=100)
	private String utenteCognome;

	/** The utente nome. */
	@Column(name="utente_nome", nullable=false, length=100)
	private String utenteNome;
	/** telefono */
	@Column(name="telefono", length=200)
	private String telefono;
	/** Email */
	@Column(name="email", length=200)
	private String email;

	/** Rup */
	//private Boolean rup;

	/** The cpass R utente settores. */
	//bi-directional many-to-one association to CpassRUtenteSettore
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRUtenteSettore> cpassRUtenteSettores;

	//bi-directional many-to-one association to CpassRUtenteRupSettore
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRUtenteRupSettore> cpassRUtenteRupSettores;

	//bi-directional many-to-one association to CpassROrdUtenteSezione
	//@OneToMany(mappedBy="cpassTUtente")
	//private List<CpassROrdUtenteSezione> cpassROrdUtenteSeziones;

	//bi-directional many-to-one association to CpassTOrdTestataRda
	//@OneToMany(mappedBy="cpassTUtente")
	//private List<CpassTOrdTestataRda> cpassTOrdTestataRdas;

	/** The cpass R dirigente settores. */
	//bi-directional many-to-one association to CpassRDirigenteSettore
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRDirigenteSettore> cpassRDirigenteSettores;


	//bi-directional many-to-one association to CpassRNotificaUtente
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRNotificaUtente> cpassRNotificaUtentes;

	//bi-directional many-to-one association to CpassROrdUtenteSezione
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassROrdUtenteSezione> cpassROrdUtenteSeziones;

	//bi-directional many-to-one association to CpassRPbaStatiIntervento
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRPbaStatiIntervento> cpassRPbaStatiInterventos;

	//bi-directional many-to-one association to CpassRPbaStoricoInterventoRup
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassRPbaStoricoInterventoRup> cpassRPbaStoricoInterventoRups;

	//bi-directional many-to-one association to CpassTOrdDestinatarioInvioNso
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassTOrdDestinatarioInvioNso> cpassTOrdDestinatarioInvioNsos;

	//bi-directional many-to-one association to CpassTOrdTestataEvasione
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassTOrdTestataEvasione> cpassTOrdTestataEvasiones;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines;

	//bi-directional many-to-one association to CpassTOrdTestataRda
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassTOrdTestataRda> cpassTOrdTestataRdas;

	//bi-directional many-to-one association to CpassTRmsTestataRm
	@OneToMany(mappedBy="cpassTUtente")
	private List<CpassTRmsTestataRms> cpassTRmsTestataRms;


	//bi-directional many-to-one association to CpassTUtenteRupDeleghe
	@OneToMany(mappedBy="cpassTUtenteRup")
	private List<CpassTUtenteRupDeleghe> cpassTUtenteRupDeleghes;

	/**
	 * Gets the utente id.
	 *
	 * @return the utente id
	 */
	public UUID getUtenteId() {
		return this.utenteId;
	}

	/**
	 * Sets the utente id.
	 *
	 * @param utenteId the new utente id
	 */
	public void setUtenteId(UUID utenteId) {
		this.utenteId = utenteId;
	}

	/**
	 * Gets the utente codice fiscale.
	 *
	 * @return the utente codice fiscale
	 */
	public String getUtenteCodiceFiscale() {
		return this.utenteCodiceFiscale;
	}

	/**
	 * Sets the utente codice fiscale.
	 *
	 * @param utenteCodiceFiscale the new utente codice fiscale
	 */
	public void setUtenteCodiceFiscale(String utenteCodiceFiscale) {
		this.utenteCodiceFiscale = utenteCodiceFiscale;
	}

	/**
	 * Gets the utente cognome.
	 *
	 * @return the utente cognome
	 */
	public String getUtenteCognome() {
		return this.utenteCognome;
	}

	/**
	 * Sets the utente cognome.
	 *
	 * @param utenteCognome the new utente cognome
	 */
	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = utenteCognome;
	}

	/**
	 * Gets the utente nome.
	 *
	 * @return the utente nome
	 */
	public String getUtenteNome() {
		return this.utenteNome;
	}

	/**
	 * Sets the utente nome.
	 *
	 * @param utenteNome the new utente nome
	 */
	public void setUtenteNome(String utenteNome) {
		this.utenteNome = utenteNome;
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
		cpassRUtenteSettore.setCpassTUtente(this);

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
		cpassRUtenteSettore.setCpassTUtente(null);

		return cpassRUtenteSettore;
	}


	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<CpassRUtenteRupSettore> getCpassRUtenteRupSettores() {
		return this.cpassRUtenteRupSettores;
	}

	public void setCpassRUtenteRupSettores(List<CpassRUtenteRupSettore> cpassRUtenteRupSettores) {
		this.cpassRUtenteRupSettores = cpassRUtenteRupSettores;
	}

	public CpassRUtenteRupSettore addCpassRUtenteRupSettore(CpassRUtenteRupSettore cpassRUtenteRupSettore) {
		getCpassRUtenteRupSettores().add(cpassRUtenteRupSettore);
		cpassRUtenteRupSettore.setCpassTUtente(this);

		return cpassRUtenteRupSettore;
	}

	public CpassRUtenteRupSettore removeCpassRUtenteRupSettore(CpassRUtenteRupSettore cpassRUtenteRupSettore) {
		getCpassRUtenteRupSettores().remove(cpassRUtenteRupSettore);
		cpassRUtenteRupSettore.setCpassTUtente(null);

		return cpassRUtenteRupSettore;
	}

	/**
	 * @return the cpassRDirigenteSettores
	 */
	public List<CpassRDirigenteSettore> getCpassRDirigenteSettores() {
		return cpassRDirigenteSettores;
	}

	/**
	 * @param cpassRDirigenteSettores the cpassRDirigenteSettores to set
	 */
	public void setCpassRDirigenteSettores(List<CpassRDirigenteSettore> cpassRDirigenteSettores) {
		this.cpassRDirigenteSettores = cpassRDirigenteSettores;
	}

	/*
	public Boolean getRup() {
		return rup;
	}

	public void setRup(Boolean rup) {
		this.rup = rup;
	}
	 */

	/**
	 * @return the cpassRNotificaUtentes
	 */
	public List<CpassRNotificaUtente> getCpassRNotificaUtentes() {
		return cpassRNotificaUtentes;
	}

	/**
	 * @param cpassRNotificaUtentes the cpassRNotificaUtentes to set
	 */
	public void setCpassRNotificaUtentes(List<CpassRNotificaUtente> cpassRNotificaUtentes) {
		this.cpassRNotificaUtentes = cpassRNotificaUtentes;
	}

	/**
	 * @return the cpassROrdUtenteSeziones
	 */
	public List<CpassROrdUtenteSezione> getCpassROrdUtenteSeziones() {
		return cpassROrdUtenteSeziones;
	}

	/**
	 * @param cpassROrdUtenteSeziones the cpassROrdUtenteSeziones to set
	 */
	public void setCpassROrdUtenteSeziones(List<CpassROrdUtenteSezione> cpassROrdUtenteSeziones) {
		this.cpassROrdUtenteSeziones = cpassROrdUtenteSeziones;
	}

	/**
	 * @return the cpassRPbaStatiInterventos
	 */
	public List<CpassRPbaStatiIntervento> getCpassRPbaStatiInterventos() {
		return cpassRPbaStatiInterventos;
	}

	/**
	 * @param cpassRPbaStatiInterventos the cpassRPbaStatiInterventos to set
	 */
	public void setCpassRPbaStatiInterventos(List<CpassRPbaStatiIntervento> cpassRPbaStatiInterventos) {
		this.cpassRPbaStatiInterventos = cpassRPbaStatiInterventos;
	}

	/**
	 * @return the cpassRPbaStoricoInterventoRups
	 */
	public List<CpassRPbaStoricoInterventoRup> getCpassRPbaStoricoInterventoRups() {
		return cpassRPbaStoricoInterventoRups;
	}

	/**
	 * @param cpassRPbaStoricoInterventoRups the cpassRPbaStoricoInterventoRups to set
	 */
	public void setCpassRPbaStoricoInterventoRups(List<CpassRPbaStoricoInterventoRup> cpassRPbaStoricoInterventoRups) {
		this.cpassRPbaStoricoInterventoRups = cpassRPbaStoricoInterventoRups;
	}

	/**
	 * @return the cpassTOrdDestinatarioInvioNsos
	 */
	public List<CpassTOrdDestinatarioInvioNso> getCpassTOrdDestinatarioInvioNsos() {
		return cpassTOrdDestinatarioInvioNsos;
	}

	/**
	 * @param cpassTOrdDestinatarioInvioNsos the cpassTOrdDestinatarioInvioNsos to set
	 */
	public void setCpassTOrdDestinatarioInvioNsos(List<CpassTOrdDestinatarioInvioNso> cpassTOrdDestinatarioInvioNsos) {
		this.cpassTOrdDestinatarioInvioNsos = cpassTOrdDestinatarioInvioNsos;
	}

	/**
	 * @return the cpassTOrdTestataEvasiones
	 */
	public List<CpassTOrdTestataEvasione> getCpassTOrdTestataEvasiones() {
		return cpassTOrdTestataEvasiones;
	}

	/**
	 * @param cpassTOrdTestataEvasiones the cpassTOrdTestataEvasiones to set
	 */
	public void setCpassTOrdTestataEvasiones(List<CpassTOrdTestataEvasione> cpassTOrdTestataEvasiones) {
		this.cpassTOrdTestataEvasiones = cpassTOrdTestataEvasiones;
	}

	/**
	 * @return the cpassTOrdTestataOrdines
	 */
	public List<CpassTOrdTestataOrdine> getCpassTOrdTestataOrdines() {
		return cpassTOrdTestataOrdines;
	}

	/**
	 * @param cpassTOrdTestataOrdines the cpassTOrdTestataOrdines to set
	 */
	public void setCpassTOrdTestataOrdines(List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines) {
		this.cpassTOrdTestataOrdines = cpassTOrdTestataOrdines;
	}

	/**
	 * @return the cpassTOrdTestataRdas
	 */
	public List<CpassTOrdTestataRda> getCpassTOrdTestataRdas() {
		return cpassTOrdTestataRdas;
	}

	/**
	 * @param cpassTOrdTestataRdas the cpassTOrdTestataRdas to set
	 */
	public void setCpassTOrdTestataRdas(List<CpassTOrdTestataRda> cpassTOrdTestataRdas) {
		this.cpassTOrdTestataRdas = cpassTOrdTestataRdas;
	}

	/**
	 * @return the cpassTRmsTestataRms
	 */
	public List<CpassTRmsTestataRms> getCpassTRmsTestataRms() {
		return cpassTRmsTestataRms;
	}

	/**
	 * @param cpassTRmsTestataRms the cpassTRmsTestataRms to set
	 */
	public void setCpassTRmsTestataRms(List<CpassTRmsTestataRms> cpassTRmsTestataRms) {
		this.cpassTRmsTestataRms = cpassTRmsTestataRms;
	}


	/**
	 * @return the cpassTUtenteRupDeleghes
	 */
	public List<CpassTUtenteRupDeleghe> getCpassTUtenteRupDeleghes() {
		return cpassTUtenteRupDeleghes;
	}

	/**
	 * @param cpassTUtenteRupDeleghes the cpassTUtenteRupDeleghes to set
	 */
	public void setCpassTUtenteRupDeleghes(List<CpassTUtenteRupDeleghe> cpassTUtenteRupDeleghes) {
		this.cpassTUtenteRupDeleghes = cpassTUtenteRupDeleghes;
	}

	@Override
	public UUID getId() {
		return utenteId;
	}

	@Override
	public void setId(UUID id) {
		utenteId = id;
	}

	@Override
	public void initId() {
		this.utenteId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, utenteCodiceFiscale);
	}
}
