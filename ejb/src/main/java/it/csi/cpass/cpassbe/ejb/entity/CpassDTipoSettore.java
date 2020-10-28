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
 * The persistent class for the cpass_d_tipo_settore database table.
 *
 */
@Entity
@Table(name="cpass_d_tipo_settore")
@NamedQuery(name="CpassDTipoSettore.findAll", query="SELECT c FROM CpassDTipoSettore c")
public class CpassDTipoSettore implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo settore id. */
	@Id
	@SequenceGenerator(name="CPASS_D_TIPO_SETTORE_TIPOSETTOREID_GENERATOR", sequenceName="CPASS_D_TIPO_SETTORE_TIPO_SETTORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_TIPO_SETTORE_TIPOSETTOREID_GENERATOR")
	@Column(name="tipo_settore_id", unique=true, nullable=false)
	private Integer tipoSettoreId;

	/** The tipo tipo_settore_codice. */
	@Column(name="tipo_settore_codice", nullable=false, length=250)
	private String tipoSettoreCodice;

	/** The tipo tipo_settore_descrizione. */
	@Column(name="tipo_settore_descrizione", nullable=false, length=250)
	private String tipoSettoreDescrizione;




	/** The cpass T settores. */
	//bi-directional many-to-one association to CpassTSettore
	@OneToMany(mappedBy="cpassDTipoSettore")
	private List<CpassTSettore> cpassTSettores;

	/**
	 * Gets the tipo settore id.
	 *
	 * @return the tipo settore id
	 */
	public Integer getTipoSettoreId() {
		return this.tipoSettoreId;
	}

	/**
	 * Sets the tipo settore id.
	 *
	 * @param tipoSettoreId the new tipo settore id
	 */
	public void setTipoSettoreId(Integer tipoSettoreId) {
		this.tipoSettoreId = tipoSettoreId;
	}


	/**
	 * @return the tipoSettoreCodice
	 */
	public String getTipoSettoreCodice() {
		return tipoSettoreCodice;
	}

	/**
	 * @param tipoSettoreCodice the tipoSettoreCodice to set
	 */
	public void setTipoSettoreCodice(String tipoSettoreCodice) {
		this.tipoSettoreCodice = tipoSettoreCodice;
	}

	/**
	 * @return the tipoSettoreDescrizione
	 */
	public String getTipoSettoreDescrizione() {
		return tipoSettoreDescrizione;
	}

	/**
	 * @param tipoSettoreDescrizione the tipoSettoreDescrizione to set
	 */
	public void setTipoSettoreDescrizione(String tipoSettoreDescrizione) {
		this.tipoSettoreDescrizione = tipoSettoreDescrizione;
	}

	/**
	 * Gets the cpass T settores.
	 *
	 * @return the cpass T settores
	 */
	public List<CpassTSettore> getCpassTSettores() {
		return this.cpassTSettores;
	}

	/**
	 * Sets the cpass T settores.
	 *
	 * @param cpassTSettores the new cpass T settores
	 */
	public void setCpassTSettores(List<CpassTSettore> cpassTSettores) {
		this.cpassTSettores = cpassTSettores;
	}

	/**
	 * Adds the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore addCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().add(cpassTSettore);
		cpassTSettore.setCpassDTipoSettore(this);

		return cpassTSettore;
	}

	/**
	 * Removes the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore removeCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().remove(cpassTSettore);
		cpassTSettore.setCpassDTipoSettore(null);

		return cpassTSettore;
	}

	@Override
	public Integer getId() {
		return tipoSettoreId;
	}

	@Override
	public void setId(Integer id) {
		tipoSettoreId = id;
	}

}
