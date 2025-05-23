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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetRecuperoNotificaNSORequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetRecuperoNotificaNSOResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNso;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoNso;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.DocumentoRichiesto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoListaDocumenti;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaDiScarto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaMdn;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoRicevutaDiConsegna;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.convert.DateConvertHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * GetRecuperoDdtService
 */
public class GetRecuperoNotificaNSOService extends BaseBatchService<GetRecuperoNotificaNSORequest, GetRecuperoNotificaNSOResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final DecodificaDad decodificaDad;
	private final NotificheDad notificheDad;
	

	/**
	 *
	 * @param configurationHelper the helper for the configuration
	 * @param externalHelperLookup
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 * @param testataOrdineDad
	 * @param destinatarioOrdineDad
	 * @param rigaOrdineDad
	 * @param decodificaDad
	 * @param documentoTrasportoDad
	 * @param documentoTrasportoRigaDad
	 * @param enteDad
	 * @param commonDad the DAD for the common
	 * @param elaborazioneTipoDad
	 */
	public GetRecuperoNotificaNSOService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ExternalHelperLookup externalHelperLookup,
			TestataOrdineDad testataOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			DecodificaDad decodificaDad,
			UtenteDad utenteDad,
			NotificheDad notificheDad
			) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.decodificaDad = decodificaDad;
		this.notificheDad = notificheDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {

		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));

		final UUID enteId = enteOpt.orElseThrow(() -> new NotFoundException("ente")).getId();
		final List<Ufficio> listaUffici = commonDad.getUfficiByEnte(enteId);
		final List<String> listIdNotier = listaUffici.stream().filter(x->Objects.nonNull(x.getIdNotier())).map(x -> x.getIdNotier()).distinct().collect(Collectors.toList());

		final String dataFine = DateConvertHelper.formatDate(new Date(),"ddMMyyyy");
		final String dataInizio = DateConvertHelper.formatDate(DateConvertHelper.addToDate(new Date(), Calendar.DAY_OF_MONTH, -5), "ddMMyyyy");

		final Parametro param = systemDad.getParametro(ChiaveEnum.GG_FINE_NOTIFICA.name(), "NOTIFICA", enteId);

		//ciclo Per ogni distinto idNotier:
		for (final String idNotier : listIdNotier) {
			// Inserisce un record per l’elaborazione
			final Elaborazione elaborazione = inizializzaElaborazione(idNotier, enteOpt.orElseThrow(() -> new NotFoundException("elaborazione")));
			String statoElaborazione = CpassEnum.OK.getCostante();

			// verifico che ci siano ordini per cui invocare i servizi di notier ( esito consegna KO)
			final List<DestinatarioInvioNso> inviiSenzaNotifica = destinatarioOrdineDad.findDestinatarioInvioNsoSenzaNotifica(idNotier);
			if (inviiSenzaNotifica.isEmpty()) {
				concludiElaborazione(elaborazione, "OK_NO_ELAB", null);
			}else {

				final List<DestinatarioInvioNso> inviiSenzaNotificaMdn = inviiSenzaNotifica.stream().filter((x) ->
				x.getEsitoConsegnaMdn()==null || x.getEsitoConsegnaMdn().equals(CpassEnum.KO.getCostante())).collect(Collectors.toList());

				final List<DestinatarioInvioNso> inviiSenzaNotificaNso = inviiSenzaNotifica.stream().filter((x) ->
				x.getEsitoConsegnaNso()==null || x.getEsitoConsegnaNso().equals(CpassEnum.KO.getCostante())).collect(Collectors.toList());

				final List<DestinatarioInvioNso> inviiSenzaNotificaRecuperati = new ArrayList<>(); // contiene gli invii recuperati dal servizio recuperoListaDocumenti.

				// richiama il servizio [7] Recupero Lista Documenti - RICEVUTA di CONSEGNA
				final EsitoRecuperoListaDocumenti esitoRecuperoListaRicevutaDiConsegna = recuperoListaDocumenti(idNotier, enteId, "&tipodoc=ricevuta_di_consegna&data_inizio="+dataInizio+"&data_fine="+dataFine+"&ordinamento=desc");

				if (StringUtils.isNotEmpty(esitoRecuperoListaRicevutaDiConsegna.getDescErrore())){
					// caso in cui il recupero lista documento dia errore
					statoElaborazione = CpassEnum.KO.getCostante();
					inserisciMessaggioErroreInElaborazione(elaborazione, "RECUPERO_DOCUMENTI", "Id Notier -->  "+idNotier , "Errore in recupero lista ricevuta di consegna cod : "+ esitoRecuperoListaRicevutaDiConsegna.getCodErrore()+" - desc --> " + esitoRecuperoListaRicevutaDiConsegna.getDescErrore() );
				}else {

					final List<DocumentoRichiesto> listaRicevutaDiConsegna = esitoRecuperoListaRicevutaDiConsegna.getListaDocumentoRichiesto();
					for(final DocumentoRichiesto documentoRichiesto :listaRicevutaDiConsegna) {

						final DestinatarioInvioNso destinatarioInvioNso = inviiSenzaNotificaNso.stream().filter(x -> x.getUrn().equals(documentoRichiesto.getUrnCollegato())).findAny().orElse(null);
						if (destinatarioInvioNso != null) {

							final EsitoRecuperoRicevutaDiConsegna esitoRecupero = recuperoRicevutaDiConsegna(documentoRichiesto.getUrn(), enteId);
							inviiSenzaNotificaRecuperati.add(destinatarioInvioNso);

							if (esitoRecupero.getCodEsito().equals("OK")) {
								DestinatarioInvioNsoXml destinatarioInvioNsoXml = new DestinatarioInvioNsoXml();
								destinatarioInvioNsoXml.setDestinatarioInvioNso(destinatarioInvioNso);
								destinatarioInvioNsoXml.setIdentificativoTrasmissione(esitoRecupero.getIdentificativoTrasmissione());
								destinatarioInvioNsoXml.setDataConsegna(esitoRecupero.getRicevutaDiConsegna().getDataOraConsegna());
								destinatarioInvioNsoXml.setNote(esitoRecupero.getRicevutaDiConsegna().getNote());
								destinatarioInvioNsoXml.setTipodoc("RICEVUTA_DI_CONSEGNA");//A seconda del file ‘RICEVUTA_DI_CONSEGNA’ Oppure ‘NOTIFICA_MDN’
								destinatarioInvioNsoXml.setFileXml(esitoRecupero.getRicevutaDiConsegna().getXml());
								destinatarioInvioNsoXml = destinatarioOrdineDad.insertDestinatarioInvioNsoXml(destinatarioInvioNsoXml);

								destinatarioInvioNso.setEsitoConsegnaNso(CpassEnum.OK.getCostante());
							}else {
								destinatarioInvioNso.setEsitoConsegnaNso(CpassEnum.KO.getCostante());
								destinatarioInvioNso.setEsitoConsegnaNsoErroreCodice(esitoRecupero.getCodErrore());
							}
							destinatarioInvioNso.setEsitoConsegnaNsoErroreDescrizione(esitoRecupero.getDescErrore()); // potrebbe essere valorizzato anche per Esito OK?
							aggiornaDestinatarioInvioNso(destinatarioInvioNso);
						}
					}
				}

				// richiama il servizio [7] Recupero Lista Documenti - Notifica di scarto ( NSO - per ogni invio nso dovrebbe essere recuperato o una notifica di consegna andata a buon fine o una notifica di scarto)
				final EsitoRecuperoListaDocumenti esitoRecuperoListaNotificaDiScarto = recuperoListaDocumenti(idNotier, enteId, "&tipodoc=notifica_di_scarto&data_inizio="+dataInizio+"&data_fine="+dataFine+"&ordinamento=desc");

				if (StringUtils.isNotEmpty(esitoRecuperoListaNotificaDiScarto.getDescErrore())){
					// caso in cui il recupero lista documento dia errore
					statoElaborazione = CpassEnum.KO.getCostante();
					inserisciMessaggioErroreInElaborazione(elaborazione, "RECUPERO_DOCUMENTI", "Id Notier -->  "+idNotier , "Errore in recupero lista notifica di scarto cod : "+ esitoRecuperoListaNotificaDiScarto.getCodErrore()+" - desc --> " + esitoRecuperoListaNotificaDiScarto.getDescErrore() );
				}else {

					final List<DocumentoRichiesto> listaNotificaDiScarto = esitoRecuperoListaNotificaDiScarto.getListaDocumentoRichiesto();
					for(final DocumentoRichiesto documentoRichiesto :listaNotificaDiScarto) {

						final DestinatarioInvioNso destinatarioInvioNso = inviiSenzaNotificaNso.stream().filter(x -> x.getUrn().equals(documentoRichiesto.getUrnCollegato())).findAny().orElse(null);
						if (destinatarioInvioNso != null) {

							final EsitoRecuperoNotificaDiScarto esitoRecupero = recuperoNotificaDiScarto(documentoRichiesto.getUrn(), enteId);
							inviiSenzaNotificaRecuperati.add(destinatarioInvioNso);

							if (esitoRecupero.getCodEsito().equals("OK")) {
								DestinatarioInvioNsoXml destinatarioInvioNsoXml = new DestinatarioInvioNsoXml();
								destinatarioInvioNsoXml.setDestinatarioInvioNso(destinatarioInvioNso);
								destinatarioInvioNsoXml.setIdentificativoTrasmissione(esitoRecupero.getIdentificativoTrasmissione());
								destinatarioInvioNsoXml.setDataRicezione(esitoRecupero.getNotificaDiScarto().getDataOraRicezione());
								destinatarioInvioNsoXml.setNote(esitoRecupero.getNotificaDiScarto().getNote());
								destinatarioInvioNsoXml.setTipodoc("NOTIFICA_DI_SCARTO");
								destinatarioInvioNsoXml.setFileXml(esitoRecupero.getNotificaDiScarto().getXml());
								destinatarioInvioNsoXml = destinatarioOrdineDad.insertDestinatarioInvioNsoXml(destinatarioInvioNsoXml);

								destinatarioInvioNso.setEsitoConsegnaNso(CpassEnum.KO.getCostante()); // l'esito di consegna in presenza di notifica di scarto è KO
							}else {
								destinatarioInvioNso.setEsitoConsegnaNso(CpassEnum.KO.getCostante());
								destinatarioInvioNso.setEsitoConsegnaNsoErroreCodice(esitoRecupero.getCodErrore());
								destinatarioInvioNso.setEsitoConsegnaNsoErroreDescrizione(esitoRecupero.getDescErrore());
							}
							aggiornaDestinatarioInvioNso(destinatarioInvioNso);
						}
					}
				}

				//				trattamento notifica_mdn
				final EsitoRecuperoListaDocumenti esitoRecuperoListaNotificaMdn = recuperoListaDocumenti(idNotier, enteId, "&tipodoc=notifica_mdn&data_inizio="+dataInizio+"&data_fine="+dataFine+"&ordinamento=desc");

				if (StringUtils.isNotEmpty(esitoRecuperoListaNotificaMdn.getDescErrore())){
					statoElaborazione = CpassEnum.KO.getCostante();
					inserisciMessaggioErroreInElaborazione(elaborazione, "RECUPERO_DOCUMENTI", "Id Notier -->  "+idNotier , "Errore in recupero lista notifica mdn cod : "+ esitoRecuperoListaNotificaMdn.getCodErrore()+" - desc --> " + esitoRecuperoListaNotificaMdn.getDescErrore() );
				}else {

					final List<DocumentoRichiesto> listaNotificaMdn = esitoRecuperoListaNotificaMdn.getListaDocumentoRichiesto();
					for(final DocumentoRichiesto documentoRichiesto :listaNotificaMdn) {

						// Interrogato il secondo servizio notier solo se il documento è da trattare
						final DestinatarioInvioNso destinatarioInvioNso = inviiSenzaNotificaMdn.stream().filter(x -> documentoRichiesto.getUrnCollegato().equals(x.getUrn())).findAny().orElse(null);

						if (destinatarioInvioNso != null) {

							final EsitoRecuperoNotificaMdn esitoRecuperoDocumento = recuperoNotificaMdn(documentoRichiesto.getUrn(), enteId);
							inviiSenzaNotificaRecuperati.add(destinatarioInvioNso);

							if (esitoRecuperoDocumento.getCodEsito().equals("OK")) {
								DestinatarioInvioNsoXml destinatarioInvioNsoXml = new DestinatarioInvioNsoXml();
								destinatarioInvioNsoXml.setDestinatarioInvioNso(destinatarioInvioNso);
								destinatarioInvioNsoXml.setIdentificativoTrasmissione(esitoRecuperoDocumento.getIdentificativoTrasmissione());
								destinatarioInvioNsoXml.setDataConsegna(esitoRecuperoDocumento.getNotificaMdn().getDataInvio());
								destinatarioInvioNsoXml.setTipodoc("NOTIFICA_MDN");//A seconda del file ‘RICEVUTA_DI_CONSEGNA’ Oppure ‘NOTIFICA_MDN’
								destinatarioInvioNsoXml.setFileXml(esitoRecuperoDocumento.getNotificaMdn().getXml());
								destinatarioInvioNsoXml = destinatarioOrdineDad.insertDestinatarioInvioNsoXml(destinatarioInvioNsoXml);

								destinatarioInvioNso.setEsitoConsegnaMdn(CpassEnum.OK.getCostante());
							}else {
								destinatarioInvioNso.setEsitoConsegnaMdn(CpassEnum.KO.getCostante());
								destinatarioInvioNso.setEsitoConsegnaMdnErroreCodice(esitoRecuperoDocumento.getCodErrore());
							}
							destinatarioInvioNso.setEsitoConsegnaMdnErroreDescrizione(esitoRecuperoDocumento.getDescErrore()); // potrebbe essere valorizzato anche per Esito OK?
							aggiornaDestinatarioInvioNso(destinatarioInvioNso);
						}
					}
				}

				// Aggiornamento Stato NSO per destinatari e testata ordine
				final Map<UUID, Utente> mapOrdineNotifica = new HashMap<>(); // testataOrdineId, utenteInvio
				final List<Integer> idDestinatarioInvioNsos = inviiSenzaNotificaRecuperati.stream().map(x -> x.getId()).distinct().collect(Collectors.toList());
				for (final Integer id : idDestinatarioInvioNsos) {
					final DestinatarioInvioNso destinatarioInvioNso = destinatarioOrdineDad.getDestinatarioInvioNso(id).orElseThrow(() -> new NotFoundException("destinatario nso"));

					final boolean consegnatoMdn = destinatarioInvioNso.getEsitoConsegnaMdn()!=null && destinatarioInvioNso.getEsitoConsegnaMdn().equals(CpassEnum.OK.getCostante());
					final boolean consegnatoNso = destinatarioInvioNso.getEsitoConsegnaNso()!=null && destinatarioInvioNso.getEsitoConsegnaNso().equals(CpassEnum.OK.getCostante());

					String statoNsoDestinatario = null;
					if (consegnatoMdn && consegnatoNso) {
						statoNsoDestinatario = ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante();
					}else {
						statoNsoDestinatario = ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante();
					}
					final Destinatario destinatario = destinatarioInvioNso.getDestinatario();
					final TestataOrdine testataOrdine = destinatarioInvioNso.getTestataOrdine();

					if (destinatario!=null && destinatario.getId()!=null) {
						// invio ordine per destinatario, aggiornamento stato nso del destinatario
						aggiornaStatoNsoDestinatario(destinatario, statoNsoDestinatario);
						mapOrdineNotifica.put(destinatario.getTestataOrdine().getId(), destinatarioInvioNso.getUtenteInvio());
					}
					if (testataOrdine != null && testataOrdine.getId()!=null) {
						// invio ordine per testata, necessario aggiornare lo stato nso massivo per tutti i destinatari
						// verificare: ci sono già o dobbiamo leggerli ?
						final List<Destinatario> destinatariDaAggiornare = testataOrdine.getListDestinatario().stream().filter((x) ->
						x.getStatoNso()==null || x.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante())).collect(Collectors.toList());
						aggiornaStatoNsoDestinatarios(destinatariDaAggiornare, statoNsoDestinatario);
						//mapOrdineNotifica.put(destinatario.getTestataOrdine().getId(), destinatarioInvioNso.getUtenteInvio());
					}
				}
				// aggiorna stato nso per testata e eventualmente invia notifica
				mapOrdineNotifica.forEach((k, v) -> aggiornaStatoNsoOrdineEInviaNotifica(k, v, param));

				concludiElaborazione(elaborazione, statoElaborazione, null);

				response.setLista(inviiSenzaNotificaRecuperati);
			}
		}
	}

	private void aggiornaDestinatarioInvioNso(DestinatarioInvioNso destinatarioInvioNso) {
		log.info("aggiornaDestinatarioInvioNso","BEGIN");
		destinatarioInvioNso.setDataModifica(new Date());
		destinatarioInvioNso.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
		destinatarioOrdineDad.updateDestinatarioInvioNso(destinatarioInvioNso);
		log.info("aggiornaDestinatarioInvioNso","END");
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
		if (statoNso != null) {
			destinatario.setStatoNso(isEntityPresent(() -> decodificaDad.getStatoNsoOpt(statoNso, ConstantsCPassOrdStatoNso.TipoEnum.ORDINE.getCostante()),"statoNSO"));
			destinatario.setDataInvioNso(new Date());
			destinatario.setDataModifica(new Date());
			destinatario.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
			destinatarioOrdineDad.updateDestinatario(destinatario);
		}
		log.info("aggiornaStatoNsoDestinatario","END");
	}

	private void aggiornaStatoNsoOrdineEInviaNotifica(UUID testataOrdineId, Utente utenteInvio, Parametro param) {
		log.info("aggiornaStatoNsoOrdine","BEGIN");

		final List<Destinatario> listaDestinatari = destinatarioOrdineDad.findByOrdine(testataOrdineId);

		final List<Destinatario> destinatariStatoNsoKO = listaDestinatari.stream().filter((x) ->
		x.getStatoNso()==null || x.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante())).collect(Collectors.toList());

		StatoNso statoNso;
		String codiceNotifica;
		if (destinatariStatoNsoKO.isEmpty()) {
			statoNso = isEntityPresent(() -> decodificaDad.getStatoNsoOpt(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante(), ConstantsCPassOrdStatoNso.TipoEnum.ORDINE.getCostante()),"statoNSO");
			codiceNotifica = ConstantsDecodifiche.NotificaEnum.N0001.getCodice();
		}else {
			statoNso = isEntityPresent(() -> decodificaDad.getStatoNsoOpt(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante(), ConstantsCPassOrdStatoNso.TipoEnum.ORDINE.getCostante()),"statoNSO");
			codiceNotifica = ConstantsDecodifiche.NotificaEnum.N0002.getCodice();
		}

		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(testataOrdineId);
		if (testataOrdine.getStatoNso() == null || !testataOrdine.getStatoNso().getCodice().equals(statoNso.getCodice())) {
			testataOrdine.setStatoNso(statoNso);
			testataOrdine.setDataModifica(new Date());
			testataOrdine.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
			testataOrdine = testataOrdineDad.updateTestataOrdineLigth(testataOrdine);
			inserisciNotificaUtente(testataOrdine, codiceNotifica, utenteInvio, param);
		}
		log.info("aggiornaStatoNsoOrdine","END");
	}

	private void inserisciNotificaUtente(TestataOrdine testataOrdine, String codiceNotifica, Utente utente, Parametro param) {
		log.info("inserisciNotificaPerUtente","NOTIFICA  -> "+codiceNotifica+" per Ordine -> "+testataOrdine.getAnno()+"/"+testataOrdine.getNumero());

		//TODO : inserire su db i testi notifica
		final TestoNotifica testoNotifica = isEntityPresent(() -> notificheDad.getTestoNotifica(codiceNotifica),"testoNotifica");

		final Notifica notifica = new Notifica();
		notifica.setEntita(testataOrdine.getId().toString());
		notifica.setFonte(ConstantsDecodifiche.NotificaFonteEnum.NSO.getCodice());
		notifica.setEntitaTipo(ConstantsDecodifiche.NotificaTipoEntitaEnum.ORDINE.getCodice());
		final Date now = new Date();
		final int intParamValue = Integer.parseInt(param.getValore());
		notifica.setDataInizio(now);
		notifica.setDataFine(DateUtils.addDays(now, intParamValue));
		notifica.setFlgGenerico(false);
		final Map<String,Object> parametri = new HashMap<>();
		parametri.put("anno",testataOrdine.getAnno());
		parametri.put("numero",testataOrdine.getNumero());
		notifica.setParametri(JsonUtility.serialize(parametri));
		notifica.setTestoNotifica(testoNotifica);
		notificheDad.saveNotificaUtente(notifica, utente);

	}

	private EsitoRecuperoNotificaMdn recuperoNotificaMdn(String urn, UUID enteId) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final EsitoRecuperoNotificaMdn esitoRichiesta = invokeExternalService(handler,() -> handler.getInstance().recuperoNotificaMdn(handler.getParams(), urn));
		return esitoRichiesta;
	}
	private EsitoRecuperoRicevutaDiConsegna recuperoRicevutaDiConsegna(String urn, UUID enteId) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final EsitoRecuperoRicevutaDiConsegna esitoRichiesta = invokeExternalService(handler,() -> handler.getInstance().recuperoRicevutaDiConsegna(handler.getParams(), urn));
		return esitoRichiesta;
	}
	private EsitoRecuperoNotificaDiScarto recuperoNotificaDiScarto(String urn, UUID enteId) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final EsitoRecuperoNotificaDiScarto esitoRichiesta = invokeExternalService(handler,() -> handler.getInstance().recuperoNotificaDiScarto(handler.getParams(), urn));
		return esitoRichiesta;
	}

	/**
	 *
	 * @param idNotier
	 * @param enteId
	 * @param parametri
	 * @return EsitoRecuperoListaDocumenti
	 */
	private EsitoRecuperoListaDocumenti recuperoListaDocumenti(String idNotier, UUID enteId, String parametri ) {
		final ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class, enteId);
		final EsitoRecuperoListaDocumenti esitoRichiesta = invokeExternalService(handler,() -> handler.getInstance().recuperoListaDocumenti(handler.getParams(), idNotier, parametri));
		return esitoRichiesta;
	}

	/**
	 *
	 * @param uff
	 * @return Elaborazione
	 */
	private Elaborazione inizializzaElaborazione(String idNotier,Ente ente) {
		Elaborazione elaborazione = new Elaborazione();
		//		elaborazione.setEntitaId(uff.getIdNotier());
		elaborazione.setEntitaId(idNotier);
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.RICEZIONE_NOTIFICHE.getCodice()).orElseThrow(() -> new NotFoundException("elaborazione tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione.setEnte(ente);
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param elab
	 * @param statoElab
	 * @param urn
	 * @return Elaborazione
	 */
	@Override
	public Elaborazione concludiElaborazione(Elaborazione elab, String statoElab,String urn) {
		log.info("concludiElaborazione", "statoElab " + statoElab);
		Elaborazione elaborazione = elaborazioneDad.loadElaborazione(elab.getId());
		elaborazione.setStato(statoElab);

		String esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB();
		if(statoElab.equals("KO")) {
			esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB();
		}
		if(statoElab.equals("OK_NO_ELAB")) {
			esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB() + " - nessun documento da trattare";
		}
		elaborazione.setEsito(esito);
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		if(!StringUtils.isEmpty(urn)) {
			elaborazione.setIdEsterno(urn);
		}
		// non dovrebbe servire rileggere il tipo di elaborazione, lo abbiamo recuperato dal db prima
		//		ElaborazioneTipo elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.RICEZIONE_DDT.getCodice()).get();
		//		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}


	/**
	 *
	 * @param elaborazione
	 * @param tipo
	 * @param codice
	 * @param descrizione
	 */
	private void inserisciMessaggioErroreInElaborazione(Elaborazione elaborazione, String tipo, String codice, String descrizione) {
		final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setTipo(tipo);
		elaborazioneMessaggio.setDescrizione(descrizione);
		elaborazioneMessaggio.setCode(codice);
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}
}