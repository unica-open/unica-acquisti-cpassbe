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
 * The persistent class for the cpass_d_mod_affidamento database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_mod_affidamento")
@NamedQuery(name="CpassDPbaModAffidamento.findAll", query="SELECT c FROM CpassDPbaModAffidamento c")
public class CpassDPbaModAffidamento implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mod affidamento id. */
	@Id
	@SequenceGenerator(name="CPASS_D_MOD_AFFIDAMENTO_MODAFFIDAMENTOID_GENERATOR", sequenceName="CPASS_D_MOD_AFFIDAMENTO_MOD_AFFIDAMENTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_MOD_AFFIDAMENTO_MODAFFIDAMENTOID_GENERATOR")
	@Column(name="mod_affidamento_id")
	private Integer modAffidamentoId;

	/** The mod affidamento codice. */
	@Column(name="mod_affidamento_codice")
	private String modAffidamentoCodice;

	/** The mod affidamento descrizione. */
	@Column(name="mod_affidamento_descrizione")
	private String modAffidamentoDescrizione;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaModAffidamento")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the mod affidamento id.
	 *
	 * @return the mod affidamento id
	 */
	public Integer getModAffidamentoId() {
		return this.modAffidamentoId;
	}

	/**
	 * Sets the mod affidamento id.
	 *
	 * @param modAffidamentoId the new mod affidamento id
	 */
	public void setModAffidamentoId(Integer modAffidamentoId) {
		this.modAffidamentoId = modAffidamentoId;
	}

	/**
	 * Gets the mod affidamento codice.
	 *
	 * @return the mod affidamento codice
	 */
	public String getModAffidamentoCodice() {
		return this.modAffidamentoCodice;
	}

	/**
	 * Sets the mod affidamento codice.
	 *
	 * @param modAffidamentoCodice the new mod affidamento codice
	 */
	public void setModAffidamentoCodice(String modAffidamentoCodice) {
		this.modAffidamentoCodice = modAffidamentoCodice;
	}

	/**
	 * Gets the mod affidamento descrizione.
	 *
	 * @return the mod affidamento descrizione
	 */
	public String getModAffidamentoDescrizione() {
		return this.modAffidamentoDescrizione;
	}

	/**
	 * Sets the mod affidamento descrizione.
	 *
	 * @param modAffidamentoDescrizione the new mod affidamento descrizione
	 */
	public void setModAffidamentoDescrizione(String modAffidamentoDescrizione) {
		this.modAffidamentoDescrizione = modAffidamentoDescrizione;
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
		cpassTPbaIntervento.setCpassDPbaModAffidamento(this);

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
		cpassTPbaIntervento.setCpassDPbaModAffidamento(null);

		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return modAffidamentoId;
	}

	@Override
	public void setId(Integer id) {
		modAffidamentoId = id;
	}

}
