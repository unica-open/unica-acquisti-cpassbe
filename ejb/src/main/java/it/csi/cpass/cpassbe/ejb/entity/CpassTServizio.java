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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_servizio database table.
 *
 */
@Entity
@Table(name="cpass_t_servizio")
@NamedQuery(name="CpassTServizio.findAll", query="SELECT c FROM CpassTServizio c")
public class CpassTServizio implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	/** The servizio id. */
	@Id
	@SequenceGenerator(name="CPASS_T_SERVIZIO_SERVIZIOID_GENERATOR", sequenceName="CPASS_T_SERVIZIO_SERVIZIO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SERVIZIO_SERVIZIOID_GENERATOR")
	@Column(name="servizio_id", unique=true, nullable=false)
	private Integer servizioId;

	@Column(name="servizio_codice")
	private String servizioCodice;

	@Column(name="servizio_descrizione")
	private String servizioDescrizione;

	//bi-directional many-to-one association to CpassRFruitoreServizio
	@OneToMany(mappedBy="cpassTServizio")
	private List<CpassRFruitoreServizio> cpassRFruitoreServizios;

	public CpassTServizio() {
	}

	public Integer getServizioId() {
		return this.servizioId;
	}

	public void setServizioId(Integer servizioId) {
		this.servizioId = servizioId;
	}

	public String getServizioCodice() {
		return this.servizioCodice;
	}

	public void setServizioCodice(String servizioCodice) {
		this.servizioCodice = servizioCodice;
	}

	public String getServizioDescrizione() {
		return this.servizioDescrizione;
	}

	public void setServizioDescrizione(String servizioDescrizione) {
		this.servizioDescrizione = servizioDescrizione;
	}

	public List<CpassRFruitoreServizio> getCpassRFruitoreServizios() {
		return this.cpassRFruitoreServizios;
	}

	public void setCpassRFruitoreServizios(List<CpassRFruitoreServizio> cpassRFruitoreServizios) {
		this.cpassRFruitoreServizios = cpassRFruitoreServizios;
	}

	public CpassRFruitoreServizio addCpassRFruitoreServizio(CpassRFruitoreServizio cpassRFruitoreServizio) {
		getCpassRFruitoreServizios().add(cpassRFruitoreServizio);
		cpassRFruitoreServizio.setCpassTServizio(this);

		return cpassRFruitoreServizio;
	}

	public CpassRFruitoreServizio removeCpassRFruitoreServizio(CpassRFruitoreServizio cpassRFruitoreServizio) {
		getCpassRFruitoreServizios().remove(cpassRFruitoreServizio);
		cpassRFruitoreServizio.setCpassTServizio(null);

		return cpassRFruitoreServizio;
	}
	@Override
	public Integer getId() {
		return servizioId;
	}

	@Override
	public void setId(Integer id) {
		servizioId = id;
	}
}
