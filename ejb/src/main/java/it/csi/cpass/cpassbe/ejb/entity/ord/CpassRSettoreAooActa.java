/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord;

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

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;



/**
 * The persistent class for the cpass_r_settore_aoo_acta database table.
 *
 */
@Entity
@Table(name="cpass_r_settore_aoo_acta")
@NamedQuery(name="CpassRSettoreAooActa.findAll", query="SELECT c FROM CpassRSettoreAooActa c")
public class CpassRSettoreAooActa implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_SETTORE_AOO_ACTA_SETTOREAOOID_GENERATOR" , sequenceName="cpass_r_settore_aoo_acta_settore_aoo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_SETTORE_AOO_ACTA_SETTOREAOOID_GENERATOR")
	@Column(name="settore_aoo_id")
	private Integer settoreAooId;

	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	//bi-directional many-to-one association to CpassDAooActa
	@ManyToOne
	@JoinColumn(name="aoo_acta_id")
	private CpassDAooActa cpassDAooActa;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	public CpassRSettoreAooActa() {
	}

	public Integer getSettoreAooId() {
		return this.settoreAooId;
	}

	public void setSettoreAooId(Integer settoreAooId) {
		this.settoreAooId = settoreAooId;
	}

	public Date getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public CpassDAooActa getCpassDAooActa() {
		return this.cpassDAooActa;
	}

	public void setCpassDAooActa(CpassDAooActa cpassDAooActa) {
		this.cpassDAooActa = cpassDAooActa;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return settoreAooId;
	}

	@Override
	public void setId(Integer id) {
		settoreAooId = id;
	}


}
