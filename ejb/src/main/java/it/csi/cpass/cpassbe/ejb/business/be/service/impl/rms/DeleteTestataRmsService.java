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

import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.DeleteTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.DeleteTestataRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class DeleteTestataRmsService extends BaseService<DeleteTestataRmsRequest, DeleteTestataRmsResponse> {

	private final RmsDad rmsDad;
	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rmsDad DAD
	 * @param decodificaDad the decodifica DAD
	 */
	public DeleteTestataRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad,DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rmsDad = rmsDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idTestataRms= request.getIdTestataRms();
		checkNotNull(idTestataRms, "idTestataRms", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final UUID idTestataRms= request.getIdTestataRms();
		final TestataRms testata = rmsDad.findOneTestata(idTestataRms);
		if(testata.getStato().getCodice().equals(ConstantsCPassStato.StatoRmsEnum.BOZZA.getCostante())) {
			rmsDad.deleteTestataRms(idTestataRms);
		}else if(testata.getStato().getCodice().equals(ConstantsCPassStato.StatoRmsEnum.ANNULLATA.getCostante())) {
			log.error("execute", "RMS gia' annullato");
		}else {
			//Regole per lo Scenario Alternativo 1 (Richiesta CONFERMATA o AUTORIZZATA)
			boolean cancellabile = true;
			for(final RigaRms riga : testata.getRigaRms()) {
				if(!riga.getStato().getCodice().equals(ConstantsCPassStato.StatoRigaRmsEnum.NEW.getCostante()) &&
						!riga.getStato().getCodice().equals(ConstantsCPassStato.StatoRigaRmsEnum.RIF.getCostante())  &&
						!riga.getStato().getCodice().equals(ConstantsCPassStato.StatoRigaRmsEnum.ADE.getCostante())){
					cancellabile = false;
				}
			}
			checkBusinessCondition(cancellabile, MsgCpassRms.RMSRMSE0021.getError());
			cambioStatoAnnullato(testata);
		}
	}

	/*private void cambioStatoAnnullato(TestataRms testata) {
		Stato statoAnnullatoTestata = decodificaDad.getStato(ConstantsCPassStato.StatoRmsEnum.ANNULLATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante()).get();
		Stato statoAnnullatoRiga = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.ADE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()).get();
		for(RigaRms riga : testata.getRigaRms()) {
			riga.setStato(statoAnnullatoRiga);
			rmsDad.updateRigaRms(riga);
		}
		rmsDad.rigaflush();
		testata.setStato(statoAnnullatoTestata );
		rmsDad.updateTestataRms(testata);
	}*/
	private void cambioStatoAnnullato(TestataRms testata) {
		final Optional<Stato> optionalStatoAnnullatoTestata = decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRmsEnum.ANNULLATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante());
		final Optional<Stato> optionalStatoAnnullatoRiga = decodificaDad.getStatoOpt(ConstantsCPassStato.StatoRigaRmsEnum.ADE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());

		if (optionalStatoAnnullatoTestata.isPresent() && optionalStatoAnnullatoRiga.isPresent()) {
			final Stato statoAnnullatoTestata = optionalStatoAnnullatoTestata.get();
			final Stato statoAnnullatoRiga = optionalStatoAnnullatoRiga.get();

			for (final RigaRms riga : testata.getRigaRms()) {
				riga.setStato(statoAnnullatoRiga);
				rmsDad.updateRigaRms(riga);
			}

			rmsDad.rigaflush();

			testata.setStato(statoAnnullatoTestata);
			rmsDad.updateTestataRms(testata);
		}
	}
}

