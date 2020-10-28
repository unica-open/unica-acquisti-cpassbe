/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetMetadatiByModuoloFunzioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetMySectorFamilyService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetOrdinamentoByModuloFunzioneTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetSettoreTreeByEnteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetUfficiBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.IsMySectorParentService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostListinoFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaDocumentoSpesaRipartibileService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaDocumentoSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaFornitoreInternoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaListinoFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostSavePreferenzeService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaSettoreIndirizzoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMetadatiByModuoloFunzioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMySectorFamilyRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetOrdinamentoByModuloFunzioneTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetSettoreTreeByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetUfficiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.IsMySectorParentRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaDocumentoSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreInternoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostSavePreferenzeRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreIndirizzoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMetadatiByModuoloFunzioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMySectorFamilyResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetOrdinamentoByModuloFunzioneTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetSettoreTreeByEnteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetUfficiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.IsMySectorParentResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaDocumentoSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreInternoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostSavePreferenzeResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreIndirizzoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreResponse;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Settore;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
public class CommonFacade extends BaseFacade {
	// Injection point
	@Inject private CommonDad commonDad;
	
	@Inject private SettoreDad settoreDad;

	/**
	 * Gets the Ufficios By Settore
	 * @return the Ufficios del settore
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUfficiBySettoreResponse getUfficiBySettore(UUID settoreId) {
		return executeService(new GetUfficiBySettoreRequest(settoreId), new GetUfficiBySettoreService(configurationHelper, commonDad));
	}
	
	/**
	 * Gets the Settori Tree By Ente
	 * @return the Settore Tree
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSettoreTreeByEnteResponse getSettoreTreeByEnte(UUID enteId) {
		return executeService(new GetSettoreTreeByEnteRequest(enteId), new GetSettoreTreeByEnteService(configurationHelper, commonDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaSettoreResponse postRicercaSettore(Integer page, Integer limit, String sort, String direction, Settore settore) {
		return executeService(new PostRicercaSettoreRequest(page, limit, sort, direction, settore), new PostRicercaSettoreService(configurationHelper, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaSettoreIndirizzoResponse postRicercaSettoreIndirizzo(Settore settore) {
		return executeService(new PostRicercaSettoreIndirizzoRequest(settore), new PostRicercaSettoreIndirizzoService(configurationHelper, settoreDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaFornitoreResponse postRicercaFornitore(Fornitore fornitore) {
		return executeService(new PostRicercaFornitoreRequest(fornitore), new PostRicercaFornitoreService(configurationHelper, externalHelperLookup));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaListinoFornitoreResponse postRicercaListinoFornitore(ListinoFornitore listinoFornitore, @Min(0) Integer offset,@Min(1) @Max(100) Integer limit, String sort, String direction) {
		return executeService(new PostRicercaListinoFornitoreRequest(offset, limit, sort, direction, listinoFornitore),new PostRicercaListinoFornitoreService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostListinoFornitoreResponse postListinoFornitore(ListinoFornitore listinoFornitore) {
		return executeService(new PostListinoFornitoreRequest(listinoFornitore), new PostListinoFornitoreService(configurationHelper, commonDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaFornitoreInternoResponse postRicercaFornitoreInterno(Fornitore fornitore) {
		return executeService(new PostRicercaFornitoreInternoRequest(fornitore),new PostRicercaFornitoreInternoService(configurationHelper, commonDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaElaborazioneResponse postRicercaElaborazione(Elaborazione elaborazione, String elaborazioneTipoCodice) {
		return executeService(new PostRicercaElaborazioneRequest(elaborazione, elaborazioneTipoCodice), new PostRicercaElaborazioneService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaDocumentoSpesaResponse postRicercaDocumentoSpesa(DocumentoSpesa ds) {
		return executeService(new PostRicercaDocumentoSpesaRequest(ds), new PostRicercaDocumentoSpesaService(configurationHelper, externalHelperLookup));
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaDocumentoSpesaResponse postRicercaDocumentoSpesaRipartibile(DocumentoSpesa ds) {
		return executeService(new PostRicercaDocumentoSpesaRequest(ds), new PostRicercaDocumentoSpesaRipartibileService(configurationHelper, externalHelperLookup));
	}

	public GetMetadatiByModuoloFunzioneResponse getMetadatiByModuoloFunzione(String modulo, String funzione) {
		return executeService(new GetMetadatiByModuoloFunzioneRequest(modulo, funzione), new GetMetadatiByModuoloFunzioneService(configurationHelper, commonDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostSavePreferenzeResponse postSavePreferenze(Map<String, Object> preferenze) {
		return executeService(new PostSavePreferenzeRequest(preferenze), new PostSavePreferenzeService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public IsMySectorParentResponse isMySectorParent(UUID idSettore, UUID idSettoreParent) {
		return executeService(new IsMySectorParentRequest(idSettore, idSettoreParent), new IsMySectorParentService(configurationHelper, commonDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetMySectorFamilyResponse getMySectorFamily(UUID idSettore) {
		return executeService(new GetMySectorFamilyRequest(idSettore), new GetMySectorFamilyService(configurationHelper, commonDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetOrdinamentoByModuloFunzioneTipoResponse getOrdinamentoByModuloFunzioneTipo(String modulo, String funzione, String tipo,List<MetadatiFunzione> listMetadatiFunzione) {
		return executeService(new GetOrdinamentoByModuloFunzioneTipoRequest( modulo,  funzione,  tipo, listMetadatiFunzione), new GetOrdinamentoByModuloFunzioneTipoService(configurationHelper, commonDad));
	}
}
