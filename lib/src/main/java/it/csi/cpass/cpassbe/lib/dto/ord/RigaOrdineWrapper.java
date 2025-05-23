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
import it.csi.cpass.cpassbe.lib.dto.BaseDto;

public class RigaOrdineWrapper extends BaseDto<UUID> implements Serializable {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private RigaOrdine rigaOrdine;
	private List<ApiError> listaWarning;

	/**
	 * @param rigaOrdine
	 * @param listaErrori
	 */
	public RigaOrdineWrapper(RigaOrdine rigaOrdine, List<ApiError> listaWarning) {
		super();
		this.rigaOrdine = rigaOrdine;
		this.listaWarning = listaWarning;
	}

	/**
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	/**
	 * @return the listaWarning
	 */
	public List<ApiError> getListaWarning() {
		return listaWarning;
	}

	/*
	 * public UUID getId() { return rigaOrdine!= null? rigaOrdine.getId() : null ; }
	 */
}
