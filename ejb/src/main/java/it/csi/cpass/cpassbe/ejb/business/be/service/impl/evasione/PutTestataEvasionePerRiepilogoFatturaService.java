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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutTestataEvasionePerRiepilogoFatturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutTestataEvasionePerRiepilogoFatturaResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class PutTestataEvasionePerRiepilogoFatturaService extends BaseService<PutTestataEvasionePerRiepilogoFatturaRequest, PutTestataEvasionePerRiepilogoFatturaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final TestataEvasioneDad testataEvasioneDad;
	private final SystemDad systemDad;
	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 */
	public PutTestataEvasionePerRiepilogoFatturaService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataEvasioneDad testataEvasioneDad,
			SystemDad systemDad, UtenteDad utenteDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.testataEvasioneDad = testataEvasioneDad;
		this.systemDad = systemDad;
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		log.info("checkServiceParams", "Strart");
		checkNotNull(request.getTestataEvasione(), "testataEvasione", true);
		checkNotNull(request.getTestataEvasione().getFornitore(), "fornitore", true);
		checkNotNull(request.getTestataEvasione().getFatturaAnno(), "fatturaAnno", true);
		checkNotNull(request.getTestataEvasione().getFatturaNumero(), "fatturaNumero", true);
		checkNotNull(request.getTestataEvasione().getFatturaCodice(), "fatturaCodice", true);
		checkNotNull(request.getTestataEvasione().getFatturaTipo(), "fatturaTipo", true);
		checkNotNull(request.getTestataEvasione().getFatturaTotale(), "fatturaTotale", true);
		checkNotNull(request.getTestataEvasione().getFatturaTotaleLiquidabile(), "fatturaTotaleLiquidabile", true);
		log.info("checkServiceParams", "Stop");
	}

	@Override
	protected void execute() {
		log.info("execute", "Strart");
		TestataEvasione testataEvasione = request.getTestataEvasione();

		BigDecimal totaleLiquidabile = testataEvasione.getFatturaTotaleLiquidabile();
		BigDecimal totaleEvaso = testataEvasione.getTotaleConIva();
		String nomeParametro = ChiaveEnum.TOLLERANZA_EVASIONE.name();
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());
		
		if(!totaleLiquidabile.equals(totaleEvaso)) {
			Parametro paramTolleranza = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(),
					settoreUtente.get(0).getEnte().getId());

			checkBusinessCondition(paramTolleranza != null, MsgCpassOrd.ORDORDE0093.getError());
			BigDecimal tolleranzaEvasione = BigDecimal.valueOf(Double.parseDouble(paramTolleranza.getValore()));
			
			// Metto i controlli bloccanti prima del controllo del bypass utente. In modo da bloccare l'operazione sempre se queste condizioni si verificano, anche 
			// se l'utente passa il parametro di bypass 
			checkBusinessCondition(totaleLiquidabile.subtract(totaleEvaso).compareTo(BigDecimal.ZERO) < 0, MsgCpassOrd.ORDORDE0101.getError());
			checkBusinessCondition(totaleLiquidabile.subtract(totaleEvaso).compareTo(tolleranzaEvasione) > 0, MsgCpassOrd.ORDORDE0102.getError());
			
			// se il parametro di bypassControl è nullo o false procedo col controllo sulla tolleranza, altrimento lo salto
			if(request.isBypassControl() == null || request.isBypassControl() == false) {
				
				// restituisco il warning all'utente che dovrà confermare l'operazione
				checkBusinessCondition(totaleLiquidabile.subtract(totaleEvaso).compareTo(BigDecimal.ZERO) > 0 &&
						totaleLiquidabile.subtract(totaleEvaso).compareTo(tolleranzaEvasione) <= 0, MsgCpassOrd.ORDORDA0103.getError());
			}
		}

		// controlli congruenza fornitore
		TestataEvasione oldTestata = testataEvasioneDad.getTestataEvasioneModel(testataEvasione.getId());
		Fornitore oldFornitore = oldTestata.getFornitore();
		
		// se i due fornitori hanno codice diverso proseguo col controllo, se lo hanno uguale lo salto
		if(!testataEvasione.getFatturaCodice().equals(oldFornitore.getCodice())) {
			
			String nParamVerifica = ChiaveEnum.VERIFICA_STORICO_FORNITORI.name();
			Parametro verificaStorico = systemDad.getParametro(nParamVerifica, ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(),
					settoreUtente.get(0).getEnte().getId());
			
			// Se l'utente non richiede il salvataggio bypassando il controllo di congruenza sui fornitori, procede coi controlli
			if (request.isBypassFornitoreControl() == null || request.isBypassFornitoreControl() == false){
				
				checkBusinessCondition(verificaStorico == null || verificaStorico.getValore().equals("NO"), MsgCpassOrd.ORDORDA0098.getError());
				
				//controllo sulla congruenza fornitori
				PagedList<Fornitore> fornitoriSiac = getFornitoriSiac(testataEvasione.getFornitore());
				
				boolean foundSoggetto = false;
				String soggettoFattura = testataEvasione.getFatturaCodice();
				
				if(fornitoriSiac != null && fornitoriSiac.getList() != null && fornitoriSiac.getList().size() > 0) {
					
					for(Fornitore siacFornitore : fornitoriSiac.getList()) {
						
						if(siacFornitore.getCodiciFornitoriCollegatiSuccessivi() != null) {
							
							for(String codice : siacFornitore.getCodiciFornitoriCollegatiSuccessivi()) {
								
								if(soggettoFattura.equals(codice)) {
									foundSoggetto = true;
									break;
								}
								
							}
							
						}
						
						if(foundSoggetto == true) {
							break;
						}
						
					}
					
				}
				
				checkBusinessCondition(foundSoggetto == true, MsgCpassOrd.ORDORDA0098.getError());
			}
		}
		
		testataEvasione = testataEvasioneDad.updateTestataEvasione(testataEvasione);
		response.setTestataEvasione(testataEvasione);
	}
	
	protected PagedList<Fornitore> getFornitoriSiac(Fornitore pFornitore) {
		
		FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
		
		// fix - filtro solo per alcuni campi
		Fornitore fornitoreFilter = new Fornitore();
		fornitoreFilter.setCodice(pFornitore.getCodice());
		fornitoreFilter.setCodiceFiscale(pFornitore.getCodiceFiscale());
		fornitoreFilter.setPartitaIva(pFornitore.getPartitaIva());
		filtroFornitore.setFornitore(fornitoreFilter);
		
		ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class);
		List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));

		for (Fornitore fornitore : fornitori) {
			String indirizzoCompleto = "";
			if (fornitore.getSedime() != null) {
				indirizzoCompleto += fornitore.getSedime() + " ";
			}
			if (fornitore.getIndirizzo() != null) {
				indirizzoCompleto += fornitore.getIndirizzo() + " ";
			}
			if (fornitore.getNumeroCivico() != null) {
				indirizzoCompleto += fornitore.getNumeroCivico();
			}
			fornitore.setIndirizzo(indirizzoCompleto.trim());
		}

		return new PagedListImpl<>(fornitori);
	}
	
	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();

		checkBusinessCondition(externalResponse.isSuccess(),
				() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));

		List<E> fornitori = (List<E>) externalResponse.getResponse();
		if (fornitori.size() == 0) {
//			final String errori;
//			if (externalResponse.getMessages() != null && externalResponse.getMessages().size() > 0) {
//				errori = externalResponse.getMessages().stream().collect(Collectors.joining(", "));
//			}
			checkBusinessCondition(false, () -> MsgCpassOrd.ORDORDE0074.getError());
		}

		return externalResponse.getResponse();
	}


}
