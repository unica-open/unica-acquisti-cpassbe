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


/**
 * The persistent class for the cpass_d_ruolo database table.
 *
 */
@Entity
@Table(name="cpass_d_ruolo")
@NamedQuery(name="CpassDRuolo.findAll", query="SELECT c FROM CpassDRuolo c")
public class CpassDRuolo implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ruolo id. */
	@Id
	@SequenceGenerator(name="CPASS_D_RUOLO_RUOLOID_GENERATOR", sequenceName="CPASS_D_RUOLO_RUOLO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_RUOLO_RUOLOID_GENERATOR")
	@Column(name="ruolo_id", unique=true, nullable=false)
	private Integer ruoloId;

	/** The ruolo ruolo_codice. */
	@Column(name="ruolo_codice", nullable=false, length=500)
	private String ruoloCodice;

	/** The ruolo ruolo_descrizione. */
	@Column(name="ruolo_descrizione", nullable=false, length=500)
	private String ruoloDescrizione;

	/** The cpass R ruolo modulos. */
	//bi-directional many-to-one association to CpassRRuoloModulo
	@OneToMany(mappedBy="cpassDRuolo")
	private List<CpassRRuoloModulo> cpassRRuoloModulos;

	/** The cpass R ruolo permessos. */
	//bi-directional many-to-one association to CpassRRuoloPermesso
	@OneToMany(mappedBy="cpassDRuolo")
	private List<CpassRRuoloPermesso> cpassRRuoloPermessos;

	/** The cpass R ruolo utente settores. */
	//bi-directional many-to-one association to CpassRRuoloUtenteSettore
	@OneToMany(mappedBy="cpassDRuolo")
	private List<CpassRRuoloUtenteSettore> cpassRRuoloUtenteSettores;

	@Column(name="selezionabile_da_procedura")
	private String selezionabileDaProcedura;
	/**
	 * Gets the ruolo id.
	 *
	 * @return the ruolo id
	 */
	public Integer getRuoloId() {
		return this.ruoloId;
	}

	/**
	 * Sets the ruolo id.
	 *
	 * @param ruoloId the new ruolo id
	 */
	public void setRuoloId(Integer ruoloId) {
		this.ruoloId = ruoloId;
	}



	/**
	 * @return the ruoloCodice
	 */
	public String getRuoloCodice() {
		return ruoloCodice;
	}

	/**
	 * @param ruoloCodice the ruoloCodice to set
	 */
	public void setRuoloCodice(String ruoloCodice) {
		this.ruoloCodice = ruoloCodice;
	}

	/**
	 * @return the ruoloDescrizione
	 */
	public String getRuoloDescrizione() {
		return ruoloDescrizione;
	}

	/**
	 * @param ruoloDescrizione the ruoloDescrizione to set
	 */
	public void setRuoloDescrizione(String ruoloDescrizione) {
		this.ruoloDescrizione = ruoloDescrizione;
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
		cpassRRuoloModulo.setCpassDRuolo(this);

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
		cpassRRuoloModulo.setCpassDRuolo(null);

		return cpassRRuoloModulo;
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
		cpassRRuoloPermesso.setCpassDRuolo(this);

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
		cpassRRuoloPermesso.setCpassDRuolo(null);

		return cpassRRuoloPermesso;
	}

	/**
	 * Gets the cpass R ruolo utente settores.
	 *
	 * @return the cpass R ruolo utente settores
	 */
	public List<CpassRRuoloUtenteSettore> getCpassRRuoloUtenteSettores() {
		return this.cpassRRuoloUtenteSettores;
	}

	/**
	 * Sets the cpass R ruolo utente settores.
	 *
	 * @param cpassRRuoloUtenteSettores the new cpass R ruolo utente settores
	 */
	public void setCpassRRuoloUtenteSettores(List<CpassRRuoloUtenteSettore> cpassRRuoloUtenteSettores) {
		this.cpassRRuoloUtenteSettores = cpassRRuoloUtenteSettores;
	}

	/**
	 * Adds the cpass R ruolo utente settore.
	 *
	 * @param cpassRRuoloUtenteSettore the cpass R ruolo utente settore
	 * @return the cpass R ruolo utente settore
	 */
	public CpassRRuoloUtenteSettore addCpassRRuoloUtenteSettore(CpassRRuoloUtenteSettore cpassRRuoloUtenteSettore) {
		getCpassRRuoloUtenteSettores().add(cpassRRuoloUtenteSettore);
		cpassRRuoloUtenteSettore.setCpassDRuolo(this);

		return cpassRRuoloUtenteSettore;
	}

	/**
	 * Removes the cpass R ruolo utente settore.
	 *
	 * @param cpassRRuoloUtenteSettore the cpass R ruolo utente settore
	 * @return the cpass R ruolo utente settore
	 */
	public CpassRRuoloUtenteSettore removeCpassRRuoloUtenteSettore(CpassRRuoloUtenteSettore cpassRRuoloUtenteSettore) {
		getCpassRRuoloUtenteSettores().remove(cpassRRuoloUtenteSettore);
		cpassRRuoloUtenteSettore.setCpassDRuolo(null);

		return cpassRRuoloUtenteSettore;
	}


	/**
	 * @return the selezionabileDaProcedura
	 */
	public String getSelezionabileDaProcedura() {
		return selezionabileDaProcedura;
	}

	/**
	 * @param selezionabileDaProcedura the selezionabileDaProcedura to set
	 */
	public void setSelezionabileDaProcedura(String selezionabileDaProcedura) {
		this.selezionabileDaProcedura = selezionabileDaProcedura;
	}

	@Override
	public Integer getId() {
		return ruoloId;
	}

	@Override
	public void setId(Integer id) {
		ruoloId = id;
	}

}
