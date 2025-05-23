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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest implements BaseRequest {

	private final String  indiceclassificazione;

	/**
	 * Constructor
	 * @param indiceclassificazione the indiceclassificazione
	 */
	public GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest(String indiceclassificazione) {
		this.indiceclassificazione = indiceclassificazione;
	}

	/**
	 * @return the indiceclassificazione
	 */
	public String getIndiceclassificazione() {
		return indiceclassificazione;
	}


}
