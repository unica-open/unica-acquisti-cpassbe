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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;

/**
 * The type Testata Ordine Wrapper
 */
public class TestataOrdineWrapper extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private TestataOrdine testataOrdine;
	private List<ApiError> listaWarning;

	/**
	 * @param testataOrdine
	 * @param listaWarning
	 */
	public TestataOrdineWrapper(TestataOrdine testataOrdine, List<ApiError> listaWarning) {
		super();
		this.testataOrdine = testataOrdine;
		this.listaWarning = listaWarning;
	}

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	/**
	 * @return the listaWarning
	 */
	public List<ApiError> getListaWarning() {
		return listaWarning;
	}
}
