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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutGestionePermessiNonAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutGestionePermessiNonAttiviResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;

public class PutGestionePermessiNonAttiviService extends BaseBoService<PutGestionePermessiNonAttiviRequest, PutGestionePermessiNonAttiviResponse> {

	private final UtenteDad utenteDad;

	public PutGestionePermessiNonAttiviService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad, ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			ElaborazioneTipoDad elaborazioneTipoDad, UtenteDad utenteDad) {
		super(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void execute() {
		final List<ModuloRuoloPermesso> moduloruoloPermessoSelezionati = request.getModuloRuoloPermessoNonAttivi();
		final List<RuoloPermesso> rpWrapperList = new ArrayList<>();

		if (!moduloruoloPermessoSelezionati.isEmpty()) {
			for (final ModuloRuoloPermesso mrp : moduloruoloPermessoSelezionati) {
				final RuoloPermesso rp = convertToRuoloPermesso(mrp);
				rpWrapperList.add(rp);
			}
			for(RuoloPermesso rp: rpWrapperList) {
				rp = utenteDad.insertRRuoloPermesso(rp);
			}
		}
	}

	private RuoloPermesso convertToRuoloPermesso(ModuloRuoloPermesso mrp) {
		final RuoloPermesso rp = new RuoloPermesso();
		rp.setRuolo(mrp.getRuolo());
		rp.setPermesso(mrp.getPermesso());
		return rp;
	}
}
