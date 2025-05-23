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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetControlloSettoreAttivoSuDestinatarioRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetControlloSettoreAttivoSuDestinatarioResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;

public class GetControlloSettoreAttivoSuDestinatarioService extends BaseService<GetControlloSettoreAttivoSuDestinatarioRequest,GetControlloSettoreAttivoSuDestinatarioResponse>{
	DestinatarioEvasioneDad destinatarioEvasioneDad;

	public GetControlloSettoreAttivoSuDestinatarioService(ConfigurationHelper configurationHelper, DestinatarioEvasioneDad destinatarioEvasioneDad) {
		super(configurationHelper);
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
	}




	@Override
	protected void checkServiceParams() {
	}




	@Override
	protected void execute() {
		//il sistema verifica se CPASS_T_SETTORE.data_fine_validita relativa a CPASS_T_ORD_DESTINATARIO_EVASIONE.settore_id è valorizzata ed è  <= alla data corrente; se il settore non è più valido, allora questo controllo va a buon fine, altrimenti esso va a buon fine con WARNING.
		final DestinatarioEvasione destEvasione = destinatarioEvasioneDad.findOne(request.getIdDestinatarioEvasione());
		final Settore settore = destEvasione.getSettore();
		final Date oggi = new Date();
		if(settore.getDataValiditaFine()== null || settore.getDataValiditaFine().compareTo(oggi) > 0 ) {
			response.setStatoAttivo("SETTORE VALIDO");
		}else {
			response.setStatoAttivo("SETTORE NON VALIDO");
		}
	}


}
