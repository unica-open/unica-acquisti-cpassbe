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

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.ConsultazioniFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;
import it.csi.cpass.cpassbe.web.business.be.api.ConsultazioniApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for CommonApiServiceImpl
 */
@Logged
public class ConsultazioniApiServiceImpl extends BaseRestServiceImpl implements ConsultazioniApi {

	@EJB
	private ConsultazioniFacade consultazioniFacade;

	@Override

	public Response postRicercaConsultazioniXImpegno(RicercaXConsultazioni ricercaXConsultazioni,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
	//public Response postRicercaConsultazioniXImpegno(Integer page, Integer limit,RicercaXConsultazioni ricercaXConsultazioni,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		//return invoke(() -> consultazioniFacade.postRicercaConsultazioniXImpegno( page,  limit,ricercaXConsultazioni));
		return invoke(() -> consultazioniFacade.postRicercaConsultazioniXImpegno(ricercaXConsultazioni));
	}

	@Override
	public Response postRicercaConsultazioniXOrdine(RicercaXConsultazioni ricercaXConsultazioni,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		//public Response postRicercaConsultazioniXOrdine(Integer page, Integer limit,RicercaXConsultazioni ricercaXConsultazioni,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		//return invoke(() -> consultazioniFacade.postRicercaConsultazioniXOrdine(page,  limit,ricercaXConsultazioni));
		return invoke(() -> consultazioniFacade.postRicercaConsultazioniXOrdine(ricercaXConsultazioni));
	}

	@Override
	public Response postRicercaConsultazioniXRiepilogo(RicercaXConsultazioni ricercaXConsultazioni,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> consultazioniFacade.postRicercaConsultazioniXRiepilogo(ricercaXConsultazioni));
	}



}
