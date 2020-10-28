/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;

public class PutDestinatarioEvasioneService extends BaseService<PutDestinatarioEvasioneRequest, PutDestinatarioEvasioneResponse> {

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final SettoreDad settoreDad;
	private DestinatarioEvasione destinatarioEvasione;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad    the testataEvasione DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PutDestinatarioEvasioneService(ConfigurationHelper configurationHelper, DestinatarioEvasioneDad destinatarioEvasioneDad, SettoreDad settoreDad) {
		super(configurationHelper);
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		destinatarioEvasione = request.getDestinatarioEvasione();
		checkNotNull(destinatarioEvasione, "destinatarioEvasione", true);
	}

	@Override
	protected void execute() {
		destinatarioEvasione = destinatarioEvasioneDad.updateDestinatarioEvasione(destinatarioEvasione);
		response.setDestinatario(destinatarioEvasione);
	}
	
}
