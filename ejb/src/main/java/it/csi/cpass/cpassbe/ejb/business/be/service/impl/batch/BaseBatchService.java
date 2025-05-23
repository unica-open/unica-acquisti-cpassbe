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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
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

/**
 * Base service implementation for the common
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseBatchService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for common */
	protected final ElaborazioneDad elaborazioneDad;
	protected final ElaborazioneTipoDad elaborazioneTipoDad;
	protected final ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	protected final EnteDad enteDad;
	protected final CommonDad commonDad;
	protected final SystemDad systemDad;


	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	protected BaseBatchService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad	) {//, CommonDad commonDad) {
		super(configurationHelper);
		this.elaborazioneDad = elaborazioneDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
		this.elaborazioneMessaggioDad = elaborazioneMessaggioDad;
		this.enteDad =  enteDad;
		this.commonDad =  commonDad;
		this.systemDad =  systemDad;
	}




	protected Elaborazione inizializzaElaborazione(UUID enteId, String elaborazioneTipoCodice, String entitaId,Integer numElabDiGiornata,String dataElabDiGiornata) {
		Elaborazione elaborazione = new Elaborazione();
		elaborazione.setEntitaId(entitaId);
		elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB());
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		elaborazione.setNumElaborazioneDiGiornata(numElabDiGiornata == null ? 0 : numElabDiGiornata );
		elaborazione.setDataElaborazioneDiGiornata(dataElabDiGiornata);

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



	/**
	 *
	 * @param elaborazione
	 * @param tipo
	 * @param codice
	 * @param descrizione
	 */
	protected ElaborazioneMessaggio inserisciMessaggioErroreInElaborazione(ElaborazioneMessaggioDad elaborazioneMessaggioDad ,Elaborazione elaborazione, String tipo, String codice, String descrizione) {
		ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setTipo(tipo);
		elaborazioneMessaggio.setDescrizione(descrizione);
		elaborazioneMessaggio.setCode(codice);
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggio = elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
		return elaborazioneMessaggio;
	}

	/**
	 *
	 * @param elab
	 * @param statoElab
	 * @param urn
	 * @return Elaborazione
	 */
	protected Elaborazione concludiElaborazione(Elaborazione elab, String statoElab,String elaborazioneTipoCodice) {
		log.info("concludiElaborazione", "statoElab " + statoElab);
		Elaborazione elaborazione = elaborazioneDad.loadElaborazione(elab.getId());
		elaborazione.setStato(statoElab);

		String esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO.getStatoDB();
		if(statoElab.equals("KO")) {
			esito = ConstantsCPassElaborazione.StatoEnum.CONCLUSO_CON_ERRORE.getStatoDB();
		}
		elaborazione.setEsito(esito);
		elaborazione.setUtente(CpassEnum.UTENTE_BATCH.getCostante());
		final ElaborazioneTipo elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(elaborazioneTipoCodice).orElseThrow(() -> new NotFoundException("tipo elaborazione"));

		//elaborazioneTipo.setCodice(ConstantsCPassElaborazione.TipoEnum.RICEZIONE_DDT.getCodice());
		elaborazione.setElaborazioneTipo(elaborazioneTipo);
		elaborazione.setData(new Date());
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		return elaborazione;
	}

	/**
	 *
	 * @param ae
	 * @param elaborazione
	 * @param ddt
	 * @param xmlId
	 * @param xstErrore
	 */
	protected void inserisciMessaggioElaborazione(Elaborazione elaborazione,String messaggiTipo,String messaggioCodice, String messaggioDescrizione) {
		final ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();
		elaborazioneMessaggio.setTipo(messaggiTipo);
		elaborazioneMessaggio.setCode(messaggioCodice);
		elaborazioneMessaggio.setDescrizione(messaggioDescrizione);
		elaborazioneMessaggio.setElaborazione(elaborazione);
		elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);
	}



	protected  String writingFile(String pathStorico, String nomeFile, String xml) {
		//    	File statText = new File("D://TMP/"+nomeFile+".xml");
		//        try(FileOutputStream is = new FileOutputStream(statText);
		//        		OutputStreamWriter osw = new OutputStreamWriter(is);
		//        		Writer w = new BufferedWriter(osw)) {
		//            //Whatever the file path is.
		//            w.write(xml);
		//        } catch (IOException e) {
		//            System.err.println("Problem writing to the file xml");
		//        }

		final Path path = Paths.get(pathStorico, nomeFile+".xml");
		try {
			Files.writeString(path, xml, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			log.error("writingFile", "Error in file write: " + e.getMessage(), e);
			return null;
		}
		return path.normalize().toString();
	}
}
