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
 * The persistent class for the cpass_d_settore_interventi database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_settore_interventi")
@NamedQuery(name="CpassDPbaSettoreInterventi.findAll", query="SELECT c FROM CpassDPbaSettoreInterventi c")
public class CpassDPbaSettoreInterventi implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The settore interventi id. */
	@Id
	@SequenceGenerator(name="CPASS_D_SETTORE_INTERVENTI_SETTOREINTERVENTIID_GENERATOR", sequenceName="CPASS_D_SETTORE_INTERVENTI_SETTORE_INTERVENTI_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_SETTORE_INTERVENTI_SETTOREINTERVENTIID_GENERATOR")
	@Column(name="settore_interventi_id", unique=true, nullable=false)
	private Integer settoreInterventiId;

	/** The settore interventi codice. */
	@Column(name="settore_interventi_codice", nullable=false, length=500)
	private String settoreInterventiCodice;

	/** The settore interventi descrizione. */
	@Column(name="settore_interventi_descrizione", nullable=false, length=500)
	private String settoreInterventiDescrizione;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaSettoreInterventi")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the settore interventi id.
	 *
	 * @return the settore interventi id
	 */
	public Integer getSettoreInterventiId() {
		return this.settoreInterventiId;
	}

	/**
	 * Sets the settore interventi id.
	 *
	 * @param settoreInterventiId the new settore interventi id
	 */
	public void setSettoreInterventiId(Integer settoreInterventiId) {
		this.settoreInterventiId = settoreInterventiId;
	}

	/**
	 * @return the settoreInterventiCodice
	 */
	public String getSettoreInterventiCodice() {
		return settoreInterventiCodice;
	}

	/**
	 * @param settoreInterventiCodice the settoreInterventiCodice to set
	 */
	public void setSettoreInterventiCodice(String settoreInterventiCodice) {
		this.settoreInterventiCodice = settoreInterventiCodice;
	}

	/**
	 * Gets the settore interventi descrizione.
	 *
	 * @return the settore interventi descrizione
	 */
	public String getSettoreInterventiDescrizione() {
		return this.settoreInterventiDescrizione;
	}

	/**
	 * Sets the settore interventi descrizione.
	 *
	 * @param settoreInterventiDescrizione the new settore interventi descrizione
	 */
	public void setSettoreInterventiDescrizione(String settoreInterventiDescrizione) {
		this.settoreInterventiDescrizione = settoreInterventiDescrizione;
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
		cpassTPbaIntervento.setCpassDPbaSettoreInterventi(this);

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
		cpassTPbaIntervento.setCpassDPbaSettoreInterventi(null);

		return cpassTPbaIntervento;
	}

	@Override
	public Integer getId() {
		return settoreInterventiId;
	}

	@Override
	public void setId(Integer id) {
		settoreInterventiId = id;
	}

}
