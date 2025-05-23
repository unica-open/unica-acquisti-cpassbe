/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.rms;

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
 * The persistent class for the cpass_r_rms_stati_riga_rms database table.
 *
 */
@Entity
@Table(name="cpass_r_rms_stati_riga_rms")
@NamedQuery(name="CpassRRmsStatiRigaRms.findAll", query="SELECT c FROM CpassRRmsStatiRigaRms c")
public class CpassRRmsStatiRigaRms implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id

	@SequenceGenerator(name="cpass_r_rms_stati_riga_rms_stati_riga_rmsID_GENERATOR", sequenceName="cpass_r_rms_stati_riga_rms_rms_stati_riga_rms_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_r_rms_stati_riga_rms_stati_riga_rmsID_GENERATOR")

	@Column(name="rms_stati_riga_rms_id")
	private Integer rmsStatiRigaRmsId;

	@Column(name="data_modifica")
	private Date dataModifica;

	private String motivazione;

	private String stato;

	@Column(name="utente_modifica")
	private String utenteModifica;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@ManyToOne
	@JoinColumn(name="riga_rms_id")
	private CpassTRmsRigaRms cpassTRmsRigaRms;

	public CpassRRmsStatiRigaRms() {
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getMotivazione() {
		return this.motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public Integer getRmsStatiRigaRmsId() {
		return this.rmsStatiRigaRmsId;
	}

	public void setRmsStatiRigaRmsId(Integer rmsStatiRigaRmsId) {
		this.rmsStatiRigaRmsId = rmsStatiRigaRmsId;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getUtenteModifica() {
		return this.utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public CpassTRmsRigaRms getCpassTRmsRigaRms() {
		return this.cpassTRmsRigaRms;
	}

	public void setCpassTRmsRigaRms(CpassTRmsRigaRms cpassTRmsRigaRms) {
		this.cpassTRmsRigaRms = cpassTRmsRigaRms;
	}

	@Override
	public Integer getId() {
		return rmsStatiRigaRmsId;
	}

	@Override
	public void setId(Integer id) {
		rmsStatiRigaRmsId = id;

	}

}
