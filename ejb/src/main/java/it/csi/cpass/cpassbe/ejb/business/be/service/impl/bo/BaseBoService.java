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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Base service implementation for the decodifica
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseBoService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for decodifica */
	//protected final DecodificaDad decodificaDad;
	ElaborazioneDad elaborazioneDad;
	ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	ElaborazioneTipoDad elaborazioneTipoDad;
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	protected BaseBoService(ConfigurationHelper configurationHelper,ElaborazioneDad elaborazioneDad, ElaborazioneMessaggioDad elaborazioneMessaggioDad, ElaborazioneTipoDad elaborazioneTipoDad) {
		super(configurationHelper);
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
		//this.decodificaDad = decodificaDad;
	}
	protected Elaborazione inizializzaElaborazioneold(Ente ente) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setData(new Date());
		//elaborazione.setEntitaId(entitaId);
		//Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.CARICAMENTO_FONTE_ESTERNA.getCostante());
		//elaborazione.setElaborazioneTipo(elaborazioneTipo.get());
		final String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
		elaborazione.setUtente(utenteId);
		elaborazione.setStato(CpassEnum.START.getCostante());
		elaborazione.setEnte(ente);
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}


	protected Elaborazione inizializzaElaborazione(UUID enteId, String elaborazioneTipoCodice, String entitaId,Integer numElabDiGiornata) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setEntitaId(entitaId);
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB());
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		elaborazione.setNumElaborazioneDiGiornata(numElabDiGiornata == null ? 0 : numElabDiGiornata );
		elaborazione.setDataElaborazioneDiGiornata("");
		final Optional<ElaborazioneTipo> elaborazioneTipoOptional = elaborazioneTipoDad.findByElaborazioneTipoCodice(elaborazioneTipoCodice);
		if (elaborazioneTipoOptional.isPresent()) {
			final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoOptional.get();
			elaborazione.setElaborazioneTipo(elaborazioneTipo);
		} else {
			throw new NotFoundException("elaborazione tipo");
		}

		elaborazione.setData(new Date());
		elaborazione.setEnte(new Ente(enteId));
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);

		return elaborazione;
	}

	protected void inserisciMessaggioElaborazione(Elaborazione elaborazione,String messaggiTipo,String messaggioCodice, String messaggioDescrizione) {
		final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setTipo(messaggiTipo);
		elaborazioneMessaggio.setCode(messaggioCodice);
		elaborazioneMessaggio.setDescrizione(messaggioDescrizione);
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}

	protected void concludiElaborazione(Elaborazione elaborazione,String stato,String esito) {
		elaborazione.setStato(stato);
		elaborazione.setEsito(esito);
		//update in tabella elaborazione
		elaborazioneDad.saveElaborazione(elaborazione);
		//log.error("concludiElaborazione", "concludiElaborazione");
	}

}
