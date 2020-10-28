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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ente database table.
 *
 */
@Entity
@Table(name="cpass_t_ente")
@NamedQuery(name="CpassTEnte.findAll", query="SELECT c FROM CpassTEnte c")
public class CpassTEnte extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ente");

	/** The ente id. */
	@Id
	@Column(name="ente_id", unique=true, nullable=false)
	private UUID enteId;

	@Column(name="ente_codice", nullable=false, length=50)
	private String enteCodice;

	@Column(name="ente_codice_fiscale", nullable=false, length=16)
	private String enteCodiceFiscale;

	@Column(name="ente_denominazione", nullable=false, length=500)
	private String enteDenominazione;

	@Column(name="codice_ipa_amministrazione", length=200)	
	private String codiceIpaAmministrazione; 
	
	@Column(name="dipartimento", length=200)			
	private String dipartimento; 
	
	@Column(name="ufficio", length=200)			
	private String ufficio; 
	
	@Column(name="regione", length=200)			
	private String regione; 
	
	@Column(name="provincia", length=200)			
	private String provincia; 
	
	@Column(name="indirizzo", length=200)			
	private String indirizzo; 
	
	@Column(name="telefono", length=200)			
	private String telefono; 
	
	@Column(name="email", length=200)		
	private String email; 
	
	@Column(name="emailPEC", length=200)		
	private String emailPEC; 
	
	/** The cpass T programmas. */
	//bi-directional many-to-one association to CpassTPbaProgramma
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTPbaProgramma> cpassTPbaProgrammas;

	/** The cpass T settores. */
	//bi-directional many-to-one association to CpassTSettore
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTSettore> cpassTSettores;

	/**
	 * Gets the ente id.
	 *
	 * @return the ente id
	 */
	public UUID getEnteId() {
		return this.enteId;
	}

	/**
	 * Sets the ente id.
	 *
	 * @param enteId the new ente id
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	/**
	 * @return the enteCodiceFiscale
	 */
	public String getEnteCodiceFiscale() {
		return enteCodiceFiscale;
	}

	/**
	 * @param enteCodiceFiscale the enteCodiceFiscale to set
	 */
	public void setEnteCodiceFiscale(String enteCodiceFiscale) {
		this.enteCodiceFiscale = enteCodiceFiscale;
	}

	/**
	 * @return the enteDenominazione
	 */
	public String getEnteDenominazione() {
		return enteDenominazione;
	}

	/**
	 * @param enteDenominazione the enteDenominazione to set
	 */
	public void setEnteDenominazione(String enteDenominazione) {
		this.enteDenominazione = enteDenominazione;
	}

	
	/**
	 * @return the codiceIpaAmministrazione
	 */
	public String getCodiceIpaAmministrazione() {
		return codiceIpaAmministrazione;
	}

	/**
	 * @param codiceIpaAmministrazione the codiceIpaAmministrazione to set
	 */
	public void setCodiceIpaAmministrazione(String codiceIpaAmministrazione) {
		this.codiceIpaAmministrazione = codiceIpaAmministrazione;
	}

	/**
	 * @return the dipartimento
	 */
	public String getDipartimento() {
		return dipartimento;
	}

	/**
	 * @param dipartimento the dipartimento to set
	 */
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}

	/**
	 * @return the ufficio
	 */
	public String getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	/**
	 * @return the regione
	 */
	public String getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(String regione) {
		this.regione = regione;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	/**
	 * @return the emailPEC
	 */
	public String getEmailPEC() {
		return emailPEC;
	}

	/**
	 * @param emailPEC the emailPEC to set
	 */
	public void setEmailPEC(String emailPEC) {
		this.emailPEC = emailPEC;
	}

	
	/**
	 * @return the enteCodice
	 */
	public String getEnteCodice() {
		return enteCodice;
	}

	/**
	 * @param enteCodice the enteCodice to set
	 */
	public void setEnteCodice(String enteCodice) {
		this.enteCodice = enteCodice;
	}

	/**
	 * Gets the cpass T programmas.
	 *
	 * @return the cpass T programmas
	 */
	public List<CpassTPbaProgramma> getCpassTPbaProgrammas() {
		return this.cpassTPbaProgrammas;
	}

	/**
	 * Sets the cpass T programmas.
	 *
	 * @param cpassTPbaProgrammas the new cpass T programmas
	 */
	public void setCpassTPbaProgrammas(List<CpassTPbaProgramma> cpassTPbaProgrammas) {
		this.cpassTPbaProgrammas = cpassTPbaProgrammas;
	}

	/**
	 * Adds the cpass T programma.
	 *
	 * @param cpassTPbaProgramma the cpass T programma
	 * @return the cpass T programma
	 */
	public CpassTPbaProgramma addCpassTPbaProgramma(CpassTPbaProgramma cpassTPbaProgramma) {
		getCpassTPbaProgrammas().add(cpassTPbaProgramma);
		cpassTPbaProgramma.setCpassTEnte(this);

		return cpassTPbaProgramma;
	}

	/**
	 * Removes the cpass T programma.
	 *
	 * @param cpassTPbaProgramma the cpass T programma
	 * @return the cpass T programma
	 */
	public CpassTPbaProgramma removeCpassTPbaProgramma(CpassTPbaProgramma cpassTPbaProgramma) {
		getCpassTPbaProgrammas().remove(cpassTPbaProgramma);
		cpassTPbaProgramma.setCpassTEnte(null);

		return cpassTPbaProgramma;
	}

	/**
	 * Gets the cpass T settores.
	 *
	 * @return the cpass T settores
	 */
	public List<CpassTSettore> getCpassTSettores() {
		return this.cpassTSettores;
	}

	/**
	 * Sets the cpass T settores.
	 *
	 * @param cpassTSettores the new cpass T settores
	 */
	public void setCpassTSettores(List<CpassTSettore> cpassTSettores) {
		this.cpassTSettores = cpassTSettores;
	}

	/**
	 * Adds the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore addCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().add(cpassTSettore);
		cpassTSettore.setCpassTEnte(this);

		return cpassTSettore;
	}

	/**
	 * Removes the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore removeCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().remove(cpassTSettore);
		cpassTSettore.setCpassTEnte(null);

		return cpassTSettore;
	}

	@Override
	public UUID getId() {
		return enteId;
	}

	@Override
	public void setId(UUID id) {
		enteId = id;
	}

	@Override
	public void initId() {
		this.enteId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, enteCodiceFiscale);
	}
}
