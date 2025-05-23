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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;

/**
 * Data Access Object interface for the entity CpassTEnte
 */
public interface CpassTEnteDao extends BaseAuditedEntityDao<UUID, CpassTEnte> {

	/**
	 *
	 * @param codiceFiscale
	 * @return
	 */
	Optional<CpassTEnte> findByCodiceFiscale(String codiceFiscale);

	Optional<CpassTEnte> getEnteByCodice(String code);

}
