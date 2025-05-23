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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.List;
import java.util.UUID;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneMessaggioDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneMessaggio;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Data Access Delegate for elaboraziones
 */
@ApplicationScoped
public class ElaborazioneMessaggioDad extends BaseDad {

	@Inject private CpassTElaborazioneMessaggioDao cpassTElaborazioneMessaggioDao;
	/**
	 * Inserts the elaborazioneMessaggio
	 * @param elaborazioneMessaggio the ElaborazioneMessaggio
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneMessaggio saveElaborazioneMessaggio(ElaborazioneMessaggio elaborazioneMessaggio) {
		CpassTElaborazioneMessaggio cpassTElaborazioneMessaggio = CpassMappers.ELABORAZIONE_MESSAGGIO.toEntity(elaborazioneMessaggio);
		cpassTElaborazioneMessaggio = cpassTElaborazioneMessaggioDao.save(cpassTElaborazioneMessaggio);
		cpassTElaborazioneMessaggioDao.flush();
		elaborazioneMessaggio.setId(cpassTElaborazioneMessaggio.getId());
		return elaborazioneMessaggio;
	}
	/**
	 * Updates the elaborazioneMessaggio
	 * @param elaborazioneMessaggio the ElaborazioneMessaggio
	 * @return the model instance
	 */
	//@Deprecated(forRemoval = true)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneMessaggio updateElaborazioneMessaggio(ElaborazioneMessaggio elaborazioneMessaggio) {
		final CpassTElaborazioneMessaggio cpassTElaborazioneMessaggio = CpassMappers.ELABORAZIONE_MESSAGGIO.toEntity(elaborazioneMessaggio);
		cpassTElaborazioneMessaggioDao.save(cpassTElaborazioneMessaggio);
		return elaborazioneMessaggio;
	}
	/**
	 * 
	 * @param entitaId
	 * @param elaborazioneTipoCodice
	 * @param enteId
	 * @return
	 */
	public List<ElaborazioneMessaggio> getMessaggioByUltimaElaborazione(String entitaId, String elaborazioneTipoCodice, UUID enteId) {
		entitaId = entitaId !=null && !entitaId.trim().equals("null") ? entitaId :null;
		final List<CpassTElaborazioneMessaggio> cpassTElaborazione =cpassTElaborazioneMessaggioDao.getMessaggiByUltimaElaborazione(entitaId, elaborazioneTipoCodice, enteId);
		return CpassMappers.ELABORAZIONE_MESSAGGIO.toModels(cpassTElaborazione);
	}


}
