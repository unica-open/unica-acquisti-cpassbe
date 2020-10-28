/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.ModalitaAffidamento;

/**
 * The Class GetModalitaAffidamentoResponse.
 */
public class GetModalitaAffidamentoResponse extends BaseGetResponse<List<ModalitaAffidamento>> {

	/** The lingue. */
	private List<ModalitaAffidamento> modalitaAffidamentos = new ArrayList<>();

	/**
	 * @return the modalitaAffidamentos
	 */
	public List<ModalitaAffidamento> getModalitaAffidamentos() {
		return modalitaAffidamentos;
	}

	/**
	 * @param modalitaAffidamentos the modalitaAffidamentos to set
	 */
	public void setModalitaAffidamentos(List<ModalitaAffidamento> modalitaAffidamentos) {
		this.modalitaAffidamentos = modalitaAffidamentos;
	}

	@Override
	protected List<ModalitaAffidamento> getEntity() {
		return modalitaAffidamentos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetModalitaAffidamentoResponse [modalitaAffidamentos=").append(modalitaAffidamentos).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
