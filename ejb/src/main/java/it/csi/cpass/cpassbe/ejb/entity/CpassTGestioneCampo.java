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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_ufficio database table.
 *
 */
@Entity
@Table(name="cpass_t_gestione_campo")
@NamedQuery(name="CpassTGestioneCampo.findAll", query="SELECT c FROM CpassTGestioneCampo c")
public class CpassTGestioneCampo implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_t_gestione_campo_gestione_campo_id_seq_GENERATOR", sequenceName="cpass_t_gestione_campo_gestione_campo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_gestione_campo_gestione_campo_id_seq_GENERATOR")
	@Column(name="gestione_campo_id")
	private Integer gestioneCampoId;

	@Column(name="nome_campo")
	private String nomeCampo;

	@Column(name="visibile")
	private Boolean visibile;

	@Column(name="obbligatorio_ins")
	private Boolean obbligatorioIns;

	@Column(name="obbligatorio_upd")
	private Boolean obbligatorioUpd;

	@Column(name="editabile")
	private Boolean editabile;

	@Column(name="note")
	private String note;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	/**
	 * @return the gestioneCampoId
	 */
	public Integer getGestioneCampoId() {
		return gestioneCampoId;
	}



	/**
	 * @param gestioneCampoId the gestioneCampoId to set
	 */
	public void setGestioneCampoId(Integer gestioneCampoId) {
		this.gestioneCampoId = gestioneCampoId;
	}



	/**
	 * @return the nomeCampo
	 */
	public String getNomeCampo() {
		return nomeCampo;
	}



	/**
	 * @param nomeCampo the nomeCampo to set
	 */
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}



	/**
	 * @return the visibile
	 */
	public Boolean getVisibile() {
		return visibile;
	}



	/**
	 * @param visibile the visibile to set
	 */
	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}



	/**
	 * @return the obbligatorioIns
	 */
	public Boolean getObbligatorioIns() {
		return obbligatorioIns;
	}



	/**
	 * @param obbligatorioIns the obbligatorioIns to set
	 */
	public void setObbligatorioIns(Boolean obbligatorioIns) {
		this.obbligatorioIns = obbligatorioIns;
	}



	/**
	 * @return the obbligatorioUpd
	 */
	public Boolean getObbligatorioUpd() {
		return obbligatorioUpd;
	}



	/**
	 * @param obbligatorioUpd the obbligatorioUpd to set
	 */
	public void setObbligatorioUpd(Boolean obbligatorioUpd) {
		this.obbligatorioUpd = obbligatorioUpd;
	}



	/**
	 * @return the editabile
	 */
	public Boolean getEditabile() {
		return editabile;
	}



	/**
	 * @param editabile the editabile to set
	 */
	public void setEditabile(Boolean editabile) {
		this.editabile = editabile;
	}



	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}



	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}



	/**
	 * @return the cpassTEnte
	 */
	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}



	/**
	 * @param cpassTEnte the cpassTEnte to set
	 */
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}



	@Override
	public Integer getId() {
		return gestioneCampoId;
	}



	@Override
	public void setId(Integer id) {
		gestioneCampoId = id;

	}


}
