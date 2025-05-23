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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostUploadCsvRegoleSmistamentoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostUploadCsvRegoleSmistamentoRmsResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.ValidaSmistamentoRmsCsv;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.custom.RegoleSmistamentoRmsCsvAdapter;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Save PostUploadCsvRegoleSmistamentoRmsService
 */
public class PostUploadCsvRegoleSmistamentoRmsService extends BaseRmsService<PostUploadCsvRegoleSmistamentoRmsRequest, PostUploadCsvRegoleSmistamentoRmsResponse> {

	public static final String GROUP_ELABORAZIONE_ID = "group.elaborazione_id";
	private final DecodificaDad decodificaDad;
	private final EnteDad enteDad;
	private final ElaborazioneDad elaborazioneDad;
	private final ElaborazioneTipoDad elaborazioneTipoDad;
	private final ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	private final SettoreDad settoreDad;
	private final TestataOrdineDad testataOrdineDad;
	private final MagazzinoDad magazzinoDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad
	 * @param decodificaDad
	 * @param utenteDad
	 * @param interventoImportiDad
	 * @param enteDad
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 */
	public PostUploadCsvRegoleSmistamentoRmsService(
			ConfigurationHelper configurationHelper,
			RmsDad rmsDad,
			DecodificaDad decodificaDad,
			EnteDad enteDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			SettoreDad settoreDad,
			MagazzinoDad magazzinoDad,
			TestataOrdineDad testataOrdineDad
			) {
		super(configurationHelper, rmsDad);
		this.decodificaDad = decodificaDad;
		this.enteDad = enteDad;
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.settoreDad = settoreDad;
		this.magazzinoDad = magazzinoDad;
		this.testataOrdineDad = testataOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAttachmentCsv(), "file csv regole");
		checkNotNull(request.getIdEnte(), "ente");
		//checkNotNull(request.getSeparatore(), "separatore");
		checkNotNull(request.getSostituisciRegola(), "sostituisci regola");
	}

	@Override
	protected void execute() {
		final String separatore = request.getSeparatore() !=null ? request.getSeparatore() : ";";

		final CsvToBean<RegoleSmistamentoRmsCsvAdapter> iterableCsvAdapter = new CsvToBeanBuilder<RegoleSmistamentoRmsCsvAdapter>(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentCsv())))
				.withType(RegoleSmistamentoRmsCsvAdapter.class)
				.withSeparator(separatore.charAt(0))
				.build();
		final Ente ente = isEntityPresent(() -> enteDad.getEnteById(request.getIdEnte()), "ente");
		final Elaborazione elaborazione = inizializzaElaborazione(request.getIdEnte().toString(),ente);
		checkCondition(iterableCsvAdapter != null , MsgCpassRms.RMSRMSE0025.getError());
		if (response.getApiErrors().size() == 0) {
			verificaFile(ente, iterableCsvAdapter,elaborazione);
		}
	}

	/**
	 *
	 * @param ente
	 * @param iterableCsvAdapter
	 * @param elaborazione
	 */
	private void verificaFile(Ente ente, CsvToBean<RegoleSmistamentoRmsCsvAdapter> iterableCsvAdapter,Elaborazione elaborazione) {

		final Map<String, Ods> cacheOds = new HashMap<>();
		final Map<String, Cpv> cacheCpv = new HashMap<>();
		final Map<String, Settore> cacheSettore = new HashMap<>();
		final Map<String, Sezione> cacheSezione = new HashMap<>();
		final Map<String, Magazzino> cacheMagazzino = new HashMap<>();
		decodificaDad.getCpvs().forEach(cpv -> cacheCpv.put(cpv.getCodice().toUpperCase().trim(), cpv));

		decodificaDad.getOdsByEnteId(ente.getId()).forEach(ods -> cacheOds.put(ods.getCodice().toUpperCase().trim(), ods));
		settoreDad.getSettoriMinimalByEnteId(ente.getId()).forEach(sett -> cacheSettore.put(sett.getCodice().toUpperCase().trim(), sett));

		testataOrdineDad.getSezioniByEnte(ente.getId()).forEach(sez -> cacheSezione.put(sez.getSezioneCodice().toUpperCase().trim(), sez));

		magazzinoDad.getMagazziniByEnteId(ente.getId()).forEach(mag -> cacheMagazzino.put(mag.getCodice().toUpperCase().trim(), mag));

		final ValidaSmistamentoRmsCsv valida = new ValidaSmistamentoRmsCsv();
		final List<ApiError> errori = valida.validaRegoleSmistamentoRmsCsv(iterableCsvAdapter,
				elaborazione,
				ente,
				cacheOds,
				cacheCpv,
				cacheSettore,
				cacheSezione,
				cacheMagazzino
				);
		if(errori.size()==0 ) {
			final List<RegoleSmistamentoRms> listaRegole = valida.getListaRegoleSmistamentoRms();
			inserisciRegole(listaRegole, ente.getId());
			concludiElaborazione(elaborazione, CpassEnum.OK.getCostante(),CpassEnum.OK.getCostante(),ente);
		}else {
			for(final ApiError ae: errori) {
				inserisciMessaggioElaborazione(elaborazione,ae);
			}
			concludiElaborazione(elaborazione, CpassEnum.KO.getCostante(),CpassEnum.ERROR.getCostante(),ente);
			response.addApiErrors(errori);
		}
	}
	/**
	 *
	 * @param listaRegole
	 * @param enteId
	 */
	private void inserisciRegole(List<RegoleSmistamentoRms> listaRegole, UUID enteId) {
		if(request.getSostituisciRegola()) {
			rmsDad.cancellaRegoleSmistamentoRms( enteId);
		}
		for(final RegoleSmistamentoRms regola: listaRegole) {
			rmsDad.saveRegoleSmistamentoRms(regola);
		}
	}

	/**
	 *
	 * @param elaborazione
	 * @param ae
	 */
	private void inserisciMessaggioElaborazione(Elaborazione elaborazione,ApiError ae) {
		final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggio.setTipo(CpassEnum.ERRORE_CARICAMENTO_PROGRAMMA_CSV.getCostante());
		elaborazioneMessaggio.setCode(ae.getCode());
		elaborazioneMessaggio.setDescrizione(ae.getFullErrorMessage(false));
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}

	/**
	 *
	 * @param entitaId
	 * @return Elaborazione
	 */
	private Elaborazione inizializzaElaborazione(String entitaId, Ente ente) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setData(new Date());
		elaborazione.setEntitaId(entitaId);
		final Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.ALIMENTAZIONE_REGOLE_SMISTAMENTO.getCostante());
		elaborazione.setElaborazioneTipo(elaborazioneTipo.orElseThrow(() -> new NotFoundException("elaborazione tipo")));
		final String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
		elaborazione.setUtente(utenteId);
		elaborazione.setStato(CpassEnum.START.getCostante());
		elaborazione.setEnte(ente);
		elaborazione.setNumElaborazioneDiGiornata(0);
		elaborazione.setDataElaborazioneDiGiornata("");
		//inserimento in tabella elaborazione
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param elaborazione
	 * @param stato
	 * @param esito
	 */
	private void concludiElaborazione(Elaborazione elaborazione,String stato,String esito, Ente ente) {
		elaborazione.setStato(stato);
		elaborazione.setEsito(esito);
		//update in tabella elaborazione
		elaborazioneDad.saveElaborazione(elaborazione);
		log.info("concludiElaborazione", "concludiElaborazione");
	}
}



