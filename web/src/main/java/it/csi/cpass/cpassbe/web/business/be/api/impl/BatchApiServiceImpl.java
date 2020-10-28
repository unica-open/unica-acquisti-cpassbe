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

import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.csi.cpass.cpassbe.ejb.business.be.facade.BatchFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.CommonFacade;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.web.business.be.api.BatchApi;
import it.csi.cpass.cpassbe.web.business.be.api.CommonApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for DecodificaApi
 */
@Logged
public class BatchApiServiceImpl extends BaseRestServiceImpl implements BatchApi {

	@EJB private BatchFacade batchFacade;

	@Override
	public Response getVerificaInvioContabilita(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getVerificaInvioContabilita());
	}


	
	

	
}
