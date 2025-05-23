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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoConfermatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoConfermatoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.dto.pba.VIntervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Programma
 */
public class PutProgrammaStatoConfermatoService extends BaseProgrammaService<PutProgrammaStatoConfermatoRequest, PutProgrammaStatoConfermatoResponse> {

	private Programma programma;
	private final InterventoDad interventoDad;
	private final SystemDad systemDad;
	private final InterventoImportiDad interventoImportiDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param interventoDad
	 */
	public PutProgrammaStatoConfermatoService(ConfigurationHelper configurationHelper,
			ProgrammaDad programmaDad,
			InterventoDad interventoDad,
			InterventoImportiDad interventoImportiDad,
			SystemDad systemDad) {
		super(configurationHelper, programmaDad);
		this.interventoDad 			= interventoDad;
		this.systemDad 				= systemDad;
		this.interventoImportiDad 	= interventoImportiDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkModel(programma, "programma");
		checkNotNull(programma.getOptlock(), "opt look");
	}

	@Override
	protected void execute() {
		final Programma programmaAttuale = programmaDad.getProgramma(programma.getId()).orElse(null);
		final Utente utente= CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<VIntervento> listInt = interventoDad.getRicercaNativaByProgrammaId(programma.getId());
		final List<UUID> listIntBloccanti = controlliXConfermaProgramma(listInt);

		if (response.getApiErrors().isEmpty()) {
			programmaDad.updateStatoProgramma(request.getProgramma().getId(), StatoProgrammaEnum.CONFERMATO.getCostante(),CpassEnum.PROGRAMMA.getCostante());

			for (final UUID interventoId : listIntBloccanti) {
				final Intervento interventoItem = isEntityPresent(() -> interventoDad.getInterventoOpt(interventoId), "intervento");
				interventoDad.updateStatoIntervento(interventoItem, StatoInterventiEnum.CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante(),utente);
			}
			//TODO riempimento tabelle appoggio
			final List<VIntervento> listaAcqScrematiValidi = listInt.stream().filter(el -> !el.getStatoCodice().equalsIgnoreCase("CANCELLATO")).collect(Collectors.toList());
			caricaTabellePerInvioAlMit(programmaAttuale,listaAcqScrematiValidi);
		}
	}

	/**
	 * @param listInt
	 * @return
	 */
	protected List<UUID> controlliXConfermaProgramma(List<VIntervento> listInt) {
		// Deve controllare che ci sia almeno un acquisto associato al programma
		checkCondition(listInt.size() > 0, MsgCpassPba.PBAPRGE0033.getError());
		/*START CPASS-524*/
		if(listInt != null && listInt.size() > 0) {
			boolean tmp = false;
			for(final VIntervento i : listInt) {
				tmp = i.getStatoCodice().equals(StatoInterventiEnum.CANCELLATO.getCostante());
				if(!tmp) {
					break;
				}
			}
			if(tmp) {
				response.addApiError(MsgCpassPba.PBAPRGE0033.getError());
			}
		}

		// non devono esistere interventi con lotto funzionale  == true senza capofilaId
		StringBuilder listCuiError = new StringBuilder();
		for(final VIntervento acq : listInt) {
			if(acq.getLottoFunzionale() && (acq.getInterventoCapofilaId()==null)){
				if(!CpassEnum.ACQ_NON_RIPROPOSTO.getCostante().equals(acq.getInterventoCopiaTipo())) {
					listCuiError.append(acq.getCui()).append(" ");
				}
			}
		}
		checkCondition(listCuiError.toString().equals(""), MsgCpassPba.PBAACQE0098.getError());

		/*END CPASS-524*/
		// Non devono esistere interventi in "bozza" e in stato "visto" e visto_ragioneria = FALSE
		// List<Intervento> listIntBozza = interventoDad.getInterventoByProgrammaStato(programma.getId());
		//final List<Intervento> listIntBloccanti = interventoDad.getInterventiBloccantiPerConfermaProgramma (programma.getId());
		final List<UUID> listIntBloccanti = interventoDad.getInterventiBloccantiIdPerConfermaProgramma (programma.getId());

		if (request.getIgnoreWarnings() == null || !request.getIgnoreWarnings().booleanValue()) {
			checkCondition(listIntBloccanti.size() == 0, MsgCpassPba.PBAPRGE0002.getError());
		}
		return listIntBloccanti;
	}

	/**
	 *
	 * @param programmaAttuale
	 * @param listIntNonAnnullati
	 */
	private void caricaTabellePerInvioAlMit(Programma programmaAttuale,List<VIntervento> listaAcqScrematiValidi) {
		final List<VIntervento> listaAcqScrematiDaSoglia = scremoAcquistiInBaseAllaSoglia(programmaAttuale.getEnte().getId(),listaAcqScrematiValidi);

		//List<AcquistiDaTrasmettere> listaAcquistiDaTrasmettere = new ArrayList<AcquistiDaTrasmettere>();
		for(final VIntervento asc : listaAcqScrematiDaSoglia) {
			final List<InterventoImporti> listInterventoImporti = interventoImportiDad.getInterventiImportiByInterventoId(asc.getInterventoId());
			for(final InterventoImporti intImp : listInterventoImporti) {
				// TODO traslo ed inserisco i dati sulle tabelle
				final AcquistiDaTrasmettere acquistiDaTrasmettere = new AcquistiDaTrasmettere();

				if(StringUtility.isNotEmpty(asc.getAcquistoVariatoCodice())) {
					acquistiDaTrasmettere.setAcquistoVariatoCodice(asc.getAcquistoVariatoCodice());
					acquistiDaTrasmettere.setAcquistoVariatoDescrizione(asc.getAcquistoVariatoDescrizione());
				}
				if(StringUtility.isNotEmpty(asc.getAusaCodice())) {
					acquistiDaTrasmettere.setAusaCodice(asc.getAusaCodice());
					acquistiDaTrasmettere.setAusaDescrizione(asc.getAusaDescrizione());
				}
				if (StringUtility.isNotEmpty(asc.getModalitaAffidamentoCodice())) {
					acquistiDaTrasmettere.setModalitaAffidamento(asc.getModalitaAffidamentoCodice());
				}
				if (StringUtility.isNotEmpty(asc.getMotivazioneNonRiproposto())) {
					acquistiDaTrasmettere.setMotivazioneNonRiproposto(asc.getMotivazioneNonRiproposto());
				}
				if (StringUtility.isNotEmpty(asc.getRicompresoTipoCodice())) {
					acquistiDaTrasmettere.setRicompresoTipoCodice(asc.getRicompresoTipoCodice());
				}
				if (StringUtility.isNotEmpty(asc.getPrioritaCodice())) {
					acquistiDaTrasmettere.setPrioritaCodice(asc.getPrioritaCodice());
					acquistiDaTrasmettere.setPrioritaDescrizione(asc.getPrioritaDescrizione());
				}
				if (asc.getInterventoCapofilaId()!=null) {
					acquistiDaTrasmettere.setInterventoCapofilaId(asc.getInterventoCapofilaId());
				}
				acquistiDaTrasmettere.setRicompresoTipoConteggioImporti(asc.getRicompresoTipoConteggioImporti());

				acquistiDaTrasmettere.setCpvCodice(asc.getCpvCodice());
				acquistiDaTrasmettere.setCpvDescrizione(asc.getCpvDescrizione());
				acquistiDaTrasmettere.setEnteCodiceFiscale(programmaAttuale.getEnte().getCodiceFiscale());
				acquistiDaTrasmettere.setEnteDenominazione(programmaAttuale.getEnte().getDenominazione());
				acquistiDaTrasmettere.setEnteId(programmaAttuale.getEnte().getId());

				acquistiDaTrasmettere.setEsenteCup(asc.getEsenteCup());
				acquistiDaTrasmettere.setInterventoCopiaTipo(asc.getInterventoCopiaTipo());
				acquistiDaTrasmettere.setInterventoCui(asc.getCui());
				acquistiDaTrasmettere.setInterventoCup(asc.getCup());
				acquistiDaTrasmettere.setInterventoDescrizioneAcquisto(asc.getDescrizioneAcquisto());
				acquistiDaTrasmettere.setInterventoDurataMesi(asc.getDurataMesi());
				acquistiDaTrasmettere.setInterventoId(asc.getInterventoId());
				acquistiDaTrasmettere.setInterventoLottoFunzionale(asc.getLottoFunzionale());
				acquistiDaTrasmettere.setInterventoNuovoAffid(asc.getNuovoAffidamento());
				acquistiDaTrasmettere.setInterventoRicompresoCui(asc.getRicompresoCui());
				acquistiDaTrasmettere.setNutsCodice(asc.getNutsCodice());
				acquistiDaTrasmettere.setNutsDescrizione(asc.getNutsDescrizione());
				acquistiDaTrasmettere.setProgrammaAnno(programmaAttuale.getAnno());
				acquistiDaTrasmettere.setProgrammaAnnoFine(programmaAttuale.getAnnoFineProgramma());
				acquistiDaTrasmettere.setProgrammaId(programmaAttuale.getId());
				acquistiDaTrasmettere.setReferenteCognome(programmaAttuale.getUtenteReferente().getCognome());
				acquistiDaTrasmettere.setReferenteNome(programmaAttuale.getUtenteReferente().getNome());
				acquistiDaTrasmettere.setRupUtenteCodiceFiscale(asc.getUtenteRupCF());
				acquistiDaTrasmettere.setRupUtenteCognome(asc.getUtenteRupCognome());
				acquistiDaTrasmettere.setRupUtenteNome(asc.getUtenteRupNome());

				acquistiDaTrasmettere.setSettoreInterventiCodice(asc.getSettoreInterventiCodice());
				acquistiDaTrasmettere.setSettoreInterventiDescrizione(asc.getSettoreInterventiDescrizione());
				acquistiDaTrasmettere.setInterventoAnnoAvvio(asc.getAnnoAvvio());

				//if(asc.getListInterventoAltriDati() != null && asc.getListInterventoAltriDati().size()>0) {
				//InterventoAltriDati altriDati = asc.getListInterventoAltriDati().get(0);
				acquistiDaTrasmettere.setNote(asc.getAltriDatinote());
				acquistiDaTrasmettere.setIvaAnniSuccessivi(asc.getAltriDatiivaAnniSuccessivi());
				acquistiDaTrasmettere.setIvaPrimoAnno(asc.getAltriDatiivaPrimoAnno());
				acquistiDaTrasmettere.setIvaSecondoAnno(asc.getAltriDatiivaSecondoAnno());
				acquistiDaTrasmettere.setIvaTerzoAnno(asc.getAltriDatiivaTerzoAnno());
				//}

				acquistiDaTrasmettere.setCodiceRisorsa(intImp.getRisorsa().getCodice());
				acquistiDaTrasmettere.setTipologiaRisorsa(intImp.getRisorsa().getTipo());

				acquistiDaTrasmettere.setDescrizioneRisorsa(intImp.getRisorsa().getDescrizione());
				acquistiDaTrasmettere.setRisorsaTagTrasmissione(intImp.getRisorsa().getTagTrasmissione());


				acquistiDaTrasmettere.setImportoAnniSuccessivi(intImp.getImportoAnniSuccessivi());
				acquistiDaTrasmettere.setImportoAnnoPrimo(intImp.getImportoAnnoPrimo());
				acquistiDaTrasmettere.setImportoAnnoSecondo(intImp.getImportoAnnoSecondo());
				acquistiDaTrasmettere.setImportoAnnoTerzo(intImp.getImportoAnnoTerzo());
				if(intImp.getRisorsa().getTagTrasmissione() !=null) {
					//inserisco la riga in cpass_t_pba_acquisti_da_trasmettere
					interventoDad.postAcquistiDaTrasmettere(acquistiDaTrasmettere);
				}else {
					if(intImp.getRisorsa().getTipo().equalsIgnoreCase(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
						final boolean importiTuttiAZero = acquistiDaTrasmettere.getImportoAnnoPrimo().signum() == 0
								&& acquistiDaTrasmettere.getImportoAnnoSecondo().signum() == 0
								&& acquistiDaTrasmettere.getImportoAnnoTerzo().signum() == 0
								&& acquistiDaTrasmettere.getImportoAnniSuccessivi().signum() == 0;
						if (!importiTuttiAZero) {
							final AcquistiCapPrivatiDaTrasmettere acquistiCapPrivatiDaTrasmettere = new AcquistiCapPrivatiDaTrasmettere();
							acquistiCapPrivatiDaTrasmettere.setCodiceRisorsa(intImp.getRisorsa().getCodice());
							acquistiCapPrivatiDaTrasmettere.setDescrizioneRisorsa(intImp.getRisorsa().getDescrizione());
							acquistiCapPrivatiDaTrasmettere.setInterventoId(asc.getInterventoId());
							acquistiCapPrivatiDaTrasmettere.setProgrammaId(programmaAttuale.getId());
							// sommo gli importi
							BigDecimal totaleCP = acquistiDaTrasmettere.getImportoAnnoPrimo();
							totaleCP = totaleCP.add(acquistiDaTrasmettere.getImportoAnnoSecondo());
							totaleCP = totaleCP.add(acquistiDaTrasmettere.getImportoAnnoTerzo());
							totaleCP = totaleCP.add(acquistiDaTrasmettere.getImportoAnniSuccessivi());

							acquistiCapPrivatiDaTrasmettere.setTotCapitalePrivato(totaleCP);

							//inserisco la riga in cpass_t_pba_acquisti_cap_privati_da_trasmettere
							interventoDad.postAcquistiCapPrivatiDaTrasmettere(acquistiCapPrivatiDaTrasmettere );
						}
					}
				}

			}
		}
	}

	private List<VIntervento> scremoAcquistiInBaseAllaSoglia(UUID enteId, List<VIntervento> listIntNonAnnullati) {
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

		final List<VIntervento>  listaScremataDaSoglia = new ArrayList<>();
		for(final VIntervento interv : listIntNonAnnullati) {
			final BigDecimal importo = clacolaImportocalcolato(interv);
			if(interv.getCui().equals("F00514490010201900290")){
				log.info("WAWA", "");
			}
			if(importo.compareTo(sogliaDiNonInvio)>=0) {
				listaScremataDaSoglia.add(interv);
			}else {
				log.info("populateProgrammaEntry", "intervento eliminato "+interv.getCui());
			}
		}

		// dovranno essere ordinati per anno di avvio dell’acquisto e quindi per numero CUI
		/*
		listaScremataDaSoglia.sort(new Comparator<Intervento>() {
			public int compare(Intervento i1, Intervento i2) {
				int c = i1.getAnnoAvvio().compareTo(i2.getAnnoAvvio());
				if (c != 0) {return c;}
				return i1.getCui().compareTo(i2.getCui());
			};
		});
		 */
		return listaScremataDaSoglia;
	}

	private BigDecimal clacolaImportocalcolato(VIntervento interv) {
		BigDecimal importoCalcolato = BigDecimal.ZERO;
		BigDecimal iva = BigDecimal.ZERO;
		/*
		Dato un acquisto, occorre innanzitutto verificare il valore del campo CPASS_T_PBA_INTERVENTO.lotto_funzionale:
		Se esso è FALSE, allora occorre calcolare l’importo come ∑ (CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_primo + CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_secondo +
		CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anni_successivi)
		Se esso è TRUE, allora occorre ricercare tutti gli altri acquisti collegati (cioè gli acquisti che hanno lo stesso capofila_id dell’acquisto che si sta prendendo in considerazione) e sommare gli importi su tutti questi acquisti come ∑ (CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_primo + CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anno_secondo +
		CPASS_T_PBA_IMPORTI_INTERVENTO.importo_anni_successivi)
		 */
		//if(interv.getCui().equals("F00514490010201900290")){
			//log.info("WAWA", iva);
		//}

		if(interv.getLottoFunzionale()== null || !interv.getLottoFunzionale()) {
			importoCalcolato = interventoImportiDad.getImportoTotByInterventoId(interv.getInterventoId());
			iva = interventoImportiDad.getImportoIvaTotByInterventoId(interv.getInterventoId());
		}else {

			if(interv.getInterventoCapofilaId() == null) {
				//log.error("calcolaImportocalcolato ", "******************************************************");
				//log.error("calcolaImportocalcolato ", "******************************************************");
				log.error("calcolaImportocalcolato ", "intervento con id -->"+ interv.getInterventoId() +" presenta flg lotto funzionale a true ma non ha indicazione sul sui intervento capofila" );
				importoCalcolato = interventoImportiDad.getImportoTotByInterventoId(interv.getInterventoId());
				iva = interventoImportiDad.getImportoIvaTotByInterventoId(interv.getInterventoId());
				//log.error("calcolaImportocalcolato ", "******************************************************");
				//log.error("calcolaImportocalcolato ", "******************************************************");
			}
			if(interv.getInterventoCapofilaId() != null) {
				final List<Intervento> listIntervCollegatoCapofila = interventoDad.getInterventiByCapofilaId(interv.getInterventoCapofilaId(),interv.getProgrammaId());
				for(final Intervento collegatoCapofila :listIntervCollegatoCapofila) {
					importoCalcolato = importoCalcolato.add(interventoImportiDad.getImportoTotByInterventoId(collegatoCapofila.getId()));
					iva = iva.add(interventoImportiDad.getImportoIvaTotByInterventoId(collegatoCapofila.getId()));
				}
			}
		}
		log.debug("CUI -->"+ interv.getCui() + " importo calcolato ", importoCalcolato);
		log.debug("CUI -->"+ interv.getCui() + " I.V.A.", iva);
		return importoCalcolato.subtract(iva);
	}
}


