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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Saves an TestataOrdine
 */
public class PutTestataOrdineService extends BaseOrdineService<PutTestataOrdineRequest, PutTestataOrdineResponse> {

	private final DecodificaDad decodificaDad;
	private TestataOrdine testataOrdine;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PutTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DecodificaDad decodificaDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
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
		checkModel(testataOrdine.getTipoProceduraOrd(), "tipo procedura");
	}

	@Override
	protected void execute() {
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStato(testataOrdine.getStato().getId()), "stato"));
		final Optional<Settore> optionalSettore = settoreDad.findByIdValid(testataOrdine.getSettore().getId());
		checkBusinessCondition(optionalSettore.isPresent(), () -> MsgCpassOrd.ORDORDE0050.getError());
		testataOrdineDad.updateTestataOrdine(testataOrdine);
	}

}
