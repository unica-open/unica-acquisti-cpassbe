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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_settore_storico database table.
 *
 */
@Entity
@Table(name="cpass_t_settore_storico")
@NamedQuery(name="CpassTSettoreStorico.findAll", query="SELECT c FROM CpassTSettoreStorico c")
public class CpassTSettoreStorico implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SETTORE_STORICO_SETTORESTORICOID_GENERATOR",  sequenceName="cpass_t_settore_storico_settore_storico_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SETTORE_STORICO_SETTORESTORICOID_GENERATOR")

	@Column(name="settore_storico_id")
	private Integer settoreStoricoId;

	private String note;

	@Column(name="settore_codice_attuale")
	private String settoreCodiceAttuale;

	@Column(name="settore_codice_storico")
	private String settoreCodiceStorico;

	@ManyToOne
	@JoinColumn(name="settore_id_attuale")
	private CpassTSettore settoreAttuale;

	@ManyToOne
	@JoinColumn(name="settore_id_storico")
	private CpassTSettore settoreStorico;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;


	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	public CpassTSettoreStorico() {
	}

	public Integer getSettoreStoricoId() {
		return this.settoreStoricoId;
	}

	public void setSettoreStoricoId(Integer settoreStoricoId) {
		this.settoreStoricoId = settoreStoricoId;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSettoreCodiceAttuale() {
		return this.settoreCodiceAttuale;
	}

	public void setSettoreCodiceAttuale(String settoreCodiceAttuale) {
		this.settoreCodiceAttuale = settoreCodiceAttuale;
	}

	public String getSettoreCodiceStorico() {
		return this.settoreCodiceStorico;
	}

	public void setSettoreCodiceStorico(String settoreCodiceStorico) {
		this.settoreCodiceStorico = settoreCodiceStorico;
	}


	/**
	 * @return the settoreAttuale
	 */
	public CpassTSettore getSettoreAttuale() {
		return settoreAttuale;
	}

	/**
	 * @param settoreAttuale the settoreAttuale to set
	 */
	public void setSettoreAttuale(CpassTSettore settoreAttuale) {
		this.settoreAttuale = settoreAttuale;
	}

	/**
	 * @return the settoreStorico
	 */
	public CpassTSettore getSettoreStorico() {
		return settoreStorico;
	}

	/**
	 * @param settoreStorico the settoreStorico to set
	 */
	public void setSettoreStorico(CpassTSettore settoreStorico) {
		this.settoreStorico = settoreStorico;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}


	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	@Override
	public Integer getId() {
		return settoreStoricoId;
	}

	@Override
	public void setId(Integer id) {
		settoreStoricoId = id;
	}
}
