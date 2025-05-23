/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;


import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutGestionePermessiAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutGestionePermessiAttiviResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;

public class PutGestionePermessiAttiviService extends BaseBoService<PutGestionePermessiAttiviRequest, PutGestionePermessiAttiviResponse > {
	private final UtenteDad utenteDad;


	public PutGestionePermessiAttiviService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad, ElaborazioneTipoDad elaborazioneTipoDad, UtenteDad utenteDad) {
		super(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void execute() {

		final List<RuoloPermesso> ruoliPermessoSelezionati = request.getRuoloPermessoAttivi();
		if(!ruoliPermessoSelezionati.isEmpty()) {
			for (final RuoloPermesso rp : ruoliPermessoSelezionati) {
				utenteDad.deleteRuoloPermessoById(rp.getId());
			}
		}

	}

}
