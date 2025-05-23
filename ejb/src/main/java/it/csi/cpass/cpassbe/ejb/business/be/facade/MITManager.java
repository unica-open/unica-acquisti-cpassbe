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
import java.util.concurrent.TimeUnit;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.ejb3.annotation.TransactionTimeout;
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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
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
	private final SystemDad systemDad;
	private final InterventoDad interventoDad;
	private final InterventoImportiDad interventoImportiDad;
	private final ProgrammaDad programmaDad;
	private final ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	private final ElaborazioneDad elaborazioneDad;
	private final ElaborazioneParametroDad elaborazioneParametroDad;
	private final DecodificaDad decodificaDad;
	private Map<String, String> parametri = null;

	/**
	 *
	 * @param systemDad
	 * @param interventoDad
	 * @param programmaDad
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 */
	public MITManager(SystemDad systemDad,
			InterventoDad interventoDad,
			InterventoImportiDad interventoImportiDad,
			ProgrammaDad programmaDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			ElaborazioneParametroDad elaborazioneParametroDad,
			DecodificaDad decodificaDad) {
		this.systemDad = systemDad;
		this.interventoDad = interventoDad;
		this.interventoImportiDad = interventoImportiDad;
		this.programmaDad = programmaDad;
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.elaborazioneParametroDad = elaborazioneParametroDad;
		this.decodificaDad = decodificaDad;
	}

	/**
	 * Trasmissione
	 *
	 * @param elaborazione the elaborazione
	 * @param programma    the programma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 10)
	public void trasmetti(Elaborazione elaborazione, Programma programma) {
		final String methodName = "trasmetti al MIT";
		try {
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.saveElaborazione(elaborazione);
			log.info(methodName, "In elaborazione - id: " + elaborazione.getId() + " - entitaId: " + elaborazione.getEntitaId());
			//System.out.println(methodName +  "In elaborazione - id: " + elaborazione.getId() + " - entitaId: " + elaborazione.getEntitaId());
			Programma programmaPubblicazione = programma;
			if (programmaPubblicazione == null) {
				final Optional<Programma> optional = programmaDad.getProgramma(UUID.fromString(elaborazione.getEntitaId()));
				programmaPubblicazione = optional.orElseThrow(() -> new NotFoundException("programma"));
			}
			final Stato statoTrasmesso = decodificaDad.getStato(ConstantsCPassStato.StatoProgrammaEnum.TRASMESSO.getCostante(),ConstantsCPassStato.TipoStatoEnum.PROGRAMMA.getCostante());
			programmaPubblicazione.setStato(statoTrasmesso);
			UUID enteId = null;
			if (programmaPubblicazione.getEnte() != null) {
				enteId = programmaPubblicazione.getEnte().getId();
			}
			parametri = systemDad.getParametri(null, AMBIENTE_MIT, enteId);
			//System.out.println(methodName +  "estraggo i parametri");
			final String token = loginMit(elaborazione);
			log.info(methodName, "token --> "+ token);
			//System.out.println("token --> "+ token);
			if (token != null) {
				final ElaborazioneParametro elaborazioneParametro = elaborazioneParametroDad.getParametro(elaborazione.getId(), MODALITA_INVIO);
				final String modalitaInvio = elaborazioneParametro.getValore();
				if (modalitaInvio != null && (modalitaInvio.equals(MODALITA_INVIO_CONTROLLO) || modalitaInvio.equals(MODALITA_INVIO_CONTROLLO_PUBBLICAZIONE))) {
					//pubblicaProgramma(elaborazione, programmaPubblicazione, token, modalitaInvio);
					//System.out.println(methodName +  "PUBBLICO IL PROGRAMMA");
					pubblicaProgramma(elaborazione, programmaPubblicazione,  modalitaInvio);
				} else {
					log.error(methodName, "modalità invio non valida " );
					throw new RuntimeException("modalità invio non valida");
				}
			}
		} catch (final Exception e) {
			log.error(methodName, e.getMessage(), e);
			final String strStacktTrace = ExceptionUtils.getStackTrace(e);
			elaborazione.setEsito("ERRORE");
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.saveElaborazione(elaborazione);
			final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setElaborazione(elaborazione);
			elaborazioneMessaggio.setTipo("E"); // errore
			elaborazioneMessaggio.setDescrizione(strStacktTrace);
			elaborazioneMessaggio.setCode(e.getMessage());
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		}
	}

	private String loginMit(Elaborazione elaborazione) {
		final String methodName = "login";
		final String username = parametri.get(MIT_USERNAME);
		final String password = parametri.get(MIT_PASSWORD);
		final String clientKey = parametri.get(MIT_CLIENT_KEY);
		final String clientId = parametri.get(MIT_CLIENT_ID);
		final String urlWsLogin = parametri.get(MIT_URL_WSLOGIN);
		final AccountApi accountApi = new AccountApi();
		accountApi.getApiClient().setBasePath(urlWsLogin);
		final OkHttpClient httpClient = getHttpClientWithProxy();
		if(httpClient != null) {
			httpClient.setConnectTimeout((long) 10 * 60 * 1000, TimeUnit.MILLISECONDS);
			accountApi.getApiClient().setHttpClient(httpClient);
		}
		String token = null;
		try {
			final LoginResult loginResult = accountApi.accountRestServiceLoginPubblica(username, password, clientKey, clientId);
			if (loginResult != null) {
				token = loginResult.getToken();
			}
		} catch (final ApiException e) {
			// esempio
			// code: 404
			// body: {"esito":false,"error":"not-found"}
			log.error(methodName,"Error. Code: \"" + e.getCode() + "\" - Message: \"" + e.getMessage() + "\" - Body: \"" + e.getResponseBody() + "\"",e);
			//System.out.println("Error. Code: \"" + e.getCode() + "\" - Message: \"" + e.getMessage() + "\" - Body: \"" + e.getResponseBody() + "\"");
			elaborazione.setEsito("" + e.getCode());
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.saveElaborazione(elaborazione);
			final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
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
		final String hostnameProxy = parametri.get(MIT_PROXY_HOSTNAME);
		final String strPortProxy = parametri.get(MIT_PROXY_PORT);
		if (hostnameProxy != null && !hostnameProxy.trim().equals("") && strPortProxy != null && !strPortProxy.trim().equals("")) {
			final int portProxy = Integer.parseInt(strPortProxy);
			final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostnameProxy, portProxy));
			httpClient = new OkHttpClient().setProxy(proxy);
		}
		return httpClient;
	}

	private void pubblicaProgramma(Elaborazione elaborazione, Programma programma,  String modalitaInvio) {
		final String methodName = "pubblicaProgramma";
		ApiClient apiClient = null;
		final String token = loginMit(elaborazione);
		long start = System.currentTimeMillis();
		try {
			final PubblicaProgrammaFornitureServiziEntry body = populateProgrammaEntry(programma);
			final ProgrammiApi programmiApi = new ProgrammiApi();
			apiClient  = new ApiClientCPass();
			// JIRA-720 10 min di timeout
			apiClient.setConnectTimeout(10 * 60 * 1000);
			// TODO: verificare se serve o anche se parametrizzare
			apiClient.getHttpClient().setWriteTimeout((long)10*60*1000, TimeUnit.MILLISECONDS);
			apiClient.getHttpClient().setReadTimeout((long)10*60*1000, TimeUnit.MILLISECONDS);
			// apiClient = programmiApi.getApiClient();
			programmiApi.setApiClient(apiClient);
			final String urlWsProgrammi = parametri.get(MIT_URL_WSPROGRAMMI);
			apiClient.setBasePath(urlWsProgrammi);
			final OkHttpClient httpClient = getHttpClientWithProxy();
			if(httpClient != null) {
				httpClient.setConnectTimeout((long)10*60*1000, TimeUnit.MILLISECONDS);
				programmiApi.getApiClient().setHttpClient(httpClient);
			}
			// ridefiniti gli adapter per errore:
			// Can not deserialize value of type java.util.Date from String "2020-03-25T10:31:19.883+01:00": expected format
			// "dd/MM/yyyy"
			// at [Source: org.glassfish.jersey.message.internal.ReaderInterceptorExecutor$UnCloseableInputStream@2bf783e3; line: 1,
			// column: 180] (through reference chain:
			// it.maggioli.eldasoft.wsprogrammi.vo.programmi.fornitureservizi.PubblicaProgrammaFornitureServiziEntry["dataApprovazione"])
			final GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter(apiClient))
					.registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
					.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
			final Gson gson = gsonBuilder.create();
			apiClient.getJSON().setGson(gson);
			log.info(methodName, "prima della stampa json");
			final String strBody = apiClient.getJSON().serialize(body);
			log.info(methodName, "strBody: " + strBody);
			log.info(methodName, "modalitaInvio: " + modalitaInvio);
			start = System.currentTimeMillis();
			final PubblicazioneResult pubblicazioneResult = programmiApi.programmiRestServicePubblicaFornitureServizi(body, modalitaInvio, token);
			final long stop = System.currentTimeMillis();
			log.info("", "millisecondi per chiamata " + (stop - start));
			final Long idRicevutoMit = pubblicazioneResult.getId();
			if (idRicevutoMit != null) {
				log.info(methodName, "idRicevutoMit: " + idRicevutoMit + " per programma: " + programma.getId());
				// se il record del programma che si sta tramsettendo non ha ancora l'idRicevutoMit
				// if (programma.getIdRicevutoMit() == null) {
				programma.setIdRicevutoMit(idRicevutoMit);
				programma.setDataTrasmissioneMit(new Date());
				programmaDad.updateProgramma(programma);
				//System.out.println(methodName +  "dopo update programma");
				log.info(methodName ,  "dopo update programma");
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
			//elaborazioneDad.updateElaborazione(elaborazione);
			elaborazioneDad.saveElaborazione(elaborazione);

			final List<ValidateEntry> listValidate = pubblicazioneResult.getValidate();
			if (listValidate != null) {
				for (final ValidateEntry validateEntry : listValidate) {
					final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
					elaborazioneMessaggio.setElaborazione(elaborazione);
					elaborazioneMessaggio.setTipo(validateEntry.getTipo());
					elaborazioneMessaggio.setDescrizione(validateEntry.getMessaggio());
					elaborazioneMessaggio.setCode(validateEntry.getNome());
					elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
				}
			}
		} catch (final ApiException e) {
			final long stop = System.currentTimeMillis();
			log.error("ApiException ", "millisecondi per chiamata " + (stop - start));
			//System.out.println("ApiException  " + "millisecondi per chiamata " + (stop - start));
			//esempio
			//code: 401
			//body: {"id":null,"error":"Il token non è valido."}
			log.error(methodName,"Error. Code: \"" + e.getCode() + "\" - Message: \"" + e.getMessage() + "\" - Body: \"" + e.getResponseBody() + "\"",e);
			//Type localVarReturnType = new TypeToken<PubblicazioneResult>(){}.getType();
			//PubblicazioneResult pubblicazioneResult = apiClient.getJSON().deserialize(e.getResponseBody(), localVarReturnType);
			elaborazione.setEsito("" + e.getCode());
			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.ERRORE_ELABORAZIONE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazioneDad.saveElaborazione(elaborazione);
			final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setElaborazione(elaborazione);
			elaborazioneMessaggio.setTipo("E"); // errore
			elaborazioneMessaggio.setDescrizione(e.getMessage() + " - " + e.getResponseBody());
			elaborazioneMessaggio.setCode("" + e.getCode());
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		}
	}

	private PubblicaProgrammaFornitureServiziEntry populateProgrammaEntry(Programma programma) {
		final String methodName="populateProgrammaEntry";
		log.info(methodName, "START");
		//System.out.println(methodName+ " START");
		final PubblicaProgrammaFornitureServiziEntry body = new PubblicaProgrammaFornitureServiziEntry();
		body.setId(programma.getCodiceMit());
		body.setAnno(programma.getAnno().longValue());
		body.setCodiceFiscaleSA(programma.getEnte().getCodiceFiscale());
		// body.setDescrizione(programma.getDescrizione());
		// if (programma.getIdRicevutoMit() != null) {
		if (programma.getVersione() > 1) {
			// Esempio: Programma biennale degli acquisti di forniture e servizi 2020-2021 – Aggiornamento 01/10/2020
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			body.setDescrizione("Programma degli acquisti di forniture e servizi " + programma.getAnno() + "-"+ (programma.getAnnoFineProgramma()) + " - Aggiornamento " + sdf.format(programma.getDataProvvedimento()));
		} else if (programma.getVersione() == 1) {
			// Esempio: Programma biennale degli acquisti di forniture e servizi 2020-2021
			body.setDescrizione("Programma degli acquisti di forniture e servizi " + programma.getAnno() + "-" + (programma.getAnnoFineProgramma()));
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
		final Utente utenteReferente = programma.getUtenteReferente();
		final DatiGeneraliTecnicoEntry referente = new DatiGeneraliTecnicoEntry();
		referente.setNome(utenteReferente.getNome());
		referente.setCognome(utenteReferente.getCognome());
		referente.setCfPiva(utenteReferente.getCodiceFiscale());
		body.setReferente(referente);

		/* sezione codice modificato per spostamento logica al conferma */
		// Acquisti Non annullati (estraggo tutti gli acquisti del programma non cancellati)
		//List<Intervento> listIntNonAnnullati = interventoDad.getInterventoByProgrammaStato(programma.getId(),StatoInterventiEnum.CANCELLATO.getCostante(), false);
		final List<AcquistiDaTrasmettere> listIntNonAnnullati = interventoDad.getAcquistiDaTrasmettereByProgrammaId(programma.getId());

		// scremo in base alla soglia e riordino
		// con la nuova versione la scrematura va fatta a monte
		//List<Intervento> listaScremataDaSoglia = scremoAcquistiInBaseAllaSoglia(programma.getEnte().getId(),listIntNonAnnullati);

		// Acquisti
		//List<AcquistoEntry> listaAcquisti = populateAcquisti(programma, listaScremataDaSoglia);
		final List<AcquistoEntry> listaAcquisti = populateAcquisti(programma, listIntNonAnnullati);

		body.setAcquisti(listaAcquisti);

		// Acquisti NON riproposti
		//List<AcquistoNonRipropostoEntry> acquistiNonRiproposti = populateAcquistiNonRiproposti(listaScremataDaSoglia);
		final List<AcquistoNonRipropostoEntry> acquistiNonRiproposti = populateAcquistiNonRiproposti(listIntNonAnnullati);

		body.setAcquistiNonRiproposti(acquistiNonRiproposti);
		/* FINE sezione codice modificato per spostamento logica al conferma */

		body.setIdRicevuto(programma.getIdRicevutoMit());
		log.info("populateProgrammaEntry", "STOP");
		//System.out.println(methodName+ " STOP");
		return body;
	}

	/**
	 * @param programma
	 * @param methodName
	 * @param listIntNonAnnullati
	 * @return
	 */
	protected List<Intervento> scremoAcquistiInBaseAllaSoglia_old(UUID enteId, List<Intervento> listIntNonAnnullati) {
		final String methodName ="scremoAcquistiInBaseAllaSoglia";
		//nel caso non fosse esplicitato il parametro sul db uso 0 come default come soglia di non invio
		BigDecimal sogliaDiNonInvio = BigDecimal.ZERO;
		final Parametro soglia = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.SOGLIA_DI_NON_INVIO_MIT.getCostante(),null, enteId);
		if(soglia != null && soglia.getValore()!=null && !soglia.getValore().trim().equals("")) {
			log.info(methodName, "");
			sogliaDiNonInvio = new BigDecimal(soglia.getValore());
			log.info(methodName, "soglia del  parametro SOGLIA_DI_NON_INVIO_MIT sul database " + sogliaDiNonInvio);
		}else {
			log.warn(methodName, "*************************************************************************************");
			log.warn(methodName, "soglia di default 0 in assenza del parametro SOGLIA_DI_NON_INVIO_MIT sul database inserire il parametro sul DB");
			log.warn(methodName, "*************************************************************************************");
		}

		final List<Intervento>  listaScremataDaSoglia = new ArrayList<>();
		for(final Intervento interv : listIntNonAnnullati) {
			final BigDecimal importo = clacolaImportocalcolato(interv);
			if(importo.compareTo(sogliaDiNonInvio)>=0) {
				listaScremataDaSoglia.add(interv);
			}else {
				log.info("populateProgrammaEntry", "intervento eliminato "+interv.getCui());
			}
		}

		// dovranno essere ordinati per anno di avvio dell’acquisto e quindi per numero CUI
		listaScremataDaSoglia.sort(new Comparator<Intervento>() {
			@Override
			public int compare(Intervento i1, Intervento i2) {
				final int c = i1.getAnnoAvvio().compareTo(i2.getAnnoAvvio());
				if (c != 0) {return c;}
				return i1.getCui().compareTo(i2.getCui());
			}
		});
		return listaScremataDaSoglia;
	}

	private BigDecimal clacolaImportocalcolato(Intervento interv) {
		BigDecimal importoCalcolato = BigDecimal.ZERO;
		BigDecimal iva = BigDecimal.ZERO;
		/*
		Dato un acquisto, occorre innanzitutto verificare il valore del campo CPASS_T_PBA_INTERVENTO.lotto_funzionale:
		Se esso è FALSE, allora occorre calcolare l’importo come ∑ (CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_primo + CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_secondo +
		CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anni_successivi)
		Se esso è TRUE, allora occorre ricercare tutti gli altri acquisti collegati (cioè gli acquisti che hanno lo stesso capofila_id dell’acquisto che si sta prendendo in considerazione) e sommare gli importi su tutti questi acquisti come ∑ (CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_primo + CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_secondo +
		CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anni_successivi)
		 */
		if(interv.getLottoFunzionale()== null || !interv.getLottoFunzionale()) {
			importoCalcolato = interventoImportiDad.getImportoTotByInterventoId(interv.getId());
			iva = interventoImportiDad.getImportoIvaTotByInterventoId(interv.getId());
		}else {
			if(interv.getInterventoCapofila()==null || interv.getInterventoCapofila().getId() == null) {
				//log.error("calcolaImportocalcolato ", "******************************************************");
				//log.error("calcolaImportocalcolato ", "******************************************************");
				importoCalcolato = interventoImportiDad.getImportoTotByInterventoId(interv.getId());
				iva = interventoImportiDad.getImportoIvaTotByInterventoId(interv.getId());
				log.error("calcolaImportocalcolato ", "intervento con id -->"+ interv.getId() +" presenta flg lotto funzionale a true ma non ha indicazione sul sui intervento capofila" );
				//log.error("calcolaImportocalcolato ", "******************************************************");
				//log.error("calcolaImportocalcolato ", "******************************************************");
			}
			final List<Intervento> listIntervCollegatoCapofila = interventoDad.getInterventiByCapofilaId(interv.getInterventoCapofila().getId(),interv.getProgramma().getId());
			for(final Intervento collegatoCapofila :listIntervCollegatoCapofila) {
				importoCalcolato = importoCalcolato.add(interventoImportiDad.getImportoTotByInterventoId(collegatoCapofila.getId()));
				iva = interventoImportiDad.getImportoIvaTotByInterventoId(collegatoCapofila.getId());
			}
		}

		log.debug("CUI -->"+ interv.getCui() + " importo calcolato ", importoCalcolato);
		log.debug("CUI -->"+ interv.getCui() + " I.V.A.", iva);
		return importoCalcolato.subtract(iva);
	}

	private List<AcquistoEntry> populateAcquisti(Programma programma,  List<AcquistiDaTrasmettere> listaAcquistiDaTrasmettere) {
		final List<AcquistoEntry> listaAcquisti = new ArrayList<>();


		final String methodName = "populateAcquisti";
		String intIdInesameOld = "";
		AcquistoEntry acquistoEntry = null;
		for (final AcquistiDaTrasmettere acquistiDaTrasmettere : listaAcquistiDaTrasmettere) {
			if (StringUtility.isNotEmpty(acquistiDaTrasmettere.getMotivazioneNonRiproposto())) {
				continue;
			}
			final String intIdInesame = acquistiDaTrasmettere.getInterventoId().toString();

			if(!intIdInesame.equalsIgnoreCase(intIdInesameOld)) {
				// Inizio inizializzazione oggetto
				acquistoEntry = new AcquistoEntry();
				if (acquistiDaTrasmettere.getSettoreInterventiCodice().equalsIgnoreCase(SETTORE_INTERVENTI_FORNITURE)) {
					acquistoEntry.setSettore(SettoreEnum.F);
				} else if (acquistiDaTrasmettere.getSettoreInterventiCodice().equalsIgnoreCase(SETTORE_INTERVENTI_SERVIZI)) {
					acquistoEntry.setSettore(SettoreEnum.S);
				} else if (acquistiDaTrasmettere.getSettoreInterventiCodice().equalsIgnoreCase(SETTORE_INTERVENTI_LAVORO)) {
					acquistoEntry.setSettore(SettoreEnum.L);
				} else {
					log.error(methodName, "codice non censito "+acquistiDaTrasmettere.getSettoreInterventiCodice());
					//System.out.println(methodName+ " codice non censito "+intervento.getSettoreInterventi().getCodice());
				}

				Long acquistoRicompreso = null;
				if (StringUtility.isNotEmpty(acquistiDaTrasmettere.getRicompresoTipoCodice())) {
					acquistoRicompreso  = Long.valueOf(acquistiDaTrasmettere.getRicompresoTipoCodice().trim());
				}
				acquistoEntry.setAcquistoRicompreso(acquistoRicompreso);

				Long priorita = null;
				if (StringUtility.isNotEmpty(acquistiDaTrasmettere.getRicompresoTipoCodice())) {
					priorita = Long.valueOf(acquistiDaTrasmettere.getPrioritaCodice().trim());
				}

				Long variato = null;
				if (StringUtility.isNotEmpty(acquistiDaTrasmettere.getAcquistoVariatoCodice())) {
					variato = Long.valueOf(acquistiDaTrasmettere.getAcquistoVariatoCodice());
				}
				acquistoEntry.setVariato(variato);

				if (acquistiDaTrasmettere.getModalitaAffidamento() != null) {
					acquistoEntry.setDelega(acquistiDaTrasmettere.getModalitaAffidamento().equalsIgnoreCase("ND") ? MIT_FALSE : MIT_TRUE);
				}

				if (acquistiDaTrasmettere.getAusaCodice() != null) {
					acquistoEntry.setCodiceSoggettoDelegato(acquistiDaTrasmettere.getAusaCodice());
					acquistoEntry.setNomeSoggettoDelegato(acquistiDaTrasmettere.getAusaDescrizione());
				}
				acquistoEntry.setPriorita(priorita);
				acquistoEntry.setCuiCollegato(StringUtility.isNotEmpty( acquistiDaTrasmettere.getInterventoRicompresoCui()) ? acquistiDaTrasmettere.getInterventoRicompresoCui() : "");
				acquistoEntry.setCpv(acquistiDaTrasmettere.getCpvCodice());
				acquistoEntry.setNuts(acquistiDaTrasmettere.getNutsCodice());
				acquistoEntry.setLottoFunzionale(acquistiDaTrasmettere.getInterventoLottoFunzionale() == null || !acquistiDaTrasmettere.getInterventoLottoFunzionale().booleanValue() ? MIT_FALSE : MIT_TRUE);
				acquistoEntry.setDurataInMesi(acquistiDaTrasmettere.getInterventoDurataMesi().longValue());
				acquistoEntry.setNuovoAffidamento(acquistiDaTrasmettere.getInterventoNuovoAffid() == null || !acquistiDaTrasmettere.getInterventoNuovoAffid().booleanValue() ? MIT_FALSE : MIT_TRUE);
				acquistoEntry.setCui(acquistiDaTrasmettere.getInterventoCui());
				acquistoEntry.setDescrizione(acquistiDaTrasmettere.getInterventoDescrizioneAcquisto());
				acquistoEntry.setAnno(acquistiDaTrasmettere.getInterventoAnnoAvvio().equals(programma.getAnno()) ? 1L:2L);
				acquistoEntry.setEsenteCup(acquistiDaTrasmettere.getInterventoCup() == null || acquistiDaTrasmettere.getInterventoCup().trim().equals("") ? MIT_TRUE : MIT_FALSE);
				acquistoEntry.setCup(acquistiDaTrasmettere.getInterventoCup());

				acquistoEntry.setImportoIva1(NumberUtility.toDouble(acquistiDaTrasmettere.getIvaPrimoAnno()));
				acquistoEntry.setImportoIva2(NumberUtility.toDouble(acquistiDaTrasmettere.getIvaSecondoAnno()));
				acquistoEntry.setImportoIva3(NumberUtility.toDouble(acquistiDaTrasmettere.getIvaTerzoAnno()));
				acquistoEntry.setImportoIvaSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getIvaAnniSuccessivi()));
				acquistoEntry.setNote(acquistiDaTrasmettere.getNote());
				// altri campi "Non valorizzati" saltati
				final DatiGeneraliTecnicoEntry rup = new DatiGeneraliTecnicoEntry();
				rup.setNome(acquistiDaTrasmettere.getRupUtenteNome());
				rup.setCognome(acquistiDaTrasmettere.getRupUtenteCognome());
				rup.setCfPiva(acquistiDaTrasmettere.getRupUtenteCodiceFiscale());
				acquistoEntry.setRup(rup);
				// da verificare se sono effettivamente a null
				acquistoEntry.setIstat(null);
				acquistoEntry.setQuantita(null);
				acquistoEntry.setUnitaMisura(null);
				acquistoEntry.setImportoCapitalePrivato(null);
				//acquistoEntry.setImportoTotale(null);
				final BigDecimal totale = acquistiDaTrasmettere.getImportoAnnoPrimo().add(acquistiDaTrasmettere.getImportoAnnoSecondo().add(acquistiDaTrasmettere.getImportoAnnoTerzo().add(acquistiDaTrasmettere.getImportoAnniSuccessivi())));
				acquistoEntry.setImportoTotale(NumberUtility.toDouble(totale));
				acquistoEntry.setCodiceInterno(null);
				acquistoEntry.setSpeseSostenute(null);
				acquistoEntry.setImportoRisorseFinanziarie(null);
				acquistoEntry.setImportoRisorseFinanziarieRegionali(null);
				acquistoEntry.setImportoRisorseFinanziarieAltro(null);
				acquistoEntry.setMeseAvvioProcedura(null);
				listaAcquisti.add(acquistoEntry);
				// Fine inizializzazione oggetto
			}
			intIdInesameOld = intIdInesame;


			//valorizzo le varie risorse
			final String tagTrasmissione = acquistiDaTrasmettere.getRisorsaTagTrasmissione() != null ? acquistiDaTrasmettere.getRisorsaTagTrasmissione().trim() : "";
			if(tagTrasmissione!=null) {
				if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_VINCOLATE_PER_LEGGE.getTagTrasmissione())) {
					acquistoEntry.setRisorseVincolatePerLegge1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseVincolatePerLegge2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseVincolatePerLegge3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseVincolatePerLeggeSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				}
				else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_MUTUO.getTagTrasmissione())) {
					acquistoEntry.setRisorseMutuo1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseMutuo2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseMutuo3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseMutuoSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				} else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_PRIVATI.getTagTrasmissione())) {
					acquistoEntry.setRisorsePrivati1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorsePrivati2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorsePrivati3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorsePrivatiSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));

					final boolean importiTuttiAZero = acquistiDaTrasmettere.getImportoAnnoPrimo().signum() == 0
							&& acquistiDaTrasmettere.getImportoAnnoSecondo().signum() == 0
							&& acquistiDaTrasmettere.getImportoAnnoTerzo().signum() == 0
							&& acquistiDaTrasmettere.getImportoAnniSuccessivi().signum() == 0;
					if (!importiTuttiAZero) {

						final String codiceRisorsaCapPrivato= interventoDad.getAcquistiDaTrasmettereCapPrivatoByIntgerventoId(acquistiDaTrasmettere.getInterventoId()).get(0).getCodiceRisorsa();

						acquistoEntry.setTipologiaCapitalePrivato(codiceRisorsaCapPrivato);//(acquistiDaTrasmettere.getCodiceRisorsa());
					}

				} else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_BILANCIO.getTagTrasmissione())) {
					acquistoEntry.setRisorseBilancio1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseBilancio2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseBilancio3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseBilancioSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				} else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_ART3.getTagTrasmissione())) {
					acquistoEntry.setRisorseArt31(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseArt32(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseArt33(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseArt3Succ(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				} else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_IMMOBILI.getTagTrasmissione())) {
					acquistoEntry.setRisorseImmobili1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseImmobili2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseImmobili3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseImmobiliSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				} else if (tagTrasmissione.equalsIgnoreCase(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_ALTRO.getTagTrasmissione())) {
					acquistoEntry.setRisorseAltro1(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoPrimo()));
					acquistoEntry.setRisorseAltro2(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoSecondo()));
					acquistoEntry.setRisorseAltro3(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnnoTerzo()));
					acquistoEntry.setRisorseAltroSucc(NumberUtility.toDouble(acquistiDaTrasmettere.getImportoAnniSuccessivi()));
				}
			}
		}
		return listaAcquisti;
	}


	private List<AcquistoNonRipropostoEntry> populateAcquistiNonRiproposti(List<AcquistiDaTrasmettere> listIntNonAnnullati) {
		final String methodName = "populateAcquistiNonRiproposti";
		final List<AcquistoNonRipropostoEntry> acquistiNonRiproposti = new ArrayList<>();
		String intIdInesameOld = "";
		for (final AcquistiDaTrasmettere intervento : listIntNonAnnullati) {
			if (StringUtility.isEmpty(intervento.getMotivazioneNonRiproposto())) {
				continue;
			}
			// abbiamo il campo motivazione_non_riproposto valorizzato
			final String intIdInesame = intervento.getInterventoId().toString();
			if( !intIdInesame.equalsIgnoreCase(intIdInesameOld)) {
				final AcquistoNonRipropostoEntry acquistoNonRipropostoEntry = new AcquistoNonRipropostoEntry();
				acquistoNonRipropostoEntry.setCui(intervento.getInterventoCui());
				acquistoNonRipropostoEntry.setCup(intervento.getInterventoCup());
				acquistoNonRipropostoEntry.setDescrizione(intervento.getInterventoDescrizioneAcquisto());
				BigDecimal importoCalcolato;
				//BigDecimal iva = BigDecimal.ZERO;
				importoCalcolato = interventoImportiDad.getImportoTotByInterventoId(intervento.getInterventoId());
				//iva = interventoImportiDad.getImportoIvaTotByInterventoId(intervento.getInterventoId());
				//acquistoNonRipropostoEntry.setImporto(NumberUtility.toDouble(importoCalcolato.subtract(iva)));
				acquistoNonRipropostoEntry.setImporto(NumberUtility.toDouble(importoCalcolato));
				log.info("populateAcquistiNonRiproposti CUI -->"+ intervento.getInterventoCui() + " importo calcolato ", importoCalcolato);
				//log.info("populateAcquistiNonRiproposti CUI -->"+ intervento.getInterventoCui() + " I.V.A.", iva);
				Long priorita = null;
				try {
					if (StringUtility.isNotEmpty(intervento.getPrioritaCodice())) {
						priorita = Long.valueOf(intervento.getPrioritaCodice());
					}
				} catch (final Exception e) {
					log.error(methodName, e.getMessage(), e);
				}
				acquistoNonRipropostoEntry.setPriorita(priorita);
				acquistoNonRipropostoEntry.setMotivo(intervento.getMotivazioneNonRiproposto());
				acquistiNonRiproposti.add(acquistoNonRipropostoEntry);
			}
			intIdInesameOld = intIdInesame;
		}
		return acquistiNonRiproposti;
	}

	private static class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
		private final ApiClient apiClient;

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
			final String str = json.getAsJsonPrimitive().getAsString();
			try {
				return apiClient.parseDateOrDatetime(str);
			} catch (final RuntimeException e) {
				throw new JsonParseException(e);
			}
		}
	}

	/**
	 * Gson TypeAdapter for Joda DateTime type
	 */
	private static class DateTimeTypeAdapter extends TypeAdapter<DateTime> {

		private final DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
		private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		@Override
		public void write(JsonWriter out, DateTime date) throws IOException {
			if (date == null) {
				out.nullValue();
			} else {
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
				final String date = in.nextString();
				return formatter.parseDateTime(date);
			}
		}
	}

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
				final String date = in.nextString();
				return formatter.parseLocalDate(date);
			}
		}
	}

}
