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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostUploadCsvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostUploadCsvResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.ValidaInterventiCsv;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
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
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort.Direction;

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
			SettoreDad settoreDad
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
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnnoProgramma(), "anno programma");
		checkNotNull(request.getAttachmentInterventi(), "file intervento");
		checkNotNull(request.getAttachmentInterventi(), "file Importi intervento");
		checkNotNull(request.getIdEnte(), "ente");
		checkNotNull(request.getVersioneProgramma(), "versione programma");
	}

	@Override
	protected void execute() {
		String methodName = "execute";
		
		// controllo: Il programma {prima_annualita} - {seconda_annualita} è stato già inserito.
		List<Programma> listaProgramma = programmaDad.getProgrammiByAnnoVersioneEnteStato(request.getAnnoProgramma(), null, request.getIdEnte(), null , true, new Sort("versione", Direction.ASC));
		int nuovaVersione = request.getVersioneProgramma().intValue() + 1;
		boolean bFound = false;
		for (Programma programma : listaProgramma) {
			if (bFound) {
				nuovaVersione = programma.getVersione().intValue() + 1;
			}
			if (programma.getVersione().intValue() == request.getVersioneProgramma().intValue()) {
				bFound = true;
			}
		}
		checkCondition(!bFound,
				MsgCpassPba.PBAACQE0042.getError("prima_annualita", request.getAnnoProgramma(), "seconda_annualita",
						Integer.valueOf(request.getAnnoProgramma().intValue() + 1), "versione", request.getVersioneProgramma(),
						"nuova_versione", nuovaVersione));

		if (response.getApiErrors().size() == 0) {
			// controllo: Per l'anno {anno} mancano alcune versioni precedenti
			int primaVersioneUsabile = 1;
			Integer maxver = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(request.getAnnoProgramma(), request.getIdEnte(), null);


			primaVersioneUsabile = maxver + 1;
			
			
			checkCondition(primaVersioneUsabile == request.getVersioneProgramma().intValue()  , MsgCpassPba.PBAACQE0043.getError("anno" ,request.getAnnoProgramma(),
					"versione",request.getVersioneProgramma(), "versioneOk", Integer.valueOf(primaVersioneUsabile)));
		}
		
		// controllo: Il referente indicato non è presente in archivio
		Ente ente = isEntityPresent(() -> enteDad.getEnteById(request.getIdEnte()), "ente");
		Utente utenteReferente = null;
		Optional<Utente> optionalUtente = utenteDad.getUtenteByCf(request.getUtenteReferenteCf());
		if (optionalUtente.isEmpty()) {
			checkCondition(false, MsgCpassPba.PBAACQE0044.getError());
		} else {
			utenteReferente = optionalUtente.get();
		}
		
		Programma programma = null;
		if (response.getApiErrors().size() == 0) {
			// per evitare javax.persistence.EntityExistsException: A different object with the same identifier value was already associated with the session
			programma = initProgramma(ente, utenteReferente);
		}
		
		// check file interventi
		BufferedReader brInterventi = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentInterventi())));
		try {
			checkCondition(brInterventi.readLine() != null, MsgCpassPba.PBAACQE0041.getError());
			brInterventi = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentInterventi())));
		} catch (IOException e) {
			log.error(methodName, e);
		}
		
		// check file importi
		BufferedReader brInterventiImp = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentImportiInterventi())));
		try {
			//String line = brInterventiImp.readLine();
			checkCondition(brInterventiImp.readLine() != null, MsgCpassPba.PBAACQE0041.getError());			
			brInterventiImp = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(request.getAttachmentImportiInterventi())));
		} catch (IOException e) {
			log.error(methodName, e);
		}
		
		if (response.getApiErrors().size() == 0) {
			verificaFile(ente, programma, brInterventi, brInterventiImp);
		}
		
		// cancello il programma inserito
		if (response.getApiErrors().size() != 0) {
			if (programma != null && programma.getId() != null) {
				programmaDad.deleteProgramma(programma.getId());
				programmaDad.flush();
				// altrimenti no rollback e il programma risulta inserito
				// throw new BusinessException("ERRORE GENERICO");
				// throw new BusinessException("Errore validazione dati", errori);
			}
		}
	}

	private void verificaFile(Ente ente, Programma programma, BufferedReader brInterventi, BufferedReader brInterventiImp) {
		Elaborazione elaborazione = inizializzaElaborazione(programma.getId().toString());
		
		Map<String, Ausa> cacheAusa = new HashMap<>();
		Map<String, Cpv> cacheCpv = new HashMap<>();
		Map<String, AcquistoVariato> cacheAcquistoVariato = new HashMap<>();
		Map<String, Nuts> cacheNuts = new HashMap<>();
		Map<String, Priorita> cachePriorita = new HashMap<>();
		Map<String, ModalitaAffidamento> cacheModalitaAffidamento = new HashMap<>();
		Map<String, Stato> cacheStato = new HashMap<>();	
		Map<String, RicompresoTipo> cacheRicompresoTipo = new HashMap<>();
		Map<String, Risorsa> cacheRisorsa = new HashMap<>();
		Map<String, Utente> cacheUtente = new HashMap<>();
		Map<String, SettoreInterventi> cacheSettoreInterventi = new HashMap<>();
		Map<String, Settore> cacheSettore = new HashMap<>();
		
		// cache interventi CUI per controllo univocita' all'interno del file
		Map<String, String> cacheInterventi = new HashMap<>();
		
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
		
		settoreDad.getSettori().forEach(settore -> cacheSettore.put(settore.getCodice(), settore));
		
		ValidaInterventiCsv valida = new ValidaInterventiCsv();
		List<ApiError> errori = valida.validaCsvInterventi(brInterventi
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
															,cacheInterventi
															,cacheSettore
															
				);
		
		List<ApiError> erroriImporti = valida.validaCsvInterventiImporti(brInterventiImp, elaborazione, cacheRisorsa, ente.getCodiceFiscale(), cacheInterventi);
					
		if(errori.size()==0 && erroriImporti.size()==0) { //  && response.getApiErrors().isEmpty()) {
			List<Intervento> interventi = inizializzaInterventi(valida.getListaInterventoCsv()
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
					);
			Map<String, Intervento> interventiInseriti = new HashMap<>();
			
			List<Intervento> interventos = salvaInterventi(interventi);
			
			interventos.forEach(intervento -> interventiInseriti.put(intervento.getCui(), intervento));
			
			interventoDad.flush();

			List<InterventoImporti> interventiImportiFile = inizializzaInterventiImporti(valida.getListaInterventoImpCsv(),cacheRisorsa, interventiInseriti);
			
			// inserisco importi a zero
			List<InterventoImporti> interventiImportiZero = new ArrayList<>();
			for (Iterator<Intervento> iterator = interventos.iterator(); iterator.hasNext();) {
				Intervento intervento = iterator.next();

				Iterator<Risorsa> iterRisorsa = cacheRisorsa.values().iterator();
				while (iterRisorsa.hasNext()) {
					Risorsa risorsa = iterRisorsa.next();
					
					InterventoImporti interventoImporti = new InterventoImporti();
					interventoImporti.setImportoAnnoPrimo(new BigDecimal(0));
					interventoImporti.setImportoAnnoSecondo(new BigDecimal(0));
					interventoImporti.setImportoAnniSuccessivi(new BigDecimal(0));
					interventoImporti.setRisorsa(risorsa);
					interventoImporti.setIntervento(intervento);
					
					// aggiorno gli importi presenti nel file
					for (InterventoImporti interventoImportiFile : interventiImportiFile) {
						if (interventoImporti.getIntervento().getId().equals(interventoImportiFile.getIntervento().getId()) 
								&& interventoImporti.getRisorsa().getId().equals(interventoImportiFile.getRisorsa().getId())) {
							interventoImporti = interventoImportiFile;
							break;
						}
					}
					
					interventiImportiZero.add(interventoImporti);
				}
			}			
			salvaInterventimporti(interventiImportiZero);
			concludiElaborazione(elaborazione, CpassEnum.OK.getCostante(),CpassEnum.OK.getCostante());
		} else {
			concludiElaborazione(elaborazione, CpassEnum.KO.getCostante(),CpassEnum.ERROR.getCostante());
			for(ApiError ae: erroriImporti) {
				// ae.setCode("File Importi " + ae.getCode());
				ae.setCode(ae.getCode());
				errori.add(ae);		
			}
			for(ApiError ae: errori) {
				inserisciMessaggioElaborazione(elaborazione,ae);
			}
			response.addApiErrors(errori);		
		}
		
	}

	private void inserisciMessaggioElaborazione(Elaborazione elaborazione,ApiError ae) {
		ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
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
	private Programma initProgramma(Ente ente, Utente utenteReferente) {
		Programma programma = new Programma();
		programma.setAnno(request.getAnnoProgramma());
		programma.setDescrizione("Programma - " + programma.getAnno() + "-" + (programma.getAnno().intValue() + 1) + " " + ente.getDenominazione());
		programma.setDescrizioneProvvedimento("");
		programma.setEnte(ente);
		programma.setStato(isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.PRO_BOZZA.getCostante(), CpassEnum.PROGRAMMA.getCostante()), "stato")); 
		programma.setVersione(request.getVersioneProgramma());
		programma.setUtenteReferente(utenteReferente);
		programma = programmaDad.saveProgramma(programma);
		programmaDad.flush();
		return programma;
	}

	private List<Intervento> salvaInterventi(List<Intervento> interventi) {
		return interventoDad.saveInterventi(interventi);
	}

	private List<InterventoImporti> salvaInterventimporti(List<InterventoImporti> interventiImporti) {
		return interventoImportiDad.saveInterventiImporti(interventiImporti);
	}
	
	private List<InterventoImporti> inizializzaInterventiImporti(List<InterventoImpCsv> listaInterventoImpCsv, Map<String, Risorsa> cacheRisorsa, Map<String, Intervento> interventiInseriti) {
		List<InterventoImporti> lista = new ArrayList<>();
		for(InterventoImpCsv intcsv : listaInterventoImpCsv) {
			InterventoImporti interventoImporti = new InterventoImporti();

			interventoImporti.setImportoAnnoPrimo(new BigDecimal(intcsv.getImportoAnnoPrimo()));
			interventoImporti.setImportoAnnoSecondo(new BigDecimal(intcsv.getImportoAnnoSecondo()));
			interventoImporti.setImportoAnniSuccessivi(new BigDecimal(intcsv.getImportoAnniSuccessivi()));
			
			Risorsa risorsa = cacheRisorsa.get(intcsv.getRisorsaCodice() + intcsv.getRisorsaTipo());
			interventoImporti.setRisorsa(risorsa);
			
			Intervento intervento = interventiInseriti.get(intcsv.getCui());
			interventoImporti.setIntervento(intervento); 
			lista.add(interventoImporti);
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
			Map<String, Settore> cacheSettore
			) {
		List<Intervento> lista = new ArrayList<>();
		for(InterventoCsv intcsv : listaInterventoCsv) {
			Intervento intervento = new Intervento();
			intervento.setCui(intcsv.getCui());
			intervento.setAnnoAvvio(Integer.valueOf(intcsv.getAnnoAvvio()));
			intervento.setCup(intcsv.getCup());
			intervento.setLottoFunzionale(Boolean.valueOf(intcsv.getLottoFunzionaleSINO().contentEquals("SI")));
			intervento.setDurataMesi(Integer.valueOf(intcsv.getDurataMesi()));
			intervento.setNuovoAffidamento(Boolean.valueOf(intcsv.getNuovoAffidamentoSiNo().contentEquals("SI")));
			intervento.setDescrizioneAcquisto(intcsv.getDescrizioneAcquisto());		
			String paddedAusaCodice = StringUtils.leftPad(StringUtils.trimToEmpty(intcsv.getAusaCodice()).toUpperCase(), 10, '0');
			intervento.setAusa(cacheAusa.get(paddedAusaCodice));			
			intervento.setUtenteRup(cacheUtente.get(intcsv.getUtenteRupCf()));			
			//intervento.setInterventoRicompreso(null);//id intervento
			intervento.setInterventoCopia(null);
			intervento.setSettoreInterventi(cacheSettoreInterventi.get(intcsv.getSettoreInterventiFS()));
			intervento.setCpv(cacheCpv.get(intcsv.getCpvCodice()));
			intervento.setProgramma(programma);
			intervento.setNuts(cacheNuts.get(intcsv.getNutsCodice()));
			intervento.setPriorita(cachePriorita.get(intcsv.getPrioritaCodice()));
			intervento.setModalitaAffidamento(cacheModalitaAffidamento.get(intcsv.getModalitaAffidamentoCodice()));
			intervento.setStato(cacheStato.get(intcsv.getStatoCodice()));
			intervento.setAcquistoVariato(cacheAcquistoVariato.get(intcsv.getAcquistoVariatoCodice()));
			intervento.setRicompresoTipo(cacheRicompresoTipo.get(intcsv.getAcquistoRicompreso()));
			intervento.setSettore(cacheSettore.get(intcsv.getSettoreCodice()));
			lista.add(intervento);			
		}
		return lista;
	}
	
	private Elaborazione inizializzaElaborazione(String entitaId) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setData(new Date());
		elaborazione.setEntitaId(entitaId);

		Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad
				.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.CARICAMENTO_FONTE_ESTERNA.getCostante());
		elaborazione.setElaborazioneTipo(elaborazioneTipo.get());
		
		String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
		elaborazione.setUtente(utenteId);

		elaborazione.setStato(CpassEnum.START.getCostante());
		//inserimento in tabella elaborazione
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}
	
	private void concludiElaborazione(Elaborazione elaborazione,String stato,String esito) {
		elaborazione.setStato(stato);
		elaborazione.setEsito(esito);
		//update in tabella elaborazione
		elaborazioneDad.updateElaborazione(elaborazione);
		log.error("concludiElaborazione", "concludiElaborazione");
	}
	
}

