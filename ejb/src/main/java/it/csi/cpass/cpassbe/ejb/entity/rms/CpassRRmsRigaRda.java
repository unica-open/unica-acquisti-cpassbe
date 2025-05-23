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
import java.math.BigDecimal;
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
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;

/**
 * The persistent class for the cpass_r_rms_stati_riga_rms database table.
 *
 */
@Entity
@Table(name="cpass_r_rms_riga_rda")
@NamedQuery(name="CpassRRmsRigaRda.findAll", query="SELECT c FROM CpassRRmsRigaRda c")
public class CpassRRmsRigaRda implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_r_rms_riga_rda_riga_rdaID_GENERATOR", sequenceName="cpass_r_rms_riga_rda_rms_riga_rda_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_r_rms_riga_rda_riga_rdaID_GENERATOR")

	@Column(name="rms_riga_rda_id")
	private Integer rmsRigaRdaId;

	@Column(name="data_modifica")
	private Date dataModifica;


	@Column(name="utente_modifica")
	private String utenteModifica;

	@Column(name="quantita")
	private BigDecimal quantita;
	//bi-directional many-to-one association to CpassTRmsRigaRm
	@ManyToOne
	@JoinColumn(name="riga_rms_id")
	private CpassTRmsRigaRms cpassTRmsRigaRms;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@ManyToOne
	@JoinColumn(name="riga_rda_id")
	private CpassTOrdRigaRda cpassTOrdRigaRda;



	public CpassRRmsRigaRda() {
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
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


	/**
	 * @return the rmsRigaRdaId
	 */
	public Integer getRmsRigaRdaId() {
		return rmsRigaRdaId;
	}

	/**
	 * @param rmsRigaRdaId the rmsRigaRdaId to set
	 */
	public void setRmsRigaRdaId(Integer rmsRigaRdaId) {
		this.rmsRigaRdaId = rmsRigaRdaId;
	}

	/**
	 * @return the cpassTOrdRigaRda
	 */
	public CpassTOrdRigaRda getCpassTOrdRigaRda() {
		return cpassTOrdRigaRda;
	}

	/**
	 * @param cpassTOrdRigaRda the cpassTOrdRigaRda to set
	 */
	public void setCpassTOrdRigaRda(CpassTOrdRigaRda cpassTOrdRigaRda) {
		this.cpassTOrdRigaRda = cpassTOrdRigaRda;
	}


	/**
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return quantita;
	}

	/**
	 * @param quantita the quantita to set
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return rmsRigaRdaId;
	}

	@Override
	public void setId(Integer id) {
		rmsRigaRdaId = id;

	}

}
