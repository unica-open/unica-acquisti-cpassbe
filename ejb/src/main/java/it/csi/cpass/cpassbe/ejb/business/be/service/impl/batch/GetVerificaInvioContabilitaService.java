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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetVerificaInvioContabilitaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetVerificaInvioContabilitaResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoStatoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.batch.VerificaInvioEvasioneInContabilita;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.external.LeggiStatoElaborazioneDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetVerificaInvioContabilitaService extends BaseBatchService<GetVerificaInvioContabilitaRequest, GetVerificaInvioContabilitaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final TestataEvasioneDad testataDad;
	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetVerificaInvioContabilitaService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ExternalHelperLookup externalHelperLookup,
			TestataEvasioneDad testataDad,
			DecodificaDad decodificaDad) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.testataDad = testataDad;
		this.decodificaDad = decodificaDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getEnteCodice());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getEnteCodice()));
		final UUID enteId = enteOpt.orElseThrow(() -> new NotFoundException("ente")).getId();

		final Elaborazione elaborazione = new Elaborazione();
		final ElaborazioneTipo elaborazioneTipo = new ElaborazioneTipo();
		elaborazioneTipo.setCodice(ConstantsCPassElaborazione.TipoEnum.INVIO_EVASIONE.getCodice());
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setEnte(enteOpt.orElseThrow(() -> new NotFoundException("elaborazione")));
		final List<Elaborazione> listaDaElaborare = elaborazioneDad.postRicercaElaborazione(elaborazione);

		for(final Elaborazione el : listaDaElaborare){
			//VerificaInvioEvasioneInContabilita res = leggiStatoElaborazioneDocumento(el.getIdEsterno());
			final ExternalServiceResolveWrapper<LeggiStatoElaborazioneDocumentoHelper> handler = externalHelperLookup.lookup(LeggiStatoElaborazioneDocumentoHelper.class,enteId);
			final VerificaInvioEvasioneInContabilita res = invokeExternalService(handler, () -> handler.getInstance().leggiStatoElaborazioneDocumento(handler.getParams(), Integer.parseInt(el.getIdEsterno())));
			gestisciCodaElaborazione(el, res, new Ente(enteId));
		}
	}

	private void gestisciCodaElaborazione(Elaborazione el,VerificaInvioEvasioneInContabilita res, Ente ente) {
		final TestataEvasione testataEvasione = testataDad.getTestataEvasione(UUID.fromString(el.getEntitaId())).orElseThrow(() -> new NotFoundException("testata evasione"));
		final String statoElaborazionEsterno = res.getStatoElaborazionEsterno() != null ? res.getStatoElaborazionEsterno() : "";
		final String esitoEsterno = res.getEsitoEsterno() != null ?res.getEsitoEsterno() : "";

		// concluso con successo
		if(statoElaborazionEsterno.equalsIgnoreCase("CONCLUSA") && esitoEsterno.equalsIgnoreCase("SUCCESSO")) {
			el.setData(new Date());
			el.setStato(ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB());
			el.setEsito(ConstantsCPassElaborazione.EsitoEnum.SUCCESSO.getCodice());
			el.setEnte(ente);
			elaborazioneDad.saveElaborazione(el);
			//modifica evasione
			final Stato stato = decodificaDad.getStato(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante());
			testataEvasione.setStato(stato);
			testataDad.saveTestataEvasione(testataEvasione);
		} else if(statoElaborazionEsterno.equalsIgnoreCase(ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB()) || esitoEsterno.equalsIgnoreCase(ConstantsCPassElaborazione.EsitoEnum.FALLIMENTO.getCodice())) {
			final String codErrore = res.getCodiceErrore();
			final String descerrore = res.getDescrizioneErrore();
			el.setData(new Date());
			el.setStato(ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB());
			el.setEsito(ConstantsCPassElaborazione.EsitoEnum.FALLIMENTO.getCodice());
			el.setEnte(ente);
			elaborazioneDad.saveElaborazione(el);

			final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
			elaborazioneMessaggio.setCode(codErrore);
			elaborazioneMessaggio.setDescrizione(descerrore);
			elaborazioneMessaggio.setElaborazione(el);
			elaborazioneMessaggio.setTipo("E");
			elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);

			//modifica evasione
			final Stato stato = decodificaDad.getStato(StatoEvasioneEnum.AUTORIZZATA.getCostante(), TipoStatoEnum.EVASIONE.getCostante());
			testataEvasione.setDataInvioContabilita(null);
			testataEvasione.setStato(stato);
			testataDad.saveTestataEvasione(testataEvasione);
		} else if(statoElaborazionEsterno.equalsIgnoreCase(ConstantsCPassElaborazione.StatoEnum.AVVIATO.getStatoDB()) ) {
			// da riprocessare pi' avanti perchè ancora in satato avviato
			el.setData(new Date());
			el.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
			el.setEsito(ConstantsCPassElaborazione.StatoEnum.AVVIATO.getStatoDB());
			el.setEnte(ente);
			elaborazioneDad.saveElaborazione(el);
		}else {
			log.error("gestisciCodaElaborazione", "*************************************ATTENZIONE***********************************");
			log.error("gestisciCodaElaborazione", "******* Risultato da elaborazione esterna non censito verificare col fruitore del servizio");
			log.error("gestisciCodaElaborazione", "******* statoElaborazionEsterno: " + statoElaborazionEsterno );
			log.error("gestisciCodaElaborazione", "******* esitoEsterno: "            + esitoEsterno  );
			log.error("gestisciCodaElaborazione", "**********************************************************************************");
		}
	}
}