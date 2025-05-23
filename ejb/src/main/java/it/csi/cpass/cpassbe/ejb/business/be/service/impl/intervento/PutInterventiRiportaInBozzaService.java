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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventiRiportaInBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventiRiportaInBozzaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.NotificaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class PutInterventiRiportaInBozzaService extends BaseInterventoService<PutInterventiRiportaInBozzaRequest, PutInterventiRiportaInBozzaResponse> {


	private final UtenteDad utenteDad;
	private final NotificheDad notificheDad;
	private final DecodificaDad decodificaDad;
	private List<Intervento> listaIntervento;
	private Stato stato;
	private final SystemDad systemDad;
	
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventiRiportaInBozzaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad,SystemDad systemDad,NotificheDad notificheDad) {
		super(configurationHelper, interventoDad);
		this.utenteDad = utenteDad;
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
		this.notificheDad =notificheDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		final Parametro isGestVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, ente.getId());
		final Parametro isVistoRagioneria = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),null, ente.getId());

		listaIntervento = request.getInterventi();
		stato           = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		for(final Intervento intervento :listaIntervento) {
			final Date now = new Date();
			intervento.setVersioneDefinitiva(Boolean.FALSE);
			// se non gestisco la versione definitiva essa è sempre true
			if(isGestVersioneDefinitiva == null || isGestVersioneDefinitiva.getValore().equalsIgnoreCase("false")) {
				intervento.setVersioneDefinitiva(Boolean.TRUE);
			}
			intervento.setDataRifiuto(now);
			intervento.setUtenteRifiuto(utenteConnesso);
			intervento.setStato(stato);
			intervento.setStatoXStorico(StatoInterventiEnum.RIPORTATO_IN_BOZZA.getCostante());

			//Se il parametro VISTO_RAGIONERIA vale FALSE, lasciare il campo a TRUE, altrimenti va impostato a FALSE
			if(isVistoRagioneria == null || isVistoRagioneria.getValore().equalsIgnoreCase("false")) {
				// in teoria questo passaggio è ridondante dato che l'intervento dovrebbe essere già true
				intervento.setVistoRagioneria(Boolean.TRUE);
			}else {
				intervento.setVistoRagioneria(Boolean.FALSE);
				intervento.setDataVistoRagioneria(null);
				intervento.setUtenteVistoRagioneria(null);
			}

			interventoDad.updateStatoIntervento(intervento,utenteConnesso);
			//inserisci Notifica RUP
			inserisciNotificaUtente(NotificaEnum.N0007.getCodice(),  intervento.getUtenteRup(),  intervento);

			//inserisci Notifica per utente Creazione
			final Optional<Intervento> interventoDaDB = interventoDad.getInterventoOpt(intervento.getId());
			if(interventoDaDB.isPresent()) {
				final Optional<Utente> utenteCreazione = utenteDad.getUtenteByCf(interventoDaDB.get().getUtenteCreazione(), false);
				if(utenteCreazione.isPresent()) {
					inserisciNotificaUtente(NotificaEnum.N0007.getCodice(),  utenteCreazione.get() ,  intervento);
				}
			}
		}

	}

	private void inserisciNotificaUtente(String codiceNotifica, Utente utente, Intervento intervento) {
		log.info("inserisciNotificaPerUtente","NOTIFICA  -> "+codiceNotifica+" per utente -> "+utente.getCodiceFiscale());
		final Date now = new Date();
		final GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		gc.add(Calendar.DAY_OF_MONTH, 20);
		final Notifica notifica = new Notifica();
		notifica.setFonte(ConstantsDecodifiche.NotificaFonteEnum.PBA.getCodice());
		notifica.setEntitaTipo(ConstantsDecodifiche.NotificaTipoEntitaEnum.PBA.getCodice());
		notifica.setEntita(intervento.getId().toString());
		notifica.setDataInizio(now);
		notifica.setDataFine(gc.getTime());
		notifica.setFlgGenerico(Boolean.FALSE);
		final Map<String,Object> parametri = new HashMap<>();
		parametri.put("cui_acquisto",intervento.getCui());
		parametri.put("motivazione",intervento.getMotivazioneRiportaInBozza());
		notifica.setParametri(JsonUtility.serialize(parametri));
		final TestoNotifica testoNotifica = isEntityPresent(() -> notificheDad.getTestoNotifica(codiceNotifica),"testoNotifica");
		notifica.setTestoNotifica(testoNotifica);
		notificheDad.saveNotificaUtente(notifica, utente);
	}

}
