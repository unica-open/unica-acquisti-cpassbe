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
package it.csi.cpass.cpassbe.ejb.entity.pba;

import java.io.Serializable;

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

import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_r_intervento_cpv database table.
 *
 */
@Entity
@Table(name="cpass_r_intervento_cpv")
@NamedQuery(name="CpassRInterventoCpv.findAll", query="SELECT c FROM CpassRInterventoCpv c")
public class CpassRInterventoCpv implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;



	@Id
	@SequenceGenerator(name="cpass_r_intervento_cpvinterventoCpvId_GENERATOR", sequenceName="CPASS_R_INTERVENTO_CPV_INTERVENTO_CPV_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_r_intervento_cpvinterventoCpvId_GENERATOR")
	@Column(name="intervento_cpv_id", unique=true, nullable=false)
	private Integer interventoCpvId;

	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_id")
	private CpassDCpv cpassDCpv;

	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id")
	private CpassTPbaIntervento cpassTPbaIntervento;

	public CpassRInterventoCpv() {
	}

	public Integer getInterventoCpvId() {
		return this.interventoCpvId;
	}

	public void setInterventoCpvId(Integer interventoCpvId) {
		this.interventoCpvId = interventoCpvId;
	}

	public CpassDCpv getCpassDCpv() {
		return this.cpassDCpv;
	}

	public void setCpassDCpv(CpassDCpv cpassDCpv) {
		this.cpassDCpv = cpassDCpv;
	}

	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return this.cpassTPbaIntervento;
	}

	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return interventoCpvId;
	}

	@Override
	public void setId(Integer id) {
		interventoCpvId = id;

	}

}
