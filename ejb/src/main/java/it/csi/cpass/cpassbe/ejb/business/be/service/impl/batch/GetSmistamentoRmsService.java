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
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetSmistamentoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetSmistamentoRmsResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.RiferimentoEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRigaRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoStatoEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.NotificaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsDaSmistare;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;

/**
 * GetSmistamentoRmsService
 */
public class GetSmistamentoRmsService extends BaseBatchService<GetSmistamentoRmsRequest, GetSmistamentoRmsResponse> {
	private final RmsDad  rmsDad;
	private final DecodificaDad  decodificaDad;
	private final UtenteDad  utenteDad;
	private final NotificheDad  notificheDad;


	public GetSmistamentoRmsService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			RmsDad  rmsDad,
			DecodificaDad  decodificaDad,
			UtenteDad  utenteDad,
			NotificheDad  notificheDad
			) {
		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.rmsDad  = rmsDad;
		this.decodificaDad = decodificaDad;
		this.utenteDad  = utenteDad;
		this.notificheDad =notificheDad;
	}

	@Override
	protected void execute() {
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		final UUID enteId = enteOpt.orElseThrow(() -> new NotFoundException("ente")).getId();
		final Elaborazione elab = inizializzaElaborazione(enteId);
		// TODO gestire la chiamata alla procedura
		final List<RmsDaSmistare> listaRmsDaSmistare = rmsDad.getRmsDaSmistareAll(enteId);
		int numeroRigeDaSmistare = 0;
		List<RegoleSmistamentoRms> listaRegole = new ArrayList<>();

		final String cf = systemDad.getParametro(ChiaveEnum.UTENTE_BATCH.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore();
		final Utente utenteBatch = utenteDad.getUtenteByCf(cf,Boolean.FALSE).orElseThrow(() -> new NotFoundException("utente batch"));

		//variabile che ragiona a livello di elaborazione completa
		//anche un solo errore di 1 sola riga determina errore
		boolean xstErrore = false;
		//variabile che ragiona su una singola ricerca di regola
		boolean regolaTrovata;
		final Stato statoDaEvadere =  decodificaDad.getStatoOpt(StatoRigaRmsEnum.DAE.getCostante(), TipoStatoEnum.RIGA_RMS.getCostante()).orElseThrow(() -> new NotFoundException("stato")) ;

		String messaggioDescrizione ="";
		for (final RmsDaSmistare rms : listaRmsDaSmistare) {

			numeroRigeDaSmistare++;
			regolaTrovata = false;
			//CPASS_T_REGOLE_SMISTAMENTO_RMS.*oggetti_spesa_codice = CODICE_ODS
			listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, rms.getCodiceOds(), null, null);
			if(listaRegole.size()==0) {
				//Se non si trova nulla, si cerca con: CPASS_T_REGOLE_SMISTAMENTO_RMS.oggetti_spesa_codice='ALL'
				//e CPASS_T_REGOLE_SMISTAMENTO_RMS.cpv_codice = CODICE_CPV
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), rms.getCodiceCpv(), null);
			}

			if(listaRegole.size()==0) {
				//Se non si trova nulla, si cicla sui livelli CPV fino a trovare qualcosa:
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), rms.getCodiceCpv().substring(0,5), "4");
			}

			if(listaRegole.size()==0) {
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), rms.getCodiceCpv().substring(0,4), "3");
			}

			if(listaRegole.size()==0) {
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), rms.getCodiceCpv().substring(0,3), "2");
			}

			if(listaRegole.size()==0) {
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), rms.getCodiceCpv().substring(0,2), "1");
			}

			if(listaRegole.size()==0) {
				listaRegole = rmsDad.getRegoleSmistamentoRms(enteId, ValoreEnum.ALL.getCostante(), ValoreEnum.ALL.getCostante(), null);
			}

			if(listaRegole.size()==0) {
				// Se non si trova nulla, SI ESCE CON ERRORE (REGOLA NON TROVATA)
				messaggioDescrizione = "RMS: "+rms.getRmsAnno()+"/"+rms.getRmsNumero()+"/"+rms.getProgressivoRiga()+": nessuna regola smistamento trovata";
				inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_SMISTAMENTO_RMS.getCostante(),MsgCpassRms.RMSRMSE0024.getError().getCode(), messaggioDescrizione);
				xstErrore = Boolean.TRUE;
				regolaTrovata = Boolean.FALSE;
			}else {
				final List<RegoleSmistamentoRms> listaRegoleDaScremare = listaRegole;
				//Sulle righe individuate, si cerca con la condizione CPASS_T_REGOLE_SMISTAMENTO_RMS.codice_struttura = STRUTTURA_RICHIEDENTE
				listaRegole = affinaLeRegoleConCodiceStruttura(listaRegoleDaScremare,rms.getStrutturaRichiedente());
				if(listaRegole.size()>1) {
					//1. Se si trovano piu righe, SI ESCE CON ERRORE (Piu REGOLE TROVATE)
					messaggioDescrizione = "RMS: "+rms.getRmsAnno()+"/"+rms.getRmsNumero()+"/"+rms.getProgressivoRiga()+": piu' regole smistamento trovate";
					inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_SMISTAMENTO_RMS.getCostante(),MsgCpassRms.RMSRMSE0024.getError().getCode(), messaggioDescrizione);
					xstErrore = Boolean.TRUE;
					regolaTrovata = Boolean.FALSE;
				}

				if(listaRegole.size()==1) {
					//2. Se si trova 1 record, SI ESCE CON SUCCESSO
					regolaTrovata = Boolean.TRUE;
				}

				if(listaRegole.size()==0) {
					//3. Se non si trova alcun record, si cerca con: CPASS_T_REGOLE_SMISTAMENTO_RMS.codice_struttura = STRUTTURA_PADRE e tutta_la_struttura = true
					listaRegole = affinaLeRegoleConCodiceStrutturaPadre(listaRegoleDaScremare, rms.getStrutturaPadre()) ;
					if(listaRegole.size()>1) {
						//i. Se si trovano più righe, SI ESCE CON ERRORE (Più REGOLE TROVATE)
						messaggioDescrizione = "RMS: "+rms.getRmsAnno()+"/"+rms.getRmsNumero()+"/"+rms.getProgressivoRiga()+": piu' regole smistamento trovate";
						inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_SMISTAMENTO_RMS.getCostante(),MsgCpassRms.RMSRMSE0024.getError().getCode(), messaggioDescrizione);
						xstErrore = Boolean.TRUE;
						regolaTrovata = Boolean.FALSE;
					}
					if(listaRegole.size()==1) {
						//ii. Se si trova 1 record, SI ESCE CON SUCCESSO
						regolaTrovata = Boolean.TRUE;
					}
					if(listaRegole.size()==0) {
						//iii. Se non si trova alcun record, si cerca ancora con la condizione: CPASS_T_REGOLE_SMISTAMENTO_RMS.codice_struttura = ALL
						listaRegole = affinaLeRegoleConAll(listaRegoleDaScremare) ;
						if(listaRegole.size()>1) {
							messaggioDescrizione = "RMS: "+rms.getRmsAnno()+"/"+rms.getRmsNumero()+"/"+rms.getProgressivoRiga()+": piu' regole smistamento trovate";
							inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_SMISTAMENTO_RMS.getCostante(),MsgCpassRms.RMSRMSE0024.getError().getCode(), messaggioDescrizione);
							xstErrore = Boolean.TRUE;
							regolaTrovata = Boolean.FALSE;
						}
						if(listaRegole.size()==1) {
							//Se si trova 1 record, SI ESCE CON SUCCESSO
							regolaTrovata = Boolean.TRUE;
						}

						if(listaRegole.size()==0) {
							//Se non si trova alcun record, SI ESCE CON ERRORE (REGOLA NON TROVATA)
							messaggioDescrizione = "RMS: "+rms.getRmsAnno()+"/"+rms.getRmsNumero()+"/"+rms.getProgressivoRiga()+": nessuna regola smistamento trovata";
							inserisciMessaggioElaborazione(elab,ValoreEnum.ERRORE_SMISTAMENTO_RMS.getCostante(),MsgCpassRms.RMSRMSE0024.getError().getCode(), messaggioDescrizione);
							xstErrore = Boolean.TRUE;
							regolaTrovata = Boolean.FALSE;
						}
					}
				}
			}

			//Stato statoDaEvadere =  decodificaDad.getStato(StatoRigaRmsEnum.DAE.getCostante(), TipoStatoEnum.RIGA_RMS.getCostante()).orElseThrow(() -> new NotFoundException("stato")) ;

			if(regolaTrovata) {
				final RigaRms rigaRms = rmsDad.findOneRiga(rms.getRigaRmsId());
				rigaRms.setStato(statoDaEvadere);
				rigaRms.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
				rigaRms.setDataModifica(new Date());
				final RegoleSmistamentoRms regola = listaRegole.get(0);
				if(rms.getRichiestaMagazzino()) {
					//rigaRms.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante())
					/*  Se RICHIESTA_MAGAZZINO = true, allora si considerano:
        				CPASS_T_REGOLE_SMISTAMENTO_RMS.centro_acquisto_id,
        				ed eventualmente CPASS_T_REGOLE_SMISTAMENTO_RMS.sezione_id, se valorizzata
					 */
					rigaRms.setSettoreAcquisto(regola.getSettoreAcquisto());
					rigaRms.setSezione(regola.getSezione());
				}else {
					/*
					se è valorizzato CPASS_T_REGOLE_SMISTAMENTO_RMS.centro_magazzino_id, si considera come output questo valore
			        ◦ altrimenti, si considerano come output: CPASS_T_REGOLE_SMISTAMENTO_RMS.centro_acquisto_id ed eventualmente CPASS_T_REGOLE_SMISTAMENTO_RMS.sezione_id, se valorizzata
					 */
					if(regola.getMagazzino()!= null) {
						rigaRms.setMagazzino(regola.getMagazzino());
					}else {
						rigaRms.setSettoreAcquisto(regola.getSettoreAcquisto());
						rigaRms.setSezione(regola.getSezione());
					}
				}
				rmsDad.updateRigaRms(rigaRms);

				final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
				statiRigaRms.setRigaRms(rigaRms);
				statiRigaRms.setStato(statoDaEvadere.getDescrizione());
				statiRigaRms.setDataModifica(new Date());
				statiRigaRms.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
				rmsDad.insertStatiRigaRms(statiRigaRms);
			}
		}

		if(numeroRigeDaSmistare == 0) {
			concludiElaborazione(elab, ConstantsCPassElaborazione.StatoEnum.CONCLUSO_SENZA_RIGHE_SMISTATE.getStatoDB());
		}else {
			if(xstErrore) {
				inserisciNotificaUtente( elab.getId().toString(), NotificaEnum.N0004.getCodice(), utenteBatch, null);
				concludiElaborazione(elab, ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB());
			}else {
				concludiElaborazione(elab, ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB());
			}
		}

	}

	private List<RegoleSmistamentoRms> affinaLeRegoleConCodiceStruttura(List<RegoleSmistamentoRms> listaRegole, String strutturaRichiedente) {
		final List<RegoleSmistamentoRms> listaRegoleConSettore = new ArrayList<>();
		for(final RegoleSmistamentoRms rsrms: listaRegole) {
			if(StringUtils.isNotEmpty(rsrms.getSettoreCodice())) {
				if(strutturaRichiedente.trim().equals(rsrms.getSettoreCodice().trim())) {
					listaRegoleConSettore.add(rsrms);
				}
			}
		}

		return listaRegoleConSettore;
	}

	private List<RegoleSmistamentoRms> affinaLeRegoleConCodiceStrutturaPadre(List<RegoleSmistamentoRms> listaRegole, String strutturaRichiedentePadre) {
		final List<RegoleSmistamentoRms> listaRegoleConSettore = new ArrayList<>();
		for(final RegoleSmistamentoRms rsrms: listaRegole) {
			if(StringUtils.isNotEmpty(rsrms.getSettoreCodice())) {
				if(strutturaRichiedentePadre.trim().equals(rsrms.getSettoreCodice().trim()) && rsrms.getTuttaLaStruttura()) {
					listaRegoleConSettore.add(rsrms);
				}
			}
		}
		return listaRegoleConSettore;
	}

	private List<RegoleSmistamentoRms> affinaLeRegoleConAll(List<RegoleSmistamentoRms> listaRegole) {
		final List<RegoleSmistamentoRms> listaRegoleConSettore = new ArrayList<>();
		for(final RegoleSmistamentoRms rsrms: listaRegole) {
			if(StringUtils.isNotEmpty(rsrms.getSettoreCodice())) {
				if(ValoreEnum.ALL.getCostante().equals(rsrms.getSettoreCodice().trim())) {
					listaRegoleConSettore.add(rsrms);
				}
			}
		}
		return listaRegoleConSettore;
	}


	/**
	 *
	 * @param uff
	 * @return Elaborazione
	 */
	private Elaborazione inizializzaElaborazione(UUID enteId) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setEntitaId(enteId.toString());
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB());
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.SMISTAMENTO_RMS.getCodice()).orElseThrow(() -> new NotFoundException("elaborazione tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione.setEnte(new Ente(enteId));

		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param elab
	 * @param statoElab
	 * @param urn
	 * @return Elaborazione
	 */
	private Elaborazione concludiElaborazione(Elaborazione elab, String statoElab) {
		log.info("concludiElaborazione", "statoElab " + statoElab);
		Elaborazione elaborazione = elaborazioneDad.loadElaborazione(elab.getId());
		elaborazione.setStato(statoElab);
		elaborazione.setEsito(statoElab);
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ConstantsCPassElaborazione.TipoEnum.SMISTAMENTO_RMS.getCodice()).orElseThrow(() -> new NotFoundException("elaborazione tipo"));
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}
	//N0004
	private void inserisciNotificaUtente(String identificativo, String codiceNotifica, Utente utente, Parametro param) {
		//TODO : inserire su db i testi notifica
		final TestoNotifica testoNotifica = isEntityPresent(() -> notificheDad.getTestoNotifica(codiceNotifica),"testoNotifica");
		final Notifica notifica = new Notifica();
		notifica.setEntita(identificativo);
		notifica.setFonte(ConstantsDecodifiche.NotificaFonteEnum.BATCH.getCodice());
		notifica.setEntitaTipo(ConstantsDecodifiche.NotificaTipoEntitaEnum.RMS.getCodice());
		notifica.setFlgGenerico(false);
		final Date now = new Date();
		//int intParamValue = Integer.parseInt(param.getValore());
		notifica.setDataInizio(now);
		notifica.setDataFine(null);
		//Map<String,Object> parametri = new HashMap<String, Object>();
		//parametri.put("anno",testataOrdine.getAnno());
		//parametri.put("numero",testataOrdine.getNumero());
		//notifica.setParametri(JsonUtility.serialize(parametri));
		notifica.setTestoNotifica(testoNotifica);
		notificheDad.saveNotificaUtente(notifica, utente);

	}
	/*
	public static void main (String[] ars) {
		System.out.println ("1234567sdfghjk".substring(0,4));
	}
	 */
}