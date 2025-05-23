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

import java.util.UUID;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.GetProtocollaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.GetProtocolloOrigineByAnnoNumService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.GetStrutturaAggregativaXIndiceclassificazioneEstesaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.GetStrutturaAggregativaXStrutturaAggregativaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.GetVerificaArchiviazioneOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale.PostArchiviaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetProtocollaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetProtocolloOrigineByAnnoNumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetStrutturaAggregativaXStrutturaAggregativaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetVerificaArchiviazioneOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.PostArchiviaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetProtocollaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetProtocolloOrigineByAnnoNumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetStrutturaAggregativaXIndiceclassificazioneEstesaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetStrutturaAggregativaXStrutturaAggregativaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetVerificaArchiviazioneOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.PostArchiviaOrdineResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;


/**
 * ProtocolloOrdineFacade
 */
@Stateless
public class DocumentaleFacade extends BaseFacade {
	// Injection point
	@Inject private DocumentaleDad documentaleDad;
	@Inject private TestataOrdineDad testataOrdineDad;
	@Inject private CommonDad    commonDad;
	@Inject private StampeDad    stampeDad;
	@Inject private UtenteDad    utenteDad;
	@Inject private SettoreDad   settoreDad;
	@Inject private FornitoreDad fornitoreDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private RigaOrdineDad rigaOrdineDad;
	@Inject private DestinatarioOrdineDad destinatarioOrdineDad;
	@Inject private SystemDad systemDad;

	public GetProtocolloOrigineByAnnoNumResponse getProtocolloOrigineByAnnoNum(Integer anno, String numero) {
		return executeService(new GetProtocolloOrigineByAnnoNumRequest(anno, numero), new GetProtocolloOrigineByAnnoNumService(configurationHelper, externalHelperLookup, documentaleDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PostArchiviaOrdineResponse getArchiviaOrdine(UUID testataOrdineId, Boolean aggiornaOrdine) {
		return executeService(new PostArchiviaOrdineRequest(testataOrdineId, aggiornaOrdine), new PostArchiviaOrdineService( configurationHelper,  externalHelperLookup, testataOrdineDad,  documentaleDad, commonDad,stampeDad,utenteDad,settoreDad,fornitoreDad,decodificaDad,rigaOrdineDad,destinatarioOrdineDad,systemDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetVerificaArchiviazioneOrdineResponse getVerificaArchiviazioneOrdine(UUID testataOrdineId) {
		return executeService(new GetVerificaArchiviazioneOrdineRequest(testataOrdineId), new GetVerificaArchiviazioneOrdineService(  configurationHelper, externalHelperLookup, testataOrdineDad, documentaleDad, commonDad, utenteDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetProtocollaOrdineResponse getProtocollaOrdine(TestataOrdine testataOrdine, ProtocolloOrdine protocolloOrdine) {
		return executeService(new GetProtocollaOrdineRequest(testataOrdine, protocolloOrdine), new GetProtocollaOrdineService( configurationHelper,  externalHelperLookup, testataOrdineDad,  documentaleDad, commonDad,stampeDad,settoreDad,systemDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetStrutturaAggregativaXIndiceclassificazioneEstesaResponse getStrutturaAggregativaXIndiceclassificazioneEstesa(String indiceclassificazione) {
		return executeService(new GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest(indiceclassificazione), new GetStrutturaAggregativaXIndiceclassificazioneEstesaService(configurationHelper, externalHelperLookup, documentaleDad, settoreDad));

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetStrutturaAggregativaXStrutturaAggregativaResponse getStrutturaAggregativaXStrutturaAggregativa(String voceTitolario, String numeroFascicolo, String aoo) {
		return executeService(new GetStrutturaAggregativaXStrutturaAggregativaRequest(voceTitolario, numeroFascicolo,aoo), new GetStrutturaAggregativaXStrutturaAggregativaService(configurationHelper, externalHelperLookup, documentaleDad, settoreDad));
	}

}
