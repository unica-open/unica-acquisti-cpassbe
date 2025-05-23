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

import it.csi.cpass.cpassbe.ejb.business.be.facade.ImpegnoFacade;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.web.business.be.api.ImpegnoApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for ImpegnoApi
 */
@Logged
public class ImpegnoApiServiceImpl extends BaseRestServiceImpl implements ImpegnoApi {

	@EJB private ImpegnoFacade impegnoFacade;

	@Override
	public Response postRicercaImpegnoByChiaveLogica(Impegno impegno, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> impegnoFacade.getImpegnoByChiaveLogica(impegno));
	}

	@Override
	public Response postRicercaSubimpegnoByChiaveLogica(Subimpegno subimpegno, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> impegnoFacade.getSubimpegnoByChiaveLogica(subimpegno));
	}


}
