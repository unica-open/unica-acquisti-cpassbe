/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PostRicercaRmsDaEvadereRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PostRicercaRmsDaEvadereResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class PostRicercaRmsDaEvadereService extends BaseService<PostRicercaRmsDaEvadereRequest, PostRicercaRmsDaEvadereResponse> {

	RdaDad rdaDad;
	DecodificaDad decodificaDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaRmsDaEvadereService(ConfigurationHelper configurationHelper, RdaDad rdaDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rdaDad = rdaDad;
		this.decodificaDad = decodificaDad;
	}

	/**
	 * Execution
	 */
	@Override
	protected void execute() {
		final Date dataInserimentoDawithoutTime = DateUtility.getDateWithoutTime(request.getDataInserimentoDa());
		final Date dataInserimentoAwithoutTime  = DateUtility.getDateWithoutTime(request.getDataInserimentoA());

		final Date dataAutorizzazioneDawithoutTime = DateUtility.getDateWithoutTime(request.getDataAutorizzazioneDa());
		final Date dataAutorizzazioneAwithoutTime  = DateUtility.getDateWithoutTime(request.getDataAutorizzazioneoA());

		final Sezione sezione = request.getSezione();
		final BigDecimal sogliaQuantita = BigDecimal.ZERO;

		final Stato statoIag = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.IAG.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
		final Stato statoDae = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
		final Stato statoEvp = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.EVP.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
		final Stato statoWip = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.WIP.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());


		final List<Stato> statoRiga = new ArrayList<>();
		if(ConstantsCPassStato.StatoRigaRmsEnum.IAG.getCostante().equals(request.getStatoRigaRms())) {
			statoRiga.add(statoIag);
		} else {
			if (ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante().equals(request.getStatoRigaRms())){
			} else {
				statoRiga.add(statoIag);
			}
			statoRiga.add(statoDae);
			statoRiga.add(statoEvp);
			statoRiga.add(statoWip);
		}

		final PagedList<RigaRms> rigaRmss = rdaDad.getRicercaRmsDaEvadere(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getNumeroRmsDa(),
				request.getNumeroRmsA(),
				request.getAnnoRmsDa(),
				request.getAnnoRmsA(),
				dataInserimentoDawithoutTime,
				dataInserimentoAwithoutTime,
				dataAutorizzazioneDawithoutTime,
				dataAutorizzazioneAwithoutTime,
				request.getTestataRms(),
				request.getRigaRms(),
				statoRiga,
				sogliaQuantita,
				sezione
				);

		response.setRigaRmss(rigaRmss);
	}
}
