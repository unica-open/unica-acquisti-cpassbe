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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornamentiImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornamentiImpegniResponse;
import it.csi.cpass.cpassbe.ejb.exception.BusinessException;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.FlussoAnomalie;
import it.csi.cpass.cpassbe.lib.dto.FlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetAggiornamentiImpegniService extends BaseBatchService<GetAggiornamentiImpegniRequest, GetAggiornamentiImpegniResponse> {

	private final ImpegnoDad impegnoDad;
	private final FornitoreDad fornitoreDad;
	public static final String IMPORTO_ERRORE = "9999999999999.99";
	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetAggiornamentiImpegniService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ExternalHelperLookup externalHelperLookup,
			ImpegnoDad impegnoDad,
			FornitoreDad fornitoreDad) {
		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.impegnoDad =  impegnoDad;
		this.fornitoreDad =  fornitoreDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		final Calendar calendar = Calendar.getInstance();
		final Integer annoCorrente = calendar.get(Calendar.YEAR);
		final Ente ente = enteDad.getEnteByCodice(request.getEnte()).orElseThrow(() -> new NotFoundException("ente"));
		final Integer limit  = 100;
		Integer offset = 0;
		boolean exit = false;
		int  i = 1;
		final float inizio = System.currentTimeMillis();
		final String sdataOdierna = request.getDataElab(); //da capire come trasformarla
		final Date dataOdierna = stringTodate(sdataOdierna); //da capire perche non funziona
		final Elaborazione elab = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.AGG_IMPEGNO_TMP.getStatoDB(),ente.getCodice(),request.getNumelab(),request.getDataElab());
		try {
			log.info("execute", "Inizio caricamento IMP"  );
			while(!exit) {
				final List<FlussoImpegniEsterni> listImp       = impegnoDad.getFlussoImpegniEsterniElaborabili(ente.getId(),"OK",limit,offset,dataOdierna,request.getNumelab());
				//log.info("execute", "cicli di caricamento --> listImp " + listImp.size());
				elaboraImpegniCaricati(listImp, annoCorrente, ente.getId());
				if (listImp == null || listImp.size()==0) {
					exit = Boolean.TRUE;
				}
				log.info("execute", "cicli di caricamento da  100 --> " + i  );
				i++;
				offset = i * limit;
			}
			log.info("execute", "Fine caricamento IMP"  );
			concludiElaborazione(elab, "OK", ConstantsCPassElaborazione.StatoEnum.AGG_IMPEGNO_TMP.getStatoDB() );

		} catch (final Exception e) {
			log.error("execute", "Exception ",e);
			concludiElaborazione(elab, "KO con errore imprevisto controllare log" , ConstantsCPassElaborazione.StatoEnum.AGG_IMPEGNO_TMP.getStatoDB() );
			//e.printStackTrace();
			throw new BusinessException("Exception aggiorna sub ", e);
		}

		final float fine = System.currentTimeMillis();
		log.info("fine procedura tmpo ", fine - inizio);
	}

	/**
	 *
	 * @param lista
	 * @param annoCorrente
	 * @param enteId
	 */
	private void elaboraImpegniCaricati(List<FlussoImpegniEsterni> lista, Integer annoCorrente, UUID enteId) {

		for(final FlussoImpegniEsterni flussoImpegniEsterni:  lista) {
			/*
			if((flussoImpegniEsterni.getAnnoImpegno().equals("2021") && flussoImpegniEsterni.getNumImpegno().equals("17351") )) {
				log.info("elaboraSubImpegniCaricati", "caso in esame");;
			}
			if(!(flussoImpegniEsterni.getAnnoImpegno().equals("2021") && flussoImpegniEsterni.getNumImpegno().equals("17351") )) {
				continue;
			}
			 */
			//if((flussoImpegniEsterni.getNumImpegno().equals("18095") || flussoImpegniEsterni.getNumImpegno().equals("23138")) && flussoImpegniEsterni.getAnnoImpegno().equals("2023")){
			// elaborazione del dato
			final List<Impegno> listaimpegnoAnnoCorrente   = impegnoDad.getImpegnoByChiaveLogica(annoCorrente  , Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()), Integer.parseInt(flussoImpegniEsterni.getNumImpegno()), enteId);
			final List<Impegno> listaimpegnoAnnoPrecedente = impegnoDad.getImpegnoByChiaveLogica(annoCorrente-1, Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()), Integer.parseInt(flussoImpegniEsterni.getNumImpegno()), enteId);
			Impegno impegnoAnnoCorrente   = new Impegno();
			Impegno impegnoAnnoPrecedente = new Impegno();
			if(listaimpegnoAnnoCorrente.size()>0) {
				impegnoAnnoCorrente = listaimpegnoAnnoCorrente.get(0);
			}
			if(listaimpegnoAnnoPrecedente.size()>0) {
				impegnoAnnoPrecedente = listaimpegnoAnnoPrecedente.get(0);
			}
			aggiornaImpegno(impegnoAnnoCorrente,impegnoAnnoPrecedente,flussoImpegniEsterni, enteId);
			//}

		}
	}

	/**
	 *
	 * @param impegnoAnnoCorrente
	 * @param impegnoAnnoPrecedente
	 * @param flussoImpegniEsterni
	 */
	private void aggiornaImpegno(Impegno impegnoAnnoCorrente, Impegno impegnoAnnoPrecedente,FlussoImpegniEsterni flussoImpegniEsterni,UUID enteId) {
		if(impegnoAnnoCorrente!=null && impegnoAnnoCorrente.getId()!=null) {
			modificaImpegno( impegnoAnnoCorrente,  impegnoAnnoPrecedente, flussoImpegniEsterni, enteId);
		}else {
			if(impegnoAnnoPrecedente!=null && impegnoAnnoPrecedente.getId()!=null) {
				inserisciImpegno(impegnoAnnoPrecedente, flussoImpegniEsterni, enteId);
			}else {
				log.warn("aggiornaImpegno", "impegno scartato non presente ne nell'anno corrente e neppure con l'esercizio precedente " + flussoImpegniEsterni.getAnnoImpegno() +"-"+ flussoImpegniEsterni.getNumImpegno() );
			}
		}
	}

	/**
	 *
	 * @param impegnoAnnoPrecedente
	 * @param flussoImpegniEsterni
	 * @return Impegno inserito nell'anno attuale di esercizio
	 */
	private Impegno inserisciImpegno(Impegno impegnoAnnoPrecedente,FlussoImpegniEsterni flussoImpegniEsterni,UUID enteId) {
		final Calendar calendar = Calendar.getInstance();
		final Integer annoCorrente = calendar.get(Calendar.YEAR);

		final Impegno impegnoNewAnnoCorrente = new Impegno();
		impegnoNewAnnoCorrente.setEnte(impegnoAnnoPrecedente.getEnte());
		impegnoNewAnnoCorrente.setAnnoEsercizio(annoCorrente);
		impegnoNewAnnoCorrente.setAnno(Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()));
		impegnoNewAnnoCorrente.setNumero(Integer.parseInt(flussoImpegniEsterni.getNumImpegno()));

		impegnoNewAnnoCorrente.setDescrizione(flussoImpegniEsterni.getDescImpegno());
		impegnoNewAnnoCorrente.setNumeroArticolo(Integer.parseInt(flussoImpegniEsterni.getCodArticolo()));

		impegnoNewAnnoCorrente.setNumeroCapitolo(Integer.parseInt(flussoImpegniEsterni.getCodCapitolo()));
		impegnoNewAnnoCorrente.setDescrizioneCapitolo(flussoImpegniEsterni.getDescCapitolo()+" - "+ flussoImpegniEsterni.getDescArticolo());

		impegnoNewAnnoCorrente.setAnnoProvvedimento(Integer.parseInt(flussoImpegniEsterni.getAnnoAttoAmministrativo()));
		impegnoNewAnnoCorrente.setNumeroProvvedimento(flussoImpegniEsterni.getNumAttoAmministrativo());

		impegnoNewAnnoCorrente.setSettoreProvvedimento(flussoImpegniEsterni.getCodCdcAttoAmministrativo()== null ? "" : flussoImpegniEsterni.getCodCdcAttoAmministrativo());

		final Fornitore fornitore = recuperaFornitoreByCode(flussoImpegniEsterni.getCodSoggetto().trim(),enteId);
		if(fornitore==null || fornitore.getId() == null) {
			impegnoNewAnnoCorrente.setFornitore(impegnoAnnoPrecedente.getFornitore());
		}else {
			impegnoNewAnnoCorrente.setFornitore(fornitore);
		}


		final BigDecimal importoIniziale = new BigDecimal(flussoImpegniEsterni.getImportoIniziale()== null ? "0" : flussoImpegniEsterni.getImportoIniziale());
		impegnoNewAnnoCorrente.setImportoIniziale(importoIniziale);

		final BigDecimal importoAttuale = new BigDecimal(flussoImpegniEsterni.getImportoAttuale()== null ? "0" : flussoImpegniEsterni.getImportoAttuale());
		impegnoNewAnnoCorrente.setImportoAttuale(importoAttuale);

		impegnoNewAnnoCorrente.setStato(flussoImpegniEsterni.getCodStatoImpegno() == null ? "" : flussoImpegniEsterni.getCodStatoImpegno().substring(0,1));

		final BigDecimal liquidatoAnnoPrecedente = calcolaLiquidatoImpegno( new Impegno(),  impegnoAnnoPrecedente, flussoImpegniEsterni, enteId);
		impegnoNewAnnoCorrente.setLiquidatoAnnoPrecedente(liquidatoAnnoPrecedente);

		impegnoNewAnnoCorrente.setDataModifica(new Date());
		impegnoNewAnnoCorrente.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
		impegnoNewAnnoCorrente.setDataCreazione(new Date());
		impegnoNewAnnoCorrente.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());
		//if(impegnoNewAnnoCorrente.getFornitore()!=null) {
		final Impegno impNew = impegnoDad.saveImpegno(impegnoNewAnnoCorrente);
		//inserisco i sub dell'intervento a partire dai sub dell'intervento copiato
		for(final Subimpegno sub :impegnoAnnoPrecedente.getSubimpegni()) {
			impegnoDad.flush();
			final Subimpegno subAnnoPrec = impegnoDad.getById(sub.getId());
			if(subAnnoPrec!=null) {
				inserisciSubimpegno(impNew, subAnnoPrec,annoCorrente);
			}
		}
		//}
		return impegnoNewAnnoCorrente;
	}

	/**
	 *
	 * @param impNew
	 * @param subimpegnoAnnoPrecedente
	 * @param flussoSubImpegniEsterni
	 * @return Subimpegno
	 */
	private Subimpegno inserisciSubimpegno(Impegno impNew, Subimpegno subimpegnoAnnoPrecedente,Integer annoCorrente) {
		// caso  inserimento da impegno nuovo
		Subimpegno subNew = new Subimpegno();
		subNew = subimpegnoAnnoPrecedente;
		subNew.setId(null);
		subNew.setAnnoEsercizio(annoCorrente);

		subNew.setImpegno(impNew);
		subNew.setDataModifica(new Date());
		subNew.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
		subNew.setDataCreazione(new Date());
		subNew.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());

		final BigDecimal liquidatoAnnoPrecedente = calcolaLiquidatoSub( subNew,  subimpegnoAnnoPrecedente, null);
		subNew.setLiquidatoAnnoPrecedente(liquidatoAnnoPrecedente);

		subNew = impegnoDad.saveSubimpegno(subNew);
		return subNew;
	}


	/**
	 *
	 * @param impegnoAnnoCorrente
	 * @param impegnoAnnoPrecedente
	 * @param flussoImpegniEsterni
	 */
	private void modificaImpegno(Impegno impegnoAnnoCorrente, Impegno impegnoAnnoPrecedente,FlussoImpegniEsterni flussoImpegniEsterni, UUID enteId) {
		final String statoPassato = flussoImpegniEsterni.getCodStatoImpegno() == null ? "" : flussoImpegniEsterni.getCodStatoImpegno().substring(0,1);
		final Fornitore fornitoreCalcolato = recuperaFornitoreByCode(flussoImpegniEsterni.getCodSoggetto().trim(),enteId);

		// procedo alla modifica solo nel caso in cui imp iniziale / imp attuale / stato / fornitore siano cambiati
		final BigDecimal flussoImpAttuale = new BigDecimal(flussoImpegniEsterni.getImportoAttuale());
		final BigDecimal flussoImpIniziale = new BigDecimal(flussoImpegniEsterni.getImportoIniziale());
		log.info("modificaImpegno", "Importo Attuale " + impegnoAnnoCorrente.getImportoAttuale());
		log.info("modificaImpegno", "Importo Iniziale " + impegnoAnnoCorrente.getImportoIniziale());
		log.info("modificaImpegno", "Stato " + impegnoAnnoCorrente.getStato());
		//log.info("modificaImpegno", "fornitore " + impegnoAnnoCorrente.getFornitore().getId());

		if(        impegnoAnnoCorrente.getImportoAttuale().compareTo(flussoImpAttuale)!=0
				|| impegnoAnnoCorrente.getImportoIniziale().compareTo(flussoImpIniziale)!=0
				|| !impegnoAnnoCorrente.getStato().equals(statoPassato)

				|| (fornitoreCalcolato!=null && impegnoAnnoCorrente.getFornitore()==null)
				|| (fornitoreCalcolato!=null && !fornitoreCalcolato.getId().equals(impegnoAnnoCorrente.getFornitore().getId())
				|| (fornitoreCalcolato==null && impegnoAnnoCorrente.getFornitore()!= null)
						)
				){
			final Impegno impegnoModificato = impegnoAnnoCorrente;
			impegnoModificato.setDescrizione(flussoImpegniEsterni.getDescImpegno());
			impegnoModificato.setNumeroArticolo(Integer.parseInt(flussoImpegniEsterni.getCodArticolo()));
			impegnoModificato.setNumeroCapitolo(Integer.parseInt(flussoImpegniEsterni.getCodCapitolo()));
			impegnoModificato.setDescrizioneCapitolo(flussoImpegniEsterni.getDescCapitolo()+" - "+ flussoImpegniEsterni.getDescArticolo());

			impegnoModificato.setFornitore(fornitoreCalcolato);

			final BigDecimal importoIniziale = new BigDecimal(flussoImpegniEsterni.getImportoIniziale());
			impegnoModificato.setImportoIniziale(importoIniziale);
			final BigDecimal importoAttuale = new BigDecimal(flussoImpegniEsterni.getImportoAttuale());
			impegnoModificato.setImportoAttuale(importoAttuale);
			impegnoModificato.setStato(statoPassato);
			impegnoModificato.setSettoreProvvedimento(flussoImpegniEsterni.getCodCdcAttoAmministrativo()== null ? "" : flussoImpegniEsterni.getCodCdcAttoAmministrativo());


			final BigDecimal liquidatoAnnoPrecedente = calcolaLiquidatoImpegno( impegnoAnnoCorrente,  impegnoAnnoPrecedente, flussoImpegniEsterni, enteId );
			impegnoModificato.setLiquidatoAnnoPrecedente(liquidatoAnnoPrecedente);
			impegnoModificato.setDataModifica(new Date());
			impegnoModificato.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
			//il fornitore puo' essere null
			impegnoDad.saveImpegno(impegnoModificato);
		}
	}

	/**
	 *
	 * @param subimpegnoAnnoCorrente
	 * @param subimpegnoAnnoPrecedente
	 * @param flussoSubimpegniEsterni
	 * @return BigDecimal
	 */
	private BigDecimal calcolaLiquidatoSub(Subimpegno subimpegnoAnnoCorrente, Subimpegno subimpegnoAnnoPrecedente, FlussoSubimpegniEsterni flussoSubimpegniEsterni) {
		// TODO Da creare Vedi algoritmo 2.2.4.4Aggiornamento di LIQ_ANNO_PREC di un subimpegno
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		if(subimpegnoAnnoCorrente==null) {
			return BigDecimal.ZERO;
		}

		BigDecimal liqAnnoPrec = subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente() == null ? BigDecimal.ZERO : subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente();

		BigDecimal ordinatoAnnoPrecedente = BigDecimal.ZERO;
		if(subimpegnoAnnoPrecedente!=null && subimpegnoAnnoPrecedente.getId()!=null) {
			ordinatoAnnoPrecedente = impegnoDad.calcolaSubimpegnoOrdinato(subimpegnoAnnoPrecedente.getId(),annoCorrente-1);
		}

		//caso di subimpegni futuri  anno impegno == anno corrente
		if(subimpegnoAnnoCorrente.getAnno() == annoCorrente) {
			if(subimpegnoAnnoPrecedente!=null && subimpegnoAnnoPrecedente.getId()!=null) {
				liqAnnoPrec = ordinatoAnnoPrecedente;
				return liqAnnoPrec;
			}
		}

		if(subimpegnoAnnoCorrente.getAnno().intValue() < annoCorrente) {
			if(subimpegnoAnnoPrecedente== null || subimpegnoAnnoPrecedente.getId()==null) {
				return BigDecimal.ZERO;
			}else {
				final BigDecimal imp1 = subimpegnoAnnoPrecedente.getLiquidatoAnnoPrecedente().add(ordinatoAnnoPrecedente);
				final BigDecimal imp2 = subimpegnoAnnoPrecedente.getImportoAttuale().add(subimpegnoAnnoCorrente.getImportoIniziale());
				liqAnnoPrec = imp1.subtract(imp2);
				if((liqAnnoPrec.compareTo(BigDecimal.ZERO)<0) || (flussoSubimpegniEsterni!=null && liqAnnoPrec.compareTo(new BigDecimal(flussoSubimpegniEsterni.getImportoAttuale()))>0)){
					inserisciAnomaliaSubImpegno( subimpegnoAnnoCorrente,  subimpegnoAnnoPrecedente, flussoSubimpegniEsterni,  liqAnnoPrec);
					liqAnnoPrec = new BigDecimal(IMPORTO_ERRORE);
				}
			}
		}

		return liqAnnoPrec;
	}

	/**
	 *
	 * @param impegnoAnnoCorrente
	 * @param impegnoAnnoPrecedente
	 * @param flussoImpegniEsterni
	 * @return BigDecimal
	 */
	private BigDecimal calcolaLiquidatoImpegno(Impegno impegnoAnnoCorrente, Impegno impegnoAnnoPrecedente,FlussoImpegniEsterni flussoImpegniEsterni, UUID enteId) {
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		BigDecimal liqAnnoPrec = impegnoAnnoCorrente.getLiquidatoAnnoPrecedente() == null ? BigDecimal.ZERO : impegnoAnnoCorrente.getLiquidatoAnnoPrecedente();

		BigDecimal ordinatoAnnoPrecedente = BigDecimal.ZERO;
		if(impegnoAnnoPrecedente!= null && impegnoAnnoPrecedente.getId()!=null) {
			ordinatoAnnoPrecedente = impegnoDad.calcolaOrdinato(impegnoAnnoPrecedente.getId(),annoCorrente-1);
			//ordinatoAnnoPrecedente = impegnoDad.calcolaOrdinatoImpegno(annoCorrente-1,Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()),Integer.parseInt(flussoImpegniEsterni.getNumImpegno()), enteId);
		}

		//caso di impegni futuri  anno impegno == anno corrente
		if(Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()) == annoCorrente) {
			if(impegnoAnnoPrecedente!= null && impegnoAnnoPrecedente.getId()!=null) {
				liqAnnoPrec = ordinatoAnnoPrecedente;
				return liqAnnoPrec;
			}
		}

		if(Integer.parseInt(flussoImpegniEsterni.getAnnoImpegno()) < annoCorrente) {
			//Se il record non viene trovato nell'esercizio precedente impostare LIQ_ANNO_PREC = 0
			if(impegnoAnnoPrecedente== null || impegnoAnnoPrecedente.getId()==null) {
				log.debug("calcolaLiquidatoImpegno ", "caso impegno SENZA residuo da anno precedente");
			}else {
				//caso impegno residuo da anno precedente
				/*
				LIQ_ANNO_PREC = OLD.liq_anno_prec + ordinato anno precedente – (OLD.importo_attuale – importo iniziale anno corrente), dove:
				     ordinato anno precedente = ∑(CPASS_T_ORD_IMPEGNO_ORDINE.importo) per
				         impegno_anno_esercizio = anno corrente – 1
				         impegno_anno = <anno impegno>
				         impegno_numero =<numero impegno>
				     importo iniziale anno corrente = FLUSSO_T_IMPEGNI_ESTERNI.importo_iniziale
				 */
				//OLD.liq_anno_prec + ordinato anno precedente
				final BigDecimal imp1 = impegnoAnnoPrecedente.getLiquidatoAnnoPrecedente().add(ordinatoAnnoPrecedente);
				// OLD.importo_attuale – importo iniziale anno corrente)
				final BigDecimal imp2 = impegnoAnnoPrecedente.getImportoAttuale().subtract(impegnoAnnoCorrente.getImportoIniziale()== null ? BigDecimal.ZERO : impegnoAnnoCorrente.getImportoIniziale());

				liqAnnoPrec = imp1.subtract(imp2);
				/*
				 Nel caso in cui il calcolo del LIQ_ANNO_PREC risultasse essere negativo
				 oppure maggiore di FLUSSO_T_IMPEGNI_ESTERNI.importo_attuale, occorrerà impostarlo uguale
				  a 9999999999999,99 ed inserire un record su 2.2.3.8CPASS_T_FLUSSO_ANOMALIE
				 */
				if((liqAnnoPrec.compareTo(BigDecimal.ZERO)<0) || (liqAnnoPrec.compareTo(new BigDecimal(flussoImpegniEsterni.getImportoAttuale()))>0)){
					inserisciAnomaliaImpegno( impegnoAnnoCorrente,  impegnoAnnoPrecedente, flussoImpegniEsterni,  liqAnnoPrec);
					liqAnnoPrec = new BigDecimal(IMPORTO_ERRORE);
				}

			}
		}
		return liqAnnoPrec;
	}

	/**
	 *
	 * @param impegnoAnnoCorrente
	 * @param impegnoAnnoPrecedente
	 * @param flussoImpegniEsterni
	 * @param liqAnnoPrec
	 */
	private void inserisciAnomaliaImpegno(Impegno impegnoAnnoCorrente, Impegno impegnoAnnoPrecedente,FlussoImpegniEsterni flussoImpegniEsterni,BigDecimal liqAnnoPrec) {
		final FlussoAnomalie flussoAnomalie = new FlussoAnomalie();
		flussoAnomalie.setImpegnoId(impegnoAnnoCorrente.getId());
		flussoAnomalie.setImportoAttuale(impegnoAnnoCorrente.getImportoAttuale());
		flussoAnomalie.setLiqAnnoPrecCalcolato(liqAnnoPrec);
		flussoAnomalie.setImportoAttuale(impegnoAnnoCorrente.getLiquidatoAnnoPrecedente());
		flussoAnomalie.setDataElaborazione(flussoImpegniEsterni.getDataElaborazione());
		impegnoDad.saveFlussoAnomalie(flussoAnomalie);
	}

	/**
	 *
	 * @param subimpegnoAnnoCorrente
	 * @param subimpegnoAnnoPrecedente
	 * @param flussosubImpegniEsterni
	 * @param liqAnnoPrec
	 */
	private void inserisciAnomaliaSubImpegno(Subimpegno subimpegnoAnnoCorrente, Subimpegno subimpegnoAnnoPrecedente, FlussoSubimpegniEsterni flussosubImpegniEsterni, BigDecimal liqAnnoPrec) {
		final FlussoAnomalie flussoAnomalie = new FlussoAnomalie();
		flussoAnomalie.setSubimpegnoId(subimpegnoAnnoCorrente.getId());
		flussoAnomalie.setImportoAttuale(subimpegnoAnnoCorrente.getImportoAttuale());
		flussoAnomalie.setLiqAnnoPrecCalcolato(liqAnnoPrec);
		flussoAnomalie.setImportoAttuale(subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente());
		flussoAnomalie.setDataElaborazione(flussosubImpegniEsterni.getDataElaborazione());
		impegnoDad.saveFlussoAnomalie(flussoAnomalie);
	}

	private Date stringTodate(String data) {
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(data);
		} catch (final ParseException e) {
			return null;
		}
		return date;
	}


	private  Fornitore recuperaFornitoreByCode(String codice,UUID enteId) {
		if(codice==null || codice.trim().equalsIgnoreCase("")) {
			return null;
		}
		final Fornitore fornitore = fornitoreDad.getFornitoreByCodice(codice, enteId);
		if(fornitore!=null) {
			return fornitore;
		}
		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		final Fornitore fornitoreFiltro = new Fornitore();
		fornitoreFiltro.setCodice(codice);
		filtroFornitore.setFornitore(fornitoreFiltro);
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		final ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
		final List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));

		if(fornitori==null || fornitori.size()==0) {
			return null;
		}
		Fornitore forn = fornitori.get(0);
		/*
		String indirizzoConSedime = "";
		if (fornitore.getSedime() != null) {
			indirizzoConSedime += fornitore.getSedime() + " ";
		}
		if (fornitore.getIndirizzo() != null) {
			indirizzoConSedime += fornitore.getIndirizzo() + " ";
		}
		fornitore.setIndirizzo(indirizzoConSedime.trim());
		 */
		/*
		String indirizzoCompleto = "";
		if (forn.getSedime() != null) {
			indirizzoCompleto += forn.getSedime() + " ";
		}
		if (forn.getIndirizzo() != null) {
			indirizzoCompleto += forn.getIndirizzo() + " ";
		}
		if (forn.getNumeroCivico() != null) {
			indirizzoCompleto += forn.getNumeroCivico();
		}
		forn.setIndirizzo(indirizzoCompleto.trim());
		 */
		final Ente ente = new Ente();
		ente.setId(enteId);
		forn.setEnte(ente);

		forn = fornitoreDad.insert(forn);
		return forn;
	}
}
