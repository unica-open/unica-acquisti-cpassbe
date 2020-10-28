/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
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


@Entity
@Table(name="cpass_r_pba_storico_intervento_rup")
@NamedQuery(name="CpassRPbaStoricoInterventoRup.findAll", query="SELECT c FROM CpassRPbaStoricoInterventoRup c")
public class CpassRPbaStoricoInterventoRup implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_PBA_INTERVENTO_RUPINTERVENTORUPID_GENERATOR", sequenceName="CPASS_R_PBA_STORICO_INTERVENTO_RUP_INTERVENTO_RUP_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_PBA_INTERVENTO_RUPINTERVENTORUPID_GENERATOR")

	@Column(name="intervento_rup_id")
	private Integer interventoRupId;

	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id")
	private CpassTPbaIntervento cpassTPbaIntervento;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_rup_id")
	private CpassTUtente cpassTUtenteRup;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_id")
	private CpassTUtente cpassTUtente;

	@Column(name="data_storicizzazione")
	private Date dataStoricizzazione;

	
	public CpassRPbaStoricoInterventoRup() {
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getInterventoRupId() {
		return this.interventoRupId;
	}

	public void setInterventoRupId(Integer interventoRupId) {
		this.interventoRupId = interventoRupId;
	}

	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return this.cpassTPbaIntervento;
	}

	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
	}

	public CpassTUtente getCpassTUtenteRup() {
		return this.cpassTUtenteRup;
	}

	public void setCpassTUtenteRup(CpassTUtente cpassTUtenteRup) {
		this.cpassTUtenteRup = cpassTUtenteRup;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	/**
	 * @return the dataStoricizzazione
	 */
	public Date getDataStoricizzazione() {
		return dataStoricizzazione;
	}

	/**
	 * @param dataStoricizzazione the dataStoricizzazione to set
	 */
	public void setDataStoricizzazione(Date dataStoricizzazione) {
		this.dataStoricizzazione = dataStoricizzazione;
	}

	@Override
	public Integer getId() {
		return interventoRupId;
	}

	@Override
	public void setId(Integer id) {
		interventoRupId = id;
	}

}
