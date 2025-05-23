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

public class FiltroFornitore {
	private Fornitore fornitore;
	private String statoFornitore;

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	/**
	 * @return the statoFornitore
	 */
	public String getStatoFornitore() {
		return statoFornitore;
	}

	/**
	 * @param statoFornitore the statoFornitore to set
	 */
	public void setStatoFornitore(String statoFornitore) {
		this.statoFornitore = statoFornitore;
	}

}
