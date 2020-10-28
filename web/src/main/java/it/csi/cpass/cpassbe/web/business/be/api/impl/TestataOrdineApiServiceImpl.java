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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.OrdineFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SalvaImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.web.business.be.api.TestataOrdineApi;
import it.csi.cpass.cpassbe.web.dto.RicercaOrdini;
import it.csi.cpass.cpassbe.web.dto.RicercaRigheDaEvadere;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for TestataOrdineApi
 */
@Logged
public class TestataOrdineApiServiceImpl extends BaseRestServiceImpl implements TestataOrdineApi {

	@EJB
	private OrdineFacade testataOrdineFacade;

	@Override
	public Response postTestataOrdine(TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postTestataOrdine(testataOrdine));
	}

	@Override
	public Response putTestataOrdineById(UUID id, TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putTestataOrdineById(id, testataOrdine));
	}
	
	@Override
	public Response deleteTestataOrdineById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.deleteTestataOrdineById(id));
	}
	
	@Override
	public Response postAnnullaTestataOrdine(TestataOrdine testataOrdine, boolean bypassControlli, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postAnnullaTestataOrdine(testataOrdine, bypassControlli));
	}

	@Override
	public Response getTestataOrdineById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getTestataOrdineById(id));
	}

	
	@Override
	public Response postRicercaProvvedimento(Integer offset, Integer limit, String sort, String direction, Provvedimento provvedimento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postRicercaProvvedimento(provvedimento));
	}

	@Override
	public Response postRicercaImpegno(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort, String direction, FiltroImpegni filtroImpegni, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postRicercaImpegno(filtroImpegni, offset, limit, sort, direction));
	}

	@Override
	public Response postOrdineDestinatario(Destinatario destinatario, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postOrdineDestinatario(destinatario));
	}

	@Override
	public Response postRigaOrdine(RigaOrdine rigaOrdine, Boolean bypassControlloIva, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postRigaOrdine(rigaOrdine, bypassControlloIva));
	}
	
	@Override
	public Response postCopiaRighe(UUID idFrom, UUID idTo, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postCopiaRighe(idFrom, idTo));
	}
	
	@Override
	public Response putOrdineDestinatario(Destinatario destinatario, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineDestinatario(destinatario));
	}

	@Override
	public Response putRigaOrdine(RigaOrdine rigaOrdine, Boolean bypassControlloIva, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putRigaOrdine(rigaOrdine, bypassControlloIva));
	}
	
	@Override
	public Response deleteDestinatario(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.deleteDestinatario(id));
	}
	
	@Override
	public Response deleteRigaOrdine(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.deleteRigaOrdine(id));
	}

	@Override
	public Response deleteImpegniByRiga(UUID rigaOrdineId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.deleteImpegniByRiga(rigaOrdineId));
	}

	@Override
	public Response getRicercaTestataOrdineByAnnoENum(Integer anno, Integer numero, UUID idEnte, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getTestataOrdineByAnnoENum(anno, numero, idEnte));
	}

	@Override
	public Response getRicercaDestinatariPerCopia(UUID idOrdine, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getDestinatariPerCopia(idOrdine));
	}

	@Override
	public Response getRicercaRigheByDestinatario(UUID idDestinatario, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getRigheByDestinatario(idDestinatario));
	}
	
	@Override
	public Response getRicercaImpegniByRiga(UUID idRiga, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getImpegniByRiga(idRiga));
	}
	
	@Override
	public Response postImpegni(SalvaImpegni salvaImpegni, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.postImpegni(salvaImpegni));
	}

	@Override
	public Response getRiepilogoImpegniByOrdineId(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getRiepilogoImpegniByOrdineId(id));
	}
	
	@Override
	public Response putOrdineControllaById(UUID id,
			TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineControllaById(id, testataOrdine));
	}

	@Override
	public Response putOrdineConfermaById(UUID id, TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineConfermaById(id, testataOrdine));
	}

	@Override
	public Response putOrdineAutorizzaById(UUID id, TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineAutorizzaById(id, testataOrdine));
	}
	
	@Override
	public Response putOrdineInviaNSO(UUID id, TestataOrdine testataOrdine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineInviaNSO(id, testataOrdine));
	}
	
	@Override
	public Response putOrdineVerificheFattibilitaChiudiById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineVerificheFattibilitaChiudiById(id));
	}
	
	@Override
	public Response putOrdineChiudiById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.putOrdineChiudiById(id));
	}

	@Override
	public Response getRicercaOrdini(Integer page, Integer limit, String sort, String direction, RicercaOrdini ricercaOrdini, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getRicercaOrdini(page, limit, sort, direction, ricercaOrdini.getAnnoOrdineDa(),
				ricercaOrdini.getNumeroOrdineDa(), ricercaOrdini.getAnnoOrdineA(), ricercaOrdini.getNumeroOrdineA(), ricercaOrdini.getDataEmissioneDa(),
				ricercaOrdini.getDataEmissioneA(), ricercaOrdini.getTestataOrdine(), ricercaOrdini.getDestinatario(), ricercaOrdini.getImpegno(),
				ricercaOrdini.getSubimpegno(), ricercaOrdini.getRigaOrdine()));
	}

	@Override
	public Response getTestateOrdineByEvasioneId(UUID idEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getTestateOrdineByEvasioneId(idEvasione));
	}
	
	@Override
	public Response getTestateOrdineByDestinatarioEvasioneId(UUID idDestinatario, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getTestateOrdineByDestinatarioEvasioneId(idDestinatario));
	}

	@Override
	public Response getTestateOrdineByRigaEvasioneId(UUID idRiga, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getTestateOrdineByRigaEvasioneId(idRiga));
	}
	
    @Override
	public Response getRicercaRigheDaEvadere(RicercaRigheDaEvadere ricercaRigheDaEvadere
			, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> testataOrdineFacade.getRigheDaEvadere(ricercaRigheDaEvadere.getAnnoOrdineDa(), ricercaRigheDaEvadere.getNumeroOrdineDa(), ricercaRigheDaEvadere.getAnnoOrdineA(), ricercaRigheDaEvadere.getNumeroOrdineA(), ricercaRigheDaEvadere.getDataEmissioneDa(), ricercaRigheDaEvadere.getDataEmissioneA(), ricercaRigheDaEvadere.getTestataOrdine(), ricercaRigheDaEvadere.getDestinatario(), ricercaRigheDaEvadere.getImpegno(), ricercaRigheDaEvadere.getSubimpegno(), ricercaRigheDaEvadere.getRigaOrdine(), ricercaRigheDaEvadere.getOdsList()
				));
	}
}
