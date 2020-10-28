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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.util.List;

public class SalvaEvasione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5604861742980976412L;
	private TestataEvasione testataEvasione;
	private List<RigaEvasione> listEvasione;

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	/**
	 * @return the listEvasione
	 */
	public List<RigaEvasione> getListEvasione() {
		return listEvasione;
	}

	/**
	 * @param listEvasione the listEvasione to set
	 */
	public void setListEvasione(List<RigaEvasione> listEvasione) {
		this.listEvasione = listEvasione;
	}

}
