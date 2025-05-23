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
 * The persistent class for the cpass_d_ausa database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_ausa")
@NamedQuery(name="CpassDPbaAusa.findAll", query="SELECT c FROM CpassDPbaAusa c")
public class CpassDPbaAusa implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ausa id. */
	@Id
	@SequenceGenerator(name="CPASS_D_AUSA_AUSAID_GENERATOR", sequenceName="CPASS_D_AUSA_AUSA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_AUSA_AUSAID_GENERATOR")
	@Column(name="ausa_id")
	private Integer ausaId;

	/** The ausa codice. */
	@Column(name="ausa_codice")
	private String ausaCodice;

	/** The ausa codice fiscale. */
	@Column(name="ausa_codice_fiscale")
	private String ausaCodiceFiscale;

	/** The ausa descrizione. */
	@Column(name="ausa_descrizione")
	private String ausaDescrizione;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaAusa")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the ausa id.
	 *
	 * @return the ausa id
	 */
	public Integer getAusaId() {
		return this.ausaId;
	}

	/**
	 * Sets the ausa id.
	 *
	 * @param ausaId the new ausa id
	 */
	public void setAusaId(Integer ausaId) {
		this.ausaId = ausaId;
	}

	/**
	 * Gets the ausa codice.
	 *
	 * @return the ausa codice
	 */
	public String getAusaCodice() {
		return this.ausaCodice;
	}

	/**
	 * Sets the ausa codice.
	 *
	 * @param ausaCodice the new ausa codice
	 */
	public void setAusaCodice(String ausaCodice) {
		this.ausaCodice = ausaCodice;
	}

	/**
	 * Gets the ausa codice fiscale.
	 *
	 * @return the ausa codice fiscale
	 */
	public String getAusaCodiceFiscale() {
		return this.ausaCodiceFiscale;
	}

	/**
	 * Sets the ausa codice fiscale.
	 *
	 * @param ausaCodiceFiscale the new ausa codice fiscale
	 */
	public void setAusaCodiceFiscale(String ausaCodiceFiscale) {
		this.ausaCodiceFiscale = ausaCodiceFiscale;
	}

	/**
	 * Gets the ausa descrizione.
	 *
	 * @return the ausa descrizione
	 */
	public String getAusaDescrizione() {
		return this.ausaDescrizione;
	}

	/**
	 * Sets the ausa descrizione.
	 *
	 * @param ausaDescrizione the new ausa descrizione
	 */
	public void setAusaDescrizione(String ausaDescrizione) {
		this.ausaDescrizione = ausaDescrizione;
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
		cpassTPbaIntervento.setCpassDPbaAusa(this);
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
		cpassTPbaIntervento.setCpassDPbaAusa(null);
		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return ausaId;
	}

	@Override
	public void setId(Integer id) {
		ausaId = id;
	}

}
