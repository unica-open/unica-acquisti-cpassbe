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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTServizioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTServizio;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTServizio
 */
@ApplicationScoped
public class CpassTServizioDaoImpl extends BaseEntityDaoImpl<Integer, CpassTServizio> implements CpassTServizioDao {

	@Override
	public List<CpassTServizio> getByFruitoreId(Integer fruitoreId) {
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rfs.cpassTServizio FROM CpassRFruitoreServizio rfs ")
				.append(" WHERE (rfs.dataValiditaInizio IS NULL OR (rfs.dataValiditaInizio IS NOT NULL and rfs.dataValiditaInizio <= :now ) ) ")
				.append(" AND (rfs.dataValiditaFine   IS NULL OR (rfs.dataValiditaFine   IS NOT NULL and rfs.dataValiditaFine   >= :now ) ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rfs.cpassTFruitore.fruitoreId", "fruitoreId", fruitoreId);
		final TypedQuery<CpassTServizio> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
