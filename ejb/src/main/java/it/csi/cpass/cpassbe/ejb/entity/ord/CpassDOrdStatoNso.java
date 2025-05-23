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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;

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
 * The persistent class for the cpass_d_ord_stato_nso database table.
 *
 */
@Entity
@Table(name="cpass_d_ord_stato_nso")
@NamedQuery(name="CpassDOrdStatoNso.findAll", query="SELECT c FROM CpassDOrdStatoNso c")
public class CpassDOrdStatoNso implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stato id. */
	@Id
	@SequenceGenerator(name="CPASS_D_ORD_STATO_NSO_STATO_NSOID_GENERATOR", sequenceName="CPASS_D_ORD_STATO_NSO_STATO_NSO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_ORD_STATO_NSO_STATO_NSOID_GENERATOR")
	@Column(name="stato_nso_id", unique=true, nullable=false)
	private Integer statoNsoId;

	/** The stato codice. */
	@Column(name="stato_nso_codice", nullable=false, length=50)
	private String statoNsoCodice;

	/** The stato descrizione. */
	@Column(name="stato_nso_descrizione", nullable=false, length=500)
	private String statoNsoDescrizione;

	/** The stato tipo. */
	@Column(name="stato_nso_tipo", nullable=false, length=200)
	private String statoNsoTipo;

	//	/** The cpass T interventos. */
	//	//bi-directional many-to-one association to CpassTPbaIntervento
	//	@OneToMany(mappedBy="cpassDStato")
	//	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the stato id.
	 *
	 * @return the stato id
	 */
	public Integer getStatoNsoId() {
		return this.statoNsoId;
	}

	/**
	 * Sets the stato id.
	 *
	 * @param statoId the new stato id
	 */
	public void setStatoNsoId(Integer statoNsoId) {
		this.statoNsoId = statoNsoId;
	}

	/**
	 * Gets the stato codice.
	 *
	 * @return the stato codice
	 */
	public String getStatoNsoCodice() {
		return this.statoNsoCodice;
	}

	/**
	 * Sets the stato codice.
	 *
	 * @param statoCodice the new stato codice
	 */
	public void setStatoNsoCodice(String statoNsoCodice) {
		this.statoNsoCodice = statoNsoCodice;
	}

	/**
	 * Gets the stato descrizione.
	 *
	 * @return the stato descrizione
	 */
	public String getStatoNsoDescrizione() {
		return this.statoNsoDescrizione;
	}

	/**
	 * Sets the stato descrizione.
	 *
	 * @param statoDescrizione the new stato descrizione
	 */
	public void setStatoNsoDescrizione(String statoNsoDescrizione) {
		this.statoNsoDescrizione = statoNsoDescrizione;
	}

	/**
	 * Gets the stato tipo.
	 *
	 * @return the stato tipo
	 */
	public String getStatoNsoTipo() {
		return this.statoNsoTipo;
	}

	/**
	 * Sets the stato tipo.
	 *
	 * @param statoTipo the new stato tipo
	 */
	public void setStatoNsoTipo(String statoNsoTipo) {
		this.statoNsoTipo = statoNsoTipo;
	}

	/**
	 * Gets the cpass T interventos.
	 *
	 * @return the cpass T interventos
	 */
	//	public List<CpassTPbaIntervento> getCpassTPbaInterventos() {
	//		return this.cpassTPbaInterventos;
	//	}

	/**
	 * Sets the cpass T interventos.
	 *
	 * @param cpassTPbaInterventos the new cpass T interventos
	 */
	//	public void setCpassTPbaInterventos(List<CpassTPbaIntervento> cpassTPbaInterventos) {
	//		this.cpassTPbaInterventos = cpassTPbaInterventos;
	//	}

	/**
	 * Adds the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	//	public CpassTPbaIntervento addCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
	//		getCpassTPbaInterventos().add(cpassTPbaIntervento);
	//		cpassTPbaIntervento.setCpassDStato(this);
	//
	//		return cpassTPbaIntervento;
	//	}

	/**
	 * Removes the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	//	public CpassTPbaIntervento removeCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
	//		getCpassTPbaInterventos().remove(cpassTPbaIntervento);
	//		cpassTPbaIntervento.setCpassDStato(null);
	//
	//		return cpassTPbaIntervento;
	//	}

	@Override
	public Integer getId() {
		return statoNsoId;
	}

	@Override
	public void setId(Integer id) {
		statoNsoId = id;
	}

}
