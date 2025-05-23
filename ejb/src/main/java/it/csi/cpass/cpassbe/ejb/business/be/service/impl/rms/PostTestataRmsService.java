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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostTestataRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Saves an TestataRms
 */
public class PostTestataRmsService extends BaseRmsService<PostTestataRmsRequest, PostTestataRmsResponse> {

	private TestataRms testataRms;
	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rms DAD
	 */
	public PostTestataRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad, DecodificaDad decodificaDad) {
		super(configurationHelper, rmsDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		testataRms = request.getTestataRms();
		checkNotNull(testataRms, "testataRms", Boolean.TRUE);
		checkNotNull(testataRms.getSettoreEmittente(), "settoreEmittente", Boolean.TRUE);
		checkNotNull(testataRms.getSettoreDestinatario(), "settoreDestinatario", Boolean.TRUE);
		checkNotNull(testataRms.getEnte(), "ente", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final List<ApiError> apiErrors = new ArrayList<>();

		// Impostazione dello stato a bozza
		testataRms.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRmsEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante()), "stato"));

		// controllo che il settore sia valido (non annullato)
		final Date dataCancellazioneSettore = testataRms.getSettoreDestinatario().getDataCancellazione();
		final Date today = new Date();
		if (dataCancellazioneSettore != null && dataCancellazioneSettore.before(today)) {
			apiErrors.add(MsgCpassRms.RMSRMSE0003.getError());
		}
		response.addApiErrors(apiErrors);

		if (apiErrors.isEmpty()) {
			testataRms = rmsDad.saveTestataRms(testataRms);
		}
		response.setTestataRms(testataRms);
	}

}
