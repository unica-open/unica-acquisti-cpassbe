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

import it.csi.cpass.cpassbe.ejb.business.be.facade.BatchFacade;
import it.csi.cpass.cpassbe.web.business.be.api.BatchApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for DecodificaApi
 */
@Logged
public class BatchApiServiceImpl extends BaseRestServiceImpl implements BatchApi {

	@EJB private BatchFacade batchFacade;

	@Override
	public Response getVerificaInvioContabilita(String ente,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getVerificaInvioContabilita(ente));
	}

	@Override
	public Response getAggiornamentiImpegni(String ente,Integer numelab, String dataElab, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getAggiornamentiImpegni( ente,numelab, dataElab ));
	}

	@Override
	public Response getCaricamentoAggiornamentiImpegni(String ente,Integer numelab,String dataElab, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getCaricamentoAggiornamentiImpegni( ente, numelab, dataElab ));
	}

	@Override
	public Response getControlloBatchImpegni(String ente ,Integer numelab,String dataElab, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getControlloBatchImpegni( ente, numelab, dataElab));
	}

	@Override
	public Response getAggiornamentiSubImpegni(String ente,Integer numelab,String dataElab, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getAggiornamentiSubImpegni( ente, numelab, dataElab ));
	}

	@Override
	public Response getCaricamentoAggiornamentiSubImpegni(String ente,Integer numelab,String dataElab, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getCaricamentoAggiornamentiSubImpegni( ente,numelab, dataElab ));
	}

	@Override
	public Response getRecuperoDdt(String enteCode, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getRecuperoDdt(enteCode));
	}

	@Override
	public Response getRecuperoNotificaNSOService(String ente, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getRecuperoNotificaNSO(ente));
	}

	@Override
	public Response getSmistamentoRms(String ente, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getSmistamentoRms(ente));
	}

	@Override
	public Response getStoricizzaFileDdt(String ente, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getStoricizzaFileDdt(ente));
	}

	@Override
	public Response getStoricizzaFileNso(String ente, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getStoricizzaFileNso(ente));
	}

	@Override
	public Response getAggiornaStruttura(String ente, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> batchFacade.getAggiornaStruttura(ente));
	}


}
