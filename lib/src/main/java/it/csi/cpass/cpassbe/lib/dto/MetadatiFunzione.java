/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

/**
 * The Class Modulo.
 */
public class MetadatiFunzione extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String modulo;

	private String funzione;

	private String chiaveColonna;

	private String descrizioneColonna;

	private Integer ordinamento;

	private Boolean ascendente;
	//private String stringaSql;

	//private String jpql;

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
	 * @return the funzione
	 */
	public String getFunzione() {
		return funzione;
	}

	/**
	 * @param funzione the funzione to set
	 */
	public void setFunzione(String funzione) {
		this.funzione = funzione;
	}



	/**
	 * @return the chiaveColonna
	 */
	public String getChiaveColonna() {
		return chiaveColonna;
	}

	/**
	 * @param chiaveColonna the chiaveColonna to set
	 */
	public void setChiaveColonna(String chiaveColonna) {
		this.chiaveColonna = chiaveColonna;
	}

	/**
	 * @return the descrizioneColonna
	 */
	public String getDescrizioneColonna() {
		return descrizioneColonna;
	}

	/**
	 * @param descrizioneColonna the descrizioneColonna to set
	 */
	public void setDescrizioneColonna(String descrizioneColonna) {
		this.descrizioneColonna = descrizioneColonna;
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
	 * @return the ascendente
	 */
	public Boolean getAscendente() {
		return ascendente;
	}

	/**
	 * @param ascendente the ascendente to set
	 */
	public void setAscendente(Boolean ascendente) {
		this.ascendente = ascendente;
	}







}
