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

import it.csi.cpass.cpassbe.ejb.business.be.facade.CommonFacade;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.web.business.be.api.CommonApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for CommonApiServiceImpl
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
	public Response getSettoreTreeByEnte(UUID enteId, String codSettoreRadice,String validita, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getSettoreTreeByEnte(enteId, codSettoreRadice,validita));
	}

	@Override
	public Response postRicercaSettore(UUID idSettoreRadice, Integer page, Integer limit, String sort, String direction, Settore settore, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postRicercaSettore(page, limit, sort, direction, settore, idSettoreRadice));
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


	@Override
	public Response isSettoreRiorganizzato(UUID idSettorePrecedente, UUID idSettoreAttuale,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.isSettoreRiorganizzato(idSettorePrecedente,  idSettoreAttuale));
	}

	@Override
	public Response checkCompatibilitaSettori(UUID idSettoreEmittente, String codiceStrutturaProponente, UUID idSettoreDetermina, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
				return invoke(() -> commonFacade.checkCompatibilitaSettori(idSettoreEmittente,idSettoreDetermina,codiceStrutturaProponente));
	}

	@Override
	public Response getRicercaParametro(String chiave, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getRicercaParametro(chiave));
	}

	@Override
	public Response getEnteById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->commonFacade.getEnteById(id));
	}

	@Override
	public Response getUfficiValidiByEnte( SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getUfficiValidiByEnte());
	}

	@Override
	public Response getGestioneCampi(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getGestioneCampi());
	}

	@Override
	public Response postOggettiSpesa(Ods ods, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.postOggettiSpesa(ods));
	}

	@Override
	public Response getRicercaOggettiSpesa(Integer page, Integer limit, String sort, String direction, Ods oggettiSpesa, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getRicercaOggettiSpesa(page, limit, sort, direction, oggettiSpesa));
	}

	@Override
	public Response putOggettiSpesa(Ods ods, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.putOggettiSpesa( ods));
	}

	@Override
	public Response delOggettiSpesa(Integer id,String controllo, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.delOggettiSpesa(id,controllo));
	}

	@Override
	public Response getOggettiSpesaById(Integer id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getOggettiSpesaById(id));

	}

	@Override
	public Response getCaricaTabelleDaTrasmettere(String idProgramma, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.putCaricaTabelleDaTrasmettere(idProgramma));
	}

	@Override
	public Response getToken(Integer numeroToken,Integer numSec, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> commonFacade.getToken(numeroToken,numSec));
	}


}
