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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostUploadCsvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostUploadCsvResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.SettoreInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.ValidaInterventiCsv;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.custom.AcquistiCsvAdapter;
import it.csi.cpass.cpassbe.lib.dto.custom.AcquistiImportiCsvAdapter;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCsv;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImpCsv;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.ModalitaAffidamento;
import it.csi.cpass.cpassbe.lib.dto.pba.Nuts;
import it.csi.cpass.cpassbe.lib.dto.pba.Priorita;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.dto.pba.RicompresoTipo;
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.dto.pba.TipoProceduraPba;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort.Direction;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Save Interventi from csv
 */
public class PostUploadCsvService extends BaseInterventoService<PostUploadCsvRequest, PostUploadCsvResponse> {

	public static final String GROUP_ELABORAZIONE_ID = "group.elaborazione_id";

	private final DecodificaDad decodificaDad;
	private final ProgrammaDad programmaDad;
	private final UtenteDad utenteDad;
	private final SettoreDad settoreDad;
	private final EnteDad enteDad;
	private final ElaborazioneDad elaborazioneDad;
	private final ElaborazioneTipoDad elaborazioneTipoDad;
	private final ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	private final InterventoImportiDad interventoImportiDad;
	private final CommonDad commonDad;
	private final SystemDad systemDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad
	 * @param decodificaDad
	 * @param programmaDad
	 * @param utenteDad
	 * @param interventoImportiDad
	 * @param enteDad
	 * @param elaborazioneDad
	 * @param elaborazioneMessaggioDad
	 */
	public PostUploadCsvService(
			ConfigurationHelper configurationHelper,
			InterventoDad interventoDad,
			DecodificaDad decodificaDad,
			ProgrammaDad programmaDad,
			UtenteDad utenteDad,
			InterventoImportiDad interventoImportiDad,
			EnteDad enteDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			SettoreDad settoreDad,
			CommonDad commonDad,
			SystemDad systemDad
			) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.programmaDad = programmaDad;
		this.utenteDad = utenteDad;
		this.enteDad = enteDad;
		this.interventoImportiDad = interventoImportiDad;
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.settoreDad = settoreDad;
		this.commonDad = commonDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		//checkNotNull(request.getSeparatore(), "separatore");
		checkNotNull(request.getAnnoProgramma(), "anno programma");
		checkNotNull(request.getAttachmentInterventi(), "file intervento");
		checkNotNull(request.getAttachmentInterventi(), "file Importi intervento");
		checkNotNull(request.getIdEnte(), "ente");
		checkNotNull(request.getVersioneProgramma(), "versione programma");
	}

	@Override
	protected void execute() {
		final String separatore = request.getSeparatore() !=null ? request.getSeparatore() : ";";
		// controllo: Il programma {prima_annualita} - {seconda_annualita} è stato già inserito.
		final Integer annoInizio = request.getAnnoProgramma();
		Integer annoFine   = annoInizio + 1;
		final UUID enteId        = request.getIdEnte();

		// controllo: Il referente indicato non è presente in archivio
		final Ente ente = isEntityPresent(() -> enteDad.getEnteById(enteId), "ente");
		Utente utenteReferente = null;
		final Optional<Utente> optionalUtente = utenteDad.getUtenteByCf(request.getUtenteReferenteCf(),Boolean.FALSE);
		if (optionalUtente.isEmpty()) {
			checkCondition(false, MsgCpassPba.PBAACQE0044.getError());
		} else {
			utenteReferente = optionalUtente.get();
		}

		final Parametro parametroDurataProgramma = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.DURATA_PROGRAMMA.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PBA.getCostante(),enteId );
		if (parametroDurataProgramma != null && parametroDurataProgramma.getValore().equalsIgnoreCase("TRIENNALE")) {
			annoFine   = annoInizio + 2;
		}

		final List<Programma> listaProgramma = programmaDad.getProgrammiByAnnoVersioneEnteStato(annoInizio,annoFine, null, enteId, null , true, new Sort("versione", Direction.ASC));
		int nuovaVersione = request.getVersioneProgramma().intValue() + 1;
		boolean bFound = false;
		for (final Programma programma : listaProgramma) {
			if (bFound) {
				nuovaVersione = programma.getVersione().intValue() + 1;
			}
			if (programma.getVersione().intValue() == request.getVersioneProgramma().intValue()) {
				bFound = Boolean.TRUE;
			}
		}
		checkCondition(!bFound,MsgCpassPba.PBAACQE0042.getError("prima_annualita", annoInizio, "seconda_annualita",annoFine, "versione", request.getVersioneProgramma(),"nuova_versione", nuovaVersione));

		if (response.getApiErrors().size() == 0) {
			// controllo: Per l'anno {anno} mancano alcune versioni precedenti
			int primaVersioneUsabile = 1;
			final Integer maxver = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(annoInizio, request.getIdEnte(), null);
			primaVersioneUsabile = maxver + 1;
			checkCondition(primaVersioneUsabile == request.getVersioneProgramma().intValue()  , MsgCpassPba.PBAACQE0043.getError("anno" ,annoInizio,"versione",request.getVersioneProgramma(), "versioneOk", Integer.valueOf(primaVersioneUsabile)));
		}

		Programma programma = null;
		if (response.getApiErrors().size() == 0) {
			// per evitare javax.persistence.EntityExistsException: A different object with the same identifier value was already associated with the session
			programma = initProgramma(ente, utenteReferente,parametroDurataProgramma);
		}

		final CsvToBean<AcquistiCsvAdapter> iterableAcquistiCsvAdapter = new CsvToBeanBuilder<AcquistiCsvAdapter>(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentInterventi())))
				.withType(AcquistiCsvAdapter.class)
				.withSeparator(separatore.charAt(0))
				.build();

		final CsvToBean<AcquistiImportiCsvAdapter> iterableImportiAcquistiCsvAdapter = new CsvToBeanBuilder<AcquistiImportiCsvAdapter>(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentImportiInterventi())))
				.withType(AcquistiImportiCsvAdapter.class)
				.withSeparator(separatore.charAt(0))
				.build();
		// check file interventi
		checkCondition(iterableAcquistiCsvAdapter != null , MsgCpassPba.PBAACQE0041.getError());
		// check file importi
		checkCondition(iterableImportiAcquistiCsvAdapter != null , MsgCpassPba.PBAACQE0041.getError());

		if (response.getApiErrors().size() == 0) {
			verificaFile(ente, programma, iterableAcquistiCsvAdapter, iterableImportiAcquistiCsvAdapter);
		}
		// cancello il programma inserito
		if (response.getApiErrors().size() != 0) {
			if (programma != null && programma.getId() != null) {
				programmaDad.deleteProgramma(programma.getId());
			}
		}
	}

	/**
	 * @param ente
	 * @param programma
	 */
	protected void aggiornaProgressivoCui(Ente ente, String settore,String cf,Integer annoProgramma) {
		final String chiaveCui = settore + cf + annoProgramma;
		final Integer maxProgressivo = interventoDad.getMaxProgressivoCUIByChiave(chiaveCui);
		commonDad.setProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), chiaveCui, ente,maxProgressivo);
	}

	/**
	 *
	 * @param ente
	 * @param programma
	 * @param iterableAcquistiCsvAdapter
	 * @param iterableImportiAcquistiCsvAdapter
	 */
	private void verificaFile(Ente ente, Programma programma, CsvToBean<AcquistiCsvAdapter> iterableAcquistiCsvAdapter,CsvToBean<AcquistiImportiCsvAdapter> iterableImportiAcquistiCsvAdapter) {
		final Elaborazione elaborazione = inizializzaElaborazione(programma.getId().toString());
		final Map<String, Ausa> cacheAusa = new HashMap<>();
		final Map<String, Cpv> cacheCpv = new HashMap<>();
		final Map<String, AcquistoVariato> cacheAcquistoVariato = new HashMap<>();
		final Map<String, Nuts> cacheNuts = new HashMap<>();
		final Map<String, Priorita> cachePriorita = new HashMap<>();
		final Map<String, ModalitaAffidamento> cacheModalitaAffidamento = new HashMap<>();
		final Map<String, Stato> cacheStato = new HashMap<>();
		final Map<String, RicompresoTipo> cacheRicompresoTipo = new HashMap<>();
		final Map<String, Risorsa> cacheRisorsa = new HashMap<>();
		final Map<String, Utente> cacheUtente = new HashMap<>();
		final Map<String, SettoreInterventi> cacheSettoreInterventi = new HashMap<>();
		final Map<String, Settore> cacheSettore = new HashMap<>();
		// cache interventi CUI per controllo univocita' all'interno del file
		final Map<String, String> cacheInterventiCui = new HashMap<>();
		final Map<String, String> cacheCodiceInterno = new HashMap<>();
		final Map<String, TipoProceduraPba> cacheTipoProceduraPba = new HashMap<>();

		decodificaDad.getAusas().forEach(ausa -> cacheAusa.put(ausa.getCodice().toUpperCase().trim(), ausa));
		decodificaDad.getAcquistiVariati().forEach(acquistoVariato -> cacheAcquistoVariato.put(acquistoVariato.getCodice().toUpperCase().trim(), acquistoVariato));
		decodificaDad.getNuts().forEach(nuts -> cacheNuts.put(nuts.getCodice().toUpperCase().trim(), nuts));
		decodificaDad.getPrioritas().forEach(priorita -> cachePriorita.put(priorita.getCodice().toUpperCase().trim(), priorita));
		decodificaDad.getModalitaAffidamentos().forEach(modalitaAffidamento -> cacheModalitaAffidamento.put(modalitaAffidamento.getCodice().toUpperCase().trim(), modalitaAffidamento));
		decodificaDad.getStatosByTipo(CpassEnum.INTERVENTO.getCostante()).forEach(stato -> cacheStato.put(stato.getCodice().toUpperCase().trim(), stato));
		decodificaDad.getCpvs().forEach(cpv -> cacheCpv.put(cpv.getCodice().toUpperCase().trim(), cpv));
		decodificaDad.getRicompresoTipos().forEach(ricompresoTipo -> cacheRicompresoTipo.put(ricompresoTipo.getCodice().toUpperCase().trim(), ricompresoTipo));
		// metto solo iniziale risorsa_tipo
		decodificaDad.getRisorsas().forEach(risorsa -> cacheRisorsa.put(risorsa.getCodice() + risorsa.getTipo().toUpperCase().trim().substring(0, 1), risorsa));
		utenteDad.getUtenti(0, 0).forEach(utente -> cacheUtente.put(utente.getCodiceFiscale(), utente));
		decodificaDad.getSettoreInterventis().forEach(settoreInterventi -> cacheSettoreInterventi.put(settoreInterventi.getCodice(), settoreInterventi));
		settoreDad.getSettoriByEnteId(ente.getId()).forEach(settore -> cacheSettore.put(settore.getCodice(), settore));
		decodificaDad.getTipoProceduraPbas(ente.getId()).forEach(tipoProceduraPba -> cacheTipoProceduraPba.put(tipoProceduraPba.getCodice().toUpperCase().trim(), tipoProceduraPba));




		final ValidaInterventiCsv valida = new ValidaInterventiCsv();
		final List<ApiError> errori = valida.validaCsvInterventi(iterableAcquistiCsvAdapter
				,elaborazione
				,cacheAusa
				,cacheCpv
				,cacheAcquistoVariato
				,cacheNuts
				,cachePriorita
				,cacheModalitaAffidamento
				,cacheStato
				,cacheRicompresoTipo
				,cacheUtente
				,cacheSettoreInterventi
				,ente.getCodiceFiscale()
				,interventoDad
				,cacheInterventiCui
				,cacheSettore
				,cacheCodiceInterno
				,cacheTipoProceduraPba
				);
		final List<ApiError> erroriImporti = valida.validaCsvInterventiImporti(iterableImportiAcquistiCsvAdapter, elaborazione, cacheRisorsa, ente.getCodiceFiscale(), cacheInterventiCui,cacheCodiceInterno);
		if(errori.size()==0 && erroriImporti.size()==0) { //  && response.getApiErrors().isEmpty()) {
			final List<Intervento> interventi = inizializzaInterventi(valida.getListaInterventoCsv()
					,cacheAusa
					,cacheCpv
					,cacheAcquistoVariato
					,cacheNuts
					,cachePriorita
					,cacheModalitaAffidamento
					,cacheStato
					,cacheRicompresoTipo
					,cacheUtente
					,cacheSettoreInterventi
					,programma
					,cacheSettore
					,cacheTipoProceduraPba
					);
			final Map<String, Intervento> interventiInseriti = new HashMap<>();
			final Map<String, Intervento> interventiInseritiConCuiGenerato = new HashMap<>();
			final List<Intervento> interventiArricchitiDiCui = attribuisciCuiAdInterventiSprovvisti(interventi,  ente,  programma, cacheInterventiCui);
			final List<Intervento> interventos = interventoDad.saveInterventoDaCaricamentoCsv(interventiArricchitiDiCui,CpassThreadLocalContainer.UTENTE_CONNESSO.get());
			//interventos.forEach(intervento -> interventiInseriti.put(intervento.getCui(), intervento));
			for(final Intervento intervento :interventos) {
				if(intervento.getFlagCuiNonGenerato()) {
					interventiInseriti.put(intervento.getCui(), intervento);
				}else {
					// associo gli importi non dal cui ma dal codice interno
					interventiInseritiConCuiGenerato.put(intervento.getListInterventoAltriDati().get(0).getCodiceInterno(), intervento);
				}
			}
			final List<InterventoImporti> interventiImportiFile = inizializzaInterventiImporti(valida.getListaInterventoImpCsv(),cacheRisorsa, interventiInseriti,interventiInseritiConCuiGenerato);
			// inserisco importi a zero
			List<InterventoImporti> interventiImportiZero = new ArrayList<>();
			for (Intervento intervento : interventos) {
				Boolean isImportoNonDaBilancio =Boolean.FALSE;
				for (Risorsa risorsa : cacheRisorsa.values()) {
					InterventoImporti interventoImporti = new InterventoImporti();
					interventoImporti.setImportoAnnoPrimo(new BigDecimal(0));
					interventoImporti.setImportoAnnoSecondo(new BigDecimal(0));
					interventoImporti.setImportoAnnoTerzo(new BigDecimal(0));
					interventoImporti.setImportoAnniSuccessivi(new BigDecimal(0));
					interventoImporti.setRisorsa(risorsa);
					interventoImporti.setIntervento(intervento);
					// aggiorno gli importi presenti nel file
					for (final InterventoImporti interventoImportiFile : interventiImportiFile) {
						if (interventoImporti.getIntervento().getId().equals(interventoImportiFile.getIntervento().getId()) && interventoImporti.getRisorsa().getId().equals(interventoImportiFile.getRisorsa().getId())) {
							interventoImporti = interventoImportiFile;
							isImportoNonDaBilancio = !risorsa.getTipo().equals("BILANCIO");
							break;
						}
					}
					interventiImportiZero.add(interventoImporti);
					//eliminare  cio' che non è BILANCIO con importi!=0
				}

				interventiImportiZero = selezionaImportiBilancioDaInserire(interventiImportiZero, isImportoNonDaBilancio);

			}
			//salvaInterventimporti(interventiImportiZero);
			interventoImportiDad.saveInterventiImportiXCsv(interventiImportiZero);
			concludiElaborazione(elaborazione, CpassEnum.OK.getCostante(),CpassEnum.OK.getCostante(),ente,programma);
		} else {
			concludiElaborazione(elaborazione, CpassEnum.KO.getCostante(),CpassEnum.ERROR.getCostante(),ente,programma);
			for(final ApiError ae: erroriImporti) {
				// ae.setCode("File Importi " + ae.getCode());
				ae.setCode(ae.getCode());
				errori.add(ae);
			}
			for(final ApiError ae: errori) {
				inserisciMessaggioElaborazione(elaborazione,ae);
			}
			response.addApiErrors(errori);
		}
	}

	/**
	 * @param interventiImportiZero
	 * @param isImportoNonDaBilancio
	 */
	protected List<InterventoImporti> selezionaImportiBilancioDaInserire(List<InterventoImporti> interventiImportiZero,Boolean isImportoNonDaBilancio) {
		List<InterventoImporti> ris = interventiImportiZero;
		if(isImportoNonDaBilancio) {
			ris = new ArrayList<>();
			for (final InterventoImporti interventoImportiZero : interventiImportiZero) {
				if(interventoImportiZero.getRisorsa().getTipo().equals("BILANCIO")) {
					ris.add(interventoImportiZero);
				}else {
					if(interventoImportiZero.getImportoAnnoPrimo().compareTo(BigDecimal.ZERO)!=0) {
						ris.add(interventoImportiZero);
					}
				}
			}
		}
		return ris;
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
		elaborazioneMessaggio.setDescrizione(ae.getFullErrorMessage());
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}

	/**
	 * @param ente
	 * @return Programma
	 */
	private Programma initProgramma(Ente ente, Utente utenteReferente,Parametro parametroDurataProgramma) {
		Programma programma = new Programma();
		programma.setAnno(request.getAnnoProgramma());
		programma.setAnnoFineProgramma(request.getAnnoProgramma()+2);
		if (parametroDurataProgramma == null || parametroDurataProgramma.getValore().equalsIgnoreCase("BIENNALE")) {
			programma.setAnnoFineProgramma(request.getAnnoProgramma()+1);
		}
		programma.setDescrizione("Programma - " + programma.getAnno() + "-" + (programma.getAnno().intValue() + 1) + " " + ente.getDenominazione());
		programma.setDescrizioneProvvedimento("");
		programma.setEnte(ente);
		programma.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoProgrammaEnum.BOZZA.getCostante(), CpassEnum.PROGRAMMA.getCostante()), "stato"));
		programma.setVersione(request.getVersioneProgramma());
		programma.setUtenteReferente(utenteReferente);
		programma = programmaDad.saveProgramma(programma);
		return programma;
	}

	/**
	 *
	 * @param listaInterventoImpCsv
	 * @param cacheRisorsa
	 * @param interventiInseriti
	 * @param interventiInseritiConCuiGenerato
	 * @return
	 */
	private List<InterventoImporti> inizializzaInterventiImporti(List<InterventoImpCsv> listaInterventoImpCsv, Map<String, Risorsa> cacheRisorsa, Map<String, Intervento> interventiInseriti, Map<String, Intervento> interventiInseritiConCuiGenerato) {
		final List<InterventoImporti> lista = new ArrayList<>();
		for(final InterventoImpCsv intcsv : listaInterventoImpCsv) {
			final InterventoImporti interventoImporti = new InterventoImporti();
			final BigDecimal importoAnnoPrimo      = NumberUtility.StringToBigDecimal(intcsv.getImportoAnnoPrimo());
			final BigDecimal importoAnnoSecondo    = NumberUtility.StringToBigDecimal(intcsv.getImportoAnnoSecondo());
			final BigDecimal importoAnnoTerzo    = NumberUtility.StringToBigDecimal(intcsv.getImportoAnnoTerzo());
			final BigDecimal importoAnniSuccessivi = NumberUtility.StringToBigDecimal(intcsv.getImportoAnniSuccessivi());
			interventoImporti.setImportoAnnoPrimo(importoAnnoPrimo);
			interventoImporti.setImportoAnnoSecondo(importoAnnoSecondo);
			interventoImporti.setImportoAnnoTerzo(importoAnnoTerzo);
			interventoImporti.setImportoAnniSuccessivi(importoAnniSuccessivi);
			final Risorsa risorsa = cacheRisorsa.get(intcsv.getRisorsaCodice().trim() + intcsv.getRisorsaTipo().trim());
			interventoImporti.setRisorsa(risorsa);
			Intervento intervento = new Intervento();
			if(StringUtils.isNotEmpty(intcsv.getCui())) {
				intervento = interventiInseriti.get(intcsv.getCui().trim());
			}else {
				intervento = interventiInseritiConCuiGenerato.get(intcsv.getCodiceInterno().trim());
			}
			if(intervento!=null) {
				interventoImporti.setIntervento(intervento);
				lista.add(interventoImporti);
			}
		}
		return lista;
	}

	/**
	 *
	 * @param listaInterventoCsv
	 * @param cacheAusa
	 * @param cacheCpv
	 * @param cacheAcquistoVariato
	 * @param cacheNuts
	 * @param cachePriorita
	 * @param cacheModalitaAffidamento
	 * @param cacheStato
	 * @param cacheRicompresoTipo
	 * @param cacheUtente
	 * @param cacheSettoreInterventi
	 * @param programma
	 * @param cacheSettore
	 * @param cacheTipoProceduraPba
	 * @return the interventi
	 */
	private List<Intervento> inizializzaInterventi(List<InterventoCsv> listaInterventoCsv,
			Map<String, Ausa> cacheAusa,
			Map<String, Cpv> cacheCpv,
			Map<String, AcquistoVariato> cacheAcquistoVariato,
			Map<String, Nuts> cacheNuts,
			Map<String, Priorita> cachePriorita,
			Map<String, ModalitaAffidamento> cacheModalitaAffidamento,
			Map<String, Stato> cacheStato,
			Map<String, RicompresoTipo> cacheRicompresoTipo,
			Map<String, Utente> cacheUtente,
			Map<String, SettoreInterventi> cacheSettoreInterventi,
			Programma programma,
			Map<String, Settore> cacheSettore,
			Map<String, TipoProceduraPba> cacheTipoProceduraPba
			) {
		final List<Intervento> lista = new ArrayList<>();

		final UUID enteId     = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Parametro isVistoRagioneria = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),null, enteId);
		final Parametro isVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, enteId);


		for(final InterventoCsv intcsv : listaInterventoCsv) {
			final Intervento intervento = new Intervento();
			intervento.setCui(intcsv.getCui().trim());
			intervento.setAnnoAvvio(Integer.valueOf(intcsv.getAnnoAvvio().trim()));
			intervento.setCup(intcsv.getCup()==null ? "" :intcsv.getCup().trim());
			intervento.setEsenteCup(Boolean.FALSE);
			if(StringUtility.isEmpty(intervento.getCup())) {
				intervento.setEsenteCup(Boolean.TRUE);
			}
			intervento.setLottoFunzionale(intcsv.getLottoFunzionaleSINO().trim().contentEquals("SI"));
			intervento.setDurataMesi(Integer.valueOf(intcsv.getDurataMesi().trim()));
			intervento.setNuovoAffidamento(intcsv.getNuovoAffidamentoSiNo().trim().contentEquals("SI"));

			final String descAcq = StringUtility.stringNoTab(intcsv.getDescrizioneAcquisto());
			intervento.setDescrizioneAcquisto(descAcq);

			final String paddedAusaCodice = StringUtils.leftPad(StringUtils.trimToEmpty(intcsv.getAusaCodice().trim()).toUpperCase(), 10, '0');
			intervento.setAusa(cacheAusa.get(paddedAusaCodice));
			intervento.setUtenteRup(cacheUtente.get(intcsv.getUtenteRupCf().trim()));
			if(intcsv.getInterventoRicompresoCui()!=null && !intcsv.getInterventoRicompresoCui().trim().equals("")) {
				intervento.setRicompresoCui(intcsv.getInterventoRicompresoCui().trim());
			}
			intervento.setInterventoCopia(null);
			intervento.setSettoreInterventi(cacheSettoreInterventi.get(intcsv.getSettoreInterventiFS().trim()));
			intervento.setCpv(cacheCpv.get(intcsv.getCpvCodice().trim()));
			intervento.setProgramma(programma);
			intervento.setNuts(cacheNuts.get(intcsv.getNutsCodice().trim()));
			intervento.setPriorita(cachePriorita.get(intcsv.getPrioritaCodice().trim()));
			intervento.setModalitaAffidamento(cacheModalitaAffidamento.get(intcsv.getModalitaAffidamentoCodice().trim()));
			intervento.setStato(cacheStato.get(intcsv.getStatoCodice().trim()));
			intervento.setAcquistoVariato(null);
			if(intcsv.getAcquistoVariatoCodice()!=null && intcsv.getAcquistoVariatoCodice().trim().length()>0) {
				intervento.setAcquistoVariato(cacheAcquistoVariato.get(intcsv.getAcquistoVariatoCodice().trim()));
			}
			intervento.setRicompresoTipo(cacheRicompresoTipo.get(intcsv.getAcquistoRicompreso().trim()));
			intervento.setSettore(cacheSettore.get(intcsv.getSettoreCodice().trim()));
			intervento.setFlagCuiNonGenerato(intcsv.getFlagCuiNonGenerato());
			//intervento.getListInterventoAltriDati().get(0).setCodiceInterno(intcsv.getCodiceInterno());
			if(!StringUtils.isEmpty(intcsv.getMotivazioneNonRiproposto())) {
				intervento.setMotivazioneNonRiproposto(intcsv.getMotivazioneNonRiproposto());
				intervento.setInterventoCopiaTipo(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante());
			}
			if(StringUtility.isNotEmpty(intcsv.getCodiceInterno()) || StringUtility.isNotEmpty(intcsv.getNote())) {
				final InterventoAltriDati iad = new InterventoAltriDati();
				iad.setCodiceInterno(intcsv.getCodiceInterno()!=null ?intcsv.getCodiceInterno().trim(): "");
				iad.setIntervento(intervento);
				iad.setNote(intcsv.getNote()!=null?intcsv.getNote() : "");
				final List<InterventoAltriDati> listInterventoAltriDati = new ArrayList<>();
				listInterventoAltriDati.add(iad);
				intervento.setListInterventoAltriDati(listInterventoAltriDati);
			}
			intervento.setVistoRagioneria(Boolean.TRUE);
			if(isVistoRagioneria != null && isVistoRagioneria.getValore().equalsIgnoreCase("true")) {
				intervento.setVistoRagioneria(Boolean.FALSE);
			}

			intervento.setVersioneDefinitiva(Boolean.FALSE);
			if(isVersioneDefinitiva == null || isVersioneDefinitiva.getValore().equalsIgnoreCase("false")) {
				intervento.setVersioneDefinitiva(Boolean.TRUE);
			}

			intervento.setUtenteVistoRagioneria(null);
			intervento.setDataVistoRagioneria(null);
			intervento.setCapofila(Boolean.FALSE);
			intervento.setInterventoCapofila(null);
			intervento.setStatoXStorico(intervento.getStato().getCodice()+" (DA FONTE ESTERNA)");

			if (!StringUtils.isBlank(intcsv.getTipoProceduraCodice())){
				intervento.setTipoProceduraPba(cacheTipoProceduraPba.get(intcsv.getTipoProceduraCodice().trim()));
			}
			lista.add(intervento);
		}
		return lista;
	}

	/**
	 *
	 * @param entitaId
	 * @return
	 */
	private Elaborazione inizializzaElaborazione(String entitaId) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setData(new Date());
		elaborazione.setEntitaId(entitaId);
		final Optional<ElaborazioneTipo> elaborazioneTipoOpt = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.CARICAMENTO_FONTE_ESTERNA.getCostante());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoOpt.orElseThrow(() -> new NotFoundException("elab tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		final String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
		elaborazione.setUtente(utenteId);
		elaborazione.setStato(CpassEnum.START.getCostante());
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		elaborazione.setEnte(ente);
		elaborazione.setNumElaborazioneDiGiornata(0);
		elaborazione.setDataElaborazioneDiGiornata("");
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param elaborazione
	 * @param stato
	 * @param esito
	 */
	private void concludiElaborazione(Elaborazione elaborazione,String stato,String esito, Ente ente, Programma programma) {
		elaborazione.setStato(stato);
		elaborazione.setEsito(esito);
		//update in tabella elaborazione
		elaborazioneDad.saveElaborazione(elaborazione);
		//aggiorna i progressivi
		if(stato.equals(CpassEnum.OK.getCostante())) {
			aggiornaProgressivoCui(ente,SettoreInterventiEnum.SERVIZI.getCodice(), ente.getCodiceFiscale(),programma.getAnno());
			aggiornaProgressivoCui(ente,SettoreInterventiEnum.FORNITURE.getCodice(), ente.getCodiceFiscale(),programma.getAnno());
			aggiornaProgressivoCui(ente,SettoreInterventiEnum.SERVIZI.getCodice(), ente.getCodiceFiscale(),programma.getAnno()+1);
			aggiornaProgressivoCui(ente,SettoreInterventiEnum.FORNITURE.getCodice(), ente.getCodiceFiscale(),programma.getAnno()+1);
		}
		log.error("concludiElaborazione", "concludiElaborazione");
	}

	/**
	 *
	 * @param interventi
	 * @param ente
	 * @param programma
	 * @param cacheInterventiCui
	 * @return
	 */
	private List<Intervento> attribuisciCuiAdInterventiSprovvisti(List<Intervento> interventi, Ente ente, Programma programma, Map<String, String> cacheInterventiCui) {
		final List<Intervento> ris = new ArrayList<>();
		for(final Intervento intervento : interventi) {
			if(StringUtils.isEmpty(intervento.getCui())) {
				final String cui = estraiCui(ente, programma, intervento, cacheInterventiCui);
				intervento.setCui(cui);
				intervento.setFlagCuiNonGenerato(Boolean.FALSE);
			}
			ris.add(intervento);
		}
		return ris;
	}

	/**
	 * @param ente
	 * @param programma
	 * @param intervento
	 * @return
	 */
	private String estraiCui(Ente ente, Programma programma, Intervento intervento, Map<String, String> cacheInterventiCui) {
		final String settore = intervento.getSettoreInterventi().getCodice();
		final String cf = ente.getCodiceFiscale();
		final Integer annoProgramma = programma.getAnno();
		final String codice = settore + cf + annoProgramma;
		Integer intProgressivo;
		intProgressivo = commonDad.getProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), codice,  ente);
		final String progressivo = StringUtils.leftPad(String.valueOf(intProgressivo), 5, '0');
		final String cui = codice + progressivo;
		if (!cacheInterventiCui.isEmpty() && cacheInterventiCui.get(cui) != null) {
			estraiCui( ente,  programma,  intervento, cacheInterventiCui);
		}
		return cui;
	}
}
