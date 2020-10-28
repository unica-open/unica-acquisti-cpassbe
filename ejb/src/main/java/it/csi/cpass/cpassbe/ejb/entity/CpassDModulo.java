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
 * The persistent class for the cpass_d_modulo database table.
 *
 */
@Entity
@Table(name="cpass_d_modulo")
@NamedQuery(name="CpassDModulo.findAll", query="SELECT c FROM CpassDModulo c")
public class CpassDModulo implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The modulo id. */
	@Id
	@SequenceGenerator(name="CPASS_D_MODULO_MODULOID_GENERATOR", sequenceName="CPASS_D_MODULO_MODULO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_MODULO_MODULOID_GENERATOR")
	@Column(name="modulo_id", unique=true, nullable=false)
	private Integer moduloId;

	/** The modulo moduloCodice. */
	@Column(name="modulo_codice", nullable=false)
	private String moduloCodice;

	/** The modulo modulo_descrizione. */
	@Column(name="modulo_descrizione", nullable=false)
	private String moduloDescrizione;

	/** The cpass R ruolo modulos. */
	//bi-directional many-to-one association to CpassRRuoloModulo
	@OneToMany(mappedBy="cpassDModulo")
	private List<CpassRRuoloModulo> cpassRRuoloModulos;

	/**
	 * Gets the modulo id.
	 *
	 * @return the modulo id
	 */
	public Integer getModuloId() {
		return this.moduloId;
	}

	/**
	 * Sets the modulo id.
	 *
	 * @param moduloId the new modulo id
	 */
	public void setModuloId(Integer moduloId) {
		this.moduloId = moduloId;
	}


	/**
	 * @return the moduloCodice
	 */
	public String getModuloCodice() {
		return moduloCodice;
	}

	/**
	 * @param moduloCodice the moduloCodice to set
	 */
	public void setModuloCodice(String moduloCodice) {
		this.moduloCodice = moduloCodice;
	}

	/**
	 * @return the moduloDescrizione
	 */
	public String getModuloDescrizione() {
		return moduloDescrizione;
	}

	/**
	 * @param moduloDescrizione the moduloDescrizione to set
	 */
	public void setModuloDescrizione(String moduloDescrizione) {
		this.moduloDescrizione = moduloDescrizione;
	}

	/**
	 * Gets the cpass R ruolo modulos.
	 *
	 * @return the cpass R ruolo modulos
	 */
	public List<CpassRRuoloModulo> getCpassRRuoloModulos() {
		return this.cpassRRuoloModulos;
	}

	/**
	 * Sets the cpass R ruolo modulos.
	 *
	 * @param cpassRRuoloModulos the new cpass R ruolo modulos
	 */
	public void setCpassRRuoloModulos(List<CpassRRuoloModulo> cpassRRuoloModulos) {
		this.cpassRRuoloModulos = cpassRRuoloModulos;
	}

	/**
	 * Adds the cpass R ruolo modulo.
	 *
	 * @param cpassRRuoloModulo the cpass R ruolo modulo
	 * @return the cpass R ruolo modulo
	 */
	public CpassRRuoloModulo addCpassRRuoloModulo(CpassRRuoloModulo cpassRRuoloModulo) {
		getCpassRRuoloModulos().add(cpassRRuoloModulo);
		cpassRRuoloModulo.setCpassDModulo(this);

		return cpassRRuoloModulo;
	}

	/**
	 * Removes the cpass R ruolo modulo.
	 *
	 * @param cpassRRuoloModulo the cpass R ruolo modulo
	 * @return the cpass R ruolo modulo
	 */
	public CpassRRuoloModulo removeCpassRRuoloModulo(CpassRRuoloModulo cpassRRuoloModulo) {
		getCpassRRuoloModulos().remove(cpassRRuoloModulo);
		cpassRRuoloModulo.setCpassDModulo(null);

		return cpassRRuoloModulo;
	}

	@Override
	public Integer getId() {
		return moduloId;
	}

	@Override
	public void setId(Integer id) {
		moduloId = id;
	}

}
