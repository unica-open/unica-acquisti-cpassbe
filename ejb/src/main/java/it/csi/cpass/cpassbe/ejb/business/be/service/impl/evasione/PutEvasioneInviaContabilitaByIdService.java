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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneInviaContabilitaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneInviaContabilitaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.InvioQuoteDocumento;
import it.csi.cpass.cpassbe.lib.dto.ModalitaPagamento;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.error.MsgTypeEnum;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.QuotaItem;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.SospensioneItem;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.InvioQuoteDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutEvasioneInviaContabilitaByIdService extends BaseTestataEvasioneService<PutEvasioneInviaContabilitaByIdRequest, PutEvasioneInviaContabilitaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	private final DecodificaDad decodificaDad;
	private final ElaborazioneDad elaborazioneDad;
	private final SystemDad systemDad;
	private final ElaborazioneTipoDad elaborazioneTipoDad;

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	private final RigaOrdineDad rigaOrdineDad;

	private String modalitaPagamentoSoggetto = null;
	private boolean bDocumentoSpesaPrimaOccorrenza = Boolean.TRUE;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ITALY);

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutEvasioneInviaContabilitaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad,
			ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad, DecodificaDad decodificaDad, ElaborazioneDad elaborazioneDad,
			SystemDad systemDad, ElaborazioneTipoDad elaborazioneTipoDad, RigaOrdineDad rigaOrdineDad) {
		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;

		this.destinatarioEvasioneDad 	= destinatarioEvasioneDad;
		this.rigaEvasioneDad 			= rigaEvasioneDad;
		this.impegnoEvasioneDad 		= impegnoEvasioneDad;
		this.subimpegnoEvasioneDad 		= subimpegnoEvasioneDad;
		this.decodificaDad 				= decodificaDad;
		this.elaborazioneDad 			= elaborazioneDad;
		this.systemDad 					= systemDad;
		this.elaborazioneTipoDad 		= elaborazioneTipoDad;
		this. rigaOrdineDad 			= rigaOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "idEvasione");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		List<ApiError> apiErrors = new ArrayList<>();

		final TestataEvasione tempEvasione = testataEvasioneDad.getTestataEvasione(request.getId()).orElseThrow(() -> new NotFoundException("testata evasione"));

		// List<TestataEvasione> evasioni = testataEvasioneDad.findEvasioniByFattura(tempEvasione.getFatturaAnno(), tempEvasione.getFatturaNumero(), tempEvasione.getFatturaTipo(), tempEvasione.getFatturaCodice());
		// Gli oggetti TestataEvasione sono completi dei relativi destinatari
		final List<TestataEvasione> evasioni = testataEvasioneDad.findEvasioniByFatturaModel(tempEvasione.getFatturaAnno(), tempEvasione.getFatturaNumero(), tempEvasione.getFatturaTipo(), tempEvasione.getFatturaCodiceFornitore());

		boolean nonAutoBozzaOrConfermata = false;
		for(final TestataEvasione tEv : evasioni) {
			if(tEv.getStato().getCodice().equals(StatoEvasioneEnum.BOZZA.getCostante()) ||
					tEv.getStato().getCodice().equals(StatoEvasioneEnum.CONFERMATA.getCostante())) {
				nonAutoBozzaOrConfermata = Boolean.TRUE;
				break;
			}
		}
		checkBusinessCondition(!nonAutoBozzaOrConfermata, MsgCpassOrd.ORDORDE0122.getError());

		if(request.getBypassControls() == null || !request.getBypassControls()) {
			boolean thereAreAuto = false;
			for(final TestataEvasione tEv : evasioni) {
				if( !tEv.getId().equals(request.getId()) &&
						tEv.getStato().getCodice().equals(StatoEvasioneEnum.AUTORIZZATA.getCostante())) {
					thereAreAuto = true;
					break;
				}
			}
			final ApiError warning = new ApiError();
			warning.setType(MsgTypeEnum.WARNING.getCostante());
			warning.setCode(MsgTypeEnum.WARNING.getCostante());
			checkBusinessCondition(!thereAreAuto, warning);
		}

		// CPASS-433
		apiErrors = UtilityVerificheEvasione.checkFatturaCollegata(evasioni, externalHelperLookup, systemDad,ente.getId());
		if (!apiErrors.isEmpty()) {
			response.setApiErrors(apiErrors);
			return;
		}

		//2.8.3 - Verifica congruenza totali
		//		Una volta individuate tutte le evasioni di cui al paragrafo 2.8.2, si sommano i valori di CPASS_T_ORD_TESTATA_EVASIONE.totale_con_iva: il totale risultante lo chiamiamo TOTALE_EVASO.
		//		Si legge il valore di CPASS_T_ORD_TESTATA_EVASIONE.fattura_totale_liquidabile (su una qualsiasi delle evasioni, il valore di quel campo è uguale su tutte): questo valore lo chiamiamo TOTALE_LIQUIDABILE.
		//		A questo punto, occorre confrontare i due importi così calcolati, TOTALE_EVASO e TOTALE_LIQUIDABILE; se non sono uguali:

		if(request.getSaltaVerificaCongruenzaTotali() == null || !request.getSaltaVerificaCongruenzaTotali()) {
			final BigDecimal totaleEvaso = evasioni.stream().map(x -> x.getTotaleConIva()).reduce(BigDecimal.ZERO, BigDecimal::add);
			final ControllaEvasione controllaEvasione = new ControllaEvasione();
			controllaEvasione.setListIgnoreWarning(new ArrayList<>());
			apiErrors = UtilityVerificheEvasione.checkCongruenza(totaleEvaso, tempEvasione.getFatturaTotaleLiquidabile(), controllaEvasione, systemDad, ente.getId());
			if (!apiErrors.isEmpty()) {
				response.setApiErrors(apiErrors);
				return;
			}
		}

		// Preparazione quote
		final ArrayList<QuotaItem> listQuotaItem = preparazioneQuote(evasioni);

		// Utilizzo la prima evasione in quanto la fattura è comunque comune a tutte le evasioni trovate
		getModalitaPagamentoSoggetto(tempEvasione.getFatturaCodiceFornitore(),ente.getId());


		// invioQuoteDocumento
		final String contenutoDocumento = creazioneXmlPerInvioQuote(tempEvasione.getFatturaCodiceFornitore(), tempEvasione.getFatturaTipo(), tempEvasione.getFatturaAnno(), tempEvasione.getFatturaCodiceExt(), listQuotaItem, ente.getCodice());
		log.info(methodName, "xml --> " + contenutoDocumento);
		// TODO da commentare
		//checkBusinessCondition(false,CoreError.GENERIC_ERROR.getError("error","errore generico per debug dev da commentare "));

		final InvioQuoteDocumento invioQuoteDocumento = new InvioQuoteDocumento();
		invioQuoteDocumento.setCodiceTipoDocumento("DOCUMENTO_SPESA");
		invioQuoteDocumento.setContenutoDocumento(contenutoDocumento);

		final ExternalServiceResolveWrapper<InvioQuoteDocumentoHelper> handler = externalHelperLookup.lookup(InvioQuoteDocumentoHelper.class, ente.getId());
		final String idOperazione = invokeExternalService(handler, () -> handler.getInstance().postInvioQuoteDocumento(handler.getParams(), invioQuoteDocumento));
		log.info(methodName, "idOperazione: " + idOperazione);

		// verifica MODALITA_INVIO_EVASIONE
		final Parametro paramModalitaInvioEvasione = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.MODALITA_INVIO_EVASIONE.getCostante(),ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), ente.getId());
		final String modalitaInvioEvasione = paramModalitaInvioEvasione != null ? paramModalitaInvioEvasione.getValore() : null;
		final boolean invioAsincrono = modalitaInvioEvasione != null && modalitaInvioEvasione.equals(ConstantsCPassParametro.ValoreEnum.MODALITA_INVIO_EVASIONE_ASINCRONA.getCostante());

		for(final TestataEvasione ev : evasioni) {
			if (invioAsincrono) {
				// inserimento in tabella elaborazione
				Elaborazione elaborazione = new Elaborazione();
				elaborazione.setEntitaId(ev.getId().toString());

				final String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
				elaborazione.setUtente(utenteId);

				elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
				elaborazione.setData(new Date(System.currentTimeMillis()));
				elaborazione.setEsito(null);

				final Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.INVIO_EVASIONE.getCostante());
				elaborazione.setElaborazioneTipo(elaborazioneTipo.orElseThrow(() -> new NotFoundException("elaborazione tipo")));

				elaborazione.setIdEsterno(idOperazione);
				elaborazione.setEnte(ente);
				elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
			}
		}
		// verifica SERVIZIO_VERIFICA_EVASIONE
		final Parametro paramServizioVerificaEvasione = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.SERVIZIO_VERIFICA_EVASIONE.getCostante(),ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), ente.getId());
		final String servizioVerificaEvasione = paramServizioVerificaEvasione != null ? paramServizioVerificaEvasione.getValore() : null;
		if (servizioVerificaEvasione != null && servizioVerificaEvasione.equals(ConstantsCPassParametro.ValoreEnum.SERVIZIO_VERIFICA_EVASIONE_FALSE.getCostante())) {

			for(final TestataEvasione ev : evasioni) {

				// per tutti i destinatari collegati
				for (final DestinatarioEvasione destinatarioEvasione : ev.getDestinatarioEvasiones()) {
					// per tutte le righe collegate
					final List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
					for (final RigaEvasione rigaEvasione : righeEvasiones) {

						final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
						if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
							for (final ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {
								impegnoEvasione.setImportoLiquidato(impegnoEvasione.getImportoRipartito());
								impegnoEvasioneDad.update(impegnoEvasione);

								if (impegnoEvasione.getSubimpegnoEvasiones() != null) {
									for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
										subimpegnoEvasione.setImportoLiquidato(subimpegnoEvasione.getImportoRipartito());
										subimpegnoEvasioneDad.update(subimpegnoEvasione);
									}
								}
							}
						}

						// aggiornare stato riga evasione
						final String codiceStatoRigaEvasione = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_TOTALMENTE_FATTURATA.getCostante();
						final Stato statoRigaEvasione = decodificaDad.getStato(codiceStatoRigaEvasione,ConstantsCPassStato.TipoStatoEnum.RIGA_EVASIONE.getCostante());
						rigaEvasione.setStato(statoRigaEvasione);
						rigaEvasioneDad.updateRigaEvasione(rigaEvasione);
					}

					// aggiornare stato destinatario evasione
					final String codiceStatoRigaEvasione = ConstantsCPassStato.StatoOrdineEvasioneEnum.DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO.getCostante();
					final Stato statoRigaEvasione = decodificaDad.getStato(codiceStatoRigaEvasione,ConstantsCPassStato.TipoStatoEnum.DESTINATARIO_EVASIONE.getCostante());
					destinatarioEvasione.setStato(statoRigaEvasione);
					destinatarioEvasioneDad.updateDestinatarioEvasione(destinatarioEvasione);
				}
			}
		}

		for(final TestataEvasione ev : evasioni) {
			// aggiornamento stato evasione
			final String codiceStatoTestataEvasione = invioAsincrono ? StatoEvasioneEnum.INVIATA.getCostante() : StatoEvasioneEnum.IN_CONTABILITA.getCostante();
			final Stato statoEvasione = decodificaDad.getStato(codiceStatoTestataEvasione, ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante());
			ev.setStato(statoEvasione);
			// aggiornamento data invio
			ev.setDataInvioContabilita(new Date(System.currentTimeMillis()));
			testataEvasioneDad.updateTestataEvasione(ev);
		}
		separaMessaggiErrorePerTipo(apiErrors);
		final TestataEvasione toReturn = testataEvasioneDad.getTestataEvasione(tempEvasione.getId()).orElse(tempEvasione);
		response.setTestataEvasione(toReturn);
	}

	private void getModalitaPagamentoSoggetto(String fatturaCodice,UUID enteId) {
		// leggere la prima modalità di pagamento valida restituita (elencoModalitaPagamento.codice con elencoModalitaPagamento.stato.codice = 'valido')
		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		final Fornitore fornitore = new Fornitore();
		fornitore.setCodice(fatturaCodice);
		filtroFornitore.setFornitore(fornitore);
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
		final ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
		final List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));

		if(fornitori.size() > 0) {
			for (final ModalitaPagamento modalitaPagamento : fornitori.get(0).getElencoModalitaPagamento()) {
				if (modalitaPagamentoSoggetto == null && modalitaPagamento.getStato().equalsIgnoreCase("valido")) {
					modalitaPagamentoSoggetto = modalitaPagamento.getCodice();
				}
			}
		}
	}

	private ArrayList<QuotaItem> preparazioneQuote(List<TestataEvasione> evasioni) {
		final String methodName = "preparazioneQuote";

		final ArrayList<QuotaItem> listQuotaItem = new ArrayList<>();
		for(final TestataEvasione ev : evasioni) {
			// per tutti i destinatari collegati
			for (final DestinatarioEvasione destinatarioEvasione : ev.getDestinatarioEvasiones()) {
				// per tutte le righe collegate
				final List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());

				for (final RigaEvasione rigaEvasione : righeEvasiones) {
					final String cig = rigaOrdineDad.findOne(rigaEvasione.getRigaOrdine().getId()).getDestinatario().getTestataOrdine().getCig();

					final List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
					if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
						for (final ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {

							if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
								QuotaItem quotaItem = findInListOrCreateNew(listQuotaItem, impegnoEvasione.getImpegnoAnno(), impegnoEvasione.getImpegnoNumero(), null, null);
								quotaItem.cig = cig;
								if (quotaItem.impegnoAnno == null && quotaItem.impegnoNumero == null) {
									quotaItem.sospensioni.add(new SospensioneItem());
									quotaItem.impegnoAnno = impegnoEvasione.getImpegnoAnno();
									quotaItem.impegnoNumero = impegnoEvasione.getImpegnoNumero();
								}

								final SospensioneItem tempSospensione = quotaItem.sospensioni.get(0);

								tempSospensione.dataSospensioneQuota = impegnoEvasione.getDataSospensione();
								if (impegnoEvasione.getCausaleSospensioneEvasione() != null) {
									tempSospensione.causaleSospensioneQuota = impegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice();
								}

								final int iQuota = listQuotaItem.indexOf(quotaItem);
								if (iQuota < 0) {

									/*
									if (tempSospensione.causaleSospensioneQuota == null) {
										quotaItem.importoQuota = impegnoEvasione.getImportoRipartito();
									} else {
										quotaItem.importoQuota = impegnoEvasione.getImportoSospeso();
									}
									listQuotaItem.add(quotaItem);
									 */
									quotaItem.importoQuota = impegnoEvasione.getImportoRipartito();
									if (tempSospensione.causaleSospensioneQuota != null && impegnoEvasione.getImportoSospeso().compareTo(BigDecimal.ZERO)!=0) {
										final QuotaItem quotaItemSospesa = quotaItem.clona(quotaItem);
										quotaItemSospesa.importoQuota = impegnoEvasione.getImportoSospeso();
										listQuotaItem.add(quotaItemSospesa);
									}
									final List<SospensioneItem> sospVuota = new ArrayList<>();
									sospVuota.add(new SospensioneItem());
									quotaItem.sospensioni = sospVuota;
									listQuotaItem.add(quotaItem);
								} else {
									/*
									quotaItem = listQuotaItem.get(iQuota);
									if (tempSospensione.causaleSospensioneQuota == null) {
										quotaItem.importoQuota = quotaItem.importoQuota.add(impegnoEvasione.getImportoRipartito());
									} else {
										quotaItem.importoQuota = quotaItem.importoQuota.add(impegnoEvasione.getImportoSospeso());
									}
									 */
									/* issue-54 */
									quotaItem = listQuotaItem.get(iQuota);
									quotaItem.importoQuota = quotaItem.importoQuota.add(impegnoEvasione.getImportoRipartito());
									if (tempSospensione.causaleSospensioneQuota != null && impegnoEvasione.getImportoSospeso().compareTo(BigDecimal.ZERO)!=0) {
										final QuotaItem quotaItemSospesa = quotaItem.clona(quotaItem);
										quotaItemSospesa.importoQuota = impegnoEvasione.getImportoSospeso();
										listQuotaItem.add(quotaItemSospesa);
									}
								}
							} else {
								for (final SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
									subimpegnoEvasione.setImportoLiquidato(subimpegnoEvasione.getImportoRipartito());
									QuotaItem quotaItem = findInListOrCreateNew(listQuotaItem, subimpegnoEvasione.getImpegnoAnno(), subimpegnoEvasione.getImpegnoNumero(),subimpegnoEvasione.getSubimpegnoAnno(), subimpegnoEvasione.getSubimpegnoNumero());
									quotaItem.cig = cig;
									if (quotaItem.impegnoAnno == null && quotaItem.impegnoNumero == null) {
										quotaItem.sospensioni.add(new SospensioneItem());
										quotaItem.impegnoAnno = subimpegnoEvasione.getImpegnoAnno();
										quotaItem.impegnoNumero = subimpegnoEvasione.getImpegnoNumero();
										quotaItem.subimpegnoAnno = subimpegnoEvasione.getSubimpegnoAnno();
										quotaItem.subimpegnoNumero = subimpegnoEvasione.getSubimpegnoNumero();
									}

									final SospensioneItem tempSospensione = quotaItem.sospensioni.get(0);
									log.info(methodName ,"data sospensione" + subimpegnoEvasione.getDataSospensione());

									tempSospensione.dataSospensioneQuota = subimpegnoEvasione.getDataSospensione();
									//if (impegnoEvasione.getCausaleSospensioneEvasione() != null) {
									if (subimpegnoEvasione.getCausaleSospensioneEvasione() != null) {
										log.info(methodName ,"causale sosp " + subimpegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice());
										//tempSospensione.causaleSospensioneQuota = impegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice();
										tempSospensione.causaleSospensioneQuota = subimpegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice();
									}

									final int iQuota = listQuotaItem.indexOf(quotaItem);
									if (iQuota < 0) {
										/*
										if (tempSospensione.causaleSospensioneQuota == null) {
											quotaItem.importoQuota = subimpegnoEvasione.getImportoRipartito();
										} else {
											quotaItem.importoQuota = subimpegnoEvasione.getImportoSospeso();
										}
										listQuotaItem.add(quotaItem);
										 */
										quotaItem.importoQuota = subimpegnoEvasione.getImportoRipartito();

										if (tempSospensione.causaleSospensioneQuota != null && subimpegnoEvasione.getImportoSospeso().compareTo(BigDecimal.ZERO)!=0) {
											final QuotaItem quotaItemSospesa = quotaItem.clona(quotaItem);
											quotaItemSospesa.importoQuota = subimpegnoEvasione.getImportoSospeso();
											listQuotaItem.add(quotaItemSospesa);
										}
										final List<SospensioneItem> sospVuota = new ArrayList<>();
										sospVuota.add(new SospensioneItem());
										quotaItem.sospensioni = sospVuota;
										listQuotaItem.add(quotaItem);
									} else {
										/*
										quotaItem = listQuotaItem.get(iQuota);
										if (tempSospensione.causaleSospensioneQuota == null) {
											quotaItem.importoQuota = quotaItem.importoQuota.add(subimpegnoEvasione.getImportoRipartito());
										} else {
											quotaItem.importoQuota = quotaItem.importoQuota.add(subimpegnoEvasione.getImportoSospeso());
										}
										 */
										/* issue-54 */
										quotaItem = listQuotaItem.get(iQuota);
										quotaItem.importoQuota = quotaItem.importoQuota.add(subimpegnoEvasione.getImportoRipartito());
										if (tempSospensione.causaleSospensioneQuota != null && subimpegnoEvasione.getImportoSospeso().compareTo(BigDecimal.ZERO)!=0) {
											final QuotaItem quotaItemSospesa = quotaItem.clona(quotaItem);
											//quotaItem.sospensioni = null;
											quotaItemSospesa.importoQuota = subimpegnoEvasione.getImportoSospeso();
											listQuotaItem.add(quotaItemSospesa);
										}
									}
								}
							}
						}
					}
				}




			}
		}

		return listQuotaItem;
	}

	private QuotaItem findInListOrCreateNew(ArrayList<QuotaItem> listQuotaItem, Integer anno, Integer numero, Integer subAnno, Integer subNumero) {
		QuotaItem item = new QuotaItem();
		for (final QuotaItem it : listQuotaItem) {
			if(subAnno == null && it.impegnoAnno.equals(anno) && it.impegnoNumero.equals(numero) ) {
				item = it;
			}
			if(subAnno != null && it.impegnoAnno.equals(anno) && it.impegnoNumero.equals(numero) && it.subimpegnoAnno.equals(subAnno) && it.subimpegnoNumero.equals(subNumero)) {
				item = it;
			}
		}
		return item;
	}

	private String creazioneXmlPerInvioQuote(String fatturaCodice, String fatturaTipo, Integer fatturaAnno, String fatturaCodiceExt, ArrayList<QuotaItem> listQuotaItem, String enteCode) {
		bDocumentoSpesaPrimaOccorrenza = true;

		final StringBuilder sbXml = new StringBuilder();
		sbXml.append("<elenchiDocumentiAllegato>");
		sbXml.append("<elenchi>");
		sbXml.append("<elenco saltaInserimento=\'true\'>");
		sbXml.append("<subdocumenti>");

		for (final QuotaItem quotaItem : listQuotaItem) {
			sbXml.append("<subdocumentoSpesa>");
			// vedere paragrafo 10.4
			sbXml.append(getSubDocumentoSpesa(fatturaCodice, fatturaTipo, fatturaAnno, fatturaCodiceExt, quotaItem, enteCode));
			sbXml.append("</subdocumentoSpesa>");
		}

		sbXml.append("</subdocumenti>");
		sbXml.append("</elenco>");
		sbXml.append("</elenchi>");
		sbXml.append("</elenchiDocumentiAllegato>");

		return sbXml.toString();
	}

	private String getSubDocumentoSpesa(String fatturaCodice, String fatturaTipo, Integer fatturaAnno, String fatturaCodiceExt, QuotaItem quotaItem, String enteCode) {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final Parametro login = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.LOGIN_INVIO_CONTABILITA.getCostante(),null, ente.getId());

		final StringBuilder sbXml = new StringBuilder();
		sbXml.append(getDocumentoSpesa(fatturaCodice, fatturaTipo, fatturaAnno, fatturaCodiceExt, enteCode));

		// Impegno Vedere 2.3 Impegno
		sbXml.append(getImpegno(quotaItem));

		// subImpegno Vedere 2.4 Subimpegno
		sbXml.append(getSubImpegno(quotaItem));

		sbXml.append(addTag("modalitaPagamentoSoggetto", addTag("codiceModalitaPagamento", modalitaPagamentoSoggetto)));
		sbXml.append(addTag("importo", quotaItem.importoQuota)); // (cfr cdu chiamante)
		sbXml.append(addTag("importoDaDedurre", 0));
		sbXml.append(addTag("loginOperazione", login.getValore()));
		sbXml.append(addTag("cig", quotaItem.cig));


		if (quotaItem.sospensioni != null && quotaItem.sospensioni.size() > 0) {
			sbXml.append("<sospensioni>");

			for(final SospensioneItem sosp : quotaItem.sospensioni) {
				if(sosp.dataSospensioneQuota != null || sosp.causaleSospensioneQuota != null || sosp.dataRiattivazione != null) {
					sbXml.append("<sospensione>");
					if(sosp.dataSospensioneQuota != null) {
						sbXml.append(addTag("dataSospensione", sdf.format(sosp.dataSospensioneQuota)));// La data va passata nel formato: 2015-09-27T14:30:05+02:00
					}
					if(sosp.causaleSospensioneQuota != null) {
						sbXml.append(addTag("causaleSospensione", sosp.causaleSospensioneQuota));
					}
					if(sosp.dataRiattivazione != null) {
						sbXml.append(addTag("dataRiattivazione", sosp.dataRiattivazione));
					}
					sbXml.append("</sospensione>");
				}
			}

			sbXml.append("</sospensioni>");
		}

		return sbXml.toString();
	}

	private String getImpegno(QuotaItem quotaItem) {
		final StringBuilder sbXml = new StringBuilder();
		sbXml.append(addTag("annoMovimento", quotaItem.impegnoAnno)); // Anno impegno relativo alla quota (cfr cdu chiamante)");
		sbXml.append(addTag("numero", quotaItem.impegnoNumero)); // Numero impegno relativo alla quota (cfr cdu chiamante)");

		return addTag("impegno", sbXml.toString());
	}

	private String getSubImpegno(QuotaItem quotaItem) {
		final StringBuilder sbXml = new StringBuilder();
		sbXml.append(addTag("annoMovimento", quotaItem.subimpegnoAnno)); // Anno subimpegno relativo alla quota (cfr cdu chiamante)
		sbXml.append(addTag("numero", quotaItem.subimpegnoNumero)); // Numero subimpegno relativo alla quota (cfr cdu chiamante)

		return addTag("subImpegno", sbXml.toString());
	}

	private String getDocumentoSpesa(String fatturaCodice, String fatturaTipo, Integer fatturaAnno, String fatturaCodiceExt, String enteCode) {
		final StringBuilder sbXml = new StringBuilder();

		// vedere paragrafo 10.3
		if (bDocumentoSpesaPrimaOccorrenza) {
			//nel caso di coto integrazione con sicraweb il tag soggetto va replicato ogni volta
			//if(enteCode!=null && !enteCode.equals("COTO")) {
			//bDocumentoSpesaPrimaOccorrenza = false;
			//}
			final String soggetto = addTag("codiceSoggetto", fatturaCodice);
			sbXml.append(addTag("soggetto", soggetto));

			String tipoDocumento = addTag("codice", fatturaTipo);
			tipoDocumento += addTag("tipoFamigliaDocumento", "SPESA");
			sbXml.append(addTag("tipoDocumento", tipoDocumento));

			sbXml.append(addTag("anno", fatturaAnno));
			sbXml.append(addTag("numero", fatturaCodiceExt));

		}
		sbXml.append(addTag("uid", "-1"));

		return addTag("documentoSpesa", sbXml.toString());
	}

	private String addTag(String tag, Object value) {
		if (value == null) {
			return "";
		} else {
			return "<" + tag + ">" + value + "</" + tag + ">";
		}
	}

}


