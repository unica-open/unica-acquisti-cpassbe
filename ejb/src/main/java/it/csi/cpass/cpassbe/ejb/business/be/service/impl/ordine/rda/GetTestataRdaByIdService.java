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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetTestataRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetTestataRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetTestataRdaByIdService extends BaseRdaService<GetTestataRdaByIdRequest, GetTestataRdaByIdResponse> {

	private final RmsDad rmsDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param RdaDad    the rda DAD
	 */
	public GetTestataRdaByIdService(ConfigurationHelper configurationHelper, RdaDad rdaDad, RmsDad rmsDad) {
		super(configurationHelper, rdaDad);
		this.rmsDad = rmsDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	// TODO possibilmente da riscrivere in maniera decente
	protected void execute() {
		final TestataRda testataRda = rdaDad.getTestataRdaById(request.getId()).orElseThrow(() -> new NotFoundException("testata rda"));
		if(testataRda != null) {
			final List<TestataOrdine> listTestataOrdine = rdaDad.getTestataOrdineByTestataRdaId(request.getId());
			testataRda.setTestataOrdines(listTestataOrdine);
			final List<RigaRda> righeRda = testataRda.getRigaRda();
			if(righeRda != null && righeRda.size() > 0) {
				for(final RigaRda rigaRda : righeRda) {
					final List<RigaRms> righeRms = rmsDad.getRigaRmsByRigaRdaId(rigaRda.getId());
					rigaRda.setRigaRms(righeRms);
					for(final RigaRms rigaRms :righeRms ) {
						final UUID testataRmsId = rigaRms.getTestataRms().getId();
						final TestataRms testataRms = rmsDad.findOneTestata(testataRmsId);
						rigaRms.setTestataRms(testataRms);
						if(testataRms.getSettoreIndirizzo().getPrincipale()) {
							rigaRms.getTestataRms().setSede("PRINCIPALE");
						} else {
							rigaRms.getTestataRms().setSede(testataRms.getSettoreIndirizzo().getDescrizione());
						}
					}
				}

			}
		}
		response.setTestataRda(testataRda);
	}
}
