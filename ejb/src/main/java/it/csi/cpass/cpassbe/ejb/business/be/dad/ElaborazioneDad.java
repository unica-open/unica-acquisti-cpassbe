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

import java.util.List;
import java.util.UUID;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Data Access Delegate for elaboraziones
 */
@ApplicationScoped
public class ElaborazioneDad extends BaseDad {

	@Inject private CpassTElaborazioneDao cpassTElaborazioneDao;
	
	/**
	 * Loads the elaboration
	 * @param key the elaboration key
	 * @return the elaboration
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Elaborazione loadElaborazione(Integer key) {
		return cpassTElaborazioneDao.findOne(key)
				.map(CpassMappers.ELABORAZIONE::toModel)
				.orElse(null);
	}
	
	/**
	 * Inserts the elaborazione
	 * @param elaborazione the Elaborazione
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Elaborazione saveElaborazione(Elaborazione elaborazione) {
		CpassTElaborazione cpassTElaborazione = CpassMappers.ELABORAZIONE.toEntity(elaborazione);
		cpassTElaborazione = cpassTElaborazioneDao.insert(cpassTElaborazione);
		elaborazione.setId(cpassTElaborazione.getId());
		return elaborazione;
	}
	
	/**
	 * Updates the elaborazione
	 * @param elaborazione the Elaborazione
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Elaborazione updateElaborazione(Elaborazione elaborazione) {
		CpassTElaborazione cpassTElaborazione = CpassMappers.ELABORAZIONE.toEntity(elaborazione);
		cpassTElaborazioneDao.update(cpassTElaborazione);
		return elaborazione;
	}
	
	public List<Elaborazione> getElaborazioniByEntityId(String entityId) {
		List<CpassTElaborazione> cpassTElaboraziones = cpassTElaborazioneDao.findByEntityId(entityId);
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaboraziones);
	}
	
}
