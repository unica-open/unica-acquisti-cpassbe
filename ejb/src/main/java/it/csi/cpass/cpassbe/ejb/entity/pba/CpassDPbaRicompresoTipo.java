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
 * The persistent class for the cpass_d_ricompreso database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_ricompreso_tipo")
@NamedQuery(name="CpassDPbaRicompresoTipo.findAll", query="SELECT c FROM CpassDPbaRicompresoTipo c")
public class CpassDPbaRicompresoTipo implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ricompreso tipo id. */
	@Id
	@SequenceGenerator(name="CPASS_D_RICOMPRESO_TIPO_RICOMPRESOTIPOID_GENERATOR", sequenceName="CPASS_D_RICOMPRESO_TIPO_RICOMPRESO_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_RICOMPRESO_TIPO_RICOMPRESOTIPOID_GENERATOR")
	@Column(name="ricompreso_tipo_id")
	private Integer ricompresoTipoId;

	/** The ricompreso tipo codice. */
	@Column(name="ricompreso_tipo_codice")
	private String ricompresoTipoCodice;

	/** The ricompreso tipo descrizione. */
	@Column(name="ricompreso_tipo_descrizione")
	private String ricompresoTipoDescrizione;

	/** The ricompreso tipo cui obbligatorio. */
	@Column(name="ricompreso_tipo_cui_obbligatorio")
	private Boolean ricompresoTipoCuiObbligatorio;
	
	/** The ricompreso tipo cui obbligatorio. */
	@Column(name="ricompreso_tipo_conteggio_importi")
	private Boolean ricompresoTipoConteggioImporti;
	

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaRicompresoTipo")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the ricompreso tipo id.
	 *
	 * @return the ricompresoTipoId
	 */
	public Integer getRicompresoTipoId() {
		return this.ricompresoTipoId;
	}

	/**
	 * Sets the ricompreso tipo id.
	 *
	 * @param ricompresoTipoId the ricompresoTipoId to set
	 */
	public void setRicompresoTipoId(Integer ricompresoTipoId) {
		this.ricompresoTipoId = ricompresoTipoId;
	}

	/**
	 * Gets the ricompreso tipo codice.
	 *
	 * @return the ricompresoTipoCodice
	 */
	public String getRicompresoTipoCodice() {
		return this.ricompresoTipoCodice;
	}

	/**
	 * Sets the ricompreso tipo codice.
	 *
	 * @param ricompresoTipoCodice the ricompresoTipoCodice to set
	 */
	public void setRicompresoTipoCodice(String ricompresoTipoCodice) {
		this.ricompresoTipoCodice = ricompresoTipoCodice;
	}

	/**
	 * Gets the ricompreso tipo cui obbligatorio.
	 *
	 * @return the ricompresoTipoCuiObbligatorio
	 */
	public Boolean getRicompresoTipoCuiObbligatorio() {
		return this.ricompresoTipoCuiObbligatorio;
	}

	/**
	 * Sets the ricompreso tipo cui obbligatorio.
	 *
	 * @param ricompresoTipoCuiObbligatorio the ricompresoTipoCuiObbligatorio to set
	 */
	public void setRicompresoTipoCuiObbligatorio(Boolean ricompresoTipoCuiObbligatorio) {
		this.ricompresoTipoCuiObbligatorio = ricompresoTipoCuiObbligatorio;
	}

	/**
	 * Gets the ricompreso tipo descrizione.
	 *
	 * @return the ricompresoTipoDescrizione
	 */
	public String getRicompresoTipoDescrizione() {
		return this.ricompresoTipoDescrizione;
	}

	/**
	 * Sets the ricompreso tipo descrizione.
	 *
	 * @param ricompresoTipoDescrizione the ricompresoTipoDescrizione to set
	 */
	public void setRicompresoTipoDescrizione(String ricompresoTipoDescrizione) {
		this.ricompresoTipoDescrizione = ricompresoTipoDescrizione;
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
		cpassTPbaIntervento.setCpassDPbaRicompresoTipo(this);
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
		cpassTPbaIntervento.setCpassDPbaRicompresoTipo(null);
		return cpassTPbaIntervento;
	}

	
	/**
	 * @return the ricompresoTipoConteggioImporti
	 */
	public Boolean getRicompresoTipoConteggioImporti() {
		return ricompresoTipoConteggioImporti;
	}

	/**
	 * @param ricompresoTipoConteggioImporti the ricompresoTipoConteggioImporti to set
	 */
	public void setRicompresoTipoConteggioImporti(Boolean ricompresoTipoConteggioImporti) {
		this.ricompresoTipoConteggioImporti = ricompresoTipoConteggioImporti;
	}

	@Override
	public Integer getId() {
		return ricompresoTipoId;
	}

	@Override
	public void setId(Integer id) {
		ricompresoTipoId = id;
	}

}
