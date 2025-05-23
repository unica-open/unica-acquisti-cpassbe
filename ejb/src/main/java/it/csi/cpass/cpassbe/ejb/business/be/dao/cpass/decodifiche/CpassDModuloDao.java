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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;

/**
 * Data Access Object interface for the entity CpassDModulo
 */
public interface CpassDModuloDao extends BaseEntityDao<Integer, CpassDModulo> {

	/**
	 * Gets by utente id and settore id
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the entities
	 */
	List<CpassDModulo> getByUtenteIdAndSettoreId(UUID utenteId, UUID settoreId);
	/**
	 * Gets by utente id and settore id
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @param checkDate the date on which the record is valid
	 * @return the entities
	 */
	List<CpassDModulo> getByUtenteIdAndSettoreId(UUID utenteId, UUID settoreId, Date checkDate);
}
