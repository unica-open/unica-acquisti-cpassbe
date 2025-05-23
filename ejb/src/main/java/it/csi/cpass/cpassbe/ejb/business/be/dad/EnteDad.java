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

import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Ente;

/**
 * Data Access Delegate for Ente
 */
@ApplicationScoped
public class EnteDad extends BaseDad {

	@Inject private CpassTEnteDao cpassTEnteDao;

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Ente> getEnteById(UUID uuid) {
		return cpassTEnteDao.findOne(uuid).map(CpassMappers.ENTE::toModel);
	}

	/**
	 * Find by codice fiscale
	 * @param codiceFiscale the codiceFiscale
	 * @return the model instance
	 */
	public Optional<Ente> getEnteByCodiceFiscale(String codiceFiscale){
		return cpassTEnteDao.findByCodiceFiscale(codiceFiscale).map(CpassMappers.ENTE::toModel);
	}
	/**
	 * 
	 * @param code
	 * @return
	 */
	public Optional<Ente> getEnteByCodice(String code) {
		return cpassTEnteDao.getEnteByCodice(code).map(CpassMappers.ENTE::toModel);
	}

}
