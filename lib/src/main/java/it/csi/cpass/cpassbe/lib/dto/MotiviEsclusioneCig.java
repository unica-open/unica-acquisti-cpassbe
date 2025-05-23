/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;


public class MotiviEsclusioneCig extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceNso;

	private String codiceSiope;

	private String descrizione;

	private List<TestataOrdine> testataOrdines;

	public MotiviEsclusioneCig() {
	}

	/**
	 * @return the codiceNso
	 */
	public String getCodiceNso() {
		return codiceNso;
	}

	/**
	 * @param codiceNso the codiceNso to set
	 */
	public void setCodiceNso(String codiceNso) {
		this.codiceNso = codiceNso;
	}

	/**
	 * @return the codiceSiope
	 */
	public String getCodiceSiope() {
		return codiceSiope;
	}

	/**
	 * @param codiceSiope the codiceSiope to set
	 */
	public void setCodiceSiope(String codiceSiope) {
		this.codiceSiope = codiceSiope;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the testataOrdines
	 */
	public List<TestataOrdine> getTestataOrdines() {
		return testataOrdines;
	}

	/**
	 * @param testataOrdines the testataOrdines to set
	 */
	public void setTestataOrdines(List<TestataOrdine> testataOrdines) {
		this.testataOrdines = testataOrdines;
	}



}
