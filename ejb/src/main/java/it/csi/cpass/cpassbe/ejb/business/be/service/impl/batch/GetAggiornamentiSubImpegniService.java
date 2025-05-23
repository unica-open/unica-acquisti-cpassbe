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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornamentiSubImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornamentiSubImpegniResponse;
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
import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetAggiornamentiSubImpegniService extends BaseBatchService<GetAggiornamentiSubImpegniRequest, GetAggiornamentiSubImpegniResponse> {

	private final ImpegnoDad impegnoDad;
	private final FornitoreDad fornitoreDad;
	public static final String IMPORTO_ERRORE = "9999999999999.99";
	private final ExternalHelperLookup externalHelperLookup;
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetAggiornamentiSubImpegniService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ImpegnoDad impegnoDad,
			FornitoreDad fornitoreDad,
			ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.impegnoDad =  impegnoDad;
		this.fornitoreDad =  fornitoreDad;
		this.externalHelperLookup =  externalHelperLookup;
	}

	@Override
	protected void execute() {
		log.info("caricamento imp ", "inizio elaborazione ");
		final Calendar calendar = Calendar.getInstance();
		final Integer annoCorrente = calendar.get(Calendar.YEAR);
		final Ente ente = enteDad.getEnteByCodice(request.getEnte()).orElseThrow(() -> new NotFoundException("ente"));
		log.info("estraggo  ", "estraggo da tab appoggio  ");

		final Integer limit  = 100;
		Integer offset = 0;

		boolean exit = false;
		int  i = 1;
		final String sdataOdierna = request.getDataElab();
		final Date dataOdierna = stringTodate(sdataOdierna);
		final Elaborazione elab = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.AGG_SUBIMPEGNO_TMP.getStatoDB(),ente.getCodice(),request.getNumelab(),request.getDataElab());
		try {
			log.info("execute", "Inizio caricamento SUBIMP"  );
			while(!exit) {
				final List<FlussoSubimpegniEsterni> listSubImp = impegnoDad.getListFlussoSubImpegniEsterniByEnte(ente.getId(),"OK",limit,offset,dataOdierna,request.getNumelab());
				elaboraSubImpegniCaricati(listSubImp, annoCorrente, ente.getId());
				log.info("execute", "cicli di caricamento da 100 --> " + i);
				if (listSubImp == null || listSubImp.size()==0) {
					exit = Boolean.TRUE;
				}
				i++;
				offset = i * limit;
			}
			log.info("execute", "Fine caricamento SUBIMP"  );
			concludiElaborazione(elab, "OK", ConstantsCPassElaborazione.StatoEnum.AGG_SUBIMPEGNO_TMP.getStatoDB() );
		} catch (final Exception e) {
			log.error("execute", "Exception ",e);
			concludiElaborazione(elab, "KO con errore imprevisto controllare log" , ConstantsCPassElaborazione.StatoEnum.AGG_SUBIMPEGNO_TMP.getStatoDB() );
			//e.printStackTrace();
			throw new BusinessException("Exception aggiorna sub ", e);
		}
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

	/**
	 *
	 * @param lista
	 * @param annoCorrente
	 * @param enteId
	 */
	private void elaboraSubImpegniCaricati(List<FlussoSubimpegniEsterni> lista, Integer annoCorrente, UUID enteId) {
		for(final FlussoSubimpegniEsterni flussoSubimpegniEsterni:  lista) {
			// TODO da scommentare e adattare per eventuali test
			/*
			if((flussoSubimpegniEsterni.getAnnoImpegno().equals("2021") && flussoSubimpegniEsterni.getNumImpegno().equals("17351") && flussoSubimpegniEsterni.getCodSubimpegno().equals("18337"))) {
				log.info("elaboraSubImpegniCaricati", "caso in esame");;
			}
			if(!(flussoSubimpegniEsterni.getAnnoImpegno().equals("2021") && flussoSubimpegniEsterni.getNumImpegno().equals("17351") && flussoSubimpegniEsterni.getCodSubimpegno().equals("18337"))) {
			if((flussoSubimpegniEsterni.getAnnoImpegno().equals("2020") && flussoSubimpegniEsterni.getNumImpegno().equals("5134") && flussoSubimpegniEsterni.getCodSubimpegno().equals("19942"))) {
				log.info("elaboraSubImpegniCaricati", "caso in esame");;
			}
			if(!(flussoSubimpegniEsterni.getAnnoImpegno().equals("2019") && flussoSubimpegniEsterni.getNumImpegno().equals("5134") && flussoSubimpegniEsterni.getCodSubimpegno().equals("19942"))) {
				continue;
			}
			 */
			final Subimpegno subimpegnoAnnoCorrente = impegnoDad.getSubimpegnoByChiaveLogica(annoCorrente,
					Integer.parseInt(flussoSubimpegniEsterni.getAnnoImpegno()),
					Integer.parseInt(flussoSubimpegniEsterni.getNumImpegno()),
					enteId,
					null,//TODO se mai ci fosse nel flusso l'annosub scommentare la riga
					//Integer.parseInt(flussoSubimpegniEsterni.getAnnoSubImpegno()),
					//Integer.parseInt(flussoSubimpegniEsterni.getAnnoImpegno()),
					Integer.parseInt(flussoSubimpegniEsterni.getCodSubimpegno())
					);


			final Subimpegno subimpegnoAnnoPrecedente = impegnoDad.getSubimpegnoByChiaveLogica(annoCorrente-1,
					Integer.parseInt(flussoSubimpegniEsterni.getAnnoImpegno()),
					Integer.parseInt(flussoSubimpegniEsterni.getNumImpegno()),
					enteId,
					null,//TODO se mai ci fosse nel flusso l'annosub scommentare la riga
					//Integer.parseInt(flussoSubimpegniEsterni.getAnnoSubImpegno()),
					Integer.parseInt(flussoSubimpegniEsterni.getCodSubimpegno())
					);


			if(subimpegnoAnnoCorrente!=null && subimpegnoAnnoCorrente.getId()!=null) {
				log.info("aggiornaSubImpegno", "SUB impegno da modificare " + flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getNumImpegno() +"-"+ flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getCodSubimpegno());
				modificaSubImpegno( subimpegnoAnnoCorrente,  subimpegnoAnnoPrecedente, flussoSubimpegniEsterni, enteId, annoCorrente);
			}else {
				if(subimpegnoAnnoPrecedente!=null && subimpegnoAnnoPrecedente.getId()!=null) {
					//inserisciSubImpegno(subimpegnoAnnoPrecedente, flussoSubimpegniEsterni, enteId);
					final List<Impegno> impNew   = impegnoDad.getImpegnoByChiaveLogica(annoCorrente  , Integer.parseInt(flussoSubimpegniEsterni.getAnnoImpegno()), Integer.parseInt(flussoSubimpegniEsterni.getNumImpegno()), enteId);
					if(impNew==null ||impNew.size()==0) {
						log.warn("aggiornaSubImpegno", "il sub che si vuol caricare non trova corrispondenza su impegni " + flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getNumImpegno() +"-"+ flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getCodSubimpegno());
					}else {
						log.info("aggiornaSubImpegno", "SUB impegno da inseire " + flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getNumImpegno() +"-"+ flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getCodSubimpegno());
						inserisciSubimpegno(impNew.get(0),  subimpegnoAnnoPrecedente,flussoSubimpegniEsterni, annoCorrente,enteId);
					}
				}else {
					log.warn("aggiornaSubImpegno", "SUB impegno scartato non presente ne nell'anno corrente e neppure con l'esercizio precedente " + flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getNumImpegno() +"-"+ flussoSubimpegniEsterni.getAnnoImpegno() +"-"+ flussoSubimpegniEsterni.getCodSubimpegno());
				}
			}




		}
	}

	private void modificaSubImpegno(Subimpegno subimpegnoAnnoCorrente, Subimpegno subimpegnoAnnoPrecedente,FlussoSubimpegniEsterni flussoSubimpegniEsterni, UUID enteId,Integer annoCorrente) {
		final String statoPassato = flussoSubimpegniEsterni.getCodStatoSubimpegno() == null ? "" : flussoSubimpegniEsterni.getCodStatoSubimpegno().substring(0,1);
		final Fornitore fornitoreCalcolato = fornitoreDad.getFornitoreByCodice(flussoSubimpegniEsterni.getCodSoggetto(), enteId);
		// procedo alla modifica solo nel caso in cui imp iniziale / imp attuale / stato / fornitore siano cambiati
		// procedo alla modifica solo nel caso in cui imp iniziale / imp attuale / stato / fornitore siano cambiati
		final BigDecimal flussoImpAttuale = new BigDecimal(flussoSubimpegniEsterni.getImportoAttuale());
		final BigDecimal flussoImpIniziale = new BigDecimal(flussoSubimpegniEsterni.getImportoIniziale());

		if(fornitoreCalcolato==null) {
			log.warn("modificaSubImpegno", "ATTENZIONE fornitore passato nel flusso non censito " + flussoSubimpegniEsterni.getCodSoggetto() );
			log.warn("modificaSubImpegno", "ATTENZIONE il fornitore è mantenuto originale a quello sul db " + flussoSubimpegniEsterni.getCodSoggetto() );
		}
		if(        subimpegnoAnnoCorrente.getImportoAttuale().compareTo(flussoImpAttuale)!=0
				|| subimpegnoAnnoCorrente.getImportoIniziale().compareTo(flussoImpIniziale)!=0
				|| !subimpegnoAnnoCorrente.getStato().equals(statoPassato)

				|| (fornitoreCalcolato!=null && !fornitoreCalcolato.getId().equals(subimpegnoAnnoCorrente.getFornitore().getId()))

				){
			if(fornitoreCalcolato!=null) {
				subimpegnoAnnoCorrente.setFornitore(fornitoreCalcolato);
			}
			final BigDecimal importoIniziale = new BigDecimal(flussoSubimpegniEsterni.getImportoIniziale());
			subimpegnoAnnoCorrente.setImportoIniziale(importoIniziale);
			final BigDecimal importoAttuale = new BigDecimal(flussoSubimpegniEsterni.getImportoAttuale());
			subimpegnoAnnoCorrente.setImportoAttuale(importoAttuale);
			subimpegnoAnnoCorrente.setStato(statoPassato);

			final BigDecimal liquidatoAnnoPrecedente = calcolaLiquidatoSub(subimpegnoAnnoCorrente, subimpegnoAnnoPrecedente, flussoSubimpegniEsterni);
			subimpegnoAnnoCorrente.setLiquidatoAnnoPrecedente(liquidatoAnnoPrecedente);
			subimpegnoAnnoCorrente.setDataModifica(new Date());
			subimpegnoAnnoCorrente.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
			//subimpegnoAnnoCorrente.setDataCreazione(new Date());
			//subimpegnoAnnoCorrente.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());

			impegnoDad.saveSubimpegno(annoCorrente,
					Integer.parseInt(flussoSubimpegniEsterni.getAnnoImpegno()),
					Integer.parseInt(flussoSubimpegniEsterni.getNumImpegno()),
					subimpegnoAnnoCorrente,
					enteId);
		}

	}



	/**
	 *
	 * @param impNew
	 * @param subimpegnoAnnoPrecedente
	 * @param flussoSubImpegniEsterni
	 * @return Subimpegno
	 */

	private Subimpegno inserisciSubimpegno(Impegno impNew, Subimpegno subimpegnoAnnoPrecedente,FlussoSubimpegniEsterni flussoSubimpegniEsterni,Integer annoCorrente,UUID enteId) {
		// caso  inserimento da impegno nuovo
		Subimpegno subNew = new Subimpegno();
		subNew = subimpegnoAnnoPrecedente;
		subNew.setAnnoEsercizio(annoCorrente);

		subNew.setImpegno(impNew);
		subNew.setDataModifica(new Date());
		subNew.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
		subNew.setDataCreazione(new Date());
		subNew.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());

		final BigDecimal importoIniziale = new BigDecimal(flussoSubimpegniEsterni.getImportoIniziale()== null ? "0" : flussoSubimpegniEsterni.getImportoIniziale());
		subNew.setImportoIniziale(importoIniziale);

		final BigDecimal importoAttuale = new BigDecimal(flussoSubimpegniEsterni.getImportoAttuale()== null ? "0" : flussoSubimpegniEsterni.getImportoAttuale());
		subNew.setImportoAttuale(importoAttuale);

		//private Fornitore fornitore;
		subNew.setFornitore(recuperaFornitoreByCode(flussoSubimpegniEsterni.getCodSoggetto().trim(),enteId));

		//2023-1432-2023-19280

		final BigDecimal liquidatoAnnoPrecedente = calcolaLiquidatoSub( subNew,  subimpegnoAnnoPrecedente, flussoSubimpegniEsterni);
		subNew.setLiquidatoAnnoPrecedente(liquidatoAnnoPrecedente);

		//stato
		subNew.setStato(flussoSubimpegniEsterni.getCodStatoSubimpegno() == null ? "" : flussoSubimpegniEsterni.getCodStatoSubimpegno().substring(0,1));
		subNew.setId(null);
		subNew = impegnoDad.saveSubimpegno(subNew);
		return subNew;
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

		final Ente ente = new Ente();
		ente.setId(enteId);
		forn.setEnte(ente);

		forn = fornitoreDad.insert(forn);
		return forn;
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
		BigDecimal liqAnnoPrec = subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente() == null ? BigDecimal.ZERO : subimpegnoAnnoCorrente.getLiquidatoAnnoPrecedente();

		BigDecimal ordinatoAnnoPrecedente = BigDecimal.ZERO;
		if(subimpegnoAnnoPrecedente!= null && subimpegnoAnnoPrecedente.getId()!=null) {
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
			if(subimpegnoAnnoPrecedente == null || subimpegnoAnnoPrecedente.getId()==null) {
				return BigDecimal.ZERO;
			}else {
				final BigDecimal imp1 = subimpegnoAnnoPrecedente.getLiquidatoAnnoPrecedente().add(ordinatoAnnoPrecedente);
				final BigDecimal imp2 = subimpegnoAnnoPrecedente.getImportoAttuale().subtract(subimpegnoAnnoCorrente.getImportoIniziale());
				liqAnnoPrec = imp1.subtract(imp2);
				if((liqAnnoPrec.compareTo(BigDecimal.ZERO)<0) || (liqAnnoPrec.compareTo(new BigDecimal(flussoSubimpegniEsterni.getImportoAttuale()))>0)){
					inserisciAnomaliaSubImpegno( subimpegnoAnnoCorrente,  subimpegnoAnnoPrecedente, flussoSubimpegniEsterni,  liqAnnoPrec);
					liqAnnoPrec = new BigDecimal(IMPORTO_ERRORE);
				}
			}
		}
		return liqAnnoPrec;
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
		if(subimpegnoAnnoCorrente.getId()==null) {
			log.error("inserisciAnomaliaSubImpegno ", "anno sub " + subimpegnoAnnoCorrente.getAnno()+ " num sub "+subimpegnoAnnoCorrente.getNumero() +" num imp" + subimpegnoAnnoCorrente.getImpegno().getNumero() +" anno imp" + subimpegnoAnnoCorrente.getImpegno().getAnno());
		}
		log.error("inserisciAnomaliaSubImpegno fuori dalla if ", "anno sub " + subimpegnoAnnoCorrente.getAnno()+ " num sub "+subimpegnoAnnoCorrente.getNumero() +" num imp" + subimpegnoAnnoCorrente.getImpegno().getNumero() +" anno imp" + subimpegnoAnnoCorrente.getImpegno().getAnno() );

		impegnoDad.saveFlussoAnomalie(flussoAnomalie);
	}
	/*
	public static void main(String... args) throws ParseException {
		//DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy.MM.dd",Locale.ITALY).withZone(ZoneId.of("Europe/Rome"));
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		date = formatter.parse("2021-02-07");
		System.out.println("date " + date.toString());

	}
	 */
}
