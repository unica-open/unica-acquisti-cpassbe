/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;


public class AooActa extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aooDescrizione;

	private Date dataFineValidita;

	private String aooCodice;

	private Integer aooActaOrigId;

	private Ente ente;

	private List<SettoreAooActa> settoreAooActas;

	private List<ProtocolloOrdine> ProtocolloOrdines;

	public AooActa() {
	}

	/**
	 * @return the aooDescrizione
	 */
	public String getAooDescrizione() {
		return aooDescrizione;
	}

	/**
	 * @param aooDescrizione the aooDescrizione to set
	 */
	public void setAooDescrizione(String aooDescrizione) {
		this.aooDescrizione = aooDescrizione;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @return the settoreAooActas
	 */
	public List<SettoreAooActa> getSettoreAooActas() {
		return settoreAooActas;
	}

	/**
	 * @param settoreAooActas the settoreAooActas to set
	 */
	public void setSettoreAooActas(List<SettoreAooActa> settoreAooActas) {
		this.settoreAooActas = settoreAooActas;
	}

	/**
	 * @return the protocolloOrdines
	 */
	public List<ProtocolloOrdine> getProtocolloOrdines() {
		return ProtocolloOrdines;
	}

	/**
	 * @param protocolloOrdines the protocolloOrdines to set
	 */
	public void setProtocolloOrdines(List<ProtocolloOrdine> protocolloOrdines) {
		ProtocolloOrdines = protocolloOrdines;
	}

	/**
	 * @return the aooCodice
	 */
	public String getAooCodice() {
		return aooCodice;
	}

	/**
	 * @param aooCodice the aooCodice to set
	 */
	public void setAooCodice(String aooCodice) {
		this.aooCodice = aooCodice;
	}

	/**
	 * @return the aooActaOrigId
	 */
	public Integer getAooActaOrigId() {
		return aooActaOrigId;
	}

	/**
	 * @param aooActaOrigId the aooActaOrigId to set
	 */
	public void setAooActaOrigId(Integer aooActaOrigId) {
		this.aooActaOrigId = aooActaOrigId;
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


}
