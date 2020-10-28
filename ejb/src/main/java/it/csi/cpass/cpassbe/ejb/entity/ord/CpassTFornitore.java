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

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_fornitore database table.
 * 
 */
@Entity
@Table(name = "cpass_t_fornitore")
@NamedQuery(name = "CpassTFornitore.findAll", query = "SELECT c FROM CpassTFornitore c")
public class CpassTFornitore implements BaseEntity<UUID> {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_fornitore");

	@Id
	@Column(name = "fornitore_id")
	private UUID fornitoreId;

	private String codice;

	private String cap;

	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@Column(name = "codice_fiscale_estero")
	private String codiceFiscaleEstero;

	private String cognome;

	private String comune;

	private String indirizzo;

	@Column(name = "natura_giuridica")
	private String naturaGiuridica;

	private String nome;

	@Column(name = "numero_civico")
	private String numeroCivico;

	@Column(name = "partita_iva")
	private String partitaIva;

	private String provincia;

	@Column(name = "ragione_sociale")
	private String ragioneSociale;

	private String stato;

	@Column(name = "cod_destinatario")
	private String codDestinatario;

	//bi-directional many-to-one association to CpassTListinoFornitore
	@OneToMany(mappedBy="cpassTFornitore")
	private List<CpassTListinoFornitore> cpassTListinoFornitores;
		
	@Transient
	private List<String> codiciFornitoriCollegati;
		
	
	public CpassTFornitore() {
	}

	public UUID getFornitoreId() {
		return this.fornitoreId;
	}

	public void setFornitoreId(UUID fornitoreId) {
		this.fornitoreId = fornitoreId;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscaleEstero() {
		return this.codiceFiscaleEstero;
	}

	public void setCodiceFiscaleEstero(String codiceFiscaleEstero) {
		this.codiceFiscaleEstero = codiceFiscaleEstero;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNaturaGiuridica() {
		return this.naturaGiuridica;
	}

	public void setNaturaGiuridica(String naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCivico() {
		return this.numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	@Override
	public UUID getId() {
		return fornitoreId;
	}

	@Override
	public void setId(UUID id) {
		fornitoreId = id;
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the codDestinatario
	 */
	public String getCodDestinatario() {
		return codDestinatario;
	}

	/**
	 * @param codDestinatario the codDestinatario to set
	 */
	public void setCodDestinatario(String codDestinatario) {
		this.codDestinatario = codDestinatario;
	}

	
	/**
	 * @return the cpassTListinoFornitores
	 */
	public List<CpassTListinoFornitore> getCpassTListinoFornitores() {
		return cpassTListinoFornitores;
	}

	/**
	 * @param cpassTListinoFornitores the cpassTListinoFornitores to set
	 */
	public void setCpassTListinoFornitores(List<CpassTListinoFornitore> cpassTListinoFornitores) {
		this.cpassTListinoFornitores = cpassTListinoFornitores;
	}
	
	

	public List<String> getCodiciFornitoriCollegati() {
		return codiciFornitoriCollegati;
	}

	public void setCodiciFornitoriCollegati(List<String> codiciFornitoriCollegati) {
		this.codiciFornitoriCollegati = codiciFornitoriCollegati;
	}

	@Override
	public void initId() {
		this.fornitoreId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, codiceFiscale + "|" + codiceFiscaleEstero);
	}

}
