/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Settore;

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
	private StatoElOrdine statoElOrdine;
	private TestataOrdine testataOrdine;
	private Settore Settore;
	private int nRighe;
	
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

	public StatoElOrdine getStatoElOrdine() {
		return statoElOrdine;
	}

	public void setStatoElOrdine(StatoElOrdine statoElOrdine) {
		this.statoElOrdine = statoElOrdine;
	}

	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
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

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Destinatario [")
				.append(", id=").append(id)
				.append("]").toString();
	}

}
