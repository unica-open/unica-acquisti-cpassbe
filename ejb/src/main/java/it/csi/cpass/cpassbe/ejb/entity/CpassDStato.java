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
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;


/**
 * The persistent class for the cpass_d_stato database table.
 *
 */
@Entity
@Table(name="cpass_d_stato")
@NamedQuery(name="CpassDStato.findAll", query="SELECT c FROM CpassDStato c")
public class CpassDStato implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stato id. */
	@Id
	@SequenceGenerator(name="CPASS_D_STATO_STATOID_GENERATOR", sequenceName="CPASS_D_STATO_STATO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_STATO_STATOID_GENERATOR")
	@Column(name="stato_id", unique=true, nullable=false)
	private Integer statoId;

	/** The stato codice. */
	@Column(name="stato_codice", nullable=false, length=50)
	private String statoCodice;

	/** The stato descrizione. */
	@Column(name="stato_descrizione", nullable=false, length=200)
	private String statoDescrizione;

	/** The stato tipo. */
	@Column(name="stato_tipo", nullable=false, length=200)
	private String statoTipo;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDStato")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the stato id.
	 *
	 * @return the stato id
	 */
	public Integer getStatoId() {
		return this.statoId;
	}

	/**
	 * Sets the stato id.
	 *
	 * @param statoId the new stato id
	 */
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}

	/**
	 * Gets the stato codice.
	 *
	 * @return the stato codice
	 */
	public String getStatoCodice() {
		return this.statoCodice;
	}

	/**
	 * Sets the stato codice.
	 *
	 * @param statoCodice the new stato codice
	 */
	public void setStatoCodice(String statoCodice) {
		this.statoCodice = statoCodice;
	}

	/**
	 * Gets the stato descrizione.
	 *
	 * @return the stato descrizione
	 */
	public String getStatoDescrizione() {
		return this.statoDescrizione;
	}

	/**
	 * Sets the stato descrizione.
	 *
	 * @param statoDescrizione the new stato descrizione
	 */
	public void setStatoDescrizione(String statoDescrizione) {
		this.statoDescrizione = statoDescrizione;
	}

	/**
	 * Gets the stato tipo.
	 *
	 * @return the stato tipo
	 */
	public String getStatoTipo() {
		return this.statoTipo;
	}

	/**
	 * Sets the stato tipo.
	 *
	 * @param statoTipo the new stato tipo
	 */
	public void setStatoTipo(String statoTipo) {
		this.statoTipo = statoTipo;
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
		cpassTPbaIntervento.setCpassDStato(this);

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
		cpassTPbaIntervento.setCpassDStato(null);

		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return statoId;
	}

	@Override
	public void setId(Integer id) {
		statoId = id;
	}

}
