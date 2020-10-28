/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

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
		cpassTElaborazioneMessaggio = cpassTElaborazioneMessaggioDao.insert(cpassTElaborazioneMessaggio);
		elaborazioneMessaggio.setId(cpassTElaborazioneMessaggio.getId());
		return elaborazioneMessaggio;
	}
	/**
	 * Updates the elaborazioneMessaggio
	 * @param elaborazioneMessaggio the ElaborazioneMessaggio
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneMessaggio updateElaborazioneMessaggio(ElaborazioneMessaggio elaborazioneMessaggio) {
		CpassTElaborazioneMessaggio cpassTElaborazioneMessaggio = CpassMappers.ELABORAZIONE_MESSAGGIO.toEntity(elaborazioneMessaggio);
		cpassTElaborazioneMessaggioDao.update(cpassTElaborazioneMessaggio);
		return elaborazioneMessaggio;
	}
}
