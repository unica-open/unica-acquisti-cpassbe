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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreIndirizzoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreIndirizzoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;

public class PostRicercaSettoreIndirizzoService extends BaseService<PostRicercaSettoreIndirizzoRequest, PostRicercaSettoreIndirizzoResponse> {


	protected final SettoreDad settoreDad;
	private Settore settore;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaSettoreIndirizzoService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper);
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		settore = request.getSettore();
		checkNotNull(settore, "settore", Boolean.TRUE);
		checkNotNull(settore.getCodice(),"codice", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final List<SettoreIndirizzo> resp = settoreDad.postRicercaSettoreIndirizzi(request.getSettore());
		response.setSettoreIndirizzi(resp);
	}
}
