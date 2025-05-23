/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.business.be.api.impl;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.ScaricoMepaFacade;
import it.csi.cpass.cpassbe.lib.dto.custom.PostOrdineMepaBodyWrapper;
import it.csi.cpass.cpassbe.web.business.be.api.ScaricoMepaApi;
import it.csi.cpass.cpassbe.web.dto.WebScaricoMepaFileHolder;

public class ScaricoMepaApiServiceImpl extends BaseRestServiceImpl implements ScaricoMepaApi {

	@EJB private ScaricoMepaFacade scaricoMepaFacade;

	@Override
	public Response getOrdiniMepaDaCaricare(Integer page, Integer limit, String sort, String direction,
											SecurityContext securityContext, HttpHeaders httpHeaders,
											@Context HttpServletRequest httpRequest) {
		return invoke(() -> scaricoMepaFacade.getOrdiniMepaDaCaricare(page, limit, sort, direction));
	}

	@Override
	public Response getTestataMepaById(Integer idTestataMepa, SecurityContext securityContext,
									   HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> scaricoMepaFacade.getTestataMepaById(idTestataMepa));
	}

	@Override
	public Response postUploadScaricoMepaXml(WebScaricoMepaFileHolder fileHolder, SecurityContext securityContext,
											 HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {

		return invoke(()->scaricoMepaFacade.postUploadScaricoMepaXml(fileHolder.toFileHolder()));
	}

	@Override
	public Response getVerificaFornitore(String sellersupplierpartyEndpointId, SecurityContext securityContext,
										 HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> scaricoMepaFacade.getVerificaFornitore(sellersupplierpartyEndpointId));
	}

	@Override
	public Response postOrdineMepa(PostOrdineMepaBodyWrapper postOrdineMepaBodyWrapper,
								   SecurityContext securityContext,
								   HttpHeaders httpHeaders,
								   @Context HttpServletRequest httpRequest) {
		return invoke(() -> scaricoMepaFacade.postOrdineMepa(postOrdineMepaBodyWrapper));
	}

	/**
	 * Rimuove un ordine MEPA con tutti i corrispondenti record dalle tabelle:
	 * cpassTScaricoMepaXml, cpassTScaricoMepaSconti, cpassTScaricoMepaRiga e cpassTScaricoMepaTestata
	 * @param idTestataMepa
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return id della testata cancellata
	 */
	@Override
	public Response deleteOrdineMepa(Integer idTestataMepa,
									 SecurityContext securityContext,
									 HttpHeaders httpHeaders,
									 @Context HttpServletRequest httpRequest) {
		return invoke(() -> scaricoMepaFacade.deleteOrdineMepa(idTestataMepa));
	}

}
