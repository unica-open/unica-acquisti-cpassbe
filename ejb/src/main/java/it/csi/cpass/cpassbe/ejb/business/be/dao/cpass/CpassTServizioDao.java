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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTServizio;

/**
 * Data Access Object interface for the entity CpassTServizio
 */
public interface CpassTServizioDao extends BaseEntityDao<Integer, CpassTServizio> {

	/**
	 * Gets by fruitore id
	 * @param fruitoreId the fruitore id
	 * @return the entities
	 */
	List<CpassTServizio> getByFruitoreId(Integer fruitoreId);

}
