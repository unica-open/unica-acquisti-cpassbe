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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Request for posting the TestataEvasione
 */
public class PutRigaEvasioneRequest implements BaseRequest {

	private final RigaEvasione rigaEvasione;
	private final Integer qtaDaEvadere;
	private final String  totaliCoerenti;

	/**
	 * Constructor
	 *
	 * @param rigaEvasione the rigaEvasione
	 */
	public PutRigaEvasioneRequest(RigaEvasione rigaEvasione, Integer qtaDaEvadere,String totaliCoerenti) {
		this.rigaEvasione   = rigaEvasione;
		this.totaliCoerenti = totaliCoerenti;
		this.qtaDaEvadere   = qtaDaEvadere;
	}

	/**
	 * @return the testataEvasione
	 */
	public RigaEvasione getRigaEvasione() {
		return rigaEvasione;
	}

	/**
	 * @return the totaliCoerenti
	 */
	public String getTotaliCoerenti() {
		return totaliCoerenti;
	}

	/**
	 * @return the qtaDaEvadere
	 */
	public Integer getQtaDaEvadere() {
		return qtaDaEvadere;
	}


}
