/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_flusso_anomalie database table.
 *
 */
@Entity
@Table(name="cpass_t_flusso_anomalie")
@NamedQuery(name="CpassTFlussoAnomalie.findAll", query="SELECT c FROM CpassTFlussoAnomalie c")
public class CpassTFlussoAnomalie implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_FLUSSO_ANOMALIE_FLUSSOANOMALIEID_GENERATOR" , sequenceName="cpass_t_flusso_anomalie_flusso_anomalie_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_FLUSSO_ANOMALIE_FLUSSOANOMALIEID_GENERATOR")
	@Column(name="flusso_anomalie_id")
	private Integer flussoAnomalieId;

	@Column(name="data_elaborazione")
	private String dataElaborazione;

	@Column(name="impegno_id")
	private UUID impegnoId;

	@Column(name="importo_attuale")
	private BigDecimal importoAttuale;

	@Column(name="liq_anno_prec_calcolato")
	private BigDecimal liqAnnoPrecCalcolato;

	@Column(name="subimpegno_id")
	private UUID subimpegnoId;

	public CpassTFlussoAnomalie() {
	}

	public Integer getFlussoAnomalieId() {
		return this.flussoAnomalieId;
	}

	public void setFlussoAnomalieId(Integer flussoAnomalieId) {
		this.flussoAnomalieId = flussoAnomalieId;
	}

	public String getDataElaborazione() {
		return this.dataElaborazione;
	}

	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public UUID getImpegnoId() {
		return this.impegnoId;
	}

	public void setImpegnoId(UUID impegnoId) {
		this.impegnoId = impegnoId;
	}

	public BigDecimal getImportoAttuale() {
		return this.importoAttuale;
	}

	public void setImportoAttuale(BigDecimal importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	public BigDecimal getLiqAnnoPrecCalcolato() {
		return this.liqAnnoPrecCalcolato;
	}

	public void setLiqAnnoPrecCalcolato(BigDecimal liqAnnoPrecCalcolato) {
		this.liqAnnoPrecCalcolato = liqAnnoPrecCalcolato;
	}

	public UUID getSubimpegnoId() {
		return this.subimpegnoId;
	}

	public void setSubimpegnoId(UUID subimpegnoId) {
		this.subimpegnoId = subimpegnoId;
	}



	@Override
	public Integer getId() {
		return flussoAnomalieId;
	}

	@Override
	public void setId(Integer id) {
		flussoAnomalieId = id;
	}

}
