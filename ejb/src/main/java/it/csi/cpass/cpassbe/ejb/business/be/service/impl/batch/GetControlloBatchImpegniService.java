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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetControlloBatchImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetControlloBatchImpegniResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetControlloBatchImpegniService extends BaseBatchService<GetControlloBatchImpegniRequest, GetControlloBatchImpegniResponse> {

	Ente ente;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetControlloBatchImpegniService(ConfigurationHelper configurationHelper,
											ElaborazioneDad elaborazioneDad,
											ElaborazioneTipoDad elaborazioneTipoDad,
											ElaborazioneMessaggioDad elaborazioneMessaggioDad,
											EnteDad enteDad,
											CommonDad commonDad,
											SystemDad systemDad) {
		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
	}

	@Override
	protected void execute() {
		//final String methodName = "execute Get Controllo Batch Impegni";
		//final Elaborazione elabControllo = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.AGG_IMPEGNO_TMP.getStatoDB(),ente.getCodice());
		log.info("GetControlloBatchImpegniService", " chiamata batch controllo");
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		ente = enteOpt.orElseThrow(() -> new NotFoundException("ente"));
		List<Integer> listaElaborazioneTipoId = new ArrayList<>();
		listaElaborazioneTipoId.add(12);
		listaElaborazioneTipoId.add(13);
		listaElaborazioneTipoId.add(14);
		listaElaborazioneTipoId.add(15);
		Date dataPassata = DateUtility.stringToDate(request.getDataElab());
		List<Elaborazione> listaElab = elaborazioneDad.getElaborazioneByEnteAndType(ente.getId(), listaElaborazioneTipoId,dataPassata ,request.getNumelab());
		if(listaElab.size() !=4) {
			response.setRis("400");
			//new Exception("Errore su Batch Impegni controllare la tabella dei log sul sistema");
		}else {
			for(Elaborazione elab:listaElab) {
				log.info("execute", "elab.getEsito() "+elab.getEsito());
				if(elab.getStato()!=null && !elab.getStato().equalsIgnoreCase("OK")) {
					//concludiElaborazione(elabControllo, "KO con errore imprevisto controllare log" , ConstantsCPassElaborazione.StatoEnum.CONTROLLO_BATCH_IMPEGNI.getStatoDB() );
					//new Exception("Errore su Batch Impegni step da controllare " + elab.getElaborazioneTipo().getId() );
					response.setRis("400");
				}
			}
		}
		log.info("execute", "Fine caricamento IMP"  );
		//concludiElaborazione(elabControllo, "OK", ConstantsCPassElaborazione.StatoEnum.CONTROLLO_BATCH_IMPEGNI.getStatoDB() );

	}
}
