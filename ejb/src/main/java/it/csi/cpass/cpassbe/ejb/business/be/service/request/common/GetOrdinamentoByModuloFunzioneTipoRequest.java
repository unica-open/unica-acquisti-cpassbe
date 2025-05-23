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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;

/**
 * The Class GetUfficiBySettoreRequest.
 */
public class GetOrdinamentoByModuloFunzioneTipoRequest implements BaseRequest {


	private final  String modulo;
	private final  String funzione;
	private final  String tipo;
	private final  List<MetadatiFunzione> listMetadatiFunzione;

	/**
	 * Constructor
	 *
	 * @param settoreId the settoreId
	 */
	public GetOrdinamentoByModuloFunzioneTipoRequest(String modulo, String funzione, String tipo,List<MetadatiFunzione> listMetadatiFunzione) {
		this.modulo = modulo;
		this.funzione = funzione;
		this.tipo = tipo;
		this.listMetadatiFunzione = listMetadatiFunzione;
	}

	/**
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * @return the funzione
	 */
	public String getFunzione() {
		return funzione;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the listMetadatiFunzione
	 */
	public List<MetadatiFunzione> getListMetadatiFunzione() {
		return listMetadatiFunzione;
	}

}
