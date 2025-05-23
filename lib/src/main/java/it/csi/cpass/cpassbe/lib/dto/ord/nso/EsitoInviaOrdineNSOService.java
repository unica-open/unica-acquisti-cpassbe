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
package it.csi.cpass.cpassbe.lib.dto.ord.nso;

import java.io.Serializable;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class EsitoInviaOrdineNSOService implements Serializable {

	private static final long serialVersionUID = 1L;
	private TestataOrdine testataOrdine;
	private List<ApiError> listApiErrors;

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	/**
	 * @return the apiErros
	 */
	public List<ApiError> getListApiErrors() {
		return listApiErrors;
	}

	/**
	 * @param apiErros the apiErros to set
	 */
	public void setListApiErrors(List<ApiError> listApiErrors) {
		this.listApiErrors = listApiErrors;
	}


}
