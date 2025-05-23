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


import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.RdaFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.web.business.be.api.RdaApi;
import it.csi.cpass.cpassbe.web.dto.RicercaRda;
import it.csi.cpass.cpassbe.web.dto.RicercaRmsDaEvadere;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for RdaApi
 */
@Logged
public class RdaApiServiceImpl extends BaseRestServiceImpl implements RdaApi {

	@EJB private RdaFacade rdaFacade;
/*
	@Override
	public Response postRicercaRmsDaEvadere(Integer page, Integer limit, String sort, String direction,
											RicercaRmsDaEvadere ricercaRmsDaEvadere,
											SecurityContext securityContext, HttpHeaders httpHeaders,
											@Context HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.postRicercaRmsDaEvadere(page, limit, sort, direction, ricercaRmsDaEvadere);
	}
	*/

	@Override
	public Response getRicercaRmsDaEvadere(Integer page, Integer limit, String sort, String direction, RicercaRmsDaEvadere ricercaRms, SecurityContext securityContext,HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.getRicercaRmsDaEvadere(page, limit, sort, direction, ricercaRms.getAnnoRmsDa(),
				ricercaRms.getNumeroRmsDa(), ricercaRms.getAnnoRmsA(), ricercaRms.getNumeroRmsA(), ricercaRms.getDataInserimentoDa(),
				ricercaRms.getDataInserimentoA(), ricercaRms.getDataAutorizzazioneDa(), ricercaRms.getDataAutorizzazioneA(), ricercaRms.getTestataRms(), ricercaRms.getRigaRms(), ricercaRms.getSezione(), ricercaRms.getStatoRigaRms()));
	}

	@Override
	public Response getTestataRdaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.getTestataRdaById(id));
	}

	@Override
	public Response getRicercaTestataRdaByAnnoENum(Integer anno, Integer numero, UUID enteId,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.getTestataRdaByAnnoENum(anno, numero, enteId));
	}

	@Override
	public Response getRicercaRda(Integer page, Integer limit, String sort, String direction, RicercaRda ricercaRda, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(
					() -> rdaFacade.getRicercaRda(page, limit, sort, direction, ricercaRda.getRdaAnnoDa(),  ricercaRda.getRdaNumeroDa(), ricercaRda.getRdaAnnoA(), ricercaRda.getRdaNumeroA(), ricercaRda.getDataInserimentoDa(), ricercaRda.getDataInserimentoA(), ricercaRda.getTestataRda(), ricercaRda.getRigaRda(), ricercaRda.getSettoreDestinatario())
				);
	}


	@Override
	public Response postTestataRda(TestataRda testataRda, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.postTestataRda(testataRda));
	}

	@Override
	public Response putTestataRdaById(UUID id, TestataRda testataRda, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.putTestataRdaById(id, testataRda));
	}

	@Override
	public Response putRigaRda(Boolean checkOdsConfermato,RigaRda rigaRda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.putRigaRda(rigaRda, checkOdsConfermato));
	}

	@Override
	public Response deleteTestataRdaById(UUID testataRdaId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.deleteTestataRdaById(testataRdaId));
	}

	@Override
	public Response deleteRigaRdaById(UUID rigaRdaId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.deleteRigaRdaById(rigaRdaId));
	}

	@Override
	public Response putCambioStatoRdaRda( UUID rdaId, String statoCodice, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> rdaFacade.putCambioStatoRdaRda(rdaId,statoCodice));
	}



}
