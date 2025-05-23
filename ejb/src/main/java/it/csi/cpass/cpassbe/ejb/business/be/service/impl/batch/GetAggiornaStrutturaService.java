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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornaStrutturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornaStrutturaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityCommon;
import it.csi.cpass.cpassbe.ejb.entity.CpassRSettoreCdc;
import it.csi.cpass.cpassbe.ejb.entity.CpassTAggiornamentoStruttura;
import it.csi.cpass.cpassbe.ejb.entity.CpassTCdc;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.SettoreStorico;
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.SettoreAooActa;

/**
 * GetStoricizzaFileDdtService
 */
public class GetAggiornaStrutturaService extends BaseBatchService<GetAggiornaStrutturaRequest, GetAggiornaStrutturaResponse> {


	//Boolean error = Boolean.FALSE;
	List<String> errors  = new ArrayList<>();
	List<String> warnings = new ArrayList<>();
	Ente ente;
	//UUID enteId;
	SettoreDad settoreDad;
	DecodificaDad decodificaDad;
	// TODO da scommentare e adattare per eventuali test
	/*
	if((flussoSubimpegniEsterni.getAnnoImpegno().equals("2021") && flussoSubimpegniEsterni.getNumImpegno().equals("17351") && flussoSubimpegniEsterni.getCodSubimpegno().equals("18337"))) {
		log.info("elaboraSubImpegniCaricati", "caso in esame");;
	}
	if(!(flussoSubimpegniEsterni.getAnnoImpegno().equals("2021") && flussoSubimpegniEsterni.getNumImpegno().equals("17351") && flussoSubimpegniEsterni.getCodSubimpegno().equals("18337"))) {
		continue;
	}
	 */
	public GetAggiornaStrutturaService( ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			SettoreDad settoreDad,
			DecodificaDad decodificaDad

			) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.settoreDad=settoreDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void execute() {
		final String methodName = "execute batchaggiorna strutura";
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		ente = enteOpt.orElseThrow(() -> new NotFoundException("ente"));


		final Elaborazione elab = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.TipoEnum.AGG_STRUTTURA.getCodice(),ente.getId().toString() + new Date().toString(),null,"");
		errors   = new ArrayList<>();
		warnings = new ArrayList<>();
		final List<CpassTAggiornamentoStruttura> listaAS = commonDad.getAggiornamentoStrutture(request.getCodEnte());
		for(final CpassTAggiornamentoStruttura as : listaAS) {
			switch(as.getAzione()) {
			case "SOSTITUZIONE":
				log.info(methodName, "caso SOSTITUZIONE");
				sostituisci(as,elab);
				break;
			case "CHIUSURA":
				log.info(methodName, "caso CHIUSURA");
				chiudi(as,elab);
				break;
			case "APERTURA":
				log.info(methodName, "caso APERTURA");
				apri(as,elab);
				break;
			case "SCORPORO":
				log.info(methodName, "caso SCORPORO");
				scorpora(as,elab);
				break;
			case "ACCORPAMENTO":
				log.info(methodName, "caso ACCORPAMENTO");
				accorpa(as,elab);
				break;
			default:
				log.error(methodName, "CASO NON CENSITO "+as.getAzione());
				errors.add("agg. strutt id --> "+as.getAggiornamentoStrutturaId() + " azione non censita azione-->"+as.getAzione());
			}
		}
		if(errors.size()==0) {
			concludiElaborazione(elab, "OK" ,ConstantsCPassElaborazione.TipoEnum.AGG_STRUTTURA.getCodice());
			response.setRis("OK");
		}else {
			concludiElaborazione(elab, "KO" ,ConstantsCPassElaborazione.TipoEnum.AGG_STRUTTURA.getCodice());
			response.setRis("KO");
		}
	}

	private void sostituisci(CpassTAggiornamentoStruttura as,Elaborazione elab) {
		final List<String> errorRecord = new ArrayList<>();

		final Optional<Settore> settoreNuovoOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreNuovo(),ente.getId());
		if(settoreNuovoOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " sostituisci. settore nuovo già esistente--> "+as.getSettoreNuovo());}

		final Optional<Settore> settoreVecchioOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreVecchio(),ente.getId());
		if(!settoreVecchioOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " sostituisci. settore vecchio non esiste --> "+as.getSettoreVecchio());}

		final Optional<TipoSettore> tipoSettoreOpt = decodificaDad.getTipoSettoreByCodeObbligatorioAndEnteId(as.getTipoSettore(), ente.getId());
		if(!tipoSettoreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " sostituisci. tipo settore non esistente -->"+as.getTipoSettore());}

		final Optional<Settore> settorePadreOpt = settoreDad.findByCodiceObbligatorio(as.getSettorePadre(),ente.getId());
		if(!settorePadreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " sostituisci. settore padre non esistente -->"+as.getSettorePadre());}

		if(errorRecord.size()==0) {
			final Settore settorePadre 	= settorePadreOpt.orElseThrow(() -> new NotFoundException("settore padre"));
			final TipoSettore tipoSettore = tipoSettoreOpt.orElseThrow(() -> new NotFoundException("tipo settore"));

			//1. Si dovrà apporre una data cancellazione al settore che ha codice = CPASS_T_AGGIORNAMENTO_STRUTTURA.vecchio_settore
			final Settore settorevecchio = storicizzaSettoreVecchio(settoreVecchioOpt);
			final List<Ufficio>          listaUffici    = settoreVecchioOpt.orElseThrow(() -> new NotFoundException("uffici")).getUffici();

			//2. Si dovranno spostare tutti gli indirizzi presenti in CPASS_T_SETTORE_INDIRIZZO per codice settore = vecchio settore sul codice settore = nuovo_settore
			List<SettoreIndirizzo> listaIndirizzi = settoreVecchioOpt.orElseThrow(() -> new NotFoundException("settore indirizzi")).getSettoreIndirizzos();
			listaIndirizzi = listaIndirizzi.stream().filter(el -> el.getDataCancellazione()==null).collect(Collectors.toList());

			//3. Si dovrà creare il nuovo settore con codice CPASS_T_AGGIORNAMENTO_STRUTTURA.nuovo_settore
			final Settore settoreNuovo = creaSettoreNuovo(as, settorePadre, tipoSettore);

			for(final SettoreIndirizzo ind : listaIndirizzi) {
				//storicizzo il bvecchi indirizzo
				ind.setDataCancellazione(new Date());
				ind.setUtenteCancellazione(CpassEnum.UTENTE_BATCH.getCostante());
				settoreDad.saveAndFlushSettoreIndirizzo(ind);
				//inserisco il nuovo indirizzo clone del vecchio
				ind.setId(null);
				ind.setUtenteCancellazione(null);
				ind.setDataCancellazione(null);
				ind.setSettore(settoreNuovo);
				ind.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());
				ind.setDataCreazione(new Date());
				ind.setDataModifica(new Date());
				ind.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
				settoreDad.saveAndFlushSettoreIndirizzo(ind);
			}

			//4. Si dovranno spostare tutte i record presenti in CPASS_R_UFFICIO_SETTORE per codice settore = vecchio settore sul codice settore = nuovo_settore
			for(final Ufficio uff : listaUffici) {
				settoreDad.trasferisciRelazioneUfficioSettore(uff,settorevecchio,settoreNuovo);
			}


			//5 aggiornamento della relazione CDC settore u
			final List<CpassRSettoreCdc> listaCdc = settoreDad.findByCdcIdSettId(null, settorevecchio.getId());
			for(final CpassRSettoreCdc cpassRSettoreCdc : listaCdc) {
				//storicizzo il vecchio indirizzo
				//settoreDad.chiudiRelazioneCdcSettore( cpassRSettoreCdc.getId(),  settorevecchio.getId());
				settoreDad.chiudiRelazioneCdcSettore( cpassRSettoreCdc.getId());
				//inserisco il nuovo indirizzo clone del vecchio
				final CpassTCdc cpassTCdc = cpassRSettoreCdc.getCpassTCdc();
				settoreDad.insertCdcSettoreXBatch(cpassTCdc, settoreNuovo);
			}

			//6 aggiornamento della relazione Aoo settore cpass_r_settori_aoo_acta
			final List<SettoreAooActa> listaSettoreAooActa = settoreDad.findByAooActaIdSettId(null, settorevecchio.getId());

			for(final SettoreAooActa settoreAooActa : listaSettoreAooActa) {
				//storicizzo il vecchio indirizzo
				//settoreDad.chiudiRelazioneAooActaSettore(settoreAooActa.getId(),  settorevecchio.getId());
				settoreDad.chiudiRelazioneAooActaSettore(settoreAooActa.getId());
				//inserisco il nuovo indirizzo clone del vecchio
				final AooActa aooActa = settoreAooActa.getAooActa();
				settoreDad.insertSettoreAooActa(aooActa, settoreNuovo);
			}


			//7 aggiornamento settoreStorico
			aggiornaStorico(as, settorevecchio, settoreNuovo);
			//AggiornaEsitoAs
			aggiornaEsito(as,"OK");
		}else {
			new ElaborazioneMessaggio();
			for(final String e: errorRecord) {
				inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "ERRORE AGGIORNAMENTO_STRUTTURA", "AGG_STRUTTURA" , e);
			}
			//aggiornaEsito(as,"" + me.getId());
			aggiornaEsito(as,"KO");
		}
		errors.addAll(errorRecord);
	}

	/**
	 *
	 * @param as
	 */
	private void apri(CpassTAggiornamentoStruttura as,Elaborazione elab) {
		final List<String> errorRecord = new ArrayList<>();

		final Optional<Settore> settoreNuovoOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreNuovo(),ente.getId());
		if(settoreNuovoOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " apri. settore nuovo già esistente--> "+as.getSettoreNuovo());}

		final Optional<TipoSettore> tipoSettoreOpt = decodificaDad.getTipoSettoreByCodeObbligatorioAndEnteId(as.getTipoSettore(), ente.getId());
		if(!tipoSettoreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " apri. tipo settore non esistente -->"+as.getTipoSettore());}

		final Optional<Settore> settorePadreOpt = settoreDad.findByCodiceObbligatorio(as.getSettorePadre(),ente.getId());
		if(!settorePadreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " apri. settore padre non esistente -->"+as.getSettorePadre());}

		if(errorRecord.size()==0) {
			final Settore settorePadre 	= settorePadreOpt.orElseThrow(() -> new NotFoundException("settore padre"));
			final TipoSettore tipoSettore = tipoSettoreOpt.orElseThrow(() -> new NotFoundException("tipo settore"));
			final Settore settNew = creaSettoreNuovo(as, settorePadre, tipoSettore);
			creaIndirizzoPrincipale(as,settNew, elab);
			aggiornaEsito(as,"OK");
		}else {
			new ElaborazioneMessaggio();
			for(final String e: errorRecord) {
				inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "ERRORE AGGIORNAMENTO_STRUTTURA", "AGG_STRUTTURA" , e);
			}
			//aggiornaEsito(as,"" + me.getId());
			aggiornaEsito(as,"KO");
		}

		errors.addAll(errorRecord);
	}

	private void creaIndirizzoPrincipale(CpassTAggiornamentoStruttura as, Settore settNew, Elaborazione elab) {
		if(StringUtility.isNotEmpty(as.getDescrizioneIndirizzo())) {
			final SettoreIndirizzo settInd = new SettoreIndirizzo();
			settInd.setDescrizione(as.getDescrizioneIndirizzo());
			settInd.setIndirizzo(as.getIndirizzo());
			settInd.setNumCivico(as.getNumCivico());
			settInd.setLocalita(as.getLocalita());
			settInd.setProvincia(as.getProvincia());
			settInd.setCap(as.getCap());
			settInd.setContatto(as.getContatto());
			settInd.setEmail(as.getEmail());
			settInd.setTelefono(as.getTelefono());
			settInd.setSettore(settNew);
			settInd.setPrincipale(Boolean.TRUE);
			settInd.setEsternoEnte(Boolean.FALSE);
			settoreDad.saveAndFlushSettoreIndirizzo(settInd);
		}else {
			//3. Dato che in questo caso non è possibile spostare gli indirizzi e gli uffici, occorrerà inserire un messaggio di elaborazione.
			warnings.add("Per la struttura "+as.getSettoreNuovo()+" non sono stati inseriti indirizzi e uffici");
			inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "WARNING STRUTTURA", "AGG_STRUTTURA" , "Per la struttura "+as.getSettoreNuovo()+" non sono stati inseriti indirizzi e uffici");

		}
	}

	/**
	 *
	 * @param CpassTAggiornamentoStruttura as
	 */
	private void chiudi(CpassTAggiornamentoStruttura as,Elaborazione elab) {
		final List<String> errorRecord = new ArrayList<>();

		final Optional<Settore> settoreVecchioOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreVecchio(),ente.getId());
		if(!settoreVecchioOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " chiudi settore vecchio non esistente-->"+as.getSettoreVecchio());}

		if(errorRecord.size()==0) {
			//Si dovrà apporre una data cancellazione al settore che ha codice = CPASS_T_AGGIORNAMENTO_STRUTTURA.vecchio_settore
			final Settore settorevecchio = storicizzaSettoreVecchio(settoreVecchioOpt);
			final List<SettoreIndirizzo> listaIndirizzi = settorevecchio.getSettoreIndirizzos();
			final List<Ufficio>          listaUffici    = settorevecchio.getUffici();

			//2. Si dovranno chiudere tutti gli indirizzi presenti in CPASS_T_SETTORE_INDIRIZZO
			for(final SettoreIndirizzo ind : listaIndirizzi) {
				ind.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
				ind.setDataModifica(new Date());
				ind.setUtenteCancellazione(CpassEnum.UTENTE_BATCH.getCostante());
				ind.setDataCancellazione(new Date());
				settoreDad.saveAndFlushSettoreIndirizzo(ind);
			}

			//3. Si dovranno chiuderre tutte i record presenti in CPASS_R_UFFICIO_SETTORE
			for(final Ufficio uff : listaUffici) {
				settoreDad.chiudiRelazioneUfficioSettore(uff,settorevecchio);
			}
			//4 aggiornamento settoreStorico
			aggiornaEsito(as,"OK");
		}else {

			new ElaborazioneMessaggio();
			for(final String e: errorRecord) {
				inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "ERRORE AGGIORNAMENTO_STRUTTURA", "AGG_STRUTTURA" , e);
			}
			//aggiornaEsito(as,"" + me.getId());
			aggiornaEsito(as,"KO");
		}

	}

	/**
	 *
	 * @param as
	 * @param elab
	 */
	private void scorpora(CpassTAggiornamentoStruttura as,Elaborazione elab) {
		final List<String> errorRecord = new ArrayList<>();
		//Optional<Settore> settoreNuovoOpt = settoreDad.findByCodiceObbligatorioObbligatorio(as.getSettoreNuovo(),ente.getId());
		//if(!settoreNuovoOpt.isPresent()) {errorRecord.add("agg. strutt id --> "+as.getId() + " settore -->"+as.getSettoreNuovo());}
		final Optional<Settore> settoreVecchioOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreVecchio(),ente.getId(),false);
		if(!settoreVecchioOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " scorpora settore vecchio non esistente-->"+as.getSettoreVecchio());}

		final Optional<TipoSettore> tipoSettoreOpt = decodificaDad.getTipoSettoreByCodeObbligatorioAndEnteId(as.getTipoSettore(), ente.getId());
		if(!tipoSettoreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " scorpora settore tipo non esistente -->"+as.getTipoSettore());}

		final Optional<Settore> settorePadreOpt = settoreDad.findByCodiceObbligatorio(as.getSettorePadre(),ente.getId());
		if(!settorePadreOpt.isPresent()) {errorRecord.add(" id --> "+as.getId() + " scorpora settore padre non esistente-->"+as.getSettorePadre());}

		if(errorRecord.size()==0) {
			final Settore settorePadre 	= settorePadreOpt.orElseThrow(() -> new NotFoundException("settore padre"));
			final TipoSettore tipoSettore = tipoSettoreOpt.orElseThrow(() -> new NotFoundException("tipo settore"));
			//1. Si dovrà apporre una data cancellazione al settore che ha codice = CPASS_T_AGGIORNAMENTO_STRUTTURA.vecchio_settore
			final Settore settorevecchio = storicizzaSettoreVecchio(settoreVecchioOpt);
			//2. Si dovrà creare il nuovo settore con codice CPASS_T_AGGIORNAMENTO_STRUTTURA.nuovo_settore
			final Settore settNew = creaSettoreNuovo(as, settorePadre, tipoSettore);
			//3 inserimento nuovo Indirizzo
			creaIndirizzoPrincipale(as,settNew, elab);
			//4 aggiornamento settoreStorico
			aggiornaStorico(as, settorevecchio, settNew);
			aggiornaEsito(as,"OK");
		}else {
			new ElaborazioneMessaggio();
			for(final String e: errorRecord) {
				inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "ERRORE AGGIORNAMENTO_STRUTTURA", "AGG_STRUTTURA" , e);
			}
			//aggiornaEsito(as,"" + me.getId());
			aggiornaEsito(as,"KO");
		}
		errors.addAll(errorRecord);
	}



	private void accorpa(CpassTAggiornamentoStruttura as,Elaborazione elab) {
		final List<String> errorRecord = new ArrayList<>();
		final Optional<Settore> settoreNuovoOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreNuovo(),ente.getId());
		//if(!settoreNuovoOpt.isPresent()) {errorRecord.add("agg. strutt id --> "+as.getId() + " settore -->"+as.getSettoreNuovo());}
		final Optional<Settore> settoreVecchioOpt = settoreDad.findByCodiceObbligatorio(as.getSettoreVecchio(),ente.getId());
		if(!settoreVecchioOpt.isPresent()) {errorRecord.add("id --> "+as.getId() + " accorpa settore vecchio non esistente -->"+as.getSettoreVecchio());}

		final Optional<TipoSettore> tipoSettoreOpt = decodificaDad.getTipoSettoreByCodeObbligatorioAndEnteId(as.getTipoSettore(), ente.getId());
		if(!tipoSettoreOpt.isPresent()) {errorRecord.add("id --> "+as.getId() + " accorpa settore tipo non esistente-->"+as.getTipoSettore());}

		final Optional<Settore> settorePadreOpt = settoreDad.findByCodiceObbligatorio(as.getSettorePadre(),ente.getId());
		if(!settorePadreOpt.isPresent()) {errorRecord.add("id --> "+as.getId() + " accorpa settore padre non esistente -->"+as.getSettorePadre());}

		if(errorRecord.size()==0) {
			final Settore settorePadre 	= settorePadreOpt.orElseThrow(() -> new NotFoundException("settore padre"));
			final TipoSettore tipoSettore = tipoSettoreOpt.orElseThrow(() -> new NotFoundException("tipo settore"));
			//1. Si dovrà apporre una data cancellazione al settore che ha codice = CPASS_T_AGGIORNAMENTO_STRUTTURA.vecchio_settore
			final Settore settorevecchio = storicizzaSettoreVecchio(settoreVecchioOpt);
			List<SettoreIndirizzo> listaIndirizzi = settorevecchio.getSettoreIndirizzos();
			listaIndirizzi = listaIndirizzi.stream().filter(el -> el.getDataCancellazione()==null).collect(Collectors.toList());

			final List<Ufficio>          listaUffici    = settorevecchio.getUffici();
			//2. Si dovrà creare il nuovo settore con codice CPASS_T_AGGIORNAMENTO_STRUTTURA.nuovo_settore
			Settore settoreNuovo = new Settore();
			boolean volteSuccessive = Boolean.TRUE;
			if(!settoreNuovoOpt.isPresent()) {
				settoreNuovo = creaSettoreNuovo(as, settorePadre, tipoSettore);
				volteSuccessive = false;
			}else {
				settoreNuovo = settoreNuovoOpt.get();
			}
			new ArrayList<SettoreIndirizzo>();
			for(final SettoreIndirizzo ind : listaIndirizzi) {
				// storicizzo il vecchio
				ind.setUtenteCancellazione(CpassEnum.UTENTE_BATCH.getCostante());
				ind.setDataCancellazione(new Date());
				settoreDad.saveAndFlushSettoreIndirizzo(ind);
				// nuovo settore indirizzo
				ind.setId(null);
				ind.setSettore(settoreNuovo);
				ind.setUtenteCancellazione(null);
				ind.setDataCancellazione(null);
				ind.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());
				ind.setDataCreazione(new Date());
				ind.setDataModifica(new Date());
				ind.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
				if(volteSuccessive && ind.getPrincipale().equals(Boolean.TRUE)) {
					ind.setPrincipale(Boolean.FALSE);
					ind.setDescrizione("SEDE SECONDARIA");
				}
				settoreDad.saveAndFlushSettoreIndirizzo(ind);
			}

			//4. Si dovranno spostare tutte i record presenti in CPASS_R_UFFICIO_SETTORE per codice settore = vecchio settore sul codice settore = nuovo_settore
			for(final Ufficio uff : listaUffici) {
				settoreDad.trasferisciRelazioneUfficioSettore(uff,settorevecchio,settoreNuovo);
			}
			//5 aggiornamento dei CDC cpass_r_settori_cdc
			final List<CpassRSettoreCdc> listaCdc = settoreDad.findByCdcIdSettId(null, settorevecchio.getId());
			for(final CpassRSettoreCdc cpassRSettoreCdc : listaCdc) {
				//storicizzo il vecchio indirizzo
				settoreDad.chiudiRelazioneCdcSettore( cpassRSettoreCdc.getId());
				//inserisco il nuovo indirizzo clone del vecchio
				final CpassTCdc cpassTCdc = cpassRSettoreCdc.getCpassTCdc();
				settoreDad.insertCdcSettoreXBatch(cpassTCdc, settoreNuovo);
			}

			//6 aggiornamento settoreStorico
			aggiornaStorico(as, settorevecchio, settoreNuovo);
			//aggiornaEsito(as,"" + me.getId());
			aggiornaEsito(as,"OK");
		}else {
			ElaborazioneMessaggio me = new ElaborazioneMessaggio();
			for(final String e: errorRecord) {
				me  = inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab, "ERRORE AGGIORNAMENTO_STRUTTURA", "AGG_STRUTTURA" , e);
			}
			aggiornaEsito(as,"" + me.getId());
		}
		errors.addAll(errorRecord);
	}

	/**
	 * @param as
	 * @param settorevecchio
	 * @param settNew
	 */
	protected void aggiornaStorico(CpassTAggiornamentoStruttura as, Settore settorevecchio, Settore settNew) {
		final SettoreStorico settoreStorico = new SettoreStorico();
		settoreStorico.setSettoreAttuale(settNew);
		settoreStorico.setSettoreStorico(settorevecchio);
		settoreStorico.setEnte(ente);
		settoreStorico.setDataValiditaInizio(new Date());
		settoreStorico.setSettoreCodiceStorico(as.getSettoreVecchio());
		settoreStorico.setSettoreCodiceAttuale(as.getSettoreNuovo());
		settoreStorico.setNote("RIORGANIZZAZIONE DEL --> "+UtilityCommon.getDate());
		settoreDad.saveSettoreStorico(settoreStorico);
	}

	/**
	 * @param as
	 * @param settorePadre
	 * @param tipoSettore
	 * @return
	 */
	private Settore creaSettoreNuovo(CpassTAggiornamentoStruttura as, Settore settorePadre, TipoSettore tipoSettore) {
		Settore settNew = new Settore();
		settNew.setCodice(as.getSettoreNuovo());
		settNew.setDescrizione(as.getDescrizioneSettore());
		settNew.setSettorePadre(settorePadre);
		settNew.setTipoSettore(tipoSettore);
		settNew.setDataValiditaInizio(as.getDataValiditaInizio() == null? new Date() : as.getDataValiditaInizio() );
		settNew.setEnte(ente);
		settNew = settoreDad.saveSettoreAndFlush(settNew);
		return settNew;
	}

	/**
	 * @param settoreVecchioOpt
	 * @return
	 */
	private Settore storicizzaSettoreVecchio(Optional<Settore> settoreVecchioOpt) {
		Settore settorevecchio 	= settoreVecchioOpt.orElseThrow(() -> new NotFoundException("settore vecchio"));
		if(settorevecchio.getDataCancellazione()== null) {
			//settorevecchio.setDataCancellazione(new Date());
			//settorevecchio.setUtenteCancellazione(CpassEnum.UTENTE_BATCH.getCostante());
			settorevecchio.setDataValiditaFine(new Date());
			settorevecchio.setDataModifica(new Date());
			settorevecchio.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
			settorevecchio = settoreDad.saveSettoreAndFlush(settorevecchio);
		}
		return settorevecchio;
	}

	private void aggiornaEsito(CpassTAggiornamentoStruttura as, String value) {
		as.setEsito(value);
		commonDad.saveAggiornamentoStruttura(as);
	}

}