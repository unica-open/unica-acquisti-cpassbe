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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_ufficio database table.
 *
 */
@Entity
@Table(name="csi_log_audit")
@NamedQuery(name="CsiLogAudit.findAll", query="SELECT c FROM CsiLogAudit c")
public class CsiLogAudit implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="CPASS_T_CSI_LOG_AUDITID_GENERATOR", sequenceName="csi_log_audit_id_audit_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_CSI_LOG_AUDITID_GENERATOR")
	@Column(name="id_audit")
	private Integer auditId;

	@Column(name="cf_utente")
	private String cfUtente;

	@Column(name="operazione")
	private String operazione;

	@Column(name="data_ora")
	private Date dataOra;

	@Override
	public Integer getId() {
		return auditId;
	}


	/**
	 * @return the auditId
	 */
	public Integer getAuditId() {
		return auditId;
	}


	/**
	 * @param audithId the audithId to set
	 */
	public void setAudithId(Integer audithId) {
		this.auditId = audithId;
	}


	/**
	 * @return the cfUtente
	 */
	public String getCfUtente() {
		return cfUtente;
	}


	/**
	 * @param cfUtente the cfUtente to set
	 */
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}


	/**
	 * @return the operazione
	 */
	public String getOperazione() {
		return operazione;
	}


	/**
	 * @param operazione the operazione to set
	 */
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}


	/**
	 * @return the dataOra
	 */
	public Date getDataOra() {
		return dataOra;
	}


	/**
	 * @param dataOra the dataOra to set
	 */
	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}


	@Override
	public void setId(Integer id) {
		auditId = id;
	}


}
