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
 * The persistent class for the cpass_d_priorita database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_priorita")
@NamedQuery(name="CpassDPbaPriorita.findAll", query="SELECT c FROM CpassDPbaPriorita c")
public class CpassDPbaPriorita implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The priorita id. */
	@Id
	@SequenceGenerator(name="CPASS_D_PRIORITA_PRIORITAID_GENERATOR", sequenceName="CPASS_D_PRIORITA_PRIORITA_ID_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_PRIORITA_PRIORITAID_GENERATOR")
	@Column(name="priorita_id", unique=true, nullable=false)
	private Integer prioritaId;

	/** The priorita codice. */
	@Column(name="priorita_codice", unique=true)
	private String prioritaCodice;

	/** The priorita descrizione. */
	@Column(name="priorita_descrizione")
	private String prioritaDescrizione;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaPriorita")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the priorita id.
	 *
	 * @return the priorita id
	 */
	public Integer getPrioritaId() {
		return this.prioritaId;
	}

	/**
	 * Sets the priorita id.
	 *
	 * @param prioritaId the new priorita id
	 */
	public void setPrioritaId(Integer prioritaId) {
		this.prioritaId = prioritaId;
	}

	/**
	 * Gets the priorita codice.
	 *
	 * @return the priorita codice
	 */
	public String getPrioritaCodice() {
		return this.prioritaCodice;
	}

	/**
	 * Sets the priorita codice.
	 *
	 * @param prioritaCodice the new priorita codice
	 */
	public void setPrioritaCodice(String prioritaCodice) {
		this.prioritaCodice = prioritaCodice;
	}

	/**
	 * Gets the priorita descrizione.
	 *
	 * @return the priorita descrizione
	 */
	public String getPrioritaDescrizione() {
		return this.prioritaDescrizione;
	}

	/**
	 * Sets the priorita descrizione.
	 *
	 * @param prioritaDescrizione the new priorita descrizione
	 */
	public void setPrioritaDescrizione(String prioritaDescrizione) {
		this.prioritaDescrizione = prioritaDescrizione;
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
		cpassTPbaIntervento.setCpassDPbaPriorita(this);

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
		cpassTPbaIntervento.setCpassDPbaPriorita(null);

		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return prioritaId;
	}

	@Override
	public void setId(Integer id) {
		prioritaId = id;
	}

}
