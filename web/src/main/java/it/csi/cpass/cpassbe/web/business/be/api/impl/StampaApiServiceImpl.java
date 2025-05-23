/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.business.be.api.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.InterventoFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.StampeFacade;
import it.csi.cpass.cpassbe.web.business.be.api.StampaApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class StampaApiServiceImpl extends BaseRestServiceImpl implements StampaApi {

	@EJB private InterventoFacade interventoFacade;
	@EJB private StampeFacade stampeFacade;

	@Override
	public Response stampa(String nomeStampa, String formatFile, List<String> listaParametri,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> stampeFacade.stampa( nomeStampa,"", formatFile, listaParametri));
	}

	@Override
	public Response printWithName(String logicName, String fileName, String formatFile, List<String> param,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> stampeFacade.stampa( logicName,fileName, formatFile, param));
	}

}
