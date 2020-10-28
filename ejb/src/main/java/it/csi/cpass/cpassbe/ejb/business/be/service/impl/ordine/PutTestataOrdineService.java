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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Saves an TestataOrdine
 */
public class PutTestataOrdineService extends BaseTestataOrdineService<PutTestataOrdineRequest, PutTestataOrdineResponse> {

	private final DecodificaDad decodificaDad;
	private TestataOrdine testataOrdine;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PutTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DecodificaDad decodificaDad) {
		super(configurationHelper, testataOrdineDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		testataOrdine = request.getTestataOrdine();
		checkModel(request.getTestataOrdine(), "testataOrdine");
		checkNotNull(testataOrdine.getDescrizione(), "descrizione");
		checkNotNull(testataOrdine.getOptlock(), "opt lock");
		checkModel(testataOrdine.getUtenteCompilatore(), "utente");
		checkModel(testataOrdine.getUfficio(), "ufficio");
		checkModel(testataOrdine.getTipoOrdine(), "tipo ordine");
		checkModel(testataOrdine.getTipoProcedura(), "tipo procedura");
	}

	@Override
	protected void execute() {
		TestataOrdine testataOrdineAttuale = isEntityPresent(() -> testataOrdineDad.getTestataOrdine(testataOrdine.getId()), "testataOrdine");

		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStato(testataOrdine.getStato().getId()), "stato"));

		// controllo per la concorrenza
		checkOptlock(testataOrdineAttuale.getOptlock(), testataOrdine.getOptlock());
		setAuditData(testataOrdine, testataOrdineAttuale);

		testataOrdineDad.updateTestataOrdine(testataOrdine);
	}

}
