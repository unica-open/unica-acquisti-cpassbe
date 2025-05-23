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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche;

import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;

/**
 * Data Access Object interface for the entity CpassDPbaNuts
 */
public interface CpassDStatoDao extends BaseEntityDao<Integer, CpassDStato> {
	/**
	 * Finds an entity by its logical key
	 * @param statoCodice the stato codice
	 * @param statoTipo the stato tipo
	 * @return the entity
	 */
	Optional<CpassDStato> findByCodiceTipo(String statoCodice, String statoTipo);

	/**
	 * Retrieves the statos by tipo
	 * @param statoTipo the stato tipo
	 * @return the statos
	 */
	List<CpassDStato> getStatosByTipo(String statoTipo);

}
