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
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetStoricizzaFileDdtRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetStoricizzaFileDdtResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.RiferimentoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoXML;

/**
 * GetStoricizzaFileDdtService
 */
public class GetStoricizzaFileDdtService extends BaseBatchService<GetStoricizzaFileDdtRequest, GetStoricizzaFileDdtResponse> {

	private final DocumentoTrasportoDad documentoTrasportoDad;
	public GetStoricizzaFileDdtService( ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			DocumentoTrasportoDad documentoTrasportoDad,
			DocumentoTrasportoRigaDad documentoTrasportoRigaDad
			) {

		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.documentoTrasportoDad = documentoTrasportoDad;
	}

	@Override
	protected void execute() {
		final Optional<Ente> enteOpt = enteDad.getEnteByCodice(request.getCodEnte());
		checkBusinessCondition(enteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Linea cliente non censita --> " + request.getCodEnte()));
		final UUID enteId = enteOpt.orElseThrow(() -> new NotFoundException("elaborazione")).getId();
		final Elaborazione elab = inizializzaElaborazione(enteId,ConstantsCPassElaborazione.TipoEnum.STORICO_FILE_DDT.getCodice(),enteId.toString() + new Date().toString(),null,"");


		final Date currentDate = new Date();
		final Long giorniStoricoNso = Long.parseLong(systemDad.getParametro(ChiaveEnum.GIORNI_STORICO_DDT.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore());
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.minusDays(giorniStoricoNso.longValue());


		final List<DocumentoTrasportoXML> lista = documentoTrasportoDad.findDDTFileByTestataEnteDaStoricizzare(enteId,Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));

		systemDad.getParametro(ChiaveEnum.UTENTE_BATCH.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore();
		final String pathBaseFromStoricizzazioneDDT = systemDad.getParametro(ChiaveEnum.STORICO_DDT.name(), RiferimentoEnum.BATCH.getCostante(), enteId).getValore();

		String xml = "";
		for(final DocumentoTrasportoXML el : lista) {
			xml = el.getFileXml();
			final String pathStorico = writingFile(pathBaseFromStoricizzazioneDDT,el.getDespatchAdviceId()+"_"+el.getDocumentoTrasporto().getId()/*+"_"+ el.getId()*/,xml);
			//TODO da scommentare commento per non perdermi i casi di test
			//el.setFileXml("");
			el.setDataSpostamento(new Date());
			el.setPathFile(pathStorico);
			documentoTrasportoDad.saveDdtXml(el);
		}
		concludiElaborazione(elab, "OK" ,ConstantsCPassElaborazione.TipoEnum.STORICO_FILE_DDT.getCodice());
	}
}