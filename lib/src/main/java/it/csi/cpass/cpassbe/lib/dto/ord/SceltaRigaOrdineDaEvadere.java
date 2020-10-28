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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Fornitore;

public class SceltaRigaOrdineDaEvadere implements Serializable {

	private static final long serialVersionUID = 2822901832886152626L;
	private Fornitore fornitore;
	private List<TestataOrdine> listTestataOrdine;
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
	 * @return the listTestataOrdine
	 */
	public List<TestataOrdine> getListTestataOrdine() {
		return listTestataOrdine;
	}
	/**
	 * @param listTestataOrdine the listTestataOrdine to set
	 */
	public void setListTestataOrdine(List<TestataOrdine> listTestataOrdine) {
		this.listTestataOrdine = listTestataOrdine;
	}
}
