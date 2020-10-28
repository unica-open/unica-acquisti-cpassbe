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

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.squareup.okhttp.OkHttpClient;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.mit.client.ApiClient;
import it.csi.cpass.cpassbe.mit.client.ApiClientCPass;
import it.csi.cpass.cpassbe.mit.client.ApiException;
import it.csi.cpass.cpassbe.mit.client.api.AccountApi;
import it.csi.cpass.cpassbe.mit.client.api.ProgrammiApi;
import it.csi.cpass.cpassbe.mit.client.model.login.LoginResult;
import it.csi.cpass.cpassbe.mit.client.model.programmi.AcquistoEntry;
import it.csi.cpass.cpassbe.mit.client.model.programmi.AcquistoEntry.SettoreEnum;
import it.csi.cpass.cpassbe.mit.client.model.programmi.AcquistoNonRipropostoEntry;
import it.csi.cpass.cpassbe.mit.client.model.programmi.DatiGeneraliTecnicoEntry;
import it.csi.cpass.cpassbe.mit.client.model.programmi.PubblicaProgrammaFornitureServiziEntry;
import it.csi.cpass.cpassbe.mit.client.model.programmi.PubblicazioneResult;
import it.csi.cpass.cpassbe.mit.client.model.programmi.ValidateEntry;

/**
 * Manager for MIT
 */
public class MITManager extends BaseFacade {
	private static final String SETTORE_INTERVENTI_SERVIZI = "S";
	private static final String SETTORE_INTERVENTI_FORNITURE = "F";
	private static final String SETTORE_INTERVENTI_LAVORO = "L";
	
	private static final String MIT_TRUE = "1"; // "S";
	private static final String MIT_FALSE = "2"; // "N";

	public static final String MODALITA_INVIO = "MODALITA_INVIO";

	private static final String AMBIENTE_MIT = "MIT";

	private static final String MIT_USERNAME = "MIT_USERNAME";
	private static final String MIT_PASSWORD = "MIT_PASSWORD";
	private static final String MIT_CLIENT_KEY = "MIT_CLIENT_KEY";
	private static final String MIT_CLIENT_ID = "MIT_CLIENT_ID";
	private static final String MIT_URL_WSLOGIN = "MIT_URL_WSLOGIN";
	private static final String MIT_URL_WSPROGRAMMI = "MIT_URL_WSPROGRAMMI";

	private static final String MIT_PROXY_HOSTNAME = "MIT_PROXY_HOSTNAME";
	private static final String MIT_PROXY_PORT = "MIT_PROXY_PORT";
	
	public static final String MODALITA_INVIO_CONTROLLO = "1";
	public static final String MODALITA_INVIO_CONTROLLO_PUBBLICAZIONE = "2";

	private SystemDad systemDad;
	private InterventoDad interventoDad;
	private ProgrammaDad programmaDad;
	private ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	private ElaborazioneDad elaborazioneDad;
	private ElaborazioneParametroDad elaborazioneParametroDad;

	private Map<String, String> parametri = null;

	/**
	 * 
	 * @param systemDad
	 * @param interventoDad
	 * @param programmaDad
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 */
	public MITManager(SystemDad systemDad, InterventoDad interventoDad, ProgrammaDad programmaDad, ElaborazioneDad elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad, ElaborazioneParametroDad elaborazioneParametroDad) {
		this.systemDad = systemDad;
		this.interventoDad = interventoDad;
		this.programmaDad = programmaDad;
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.elaborazioneParametroDad = elaborazioneParametroDad;
	}

	/**
	 * Trasmissione
	 * 
	 * @param elaborazione the elaborazione
	 * @param programma    the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void trasmetti(Elaborazione elaborazione, Programma programma) {
		final String methodName = "trasmetti";
		try {
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.updateElaborazione(elaborazione);
			elaborazioneDad.flush();
			log.info(methodName, "In elaborazione - id: " + elaborazione.getId() + " - entitaId: " + elaborazione.getEntitaId());
		
			Programma programmaPubblicazione = programma;
			if (programmaPubblicazione == null) {
				Optional<Programma> optional = programmaDad.getProgramma(UUID.fromString(elaborazione.getEntitaId()));
				programmaPubblicazione = optional.get();
			}
			
			UUID enteId = null;
			if (programmaPubblicazione.getEnte() != null) {
				enteId = programmaPubblicazione.getEnte().getId();
			}
			parametri = systemDad.getParametri(null, AMBIENTE_MIT, enteId);

			String token = login(elaborazione);
			if (token != null) {
				ElaborazioneParametro elaborazioneParametro = elaborazioneParametroDad.getParametro(elaborazione.getId(), MODALITA_INVIO);
				String modalitaInvio = elaborazioneParametro.getValore();
				if (modalitaInvio != null && (modalitaInvio.equals(MODALITA_INVIO_CONTROLLO) || modalitaInvio.equals(MODALITA_INVIO_CONTROLLO_PUBBLICAZIONE))) {
					pubblicaProgramma(elaborazione, programmaPubblicazione, token, modalitaInvio);
				} else {
					throw new RuntimeException("modalità invio non valida");
				}
			}
		} catch (Exception e) {
			log.error(methodName, e.getMessage(), e);

			String strStacktTrace = ExceptionUtils.getStackTrace(e);

			elaborazione.setEsito("ERRORE");
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.updateElaborazione(elaborazione);

			ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setElaborazione(elaborazione);
			elaborazioneMessaggio.setTipo("E"); // errore
			elaborazioneMessaggio.setDescrizione(strStacktTrace);
			elaborazioneMessaggio.setCode(e.getMessage());
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		}
	}

	private String login(Elaborazione elaborazione) {
		final String methodName = "login";
		String username = parametri.get(MIT_USERNAME);
		String password = parametri.get(MIT_PASSWORD);
		String clientKey = parametri.get(MIT_CLIENT_KEY);
		String clientId = parametri.get(MIT_CLIENT_ID);
		String urlWsLogin = parametri.get(MIT_URL_WSLOGIN);

		AccountApi accountApi = new AccountApi();
		accountApi.getApiClient().setBasePath(urlWsLogin);
		
		OkHttpClient httpClient = getHttpClientWithProxy();
		if (httpClient != null) {
			accountApi.getApiClient().setHttpClient(httpClient);
		}

		LoginResult loginResult = null;
		String token = null;
		try {
			loginResult = accountApi.accountRestServiceLoginPubblica(username, password, clientKey, clientId);
			if (loginResult != null) {
				token = loginResult.getToken();
			}
		} catch (ApiException e) {
			// esempio
			// code: 404
			// body: {"esito":false,"error":"not-found"}
			log.error(methodName,
					"Error. Code: \"" + e.getCode() + "\" - Message: \"" + e.getMessage() + "\" - Body: \"" + e.getResponseBody() + "\"",
					e);

			elaborazione.setEsito("" + e.getCode());
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.updateElaborazione(elaborazione);

			ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setElaborazione(elaborazione);
			elaborazioneMessaggio.setTipo("E"); // errore
			elaborazioneMessaggio.setDescrizione(e.getMessage() + " - " + e.getResponseBody());
			elaborazioneMessaggio.setCode("" + e.getCode());
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		}

		return token;
	}

	private OkHttpClient getHttpClientWithProxy() {
		OkHttpClient httpClient = null;
		String hostnameProxy = parametri.get(MIT_PROXY_HOSTNAME);
		String strPortProxy = parametri.get(MIT_PROXY_PORT);
		if (hostnameProxy != null && !hostnameProxy.trim().equals("") && strPortProxy != null && !strPortProxy.trim().equals("")) {
			int portProxy = Integer.parseInt(strPortProxy);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostnameProxy, portProxy));
			httpClient = new OkHttpClient().setProxy(proxy);
		}
		return httpClient;
	}

	private void pubblicaProgramma(Elaborazione elaborazione, Programma programma, String token, String modalitaInvio) {
		final String methodName = "pubblicaProgramma";
		ApiClient apiClient = null;
		try {
			PubblicaProgrammaFornitureServiziEntry body = populateProgrammaEntry(programma);
			ProgrammiApi programmiApi = new ProgrammiApi();
			
			apiClient  = new ApiClientCPass();
			// apiClient = programmiApi.getApiClient();
			programmiApi.setApiClient(apiClient);
			
			String urlWsProgrammi = parametri.get(MIT_URL_WSPROGRAMMI);
			apiClient.setBasePath(urlWsProgrammi);

			OkHttpClient httpClient = getHttpClientWithProxy();
			if (httpClient != null) {
				programmiApi.getApiClient().setHttpClient(httpClient);
			}
			
			// ridefiniti gli adapter per errore:
			// Can not deserialize value of type java.util.Date from String "2020-03-25T10:31:19.883+01:00": expected format
			// "dd/MM/yyyy"
			// at [Source: org.glassfish.jersey.message.internal.ReaderInterceptorExecutor$UnCloseableInputStream@2bf783e3; line: 1,
			// column: 180] (through reference chain:
			// it.maggioli.eldasoft.wsprogrammi.vo.programmi.fornitureservizi.PubblicaProgrammaFornitureServiziEntry["dataApprovazione"])
			GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter(apiClient))
					.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
					.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
			Gson gson = gsonBuilder.create();
			apiClient.getJSON().setGson(gson);
			
			String strBody = apiClient.getJSON().serialize(body);
			log.info(methodName, "strBody: " + strBody);
			log.info(methodName, "modalitaInvio: " + modalitaInvio);

			PubblicazioneResult pubblicazioneResult = programmiApi.programmiRestServicePubblicaFornitureServizi(body, modalitaInvio, token);
			Long idRicevutoMit = pubblicazioneResult.getId();
			if (idRicevutoMit != null) {
				log.info(methodName, "idRicevutoMit: " + idRicevutoMit + " per programma: " + programma.getId());

				// se il record del programma che si sta tramsettendo non ha ancora l'idRicevutoMit
				// if (programma.getIdRicevutoMit() == null) {
					programma.setIdRicevutoMit(idRicevutoMit);
					programmaDad.updateProgramma(programma);
				// }
			}

			elaborazione.setEsito(pubblicazioneResult.getError());
			if (pubblicazioneResult.getError() == null) {
				elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ELABORATO.getStatoDB());
				elaborazione.setEsito("OK");
			} else {
				elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			}
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.updateElaborazione(elaborazione);

			List<ValidateEntry> listValidate = pubblicazioneResult.getValidate();
			if (listValidate != null) {
				for (ValidateEntry validateEntry : listValidate) {
					ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
					elaborazioneMessaggio.setElaborazione(elaborazione);
					elaborazioneMessaggio.setTipo(validateEntry.getTipo());
					elaborazioneMessaggio.setDescrizione(validateEntry.getMessaggio());
					elaborazioneMessaggio.setCode(validateEntry.getNome());
	
					elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
				}
			}

		} catch (ApiException e) {
			// esempio
			// code: 401
			// body: {"id":null,"error":"Il token non è valido."}
			log.error(methodName,"Error. Code: \"" + e.getCode() + "\" - Message: \"" + e.getMessage() + "\" - Body: \"" + e.getResponseBody() + "\"",e);			
//			Type localVarReturnType = new TypeToken<PubblicazioneResult>(){}.getType();
//			PubblicazioneResult pubblicazioneResult = apiClient.getJSON().deserialize(e.getResponseBody(), localVarReturnType);
			elaborazione.setEsito("" + e.getCode());
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.updateElaborazione(elaborazione);

			ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setElaborazione(elaborazione);
			elaborazioneMessaggio.setTipo("E"); // errore
			elaborazioneMessaggio.setDescrizione(e.getMessage() + " - " + e.getResponseBody());
			elaborazioneMessaggio.setCode("" + e.getCode());
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		}
	}

	private PubblicaProgrammaFornitureServiziEntry populateProgrammaEntry(Programma programma) {
		PubblicaProgrammaFornitureServiziEntry body = new PubblicaProgrammaFornitureServiziEntry();
		body.setId(programma.getCodiceMit());
		body.setAnno(Long.valueOf(programma.getAnno().longValue()));
		body.setCodiceFiscaleSA(programma.getEnte().getCodiceFiscale());
		
		// body.setDescrizione(programma.getDescrizione());
		if (programma.getIdRicevutoMit() != null) {
			// Esempio: Programma biennale degli acquisti di forniture e servizi 2020-2021 – Aggiornamento 01/10/2020
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			body.setDescrizione("Programma biennale degli acquisti di forniture e servizi " + programma.getAnno() + "-"
					+ (programma.getAnno() + 1) + " - Aggiornamento " + sdf.format(programma.getDataApprovazione()));
		} else {
			// Esempio: Programma biennale degli acquisti di forniture e servizi 2020-2021
			body.setDescrizione(
					"Programma biennale degli acquisti di forniture e servizi " + programma.getAnno() + "-" + (programma.getAnno() + 1));
		}

		if (programma.getNumeroProvvedimento() != null) {
			body.setNumeroApprovazione("" + programma.getNumeroProvvedimento());
		}

		body.setDataApprovazione(new DateTime(programma.getDataProvvedimento()));
		body.setDataPubblicazioneApprovazione(new DateTime(programma.getDataPubblicazione()));
		body.setTitoloAttoApprovazione(programma.getDescrizioneProvvedimento());
		body.setUrlAttoApprovazione(programma.getUrl());
		body.setPrimaPubblicazioneSCP(null);
		body.setUltimaModificaSCP(null);

		// utenteDad.getUtente(programma.getUtenteReferente().getId());
		Utente utenteReferente = programma.getUtenteReferente();
		DatiGeneraliTecnicoEntry referente = new DatiGeneraliTecnicoEntry();
		referente.setNome(utenteReferente.getNome());
		referente.setCognome(utenteReferente.getCognome());
		referente.setCfPiva(utenteReferente.getCodiceFiscale());
		body.setReferente(referente);

		// Acquisti
		List<AcquistoEntry> acquisti = new ArrayList<>();
		body.setAcquisti(acquisti);

		// Acquisti Non annullati
		List<Intervento> listIntNonAnnullati = interventoDad.getInterventoByProgrammaStato(programma.getId(),CpassStatiEnum.INT_CANCELLATO.getCostante(), false);
		
		// dovranno essere ordinati per anno di avvio dell’acquisto e quindi per numero CUI 
		listIntNonAnnullati.sort(new Comparator<Intervento>() {
			public int compare(Intervento i1, Intervento i2) {
				int c = i1.getAnnoAvvio().compareTo(i2.getAnnoAvvio());
				if (c != 0) {
					return c;
				}
				return i1.getCui().compareTo(i2.getCui());
			};
		});
		
		populateAcquisti(programma, acquisti, listIntNonAnnullati);

		// Acquisti NON riproposti
		List<AcquistoNonRipropostoEntry> acquistiNonRiproposti = new ArrayList<>();
		body.setAcquistiNonRiproposti(acquistiNonRiproposti);
		populateAcquistiNonRiproposti(listIntNonAnnullati, acquistiNonRiproposti);

		body.setIdRicevuto(programma.getIdRicevutoMit());
		return body;
	}

	private void populateAcquisti(Programma programma, List<AcquistoEntry> acquisti, List<Intervento> listIntNonAnnullati) {
		final String methodName = "populateAcquisti";
		for (Intervento intervento : listIntNonAnnullati) {
			if (StringUtils.isNotEmpty(intervento.getMotivazioneNonRiproposto())) {
				continue;
			}
			AcquistoEntry acquistoEntry = new AcquistoEntry();
			acquisti.add(acquistoEntry);

			acquistoEntry.setCui(intervento.getCui());

			if (intervento.getSettoreInterventi().getCodice().equalsIgnoreCase(SETTORE_INTERVENTI_FORNITURE)) {
				acquistoEntry.setSettore(SettoreEnum.F);
			} else if (intervento.getSettoreInterventi().getCodice().equalsIgnoreCase(SETTORE_INTERVENTI_SERVIZI)) {
				acquistoEntry.setSettore(SettoreEnum.S);
			} else if (intervento.getSettoreInterventi().getCodice().equalsIgnoreCase(SETTORE_INTERVENTI_LAVORO)) {
				acquistoEntry.setSettore(SettoreEnum.L);
			} else {
				// TODO errore?
			}

			acquistoEntry.setCodiceInterno(null);
			acquistoEntry.setDescrizione(intervento.getDescrizioneAcquisto());
			acquistoEntry.setAnno(intervento.getAnnoAvvio().intValue() == programma.getAnno().intValue() ? 1L:2L);
			acquistoEntry.setEsenteCup(intervento.getCup() == null || intervento.getCup().trim().equals("") ? MIT_TRUE : MIT_FALSE);
			acquistoEntry.setCup(intervento.getCup());

			Long acquistoRicompreso = null;
			try {
				if (intervento.getRicompresoTipo() != null) {
					acquistoRicompreso = Long.valueOf(intervento.getRicompresoTipo().getCodice().trim());
				}
			} catch (Exception e) {
				log.error(methodName, e.getMessage(), e);
			}
			acquistoEntry.setAcquistoRicompreso(acquistoRicompreso);		
			acquistoEntry.setCuiCollegato(intervento.getRicompresoCui() != null ? intervento.getRicompresoCui() : "");
			/*
			if (intervento.getInterventoRicompreso() != null) {
				acquistoEntry.setCuiCollegato(intervento.getInterventoRicompreso().getCui());
			}
			*/
			acquistoEntry.setCpv(intervento.getCpv().getCodice());
			acquistoEntry.setIstat(null);
			acquistoEntry.setNuts(intervento.getNuts().getCodice());
			acquistoEntry.setQuantita(null);
			acquistoEntry.setUnitaMisura(null);

			Long priorita = null;
			try {
				priorita = Long.valueOf(intervento.getPriorita().getCodice());
			} catch (Exception e) {
				log.error(methodName, e.getMessage(), e);
			}
			acquistoEntry.setPriorita(priorita);
			acquistoEntry.setLottoFunzionale(intervento.getLottoFunzionale() == null || !intervento.getLottoFunzionale().booleanValue() ? MIT_FALSE : MIT_TRUE);
			acquistoEntry.setDurataInMesi(Long.valueOf(intervento.getDurataMesi().longValue()));
			acquistoEntry.setNuovoAffidamento(intervento.getNuovoAffidamento() == null || !intervento.getNuovoAffidamento().booleanValue() ? MIT_FALSE : MIT_TRUE);
			InterventoImporti interventoImportiFilter = new InterventoImporti();
			interventoImportiFilter.setIntervento(intervento);
			// PagedList<InterventoImporti> pagedList = interventoImportiDad.getInterventiImporti(interventoImportiFilter, 1, 0);
			// for (InterventoImporti interventoImporti : pagedList.getList()) {
			for (InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
				if (interventoImporti.getRisorsa().getTagTrasmissione() != null) {
					if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_VINCOLATE_PER_LEGGE.getTagTrasmissione())) {
						acquistoEntry.setRisorseVincolatePerLegge1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseVincolatePerLegge2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseVincolatePerLeggeSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_MUTUO.getTagTrasmissione())) {
						acquistoEntry.setRisorseMutuo1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseMutuo2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseMutuoSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_PRIVATI.getTagTrasmissione())) {
						acquistoEntry.setRisorsePrivati1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorsePrivati2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorsePrivatiSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_BILANCIO.getTagTrasmissione())) {
						acquistoEntry.setRisorseBilancio1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseBilancio2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseBilancioSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));	
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_ART3.getTagTrasmissione())) {
						acquistoEntry.setRisorseArt31(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseArt32(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseArt3Succ(toDouble(interventoImporti.getImportoAnniSuccessivi()));	
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_IMMOBILI.getTagTrasmissione())) {
						acquistoEntry.setRisorseImmobili1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseImmobili2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseImmobiliSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));
					} else if (interventoImporti.getRisorsa().getTagTrasmissione().equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_ALTRO.getTagTrasmissione())) {
						acquistoEntry.setRisorseAltro1(toDouble(interventoImporti.getImportoAnnoPrimo()));
						acquistoEntry.setRisorseAltro2(toDouble(interventoImporti.getImportoAnnoSecondo()));
						acquistoEntry.setRisorseAltroSucc(toDouble(interventoImporti.getImportoAnniSuccessivi()));
					}				
				} else if (interventoImporti.getRisorsa().getTipo().equalsIgnoreCase(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
					boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0 
							&& interventoImporti.getImportoAnnoSecondo().signum() == 0
							&& interventoImporti.getImportoAnniSuccessivi().signum() == 0;
					if (!importiTuttiAZero) {
						acquistoEntry.setTipologiaCapitalePrivato(interventoImporti.getRisorsa().getCodice());
					}
				}
			}

			acquistoEntry.setSpeseSostenute(null);
			acquistoEntry.setImportoRisorseFinanziarie(null);
			acquistoEntry.setImportoRisorseFinanziarieRegionali(null);
			acquistoEntry.setImportoRisorseFinanziarieAltro(null);
			acquistoEntry.setMeseAvvioProcedura(null);

			if (intervento.getModalitaAffidamento() != null) {
				acquistoEntry.setDelega(intervento.getModalitaAffidamento().getCodice().equalsIgnoreCase("ND") ? MIT_FALSE : MIT_TRUE);
			}

			if (intervento.getAusa() != null) {
				acquistoEntry.setCodiceSoggettoDelegato(intervento.getAusa().getCodice());
				acquistoEntry.setNomeSoggettoDelegato(intervento.getAusa().getDescrizione());
			}

			Long variato = null;
			try {
				if (intervento.getAcquistoVariato() != null) {
					variato = Long.valueOf(intervento.getAcquistoVariato().getCodice());
				}
			} catch (Exception e) {
				log.error(methodName, e.getMessage(), e);
			}
			acquistoEntry.setVariato(variato);

			acquistoEntry.setNote(null);
			// altri campi "Non valorizzati" saltati

			DatiGeneraliTecnicoEntry rup = new DatiGeneraliTecnicoEntry();
			rup.setNome(intervento.getUtenteRup().getNome());
			rup.setCognome(intervento.getUtenteRup().getCognome());
			rup.setCfPiva(intervento.getUtenteRup().getCodiceFiscale());
			acquistoEntry.setRup(rup);

			acquistoEntry.setImportoCapitalePrivato(null);
			acquistoEntry.setImportoTotale(null);
		}
	}
	
	private void populateAcquistiNonRiproposti(List<Intervento> listIntNonAnnullati,
			List<AcquistoNonRipropostoEntry> acquistiNonRiproposti) {
		final String methodName = "populateAcquistiNonRiproposti";
		for (Intervento intervento : listIntNonAnnullati) {
			// abbiano il campo motivazione_non_riproposto valorizzato
			if (intervento.getMotivazioneNonRiproposto() == null || intervento.getMotivazioneNonRiproposto().trim().equals("")) {
				continue;
			}

			AcquistoNonRipropostoEntry acquistoNonRipropostoEntry = new AcquistoNonRipropostoEntry();
			acquistiNonRiproposti.add(acquistoNonRipropostoEntry);

			acquistoNonRipropostoEntry.setCui(intervento.getCui());
			acquistoNonRipropostoEntry.setCup(intervento.getCup());
			acquistoNonRipropostoEntry.setDescrizione(intervento.getDescrizioneAcquisto());

			BigDecimal importo = BigDecimal.ZERO;
			for (InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
				importo = importo.add(interventoImporti.getImportoAnnoPrimo());
				importo = importo.add(interventoImporti.getImportoAnnoSecondo());
				importo = importo.add(interventoImporti.getImportoAnniSuccessivi());
			}
			acquistoNonRipropostoEntry.setImporto(toDouble(importo));

			Long priorita = null;
			try {
				if (intervento.getPriorita() != null) {
					priorita = Long.valueOf(intervento.getPriorita().getCodice());
				}
			} catch (Exception e) {
				log.error(methodName, e.getMessage(), e);
			}
			acquistoNonRipropostoEntry.setPriorita(priorita);

			acquistoNonRipropostoEntry.setMotivo(intervento.getMotivazioneNonRiproposto());
		}
	}

	private Double toDouble(BigDecimal bd) {
		return Double.valueOf(bd.doubleValue());
	}

	private static class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
		private final ApiClient apiClient;

		/**
		 * Constructor for DateAdapter
		 *
		 * @param apiClient Api client
		 */
		public DateAdapter(ApiClient apiClient) {
			super();
			this.apiClient = apiClient;
		}

		/**
		 * Serialize
		 *
		 * @param src       Date
		 * @param typeOfSrc Type
		 * @param context   Json Serialization Context
		 * @return Json Element
		 */
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			if (src == null) {
				return JsonNull.INSTANCE;
			}
			return new JsonPrimitive(apiClient.formatDatetime(src));
		}

		/**
		 * Deserialize
		 *
		 * @param json    Json element
		 * @param date    Type
		 * @param context Json Serialization Context
		 * @return Date
		 * @throw JsonParseException if fail to parse
		 */
		@Override
		public Date deserialize(JsonElement json, Type date, JsonDeserializationContext context) throws JsonParseException {
			String str = json.getAsJsonPrimitive().getAsString();
			try {
				return apiClient.parseDateOrDatetime(str);
			} catch (RuntimeException e) {
				throw new JsonParseException(e);
			}
		}
	}

	/**
	 * Gson TypeAdapter for Joda DateTime type
	 */
	private static class DateTimeTypeAdapter extends TypeAdapter<DateTime> {

		private final DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
		private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		@Override
		public void write(JsonWriter out, DateTime date) throws IOException {
			if (date == null) {
				out.nullValue();
			} else {
//	            out.value(formatter.print(date));

				out.value(sdf.format(date.toDate()));
			}
		}

		@Override
		public DateTime read(JsonReader in) throws IOException {
			switch (in.peek()) {
			case NULL:
				in.nextNull();
				return null;
			default:
				String date = in.nextString();
				return formatter.parseDateTime(date);
//	                return sdf.parse(date);
			}
		}
	}

	/**
	 * Gson TypeAdapter for Joda LocalDate type
	 */
	private static class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

		private final DateTimeFormatter formatter = ISODateTimeFormat.date();

		@Override
		public void write(JsonWriter out, LocalDate date) throws IOException {
			if (date == null) {
				out.nullValue();
			} else {
				out.value(formatter.print(date));
			}
		}

		@Override
		public LocalDate read(JsonReader in) throws IOException {
			switch (in.peek()) {
			case NULL:
				in.nextNull();
				return null;
			default:
				String date = in.nextString();
				return formatter.parseLocalDate(date);
			}
		}
	}

}
