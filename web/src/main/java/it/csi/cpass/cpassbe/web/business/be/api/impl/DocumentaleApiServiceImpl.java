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

import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.DocumentaleFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.web.business.be.api.DocumentaleApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for ProtocolloApiService
 */
@Logged
public class DocumentaleApiServiceImpl extends BaseRestServiceImpl implements DocumentaleApi {

	@EJB
	private DocumentaleFacade documentaleFacade;


	@Override
	public Response getProtocolloOrigineByAnnoNum(Integer anno, String numero, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> documentaleFacade.getProtocolloOrigineByAnnoNum(anno, numero));
	}


	@Override
	public Response getArchiviaOrdine(UUID testataOrdineId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> documentaleFacade.getArchiviaOrdine(testataOrdineId, Boolean.TRUE));
	}


	@Override
	public Response getVerificaArchiviazioneOrdine(UUID testataOrdineId, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> documentaleFacade.getVerificaArchiviazioneOrdine(testataOrdineId));
	}


	@Override
	public Response getProtocollaOrdine(UUID testataOrdineId, TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> documentaleFacade.getProtocollaOrdine(testataOrdine, testataOrdine.getProtocolloOrdines().get(0)));
	}


	@Override
	public Response getStrutturaAggregativaXIndiceclassificazioneEstesa(ProtocolloOrdine protocolloOrdine,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> documentaleFacade.getStrutturaAggregativaXIndiceclassificazioneEstesa(protocolloOrdine.getIndiceClassificazioneEsteso()));
	}


	@Override
	public Response getStrutturaAggregativaXStrutturaAggregativa(ProtocolloOrdine protocolloOrdine, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String voceTitolario = protocolloOrdine.getVoceTitolario();
		String numeroFascicolo = protocolloOrdine.getNumeroFascicoloDossier();
		String aooDossier = protocolloOrdine.getAooDossier();
		return invoke(() -> documentaleFacade.getStrutturaAggregativaXStrutturaAggregativa(voceTitolario, numeroFascicolo,aooDossier));
	}
}

