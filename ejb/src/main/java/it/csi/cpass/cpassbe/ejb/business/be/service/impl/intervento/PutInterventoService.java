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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Intervento
 */
public class PutInterventoService extends BaseInterventoService<PutInterventoRequest, PutInterventoResponse> {

	private final DecodificaDad decodificaDad;
	private final ProgrammaDad programmaDad;
	private final InterventoImportiDad interventoImportiDad;
	private Intervento intervento;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param decodificaDad the decodifica DAD
	 * @param programmaDad the programma DAD
	 */
	public PutInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, InterventoImportiDad interventoImportiDad, DecodificaDad decodificaDad, ProgrammaDad programmaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.programmaDad = programmaDad;
		this.interventoImportiDad = interventoImportiDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkModel(request.getIntervento(), "intervento");
		checkNotNull( intervento.getAnnoAvvio(),"anno avvio");
		checkNotNull( intervento.getDurataMesi(),"durata mesi");
		checkNotNull( intervento.getDescrizioneAcquisto(),"descrizione acquisto");
		checkNotNull( intervento.getOptlock(),"opt lock");
		checkModel(intervento.getUtenteRup(), "utente Rup");
		checkModel(intervento.getSettoreInterventi(), "settore interventi");
		checkModel(intervento.getCpv(), "cpv");
		checkModel(intervento.getProgramma(), "programma");
		checkModel(intervento.getNuts(), "nuts");
		checkModel(intervento.getPriorita(), "priorita");
		checkModel(intervento.getModalitaAffidamento(), "modalita affidamento");

		checkCondition(intervento.getListInterventoAltriDati() == null || intervento.getListInterventoAltriDati().isEmpty() || intervento.getListInterventoAltriDati().size() < 2,CoreError.GENERIC_ERROR.getError("error", "La lista di altri dati può contenere un unico elemento"));
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		log.info(methodName, "Start");
		final Utente utente   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		boolean daStoricizzareIlCambioStato = Boolean.FALSE;
		String statoCodice = "";
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(intervento.getId()), "intervento");

		intervento.setCpv(isEntityPresent(() -> decodificaDad.getCpv(intervento.getCpv().getId()), "cpv"));
		intervento.setNuts(isEntityPresent(() -> decodificaDad.getNut(intervento.getNuts().getId()), "nuts"));
		intervento.setSettoreInterventi(isEntityPresent(() -> decodificaDad.getSettoreInterventi(intervento.getSettoreInterventi().getId()), "settore interventi"));
		intervento.setPriorita(isEntityPresent(() -> decodificaDad.getPriorita(intervento.getPriorita().getId()), "priorita"));
		intervento.setModalitaAffidamento(isEntityPresent(() -> decodificaDad.getModalitaAffidamento(intervento.getModalitaAffidamento().getId()), "modalita affidamento"));
		intervento.setProgramma(isEntityPresent(() -> programmaDad.getProgramma(intervento.getProgramma().getId()),"programma"));
		if(intervento.getCup()!=null) {
			intervento.setCup(intervento.getCup().toUpperCase());
		}
		intervento.setStato(isEntityPresent(() -> decodificaDad.getStato(intervento.getStato().getId()), "stato"));
		final String motivazione = intervento.getMotivazioneNonRiproposto();

		if(intervento.getAcquistoVariato()!= null
				&& intervento.getAcquistoVariato().getAcquistiNonRiproposti().equals(Boolean.TRUE)
				&& motivazione!= null
				&& !motivazione.trim().equals("")) {
			intervento.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato"));
			daStoricizzareIlCambioStato = Boolean.TRUE;
			statoCodice = StatoInterventiEnum.CANCELLATO.getCostante();
		}

		if(intervento.getAcquistoVariato()!= null && intervento.getAcquistoVariato().getAcquistiNonRiproposti().equals(Boolean.TRUE) ) {
			intervento.setVersioneDefinitiva(Boolean.TRUE);
		}

		//se non vengono passati i parametri desumo siano false
		initField(intervento.getLottoFunzionale(), () -> intervento.setLottoFunzionale(Boolean.FALSE));
		initField(intervento.getNuovoAffidamento(), () -> intervento.setNuovoAffidamento(Boolean.FALSE));
		initField(intervento.getFlagCuiNonGenerato(), () -> intervento.setFlagCuiNonGenerato(Boolean.FALSE));
		initField(intervento.getCapofila(), () -> intervento.setCapofila(Boolean.FALSE));
		//initField(intervento.getVistoRagioneria(), () -> intervento.setVistoRagioneria(Boolean.FALSE));
		initField(intervento.getVersioneDefinitiva(), () -> intervento.setVersioneDefinitiva(Boolean.TRUE));
		// da testare bene che non vada in loop il salvataggio
		if(intervento.getCapofila()) {
			final Intervento interventoCapofila = new Intervento();
			interventoCapofila.setId(intervento.getId());
			intervento.setInterventoCapofila(interventoCapofila);
		}

		//gestione lotto
		if(intervento.getLottoFunzionale() == null || !intervento.getLottoFunzionale()) {
			intervento.setInterventoCapofila(null);
			intervento.setCapofila(Boolean.FALSE);
		}

		if (intervento.getRicompresoTipo()!=null && intervento.getRicompresoTipo().getDescrizione().toUpperCase().equals("SI")) {
			checkBusinessCondition(controlloFormaleCui(intervento.getRicompresoCui()), MsgCpassPba.PBAACQE0022.getError());
		}
		if(!(intervento.getAcquistoVariato() != null && intervento.getAcquistoVariato().getAcquistiNonRiproposti() != null && intervento.getAcquistoVariato().getAcquistiNonRiproposti().equals(Boolean.TRUE))) {
			checkModifica(intervento,enteId);
		}

		boolean importiTuttiAZero = Boolean.TRUE;
		InterventoImporti interventoImportiPrivati = null;
		// se ho importi passati a null li inizializzo a 0
		final List<InterventoImporti> listaImporti = new ArrayList<>();
		for(final InterventoImporti ii : intervento.getListInterventoImporti()) {
			initField(ii.getImportoAnnoSecondo()   , () -> ii.setImportoAnnoSecondo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoTerzo()   , () -> ii.setImportoAnnoTerzo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoPrimo()     , () -> ii.setImportoAnnoPrimo(BigDecimal.ZERO));
			initField(ii.getImportoAnniSuccessivi(), () -> ii.setImportoAnniSuccessivi(BigDecimal.ZERO));
			if(importiTuttiAZero) {
				importiTuttiAZero = ii.getImportoAnnoPrimo().signum() == 0 && ii.getImportoAnnoSecondo().signum() == 0 && ii.getImportoAnnoTerzo().signum() == 0 && ii.getImportoAnniSuccessivi().signum() == 0;
			}
			listaImporti.add(ii);
			interventoImportiPrivati = getInterventoImportiPrivati(ii, intervento, decodificaDad);
		}

		if (interventoImportiPrivati != null) {
			// togliere vecchio importo capitale privato
			final InterventoImporti interventoImportiFilter = new InterventoImporti();
			interventoImportiFilter.setIntervento(interventoAttuale);
			final List<InterventoImporti> interventoImportis = interventoImportiDad.getInterventiImporti(interventoImportiFilter, 0, 0).getList();

			for (final InterventoImporti interventoImportiOld : interventoImportis) {
				if (interventoImportiOld.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {

					if (interventoImportiOld.getRisorsa().getId().equals(interventoImportiPrivati.getRisorsa().getId())) {
						interventoImportiPrivati.setId(interventoImportiOld.getId());
						interventoImportiPrivati.setOptlock(interventoImportiOld.getOptlock());
					} else {
						interventoImportiDad.deleteNotLogically(interventoImportiOld.getId());
					}
				}
			}

			if (interventoImportiPrivati.getId() != null) {
				interventoImportiPrivati.setIntervento(interventoAttuale);
				interventoImportiDad.updateInterventoImporti(interventoImportiPrivati);
			} else {
				interventoImportiPrivati.setIntervento(interventoAttuale);
				interventoImportiDad.saveInterventoImporti(interventoImportiPrivati);
			}
		}else {
			intervento.setRisorsaIdCapitalePrivato(null);
		}

		intervento.setListInterventoImporti(listaImporti);
		checkBusinessCondition(!importiTuttiAZero, MsgCpassPba.PBAACQA0011.getError());
		/////////////////////////////fine lista CPV///////////////
		//controllo che l'anno di avvio sia o il primo anno del programma o al massimo quello successivo
		//controllo commentato da PATCH-1.2.0
		/*
		int annoAvvio = intervento.getAnnoAvvio().intValue();
		int annoProgramma = intervento.getProgramma().getAnno().intValue();
		int annoProgramma1 = annoProgramma +1;
		checkBusinessCondition((annoAvvio == annoProgramma || annoAvvio == annoProgramma1), MsgCpassPba.PBAACQE0020.getError());
		 */
		intervento = interventoDad.updateIntervento(intervento);
		if(daStoricizzareIlCambioStato) {
			interventoDad.salvaStoricoStatoIntervento( utente,  intervento, statoCodice,Boolean.FALSE);
		}
		log.info(methodName, "Stop");
	}

	private void checkModifica(Intervento interventoDaControllare,UUID enteId) {
		// 	Se la versione del programma è > 1 e l’acquisto era già stato trasmesso in un biennio precedente o in una versione precedente del programma in questione , occorre effettuare i seguenti controlli:
		final Integer versione = interventoDaControllare.getProgramma().getVersione();
		final Integer anno = interventoDaControllare.getProgramma().getAnno();

		if(versione>1) {
			//List<Intervento> interventiOld = interventoDad.findInterventiByCUI(interventoDaControllare.getCui(), enteId).stream().filter((x) -> (!x.getProgramma().getId().equals(interventoDaControllare.getProgramma().getId()))).filter(i -> i.getProgramma().getAnno() < interventoDaControllare.getProgramma().getAnno()).collect(Collectors.toList());
			final List<Intervento> interventiOld = interventoDad.findInterventiOldByCUI(interventoDaControllare.getCui(), anno, versione, enteId);
			boolean isPrimaAnnualitaModificata 		= Boolean.FALSE;
			boolean isTotaleAumentato 				= Boolean.FALSE;
			boolean isTotaleImportiUgualiODiminuiti = Boolean.FALSE;
			boolean isModificaCpvODescrizione 		= Boolean.FALSE;


			if(!StringUtility.isListEmpty(interventiOld)) {
				//escludo dalla lista se stesso
				final Intervento interventoPadre = interventiOld.get(0);

				//Se è stato modificato il valore del campo 11 Prima Annualità ed è stato anticipato (è stato posto uguale al primo anno del biennio)
				//occorre verificare che nel campo 27 Acquisto aggiunto o variato sia stato inserito un valore che ha CPASS_D_PBA_ACQUISTO_VARIATO.controlli = ‘ANNO’,
				//altrimenti occorre esporre il messaggio PBA-ACQ-E0086
				if(interventoDaControllare.getAnnoAvvio()<interventoPadre.getAnnoAvvio()) {
					isPrimaAnnualitaModificata = Boolean.TRUE;
				}
				///////////////////////////////

				//Se sono stati modificati gli importi ed il totale è rimasto inalterato oppure è diminuito, occorre verificare che nel campo 27 Acquisto aggiunto o variato sia stato inserito un valore che ha CPASS_D_PBA_ACQUISTO_VARIATO.controlli = ‘CORREZIONE’, altrimenti occorre esporre il messaggio PBA-ACQ-E0087
				boolean    importiUguali = Boolean.TRUE;
				BigDecimal totaleImportiDaConfrontare = BigDecimal.ZERO;
				BigDecimal totaleImportiPadre = BigDecimal.ZERO;

				//confrontaImporti(interventoDaControllare.getListInterventoImporti(),interventoPadre.getListInterventoImporti(),importiUguali,importiTotaliUguali,totaleDaConfrontare,totalePadre);
				for(final InterventoImporti impDaConfr : interventoDaControllare.getListInterventoImporti()) {
					final InterventoImporti importiPadre = interventoPadre.getListInterventoImporti().stream()
							.filter(x -> x.getRisorsa().getId().equals(impDaConfr.getRisorsa().getId()))
							.collect(Collectors.toList())
							.get(0);
					if(impDaConfr.getImportoAnnoPrimo().compareTo(importiPadre.getImportoAnnoPrimo())!=0) {
						if(importiUguali) {importiUguali = Boolean.FALSE;}
					}
					if(impDaConfr.getImportoAnnoSecondo().compareTo(importiPadre.getImportoAnnoSecondo())!=0) {
						if(importiUguali) {importiUguali = Boolean.FALSE;}
					}
					if(impDaConfr.getImportoAnniSuccessivi().compareTo(importiPadre.getImportoAnniSuccessivi())!=0) {
						if(importiUguali) {importiUguali = Boolean.FALSE;}
					}
					totaleImportiPadre         = totaleImportiPadre.add(importiPadre.getImportoAnnoPrimo()).add(importiPadre.getImportoAnnoSecondo()).add(importiPadre.getImportoAnniSuccessivi()).add(importiPadre.getImportoAnnoTerzo());
					totaleImportiDaConfrontare = totaleImportiDaConfrontare.add(impDaConfr.getImportoAnnoPrimo()).add(impDaConfr.getImportoAnnoSecondo()).add(impDaConfr.getImportoAnniSuccessivi()).add(impDaConfr.getImportoAnnoTerzo());
				}

				if(totaleImportiPadre.compareTo(totaleImportiDaConfrontare) < 0) {
					isTotaleAumentato = Boolean.TRUE;
				}

				if(!importiUguali && (totaleImportiPadre.compareTo(totaleImportiDaConfrontare) >= 0)){
					isTotaleImportiUgualiODiminuiti = Boolean.TRUE;
				}

				////////////////////////////
				//Se sono stati modificati il campo 15 Descrizione Acquisto e/o il campo 19 CPV -Descrizione CPV, occorre verificare che nel campo 27 Acquisto aggiunto o variato sia stato inserito un valore che ha CPASS_D_PBA_ACQUISTO_VARIATO.controlli = ‘CORREZIONE’, altrimenti occorre esporre il messaggio PBA-ACQ-E0091
				if(!interventoDaControllare.getDescrizioneAcquisto().trim().equalsIgnoreCase(interventoPadre.getDescrizioneAcquisto().trim())) {
					isModificaCpvODescrizione = Boolean.TRUE;
				}
				if(!interventoDaControllare.getCpv().getId().equals(interventoPadre.getCpv().getId())) {
					isModificaCpvODescrizione = Boolean.TRUE;
				}

				//inizio gestione errore
				String controllo = "";
				if(interventoDaControllare.getAcquistoVariato()!=null && interventoDaControllare.getAcquistoVariato().getControlli()!= null ) {
					controllo = interventoDaControllare.getAcquistoVariato().getControlli();
				}
				//Anticipo Anno
				if(isPrimaAnnualitaModificata && !isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && !isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("ANNO"))) {
						generaException(MsgCpassPba.PBAACQE0086.getError());
					}
				}
				//Aumento Totali
				if(!isPrimaAnnualitaModificata && isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && !isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("IMPORTI"))) {
						generaException(MsgCpassPba.PBAACQE0087.getError());
					}
				}
				//Modifica importi a totale inalterato
				if(!isPrimaAnnualitaModificata && !isTotaleAumentato && isTotaleImportiUgualiODiminuiti && !isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("CORREZIONE"))) {
						generaException(MsgCpassPba.PBAACQE0087.getError());
					}
				}
				//Modifica cpv e/o descrizione
				if(!isPrimaAnnualitaModificata && !isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("CORREZIONE"))) {
						generaException(MsgCpassPba.PBAACQE0091.getError());
					}
				}
				//Anticipo anno + Aumento Totali
				if(isPrimaAnnualitaModificata && isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && !isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("ANNO") || controllo.equalsIgnoreCase("IMPORTI"))) {
						generaException(MsgCpassPba.PBAACQE0094.getError());
					}
				}
				//Anticipo anno + Modifica importi a totale inalterato
				if(isPrimaAnnualitaModificata && !isTotaleAumentato && isTotaleImportiUgualiODiminuiti && !isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("ANNO"))) {
						generaException(MsgCpassPba.PBAACQE0086.getError());
					}
				}
				//Aumento totali + Modifica cpv e/o descrizione
				if(!isPrimaAnnualitaModificata && isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("IMPORTI"))) {
						generaException(MsgCpassPba.PBAACQE0087.getError());
					}
				}
				//Modifica importi a totale inalterato + Modifica cpv e/o descrizione
				if(!isPrimaAnnualitaModificata && !isTotaleAumentato && isTotaleImportiUgualiODiminuiti && isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("CORREZIONE"))) {
						generaException(MsgCpassPba.PBAACQE0091.getError());
					}
				}
				//Anticipo anno + Modifica importi a totale inalterato + Modifica cpv e/o descrizione
				if(isPrimaAnnualitaModificata && !isTotaleAumentato && isTotaleImportiUgualiODiminuiti && isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("ANNO"))) {
						generaException(MsgCpassPba.PBAACQE0086.getError());
					}
				}
				//Anticipo anno + Aumento Totali + Modifica cpv e/o descrizione
				if(isPrimaAnnualitaModificata && isTotaleAumentato && !isTotaleImportiUgualiODiminuiti && isModificaCpvODescrizione) {
					if(!(controllo.equalsIgnoreCase("ANNO") || controllo.equalsIgnoreCase("IMPORTI"))) {
						generaException(MsgCpassPba.PBAACQE0094.getError());
					}
				}

			}else {
				//Se la versione del programma è > 1 e l’acquisto NON è mai stato trasmesso, occorre verificare che nel campo 27 Acquisto aggiunto o variato sia stato inserito un valore che ha CPASS_D_PBA_ACQUISTO_VARIATO.controlli = ‘INSERT’, altrimenti occorre esporre il messaggio PBA-ACQ-E0092
				if(!(interventoDaControllare.getAcquistoVariato()!=null && interventoDaControllare.getAcquistoVariato().getControlli()!= null && interventoDaControllare.getAcquistoVariato().getControlli().equalsIgnoreCase("INSERT"))) {
					generaException(MsgCpassPba.PBAACQE0092.getError());
				}
			}
		}
	}

}
