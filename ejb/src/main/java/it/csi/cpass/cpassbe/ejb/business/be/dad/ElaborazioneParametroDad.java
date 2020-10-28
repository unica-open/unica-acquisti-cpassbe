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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneParametroDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneParametro;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;

/**
 * Data Access Delegate for ElaborazioneParametro
 */
@ApplicationScoped
public class ElaborazioneParametroDad extends BaseDad {

	@Inject
	private CpassTElaborazioneParametroDao cpassTElaborazioneParametroDao;

	/**
	 * Inserts the elaborazioneParametro
	 * 
	 * @param elaborazioneParametro the ElaborazioneParametro
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneParametro saveElaborazioneParametro(ElaborazioneParametro elaborazioneParametro) {
		CpassTElaborazioneParametro cpassTElaborazioneParametro = CpassMappers.ELABORAZIONE_PARAMETRO.toEntity(elaborazioneParametro);
		cpassTElaborazioneParametro = cpassTElaborazioneParametroDao.insert(cpassTElaborazioneParametro);
		elaborazioneParametro.setId(cpassTElaborazioneParametro.getId());
		return elaborazioneParametro;
	}

	/**
	 * Updates the elaborazioneParametro
	 * 
	 * @param elaborazioneParametro the ElaborazioneParametro
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneParametro updateElaborazioneParametro(ElaborazioneParametro elaborazioneParametro) {
		CpassTElaborazioneParametro cpassTElaborazioneParametro = CpassMappers.ELABORAZIONE_PARAMETRO.toEntity(elaborazioneParametro);
		cpassTElaborazioneParametroDao.update(cpassTElaborazioneParametro);
		return elaborazioneParametro;
	}

	/**
	 * Load elaborazioneParametro
	 * @param elaborazioneId
	 * @param chiave
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ElaborazioneParametro getParametro(Integer elaborazioneId, String chiave) {
		CpassTElaborazioneParametro entity = cpassTElaborazioneParametroDao.getParametro(elaborazioneId, chiave);
		return CpassMappers.ELABORAZIONE_PARAMETRO.toModel(entity);
	}
	
	
}
