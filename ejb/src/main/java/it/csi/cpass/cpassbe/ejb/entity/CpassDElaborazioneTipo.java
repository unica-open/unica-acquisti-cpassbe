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
 * The persistent class for the cpass_d_stato database table.
 *
 */
@Entity
@Table(name="cpass_d_elaborazione_tipo")
@NamedQuery(name="CpassDElaborazioneTipo.findAll", query="SELECT c FROM CpassDElaborazioneTipo c")
public class CpassDElaborazioneTipo implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stato id. */
	@Id
	@SequenceGenerator(name="CPASS_D_ELABORAZIONE_TIPO_ELABORAZIONE_TIPOID_GENERATOR", sequenceName="CPASS_D_ELABORAZIONE_TIPO_ELABORAZIONE_TIPO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_ELABORAZIONE_TIPO_ELABORAZIONE_TIPOID_GENERATOR")
	@Column(name="elaborazione_tipo_id", unique=true, nullable=false)
	private Integer elaborazioneTipoId;

	/** The  codice. */
	@Column(name="elaborazione_tipo_codice", nullable=false, length=50)
	private String elaborazioneTipoCodice;

	/** The  descrizione. */
	@Column(name="elaborazione_tipo_descrizione", nullable=false, length=200)
	private String elaborazioneTipoDescrizione;

	/** The moduloCodice. */
	@Column(name="modulo_codice")
	private String moduloCodice;
	
	
	/** The cpass T cpassTElaboraziones. */
	//bi-directional many-to-one association to CpassDElaborazioneTipo
	@OneToMany(mappedBy="cpassDElaborazioneTipo")
	private List<CpassTElaborazione> cpassTElaboraziones;
	
	/**
	 * @return the elaborazioneTipoId
	 */
	public Integer getElaborazioneTipoId() {
		return elaborazioneTipoId;
	}

	/**
	 * @param elaborazioneTipoId the elaborazioneTipoId to set
	 */
	public void setElaborazioneTipoId(Integer elaborazioneTipoId) {
		this.elaborazioneTipoId = elaborazioneTipoId;
	}

	/**
	 * @return the elaborazioneTipoCodice
	 */
	public String getElaborazioneTipoCodice() {
		return elaborazioneTipoCodice;
	}

	/**
	 * @param elaborazioneTipoCodice the elaborazioneTipoCodice to set
	 */
	public void setElaborazioneTipoCodice(String elaborazioneTipoCodice) {
		this.elaborazioneTipoCodice = elaborazioneTipoCodice;
	}

	/**
	 * @return the elaborazioneTipoDescrizione
	 */
	public String getElaborazioneTipoDescrizione() {
		return elaborazioneTipoDescrizione;
	}

	/**
	 * @param elaborazioneTipoDescrizione the elaborazioneTipoDescrizione to set
	 */
	public void setElaborazioneTipoDescrizione(String elaborazioneTipoDescrizione) {
		this.elaborazioneTipoDescrizione = elaborazioneTipoDescrizione;
	}

	/**
	 * Gets the cpass T elaborazione.
	 *
	 * @return the cpass T elaborazione
	 */
	public List<CpassTElaborazione> getCpassTElaboraziones() {
		return this.cpassTElaboraziones;
	}

	/**
	 * Sets the cpass T interventos.
	 *
	 * @param cpassTElaboraziones the new cpass T interventos
	 */
	public void setCpassTElaboraziones(List<CpassTElaborazione> cpassTElaboraziones) {
		this.cpassTElaboraziones = cpassTElaboraziones;
	}

	/*
	public CpassTElaborazione addCpassTPbaIntervento(CpassTElaborazione cpassTElaborazione) {
		getCpassTElaboraziones().add(cpassTElaborazione);
		cpassTElaborazione.setCpassTElaborazione(this);

		return cpassTElaborazione;
	}

	public CpassTElaborazione removeCpassTElaborazione(CpassTElaborazione cpassTElaborazione) {
		getCpassTElaboraziones().remove(cpassTElaborazione);
		cpassTElaborazione.setCpassTElaborazione(null)
		return cpassTElaborazione;
	}
*/
	

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
	
	@Override
	public Integer getId() {
		return elaborazioneTipoId;
	}


	@Override
	public void setId(Integer id) {
		elaborazioneTipoId = id;
	}
}
