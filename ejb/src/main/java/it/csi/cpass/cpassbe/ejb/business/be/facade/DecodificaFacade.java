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

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAcquistiVariatiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAliquoteIvaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAllCausaleSospensioneEvasioneValideService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetAusaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetCpvService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetCpvTreeService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetModalitaAffidamentoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetNutsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetPrioritaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetRicercaOggettiSpesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetRicompresoTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetRisorsaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetSettoreInterventiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetStatoByTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetStatoElOrdineByTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetStatoNsoByTipoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoAcquistoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetTipoProceduraService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.GetUnitaMisuraService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAcquistiVariatiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAliquoteIvaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAllCausaleSospensioneEvasioneValideRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAusaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetCpvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetCpvTreeRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetModalitaAffidamentoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetNutsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetPrioritaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRicercaOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRicompresoTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRisorsaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetSettoreInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetStatoByTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetStatoElOrdineByTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetStatoNsoByTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoAcquistoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoProceduraRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetUnitaMisuraRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAcquistiVariatiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAliquoteIvaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAllCausaleSospensioneEvasioneValideResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAusaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetCpvResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetCpvTreeResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetModalitaAffidamentoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetNutsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetPrioritaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRicercaOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRicompresoTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRisorsaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetSettoreInterventiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetStatoByTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetStatoElOrdineByTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetStatoNsoByTipoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoAcquistoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoProceduraResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetUnitaMisuraResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
public class DecodificaFacade extends BaseFacade {
	// Injection point
	@Inject private DecodificaDad decodificaDad;

	/**
	 * Gets the Cpvs
	 * @return the cpvs
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetCpvResponse getCpv() {
		return executeService(new GetCpvRequest(), new GetCpvService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the getCpvTree
	 * @return the getCpvTree
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetCpvTreeResponse getCpvTree() {
		return executeService(new GetCpvTreeRequest(false), new GetCpvTreeService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the getCpvOdsTree
	 * @return the getCpvOdsTree
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetCpvTreeResponse getCpvOdsTree() {
		return executeService(new GetCpvTreeRequest(true), new GetCpvTreeService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the ModalitaAffidamentos
	 * @return the modalitaAffidamentos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetModalitaAffidamentoResponse getModalitaAffidamento() {
		return executeService(new GetModalitaAffidamentoRequest(), new GetModalitaAffidamentoService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Nuts
	 * @return the nuts
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetNutsResponse getNuts() {
		return executeService(new GetNutsRequest(), new GetNutsService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Prioritas
	 * @return the prioritas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetPrioritaResponse getPriorita() {
		return executeService(new GetPrioritaRequest(), new GetPrioritaService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the Risorses
	 * @return the risorses
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRisorsaResponse getRisorse() {
		return executeService(new GetRisorsaRequest(), new GetRisorsaService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the SettoreInterventis
	 * @return the settoreInterventis
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSettoreInterventiResponse getSettoreInterventi() {
		return executeService(new GetSettoreInterventiRequest(), new GetSettoreInterventiService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the ausa
	 * @return the ausas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetAusaResponse getAusas() {
		return executeService(new GetAusaRequest(), new GetAusaService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the AcquistiVariati
	 * @return the acquistiVariati
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetAcquistiVariatiResponse getAcquistiVariati() {
		return executeService(new GetAcquistiVariatiRequest(), new GetAcquistiVariatiService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the RicompresoTipo
	 * @return the RicompresoTipo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicompresoTipoResponse getRicompresoTipos() {
		return executeService(new GetRicompresoTipoRequest(), new GetRicompresoTipoService(configurationHelper, decodificaDad));
	}
	
	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetTipoOrdineResponse getTipoOrdine() {
		return executeService(new GetTipoOrdineRequest(), new GetTipoOrdineService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the TipoProceduras
	 * @return the TipoProceduras
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetTipoProceduraResponse getTipoProcedura() {
		return executeService(new GetTipoProceduraRequest(), new GetTipoProceduraService(configurationHelper, decodificaDad));
	}
	
	/**
	 * Gets the StatoElOrdines
	 * @return the StatoElOrdines
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetStatoElOrdineByTipoResponse getStatoElOrdineByTipo(String tipo) {
		return executeService(new GetStatoElOrdineByTipoRequest(tipo), new GetStatoElOrdineByTipoService(configurationHelper, decodificaDad));
	}

	
	/**
	 * Gets the AliquoteIva
	 * @return the AliquoteIvas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetAliquoteIvaResponse getAliquoteIva() {
		return executeService(new GetAliquoteIvaRequest(), new GetAliquoteIvaService(configurationHelper, decodificaDad));
	}
	
	/**
	 * Gets the UnitaMisura
	 * @return the UnitaMisura
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetUnitaMisuraResponse getUnitaMisura() {
		return executeService(new GetUnitaMisuraRequest(), new GetUnitaMisuraService(configurationHelper, decodificaDad));
	}

	/**
	 * Retrieves the Interventi
	 * @param page the page
	 * @param limit the limit
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 * @return the interventos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaOggettiSpesaResponse getRicercaOggettiSpesa(Integer page, Integer limit, String sort, String direction, OggettiSpesa oggettiSpesa) {
		return executeService(new GetRicercaOggettiSpesaRequest(page, limit, sort, direction, oggettiSpesa), new GetRicercaOggettiSpesaService(configurationHelper, decodificaDad));
	}
	
	/**
	 * Gets the Statos
	 * @return the Statos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetAllCausaleSospensioneEvasioneValideResponse getAllCausaleSospensioneEvasioneValide() {
		return executeService(new GetAllCausaleSospensioneEvasioneValideRequest(), new GetAllCausaleSospensioneEvasioneValideService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetTipoEvasioneResponse getTipoEvasione() {
		return executeService(new GetTipoEvasioneRequest(), new GetTipoEvasioneService(configurationHelper, decodificaDad));
	}
	/**
	 * Gets the TipoOrdines
	 * @return the TipoOrdines
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetTipoAcquistoResponse getTipoAcquisto() {
		return executeService(new GetTipoAcquistoRequest(), new GetTipoAcquistoService(configurationHelper, decodificaDad));
	}
}
