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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProvvedimentoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaProvvedimentoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaProvvedimentoResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.EsitoProvvedimento;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.external.ProvvedimentoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;


/**
 * Retrieves an Provvedimentos
 */
public class PostRicercaProvvedimentoService extends BaseService<PostRicercaProvvedimentoRequest, PostRicercaProvvedimentoResponse> {
	private final ExternalHelperLookup externalHelperLookup;
	private final ProvvedimentoDad provvedimentoDad;
	private final SettoreDad settoreDad;
	private Provvedimento provvedimento;

	@Override
	protected void checkServiceParams() {
		provvedimento = request.getProvvedimento();
		checkNotNull(provvedimento.getAnno(), "anno", Boolean.TRUE);
		checkNotNull(provvedimento.getNumero(), "numero", Boolean.TRUE);
		checkNotNull(provvedimento.getProvvedimentoTipo(), "tipo provvedimento", Boolean.TRUE);
		checkNotNull(provvedimento.getProvvedimentoTipo().getId(), "tipo provvedimento", Boolean.TRUE);
	}
	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaProvvedimentoService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, ProvvedimentoDad provvedimentoDad, SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.provvedimentoDad     = provvedimentoDad;
		this.settoreDad           = settoreDad;
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final ExternalServiceResolveWrapper<ProvvedimentoHelper> handler = externalHelperLookup.lookup(ProvvedimentoHelper.class ,  ente.getId());
		List<Provvedimento> provvedimenti = new ArrayList<>();
		EsitoProvvedimento ep = new EsitoProvvedimento();
		try {
			//1 versione
			//ep = invokeExternalService(handler, () -> handler.getInstance().getProvvedimenti(handler.getParams(), request.getProvvedimento()));
			//2 versione
			//ep = invokeExternalService(handler, () -> handler.getInstance().getDocumentiProvvedimenti(handler.getParams(), request.getProvvedimento(),ente.getCodice()));
			/*
				<codApplicazione>UNICA</codApplicazione>
				<istanzaApplicazione>TEST</istanzaApplicazione>
				<userName>UNICA-USER</userName>
				<password>Stilo2022!</password>
				<xml><![CDATA[<?xml version="1.0" encoding="UTF-8" standalone="yes"?><RequestGetAttoPerAcquisti><SiglaRegistro>DD</SiglaRegistro><Anno>2022</Anno><Nro>1879</Nro></RequestGetAttoPerAcquisti>]]></xml>
         		<hash>fOtNFhNgfqRy3lI8/sKAZdL0deM=</hash>
			 */
			//3 versione
			ep = invokeExternalService(handler, () -> handler.getInstance().getAttiPerAcquisti(handler.getParams(), request.getProvvedimento(),ente.getCodice()));
		}catch(final Exception e){
			log.error("getProvvedimenti", "Problemi nell'invocazione del servizio esposto da stilo verificare se il servizio esterno e' attivo ",e );
		}
		if(ep.getCodErrore()!=null) {
			log.error("getProvvedimenti", "errore code --> " + ep.getCodErrore() );
			log.error("getProvvedimenti", "errore desc --> " + (ep.getDescErrore()!=null? ep.getDescErrore() : "") );
		}

		if (ep.getLista() == null || ep.getLista().isEmpty()) {
			provvedimenti = estraiProvvedimentoDaStorico(provvedimento.getAnno(), provvedimento.getNumero(), provvedimento.getProvvedimentoTipo().getId(),ente.getId());

			for(final Provvedimento prov :provvedimenti) {

				if(prov.getCdc()!= null && prov.getCdc().getId()!=null) {
					final Optional<Cdc> cdc = settoreDad.findCdcById(prov.getCdc().getId());
					if(cdc.isPresent()) {
						final Settore sett = new Settore();
						sett.setCodice(cdc.get().getCodice());
						sett.setIsCdc(Boolean.TRUE);
						prov.setSettore(sett);
					}
				}

			}
		}else {
			provvedimenti = ep.getLista();
			for(final Provvedimento prov :provvedimenti) {
				final String codiceSettore = prov.getSettore().getCodice();
				if(!StringUtils.isEmpty(codiceSettore)) {
					final Optional<Settore> settore = settoreDad.findByCodice(codiceSettore, ente.getId(), true, "equals");
					if(settore.isPresent()) {
						prov.setSettore(settore.get());
					}else {
						final Settore sett = new Settore();
						sett.setCodice(codiceSettore);
						sett.setIsCdc(Boolean.TRUE);
						prov.setSettore(sett);
					}
				}
			}
		}

		final List<ApiError> apiErrors = new ArrayList<>();
		if(provvedimenti != null && provvedimenti.size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0005.getError());
		}
		response.addApiErrors(apiErrors);
		response.setProvvedimenti(provvedimenti);
	}
	/**
	 *
	 * @param anno
	 * @param numero
	 * @param tipoId
	 * @return List<Provvedimento>
	 */
	private List<Provvedimento> estraiProvvedimentoDaStorico(Integer anno, String numero, Integer tipoId,UUID enteId) {
		final Provvedimento pProvvedimento = request.getProvvedimento();
		return provvedimentoDad.getProvvedimentoByAnnoNumeroTipo( pProvvedimento.getAnno(),  pProvvedimento.getNumero(),  pProvvedimento.getProvvedimentoTipo().getId(), enteId);
	}
}
