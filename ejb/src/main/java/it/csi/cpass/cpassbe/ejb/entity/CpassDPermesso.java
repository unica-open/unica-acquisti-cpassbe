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
 * The persistent class for the cpass_d_permesso database table.
 *
 */
@Entity
@Table(name="cpass_d_permesso")
@NamedQuery(name="CpassDPermesso.findAll", query="SELECT c FROM CpassDPermesso c")
public class CpassDPermesso implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3180578833623807863L;

	/** The permesso id. */
	@Id
	@SequenceGenerator(name="CPASS_D_PERMESSO_PERMESSOID_GENERATOR", sequenceName="CPASS_D_PERMESSO_PERMESSO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_PERMESSO_PERMESSOID_GENERATOR")
	@Column(name="permesso_id", unique=true, nullable=false)
	private Integer permessoId;

	/** The permesso codice. */
	@Column(name="permesso_codice")
	private String permessoCodice;

	/** The permesso descrizione. */
	@Column(name="permesso_descrizione")
	private String permessoDescrizione;

	/** The permesso tipo. */
	@Column(name="permesso_tipo")
	private String permessoTipo;


	/** The permesso tipo. */
	//@Column(name="permesso_trasversale")
	//private Boolean permessoTrasversale;

	/** The titolo box. */
	@Column(name="permesso_titolo_box")
	private String permessoTitoloBox;

	/** The voce menu. */
	@Column(name="permesso_voce_menu")
	private Boolean permessoVoceMenu;


	/** The cpass R ruolo permessos. */
	//bi-directional many-to-one association to CpassRRuoloPermesso
	@OneToMany(mappedBy="cpassDPermesso")
	private List<CpassRRuoloPermesso> cpassRRuoloPermessos;

	/**
	 * Gets the permesso id.
	 *
	 * @return the permesso id
	 */
	public Integer getPermessoId() {
		return this.permessoId;
	}

	/**
	 * Sets the permesso id.
	 *
	 * @param permessoId the new permesso id
	 */
	public void setPermessoId(Integer permessoId) {
		this.permessoId = permessoId;
	}

	/**
	 * Gets the cpass R ruolo permessos.
	 *
	 * @return the cpass R ruolo permessos
	 */
	public List<CpassRRuoloPermesso> getCpassRRuoloPermessos() {
		return this.cpassRRuoloPermessos;
	}

	/**
	 * Sets the cpass R ruolo permessos.
	 *
	 * @param cpassRRuoloPermessos the new cpass R ruolo permessos
	 */
	public void setCpassRRuoloPermessos(List<CpassRRuoloPermesso> cpassRRuoloPermessos) {
		this.cpassRRuoloPermessos = cpassRRuoloPermessos;
	}

	/**
	 * Adds the cpass R ruolo permesso.
	 *
	 * @param cpassRRuoloPermesso the cpass R ruolo permesso
	 * @return the cpass R ruolo permesso
	 */
	public CpassRRuoloPermesso addCpassRRuoloPermesso(CpassRRuoloPermesso cpassRRuoloPermesso) {
		getCpassRRuoloPermessos().add(cpassRRuoloPermesso);
		cpassRRuoloPermesso.setCpassDPermesso(this);

		return cpassRRuoloPermesso;
	}

	/**
	 * Removes the cpass R ruolo permesso.
	 *
	 * @param cpassRRuoloPermesso the cpass R ruolo permesso
	 * @return the cpass R ruolo permesso
	 */
	public CpassRRuoloPermesso removeCpassRRuoloPermesso(CpassRRuoloPermesso cpassRRuoloPermesso) {
		getCpassRRuoloPermessos().remove(cpassRRuoloPermesso);
		cpassRRuoloPermesso.setCpassDPermesso(null);

		return cpassRRuoloPermesso;
	}

	/**
	 * Gets the permesso codice.
	 *
	 * @return the permesso codice
	 */
	public String getPermessoCodice() {
		return permessoCodice;
	}

	/**
	 * Sets the permesso codice.
	 *
	 * @param permessoCodice the new permesso codice
	 */
	public void setPermessoCodice(String permessoCodice) {
		this.permessoCodice = permessoCodice;
	}

	/**
	 * Gets the permesso descrizione.
	 *
	 * @return the permesso descrizione
	 */
	public String getPermessoDescrizione() {
		return permessoDescrizione;
	}

	/**
	 * Sets the permesso descrizione.
	 *
	 * @param permessoDescrizione the new permesso descrizione
	 */
	public void setPermessoDescrizione(String permessoDescrizione) {
		this.permessoDescrizione = permessoDescrizione;
	}

	/**
	 * Gets the permesso tipo.
	 *
	 * @return the permesso tipo
	 */
	public String getPermessoTipo() {
		return permessoTipo;
	}

	/**
	 * Sets the permesso tipo.
	 *
	 * @param permessoTipo the new permesso tipo
	 */
	public void setPermessoTipo(String permessoTipo) {
		this.permessoTipo = permessoTipo;
	}

	/**
	 * Gets the permesso titolo box.
	 *
	 * @return the permesso titolo box
	 */
	public String getPermessoTitoloBox() {
		return permessoTitoloBox;
	}

	/**
	 * Sets the permesso titolo box.
	 *
	 * @param permessoTitoloBox the new permesso titolo box
	 */
	public void setPermessoTitoloBox(String permessoTitoloBox) {
		this.permessoTitoloBox = permessoTitoloBox;
	}

	/**
	 * Gets the permesso voce menu.
	 *
	 * @return the permesso voce menu
	 */
	public Boolean getPermessoVoceMenu() {
		return permessoVoceMenu;
	}

	/**
	 * Sets the permesso voce menu.
	 *
	 * @param permessoVoceMenu the new permesso voce menu
	 */
	public void setPermessoVoceMenu(Boolean permessoVoceMenu) {
		this.permessoVoceMenu = permessoVoceMenu;
	}

	@Override
	public Integer getId() {
		return permessoId;
	}

	@Override
	public void setId(Integer id) {
		permessoId = id;
	}

}
