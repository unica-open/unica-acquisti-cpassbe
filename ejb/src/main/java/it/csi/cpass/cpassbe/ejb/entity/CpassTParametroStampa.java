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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_d_stato database table.
 *
 */
@Entity
@Table(name="cpass_t_parametro_stampa")
@NamedQuery(name="CpassTParametroStampa.findAll", query="SELECT c FROM CpassTParametroStampa c")
public class CpassTParametroStampa implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stato id. */
	@Id
	@SequenceGenerator(name="CPASS_T_PARAMETRO_STAMPA_STAMPA_GENERATOR", sequenceName="CPASS_T_PARAMETRO_STAMPA_STAMPA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_PARAMETRO_STAMPA_STAMPA_GENERATOR")
	@Column(name="parametro_stampa_id", unique=true, nullable=false)
	private Integer parametroStampaId;

	@Column(name="modulo")
	private String modulo;

	@Column(name="nome_stampa")
	private String nomeStampa;

	@Column(name="file_name_template")
	private String fileNameTemplate;

	@Column(name="parametro")
	private String parametro;

	@Column(name="parametro_tipo")
	private String parametroTipo;

	@Column(name="note")
	private String note;
	
	@Column(name="procedure_utilizzate")
	private String procedureUtilizzate;

	@Column(name="ordinamento")
	private Integer ordinamento;

	@Column(name="formato_stampa")
	private String formatoStampa;

	/**
	 * @return the parametroStampaId
	 */
	public Integer getParametroStampaId() {
		return parametroStampaId;
	}

	/**
	 * @param parametroStampaId the parametroStampaId to set
	 */
	public void setParametroStampaId(Integer parametroStampaId) {
		this.parametroStampaId = parametroStampaId;
	}

	/**
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * @param modulo the modulo to set
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	/**
	 * @return the nomeStampa
	 */
	public String getNomeStampa() {
		return nomeStampa;
	}

	/**
	 * @param nomeStampa the nomeStampa to set
	 */
	public void setNomeStampa(String nomeStampa) {
		this.nomeStampa = nomeStampa;
	}

	/**
	 * @return the fileNameTemplate
	 */
	public String getFileNameTemplate() {
		return fileNameTemplate;
	}

	/**
	 * @param fileNameTemplate the fileNameTemplate to set
	 */
	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}

	/**
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the parametroTipo
	 */
	public String getParametroTipo() {
		return parametroTipo;
	}

	/**
	 * @param parametroTipo the parametroTipo to set
	 */
	public void setParametroTipo(String parametroTipo) {
		this.parametroTipo = parametroTipo;
	}

	/**
	 * @return the ordinamento
	 */
	public Integer getOrdinamento() {
		return ordinamento;
	}

	/**
	 * @param ordinamento the ordinamento to set
	 */
	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
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
	 * @return the procedureUtilizzate
	 */
	public String getProcedureUtilizzate() {
		return procedureUtilizzate;
	}

	/**
	 * @param procedureUtilizzate the procedureUtilizzate to set
	 */
	public void setProcedureUtilizzate(String procedureUtilizzate) {
		this.procedureUtilizzate = procedureUtilizzate;
	}
	
	/**
	 * @return the formatoStampa
	 */
	public String getFormatoStampa() {
		return formatoStampa;
	}

	/**
	 * @param formatoStampa the formatoStampa to set
	 */
	public void setFormatoStampa(String formatoStampa) {
		this.formatoStampa = formatoStampa;
	}

	@Override
	public Integer getId() {
		return parametroStampaId;
	}

	@Override
	public void setId(Integer id) {
		parametroStampaId = id;
	}

}
