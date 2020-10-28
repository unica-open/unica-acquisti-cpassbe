/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

/**
 * The Class ParametroStampa.
 */
public class ParametroStampa extends BaseDto<Long> implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String modulo;
	private String nomeStampa;
	private String fileNameTemplate;
	private String parametro;
	private String parametroTipo;
	private Integer ordinamento;
	private String note;
	private String procedureUtilizzate;
	private String formatoStampa;

	/** Default constructor */
	public ParametroStampa() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public ParametroStampa(Long id) {
		super(id);
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
	public String toString() {
		return new StringBuilder()
			.append("Parametro Stampa [")	
			.append(", modulo=").append(modulo)
			.append(", nomeStampa=").append(nomeStampa)
			.append(", fileNameTemplate=").append(fileNameTemplate)
			.append(", parametro=").append(parametro)
			.append(", parametroTipo=").append(parametroTipo)
			.append(", ordinamento=").append(ordinamento)
			.append(", ordinamento=").append(note)
			.append(", ordinamento=").append(procedureUtilizzate)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}
}
