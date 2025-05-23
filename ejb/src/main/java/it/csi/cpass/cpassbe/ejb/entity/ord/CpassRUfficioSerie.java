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

import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the cpass_r_ufficio_serie database table.
 *
 */
@Entity
@Table(name="cpass_r_ufficio_serie")
@NamedQuery(name="CpassRUfficioSerie.findAll", query="SELECT c FROM CpassRUfficioSerie c")
public class CpassRUfficioSerie implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_UFFICIO_SERIE_UFFICIOSERIEID_GENERATOR" , sequenceName="cpass_r_ufficio_serie_ufficio_serie_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_UFFICIO_SERIE_UFFICIOSERIEID_GENERATOR")
	@Column(name="UFFICIO_SERIE_ID")
	private Integer ufficioSerieId;

	@Column(name="uuid_serie_acta")
	private String uuidSerieActa;

	//bi-directional many-to-one association to CpassTUfficio
	@ManyToOne
	@JoinColumn(name="ufficio_id")
	private CpassTUfficio cpassTUfficio;

	public CpassRUfficioSerie() {
	}


	/**
	 * @return the ufficioSerieId
	 */
	public Integer getUfficioSerieId() {
		return ufficioSerieId;
	}


	/**
	 * @param ufficioSerieId the ufficioSerieId to set
	 */
	public void setUfficioSerieId(Integer ufficioSerieId) {
		this.ufficioSerieId = ufficioSerieId;
	}


	/**
	 * @return the uuidSerieActa
	 */
	public String getUuidSerieActa() {
		return uuidSerieActa;
	}


	/**
	 * @param uuidSerieActa the uuidSerieActa to set
	 */
	public void setUuidSerieActa(String uuidSerieActa) {
		this.uuidSerieActa = uuidSerieActa;
	}


	/**
	 * @return the cpassTUfficio
	 */
	public CpassTUfficio getCpassTUfficio() {
		return cpassTUfficio;
	}


	/**
	 * @param cpassTUfficio the cpassTUfficio to set
	 */
	public void setCpassTUfficio(CpassTUfficio cpassTUfficio) {
		this.cpassTUfficio = cpassTUfficio;
	}


	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return ufficioSerieId;
	}

	@Override
	public void setId(Integer id) {
		ufficioSerieId = id;

	}

}
