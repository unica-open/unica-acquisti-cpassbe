/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The Class Fornitore.
 */
public class Fornitore extends BaseAuditedDto<UUID> implements Serializable {

	/** For serialization */
	private static final long serialVersionUID = 5228253568772398388L;

	private String codice;
	private String naturaGiuridica;
	private String ragioneSociale;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String codiceFiscaleEstero;
	private String partitaIva;
	private String sedime;
	private String indirizzo;
	private String numeroCivico;
	private String cap;
	private String comune;
	private String provincia;
	private String stato;
	//private String codDestinatario;
	private List<ListinoFornitore> listinoFornitores;
	private boolean selected; // utilizzando nel FE
	private List<String> codiciFornitoriCollegatiSuccessivi;
	private List<ModalitaPagamento> elencoModalitaPagamento;
	private Ente ente;


	/** Default constructor */
	public Fornitore() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Fornitore(UUID id) {
		super(id);
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
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
	 * @return the naturaGiuridica
	 */
	public String getNaturaGiuridica() {
		return naturaGiuridica;
	}

	/**
	 * @param naturaGiuridica the naturaGiuridica to set
	 */
	public void setNaturaGiuridica(String naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the codiceFiscaleEstero
	 */
	public String getCodiceFiscaleEstero() {
		return codiceFiscaleEstero;
	}

	/**
	 * @param codiceFiscaleEstero the codiceFiscaleEstero to set
	 */
	public void setCodiceFiscaleEstero(String codiceFiscaleEstero) {
		this.codiceFiscaleEstero = codiceFiscaleEstero;
	}

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
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
	 * @return the sedime
	 */
	public String getSedime() {
		return sedime;
	}

	/**
	 * @param sedime the sedime to set
	 */
	public void setSedime(String sedime) {
		this.sedime = sedime;
	}

	/**
	 * @return the numeroCivico
	 */
	public String getNumeroCivico() {
		return numeroCivico;
	}

	/**
	 * @param numeroCivico the numeroCivico to set
	 */
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the comune
	 */
	public String getComune() {
		return comune;
	}

	/**
	 * @param comune the comune to set
	 */
	public void setComune(String comune) {
		this.comune = comune;
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
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}


	/**
	 * @return the codiciFornitoriCollegatiSuccessivi
	 */
	public List<String> getCodiciFornitoriCollegatiSuccessivi() {
		return codiciFornitoriCollegatiSuccessivi;
	}
	/**
	 * @param codiciFornitoriCollegatiSuccessivi the codiciFornitoriCollegatiSuccessivi to set
	 */
	public void setCodiciFornitoriCollegatiSuccessivi(List<String> codiciFornitoriCollegatiSuccessivi) {
		this.codiciFornitoriCollegatiSuccessivi = codiciFornitoriCollegatiSuccessivi;
	}

	/**
	 * @return the listinoFornitores
	 */
	public List<ListinoFornitore> getListinoFornitores() {
		return listinoFornitores;
	}

	/**
	 * @param listinoFornitores the listinoFornitores to set
	 */
	public void setListinoFornitores(List<ListinoFornitore> listinoFornitores) {
		this.listinoFornitores = listinoFornitores;
	}

	/**
	 * @return the elencoModalitaPagamento
	 */
	public List<ModalitaPagamento> getElencoModalitaPagamento() {
		return elencoModalitaPagamento;
	}

	/**
	 * @param elencoModalitaPagamento the elencoModalitaPagamento to set
	 */
	public void setElencoModalitaPagamento(List<ModalitaPagamento> elencoModalitaPagamento) {
		this.elencoModalitaPagamento = elencoModalitaPagamento;
	}

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	/**
	 * @return the indirizzoCompleto
	 */
	public String getIndirizzoCompleto() {
		String indirizzoCompleto = "";
		if (getSedime() != null) {
			indirizzoCompleto += getSedime().trim() + " ";
		}
		if (getIndirizzo() != null) {
			indirizzoCompleto += getIndirizzo().trim() + " ";
		}
		if (getNumeroCivico() != null) {
			indirizzoCompleto += getNumeroCivico().trim();
		}
		//setIndirizzoCompleto(indirizzoCompleto.trim());
		return indirizzoCompleto.trim();
	}

	/**
	 * @return the indirizzoCompleto
	 */
	public String getIndirizzoConSedime() {
		String indirizzoConSedime = "";
		if (getSedime() != null) {
			indirizzoConSedime += getSedime().trim() + " ";
		}
		if (getIndirizzo() != null) {
			indirizzoConSedime += getIndirizzo().trim() + " ";
		}
		return indirizzoConSedime.trim();
	}


	@Override
	public String toString() {
		return new StringBuilder().append("Fornitore [ ")
				.append(", codice=").append(codice)
				.append(", naturaGiuridica=").append(naturaGiuridica)
				.append(", ragioneSociale=").append(ragioneSociale)
				.append(", cognome=").append(cognome)
				.append(", nome=").append(nome)
				.append(", codiceFiscale=").append(codiceFiscale)
				.append(", codiceFiscaleEstero=").append(codiceFiscaleEstero)
				.append(", partitaIva=").append(partitaIva)
				.append(", indirizzo=").append(indirizzo)
				.append(", numeroCivico=").append(numeroCivico)
				.append(", cap=").append(cap)
				.append(", comune=").append(comune)
				.append(", provincia=").append(provincia)
				.append(", stato=").append(stato)
				.append(", id=").append(id)
				.append("]").toString();
	}

}
