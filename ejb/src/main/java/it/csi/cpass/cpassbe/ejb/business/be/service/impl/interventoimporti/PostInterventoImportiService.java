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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.interventoimporti;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.interventoimporti.PostInterventoImportiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.interventoimporti.PostInterventoImportiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Saves an InterventoImporti
 */
public class PostInterventoImportiService extends BaseInterventoImportiService<PostInterventoImportiRequest, PostInterventoImportiResponse> {

	private InterventoImporti interventoImporti;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importi DAD
	 */
	public PostInterventoImportiService(ConfigurationHelper configurationHelper, InterventoImportiDad interventoImportiDad) {
		super(configurationHelper, interventoImportiDad);
	}

	@Override
	protected void checkServiceParams() {
		interventoImporti = request.getInterventoImporti();
		checkNotNull( interventoImporti.getImportoAnnoPrimo(),"importo primo anno");
		checkNotNull( interventoImporti.getImportoAnniSuccessivi(),"importo anni successivi");
		checkNotNull( interventoImporti.getImportoAnnoSecondo(),"importo secondo anno");
		checkNotNull( interventoImporti.getImportoAnnoTerzo(),"importo terzo anno");
		checkModel(interventoImporti.getRisorsa(),"risorsa");
		checkNotNull( interventoImporti.getIntervento(),"intervento");
	}

	@Override
	protected void execute() {
		interventoImporti = interventoImportiDad.saveInterventoImporti(request.getInterventoImporti());
		response.setInterventoImporti(interventoImporti);
	}

}
