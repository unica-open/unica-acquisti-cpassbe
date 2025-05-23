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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class DestinatarioEvasione extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer progressivo;
	private Stato stato;
	private Destinatario destinatarioOrdine;
	private TestataEvasione testataEvasione;
	private Settore settore;
	private List<RigaEvasione> rigaEvasiones;
	private String cap;
	private String email;
	private String indirizzo;
	private String localita;
	private String numCivico;
	private String contatto;
	private String provincia;
	private String telefono;
	private String sede;

	public DestinatarioEvasione() {
	}

	/**
	 * @return the progressivo
	 */
	public Integer getProgressivo() {
		return progressivo;
	}

	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * @return the destinatarioOrdine
	 */
	public Destinatario getDestinatarioOrdine() {
		return destinatarioOrdine;
	}

	/**
	 * @param destinatarioOrdine the destinatarioOrdine to set
	 */
	public void setDestinatarioOrdine(Destinatario destinatarioOrdine) {
		this.destinatarioOrdine = destinatarioOrdine;
	}

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	/**
	 * @return the rigaEvasiones
	 */
	public List<RigaEvasione> getRigaEvasiones() {
		return rigaEvasiones;
	}

	/**
	 * @param rigaEvasiones the rigaEvasiones to set
	 */
	public void setRigaEvasiones(List<RigaEvasione> rigaEvasiones) {
		this.rigaEvasiones = rigaEvasiones;
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
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}

	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	/**
	 * @return the numCivico
	 */
	public String getNumCivico() {
		return numCivico;
	}

	/**
	 * @param numCivico the numCivico to set
	 */
	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	/**
	 * @return the contatto
	 */
	public String getContatto() {
		return contatto;
	}

	/**
	 * @param contatto the contatto to set
	 */
	public void setContatto(String contatto) {
		this.contatto = contatto;
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
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}

	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}


}
