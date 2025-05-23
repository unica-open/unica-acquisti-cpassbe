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
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFruitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTServizioDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTServizio;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Fruitore;
import it.csi.cpass.cpassbe.lib.dto.Servizio;

/**
 * Data Access Delegate for fruitore
 */
@ApplicationScoped
public class FruitoreDad extends BaseDad {

	@Inject private CpassTFruitoreDao cpassTFruitoreDao;
	@Inject private CpassTServizioDao cpassTServizioDao;

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Fruitore> getFruitore(String fruitoreCodice, String fruitoreEnteCodiceFiscale) {
		return cpassTFruitoreDao.findByChiaveLogica(fruitoreCodice,fruitoreEnteCodiceFiscale).map(CpassMappers.FRUITORE::toModel);
	}

	/**
	 * Gets the Servizio by the fruitore
	 * @param fruitoreId
	 * @return the servizio instances
	 */
	public List<Servizio> getServizioByUtenteAndSettoreAndModulo(Integer fruitoreId) {
		final List<CpassTServizio> entities = cpassTServizioDao.getByFruitoreId(fruitoreId);
		return CpassMappers.SERVIZIO.toModels(entities);
	}

}
