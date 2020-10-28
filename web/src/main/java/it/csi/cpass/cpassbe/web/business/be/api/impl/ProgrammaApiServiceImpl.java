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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.ProgrammaFacade;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.web.business.be.api.ProgrammaApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for ProgrammaApi
 */
@Logged
public class ProgrammaApiServiceImpl extends BaseRestServiceImpl implements ProgrammaApi {

	@EJB private ProgrammaFacade programmaFacade;

	@Override
	public Response deleteProgrammaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.deleteProgrammaById(id));
	}
	@Override
	public Response getProgrammaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getProgrammaById(id));
	}
	@Override
	public Response postProgramma(Programma programma, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.postProgramma(programma));
	}
	
	@Override
	public Response postProgrammaCopia(Boolean soloControlli, Programma programma, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.postProgrammaCopia(programma, soloControlli));
	}

	@Override
	public Response putProgrammaById(UUID id, Programma programma, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.putProgrammaById(id, programma));
	}
	@Override
	public Response putProgrammaStatoAnnullatoById(UUID id, Programma programma,  SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.putProgrammaStatoAnnullatoById(id, programma));
	}

	@Override
	public Response putProgrammaStatoConfermatoById(UUID id, Boolean ignoreWarning, Programma programma, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.putProgrammaStatoConfermatoById(id, programma, ignoreWarning));
	}

	@Override
	public Response putProgrammaStatoRiportaInBozzaById(UUID id, Programma programma, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.putProgrammaStatoRiportaInBozzaById(id, programma));
	}
	
	@Override
	public Response getProgrammiBySettore(UUID settoreId, Boolean soloValidi ,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getProgrammiBySettore(settoreId, soloValidi));
	}
	@Override
	public Response getProgrammiBySettoreAnnoVersione(UUID settoreId, Integer anno, Integer versione,SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getProgrammiBySettoreAnnoVersione(settoreId, anno, versione));
	}
	@Override
	public Response getProgrammiBySettoreAndStato(UUID settoreId, String statoCode, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getProgrammiBySettoreAndStato(settoreId, statoCode));
	}
	@Override
	public Response getUltimiProgrammiBySettoreAndStato(UUID settoreId, String statoCode,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getUltimiProgrammiBySettoreAndStato(settoreId, statoCode));
	}	
	@Override
	public Response getProgrammiTrasmissioneMIT(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.getProgrammiTrasmissioneMIT());
	}
	
	@Override
	public Response putTrasmettiProgrammaById(UUID idProgramma, UUID idUtente, String modalitaInvio, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> programmaFacade.putTrasmettiProgrammaById(idProgramma, idUtente, modalitaInvio));
	}
	
}
