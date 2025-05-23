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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineAutorizzaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineAutorizzaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.TipoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.DocumentaleHelper;
import it.csi.cpass.cpassbe.lib.external.dto.GestoreDocumentoActa;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutOrdineAutorizzaByIdService extends BaseDocumentaleService<PutOrdineAutorizzaByIdRequest, PutOrdineAutorizzaByIdResponse> {

	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	//private final SystemDad systemDad;
	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param rigaOrdineDad
	 */
	public PutOrdineAutorizzaByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, RigaOrdineDad rigaOrdineDad,
			DecodificaDad decodificaDad,SettoreDad settoreDad,DocumentaleDad documentaleDad, CommonDad commonDad,StampeDad stampeDad,UtenteDad utenteDad,FornitoreDad fornitoreDad,
			SystemDad systemDad,ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper, testataOrdineDad, documentaleDad, settoreDad,commonDad,stampeDad,systemDad);
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
		//this.systemDad = systemDad;
		this.externalHelperLookup 	= externalHelperLookup;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());
		final Parametro paramSysDocument = systemDad.getParametro(ChiaveEnum.SISTEMA_DOCUMENTALE.name(), ente.getId());
		final boolean noProtocollo = (paramSysDocument != null && paramSysDocument.getValore().equals("NO PROTOCOLLO"));
		String tipDoc = testataOrdine.getTipoOrdine().getTipologiaDocumentoCodice() == null ? "" : testataOrdine.getTipoOrdine().getTipologiaDocumentoCodice();
		if (
				(
				   (!TipoOrdineEnum.INTERNO.getCodice().equals(tipDoc)) 
				&& (!TipoOrdineEnum.MEPA.getCodice().equals(tipDoc)) 
				)
			&& !noProtocollo) {
			
			final ExternalServiceResolveWrapper<DocumentaleHelper> handler = externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());
			final Long idAOO = getAooBySettore(settoreCorrente.getId(),ente.getId());

			testataOrdine.getSettore().getFirma();
			if(StatoOrdineEnum.IN_FIRMA.getCostante().equals(testataOrdine.getStato().getCodice())) {
				//if ("DWD".equalsIgnoreCase(firma)) {
				// VERIFICA PROTOCOLLAZIONE SU ACTA
				final String uuiProtocolloOrdine = testataOrdine.getProtocolloOrdines()!=null && !testataOrdine.getProtocolloOrdines().isEmpty() ?
						testataOrdine.getProtocolloOrdines().get(0).getUuidDocumentoOrdine() : null;
						final GestoreDocumentoActa gestoreDocumentoActa = invokeExternalService(handler, () -> handler.getInstance().verificaDocumentoProtocollato(handler.getParams(),idAOO,uuiProtocolloOrdine,settoreCorrente.getCodice()));
						if (gestoreDocumentoActa!=null &&  !StringUtils.isBlank(gestoreDocumentoActa.getUuidRegProtocolloOrdine())){
							testataOrdine.getProtocolloOrdines().get(0).setUuidRegProtocolloOrdine(gestoreDocumentoActa.getUuidRegProtocolloOrdine());
							testataOrdine.getProtocolloOrdines().get(0).setAnnoProtocollo(Integer.parseInt(gestoreDocumentoActa.getAnnoProtocollo()));
							testataOrdine.getProtocolloOrdines().get(0).setNumeroProtocollo(gestoreDocumentoActa.getNumeroProtocollo());
							testataOrdine.getProtocolloOrdines().get(0).setAoo(String.valueOf(idAOO));
							testataOrdine.getProtocolloOrdines().get(0).setDescrizioneProtocollo(gestoreDocumentoActa.getDescrizioneProtocollo());
							testataOrdine.getProtocolloOrdines().get(0).setDataProtocollo(DateUtility.stringToDate(gestoreDocumentoActa.getDataProtocollo()));
						}
			}
		}

		// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
		testataOrdine.setTotaleConIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleConIva()));
		testataOrdine.setTotaleNoIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleNoIva()));
		testataOrdine.setDataAutorizzazione(new Date(System.currentTimeMillis()));
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.AUTORIZZATO.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()),"stato"));
		testataOrdineDad.updateTestataOrdine(testataOrdine, false);

		if (testataOrdine.getListDestinatario() != null) {
			for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {
				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (final RigaOrdine rigaOrdine : rigaOrdines) {
						// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
						rigaOrdine.setImportoIva(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoIva()));
						rigaOrdine.setImportoNetto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoNetto()));
						rigaOrdine.setImportoTotale(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale()));
						rigaOrdine.setImportoSconto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto()));
						rigaOrdine.setImportoSconto2(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto2()));
						rigaOrdineDad.updateRigaOrdine(rigaOrdine);
						rigaOrdineDad.saveOdsDatiContabili(rigaOrdine);
					}
				}
			}
		}

		testataOrdineDad.deleteAssociati(testataOrdine);
		response.setTestataOrdine(testataOrdine);
	}


}
