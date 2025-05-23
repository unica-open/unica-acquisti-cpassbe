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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetStoricizzaFileNsoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetStoricizzaFileNsoResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.RiferimentoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNsoXml;

/**
 * GetStoricizzaFileNsoService
 */
public class GetStoricizzaFileNsoService extends BaseBatchService<GetStoricizzaFileNsoRequest, GetStoricizzaFileNsoResponse> {

	private final DestinatarioOrdineDad destinatarioOrdineDad;

	public GetStoricizzaFileNsoService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			SystemDad systemDad
			) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);

		this.destinatarioOrdineDad = destinatarioOrdineDad;
	}

	@Override
	protected void execute() {
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		final UUID enteId = enteOpt.orElseThrow(() -> new NotFoundException("ente")).getId();
		final Date currentDate = new Date();
		final Elaborazione elab = inizializzaElaborazione(enteId,ConstantsCPassElaborazione.TipoEnum.STORICO_FILE_NSO.getCodice(),enteId + currentDate.toString(),null,"");
		final Long giorniStoricoNso = Long.parseLong(systemDad.getParametro(ChiaveEnum.GIORNI_STORICO_NSO.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore());
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.minusDays(giorniStoricoNso.longValue());
		final List<DestinatarioInvioNsoXml> list = destinatarioOrdineDad.findDestinatarioInvioNsoByEnteDaStoricizzare(enteId,Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		systemDad.getParametro(ChiaveEnum.UTENTE_BATCH.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore();
		final String pathBaseFromStoricizzazioneNso = systemDad.getParametro(ChiaveEnum.STORICO_NSO.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore();
		String xml = "";
		String metadati = "";
		String nomeFile = "";
		for(final DestinatarioInvioNsoXml el : list) {
			xml = el.getFileXml();
			metadati = el.getMetadatiXml();

			nomeFile = generaNomeFile("File_",el);

			final String pathStorico = writingFile(pathBaseFromStoricizzazioneNso,nomeFile,xml);
			if(metadati!=null && !metadati.trim().equals("")) {
				nomeFile = generaNomeFile("Meta_",el);
				writingFile(pathBaseFromStoricizzazioneNso,nomeFile,metadati);
			}
			//TODO da scommentare commento per non perdermi i casi di test
			el.setDataSpostamento(currentDate);
			el.setPathFile(pathStorico);
			destinatarioOrdineDad.saveDestinatarioInvioNsoXml(el);
		}
		concludiElaborazione(elab, "OK" ,ConstantsCPassElaborazione.TipoEnum.STORICO_FILE_NSO.getCodice());
	}

	/**
	 * @param el
	 * @return
	 */
	protected String generaNomeFile(String prefix,  DestinatarioInvioNsoXml el) {
		String data;
		data = el.getDataConsegna() == null || el.getDataConsegna().trim().equals("") ? el.getDataRicezione() : el.getDataConsegna() ;
		if(data!=null && !data.trim().equals("")) {
			data = "_" + data.replace("-", "").replace("/", "");
		}else {
			data = "";
		}
		//return "File_"+el.getDestinatarioInvioNso().getCbcId()+data;
		return prefix + el.getDestinatarioInvioNso().getCbcId() + data;
	}
}