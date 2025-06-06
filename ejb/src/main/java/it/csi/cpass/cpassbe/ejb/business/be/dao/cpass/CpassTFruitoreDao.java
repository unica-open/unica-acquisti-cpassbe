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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFruitore;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTFruitoreDao extends BaseEntityDao<Integer, CpassTFruitore> {

	/**
	 * Finds the entity by its CodiceFruitore and enteCodiceFiscale
	 * @param fruitoreCodice the codice fruitore
	 * @param fruitoreEnteCodiceFiscale the codice fiscale ente
	 * @return the entity
	 */
	Optional<CpassTFruitore> findByChiaveLogica(String fruitoreCodice, String fruitoreEnteCodiceFiscale);

}
