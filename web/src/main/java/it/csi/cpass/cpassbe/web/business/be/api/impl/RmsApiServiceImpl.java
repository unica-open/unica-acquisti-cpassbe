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
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.RmsFacade;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.web.business.be.api.RmsApi;
import it.csi.cpass.cpassbe.web.dto.RicercaRms;
import it.csi.cpass.cpassbe.web.dto.RicercaRmsDaSmistare;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for RmsApi
 */
@Logged
public class RmsApiServiceImpl extends BaseRestServiceImpl implements RmsApi {

	@EJB private RmsFacade rmsFacade;

	@Override
	public Response getTestataRmsById(UUID testataRmsId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.getTestataRmsById(testataRmsId));
	}

	@Override
	public Response getRicercaRmsByAnnoENum(Integer anno, Integer numero, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.getTestataRmsByAnnoENum(anno, numero));
	}


	@Override
	public Response postTestataRms(TestataRms testataRms, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.postTestataRms(testataRms));
	}

	@Override
	public Response putTestataRmsById(UUID id, TestataRms testataRms, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.putTestataRmsById(id, testataRms));
	}

	@Override
	public Response postRigaRms(RigaRms rigaRms, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.postRigaRms(rigaRms));
	}

	@Override
	public Response deleteTestataRmsById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.deleteTestataRmsById(id));
	}

	@Override
	public Response getRicercaRms(Integer page, Integer limit, String sort, String direction, RicercaRms ricercaRms, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.getRicercaRms(page, limit, sort, direction, ricercaRms.getAnnoRmsDa(),
				ricercaRms.getNumeroRmsDa(), ricercaRms.getAnnoRmsA(), ricercaRms.getNumeroRmsA(), ricercaRms.getDataInserimentoDa(),
				ricercaRms.getDataInserimentoA(), ricercaRms.getTestataRms(), ricercaRms.getRigaRms(), ricercaRms.getSettoreEmittente(), ricercaRms.getSettore(), ricercaRms.getSettoreIndirizzo()));
	}

	@Override
	public Response putRigaRms(RigaRms rigaRms, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.putRigaRms(rigaRms));
	}

	@Override
	public Response putCambioStatoRms(UUID id, String statoCode, TestataRms testataRms,  SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.putCambioStatoRms(id, statoCode, testataRms));
	}

	@Override
	public Response deleteRigaRms(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return invoke(() -> rmsFacade.deleteRigaRms(id));
	}

	@Override
	public Response putCambioStatoRigaRms(String statoCode, List<RigaRms> rigaRmsList, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.putCambioStatoRigaRms(statoCode, rigaRmsList));
	}

	@Override
	public Response getRicercaRmsDaSmistare(Integer page, Integer limit, String sort, String direction, RicercaRmsDaSmistare ricercaRms, SecurityContext securityContext,HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rmsFacade.getRicercaRmsDaSmistare(page, limit, sort, direction, ricercaRms.getAnnoRmsDa(),
				ricercaRms.getNumeroRmsDa(), ricercaRms.getAnnoRmsA(), ricercaRms.getNumeroRmsA(), ricercaRms.getDataInserimentoDa(),
				ricercaRms.getDataInserimentoA(), ricercaRms.getTestataRms(), ricercaRms.getRigaRms()));
	}

}
