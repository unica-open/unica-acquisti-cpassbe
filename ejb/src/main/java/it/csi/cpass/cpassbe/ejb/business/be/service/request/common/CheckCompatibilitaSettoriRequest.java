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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class CheckCompatibilitaSettoriRequest implements BaseRequest {

	private UUID idSettoreEmittente;
	private UUID idSettoreDetermina;
	private String codiceStrutturaProponente;


	public CheckCompatibilitaSettoriRequest(UUID idSettoreEmittente, UUID idSettoreDetermina, String codiceStrutturaProponente) {
		super();
		this.idSettoreEmittente = idSettoreEmittente;
		this.idSettoreDetermina = idSettoreDetermina;
		this.codiceStrutturaProponente = codiceStrutturaProponente;
	}


	public UUID getIdSettoreEmittente() {
		return idSettoreEmittente;
	}


	public void setIdSettoreEmittente(UUID idSettoreEmittente) {
		this.idSettoreEmittente = idSettoreEmittente;
	}


	public UUID getIdSettoreDetermina() {
		return idSettoreDetermina;
	}


	public void setIdSettoreDetermina(UUID idSettoreDetermina) {
		this.idSettoreDetermina = idSettoreDetermina;
	}


	/**
	 * @return the codiceStrutturaProponente
	 */
	public String getCodiceStrutturaProponente() {
		return codiceStrutturaProponente;
	}


	/**
	 * @param codiceStrutturaProponente the codiceStrutturaProponente to set
	 */
	public void setCodiceStrutturaProponente(String codiceStrutturaProponente) {
		this.codiceStrutturaProponente = codiceStrutturaProponente;
	}

}
