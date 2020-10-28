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

import it.csi.cpass.cpassbe.ejb.business.be.facade.CommonFacade;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.web.business.be.api.CommonApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for DecodificaApi
 */
@Logged
public class CommonApiServiceImpl extends BaseRestServiceImpl implements CommonApi {

	@EJB
	private CommonFacade commonFacade;

	@Override
	public Response getUfficiBySettore(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getUfficiBySettore(settoreId));
	}

	@Override
	public Response getSettoreTreeByEnte(UUID enteId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getSettoreTreeByEnte(enteId));
	}

	@Override
	public Response postRicercaSettore(Integer page, Integer limit, String sort, String direction, Settore settore, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaSettore(page, limit, sort, direction, settore));
	}

	@Override
	public Response postRicercaSettoreIndirizzo(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest, Settore settore) {
		return invoke(() -> commonFacade.postRicercaSettoreIndirizzo(settore));
	}

	@Override
	public Response postRicercaFornitore(Integer offset, Integer limit, String sort, String direction, Fornitore fornitore, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaFornitore(fornitore));
	}

	@Override
	public Response postRicercaDocumentoSpesa(DocumentoSpesa ds, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaDocumentoSpesa(ds));
	}

	@Override
	public Response postRicercaDocumentoSpesaRipartibile(DocumentoSpesa ds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaDocumentoSpesaRipartibile(ds));
	}

	@Override
	public Response postRicercaListinoFornitore(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort, String direction,
			ListinoFornitore listinoFornitore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaListinoFornitore(listinoFornitore, offset, limit, sort, direction));
	}

	@Override
	public Response postListinoFornitore(ListinoFornitore listinoFornitore, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postListinoFornitore(listinoFornitore));
	}

	@Override
	public Response postRicercaFornitoreInterno(Fornitore fornitore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaFornitoreInterno(fornitore));
	}

	@Override
	public Response postRicercaElaborazioneProgramma(Elaborazione elaborazione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaElaborazione(elaborazione, ElaborazioneTipoEnum.TRASMISSIONE_PROGRAMMA_MIT.getCostante()));
	}

	@Override
	public Response getMetadatiByModuoloFunzione(String modulo, String funzione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getMetadatiByModuoloFunzione(modulo, funzione));
	}

	@Override
	public Response postSavePreferenze(Map<String, Object> preferenze, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postSavePreferenze(preferenze));
	}

	@Override
	public Response isMySectorParent(UUID idSettore, UUID idSettoreParent, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.isMySectorParent(idSettore, idSettoreParent));
	}

	@Override
	public Response getMySectorFamily(UUID idSettore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getMySectorFamily(idSettore));
	}

	@Override
	public Response getOrdinamentoByModuloFunzioneTipo(String modulo, String funzione, String tipo,List<MetadatiFunzione> listMetadatiFunzione, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getOrdinamentoByModuloFunzioneTipo( modulo,  funzione,  tipo,listMetadatiFunzione));
	}

}
