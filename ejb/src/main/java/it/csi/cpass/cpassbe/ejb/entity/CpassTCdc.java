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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_cdc database table.
 *
 */
@Entity
@Table(name="cpass_t_cdc")
@NamedQuery(name="CpassTCdc.findAll", query="SELECT c FROM CpassTCdc c")
public class CpassTCdc implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_CDC_CDCID_GENERATOR", sequenceName="CPASS_T_CDC_CDC_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_CDC_CDCID_GENERATOR")
	@Column(name="cdc_id")
	private Integer cdcId;

	@Column(name="cdc_codice")
	private String cdcCodice;

	@Column(name="cdc_descrizione")
	private String cdcDescrizione;

	/** The data modifica. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	/** The data modifica. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	//bi-directional many-to-one association to CpassRSettoreCdc
	@OneToMany(mappedBy="cpassTCdc")
	private List<CpassRSettoreCdc> cpassRSettoreCdcs;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	public CpassTCdc() {
	}

	public Integer getCdcId() {
		return this.cdcId;
	}

	public void setCdcId(Integer cdcId) {
		this.cdcId = cdcId;
	}

	public String getCdcCodice() {
		return this.cdcCodice;
	}

	public void setCdcCodice(String cdcCodice) {
		this.cdcCodice = cdcCodice;
	}

	public String getCdcDescrizione() {
		return this.cdcDescrizione;
	}

	public void setCdcDescrizione(String cdcDescrizione) {
		this.cdcDescrizione = cdcDescrizione;
	}

	public List<CpassRSettoreCdc> getCpassRSettoreCdcs() {
		return this.cpassRSettoreCdcs;
	}

	public void setCpassRSettoreCdcs(List<CpassRSettoreCdc> cpassRSettoreCdcs) {
		this.cpassRSettoreCdcs = cpassRSettoreCdcs;
	}

	public CpassRSettoreCdc addCpassRSettoreCdc(CpassRSettoreCdc cpassRSettoreCdc) {
		getCpassRSettoreCdcs().add(cpassRSettoreCdc);
		cpassRSettoreCdc.setCpassTCdc(this);

		return cpassRSettoreCdc;
	}

	public CpassRSettoreCdc removeCpassRSettoreCdc(CpassRSettoreCdc cpassRSettoreCdc) {
		getCpassRSettoreCdcs().remove(cpassRSettoreCdc);
		cpassRSettoreCdc.setCpassTCdc(null);

		return cpassRSettoreCdc;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
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

	@Override
	public Integer getId() {
		return cdcId;
	}

	@Override
	public void setId(Integer id) {
		cdcId = id;
	}

}
