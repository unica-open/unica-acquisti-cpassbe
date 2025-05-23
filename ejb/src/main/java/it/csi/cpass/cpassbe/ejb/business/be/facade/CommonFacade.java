/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.jboss.ejb3.annotation.TransactionTimeout;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetEnteByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetGestioneCampiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetMetadatiByModuoloFunzioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetMySectorFamilyService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetOrdinamentoByModuloFunzioneTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetRicercaParametroService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetSettoreTreeByEnteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetTokenService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetUfficiBySettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.GetUfficiValidiByEnteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.IsMySectorParentService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostListinoFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaDocumentoSpesaRipartibileService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaDocumentoSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaFornitoreInternoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostRicercaListinoFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PostSavePreferenzeService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.PutCaricaTabelleDaTrasmettereService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.DelOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.GetOggettiSpesaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.GetRicercaOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.PostOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.PutOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.PostRicercaElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaSettoreIndirizzoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.CheckCompatibilitaSettoriService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.IsSettoreRiorganizzatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.CheckCompatibilitaSettoriRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetGestioneCampiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMetadatiByModuoloFunzioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMySectorFamilyRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetOrdinamentoByModuloFunzioneTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetRicercaParametroRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetSettoreTreeByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetTokenRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetUfficiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetUfficiValidiByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.IsMySectorParentRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.IsSettoreRiorganizzatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaDocumentoSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreInternoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaListinoFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostSavePreferenzeRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PutCaricaTabelleDaTrasmettereRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.DelOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetOggettiSpesaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetRicercaOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.PostOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.PutOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ente.GetEnteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreIndirizzoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.CheckCompatibilitaSettoriResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetGestioneCampiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMetadatiByModuoloFunzioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMySectorFamilyResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetOrdinamentoByModuloFunzioneTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetRicercaParametroResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetSettoreTreeByEnteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetTokenResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetUfficiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetUfficiValidiByEnteResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.IsMySectorParentResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.IsSettoreRiorganizzatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaDocumentoSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreInternoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaListinoFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostSavePreferenzeResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PutCaricaTabelleDaTrasmettereResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.DelOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetOggettiSpesaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetRicercaOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.PostOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.PutOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ente.GetEnteByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreIndirizzoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreResponse;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
public class CommonFacade extends BaseFacade {
	// Injection point
	@Inject private CommonDad commonDad;
	@Inject private ProgrammaDad programmaDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private ElaborazioneDad elaborazioneDad;
	@Inject private SettoreDad settoreDad;
	@Inject private FornitoreDad fornitoreDad;
	@Inject private SystemDad systemDad;
	@Inject private EnteDad enteDad;
	@Inject private InterventoDad interventoDad;
	@Inject private InterventoImportiDad interventoImportiDad;

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
	public GetSettoreTreeByEnteResponse getSettoreTreeByEnte(UUID enteId, String codSettoreRadice, String validita) {
		return executeService(new GetSettoreTreeByEnteRequest(enteId, codSettoreRadice, validita ), new GetSettoreTreeByEnteService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaSettoreResponse postRicercaSettore(Integer page, Integer limit, String sort, String direction, Settore settore, UUID idSettoreRadice) {
		return executeService(new PostRicercaSettoreRequest(page, limit, sort, direction, settore, idSettoreRadice), new PostRicercaSettoreService(configurationHelper, settoreDad));
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
		return executeService(new PostListinoFornitoreRequest(listinoFornitore), new PostListinoFornitoreService(configurationHelper, commonDad, fornitoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaFornitoreInternoResponse postRicercaFornitoreInterno(Fornitore fornitore) {
		return executeService(new PostRicercaFornitoreInternoRequest(fornitore),new PostRicercaFornitoreInternoService(configurationHelper, commonDad, fornitoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaElaborazioneResponse postRicercaElaborazione(Elaborazione elaborazione, String elaborazioneTipoCodice) {
		return executeService(new PostRicercaElaborazioneRequest(elaborazione, elaborazioneTipoCodice), new PostRicercaElaborazioneService(configurationHelper, elaborazioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaDocumentoSpesaResponse postRicercaDocumentoSpesa(DocumentoSpesa ds) {
		return executeService(new PostRicercaDocumentoSpesaRequest(ds), new PostRicercaDocumentoSpesaService(configurationHelper, externalHelperLookup, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostRicercaDocumentoSpesaResponse postRicercaDocumentoSpesaRipartibile(DocumentoSpesa ds) {
		return executeService(new PostRicercaDocumentoSpesaRequest(ds), new PostRicercaDocumentoSpesaRipartibileService(configurationHelper, externalHelperLookup, settoreDad));
	}

	public GetMetadatiByModuoloFunzioneResponse getMetadatiByModuoloFunzione(String modulo, String funzione) {
		return executeService(new GetMetadatiByModuoloFunzioneRequest(modulo, funzione), new GetMetadatiByModuoloFunzioneService(configurationHelper, commonDad,  systemDad ));
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
		return executeService(new GetOrdinamentoByModuloFunzioneTipoRequest( modulo,  funzione,  tipo, listMetadatiFunzione), new GetOrdinamentoByModuloFunzioneTipoService(configurationHelper, commonDad, systemDad));
	}

	public IsSettoreRiorganizzatoResponse isSettoreRiorganizzato(UUID idSettorePrecedente, UUID idSettoreAttuale) {
		return executeService(new IsSettoreRiorganizzatoRequest( idSettorePrecedente, idSettoreAttuale), new IsSettoreRiorganizzatoService(configurationHelper, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CheckCompatibilitaSettoriResponse checkCompatibilitaSettori(UUID idSettoreEmittente, UUID idSettoreDetermina, String codiceStrutturaProponente) {
		return executeService( new CheckCompatibilitaSettoriRequest(idSettoreEmittente,idSettoreDetermina,codiceStrutturaProponente), new CheckCompatibilitaSettoriService(configurationHelper,settoreDad, systemDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaParametroResponse getRicercaParametro(String chiave) {
		return executeService( new GetRicercaParametroRequest(chiave), new GetRicercaParametroService(configurationHelper, commonDad, systemDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetEnteByIdResponse getEnteById(UUID id) {
		return executeService(new GetEnteByIdRequest(id), new GetEnteByIdService(configurationHelper, enteDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUfficiValidiByEnteResponse getUfficiValidiByEnte() {
		return executeService(new GetUfficiValidiByEnteRequest(), new GetUfficiValidiByEnteService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetGestioneCampiResponse getGestioneCampi() {
		return executeService(new GetGestioneCampiRequest(), new GetGestioneCampiService(configurationHelper, commonDad));
	}

	public PostOggettiSpesaResponse postOggettiSpesa(Ods ods) {
		return executeService(new PostOggettiSpesaRequest(ods),new PostOggettiSpesaService(configurationHelper, decodificaDad, commonDad));
	}

	public GetRicercaOggettiSpesaResponse getRicercaOggettiSpesa(Integer page, Integer limit, String sort, String direction, Ods oggettiSpesa) {
		return executeService(new GetRicercaOggettiSpesaRequest(page, limit, sort, direction, oggettiSpesa), new GetRicercaOggettiSpesaService(configurationHelper, commonDad));
	}

	public GetOggettiSpesaByIdResponse getOggettiSpesaById(Integer id) {
		return executeService(new GetOggettiSpesaByIdRequest(id), new GetOggettiSpesaByIdService(configurationHelper, commonDad));
	}

	public DelOggettiSpesaResponse delOggettiSpesa(Integer id,String controllo) {
		return executeService(new DelOggettiSpesaRequest(id, controllo), new DelOggettiSpesaService(configurationHelper, commonDad));
	}

	public PutOggettiSpesaResponse putOggettiSpesa(Ods ods) {
		return executeService(new PutOggettiSpesaRequest(ods),new PutOggettiSpesaService(configurationHelper, commonDad));
	}
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 20)
	public PutCaricaTabelleDaTrasmettereResponse putCaricaTabelleDaTrasmettere(String idProgramma) {
		return executeService(new PutCaricaTabelleDaTrasmettereRequest(idProgramma),new PutCaricaTabelleDaTrasmettereService(    configurationHelper,
				commonDad,
				programmaDad,
				interventoDad,
				systemDad,
				interventoImportiDad));
	}
	public GetTokenResponse getToken(Integer numeroToken,Integer numSec) {
		return executeService(new GetTokenRequest(numeroToken,numSec), new GetTokenService(configurationHelper, commonDad,systemDad));
	}
}
