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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;

/**
 * Response for reading list of DocumentoSpesa.
 */
public class PostRicercaDocumentoSpesaResponse extends BaseGetResponse<List<DocumentoSpesa>> {

	private List<DocumentoSpesa> list= new ArrayList<>();
	/**
	 * @return the DocumentoSpesa
	 */
	public List<DocumentoSpesa> getDocumentoSpesa() {
		return list;
	}

	/**
	 * @param fornitori the fornitori to set
	 */
	public void setDocumentoSpesa(List<DocumentoSpesa> lista) {
		this.list = lista != null ? lista : new ArrayList<>();
	}

	@Override
	protected List<DocumentoSpesa> getEntity() {
		return list;
	}


}
