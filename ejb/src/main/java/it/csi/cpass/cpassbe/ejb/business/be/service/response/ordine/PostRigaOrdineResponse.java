/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdineWrapper;

public class PostRigaOrdineResponse extends BasePostResponse<UUID, RigaOrdineWrapper> {

	private RigaOrdine rigaOrdine;
	/**
	 * @return the testataOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

	@Override
	protected String getBaseUri() {
		return "rigaOrdine";
	}

	@Override
	protected RigaOrdineWrapper getEntity() {
		return new RigaOrdineWrapper(rigaOrdine,apiWarnings);
	}

	/*
	public static class PostRigaOrdineWrapper extends BaseDto<UUID>{
		final RigaOrdine rigaOrdine;
		final List<ApiError> listaErrori;

		public PostRigaOrdineWrapper(RigaOrdine rigaOrdine, List<ApiError> listaErrori) {
			super();
			this.rigaOrdine = rigaOrdine;
			this.listaErrori = listaErrori;
		}
		public RigaOrdine getRigaOrdine() {
			return rigaOrdine;
		}
		public List<ApiError> getListaErrori() {
			return listaErrori;
		}

		public UUID getId() {
			return rigaOrdine!= null? rigaOrdine.getId() : null ;
		}
	}
	 */
}
