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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.CopiaInterventoWrapper;

/**
 * Request for posting the Intervento
 */
public class PostInterventiCopiaV2Request implements BaseRequest {

	private List<CopiaInterventoWrapper> listCopiaInterventoWrapper = new ArrayList<>();
	private final UUID idProgramma;
	private final String stato;

	/**
	 * Constructor
	 * @param intervento the intervento
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param idProgramma
	 */
	public PostInterventiCopiaV2Request(List<CopiaInterventoWrapper> listCopiaInterventoWrapper,UUID idProgramma, String stato) {
		this.listCopiaInterventoWrapper = listCopiaInterventoWrapper;
		this.idProgramma = idProgramma;
		this.stato = stato;
	}


	/**
	 * @return the idProgramma
	 */
	public UUID getIdProgramma() {
		return idProgramma;
	}


	/**
	 * @return the listCopiaInterventoWrapper
	 */
	public List<CopiaInterventoWrapper> getListCopiaInterventoWrapper() {
		return listCopiaInterventoWrapper;
	}


	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}
}
