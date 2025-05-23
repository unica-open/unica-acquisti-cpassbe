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
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutSmistamentoManualeRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutSmistamentoManualeRmsResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRigaRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoStatoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an TestataRms
 */
public class PutSmistamentoManualeRmsService extends BaseRmsService<PutSmistamentoManualeRmsRequest, PutSmistamentoManualeRmsResponse> {

	TestataOrdineDad testataOrdineDad;
	MagazzinoDad magazzinoDad;
	DecodificaDad decodificaDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rms DAD
	 */
	public PutSmistamentoManualeRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad, MagazzinoDad magazzinoDad, DecodificaDad decodificaDad,TestataOrdineDad testataOrdineDad) {
		super(configurationHelper, rmsDad);
		this.magazzinoDad = magazzinoDad;
		this.decodificaDad = decodificaDad;
		this.testataOrdineDad = testataOrdineDad;
	}

	@Override
	protected void checkServiceParams() {

	}

	@Override
	protected void execute() {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();

		checkBusinessCondition(!(request.getMagazzino().getId() == null && request.getSettore().getId() == null), MsgCpassRms.RMSRMSE0028.getError());
		checkBusinessCondition(!(request.getMagazzino().getId() != null && request.getSettore().getId() != null), MsgCpassRms.RMSRMSE0028.getError());
		checkBusinessCondition(!(request.getMagazzino().getId() != null && request.getSezione().getId() != null), MsgCpassRms.RMSRMSE0028.getError());
		final Stato statoDaEvadere =  decodificaDad.getStatoOpt(StatoRigaRmsEnum.DAE.getCostante(), TipoStatoEnum.RIGA_RMS.getCostante()).orElseThrow(() -> new NotFoundException("stato")) ;

		final List<RigaRms> rigaRmss = new ArrayList<>();
		for(final RigaRms riga : request.getListaRigheRms()) {
			final RigaRms rigaDb = rmsDad.findOneRiga(riga.getId());
			if(request.getMagazzino()!= null && request.getMagazzino().getId() != null) {
				final Optional<Magazzino> maga = magazzinoDad.getMagazzinoById(request.getMagazzino().getId());
				rigaDb.setMagazzino(maga.orElseThrow(() -> new NotFoundException("magazzino")));
			}else {
				final Settore settore = request.getSettore();
				rigaDb.setSettoreAcquisto(request.getSettore());
				if(request.getSezione()!=null && request.getSezione().getId()!=null) {
					final Sezione sezione = testataOrdineDad.getSezioneById(request.getSezione().getId()).orElseThrow(() -> new NotFoundException("magazzino"));
					checkBusinessCondition(settore.getId().equals(sezione.getSettore().getId()), MsgCpassRms.RMSRMSE0027.getError());
					rigaDb.setSezione(sezione);
				}
			}
			rigaDb.setStato(statoDaEvadere);
			rmsDad.updateRigaRms(rigaDb);

			final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
			statiRigaRms.setRigaRms(riga);
			statiRigaRms.setStato(statoDaEvadere.getDescrizione());
			statiRigaRms.setDataModifica(new Date());
			statiRigaRms.setUtenteModifica(utenteConnesso.getCodiceFiscale());
			rmsDad.insertStatiRigaRms(statiRigaRms);

			rigaRmss.add(rigaDb);
		}
		response.setRigaRmss(rigaRmss);
	}

}

