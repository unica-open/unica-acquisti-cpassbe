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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseInviaOrdineNSOResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNso;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoInvioDocumento;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.TipoOrdineNSO;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.NSOListener;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Invio ordine a NSO
 */
//public class BaseInviaOrdineNSOService extends BaseTestataOrdineService<InviaOrdineNSORequest, InviaOrdineNSOResponse> implements NSOListener {
public abstract class BaseInviaOrdineNSOService<Q extends BaseRequest, R extends BaseInviaOrdineNSOResponse> extends BaseService<Q, R> implements NSOListener{

	/** Data Access Delegate for TestataOrdine */
	protected final TestataOrdineDad testataOrdineDad;
	protected final DecodificaDad decodificaDad;
	protected final DestinatarioOrdineDad destinatarioOrdineDad;
	protected final RigaOrdineDad rigaOrdineDad;
	protected final ImpegnoDad impegnoDad;
	protected final SettoreDad settoreDad;
	protected final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param externalHelperLookup
	 */
	protected BaseInviaOrdineNSOService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad,
			RigaOrdineDad rigaOrdineDad, DecodificaDad decodificaDad,ImpegnoDad impegnoDad,SettoreDad settoreDad, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
		this.decodificaDad = decodificaDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.settoreDad = settoreDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	protected  void inviaOrdineNSO (TestataOrdine testataOrdine, List<Destinatario> destinatariDaInviare, TipoOrdineNSO tipoOrdineNSO) {
		final String methodName = "inviaOrdineNSO";
		final UUID enteId= testataOrdine.getSettore().getEnte().getId();
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final String nsoUnicoDestinatario = handler.getParams().get("NSO_UNICO_DESTINATARIO");
		log.info(methodName, "NSO_UNICO_DESTINATARIO "+nsoUnicoDestinatario);

		// recupero indirizzo principale settore emittente dell'ordine
		final List<SettoreIndirizzo> settoreIndirizzos =  settoreDad.postRicercaSettoreIndirizzi(testataOrdine.getSettore());
		final SettoreIndirizzo indirizzoPrincipaleSettoreEmittente = settoreIndirizzos.stream().filter(x -> x.getPrincipale()).findAny().orElse(null);

		if (!nsoUnicoDestinatario.equalsIgnoreCase("TRUE")) {

			final List<UUID> uuidDestinatariDaInviare = destinatariDaInviare.stream().map(x -> x.getId()).distinct().collect(Collectors.toList());
			final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatarios(uuidDestinatariDaInviare);
			checkCondition(!rigaOrdines.isEmpty(), MsgCpassOrd.ORDORDP0124.getError());
			final RigheOrdineConTotali righeOrdineConTotali = computeRigheOrdineConTotali(rigaOrdines);

			final EsitoInvioDocumento esitoInvioDocumento = invokeExternalService(handler,() -> handler.getInstance().invioDocumentoNSO(
					tipoOrdineNSO, handler.getParams(), testataOrdine, null, righeOrdineConTotali,indirizzoPrincipaleSettoreEmittente, this));
			log.info(methodName, "urnDocumento: " + esitoInvioDocumento.getUrnDocumento());

			DestinatarioInvioNso destinatarioInvioNso = new DestinatarioInvioNso();
			destinatarioInvioNso.setTestataOrdine(testataOrdine);
			destinatarioInvioNso.setProgressivoInvio(this.getMaxProgressivoInvio(null, testataOrdine.getId())+1);
			destinatarioInvioNso.setUrn(esitoInvioDocumento.getUrnDocumento());
			destinatarioInvioNso = salvaInvioNso(destinatarioInvioNso, esitoInvioDocumento, tipoOrdineNSO);

			if(!StringUtils.isEmpty(esitoInvioDocumento.getUrnDocumento())) {
				aggiornaStatoNsoDestinatarios(destinatariDaInviare, ConstantsCPassOrdStatoNso.StatoEnum.INVIATO.getCostante());
				response.setEsitoInvio(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante());
			}else {
				if (!tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA)) {
					aggiornaStatoNsoDestinatarios(destinatariDaInviare, ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante());
				}
				response.setEsitoInvio(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante());
			}

		}else {

			for (final Destinatario destinatarioOrdine : destinatariDaInviare) {

				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatarioOrdine.getId());
				final RigheOrdineConTotali righeOrdineConTotali = computeRigheOrdineConTotali(rigaOrdines);

				// richiama servizio Invio Documento
				final EsitoInvioDocumento esitoInvioDocumento = invokeExternalService(handler,() -> handler.getInstance().invioDocumentoNSO(
						tipoOrdineNSO, handler.getParams(), testataOrdine, destinatarioOrdine, righeOrdineConTotali,indirizzoPrincipaleSettoreEmittente, this));
				log.info(methodName, "urnDocumento: " + esitoInvioDocumento.getUrnDocumento());

				// Aggiornamento tabelle post elaborazione asincrona
				//CPASS_T_ORD_DESTINATARIO_INVIO_NSO
				DestinatarioInvioNso destinatarioInvioNso = new DestinatarioInvioNso();
				destinatarioInvioNso.setDestinatario(destinatarioOrdine);
				destinatarioInvioNso.setProgressivoInvio(this.getMaxProgressivoInvio(destinatarioOrdine.getId(), testataOrdine.getId())+1);
				destinatarioInvioNso.setUrn(esitoInvioDocumento.getUrnDocumento());
				destinatarioInvioNso = salvaInvioNso(destinatarioInvioNso,esitoInvioDocumento,tipoOrdineNSO);

				//CPASS_T_ORD_DESTINATARIO
				if (!StringUtils.isEmpty(esitoInvioDocumento.getUrnDocumento())) {
					aggiornaStatoNsoDestinatario(destinatarioOrdine, ConstantsCPassOrdStatoNso.StatoEnum.INVIATO.getCostante());
					response.setEsitoInvio(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante());
				}else {
					if (!tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA)) {
						aggiornaStatoNsoDestinatario(destinatarioOrdine, ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante());
					}
					response.setEsitoInvio(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante());
				}
				log.info(methodName,"Stato Nso Destinatario "+destinatarioOrdine.getId()+":"+ destinatarioOrdine.getStatoNso().getCodice());
			}
		}
		//		response.setTestataOrdine(testataOrdine);
	}

	private RigheOrdineConTotali computeRigheOrdineConTotali (List<RigaOrdine> rigaOrdines) {
		final RigheOrdineConTotali righeOrdineConTotali = new RigheOrdineConTotali();
		righeOrdineConTotali.setRigaOrdines(rigaOrdines);

		for (final RigaOrdine rigaOrdine : rigaOrdines) {
			righeOrdineConTotali.taxAmount = righeOrdineConTotali.taxAmount.add(rigaOrdine.getImportoIva());
			righeOrdineConTotali.lineExtensionAmount = righeOrdineConTotali.lineExtensionAmount.add(rigaOrdine.getImportoNetto());
			righeOrdineConTotali.taxExclusiveAmount = righeOrdineConTotali.taxExclusiveAmount.add(rigaOrdine.getImportoNetto());
			righeOrdineConTotali.taxInclusiveAmount = righeOrdineConTotali.taxInclusiveAmount.add(rigaOrdine.getImportoTotale());
			righeOrdineConTotali.payableAmount = righeOrdineConTotali.payableAmount.add(rigaOrdine.getImportoTotale());
		}
		righeOrdineConTotali.taxAmount = righeOrdineConTotali.taxInclusiveAmount.subtract(righeOrdineConTotali.taxExclusiveAmount);

		return righeOrdineConTotali;

	}

	private DestinatarioInvioNso salvaInvioNso(DestinatarioInvioNso destinatarioInvioNso, EsitoInvioDocumento esitoInvioDocumento, TipoOrdineNSO tipoOrdineNSO) {
		log.info("salvaInvioNso", "BEGIN");
		// DESTINATARIO_INVIO_NSO
		destinatarioInvioNso.setEsitoInvioErroreCodice(esitoInvioDocumento.getCodErrore());
		destinatarioInvioNso.setCbcId(esitoInvioDocumento.getDocumento().getOrderId());
		destinatarioInvioNso.setOrderDocumentReferenceId(esitoInvioDocumento.getDocumento().getOrderDocumentReferenceId());
		destinatarioInvioNso.setUtenteInvio(CpassThreadLocalContainer.UTENTE_CONNESSO.get());
		destinatarioInvioNso.setUtenteCreazione(CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString());
		destinatarioInvioNso.setUtenteModifica(CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString());
		destinatarioInvioNso.setDataCreazione(new Date());
		destinatarioInvioNso.setDataModifica(new Date());
		if (!StringUtils.isEmpty(esitoInvioDocumento.getUrnDocumento())) {
			destinatarioInvioNso.setEsitoInvio("OK");
		}else {
			destinatarioInvioNso.setEsitoInvio("KO");
			destinatarioInvioNso.setEsitoInvioErroreCodice(esitoInvioDocumento.getCodErrore());
		}
		destinatarioInvioNso.setEsitoInvioErroreDescrizione(esitoInvioDocumento.getDescErrore());// SE ESITO RESTITUITO DA SERVIZIO WARN la descrizione è valorizzata
		destinatarioInvioNso = destinatarioOrdineDad.saveDestinatarioInvioNso(destinatarioInvioNso);
		log.info("salvaInvioNso", "DestinatarioInvioNso ID "+destinatarioInvioNso.getId());

		//CPASS_T_ORD_DESTINATARIO_INVIO_NSO_XML
		DestinatarioInvioNsoXml destinatarioInvioNsoXml = new DestinatarioInvioNsoXml();
		destinatarioInvioNsoXml.setFileXml(esitoInvioDocumento.getDocumento().getXml());
		destinatarioInvioNsoXml.setMetadatiXml(esitoInvioDocumento.getDocumento().getMetadati());
		destinatarioInvioNsoXml.setTipodoc(tipoOrdineNSO.getDescrizione());
		destinatarioInvioNsoXml.setDestinatarioInvioNso(destinatarioInvioNso);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		destinatarioInvioNsoXml.setDataConsegna(sdf.format(new Date()));
		destinatarioInvioNsoXml = destinatarioOrdineDad.insertDestinatarioInvioNsoXml(destinatarioInvioNsoXml);

		log.info("salvaInvioNso", "destinatarioInvioNsoXml ID "+destinatarioInvioNsoXml.getId());
		log.info("salvaInvioNso", "END");

		return destinatarioInvioNso;
	}

	private void aggiornaStatoNsoDestinatarios(List<Destinatario> destinatarios, String statoNso) {
		log.info("aggiornaStatoNsoDestinatarios","BEGIN");
		for (final Destinatario destinatario : destinatarios) {
			aggiornaStatoNsoDestinatario(destinatario, statoNso);
		}
		log.info("aggiornaStatoNsoDestinatarios","END");
	}
	private void aggiornaStatoNsoDestinatario(Destinatario destinatario, String statoNso) {
		log.info("aggiornaStatoNsoDestinatario","BEGIN");
		destinatario.setStatoNso(isEntityPresent(() -> decodificaDad.getStatoNsoOpt(statoNso, ConstantsCPassOrdStatoNso.TipoEnum.ORDINE.getCostante()),"statoNSO"));
		destinatario.setDataInvioNso(new Date());
		destinatario.setDataModifica(new Date());
		destinatario.setUtenteModifica(CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString());
		destinatarioOrdineDad.updateDestinatario(destinatario);
		log.info("aggiornaStatoNsoDestinatario","END");
	}

	@Override
	public int getMaxProgressivoInvio(UUID idDestinatario, UUID idTestataOrdine) {
		Optional<DestinatarioInvioNso> invioNsoOpt = Optional.empty();
		if (idDestinatario != null) {
			invioNsoOpt = destinatarioOrdineDad.getUltimoInvioNsoByDestinatario(idDestinatario);
		}else {
			invioNsoOpt = destinatarioOrdineDad.getUltimoInvioNsoByOrdine(idTestataOrdine);
		}
		if (invioNsoOpt.isPresent()) {
			return invioNsoOpt.get().getProgressivoInvio();
		} else {
			return 0;
		}
	}

	/*public static <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier,
			List<ApiError> apiErrors) {
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		if (!externalResponse.isSuccess()) {
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		}
		return externalResponse.getResponse();
	}*/

}
