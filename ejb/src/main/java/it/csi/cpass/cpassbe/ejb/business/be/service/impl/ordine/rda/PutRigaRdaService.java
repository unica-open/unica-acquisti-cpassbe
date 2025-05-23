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



import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutRigaRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutRigaRdaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;


public class PutRigaRdaService  extends BaseService<PutRigaRdaRequest,PutRigaRdaResponse>{

	private final RdaDad rdaDad;
	private RigaRda rigaRda;
	private final DecodificaDad decodificaDad;

	public PutRigaRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rdaDad = rdaDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		rigaRda = request.getRigaRda();
		checkNotNull(rigaRda, "rigaRda", true);
	}

	@Override
	protected void execute() {

		final Ods newOds = decodificaDad.getOdsById(request.getRigaRda().getOggettiSpesa().getId());
		final RigaRda currentRigaRda = rdaDad.findOneRiga(rigaRda.getId());
		final Ods oldOds = currentRigaRda.getOggettiSpesa();
		if(checkOdsCongruences(oldOds, newOds) || request.getCheckOdsConfermato()) {
			final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
			final Ente ente = settoreUtente.getEnte();
			rigaRda.setEnte(ente);
			rigaRda = rdaDad.updateRigaRda(rigaRda);
			response.setRigaRda(rigaRda);
		}else {
			final ApiError warning = new ApiError();
			warning.setCode(MsgCpassOrd.ORDORDA0148.getCode());
			response.addApiWarnings(new ApiError());
		}

	}

	private boolean checkOdsCongruences(Ods oldOds, Ods newOds) {
		if(!(newOds.getCodice().equals(oldOds.getCodice()))) {

			if(oldOds.getGenerico() == null || !oldOds.getGenerico()) {

				if(!newOds.getCpv().getId().equals(oldOds.getCpv().getId())) {
					return false;
				}
			}

		}

		return Boolean.TRUE;
	}

}
