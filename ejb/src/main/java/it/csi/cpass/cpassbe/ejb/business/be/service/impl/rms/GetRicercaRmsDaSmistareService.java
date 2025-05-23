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

import java.util.Arrays;
import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetRicercaRmsDaSmistareRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetRicercaRmsDaSmistareResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Rmss
 */
public class GetRicercaRmsDaSmistareService extends BaseRmsService<GetRicercaRmsDaSmistareRequest, GetRicercaRmsDaSmistareResponse> {

	DecodificaDad decodificaDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaRmsDaSmistareService(ConfigurationHelper configurationHelper, RmsDad rmsDad, DecodificaDad decodificaDad) {
		super(configurationHelper, rmsDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void execute() {
		final Date dataInserimentoDawithoutTime 	= DateUtility.getDateWithoutTime(request.getDataInserimentoDa());
		final Date dataInserimentoAwithoutTime  	= DateUtility.getDateWithoutTime(request.getDataInserimentoA());
		final Stato statoRmsDasmistare     		= isEntityPresent(() -> decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRmsEnum.AUTORIZZATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante()), "stato testata");


		final TestataRms testata 					= request.getTestataRms();
		testata.setStato(statoRmsDasmistare);
		testata.setRichiestaMagazzino(null);

		final Stato statoRigaRmsDasmistare 		= isEntityPresent(() -> decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRmsEnum.NEW.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()), "stato riga");
		final Stato statoRigaRmsRif 				= isEntityPresent(() -> decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRmsEnum.RIF.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()), "stato riga rif");
		final RigaRms riga       					= request.getRigaRms();
		riga.setStatiRigaRms(Arrays.asList(statoRigaRmsDasmistare, statoRigaRmsRif));
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		final PagedList<RigaRms> rigaRmss = rmsDad.getRicercaRmsDaSmistare(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getNumeroRmsDa(),
				request.getNumeroRmsA(),
				request.getAnnoRmsDa(),
				request.getAnnoRmsA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				request.getTestataRms(),
				//request.getRigaRms(),
				riga,
				ente
				);

		checkBusinessCondition(!rigaRmss.isEmpty(), MsgCpassRms.RMSRMSE0019.getError());
		response.setRigaRmss(rigaRmss);
	}
}
