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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.math.BigDecimal;
import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutRigaRmsService  extends BaseService<PutRigaRmsRequest,PutRigaRmsResponse>{

	private final RmsDad rmsDad;
	private final DecodificaDad decodificaDad;
	private RigaRms rigaRms;

	public PutRigaRmsService(ConfigurationHelper configurationHelper,RmsDad rmsDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rmsDad = rmsDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		rigaRms = request.getRigaRms();
		checkNotNull(rigaRms, "rigaRms", true);
	}

	@Override
	protected void execute() {

		boolean hasErrors = false;
		final Date now = new Date();
		CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		settoreUtente.getEnte();


		//controllo che esista il cpv e non sia scaduto
		final Cpv cpv = decodificaDad.getCpvByCodice(rigaRms.getOggettiSpesa().getCpv().getCodice());
		if(cpv == null) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0005.getError());
			return;
		}

		//controllo che esista l'ods e non sia scaduto
		final Ods ods = decodificaDad.getOdsByCodice(rigaRms.getOggettiSpesa().getCodice());
		if(ods == null || (ods.getDataValiditaFine() != null && ods.getDataValiditaFine().before(now))) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0006.getError());
			return;
		}

		// controllo congruenza tra i due
		if(!ods.getCpv().getCodice().equals(cpv.getCodice())) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0007.getError());
		}

		//confronto tra quantita indicata e quantita massima richiedibile
		//CPASS-692 - QuantitaMaxRichiedibile campo eliminato su riga rms
		/*if(ods.getQuantitaMaxRichiedibile() != null) {

			if(rigaRms.getQuantita().compareTo(ods.getQuantitaMaxRichiedibile()) > 0) {
				hasErrors = true;
				response.addApiError(MsgCpassRms.RMSRMSE0009.getError());
			}

		}*/

		//controllo se il tipo dell'oggetto di spesa è generico
		if(ods.getGenerico() != null && ods.getGenerico() && (rigaRms.getNote() == null || rigaRms.getNote().equals(""))){
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0022.getError());
		}

		//controllo quantita
		if(rigaRms.getQuantita().compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0008.getError());
		}


		if(!hasErrors) {
			if(rigaRms.getMagazzino() == null || rigaRms.getMagazzino().getId() == null) {
				rigaRms.setMagazzino(null);
			}
			if(rigaRms.getSettoreAcquisto() == null || rigaRms.getSettoreAcquisto() .getId() == null) {
				rigaRms.setSettoreAcquisto(null);
			}
			rigaRms = rmsDad.updateRigaRms(rigaRms);



			response.setRigaRms(rigaRms);
		}
	}
}
