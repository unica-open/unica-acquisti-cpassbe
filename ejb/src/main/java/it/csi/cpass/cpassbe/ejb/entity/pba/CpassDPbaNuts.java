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
 * The persistent class for the cpass_d_nuts database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_nuts")
@NamedQuery(name="CpassDPbaNuts.findAll", query="SELECT c FROM CpassDPbaNuts c")
public class CpassDPbaNuts implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nuts id. */
	@Id
	@SequenceGenerator(name="CPASS_D_NUTS_NUTSID_GENERATOR", sequenceName="CPASS_D_NUTS_NUTS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_NUTS_NUTSID_GENERATOR")
	@Column(name="nuts_id", unique=true, nullable=false)
	private Integer nutsId;

	/** The nuts codice. */
	@Column(name="nuts_codice", nullable=false, length=50)
	private String nutsCodice;

	/** The nuts descrizione. */
	@Column(name="nuts_descrizione", nullable=false, length=200)
	private String nutsDescrizione;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaNuts")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the nuts id.
	 *
	 * @return the nuts id
	 */
	public Integer getNutsId() {
		return this.nutsId;
	}

	/**
	 * Sets the nuts id.
	 *
	 * @param nutsId the new nuts id
	 */
	public void setNutsId(Integer nutsId) {
		this.nutsId = nutsId;
	}

	/**
	 * Gets the nuts codice.
	 *
	 * @return the nuts codice
	 */
	public String getNutsCodice() {
		return this.nutsCodice;
	}

	/**
	 * Sets the nuts codice.
	 *
	 * @param nutsCodice the new nuts codice
	 */
	public void setNutsCodice(String nutsCodice) {
		this.nutsCodice = nutsCodice;
	}

	/**
	 * Gets the nuts descrizione.
	 *
	 * @return the nuts descrizione
	 */
	public String getNutsDescrizione() {
		return this.nutsDescrizione;
	}

	/**
	 * Sets the nuts descrizione.
	 *
	 * @param nutsDescrizione the new nuts descrizione
	 */
	public void setNutsDescrizione(String nutsDescrizione) {
		this.nutsDescrizione = nutsDescrizione;
	}

	/**
	 * Gets the cpass T interventos.
	 *
	 * @return the cpass T interventos
	 */
	public List<CpassTPbaIntervento> getCpassTPbaInterventos() {
		return this.cpassTPbaInterventos;
	}

	/**
	 * Sets the cpass T interventos.
	 *
	 * @param cpassTPbaInterventos the new cpass T interventos
	 */
	public void setCpassTPbaInterventos(List<CpassTPbaIntervento> cpassTPbaInterventos) {
		this.cpassTPbaInterventos = cpassTPbaInterventos;
	}

	/**
	 * Adds the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento addCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().add(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassDPbaNut(this);

		return cpassTPbaIntervento;
	}

	/**
	 * Removes the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento removeCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().remove(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassDPbaNut(null);

		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return nutsId;
	}

	@Override
	public void setId(Integer id) {
		nutsId = id;
	}

}
