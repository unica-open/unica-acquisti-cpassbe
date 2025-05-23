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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetRicercaRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetRicercaRdaResponse;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Rmss
 */
public class GetRicercaRdaService extends BaseRdaService<GetRicercaRdaRequest, GetRicercaRdaResponse> {


	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	RmsDad rmsDad;
	public GetRicercaRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad, RmsDad rmsDad) {
		super(configurationHelper, rdaDad);
		this.rmsDad = rmsDad;
	}

	@Override
	protected void execute() {

		final Date dataInserimentoDawithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoDa());
		final Date dataInserimentoAwithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoA());


		final long count = rdaDad.countRicercaRda(
				request.getRdaNumeroDa(),
				request.getRdaNumeroA(),
				request.getRdaAnnoDa(),
				request.getRdaAnnoA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getTestataRda(),
				request.getRigaRda(),
				request.getSettoreDestinatario());

		checkBusinessCondition(count>0, MsgCpassOrd.ORDORDE0155.getError());


		final PagedList<TestataRda> testataRdas = rdaDad.getRicercaRda(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getRdaNumeroDa(),
				request.getRdaNumeroA(),
				request.getRdaAnnoDa(),
				request.getRdaAnnoA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getTestataRda(),
				request.getRigaRda(),
				request.getSettoreDestinatario()
				);

		checkBusinessCondition(!testataRdas.isEmpty(), MsgCpassOrd.ORDORDE0156.getError());
		for(final TestataRda testataRda : testataRdas.getList()) {
			for(final RigaRda rigaRda: testataRda.getRigaRda()) {
				//RigaRms rigaRms = rigaRda.getRigaRms().get(0);
				final List<RigaRms> listrigaRms = rmsDad.getRigaRmsByRigaRdaId(rigaRda.getId());
				rigaRda.setRigaRms(listrigaRms);
				for(final RigaRms rigaRms :listrigaRms) {
					final Optional<TestataRms> testataRmsOpt = rmsDad.getTestataRmsById(rigaRms.getTestataRms().getId());
					if(testataRmsOpt.isPresent()) {
						final TestataRms testataRms = testataRmsOpt.get();
						if(testataRms .getSettoreIndirizzo().getPrincipale()) {
							final String destinatario = testataRms.getSettoreDestinatario().getCodice() + " - " +testataRms.getSettoreDestinatario().getDescrizione();
							rigaRms.setDestinatario(destinatario);
						} else {
							final String destinatario = testataRms.getSettoreDestinatario().getCodice() + " - " + testataRms.getSettoreIndirizzo().getDescrizione();
							rigaRms.setDestinatario(destinatario);
						}
					}
				}
			}
		}
		response.setTestataRdas(testataRdas);
	}
}
