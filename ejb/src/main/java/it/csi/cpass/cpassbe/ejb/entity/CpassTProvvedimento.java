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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;



/**
 * The persistent class for the cpass_t_provvedimento database table.
 *
 */
@Entity
@Table(name="cpass_t_provvedimento")
@NamedQuery(name="CpassTProvvedimento.findAll", query="SELECT c FROM CpassTProvvedimento c")
public class CpassTProvvedimento implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="provvedimento_id")
	private Integer provvedimentoId;

	@Column(name="provvedimento_anno")
	private Integer provvedimentoAnno;

	@Column(name="provvedimento_note")
	private String provvedimentoNote;

	@Column(name="provvedimento_numero")
	private String provvedimentoNumero;

	@Column(name="provvedimento_oggetto")
	private String provvedimentoOggetto;

	@Column(name="provvedimento_descrizione")
	private String provvedimentoDescrizione;

	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;

	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;


	//bi-directional many-to-one association to CpassDProvvedimentoTipo
	@ManyToOne
	@JoinColumn(name="provvedimento_tipo_id")
	private CpassDProvvedimentoTipo cpassDProvvedimentoTipo;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTCdc
	@ManyToOne
	@JoinColumn(name="cdc_id")
	private CpassTCdc cpassTCdc;



	public CpassTProvvedimento() {
	}



	/**
	 * @return the provvedimentoId
	 */
	public Integer getProvvedimentoId() {
		return provvedimentoId;
	}



	/**
	 * @param provvedimentoId the provvedimentoId to set
	 */
	public void setProvvedimentoId(Integer provvedimentoId) {
		this.provvedimentoId = provvedimentoId;
	}



	/**
	 * @return the provvedimentoAnno
	 */
	public Integer getProvvedimentoAnno() {
		return provvedimentoAnno;
	}



	/**
	 * @param provvedimentoAnno the provvedimentoAnno to set
	 */
	public void setProvvedimentoAnno(Integer provvedimentoAnno) {
		this.provvedimentoAnno = provvedimentoAnno;
	}



	/**
	 * @return the provvedimentoNote
	 */
	public String getProvvedimentoNote() {
		return provvedimentoNote;
	}



	/**
	 * @param provvedimentoNote the provvedimentoNote to set
	 */
	public void setProvvedimentoNote(String provvedimentoNote) {
		this.provvedimentoNote = provvedimentoNote;
	}



	/**
	 * @return the provvedimentoNumero
	 */
	public String getProvvedimentoNumero() {
		return provvedimentoNumero;
	}



	/**
	 * @param provvedimentoNumero the provvedimentoNumero to set
	 */
	public void setProvvedimentoNumero(String provvedimentoNumero) {
		this.provvedimentoNumero = provvedimentoNumero;
	}



	/**
	 * @return the provvedimentoOggetto
	 */
	public String getProvvedimentoOggetto() {
		return provvedimentoOggetto;
	}



	/**
	 * @param provvedimentoOggetto the provvedimentoOggetto to set
	 */
	public void setProvvedimentoOggetto(String provvedimentoOggetto) {
		this.provvedimentoOggetto = provvedimentoOggetto;
	}



	/**
	 * @return the cpassDProvvedimentoTipo
	 */
	public CpassDProvvedimentoTipo getCpassDProvvedimentoTipo() {
		return cpassDProvvedimentoTipo;
	}



	/**
	 * @param cpassDProvvedimentoTipo the cpassDProvvedimentoTipo to set
	 */
	public void setCpassDProvvedimentoTipo(CpassDProvvedimentoTipo cpassDProvvedimentoTipo) {
		this.cpassDProvvedimentoTipo = cpassDProvvedimentoTipo;
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
		return provvedimentoId;
	}

	@Override
	public void setId(Integer id) {
		provvedimentoId = id;
	}



	/**
	 * @return the provvedimentoDescrizione
	 */
	public String getProvvedimentoDescrizione() {
		return provvedimentoDescrizione;
	}



	/**
	 * @param provvedimentoDescrizione the provvedimentoDescrizione to set
	 */
	public void setProvvedimentoDescrizione(String provvedimentoDescrizione) {
		this.provvedimentoDescrizione = provvedimentoDescrizione;
	}



	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}



	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}



	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}



	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}



	/**
	 * @return the dataCreazione
	 */
	public Date getDataCreazione() {
		return dataCreazione;
	}



	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}



	/**
	 * @return the dataModifica
	 */
	public Date getDataModifica() {
		return dataModifica;
	}



	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}



	/**
	 * @return the cpassTSettore
	 */
	public CpassTSettore getCpassTSettore() {
		return cpassTSettore;
	}



	/**
	 * @param cpassTSettore the cpassTSettore to set
	 */
	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}



	/**
	 * @return the cpassTCdc
	 */
	public CpassTCdc getCpassTCdc() {
		return cpassTCdc;
	}



	/**
	 * @param cpassTCdc the cpassTCdc to set
	 */
	public void setCpassTCdc(CpassTCdc cpassTCdc) {
		this.cpassTCdc = cpassTCdc;
	}


}
