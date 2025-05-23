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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_pba_intervento_cig database table.
 *
 */
@Entity
@Table(name="cpass_t_pba_intervento_cig")
@NamedQuery(name="CpassTPbaInterventoCig.findAll", query="SELECT c FROM CpassTPbaInterventoCig c")
public class CpassTPbaInterventoCig implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_PBA_INTERVENTO_CIG_INTERVENTOCIGID_GENERATOR", sequenceName="CPASS_T_PBA_INTERVENTO_CIG_INTERVENTO_CIG_ID_SEQ", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_PBA_INTERVENTO_CIG_INTERVENTOCIGID_GENERATOR")
	@Column(name="intervento_cig_id")
	private Integer interventoCigId;

	private String cig;

	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id")
	private CpassTPbaIntervento cpassTPbaIntervento;

	public CpassTPbaInterventoCig() {
	}

	public Integer getInterventoCigId() {
		return this.interventoCigId;
	}

	public void setInterventoCigId(Integer interventoCigId) {
		this.interventoCigId = interventoCigId;
	}


	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}

	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return this.cpassTPbaIntervento;
	}

	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return interventoCigId;
	}

	@Override
	public void setId(Integer id) {
		interventoCigId = id;
	}

}
