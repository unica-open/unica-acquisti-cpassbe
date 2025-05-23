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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Stato;

/**
 * The Class Destinatario.
 */
public class Destinatario extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer progressivo;
	private String cap;
	private String contatto;
	private Date dataInvioNso;
	private String email;
	private String indirizzo;
	private String localita;
	private String numCivico;
	private String provincia;
	private StatoNso statoNso;
	private String telefono;
	private Stato stato;
	private TestataOrdine testataOrdine;
	private Settore Settore;
	private int nRighe;
	private String indirizzoCodice;
	private SettoreIndirizzo settoreIndirizzo;

	public Destinatario() {}

	public Destinatario(UUID id) {
		this.id = id;
	}

	public Integer getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getContatto() {
		return contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public Date getDataInvioNso() {
		return dataInvioNso;
	}

	public void setDataInvioNso(Date dataInvioNso) {
		this.dataInvioNso = dataInvioNso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNumCivico() {
		return numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public StatoNso getStatoNso() {
		return statoNso;
	}

	public void setStatoNso(StatoNso statoNso) {
		this.statoNso = statoNso;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
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

	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	public Settore getSettore() {
		return Settore;
	}

	public void setSettore(Settore settore) {
		Settore = settore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getnRighe() {
		return nRighe;
	}

	public void setnRighe(int nRighe) {
		this.nRighe = nRighe;
	}

	/**
	 * @return the indirizzoCodice
	 */
	public String getIndirizzoCodice() {
		return indirizzoCodice;
	}


	/**
	 * @return the settoreIndirizzo
	 */
	public SettoreIndirizzo getSettoreIndirizzo() {
		return settoreIndirizzo;
	}

	/**
	 * @param settoreIndirizzo the settoreIndirizzo to set
	 */
	public void setSettoreIndirizzo(SettoreIndirizzo settoreIndirizzo) {
		this.settoreIndirizzo = settoreIndirizzo;
	}

	/**
	 * @param indirizzoCodice the indirizzoCodice to set
	 */
	public void setIndirizzoCodice(String indirizzoCodice) {
		this.indirizzoCodice = indirizzoCodice;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Destinatario [")
				.append(", id=").append(id)
				.append("]").toString();
	}

}
