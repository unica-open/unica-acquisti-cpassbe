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

import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_r_pba_stati_intervento database table.
 *
 */
@Entity
@Table(name="cpass_r_pba_stati_intervento")
@NamedQuery(name="CpassRPbaStatiIntervento.findAll", query="SELECT c FROM CpassRPbaStatiIntervento c")
public class CpassRPbaStatiIntervento implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_PBA_STATI_INTERVENTO_STATIINTERVENTOID_GENERATOR" , sequenceName="cpass_r_pba_stati_intervento_stati_intervento_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_PBA_STATI_INTERVENTO_STATIINTERVENTOID_GENERATOR")

	@Column(name="stati_intervento_id")
	private Integer statiInterventoId;

	private Date data;

	private String motivazione;

	private String stato;

	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id")
	private CpassTPbaIntervento cpassTPbaIntervento;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id")
	private CpassTUtente cpassTUtente;



	public CpassRPbaStatiIntervento() {
	}

	public Integer getStatiInterventoId() {
		return this.statiInterventoId;
	}

	public void setStatiInterventoId(Integer statiInterventoId) {
		this.statiInterventoId = statiInterventoId;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMotivazione() {
		return this.motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return this.cpassTPbaIntervento;
	}

	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	@Override
	public Integer getId() {
		return statiInterventoId;
	}

	@Override
	public void setId(Integer id) {
		statiInterventoId = id;
	}
}
