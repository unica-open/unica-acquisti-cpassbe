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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cpass_r_fruitore_servizio database table.
 *
 */
@Entity
@Table(name="cpass_r_fruitore_servizio")
@NamedQuery(name="CpassRFruitoreServizio.findAll", query="SELECT c FROM CpassRFruitoreServizio c")
public class CpassRFruitoreServizio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fruitore_servizio_id")
	private Integer fruitoreServizioId;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	//bi-directional many-to-one association to CpassTFruitore
	@ManyToOne
	@JoinColumn(name="fruitore_id")
	private CpassTFruitore cpassTFruitore;

	//bi-directional many-to-one association to CpassTServizio
	@ManyToOne
	@JoinColumn(name="servizio_id")
	private CpassTServizio cpassTServizio;

	public CpassRFruitoreServizio() {
	}

	public Integer getFruitoreServizioId() {
		return this.fruitoreServizioId;
	}

	public void setFruitoreServizioId(Integer fruitoreServizioId) {
		this.fruitoreServizioId = fruitoreServizioId;
	}

	public Date getDataValiditaFine() {
		return this.dataValiditaFine;
	}

	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	public Date getDataValiditaInizio() {
		return this.dataValiditaInizio;
	}

	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	public CpassTFruitore getCpassTFruitore() {
		return this.cpassTFruitore;
	}

	public void setCpassTFruitore(CpassTFruitore cpassTFruitore) {
		this.cpassTFruitore = cpassTFruitore;
	}

	public CpassTServizio getCpassTServizio() {
		return this.cpassTServizio;
	}

	public void setCpassTServizio(CpassTServizio cpassTServizio) {
		this.cpassTServizio = cpassTServizio;
	}

}
