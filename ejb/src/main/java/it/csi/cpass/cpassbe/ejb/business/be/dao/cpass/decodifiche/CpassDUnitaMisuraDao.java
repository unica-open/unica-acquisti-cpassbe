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
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;

/**
 * Data Access Object interface for the entity CpassDCpv
 */
public interface CpassDUnitaMisuraDao extends BaseEntityDao<Integer, CpassDUnitaMisura> {
	/**
	 * Finds the valid types
	 * @return the active types
	 */
	List<CpassDUnitaMisura> findValid();

	Optional<CpassDUnitaMisura> findValidByCodice(String codice);
}
