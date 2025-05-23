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

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods.GetRicercaCpvOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAcquistiVariatiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAliquoteIvaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAllCausaleSospensioneEvasioneValideService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAusaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetCpvService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetCpvTreeService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetModalitaAffidamentoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetMotiviEsclusioneCigService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetNutsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetPrioritaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetProvvedimentoTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetRicompresoTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetRisorsaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetSettoreInterventiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetStatoByTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetStatoNsoByTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoAcquistoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoProceduraOrdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoProceduraPbaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetUnitaMisuraService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetRicercaCpvOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAcquistiVariatiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAliquoteIvaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAllCausaleSospensioneEvasioneValideRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAusaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetCpvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetCpvTreeRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetModalitaAffidamentoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetMotiviEsclusioneCigRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetNutsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetPrioritaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetProvvedimentoTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRicompresoTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRisorsaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetSettoreInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetStatoByTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetStatoNsoByTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoAcquistoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoProceduraOrdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoProceduraPbaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetUnitaMisuraRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetRicercaCpvOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAcquistiVariatiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAliquoteIvaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAllCausaleSospensioneEvasioneValideResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAusaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetCpvResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetCpvTreeResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetModalitaAffidamentoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetMotiviEsclusioneCigResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetNutsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetPrioritaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetProvvedimentoTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRicompresoTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRisorsaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetSettoreInterventiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetStatoByTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetStatoNsoByTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoAcquistoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoProceduraOrdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoProceduraPbaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoSettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetUnitaMisuraResponse;
import it.csi.cpass.cpassbe.lib.dto.Ods;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Lock(LockType.READ)
public class DecodificaFacade extends BaseFacade {
	// Injection point
	@Inject private DecodificaDad decodificaDad;

	/**
	 * Gets the Cpvs
	 * @return the cpvs
	 */
	public GetCpvResponse getCpv() {
		return executeService(new GetCpvRequest(), new GetCpvService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the getCpvTree
	 * @return the getCpvTree
	 */
	public GetCpvTreeResponse getCpvTree() {
		return executeService(new GetCpvTreeRequest(Boolean.FALSE), new GetCpvTreeService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the getCpvOdsTree
	 * @return the getCpvOdsTree
	 */
	public GetCpvTreeResponse getCpvOdsTree() {
		return executeService(new GetCpvTreeRequest(Boolean.TRUE), new GetCpvTreeService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the ModalitaAffidamentos
	 * @return the modalitaAffidamentos
	 */
	public GetModalitaAffidamentoResponse getModalitaAffidamento() {
		return executeService(new GetModalitaAffidamentoRequest(), new GetModalitaAffidamentoService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Nuts
	 * @return the nuts
	 */
	public GetNutsResponse getNuts() {
		return executeService(new GetNutsRequest(), new GetNutsService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Prioritas
	 * @return the prioritas
	 */
	public GetPrioritaResponse getPriorita() {
		return executeService(new GetPrioritaRequest(), new GetPrioritaService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Risorses
	 * @return the risorses
	 */
	public GetRisorsaResponse getRisorse() {
		return executeService(new GetRisorsaRequest(), new GetRisorsaService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the SettoreInterventis
	 * @return the settoreInterventis
	 */
	public GetSettoreInterventiResponse getSettoreInterventi() {
		return executeService(new GetSettoreInterventiRequest(), new GetSettoreInterventiService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the ausa
	 * @return the ausas
	 */
	public GetAusaResponse getAusas() {
		return executeService(new GetAusaRequest(), new GetAusaService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the AcquistiVariati
	 * @return the acquistiVariati
	 */
	public GetAcquistiVariatiResponse getAcquistiVariati() {
		return executeService(new GetAcquistiVariatiRequest(), new GetAcquistiVariatiService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the RicompresoTipo
	 * @return the RicompresoTipo
	 */
	public GetRicompresoTipoResponse getRicompresoTipos() {
		return executeService(new GetRicompresoTipoRequest(), new GetRicompresoTipoService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	public GetTipoOrdineResponse getTipoOrdine() {
		return executeService(new GetTipoOrdineRequest(), new GetTipoOrdineService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	public GetTipoOrdineResponse getListaTipoOrdineExcludeCode(String noTypeCode) {
		return executeService(new GetTipoOrdineRequest( noTypeCode), new GetTipoOrdineService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the TipoProceduras
	 * @return the TipoProceduras
	 */
	public GetTipoProceduraOrdResponse getTipoProceduraOrd() {
		return executeService(new GetTipoProceduraOrdRequest(), new GetTipoProceduraOrdService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the TipoProceduras
	 * @return the TipoProceduras
	 */
	public GetTipoProceduraPbaResponse getTipoProceduraPba() {
		return executeService(new GetTipoProceduraPbaRequest(), new GetTipoProceduraPbaService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the AliquoteIva
	 * @return the AliquoteIvas
	 */
	public GetAliquoteIvaResponse getAliquoteIva() {
		return executeService(new GetAliquoteIvaRequest(), new GetAliquoteIvaService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the UnitaMisura
	 * @return the UnitaMisura
	 */
	public GetUnitaMisuraResponse getUnitaMisura() {
		return executeService(new GetUnitaMisuraRequest(), new GetUnitaMisuraService(configurationHelper, decodificaDad));
	}

	/**
	 *
	 * @param page
	 * @param limit
	 * @param sort
	 * @param direction
	 * @param ods
	 * @return
	 */
	public GetRicercaCpvOggettiSpesaResponse getRicercaCpvOggettiSpesa(Integer page, Integer limit, String sort, String direction, Ods ods) {
		return executeService(new GetRicercaCpvOggettiSpesaRequest(page, limit, sort, direction, ods), new GetRicercaCpvOggettiSpesaService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the Statos
	 * @return the Statos
	 */
	public GetStatoByTipoResponse getStatoByTipo(String tipo) {
		return executeService(new GetStatoByTipoRequest(tipo), new GetStatoByTipoService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the StatoNsos
	 * @return the StatoNsos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetStatoNsoByTipoResponse getStatoNsoByTipo(String tipo) {
		return executeService(new GetStatoNsoByTipoRequest(tipo), new GetStatoNsoByTipoService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the CausaleSospensioneEvasione valid
	 * @return the CausaleSospensioneEvasiones
	 */
	public GetAllCausaleSospensioneEvasioneValideResponse getAllCausaleSospensioneEvasioneValide() {
		return executeService(new GetAllCausaleSospensioneEvasioneValideRequest(), new GetAllCausaleSospensioneEvasioneValideService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	public GetTipoEvasioneResponse getTipoEvasione() {
		return executeService(new GetTipoEvasioneRequest(), new GetTipoEvasioneService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	public GetTipoAcquistoResponse getTipoAcquisto() {
		return executeService(new GetTipoAcquistoRequest(), new GetTipoAcquistoService(configurationHelper, decodificaDad));
	}

	public GetProvvedimentoTipoResponse getProvvedimentoTipo() {
		return executeService(new GetProvvedimentoTipoRequest(), new GetProvvedimentoTipoService(configurationHelper, decodificaDad));
	}

	public GetMotiviEsclusioneCigResponse getMotiviEsclusioneCig() {
		return executeService(new GetMotiviEsclusioneCigRequest(), new GetMotiviEsclusioneCigService(configurationHelper, decodificaDad));
	}

	public GetTipoSettoreResponse getTipoSettore() {
		return executeService(new GetTipoSettoreRequest(), new GetTipoSettoreService(configurationHelper, decodificaDad));
	}
}
