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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
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
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
		checkNotNull(request.getTestataEvasione(), "testataEvasione", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFornitore(), "fornitore", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaAnno(), "fatturaAnno", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaNumero(), "fatturaNumero", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaCodiceFornitore(), "fatturaCodice", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaTipo(), "fatturaTipo", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaTotale(), "fatturaTotale", Boolean.TRUE);
		checkNotNull(request.getTestataEvasione().getFatturaTotaleLiquidabile(), "fatturaTotaleLiquidabile", Boolean.TRUE);
		log.info("checkServiceParams", "Stop");
	}

	@Override
	protected void execute() {
		log.info("execute", "Strart");
		TestataEvasione testataEvasione = request.getTestataEvasione();
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final BigDecimal totaleLiquidabile = testataEvasione.getFatturaTotaleLiquidabile();
		final BigDecimal totaleEvaso = testataEvasione.getTotaleConIva();
		final String nomeParametro = ChiaveEnum.TOLLERANZA_EVASIONE.name();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());

		if(!totaleLiquidabile.equals(totaleEvaso)) {
			final Parametro paramTolleranza = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(),settoreUtente.get(0).getEnte().getId());

			final BigDecimal tolleranzaEvasione = BigDecimal.valueOf(Double.parseDouble(paramTolleranza.getValore()));

			// Metto i controlli bloccanti prima del controllo del bypass utente. In modo da bloccare l'operazione sempre se queste condizioni si verificano, anche
			// se l'utente passa il parametro di bypass

			final int liquidabileMaggioreEvaso = totaleLiquidabile.subtract(totaleEvaso).compareTo(BigDecimal.ZERO);
			checkBusinessCondition(liquidabileMaggioreEvaso >= 0, MsgCpassOrd.ORDORDE0101.getError());

			if((request.isBypassControl() == null || !request.isBypassControl()) && liquidabileMaggioreEvaso>0) {
				// warning: superato il limite di tolleranza , l'utente dovrà confermare l'operazione
				checkBusinessCondition(totaleLiquidabile.subtract(totaleEvaso).compareTo(tolleranzaEvasione) <= 0, MsgCpassOrd.ORDORDA0142.getError());
				// warning:NON superato il limite di tolleranza , l'utente dovrà confermare l'operazione
				checkBusinessCondition(totaleLiquidabile.subtract(totaleEvaso).compareTo(tolleranzaEvasione) > 0, MsgCpassOrd.ORDORDA0103.getError());
			}
		}

		// controlli congruenza fornitore
		final TestataEvasione oldTestata = testataEvasioneDad.getTestataEvasioneModel(testataEvasione.getId());
		// fornitore evasione
		final Fornitore soggettoEvasione = oldTestata.getFornitore();

		// fornitore inserito nel form
		final String soggettoFatturaCod = testataEvasione.getFatturaCodiceFornitore();

		// se i due fornitori hanno codice diverso proseguo col controllo, se lo hanno uguale lo salto
		if(!soggettoEvasione.getCodice().equals(soggettoFatturaCod)) {

			final String nParamVerifica = ChiaveEnum.VERIFICA_STORICO_FORNITORI.name();
			final Parametro verificaStorico = systemDad.getParametro(nParamVerifica, ConstantsCPassParametro.RiferimentoEnum.DOCUMENTO_SPESA.getCostante(),settoreUtente.get(0).getEnte().getId());

			// Se l'utente non richiede il salvataggio bypassando il controllo di congruenza sui fornitori, procede coi controlli
			if (request.isBypassFornitoreControl() == null || !request.isBypassFornitoreControl()){
				checkBusinessCondition(verificaStorico != null && verificaStorico.getValore().equals("SI"), MsgCpassOrd.ORDORDA0098.getError());
				//controllo sulla congruenza fornitori
				boolean foundSoggetto = false;
				final PagedList<Fornitore> fornitoriSiac = getFornitoriSiac(soggettoEvasione,enteId);

				if(fornitoriSiac != null && fornitoriSiac.getList() != null && fornitoriSiac.getList().size() > 0) {
					for(final Fornitore siacFornitore : fornitoriSiac.getList()) {
						if(siacFornitore.getCodiciFornitoriCollegatiSuccessivi() != null) {
							for(final String codice : siacFornitore.getCodiciFornitoriCollegatiSuccessivi()) {
								if(soggettoFatturaCod.equals(codice)) {
									foundSoggetto = Boolean.TRUE;
									break;
								}
							}
						}
						if(foundSoggetto) {
							break;
						}
					}
				}
				checkBusinessCondition(foundSoggetto, MsgCpassOrd.ORDORDA0098.getError());
			}
		}

		testataEvasione = testataEvasioneDad.updateTestataEvasione(testataEvasione);
		response.setTestataEvasione(testataEvasione);
	}

	protected PagedList<Fornitore> getFornitoriSiac(Fornitore pFornitore,UUID enteId) {

		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		// fix - filtro solo per alcuni campi
		final Fornitore fornitoreFilter = new Fornitore();
		fornitoreFilter.setCodice(pFornitore.getCodice());
		fornitoreFilter.setCodiceFiscale(pFornitore.getCodiceFiscale());
		fornitoreFilter.setPartitaIva(pFornitore.getPartitaIva());
		filtroFornitore.setFornitore(fornitoreFilter);

		final ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
		final List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));
		/*
		for (Fornitore fornitore : fornitori) {
			String indirizzoConSedime = "";
			if (fornitore.getSedime() != null) {
				indirizzoConSedime += fornitore.getSedime() + " ";
			}
			if (fornitore.getIndirizzo() != null) {
				indirizzoConSedime += fornitore.getIndirizzo() + " ";
			}
			fornitore.setIndirizzo(indirizzoConSedime.trim());
		}
		 */
		return new PagedListImpl<>(fornitori);
	}

	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		checkBusinessCondition(externalResponse.isSuccess(),() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		final List<E> fornitori = (List<E>) externalResponse.getResponse();
		if (fornitori.size() == 0) {
			checkBusinessCondition(false, () -> MsgCpassOrd.ORDORDE0074.getError());
		}
		return externalResponse.getResponse();
	}
}
